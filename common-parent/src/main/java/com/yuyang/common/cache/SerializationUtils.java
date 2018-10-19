package com.yuyang.common.cache;

import java.io.*;

/**
 * 用于调用session、cache等缓存服务时序列化对象， 改写自org.springframework.util.SerializationUtils，
 * 主要替换了deserialize方法，使用自定义的ObjectInputStream，解决反序列化时找不到类的问题
 *
 */
public class SerializationUtils {
	public static byte[] serialize(Object object) {
		if (object == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
		} catch (IOException ex) {
			throw new IllegalArgumentException("Failed to serialize object of type: " + object.getClass(), ex);
		}
		return baos.toByteArray();
	}

	public static Object deserialize(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			return ois.readObject();
		} catch (IOException ex) {
			throw new IllegalArgumentException("Failed to deserialize object", ex);
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Failed to deserialize object type", ex);
		}
	}

}
