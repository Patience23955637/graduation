package com.hzhiping.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hzhiping.dao.GoodsDao;
import com.hzhiping.entity.GoodsInfo;
import com.hzhiping.util.SpringUtil;

/**
 * 
 * @title:dao层接口的实现类
 * @description:商品信息的接口的实现类
 * @author:hzhiping
 * @since:2018年4月11日 下午7:48:12
 */
@Repository
public class GoodsDaoImpl implements GoodsDao {
	@Autowired
	private JdbcTemplate jdbctemplate;

	@Override
	public void saveGoods(ArrayList<GoodsInfo> goodsList) {
		// TODO Auto-generated method stub
		String sql="insert into goods_info (id,goods_id,goods_name,img_url,goods_price) values (?,?,?,?,?)";
		for (GoodsInfo goodsInfo : goodsList) {
//			StringBuffer sql = new StringBuffer();
//			sql.append("insert into goods_info (id,goods_id,goods_name,img_url,goods_price) values (");
//			sql.append(goodsInfo.getId()+",'");
//			sql.append(goodsInfo.getGoodsId()+"','");
//			sql.append(goodsInfo.getGoodsName()+"','");
//			sql.append(goodsInfo.getImgUrl()+"','");
//			sql.append(goodsInfo.getGoodsPrice()+"')");
//			System.out.println(sql.toString());
//			System.out.println(jdbctemplate.toString());
//			jdbctemplate.update(sql.toString());
			jdbctemplate.update(sql, goodsInfo.getId(), goodsInfo.getGoodsId(), goodsInfo.getGoodsName(),
					goodsInfo.getImgUrl(), goodsInfo.getGoodsPrice());
		}
	}

	/**
	 * 测试一下数据保存
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		GoodsInfo goodinfo = new GoodsInfo(null, "1111", "111111", "11111", "111111");
		GoodsInfo goodinfo1 = new GoodsInfo(null, "2222", "222222", "22222", "222222");
		GoodsInfo goodinfo2 = new GoodsInfo(null, "3333", "333333", "33333", "333333");
		goodsList.add(goodinfo);
		goodsList.add(goodinfo1);
		goodsList.add(goodinfo2);
//		GoodsDaoImpl gdi = new GoodsDaoImpl();
		GoodsDaoImpl gdi = SpringUtil.getApplicationContext().getBean(GoodsDaoImpl.class);
		gdi.saveGoods(goodsList);
	}
}
