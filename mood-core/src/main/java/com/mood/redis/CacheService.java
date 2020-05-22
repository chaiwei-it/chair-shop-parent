package com.mood.redis;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface CacheService<K, V> {

	/**
	 * fetch data by rule id
	 *
	 * @param key rule id
	 * @return Result<XxxxDO>
	 */
	V get(K key);

	/**
	 * fetch data by rule id
	 *
	 * @param key rule id
	 * @param member rule id
	 * @param exp rule id
	 * @return Result<XxxxDO>
	 */
	void set(K key, V member, int exp);

	/**
	 * fetch data by rule id
	 *
	 * @param key rule id
	 * @return Result<XxxxDO>
	 */
	boolean exists(K key);

	/**
	 * fetch data by rule id
	 *
	 * @param key rule id
	 * @return Result<XxxxDO>
	 */
	void del(K key);

	/**
	 * fetch data by rule id
	 *
	 * @param key rule id
	 * @return Result<XxxxDO>
	 */
	Integer getInteger(String key);

	/**
	 * fetch data by rule id
	 *
	 * @param key rule id
	 * @return Result<XxxxDO>
	 */
	String getString(String key);
}
