package com.zcy.cn;

import com.alibaba.fastjson.JSON;
import com.zcy.cn.pojo.PersonInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class EurkaProducerApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void saveUserBefore() {
        PersonInfo personInfo1 = PersonInfo.builder().code("001")
                .password("001").role(0).username("张三").build();

        PersonInfo personInfo2 = PersonInfo.builder().code("002")
                .password("002").role(1).username("李四").build();

        PersonInfo personInfo3 = PersonInfo.builder().code("003")
                .password("003").role(2).username("王五").build();

        redisTemplate.opsForHash().put("user","001", JSON.toJSONString(personInfo1));
        redisTemplate.opsForHash().put("user","002",JSON.toJSONString(personInfo2));
        redisTemplate.opsForHash().put("user","003",JSON.toJSONString(personInfo3));
    }

}
