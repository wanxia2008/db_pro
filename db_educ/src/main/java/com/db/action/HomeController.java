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
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.Notice;
import com.db.entity.Role;
import com.db.entity.Student;
import com.db.entity.Teacher;
import com.db.entity.UserRole;
import com.db.service.NoticeService;
import com.db.service.RoleService;
import com.db.service.StudentService;
import com.db.service.UserRoleService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
/**
 * 首页
 * @author admin
 *
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseUtil {

	@Resource
	public NoticeService noticeService;
	@Resource
	public StudentService studentService;
	@Resource
	private RoleService roleService;
	@Resource
	private UserRoleService userRoleService;
	
	public Logger logger = Logger.getLogger(HomeController.class);
	
	HttpSession session;
	
	@RequestMapping("/tohome")
	public ModelAndView toHome(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		UserRole userRole = userRoleService.getUserRole(currTeacher.getTeacherId());
		Role role = roleService.getRoleStringById(userRole.getRoleId());
		boolean flag = false;
		String[] rolsAry = role.getMenuIds().split(",");
		for(String a:rolsAry){
			if(a.equals("8")){
				flag = true;
			}
		}
		ModelAndView view = new ModelAndView();
		if(!flag) {//没有权限
			view.addObject("permiss_createStu", 1);
		} else {
			view.addObject("permiss_createStu", 0);
		}
		view.setViewName("../../home");
		return view;
	}
	/**
	 * 添加公告
	 * @param request
	 * @param response
	 * @param notice
	 */
	@RequestMapping("/addnotice")
	public void addNotice(HttpServletRequest request,HttpServletResponse response,Notice notice) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		notice.setCreateTime(new Date());
		notice.setUserId(teacher.getTeacherId());
		noticeService.addNotice(notice);//返回主键
		Notice notice2 = noticeService.getNoticeById(notice.getNoticeId());
		if(notice2!=null) {
			output(response, JsonUtil.buildFalseJson("0", "添加成功！"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "添加失败！"));
		}
	}
	/**
	 * 公告列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getnotice")
	public void getNotice(HttpServletRequest request,HttpServletResponse response) {
		int pageNo = Integer.valueOf(request.getParameter("pageNo"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<Notice> noticeList = noticeService.findUserById(currTeacher.getTeacherId());
		Integer deleteId = null;
		for (Notice notice:noticeList) {
			if (notice.getDeleteId() != null) {
				deleteId = notice.getDeleteId();
			}
		}
		List<Notice> list = noticeService.getAllNotice((pageNo-1)*pageSize, pageSize,deleteId);
		output(response, JsonUtil.buildJson(list));
	}
	
	/**
	 * 获取学生最新报名记录
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getstudentbytime")
	public void getStudentByTime(HttpServletRequest request,HttpServletResponse response) {
		int pageNo = 0;
		int pageSize = 8;
		List<Student> list = studentService.getStudentByTime(pageNo, pageSize);
		output(response, JsonUtil.buildJson(list));
	}
	/**
	 * 修改公告
	 * @param id
	 * @param title
	 * @param content
	 * @param response
	 */
	@RequestMapping("updatenotice")
	public void updateNotice(Integer id,String title,String content,HttpServletResponse response) {
		Notice notice = new Notice();
		notice.setNoticeId(id);
		notice.setNoticeTitle(title);
		notice.setNoticeContent(content);
		int len = noticeService.updateNotice(notice);
		if(len >0) {
			output(response, JsonUtil.buildFalseJson("0", "修改成功"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "修改失败"));
		}
	}
	/**
	 * 删除公告
	 * @param id
	 * @param response
	 */
	@RequestMapping("deletenotice")
	public void deleteNotice(Integer id,HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Notice notice = noticeService.getNoticeById(id);
		notice.setDeleteId(currTeacher.getTeacherId());
		notice.setIsRemove(2);
		noticeService.updateIsRemove(notice);
		try {
			Notice notice1 = noticeService.getNoticeById(notice.getNoticeId());
			 noticeService.updateIsRemoveDelete(notice1.getNoticeId());
			output(response, JsonUtil.buildFalseJson("0", "删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
			output(response, JsonUtil.buildFalseJson("1", "删除失败"));
		}
	}
	/**
	 * 首页搜索
	 * @param name
	 * @param response
	 */
	@RequestMapping("getstudent")
	public void getStudent(String name,HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		UserRole userRole = userRoleService.getUserRole(currTeacher.getTeacherId());
		Role role = roleService.getRoleStringById(userRole.getRoleId());
		boolean flag = false;
		String[] rolsAry = role.getMenuIds().split(",");
		for(String a:rolsAry){
			if(a.equals("8")){
				flag = true;
			}
		}
		if (!flag) {
			output(response, JsonUtil.buildFalseJson("2", "抱歉，您没有查看学员的权限!"));
		}else {
			List<Student> student = studentService.getStudnetByName(name);
			if(student == null || student.isEmpty()) {
				output(response, JsonUtil.buildFalseJson("1", "没有找到符合的学生信息"));
			} else {
				output(response, JsonUtil.buildFalseJson("0", "获取成功"));
			}
		}
	}
	/**
	 * 快速添加学员入口
	 * @param response
	 * @param request
	 */
	@RequestMapping("/addStudent")
	public void addStudent(HttpServletResponse response,HttpServletRequest request){
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		UserRole userRole = userRoleService.getUserRole(currTeacher.getTeacherId());
		Role role = roleService.getRoleStringById(userRole.getRoleId());
		boolean flag = false;
		String[] rolsAry = role.getMenuIds().split(",");
		for(String a:rolsAry){
			if(a.equals("8")){
				flag = true;
			}
		}
		if (!flag) {
			output(response, JsonUtil.buildFalseJson("2", "抱歉，您没有添加学员的权限!"));
		}else {
			output(response, JsonUtil.buildFalseJson("0", "有添加学员的权限!"));
		}
	}
}
