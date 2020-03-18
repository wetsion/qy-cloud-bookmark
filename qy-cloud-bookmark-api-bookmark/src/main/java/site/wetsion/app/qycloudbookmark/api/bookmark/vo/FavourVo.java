package site.wetsion.app.qycloudbookmark.api.bookmark.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 用于视图展示的点赞信息
 *
 * Created by wetsion on 2020/3/17.
 */
@Data
@Builder
public class FavourVo {

    private BookmarkVo bookmarkVo;

}
