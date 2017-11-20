package com.db.util;

import java.util.List;
import java.util.Map;

import com.db.entity.TimeAndLesson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	public static String buildJson(List list) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String jsonString = "{";
		String JsonContext = "";
		int count = 0;
		if (!CommonUtil.isEmpty(list)) {
			JsonContext = gson.toJson(list);
			count = list.size();
			jsonString += "\"code\": \"0\", ";
			jsonString += "\"msg\": \"处理成功\",";
		} else {
			JsonContext = "[] ";
			count = 0;
			jsonString += "\"code\": \"1\", ";
			jsonString += "\"msg\": \"处理失败,没有数据\",";
		}
		jsonString += "\"count\": " + count + ",";
		jsonString += "\"objects\":";
		jsonString+=JsonContext;
		jsonString += "} ";
		return jsonString;
	}
	public static String buildFalseJson(String code, String message) {
		String jsonString = "{";
		jsonString += "\"code\": \""+code+"\", ";
		jsonString += "\"msg\": \""+message+"\" ";
		jsonString += "}";
		return jsonString;
	}
	//返回总记录数
	public static String buildJsonByTotalCount(List list,int totalCount) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String jsonString = "{";
		String JsonContext = "";
		int count = 0;
		if (!CommonUtil.isEmpty(list)) {
			JsonContext = gson.toJson(list);
			count = totalCount;
			jsonString += "\"code\": \"0\", ";
			jsonString += "\"msg\": \"处理成功\",";
		} else {
			JsonContext = "[] ";
			count = 0;
			jsonString += "\"code\": \"1\", ";
			jsonString += "\"msg\": \"处理失败,没有数据\",";
		}
		jsonString += "\"count\": " + count + ",";
		jsonString += "\"objects\":";
		jsonString+=JsonContext;
		jsonString += "} ";
		return jsonString;
	}
	public static String MapToJson(Map<String,Object> m){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String jsonString = "{";
		String JsonContext = "";
		int count = 0;
		if (!CommonUtil.isEmpty(m)) {
			JsonContext =  gson.toJson(m);;
			count = m.size();
			jsonString += "\"code\": \"0\", ";
			jsonString += "\"msg\": \"处理成功\",";
		} else {
			JsonContext = "[] ";
			count = 0;
			jsonString += "\"code\": \"1\", ";
			jsonString += "\"msg\": \"处理失败,没有数据\",";
		}
		jsonString += "\"count\": " + count + ",";
		jsonString += "\"objects\":";
		jsonString+=JsonContext;
		jsonString += "}";
		System.out.println(jsonString);
		return jsonString;
	}
	public static String MapToMyJson(Map<String,List<TimeAndLesson>> m){
		String jsonString = "";
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		if(!CommonUtil.isEmpty(m)){
			String listJson = gson.toJson(m);
			jsonString += listJson;
		}else{
			jsonString += "[] ";
		}
		return jsonString;
	}
	public static String builderFalseJson(String code, String message) {
		String jsonString = "{";
		jsonString += "\"\": { ";
		jsonString += "\"resultcode\": \""+code+"\", ";
		jsonString += "\"resultdesc\": \""+message+"\" ";
		jsonString += " }";
		jsonString += "}";
		return jsonString;
	}
}
