package com.controller.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Order;
import com.pojo.OrderDetails;
import com.service.OrderService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author tang
 */
@WebServlet("/Order/Insert")
public class InsertOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(InsertOrderServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 接收参数
        String userId = request.getParameter("userId");
        String userType = request.getParameter("userType");
        // 获取请求体参数
        BufferedReader reader = request.getReader();
        String postBody = IOUtils.toString(reader);
        // 将参数转换为JSON对象
        JSONObject jsonUserInfo = JSON.parseObject(postBody).getJSONObject("order");
        // 构造order对象
        Order order = JSON.parseObject(String.valueOf(jsonUserInfo), Order.class);
        order.setStudentId(Integer.valueOf(userId));
        // 构造orderDetails对象
        List<OrderDetails> orderDetails = JSON.parseArray(jsonUserInfo.getString("dishes"), OrderDetails.class);
        // 如果是学生
        if ("1".equals(userType)) {
            // 创建服务对象
            OrderService orderService = new OrderService();
            order.setStudentId(Integer.valueOf(userId));
            int insertOrder = orderService.insertOrder(order, orderDetails);
            if (insertOrder > 0) {
                out.print("{\"success\":\"true\"}");
                return;
            }
        }
        // 返回结果集
        out.print("{\"success\":\"false\"}");
    }

}