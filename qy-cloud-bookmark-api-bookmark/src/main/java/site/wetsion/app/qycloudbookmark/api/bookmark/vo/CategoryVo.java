package site.wetsion.app.qycloudbookmark.api.bookmark.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 用于视图展示的分类信息
 *
 * Created by wetsion on 2020/3/17.
 */
@Data
@Builder
public class CategoryVo {

    private Long id;

    private String name;
}
