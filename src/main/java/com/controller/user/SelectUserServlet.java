package com.controller.user;

import com.alibaba.fastjson.JSON;
import com.pojo.Result;
import com.pojo.Store;
import com.pojo.User;
import com.service.StoreService;
import com.service.UserService;
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
@WebServlet("/User/Login")
public class SelectUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(SelectUserServlet.class);

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
        // 创建服务对象
        UserService userService = new UserService();
        // 尝试获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        // 获取用户
        Result result = userService.selectUser(username, password, Integer.parseInt(userType));
        String storeListString = JSON.toJSONString(result);
        //输出
        out.print(storeListString);
    }

}