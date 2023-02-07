package com.liu.donate.vo.request;

import com.liu.donate.common.Search;
import lombok.Getter;
import lombok.Setter;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/15 19:53
 */
@Getter
@Setter
public class RequestNoticeListVo extends Search {
    /**
     * 标题
     */
    private String title;
    /**
     * 公告内容
     */
    private String noticeText;
}
