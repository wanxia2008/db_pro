package com.db.util;

import java.util.Random;

public class PhoneMessageUtil {
	//短信的配置
	String url = "http://api.smsbao.com/sms";
	String account = "chenxiangyou"; //在短信宝注册的用户名
	String pswd = "wwzd2016"; //在短信宝注册的密码
	String mobile = "13698571527";
	String msg = "【云教育】您的验证码是1234,5分钟内有效。若非本人操作请忽略此消息。"; // 注意测试时，也请带上公司简称或网站签名，发送正规内容短信。千万不要发送无意义的内容：例如 测一下、您好。否则可能会收不到
	

   public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	//拿到六位随机数
	public static String ProductCode() {
		String Code = "";
		int num;
		Random r = new Random();
		for (int i = 0; i < 6; i++) {
			num = r.nextInt(9);
			Code += num;
		}
		return Code;
	}
}
