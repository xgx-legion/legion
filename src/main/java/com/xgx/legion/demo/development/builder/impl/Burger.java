package com.xgx.legion.demo.development.builder.impl;

import com.xgx.legion.demo.development.builder.MealItems;

/**
 * 汉堡抽象类
 * @class Burger.java 
 * @author xinggx
 * @date 2018年2月22日
 */
public abstract class Burger implements MealItems {

	@Override
	public abstract String name();

	@Override
	public abstract float price();

}
