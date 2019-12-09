package com.zcy.cn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class ProducerController {

    @GetMapping
    public String getInfo() {
        return "Hello World";
    }

}
