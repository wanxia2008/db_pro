package com.db.util;

import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class MapCacheUtil {

	private volatile static MapCacheUtil instance;
	// 缓存某个学生读题的状态
	private volatile static Map<String, Map<String, Object>> casemap = new HashMap<String, Map<String, Object>>();
	private volatile static Map<String, Object> map = new HashMap<>();
	
	private MapCacheUtil(){}
	
	public static MapCacheUtil getInstance(){
		if(instance==null) {
			synchronized (MapCacheUtil.class) {
				if(instance==null) {
					instance = new MapCacheUtil();
				}
			}
		}
		return instance;
	}
	//存缓存
	public Map<String, Object> setMapCache(String key,Object value) {
		map.put(key, value);
		return map;
	}
	//存缓存
	public void setCaseMap(String key,Map<String, Object> value) {
		casemap.put(key, value);
	}
	//读取缓存
	public Object getMapCache(String key) {
		return map.get(key);
	}
	//读取缓存
	public Map<String, Object> getCaseMap(String key) {
		return casemap.get(key);
	}
	//清除该学生考试的缓存,即清除key
	public void removeCaseMap(String key) {
		casemap.remove(key);
	}
	//清除该学生考试的缓存
	public void removeMap(String key) {
		map.remove(key);
	}
}
