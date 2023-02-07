package com.liu.donate.vo.request;

import com.liu.donate.common.Search;
import lombok.Getter;
import lombok.Setter;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/13 9:43
 */
@Getter
@Setter
public class RequestDonationInfoListVo extends Search {
    private Integer type;

    private String donor;

    private Integer status;

    private String projectName;

    private Integer userId;


}
