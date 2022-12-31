package com.project.webapi.core.middlewares.filter;

import com.project.webapi.core.data.dao.FlowLog;
import com.project.webapi.core.service.FlowLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
@Order(1)
public class RequestResponseLoggingFilter implements Filter {

    private FlowLogService flowLogService;

    @Autowired
    public RequestResponseLoggingFilter(FlowLogService flowLogService) {
        this.flowLogService = flowLogService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response);

        // todo log the request to app
        filterChain.doFilter(req, resp);

        String method = req.getMethod();
        String path = req.getRequestURI();

        if (path.startsWith("/api") && !method.equals("GET")) {
            String requestBody = new String(req.getContentAsByteArray(), StandardCharsets.UTF_8);
            String responseBody = new String(resp.getContentAsByteArray(), StandardCharsets.UTF_8);

            flowLogService.add(new FlowLog(requestBody, responseBody, req.getMethod()));
        }

        // todo log the response to app
        resp.copyBodyToResponse();

    }

}
