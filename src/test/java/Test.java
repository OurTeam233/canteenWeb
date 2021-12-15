//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.util.Date;
//
///**
// * <p> 随便测试一点东西 </p>
// *
// * @author 汤卫豪
// * @version V1.0
// * @projectName CanteenWeb
// * @package PACKAGE_NAME
// * @className Test
// * @date 2021/12/13 17:33
// * @TODO
// **/
//public class Test {
//    @org.junit.Test
//    public void getOpenId() {
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
//        url = url.replace("APPID", "wxa93be96343aa1696");
//        url = url.replace("SECRET", "686aa322b8310a2df92c7abceb56c91b");
//        url = url.replace("JSCODE", );
//        HttpGet httpGet = new HttpGet(url);
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        try {
//            HttpEntity entity = httpClient.execute(httpGet).getEntity();
//            String result = EntityUtils.toString(entity);
//            System.out.println(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
