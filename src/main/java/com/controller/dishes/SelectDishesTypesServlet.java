package com.controller.dishes;

import com.alibaba.fastjson.JSON;
import com.controller.store.SelectStoreServlet;
import com.pojo.Dishes;
import com.pojo.DishesTypes;
import com.service.DishesService;
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
@WebServlet("/Dishes/Types/Select")
public class SelectDishesTypesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(SelectStoreServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 初始化
        String userId = request.getParameter("userId");
        DishesService dishesService = new DishesService();
        List<DishesTypes> dishesTypesList = dishesService.selectDishesTypes(userId);
        // 返回结果集
        if (dishesTypesList != null) {
            String dishesListString = JSON.toJSONString(dishesTypesList);
            out.print(dishesListString);
        } else {
            // 如果为空返回 status:0
            out.println("{\"status\":\"0\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}