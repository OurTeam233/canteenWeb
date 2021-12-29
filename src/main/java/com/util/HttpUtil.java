package com.util;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * <p> http请求工具类 </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.util
 * @className HttpUtil
 * @date 2021/12/28 23:40
 * @TODO
 **/
public class HttpUtil {
    private Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    //httpclient连接池
    private static final PoolingHttpClientConnectionManager pcm;
    //http连接
    private CloseableHttpClient httpClient = null;
    //连接超时时间
    private int connectTimeout = 120000;
    //从连接池获取连接超时时间
    private final int connectionRequestTimeout = 10000;
    //获取数据超时时间
    private final int socketTimeout = 300000;
    private final String charset = "utf-8";
    //请求配置
    private RequestConfig requestConfig = null;
    //build requestConfig
    private Builder requestConfigBuilder = null;

    private List<NameValuePair> nvps = new ArrayList<>();
    private List<Header> headers = new ArrayList<>();
    private String requestParam = "";

    static {
        pcm = new PoolingHttpClientConnectionManager();
        //整个连接池最大连接数
        pcm.setMaxTotal(50);
        //每路由最大连接数，默认值是2
        pcm.setDefaultMaxPerRoute(50);
    }

    /**
     * 默认设置
     *
     * @author Liu Jiong
     * @createDate 2016年10月30日
     */
    private static HttpUtil defaultInit() {
        HttpUtil httpUtil = new HttpUtil();
        if (httpUtil.requestConfig == null) {
            httpUtil.requestConfigBuilder = RequestConfig.custom().setConnectTimeout(httpUtil.connectTimeout)
                    .setConnectionRequestTimeout(httpUtil.connectionRequestTimeout)
                    .setSocketTimeout(httpUtil.socketTimeout);
            httpUtil.requestConfig = httpUtil.requestConfigBuilder.build();
        }
        return httpUtil;
    }

    /**
     * 初始化 httpUtil
     */
    public static HttpUtil init() {
        HttpUtil httpUtil = defaultInit();
        if (httpUtil.httpClient == null) {
            httpUtil.httpClient = HttpClients.custom().setConnectionManager(pcm).build();
        }
        return httpUtil;
    }

    /**
     * 初始化 httpUtil
     */
    public static HttpUtil init(Map<String, String> paramMap) {
        HttpUtil httpUtil = init();
        httpUtil.setParamMap(paramMap);
        return httpUtil;
    }

