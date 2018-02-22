package com.xgx.legion.demo.development.builder.bean;

import com.xgx.legion.demo.development.builder.impl.Burger;

/**
 * 实体：素食汉堡
 * @class VegBurger.java 
 * @author xinggx
 * @date 2018年2月22日
 */
public class VegBurger extends Burger {

	@Override
	public String name() {
		return "VegBurger";
	}

	@Override
	public float price() {
		return 18.0f;
	}

}
