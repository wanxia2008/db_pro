package com.db.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.ClassNo;
import com.db.entity.Course;
import com.db.entity.Grade;
import com.db.entity.Role;
import com.db.entity.SchoolYear;
import com.db.entity.SchoolZone;
import com.db.entity.SeasonType;
import com.db.entity.Student;
import com.db.entity.StudentCareer;
import com.db.entity.Subject;
import com.db.entity.Teacher;
import com.db.service.ClassNoService;
import com.db.service.CourseService;
import com.db.service.GradeService;
import com.db.service.RoleService;
import com.db.service.SchoolYearService;
import com.db.service.SchoolZoneService;
import com.db.service.SeasonTypeService;
import com.db.service.StudentCareerService;
import com.db.service.StudentService;
import com.db.service.SubjectService;
import com.db.service.TeacherService;
import com.db.util.BaseUtil;
import com.db.util.CommonUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;

@Controller
@RequestMapping("/classes")
public class ClassesController extends BaseUtil {

	@Resource
	private SubjectService subjectservice;
	@Resource
	private SeasonTypeService seasonservice;
	@Resource
	private GradeService gradeservice;
	@Resource
	private SchoolZoneService schoolservice;
	@Resource
	private ClassNoService classnoservice;
	@Resource
	private StudentService studentService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private SchoolYearService schoolYearService;
	@Resource
	private StudentCareerService studentCareerService;

	@Resource
	private StudentCareerService careerService;
	@Resource
	private CourseService courseService;
	@Resource
	private RoleService roleService;

	private Logger log = Logger.getLogger("ClassesController");

	private HttpSession sesssion;

	/**
	 * 班级管理列表
	 * 
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @param schoolName
	 * @return
	 */
	@RequestMapping("/classmanage")
	public ModelAndView Classesmanage(HttpServletRequest request, Integer pageNo, Integer pageSize, String name,
			Integer seasontype, Integer subject, Integer yearId) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = 0;
		Map<String, Object> map = new HashMap<>();
		sesssion = request.getSession();
		Teacher teacher = (Teacher) sesssion.getAttribute("currTeacher");
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("schoolArea", teacher.getCampus());
		if (seasontype != null) {
			map.put("seasonType", seasontype);
		}
		if (subject != null) {
			map.put("subject", subject);
		}
		if (yearId != null) {
			map.put("year", yearId);
		}
		if (name != null) {
			map.put("content", name);
		}
		List<ClassNo> list = null;
		if (!teacher.getTeacherType().equals("1")) {
			list = classnoservice.findSchoolIdList(map);
			rowsCount = classnoservice.schoolCount(map);
		} else {
			list = classnoservice.getAllClasses1(map);
			rowsCount = classnoservice.getCount();
		}
		// 查询该班级是否有学生和教师
//		for (ClassNo classNo : list) {
//			List<Teacher> teachers = teacherService.getTeacherByClassId(classNo.getClassId(), classNo.getSchoolArea());
//			List<Student> students = studentService.getStudentByClassandSchool(classNo.getClassId(),
//					classNo.getSchoolArea());
//			if (teachers != null && !teachers.isEmpty()) {
//				classNo.setTeachers(teachers);
//			} else {
//				classNo.setTeachers(null);
//			}
//			if (students != null && !students.isEmpty()) {
//				classNo.setStudentlist(students);
//			} else {
//				classNo.setStudentlist(null);
//			}
//		}
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<Subject> subjects = subjectservice.getSubject();
		List<SeasonType> seasonTypes = seasonservice.getSeasontype();
		List<Grade> grades = gradeservice.getGrade();
		List<SchoolYear> years = schoolYearService.getYear();

