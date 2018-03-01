package com.xgx.legion.demo.development.adapter.interfaces.impl;

import com.xgx.legion.demo.development.adapter.interfaces.ChargeUnit220Handler;

public class NormalChargeUnit implements ChargeUnit220Handler {

	@Override
	public void charge(int voltage) {
		if (voltage != 220) {
			System.out.println("voltage not supported!");
			return;
		}
		System.out.println("Charging...");
	}

}
