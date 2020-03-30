package site.wetsion.app.qycloudbookmark.api.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import site.wetsion.app.qycloudbookmark.common.dto.BaseDto;

/**
 * 角色
 *
 * Created by wetsion on 2020/3/23.
 */
@Data
@TableName("app_role")
public class AppRole extends BaseDto {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String roleName;

    private String roleCode;

    private String roleDesc;
}
