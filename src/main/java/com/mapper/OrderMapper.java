package com.mapper;

import com.pojo.Order;
import com.pojo.OrderDetails;
import org.apache.ibatis.annotations.Param;

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

    /**
     * <p> 查询storeId的time时间所有订单数量 </p>
     *
     * @param time    时间
     * @param storeId 店铺id
     * @return int
     * @since 2021/12/17
     */
    int selectOrderCount(@Param("time") String time,@Param("storeId") int storeId);

    /**
     * <p> 添加订单 </p>
     *
     * @param order 待添加的订单
     * @return int 订单id
     * @since 2021/12/17
     */
    int insertOrder(@Param("order") Order order);

    /**
     * <p> 添加订单细节 </p>
     *
     * @param orderDetails 订单细节
     * @return int
     * @since 2021/12/17
     */
    int insertOrderDetails(@Param("orderDetails") OrderDetails orderDetails);

    /**
     * <p> 根据店铺id查询订单 </p>
     *
     * @param storeId 店铺id
     * @return java.util.List<com.pojo.Order>
     * @since 2021/12/17
     */
    List<Order> selectOrderByStoreId(int storeId);
}
