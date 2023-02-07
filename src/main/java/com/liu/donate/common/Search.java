package com.liu.donate.common;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/4 13:56
 * 搜索父类
 */

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
public class Search {
    /**
     * 每一页的条数
     */
    private Long displayLength;
    /**
     * 第几页
     */
    private Long displayStart;
}
