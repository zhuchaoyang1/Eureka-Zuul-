package com.zcy.cn.controller;

import com.alibaba.fastjson.JSON;
import com.zcy.cn.pojo.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ProducerController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录需要有编号和密码正确即可
     *
     * @param personInfo
     * @return
     */
    @PostMapping("/login")
    public PersonInfo login(@RequestBody PersonInfo personInfo) {
        Object object = redisTemplate.opsForHash().get("user", personInfo.getCode());
        PersonInfo person = null;
        if (object != null) {
            person = JSON.parseObject(object.toString(), PersonInfo.class);
        }
        return person;
    }

}
