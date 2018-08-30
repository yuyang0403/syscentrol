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
 * Description: 抓取江苏省镇江市信息
 * @author nzq
 * @date 2017年9月9日 上午9:02:43
 * @version V1.0
 */

public class GrabZj extends GrabTidyTableUtils {
	private Log logger = LogFactory.getLog(GrabZj.class);
	
	/**
	 * 江苏省镇江市发改委
	 * http://fgw.zhenjiang.gov.cn/tzgg_1/
	 */
 	public List<Map<String, Object>> grabListFgw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://fgw.zhenjiang.gov.cn/tzgg_1/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("table[align]").get(1).select("tr").select("a");
 			for (Element element : elements) {
				String infoUrl = element.attr("href");
				infoUrl =url+infoUrl.substring(2,infoUrl.length());
				String pushDate = element.parent().nextElementSibling().text();
				pushDate = pushDate.substring(1,pushDate.length()-1);
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pushDate,"");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省镇江市发改委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省镇江市商务局
	 * http://www.zjftec.gov.cn/html/notice/
	 */
 	public List<Map<String, Object>> grabListSwj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://www.zjftec.gov.cn/html/notice/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementInfo = doc.select(".list").select("p a");
			for (Element element : elementInfo) {
				String infoUrl = element.text();
				String date = element.previousElementSibling().text();
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,date,"");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省镇江市商务局信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省镇江市经信委
	 * http://jxw.yancheng.gov.cn/xwzx/tzgg/
	 */
 	public List<Map<String, Object>> grabListJxw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://jxw.zhenjiang.gov.cn/nzwgk/gsgg/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementInfo = doc.select("table[align]").select("a[title]");
			for (Element element : elementInfo) {
				String infoUrl = element.attr("href");
				String pushDate = element.parent().nextElementSibling().text();
				infoUrl = url+infoUrl.substring(2,infoUrl.length());
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pushDate,"");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省镇江市经信委信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		return list;
	}
 	
 	/**
	 * 江苏省镇江市科技局
	 * http://kjj.zhenjiang.gov.cn/zwgk/tzgg/
	 */
 	public List<Map<String, Object>> grabListKjj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://kjj.zhenjiang.gov.cn/zwgk/tzgg/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select(".lb_gjlb").select("a");
			for (Element element : elements) {
				String infoUrl = element.attr("href");
				String pushDate =element.parent().nextElementSibling().text();
				pushDate=pushDate.replace(" ", "");
				if(!infoUrl.contains("http")){
					infoUrl = infoUrl.replace("./", "");
					infoUrl = url+infoUrl;
				}
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					pushDate = pushDate.substring(1,pushDate.length()-1);
					list = this.grabNoti(infoUrl, list,pushDate,url);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省镇江市科技局信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	/**
	 * 江苏省镇江市知识产权局
	 *  http://zscqj.zhenjiang.gov.cn/zwgk/gzgg/
	 */
 	public List<Map<String, Object>> grabListZscqj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://zscqj.zhenjiang.gov.cn/zwgk/gzgg/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementInfo = doc.select("table[align]").select("a[title]");
			for (Element element : elementInfo) {
				String infoUrl = element.attr("href");
				String pushDate = element.parent().nextElementSibling().text();
				pushDate = pushDate.substring(1,pushDate.length()-1);
				infoUrl = url+infoUrl.substring(2,infoUrl.length());
				String title = element.attr("title");
				if (this.isAtta(infoUrl)) {
					if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
						list = this.grabNoti(infoUrl, list,pushDate,"0");
					}
				}else{
					if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
						list = this.grabNoti(infoUrl, list,pushDate,title);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省镇江市经信委信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		return list;
	}
 	/**抓取数据**/
	private List<Map<String, Object>> grabNoti(String url, List<Map<String, Object>> list,String pushDate,String urlType) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sourceUrl", url);
		model.put("isWebsite", "0");//是否网址 0_是 1_否
		model.put("downloadUrl", "");
		model.put("pubDate", pushDate);
		model.put("addr", "江苏省镇江市");
		if(url.contains("http://fgw.zhenjiang.gov.cn/tzgg_1/")) {
			model.put("source", "发改委");
			model.put("infoType", "通知公告");
			model = this.grabFgwInfo(url, model);
		} else if(url.contains("http://www.zjftec.gov.cn/")) {
			model.put("source", "商务局");
			model.put("infoType", "通知公告");
			model = this.grabSwjInfo(url, model);
		} else if(url.contains("http://jxw.zhenjiang.gov.cn/nzwgk/gsgg/")) {
			model.put("source", "经信委");
			model.put("infoType", "通知公告");
			model = this.grabJxwInfo(url, model);
		} else if(urlType.contains("http://kjj.zhenjiang.gov.cn/zwgk/tzgg/")) {
			model.put("source", "科技局");
			model.put("infoType", "通知公告");
			model = this.grabKjjInfo(url, model);
		}else if(url.contains("http://zscqj.zhenjiang.gov.cn/zwgk/gzgg/")) {
			model.put("source", "知识产权局");
			model.put("infoType", "通知公告");
			if(urlType.equals("0")){
				model = this.grabZscqjInfo(url, model);
			}else{
				model.put("title", urlType);
				model.put("isWebsite", "1");//是否网址 0_是 1_否
				model.put("downloadUrl", url);
				model.put("pubOrg", "");
				model.put("refNo", "");
				model.put("info", "");
			}
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
			Elements elementsInfo = doc.select("table[style]").get(3).select("div");
			Elements titles = doc.select("table[style]").get(2).select("td[style]");
			model.put("title", titles.text());
			model.put("pubOrg", "");
			model.put("refNo", "");
			Elements elementsA = elementsInfo.select("a");
			for (Element element : elementsA) {
				String urlDow = element.attr("href");
				if(!urlDow.isEmpty()){
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
		logger.info("抓取江苏省镇江市发改委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 江苏省镇江市商务局
	 * http://swj.yancheng.gov.cn/xwzx/tzgg/201709/t20170913_667724.html
	 */
	private Map<String, Object> grabSwjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementsInfo = doc.select(".content");
			model.put("title", doc.select(".title").text());
			model.put("pubOrg","");
			model.put("refNo", "");
			Elements elementsScript = elementsInfo.select("a");
			if(elementsScript!=null){
				for (Element element : elementsScript) {
					String infoUrl = element.attr("href");
					if(!infoUrl.isEmpty()){
						infoUrl ="http://www.zjftec.gov.cn"+infoUrl;
						try {
							element.attr("href", infoUrl);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}	
			}
			//内容
			String info = elementsInfo.html();
			model.put("info", this.unescapeJava(info));
			
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省镇江市商务局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}

	/**
	 * 经信委
	 * http://jxw.zhenjiang.gov.cn/nzwgk/gsgg/
	 */
	private Map<String, Object> grabJxwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
			try {
				Document doc = Jsoup.connect(url).timeout(10000).get();
				Elements elements = doc.select("div[id]");
				elements.remove(0);
				Elements titles = doc.select("td[style]");
				model.put("title", titles.get(1).text());
				model.put("pubOrg", "");
				model.put("refNo", "");
				Elements elementsA = elements.select("a");
				if(elementsA!=null){
					for (Element element : elementsA) {
						String infoUrl = element.attr("href");
						if(infoUrl.contains("C:")){
							continue;
						}
						if(!infoUrl.isEmpty()){
							infoUrl =url.substring(0,url.lastIndexOf("/"))+infoUrl.substring(1,infoUrl.length());
							try {
								if (this.isAtta(infoUrl)) {
									continue;
								}
								element.attr("href", infoUrl);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				Elements elementsB = elements.select("img");
				if(elementsA!=null){
					for (Element element : elementsB) {
						String infoUrl = element.attr("src");
						if(!infoUrl.isEmpty()){
							infoUrl =url.substring(0,url.lastIndexOf("/"))+infoUrl.substring(1,infoUrl.length());
							try {
								if (this.isAtta(infoUrl)) {
									continue;
								}
								element.attr("src", infoUrl);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
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
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省镇江市经信委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 科技局
	 * http://kjj.zhenjiang.gov.cn/zwgk/tzgg/
	 */
	private Map<String, Object> grabKjjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		if(url.contains("http://kjj.zhenjiang.gov.cn")){
			try {
				Document doc = Jsoup.connect(url).timeout(10000).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").get();
				Elements elementsInfo = doc.select(".Custom_UnionStyle");
				if(elementsInfo.isEmpty()){
					elementsInfo = doc.select(".gjzw");
				}
				Elements elementDate =doc.select("tbody").select("table[border]").select("td[valign]");
				model.put("title", elementDate.get(0).text());
				model.put("pubOrg", "");
				model.put("refNo", "");
				Elements elementsA = elementsInfo.select("a");
				for (Element element : elementsA) {
					String urlDow = element.attr("href");
					if(!urlDow.isEmpty()){
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
				}
				elementsInfo = this.grabTidyTable(elementsInfo);
				//内容
				String info = elementsInfo.html();
				model.put("info", this.unescapeJava(info));
				
			} catch (Exception e) {
				System.err.println("ERROR:"+url);
				model.clear();
			}
		}else if(url.contains("http://www.zhenjiang.gov.cn/")){
			try {
				Document doc = Jsoup.connect(url).timeout(10000).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").get();
				Elements elementsInfo = doc.select("div[id]");
				model.put("title", doc.select(".gjzwtit").text());
				model.put("pubOrg", "");
				model.put("refNo","");
				Elements elementsA = elementsInfo.select("a");
				for (Element element : elementsA) {
					String urlDow = element.attr("href");
					if(!urlDow.isEmpty()&&!urlDow.contains("http")){
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
				}
				elementsInfo = this.grabTidyTable(elementsInfo);
				//内容
				String info = elementsInfo.html();
				model.put("info", this.unescapeJava(info));
				
			} catch (Exception e) {
				System.err.println("ERROR:"+url);
				model.clear();
			}
		}else if(url.contains("http://xxgk.zhenjiang.gov.cn")){
			try {
				Document doc = Jsoup.connect(url).timeout(10000).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").get();
				Elements elementsInfo = doc.select("table[align]");
				Elements titles  =  elementsInfo.select("td[style]");
				model.put("title",titles.select("div[style]").text());
				model.put("pubOrg", "");
				model.put("refNo","");
				Elements elementsA = elementsInfo.get(6).select("td");
				Elements elementsB = elementsA.select("a");
				for (Element element : elementsB) {
					String urlDow = element.attr("href");
					if(!urlDow.isEmpty()){
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
				}
				elementsA = this.grabTidyTable(elementsA);
				//内容
				String info = elementsA.html();
				model.put("info", this.unescapeJava(info));
				
			} catch (Exception e) {
				System.err.println("ERROR:"+url);
				model.clear();
			}
		}
		
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省镇江市科技局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	/**
	 * 知识产权局
	 *  http://zscqj.zhenjiang.gov.cn/zwgk/gzgg/
	 */
	private Map<String, Object> grabZscqjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
			try {
				Document doc = Jsoup.connect(url).timeout(10000).get();
				Elements elements = doc.select("div[id]");
				Elements titles = doc.select("td[style]");
				model.put("title", titles.get(1).text());
				model.put("pubOrg", "");
				model.put("refNo", "");
				Elements elementsA = elements.select("a");
				if(elementsA!=null){
					for (Element element : elementsA) {
						String infoUrl = element.attr("href");
						if(!infoUrl.isEmpty()){
							infoUrl =url.substring(0,url.lastIndexOf("/"))+infoUrl.substring(1,infoUrl.length());
							try {
								if (this.isAtta(infoUrl)) {
									continue;
								}
								element.attr("href", infoUrl);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
				Elements elementsB = elements.select("img");
				if(elementsA!=null){
					for (Element element : elementsB) {
						String infoUrl = element.attr("src");
						if(!infoUrl.isEmpty()){
							infoUrl =url.substring(0,url.lastIndexOf("/"))+infoUrl.substring(1,infoUrl.length());
							try {
								if (this.isAtta(infoUrl)) {
									continue;
								}
								element.attr("src", infoUrl);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
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
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省镇江市经信委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	public static void main(String[] args) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		GrabZj grabzj = new GrabZj();
	 	grabzj.grabListFgw(list); // 集合
//		grabzj.grabListSwj(list);
//		grabzj.grabListJxw(list);
//		grabzj.grabListKjj(list);
//		grabzj.grabListZscqj(list);
	}
}
