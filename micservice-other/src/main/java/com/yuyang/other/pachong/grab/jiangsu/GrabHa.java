package com.yuyang.other.pachong.grab.jiangsu;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.yuyang.other.pachong.grab.util.GrabTidyTableUtils;
import com.yuyang.other.pachong.grab.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Description: 抓取江苏省淮安市信息
 * @author nzq
 * @date 2017年9月9日 上午9:02:43
 * @version V1.0
 */

public class GrabHa extends GrabTidyTableUtils {
	private Log logger = LogFactory.getLog(GrabHa.class);
	
	/**
	 * 江苏省淮安市发改委
	 * http://fgw.huaian.gov.cn/tzgg/list.html
	 */
 	public List<Map<String, Object>> grabListFgw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://fgw.huaian.gov.cn/tzgg/list.html";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select(".lb").select("a");
 			for (Element element : elements) {
				String infoUrl = element.attr("href");
				infoUrl ="http://fgw.huaian.gov.cn/"+infoUrl;
				String pushDate = "";
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省淮安市发改委 信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省淮安市商务局
	 * http://boc.huaian.gov.cn/tzgg/list.html
	 */
 	public List<Map<String, Object>> grabListSwj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://boc.huaian.gov.cn/tzgg/list.html";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementInfo = doc.select(".lb_1").select("a");
			for (Element element : elementInfo) {
				String infoUrl = "http://boc.huaian.gov.cn/"+element.attr("href");
				String date = "20"+element.parent().nextElementSibling().text();
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,date);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省淮安市商务局信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	
 	/**
	 * 江苏省淮安市经信委
	 * http://jxw.huaian.gov.cn/web/jxw/54739/54745/54745.html
	 */
 	public List<Map<String, Object>> grabListJxw(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://jxw.huaian.gov.cn/web/jxw/54739/54745/54745.html";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementInfo = doc.select("a.textblack2");
			for (Element element : elementInfo) {
				String infoUrl = element.attr("href");
				infoUrl = "http://jxw.huaian.gov.cn"+infoUrl;
				String pushDate = element.parent().nextElementSibling().text();
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省淮安市经信委信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		return list;
	}
 	
 	/**
	 * 江苏省淮安市科技局
	 * http://kjj.huaian.gov.cn/tzgg/list.html
	 */
 	public List<Map<String, Object>> grabListKjj(List<Map<String, Object>> listParam) {
 		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://kjj.huaian.gov.cn/tzgg/list.html";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select(".lb_zw").select("a");
			for (Element element : elements) {
				String infoUrl = element.attr("href");
				String pushDate =element.previousElementSibling().text();
				if(!infoUrl.contains("http")){
					infoUrl = "http://kjj.huaian.gov.cn/"+infoUrl;
				}
				if(!this.listContains(listParam, "sourceUrl", infoUrl)) {
					list = this.grabNoti(infoUrl, list,pushDate);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省淮安市科技局信息列表数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(list)+"】");
		
		return list;
	}
 	/**抓取数据**/
	private List<Map<String, Object>> grabNoti(String url, List<Map<String, Object>> list,String pushDate) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sourceUrl", url);
		model.put("isWebsite", "0");//是否网址 0_是 1_否
		model.put("downloadUrl", "");
		model.put("pubDate", pushDate);
		model.put("addr", "江苏省淮安市");
		if(url.contains("http://fgw.huaian.gov.cn")) {
			model.put("source", "发改委");
			model.put("infoType", "通知公告");
			model = this.grabFgwInfo(url, model);
		} else if(url.contains("http://boc.huaian.gov.cn/")) {
			model.put("source", "商务局");
			model.put("infoType", "通知公告");
			model = this.grabSwjInfo(url, model);
		} else if(url.contains("http://jxw.huaian.gov.cn")) {
			model.put("source", "经信委");
			model.put("infoType", "通知公告");
			model = this.grabJxwInfo(url, model);
		} else if(url.contains("http://kjj.huaian.gov.cn")) {
			model.put("source", "科技局");
			model.put("infoType", "通知公告");
			model = this.grabKjjInfo(url, model);
		}else {
			model.clear();
		}
		if(model.size() > 0) {
			list.add(model);
		}
		return list;
	}
	
	/**
	 * 发改委
	 * http://fgw.huaian.gov.cn/tzgg/content/5e38cfb85ea8b5a6015eb7123fbb0a0e.html
	 */
	private Map<String, Object> grabFgwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementsInfo = doc.select(".artice");
			model.put("title", doc.select("h3").text());
			model.put("pubOrg", "");
			model.put("refNo", "");
			Elements elementsFbsj = doc.select(".fbsj");
			String fbsj = elementsFbsj.text().toString();
			fbsj = fbsj.substring(fbsj.indexOf("：")+1,fbsj.indexOf("字"));
			fbsj = fbsj.replace("　", "");
			model.put("pubDate", fbsj);
			Elements elementsA = elementsInfo.select("a");
			for (Element element : elementsA) {
				String urlDow = element.attr("href");
				if(!urlDow.isEmpty()){
					String attachmentUrl = "http://fgw.huaian.gov.cn/"+urlDow;
					try {
						if (this.isAtta(attachmentUrl)) {
							continue;
						}
						element.attr("href", attachmentUrl);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Elements elementsB = elementsInfo.select("img");
			for (Element element : elementsB) {
				String attachmentUrl = element.attr("src");// 附件地址
				if(!attachmentUrl.isEmpty()){
					String urlPath = "http://fgw.huaian.gov.cn/"+attachmentUrl;
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
		logger.info("抓取江苏省淮安市发改委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 江苏省淮安市商务局
	 * http://boc.huaian.gov.cn/tzgg/5e38cfb85e126f6c015e1852cc315e4e.html
	 */
	private Map<String, Object> grabSwjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elementsInfo = doc.select(".nr_centre");
			model.put("title", doc.select(".nr_title").text());
			model.put("pubOrg","");
			model.put("refNo", "");
			Elements elementsScript = elementsInfo.select("a");
			if(elementsScript!=null){
				for (Element element : elementsScript) {
					String infoUrl = element.attr("href");
					if(!infoUrl.isEmpty()){
						infoUrl ="http://boc.huaian.gov.cn/"+infoUrl;
						if(infoUrl.indexOf(".GD")>0){
							element.attr("href", infoUrl);
						}
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
			//内容
			String info = elementsInfo.html();
			model.put("info", this.unescapeJava(info));
			
		} catch (Exception e) {
			System.err.println("ERROR:"+url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省淮安市商务局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}

	/**
	 * 经信委
	 * http://jxw.huaian.gov.cn/web/jxw/2017/09/16/3663725.html
	 */
	private Map<String, Object> grabJxwInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
			try {
				Document doc = Jsoup.connect(url).timeout(10000).get();
				Elements elements = doc.select("td.style14");
				model.put("title",  doc.select("td.style6").text());
				model.put("pubOrg", "");
				model.put("refNo", "");
				Elements elementsA = elements.select("a");
				if(elementsA!=null){
					for (Element element : elementsA) {
						String infoUrl = element.attr("href");
						if(!infoUrl.isEmpty()){
							infoUrl ="http://jxw.huaian.gov.cn"+infoUrl;
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
							infoUrl ="http://jxw.huaian.gov.cn"+infoUrl; 
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
		logger.info("抓取江苏省淮安市经信委详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	
	/**
	 * 科技局
	 * 
	 */
	private Map<String, Object> grabKjjInfo(String url, Map<String, Object> model) {
		long start = System.currentTimeMillis(); 
			try {
				Document doc = Jsoup.connect(url).timeout(10000).get();
				Elements elementsInfo = doc.select(".nr_k");
				model.put("title", elementsInfo.select("h1").text());
				model.put("pubOrg", "");
				model.put("refNo", "");
				elementsInfo.select("h2").remove();
				elementsInfo.select("h1").remove();
				Elements elementsA = elementsInfo.select("a");
				for (Element element : elementsA) {
					String urlDow = element.attr("href");
					if(!urlDow.isEmpty()){
						String attachmentUrl ="http://kjj.huaian.gov.cn/"+urlDow;
						try {
							if (this.isAtta(attachmentUrl)) {
								continue;
							}
							element.attr("href", attachmentUrl);
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
		logger.info("抓取江苏省淮安市科技局详情数据 数据耗时："+(end-start)+"ms(毫秒), 数据结果【"+JsonUtils.toJson(model)+"】");
		return model;
	}
	public static void main(String[] args) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		GrabHa grabha = new GrabHa();
	 	grabha.grabListFgw(list); // 集合
//		grabha.grabListJxw(list);
//		grabha.grabListSwj(list);
//		grabha.grabListKjj(list);
	}
}
