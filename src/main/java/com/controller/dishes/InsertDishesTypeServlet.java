package com.controller.dishes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Order;
import com.pojo.Result;
import com.service.DishesService;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(value = "/Dishes/Type/Insert")
public class InsertDishesTypeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 获取参数
        BufferedReader reader = request.getReader();
        String postBody = IOUtils.toString(reader);
        // 将参数转换为JSON对象
        JSONObject jsonObject = JSON.parseObject(postBody);
        String name = jsonObject.getString("name");
        // 创建服务对象
        DishesService dishesService = new DishesService();
        // 调用方法
        int dishesTypeId = dishesService.insertDishesType(name);
        // 创建结果集
        Result result = new Result();
        result.setSuccess(dishesTypeId > 0);
        result.setId(dishesTypeId);
        String jsonString = JSON.toJSONString(result);
        //输出
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}