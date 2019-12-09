package com.zcy.cn.controller;

import com.zcy.cn.service.impl.FeignData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class GetData {

    @Autowired
    private FeignData feignData;

    @GetMapping
    public Object getData() {
        return feignData.getInfo();
    }


}
