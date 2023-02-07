package com.liu.donate.vo.request;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/6 19:58
 */

import com.liu.donate.common.Search;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCompanyInfoListVo extends Search {
    private String companyAddress;
    private String companyLinkman;
    private String companyName;
}
