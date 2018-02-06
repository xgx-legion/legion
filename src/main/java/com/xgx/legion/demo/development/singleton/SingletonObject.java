package com.xgx.legion.demo.development.singleton;

public class SingletonObject {
	private String id = "123";
	private String name = "test";

	public void setId(String id){
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	// 构造函数为private，这样该类就不会被其他对象实例化
	// private SingletonObject(String id) {
	// System.out.println(id);
	// }

	private SingletonObject() {
	}

	// 创建一个单例对象
	// private static SingletonObject instance = new SingletonObject("123");
	private static SingletonObject instance = new SingletonObject();

	// 提供获取实例的方法
	public static SingletonObject getInstance() {
		return instance;
	}
}
