package xyz.douzhan.blog.mapper;

import xyz.douzhan.blog.po.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 斗战圣洋
 * @since 2023-12-14
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
