package com.nantian.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CORSFilter
 * @Description 后端解决跨域的过滤器
 * @Author xsc
 * @CreateTime 2020年10月28日 10:48:06
 * @Version 1.0.0
 */

//@Component
//@WebFilter(urlPatterns = { "/" } )
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) resp;
        // * 号表示对所有请求都允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        chain.doFilter(req, resp);
    }

}
