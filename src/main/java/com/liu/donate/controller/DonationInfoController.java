package com.liu.donate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liu.donate.common.Result;
import com.liu.donate.entity.ItemList;
import com.liu.donate.service.DonationInfoService;
import com.liu.donate.utils.DateUtils;
import com.liu.donate.utils.ExportExcelUtils;
import com.liu.donate.utils.StringConsts;
import com.liu.donate.vo.request.RequestCheckDonationVo;
import com.liu.donate.vo.request.RequestDonateVo;
import com.liu.donate.vo.request.RequestDonationInfoListVo;
import com.liu.donate.vo.request.RequestExportExcelVo;
import com.liu.donate.vo.response.ResponseDonationInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/13 8:57
 */
@RestController
@RequestMapping("/donation-info/")
public class DonationInfoController {

    @Autowired
    private DonationInfoService donationInfoService;

    @PostMapping("donate")
    public Result donate(@RequestBody RequestDonateVo requestDonateVo){
        donationInfoService.donate(requestDonateVo);
        return Result.getSuccess().setMsg(StringConsts.DONATE_SUCCESS);
    }

    @PostMapping("donateInfoList")
    public Result donationInfoList(@RequestBody RequestDonationInfoListVo donationInfoListVo){
        IPage<ResponseDonationInfoVo> page = donationInfoService.donationInfoList(donationInfoListVo);
        return Result.getSuccess().setData(page);
    }

    @PostMapping("checkDonation")
    public Result checkDonation(@RequestBody RequestCheckDonationVo checkDonationVo){
        donationInfoService.checkDonation(checkDonationVo);
        return Result.getSuccess().setMsg(StringConsts.CHECK_SUCCESS);
    }

    @PostMapping("exportDonationInfoToExcel")
    public ResponseEntity<byte[]> exportDonationInfoToExcel(@RequestBody RequestExportExcelVo requestExportExcelVo){
        String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";
        String title = "捐献信息记录";
        String[] rowsName = new String[]{"序号","项目名称","捐献编号","申请状态","捐献类型","捐献者"};
        List<Object[]> donationInfoList = donationInfoService.exportDonationInfoToExcel(requestExportExcelVo);
        ExportExcelUtils ex = new ExportExcelUtils(title,rowsName,fileName,donationInfoList);
        return ex.export();
    }
}
