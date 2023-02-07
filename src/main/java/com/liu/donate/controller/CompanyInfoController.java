package com.liu.donate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liu.donate.common.Result;
import com.liu.donate.entity.CompanyInfo;
import com.liu.donate.service.CompanyInfoService;
import com.liu.donate.utils.DateUtils;
import com.liu.donate.utils.StringConsts;
import com.liu.donate.vo.request.RequestCompanyInfoListVo;
import com.liu.donate.vo.request.RequestDeleteVo;
import com.liu.donate.vo.response.ResponseCompanyNameVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/6 14:20
 */
@RestController
@RequestMapping("/company-info")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @PostMapping("/list")
    public Result list(@RequestBody RequestCompanyInfoListVo requestCompanyInfoListVo){
        IPage<CompanyInfo> Ipage = companyInfoService.companyInfoList(requestCompanyInfoListVo);
        return Result.getSuccess().setData(Ipage);
    }

    @GetMapping("/getAllCompanyName")
    public Result getAllCompanyName(){
        List<ResponseCompanyNameVo> result = companyInfoService.getAllCompanyName();
        return Result.getSuccess().setData(result);
    }

    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Integer id){
        CompanyInfo companyInfo = companyInfoService.getById(id);
        return Result.getSuccess().setData(companyInfo);
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody CompanyInfo companyInfo){
        Date date = new Date();
        companyInfo.setCreateTime(DateUtils.getDateTimeOfTimestamp(date.getTime()));
        if (companyInfoService.saveOrUpdate(companyInfo)){
            return Result.getSuccess().setMsg(StringConsts.SAVE_OR_UPDATE_SUCCESS);
        }
        else {
            return Result.getFailure().setMsg(StringConsts.SAVE_OR_UPDATE_FAIL);
        }
    }

    @DeleteMapping("/deleteByIds")
    public Result deleteByIds(@RequestBody RequestDeleteVo requestDeleteVo){
        List<Integer> idList = Arrays.asList(requestDeleteVo.getIds());
        if (idList.size()>0 && companyInfoService.removeByIds(idList)){
            return Result.getSuccess().setMsg(StringConsts.DELETE_SUCCESS);
        }
        else {
            return Result.getFailure().setMsg(StringConsts.DELETE_FAIL);
        }
    }
}
