package com.controller.statistics;

import com.alibaba.fastjson.JSON;
import com.pojo.Dishes;
import com.service.StatisticsService.StatisticsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Statistic/StoreDishes")
public class SelectStoreDishesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 获取参数
        String storeId = request.getParameter("userId");
        // 调用service
        StatisticsServiceImpl statisticsServiceImpl = new StatisticsServiceImpl();
        List<Dishes> dishesList = statisticsServiceImpl.selectStoreDishes(storeId);
        // 返回结果
        String jsonString = JSON.toJSONString(dishesList);
        out.println(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}