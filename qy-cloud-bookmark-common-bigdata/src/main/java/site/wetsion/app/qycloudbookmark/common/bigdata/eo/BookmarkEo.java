package site.wetsion.app.qycloudbookmark.common.bigdata.eo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 用于 elasticsearch 存储的书签实体
 *
 * @author weixin
 * @version 1.0
 * @date 2020/3/30 4:06 PM
 */
@Document(indexName = "qy-cloud-bookmark", type = "bookmark")
@Data
@Builder
public class BookmarkEo {

    /** 书签ID **/
    private Long id;

    /** 用户ID**/
    private Long userId;

    /** 书签标题 **/
    private String title;

    /** 书签网页内容 **/
    private String content;
}
