package site.wetsion.app.qycloudbookmark.api.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import site.wetsion.app.qycloudbookmark.api.system.vo.AnnouncementVo;
import site.wetsion.app.qycloudbookmark.common.constant.AppsConstant;

import java.util.List;

/**
 * 系统服务的远程feign客户端
 *
 * Created by wetsion on 2020/3/18.
 */
@FeignClient(value = AppsConstant.APP_SYSTEM_SERVICE,
        fallback = ISystemServiceClientFallback.class)
public interface ISystemServiceClient {

    @GetMapping(AppsConstant.APP_COMMON_API_PREFIX + "/list")
    List<AnnouncementVo> getAnnouncementList();
}
