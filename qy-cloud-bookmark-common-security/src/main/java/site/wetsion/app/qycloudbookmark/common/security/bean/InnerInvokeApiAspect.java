package site.wetsion.app.qycloudbookmark.common.security.bean;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import site.wetsion.app.qycloudbookmark.common.constant.AuthConstant;
import site.wetsion.app.qycloudbookmark.common.security.annotation.InnerInvokeApi;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 对被 {@link InnerInvokeApi} 声明的方法做切面拦截，进一步判断是否有访问权限
 *
 * @author weixin
 * @version 1.0
 * @date 2020/3/26 7:46 PM
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class InnerInvokeApiAspect {

    @SneakyThrows
    @Around("@annotation(inner)")
    public Object around(ProceedingJoinPoint point, InnerInvokeApi inner) {
        HttpServletRequest request =
                ((ServletRequestAttributes )Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest();
        String header = request.getHeader(AuthConstant.FROM);
        if (!inner.value()) {
            log.info("访问的接口 {} 非内部调用接口，无权限访问", point.getSignature().getName());
            throw new AccessDeniedException("Access is denied");
        }
        if (inner.value() && !AuthConstant.FROM_IN.equals(header)) {
            log.info("访问接口 {} 没有权限", point.getSignature().getName());
            throw new AccessDeniedException("Access is denied");
        }
        return point.proceed();
    }
}
