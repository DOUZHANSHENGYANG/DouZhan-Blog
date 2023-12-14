package xyz.douzhan.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.douzhan.blog.dto.LoginDto;
import xyz.douzhan.blog.dto.ResponseResult;
import xyz.douzhan.blog.service.UserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 斗战圣洋
 * @since 2023-12-14
 */
@RestController
@RequestMapping("/blog/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login/login_auth")
    public ResponseResult loginAuth(@RequestBody LoginDto loginDto){
        userService.loginAuth(loginDto);
        return ResponseResult.success();
    }
}
