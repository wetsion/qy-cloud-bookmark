package site.wetsion.app.qycloudbookmark.service.system.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.wetsion.app.qycloudbookmark.api.system.entity.Announcement;
import site.wetsion.app.qycloudbookmark.api.system.vo.AnnouncementVo;
import site.wetsion.app.qycloudbookmark.common.constant.AppsConstant;
import site.wetsion.app.qycloudbookmark.service.system.service.IAnnouncementService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wetsion on 2020/3/18.
 */
@RestController
@AllArgsConstructor
@RequestMapping(AppsConstant.APP_COMMON_API_PREFIX + "/announcement")
public class AnnouncementController {

    IAnnouncementService announcementService;

    @GetMapping("/list")
    public List<AnnouncementVo> getAnnouncementList() {
        // TODO 需要修改
        List<Announcement> announcements = announcementService.list();
        return announcements.stream().map(announcement -> AnnouncementVo.transfer(announcement, null))
                .collect(Collectors.toList());
    }
}
