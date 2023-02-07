package com.liu.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/30 11:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String noticeText;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
