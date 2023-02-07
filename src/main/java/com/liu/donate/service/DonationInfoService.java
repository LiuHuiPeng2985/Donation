package com.liu.donate.service;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/13 8:58
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.donate.entity.DonationInfo;
import com.liu.donate.vo.request.RequestCheckDonationVo;
import com.liu.donate.vo.request.RequestDonateVo;
import com.liu.donate.vo.request.RequestDonationInfoListVo;
import com.liu.donate.vo.request.RequestExportExcelVo;
import com.liu.donate.vo.response.ResponseDonationInfoVo;

import java.util.List;

public interface DonationInfoService extends IService<DonationInfo> {

    /**
     * 用户捐赠
     * @param requestDonateVo
     */
    void donate(RequestDonateVo requestDonateVo);

    /**
     * 查询捐赠信息列表
     * @param donationInfoListVo
     * @return
     */
    IPage<ResponseDonationInfoVo> donationInfoList(RequestDonationInfoListVo donationInfoListVo);

    /**
     * 审核捐献信息
     * @param checkDonationVo
     */
    void checkDonation(RequestCheckDonationVo checkDonationVo);

    /**
     *  导出Excel
     * @param requestExportExcelVo
     * @return
     */
    List<Object[]> exportDonationInfoToExcel(RequestExportExcelVo requestExportExcelVo);
}
