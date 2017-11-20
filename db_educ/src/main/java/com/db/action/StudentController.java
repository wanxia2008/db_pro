package com.db.action;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.ClassNo;
import com.db.entity.Grade;
import com.db.entity.SchoolYear;
import com.db.entity.SchoolZone;
import com.db.entity.SeasonType;
import com.db.entity.Student;
import com.db.entity.StudentCareer;
import com.db.entity.Subject;
import com.db.entity.Teacher;
import com.db.service.ClassNoService;
import com.db.service.GradeService;
import com.db.service.SchoolYearService;
import com.db.service.SchoolZoneService;
import com.db.service.SeasonTypeService;
import com.db.service.StudentCareerService;
import com.db.service.StudentService;
import com.db.service.SubjectService;
import com.db.service.TeacherService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.MD5Util;
import com.db.util.PageUtil;

@Controller
@RequestMapping("/student")
public class StudentController extends BaseUtil {
	
	@Resource
	private StudentService studentservice;
	@Resource
	private SchoolZoneService schoolZoneService;
	@Resource
	private GradeService gradeService;
	@Resource
	private SeasonTypeService seasonTypeService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private ClassNoService classnoservice;
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private SchoolYearService schoolYearService;
	@Resource
	private StudentCareerService careerService;
	
	
	private HttpSession session;
	
	private Logger log = Logger.getLogger(StudentController.class);
	
	
	/**
	 * 查看个人详情
	 * @param response
	 * @param studentId
	 */
	@RequestMapping("/studentDateills")
	public void dateills(HttpServletResponse response,Integer studentId){
		Student  student = studentservice.studentList(studentId);
		String classno = student.getClassNo();
		List<ClassNo> classNos;
		if(classno.indexOf(",")>=0) {
			List classlist = Arrays.asList(classno.split(","));
			classNos=classnoservice.getClassByClassnos(classlist, null);
		} else {
			classNos=classnoservice.getClassByClassnos(null, Integer.valueOf(classno));
		}
//		student.setClassNos(classNos);
		List<Student> sList = new ArrayList<>();
		sList.add(student);
 		if (sList != null && !sList.isEmpty()) {
 			output(response, JsonUtil.buildJson(sList));
		}else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}
	
