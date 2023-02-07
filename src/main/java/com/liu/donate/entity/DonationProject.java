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
public class DonationProject implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 捐赠单位外键
     */
    private Integer companyId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目详情
     */
    private String projectDesc;

    /**
     * 项目负责人
     */
    private String projectLeader;

    /**
     * 项目状态（0是筹款中，1是结束筹款）
     */
    private Integer projectStatus;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
