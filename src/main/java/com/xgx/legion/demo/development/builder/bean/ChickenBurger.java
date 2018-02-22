package com.xgx.legion.demo.development.builder.bean;

import com.xgx.legion.demo.development.builder.impl.Burger;

/**
 * 实体：鸡肉汉堡
 * @class ChickenBurger.java 
 * @author xinggx
 * @date 2018年2月22日
 */
public class ChickenBurger extends Burger {

	@Override
	public String name() {
		return "ChickenBurger";
	}

	@Override
	public float price() {
		return 20.5f;
	}

}
