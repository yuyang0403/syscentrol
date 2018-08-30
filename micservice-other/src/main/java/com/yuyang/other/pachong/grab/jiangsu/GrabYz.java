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
 * Description: 抓取江苏省扬州信息
 * @author nzq
 * @date 2017年9月9日 上午9:02:43
 * @version V1.0
 */

public class GrabYz extends GrabTidyTableUtils {
	private Log logger = LogFactory.getLog(GrabYz.class);
	
	/**
	 * 江苏省扬州市发改委
	 * http://fgw.yangzhou.gov.cn/fgw/tzgg/fgwlist.shtml
	 */
 	public List<Map<String, Object>> grabListFgw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://fgw.yangzhou.gov.cn/fgw/tzgg/fgwlist.shtml";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementInfo = doc.select(".item").select("dd");
			for (Element element : elementInfo) {
				String infoUrl = element.select("a").attr("href");
				String pubDate = element.select("span").text();
				infoUrl ="http://fgw.yangzhou.gov.cn/"+infoUrl.substring(6,infoUrl.length());
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pubDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省扬州市发改委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省扬州市商务局
	 * http://swj.yangzhou.gov.cn/swj/tzgg2/swj_list.shtml
	 */
 	public List<Map<String, Object>> grabListSwj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://swj.yangzhou.gov.cn/swj/tzgg2/swj_list.shtml";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select(".list_right_list").select("tr");
			for (Element element : elements) {
				String infoUrl = element.select("a").attr("href");
				String pushDate =element.select("td").get(2).text();
				infoUrl = "http://swj.yangzhou.gov.cn/"+infoUrl.substring(6,infoUrl.length());
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省扬州市商务局信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省扬州市经信委
	 * http://jxw.yangzhou.gov.cn/jingxw/tzgg/jxw_list.shtml
	 */
 	public List<Map<String, Object>> grabListJxw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://jxw.yangzhou.gov.cn/jingxw/tzgg/jxw_list.shtml";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementInfo = doc.select(".item").select("dd");
			for (Element element : elementInfo) {
				String infoUrl = element.select("a").attr("href");
				String date = element.select("span").text();
				String pubDate = date.substring(1,date.length()-1);
				pubDate = pubDate.replace(" ", "");
				infoUrl ="http://jxw.yangzhou.gov.cn/"+infoUrl.substring(6,infoUrl.length());
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pubDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省扬州市经信委信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省扬州市科技局
	 * http://kjj.yangzhou.gov.cn/kjj/tzgg/list_kjj.shtml
	 */
 	public List<Map<String, Object>> grabListKjj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://kjj.yangzhou.gov.cn/kjj/tzgg/list_kjj.shtml";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select(".list_right_list").select("tr");
			for (Element element : elements) {
				String infoUrl = element.select("a").attr("href");
				String pushDate =element.select("td").get(2).text();
				infoUrl = "http://kjj.yangzhou.gov.cn/"+infoUrl.substring(6,infoUrl.length());
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省扬州市科技局信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**抓取数据**/
	private List<Map<String, Object>> grabNoti(String url, List<Map<String, Object>> list,String pushDate) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sourceUrl", url);
		model.put("isWebsite", "0");//是否网址 0_是 1_否
		model.put("downloadUrl", "");
		model.put("pubDate", pushDate);
		model.put("addr", "江苏省扬州市");
		if(url.contains("http://fgw.yangzhou.gov.cn/")) {
			model.put("source", "发改委");
			model.put("infoType", "通知公告");
			model = this.grabFgwInfo(url, model);
		} else if(url.contains("http://swj.yangzhou.gov.cn/")) {
			model.put("source", "商务局");
			model.put("infoType", "通知公告");
			model = this.grabSwjInfo(url, model);
		} else if(url.contains("http://jxw.yangzhou.gov.cn/")) {
			model.put("source", "经信委");
			model.put("infoType", "通知公告");
			model = this.grabJxwInfo(url, model);
		} else if(url.contains("http://kjj.yangzhou.gov.cn/")) {
			model.put("source", "科技局");
			model.put("infoType", "通知公告");
			model = this.grabKjjInfo(url, model);
		} else {
			model.clear();
		}
		if(model.size() > 0) {
			list.add(model);
		}
		return list;
	}
	
	/**
	 * 发改委
	 * http://fgw.yancheng.gov.cn/xwzx/tzgg/201709/t20170913_667475.html
	 */
	private Map<String, Object> grabFgwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document docUrl = Jsoup.connect(url).timeout(10000).get();
			Elements elementInfo = docUrl.select(".content");
			Elements elementTitle =  docUrl.select(".h120");
			elementTitle.select("span").remove();
			model.put("title",elementTitle.text());
			model.put("pubOrg", "");
			model.put("refNo","");
			Elements elementsA = elementInfo.select("a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(!attachmentUrl.isEmpty()){
					String urlPath = url.substring(0,url.lastIndexOf("/")+1)+attachmentUrl;
					try {
						if (this.isAtta(attachmentUrl)) {
							continue;
						}
						element.attr("href", urlPath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			Elements elementsB = elementInfo.select("img");
			for (Element element : elementsB) {
				String attachmentUrl = element.attr("src");// 附件地址
				if(!attachmentUrl.isEmpty()){
					String urlPath = "";
					if(attachmentUrl.indexOf("sysimage")!=-1){//附件图标
						urlPath = "http://fgw.yancheng.gov.cn"+attachmentUrl;
					}else{//附件图片
						urlPath = url.substring(0,url.lastIndexOf("/")+1)+attachmentUrl;
					}
					try {
						element.attr("src", urlPath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			elementInfo = this.grabTidyTable(elementInfo);
			//内容
			String info = elementInfo.html();
			model.put("info", this.unescapeJava(info));
			
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省扬州市发改委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 江苏省扬州市商务局
	 * http://swj.yancheng.gov.cn/xwzx/tzgg/201709/t20170913_667724.html
	 */
	private Map<String, Object> grabSwjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementsInfo = doc.select("div.content");
			model.put("title",  doc.select(".content_title").text());
			model.put("pubOrg", "");
			model.put("refNo", "");
			Elements elementsA = elementsInfo.select("a");
			for (Element element : elementsA) {
				String urlDow = element.attr("href");
				String attachmentUrl = url.substring(0,url.lastIndexOf("/")+1)+urlDow;
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
			Elements elementsB = elementsInfo.select("img");
			for (Element element : elementsB) {
				String attachmentUrl = element.attr("src");// 附件地址
				if(!attachmentUrl.isEmpty()){
					String urlPath = "";
					if(attachmentUrl.indexOf("sysimage")!=-1){
						urlPath = "http://swj.yancheng.gov.cn"+attachmentUrl;
					}else{
						urlPath = url.substring(0,url.lastIndexOf("/")+1)+attachmentUrl;
					}
					try {
						element.attr("src", urlPath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			elementsInfo = this.grabTidyTable(elementsInfo);
			//内容
			String info = elementsInfo.html();
			model.put("info", this.unescapeJava(info));
			
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省扬州市商务局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}

	/**
	 * 经信委
	 * http://jxw.yangzhou.gov.cn/
	 */
	private Map<String, Object> grabJxwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document docUrl = Jsoup.connect(url).timeout(10000).get();
			Elements elementInfo = docUrl.select(".content");
			Elements elementTitle =  docUrl.select(".h120");
			elementTitle.select(".content_subtitle").remove();
			model.put("title", elementTitle.text());
			model.put("pubOrg", "");
			model.put("refNo", "");
			Elements elementsA = elementInfo.select("a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if(!attachmentUrl.isEmpty()){
					String urlPath = url.substring(0,url.lastIndexOf("/")+1)+attachmentUrl;
					try {
						if (this.isAtta(attachmentUrl)) {
							continue;
						}
						element.attr("href", urlPath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Elements elementsB = elementInfo.select("img");
			for (Element element : elementsB) {
				String attachmentUrl = element.attr("src");// 附件地址
				if(!attachmentUrl.isEmpty()){
					String urlPath = "";
					if(attachmentUrl.indexOf("sysimage")!=-1){
						urlPath = "http://jxw.yangzhou.gov.cn"+attachmentUrl;
					}else{
						urlPath = url.substring(0,url.lastIndexOf("/")+1)+attachmentUrl;
					}
					try {
						element.attr("src", urlPath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			elementInfo = this.grabTidyTable(elementInfo);
			//内容
			String info = elementInfo.html();
			model.put("info", this.unescapeJava(info));
			
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省扬州市经信委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 科技局
	 * http://kjj.yangzhou.gov.cn/kjj/tzgg/list_kjj.shtml
	 */
	private Map<String, Object> grabKjjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementsInfo = doc.select("div.content");
			model.put("title",  doc.select(".content_title").text());
			model.put("pubOrg", "");
			model.put("refNo", "");
			Elements elementsA = elementsInfo.select("a");
			for (Element element : elementsA) {
				String urlDow = element.attr("href");
				String attachmentUrl = url.substring(0,url.lastIndexOf("/")+1)+urlDow;
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
			Elements elementsB = elementsInfo.select("img");
			for (Element element : elementsB) {
				String attachmentUrl = element.attr("src");// 附件地址
				if(!attachmentUrl.isEmpty()){
					String urlPath = "";
					if(attachmentUrl.indexOf("sysimage")!=-1){
						urlPath = "http://kjj.yangzhou.gov.cn"+attachmentUrl;
					}else{
						urlPath = url.substring(0,url.lastIndexOf("/")+1)+attachmentUrl;
					}
					try {
						element.attr("src", urlPath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			elementsInfo = this.grabTidyTable(elementsInfo);
			//内容
			String info = elementsInfo.html();
			model.put("info", this.unescapeJava(info));
			
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省扬州市科技局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		GrabYz grabyz = new GrabYz();
//		grabyz.grabListFgw(list); // 集合
//		grabyz.grabListSwj(list);
//		grabyz.grabListJxw(list);
		grabyz.grabListKjj(list);
	}
}
