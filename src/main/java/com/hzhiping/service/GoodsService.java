package com.hzhiping.service;

import java.util.Map;
/**
 * 
 * @title:服务层的接口
 * @description:服务层的接口
 * @author: hzhiping
 * @since:2018年4月12日 上午11:11:48
 */
public interface GoodsService {
	/**
	 * 数据的保存
	 */
	void savaData(String url,Map<String, String>params);
	
}
