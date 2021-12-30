package com.controller.post;

import com.alibaba.fastjson.JSON;
import com.pojo.Post;
import com.service.PostService.PostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Post/Select")
public class SelectPostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        String type = request.getParameter("type");
        // 调用帖子服务
        PostServiceImpl postService = new PostServiceImpl();
        // 查询所有帖子
        List<Post> posts = postService.selectPost(type);
        // 序列化
        String jsonString = JSON.toJSONString(posts);
        //输出
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}