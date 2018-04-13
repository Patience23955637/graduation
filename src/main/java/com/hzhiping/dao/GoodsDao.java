package com.hzhiping.dao;

import java.util.ArrayList;

import com.hzhiping.entity.GoodsInfo;

/**
 * 
 * @title:商品的接口的类
 * @description:商品将数据存储到数据库中的接口类
 * @author:hzhiping
 * @time:2018年4月10日 下午9:33:34
 */
public interface GoodsDao {
	// 保存商品信息
	void saveGoods(ArrayList<GoodsInfo> goodsList);
}
