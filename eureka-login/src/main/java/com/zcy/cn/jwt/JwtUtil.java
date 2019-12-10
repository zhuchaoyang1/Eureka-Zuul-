package com.zcy.cn.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;
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
     * 生成一个token
     *
     * @param payloadMap JWT 载荷数据
     * @return
     * @throws JOSEException
     */
    public static String creatToken(Map<String, Object> payloadMap) throws JOSEException {
        //3.先建立一个头部Header
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));
        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //建立一个密匙
        JWSSigner jwsSigner = new MACSigner(secret);
        //签名
        jwsObject.sign(jwsSigner);
        //生成token
        return jwsObject.serialize();
    }

}
