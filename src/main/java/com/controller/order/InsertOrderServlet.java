package com.controller.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Order;
import com.pojo.OrderDetails;
import com.pojo.Store;
import com.service.DishesService.DishesServiceImpl;
import com.service.OrderService.OrderServiceImpl;
import com.service.StoreService.StoreServiceImpl;
import com.util.SessionUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author tang
 */
@WebServlet("/Order/Insert")
public class InsertOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(InsertOrderServlet.class);

    private WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 接收参数
        String userId = request.getParameter("userId");
        String userType = request.getParameter("userType");
        // 获取请求体参数
        BufferedReader reader = request.getReader();
        String postBody = IOUtils.toString(reader);
        // 将参数转换为JSON对象
        JSONObject jsonUserInfo = JSON.parseObject(postBody).getJSONObject("order");
        // 构造order对象
        Order order = JSON.parseObject(JSON.toJSONString(jsonUserInfo), Order.class);
        order.setStudentId(Integer.valueOf(userId));
        // 构造orderDetails对象
        List<OrderDetails> orderDetails = JSON.parseArray(jsonUserInfo.getString("dishes"), OrderDetails.class);
        // 获取店铺状态
        int storeId = order.getStoreId();
        StoreServiceImpl storeServiceImpl = new StoreServiceImpl();
        Store store = storeServiceImpl.selectByStoreId(storeId);
        // 如果是学生 并且店铺营业中
        if ("1".equals(userType) && store.getStatus() == 1) {
            // 创建服务对象
            OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
            order.setStudentId(Integer.valueOf(userId));
            int insertOrder = orderServiceImpl.insertOrder(order, orderDetails);
            // 增加菜品售卖数量
            // 创建菜品服务对象
            DishesServiceImpl dishesServiceImpl = new DishesServiceImpl();
            for (OrderDetails orderDetail : orderDetails) {
                dishesServiceImpl.updateDishesSales(String.valueOf(orderDetail.getDishesId()));
            }
            // 返回结果集
            if (insertOrder > 0) {
                out.print("{\"success\":\"true\"}");
                // 向商家发送消息
                broadcast(String.valueOf(order.getStoreId()), "{\"type\":\"order\",\"orderId\":" + order.getId() + "}");
                return;
            }
        }
        // 返回结果集
        out.print("{\"success\":\"false\"}");
    }

    public void broadcast(String storeId, String message) {
        if (SessionUtils.hasConnection(storeId)) {
            SessionUtils.get(storeId).getAsyncRemote().sendText(message);
        }
//        else {
//            throw new NullPointerException(SessionUtils.getKey(storeId) + "Connection does not exist");
//        }
    }
}