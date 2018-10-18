package com.yuyang.other.lua.service;

import java.util.Map;

public interface LuaTestService {
	public Map<String, String> writeToRedis();
	public String readFromRedis();
}
