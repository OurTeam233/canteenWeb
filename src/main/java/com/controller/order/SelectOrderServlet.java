package com.controller.order;

import com.alibaba.fastjson.JSON;
import com.pojo.Order;
import com.service.OrderService;
import com.util.RequestParameterWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author tang
 */
@WebServlet("/Order/Select")
public class SelectOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(SelectOrderServlet.class);
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
        // 如果是商家，那么直接返回该商户的店铺信息
        String userId = request.getParameter("userId");
        String userType = request.getParameter("userType");
        System.out.println(userId);
        System.out.println(userType);
        if ("1".equals(userType)) {
            orderList = orderService.selectOrderByStudentId(Integer.parseInt(userId));
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

}