package com.liu.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.donate.entity.DonationInfo;
import com.liu.donate.entity.DonationProject;
import com.liu.donate.entity.ItemList;
import com.liu.donate.mapper.DonationInfoMapper;
import com.liu.donate.service.DonationInfoService;
import com.liu.donate.service.DonationProjectService;
import com.liu.donate.service.ItemListService;
import com.liu.donate.vo.request.*;
import com.liu.donate.vo.response.ResponseDonationInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/13 8:59
 */
@Service
public class DonateInfoServiceImpl extends ServiceImpl<DonationInfoMapper, DonationInfo> implements DonationInfoService {

    @Autowired
    private DonationProjectService donationProjectService;

    @Autowired
    private ItemListService itemListService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void donate(RequestDonateVo requestDonateVo) {
        DonationInfo donationInfo = new DonationInfo();
        BeanUtils.copyProperties(requestDonateVo,donationInfo);
        DonationProject project = donationProjectService.getById(requestDonateVo.getProjectId());
        donationInfo.setProjectName(project.getProjectName());
        donationInfo.setProjectDesc(project.getProjectDesc());
        donationInfo.setStatus(0);
        donationInfo.setDonateNum(IdWorker.getIdStr());
        save(donationInfo);
        List<ItemList> itemLists = new ArrayList<>();
        ItemList itemList;
        for (RequestItemListVo vo:requestDonateVo.getItemListVos()){
            itemList = new ItemList();
            BeanUtils.copyProperties(vo,itemList);
            itemList.setDonationInfoId(donationInfo.getId());
            itemLists.add(itemList);
        }
        itemListService.saveBatch(itemLists);
    }

    @Override
    public IPage<ResponseDonationInfoVo> donationInfoList(RequestDonationInfoListVo donationInfoListVo) {
        IPage<DonationInfo> p = new Page<>(donationInfoListVo.getDisplayStart(),donationInfoListVo.getDisplayLength());
        QueryWrapper<DonationInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(donationInfoListVo.getProjectName())){
            queryWrapper.like("project_name",donationInfoListVo.getProjectName());
        }
        if (StringUtils.isNotEmpty(donationInfoListVo.getDonor())){
            queryWrapper.like("donor",donationInfoListVo.getDonor());
        }
        if (ObjectUtils.isNotEmpty(donationInfoListVo.getStatus())){
            queryWrapper.eq("status",donationInfoListVo.getStatus());
        }
        if (ObjectUtils.isNotEmpty(donationInfoListVo.getUserId())){
            queryWrapper.like("user_id",donationInfoListVo.getUserId());
        }
        if (ObjectUtils.isNotEmpty(donationInfoListVo.getType())){
            queryWrapper.like("type",donationInfoListVo.getType());
        }
        IPage<DonationInfo> donationInfoIPage = page(p,queryWrapper);

        return donationInfoIPage.convert(donationInfo -> {
            ResponseDonationInfoVo donationInfoVo = new ResponseDonationInfoVo();
            BeanUtils.copyProperties(donationInfo,donationInfoVo);
            DonationProject project = donationProjectService.getById(donationInfo.getProjectId());
            donationInfoVo.setProjectStatus(project.getProjectStatus());
            QueryWrapper<ItemList> itemListQueryWrapper = new QueryWrapper<>();
            itemListQueryWrapper.eq("donation_info_id",donationInfo.getId());
            List<ItemList> itemLists = itemListService.list(itemListQueryWrapper);
            donationInfoVo.setResponseItemListVoList(itemLists);
            return donationInfoVo;
        });
    }

    @Override
    public void checkDonation(RequestCheckDonationVo checkDonationVo) {
        DonationInfo donationInfo = getById(checkDonationVo.getId());
        if (checkDonationVo.isCheckStatus()){
            donationInfo.setStatus(1);
        }
        else {
            donationInfo.setStatus(2);
        }
        updateById(donationInfo);
    }

    @Override
    public List<Object[]> exportDonationInfoToExcel(RequestExportExcelVo requestExportExcelVo) {
        QueryWrapper<DonationInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(requestExportExcelVo.getProjectName())){
            queryWrapper.like("project_name",requestExportExcelVo.getProjectName());
        }
        if (StringUtils.isNotEmpty(requestExportExcelVo.getDonor())){
            queryWrapper.like("donor",requestExportExcelVo.getDonor());
        }
        if (ObjectUtils.isNotEmpty(requestExportExcelVo.getType())){
            queryWrapper.eq("type",requestExportExcelVo.getType());
        }
        if (ObjectUtils.isNotEmpty(requestExportExcelVo.getStatus())){
            queryWrapper.eq("status",requestExportExcelVo.getStatus());
        }
        List<DonationInfo> donationInfoList = list(queryWrapper);
        List<Object[]> result = new ArrayList<>();
        Object[] objects;
        for (DonationInfo donationInfo:donationInfoList){
            objects = new Object[6];
            objects[0] = donationInfo.getId();
            objects[1] = donationInfo.getProjectName();
            objects[2] = donationInfo.getDonateNum();
            objects[3] = donationInfo.getStatus() == 0?"申请中":(donationInfo.getStatus() == 1?"申请成功":"申请失败");
            objects[4] = donationInfo.getType() == 0?"物资":"现金";
            objects[5] = donationInfo.getDonor();
            result.add(objects);
        }
        return result;
    }
}
