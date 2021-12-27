package com.service.OrderService;

import com.pojo.Order;
import com.pojo.OrderDetails;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.service.OrderService
 * @interfaceName OrderService
 * @date 2021/12/27 19:53
 **/
public interface OrderService {

    /**
     * <p> 根据学生id查询所有订单信息 </p>
     *
     * @param studentId 学生id
     * @return java.util.List<com.pojo.Order>
     * @since 2021/12/11
     */
    List<Order> selectOrderByStudentId(String studentId);

    /**
     * <p> 根据店铺id查询所有订单信息 </p>
     *
     * @param storeId 店铺id
     * @return java.util.List<com.pojo.Order>
     * @since 2021/12/11
     */
    List<Order> selectOrderByStoreId(String storeId);

    /**
     * <p> 添加订单 </p>
     *
     * @param order      订单信息
     * @param dishesList 订单详情
     * @return int
     * @since 2021/12/17
     */
    int insertOrder(Order order, List<OrderDetails> dishesList);

    /**
     * <p> 通过id查询订单 </p>
     *
     * @param orderId 订单id
     * @return com.pojo.Order
     * @since 2021/12/19
     */
    Order selectOrderById(String orderId);

    /**
     * <p> 按订单id更新订单状态 </p>
     *
     * @param orderId 订单id
     * @param type 订单状态
     * @return int 被更新的行数
     * @since 2021/12/19
     */
    int updateOrderById(String orderId, String type);

    /**
     * <p> 根据订单id取消订单 </p>
     *
     * @param orderId 订单id
     * @return boolean true 取消成功
     * @since 2021/12/20
     */
    boolean cancelOrderById(String orderId);
}
