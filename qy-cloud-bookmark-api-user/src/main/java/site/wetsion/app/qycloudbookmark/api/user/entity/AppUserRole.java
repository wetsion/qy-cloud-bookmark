package site.wetsion.app.qycloudbookmark.api.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import site.wetsion.app.qycloudbookmark.common.dto.BaseDto;

/**
 * Created by wetsion on 2020/3/23.
 */
@Data
@TableName("app_user_role")
public class AppUserRole extends BaseDto {

    @TableId
    private Long id;

    private Long userId;

    private Long roleId;
}
