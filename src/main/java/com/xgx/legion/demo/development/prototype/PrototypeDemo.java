package com.xgx.legion.demo.development.prototype;

public class PrototypeDemo {
	public static void main(String[] args) {
		UserBean user = new UserBean();
		user.setId("001");
		user.setName("admin");
		System.out.println("source bean---->" + user.toString());

		UserBean cloneUser = (UserBean) user.clone();
		cloneUser.setName("admin2");
		System.out.println("clone bean---->" + cloneUser.toString());

	}
}
