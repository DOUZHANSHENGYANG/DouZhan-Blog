package xyz.douzhan.blog.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Description: 自定义用户详情
 * date: 2023/12/14 19:57
 *
 * @author 斗战圣洋
 * @since JDK 17
 */

@Getter
public class MyUserDetails extends User {
    private byte id;
    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, byte id) {
        super(username, password, authorities);
        this.id=id;
    }

    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MyUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
    
}
