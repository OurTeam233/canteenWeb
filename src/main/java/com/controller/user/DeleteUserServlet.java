package com.controller.user;

import com.alibaba.fastjson.JSON;
import com.pojo.Result;
import com.service.UserService.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/User/Delete")
public class DeleteUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        String userType = request.getParameter("userType");
        String id = request.getParameter("id");
        // 如果是管理员，继续操作
        // 创建结果集
        Result result = new Result();
        result.setSuccess(false);
        if ("0".equals(userType)) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            boolean deletable = userServiceImpl.deleteUserById(id);
            result.setSuccess(deletable);
        }
        // 返回结果
        out.println(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}