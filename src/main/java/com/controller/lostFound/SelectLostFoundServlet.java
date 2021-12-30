package com.controller.lostFound;

import com.alibaba.fastjson.JSON;
import com.pojo.LostFound;
import com.service.LostFound.LostFoundServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/LostFound/Select")
public class SelectLostFoundServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        String types = request.getParameter("types");
        LostFoundServiceImpl lostFoundService = new LostFoundServiceImpl();
        List<LostFound> lostFoundList = lostFoundService.selectLostFound(types);
        //输出
        String jsonString = JSON.toJSONString(lostFoundList);
        out.print(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}