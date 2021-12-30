package com.controller.lostFound;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.LostFound;
import com.pojo.Result;
import com.service.LostFound.LostFoundServiceImpl;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LostFound/Insert")
public class InsertLostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 获取参数
        String userId = request.getParameter("userId");
        // 获取失物招领信息
        BufferedReader reader = request.getReader();
        String postBody = IOUtils.toString(reader);
        // 解析json
        JSONObject jsonUserInfo = JSON.parseObject(postBody).getJSONObject("post");
        // 构造lostFound对象
        LostFound lostFound = JSON.parseObject(JSON.toJSONString(jsonUserInfo), LostFound.class);
        lostFound.setStudentId(Integer.valueOf(userId));
        // 调用服务层
        LostFoundServiceImpl lostFoundService = new LostFoundServiceImpl();
        boolean insertable = lostFoundService.insertLostFound(lostFound);
        //输出
        Result result = new Result();
        result.setSuccess(insertable);
        String jsonString = JSON.toJSONString(result);
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}