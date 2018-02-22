package com.xgx.legion.demo.development.builder.bean;

import com.xgx.legion.demo.development.builder.impl.Drink;

/**
 * 实体：可口可乐
 * @class CokeDrink.java 
 * @author xinggx
 * @date 2018年2月22日
 */
public class CokeDrink extends Drink {

	@Override
	public String name() {
		return "CokeDrink";
	}

	@Override
	public float price() {
		return 3.5f;
	}

}
