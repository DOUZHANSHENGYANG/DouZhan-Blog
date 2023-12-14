package xyz.douzhan.blog.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 斗战圣洋
 * @since 2023-12-14
 */
@Getter
@Setter
@TableName("user")
@Schema(description = "User对象")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Byte id;

    @Schema(description = "姓名")
    @TableField("nickname")
    private String nickname;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "qq")
    @TableField("qq")
    private String qq;

    @Schema(description = "qq邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "github")
    @TableField("github")
    private String github;

    @Schema(description = "头像")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "座右铭")
    @TableField("motto")
    private String motto;

    @Schema(description = "自我描述")
    @TableField("description")
    private String description;

}
