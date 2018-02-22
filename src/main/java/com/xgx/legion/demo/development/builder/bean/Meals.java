package com.xgx.legion.demo.development.builder.bean;

import java.util.ArrayList;
import java.util.List;

import com.xgx.legion.demo.development.builder.MealItems;

/**
 * 实体：套餐（复杂对象）
 * 
 * @class Meals.java
 * @author xinggx
 * @date 2018年2月22日
 */
public class Meals {
	
	private String name;
	
	private List<MealItems> itemList = new ArrayList<MealItems>();

	/**
	 * 套餐名称
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	public void getName(){
		System.out.println(this.name);
	}
	
	/**
	 * 往套餐中加入实体食品
	 * 
	 * @param item
	 */
	public void addItem(MealItems item) {
		itemList.add(item);
	}

	/**
	 * 计算套餐总价格
	 * 
	 * @return
	 */
	public float getCost() {
		float cost = 0.0f;
		for (MealItems item : itemList) {
			cost += item.price();
		}
		return cost;
	}

	/**
	 * 打印套餐小票
	 */
	public void printTicket() {
		for(MealItems item : itemList){
			System.out.print("Item:" + item.name() + ";");
			System.out.println("Price:" + item.price() + "$");
		}
		System.out.println("Totle cost:" + getCost() + "$");
	}
}
