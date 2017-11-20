package com.db.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.Log;
import com.db.entity.Role;
import com.db.entity.Teacher;
import com.db.entity.UserRole;
import com.db.service.LogService;
import com.db.service.RoleService;
import com.db.service.TeacherService;
import com.db.service.UserRoleService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

/**
 * 角色管理
 * 
 * @author sun
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseUtil {

	@Resource
	private RoleService roleService;
	@Resource
	private LogService logService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private UserRoleService userRoleService;

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	@RequestMapping("/rolemanage")
	public ModelAndView Rolemanage(Integer pageNo, Integer pageSize, Role role, String roleName) {
		ModelAndView mv = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = roleService.getRoleCount(roleName);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<Role> rList = roleService.getRoleList(roleName, (pageNo - 1) * pageSize, pageSize);
		if(!rList.isEmpty() && rList!=null) {
			mv.addObject("rList", rList);
		} else {
			mv.addObject("rList", null);
		}
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("roleName", roleName);
		mv.setViewName("rolemanage/rolemanage");
		return mv;
	}
	
	
	/**
	 * 角色添加
	 * 
	 * @return
	 */
	@RequestMapping("/newrole")
	public String Newrole() {
		return "rolemanage/newrole";
	}
	
	/**
	 * 删除角色
	 * 
	 * @param roleId
	 * @param response
	 */
	@RequestMapping("/roleDelete")
	public void roleDelete(Integer roleId, HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<UserRole> userRole = userRoleService.getRoleById(roleId);
		Role role = roleService.getRoleById(roleId);
		if (!currTeacher.getTeacherType().equals("1") && !currTeacher.getTeacherType().equals("2")) {
			output(response, JsonUtil.buildFalseJson("2", "您不是超级管理员，不能删除角色!"));
		} else if (userRole != null && !userRole.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("3", "该角色已被使用，暂不能删除!"));
		} else {
			try {
				roleService.roleDelete(roleId);
				
				Log log = new Log();
				log.setStartTime(new Date());
				log.setOperationName(currTeacher.getTeacherName());
				log.setOperationId(currTeacher.getTeacherId().toString());
				log.setOperationResult("成功");
				log.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】删除角色成功，用户名为[" + role.getRoleName()+"]");
				log.setOperationType("删除");
				log.setModular("角色管理");
				log.setEndTime(new Date());
				logService.saveLog(log);
				output(response, JsonUtil.buildFalseJson("0", "添加成功"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
				Log log = new Log();
				log.setStartTime(new Date());
				log.setOperationName(currTeacher.getTeacherName());
				log.setOperationId(currTeacher.getTeacherId().toString());
				log.setOperationResult("失败");
				log.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】删除角色失败，用户名为[" + role.getRoleName()+"]");
				log.setOperationType("删除");
				log.setModular("角色管理");
				log.setEndTime(new Date());
				logService.saveLog(log);
			}
		}
	}
	
	/**
	 * 添加角色
	 * @param pageNo
	 * @param pageSize
	 * @param role
	 * @param roleName
	 * @param menuIds
	 * @param roleDescribe
	 * @return
	 */
	@RequestMapping("/saveRoleUser")
	public void saveRoleUser(Role role,HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		Role roleName = roleService.findIsName1(role.getRoleName());
		if (role.getRoleName() == null || role.getRoleName()=="") {
			output(response, JsonUtil.buildFalseJson("1", "请输入角色名称"));
		} else if (roleName != null) {
			output(response, JsonUtil.buildFalseJson("2", "该角色已存在!"));
		} else if (!teacher.getTeacherType().equals("1") && !teacher.getTeacherType().equals("2")) {
			output(response, JsonUtil.buildFalseJson("3", "对不起你没有分配角色的权限!"));
		} else if(role.getMenuIds()== null || role.getMenuIds()== ""){
			output(response, JsonUtil.buildFalseJson("4", "请勾选你需要分配的权限"));
		} else {
			role.setCreateTime(new Date());
			role.setIsModify(role.getIsModify());
			int sun=roleService.addRole(role);
			if(sun!=0){
					Log log = new Log();
					log.setStartTime(new Date());
					log.setOperationName(teacher.getTeacherName());
					log.setOperationId(teacher.getTeacherId().toString());
					log.setOperationResult("成功");
					log.setOperationContent("管理员【" + teacher.getTeacherName() + "】添加用户成功，角色名为[" + role.getRoleName()+"]");
					log.setOperationType("新增");
					log.setModular("角色管理");
					log.setEndTime(new Date());
					logService.saveLog(log);
					output(response, JsonUtil.buildFalseJson("0", "添加成功"));
			}else{
				Log log = new Log();
				log.setStartTime(new Date());
				log.setOperationName(teacher.getTeacherName());
				log.setOperationId(teacher.getTeacherId().toString());
				log.setOperationResult("失败");
				log.setOperationContent("管理员【" + teacher.getTeacherName() + "】添加角色失败，角色名为[" + role.getRoleName()+"]");
				log.setOperationType("新增");
				log.setModular("角色管理");
				log.setEndTime(new Date());
				logService.saveLog(log);
				output(response, JsonUtil.buildFalseJson("5", "添加成功"));
			}	
		}
	}
	
	/**
	 * 修改角色权限
	 * 
	 * @param userId
	 * @param menuIds
	 * @param response
	 */
	@RequestMapping("/updateUserRole")
	public void updateUserRole(Integer roleId, String menuIds,Integer isModify,HttpServletRequest request ,HttpServletResponse response,String roleDescribe,String roleName) {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		Role role = roleService.getRoleById(roleId);
		Role role1 = roleService.findIsName(roleId,roleName);
		if (!teacher.getTeacherType().equals("1") && !teacher.getTeacherType().equals("2") ) {
			output(response, JsonUtil.buildFalseJson("2", "对不起你没有分配角色的权限!"));
		}else if (role1 != null) {
			output(response, JsonUtil.buildFalseJson("3", "该角色已存在!"));
		} else if(role.getMenuIds() == null || role.getMenuIds() == ""){
			output(response, JsonUtil.buildFalseJson("4", "请勾选你需要分配的权限!"));
		} else {
				if (roleDescribe != null) {
					role.setRoleDescribe(roleDescribe);
				}
				if (roleName != null) {
					role.setRoleName(roleName);
				}
				role.setMenuIds(menuIds);
				role.setIsModify(isModify);
			    int sum=roleService.updateRole(role);
			    if(sum!=0){
					Log log = new Log();
					log.setStartTime(new Date());
					log.setOperationName(teacher.getTeacherName());
					log.setOperationId(teacher.getTeacherId().toString());
					log.setOperationResult("成功");
					log.setOperationContent("管理员【" + teacher.getTeacherName() + "】修改角色成功，用户名为[" + role.getRoleName()+"]");
					log.setOperationType("修改");
					log.setModular("角色管理");
					log.setEndTime(new Date());
					logService.saveLog(log);
					output(response, JsonUtil.buildFalseJson("0", "添加成功"));
			}else{
				Log log = new Log();
				log.setStartTime(new Date());
				log.setOperationName(teacher.getTeacherName());
				log.setOperationId(teacher.getTeacherId().toString());
				log.setOperationResult("失败");
				log.setOperationContent("管理员【" + teacher.getTeacherName() + "】修改角色失败，用户名为[" + role.getRoleName()+"]");
				log.setOperationType("修改");
				log.setModular("角色管理");
				log.setEndTime(new Date());
				logService.saveLog(log);
				output(response, JsonUtil.buildFalseJson("5", "添加成功"));
			}	
		}
	}
}
