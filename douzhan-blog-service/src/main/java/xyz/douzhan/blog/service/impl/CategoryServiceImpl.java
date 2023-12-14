package xyz.douzhan.blog.service.impl;

import xyz.douzhan.blog.service.CategoryService;
import xyz.douzhan.blog.test.entity.Category;
import xyz.douzhan.blog.mapper.CategoryMapper;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
