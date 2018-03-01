package com.xgx.legion.demo.development.adapter.interfaces.impl;

import com.xgx.legion.demo.development.adapter.IphoneChargeAdapter;
import com.xgx.legion.demo.development.adapter.interfaces.ChargeUnit110Handler;

public class IphoneChargeUnit implements ChargeUnit110Handler {

	@Override
	public void charge(int voltage) {
		if (voltage != 110) {
			IphoneChargeAdapter adapter = new IphoneChargeAdapter();
			adapter.charge(voltage);
			//System.out.println("voltage not supported!");
			return;
		}
		System.out.println("Iphone Charging...");
	}

}
