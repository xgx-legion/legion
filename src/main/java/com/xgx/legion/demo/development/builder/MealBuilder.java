package com.xgx.legion.demo.development.builder;

import com.xgx.legion.demo.development.builder.bean.ChickenBurger;
import com.xgx.legion.demo.development.builder.bean.CokeDrink;
import com.xgx.legion.demo.development.builder.bean.Frenchfries;
import com.xgx.legion.demo.development.builder.bean.Meals;
import com.xgx.legion.demo.development.builder.bean.PepsiDrink;
import com.xgx.legion.demo.development.builder.bean.VegBurger;

/**
 * 套餐建造者
 * 
 * @class MealBuilder.java
 * @author xinggx
 * @date 2018年2月22日
 */
public class MealBuilder {
	
	/**
	 * 建造套餐A（素食汉堡+可口可乐）
	 * @return
	 */
	public Meals buildVegMeal(){
		Meals meal = new Meals();
		meal.setName("Veg Meal");
		meal.addItem(new VegBurger());
		meal.addItem(new CokeDrink());
		return meal;
	}
	
	/**
	 * 建造套餐B（鸡肉汉堡+百事可乐）
	 * @return
	 */
	public Meals buildChickenMeal(){
		Meals meal = new Meals();
		meal.setName("Chicken Meal");
		meal.addItem(new ChickenBurger());
		meal.addItem(new PepsiDrink());
		meal.addItem(new Frenchfries());
		return meal;
	}
}
