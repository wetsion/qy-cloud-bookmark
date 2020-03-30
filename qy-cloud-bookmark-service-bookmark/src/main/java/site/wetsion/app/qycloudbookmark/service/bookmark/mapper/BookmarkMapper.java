package site.wetsion.app.qycloudbookmark.service.bookmark.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import site.wetsion.app.qycloudbookmark.api.bookmark.entity.Bookmark;

/**
 * @author weixin
 * @version 1.0
 * @CLassName BookmarkMapper
 * @date 2020/3/30 4:26 PM
 */
@Mapper
public interface BookmarkMapper extends BaseMapper<Bookmark> {
}
