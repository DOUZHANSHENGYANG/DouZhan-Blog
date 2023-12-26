package xyz.douzhan.blog.security;


import cn.hutool.core.util.StrUtil;
import constants.AuthConstant;
import constants.RedisConstant;
import exception.AuthException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.douzhan.blog.po.User;
import xyz.douzhan.blog.service.UserService;
import xyz.douzhan.blog.utils.CypherUtil;
import xyz.douzhan.blog.utils.RedisUtil;
import xyz.douzhan.blog.utils.JwtUtil;

import java.io.IOException;
import java.util.Collections;

/**
 * Jwt过滤器
 *
 * @author 斗战圣洋
 * @date 2023/12/26 19:22
 * @since JDK 17
 **/
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final String USER_ID="id";
    private static final String BEARER="Bearer ";
    private  UserService userService;
    private AuthenticationManager authenticationManager;

    public JwtAuthFilter(UserService userService,AuthenticationManager authenticationManager) {
        this.userService=userService;
        this.authenticationManager=authenticationManager;
    }



    /**
    * @Description: 过滤器
    * @Param: [request, response, filterChain]
    * @return: void
    * @Author: 斗战圣洋
    * @Date: 2023/12/26 19:33
    */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AuthConstant.TOKEN_HEADER);
        if (StrUtil.isEmpty(authHeader)||!authHeader.startsWith(BEARER)){
            filterChain.doFilter(request,response);
            return;
        }
        String token = authHeader.substring(7);

        Object redisToken = RedisUtil.get(RedisConstant.USER_TOKEN_PREFIX + token);
        if (redisToken!=null){
            Integer userId = (Integer) redisToken;
            postAuthSuccess(request,userId.byteValue());
            filterChain.doFilter(request,response);
            return;
        }

        Claims claims = JwtUtil.getClaims(token);
        if (claims==null){
            throw new AuthException(AuthConstant.INVALID_TOKEN);
        }

        Integer isExpire = JwtUtil.verify(claims);
        if (isExpire!=0){
            throw new AuthException(AuthConstant.EXPIRED_TOKEN);
        }

        Byte id = (Byte) claims.get(USER_ID);
        postAuthSuccess(request,id);
        filterChain.doFilter(request,response);
    }

    private  void postAuthSuccess(HttpServletRequest request,Byte id) {
        User user = userService.getById(id);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                CypherUtil.decodeWithSm4(user.getPassword()),
                Collections.emptyList()
        );
        authRequest.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticate = authenticationManager.authenticate(authRequest);

        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }
}
