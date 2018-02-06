package com.xgx.legion.utils.redis;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
	private static final Log log = LogFactory.getLog(JedisUtils.class);

	private JedisUtils() {
	};

	private static class RedisHandler {
		private static final JedisUtils instance = new JedisUtils();
	}

	public static JedisUtils getInstance() {
		return RedisHandler.instance;
	}

	private static Map<String, JedisPool> poolmap = new HashMap<String, JedisPool>();

	/**
	 * 构建jedis连接池
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	private static JedisPool getPool(String ip, int port) {
		String key = ip + ":" + port;
		JedisPool pool = null;
		if (!poolmap.containsKey(key)) {
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(RedisConfig.MAX_ACTIVE);
			poolConfig.setMaxIdle(RedisConfig.MAX_IDLE);
			poolConfig.setMaxWaitMillis(RedisConfig.MAX_WAIT);
			poolConfig.setTestOnBorrow(true);
			poolConfig.setTestOnReturn(true);

			pool = new JedisPool(poolConfig, ip, port, RedisConfig.TIME_OUT);
			poolmap.put(key, pool);
		} else {
			pool = poolmap.get(key);
		}
		return pool;
	}

	/**
	 * 获取Jedis
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	public Jedis getJedis(String ip, int port) {
		Jedis jedis = null;
		int count = 0;
		do {
			try {
				jedis = getPool(ip, port).getResource();
			} catch (Exception e) {
				log.error("获取jedis失败！", e);
				closeJedis(jedis, ip, port);
			}
		} while (jedis == null && count < RedisConfig.RETRY_NUM);

		return jedis;
	}

	/**
	 * 关闭Jedis
	 * 
	 * @param jedis
	 * @param ip
	 * @param port
	 */
	public void closeJedis(Jedis jedis, String ip, int port) {
		if (jedis != null) {
			jedis.close();
		}
	}
}
