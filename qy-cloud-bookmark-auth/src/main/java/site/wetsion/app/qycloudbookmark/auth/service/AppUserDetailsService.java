package site.wetsion.app.qycloudbookmark.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.wetsion.app.qycloudbookmark.api.user.entity.AppRole;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserAccount;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserInfo;
import site.wetsion.app.qycloudbookmark.api.user.feign.IUserServiceClient;
import site.wetsion.app.qycloudbookmark.common.constant.AuthConstant;
import site.wetsion.app.qycloudbookmark.common.util.R;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * security 获取用户信息
 *
 * Created by wetsion on 2020/3/23.
 */
@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private IUserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        R<UserInfo> userInfoR =
                userServiceClient.getUserInfoByUsername(username, AuthConstant.FROM_IN);
        List<AppRole> appRoles = userInfoR.getData().getAppRoles();
        UserAccount userAccount = userInfoR.getData().getUserAccount();
        Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(
                        appRoles.stream().map(AppRole::getRoleCode)
                        .collect(Collectors.toList()).toArray(new String[0]));
        return new AuthUser(userAccount.getId(), username, userAccount.getPassword(), authorities);
    }
}