		ModelAndView view = new ModelAndView();
		view.setViewName("classmanage/classmanage");
		if (!list.isEmpty() && list != null) {
			view.addObject("classList", list);
		} else {
			view.addObject("classList", null);
		}
		view.addObject("subjectList", subjects);
		view.addObject("seasonList", seasonTypes);
		view.addObject("gradesList", grades);
		view.addObject("yearList", years);
		view.addObject("page", pageNo);
		view.addObject("subject", subject);
		view.addObject("seasontype", seasontype);
		view.addObject("yearId", yearId);
		view.addObject("pageSize", pageSize);
		view.addObject("totalPages", totalPages);
		return view;
	}

	@RequestMapping("/classstudent")
	public String Classstudent() {
		return "classmanage/class_student";
	}

	/**
	 * 添加班级页面
	 * 
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @param schoolName
	 * @return
	 */
	@RequestMapping("/newclasses")
	public ModelAndView Newclasses(HttpServletResponse response, Integer pageNo, Integer pageSize, String schoolName,
			HttpServletRequest request) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<Subject> subjects = null;
		List<Grade> grades = null;
		List<SchoolZone> schoolZones = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			subjects = subjectservice.getSubject();
			grades = gradeservice.getGrade();
			if (currTeacher.getTeacherType().equals("1")) {
				schoolZones = schoolservice.getSchoolZone(schoolName);
			} else {
				schoolZones = schoolservice.findSchoolList(currTeacher.getCampus());
			}
		} else {
			subjects = subjectservice.getTeacherIsSubject(currTeacher.getSubject());
			String[] grade = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(grade);
			grades = gradeservice.getGradeByIds(gradeList);
			schoolZones = schoolservice.findSchoolList(currTeacher.getCampus());
		}
		List<SeasonType> seasonTypes = seasonservice.getSeasontype();
		List<SchoolYear> years = schoolYearService.getYear();
		ModelAndView view = new ModelAndView();
		view.addObject("yearList", years);
		view.setViewName("classmanage/newclasses");
		view.addObject("subjectList", subjects);
		view.addObject("seasonList", seasonTypes);
		view.addObject("gradesList", grades);
		view.addObject("schoolList", schoolZones);
		return view;
	}

	/**
	 * 添加班级操作
	 */
	@RequestMapping("/addclasses")
	public void addClasses(ClassNo classNo, HttpServletResponse response, HttpServletRequest request) {
		ClassNo cn = classnoservice.addIsExistence(classNo.getSchoolArea(), classNo.getGrade(), classNo.getSubject(),
				classNo.getClassName());
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");

		String teacherId = request.getParameter("teacherId");
		log.info("teacherId:  "+teacherId);
		Teacher teacher = teacherService.getTeacherById( Integer.valueOf(teacherId) );
		String classids = teacher.getClassId();
		log.info("classids:  "+classids);
		Teacher teacher2 = new Teacher();
		
		
		if (cn != null) {
			output(response, JsonUtil.buildFalseJson("2", "该班级已存在，请重新添加!"));
		} else if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("4", "您没有添加班级的权限!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				classNo.setCreateTime(new Date());
				classNo.setActualNumber(0);// 人数依次加一
				if (classNo.getMaxNumber() == null) {
					classNo.setMaxNumber(1);
				}
				classNo.setActualCourse(0);
				classnoservice.addClasses(classNo);// 返回主键
				ClassNo classNo3 = classnoservice.getClassById(classNo.getClassId());
				classids = classids + "," + String.valueOf(classNo.getClassId());
				
				int len  = 1;
				if (null !=teacherId && !teacherId.isEmpty()){
					//添加教师到班级
					teacher2.setClassId(classids);
					teacher2.setTeacherId(Integer.valueOf(teacherId)) ;
					teacher2.setUpdateTime(new Date());
					classNo.setUpdateTime(new Date());
					classNo.setTeacherId(Integer.valueOf(teacherId));
					classnoservice.updateClasses(classNo);
					len = teacherService.updateTeacherClass(teacher2);
				}
				
		
				if (classNo3 != null && len > 0) {
					output(response, JsonUtil.buildFalseJson("0", "添加班级成功!"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "添加班级失败!"));
				}
			} else {
				if (!currTeacher.getCampus().equals(classNo.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("3", "您添加的校区与您所在的校区不一致!"));
				} else {
					classNo.setCreateTime(new Date());
					classNo.setActualNumber(0);// 人数依次加一
					if (classNo.getMaxNumber() == null) {
						classNo.setMaxNumber(1);
					}
					classNo.setActualCourse(0);
					classnoservice.addClasses(classNo);// 返回主键
					ClassNo classNo3 = classnoservice.getClassById(classNo.getClassId());
					classids = classids + "," + String.valueOf(classNo.getClassId());
					
					//添加教师到班级
					teacher2.setClassId(classids);
					teacher2.setTeacherId(Integer.valueOf(teacherId)) ;
					teacher2.setUpdateTime(new Date());
					classNo.setUpdateTime(new Date());
					classNo.setTeacherId(Integer.valueOf(teacherId));
					classnoservice.updateClasses(classNo);
					int len = teacherService.updateTeacherClass(teacher2);
	
					if (classNo3 != null && len > 0) {
						output(response, JsonUtil.buildFalseJson("0", "添加班级成功!"));
					} else {
						output(response, JsonUtil.buildFalseJson("1", "添加班级失败!"));
					}
				}
			}
		}
	}

	/**
	 * 查看班级
	 * 
	 * @param classId
	 * @return
	 */
	@RequestMapping("/toupdateclasses")
	public ModelAndView Updateclasses(@RequestParam("classId") String classId, int page, Integer pageNo,
			Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		ModelAndView view = new ModelAndView();
		int id = Integer.parseInt(classId);
		int rowsCount = studentCareerService.getCountClassNo(Integer.valueOf(classId), 2);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		ClassNo classNo = classnoservice.getClassById(id);
		List<StudentCareer> list = studentCareerService.getStudentByClassNo(Integer.valueOf(classId), 2);
		// 根据编号获取学生列表
		List<Teacher> list4 = teacherService.getTeacherByClassId(id, classNo.getSchoolArea());
		int rowsCountTeacher = teacherService.getClassNoById(id, classNo.getSchoolArea());
		int totalPages1 = 0;
		if (rowsCountTeacher % pageSize == 0) {
			totalPages1 = rowsCountTeacher / pageSize;
		} else {
			totalPages1 = (rowsCountTeacher / pageSize) + 1;
		}
		for (int i = 0; i < list4.size(); i++) {
			list4.get(i).setGrade(classNo.getGradelist().get(0).getGradeName());
			list4.get(i).setSubjectName(classNo.getSubjectlist().get(0).getSubjectName());
		}
		if (list4 != null && !list4.isEmpty()) {
			view.addObject("classTeacher", list4);
		} else {
			view.addObject("classTeacher", null);
		}
		if (list != null && !list.isEmpty()) {
			view.addObject("studentList", list);
		} else {
			view.addObject("studentList", null);
		}
		List<Role> roles = roleService.getRole();
		List<Subject> list2 = subjectservice.getSubject();// 获取课程列表
		List<SeasonType> seasonTypes = seasonservice.getSeasontype();
		List<SchoolYear> years = schoolYearService.getYear();
		List<SchoolZone> schoolZones = schoolservice.getSchoolZone("");
		List<Grade> grades = gradeservice.getGrade();
		view.setViewName("classmanage/updateclasses");
		view.addObject("classes", classNo);
		view.addObject("roleList", roles);
		view.addObject("subjectList", list2);
		view.addObject("yearList", years);
		view.addObject("seasonList", seasonTypes);
		view.addObject("page", pageNo);
		view.addObject("pageNo", page);
		view.addObject("gradesList", grades);
		view.addObject("pageSize", pageSize);
		view.addObject("totalPages", totalPages);
		view.addObject("totalPages1", totalPages1);
		view.addObject("schoolList", schoolZones);
		return view;
	}

	/**
	 * 修改班级
	 * 
	 * @param classNo
	 * @param response
	 */
	@RequestMapping("/updateclass")
	public void updateClass(ClassNo classNo, HttpServletResponse response, HttpServletRequest request) {
		ClassNo classNo2 = classnoservice.judgeSameClassName(classNo.getClassName(), classNo.getClassId());
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		ClassNo classNo3 = classnoservice.getClassById(classNo.getClassId());
		if (classNo2 != null) {
			output(response, JsonUtil.buildFalseJson("1", "所输入的班级名称已存在，请重换一个!"));
		} else if (classNo3.getActualCourse() != 0 && !classNo.getCourseNumber().equals(classNo3.getCourseNumber())) {
			output(response, JsonUtil.buildFalseJson("4", "改班级已排课，暂不能修改排课总数!"));
		} else if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("5", "你没有修改班级的权限!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				classNo.setUpdateTime(new Date());
				int len = classnoservice.updateClasses(classNo);
				if (len > 0) {
					log.info("修改成功");
					output(response, JsonUtil.buildFalseJson("0", "修改成功"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "修改失败"));
				}
			} else {
				if (!classNo.getSchoolArea().equals(currTeacher.getCampus())) {
					output(response, JsonUtil.buildFalseJson("2", "您修改的校区与您所在的校区不一致!"));
				} else {
					classNo.setUpdateTime(new Date());
					int len = classnoservice.updateClasses(classNo);
					if (len > 0) {
						log.info("修改成功");
						output(response, JsonUtil.buildFalseJson("0", "修改成功"));
					} else {
						output(response, JsonUtil.buildFalseJson("1", "修改失败"));
					}
				}
			}
		}
	}

	/**
	 * 检测是否有符合教师的班级
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getallteacher")
	public void getAllTeacher(HttpServletRequest request, Integer subjectId,Integer gradeId,Integer schoolId,
			HttpServletResponse response,Integer roleId,Integer pageNo,Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		try {
			int totalCount = (int) teacherService.getTeacherByGradeandSubjectCount(gradeId, subjectId, schoolId,roleId);// 获取总数
			PageUtil util = new PageUtil(totalCount, pageNo, pageSize);
			List<Teacher> list = teacherService.getTeacherByGradeandSubject(gradeId, subjectId, schoolId,
					util.getStartPos(), pageSize,roleId);
			Grade grade = gradeservice.getGradeById(gradeId);
			List<Subject> subjects = subjectservice.getSubjectById(subjectId);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setGrade(grade.getGradeName());
				list.get(i).setSubjectName(subjects.get(0).getSubjectName());
			}
			//log.info(list.leng);
			log.info("获取学员信息成功");
			output(response, JsonUtil.buildJsonByTotalCount(list, util.getTotalPageCount()));
		} catch (NumberFormatException e) {

			output(response, JsonUtil.buildFalseJson("2", "获取失败"));
		}
	}

	/**
	 * 判断是否有可以加入该条件下的学员
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param schoolId
	 * @param classId
	 * @param subjectId
	 * @param gradeId
	 * @param seasonId
	 * @param year
	 * @param content
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getallstudent")
	public void getAllStudent(Integer pageNo, Integer pageSize, Integer schoolId, Integer classId, Integer subjectId,
			Integer gradeId, Integer seasonId, Integer year, String content, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("传来的校区编号：" + schoolId);
		sesssion = request.getSession();
		Map<String, Object> map = new HashMap<>();
		if (pageNo == null) {
			map.put("pageNo", 1);
		} else {
			map.put("pageNo", (pageNo - 1) * pageSize);
		}
		if (pageSize == null) {
			map.put("pageSize", 5);
		} else {
			map.put("pageSize", pageSize);
		}
		map.put("grade", gradeId);
		map.put("subject", subjectId);
		map.put("year", year);
		map.put("content", content);
		// map.put("classId", classId);
		map.put("schoolId", schoolId);
		map.put("seasonId", seasonId);
		ClassNo cn = classnoservice.getClassById(classId);
		if (cn != null && cn.getClassNumber() == cn.getClassNumber()) {
			output(response, JsonUtil.buildFalseJson("2", "添加的人数已达到最大值，请加大班级人数!"));
		} else {
			List<Student> list = studentService.getIsAllStudent(map);
			int totalcount = (int) studentService.getAllStudentCount(map);
			if (!list.isEmpty() && list != null) {
				log.info("获取学员信息成功");
				PageUtil pageUtil = new PageUtil(totalcount, pageNo, pageSize);
				output(response, JsonUtil.buildJsonByTotalCount(list, pageUtil.getTotalPageCount()));
			} else {
				output(response, JsonUtil.buildFalseJson("1", "没有数据"));
			}
		}
		// List<StudentCareer> list = studentCareerService.getAllStudent(map);
	}

	/**
	 * 添加学员到学员生涯表
	 * 
	 * @param classId
	 * @param studentId
	 * @param schoolId
	 * @param response
	 */
	@RequestMapping("/updatestudentbyclass")
	public void updateStudentByClass(Integer classId, Integer studentId, Integer schoolId, HttpServletResponse response,
			Integer subjectId, Integer year, Integer gradeId, Integer seasonId) {
		Student student = studentService.getStudentById(studentId);
		if (student.getStudentStatus() == 2) {
			output(response, JsonUtil.buildFalseJson("1", "该学生尚未缴费，无法添加~"));
		} else if (student.getStudentStatus() == 3) {
			// //判断某年某年级某课程该学生是否已经参加了入学考试
			StudentCareer scCareer = studentCareerService.findStudentCareer(studentId, classId);
			if (scCareer != null) {
				output(response, JsonUtil.buildFalseJson("2", "该学生已分个该班!"));
			} else {
				try {
					StudentCareer career = new StudentCareer();
					career.setCreateTime(new Date());
					career.setClassId(classId);
					career.setStudentId(studentId);
					career.setStatus(2);// 已分班
					career.setYear(year);
					career.setSubject(subjectId);
					career.setGrade(gradeId);
					career.setSeasonType(schoolId);
					studentCareerService.addStudentCareer(career);// 返回主键
					// 修改学员的入学状态
					student.setUpdateTime(new Date());
					student.setClassNo(classId.toString());
					student.setStudentStatus(4);// 已分班
					studentService.modityClassNo(student);
					output(response, JsonUtil.buildFalseJson("0", "添加学员成功!"));
				} catch (Exception e) {
					output(response, JsonUtil.buildFalseJson("1", "添加学员失败!"));
				}
			}
		}
	}

	/**
	 * 删除学员(结束课程)
	 * 
	 * @param studentId
	 * @param classId
	 * @param status
	 * @param response
	 */
	@RequestMapping("/deletestudentbyclass")
	public void deleteStudentByClass(Integer studentId, Integer classId, Integer status, HttpServletResponse response) {
		// 判断是课程修完（假删除）还是只是删除学生（真删除）
		StudentCareer career = new StudentCareer();
		career.setStudentId(studentId);
		career.setClassId(classId);
		career.setUpdateTime(new Date());
		career.setStatus(3);// 假删除，将状态改为3
		career.setIsEndCourse(1);// 结束课程
		Student student = studentService.getStudentById(studentId);
		student.setUpdateTime(new Date());
		int len = 0;
		if (status == 1) {// 假删除
			student.setStatus(2);
			student.setStudentStatus(5);
			studentService.updateStudentStatus(student);
			len = studentCareerService.updateCareerByStatus(career);
		} else if (status == 2) {// 真删除
			List<StudentCareer> careers = careerService.careerList(studentId, 0 ,100);
			if (careers == null || careers.isEmpty()) {
				student.setUpdateTime(new Date());
				student.setClassNo(null);
				student.setStudentStatus(3);
				studentService.updateClassNo(student);
			}
//			student.setStatus(2);
//			student.setStudentStatus(3);
//			studentService.updateStudentStatus(student);
			len = studentCareerService.deleteCareer(studentId, classId);
		}
		
		if (len > 0) {
			output(response, JsonUtil.buildFalseJson("0", "结束课程成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "结束课程失败!"));
		}
	}

	/**
	 * 结束课程
	 * 
	 * @param response
	 * @param classId
	 */
	@RequestMapping("/endCurriculum")
	public void endCurriculum(HttpServletResponse response, Integer classId, HttpServletRequest request) {
		ClassNo classNo = classnoservice.getClassById(classId);
		List<StudentCareer> careers = studentCareerService.findClassIdList(classId);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("3", "您没有结束课程的权限!"));
		} else if (classNo.getIsEndCourse() == 1) {
			output(response, JsonUtil.buildFalseJson("2", "该课程已结束"));
		} else {
			try {
				if (currTeacher.getTeacherType().equals("1")) {
					if (careers != null && !careers.isEmpty()) {
						for (StudentCareer career : careers) {
							studentCareerService.updateCareById(career.getId());
							Student student = studentService.getStudentById(career.getStudentId());
							if (student != null) {
								student.setUpdateTime(new Date());
								student.setStudentStatus(5);
								studentService.updateStudentStatus(student);
							}
						}
					}
					classNo.setUpdateTime(new Date());
					classNo.setIsEndCourse(1);
					classnoservice.updateClasses(classNo);
					output(response, JsonUtil.buildFalseJson("0", "结束课程成功"));
				} else {
					if (!currTeacher.getCampus().equals(classNo.getSchoolArea())) {
						output(response, JsonUtil.buildFalseJson("4", "系统检测到您所在的校区与该班级不一致!"));
					} else {
						if (careers != null && !careers.isEmpty()) {
							for (StudentCareer career : careers) {
								studentCareerService.updateCareById(career.getId());
								Student student = studentService.getStudentById(career.getStudentId());
								if (student != null) {
									student.setUpdateTime(new Date());
									student.setStudentStatus(5);
									studentService.updateStudentStatus(student);
								}
							}
						}
						classNo.setUpdateTime(new Date());
						classNo.setIsEndCourse(1);
						classnoservice.updateClasses(classNo);
					}
				}
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "结束课程失败"));
			}
		}
	}

	/**
	 * 班级添加教师
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateteacherbyclass")
	public void updateTeacherByClass(HttpServletRequest request, HttpServletResponse response) {
		int status = 0;
		int classid = Integer.parseInt(request.getParameter("classId"));
		int teacherid = Integer.parseInt(request.getParameter("teacherId"));
		Teacher teacher = teacherService.getTeacherById(teacherid);
		String classids = teacher.getClassId();
		ClassNo classNo = classnoservice.getClassById(classid);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("4", "你没有添加教师的权限!"));
		} else if (classNo != null && classNo.getTeacherId() != 0) {
			output(response, JsonUtil.buildFalseJson("3", "班级目前已有老师!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				if (classids != "" && classids != null) {
					if (classids.indexOf(",") >= 0) {
						String[] classid1 = classids.split(",");
						for (int i = 0; i < classid1.length; i++) {
							if (String.valueOf(classid) == classid1[i]) {
								status = 1;
							}
						}
					} else {
						if (String.valueOf(classid) == classids) {
							status = 1;
						}
					}
					classids = classids + "," + String.valueOf(classid);
				} else {
					classids = String.valueOf(classid);
				}
				if (status == 1) {
					output(response, JsonUtil.buildFalseJson("2", "该教师已在班级教师名单上"));
				} else {
					Teacher teacher2 = new Teacher();
					teacher2.setClassId(classids);
					teacher2.setTeacherId(teacherid);
					teacher2.setUpdateTime(new Date());
					classNo.setUpdateTime(new Date());
					classNo.setTeacherId(teacherid);
					classnoservice.updateClasses(classNo);
					int len = teacherService.updateTeacherClass(teacher2);
					if (len > 0) {
						output(response, JsonUtil.buildFalseJson("0", "添加教师成功！"));
					} else {
						output(response, JsonUtil.buildFalseJson("1", "添加教师失败！"));
					}
				}
			} else {
				if (!currTeacher.getCampus().equals(classNo.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("5", "系统检测到您与该班级所在的校区不一致!"));
				} else {
					if (classids != "" && classids != null) {
						if (classids.indexOf(",") >= 0) {
							String[] classid1 = classids.split(",");
							for (int i = 0; i < classid1.length; i++) {
								if (String.valueOf(classid) == classid1[i]) {
									status = 1;
								}
							}
						} else {
							if (String.valueOf(classid) == classids) {
								status = 1;
							}
						}
						classids = classids + "," + String.valueOf(classid);
					} else {
						classids = String.valueOf(classid);
					}
					if (status == 1) {
						output(response, JsonUtil.buildFalseJson("2", "该教师已在班级教师名单上"));
					} else {
						Teacher teacher2 = new Teacher();
						teacher2.setClassId(classids);
						teacher2.setTeacherId(teacherid);
						teacher2.setUpdateTime(new Date());
						classNo.setUpdateTime(new Date());
						classNo.setTeacherId(teacherid);
						classnoservice.updateClasses(classNo);
						int len = teacherService.updateTeacherClass(teacher2);
						if (len > 0) {
							output(response, JsonUtil.buildFalseJson("0", "添加教师成功！"));
						} else {
							output(response, JsonUtil.buildFalseJson("1", "添加教师失败！"));
						}
					}
				}
			}
		}
	}

	/**
	 * 移除教师
	 * 
	 * @param request
	 * @param teacherId
	 * @param classId
	 * @param response
	 */
	@RequestMapping("/deleteteacherbyclass")
	public void deleteTeacherByClass(HttpServletRequest request, Integer teacherId, Integer classId,
			HttpServletResponse response) {
		int status = 0;// 标志
		String classid = null;
		int len = 0;
		Teacher teacher = teacherService.getTeacherById(teacherId);
		ClassNo classNo = classnoservice.getClassById(classId);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("2", "您没有删除教师的权限!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				if (teacher.getClassId().indexOf(",") >= 0) {
					String[] classids = teacher.getClassId().split(",");
					for (int i = 0; i < classids.length; i++) {
						if (classId == Integer.valueOf(classids[i])) {
							status = i;
						}
					}
					classid = CommonUtil.deleteToNewString(classids, status);
				} else if (String.valueOf(classId) == teacher.getClassId()) {
					classid = null;
				}
				teacher.setClassId(classid);
				teacher.setUpdateTime(new Date());
				classNo.setUpdateTime(new Date());
				classNo.setTeacherId(0);
				classnoservice.updateClasses(classNo);
				len = teacherService.updateTeacherClass(teacher);
			} else {
				if (!currTeacher.getCampus().equals(classNo.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("3", "系统检测到您与该班级所在的校区不一致!"));
				} else {
					if (teacher.getClassId().indexOf(",") >= 0) {
						String[] classids = teacher.getClassId().split(",");
						for (int i = 0; i < classids.length; i++) {
							if (classId == Integer.valueOf(classids[i])) {
								status = i;
							}
						}
						classid = CommonUtil.deleteToNewString(classids, status);
					} else if (String.valueOf(classId) == teacher.getClassId()) {
						classid = null;
					}
					teacher.setClassId(classid);
					teacher.setUpdateTime(new Date());
					classNo.setUpdateTime(new Date());
					classNo.setTeacherId(0);
					classnoservice.updateClasses(classNo);
					len = teacherService.updateTeacherClass(teacher);
				}
			}
			if (len > 0) {
				output(response, JsonUtil.buildFalseJson("0", "删除教师成功!"));
			} else {
				output(response, JsonUtil.buildFalseJson("1", "删除教师失败!"));
			}
		}
	}

	/**
	 * 删除班级
	 * 
	 * @param classId
	 * @param response
	 */
	@RequestMapping("/deleteclass")
	public void deleteClass(Integer classId, HttpServletResponse response, HttpServletRequest request) {
		List<StudentCareer> sCareer = careerService.isDistributionClass(classId);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		ClassNo classNo = classnoservice.getClassById(classId);
		List<Course> courses = courseService.findCourseDateList(classId, null);
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("3", "您没有删除班级的权限!"));
		} else if (sCareer != null && !sCareer.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("2", "班级目前已有学员加入，暂不能删除!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				if (courses.size()>0) {
					for (Course course :courses) {
						courseService.deleteCourseById(course.getCourseId());
					}
				}
				int len = classnoservice.deleteClasses(classId);
				if (len > 0) {
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
				}
			}else {
				if (!currTeacher.getCampus().equals(classNo.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("4", "您不能删其他校区的班级!"));
				}else {
					if (courses.size() > 0) {
						for (Course course :courses) {
							courseService.deleteCourseById(course.getCourseId());
						}
					}
					int len = classnoservice.deleteClasses(classId);
					if (len > 0) {
						output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
					} else {
						output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
					}
				}
			}
		}
	}

	/**
	 * 添加任务时普通任务对象分业
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@RequestMapping("/ajaxclassmanage")
	public void ajaxclassmanage(Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response, Integer schoolId, Integer subject, String className) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 3;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Map<String, Object> map = new HashMap<>();
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("className", className);
		map.put("subject", subject);
		List<ClassNo> list = null;
		int totalcount = 0;
		if (currTeacher.getTeacherType().equals("1")) {
			map.put("schoolId", schoolId);
			list = classnoservice.getClassByTeacherclass(map);
			totalcount = classnoservice.findClassCount(map);
		} else {
			map.put("schoolId", currTeacher.getCampus());
			if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
				if (currTeacher.getClassId() != null) {
					map.put("classId", currTeacher.getClassId());
					list = classnoservice.getClassByTeacherclass(map);
					totalcount = classnoservice.findClassCount(map);
				}
			} else {
				list = classnoservice.getClassByTeacherclass(map);
				totalcount = classnoservice.findClassCount(map);
			}
		}
		PageUtil pageUtil = new PageUtil(totalcount, pageNo, pageSize);
		if (list != null && !list.isEmpty()) {
			output(response, JsonUtil.buildJsonByTotalCount(list, pageUtil.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有更多数据!"));
		}
	}

	/**
	 * 查看班级课程情况(课程历史记录)
	 * 
	 * @param pageNo
	 * @param classId
	 */
	@RequestMapping("/findSubjectList")
	public ModelAndView findSubjectList(Integer pageNo, Integer classId, Integer pageSize, String date,
			HttpServletRequest request) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 15;
		}
		ModelAndView mv = new ModelAndView();
		int worsCount = 0;
		int totalPages = 0;
		List<Course> courses = null;
		HttpSession session = request.getSession();
		List<ClassNo> cnList = null;
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			courses = courseService.findClassCourseList(null, (pageNo - 1) * pageSize, pageSize, date, null);
			worsCount = courseService.getClassCourseCount(null, date);
			cnList = classnoservice.findClassByIdList();
		} else {
			if (currTeacher.getClassId() != null) {
				
				String getClassId = currTeacher.getClassId();
				String[] arr=getClassId.split(",");
				List<Integer> classidlist =new ArrayList<Integer>();
				for (int i = 0; i < arr.length; i++) {
					classidlist.add(Integer.valueOf(arr[i].trim()));
			            }
				log.info("getClassId :"+getClassId);
				log.info("classidlist :"+classidlist.toString());
	
				if (classidlist.size()>1){
					courses = courseService.findClassCourseListbyIds(classidlist,
							(pageNo - 1) * pageSize, pageSize, date, null);
					worsCount = courseService.getClassCourseCountbyIds(classidlist,date);
					System.out.println(true);
					
				}else{
					courses = courseService.findClassCourseList(Integer.valueOf(currTeacher.getClassId()),
					(pageNo - 1) * pageSize, pageSize, date, null);
					worsCount = courseService.getClassCourseCount(Integer.valueOf(currTeacher.getClassId()), date);
				}
				
				
				
			}
		}
		if (courses != null && !courses.isEmpty()) {
			mv.addObject("courseList", courses);
		} else {
			mv.addObject("courseList", null);
		}
		if (worsCount % pageSize == 0) {
			totalPages = worsCount / pageSize;
		} else {
			totalPages = (worsCount / pageSize) + 1;
		}
		// 筛选出已排课的日期
		List<Course> date_list = courseService.getDateByClass(null, classId);
		mv.addObject("pageSize", pageSize);
		mv.addObject("page", pageNo);
		mv.addObject("classId", classId);
		mv.addObject("totalPages", totalPages);
		mv.addObject("dateList", date_list);
		mv.addObject("cnList", cnList);
		mv.setViewName("classCurriculum/classCourse");
		return mv;
	}

	/**
	 * 编辑课程或者添加课程
	 * @param pageNo
	 * @param classId
	 * @param pageSize
	 * @param date
	 * @param request
	 * @return
	 */
	@RequestMapping("/findclassCourseList")
	public ModelAndView findclassCourseList(Integer pageNo, Integer classId, Integer pageSize, String date,
			HttpServletRequest request){
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		ModelAndView mv = new ModelAndView();
		int worsCount = 0;
		int totalPages = 0;
		List<Course> courses = null;
		List<ClassNo> cnList = null;
		// 获取当天日期接下来的周末
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance(Locale.CHINA);// 日历
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		System.out.println("离当前最近的周末为：" + dateFormat.format(calendar.getTime()));
		// 拿最近的周末去取得课程表
		courses = courseService.findClassCourseList(classId, (pageNo - 1) * pageSize, pageSize, date, null);
		worsCount = courseService.getClassCourseCount(classId, date);
		if (courses != null && !courses.isEmpty()) {
			mv.addObject("courseList", courses);
		} else {
			mv.addObject("courseList", null);
		}
		if (worsCount % pageSize == 0) {
			totalPages = worsCount / pageSize;
		} else {
			totalPages = (worsCount / pageSize) + 1;
		}
		// 筛选出已排课的日期
		List<Course> date_list = courseService.getDateByClass(null, classId);

		mv.addObject("pageSize", pageSize);
		mv.addObject("page", pageNo);
		mv.addObject("date", date);
		mv.addObject("classId", classId);
		mv.addObject("totalPages", totalPages);
		mv.addObject("dateList", date_list);
		mv.addObject("cnList", cnList);
		mv.setViewName("classCurriculum/addclasscourse");
		return mv;
	}
	
	/**
	 * 添加课程（单个）
	 * @param response
	 * @param classId
	 * @param courseDate
	 */
	@RequestMapping("/addspringfalllesson")
	public void addDourse(HttpServletResponse response, Integer classId, String courseDate,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<Course> couList = courseService.findCourseDateList(classId,courseDate);
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("1", "您没有添加课程的权限!"));
		} else if (!couList.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("3", "您选择的时间段此班级已有课程，请重新选择!"));
		} else {
			List<Course> courses = null;
			ClassNo classNo = classnoservice.getClassById(classId);
			if (classNo.getSeasonType() == 1 || classNo.getSeasonType() == 2 || classNo.getSeasonType() == 3) {
				courses = courseService.findClassCourseList(classId, null, null, null, 0);
				if (!courses.isEmpty() && courses.size() >= classNo.getCourseNumber()) {
					output(response, JsonUtil.buildFalseJson("2", "系统检测到该班级所有课程已排完!"));
				} else {
					try {
						Course course = new Course();
						course.setSubjectId(classNo.getSubject());
						course.setCourseType(0);// 春秋季排课
						course.setCreateTime(new Date());
						course.setCourseDate(courseDate);
						course.setClassId(classId);
						course.setRoomId(courses.get(courses.size() - 1).getRoomId());
						course.setCourseTimes(courses.get(courses.size() - 1).getCourseTimes());
						course.setCourseDate(course.getCourseDate());
						courseService.addCourse(course);// 返回插入的主键
						output(response, JsonUtil.buildFalseJson("0", "排课成功!"));
					} catch (Exception e) {
						output(response, JsonUtil.buildFalseJson("1", "排课失败!"));
					}
				}
			} else {
				courses = courseService.findClassCourseList(classId, null, null, null, 1);
				if (!courses.isEmpty() && courses.size() >= classNo.getActualCourse()) {
					output(response, JsonUtil.buildFalseJson("2", "系统检测到该班级所有课程已排完!"));
				} else {
					try {
						Course course = new Course();
						course.setSubjectId(classNo.getSubject());
						course.setCourseType(1);// 春秋季排课
						course.setCreateTime(new Date());
						course.setRoomId(courses.get(courses.size() - 1).getRoomId());
						course.setCourseDate(courseDate);
						course.setClassId(classId);
						course.setCourseTimes(courses.get(courses.size() - 1).getCourseTimes());
						course.setCourseDate(course.getCourseDate());
						courseService.addCourse(course);// 返回插入的主键
						output(response, JsonUtil.buildFalseJson("0", "排课成功!"));
					} catch (Exception e) {
						output(response, JsonUtil.buildFalseJson("1", "排课失败!"));
					}
				}
			}
		}
	}
	
	/**
	 * 检测是否有排课
	 * 
	 * @param classId
	 * @param response
	 */
	@RequestMapping("/testingCurriculum")
	public void testingCurriculum(Integer classId, HttpServletResponse response) {
		List<Course> courses = courseService.findClassCourseList(classId, null, null, null, null);
		if (courses != null && !courses.isEmpty()) {
			output(response, JsonUtil.buildJson(courses));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "系统检测到该班级暂无安排课程"));
		}
	}

	/**
	 * 删除排课记录
	 * 
	 * @param courseId
	 * @param response
	 */
	@RequestMapping("deleteCourseId")
	public void deleteCourseId(Integer courseId, HttpServletResponse response, HttpServletRequest request) {
		Course course = courseService.getCourseById(courseId);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("2", "您没有删除课程的权限!"));
		} else {
			if (!currTeacher.getTeacherType().equals("1")) {
				ClassNo classNo = classnoservice.getClassById(course.getClassId());
				if (!classNo.getSchoolArea().equals(currTeacher.getCampus())) {
					output(response, JsonUtil.buildFalseJson("3", "您没有删除其它校区课程的权限!"));
				} else {
					try {
						courseService.deleteCourseById(courseId);
						output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
					} catch (Exception e) {
						output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
					}
				}
			} else {
				try {
					courseService.deleteCourseById(courseId);
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				} catch (Exception e) {
					output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
				}
			}
		}
	}
}
