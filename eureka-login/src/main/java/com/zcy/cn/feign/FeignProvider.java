package com.zcy.cn.feign;

import com.zcy.cn.pojo.PersonInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "provider-one")
public interface FeignProvider {

    @PostMapping("/user/login")
    PersonInfo login(@RequestBody PersonInfo personInfo);

}
