package com.controller.user;

import com.alibaba.fastjson.JSON;
import com.pojo.Result;
import com.service.UserService;
import com.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author tang
 */
@WebServlet("/Login")
public class UserLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServlet.class);

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
        // 获取ua和ip
        String requestHeader = request.getHeader("User-Agent");
        String remoteAddr = request.getRemoteAddr();
        // 获取用户
        int userId = userService.selectUserStore(username, password, Integer.parseInt(userType));
        // 生成结果集
        Result result = new Result();
        // 初始化结果集
        result.setSuccess(false);
        if (userId != 0 || "0".equals(userType)) {
            // 如果存在用户
            result.setSuccess(true);
            // 生成jwt
            String jwtToken = JwtUtil.generateToken(userId + "",  userType, requestHeader, remoteAddr);
            result.setToken(jwtToken);
        }
        // 返回结果
        String storeListString = JSON.toJSONString(result);
        //输出
        out.print(storeListString);
    }

}