package com.liu.donate.vo.request;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/6 13:37
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestChangePasswordVo {
    private String password;
    private String newPassword;
    private Integer id;
}
