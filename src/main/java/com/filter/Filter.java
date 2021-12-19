package com.filter;
import com.util.JwtUtil;
import com.util.RequestParameterWrapper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tang
 */
@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper((HttpServletRequest) request);
        // 如果访问登录界面，直接放行
        String requestUri = requestParameterWrapper.getRequestURI();
        if (requestUri.startsWith("/CanteenWeb/Login") || requestUri.startsWith("/CanteenWeb/Websocket")) {
            chain.doFilter(request, response);
        } else {
            // 获取请求头中的token
            String token = requestParameterWrapper.getHeader("token");
            // 尝试解析token
            Claims claim = null;
            // 如果token不为空，尝试解析token
            if (token != null) {
                claim = JwtUtil.getClaimByToken(token);
            }
            // 如果解析成功
            if (claim != null) {
                // 获取ua
                String header = requestParameterWrapper.getHeader("User-Agent");
                // 获取ip
                String remoteAddr = requestParameterWrapper.getRemoteAddr();
                // 如果ua和ip都相同，则继续判断
                if (header.equals(claim.get("userAgent")) && remoteAddr.equals(claim.get("ip"))) {
                    // 获取userId和userType
                    String userId = claim.get("userId") + "";
                    String userType = claim.get("userType") + "";
                    // (如果用户为学生) 或者 (如果用户为不为学生但token没有过期)，放行
                    boolean studentLoginEnabled = "1".equals(userType);
                    boolean otherLoginEnabled = (!"1".equals(userType) && claim.getExpiration().after(new Date()));
                    if (studentLoginEnabled || otherLoginEnabled) {
                        // 向request中添加用户信息
                        // 修改集合中的参数
                        requestParameterWrapper.addParameter("userId", userId);
                        requestParameterWrapper.addParameter("userType", userType);
                        // 如果解析成功并且时间没有过期则放行
                        chain.doFilter(requestParameterWrapper, response);
                        return;
                    }
                }
            }
            // 如果解析失败或者时间过期则返回401
            // 强制转换为HttpServletResponse
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            // 设置响应头
            httpServletResponse.setStatus(401);
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("text/json;charset=utf-8");
            // 写入响应内容
            PrintWriter out = httpServletResponse.getWriter();
            out.println("{\"code\":401,\"msg\":\"token过期或无效\"}");
        }
    }
}
