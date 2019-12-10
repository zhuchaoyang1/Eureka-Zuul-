package com.zcy.cn.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 */
@Component
public class JwtUtil {

    /**
     * 1.创建一个32-byte的密匙
     */
    private static final byte[] secret = "geiwodiangasfdjsikolkjikolkijswe".getBytes();

    /**
     * JWT 解析Token工具类
     *
     * @param token
     * @return
     * @throws ParseException
     * @throws JOSEException
     */
    public Map<String, Object> valid(String token) throws ParseException, JOSEException {
        // 解析token
        JWSObject jwsObject = JWSObject.parse(token);
        //获取到载荷
        Payload payload = jwsObject.getPayload();
        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        Map<String, Object> resultMap = new HashMap<>();
        //判断token
        if (jwsObject.verify(jwsVerifier)) {
            // Token正常比标识
            resultMap.put("Result", 0);
            //载荷的数据解析成json对象。
            JSONObject jsonObject = payload.toJSONObject();
            resultMap.put("data", jsonObject);

            //判断token是否过期
            if (jsonObject.containsKey("expireTime")) {
                Long expTime = Long.valueOf((String) jsonObject.get("expireTime"));
                Long nowTime = Long.valueOf(DateTime.now().toString("yyyyMMddHHmmssSSS"));
                //判断是否过期
                if (nowTime > expTime) {
                    //已经过期
                    resultMap.clear();
                    // Token已经过期
                    resultMap.put("Result", 2);
                }
            }
        } else {
            // Token 解密不通过
            resultMap.put("Result", 1);
        }
        return resultMap;
    }

}
