package com.db.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.ClassNo;
import com.db.entity.Grade;
import com.db.entity.Log;
import com.db.entity.Role;
import com.db.entity.SchoolZone;
import com.db.entity.Subject;
import com.db.entity.Teacher;
import com.db.entity.TeacherAuthority;
import com.db.entity.UserRole;
import com.db.service.ClassNoService;
import com.db.service.GradeService;
import com.db.service.LogService;
import com.db.service.RoleService;
import com.db.service.SchoolZoneService;
import com.db.service.SubjectService;
import com.db.service.TeacherAuthorityService;
import com.db.service.TeacherService;
import com.db.service.UserRoleService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.MD5Util;
import com.db.util.PageUtil;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseUtil {

	@Resource
	private SchoolZoneService schoolservice;
	@Resource
	private SubjectService subjectService;
	@Resource
	private GradeService gradeService;
	@Resource
	private ClassNoService classNoService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private TeacherAuthorityService teacherAuthorityService;
	@Resource
	private RoleService roleService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private LogService logService;

	private Logger log = Logger.getLogger("TeacherController");

	/**
	 * 后台教师列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param schoolName
	 * @return
	 */
	@RequestMapping("/teachermanage")
	public ModelAndView Teachermanage(Integer pageNo, Integer pageSize, String schoolName, HttpServletRequest request,
			Integer subject, Integer schoolId) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		int rowsCount = 0;
		int totalPages = 0;

		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<Teacher> tList = null;
		if (currTeacher.getTeacherType().equals("1")) {
			// 超级管理员看全部老师
			tList = teacherService.findTeacherById((pageNo - 1) * pageSize, pageSize, schoolName, subject, schoolId);
			rowsCount = teacherService.getCountId(schoolName, subject, schoolId);
		} else if (currTeacher.getTeacherType().equals("2") || currTeacher.getTeacherType().equals("3")
				|| currTeacher.getTeacherType().equals("4")) {
			// 校区管理员看整个校区老师
			tList = teacherService.findTeacherSchoolIdById((pageNo - 1) * pageSize, pageSize, schoolName,
					currTeacher.getCampus(), subject);
			rowsCount = teacherService.getSchoolIdById(schoolName, currTeacher.getCampus(), subject);
		} else {
			// 普通老师只能看自己
			rowsCount = teacherService.getCountTeacherId(schoolName, currTeacher.getTeacherId());
			tList = teacherService.seeTeacherById(currTeacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize,
					schoolName);
		}
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("teachermanage/teachermanage");
		List<SchoolZone> list = schoolservice.getSchoolZone(schoolName);
		List<Grade> list2;
		List<Role> rList;
		for (int i = 0; i < tList.size(); i++) {
			if (tList.get(i).getGrade() != null && !tList.get(i).equals("")) {
				String grades = tList.get(i).getGrade();
				String[] gradess = grades.split(",");
				System.out.println("grades" + gradess);
				List listGrades = Arrays.asList(gradess);
				list2 = gradeService.getGradeByIds(listGrades);
				tList.get(i).setGradelist(list2);
			} else {
				tList.get(i).setGradelist(null);
			}
		}
		for (int i = 0; i < tList.size(); i++) {
			String userRole = tList.get(i).getUserRole().getRoleId();
			String[] user = userRole.split(",");
			List roleList = Arrays.asList(user);
			rList = roleService.getRoleIdsById(roleList);
			tList.get(i).getUserRole().setRlList(rList);
		}
		if (!tList.isEmpty() && tList != null) {
			view.addObject("tList", tList);
		} else {
			view.addObject("tList", null);
		}
		List<Subject> subjects = subjectService.getSubject();
		view.addObject("schoolList", list);
		view.addObject("pageSize", pageSize);
		view.addObject("page", pageNo);
		view.addObject("currTeacher", currTeacher);
		view.addObject("subjects", subjects);
		view.addObject("schoolId", schoolId);
		view.addObject("subject", subject);
		view.addObject("schoolName", schoolName);
		view.addObject("totalPages", totalPages);
		return view;
	}

	//新增老师页面
	@RequestMapping("/newteacher")
	public ModelAndView Newteacher(Integer pageNo, Integer pageSize, String schoolName, HttpServletRequest request) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<SchoolZone> list = null;
		if (currTeacher.getTeacherType().equals("1")) {
			list = schoolservice.getSchoolZone(schoolName);
		} else if (currTeacher.getTeacherType().equals("2") || currTeacher.getTeacherType().equals("3")
				|| currTeacher.getTeacherType().equals("4")) {
			list = schoolservice.findSchoolList(currTeacher.getCampus());
		} else {
			list = schoolservice.getSchoolZone(schoolName);
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("teachermanage/newteacher");
		List<Subject> list2 = subjectService.getSubject();
		List<Grade> list3 = gradeService.getGrade();
		List<Role> rList = roleService.getRole();
		view.addObject("rList", rList);
		view.addObject("schoolList", list);
		view.addObject("subjectList", list2);
		view.addObject("gradeList", list3);
		return view;
	}

	/**
	 * 后台修改教师页面
	 * 
	 * @param teacherid
	 * @param pageNo
	 * @param pageSize
	 * @param schoolName
	 * @return
	 */
	@RequestMapping(value = "/toupdateteacher")
	public ModelAndView toUpdateteacher(Integer teacherid, Integer pageNo, Integer pageSize, String schoolName,
			HttpServletRequest request) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("teachermanage/updateteacher");
		Teacher teacher = teacherService.getTeacherById(teacherid);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<SchoolZone> list = null;
		if (currTeacher.getTeacherType().equals("1")) {
			list = schoolservice.getSchoolZone(schoolName);
		} else if (currTeacher.getTeacherType().equals("2") || currTeacher.getTeacherType().equals("3")
				|| currTeacher.getTeacherType().equals("4")) {
			list = schoolservice.findSchoolList(currTeacher.getCampus());
		} else {
			list = schoolservice.getSchoolZone(schoolName);
		}
		SchoolZone sZone = schoolservice.getSchoolZoneById(teacher.getCampus());
		List<Subject> list2 = subjectService.getSubject();
		List<Grade> list3 = gradeService.getGrade();
		Role role = roleService.getRoleStringById(teacher.getTeacherType());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String myDate = sdf.format(teacher.getAccountValidity());
		view.addObject("schoolList", list);
		view.addObject("sZone", sZone);
		view.addObject("subjectList", list2);
		view.addObject("gradeList", list3);
		view.addObject("role", role);
		List<Role> rList = roleService.getRole();
		view.addObject("rList", rList);
		view.addObject("page", pageNo);
		view.addObject("teacher", teacher);
		view.addObject("myDate", myDate);
		return view;
	}

	@RequestMapping("/getallteacher")
	public void getAllteacher(HttpServletRequest request, HttpServletResponse response) {
		try {
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int totalCount = (int) teacherService.getCount();
			PageUtil util = new PageUtil(totalCount, pageNo, pageSize);
			List<Teacher> list = teacherService.getAllTeacher(util.getStartPos(), util.getPageSize());
			List<Grade> list2;
			for (int i = 0; i < list.size(); i++) {
				String grades = list.get(i).getGrade();
				String[] gradess = grades.split(",");
				System.out.println("grades" + gradess);
				List listGrades = Arrays.asList(gradess);
				System.out.println(listGrades);
				list2 = gradeService.getGradeByIds(listGrades);
				list.get(i).setGradelist(list2);
			}
			log.info("获取教师信息成功！" + list);
			output(response, JsonUtil.buildJsonByTotalCount(list, util.getTotalPageCount()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			output(response, JsonUtil.buildFalseJson("1", "获取教师信息失败"));
		}
	}

	/**
	 * 后台新增教师
	 * 
	 * @param request
	 * @param teacher
	 * @param pageNo
	 * @param pageSize
	 * @param schoolName
	 * @return
	 */
	@RequestMapping("/addteacher")
	public void addTeacher(HttpServletRequest request, Teacher teacher, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Teacher teName = teacherService.getTeacherByUserName(teacher.getUserName());
		Teacher phone = null;
		if (teacher.getPhone() != null && teacher.getPhone() != "") {
			phone = teacherService.getPhone1(teacher.getPhone());
		}
		if (!currTeacher.getTeacherType().equals("1") && !currTeacher.getTeacherType().equals("2")
				&& !currTeacher.getTeacherType().equals("3") && !currTeacher.getTeacherType().equals("4")) {
			output(response, JsonUtil.buildFalseJson("2", "你没有添加教师的权限!"));
		} else if (teName != null) {
			output(response, JsonUtil.buildFalseJson("1", "用户已存在!"));
		} else if (phone != null) {
			output(response, JsonUtil.buildFalseJson("3", "该手机号码已存在!"));
		} else {
			if (!teacher.getTeacherType().equals("5") && !teacher.getTeacherType().equals("6")) {
				try {
					if (teacher.getGrade() != null && !teacher.getGrade().equals("")) {
						teacher.setGrade("," + request.getParameter("grade") + ",");
					} else {
						teacher.setGrade(null);
					}
					System.out.println("获取的年级为：" + request.getParameter("grade"));
					teacher.setCreateTime(new Date());
					teacher.setPassword(MD5Util.MD5Encode(teacher.getPassword(), "utf-8"));
					teacherService.addTeacher(teacher);
					Teacher teacher2 = teacherService.getTeacherById(teacher.getTeacherId());
					if (teacher2 != null) {
						String[] grade = null;
						if (teacher2.getGrade() != null && !teacher2.getGrade().equals("")) {
							grade = teacher2.getGrade().split(",");
						}
						TeacherAuthority tAuthority = new TeacherAuthority();
						tAuthority.setCreateTime(new Date());
						tAuthority.setTeacherId(teacher2.getTeacherId());
						tAuthority.setHandoutAuthority(3);
						tAuthority.setPaperAuthority(3);
						tAuthority.setQuestionsAuthority(3);
						if (grade != null) {
							for (int i = 0; i < grade.length; i++) {
								// 往教师权限(题目、讲义、试卷)插入数据
								tAuthority.setGrade(grade[i]);
								teacherAuthorityService.addTeacherAuthority(tAuthority);
							}
						} else {
							teacherAuthorityService.addTeacherAuthority(tAuthority);
						}
						// 往用户权限表插入数据
						UserRole userRole = new UserRole();
						userRole.setUserId(teacher2.getTeacherId());
						userRole.setRoleId(teacher2.getTeacherType());
						userRoleService.saveUserRole(userRole);

						// 往日志表里插入数据
						Log log = new Log();
						log.setStartTime(new Date());
						log.setOperationId(currTeacher.getTeacherId().toString());
						log.setOperationName(currTeacher.getTeacherName());
						log.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】添加用户成功，用户名为["
								+ teacher2.getTeacherName() + "]");
						log.setOperationType("新增");
						log.setModular("角色管理");
						log.setOperationResult("成功");
						log.setEndTime(new Date());
						logService.saveLog(log);
						output(response, JsonUtil.buildFalseJson("0", "新增教师成功!"));
					}
					log.info("新增老师成功");
				} catch (Exception e) {
					e.printStackTrace();
					Log log = new Log();
					log.setStartTime(new Date());
					log.setOperationId(currTeacher.getTeacherId().toString());
					log.setOperationName(currTeacher.getTeacherName());
					log.setOperationContent(
							"管理员【" + currTeacher.getTeacherName() + "】添加用户成功失败，用户名为[" + teacher.getTeacherName() + "]");
					log.setOperationType("新增");
					log.setModular("角色管理");
					log.setOperationResult("失败");
					log.setEndTime(new Date());
					logService.saveLog(log);
					output(response, JsonUtil.buildFalseJson("1", "新增教师失败!"));
				}
			} else {
				if (teacher.getGrade() != null && !teacher.getGrade().equals("") && teacher.getSubject() != null) {
					try {
						if (teacher.getGrade() != null && !teacher.getGrade().equals("")) {
							teacher.setGrade("," + request.getParameter("grade") + ",");
						} else {
							teacher.setGrade(null);
						}
						System.out.println("获取的年级为：" + request.getParameter("grade"));
						teacher.setCreateTime(new Date());
						teacher.setPassword(MD5Util.MD5Encode(teacher.getPassword(), "utf-8"));
						teacherService.addTeacher(teacher);
						Teacher teacher2 = teacherService.getTeacherById(teacher.getTeacherId());
						if (teacher2 != null) {
							String[] grade = null;
							if (teacher2.getGrade() != null && !teacher2.getGrade().equals("")) {
								grade = teacher2.getGrade().split(",");
							}
							TeacherAuthority tAuthority = new TeacherAuthority();
							tAuthority.setCreateTime(new Date());
							tAuthority.setTeacherId(teacher2.getTeacherId());
							tAuthority.setHandoutAuthority(3);
							tAuthority.setPaperAuthority(3);
							tAuthority.setQuestionsAuthority(3);
							if (grade != null) {
								for (int i = 0; i < grade.length; i++) {
									// 往教师权限(题目、讲义、试卷)插入数据
									tAuthority.setGrade(grade[i]);
									teacherAuthorityService.addTeacherAuthority(tAuthority);
								}
							} else {
								teacherAuthorityService.addTeacherAuthority(tAuthority);
							}
							// 往用户权限表插入数据
							UserRole userRole = new UserRole();
							userRole.setUserId(teacher2.getTeacherId());
							userRole.setRoleId(teacher2.getTeacherType());
							userRoleService.saveUserRole(userRole);

							// 往日志表里插入数据
							Log log = new Log();
							log.setStartTime(new Date());
							log.setOperationId(currTeacher.getTeacherId().toString());
							log.setOperationName(currTeacher.getTeacherName());
							log.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】添加用户成功，用户名为["
									+ teacher2.getTeacherName() + "]");
							log.setOperationType("新增");
							log.setModular("角色管理");
							log.setOperationResult("成功");
							log.setEndTime(new Date());
							logService.saveLog(log);
							output(response, JsonUtil.buildFalseJson("0", "新增教师成功!"));
						}
						log.info("新增老师成功");
					} catch (Exception e) {
						e.printStackTrace();
						Log log = new Log();
						log.setStartTime(new Date());
						log.setOperationId(currTeacher.getTeacherId().toString());
						log.setOperationName(currTeacher.getTeacherName());
						log.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】添加用户成功失败，用户名为["
								+ teacher.getTeacherName() + "]");
						log.setOperationType("新增");
						log.setModular("角色管理");
						log.setOperationResult("失败");
						log.setEndTime(new Date());
						logService.saveLog(log);
						output(response, JsonUtil.buildFalseJson("1", "新增教师失败!"));
					}
				} else {
					output(response, JsonUtil.buildFalseJson("4", "请选择该教师的所教年级级科目!"));
				}
			}
		}
	}

	/**
	 * 保存修改的教师信息
	 * 
	 * @param request
	 * @param teacher
	 * @param pageNo
	 * @param pageSize
	 * @param schoolName
	 * @return
	 */
	@RequestMapping(value = "/updateteacher", method = RequestMethod.POST)
	public void updateTeacher(HttpServletRequest request, Teacher teacher, HttpServletResponse response) {

		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Teacher teacher2 = teacherService.getTeacherById(teacher.getTeacherId());
		Teacher teacher3 = null;
		if (teacher.getPhone() != null && !teacher.getPhone().equals("")) {
			teacher3 = teacherService.getPhone(teacher.getTeacherId(), teacher.getPhone());
		}
		if ((!currTeacher.getTeacherType().equals("1") && !currTeacher.getTeacherType().equals("2"))
				&& currTeacher.getTeacherId() != teacher2.getTeacherId() && !currTeacher.getTeacherType().equals("3")
				&& !currTeacher.getTeacherType().equals("4")) {
			output(response, JsonUtil.buildFalseJson("1", "您没有修改教师的权限!"));
		} else if (teacher3 != null && currTeacher.getTeacherId() != teacher2.getTeacherId()
				&& teacher.getPhone() != teacher2.getPhone()) {
			output(response, JsonUtil.buildFalseJson("2", "该号码已存在!"));
		} else {
			try {
				System.out.println("获取的年级为：" + request.getParameter("grade"));
				if (teacher.getGrade().equals("")) {
					teacher.setGrade(String.valueOf(0));
				} else {
					teacher.setGrade("," + teacher.getGrade() + ",");
				}
				if (!teacher.getPassword().equals(teacher2.getPassword())) {
					teacher.setPassword(MD5Util.MD5Encode(teacher.getPassword(), "utf-8"));
				}
				teacher.setUpdateTime(new Date());
				int len = teacherService.updateTeacher(teacher);
				// 修改教师权限表
				String[] grade = request.getParameter("grade").split(",");
				if (grade != null) {
					//1.删除掉权限表
					teacherAuthorityService.deleteAuthorityByteacherId(teacher.getTeacherId());
					
					//2.增加新的权限
					List<TeacherAuthority> addtAuthorityList = new  ArrayList<TeacherAuthority>();
					for (int i = 0; i < grade.length; i++) {
						if(null !=grade[i] && !grade[i].isEmpty()){
							TeacherAuthority ta1 =new TeacherAuthority();
							ta1.setCreateTime(new Date());
							ta1.setTeacherId(teacher.getTeacherId());
							ta1.setGrade(grade[i]);
							ta1.setPaperAuthority(3);
							ta1.setHandoutAuthority(3);
							ta1.setQuestionsAuthority(3);
//							teacherAuthorityService.addTeacherAuthority(ta1);
							addtAuthorityList.add(ta1);
						}
						
					}
					teacherAuthorityService.addTeacherAuthoritybyList(addtAuthorityList);
		
//					List<TeacherAuthority> taList = teacherAuthorityService.getTeacherIdById(teacher.getTeacherId());
//					for (TeacherAuthority ta : taList) {
//						if (ta.getGrade() != null) {
//							for (int i = 0; i < grade.length; i++) {
//								if (!ta.getGrade().equals(grade[i])) {
//									TeacherAuthority ta1 = teacherAuthorityService.findIsTeacherGradeById(teacher.getTeacherId(),
//											grade[i]);
//									log.info("ta1 :"+ ta1);
//									if (ta1 == null ) {						
//										ta1 = new TeacherAuthority();
//										ta1.setCreateTime(new Date());
//										ta1.setTeacherId(teacher.getTeacherId());
//										ta1.setGrade(grade[i]);
//										ta1.setPaperAuthority(3);
//										ta1.setHandoutAuthority(3);
//										ta1.setQuestionsAuthority(3);
//										teacherAuthorityService.addTeacherAuthority(ta1);
//									}else {
//										log.info("getTaId :"+ ta1.getTaId());
//										log.info("getGrade  :    "+ ta1.getGrade() );
//										teacherAuthorityService.deleteAuthorityById(ta.getTaId());
//									}
//								}
//							}
//						}
//					}
				}
				Log log = new Log();
				log.setStartTime(new Date());
				log.setOperationId(currTeacher.getTeacherId().toString());
				log.setOperationName(currTeacher.getTeacherName());
				log.setOperationResult("成功");
				log.setOperationType("修改");
				log.setModular("角色管理");
				log.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】修改教师【" + teacher.getTeacherName() + "】成功");
				log.setEndTime(new Date());
				logService.saveLog(log);
				output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
			} catch (Exception e) {
				e.printStackTrace();
				Log log = new Log();
				log.setStartTime(new Date());
				log.setOperationId(currTeacher.getTeacherId().toString());
				log.setOperationName(currTeacher.getTeacherName());
				log.setOperationResult("失败");
				log.setOperationType("修改");
				log.setModular("角色管理");
				log.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】修改教师【" + teacher.getTeacherName() + "】失败");
				log.setEndTime(new Date());
				logService.saveLog(log);
				output(response, JsonUtil.buildFalseJson("5", "修改失败!"));
			}
		}
	}

	/**
	 * 删除教师信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteteacher", method = RequestMethod.POST)
	public void deleteteacher(Integer teacherId, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Teacher teacherName = teacherService.getTeacherById(teacherId);
		ClassNo classNo = classNoService.getStringClassById(teacherName.getClassId());
		if (!currTeacher.getTeacherType().equals("1") && !currTeacher.getTeacherType().equals("2")
				&& !currTeacher.getTeacherType().equals("3") && !currTeacher.getTeacherType().equals("4")) {
			Log log = new Log();
			log.setStartTime(new Date());
			log.setOperationId(currTeacher.getTeacherId().toString());
			log.setOperationName(currTeacher.getTeacherName());
			log.setOperationResult("成功");
			log.setOperationType("删除");
			log.setModular("角色管理");
			log.setOperationContent(
					"管理员【" + currTeacher.getTeacherName() + "】删除教师[" + teacherName.getTeacherName() + "失败!");
			log.setEndTime(new Date());
			logService.saveLog(log);
			output(response, JsonUtil.buildFalseJson("2", "您没有删除教师的权限!"));
		} else if (classNo != null) {
			output(response, JsonUtil.buildFalseJson("3", "该教师已有班级，暂时不能删除!"));
		} else {
			try {
				teacherService.deleteTeacher(teacherId);// 教师信息表
				//删除角色权限修改 by zhangzhaojian 2017.11.17
				teacherAuthorityService.deleteAuthorityByteacherId(teacherId);// 角色权限表
				
//				List<TeacherAuthority> taList = teacherAuthorityService.getTeacherIdById(teacherId);
//				for (TeacherAuthority ta : taList) {
//					if (ta != null) {
//						teacherAuthorityService.deleteAuthorityById(ta.getTaId());// 角色权限表
//					}
//				}
				userRoleService.deleteUserRoleById(teacherId);// 用户角色表
				Log log = new Log();
				log.setStartTime(new Date());
				log.setOperationId(currTeacher.getTeacherId().toString());
				log.setOperationName(currTeacher.getTeacherName());
				log.setOperationResult("成功");
				log.setOperationType("删除");
				log.setModular("角色管理");
				log.setOperationContent(
						"管理员【" + currTeacher.getTeacherName() + "】成功删除教师[" + teacherName.getTeacherName());
				log.setEndTime(new Date());
				logService.saveLog(log);
				output(response, JsonUtil.buildFalseJson("0", "删除成功"));
			} catch (NumberFormatException e) {
				Log log = new Log();
				log.setStartTime(new Date());
				log.setOperationId(currTeacher.getTeacherId().toString());
				log.setOperationName(currTeacher.getTeacherName());
				log.setOperationResult("失败");
				log.setOperationType("删除");
				log.setModular("角色管理");
				log.setOperationContent(
						"管理员【" + currTeacher.getTeacherName() + "】删除教师[" + teacherName.getTeacherName() + "失败");
				log.setEndTime(new Date());
				logService.saveLog(log);
				output(response, JsonUtil.buildFalseJson("1", "删除失败"));
			}
		}
	}

	@RequestMapping("/searchbyname")
	public void searchByName(HttpServletRequest request, HttpServletResponse response) {
		try {
			String content = request.getParameter("content");
			List<Teacher> list = teacherService.searchByName(content);
			log.info("模糊查询获取数据成功");
			output(response, JsonUtil.buildJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("模糊查询获取数据失败！");
			output(response, JsonUtil.buildFalseJson("1", "获取失败"));
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param response
	 * @param request
	 * @param modifyPassword
	 * @param password
	 * @param confirmPassword
	 * @param model
	 */
	@RequestMapping("/preservationPass")
	public void preservationPass(HttpServletResponse response, HttpServletRequest request, String modifyPassword,
			String password, String confirmPassword, Model model) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Teacher teacher = teacherService.getTeacherById(currTeacher.getTeacherId());
		if (teacher == null) {
			output(response, JsonUtil.buildFalseJson("1", "该用户不存在!"));
		} else {
			if (MD5Util.MD5Encode(password, "utf-8").equals(teacher.getPassword())
					&& confirmPassword.equals(modifyPassword)) {
				teacherService.updatePassword(teacher.getTeacherId(), MD5Util.MD5Encode(modifyPassword, "utf-8"));

				output(response, JsonUtil.buildFalseJson("0", "修改密码成功,请牢记你的密码!"));

			} else if (!MD5Util.MD5Encode(password, "utf-8").equals(teacher.getPassword())) {

				output(response, JsonUtil.buildFalseJson("2", "原始密码输入错误，请重新输入!"));
			} else {
				output(response, JsonUtil.buildFalseJson("3", "确认密码与和新密码不匹配"));
			}
		}
	}
}
