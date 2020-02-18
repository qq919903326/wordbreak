package com.wordbreak.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CSRFFilter implements Filter {
    private FilterConfig filterConfig = null;

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("==进入CSRF过滤器===");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 从http头中获取Referer
        String referer = req.getHeader("Referer");
        // 系统配置的referer头信息
        String myReferer = filterConfig.getInitParameter("referer");
        int count = 0;
        // 判空
        if (StringUtils.isNotBlank(myReferer)) {
            String[] myReferers = myReferer.split(";");
            for (int i = 0; i < myReferers.length; i++) {
                if (referer != null && !referer.trim().startsWith(myReferers[i])) {
                    count++;
                } else {
                    chain.doFilter(request, response);
                    break;
                }
            }
            if (count == myReferers.length) {
                log.error("检测到您发送的请求可能为跨站伪造请求1:" + HttpServletResponse.SC_BAD_REQUEST);
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        log.info("==结束CSRF过滤器===");
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
}