package site.wetsion.app.qycloudbookmark.api.bookmark.vo;

import lombok.Data;
import site.wetsion.app.qycloudbookmark.api.bookmark.entity.Bookmark;
import site.wetsion.app.qycloudbookmark.api.bookmark.entity.Category;
import site.wetsion.app.qycloudbookmark.api.user.vo.UserVo;

import java.time.LocalDateTime;

/**
 * 传给视图展示的书签信息
 *
 * Created by wetsion on 2020/3/17.
 */
@Data
public class BookmarkVo {

    private Long id;

    /** 所属用户 */
    private UserVo userVo;

    /** 书签标题 */
    private String title;

    /** 书签网页的源地址 */
    private String url;

    /** 书签网页的快照地址 */
    private String snapshot;

    /** 书签设置的所有权，0为共享，1为私有 */
    private Integer ownership;

    /** 书签所属的分类 */
    private Category category;

    /** 点赞数 */
    private Integer favourCount;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModify;
}
