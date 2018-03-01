package com.xgx.legion.demo.development.adapter;

import com.xgx.legion.demo.development.adapter.interfaces.impl.IphoneChargeUnit;

public class AdapterDemo {
	public static void main(String[] args) {
		IphoneChargeUnit iphone = new IphoneChargeUnit();
		iphone.charge(220);
	}
}
