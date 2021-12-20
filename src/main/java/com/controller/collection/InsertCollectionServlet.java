package com.controller.collection;

import com.alibaba.fastjson.JSON;
import com.pojo.Collections;
import com.pojo.Result;
import com.service.CollectionsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(value = "/Collection/Insert")
public class InsertCollectionServlet extends HttpServlet {

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
        String storeId = request.getParameter("storeId");
        // 创建服务对象
        CollectionsService collectionsService = new CollectionsService();
        // 调用方法
        boolean insertable = false;
        if (!collectionsService.selectStudentCollectStore(userId, storeId)) {
            insertable = collectionsService.insertStudentCollectStore(userId, storeId);
        }
        // 创建结果集
        Result result = new Result();
        result.setSuccess(insertable);
        String jsonString = JSON.toJSONString(result);
        //输出
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}