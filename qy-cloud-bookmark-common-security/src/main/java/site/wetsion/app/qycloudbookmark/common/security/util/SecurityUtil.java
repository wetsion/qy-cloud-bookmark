package site.wetsion.app.qycloudbookmark.common.security.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import site.wetsion.app.qycloudbookmark.common.security.service.AuthUser;

/**
 * 工具类，从认证信息中获取用户信息
 *
 * @author weixin
 * @version 1.0
 * @date 2020/3/26 4:54 PM
 */
@UtilityClass
public class SecurityUtil {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public AuthUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthUser) {
            return (AuthUser) principal;
        }
        return null;
    }

    public AuthUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }
}
