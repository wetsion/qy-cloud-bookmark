package site.wetsion.app.qycloudbookmark.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.wetsion.app.qycloudbookmark.api.user.entity.AppRole;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserAccount;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserInfo;
import site.wetsion.app.qycloudbookmark.service.user.mapper.AppRoleMapper;
import site.wetsion.app.qycloudbookmark.service.user.mapper.UserAccountMapper;
import site.wetsion.app.qycloudbookmark.service.user.service.IUserAccountService;

import java.util.List;

/**
 *  用户账户
 *
 * Created by wetsion on 2020/3/17.
 */
@AllArgsConstructor
@Service
@Transactional
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount>
        implements IUserAccountService {

    private AppRoleMapper appRoleMapper;

    @Override
    public UserInfo getCompleteUserInfo(UserAccount userAccount) {
        List<AppRole> appRoles = appRoleMapper.getRolesByUserId(userAccount.getId());
        UserInfo userInfo = new UserInfo();
        userInfo.setAppRoles(appRoles);
        userInfo.setUserAccount(userAccount);
        return userInfo;
    }
}
