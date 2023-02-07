package com.liu.donate.vo.request;

import lombok.Getter;
import lombok.Setter;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/13 14:29
 */
@Getter
@Setter
public class RequestExportExcelVo {

    private Integer type;

    private Integer status;

    private String donor;

    private String projectName;
}
