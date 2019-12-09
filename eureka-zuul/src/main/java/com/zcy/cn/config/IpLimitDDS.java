//package com.zcy.cn.config;
//
//import com.google.common.util.concurrent.RateLimiter;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Slf4j
//@Component
//public class IpLimitDDS extends ZuulFilter {
//
//    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);
//
//    @Override
//    public String filterType() {
//        return FilterConstants.PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return -5;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        RequestContext context = RequestContext.getCurrentContext();
//        HttpServletRequest request = context.getRequest();
//        request.getRemoteHost();
//        return false;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        log.info("放行");
//        return null;
//    }
//}
