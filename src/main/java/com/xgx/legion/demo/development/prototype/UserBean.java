package com.xgx.legion.demo.development.prototype;

public class UserBean implements Cloneable {

	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * clone方法
	 */
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
	
	public String toString(){
		String json = "[{'id':"+ this.id +",'name':"+ this.name +"}]";
		return json;
	}

}
