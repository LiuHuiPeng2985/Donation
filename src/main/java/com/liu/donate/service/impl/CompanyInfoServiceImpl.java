package com.liu.donate.service.impl;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/6 14:22
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.donate.entity.CompanyInfo;
import com.liu.donate.mapper.CompanyInfoMapper;
import com.liu.donate.service.CompanyInfoService;
import com.liu.donate.vo.request.RequestCompanyInfoListVo;
import com.liu.donate.vo.response.ResponseCompanyNameVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements CompanyInfoService {
    @Override
    public List<ResponseCompanyNameVo> getAllCompanyName() {
        //查询数据库中所有的数据
        List<CompanyInfo> companyInfoList = list();
        List<ResponseCompanyNameVo> voList =new ArrayList<>();

        for (CompanyInfo companyInfo:companyInfoList){
            ResponseCompanyNameVo vo = new ResponseCompanyNameVo();
            BeanUtils.copyProperties(companyInfo,vo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public IPage<CompanyInfo> companyInfoList(RequestCompanyInfoListVo requestCompanyInfoListVo) {
        IPage<CompanyInfo> p = new Page<>(requestCompanyInfoListVo.getDisplayStart(), requestCompanyInfoListVo.getDisplayLength());
        QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotEmpty(requestCompanyInfoListVo.getCompanyName())){
            queryWrapper.like("company_name",requestCompanyInfoListVo.getCompanyName());
        }
        if (ObjectUtils.isNotEmpty(requestCompanyInfoListVo.getCompanyLinkman())){
            queryWrapper.like("company_linkman",requestCompanyInfoListVo.getCompanyLinkman());
        }
        if (ObjectUtils.isNotEmpty(requestCompanyInfoListVo.getCompanyAddress())){
            queryWrapper.like("company_address",requestCompanyInfoListVo.getCompanyAddress());
        }
        IPage<CompanyInfo> Ipage = page(p,queryWrapper);
        return Ipage;
    }
}
