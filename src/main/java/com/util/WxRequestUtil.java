package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p> 微信请求openid工具类 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.util
 * @className WxRequestUtil
 * @date 2021/12/13 20:58
 * @TODO
 **/
public class WxRequestUtil {
    /**
     * 获取openid
     * @param code 前端请求的code
     * @return
     */
    public static String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        url = url.replace("APPID", "wxa93be96343aa1696");
        url = url.replace("SECRET", "686aa322b8310a2df92c7abceb56c91b");
        url = url.replace("JSCODE", code);
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpEntity entity = httpClient.execute(httpGet).getEntity();
            String content = IOUtils.toString(entity.getContent());
            System.out.println(content);
            JSONObject parseObject = JSON.parseObject(content);
            return parseObject.getString("openid");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
