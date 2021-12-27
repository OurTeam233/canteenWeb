package com.controller.store;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Store;
import com.service.StoreService.StoreServiceImpl;
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
@WebServlet("/Store/Like")
public class SearchLikeStoreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(SearchLikeStoreServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 创建服务对象
        StoreServiceImpl storeServiceImpl = new StoreServiceImpl();
        // 尝试获取参数
        BufferedReader reader = request.getReader();
        String postBody = IOUtils.toString(reader);
        JSONObject jsonObject = JSON.parseObject(postBody);
        String keyword = jsonObject.getString("keyword");
        // 调用服务
        List<Store> storeList = storeServiceImpl.likeSelectStore(keyword);
        // 返回结果
        String storeListString = JSON.toJSONString(storeList);
        out.print(storeListString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}