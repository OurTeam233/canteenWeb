package com.controller.order;

import com.alibaba.fastjson.JSON;
import com.pojo.Result;
import com.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet("/Order/Cancel")
public class CancelOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        String orderId = request.getParameter("orderId");
        // 创建服务对象
        OrderService orderService = new OrderService();
        boolean cancelable = orderService.cancelOrderById(orderId);
        // 创建结果集
        Result result = new Result();
        result.setSuccess(cancelable);
        String jsonString = JSON.toJSONString(result);
        //输出
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}