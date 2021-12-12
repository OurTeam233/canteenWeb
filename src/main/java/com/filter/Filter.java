package com.filter;

import com.util.JwtUtil;
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

/**
 * @author tang
 */
@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 如果访问登录界面，直接放行
        String requestUri = ((HttpServletRequest) request).getRequestURI();
        if (requestUri.contains("/CanteenWeb/User/Login")) {
            chain.doFilter(request, response);
        } else {
            // 获取请求头中的token
            String token = ((HttpServletRequest) request).getHeader("token");
            // 尝试解析token
            Claims claim = null;
            // 如果token不为空，尝试解析token
            if (token != null) {
                claim = JwtUtil.getClaimByToken(token);
            }
            if (claim != null && claim.getExpiration().after(new Date())) {
                // 如果解析成功并且时间没有过期则放行
                chain.doFilter(request, response);
            } else {
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
}
