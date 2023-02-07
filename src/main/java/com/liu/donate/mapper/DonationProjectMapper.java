package com.liu.donate.mapper;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/12 8:55
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.donate.entity.DonationProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DonationProjectMapper extends BaseMapper<DonationProject> {
    @Select("UPDATE donation_project SET project_status = 1 " +
            "WHERE project_status = 0 AND end_time < NOW()")
    void updateStatus();
}
