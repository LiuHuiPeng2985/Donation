package com.liu.donate.mapper;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/15 19:45
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.donate.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}
