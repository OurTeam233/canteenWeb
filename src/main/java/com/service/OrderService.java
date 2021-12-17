package com.service;

import com.mapper.DishesMapper;
import com.mapper.OrderMapper;
import com.pojo.Dishes;
import com.pojo.Order;
import com.pojo.OrderDetails;
import com.pojo.Student;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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
public class OrderService {
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
    public List<Order> selectOrderByStudentId(int studentId) {
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
    public List<Order> selectOrderByStoreId(int storeId) {
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
            String time = currentDate.getTime().toString();
            // 获取今天的订单数量
            int orderCount = orderMapper.selectOrderCount(time, order.getStoreId());
            // 构造order
            order.setOrderNumber(String.format("%03d", order.getStoreId()) + "-" + String.format("%04d", orderCount + 1));
            // 存入订单
            int insertOrder = orderMapper.insertOrder(order);
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
}
