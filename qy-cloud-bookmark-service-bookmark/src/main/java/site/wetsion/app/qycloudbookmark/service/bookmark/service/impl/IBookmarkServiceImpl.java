package site.wetsion.app.qycloudbookmark.service.bookmark.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.wetsion.app.qycloudbookmark.api.bookmark.entity.Bookmark;
import site.wetsion.app.qycloudbookmark.service.bookmark.mapper.BookmarkMapper;
import site.wetsion.app.qycloudbookmark.service.bookmark.service.IBookmarkService;

/**
 * @author weixin
 * @version 1.0
 * @date 2020/3/30 4:29 PM
 */
@Service
public class IBookmarkServiceImpl extends ServiceImpl<BookmarkMapper, Bookmark> implements IBookmarkService {
}
