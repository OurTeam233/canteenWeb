package com.controller.dishes;

import com.alibaba.fastjson.JSON;
import com.controller.store.SelectStoreServlet;
import com.pojo.Dishes;
import com.service.DishesService.DishesServiceImpl;
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
@WebServlet("/Dishes")
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
        // 初始化
        DishesServiceImpl dishesServiceImpl = new DishesServiceImpl();
        List<Dishes> dishesList = null;
        String storeId = request.getParameter("storeId");
        if (storeId != null) {
            // 如果有storeId，则查询该店铺的菜品
            dishesList = dishesServiceImpl.selectDishesByStoreId(Integer.parseInt(storeId));
        } else {
            // 如果是商家，那么直接返回该商户的店铺信息
            String userId = request.getParameter("userId");
            String userType = request.getParameter("userType");
            if ("2".equals(userType)) {
                dishesList = dishesServiceImpl.selectDishesByStoreId(Integer.parseInt(userId));
            }
        }
        // 返回结果集
        if (dishesList != null) {
            String dishesListString = JSON.toJSONString(dishesList);
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