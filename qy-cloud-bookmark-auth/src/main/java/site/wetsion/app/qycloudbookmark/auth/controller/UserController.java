package site.wetsion.app.qycloudbookmark.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author weixin
 * @version 1.0
 * @CLassName UserController
 * @date 2020/3/24 8:23 PM
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public Principal userInfo(Principal principal) {
        log.info("user info: {} ", principal.toString());
        return principal;
    }
}
