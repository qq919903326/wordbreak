package com.wordbreak.filter;

import com.wordbreak.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 功能：通过Filter过滤器来防止跨站点脚本编制攻击
 * </P>
 */
@Slf4j
public class XSSFilter implements Filter {
    private FilterConfig filterConfig = null;

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("==进入xss过滤器===");
        String xssPatterns = filterConfig.getInitParameter("xss-patterns");
        xssPatterns = StrUtil.formatNull(xssPatterns);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String queryString = StrUtil.formatNull(req.getQueryString());
        queryString = java.net.URLDecoder.decode(queryString);
        // log.info("检测到参数==== "+queryString);
        if (xssPatterns.trim().length() > 0) {
            String[] patterns = xssPatterns.split(";");
            for (int i = 0; i < patterns.length; i++) {
                // log.info("==patterns[i]==== "+patterns[i]);
                Pattern p = Pattern.compile("(?i)" + patterns[i]);
                Matcher m = p.matcher(queryString);
                if (m.find()) {
                    log.error("检测到您发送请求中的参数中含有跨站脚本编制非法字符:{}",HttpServletResponse.SC_BAD_REQUEST);
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
}
