package com.controller.Store;

import com.alibaba.fastjson.JSON;
import com.pojo.Store;
import com.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author tang
 */
@WebServlet("/StoreList")
public class SelectStoreListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(SelectStoreListServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 创建服务对象
        StoreService storeService = new StoreService();
        // 尝试获取canteenId参数
        String canteenId = request.getParameter("canteenId");
        // 尝试获取storeId参数
        String tagsId = request.getParameter("tagsId");
        // 根据请求参数判断调用方法
        List<Store> storeList = null;
        if (canteenId != null) {
            // 获取所有食堂
            storeList = storeService.selectAllByCanteenId(Integer.parseInt(canteenId));
        } else if (tagsId != null) {
            // 获取所有食堂
            storeList = storeService.selectAllByTagsId(Integer.parseInt(tagsId));
        } else {
            storeList = storeService.selectAll();
        }
        // 返回结果
        String storeListString = JSON.toJSONString(storeList);
        out.print(storeListString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}