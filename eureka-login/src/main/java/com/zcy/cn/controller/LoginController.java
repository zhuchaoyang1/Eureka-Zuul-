package com.zcy.cn.controller;

import com.nimbusds.jose.JOSEException;
import com.zcy.cn.feign.FeignProvider;
import com.zcy.cn.jwt.JwtUtil;
import com.zcy.cn.pojo.PersonInfo;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private FeignProvider feignProvider;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody PersonInfo personInfo, HttpServletResponse response) {

        PersonInfo person = feignProvider.login(personInfo);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> payloadMap;
        String token = null;
        if (person != null) {
            // 用户登录成功 开始构造Token
            map.put("code", 200);
            map.put("msg", "success");

            payloadMap = new HashMap<>();
            payloadMap.put("code", person.getCode());
            payloadMap.put("role", person.getRole());
            payloadMap.put("username", person.getUsername());
            payloadMap.put("expireTime", DateTime.now().plusMinutes(30).toString("yyyyMMddHHmmssSSS"));

            try {
                token = jwtUtil.creatToken(payloadMap);
            } catch (JOSEException e) {
                e.printStackTrace();
                log.info("Token构造失败");
            }
            map.put("token", token);
            log.info("构造Token如下：{}", token);
        } else {
            map.put("code", 200);
            map.put("msg", "error");
            return map;
        }
        return map;
    }

}
