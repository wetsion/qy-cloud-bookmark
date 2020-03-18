package site.wetsion.app.qycloudbookmark.api.system.vo;

import lombok.Builder;
import lombok.Data;
import site.wetsion.app.qycloudbookmark.api.system.entity.Announcement;
import site.wetsion.app.qycloudbookmark.api.system.entity.Verbosity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用于视图层的公告实体
 *
 * Created by wetsion on 2020/3/18.
 */
@Data
@Builder
public class AnnouncementVo {

    private Long id;

    /** 标题 **/
    private String title;

    /** 简述 */
    private String introduction;

    /** 公告形式，默认0：简单公告，1：大段文本公告 */
    private int form;

    /** 大段文本 */
    private String content;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModify;

    public static AnnouncementVo transfer(Announcement announcement, Verbosity verbosity) {
        return AnnouncementVo.builder()
                .id(announcement.getId())
                .title(announcement.getTitle())
                .introduction(announcement.getIntroduction())
                .form(announcement.getForm())
                .content(Objects.nonNull(verbosity) ? verbosity.getContent() : "")
                .gmtCreate(announcement.getGmtCreate())
                .gmtModify(announcement.getGmtModify())
                .build();
    }
}
