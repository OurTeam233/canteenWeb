package com.util;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能说明：用来存储业务定义的sessionId和连接的对应关系
 * 利用业务逻辑中组装的sessionId获取有效连接后进行后续操作
 *
 * @author tang
 */
public class SessionUtils {

    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    public static void put(String storeId, Session session) {
        clients.put(getKey(storeId), session);
    }

    public static Session get(String storeId) {
        return clients.get(getKey(storeId));
    }

    public static void remove(String storeId) {
        clients.remove(getKey(storeId));
    }

    /**
     * 判断是否有连接
     *
     * @param relationId
     * @param userCode
     * @return
     */
    public static boolean hasConnection(String storeId) {
        return clients.containsKey(getKey(storeId));
    }

    /**
     * 组装唯一识别的key
     *
     * @param relationId
     * @param userCode
     * @return
     */
    public static String getKey(String storeId) {
        return storeId;
    }

}