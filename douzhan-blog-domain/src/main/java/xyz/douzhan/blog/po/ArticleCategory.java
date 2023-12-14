package xyz.douzhan.blog.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

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
@TableName("article_category")
@Schema(description = "ArticleCategory对象")
public class ArticleCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "分类id")
    @TableId("category_id")
    private Integer categoryId;

    @Schema(description = "文章id")
    @TableId("article_id")
    private Integer articleId;
}
