package com.liu.donate.vo.response;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/6 9:41
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseUserListVo implements Serializable {
    private Integer id;

    /**
     * 姓名
     */
    private String username;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
