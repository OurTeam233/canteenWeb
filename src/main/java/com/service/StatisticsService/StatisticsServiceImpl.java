package com.service.StatisticsService;

import com.mapper.DishesMapper;
import com.mapper.OrderMapper;
import com.pojo.Dishes;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p> 对dishes表进行操作 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service
 * @className DishesService
 * @date 2021/12/8 11:21
 * @TODO
 **/
public class StatisticsServiceImpl implements StatisticsService{
    /**
     * 创建SqlSessionFactory工厂
     */
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * <p> 根据店铺id查询第二天的的菜品数量 </p>
     *
     * @param storeId 店铺id
     * @return java.util.List<com.pojo.Dishes>
     * @since 2021/12/25
     */
    @Override
    public List<Dishes> selectDishesNextDayStatus1ByStoreId(String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 创建date为第二天早上
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date time = calendar.getTime();
            // 执行sql并返回结果
            return dishesMapper.selectDishesNextDayStatus1ByStoreId(storeId, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    /**
     * <p> 根据店铺id查询总销售额 </p>
     *
     * @param storeId 店铺id
     * @return Integer 销售额
     * @since 2021/12/25
     */
    @Override
    public Integer selectTotalPrice(String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            return dishesMapper.selectTotalPrice(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * <p> 查询昨天预定今天的菜品数量 </p>
     *
     * @param storeId 店铺id
     * @return java.util.List<com.pojo.Dishes>
     * @since 2021/12/28
     */
    @Override
    public List<Dishes> selectDishesTodayStatue1ByStoreId(String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 创建date为第二天早上
            Calendar calendar = Calendar.getInstance();
            Date curTime = calendar.getTime();
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date nextTime = calendar.getTime();
            // 执行sql并返回结果
            return dishesMapper.selectDishesTodayStatus1ByStoreId(storeId, curTime, nextTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * <p> 查询违规订单数量 </p>
     *
     * @param storeId 店铺id
     * @return java.lang.Integer
     * @since 2021/12/29
     */
    public Integer selectIllegalOrder(String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            // 执行sql并返回结果
            return orderMapper.selectIllegalOrder(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p> 查询所有订单数量 </p>
     *
     * @param storeId 店铺id
     * @return java.lang.Integer
     * @since 2021/12/29
     */
    public Integer selectOrderTotal(String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            // 执行sql并返回结果
            return orderMapper.selectOrderTotal(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p> 查询店铺菜品数量的统计 </p>
     *
     * @param storeId 店铺id
     * @return java.lang.Integer
     * @since 2021/12/29
     */
    public List<Dishes> selectStoreDishes(String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            return dishesMapper.selectStoreDishes(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * <p> 查询店铺的总客户数量 </p>
     *
     * @param storeId 店铺id
     * @return java.lang.Integer
     * @since 2021/12/29
     */
    public Integer totalStudent(String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            // 执行sql并返回结果
            return orderMapper.selectTotalStudent(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p> 查询所有菜品数量 </p>
     *
     * @param storeId 店铺id
     * @return java.lang.Integer
     * @since 2021/12/29
     */
    public Integer TotalDishes(String storeId) {
        try (// 创建连接
             SqlSession sqlSession = sqlSessionFactory.openSession()
        ) {
            // 创建映射关系
            DishesMapper dishesMapper = sqlSession.getMapper(DishesMapper.class);
            // 执行sql并返回结果
            return dishesMapper.selectTotalDishes(storeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
