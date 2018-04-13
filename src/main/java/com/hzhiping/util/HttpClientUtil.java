package com.hzhiping.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzhiping.constant.SysConstant;

/**
 * @title:从客户端发送请求到服务端的类（包括了GET POST两种方式）
 * @description:apache的httpclient类可以将客户端的请求发送到服务端
 * @author:hzhiping
 * @since:2018年4月11日 下午3:44:59
 */
public class HttpClientUtil {
	// 设置本工具类的日志
	public final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	// 设置两种请求的方式
	public final static String GET_METHOD = "GET";
	public final static String POST_METHOD = "POST";

	/**
	 * @param url：请求的url
	 * @param header：请求头
	 * @param params：发送请求的相关的参数
	 * @return String：
	 */
	public static String sendGET(String url, Map<String, String> headers, Map<String, String> params) {
		// 创建一个httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 设置请求的url
		StringBuilder reqUrl = new StringBuilder();
		// 设置返回的结果
		String result = "";
		/*
		 * 设置参数
		 */
		if (params != null && params.size() > 0) {
			reqUrl.append("?");
			// 使用Entry可以一次性获取到Map中的key或者value这两个值
			// for循环将参数迭代写入
			for (Entry<String, String> param : params.entrySet()) {
				reqUrl.append(param.getKey() + "=" + param.getValue() + "&");
			}
			url = reqUrl.subSequence(0, reqUrl.length() - 1).toString();
		}
		logger.debug("[url:" + url + ",method:" + GET_METHOD + "]");
//		System.out.println(url+"黄志平");
		// httpget获取的连接需要是SysContant类中的东西加上上面设置参数之后的url，否则只有一个url
		HttpGet httpget = new HttpGet(SysConstant.BASE_URL+url);
		/*
		 * 设置头部
		 */
		logger.debug("Header\n");
		if (headers != null && headers.size() > 0) {
			// for循环直接迭代写入
			for (Entry<String, String> header : headers.entrySet()) {
				httpget.addHeader(header.getKey(), header.getValue());
				logger.debug(header.getKey() + ":" + header.getValue());
			}
		}
		// 判断是否请求成功
		CloseableHttpResponse response = null;
		try {
			// httpclient执行之后得到的相应
			response = httpclient.execute(httpget);
			/*
			 * 请求发送成功(状态代码为200)
			 */
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, SysConstant.DEFAULT_CHARSET);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("网络请求出错，请检查原因");
		}
		return result;
	}

	/**
	 * 使用post发送请求
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 */
	public static String sendPOST(String url, Map<String, String> headers, Map<String, String> params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		String result = "";
		/*
		 * 设置参数：此处使用到NameValuePair（简单名称值节点）
		 */
		if (params != null && params.size() > 0) {
			// 设置数组集合
			ArrayList<NameValuePair> nameList = new ArrayList<NameValuePair>();
			// 将params循环迭代写入
			for (Entry<String, String> param : params.entrySet()) {
				nameList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}
			logger.debug("[url:" + url + ",method:" + POST_METHOD + "]");
		}

		/*
		 * 设置头部
		 */
		logger.debug("Header\n");
		if (headers != null && headers.size() > 0) {
			// 将headers循环迭代写入
			for (Entry<String, String> header : headers.entrySet()) {
				httppost.addHeader(header.getKey(), header.getValue());
				logger.debug(header.getKey() + ":" + header.getValue());
			}
		}

		/*
		 * 模拟表单提交
		 */

		/*
		 * 判断请求成功与否
		 */
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, SysConstant.DEFAULT_CHARSET);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("网络请求出错，请检查原因");
		} finally {
			// 关闭响应
		}
		return result;
	}

	/**
	 * 使用post发送json请求
	 * 
	 * @param url
	 * @param json
	 * @param headers
	 * @return
	 */
	public static String sendPOSTJson(String url, String json, Map<String, String> headers) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		// 设置一个StringEntity
		StringEntity stringentity = new StringEntity(json, ContentType.APPLICATION_JSON);
		httppost.setEntity(stringentity);
		logger.debug("url:" + url + ",method:" + POST_METHOD + ",json:" + json);
		String result = "";
		/*
		 * 设置头部
		 */
		if (headers != null && headers.size() > 0) {
			for (Entry<String, String> header : headers.entrySet()) {
				httppost.addHeader(header.getKey(), header.getValue());
				logger.debug(header.getKey() + ":" + header.getValue());
			}
		}
		/*
		 * 设置请求的响应
		 */
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				// 请求成功
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, SysConstant.DEFAULT_CHARSET);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("网络请求出错，请检查原因...");
		}
		return result;
	}
}
