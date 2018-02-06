package com.xgx.legion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShortLink {
	private static final String appkey = "2568809760";
	private static final String appSecret = "3eb2076efdaf7d7d925e535669d90dab";
	private static final String redirectURL = "http://192.168.24.84";
	
	public static void main(String[] args) {
		/*
		 * URL:https://api.weibo.com/2/short_url/shorten.json
		 * HTTP:GET
		 * Param:access_token(String/OAuth);url_long(String/20 max/URLencoded/url_long=aaa&url_long=bbb)
		 * appkey:2568809760
		 * appSecret:3eb2076efdaf7d7d925e535669d90dab
		 */
		// 短连接API地址
		String api = "https://api.weibo.com/2/short_url/shorten.json";
		// 获取code
		String codeApi = "https://api.weibo.com/oauth2/authorize?client_id=" + appkey + "&response_type=code&redirect_uri=" + redirectURL;
		String codeUrlResponse = "";
		try {
			URL codeUrl = new URL(codeApi);
			HttpURLConnection connection = (HttpURLConnection) codeUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// 换取access_token
		String tokenApi = "https://api.weibo.com/oauth2/access_token";
		
		
		
		
		
		//https://api.weibo.com/oauth2/authorize?client_id=2568809760&response_type=code&redirect_uri=http://192.168.24.84
	}
}
