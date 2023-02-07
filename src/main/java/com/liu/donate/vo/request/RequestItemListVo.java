package com.liu.donate.vo.request;

import lombok.Getter;
import lombok.Setter;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/13 8:54
 */
@Getter
@Setter
public class RequestItemListVo {
    /**
     * 币种
     */
    private String currency;

    /**
     * 捐赠金额
     */
    private Float amount;

    /**
     * 捐赠物名称
     */
    private String itemName;

    /**
     * 捐赠数量
     */
    private Integer itemAmount;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 生成标准
     */
    private String standard;
}
