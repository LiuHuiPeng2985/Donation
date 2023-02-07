package com.liu.donate.vo.request;

import lombok.Getter;
import lombok.Setter;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/12 12:26
 */
@Getter
@Setter
public class RequestSaveOrUpdateProjectVo {
    private Integer id;

    /**
     * 捐献单位外键
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
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;
}
