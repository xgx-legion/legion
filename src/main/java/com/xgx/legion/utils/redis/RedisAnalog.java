package com.xgx.legion.utils.redis;

import redis.clients.jedis.Jedis;

/**
 * Redis模拟
 * 启动服务端redis-server.exe
 * 启动客户端redis-cli.exe -h ip port(6379)
 * @class RedisAnalog.java
 * @author xinggx
 * @date 2017年9月26日
 */
public class RedisAnalog {
	// redis服务器地址
	private static final String ip = "127.0.0.1";
	// redis服务器端口
	private static final int port = 6379;
	private static Jedis jedis = new Jedis();

	public static void main(String[] args) {
		jedis = JedisUtils.getInstance().getJedis(ip, port);
		RedisAnalog.set("test", "123abc");
		System.out.println(RedisAnalog.get("test"));
		RedisAnalog.set("test", "456abc");
		//RedisAnalog.delete("test");
		System.out.println(RedisAnalog.get("test"));
	}

	// get值
	public static String get(String key){
		return jedis.get(key);
	}
	
	// set键值对（覆盖）
	public static void set(String key, String value) {
		jedis.set(key, value);
	}
	
	// 判断是否存在key，存在则删除
	public static void delete(String key){
		if(jedis.exists(key)){
			jedis.del(key);
		}
	}

	// 清空数据
	public static String flushDB() throws Exception {
		return jedis.flushDB();
	}
}
