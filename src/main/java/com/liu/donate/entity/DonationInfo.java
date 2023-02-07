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
public class DonationInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户外键id
     */
    private Integer userId;

    /**
     * 捐赠项目外键id
     */
    private Integer projectId;

    /**
     * 捐赠项目名
     */
    private String projectName;

    private String donateNum;

    /**
     * 项目详情
     */
    private String projectDesc;

    /**
     * 1代表现金，0代表物资
     */
    private Integer type;

    /**
     * 捐赠单位或人
     */
    private String donor;

    /**
     * 0是申请中，1是申请完成，2是申请失败
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
