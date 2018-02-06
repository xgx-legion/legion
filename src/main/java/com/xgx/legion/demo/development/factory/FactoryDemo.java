package com.xgx.legion.demo.development.factory;

import com.xgx.legion.demo.development.factory.impl.Circle;

public class FactoryDemo {
	public static void main(String[] args) {
		Circle circle = (Circle) ShapeFactory.getClass(Circle.class);
		System.out.println(circle.draw());
	}
}
