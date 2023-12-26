package xyz.douzhan.blog.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * druid数据库连接
 *
 * @author 斗战圣洋
 * @date 2023/12/26 22:21
 * @since JDK 17
 **/
@Configuration
public class DruidDataSourceConfig {

    /**
     * 添加 DruidDataSource 组件到容器中，并绑定属性
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
    public DataSource druid(){
        return  new DruidDataSource();
    }
}
