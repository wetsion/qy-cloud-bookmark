package site.wetsion.app.qycloudbookmark.gateway.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.wetsion.app.qycloudbookmark.common.util.R;

/**
 * Created by wetsion on 2020/3/27.
 */
//@RestController
public class FallbackEndpoint {

    @GetMapping("/fallback")
    public R fallback() {
        return R.fail("服务器异常");
    }
}
