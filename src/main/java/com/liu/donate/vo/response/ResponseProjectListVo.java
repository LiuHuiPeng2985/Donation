package com.liu.donate.vo.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/12 13:53
 */
@Getter
@Setter
public class ResponseProjectListVo {
    private Integer id;

    /**
     * 项目名称
     */
    private String projectName;

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
     * 受捐单位名称
     */
    private String companyName;
}
