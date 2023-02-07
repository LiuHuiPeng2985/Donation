package com.liu.donate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liu.donate.common.Result;
import com.liu.donate.entity.Notice;
import com.liu.donate.service.NoticeService;
import com.liu.donate.utils.DateUtils;
import com.liu.donate.utils.StringConsts;
import com.liu.donate.vo.request.RequestDeleteVo;
import com.liu.donate.vo.request.RequestNoticeListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/15 19:44
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/list")
    public Result list(@RequestBody RequestNoticeListVo requestNoticeListVo){
        IPage<Notice> voIPage = noticeService.noticeList(requestNoticeListVo);
        return Result.getSuccess().setData(voIPage);
    }

    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Integer id){
        Notice notice = noticeService.getById(id);
        return Result.getSuccess().setData(notice);
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Notice notice){
        Date date = new Date();
        notice.setCreateTime(DateUtils.getDateTimeOfTimestamp(date.getTime()));
        if (noticeService.saveOrUpdate(notice)){
            return Result.getSuccess().setMsg(StringConsts.SAVE_OR_UPDATE_SUCCESS);
        }
        else {
            return Result.getFailure().setMsg(StringConsts.SAVE_OR_UPDATE_FAIL);
        }
    }

    @DeleteMapping("/deleteByIds")
    public Result deleteByIds(@RequestBody RequestDeleteVo requestDeleteVo){
        List<Integer> idList = Arrays.asList(requestDeleteVo.getIds());
        if (idList.size()>0 && noticeService.removeByIds(idList)){
            return Result.getSuccess().setMsg(StringConsts.DELETE_SUCCESS);
        }
        else {
            return Result.getFailure().setMsg(StringConsts.DELETE_FAIL);
        }
    }
}
