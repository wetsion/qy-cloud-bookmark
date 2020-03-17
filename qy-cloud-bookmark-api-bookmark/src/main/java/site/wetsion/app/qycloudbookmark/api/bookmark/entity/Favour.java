package site.wetsion.app.qycloudbookmark.api.bookmark.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import site.wetsion.app.qycloudbookmark.common.dto.BaseDto;

/**
 * 点赞实体类
 *
 * Created by wetsion on 2020/3/17.
 */
@Data
@TableName("favour")
public class Favour extends BaseDto {

    @TableId
    private Long id;

    private Long userId;

    private Long bookmarkId;
}
