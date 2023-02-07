package com.liu.donate.service;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/6 14:21
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.donate.entity.CompanyInfo;
import com.liu.donate.vo.request.RequestCompanyInfoListVo;
import com.liu.donate.vo.response.ResponseCompanyNameVo;

import java.util.List;

public interface CompanyInfoService extends IService<CompanyInfo> {
    List<ResponseCompanyNameVo> getAllCompanyName();

    IPage<CompanyInfo> companyInfoList(RequestCompanyInfoListVo requestCompanyInfoListVo);
}
