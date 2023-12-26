package xyz.douzhan.blog.security;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.douzhan.blog.utils.CypherUtil;

/**
 * Description:
 * date: 2023/12/14 20:00
 *
 * @author 斗战圣洋
 * @since JDK 17
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {


    /**
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return CypherUtil.encodeWithSm4(rawPassword.toString());
    }

    /**
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (StrUtil.equals(encode(rawPassword),encodedPassword)){
            return true;
        }
        return false;
    }




}
