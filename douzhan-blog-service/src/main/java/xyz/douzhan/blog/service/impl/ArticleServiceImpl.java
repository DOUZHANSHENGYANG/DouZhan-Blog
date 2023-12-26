package xyz.douzhan.blog.service.impl;

import xyz.douzhan.blog.po.Article;
import xyz.douzhan.blog.mapper.ArticleMapper;
import xyz.douzhan.blog.service.ArticleService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
