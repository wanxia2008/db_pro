package com.db.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.db.entity.Student;
import com.db.service.StudentService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.MD5Util;

@Controller
@RequestMapping("/app/login")
public class APPLoginController extends BaseUtil{
	@Resource
	private StudentService studentService;
	
	@RequestMapping("/studentLogin")
	public void confirmLogin(HttpServletResponse response,String userName,String password){
		Student loginer = studentService.getStudentByUserName(userName);
		if(loginer==null){//该用户不存在
			output(response, JsonUtil.buildFalseJson("1", "用户名不存在!"));
		}else{//用户存在,则判断密码
			if(!loginer.getPassword().equals(MD5Util.MD5Encode(password, "utf-8"))){
				output(response, JsonUtil.buildFalseJson("2", "密码不正确!"));
			}else{
				List<Student> uL = new ArrayList<Student>();
				uL.add(loginer);
				output(response, JsonUtil.buildJson(uL));
			}
		}
	}
}
