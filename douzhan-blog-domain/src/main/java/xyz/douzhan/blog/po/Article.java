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
import java.time.LocalDateTime;

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
@TableName("article")
@Schema( description = "Article对象")
public class Article implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户id")
    @TableField("user_id")
    private Byte userId;

    @Schema(description = "文章标题")
    @TableField("title")
    private String title;

    @Schema(description = "文章概要")
    @TableField("outline")
    private String outline;

    @Schema(description = "修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "文章存放路径")
    @TableField("uri")
    private String uri;
}
