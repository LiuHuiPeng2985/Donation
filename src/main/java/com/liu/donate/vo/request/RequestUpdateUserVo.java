package com.liu.donate.vo.request;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/6 13:54
 */

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestUpdateUserVo {
    private Integer id;
    private String telephone;
    private String sex;
    private Integer age;
    private String province;
    private String city;
    private String local;
    private String email;
}
