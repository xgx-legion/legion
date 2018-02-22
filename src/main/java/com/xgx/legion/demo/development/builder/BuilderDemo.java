package com.xgx.legion.demo.development.builder;

import com.xgx.legion.demo.development.builder.bean.Meals;

public class BuilderDemo {
	private static void getMeal(Meals meal){	
		meal.getName();
		meal.printTicket();
	}
	
	public static void main(String[] args) {
		MealBuilder mealBuilder = new MealBuilder();
		Meals vegMeal = mealBuilder.buildVegMeal();
		Meals chickenMeal = mealBuilder.buildChickenMeal();
		getMeal(vegMeal);
		System.out.println();
		getMeal(chickenMeal);
	}
}
