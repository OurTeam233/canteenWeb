package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * <p> 生成和解码jwt </p>
 *
 * @author 汤卫豪
 * @version V1.0
 * @projectName CanteenWeb
 * @package com.util
 * @className JwtUtil
 * @date 2021/12/12 15:55
 * @TODO
 **/
public class JwtUtil {
    public static final String SECRET = "ToxhIw2l";

    /**
     * <p> 生成jwt </p>
     *
     * @param userId 用户id
     * @return java.lang.String
     * @since 2021/12/12
     */
    public static String generateToken(String userId) {
        Date date = new Date();
        Date expireTime = new Date(date.getTime() + 60 * 60 * 1000 * 3);
        return Jwts.builder()
                // header
                .setHeaderParam("typ", "JWT")
                // payload
                .claim("userId", userId)
                .setIssuedAt(date)
                //过期时间
                .setExpiration(expireTime)
                //秘钥
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * <p> 将token解码 </p>
     *
     * @param token 待解码的token
     * @return io.jsonwebtoken.Claims
     * @since 2021/12/12
     */
    public static Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    //获取秘钥
                    .setSigningKey(SECRET)
                    //解析验证token
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(JwtUtil.class);
            logger.info("token验证失败", e);
            return null;
        }
    }
}
