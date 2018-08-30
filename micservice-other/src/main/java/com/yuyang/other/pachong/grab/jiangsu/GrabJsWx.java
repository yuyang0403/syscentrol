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
 * Description: 抓取江苏省无锡市 信息
 * @author nzq
 * @date 2017年9月9日 上午9:02:43
 * @version V1.0
 */

public class GrabJsWx extends GrabTidyTableUtils {
	private Log logger = LogFactory.getLog(GrabJsWx.class);
	
	/**
	 * 无锡市  科技局
	 * http://stc.wuxi.gov.cn/zfxxgk/gggs/index.shtml
	 */
 	public List<Map<String, Object>> grabListKjj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://stc.wuxi.gov.cn/zfxxgk/gggs/index.shtml";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("ul.List_list.font14.lh26").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				infoUrl = "http://stc.wuxi.gov.cn"+infoUrl;
				if(!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.nextElementSibling().text();
					pushDate = pushDate.substring(1, pushDate.length());
					pushDate = pushDate.substring(0, pushDate.length()-1);
					pushDate = pushDate.replaceAll("/", "-");
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取无锡市  科技局 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省无锡市  经信委
	 * http://etc.wuxi.gov.cn/zfxxgk/gggs/index.shtml
	 */
 	public List<Map<String, Object>> grabListJxw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://etc.wuxi.gov.cn/zfxxgk/gggs/index.shtml";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("ul.List_list.font14.lh26").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				infoUrl = "http://etc.wuxi.gov.cn"+infoUrl;
				if(!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.nextElementSibling().text();
					pushDate = pushDate.substring(1, pushDate.length());
					pushDate = pushDate.substring(0, pushDate.length()-1);
					pushDate = pushDate.replaceAll("/", "-");
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省无锡市  经信委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省无锡市 商务局
	 * http://mofcom.wuxi.gov.cn/zfxxgk/gggs/index.shtml
	 */
 	public List<Map<String, Object>> grabListSwj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://mofcom.wuxi.gov.cn/zfxxgk/gggs/index.shtml";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("ul.List_list.font14.lh26").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				infoUrl = "http://mofcom.wuxi.gov.cn"+infoUrl;
				if(!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.nextElementSibling().text();
					pushDate = pushDate.substring(1, pushDate.length());
					pushDate = pushDate.substring(0, pushDate.length()-1);
					pushDate = pushDate.replaceAll("/", "-");
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省无锡市 商务局 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省无锡市 发改委
	 * http://dpc.wuxi.gov.cn/zfxxgk/gggs/index.shtml
	 */
 	public List<Map<String, Object>> grabListFgw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://dpc.wuxi.gov.cn/zfxxgk/gggs/index.shtml";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("ul.List_list.font14.lh26").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				infoUrl = "http://dpc.wuxi.gov.cn"+infoUrl;
				if(!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.nextElementSibling().text();
					pushDate = pushDate.substring(1, pushDate.length());
					pushDate = pushDate.substring(0, pushDate.length()-1);
					pushDate = pushDate.replaceAll("/", "-");
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省无锡市 发改委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**抓取数据**/
	private List<Map<String, Object>> grabNoti(String url, List<Map<String, Object>> list,String pushDate) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sourceUrl", url);
		model.put("isWebsite", "0");//是否网址 0_是 1_否
		model.put("downloadUrl", "");
		model.put("addr", "江苏省无锡市");
		if(url.contains("http://stc.wuxi.gov.cn")) {
			model.put("source", "科技局");
			model.put("pubDate", pushDate);
			model.put("infoType", "公告公示");
			model = this.grabKjjInfo(url, model);
		} else if(url.contains("http://etc.wuxi.gov.cn")) {
			model.put("source", "经信委");
			model.put("pubDate", pushDate);
			model.put("infoType", "公告公示");
			model = this.grabJxwInfo(url, model);
		} else if(url.contains("http://mofcom.wuxi.gov.cn")) {
			model.put("source", "商务局");
			model.put("infoType", "公告公示");
			model.put("pubDate", pushDate);
			model = this.grabSwjInfo(url, model);
		} else if(url.contains("http://dpc.wuxi.gov.cn")) {
			model.put("source", "发改委");
			model.put("infoType", "公告公示");
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
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String title = doc.select("title").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("Zoom");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("/")){
					attachmentUrl="http://stc.wuxi.gov.cn"+attachmentUrl;
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
		logger.info("抓取 江苏省无锡市 科技局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 经信委
	 */
	private Map<String, Object> grabJxwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String title = doc.select("title").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("Zoom");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("http")){
					continue;
				}else{
					attachmentUrl="http://etc.wuxi.gov.cn"+attachmentUrl;
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
				String filePath = "http://etc.wuxi.gov.cn"+attachmentUrl;
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
		logger.info("抓取 江苏省无锡市 经信委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}

	/**
	 * 商务局
	 */
	private Map<String, Object> grabSwjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String title = doc.select("title").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("Zoom");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("http")){
					continue;
				}else{
					attachmentUrl="http://mofcom.wuxi.gov.cn"+attachmentUrl;
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
				String filePath = "http://mofcom.wuxi.gov.cn"+attachmentUrl;
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
		logger.info("抓取 江苏省无锡市 商务局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 发改委
	 */
	private Map<String, Object> grabFgwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String title = doc.select("title").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("Zoom");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("http")){
					continue;
				}else{
					attachmentUrl="http://dpc.wuxi.gov.cn"+attachmentUrl;
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
				String filePath = "http://dpc.wuxi.gov.cn"+attachmentUrl;
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
		logger.info("抓取 江苏省无锡市发改委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		GrabJsWx grabJsNj = new GrabJsWx();
//		list = grabJsNj.grabListKjj(list); // 集合
//		list = grabJsNj.grabListJxw(list);
//		list = grabJsNj.grabListSwj(list);
		list = grabJsNj.grabListFgw(list);
	}
}
