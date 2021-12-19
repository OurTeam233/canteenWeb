package com.controller;

import com.util.SessionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * 功能说明：websocket处理类, 使用J2EE7的标准
 * 切忌直接在该连接处理类中加入业务处理代码，否则会导致websocket连接无法正常关闭
 * @author tang
 */
@ServerEndpoint("/Websocket/{storeId}")
public class WebsocketPoint {

    private static Log log = LogFactory.getLog(WebsocketPoint.class);

    /**
     * 打开连接时触发
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("storeId") String storeId,
                       Session session) {
        log.info("Websocket Start Connecting:" + SessionUtils.getKey(storeId));
        SessionUtils.put(storeId, session);
    }

    /**
     * 收到客户端消息时触发
     *
     * @param message
     * @return
     */
    @OnMessage
    public String onMessage(@PathParam("storeId") String storeId,
                            String message) {
        return "Got your message (" + message + ").Thanks !";
    }

    /**
     * 异常时触发
     *
     * @param session
     */
    @OnError
    public void onError(@PathParam("storeId") String storeId,
                        Throwable throwable,
                        Session session) {
        log.info("Websocket Connection Exception:" + SessionUtils.getKey(storeId));
        log.info(throwable.getMessage(), throwable);
        SessionUtils.remove(storeId);
    }

    /**
     * 关闭连接时触发
     *
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("storeId") String storeId,
                        Session session) {
        SessionUtils.remove(storeId);
        log.info("Websocket Close Connection:" + SessionUtils.getKey(storeId));
    }

}