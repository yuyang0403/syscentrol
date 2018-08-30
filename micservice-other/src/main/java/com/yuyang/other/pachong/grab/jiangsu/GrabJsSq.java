package com.yuyang.other.pachong.grab.jiangsu;

import com.yuyang.other.pachong.grab.util.GrabTidyTableUtils;
import com.yuyang.other.pachong.grab.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Description: 抓取江苏省宿迁市 信息
 * @author nzq
 * @date 2017年9月9日 上午9:02:43
 * @version V1.0
 */

public class GrabJsSq extends GrabTidyTableUtils {
	private Log logger = LogFactory.getLog(GrabJsSq.class);
	
	/**
	 * 宿迁市  科技局
	 * http://kjj.suqian.gov.cn/skjk/tzgg/list_wz.shtml
	 */
 	public List<Map<String, Object>> grabListKjj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://kjj.suqian.gov.cn/skjk/tzgg/list_wz.shtml";
		try {
			 Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
			    		.header("Accept","gzip, deflate")
			    		.header("Accept-Encoding","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
			    		.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
			    		.get();  
			Elements elements = doc.select("div.list_r_lb_h").select("table").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				if(infoUrl.contains("../../")){
					infoUrl = "http://kjj.suqian.gov.cn"+infoUrl.substring(5, infoUrl.length());
				}else{
					continue;
				}
				if(!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.parent().nextElementSibling().text();
					pushDate = pushDate.substring(1, pushDate.length());
					pushDate = pushDate.substring(0, pushDate.length()-1);
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取宿迁市  科技局 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省宿迁市  经信委
	 * http://jmw.suqian.gov.cn/sjxw/tzgg/listwz.shtml
	 */
 	public List<Map<String, Object>> grabListJxw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://jmw.suqian.gov.cn/sjxw/tzgg/listwz.shtml";
		try {
			Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
		    		.header("Accept","gzip, deflate")
		    		.header("Accept-Encoding","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		    		.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
		    		.get();  
		Elements elements = doc.select("div.list_r_lb_h").select("table").select("a");
		for (Element el : elements) {
			String infoUrl = el.attr("href");
			if(infoUrl.contains("../../")){
				infoUrl = "http://jmw.suqian.gov.cn"+infoUrl.substring(5, infoUrl.length());
			}else{
				continue;
			}
			if(!listContains(listParam, "sourceUrl", infoUrl)) {
				String pushDate = el.parent().nextElementSibling().text();
				pushDate = pushDate.substring(1, pushDate.length());
				pushDate = pushDate.substring(0, pushDate.length()-1);
				list = this.grabNoti(infoUrl, list,pushDate);
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省宿迁市  经信委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省宿迁市 商务局
	 * http://sqswj.suqian.gov.cn/sswj/ggtz/list_wz.shtml
	 */
 	public List<Map<String, Object>> grabListSwj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://sqswj.suqian.gov.cn/sswj/ggtz/list_wz.shtml";
		try {
			Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
		    		.header("Accept","gzip, deflate")
		    		.header("Accept-Encoding","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		    		.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
		    		.get();  
		Elements elements = doc.select("div.list_r_lb_h").select("table").select("a");
		for (Element el : elements) {
			String infoUrl = el.attr("href");
			if(infoUrl.contains("../../")){
				infoUrl = "http://sqswj.suqian.gov.cn"+infoUrl.substring(5, infoUrl.length());
			}else{
				continue;
			}
			if(!listContains(listParam, "sourceUrl", infoUrl)) {
				String pushDate = el.parent().nextElementSibling().text();
				pushDate = pushDate.substring(1, pushDate.length());
				pushDate = pushDate.substring(0, pushDate.length()-1);
				list = this.grabNoti(infoUrl, list,pushDate);
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省宿迁市 商务局 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省宿迁市 发改委
	 * http://fgw.suqian.gov.cn/sfgw/tzgg1/list_zz.shtml
	 */
 	public List<Map<String, Object>> grabListFgw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://fgw.suqian.gov.cn/sfgw/tzgg1/list_zz.shtml";
		try {
			Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
		    		.header("Accept","gzip, deflate")
		    		.header("Accept-Encoding","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		    		.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
		    		.get();  
		Elements elements = doc.select("div.list_r_lb_h").select("table").select("a");
		for (Element el : elements) {
			String infoUrl = el.attr("href");
			if(infoUrl.contains("../../")){
				infoUrl = "http://fgw.suqian.gov.cn"+infoUrl.substring(5, infoUrl.length());
			}else{
				continue;
			}
			if(!listContains(listParam, "sourceUrl", infoUrl)) {
				String pushDate = el.parent().nextElementSibling().text();
				pushDate = pushDate.substring(1, pushDate.length());
				pushDate = pushDate.substring(0, pushDate.length()-1);
				list = this.grabNoti(infoUrl, list,pushDate);
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省宿迁市 发改委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**抓取数据**/
	private List<Map<String, Object>> grabNoti(String url, List<Map<String, Object>> list,String pushDate) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sourceUrl", url);
		model.put("isWebsite", "0");//是否网址 0_是 1_否
		model.put("downloadUrl", "");
		model.put("addr", "江苏省宿迁市");
		if(url.contains("http://kjj.suqian.gov.cn")) {
			model.put("source", "科技局");
			model.put("pubDate", pushDate);
			model.put("infoType", "通知公告");
			model = this.grabKjjInfo(url, model);
		} else if(url.contains("http://jmw.suqian.gov.cn")) {
			model.put("source", "经信委");
			model.put("pubDate", pushDate);
			model.put("infoType", "通知公告");
			model = this.grabJxwInfo(url, model);
		} else if(url.contains("http://sqswj.suqian.gov.cn")) {
			model.put("source", "商务局");
			model.put("infoType", "公告通知");
			model.put("pubDate", pushDate);
			model = this.grabSwjInfo(url, model);
		} else if(url.contains("http://fgw.suqian.gov.cn")) {
			model.put("source", "发改委");
			model.put("infoType", "通知公告");
			model.put("pubDate", pushDate);
			model = this.grabFgwInfo(url, model);
		} else {
			model.clear();
		}
		if(model.size() > 0) {
			list.add(model);
		}
		return list;
	}
	
	/**
	 * 科技局
	 */
	private Map<String, Object> grabKjjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0").get();
			String title = doc.getElementById("zoomtitle").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("zoomcon");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("files")){
					attachmentUrl=url.substring(0,url.lastIndexOf("/"))+"/"+attachmentUrl;
				}else{
					continue;
				}
				String filePath = attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("href", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Elements elementsImg = elementsInfo.select("p img");
			for (Element element : elementsImg) {
				String attachmentUrl = element.attr("src");// 图片地址
				String filePath = "http://stc.wuxi.gov.cn"+attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("src", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取 江苏省宿迁市 科技局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 经信委
	 */
	private Map<String, Object> grabJxwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0").get();
			String title = doc.getElementById("zoomtitle").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("zoomcon");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("files")){
					attachmentUrl=url.substring(0,url.lastIndexOf("/"))+"/"+attachmentUrl;
				}else{
					continue;
				}
				String filePath = attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("href", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Elements elementsImg = elementsInfo.select("p img");
			for (Element element : elementsImg) {
				String attachmentUrl = element.attr("src");// 图片地址
				String filePath = url.substring(0,url.lastIndexOf("/"))+"/"+attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("src", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取 江苏省宿迁市 经信委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}

	/**
	 * 商务局
	 */
	private Map<String, Object> grabSwjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0").get();
			String title = doc.getElementById("zoomtitle").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("zoomcon");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("files")){
					attachmentUrl=url.substring(0,url.lastIndexOf("/"))+"/"+attachmentUrl;
				}else{
					continue;
				}
				String filePath = attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("href", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Elements elementsImg = elementsInfo.select("p img");
			for (Element element : elementsImg) {
				String attachmentUrl = element.attr("src");// 图片地址
				String filePath = url.substring(0,url.lastIndexOf("/"))+"/"+attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("src", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取 江苏省宿迁市 商务局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 发改委
	 */
	private Map<String, Object> grabFgwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0").get();
			String title = doc.getElementById("zoomtitle").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("zoomcon");
			Elements elementsA = elementsInfo.select("a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("files")){
					attachmentUrl=url.substring(0,url.lastIndexOf("/"))+"/"+attachmentUrl;
				}else{
					continue;
				}
				String filePath = attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("href", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Elements elementsImg = elementsInfo.select("p img");
			for (Element element : elementsImg) {
				String attachmentUrl = element.attr("src");// 图片地址
				String filePath = url.substring(0,url.lastIndexOf("/"))+"/"+attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("src", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取 江苏省宿迁市发改委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		GrabJsSq grabJsNj = new GrabJsSq();
//		list = grabJsNj.grabListKjj(list); // 集合
//		list = grabJsNj.grabListJxw(list);
//		list = grabJsNj.grabListSwj(list);
		list = grabJsNj.grabListFgw(list);
	}
}
