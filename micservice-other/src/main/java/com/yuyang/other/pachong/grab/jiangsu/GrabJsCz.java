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
 * Description: 抓取江苏省常州市 信息
 * @author nzq
 * @date 2017年9月9日 上午9:02:43
 * @version V1.0
 */

public class GrabJsCz extends GrabTidyTableUtils {
	private Log logger = LogFactory.getLog(GrabJsCz.class);
	
	/**
	 * 常州市  科技局
	 * http://kjj.changzhou.gov.cn/class/MHFPIKKM
	 */
 	public List<Map<String, Object>> grabListKjj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://kjj.changzhou.gov.cn/class/MHFPIKKM";
		try {
		    Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
		    		.header("Accept","gzip, deflate")
		    		.header("Accept-Encoding","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		    		.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
		    		.get();  
			Elements elements = doc.select("table.titi3").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				infoUrl = "http://kjj.changzhou.gov.cn"+infoUrl;
				if(!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.parent().nextElementSibling().text();
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取常州市  科技局 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省常州市  经信委
	 * http://jxw.changzhou.gov.cn/class/PIFDOPAQ
	 */
 	public List<Map<String, Object>> grabListJxw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://jxw.changzhou.gov.cn/class/PIFDOPAQ";
		try {
			 Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
			    		.header("Accept","gzip, deflate")
			    		.header("Accept-Encoding","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
			    		.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
			    		.get();  
			 Elements elements = doc.select("table.border2").select("a");
				for (Element el : elements) {
					String infoUrl = el.attr("href");
					infoUrl = "http://jxw.changzhou.gov.cn"+infoUrl;
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
		logger.info("抓取江苏省常州市  经信委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	

 	/**
	 * 江苏省常州市 商务局
	 * http://swj.changzhou.gov.cn/class/IMDDOPEC
	 */
 	public List<Map<String, Object>> grabListSwj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://swj.changzhou.gov.cn/class/IMDDOPEC";
		try {
			 Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
			    		.header("Accept","gzip, deflate")
			    		.header("Accept-Encoding","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
			    		.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
			    		.get();  
			 Elements elements = doc.select("[valign=top").select("a");
				for (Element el : elements) {
					String infoUrl = el.attr("href");
					if(infoUrl.contains("/html/swj")){
						if(!listContains(listParam, "sourceUrl", infoUrl)) {
							infoUrl = "http://swj.changzhou.gov.cn"+infoUrl;
							if(el.parent().nextElementSibling()!=null){
								String pushDate = el.parent().nextElementSibling().text();
								list = this.grabNoti(infoUrl, list,pushDate);
							}
						}
					}else{
						continue;
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省常州市 商务局 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省常州市 发改委
	 * http://fgw.changzhou.gov.cn/class/ICPQOFCJ
	 */
 	public List<Map<String, Object>> grabListFgw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://fgw.changzhou.gov.cn/class/ICPQOFCJ";
		try {
			 Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
			    		.header("Accept","gzip, deflate")
			    		.header("Accept-Encoding","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
			    		.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
			    		.get();  
			 Elements elements = doc.select("table.border2").select("a");
				for (Element el : elements) {
					String infoUrl = el.attr("href");
					infoUrl = "http://fgw.changzhou.gov.cn"+infoUrl;
					if(!listContains(listParam, "sourceUrl", infoUrl)) {
						String pushDate = el.parent().nextElementSibling().text();
						pushDate = pushDate.substring(1, pushDate.length());
						pushDate = pushDate.substring(0, pushDate.length()-1);
						list = this.grabNoti(infoUrl, list,"20"+pushDate);
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省常州市 发改委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**抓取数据**/
	private List<Map<String, Object>> grabNoti(String url, List<Map<String, Object>> list,String pushDate) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sourceUrl", url);
		model.put("isWebsite", "0");//是否网址 0_是 1_否
		model.put("downloadUrl", "");
		model.put("addr", "江苏省常州市");
		if(url.contains("http://kjj.changzhou.gov.cn")) {
			model.put("source", "科技局");
			model.put("pubDate", pushDate);
			model.put("infoType", "通知公告");
			model = this.grabKjjInfo(url, model);
		} else if(url.contains("http://jxw.changzhou.gov.cn")) {
			model.put("source", "经信委");
			model.put("pubDate", pushDate);
			model.put("infoType", "通知公告公示");
			model = this.grabJxwInfo(url, model);
		} else if(url.contains("http://swj.changzhou.gov.cn")) {
			model.put("source", "商务局");
			model.put("infoType", "通知公告");
			model.put("pubDate", pushDate);
			model = this.grabSwjInfo(url, model);
		} else if(url.contains("http://fgw.changzhou.gov.cn")) {
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
			String title = doc.select("h1.iip").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("czfxfontzoom");
			Elements elementsA = elementsInfo.select("a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("uploadfile")){
					attachmentUrl="http://kjj.changzhou.gov.cn"+attachmentUrl;
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
				String filePath = "http://kjj.changzhou.gov.cn"+attachmentUrl;
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
		logger.info("抓取 江苏省常州市 科技局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 经信委
	 */
	private Map<String, Object> grabJxwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0").get();
			String title = doc.select("table").select("td.NewsTitle").text();
			model.put("title", title);
			Elements Elements = doc.select("td.NewsTitle").parents();
			String pubOrg = Elements.get(2).text();
			String code1 = "来源：";
			String code2="字号:";
			int index1 = pubOrg.indexOf(code1);
			if(index1>-1){
				index1 = index1+code1.length();
				pubOrg = pubOrg.substring(index1, pubOrg.indexOf(code2)).trim();
				model.put("pubOrg", pubOrg);
			}
			Elements elementsInfo = doc.select("td.NewsContent");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("uploadfile")){
					attachmentUrl="http://jxw.changzhou.gov.cn"+attachmentUrl;
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
				String filePath = "http://jxw.changzhou.gov.cn"+attachmentUrl;
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
		logger.info("抓取 江苏省常州市 经信委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}

	/**
	 * 商务局
	 */
	private Map<String, Object> grabSwjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0").get();
			String title = doc.select("td.NewsTitle").text();
			model.put("title", title);
			Elements elementsInfo = doc.select("td.NewsText");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("uploadfile")){
					attachmentUrl="http://swj.changzhou.gov.cn"+attachmentUrl;
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
				String filePath = "http://swj.changzhou.gov.cn"+attachmentUrl;
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
		logger.info("抓取 江苏省常州市 商务局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 发改委
	 */
	private Map<String, Object> grabFgwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0").get();
			String title = doc.select("td.NewsTitle").text();
			model.put("title", title.substring(0,title.indexOf("分享")).trim());
			Element elementsInfo = doc.getElementById("czfxfontzoom");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("uploadfile")){
					attachmentUrl="http://fgw.changzhou.gov.cn"+attachmentUrl;
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
				String filePath = "http://fgw.changzhou.gov.cn"+attachmentUrl;
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
		logger.info("抓取 江苏省常州市发改委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		GrabJsCz grabJsNj = new GrabJsCz();
//		list = grabJsNj.grabListKjj(list); // 集合
//		list = grabJsNj.grabListJxw(list);
//		list = grabJsNj.grabListSwj(list);
		list = grabJsNj.grabListFgw(list);
	}
}
