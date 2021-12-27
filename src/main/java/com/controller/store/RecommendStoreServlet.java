package com.controller.store;

import com.alibaba.fastjson.JSON;
import com.pojo.Store;
import com.service.StoreService.StoreServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Store/Recommend")
public class RecommendStoreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        String userId = request.getParameter("userId");
        // 创建服务对象
        StoreServiceImpl storeServiceImpl = new StoreServiceImpl();
        // 调用服务对象的方法
        List<Store> stores = storeServiceImpl.recommendStore(userId);
        // 返回结果
        String jsonString = JSON.toJSONString(stores);

        //输出
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}