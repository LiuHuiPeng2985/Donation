package com.liu.donate.vo.request;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/12 13:50
 */

import com.liu.donate.common.Search;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestProjectListVo extends Search {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目状态（0是筹款中，1是结束筹款）
     */
    private Integer projectStatus;

}
