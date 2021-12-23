package com.controller.store;

import com.alibaba.fastjson.JSON;
import com.pojo.Result;
import com.service.StoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(value = "/Store/Update")
public class UpdateStoreTypeServlet extends HttpServlet {

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
        String status = request.getParameter("status");
        // 创建服务对象
        StoreService storeService = new StoreService();
        boolean updatable = storeService.updateStoreStatus(userId, status);
        // 返回结果集
        Result result = new Result();
        result.setSuccess(updatable);
        String jsonString = JSON.toJSONString(result);
        //输出
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}