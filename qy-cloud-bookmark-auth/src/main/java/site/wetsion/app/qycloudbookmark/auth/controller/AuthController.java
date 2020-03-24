package site.wetsion.app.qycloudbookmark.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Created by wetsion on 2020/3/25.
 */
@Controller
public class AuthController {

    @GetMapping("/auth/login")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://www.baidu.com");
    }
}
