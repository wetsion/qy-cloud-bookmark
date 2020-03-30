package site.wetsion.app.qycloudbookmark.common.security.bean;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;
import site.wetsion.app.qycloudbookmark.common.constant.AuthConstant;
import site.wetsion.app.qycloudbookmark.common.security.service.AuthUser;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 从认证中抽取用户信息
 *
 * @author weixin
 * @version 1.0
 * @date 2020/3/26 5:40 PM
 */
public class AuthUserAuthenticationConverter implements UserAuthenticationConverter {

    private final static String NA_PWD = "N/A";

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(USERNAME, userAuthentication.getName());
        if (userAuthentication.getAuthorities() != null && !userAuthentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(userAuthentication.getAuthorities()));
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(map);

            String username = (String) map.get(USERNAME);
            Object uid = map.get(AuthConstant.LABEL_USERID);
            Long userId;
            if (uid instanceof Integer) {
                userId = ((Integer) uid).longValue();
            } else {
                userId = (Long) uid;
            }
            AuthUser user = new AuthUser(userId, username, NA_PWD, authorities);
            return new UsernamePasswordAuthenticationToken(user, NA_PWD, authorities);
        }
        return null;
    }

    /**
     * 抽取权限信息
     *
     * @param map token的map
     * @author weixin
     * @date 6:25 PM 2020/3/26
     * @return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
     **/
    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        Object authorities = map.get(AUTHORITIES);
        if (Objects.isNull(authorities)) {
            return AuthorityUtils.NO_AUTHORITIES;
        }
        if (authorities instanceof String) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
        }
        if (authorities instanceof Collection) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
                    .collectionToCommaDelimitedString((Collection<?>) authorities));
        }
        throw new IllegalArgumentException("权限必须为字符串或者集合形式");
    }
}
