package com.liu.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.donate.entity.DonationProject;
import com.liu.donate.mapper.DonationProjectMapper;
import com.liu.donate.service.CompanyInfoService;
import com.liu.donate.service.DonationProjectService;
import com.liu.donate.vo.request.RequestProjectListVo;
import com.liu.donate.vo.response.ResponseCompanyNameVo;
import com.liu.donate.vo.response.ResponseProjectListVo;
import com.liu.donate.vo.response.ResponseProjectNameVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/12 12:08
 */
@Service
public class DonationProjectServiceImpl extends ServiceImpl<DonationProjectMapper, DonationProject> implements DonationProjectService {

    @Autowired
    private DonationProjectMapper donationProjectMapper;

    @Autowired
    private CompanyInfoService companyInfoService;

    @Override
    public List<ResponseProjectNameVo> queryByCompanyId(Integer companyId) {
        QueryWrapper<DonationProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id",companyId);
        queryWrapper.eq("project_status",0);
        List<DonationProject> projectList = list(queryWrapper);
        List<ResponseProjectNameVo> nameVoList = new ArrayList<>();
        ResponseProjectNameVo vo;
        for (DonationProject donationProject:projectList){
            vo = new ResponseProjectNameVo();
            BeanUtils.copyProperties(donationProject,vo);
            nameVoList.add(vo);
        }
        return nameVoList;
    }

    @Override
    public void updateStatus() {
        donationProjectMapper.updateStatus();
    }

    @Override
    public IPage<ResponseProjectListVo> projectList(RequestProjectListVo requestProjectListVo) {
        //分页条件
        IPage<DonationProject> p = new Page<>(requestProjectListVo.getDisplayStart(), requestProjectListVo.getDisplayLength());
        //查询条件
        QueryWrapper<DonationProject> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(requestProjectListVo.getProjectName())){
            queryWrapper.like("project_name",requestProjectListVo.getProjectName());
        }
        if (ObjectUtils.isNotEmpty(requestProjectListVo.getProjectStatus())){
            queryWrapper.eq("project_status",requestProjectListVo.getProjectStatus());
        }
        //查询到的project信息
        IPage<DonationProject> projectIPage = page(p,queryWrapper);
        //先查询出所有的companyInfo，然后转成map，再根据companyID去map中查询
        List<ResponseCompanyNameVo> allCompanyName = companyInfoService.getAllCompanyName();
        //key=id,value=name
        Map<Integer,String> nameMap = new HashMap<>();
        for (ResponseCompanyNameVo vo:allCompanyName){
            nameMap.put(vo.getId(), vo.getCompanyName());
        }
        //泛型转换
        List<DonationProject> projectList = projectIPage.getRecords();
        List<ResponseProjectListVo> voList = new ArrayList<>();
        ResponseProjectListVo vo;
        for (DonationProject donationProject:projectList){
            vo = new ResponseProjectListVo();
            BeanUtils.copyProperties(donationProject,vo);
            //从map中去出name
            vo.setCompanyName(nameMap.get(donationProject.getCompanyId()));
            voList.add(vo);
        }
        IPage<ResponseProjectListVo> page = new Page<>();
        //将分页参数拷贝到page中
        BeanUtils.copyProperties(projectIPage,page);
        //拷贝记录
        page.setRecords(voList);
        return page;
    }
}
