package com.xgx.legion.demo.development.builder.bean;

import com.xgx.legion.demo.development.builder.impl.Snacks;

/**
 * 实体：薯条
 * @class Frenchfries.java 
 * @author xinggx
 * @date 2018年2月22日
 */
public class Frenchfries extends Snacks {

	@Override
	public String name() {
		return "French fries";
	}

	@Override
	public float price() {
		return 9.0f;
	}

}
