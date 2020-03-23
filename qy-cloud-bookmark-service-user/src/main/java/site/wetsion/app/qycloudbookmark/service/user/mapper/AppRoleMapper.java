package site.wetsion.app.qycloudbookmark.service.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import site.wetsion.app.qycloudbookmark.api.user.entity.AppRole;

import java.util.List;

/**
 * Created by wetsion on 2020/3/23.
 */
@Mapper
public interface AppRoleMapper extends BaseMapper<AppRole> {

    /**
     * 根据用户ID获取拥有的角色
     * @param userId
     * @return
     */
    @Select("SELECT r.* FROM app_role r LEFT JOIN " +
            "app_user_role ur ON r.id = ur.role_id WHERE ur.user_id = #{userId} ")
    List<AppRole> getRolesByUserId(@Param("userId") Long userId);

}
