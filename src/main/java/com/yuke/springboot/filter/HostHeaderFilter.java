package com.yuke.springboot.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HostHeaderFilter implements Filter {

    @Value(value = "${config.host}")
    private String ALLOWED_HOSTS;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String hostHeader = httpRequest.getHeader("Host");
        if (StringUtils.isNotBlank(hostHeader)) {
            boolean flag = false;
            String [] allowedHosts = ALLOWED_HOSTS.split(",");
            for(String allowedHost : allowedHosts){
                if(hostHeader.indexOf(allowedHost) > -1){
                    flag = true;
                }
            }
            if(!flag){
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 销毁逻辑
    }
}

