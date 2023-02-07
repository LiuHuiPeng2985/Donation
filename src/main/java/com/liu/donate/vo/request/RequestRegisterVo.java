package com.liu.donate.vo.request;

import lombok.Getter;
import lombok.Setter;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/4 14:55
 * 注册请求VO
 */
@Setter
@Getter
public class RequestRegisterVo {

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 具体地址
     */
    private String local;

    /**
     * 邮箱
     */
    private String email;

}
