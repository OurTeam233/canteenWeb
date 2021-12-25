package com.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Order;
import com.pojo.Result;
import com.pojo.Store;
import com.pojo.User;
import com.service.UserService;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/User/Insert")
public class InsertUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        String userType = request.getParameter("userType");
        // 获取请求体内信息
        BufferedReader reader = request.getReader();
        String postBody = IOUtils.toString(reader);
        // 获取店铺对象和用户对象
        // 将参数转换为JSON对象
        JSONObject storeJsonObject = JSON.parseObject(postBody).getJSONObject("store");
        JSONObject userJsonObject = JSON.parseObject(postBody).getJSONObject("user");
        // 构造order对象
        Store store = JSON.parseObject(JSON.toJSONString(storeJsonObject), Store.class);
        User user = JSON.parseObject(JSON.toJSONString(userJsonObject), User.class);
        // 如果是管理员，继续操作
        // 创建结果集
        Result result = new Result();
        result.setSuccess(false);
        if ("0".equals(userType)) {
            UserService userService = new UserService();
            boolean insertable = userService.insertUser(user, store);
            result.setSuccess(insertable);
        }
        // 返回结果
        out.println(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}