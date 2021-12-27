package com.controller.order;

import com.alibaba.fastjson.JSON;
import com.pojo.Result;
import com.service.OrderService.OrderServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/Order/Update")
public class UpdateOrderTypeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 获取参数
        String orderId = request.getParameter("orderId");
        String type = request.getParameter("type");
        // 创建订单服务对象
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        // 更新订单状态
        int updateOrderById = orderServiceImpl.updateOrderById(orderId, type);
        // 返回结果
        Result result = new Result();
        result.setSuccess(updateOrderById > 0);
        //输出
        out.println(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}