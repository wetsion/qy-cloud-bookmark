package site.wetsion.app.qycloudbookmark.service.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserAccount;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserInfo;
import site.wetsion.app.qycloudbookmark.common.constant.AppsConstant;
import site.wetsion.app.qycloudbookmark.common.util.R;
import site.wetsion.app.qycloudbookmark.service.user.service.IUserAccountService;

import java.util.Objects;

/**
 * 用户rest接口
 *
 * Created by wetsion on 2020/3/16.
 */
@RestController
@RequestMapping(AppsConstant.APP_COMMON_API_PREFIX + "/user")
@AllArgsConstructor
public class UserController {

    private final IUserAccountService userAccountService;

    /**
     * 根据用户账号获取用户全部信息
     * @param account 账号
     * @return
     */
    @GetMapping("/info/{account}")
    public R getUserInfoByUsername(@PathVariable("account") String account) {
        UserAccount userAccount = userAccountService.getOne(
                Wrappers.<UserAccount>query()
                        .lambda().eq(UserAccount::getAccount, account));
        if (Objects.isNull(userAccount)) {
            return R.fail("用户不存在！");
        }
        return R.ok(userAccountService.getCompleteUserInfo(userAccount));
    }

    @GetMapping("/social/{socialStr}")
    public R<UserInfo> getUserInfoBySocial(@PathVariable("socialStr") String socialStr) {
        QueryWrapper<UserAccount> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq("phone", socialStr).or().eq("email", socialStr);
        UserAccount userAccount = userAccountService.getOne(
                Wrappers.<UserAccount>query().lambda()
                        .eq(UserAccount::getEmail, socialStr)
                        .or()
                        .eq(UserAccount::getPhone, socialStr)
        );
        if (Objects.isNull(userAccount)) {
            return R.fail("用户不存在！");
        }
        return R.ok(userAccountService.getCompleteUserInfo(userAccount));
    }
}
