package com.zcy.cn.service.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider-one")
public interface FeignData {

    @GetMapping("/info")
    String getInfo();

}
