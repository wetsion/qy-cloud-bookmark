package site.wetsion.app.qycloudbookmark.service.bookmark.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.wetsion.app.qycloudbookmark.api.bookmark.entity.Bookmark;
import site.wetsion.app.qycloudbookmark.common.bigdata.dao.BookmarkEoDao;
import site.wetsion.app.qycloudbookmark.common.bigdata.eo.BookmarkEo;
import site.wetsion.app.qycloudbookmark.common.constant.AppsConstant;
import site.wetsion.app.qycloudbookmark.common.security.util.SecurityUtil;
import site.wetsion.app.qycloudbookmark.common.util.R;
import site.wetsion.app.qycloudbookmark.service.bookmark.service.IBookmarkService;
import site.wetsion.app.qycloudbookmark.service.bookmark.util.EoBuilder;

/**
 * @author weixin
 * @version 1.0
 * @CLassName BookmarkController
 * @date 2020/3/30 4:19 PM
 */
@Slf4j
@RestController
@RequestMapping(AppsConstant.APP_COMMON_API_PREFIX + "/bookmark")
@AllArgsConstructor
public class BookmarkController {

    private IBookmarkService bookmarkService;

    private BookmarkEoDao bookmarkEoDao;

    /**
     * 添加收藏
     *
     * @param url 收藏的网页链接
     * @param snapshot 收藏的网页快照
     * @param title 收藏的网页标题
     * @author weixin
     * @date 4:21 PM 2020/3/30
     * @return site.wetsion.app.qycloudbookmark.common.util.R
     **/
    @PostMapping("/collect")
    public R collect(@RequestParam("title") String title,
                     @RequestParam("url") String url,
                     @RequestParam("snapshot") String snapshot) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(SecurityUtil.getUser().getId());
        bookmark.setTitle(title);
        bookmark.setUrl(url);
        bookmark.setSnapshot(snapshot);
        if (bookmarkService.saveOrUpdate(bookmark)) {
            try {
                bookmarkEoDao.save(EoBuilder.bookmarkEo(bookmark));
            } catch (Exception e){
                log.error("往elasticsearch写bookmark失败，ID：{}", bookmark.getId());
            }
            return R.ok();
        }
        return R.fail("收藏失败");
    }
}
