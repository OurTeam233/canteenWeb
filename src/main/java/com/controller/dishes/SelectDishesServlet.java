package com.controller.dishes;

import com.alibaba.fastjson.JSON;
import com.controller.Store.SelectStoreServlet;
import com.pojo.Dishes;
import com.service.DishesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author tang
 */
@WebServlet(name = "SelectDishesServlet", value = "/Dishes")
public class SelectDishesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(SelectStoreServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        int storeId = Integer.parseInt(request.getParameter("storeId"));
        DishesService dishesService = new DishesService();
        List<Dishes> dishesList = dishesService.selectDishesByStoreId(storeId);
        logger.info(dishesList.toString());
        String dishesListString = JSON.toJSONString(dishesList);
        //输出
        out.print(dishesListString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}