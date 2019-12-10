package com.zcy.cn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过设置线程长时间休眠导致超时时间
 * 测试
 * Zuul网关重试机制
 * <p>
 * 路径前加一层admin包裹是为了方便管理员绕过Zuul鉴权进入该模块测试
 */
@Slf4j
@RestController
@RequestMapping("/admin/ribbon")
public class RetryController {
    @GetMapping
    public Object testRibbon(HttpServletRequest request) {
        log.info("观察请求对象：{}", request);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "{\"code\":200}";
    }
}
