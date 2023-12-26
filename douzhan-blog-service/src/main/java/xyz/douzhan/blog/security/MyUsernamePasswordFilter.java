package xyz.douzhan.blog.security;


import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.AuthConstant;
import constants.RedisConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import xyz.douzhan.blog.utils.RedisUtil;
import xyz.douzhan.blog.dto.ResponseResult;
import xyz.douzhan.blog.utils.JwtUtil;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 自定义用户名密码登录
 *
 * @author 斗战圣洋
 * @date 2023/12/26 20:15
 * @since JDK 17
 **/
public class MyUsernamePasswordFilter extends AbstractAuthenticationProcessingFilter {
    public static final String MY_FORM_USERNAME_KEY = "username";
    public static final String MY_FORM_PASSWORD_KEY = "password";
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/blog/user/login", "POST");
    private String usernameParameter = "username";
    private String passwordParameter = "password";
    @Setter
    private boolean postOnly = true;

    public MyUsernamePasswordFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String post = "POST";
        if (this.postOnly && !post.equals(request.getMethod())) {
            throw new AuthenticationServiceException(AuthConstant.AUTH_METHOD_NOT_SUPPORT + request.getMethod());
        } else {
            String username = null;
            String password = null;
            try {
                username = request.getParameter(usernameParameter);
                password = request.getParameter(passwordParameter);
                username = username != null ? username.trim() : "";
                password = password != null ? password : "";
            } catch (Exception e) {
                throw new AuthenticationServiceException(AuthConstant.PARSE_REQUEST_BODY_ERROR + e.getMessage());
            }
            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
            this.setDetails(request, authRequest);
            Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
            if (authentication.isAuthenticated()) {
                String token = null;
                try {
                    MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                    token = JwtUtil.genToken(userDetails.getId());
                    RedisUtil.setWithExpire(RedisConstant.USER_TOKEN_PREFIX + token, userDetails.getId(), JwtUtil.TOKEN_TIME_OUT,TimeUnit.HOURS);

                    response.setContentType("application/json; charset=utf-8");
                    String jsonResult = JSON.toJSONString(ResponseResult.success(token));
                    PrintWriter writer = response.getWriter();
                    writer.print(jsonResult);
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    throw new AuthenticationServiceException(AuthConstant.GEN_TOKEN_ERROR + e.getMessage());
                }
            }
            return authentication;
        }
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public final String getUsernameParameter() {
        return this.usernameParameter;
    }

    public final String getPasswordParameter() {
        return this.passwordParameter;
    }

}
