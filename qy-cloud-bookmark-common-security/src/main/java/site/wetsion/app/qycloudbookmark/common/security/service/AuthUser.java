package site.wetsion.app.qycloudbookmark.common.security.service;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * security 使用的用户
 *
 * Created by wetsion on 2020/3/24.
 */
@Data
public class AuthUser extends User {

    private Long id;

    public AuthUser(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = userId;
    }

}
