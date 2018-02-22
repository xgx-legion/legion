package com.xgx.legion.demo.development.builder.bean;

import com.xgx.legion.demo.development.builder.impl.Drink;

/**
 * 实体：百事可乐
 * @class PepsiDrink.java 
 * @author xinggx
 * @date 2018年2月22日
 */
public class PepsiDrink extends Drink {

	@Override
	public String name() {
		return "PepsiDrink";
	}

	@Override
	public float price() {
		return 4.0f;
	}

}
