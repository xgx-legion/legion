package com.xgx.legion.demo.development.adapter;

import com.xgx.legion.demo.development.adapter.interfaces.ChargeUnit110Handler;
import com.xgx.legion.demo.development.adapter.interfaces.ChargeUnit220Handler;
import com.xgx.legion.demo.development.adapter.interfaces.impl.IphoneChargeUnit;
import com.xgx.legion.demo.development.adapter.interfaces.impl.NormalChargeUnit;

public class IphoneChargeAdapter {

	public void charge(int voltage) {
		if (voltage == 220) {
			ChargeUnit220Handler handler = new NormalChargeUnit();
			handler.charge(voltage);
			return;
		}
		if(voltage == 110){
			ChargeUnit110Handler handler = new IphoneChargeUnit();
			handler.charge(voltage);
			return;
		}
		System.out.println("voltage not supported!");
	}

}
