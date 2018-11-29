package com.yuyang.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author yuyang
 * @create 2018/11/29 15:30
 * @desc
 **/
public class Constant {
    public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
}
