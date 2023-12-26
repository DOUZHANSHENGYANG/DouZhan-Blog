package xyz.douzhan.blog.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import constants.AuthConstant;
import exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.douzhan.blog.po.User;
import xyz.douzhan.blog.service.UserService;

import java.util.Collections;

/**
 * Description:
 * date: 2023/12/14 19:52
 *
 * @author 斗战圣洋
 * @since JDK 17
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username==null){
            throw new AuthException(AuthConstant.USERNAME_NOT_NULL);
        }
        User user = userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username));
        if (user==null){
            throw new AuthException(AuthConstant.USERNAME_NOT_EXISTS);
        }
        return new MyUserDetails(username,user.getPassword(), Collections.emptyList(),user.getId());
    }
}
