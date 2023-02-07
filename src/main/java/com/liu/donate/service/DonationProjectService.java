package com.liu.donate.service;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/12 12:08
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.donate.entity.DonationProject;
import com.liu.donate.vo.request.RequestProjectListVo;
import com.liu.donate.vo.response.ResponseProjectListVo;
import com.liu.donate.vo.response.ResponseProjectNameVo;

import java.util.List;

public interface DonationProjectService extends IService<DonationProject> {

    /**
     * 根据companyId查询项目信息
     * @param companyId
     * @return
     */
    List<ResponseProjectNameVo> queryByCompanyId(Integer companyId);

    /**
     * 定时任务修改状态
     */
    void updateStatus();

    /**
     * 分页查询受捐项目列表
     * @param requestProjectListVo
     * @return
     */
    IPage<ResponseProjectListVo> projectList(RequestProjectListVo requestProjectListVo);
}
