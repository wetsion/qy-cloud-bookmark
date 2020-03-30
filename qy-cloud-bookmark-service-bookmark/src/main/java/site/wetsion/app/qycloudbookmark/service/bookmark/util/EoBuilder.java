package site.wetsion.app.qycloudbookmark.service.bookmark.util;

import lombok.experimental.UtilityClass;
import site.wetsion.app.qycloudbookmark.api.bookmark.entity.Bookmark;
import site.wetsion.app.qycloudbookmark.common.bigdata.eo.BookmarkEo;

/**
 * elasticsearch实体建造工具类
 *
 * @author weixin
 * @version 1.0
 * @date 2020/3/30 4:33 PM
 */
@UtilityClass
public class EoBuilder {

    /**
     * bookmark 数据实体转elasticsearch 实体
     *
     * @param bookmark
     * @author weixin
     * @date 4:37 PM 2020/3/30
     * @return site.wetsion.app.qycloudbookmark.common.bigdata.eo.BookmarkEo
     **/
    public BookmarkEo bookmarkEo(Bookmark bookmark) {
        return BookmarkEo.builder()
                .id(bookmark.getId())
                .title(bookmark.getTitle())
                .content(bookmark.getTitle())
                .userId(bookmark.getUserId())
                .build();
    }
}
