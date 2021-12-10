package com.controller.picture;

import com.alibaba.fastjson.JSON;
import com.controller.Store.SelectStoreServlet;
import com.pojo.Dishes;
import com.pojo.Picture;
import com.service.DishesService;
import com.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
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
@WebServlet("/Slideshow")
public class SelectSlideshowServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(SelectSlideshowServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        PictureService pictureService = new PictureService();
        List<Picture> pictureList = pictureService.selectAllSlideshow();
        logger.info(pictureList.toString());
        String dishesListString = JSON.toJSONString(pictureList);
        //输出
        out.print(dishesListString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}