package com.controller.order;

import com.alibaba.fastjson.JSON;
import com.pojo.Order;
import com.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@WebServlet(value = "/Order/One")
public class SelectOneOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 创建服务对象
        OrderService orderService = new OrderService();
        // 获取参数
        String userId = request.getParameter("orderId");
        // 按id查询
        Order order = orderService.selectOrderById(Integer.parseInt(userId));
        // 返回结果集
        if (order != null) {
            String orderString = JSON.toJSONString(order);
            out.print(orderString);
        } else {
            // 如果为空返回 status:0
            out.println("{\"status\":\"0\"}");
        }

        //输出
        out.print("");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}