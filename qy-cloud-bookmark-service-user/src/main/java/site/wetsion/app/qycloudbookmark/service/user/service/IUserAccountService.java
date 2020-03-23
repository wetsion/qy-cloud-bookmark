package site.wetsion.app.qycloudbookmark.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserAccount;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserInfo;

/**
 *
 *
 * Created by wetsion on 2020/3/17.
 */
public interface IUserAccountService extends IService<UserAccount> {

    /**
     * 获取完整的用户信息
     * @param userAccount 用户基本账户信息
     * @return
     */
    UserInfo getCompleteUserInfo(UserAccount userAccount);
}
