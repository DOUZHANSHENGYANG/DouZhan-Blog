package xyz.douzhan.blog.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Description:
// * date: 2023/12/13 23:18
// *
// * @author 斗战圣洋
// * @since JDK 17
// */
//@Configuration
//public class PrometheusConfig {
//
//    /**
//     * 为普罗米修斯命名*
//     *
//     * @param applicationName
//     * @return
//     */
//    @Bean
//    MeterRegistryCustomizer<MeterRegistry> configure(@Value("${spring.application.name") String applicationName) {
//        return register -> {
//            register.config().commonTags("application", applicationName);
//        };
//    }
//}
