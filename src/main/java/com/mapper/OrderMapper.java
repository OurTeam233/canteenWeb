package com.mapper;

import com.pojo.Order;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.mapper
 * @date 2021/12/8 8:35
 **/
public interface OrderMapper {
    /**
     * <p> 根据显示id查询所有订单号 </p>
     *
     * @param StudentId 学生id
     * @return java.util.List<com.pojo.Order>
     * @since 2021/12/16
     */
    List<Order> selectOrderByStudentId(int studentId);
}
