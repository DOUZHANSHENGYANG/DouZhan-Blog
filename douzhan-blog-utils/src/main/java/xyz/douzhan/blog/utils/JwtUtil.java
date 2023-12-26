package xyz.douzhan.blog.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Description:
 * date: 2023/12/14 16:309
 *
 * @author 斗战圣洋
 * @since JDK 17
 */

public class JwtUtil {

    public static final String USER_ID = "id";
    //过期时间
    public static final Long TOKEN_TIME_OUT = 3600L;
    //秘钥
    public static final String TOKEN_SECRET = "qHIey8u+YM1h19L2zdCKqxXHxyLdlTaueXpQvhgjfMpDDLrq3ed6q+PQYR8c4pHE9WW04MfytpKalnA2gkzfyQ==";
    //    最小刷新时间
    public static final int REFRESH_TIME = 300;

    public static String genToken(byte id) throws Exception {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, id);
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTime))
                .compressWith(CompressionCodecs.GZIP)
                .signWith(SignatureAlgorithm.HS512,getKey())
                .setExpiration(new Date(currentTime + TOKEN_TIME_OUT * 10000))
                .addClaims(claims)
                .compact();
    }

    public static Claims getClaims(String token) {

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * @Description: 验证合法性
     * @Param: [claims]
     * @return: java.lang.Integer
     * @Author: 斗战圣洋
     * @Date: 2023/12/26 12:49
     */
    public static Integer verify(Claims claims) {
        if (claims == null) {
            return -2;
        }
        try {
            boolean isExpire = claims.getExpiration().after(new Date());
            if (isExpire) {
                return -1;
            }
        } catch (Exception e) {
            return -2;
        }
        return 0;
    }
    private static byte[] getKey(){
        return Base64.decode(TOKEN_SECRET);
    }

}
