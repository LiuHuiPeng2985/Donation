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
public class ItemList implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 物品清单外键id
     */
    private Integer donationInfoId;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
