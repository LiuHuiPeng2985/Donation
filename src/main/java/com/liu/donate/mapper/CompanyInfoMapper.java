package com.liu.donate.mapper;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/6 14:24
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.donate.entity.CompanyInfo;
import com.liu.donate.vo.response.ResponseCompanyNameVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompanyInfoMapper extends BaseMapper<CompanyInfo> {
    @Select("select id,company_name form company_info")
    List<ResponseCompanyNameVo> getAllCompanyName();
}
