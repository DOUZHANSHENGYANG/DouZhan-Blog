package xyz.douzhan.blog.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Slf4j
@Tag(name = "用户管理")
public class UserController {
}