	/**
	 * 后台学员查询列表
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/studentmanage")
	public ModelAndView Studentmanage(Integer pageNo,Integer pageSize,String studentName,
			String endCreateTime,String startCreateTime,Integer studentStatus,
			Integer schoolId,HttpServletRequest request) throws ParseException {
		
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		HttpSession session = request.getSession();
		List<Student> sList = null;
		int rowsCount = 0;
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (!currTeacher.getTeacherType().equals("1")) {
			if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
				rowsCount = studentservice.getScoolIdCount(studentName,studentStatus,currTeacher.getCampus(),currTeacher.getClassId());
				sList =studentservice.getScoolIdList((pageNo-1)*pageSize,pageSize,studentName,studentStatus,currTeacher.getCampus(),currTeacher.getClassId());
				
			}else {
				rowsCount = studentservice.getScoolIdCount(studentName,studentStatus,currTeacher.getCampus(),null);
				sList =studentservice.getScoolIdList((pageNo-1)*pageSize,pageSize,studentName,studentStatus,currTeacher.getCampus(),null);
			}
		} else {
			sList = studentservice.getStudentList((pageNo-1)*pageSize,pageSize,studentName,studentStatus,schoolId);
			rowsCount= studentservice.getCountMuber(studentName,studentStatus,schoolId);
		}
		
		int totalPages = 0;
		if(rowsCount % pageSize == 0){
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize)+1;
		}
		for (Student student : sList) {
			List<StudentCareer> list = careerService.findCareerGradeId(student.getStudentId(), student.getGrade());
			if (list != null && !list.isEmpty()) {
				student.setCareerList(list);
			} else {
				student.setCareerList(null);
			}
		}
		ModelAndView mv = new ModelAndView();
		if(!sList.isEmpty() && sList!=null) {
			mv.addObject("sList", sList);
		} else {
			mv.addObject("sList", null);
		}
		List<ClassNo> cnList = classnoservice.getClassesNo();
		mv.addObject("cnList", cnList);
		List<SchoolZone> szList = schoolZoneService.getAllSchool();
		List<Subject> subjects = subjectService.getSubject();
		List<Grade> grades = gradeService.getGrade();
		List<SeasonType> stList = seasonTypeService.getSeasontype();
		mv.addObject("szList", szList);
		mv.addObject("stList", stList);
		mv.addObject("grades", grades);
		mv.addObject("subjects", subjects);
		mv.addObject("schoolId", schoolId);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("studentName", studentName);
		mv.addObject("startCreateTime", startCreateTime);
		mv.addObject("endCreateTime", endCreateTime);
		mv.addObject("studentStatus", studentStatus);
		mv.addObject("currTeacher", currTeacher);
		mv.setViewName("studentmanage/studentmanage");
		return mv;
	}
	
	/**
	 * 报名管理列表
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/studentEnlistList")
	public ModelAndView studentEnlistList(Integer pageNo,Integer pageSize,String studentName,
			String endCreateTime,String startCreateTime,Integer studentStatus,
			Integer schoolId,HttpServletRequest request) throws ParseException {
		
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		HttpSession session = request.getSession();
		List<Student> sList = null;
		int rowsCount = 0;
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (!currTeacher.getTeacherType().equals("1")) {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				rowsCount = studentservice.getScoolIdCount(studentName,studentStatus,currTeacher.getCampus(),null);
				sList =studentservice.getScoolIdList((pageNo-1)*pageSize,pageSize,studentName,studentStatus,currTeacher.getCampus(),null);
			}else {
				rowsCount = studentservice.getScoolIdCount(studentName,studentStatus,currTeacher.getCampus(),currTeacher.getClassId());
				sList =studentservice.getScoolIdList((pageNo-1)*pageSize,pageSize,studentName,studentStatus,currTeacher.getCampus(),currTeacher.getClassId());
			}
		}else {
			sList = studentservice.getStudentList((pageNo-1)*pageSize,pageSize,studentName,studentStatus,schoolId);
			rowsCount= studentservice.getCountMuber(studentName,studentStatus,schoolId);
		}
		int totalPages = 0;
		if(rowsCount % pageSize == 0){
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize)+1;
		}
		for (Student student:sList) {
			List<StudentCareer> list = careerService.findCareerGradeId(student.getStudentId(), student.getGrade());
			if (list != null && !list.isEmpty()) {
				student.setCareerList(list);
			} else {
				student.setCareerList(null);
			}
		}
		ModelAndView mv = new ModelAndView();
		if(!sList.isEmpty() && sList!=null) {
			mv.addObject("sList", sList);
		} else {
			mv.addObject("sList", null);
		}
		List<ClassNo> cnList = classnoservice.getClassesNo();
		mv.addObject("cnList", cnList);
		List<SchoolZone> szList = schoolZoneService.getAllSchool();
		List<Subject> subjects = subjectService.getSubject();
		List<Grade> grades = gradeService.getGrade();
		List<SeasonType> stList = seasonTypeService.getSeasontype();
		mv.addObject("szList", szList);
		mv.addObject("stList", stList);
		mv.addObject("grades", grades);
		mv.addObject("subjects", subjects);
		mv.addObject("schoolId", schoolId);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("studentName", studentName);
		mv.addObject("startCreateTime", startCreateTime);
		mv.addObject("endCreateTime", endCreateTime);
		mv.addObject("studentStatus", studentStatus);
		mv.addObject("currTeacher", currTeacher);
		mv.setViewName("studentenlist/enlistList");
		return mv;
	}
	/**
	 * 新增学员页面
	 * @return
	 */
	@RequestMapping("/newstudent")
	public ModelAndView Newstudent() {
		List<Grade> list = gradeService.getGrade();
		List<SeasonType> list3 = seasonTypeService.getSeasontype();
		List<Subject> list4 = subjectService.getSubject();
		ModelAndView view = new ModelAndView();
		List<SchoolZone> szList = schoolZoneService.getAllSchool();
		List<SchoolYear> syList = schoolYearService.getYear();
		view.setViewName("studentmanage/newstudent");
		view.addObject("gradeList", list);
		view.addObject("syList", syList);
		view.addObject("szList", szList);
		view.addObject("seasonList", list3);
		view.addObject("subjectList", list4);
		return view;
	}
	
