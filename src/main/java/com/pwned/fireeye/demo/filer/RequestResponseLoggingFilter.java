package com.pwned.fireeye.demo.filer;

import com.pwned.fireeye.demo.service.BreachService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestResponseLoggingFilter implements Filter {

    private static final Logger log = LogManager.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        long start = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        log.info(
                "{}: {} Request on {} from {}", uuid, req.getMethod(),
                req.getRequestURI(), req.getRemoteHost() + ":" + req.getRemotePort());
        chain.doFilter(request, response);
        log.info(
                "{}: Sending Response {} in {} ms", uuid,
                res.getContentType(), (System.currentTimeMillis() - start));
    }


}