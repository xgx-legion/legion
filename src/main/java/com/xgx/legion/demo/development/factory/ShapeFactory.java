package com.xgx.legion.demo.development.factory;

public class ShapeFactory {
	public static Object getClass(Class<?extends ShapeHandler> clazz){
		// 利用反射机制获取接口的实现类对象
		Object obj = null;
		try {
			obj = Class.forName(clazz.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
