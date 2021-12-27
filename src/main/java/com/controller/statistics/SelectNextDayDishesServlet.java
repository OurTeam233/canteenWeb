package com.controller.statistics;

import com.alibaba.fastjson.JSON;
import com.pojo.Dishes;
import com.service.StatisticsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@WebServlet("/Statistic/Dishes/NextDay")
public class SelectNextDayDishesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 获取参数
        String userId = request.getParameter("userId");
        // 调用service
        StatisticsService statisticsService = new StatisticsService();
        List<Dishes> dishesList = statisticsService.selectDishesStatus1ByStoreId(userId);
        // 创建json对象
        String jsonString = JSON.toJSONString(dishesList);
        //输出
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}