package site.wetsion.app.qycloudbookmark.api.user.feign;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserInfo;
import site.wetsion.app.qycloudbookmark.common.util.R;

/**
 * <p>{@link IUserServiceClient} 的失败处理</p>
 *
 * Created by wetsion on 2020/3/16.
 */
@Slf4j
@AllArgsConstructor
@Component
public class IUserServiceClientFallback implements IUserServiceClient {

    private Throwable throwable;

    @Override
    public R<UserInfo> getUserInfoByUsername(String account, String from) {
        log.error("feign 根据用户账号：{} 获取用户信息失败", account, throwable);
        return null;
    }

    @Override
    public R<UserInfo> getUserInfoBySocial(String socialStr) {
        log.error("feign 根据社交账号：{} 获取用户信息失败", socialStr, throwable);
        return null;
    }
}
