package com.hzqing.admin.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author hzqing
 * @date 2019-05-21 10:03
 */
public class JwtTokenUtil {
    private JwtTokenUtil() {
        throw new RuntimeException("工具类不能实例话");
    }
    private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 生成token
     * @param claims
     * @param expiration
     * @return
     */
    public static String generateToken(Map<String, Object> claims,  Integer expiration) {

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate(expiration))
                .signWith(key)
                .compact();
    }

    private static Date generateExpirationDate(Integer expiration) {
        return new Date(System.currentTimeMillis() + (long)(expiration * 1000));
    }

    /**
     * 解析token 返回登陆用户账号
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception var4) {
            username = null;
        }

        return username;
    }

    public static Object getObjectFromToken(String token, String data) {
        Claims claims = getClaimsFromToken(token);
        return claims.get(data);
    }

    private static Claims getClaimsFromToken(String token) {
        return (Claims)Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public static long checkJwtExpired(String token) {
        long expired = -1L;

        try {
            Claims claims = getClaimsFromToken(token);
            if (null != claims) {
                expired = (claims.getExpiration().getTime() - (new Date()).getTime()) / 1000L;
            }

            return expired;
        } catch (ExpiredJwtException var5) {
            return expired;
        }
    }

}
