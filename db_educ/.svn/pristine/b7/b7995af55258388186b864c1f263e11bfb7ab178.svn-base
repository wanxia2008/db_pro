package com.db.action;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.MD5Util;
import com.db.entity.Role;
import com.db.entity.Teacher;
import com.db.entity.User;
import com.db.service.RoleService;
import com.db.service.StudentService;
import com.db.service.TeacherService;
import com.db.service.UserService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/user")
public class UserAction extends BaseUtil {
	@Resource
	private UserService userService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private StudentService studentService;
	@Resource
	private RoleService roleService;

	public static Logger log = Logger.getLogger(UserAction.class);

	@RequestMapping("/index")
	public String main() {
		return "../../index";
	}

	@RequestMapping("/login")
	public String login() {

		return "../../login";
	}

	/**
	 * 后台登录
	 * @param request
	 * @param model
	 * @param teacher
	 * @param imageCode
	 * @return
	 */
	@RequestMapping("/teacherLogin")
	public String toIndex(HttpServletRequest request, Model model, Teacher teacher, String imageCode) {
		Teacher loginedT = (Teacher) request.getSession().getAttribute("currTeacher");
		if (loginedT == null) {
			Teacher thisT = teacherService.getTeacherByUserName(teacher.getUserName());
			Date now = new Date();
			if (teacher.getUserName() == null || teacher.getUserName().trim().equals("")) {
				model.addAttribute("errorUserName", "用户名不能为空!");
				//返回当前输入的账号和密码
				model.addAttribute("userName", teacher.getUserName());
				model.addAttribute("password", teacher.getPassword());
				return "../../login";
			} else if (teacher.getPassword() == null || teacher.getPassword().trim().equals("")) {
				model.addAttribute("errorPassword", "密码不能为空!");
				//返回当前输入的账号和密码
				model.addAttribute("userName", teacher.getUserName());
				model.addAttribute("password", teacher.getPassword());
				return "../../login";
//			} else if (imageCode == null || imageCode.equals("")) {
//				model.addAttribute("errorImageCode", "验证码不能为空!");
//				//返回当前输入的账号和密码
//				model.addAttribute("userName", teacher.getUserName());
//				model.addAttribute("password", teacher.getPassword());
//				return "../../login";
			} else if (imageCode.equalsIgnoreCase((String) request.getSession().getAttribute("randomString"))) {
				if (thisT == null) {
					model.addAttribute("errorUserName", "用户名不存在!");
					//返回当前输入的账号和密码
					model.addAttribute("userName", teacher.getUserName());
					model.addAttribute("password", teacher.getPassword());
					return "../../login";
				} else if (!thisT.getPassword().equals(MD5Util.MD5Encode(teacher.getPassword(), "utf-8"))) {
					model.addAttribute("errorPassword", "密码错误!");
					//返回当前输入的账号和密码
					model.addAttribute("userName", teacher.getUserName());
					model.addAttribute("password", teacher.getPassword());
					return "../../login";
				} else if (now.getTime() > thisT.getAccountValidity().getTime()) {
					model.addAttribute("accountValidity", "账号已过期!");
					//返回当前输入的账号和密码
					model.addAttribute("userName", teacher.getUserName());
					model.addAttribute("password", teacher.getPassword());
					return "../../login";
				} else if (thisT.getTeacherStatus() == 2) {
					model.addAttribute("teacherStatus", "您已离职!");
					//返回当前输入的账号和密码
					model.addAttribute("userName", teacher.getUserName());
					model.addAttribute("password", teacher.getPassword());
					return "../../login";
				} else {
					Teacher teacher2 = teacherService.getTeacherRileById(thisT.getTeacherId());
					model.addAttribute("teacher2", teacher2);
					request.getSession().setAttribute("currTeacher", thisT);
					request.getSession().setMaxInactiveInterval(7200);
					return "../../index";
				}
			} else {
//				model.addAttribute("errorImageCode", "验证码错误!");
//				//返回当前输入的账号和密码
//				model.addAttribute("userName", teacher.getUserName());
//				model.addAttribute("password", teacher.getPassword());
//				return "../../login";
				
				//去除验证码登录
				Teacher teacher2 = teacherService.getTeacherRileById(thisT.getTeacherId());
				model.addAttribute("teacher2", teacher2);
				request.getSession().setAttribute("currTeacher", thisT);
				request.getSession().setMaxInactiveInterval(7200);
				return "../../index";
			}
		} else {
			Teacher teacher2 = teacherService.getTeacherRileById(loginedT.getTeacherId());
			model.addAttribute("teacher2", teacher2);
			request.getSession().setAttribute("currTeacher", loginedT);
			request.getSession().setMaxInactiveInterval(7200);
			return "../../index";
		}
	}






	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		return "login";
	}

	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request, HttpServletResponse response, int userId) {
		User user = this.userService.getUserById(userId);
		request.setAttribute("user", user);
		System.out.println("我要访问showUser.jsp");
		return "showUser";
	}

	@RequestMapping("/showUser1")
	public ModelAndView toIndex(ModelAndView modelAndView, int userId) {
		User user = this.userService.getUserById(userId);
		modelAndView.setViewName("showUser");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping("/showUser2")
	public String toIndex(Model model, int userId) {
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}

	@RequestMapping("/userList")
	public List<User> userList() {
		List<User> uList = userService.getAllUser();
		return uList;
	}

	@RequestMapping("/resp")
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) {

		return new ModelAndView("/showUser");
	}

	@RequestMapping("/getUserList")
	public void getUserList(HttpServletResponse response) {
		List<User> uList = userService.getAllUser();
		output(response, JsonUtil.buildJson(uList));
	}

	// 后台拿用户详情
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String userDetails(HttpServletRequest request, Model model) {
		User user = userService.getUserById(1);
		model.addAttribute("user", user);
		return "showUser";
	}

	/**
	 * 退出系统
	 * 
	 * @param request
	 */
	@RequestMapping("/doLogout")
	public ModelAndView doLogout(HttpServletRequest request, ModelAndView mv) {
		HttpSession session1 = request.getSession();
		// Teacher teacher = (Teacher) session1.getAttribute("currTeacher");
		session1.invalidate();
		mv.setViewName("../../login");
		return mv;
	}
}
