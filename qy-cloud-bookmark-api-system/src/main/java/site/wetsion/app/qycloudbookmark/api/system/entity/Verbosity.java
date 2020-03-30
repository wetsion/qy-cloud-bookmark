package site.wetsion.app.qycloudbookmark.api.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import site.wetsion.app.qycloudbookmark.common.dto.BaseDto;

/**
 * 公告中的大段文本
 *
 * Created by wetsion on 2020/3/18.
 */
@Data
@TableName("verbosity")
public class Verbosity extends BaseDto {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String content;
}
