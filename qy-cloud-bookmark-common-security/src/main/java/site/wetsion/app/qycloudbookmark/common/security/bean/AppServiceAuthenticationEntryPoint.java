package site.wetsion.app.qycloudbookmark.common.security.bean;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import site.wetsion.app.qycloudbookmark.common.constant.ResponseConstant;
import site.wetsion.app.qycloudbookmark.common.util.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 子服务认证失败统一处理
 *
 * @author weixin
 * @version 1.0
 * @date 2020/3/26 11:53 AM
 */
@Slf4j
@Component
public class AppServiceAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        response.setCharacterEncoding(ResponseConstant.CHARSET_UTF8);
        response.setContentType(ResponseConstant.CONTENT_TYPE_JSON);
        R result = R.fail();
        if (authException != null) {
            result = R.fail(authException.getMessage(), "error", HttpStatus.UNAUTHORIZED.value());
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSON.toJSONString(result));
    }
}
