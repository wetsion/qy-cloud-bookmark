package site.wetsion.app.qycloudbookmark.api.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import site.wetsion.app.qycloudbookmark.common.constant.AppsConstant;

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
}
