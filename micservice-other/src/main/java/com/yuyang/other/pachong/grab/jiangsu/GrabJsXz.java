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
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Description: 抓取江苏省徐州市 信息
 * 
 * @author nzq
 * @date 2017年9月9日 上午9:02:43
 * @version V1.0
 */

public class GrabJsXz extends GrabTidyTableUtils {
	private Log logger = LogFactory.getLog(GrabJsXz.class);

	/**
	 * 徐州市 科技局 http://www.xsti.net/index.php?m=content&c=index&a=lists&catid=11
	 */
	public List<Map<String, Object>> grabListKjj(
			List<Map<String, Object>> listParam) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://www.xsti.net/index.php?m=content&c=index&a=lists&catid=11";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("ul.tpl2_list.mb10").select("li")
					.select("span.fl").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				if (!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.parent().nextElementSibling()
							.children().text();
					list = this.grabNoti(infoUrl, list, pushDate, "kjj");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取徐州市  科技局 信息列表数据耗时：" + (end - start) + "ms(毫秒), 数据结果【"
				+ JsonUtils.toJson(list) + "】");

		return list;
	}

	/**
	 * 江苏省徐州市 经信委
	 *  http://www.xzeic.gov.cn/active.aspx?category_id=222
	 */
	public List<Map<String, Object>> grabListJxw(
			List<Map<String, Object>> listParam) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://www.xzeic.gov.cn/active.aspx?category_id=222";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("div.ny_list").select("li").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				if (infoUrl.contains(".aspx")) {
					infoUrl = "http://www.xzeic.gov.cn"	+ infoUrl;
				} else {
					continue;
				}
				if (!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.nextElementSibling().text();
					list = this.grabNoti(infoUrl, list, pushDate,null);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省徐州市  经信委 信息列表数据耗时：" + (end - start) + "ms(毫秒), 数据结果【"
				+ JsonUtils.toJson(list) + "】");

		return list;
	}

	/**
	 * 江苏省徐州市 商务局 http://swj.xz.gov.cn/swj/tzgg/
	 */
	public List<Map<String, Object>> grabListSwj(
			List<Map<String, Object>> listParam) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://swj.xz.gov.cn/swj/tzgg/";
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			Elements elements = doc.select("td.moreinfoTitle").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				if (infoUrl.contains(".htm")) {
					infoUrl = "http://swj.xz.gov.cn"
							+ infoUrl;
				} else {
					continue;
				}
				if (!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.parent().nextElementSibling().text();
					list = this.grabNoti(infoUrl, list, pushDate,null);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省徐州市 商务局 信息列表数据耗时：" + (end - start) + "ms(毫秒), 数据结果【"
				+ JsonUtils.toJson(list) + "】");

		return list;
	}

	/**
	 * 江苏省徐州市 发改委http://dpc.xz.gov.cn/fgw/ywgz/004021/
	 */
	public List<Map<String, Object>> grabListFgw(
			List<Map<String, Object>> listParam) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		long start = System.currentTimeMillis();
		String url = "http://dpc.xz.gov.cn/fgw/ywgz/004021/";
		try {
			Document doc = Jsoup
					.connect(url)
					.get();
			Elements elements = doc.getElementById("categorypagingcontent").select("table").select("a");
			for (Element el : elements) {
				String infoUrl = el.attr("href");
				if (infoUrl.contains(".htm")) {
					infoUrl = "http://dpc.xz.gov.cn" + infoUrl;
				} else {
					continue;
				}
				if (!listContains(listParam, "sourceUrl", infoUrl)) {
					String pushDate = el.parent().nextElementSibling().text();
					list = this.grabNoti(infoUrl, list, pushDate,null);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取江苏省徐州市 发改委 信息列表数据耗时：" + (end - start) + "ms(毫秒), 数据结果【"
				+ JsonUtils.toJson(list) + "】");

		return list;
	}

	/** 抓取数据 **/
	private List<Map<String, Object>> grabNoti(String url,
			List<Map<String, Object>> list, String pushDate, String type) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sourceUrl", url);
		model.put("isWebsite", "0");// 是否网址 0_是 1_否
		model.put("downloadUrl", "");
		model.put("addr", "江苏省徐州市");
		if ("kjj".equals(type)) {
			model.put("source", "科技局");
			model.put("pubDate", pushDate);
			model.put("infoType", "政策法规");
			model = this.grabKjjInfo(url, model);
		} else if (url.contains("http://www.xzeic.gov.cn")) {
			model.put("source", "经信委");
			model.put("pubDate", pushDate);
			model.put("infoType", "公告动态");
			model = this.grabJxwInfo(url, model);
		} else if (url.contains("http://swj.xz.gov.cn")) {
			model.put("source", "商务局");
			model.put("infoType", "通知公告");
			model.put("pubDate", pushDate);
			model = this.grabSwjInfo(url, model);
		} else if (url.contains("http://dpc.xz.gov.cn")) {
			model.put("source", "发改委");
			model.put("infoType", "通知公告");
			model.put("pubDate", pushDate);
			model = this.grabFgwInfo(url, model);
		} else {
			model.clear();
		}
		if (model.size() > 0) {
			list.add(model);
		}
		return list;
	}

	/**
	 * 科技局
	 */
	private Map<String, Object> grabKjjInfo(String url,
			Map<String, Object> model) {
		long start = System.currentTimeMillis();
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String title = doc.select("h2.mb10").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("cont");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if (!attachmentUrl.contains("uploadfile")) {
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
				if (!attachmentUrl.contains("www")) {
					continue;
				}
				String filePath = attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("src", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
		} catch (Exception e) {
			System.err.println("ERROR:" + url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取 江苏省徐州市 科技局详情数据 数据耗时：" + (end - start) + "ms(毫秒), 数据结果【"
				+ JsonUtils.toJson(model) + "】");
		return model;
	}

	/**
	 * 经信委
	 */
	private Map<String, Object> grabJxwInfo(String url,
			Map<String, Object> model) {
		long start = System.currentTimeMillis();
		try {
			Document doc = Jsoup.connect(url).timeout(10000).get();
			String title = doc.select("title").text();
			model.put("title", title.substring(0, title.indexOf("_")));
			Elements elementsInfo = doc.select("div.ny_m");
			elementsInfo.select("div.pos").remove();
			elementsInfo.select("h1").remove();
			elementsInfo.select("h3").remove();
			Elements elementsA = elementsInfo.select("a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if (attachmentUrl.contains("aspx")) {
					attachmentUrl = "http://www.xzeic.gov.cn"+ attachmentUrl;
				} else {
					continue;
				}
				String filePath = attachmentUrl;
				try {
					element.attr("href", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Elements elementsImg = elementsInfo.select("p img");
			for (Element element : elementsImg) {
				String attachmentUrl = element.attr("src");// 图片地址
				String filePath = url.substring(0, url.lastIndexOf("/")) + "/"
						+ attachmentUrl;
				try {
					element.attr("src", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
		} catch (Exception e) {
			System.err.println("ERROR:" + url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取 江苏省徐州市 经信委详情数据 数据耗时：" + (end - start) + "ms(毫秒), 数据结果【"
				+ JsonUtils.toJson(model) + "】");
		return model;
	}

	/**
	 * 商务局
	 */
	private Map<String, Object> grabSwjInfo(String url,
			Map<String, Object> model) {
		long start = System.currentTimeMillis();
		try {
	        Document doc = Jsoup.parse(new URL(url).openStream(), "GBK", url);
			String title = doc.select("font.infotitle").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("TDContent");
			Elements elementsA = elementsInfo.select("p a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if (attachmentUrl.contains("files")) {
					attachmentUrl = url.substring(0, url.lastIndexOf("/"))
							+ "/" + attachmentUrl;
				} else {
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
				String filePath = url.substring(0, url.lastIndexOf("/")) + "/"
						+ attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("src", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
		} catch (Exception e) {
			System.err.println("ERROR:" + url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取 江苏省徐州市 商务局详情数据 数据耗时：" + (end - start) + "ms(毫秒), 数据结果【"
				+ JsonUtils.toJson(model) + "】");
		return model;
	}

	/**
	 * 发改委
	 */
	private Map<String, Object> grabFgwInfo(String url,
			Map<String, Object> model) {
		long start = System.currentTimeMillis();
		try {
			Document doc = Jsoup
					.connect(url)
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")
					.get();
			String title = doc.getElementById("tdTitle").select("font.infotitle").text();
			model.put("title", title);
			Element elementsInfo = doc.getElementById("TDContent");
			Elements elementsA = elementsInfo.select("a");
			for (Element element : elementsA) {
				String attachmentUrl = element.attr("href");// 附件地址
				if (attachmentUrl.contains("UploadFile")) {
					attachmentUrl = "http://dpc.xz.gov.cn" + attachmentUrl;
				} else {
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
				String filePath = "http://dpc.xz.gov.cn" + attachmentUrl;
				try {
					if (this.isAtta(attachmentUrl)) {
						continue;
					}
					element.attr("src", filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 内容
			String info = elementsInfo.html();
			model.put("info", unescapeJava(info));
		} catch (Exception e) {
			System.err.println("ERROR:" + url);
			model.clear();
		}
		long end = System.currentTimeMillis();
		logger.info("抓取 江苏省徐州市发改委详情数据 数据耗时：" + (end - start) + "ms(毫秒), 数据结果【"
				+ JsonUtils.toJson(model) + "】");
		return model;
	}

	public static void main(String[] args) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		GrabJsXz grabJsNj = new GrabJsXz();
		// list = grabJsNj.grabListKjj(list); // 集合
//		list = grabJsNj.grabListJxw(list);
//		 list = grabJsNj.grabListSwj(list);
		 list = grabJsNj.grabListFgw(list);
	}
}
