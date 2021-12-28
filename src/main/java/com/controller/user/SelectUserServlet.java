package com.controller.user;

import com.alibaba.fastjson.JSON;
import com.pojo.Dishes;
import com.pojo.Store;
import com.pojo.User;
import com.service.DishesService.DishesService;
import com.service.DishesService.DishesServiceImpl;
import com.service.StoreService.StoreServiceImpl;
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
        // 如果为管理员
        if ("0".equals(userType)) {
            // 查询出所有用户
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            List<User> userList = userServiceImpl.selectUser();
            // 查询出所有用户的店铺信息
            StoreServiceImpl storeService = new StoreServiceImpl();
            for (User user : userList) {
                Store store = storeService.selectByStoreId(user.getRelationId());
                user.setStore(store);
            }
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