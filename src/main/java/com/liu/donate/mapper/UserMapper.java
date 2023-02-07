package com.liu.donate.mapper;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/4 15:19
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.donate.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
