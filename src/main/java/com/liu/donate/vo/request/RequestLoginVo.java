package com.liu.donate.vo.request;

import lombok.Getter;
import lombok.Setter;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/6 8:44
 * 登录请求VO
 */
@Setter
@Getter
public class RequestLoginVo {

    private String captcha;
    private String password;
    private String telephone;
}