    /**
     * 验证初始化
     */
    public static HttpUtil initWithAuth(String ip, int port, String username, String password) {
        HttpUtil httpUtil = defaultInit();
        if (httpUtil.httpClient == null) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(new AuthScope(ip, port, AuthScope.ANY_REALM), new UsernamePasswordCredentials(username, password));
            httpUtil.httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider)
                    .setConnectionManager(pcm).build();
        }
        return httpUtil;
    }

    /**
     * 设置请求头
     */
    public HttpUtil setHeader(String name, String value) {
        Header header = new BasicHeader(name, value);
        headers.add(header);
        return this;
    }

    /**
     * 设置请求头
     */
    public HttpUtil setHeaderMap(Map<String, String> headerMap) {
        for (Entry<String, String> param : headerMap.entrySet()) {
            Header header = new BasicHeader(param.getKey(), param.getValue());
            headers.add(header);
        }
        return this;
    }

    /**
     * 设置请求参数
     */
    public HttpUtil setParam(String name, String value) {
        nvps.add(new BasicNameValuePair(name, value));
        return this;
    }

    /**
     * 设置请求参数
     */
    public HttpUtil setParamMap(Map<String, String> paramMap) {
        for (Entry<String, String> param : paramMap.entrySet()) {
            nvps.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        return this;
    }

    /**
     * 设置字符串参数
     */
    public HttpUtil setStringParam(String requestParam) {
        this.requestParam = requestParam;
        return this;
    }

    /**
     * 设置连接超时时间
     */
    public HttpUtil setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        this.requestConfigBuilder = requestConfigBuilder.setConnectTimeout(connectTimeout);
        requestConfig = requestConfigBuilder.build();
        return this;
    }

    /**
     * http get 请求
     */
    public Map<String, String> get(String url) {
        Map<String, String> resultMap = new HashMap<>();
        //获取请求URI
        URI uri = getUri(url);
        if (uri != null) {
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setConfig(requestConfig);
            if (!CollectionUtils.isEmpty(headers)) {
                Header[] header = new Header[headers.size()];
                httpGet.setHeaders(headers.toArray(header));
            }

            //执行get请求
            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                return getHttpResult(response, url, httpGet, resultMap);
            } catch (Exception e) {
                httpGet.abort();
                resultMap.put("result", e.getMessage());
                logger.error("获取http GET请求返回值失败 url======" + url, e);
            }
        }
        return resultMap;
    }

    /**
     * http post 请求
     */
    public Map<String, String> post(String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        if (!CollectionUtils.isEmpty(headers)) {
            Header[] header = new Header[headers.size()];
            httpPost.setHeaders(headers.toArray(header));
        }
        if (!CollectionUtils.isEmpty(nvps)) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
            } catch (UnsupportedEncodingException e) {
                logger.error("http post entity form error", e);
            }
        }
        if (!StringUtils.isEmpty(requestParam)) {
            try {
                httpPost.setEntity(new StringEntity(requestParam, charset));
            } catch (UnsupportedCharsetException e) {
                logger.error("http post entity form error", e);
            }
        }
        Map<String, String> resultMap = new HashMap<>();
        //执行post请求
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return getHttpResult(response, url, httpPost, resultMap);
        } catch (Exception e) {
            httpPost.abort();
            resultMap.put("result", e.getMessage());
            logger.error("获取http POST请求返回值失败 url======" + url, e);
        }
        return resultMap;
    }

    /**
     * post 上传文件
     */
    public Map<String, String> postUploadFile(String url, Map<String, File> fileParam) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        if (!CollectionUtils.isEmpty(headers)) {
            Header[] header = new Header[headers.size()];
            httpPost.setHeaders(headers.toArray(header));
        }

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if (fileParam != null) {
            for (Entry<String, File> entry : fileParam.entrySet()) {
                //将要上传的文件转化为文件流
                FileBody fileBody = new FileBody(entry.getValue());
                //设置请求参数
                builder.addPart(entry.getKey(), fileBody);
            }
        }

        if (!CollectionUtils.isEmpty(nvps)) {
            for (NameValuePair nvp : nvps) {
                String value = nvp.getValue();
                if (!StringUtils.isEmpty(value)) {
                    builder.addTextBody(nvp.getName(), value, ContentType.create("text/plain", charset));
                }
            }
        }
        httpPost.setEntity(builder.build());
        Map<String, String> resultMap = new HashMap<>();
        //执行post请求
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return getHttpResult(response, url, httpPost, resultMap);
        } catch (Exception e) {
            httpPost.abort();
            resultMap.put("result", e.getMessage());
            logger.error("获取http postUploadFile 请求返回值失败 url======" + url, e);
        }
        return resultMap;
    }

    /**
     * 获取请求返回值
     */
    private Map<String, String> getHttpResult(CloseableHttpResponse response, String url, HttpUriRequest request, Map<String, String> resultMap) {
        String result = "";
        int statusCode = response.getStatusLine().getStatusCode();
        resultMap.put("statusCode", statusCode + "");
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try {
                result = EntityUtils.toString(entity, charset);
                //释放连接
                EntityUtils.consume(entity);
            } catch (Exception e) {
                logger.error("获取http请求返回值解析失败", e);
                request.abort();
            }
        }
        if (statusCode != 200) {
            result = "HttpClient status code :" + statusCode + "  request url===" + url;
            logger.info("HttpClient status code :" + statusCode + "  request url===" + url);
            request.abort();
        }
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 获取重定向url返回的location
     */
    public String redirectLocation(String url) {
        String location = "";
        //获取请求URI
        URI uri = getUri(url);
        if (uri != null) {
            HttpGet httpGet = new HttpGet(uri);
            requestConfig = requestConfigBuilder.setRedirectsEnabled(false).build();//设置自动重定向false
            httpGet.setConfig(requestConfig);
            if (!CollectionUtils.isEmpty(headers)) {
                Header[] header = new Header[headers.size()];
                httpGet.setHeaders(headers.toArray(header));
            }

            try {
                //执行get请求
                CloseableHttpResponse response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {//301 302
                    Header header = response.getFirstHeader("Location");
                    if (header != null) {
                        location = header.getValue();
                    }
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
            } catch (Exception e) {
                logger.error("获取http GET请求获取 302 Location失败 url======" + url, e);
                httpGet.abort();
            }
        }
        return location;
    }

    /**
     * 获取输入流
     */
    public InputStream getInputStream(String url) {
        //获取请求URI
        URI uri = getUri(url);
        if (uri != null) {
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setConfig(requestConfig);
            if (!CollectionUtils.isEmpty(headers)) {
                Header[] header = new Header[headers.size()];
                httpGet.setHeaders(headers.toArray(header));
            }
            //执行get请求
            try {
                CloseableHttpResponse response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    logger.info("HttpClient status code :" + statusCode + "  request url===" + url);
                    httpGet.abort();
                } else {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        InputStream in = entity.getContent();
                        return in;
                    }
                }
            } catch (Exception e) {
                logger.error("获取http GET inputStream请求失败 url======" + url, e);
                httpGet.abort();
            }
        }
        return null;
    }

    private URI getUri(String url) {
        URI uri = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (!CollectionUtils.isEmpty(nvps)) {
                uriBuilder.setParameters(nvps);
            }
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            logger.error("url 地址异常", e);
        }
        return uri;
    }


    /**
     * from请求
     */
    public static String form(String url, Map<String, String> params) {
        URL u = null;
        HttpURLConnection con = null;
        // 构建请求参数
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (Entry<String, String> e : params.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.substring(0, sb.length() - 1);
        }
        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            // POST 只能为大写，严格限制，post会不识别
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            osw.write(sb.toString());
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        // 读取返回内容
        StringBuffer buffer = new StringBuffer();
        try {
            //一定要有返回值，否则无法把请求发送给server端。
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
                buffer.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    // 使用方法
    /*Map<String, String> map = new HashMap<>();
    JSONObject param = new JSONObject();
    param.put("activity_id","60:video:comets:10011#2");
    param.put("state",3);
    param.put("attr_tags","");
    param.put("msg","");
    map.put("param",param.toJSONString());
    String form = form("http://yp5ntd.natappfree.cc/CnInteraction/services/commentForHd/auditCallBackNew", map);
    System.out.println(form);*/

    /*
    JSONObject params = new JSONObject();
    params.put("video_id","60_8be004799c424688949704814ea0d16d");
    params.put("state",3+"");
    params.put("attr_tags","");
    params.put("msg","");
    HttpUtil httpUtil = HttpUtil.init();
    httpUtil.setParam("param",params.toJSONString());
    String URL ="http://abcd.com/v1/ShenHeInfo/shenheNotify?platform=AUDIT&token=94cead75c";
    Map<String, String> post = httpUtil.post(URL);
    System.out.println(post.get("result"));*/


}
