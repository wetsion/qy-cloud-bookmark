package site.wetsion.app.qycloudbookmark.api.bookmark.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import site.wetsion.app.qycloudbookmark.common.dto.BaseDto;

/**
 * 书签分类实体类
 *
 * Created by wetsion on 2020/3/17.
 */
@Data
@TableName("category")
public class Category extends BaseDto {

    @TableId
    private Long id;

    private String name;

    private Long userId;
}
