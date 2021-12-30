package com.controller.post;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Post;
import com.pojo.Result;
import com.service.PostService.PostServiceImpl;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Post/Insert")
public class InsertPostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        String userId = request.getParameter("userId");
        BufferedReader reader = request.getReader();
        String postBody = IOUtils.toString(reader);
        // 创建帖子对象
        // 将参数转换为JSON对象
        String content = JSON.parseObject(postBody).getString("text");
        String type = JSON.parseObject(postBody).getString("type");
        String fileList = JSON.parseObject(postBody).getString("fileList");
        Post post = new Post();
        post.setContent(content);
        post.setStudentId(Integer.valueOf(userId));
        post.setTypes(Integer.valueOf(type));
        // 创建图片对象
        // 将fileList转换为List<Object>
        List<JSONObject> jsonObjects = JSON.parseArray(fileList, JSONObject.class);
        List<String> pictureList = new ArrayList<>();
        if (jsonObjects != null) {
            for (JSONObject object : jsonObjects) {
                pictureList.add(object.getString("url"));
            }
        }
        // 调用帖子服务
        PostServiceImpl postService = new PostServiceImpl();
        // 新增帖子
        boolean insertable = postService.insertPost(post, pictureList);
        // 序列化
        Result result = new Result();
        result.setSuccess(insertable);
        String jsonString = JSON.toJSONString(result);
        //输出
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }

}