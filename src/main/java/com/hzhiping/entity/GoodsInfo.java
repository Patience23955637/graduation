package com.hzhiping.entity;

/**
 * @title:商品名称类
 * @description:将从京东上面爬取下来的商品信息罗列出来
 * @author:hzhiping
 * @time:2018年4月10日 下午9:30:29
 */
public class GoodsInfo {
	// 属性罗列
	private Integer id;// 作为主键
	private String goodsId;
	private String goodsName;// 商品的名称
	private String imgUrl;// 图片的url地址
	private String goodsPrice;// 商品的价格

	public GoodsInfo() {
		super();
	}

	public GoodsInfo(Integer id, String goodsId, String goodsName, String imgUrl, String goodsPrice) {
		super();
		this.id = id;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.imgUrl = imgUrl;
		this.goodsPrice = goodsPrice;
	}

	@Override
	public String toString() {
		return "GoodsInfo [id=" + id + ", goodsId=" + goodsId + ", goodsName=" + goodsName + ", imgUrl=" + imgUrl
				+ ", goodsPrice=" + goodsPrice + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
}
