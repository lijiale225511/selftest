package com.example.selftest.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.example.selftest.dto.TokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {

    // 加密秘钥
    private static String secretKey = "nit-JavaWeb-2021";

    private static final Logger logger = LoggerFactory.getLogger(com.example.selftest.utils.JWTUtil.class);

    /**
     * 生成签名
     *
     * @param seed, expire
     * @return 加密的Token
     */
    public static String createSign(String seed, Integer expire) {
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, expire);
        Date expiresDate = nowTime.getTime();

        return JWT.create()
                .withClaim("seed", null == seed ? null : seed)
                .withIssuedAt(iatDate)           // sign time
                .withExpiresAt(expiresDate)      // expire time
                .sign(Algorithm.HMAC256(secretKey));
    }

    /**
     * 校验Token是否正确
     *
     * @param token
     * @return 是否正确
     */
    public static TokenDTO verifyToken(String token) {
        //根据传来的Token获取Seed
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        TokenDTO tokenDTO = new TokenDTO();
        try {
            System.out.println("token: " + token);
            Map<String, Claim> claims = verifier.verify(token).getClaims();
            System.out.println("claims: " + claims);
            Claim seed_claim = claims.get("seed");
            String jsonSign = seed_claim.asString();
            JSONObject jsonObject = JSONObject.parseObject(jsonSign);
            tokenDTO.setLoginId(jsonObject.getInteger("loginId"));
            tokenDTO.setLoginCode(jsonObject.getString("loginCode"));
            tokenDTO.setLoginName(jsonObject.getString("loginName"));
        } catch (Exception e) {
            logger.error("Token验证======>" + tokenDTO.toString());
            logger.error(e.toString());
            tokenDTO = null;
        }
        return tokenDTO;
    }

    /**
     * 无需secret解密，获得Token中的Seed信息
     *
     * @param token
     * @return Token中包含的种子值，通常是用户ID
     */
    public static String getSeed(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        Map<String, Claim> claims = verifier.verify(token).getClaims();
        Claim seed_claim = claims.get("seed");
        return seed_claim.asString();
    }
}
