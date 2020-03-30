package site.wetsion.app.qycloudbookmark.api.bookmark.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import site.wetsion.app.qycloudbookmark.common.dto.BaseDto;

import java.time.LocalDateTime;

/**
 * 书签基本信息实体类
 *
 * Created by wetsion on 2020/3/17.
 */
@Data
@TableName("bookmark")
public class Bookmark extends BaseDto {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 所属用户ID */
    private Long userId;

    /** 书签标题 */
    private String title;

    /** 书签网页的源地址 */
    private String url;

    /** 书签网页的快照地址 */
    private String snapshot;

    /** 书签设置的所有权，0为共享，1为私有 */
    private Integer ownership;

    /** 书签所属的分类 */
    private Long categoryId;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModify;
}
