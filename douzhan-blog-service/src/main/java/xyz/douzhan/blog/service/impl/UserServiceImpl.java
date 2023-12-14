package xyz.douzhan.blog.service.impl;

import xyz.douzhan.blog.dto.LoginDto;
import xyz.douzhan.blog.po.User;
import xyz.douzhan.blog.service.UserService;
import xyz.douzhan.blog.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 斗战圣洋
 * @since 2023-12-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 登录
     *
     * @param loginDto
     */
    @Override
    public void loginAuth(LoginDto loginDto) {

    }
}
