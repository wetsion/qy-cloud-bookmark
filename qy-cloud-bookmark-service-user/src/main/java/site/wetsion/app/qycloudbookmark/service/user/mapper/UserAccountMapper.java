package site.wetsion.app.qycloudbookmark.service.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserAccount;

/**
 * user_account mapper 接口
 *
 * Created by wetsion on 2020/3/17.
 */
@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccount> {
}
