package site.wetsion.app.qycloudbookmark.common.bigdata.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import site.wetsion.app.qycloudbookmark.common.bigdata.eo.BookmarkEo;

/**
 * @author weixin
 * @version 1.0
 * @CLassName BookmarkEoDao
 * @date 2020/3/30 4:14 PM
 */
@Component
public interface BookmarkEoDao extends ElasticsearchRepository<BookmarkEo, Long> {
}
