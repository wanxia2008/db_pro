package com.db.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.Grade;
import com.db.entity.Log;
import com.db.entity.Role;
import com.db.entity.Teacher;
import com.db.entity.TeacherAuthority;
import com.db.entity.UserRole;
import com.db.service.GradeService;
import com.db.service.LogService;
import com.db.service.RoleService;
import com.db.service.TeacherAuthorityService;
import com.db.service.TeacherService;
import com.db.service.UserRoleService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/teacherAuthority")
public class TeacherAuthorityController extends BaseUtil {
	@Resource
	private TeacherAuthorityService teacherAuthorityService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private GradeService gradeService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private RoleService roleService;
	@Resource
	private LogService logService;

	/**
	 * 权限管理列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param teacherName
	 * @return
	 */
	@RequestMapping("/permissions")
	public ModelAndView Permissionmanage(Integer pageNo, Integer pageSize, String teacherName,
			HttpServletRequest request) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		int rowsCount = 0;
		int totalPages = 0;
		
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		List<TeacherAuthority> tList = null;
		if (teacher.getTeacherType().equals("1")) {
			rowsCount = teacherAuthorityService.getCountId(teacherName);
			tList = teacherAuthorityService.findAuthorityById((pageNo - 1) * pageSize, pageSize,
					teacherName);
		} else if (teacher.getTeacherType().equals("2")) {
			rowsCount = teacherAuthorityService.getCountSchoolId(teacherName,teacher.getCampus());
			tList = teacherAuthorityService.findSchoolIdList((pageNo - 1) * pageSize, pageSize,
					teacherName,teacher.getCampus());
		} else {
			rowsCount = teacherAuthorityService.seeCountId(teacherName,teacher.getTeacherId());
			tList = teacherAuthorityService.seefindAuthorityById((pageNo - 1) * pageSize, pageSize,
					teacherName,teacher.getTeacherId());
		}
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<Role> rnameList = roleService.getRnameList();
		List<Role> rList;
		for (int i = 0; i < tList.size(); i++) {
			String userRole = tList.get(i).getUserRole().getRoleId();
			String[] user = userRole.split(",");
			List roleList = Arrays.asList(user);
			rList = roleService.getRoleIdsById(roleList);
			tList.get(i).getUserRole().setRlList(rList);
		}
		mv.setViewName("teachermanage/permissions");
		if(!tList.isEmpty() && tList!=null) {
			mv.addObject("tList", tList);
		} else {
			mv.addObject("tList", null);
		}
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("rnameList", rnameList);
		mv.addObject("teacherName", teacherName);
		return mv;
	}

	/**
	 * 新增教师权限页面
	 * 
	 * @return
	 */

	@RequestMapping("addAuthority")
	public String addAuthority(Model model) {
		List<Grade> gList = gradeService.getGrade();
		model.addAttribute("gList", gList);
		return "teachermanage/addAuthority";

	}

	/**
	 * 新增教师权限
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param schoolName
	 * @param ta
	 * @return
	 */
	@RequestMapping(value = "/saveAuthority")
	public ModelAndView addPermissions(Integer pageNo, Integer pageSize, String schoolName, TeacherAuthority ta) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 2;
		}
		ta.setCreateTime(new Date());
		teacherAuthorityService.addTeacherAuthority(ta);
		return this.Permissionmanage(pageNo, pageSize, schoolName, null);
	}

	/**
	 * 修改教师权限
	 * 
	 * @param teacherId
	 * @param menuIds
	 * @return
	 */
	@RequestMapping("/updateAuthority")
	public void updateAuthority(Integer taId, Integer questionsAuthority, String roleId, Integer paperAuthority,
			Integer handoutAuthority, HttpServletResponse response, HttpServletRequest request) {
		TeacherAuthority ta = teacherAuthorityService.getTaIdById(taId);
		UserRole userRole = userRoleService.getUserRole(ta.getTeacherId());
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (!currTeacher.getTeacherType().equals("1") && !currTeacher.getTeacherType().equals("2")) {
			output(response, JsonUtil.buildFalseJson("3", "您没有修改的权限!"));
		} else if (userRole == null) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据 !"));
		} else {
			if (roleId != null) {
				userRole.setRoleId(roleId);
				userRoleService.updateUserRole(userRole);
			}
			if (questionsAuthority != null) {
				ta.setQuestionsAuthority(questionsAuthority);
			}
			if (paperAuthority != null) {
				ta.setPaperAuthority(paperAuthority);
			}
			if (handoutAuthority != null) {
				ta.setHandoutAuthority(handoutAuthority);
			}
			Teacher teacher = teacherService.getTeacherById(ta.getTeacherId());
			try {
				teacherAuthorityService.updateTeacherAuthority(ta);
				output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("角色管理");
				log1.setOperationType("修改");
				log1.setOperationResult("成功");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent("管理员【"+currTeacher.getTeacherName()+"】修改用户权限【"+teacher.getTeacherName()+"】成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "系统错误!"));
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("角色管理");
				log1.setOperationType("修改");
				log1.setOperationResult("失败");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent("管理员【"+currTeacher.getTeacherName()+"】修改用户权限【"+teacher.getTeacherName()+"】失败!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
			}
		}
	}

	/**
	 * 删除讲师
	 * 
	 * @param response
	 * @param teacherId
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, Integer taId) {
		try {
			teacherAuthorityService.deleteAuthorityById(taId);
			output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
		} catch (Exception e) {
			output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
		}
	}
}
