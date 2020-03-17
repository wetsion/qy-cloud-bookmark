package site.wetsion.app.qycloudbookmark.api.bookmark.feign;

import org.springframework.cloud.openfeign.FeignClient;
import site.wetsion.app.qycloudbookmark.common.constant.AppsConstant;

/**
 * service bookmark 的远程feign客户端
 *
 * Created by wetsion on 2020/3/18.
 */
@FeignClient(
        value = AppsConstant.APP_BOOKMARK_SERVICE,
        fallback = IBookmarkServiceClientFallback.class
)
public interface IBookmarkServiceClient {
}
