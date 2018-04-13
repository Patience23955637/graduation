package com.hzhiping.service.impl;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hzhiping.dao.impl.GoodsDaoImpl;
import com.hzhiping.entity.GoodsInfo;
import com.hzhiping.service.GoodsService;
import com.hzhiping.util.HttpClientUtil;

/**
 * @title:服务层的实现类
 * @description:服务层的实现类，用于解析html以及调用dao层的方法将数据存入
 * @author: hzhiping
 * @since:2018年4月12日 上午11:10:48
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	// 设置日志的读取
	private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

	// 设置http的协议
	private static final String HTTPS_PROTOCOL = "https:";
	@Resource(name="goodsDaoImpl")
	private GoodsDaoImpl goodsDaoImpl;

	/**
	 * 将解析的html数据保存到我们的数据库中去
	 * 
	 * @param url：要爬取的网站
	 * @param params：网站后面的参数
	 */
	@Override
	public void savaData(String url, Map<String, String> params) {
		// TODO Auto-generated method stub
		// 经过分析页面我们可以知道网页数据使用get方式提交
		String html = HttpClientUtil.sendGET(url, null, params);
		if (!StringUtil.isBlank(html)) {
			ArrayList<GoodsInfo> goodsList = parseHtml(html);
			goodsDaoImpl.saveGoods(goodsList);
		}
	}

	/**
	 * 解析html的方法
	 * 
	 * @param html:分析的页面
	 * @return List:返回数据集合
	 */
	private static ArrayList<GoodsInfo> parseHtml(String html) {
		// 设置商品的集合
		ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		//
		Document document = Jsoup.parse(html);
		Elements elements = document.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
		int index = 0;
		// 页面分析并且将数据保存到集合中
		for (Element element : elements) {
			String goodsId = element.attr("data-sku");
			String goodsName = element.select("div[class=p-name p-name-type-2]").select("em").text();
			String goodsPrice = element.select("div[class=p-price]").select("strong").select("i").text();
			String imgUrl = HTTPS_PROTOCOL + element.select("div[class=p-img]").select("a").select("img").attr("src");
			GoodsInfo goodsInfo = new GoodsInfo(null, goodsId, goodsName, imgUrl, goodsPrice);
			goodsList.add(goodsInfo);
			String jsonStr = JSON.toJSONString(goodsInfo);
			logger.info("成功爬取【" + goodsName + "】的基本信息 ");
			logger.info(jsonStr);
			if (index++ == 9) {
				break;
			}
		}
		return goodsList;
	}

}
