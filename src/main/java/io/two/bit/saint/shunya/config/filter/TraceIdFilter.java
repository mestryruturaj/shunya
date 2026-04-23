package io.two.bit.saint.shunya.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

public class TraceIdFilter implements Filter {
    private static final String TRACE_ID_HEADER = "X-Trace-Id";
    private static final String MDC_TRACE_ID_KEY = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // 1. Check if header exists, otherwise generate a new one
        String traceId = httpRequest.getHeader(TRACE_ID_HEADER);
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        }

        try {
            // 2. Push to MDC (This makes it available to all logs in this thread)
            MDC.put(MDC_TRACE_ID_KEY, traceId);

            // 3. Add to response header so the client/caller knows the ID
            httpResponse.setHeader(TRACE_ID_HEADER, traceId);

            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // 4. CRITICAL: Clean up MDC after request is done (Threads are reused in Tomcat)
            MDC.remove(MDC_TRACE_ID_KEY);
        }
    }
}
