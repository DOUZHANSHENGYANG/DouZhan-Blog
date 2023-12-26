package constants;

import java.util.List;

/**
 * Description:
 * date: 2023/12/14 16:24
 *
 * @author 斗战圣洋
 * @since JDK 17
 */
public class AuthConstant {
    public static final String LOGOUT_URL="/blog/user/logout";
    public static final List<String> WHITE_LIST= List.of(
            "/doc.html","/swagger-resources/**","/v2/api-docs","/swagger-ui.html","/v3/api-docs/**","/swagger-ui/index.html", "/test/**");
    public static final String TOKEN_HEADER="Authorization";
    public static final String INVALID_TOKEN="不合法token";
    public static final String EXPIRED_TOKEN="token过期";
    public static final String LOGOUT_SUCCESS="退出成功";
    public static final String USERNAME_NOT_NULL="用户名不能为空";
    public static final String USERNAME_NOT_EXISTS="用户名不存在";
    public static final String AUTH_METHOD_NOT_SUPPORT="认证方法不支持";
    public static final String PARSE_REQUEST_BODY_ERROR="解析请求体错误";
    public static final String GEN_TOKEN_ERROR="token生成异常";
}
