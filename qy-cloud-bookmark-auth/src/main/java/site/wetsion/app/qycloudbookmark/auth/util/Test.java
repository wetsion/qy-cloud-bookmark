package site.wetsion.app.qycloudbookmark.auth.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author weixin
 * @version 1.0
 * @CLassName Test
 * @date 2020/3/24 3:58 PM
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
