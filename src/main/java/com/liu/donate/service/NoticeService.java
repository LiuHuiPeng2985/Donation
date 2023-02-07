package com.liu.donate.service;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/15 19:46
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.donate.entity.Notice;
import com.liu.donate.vo.request.RequestNoticeListVo;

public interface NoticeService extends IService<Notice> {
    IPage<Notice> noticeList(RequestNoticeListVo requestNoticeListVo);
}
