package com.xgx.legion.demo.development.singleton;

public class SingletonDemo {
	public static void main(String[] args) {
		// 获取单例的唯一可用对象
		SingletonObject object = SingletonObject.getInstance();

		object.setId("456");

		System.out.println(object.getId());
		System.out.println(object.getName());

		// 无法实例化
		// SingletonObject object2 = new SingletonObject();

	}
}
