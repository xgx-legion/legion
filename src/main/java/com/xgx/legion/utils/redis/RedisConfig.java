package com.xgx.legion.utils.redis;

/**
 * Redis配置
 * 
 * @class RedisConfig.java
 * @author xinggx
 * @date 2017年9月26日
 */
public class RedisConfig {
	/*
	 * 可用连接实例的最大数，默认值为8 值为-1表示不限制
	 */
	public static int MAX_ACTIVE = 300;

	/*
	 * 允许最大的空闲实例数，默认值为8
	 */
	public static int MAX_IDLE = 100;

	/*
	 * 最大等待时间，单位毫秒，默认值-1（永不超时） 异常抛出：JedisConnectionException
	 */
	public static int MAX_WAIT = 10000;

	/*
	 * 重试次数
	 */
	public static int RETRY_NUM = 5;

	public static int TIME_OUT = 10000;
}
