package site.wetsion.app.qycloudbookmark.api.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import site.wetsion.app.qycloudbookmark.common.dto.BaseDto;

import java.time.LocalDateTime;

/**
 * 用户账户信息，账号、密码、手机等
 *
 * Created by wetsion on 2020/3/16.
 */
@Data
@TableName("user_account")
public class UserAccount extends BaseDto {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 账号 */
    private String account;

    /** 密码 */
    private String password;

    /** 手机号 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 头像 */
    private String avatar;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModify;

}
