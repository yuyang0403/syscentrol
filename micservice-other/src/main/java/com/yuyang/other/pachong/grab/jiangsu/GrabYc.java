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
 * Description: 抓取辽宁省 信息
 * @author nzq
 * @date 2017年9月9日 上午9:02:43
 * @version V1.0
 */

public class GrabYc extends GrabTidyTableUtils {
	private Log logger = LogFactory.getLog(GrabYc.class);
	
	/**
	 * 江苏省盐城市发改委
	 * http://fgw.yancheng.gov.cn/xwzx/tzgg/
	 */
 	public List<Map<String, Object>> grabListFgw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://fgw.yancheng.gov.cn/xwzx/tzgg/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("table").get(13).select("tr");
			for (Element element : elements) {
				String infoUrl = element.select("a").attr("href");
				infoUrl = url+infoUrl.replace("./", "");
				String pushDate = element.select("td[width]").text();
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省盐城市发改委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省盐城市商务局
	 * http://swj.yancheng.gov.cn/xwzx/tzgg/
	 */
 	public List<Map<String, Object>> grabListSwj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://swj.yancheng.gov.cn/xwzx/tzgg/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("table[align]");
			Elements elementInfo = elements.get(3).select("tr");
			for (Element element : elementInfo) {
				String text = element.text();
				String date = text.substring(text.length()-11,text.length()-1);
				String infoUrl = element.select("a").attr("href").replace("./", "");
				infoUrl = url+infoUrl;
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,date);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省盐城市商务局信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省盐城市经信委
	 * http://jxw.yancheng.gov.cn/xwzx/tzgg/
	 */
 	public List<Map<String, Object>> grabListJxw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://jxw.yancheng.gov.cn/xwzx/tzgg/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementInfo = doc.select("table[style]").select("table[width]");
			Elements elements = elementInfo.get(1).select("tr");
			for (Element element : elements) {
				String infoUrl = element.select("a").attr("href");
				element.select("td[height]").remove();
				String pushDate = element.select("td[align]").text();
				infoUrl = url+infoUrl.substring(2,infoUrl.length());
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省盐城市经信委信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省盐城市科技局
	 * http://kjj.yancheng.gov.cn/xwzx/tzgg/
	 */
 	public List<Map<String, Object>> grabListKjt(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://kjj.yancheng.gov.cn/xwzx/tzgg/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("a[target]");
			for (Element element : elements) {
				String infoUrl = element.attr("href");
				String pushDate ="";
				if(infoUrl.indexOf(".html")!=-1){
					infoUrl = url+infoUrl.substring(2,infoUrl.length());
				}else{
					continue;
				}
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省盐城市科技局信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**抓取数据**/
	private List<Map<String, Object>> grabNoti(String url, List<Map<String, Object>> list,String pushDate) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sourceUrl", url);
		model.put("isWebsite", "0");//是否网址 0_是 1_否
		model.put("downloadUrl", "");
		model.put("pubDate", pushDate);
		model.put("addr", "江苏省盐城市");
		if(url.contains("http://fgw.yancheng.gov.cn/xwzx/tzgg/")) {
			model.put("source", "发改委");
			model.put("infoType", "通知公告");
			model = this.grabFgwInfo(url, model);
		} else if(url.contains("http://swj.yancheng.gov.cn/xwzx/tzgg/")) {
			model.put("source", "商务局");
			model.put("infoType", "通知公告 ");
			model = this.grabSwjInfo(url, model);
		} else if(url.contains("http://jxw.yancheng.gov.cn/xwzx/tzgg/")) {
			model.put("source", "经信委");
			model.put("infoType", "通知公告 ");
			model = this.grabJxwInfo(url, model);
		} else if(url.contains("http://kjj.yancheng.gov.cn/xwzx/tzgg/")) {
			model.put("source", "科技局");
			model.put("infoType", "通知公告 ");
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
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("table[style]");
			Elements titles = elements.get(3).select("td");
			model.put("title", titles.get(1).text());
			model.put("refNo", "");
			String newsInfo =titles.get(2).text();
			String code1 = "稿件来源：";
			String code2="浏览次数";
			int index1 = newsInfo.indexOf(code1)+ code1.length();
			String pubOrg = newsInfo.substring(index1, newsInfo.indexOf(code2));
			model.put("pubOrg", pubOrg);
			Elements elementsInfo = doc.select("div.TRS_Editor");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				String filePath = url.substring(url.lastIndexOf("/"))+attachmentUrl.substring(1,attachmentUrl.length());
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
				String filePath = url.substring(url.lastIndexOf("/"))+attachmentUrl.substring(1,attachmentUrl.length());
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("src", filePath);
				} catch (Exception e) {
					e.printStackTrace();
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
		logger.info("抓取江苏省盐城市发改委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 江苏省盐城市商务局
	 * http://swj.yancheng.gov.cn/xwzx/tzgg/201709/t20170913_667724.html
	 */
	private Map<String, Object> grabSwjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("table");
			model.put("title", elements.get(6).select("td").get(0).text());
			model.put("pubOrg","");
			model.put("refNo", "");
			Elements elementsInfo = elements.select("div.TRS_Editor");
			Elements elementsScript = elementsInfo.select("img");
			if(elementsScript!=null){
				for (Element element : elementsScript) {
					String infoUrl = element.attr("src");
					infoUrl = url.substring(0,url.lastIndexOf("/")+1)+infoUrl.substring(2,infoUrl.length());
					try {
						element.attr("src", infoUrl);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}	
			}
			Elements elementsHref = elementsInfo.select("a");
			if(elementsHref!=null){
				for (Element element : elementsHref) {
					String infoUrl = element.attr("href");
					infoUrl = url.substring(0,url.lastIndexOf("/")+1)+infoUrl.substring(2,infoUrl.length());
					try {
						element.attr("href", infoUrl);
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
		logger.info("抓取江苏省盐城市商务局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}

	/**
	 * 经信委
	 * http://jxw.yancheng.gov.cn/xwzx/tzgg/
	 */
	private Map<String, Object> grabJxwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document docUrl = Jsoup.connect("http://jxw.yancheng.gov.cn/xwzx/tzgg/").timeout(10000).get();
			Elements elementInfo = docUrl.select("table[style]").select("table[width]");
			Elements elements = elementInfo.get(1).select("tr");
			for (Element element : elements) {
				String infoUrl = element.select("a").attr("href");
				infoUrl = url+infoUrl.substring(2,infoUrl.length());
				if(infoUrl.equals(url)){
					model.put("title", element.select("a").text());
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		if(!this.isAtta(url)){
			//http://jxw.yancheng.gov.cn/xwzx/tzgg/201709/P020170905337565363721.docx
			model.put("isWebsite", "1");//是否网址 0_是 1_否
			model.put("downloadUrl", url);
			model.put("pubOrg", "");
			model.put("refNo", "");
			model.put("info", "");
		}else{
			try {
				Document doc = Jsoup.connect(url).timeout(10000).get();
				Elements elements = doc.select("div.TRS_Editor");
				model.put("pubOrg", "");
				model.put("refNo", "");
				Elements elementsA = elements.select("a");
				for (Element element : elementsA) {
					String attachmentUrl = element.attr("href");// 附件地址
					try {
						element.attr("href", attachmentUrl);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				elements = this.grabTidyTable(elements);
				//内容
				String info = elements.html();
				model.put("info", this.unescapeJava(info));
				
			} catch (Exception e) {
				System.err.println("ERROR:"+url);
				model.clear();
			}
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省盐城市经信委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 科技厅
	 * http://kjj.yancheng.gov.cn/xwzx/tzgg/
	 */
	private Map<String, Object> grabKjjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementsInfo = doc.select("div.TRS_Editor");
			Elements elementDate =doc.select("table[style]").get(3).select("td[align]");
			model.put("title", elementDate.get(1).text());
			String newsInfo = elementDate.get(2).text();
			String pubDate = newsInfo.substring(5, 15);
			model.put("pubDate", pubDate);
			model.put("pubOrg", "");
			model.put("refNo", "");
			Elements elementsA = elementsInfo.select("a");
			for (Element element : elementsA) {
				String urlDow = element.attr("href");
				String attachmentUrl = url.substring(0,url.lastIndexOf("/"))+urlDow.substring(1,urlDow.length());
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
			model.put("info", this.unescapeJava(info));
			
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省盐城市科技局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		GrabYc grabyc = new GrabYc();
//		grabyc.grabListFgw(list); // 集合
//		grabyc.grabListSwj(list);
		grabyc.grabListJxw(list);
//		grabyc.grabListKjt(list);
	}
}
