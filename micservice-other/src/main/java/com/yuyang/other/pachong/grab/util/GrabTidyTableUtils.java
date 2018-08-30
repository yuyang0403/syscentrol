package com.yuyang.other.pachong.grab.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Title: GrabTidyTableUtils.java
 * Package: com.rpc.base.utils.grab
 * Description: 抓取整理表格数据
 * Company: 北京恒冠网络数据处理有限公司
 * @author liqiang
 * @date 2017年8月22日 上午8:30:42
 * @version V1.0
 */

public class GrabTidyTableUtils {
	
	protected String[] prefixs = {".pdf",".zip",".rar",".7z",".doc",".docx",".xlsx",".xls",".png",".jpg",".jpeg",".gif"};
	
	/**p table**/
	protected Elements grabTidyTable(Elements elementsInfo) {
		Elements elementsP = elementsInfo.select("p");
		for (Element element : elementsP) {
			element.attr("class", "").attr("style","");
		}
		
		Elements elementsTable = elementsInfo.select("table");
		for (Element element : elementsTable) {
			element.attr("width","100%").attr("class", "").attr("style","");
			Elements elementsTr = element.select("tr");
			for (Element tr : elementsTr) {
				tr.attr("width","").attr("class", "").attr("style","");
			}
			Elements elementsTd = element.select("td");
			for (Element td : elementsTd) {
				td.attr("width","").attr("class", "").attr("style","").attr("nowrap","");
			}
		}
		
		return elementsInfo;
	}
	
	protected boolean isAtta(String fileName) {
		String prefix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();// 获取文件后缀名称
		boolean b = false;
		if (prefixs.length > 0) {
			if (!isExistToArry(prefixs, prefix)) {
				b = true;
			}
		}
		return b;
	}
	
	// 查询是否存在数组中
	protected boolean isExistToArry(String[] arr, String targetValue) {
		return Arrays.asList(arr).contains(targetValue);
	}
	
	/**
	 * 
	 * Description: 验证 list中是否包含 map 中的key
	 * @author liqiang
	 * @date 2017年7月17日 下午2:21:10
	 * @version V1.0
	 *
	 * @param list
	 * @return
	 */
	protected Boolean listContains(List<Map<String, Object>> list, String key) {
		boolean contains = false;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if(map.get(key) != null) {
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	/**
	 * 
	 * Description: 验证 list中是否包含 map 中的key值
	 * @author liqiang
	 * @date 2017年7月17日 下午2:21:10
	 * @version V1.0
	 *
	 * @param list
	 * @return
	 */
	public Boolean listContains(List<Map<String, Object>> list, String key, String value) {
		boolean contains = false;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if(map.get(key) != null && value.equals(map.get(key).toString())) {
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	/**反编译的内容**/
	protected String unescapeJava(String content){
		if(notEmpty(content)) {
			content = StringEscapeUtils.unescapeJava(content);
		}
		return content;
	}
	
	protected boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}
}
