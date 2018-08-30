package com.yuyang.other.pachong.grab.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * Title: DateUtil.java
 * Package: com.rpc.base.utils
 * Description: 日期Util类
 * Company: 北京恒冠网络数据处理有限公司
 * @author liqiang
 * @date 2016年9月29日 上午11:30:35
 * @version V1.0
 */
public class DateUtil {
	/**
	 * 获取当前时间
	 * 数据库和应用服务器时间会同步，所以可以直接取系统时间
	 * 统一使用此方法获取当前时间以后调整的话方便维护
	 * @return
	 * @author liqiang
	 * @createTime 2016年10月13日 下午7:12:05
	 */
	public static Date currentDate() {
		return new Date();
	}
	/**
	 * 
	 * Description: 按照参数format的格式，日期转字符串
	 * @author liqiang
	 * @date 2016年10月14日 下午3:03:20
	 * @version V1.0
	 *
	 * @return
	 */
	public static String getShiftTime(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
	
	public static String getShiftTime(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	 * 
	 * Description: 按照参数format的格式，字符串转日期
	 * @author liqiang
	 * @date 2017年3月17日 上午10:42:40
	 * @version V1.0
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getShiftTime(String date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * Description: 时间戳转日期
	 * @author liqiang
	 * @date 2017年7月24日 上午11:10:45
	 * @version V1.0
	 *
	 * @param timestamp
	 * @param format
	 * @return
	 */
	public static Date getTimestamp(String timestamp) {
		long lt = new Long(timestamp);
		return new Date(lt);
	}
	
	/**
	 *  
	 * Description: 把时间根据时、分、秒转换为时间段
	 * @author liqiang
	 * @date 2016年9月29日 上午11:40:37
	 * @version V1.0
	 *
	 * @param StrDate
	 * @return
	 */
	public static String getTimes(Date date) {
		String resultTimes = "";

		Date now = new Date();
		long times = now.getTime() - date.getTime();
		long day = times / (24 * 60 * 60 * 1000);
		long hour = (times / (60 * 60 * 1000) - day * 24);
		long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

		StringBuffer sb = new StringBuffer();
		// sb.append("发表于：");
		if(day > 0) {
			sb.append(day + "天前");
		} else if (hour > 0) {
			sb.append(hour + "小时前");
		} else if (min > 0) {
			sb.append(min + "分钟前");
		} else {
			sb.append(sec + "秒前");
		}
		resultTimes = sb.toString();

		return resultTimes;
	}
	
	/**
	 *  
	 * Description: 把时间根据时、分、秒转换为时间段
	 * @author liqiang
	 * @date 2016年9月29日 上午11:40:37
	 * @version V1.0
	 *
	 * @param StrDate
	 * @return
	 */
	public static String getTimes(String strDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat(ConstantsDef.TIME_FORMAT_1);
		Date now;

		try {
			now = new Date();
			Date date = df.parse(strDate);
			long times = now.getTime() - date.getTime();
			long day = times / (24 * 60 * 60 * 1000);
			long hour = (times / (60 * 60 * 1000) - day * 24);
			long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			StringBuffer sb = new StringBuffer();
			// sb.append("发表于：");
			if(day > 0) {
				sb.append(day + "天前");
			} else if (hour > 0) {
				sb.append(hour + "小时前");
			} else if (min > 0) {
				sb.append(min + "分钟前");
			} else {
				sb.append(sec + "秒前");
			}

			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultTimes;
	}

	/**
	 * @Title: compareDate
	 * @Description: TO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws 
	 * @author liqiang
	 */
	public static boolean compareDate(String s, String e) {
		if (getShiftTime(s,ConstantsDef.TIME_FORMAT_1) == null || getShiftTime(e,ConstantsDef.TIME_FORMAT_1) == null) {
			return false;
		}
		return getShiftTime(s,ConstantsDef.TIME_FORMAT_1).getTime() >= getShiftTime(e,ConstantsDef.TIME_FORMAT_1).getTime();
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param daysInt
	 * @return date(Date类型)
	 */
	public static Date getAfterDayDate(Integer daysInt) {

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		return date;
	}

	/**
	 * 得到n天之后是周几
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}
	public static String getDayWeek(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);
		
		return dateStr;
	}
	
	
	
	
	/** 
     * 获得指定日期的前一天 
     *  
     * @param specifiedDay 
     * @return 
     * @throws Exception 
     */  
    public static String getSpecifiedDayBefore(String specifiedDay) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - 1);  
  
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c  
                .getTime());  
        return dayBefore;  
    }  
  
    /** 
     * 获得指定日期的后一天 
     *  
     * @param specifiedDay 
     * @return 
     */  
    public static String getSpecifiedDayAfter(String specifiedDay) {  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day + 1);  
  
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")  
                .format(c.getTime());  
        return dayAfter;  
    } 


	public static void main(String[] args) {
		
//		System.out.println(String.format("%tm", currentDate()));
		
		
//		System.out.println(getShiftTime(ConstantsDef.TIME_FORMAT_1));
		System.out.println(getTimes("2017-08-19 11:32:50"));
	}

}
