package com.liu.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.donate.entity.User;
import com.liu.donate.mapper.UserMapper;
import com.liu.donate.service.UserService;
import com.liu.donate.utils.DateUtils;
import com.liu.donate.utils.Md5Utils;
import com.liu.donate.vo.request.RequestRegisterVo;
import com.liu.donate.vo.request.RequestUserListVo;
import com.liu.donate.vo.response.ResponseUserListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/4 15:14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User queryByTel(String telephone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper <User>();
        queryWrapper.eq("telephone",telephone);
        User user = getOne(queryWrapper);
        return user;
    }

    @Override
    public void register(RequestRegisterVo requestRegisterVo) {
        User user = new User();
        //拷贝类型一致，名称一致的属性
        BeanUtils.copyProperties(requestRegisterVo,user);
        user.setType(false);
        user.setPassword(Md5Utils.hash(user.getPassword()));
        Date date = new Date();
        user.setCreateTime(DateUtils.getDateTimeOfTimestamp(date.getTime()));
        save(user);
    }

    @Override
    public IPage<ResponseUserListVo> userList(RequestUserListVo requestUserListVo) {

        //构造分页条件
        IPage<User> p = new Page<>(requestUserListVo.getDisplayStart(),
                requestUserListVo.getDisplayLength());
        //构造查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotEmpty(requestUserListVo.getUsername())){
            queryWrapper.like("username",requestUserListVo.getUsername());
        }
        if (ObjectUtils.isNotEmpty(requestUserListVo.getEmail())){
            queryWrapper.like("email",requestUserListVo.getEmail());
        }
        if (ObjectUtils.isNotEmpty(requestUserListVo.getTelephone())){
            queryWrapper.like("telephone",requestUserListVo.getTelephone());
        }
        //执行查询操作
        IPage<User> userIPage = page(p, queryWrapper);
        //泛型转换：user==>ResponseUserListVo
//        List<User> records = userIPage.getRecords();
//        IPage<ResponseUserListVo> voIPage = new Page<>();
//        List<ResponseUserListVo> voList = new ArrayList<>();
//        for (User user:records){
//            ResponseUserListVo vo = new ResponseUserListVo();
//            BeanUtils.copyProperties(user,vo);
//            voList.add(vo);
//        }
//        BeanUtils.copyProperties(userIPage,voIPage);
//        voIPage.setRecords(voList);
//        return voIPage;
        return userIPage.convert(user -> {
            ResponseUserListVo vo = new ResponseUserListVo();
            BeanUtils.copyProperties(user,vo);
            return vo;
        });
    }

    @Override
    public ResponseUserListVo queryById(Integer userId) {
        ResponseUserListVo vo = (ResponseUserListVo) redisTemplate.opsForValue().get("USER_" + userId);
        if (ObjectUtils.isNull(vo)) {
            vo = new ResponseUserListVo();
            //从数据库中查询数据
            User user = getById(userId);
            BeanUtils.copyProperties(user, vo);
            //保存数据到Redis中
            redisTemplate.opsForValue().set("USER_" + userId, vo);
            System.out.println("从数据库中查询");
        }
        return vo;
    }
}
