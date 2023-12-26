package xyz.douzhan.blog.config;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson2.JSON;
import constants.AuthConstant;
import constants.RedisConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import xyz.douzhan.blog.dto.ResponseResult;
import xyz.douzhan.blog.security.JwtAuthFilter;
import xyz.douzhan.blog.security.MyPasswordEncoder;
import xyz.douzhan.blog.security.MyUsernamePasswordFilter;
import xyz.douzhan.blog.security.UserDetailsServiceImpl;
import xyz.douzhan.blog.service.UserService;
import xyz.douzhan.blog.utils.RedisUtil;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 * date: 2023/12/14 16:09
 *
 * @author 斗战圣洋
 * @since JDK 17
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final MyPasswordEncoder passwordEncoder;
    private final UserService userService;

    /**
    * @Description: 自定义安全管理器 
    * @Param: []
    * @return: org.springframework.security.authentication.AuthenticationManager 
    * @Author: 斗战圣洋
    * @Date: 2023/12/26 20:48
    */
    
    @Bean
    @Primary
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return new ProviderManager(provider);
    }

    /**
     * @Description: 静态资源放行
     * @Param: []
     * @return: org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
     * @Author: 斗战圣洋
     * @Date: 2023/12/26 19:36
     */

    @Bean
    public WebSecurityCustomizer securityCustomizer() {
        return web -> web.ignoring().requestMatchers("/static/**");
    }

    /**
     * @Description: 配置过滤器链
     * @Param: [http]
     * @return: org.springframework.security.web.SecurityFilterChain
     * @Author: 斗战圣洋
     * @Date: 2023/12/26 19:36
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(AuthConstant.WHITE_LIST.toArray(new String[0])).permitAll()
                        .anyRequest().authenticated()
                )
                // jwt校验
                .addFilterBefore(new JwtAuthFilter(userService,authenticationManager()), LogoutFilter.class)
                // 自定义用户名密码校验
                .addFilterBefore(new MyUsernamePasswordFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl(AuthConstant.LOGOUT_URL)
                        .logoutSuccessHandler((request, response, authentication) -> sendMessage(
                                request,
                                response,
                                ResponseResult.success(AuthConstant.LOGOUT_SUCCESS),
                                null,
                                true)))
                //类错误异常处理 以下针对于访问资源路径 认证异常捕获 和 无权限处理
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> sendMessage(
                                request,
                                response,
                                ResponseResult.error(authException.getMessage()).code(HttpStatus.HTTP_UNAUTHORIZED), authException,false))
                        .accessDeniedHandler((request, response, accessDeniedException) -> sendMessage(
                                request,
                                response,
                                ResponseResult.error(accessDeniedException.getMessage()).code(HttpStatus.HTTP_UNAUTHORIZED), accessDeniedException,false)))
                // 禁用session jwt就可以了
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

    /**
     * @Description: 统一返回异常处理结果方法
     * @Param: [response, result, e]
     * @return: void
     * @Author: 斗战圣洋
     * @Date: 2023/12/26 19:36
     */
    private void sendMessage(HttpServletRequest request,HttpServletResponse response, ResponseResult result, Exception e, boolean flag) throws IOException {
        if (flag){
            RedisUtil.del(RedisConstant.USER_TOKEN_PREFIX+request.getHeader(AuthConstant.TOKEN_HEADER).substring(7));
        }
        if (e != null) {
            log.error(e.getMessage());
        }
        response.setContentType("application/json; charset=utf-8");
        String jsonResult = JSON.toJSONString(result);
        PrintWriter writer = response.getWriter();
        writer.print(jsonResult);
        writer.flush();
        writer.close();
    }

}
