package com.liu.donate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liu.donate.common.Result;
import com.liu.donate.entity.DonationProject;
import com.liu.donate.service.DonationProjectService;
import com.liu.donate.utils.DateUtils;
import com.liu.donate.utils.StringConsts;
import com.liu.donate.vo.request.RequestDeleteVo;
import com.liu.donate.vo.request.RequestProjectListVo;
import com.liu.donate.vo.request.RequestSaveOrUpdateProjectVo;
import com.liu.donate.vo.response.ResponseProjectListVo;
import com.liu.donate.vo.response.ResponseProjectNameVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/12 8:50
 */
@RestController
@RequestMapping("/donation-project/")
public class DonationProjectController {

    @Autowired
    private DonationProjectService donationProjectService;

    @GetMapping("queryByCompanyId/{companyId}")
    public Result queryByCompanyId(@PathVariable Integer companyId){
        List<ResponseProjectNameVo> nameVoList = donationProjectService.queryByCompanyId(companyId);
        return Result.getSuccess().setData(nameVoList);
    }

    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id){
        DonationProject donationProject = donationProjectService.getById(id);
        return Result.getSuccess().setData(donationProject);
    }

    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RequestSaveOrUpdateProjectVo projectVo){
        DonationProject donationProject = new DonationProject();
        BeanUtils.copyProperties(projectVo,donationProject);
        donationProject.setStartTime(DateUtils.getDateTimeOfTimestamp(projectVo.getStartTime()));
        donationProject.setEndTime(DateUtils.getDateTimeOfTimestamp(projectVo.getEndTime()));
        if (donationProjectService.saveOrUpdate(donationProject)){
            return Result.getSuccess().setMsg(StringConsts.SAVE_OR_UPDATE_SUCCESS);
        }
        else {
            return Result.getFailure().setMsg(StringConsts.SAVE_OR_UPDATE_FAIL);
        }
    }

    @PostMapping ("list")
    public Result list(@RequestBody RequestProjectListVo requestProjectListVo){
        IPage<ResponseProjectListVo> page = donationProjectService.projectList(requestProjectListVo);
        return Result.getSuccess().setData(page);
    }

    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody RequestDeleteVo requestDeleteVo){
        List<Integer> idList = Arrays.asList(requestDeleteVo.getIds());
        if (idList.size()>0 && donationProjectService.removeByIds(idList)){
            return Result.getSuccess().setMsg(StringConsts.DELETE_SUCCESS);
        }
        else {
            return Result.getFailure().setMsg(StringConsts.DELETE_FAIL);
        }
    }
}