	/**
	 * 修改学员页面信息
	 * @param studentid
	 * @return
	 */
	@RequestMapping("/toupdatestudent")
	public ModelAndView Updatestudent(@RequestParam("studentid")String studentid,Integer pageNo,
			Integer pageSize,int type) {
		int id = Integer.parseInt(studentid);
		
		ModelAndView view = new ModelAndView();
		if (type == 1) {
			view.setViewName("studentmanage/updatestudent");
			if (pageNo == null) {
				pageNo = 1;
			}
			if (pageSize == null) {
				pageSize = 8;
			}
			int rowsCount = careerService.getCountStudentId(id);
			int totalPages = 0;
			
			//查看 该学员的历史记录
			List<StudentCareer> trList = careerService.getTestRecordStudentId(id);
			if (trList != null && !trList.isEmpty()) {
				view.addObject("trList", trList);
			}else {
				view.addObject("trList", null);
			}
			if (rowsCount % pageSize == 0) {
				totalPages = rowsCount / pageSize;
			} else {
				totalPages = (rowsCount / pageSize)+1;
			}
			view.addObject("totalPages", totalPages);
		}else {
			view.setViewName("studentenlist/updatestudent");
		}
		Student student = studentservice.findStudentGradeById(id);
		List<Subject> subList = null;
		String subjects = student.getSubjectType();
		System.out.println("subjects:"+subjects);
		if(subjects !="" && subjects != null) {
			String[] subjectss = subjects.split(",");
			List subnamelist = Arrays.asList(subjectss);
			subList = subjectService.getSubjectByIds(subnamelist);
			student.setSubjectlist(subList);
		} 
		List<Subject> list = subjectService.getSubject();
		List<Grade> list3 = gradeService.getGrade();
		List<SchoolZone> szList = schoolZoneService.getAllSchool();
		List<SchoolYear> syList = schoolYearService.getYear();
		List<SeasonType> stList = seasonTypeService.getSeasontype();
		List<ClassNo> cnList = classnoservice.getClassesNo();
		view.addObject("students", student);
		view.addObject("subjectList", list);
		view.addObject("cnList", cnList);
		view.addObject("gradeList", list3);
		view.addObject("szList", szList);
		view.addObject("page", pageNo);
		view.addObject("syList", syList);
		view.addObject("stList", stList);
		view.addObject("pageSize", pageSize);
		view.addObject("subList", subList);
		return view;
	}
	@RequestMapping("/toupdatestudentaa")
	public ModelAndView Updatestudent1(@RequestParam("studentid")String studentid,Integer pageNo,
			Integer pageSize,int type) {
		int id = Integer.parseInt(studentid);
		
		ModelAndView view = new ModelAndView();
		if (type == 1) {
			view.setViewName("studentenlist/updatestudent");
			if (pageNo == null) {
				pageNo = 1;
			}
			if (pageSize == null) {
				pageSize = 8;
			}
			int rowsCount = careerService.getCountStudentId(id);
			int totalPages = 0;
			
			//查看 该学员的历史记录
			List<StudentCareer> trList = careerService.getTestRecordStudentId(id);
			if (trList != null && !trList.isEmpty()) {
				view.addObject("trList", trList);
			}else {
				view.addObject("trList", null);
			}
			if (rowsCount % pageSize == 0) {
				totalPages = rowsCount / pageSize;
			} else {
				totalPages = (rowsCount / pageSize)+1;
			}
			view.addObject("totalPages", totalPages);
		}else {
			view.setViewName("studentenlist/updatestudent");
		}
		Student student = studentservice.findStudentGradeById(id);
		List<Subject> subList = null;
		String subjects = student.getSubjectType();
		System.out.println("subjects:"+subjects);
		if(subjects !="" && subjects != null) {
			String[] subjectss = subjects.split(",");
			List subnamelist = Arrays.asList(subjectss);
			subList = subjectService.getSubjectByIds(subnamelist);
			student.setSubjectlist(subList);
		} 
		List<Subject> list = subjectService.getSubject();
		List<Grade> list3 = gradeService.getGrade();
		List<SchoolZone> szList = schoolZoneService.getAllSchool();
		List<SchoolYear> syList = schoolYearService.getYear();
		List<SeasonType> stList = seasonTypeService.getSeasontype();
		List<ClassNo> cnList = classnoservice.getClassesNo();
		view.addObject("students", student);
		view.addObject("subjectList", list);
		view.addObject("cnList", cnList);
		view.addObject("gradeList", list3);
		view.addObject("szList", szList);
		view.addObject("page", pageNo);
		view.addObject("syList", syList);
		view.addObject("stList", stList);
		view.addObject("pageSize", pageSize);
		view.addObject("subList", subList);
		return view;
	}
	
