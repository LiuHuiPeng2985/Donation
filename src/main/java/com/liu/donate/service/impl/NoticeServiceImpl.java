package com.liu.donate.service.impl;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/15 19:46
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.donate.entity.CompanyInfo;
import com.liu.donate.entity.Notice;
import com.liu.donate.mapper.NoticeMapper;
import com.liu.donate.service.NoticeService;
import com.liu.donate.vo.request.RequestNoticeListVo;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Override
    public IPage<Notice> noticeList(RequestNoticeListVo requestNoticeListVo) {
        IPage<Notice> p = new Page<>(requestNoticeListVo.getDisplayStart(), requestNoticeListVo.getDisplayLength());
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotEmpty(requestNoticeListVo.getTitle())){
            queryWrapper.like("title",requestNoticeListVo.getTitle());
        }
        if (ObjectUtils.isNotEmpty(requestNoticeListVo.getNoticeText())){
            queryWrapper.like("noticeText",requestNoticeListVo.getNoticeText());
        }
        IPage<Notice> Ipage = page(p,queryWrapper);
        return Ipage;
    }
}
