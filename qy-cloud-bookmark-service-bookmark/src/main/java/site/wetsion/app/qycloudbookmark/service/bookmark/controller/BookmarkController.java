package site.wetsion.app.qycloudbookmark.service.bookmark.controller;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;
import site.wetsion.app.qycloudbookmark.api.bookmark.entity.Bookmark;
import site.wetsion.app.qycloudbookmark.common.bigdata.dao.BookmarkEoDao;
import site.wetsion.app.qycloudbookmark.common.bigdata.eo.BookmarkEo;
import site.wetsion.app.qycloudbookmark.common.constant.AppsConstant;
import site.wetsion.app.qycloudbookmark.common.security.util.SecurityUtil;
import site.wetsion.app.qycloudbookmark.common.util.R;
import site.wetsion.app.qycloudbookmark.service.bookmark.service.IBookmarkService;
import site.wetsion.app.qycloudbookmark.service.bookmark.util.EoBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private ElasticsearchTemplate elasticsearchTemplate;

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

    @GetMapping("/search")
    public R search(@RequestParam("keyword") String keyword,
                    @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, "title", "content");

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withHighlightFields(new HighlightBuilder.Field("title").preTags("<span>").postTags("</span>"))
                .build();
        searchQuery.setPageable(pageable);
        Page<BookmarkEo> page = bookmarkEoDao.search(searchQuery);

        Page<BookmarkEo> page1 =
                elasticsearchTemplate.queryForPage(searchQuery, BookmarkEo.class);
        return R.ok(page);
    }
}
