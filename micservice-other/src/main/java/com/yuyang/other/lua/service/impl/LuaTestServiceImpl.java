package com.yuyang.other.lua.service.impl;

import com.yuyang.common.cache.RedisCache;
import com.yuyang.other.lua.service.LuaTestService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class LuaTestServiceImpl implements LuaTestService {
	private static final String SUB_PRODUCT_PATH="/lua/writeJsonToRedis.lua";
	private static final String READFROMREDISPATH="/lua/readFromRedis.lua";
	private static final String JSON_PATH="/json/product.json"; 
	private static String SUB_PRODUCT_SCRIPT=null;
	private static String READFROMREDISSCRIPT=null;
	private static String JSON_CONTENT=null;
	@Autowired
	RedisCache redisCache;
	static{
		SUB_PRODUCT_SCRIPT=loadScript(SUB_PRODUCT_PATH);
		JSON_CONTENT=loadScript(JSON_PATH);
		READFROMREDISSCRIPT=loadScript(READFROMREDISPATH);
		System.out.println("=====脚本内容=====");
		System.out.println(SUB_PRODUCT_SCRIPT);
		System.out.println(READFROMREDISSCRIPT);
		System.out.println("====参数====");
		System.out.println(JSON_CONTENT);
	}
	public Map<String, String> writeToRedis() {
		List<String> params=new ArrayList<>();
		params.add(JSON_CONTENT);
		redisCache.eval(SUB_PRODUCT_SCRIPT, new ArrayList<>(), params);
		Map<String, String> maps=new HashMap<String, String>();
		maps.put("script", SUB_PRODUCT_SCRIPT);
		maps.put("json", JSON_CONTENT);
		return maps;
	}
	/**
	 * 读取脚本文件
	 * @param path
	 * @return
	 */
	public static String loadScript(String path){
		try {
			ClassPathResource resource=new ClassPathResource(path);
			File file=resource.getFile();
			if(file==null){
				return null;
			}
			return FileUtils.readFileToString(file, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public String readFromRedis() {
		Object obj=redisCache.eval(READFROMREDISSCRIPT,new ArrayList<>(),new ArrayList<>());
		return obj.toString();
	}

}
