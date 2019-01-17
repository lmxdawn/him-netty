package com.lmxdawn.him.api.utils;

import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * jwt
 */
public class JwtUtils {

    /**
     * 生成 token
     *
     * @param claims    自定义的 map
     * @param ttl 过期时间
     * @return
     */
    public static String createToken(Map<String,Object> claims, Long ttl) {
        Key key = generateKey();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Long nowMillis = System.currentTimeMillis(); //生成JWT的时间
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT") //设置header
                .setHeaderParam("alg", "HS256")
                .setClaims(claims) //设置payload的键值对
                // .setIssuedAt(now) //设置iat
                // .setIssuer("vue-api")
                .signWith(signatureAlgorithm, key); //签名，需要算法和key
        if (ttl != null && ttl >= 0) {
            Long expMillis = nowMillis + ttl * 1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp); //设置过期时间
        }
        return builder.compact();
    }

    /**
     * 生成 token ，没有过期时间
     *
     * @param claims 自定义的 map
     * @return
     */
    public static String createToken(Map<String,Object> claims) {
        return createToken(claims, null);
    }

    /**
     * 解密 jwt
     * @param jwt 创建的 jwt 字符串
     * @return
     */
    public static Claims parse(String jwt) {

        if (jwt == null) {
            return null;
        }

        try {
            return Jwts.parser()
                    .setSigningKey(generateKey())     //此处的key要与之前创建的key一致
                    .parseClaimsJws(jwt)
                    .getBody();
        }catch (ExpiredJwtException e){
            return null;
        }
    }

    /**
     * 获取 key
     *
     * @return
     */
    private static SecretKey generateKey() {
        String stringKey = "hui-hui-im";
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}
