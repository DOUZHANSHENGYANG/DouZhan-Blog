package xyz.douzhan.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Description:
 * date: 2023/12/14 11:29
 *
 * @author 斗战圣洋
 * @since JDK 17
 */

public class LoginDto {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;

}
