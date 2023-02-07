package com.liu.donate.vo.response;

import lombok.Getter;
import lombok.Setter;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/12 9:01
 */
@Getter
@Setter
public class ResponseProjectNameVo {
    private Integer id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目详情
     */
    private String projectDesc;
}
