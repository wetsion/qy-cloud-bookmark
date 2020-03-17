package site.wetsion.app.qycloudbookmark.api.user.vo;

import lombok.Data;

/**
 * 视图展示用的用户类
 *
 * Created by wetsion on 2020/3/17.
 */
@Data
public class UserVo {

    private Long id;

    /** 账号 */
    private String account;

    /** 手机号 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 头像 */
    private String avatar;
}
