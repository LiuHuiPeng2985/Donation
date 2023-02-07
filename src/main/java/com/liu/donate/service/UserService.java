package com.liu.donate.service;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/4 15:14
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.donate.entity.User;
import com.liu.donate.vo.request.RequestRegisterVo;
import com.liu.donate.vo.request.RequestUserListVo;
import com.liu.donate.vo.response.ResponseUserListVo;

public interface UserService extends IService<User> {

    User queryByTel(String telephone);

    void register(RequestRegisterVo requestRegisterVo);

    IPage<ResponseUserListVo> userList(RequestUserListVo requestUserListVo);

    /**
     * 根据Id查询用户信息
     * @param userId
     * @return
     */
    ResponseUserListVo queryById(Integer userId);
}
