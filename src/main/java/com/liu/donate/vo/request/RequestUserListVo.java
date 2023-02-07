package com.liu.donate.vo.request;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/6 9:46
 * 查询用户信息VO
 */

import com.liu.donate.common.Search;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestUserListVo extends Search {

    private String email;
    private String telephone;
    private String username;
}
