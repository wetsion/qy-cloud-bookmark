package site.wetsion.app.qycloudbookmark.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.wetsion.app.qycloudbookmark.api.user.entity.UserAccount;
import site.wetsion.app.qycloudbookmark.service.user.mapper.UserAccountMapper;
import site.wetsion.app.qycloudbookmark.service.user.service.IUserAccountService;

/**
 *  用户账户
 *
 * Created by wetsion on 2020/3/17.
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount>
        implements IUserAccountService {
}
