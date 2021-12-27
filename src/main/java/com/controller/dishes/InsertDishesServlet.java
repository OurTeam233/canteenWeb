package com.controller.dishes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Dishes;
import com.pojo.Result;
import com.service.DishesService.DishesServiceImpl;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Dishes/Insert")
public class InsertDishesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        // 获取参数
        BufferedReader reader = request.getReader();
        String postBody = IOUtils.toString(reader);
        JSONObject jsonObject = JSON.parseObject(postBody);
        String userId = request.getParameter("userId");
        // 将jsonObject转换为其他对象
        Dishes dishes = JSON.toJavaObject(jsonObject.getJSONObject("dishes"), Dishes.class);
        String dishesTypeName = jsonObject.getJSONObject("dishes").getString("dishesTypeName");
        // 创建服务对象
        DishesServiceImpl dishesServiceImpl = new DishesServiceImpl();
        // 调用方法
        int insertId = dishesServiceImpl.insertDishes(dishes, dishesTypeName, userId);
        // 创建结果集
        Result result = new Result();
        result.setSuccess(insertId > 0);
        String jsonString = JSON.toJSONString(result);
        //输出
        out.print(jsonString);
    }

}