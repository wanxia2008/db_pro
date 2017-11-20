package com.db.util;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public class BaseUtil extends WebMvcConfigurerAdapter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaseUtil.class);

	protected void output(HttpServletResponse response, String jsonResult) {
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setContentLength(jsonResult.getBytes("UTF-8").length);
			logger.debug("response json: " + jsonResult);
			// response.getOutputStream().write(jsonResult.getBytes());
			// printStream.println(result);
			PrintWriter printWriter = response.getWriter();
			printWriter.write(jsonResult);
			printWriter.flush();
			// response.getOutputStream().
		} catch (Exception e) {
			logger.error("Error output json data to the client!!!orginal json=" + jsonResult, e);
		}
	}

	protected void writeString(String str) {
		HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter;
		try {
			printWriter = response.getWriter();
			printWriter.write(str);
			printWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	// 过滤标签
	public static String guoHtml(String string) {
		if (!string.equals("") && string != null) {
			/*
			 * String str = string.replaceAll("<[.[^<]]*>", ""); str =
			 * str.replaceAll("\\s*|\t|\r|\n", "");
			 */
			String content = string.replaceAll("</?[^<]+>", "");
			// 去除字符串中的空格 回车 换行符 制表符 等
			content = content.replaceAll("\\s*|\t|\r|\n", "");
			// 去除空格
			content = content.replaceAll("&nbsp;", "");
			// 去掉其他一些字符
			// content = content.replaceAll("\\", "");
			return content;
		} else {
			return string;
		}
	}
}
