package site.wetsion.app.qycloudbookmark.api.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserInfo;
import site.wetsion.app.qycloudbookmark.common.constant.AppsConstant;
import site.wetsion.app.qycloudbookmark.common.constant.AuthConstant;
import site.wetsion.app.qycloudbookmark.common.util.R;

/**
 * 用户服务 feign 远程客户端
 *
 * Created by wetsion on 2020/3/16.
 */
@FeignClient(
        value = AppsConstant.APP_USER_SERVICE,
        fallback = IUserServiceClientFallback.class
)
public interface IUserServiceClient {

    /**
     * 根据用户名获取用户信息
     *
     * @param account 账号
     * @param from 调用方式
     * @return
     */
    @GetMapping(AppsConstant.APP_COMMON_API_PREFIX + "/user/info/{account}")
    R<UserInfo> getUserInfoByUsername(@PathVariable("account") String account,
                                      @RequestHeader(AuthConstant.FROM) String from);

    /**
     * 根据社交信息email、手机号获取用户信息
     *
     * @param socialStr 社交账号
     * @return
     */
    @GetMapping(AppsConstant.APP_COMMON_API_PREFIX + "/user/social/{socialStr}")
    R<UserInfo> getUserInfoBySocial(@PathVariable("socialStr") String socialStr);
}
