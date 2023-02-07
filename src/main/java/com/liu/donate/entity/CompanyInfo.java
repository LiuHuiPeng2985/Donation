package com.liu.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/30 11:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 受捐单位
     */
    private String companyName;

    /**
     * 受捐单位地址
     */
    private String companyAddress;

    /**
     * 联系人
     */
    private String companyLinkman;

    /**
     * 联系电话
     */
    private String companyPhone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
