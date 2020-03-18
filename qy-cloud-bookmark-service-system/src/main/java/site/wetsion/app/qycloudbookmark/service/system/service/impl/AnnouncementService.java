package site.wetsion.app.qycloudbookmark.service.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.wetsion.app.qycloudbookmark.api.system.entity.Announcement;
import site.wetsion.app.qycloudbookmark.service.system.mapper.AnnouncementMapper;
import site.wetsion.app.qycloudbookmark.service.system.service.IAnnouncementService;

/**
 * Created by wetsion on 2020/3/18.
 */
@Service
@Transactional
public class AnnouncementService extends ServiceImpl<AnnouncementMapper, Announcement>
        implements IAnnouncementService {
}