	/**
	 * 修改学员历史记录
	 * @param response
	 * @param id
	 */
	@RequestMapping("/distStudent")
	public void distributionStudent(HttpServletResponse response,Integer id,Integer gradeId,
			Integer subjectId,Integer year,Integer choiceId,Integer classId){
		    StudentCareer career = careerService.getStuCareerById(id);
		    Student student = studentservice.getStudentById(career.getStudentId());
			if (classId == null) {
				output(response, JsonUtil.buildFalseJson("1", "请选择班级!"));
			}else {
				career.setUpdateTime(new Date());
				career.setClassId(classId);
				careerService.updateCareerBy(career);
				//同时修改学员表的班级记录
				student.setUpdateTime(new Date());
				student.setClassNo(classId.toString());
				student.setStudentStatus(4);
				studentservice.updateClassNo(student);
				output(response, JsonUtil.buildFalseJson("0", "修改成功"));
			}
	}
	/**
	 * 删除学员历史记录
	 * @param response
	 * @param id
	 */
	@RequestMapping("/testRecoreDelete")
	public void testRecoreDelete(HttpServletResponse response, HttpServletRequest request,
			Integer id, Integer classId, Integer pageNo,Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		StudentCareer tr = careerService.getStuCareerById(id);
		ClassNo cn = classnoservice.getClassById(classId);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("1", "系统检测到您没有删除学员的权限!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				if (tr != null) {
					careerService.deleteStudentCareerById(id);
					cn.setUpdateTime(new Date());
					cn.setActualNumber(cn.getActualNumber() - 1);
					classnoservice.updateClasses(cn);
					List<StudentCareer> careers = careerService.careerList(tr.getStudentId(), (pageNo - 1) * pageSize,
							pageSize);
					if (careers == null || careers.isEmpty()) {
						try {
							Student student = studentservice.getStudentById(tr.getStudentId());
							student.setUpdateTime(new Date());
							student.setClassNo(null);
							student.setStudentStatus(3);
							studentservice.updateClassNo(student);
							output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
						} catch (Exception e) {
							output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
						}
					}
				}
			}else {
				if (!currTeacher.getCampus().equals(cn.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("2", "系统检测到您所在的校区与该班级不一致!"));
				}else {
					if (tr != null) {
						careerService.deleteStudentCareerById(id);
						cn.setUpdateTime(new Date());
						cn.setActualNumber(cn.getActualNumber() - 1);
						classnoservice.updateClasses(cn);
						List<StudentCareer> careers = careerService.careerList(tr.getStudentId(), (pageNo - 1) * pageSize,
								pageSize);
						if (careers == null || careers.isEmpty()) {
							try {
								Student student = studentservice.getStudentById(tr.getStudentId());
								student.setUpdateTime(new Date());
								student.setClassNo(null);
								student.setStudentStatus(3);
								studentservice.updateClassNo(student);
								output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
							} catch (Exception e) {
								output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
							}
						}
					}
				}
			}
		}
	}
	/**
	 * 编辑历史记录
	 * @param id
	 * @return
	 */
	@RequestMapping("/modityExam")
	public ModelAndView modityExam(Integer id){
		ModelAndView mv = new ModelAndView();
		StudentCareer tr = careerService.getStuCareerById(id);
		List<Subject> subjects = subjectService.getSubject();
		List<Grade> grades = gradeService.getGrade();
		mv.addObject("subjects", subjects);
		mv.addObject("grades", grades);
		mv.addObject("tr", tr);
		mv.setViewName("studentmanage/modityExam");
		return mv;
	}
	@RequestMapping("/getallstudent")
	public void getAllstudent(HttpServletRequest request,HttpServletResponse response) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		try {
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int totalCount = (int) studentservice.getCount();//获取总数
			PageUtil util = new PageUtil(totalCount, pageNo, pageSize);
			List<Student> list = studentservice.getAllStudent(util.getStartPos(), pageSize,teacher.getCampus());
			List<Subject> list2;
			for(int i=0;i<list.size();i++) {
				String subjects = list.get(i).getSubjectType();
				if(subjects == "" || subjects == null) {
					continue;
				}
				String[] subjectss = subjects.split(",");
				List subjectlist = Arrays.asList(subjectss);
				list2 = subjectService.getSubjectByIds(subjectlist);
				list.get(i).setSubjectlist(list2);
			}
			log.info("获取学员信息成功");
			output(response, JsonUtil.buildJsonByTotalCount(list, util.getTotalPageCount()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			output(response, JsonUtil.buildFalseJson("2", "获取失败"));
		}
	}
	/**
	 * 
	 * 新增学员
	 * @param request
	 * @param response
	 * @param student
	 */
	@RequestMapping("/addstudent")
	public void addStudent(HttpServletRequest request,HttpServletResponse response,Student student) {
//		session = request.getSession();
//		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		
		Student stu = studentservice.getStudentByUserName(student.getUserName());
		Student student3 = null;
		Student phone1 = null;
		Student phone2 = null;
		if (student.getPhone() != null && student.getPhone() != "") {
			student3 = studentservice.getStudentByPhone(student.getPhone());
		}
		if (student.getCustodian1Phone() != null && student.getCustodian1Phone() != "") {
			phone1 = studentservice.getStudentByPhone(student.getCustodian1Phone());
		}
		if (student.getCustodian2Phone() != null && student.getCustodian2Phone() != "") {
			
			phone2 = studentservice.getStudentByPhone(student.getCustodian2Phone());
		}
		if(stu != null) {
			output(response, JsonUtil.buildFalseJson("1", "用户名已经存在!"));
			log.info("新增失败，用户名已经存在");
		}  else if (student.getSubjectType() == null || student.getSubjectType()=="") {
			output(response, JsonUtil.buildFalseJson("2", "请选择科目!"));
		} else if (student3 != null || phone1 != null || phone2 != null) {
			output(response, JsonUtil.buildFalseJson("3", "改号码已存在!"));
		} else {
			student.setCreateTime(new Date());
			student.setPassword(MD5Util.MD5Encode(student.getPassword(), "utf-8"));
			if(student.getSubjectType()!="" && student.getSeasonType() != null) {
				student.setSubjectType(student.getSubjectType());
			}
//			student.setSchoolId(teacher.getCampus());
			student.setStatus(0);//默认是未入学考试状态
			student.setStudentStatus(1);
			studentservice.addNewStudent(student);//返回主键记录
			Student student2 = studentservice.getStudentById(student.getStudentId());//通过返回的主键查询新增记录
			if(student2 == null) {
				output(response, JsonUtil.buildFalseJson("2", "新增失败!"));
				log.info("新增失败");
			} else {
				List<Student> list = new ArrayList<Student>();
				list.add(student2);
				log.info("新增成功!");
				output(response, JsonUtil.buildJson(list));
			}
		}
	}
	/**
	 * 修改学员信息
	 * @param response
	 * @param student
	 */
	@RequestMapping("/updatestudent")
	public void updateStudent(HttpServletResponse response,Student student) {
		Student student1 = studentservice.getStudentByUserName(student.getUserName());
		Student phone = null;
		Student phone1 = null;
		Student phone2 = null;
		if (student.getPhone() != null && student.getPhone() != "") {
			phone = studentservice.getStudentByPhone(student.getPhone());
		}
		if (student.getCustodian1Phone() != null && student.getCustodian1Phone() != "") {
			phone1 = studentservice.getStudentByPhone(student.getCustodian1Phone());
		}
		if (student.getCustodian2Phone() != null && student.getCustodian2Phone() != "") {
			
			phone2 = studentservice.getStudentByPhone(student.getCustodian2Phone());
		}
		Student student2 = studentservice.findstudentDateils(student.getStudentId());
		if(student1!=null && student1.getStudentId() != student.getStudentId()) {
			output(response, JsonUtil.buildFalseJson("1", "该用户名已经存在"));
			log.info("修改失败，用户名已经存在");
		} else if (phone != null && !student2.getPhone().equals(student.getPhone())) {
			output(response, JsonUtil.buildFalseJson("2", "该手机已存在!"));
		} else if (phone1 != null && !student2.getCustodian1Phone().equals(student.getCustodian1Phone())) {
			output(response, JsonUtil.buildFalseJson("2", "该手机已存在!"));
		} else if (phone2 != null && !student2.getCustodian2Phone().equals(student.getCustodian2Phone())) {
			output(response, JsonUtil.buildFalseJson("2", "该手机已存在!"));
		} else {
			student.setUpdateTime(new Date());
			if(student.getSubjectType()!= "" && student.getSubjectType() != null) {
				student.setSubjectType(student.getSubjectType()+",");
			}
			
			student.setPassword(MD5Util.MD5Encode(student.getPassword(), "utf-8"));
			studentservice.updateStudent(student);
			log.info("修改成功!");
			output(response, JsonUtil.buildFalseJson("0", "修改成功！"));
		}
	}
	/**
	 * 删除学员
	 * @param request
	 * @param response
	 */
	@RequestMapping("/deletestudent")
	public void deleteStudent(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		int id = Integer.parseInt(request.getParameter("studentid"));
		Student student = studentservice.getStudentById(id);
		if (student.getStudentStatus() != 5 && student.getStudentStatus() == 4) {
			output(response, JsonUtil.buildFalseJson("1", "该学员还没毕业,暂不能删除"));
		}else {
			if (currTeacher.getTeacherType().equals("1")) {
				studentservice.deleteStudent(id);
				log.info("删除成功!");
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			}else {
				studentservice.falseDeleteStudent(id);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			}
		}
	}

	/**
	 * 分配班级
	 * @param classId
	 * @param studentId
	 */
	@RequestMapping("/distClassId")
	public void distClassId(Integer classId,Integer studentId,HttpServletResponse response,Integer gradeId
			,Integer subjectId,Integer year,Integer seasonId,Integer schoolId){
		Student student = studentservice.getStudentById(studentId);
		ClassNo cn = classnoservice.getClassById(classId);
		StudentCareer sCareer = careerService.findStudentCareer(studentId,classId);
		StudentCareer sCareer2 = careerService.findCareerBySIdAndYearSeason(studentId, year, seasonId,gradeId,subjectId);
		if (student.getGrade() != gradeId) {
			output(response, JsonUtil.buildFalseJson("2", "您分配的班级与该学员所在的年级不符合，请重新分配!"));
		} else if (cn != null && cn.getClassNumber() == cn.getActualNumber()) {
			output(response, JsonUtil.buildFalseJson("3", "该班级人数已满，请分配其他班级!"));
		}  else if (sCareer != null) {
			output(response, JsonUtil.buildFalseJson("5", "此条件下，该学员已分配班级"));
		} else if (sCareer2 != null) {
			output(response, JsonUtil.buildFalseJson("4", "该学员已分配班级!"));
		}
//        else if (cn.getSubject() == subjectId) {
//			output(response, JsonUtil.buildFalseJson("4", "该学员已分配班级!"));
//		} 
        else {
			try {
				student.setUpdateTime(new Date());
				student.setClassNo(classId.toString());
				student.setStudentStatus(4);
				studentservice.modityClassNo(student);
				// 往学员生涯表添加记录
				StudentCareer career = new StudentCareer();
				career.setCreateTime(new Date());
				career.setGrade(gradeId);
				career.setStudentId(studentId);
				career.setClassId(classId);
				career.setYear(year);
				career.setSubject(subjectId);
				career.setSeasonType(cn.getSeasonType());
				career.setStatus(2);
				career.setSchoolId(cn.getSchoolArea());
				careerService.addStudentCareer(career);
				// 分配班级成功后，班级实际人数依次加一
				cn.setUpdateTime(new Date());
				cn.setActualNumber(cn.getActualNumber() + 1);
				classnoservice.updateClasses(cn);
				output(response, JsonUtil.buildFalseJson("0", "分配班级成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "分配班级失败!"));
			}
		}
	}
	
	
	/**
	 * 分配班级页面列表
	 */
	@RequestMapping("/getClassNoList")
	public void getClassNoList(HttpServletResponse response,Integer pageNo,Integer pageSize
			,String className,Integer subject,Integer seasonType,Integer grade){
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		List<ClassNo> sList = classnoservice.findSubjectList(className,subject,seasonType,grade,(pageNo - 1) * pageSize, pageSize);
		int rowsCount = classnoservice.getCountClassId(className,subject,seasonType,grade);
		PageUtil util = new PageUtil(rowsCount, pageNo, pageSize);
		if (!sList.isEmpty() && sList != null) {
			output(response, JsonUtil.buildJsonByTotalCount(sList, util.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
	}
	
}

