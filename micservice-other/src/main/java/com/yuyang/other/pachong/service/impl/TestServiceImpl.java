package com.yuyang.other.pachong.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuyang.other.pachong.grab.util.JsonUtils;
import com.yuyang.other.pachong.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yuyang
 * @create 2018/8/30 10:16
 * @desc
 **/
@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public JSONArray getHtml() {
        long start = System.currentTimeMillis();
        JSONArray jsonArray=new JSONArray();
        //先获取各个地区的网址url
        String url="http://www.xiaozhu.com/";
        try {
            Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
                    .header("Accept","gzip, deflate")
                    .header("Accept-Encoding","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .get();
            Elements elements=doc.select("dl.link_dl").select("a");
            for (Element el : elements) {
               String cityUrl=el.attr("href");
               if(!cityUrl.contains("http")){
                   cityUrl="http:"+cityUrl;
                   JSONObject jo=new JSONObject();
                   jo.put(el.text(),getHtmlByUrl(cityUrl));
                   jsonArray.add(jo);
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("拉取数据结束，总用时："+(end-start)+"ms(毫秒)");
        return jsonArray;
    }

    private JSONArray getHtmlByUrl(String url) {
        JSONArray jsonArray=new JSONArray();
        long start = System.currentTimeMillis();
        try {
            Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
                    .header("Accept","gzip, deflate")
                    .header("Accept-Encoding","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .get();
            Elements elements=doc.getElementById("space_list").children().get(1).children();
            for (Element el : elements) {
                JSONObject jo=new JSONObject();
                jo.put("object",el.html());,
                jsonArray.add(jo);
               log.info(el.html());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+jsonArray.toString()+"】");

        return jsonArray;
    }
}
