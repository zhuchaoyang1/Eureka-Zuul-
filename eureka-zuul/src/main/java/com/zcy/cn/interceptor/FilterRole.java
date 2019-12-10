package com.zcy.cn.interceptor;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.nimbusds.jose.JOSEException;
import com.zcy.cn.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

/**
 * Zuul网关
 * 拦截所有请求判断权限问题  通过Service前缀
 * 不拦截登录模块
 */
@Slf4j
@Component
public class FilterRole extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    /**
     * Ture  执行过滤器
     * False 不执行过滤器
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if (request.getRequestURI().equals("/login/user/login")) {
            // 只有登录SOA不拦截
            return false;
        } else {
            // 除了Login剩下的所有微服务都需拦截
            return true;
        }
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        Map<String, Object> map;
        JSONObject playoud;
        String role;
        boolean flag = false;
        String token = request.getHeader("token");
        String errmsg = "当前账户权限不足";
        if (token == null) {
            // Token 口令为空不执行
            flag = false;
            errmsg = "未登录";
        }
        try {
            if (token != null) {
                map = jwtUtil.valid(token);
                if (map.get("Result").toString().equals("0")) {
                    // Token正常 查看Role
                    playoud = (JSONObject) map.get("data");
                    role = playoud.get("role").toString();
                    flag = checkAuth(request.getRequestURI(), role);
                } else {
                    flag = false;
                    if (map.get("Result").toString().equals("1")) {
                        errmsg = "Token解密不通过，可能被篡改";
                    }
                    if (map.get("Result").toString().equals("2")) {
                        errmsg = "Token已过期，请重新登录";
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }

        if (!flag) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            context.getResponse().setContentType("text/html;charset=UTF-8");
            context.setResponseBody(errmsg);
        }

        return null;
    }

    /**
     * @param url
     * @param role
     * @return
     */
    public boolean checkAuth(String url, String role) {
        if (url.contains("/admin")) {
            return role.equals("0");
        } else if (url.contains("/teacher")) {
            return role.equals("1");
        } else if (url.contains("/student")) {
            return role.equals("2");
        }
        return false;
    }
}
