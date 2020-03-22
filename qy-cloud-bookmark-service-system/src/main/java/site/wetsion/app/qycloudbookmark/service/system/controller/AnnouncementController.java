package site.wetsion.app.qycloudbookmark.service.system.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.wetsion.app.qycloudbookmark.api.system.entity.Announcement;
import site.wetsion.app.qycloudbookmark.api.system.vo.AnnouncementVo;
import site.wetsion.app.qycloudbookmark.common.constant.AppsConstant;
import site.wetsion.app.qycloudbookmark.common.util.R;
import site.wetsion.app.qycloudbookmark.service.system.service.IAnnouncementService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 公告controller
 *
 * Created by wetsion on 2020/3/18.
 */
@RestController
@AllArgsConstructor
@RequestMapping(AppsConstant.APP_COMMON_API_PREFIX + "/announcement")
public class AnnouncementController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AnnouncementController.class);

    private IAnnouncementService announcementService;

    @GetMapping("/list")
    public R getAnnouncementList() {
        LOGGER.info("通知列表");
        // TODO 需要修改
        List<Announcement> announcements = announcementService.list();
        return R.ok(announcements.stream().map(announcement -> AnnouncementVo.transfer(announcement, null))
                .collect(Collectors.toList()));
    }
}
