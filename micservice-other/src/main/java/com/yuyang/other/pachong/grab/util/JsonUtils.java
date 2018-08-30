package com.yuyang.other.pachong.grab.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.gson.GsonBuilder;

public class JsonUtils {
	public static String toJson(Object obj) {
		return new GsonBuilder().create().toJson(obj);
	}
	/**
	 * 
	 * Description: 转换json
	 * @author liqiang
	 * @date 2016年10月14日 下午2:58:34
	 * @version V1.0
	 *
	 * @param obj 数据
	 * @param format 时间格式 ，例如："yyyy-MM-dd HH:mm:ss" 
	 * @return
	 */
	public static String toJson(Object obj, String format) {
		if("".equals(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		return new GsonBuilder().setDateFormat(format).create().toJson(obj);
	}

	@SuppressWarnings("unchecked")
	public static <T> T toObject(String json, Class<?> clazz) {
		return (T) JSON.parseObject(json, clazz);  
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		String res ="{\"id\":\"20\",\"name\":\"test\"}";
		Map data = JsonUtils.toObject(res, Map.class);
		System.out.println(data);
		String res1 ="{\"id\":20,\"name\":\"test\"}";
		Map data1 = JsonUtils.toObject(res1, LinkedHashMap.class);
		System.out.println(data1);
	}
}
