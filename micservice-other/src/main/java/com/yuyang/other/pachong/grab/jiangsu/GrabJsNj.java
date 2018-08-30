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
 * Description: 抓取江苏省南京市 信息
 * @author nzq
 * @date 2017年9月9日 上午9:02:43
 * @version V1.0
 */

public class GrabJsNj extends GrabTidyTableUtils {
	private Log logger = LogFactory.getLog(GrabJsNj.class);
	
	/**
	 * 南京市  科技局
	 * http://kw.nanjing.gov.cn/23955/23968/23969
	 */
 	public List<Map<String, Object>> grabListNjKjj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://kw.nanjing.gov.cn/23955/23968/23969";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.getElementById("gv_news").select("tr").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				infoUrl = url+infoUrl.substring(1,infoUrl.length());
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
		logger.info("抓取南京市  科技局 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省南京市  经信委
	 * http://www.njec.gov.cn/zwgk/gsgg/
	 */
 	public List<Map<String, Object>> grabListJxw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://www.njec.gov.cn/zwgk/gsgg/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("div.c1-body").select("div").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href").replace("./", "");
				infoUrl = url+infoUrl;
				if(!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.parent().nextElementSibling().text();
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省南京市  经信委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省南京市 商务局
	 * http://www.njcom.gov.cn/73708
	 */
 	public List<Map<String, Object>> grabListSwj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://www.njcom.gov.cn/73708/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("div.row").select("li").select("a").select("a.b_link");
			for (Element el : elements) {
				String infoUrl = el.attr("href").replace("./", "");
				infoUrl = url+infoUrl;
				if(!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.previousElementSibling().text();
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省南京市 商务局 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省南京市 发改委
	 * http://www.njdpc.gov.cn/tongzhigonggao/
	 */
 	public List<Map<String, Object>> grabListFgw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://www.njdpc.gov.cn/tongzhigonggao/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("td.line-01").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				if(infoUrl.contains("www")&&infoUrl.contains(".html")){
					
				}else if(infoUrl.contains("../")&&infoUrl.contains(".html")){
					infoUrl = "http://www.njdpc.gov.cn"+infoUrl.substring(3, infoUrl.length());
				}else if(infoUrl.contains("./")&&infoUrl.contains(".html")){
					infoUrl = url+infoUrl.substring(2, infoUrl.length());
				}
				if(!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.parent().nextElementSibling().text();
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省南京市 发改委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**抓取数据**/
	private List<Map<String, Object>> grabNoti(String url, List<Map<String, Object>> list,String pushDate) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sourceUrl", url);
		model.put("isWebsite", "0");//是否网址 0_是 1_否
		model.put("downloadUrl", "");
		model.put("addr", "江苏省南京市");
		if(url.contains("http://kw.nanjing.gov.cn")) {
			model.put("source", "科技局");
			model.put("pubDate", pushDate);
			model.put("infoType", "信息公开");
			model = this.grabKjjInfo(url, model);
		} else if(url.contains("http://www.njec.gov.cn")) {
			model.put("source", "经信委");
			model.put("pubDate", pushDate);
			model.put("infoType", "通知公告");
			model = this.grabJxwInfo(url, model);
		} else if(url.contains("http://www.njcom.gov.cn")) {
			model.put("source", "商务局");
			model.put("infoType", "通知公告 ");
			model.put("pubDate", pushDate);
			model = this.grabSwjInfo(url, model);
		} else if(url.contains("http://www.njdpc.gov.cn")) {
			model.put("source", "发改委");
			model.put("infoType", "通知公告 ");
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
	 * http://kw.nanjing.gov.cn/23955/23968/23969/
	 */
	private Map<String, Object> grabKjjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String title = doc.getElementById("lbltitle").text();
			model.put("title", title);
			String newsInfo = doc.getElementById("lblupdatetime").parent().text();
			String code = "信息来源：";
			String pubOrg = newsInfo.substring(newsInfo.indexOf(code)+code.length(),newsInfo.length() );
			model.put("pubOrg", pubOrg);
			
			Element elementsInfo = doc.getElementById("tabinfo");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(attachmentUrl.contains("./")){
					attachmentUrl=url.substring(0,url.lastIndexOf("/"))+attachmentUrl.substring(1,attachmentUrl.length());
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
				String filePath = "http://kw.nanjing.gov.cn"+attachmentUrl;
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
		logger.info("抓取 江苏省南京市  科技局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 经信委
	 * http://www.njec.gov.cn/zwgk/gsgg/
	 */
	private Map<String, Object> grabJxwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementsInfo = doc.select("div.TRS_Editor");
			model.put("title", doc.select("title").text());
			if(elementsInfo.isEmpty()) {
				model.clear();
				return model;
			}
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");
				if(attachmentUrl.contains("./")){
					attachmentUrl = url.substring(0, url.lastIndexOf("/")+1)+attachmentUrl.replace("./", "");// 附件地址
					
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
			elementsInfo = this.grabTidyTable(elementsInfo);
			//内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
			
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取  江苏省南京市 经信委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}

	/**
	 * 商务局
	 * http://www.njcom.gov.cn/73708/
	 */
	private Map<String, Object> grabSwjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String title = doc.select("div.v_title.font_yh").text();
			model.put("title", title);
			Elements elementsInfo = doc.select("div.content");
			if(elementsInfo.isEmpty()) {
				model.clear();
				return model;
			}
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");
				if(attachmentUrl.contains("./")){
					attachmentUrl = url.substring(0, url.lastIndexOf("/")+1)+attachmentUrl.replace("./", "");// 附件地址
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
			elementsInfo = this.grabTidyTable(elementsInfo);
			//内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
			
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取  江苏省南京市商务局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 发改委
	 * http://www.njdpc.gov.cn/tongzhigonggao/
	 */
	private Map<String, Object> grabFgwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			model.put("title", doc.select("title").text());
			Elements elementsInfo = doc.getElementById("attach").parent().select("td.p1");
			if(elementsInfo.isEmpty()) {
				model.clear();
				return model;
			}
			Elements elementsA = elementsInfo.select("a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");
				if(attachmentUrl.contains("./")){
					attachmentUrl = url.substring(0, url.lastIndexOf("/")+1)+attachmentUrl.replace("./", "");// 附件地址
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
			elementsInfo = this.grabTidyTable(elementsInfo);
			//内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
			
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取  江苏省南京市 发改委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		GrabJsNj grabJsNj = new GrabJsNj();
		list = grabJsNj.grabListNjKjj(list); // 集合
		list = grabJsNj.grabListJxw(list);
		list = grabJsNj.grabListSwj(list);
		list = grabJsNj.grabListFgw(list);
	}
}
