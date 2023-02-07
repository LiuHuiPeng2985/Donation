package com.liu.donate.vo.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/13 8:45
 */
@Getter
@Setter
public class RequestDonateVo {

    /**
     * 用户外键id
     */
    private Integer userId;

    /**
     * 捐赠项目外键id
     */
    private Integer projectId;

    /**
     * 捐赠单位或人
     */
    private String donor;

    /**
     * 1代表现金，0代表物资
     */
    private Integer type;

    private List<RequestItemListVo> itemListVos;
}
