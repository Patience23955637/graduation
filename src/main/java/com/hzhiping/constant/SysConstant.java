package com.hzhiping.constant;

/**
 * @title:系统常量的类
 * @description:用于将系统常量罗列出来的接口类
 * @author:hzhiping
 * @time:2018年4月10日 下午9:35:48
 */
public interface SysConstant {
	// 设置需要访问的url地址
	String BASE_URL="https://search.jd.com/Search/";
	
	// 设置系统的字符集编码
	String DEFAULT_CHARSET = "UTF-8";

	// 设置头部相关的一些常量
	interface Header {
		String ACCEPT = "Accept";
		String ACCEPT_ENCODING = "Accept-Encoding";
		String ACCEPT_LANGUAGE = "Accept-Language";
		String CACHE_CONTROL = "Cache-Controle";
		String COOKIE = "Cookie";
		String HOST = "Host";
		String PROXY_CONNECTION = "Proxy-Connection";
		String REFERER = "Referer";
		String USER_AGENT = "User-Agent";
	}

	// 设置时间的格式
	String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
