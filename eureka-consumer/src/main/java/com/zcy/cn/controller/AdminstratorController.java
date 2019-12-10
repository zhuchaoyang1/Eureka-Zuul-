package com.zcy.cn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminstratorController {

    @GetMapping("/info")
    public Object getData() {
        return "我是管理员";
    }


}
