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
@WebServlet("/Store")
public class SelectStoreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(SelectStoreServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 创建服务对象
        StoreService storeService = new StoreService();
        // 创建店铺对象
        Store store = null;
        // 尝试获取店铺参数
        String storeId = request.getParameter("storeId");
        if (storeId != null) {
            // 根据店铺id查询店铺信息
            store = storeService.selectByStoreId(Integer.parseInt(storeId));
        } else {
            // 如果是商家，那么直接返回该商户的店铺信息
            String userId = request.getParameter("userId");
            String userType = request.getParameter("userType");
            if ("2".equals(userType)) {
                store = storeService.selectByStoreId(Integer.parseInt(userId));
            }
        }
        // 返回结果
        // 判断店铺信息是否为空
        if (store != null) {
            String storeString = JSON.toJSONString(store);
            out.println(storeString);
        } else {
            // 如果为空返回 status:0
            out.println("{\"status\":\"0\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}