package site.wetsion.app.qycloudbookmark.common.security.annotation;

import java.lang.annotation.*;

/**
 * 声明是内部服务间调用的接口
 *
 * @author weixin
 * @version 1.0
 * @CLassName InnerInvokeApi
 * @date 2020/3/26 7:44 PM
 */
@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InnerInvokeApi {

    boolean value() default true;

}
