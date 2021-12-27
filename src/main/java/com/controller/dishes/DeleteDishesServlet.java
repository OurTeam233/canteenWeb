package com.controller.dishes;

import com.alibaba.fastjson.JSON;
import com.pojo.Result;
import com.service.DishesService.DishesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Dishes/Delete")
public class DeleteDishesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 获取参数
        String dishesId = request.getParameter("dishesId");
        // 创建服务对象
        DishesServiceImpl dishesServiceImpl = new DishesServiceImpl();
        // 调用方法
        boolean deletable = dishesServiceImpl.delDishesById(dishesId);
        // 创建结果集
        Result result = new Result();
        result.setSuccess(deletable);
        String jsonString = JSON.toJSONString(result);
        //输出
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}