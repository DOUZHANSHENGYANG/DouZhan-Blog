package xyz.douzhan.blog.utils;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.SM4;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

/**
 * 加密工具类
 *
 * @author 斗战圣洋
 * @date 2023/12/26 19:01
 * @since JDK 17
 **/
public class CypherUtil {

    private static SM4 getSm4() {
        return new SM4(Mode.CBC, Padding.ZeroPadding,
                "abc1111111111333".getBytes(CharsetUtil.CHARSET_UTF_8),
                "huiyinwobaailiya".getBytes(CharsetUtil.CHARSET_UTF_8));
    }

    /**
     * @Description: sm4算法加密
     * @Param: [rawString]
     * @return: java.lang.String
     * @Author: 斗战圣洋
     * @Date: 2023/12/26 19:07
     */

    public static String encodeWithSm4(String rawString) {
        return getSm4().encryptBase64(rawString);
    }

    /**
     * @Description: sm4算法解密
     * @Param: [encodedString]
     * @return: java.lang.String
     * @Author: 斗战圣洋
     * @Date: 2023/12/26 19:06
     */

    public static String decodeWithSm4(String encodedString) {
        return getSm4().decryptStr(Base64.decode(encodedString));
    }

   /**
   * @Description: 生成DES秘钥
   * @Param: [secret]
   * @return: java.security.Key
   * @Author: 斗战圣洋
   * @Date: 2023/12/26 23:16
   */
    public static Key genDesKey(String secret) throws Exception{
        DESKeySpec dks = new DESKeySpec(Base64.decode(secret));
        return SecretKeyFactory.getInstance("DES").generateSecret(dks);
    }

}
