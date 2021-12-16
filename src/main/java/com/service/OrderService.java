package com.service;

import com.mapper.DishesMapper;
import com.mapper.OrderMapper;
import com.pojo.Dishes;
import com.pojo.Order;
import com.pojo.Student;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collections;
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
}
