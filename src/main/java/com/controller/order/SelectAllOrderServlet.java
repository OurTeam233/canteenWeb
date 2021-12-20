package com.controller.order;

import com.alibaba.fastjson.JSON;
import com.pojo.Order;
import com.pojo.Store;
import com.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * @author tang
 */
@WebServlet("/Order/Select")
public class SelectAllOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(SelectAllOrderServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 初始化
        OrderService orderService = new OrderService();
        List<Order> orderList = null;
        // 获取参数
        String userId = request.getParameter("userId");
        String userType = request.getParameter("userType");
        // 根据不同的用户类型，获取不同的订单
        if ("1".equals(userType)) {
            orderList = orderService.selectOrderByStudentId(userId);
            updateOrderType(orderList);
        } else if ("2".equals(userType)) {
            orderList = orderService.selectOrderByStoreId(userId);
            updateOrderType(orderList);
        }
        // 返回结果集
        if (orderList != null) {
            String orderListString = JSON.toJSONString(orderList);
            out.print(orderListString);
        } else {
            // 如果为空返回 status:0
            out.println("{\"status\":\"0\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * <p> 更新订单状态 </p>
     *
     * @param orderList 订单列表
     * @since 2021/12/20
     */
    public void updateOrderType(List<Order> orderList) {
        Date current = new Date();
        OrderService orderService = new OrderService();
        for (Order order : orderList) {
            boolean operable = order.getType() == 0 || order.getType() == 1;
            if (current.after(order.getOrderTime()) && operable) {
                orderService.updateOrderById(order.getId() + "", "3");
                order.setType(3);
            }
        }
    }
}