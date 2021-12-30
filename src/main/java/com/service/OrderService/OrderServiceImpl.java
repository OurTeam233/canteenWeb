package com.service.OrderService;

import com.mapper.OrderMapper;
import com.pojo.Order;
import com.pojo.OrderDetails;
import com.util.ImageUtil;
import com.util.QrCodeUtils;
import com.util.SqlSessionFactoryUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p> 订单服务类 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service
 * @className OrderService
 * @date 2021/12/16 17:13
 **/
public class OrderServiceImpl implements OrderService{
    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * <p> 根据学生id查询所有订单信息 </p>
     *
     * @param studentId 学生id
     * @return java.util.List<com.pojo.Order>
     * @since 2021/12/11
     */
    @Override
    public List<Order> selectOrderByStudentId(String studentId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            // 执行sql并返回结果
            return orderMapper.selectOrderByStudentId(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * <p> 根据店铺id查询所有订单信息 </p>
     *
     * @param storeId 店铺id
     * @return java.util.List<com.pojo.Order>
     * @since 2021/12/11
     */
    @Override
    public List<Order> selectOrderByStoreId(String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            // 执行sql并返回结果
            return orderMapper.selectOrderByStoreId(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * <p> 添加订单 </p>
     *
     * @param order      订单信息
     * @param dishesList 订单详情
     * @return int
     * @since 2021/12/17
     */
    @Override
    public int insertOrder(Order order, List<OrderDetails> dishesList) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            // 获取今天的开始时间
            Calendar currentDate = Calendar.getInstance();
            currentDate.set(Calendar.HOUR_OF_DAY, 0);
            currentDate.set(Calendar.MINUTE, 0);
            currentDate.set(Calendar.SECOND, 0);
            long time = currentDate.getTime().getTime();
            // 获取今天的订单数量
            int orderCount = orderMapper.selectOrderCount(String.valueOf(time), order.getStoreId());
            // 构造order
            order.setOrderNumber(String.format("%03d", order.getStoreId()) + "-" + String.format("%04d", orderCount + 1));
            order.setStatus(DateUtils.isSameDay(order.getOrderTime(), order.getTime()) ? 0 : 1);
            // 存入订单
            int insertOrder = orderMapper.insertOrder(order);
            // 生成二维码，并设置二维码路径
            String content = order.getId() + "";
            BufferedImage bufferedImage = QrCodeUtils.createImage(content, null, false);
            ImageUtil imageUtil = new ImageUtil();
            orderMapper.updateOrderQrCodeById(order.getId() + "", imageUtil.bufferedImageToUrl(bufferedImage));
            // 存入订单细节
            for (OrderDetails orderDetails : dishesList) {
                orderDetails.setOrderId(order.getId());
                int insertOrderDetails = orderMapper.insertOrderDetails(orderDetails);
                if (insertOrderDetails == 0) {
                    return 0;
                }
            }
            // 提交事务
            sqlSession.commit();
            return insertOrder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p> 通过id查询订单 </p>
     *
     * @param orderId 订单id
     * @return com.pojo.Order
     * @since 2021/12/19
     */
    @Override
    public Order selectOrderById(String orderId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            // 执行sql并返回结果
            return orderMapper.selectOrderById(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p> 按订单id更新订单状态 </p>
     *
     * @param orderId 订单id
     * @param type 订单状态
     * @return int 被更新的行数
     * @since 2021/12/19
     */
    @Override
    public int updateOrderById(String orderId, String type) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            // 执行sql并返回结果
            int updateOrderById = orderMapper.updateOrderById(orderId, type);
            sqlSession.commit();
            return updateOrderById;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p> 根据订单id取消订单 </p>
     *
     * @param orderId 订单id
     * @return boolean true 取消成功
     * @since 2021/12/20
     */
    @Override
    public boolean cancelOrderById(String orderId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            // 执行sql并返回结果
            Order order = orderMapper.selectOrderById(orderId);
            // 获取今天的时间和订单的取餐时间
            Date orderTime = order.getOrderTime();
            Date currentTime = new Date();
            // 判断是否能取消订单
            if (currentTime.getTime() > orderTime.getTime() - 1000 * 60 * 30) {
                return false;
            } else {
                // 执行sql并返回结果
                boolean cancelable = orderMapper.updateOrderById(orderId, "4") > 0;
                sqlSession.commit();
                return cancelable;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
