package com.hzhiping.handler;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Maps;
import com.hzhiping.constant.SysConstant;
import com.hzhiping.service.impl.GoodsServiceImpl;
import com.hzhiping.util.SpringUtil;
@Controller
public class SpiderHandler {
	// 添加日志
	private static final Logger logger = LoggerFactory.getLogger(SpiderHandler.class);

	@Autowired
	@Resource(name="goodsServiceImpl")
	private GoodsServiceImpl goodsServiceImpl;

	/**
	 * 爬取数据的方法
	 */
	public void spiderData() {
		logger.info("爬虫小程序开始执行...");
		Date startDate = new Date();
		// 使用线程池提交任务
//		ExecutorService executorService = Executors.newFixedThreadPool(5);
		// 引入countDownLatch进行线程同步，使主线程等待线程池的所有任务结束，便于计时
//		CountDownLatch countDownLatch = new CountDownLatch(100);
		for (int i = 1; i < 201; i += 2) {
			Map<String, String> params = Maps.newHashMap();
			params.put("keyword", "零食");
			params.put("enc", "utf-8");
			params.put("wq", "零食");
			params.put("page", i + "");
			System.out.println(params.entrySet().toString());
//			executorService.submit(() -> {
			goodsServiceImpl.savaData(SysConstant.BASE_URL, params);
//				countDownLatch.countDown();
//			});
		}
//		try {
//			countDownLatch.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		executorService.shutdown();
		Date endDate = new Date();

		FastDateFormat fdf = FastDateFormat.getInstance(SysConstant.DEFAULT_DATE_FORMAT);
		logger.info("爬虫结束....");
		logger.info("[开始时间:" + fdf.format(startDate) + ",结束时间:" + fdf.format(endDate) + ",耗时:"
				+ (endDate.getTime() - startDate.getTime()) + "ms]");

	}
	
	public static void main(String[] args) {
		SpiderHandler spiderHandler = SpringUtil.getApplicationContext().getBean(SpiderHandler.class);
		spiderHandler.spiderData();
	}

}
