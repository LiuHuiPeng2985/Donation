package com.liu.donate.service.impl;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/13 9:17
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.donate.entity.ItemList;
import com.liu.donate.mapper.ItemListMapper;
import com.liu.donate.service.ItemListService;
import org.springframework.stereotype.Service;

@Service
public class ItemListServiceImpl extends ServiceImpl<ItemListMapper, ItemList> implements ItemListService {
}
