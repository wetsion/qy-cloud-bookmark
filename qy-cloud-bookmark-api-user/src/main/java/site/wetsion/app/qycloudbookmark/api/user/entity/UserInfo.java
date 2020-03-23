package site.wetsion.app.qycloudbookmark.api.user.entity;

import lombok.Data;

import java.util.List;

/**
 * 用户信息，包含账户信息和角色
 *
 * Created by wetsion on 2020/3/16.
 */
@Data
public class UserInfo {

    private UserAccount userAccount;

    private List<AppRole> appRoles;
}
