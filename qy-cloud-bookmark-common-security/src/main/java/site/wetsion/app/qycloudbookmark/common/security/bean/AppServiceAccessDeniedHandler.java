package site.wetsion.app.qycloudbookmark.common.security.bean;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import site.wetsion.app.qycloudbookmark.common.constant.ResponseConstant;
import site.wetsion.app.qycloudbookmark.common.util.R;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 授权被拒绝后的统一异常处理
 *
 * @author weixin
 * @version 1.0
 * @date 2020/3/26 2:28 PM
 */
@Slf4j
@Component
public class AppServiceAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        log.info("授权失败，禁止访问 {}", request.getRequestURI());
        response.setCharacterEncoding(ResponseConstant.CHARSET_UTF8);
        response.setContentType(ResponseConstant.CONTENT_TYPE_JSON);
        R<Exception> r = R.fail(new Exception("授权失败，禁止访问"));
        response.setStatus(HttpStatus.FORBIDDEN.value());
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSON.toJSONString(r));
    }
}
