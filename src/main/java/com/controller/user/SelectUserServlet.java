package com.controller.user;

import com.alibaba.fastjson.JSON;
import com.pojo.User;
import com.service.UserService.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/User/Select")
public class SelectUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        String userType = request.getParameter("userType");
        if ("0".equals(userType)) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            List<User> userList = userServiceImpl.selectUser();
            // 返回结果
            String toJSONString = JSON.toJSONString(userList);
            //输出
            out.print(toJSONString);
        } else {
            out.println("{\"status\":\"0\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}