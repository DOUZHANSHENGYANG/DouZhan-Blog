package xyz.douzhan.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * date: 2023/12/14 16:07
 *
 * @author 斗战圣洋
 * @since JDK 17
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DouzhanBlogAPI 接口文档")
                        .description("博客接口文档")
                        .version("v1.0.0")
                        .contact(new Contact().name("斗战圣洋").email("3555936530@qq.com"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                );

    }

    @Bean
    public GroupedOpenApi clientApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/blog/**")
                .build();
    }
}
