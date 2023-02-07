package com.liu.donate.vo.response;

import com.liu.donate.entity.ItemList;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/13 9:49
 */
@Setter
@Getter
public class ResponseDonationInfoVo {

    private Integer id;

    /**
     * 捐赠项目名
     */
    private String projectName;

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
     * 项目状态（0是筹款中，1是结束筹款）
     */
    private Integer projectStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private List<ItemList> responseItemListVoList;

}
