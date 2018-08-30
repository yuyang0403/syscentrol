package com.yuyang.other.pachong.grab.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;


public class Tools {
	
	/**
	 * 读取txt内容
	 * @param file
	 * @return
	 */
    public static String readTxtFile(File file){
    	StringBuffer sbstr = new StringBuffer();
        try {
            String encoding="gb2312";
//            String encoding="utf-8";
            
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = "";
			while((lineTxt = bufferedReader.readLine()) != null){
				sbstr.append(lineTxt);
            }
            read.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return sbstr.toString();
     
    }
    
    
    /**
	 * 读取txt内容
	 * @param file
	 * @return
	 */
    public static String readTxtExcelFile(File file){
    	StringBuffer sbstr = new StringBuffer();
        try {
            String encoding="gb2312";
//            String encoding="utf-8";
            
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = "";
			while((lineTxt = bufferedReader.readLine()) != null){
				sbstr.append(lineTxt);
            }
            read.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return sbstr.toString();
     
    }
    
    public static boolean writeTxtFile(String content,File file) {  

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(file);
			o.write(content.getBytes("utf-8"));
//			o.write(content.getBytes("gb2312"));
			o.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mm != null) {
				try {
					mm.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}
