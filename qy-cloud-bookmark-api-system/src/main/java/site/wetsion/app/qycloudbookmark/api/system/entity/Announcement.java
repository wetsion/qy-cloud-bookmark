package site.wetsion.app.qycloudbookmark.api.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import site.wetsion.app.qycloudbookmark.common.dto.BaseDto;

import java.time.LocalDateTime;

/**
 * 通知公告
 *
 * Created by wetsion on 2020/3/18.
 */
@Data
@TableName("announcement")
public class Announcement extends BaseDto {

    @TableId
    private Long id;

    /** 标题 **/
    private String title;

    /** 简述 */
    private String introduction;

    /** 公告形式，默认0：简单公告，1：大段文本公告 */
    private int form;

    /** 如果有大段文本，大段文本的id */
    private Long verbosityId;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModify;
}
