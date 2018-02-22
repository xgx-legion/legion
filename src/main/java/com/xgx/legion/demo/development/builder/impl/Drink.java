package com.xgx.legion.demo.development.builder.impl;

import com.xgx.legion.demo.development.builder.MealItems;

/**
 * 饮料抽象类
 * 
 * @class Drink.java
 * @author xinggx
 * @date 2018年2月22日
 */
public abstract class Drink implements MealItems {

	@Override
	public abstract String name();

	@Override
	public abstract float price();
}
