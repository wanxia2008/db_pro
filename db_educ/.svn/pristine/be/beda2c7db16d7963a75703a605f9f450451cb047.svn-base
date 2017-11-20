package com.db.action;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.ClassNo;
import com.db.entity.Classroom;
import com.db.entity.Course;
import com.db.entity.Grade;
import com.db.entity.SchoolYear;
import com.db.entity.SchoolZone;
import com.db.entity.SeasonType;
import com.db.entity.Subject;
import com.db.entity.Teacher;
import com.db.service.ClassNoService;
import com.db.service.ClassroomService;
import com.db.service.CourseService;
import com.db.service.GradeService;
import com.db.service.SchoolYearService;
import com.db.service.SchoolZoneService;
import com.db.service.SeasonTypeService;
import com.db.service.SubjectService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;

@Controller
@RequestMapping("/arrangeclass")
public class ArrangeclassController extends BaseUtil {

	@Resource
	private ClassroomService classroomservice;
	@Resource
	private SchoolZoneService schoolZoneService;
	@Resource
	private ClassNoService classNoService;
	@Resource
	private GradeService gradeService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private SeasonTypeService seasonTypeService;
	@Resource
	private CourseService courseService;
	@Resource
	private SchoolYearService schoolYearService;

	private HttpSession session;

	private Logger log = Logger.getLogger("ArrangeclassController");

	private String respJson = null;

	/**
	 * 教室信息列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param schoolName
	 * @return
	 */
	@RequestMapping("/classroom")
	public ModelAndView Classrooom(String schoolName, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<SchoolZone> schoolList = null;
		if (currTeacher.getTeacherType().equals("1")) {
			schoolList = schoolZoneService.getSchoolZone(schoolName);
		} else {
			schoolList = schoolZoneService.findSchoolList(currTeacher.getCampus());
		}
		view.addObject("schoolList", schoolList);
		view.setViewName("arrangeclassmanage/classroom");
		return view;
	}

	@RequestMapping("/getclassroom")
	public void getClassroom(Integer pageNo, Integer pageSize, String roomName, HttpServletRequest request,
			HttpServletResponse response) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 9;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<Classroom> list = null;
		int totalCount = 0;
		PageUtil util = null;
		if (currTeacher.getTeacherType().equals("1")) {
			totalCount = (int) classroomservice.getCount(roomName);// 获取总记录数
			util = new PageUtil(totalCount, pageNo, pageSize);
			list = classroomservice.getClassroom(util.getStartPos(), util.getPageSize(), roomName);
		} else {
			totalCount = classroomservice.getSchoolIdCount(roomName, currTeacher.getCampus());// 获取总记录数
			util = new PageUtil(totalCount, pageNo, pageSize);
			list = classroomservice.getClassroomSchoolId(util.getStartPos(), util.getPageSize(), roomName,
					currTeacher.getCampus());
		}
		if (!list.isEmpty() && list != null) {
			output(response, JsonUtil.buildJsonByTotalCount(list, util.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "获取失败"));
		}
	}

	/**
	 * 修改教室
	 * 
	 * @param response
	 * @param classroom
	 */
	@RequestMapping(value = "/updateclassroom", method = RequestMethod.POST)
	public void UpdateClassroom(HttpServletResponse response, Classroom classroom) {
		try {
			Classroom classroom2 = classroomservice.getClassroomByName(classroom.getClassroomName());
			if (classroom2 != null && !classroom.getClassroomName().equals(classroom2.getClassroomName())) {
				output(response, JsonUtil.buildFalseJson("1", "所输入的教室编号已存在，请重新输入！"));
			} else {
				classroom.setUpdateTime(new Date());
				classroomservice.updateClassroom(classroom);
				log.info("修改成功");
				output(response, JsonUtil.buildFalseJson("0", "修改成功！"));
			}
		} catch (Exception e) {

			log.info("修改失败");
			output(response, JsonUtil.buildFalseJson("1", "修改失败"));
		}
	}

	/**
	 * 新增教室
	 * 
	 * @param classroom
	 * @param pageNo
	 * @param pageSize
	 * @param schoolName
	 * @param response
	 */
	@RequestMapping(value = "/addclassroom", method = RequestMethod.POST)
	public void addClassroom(Classroom classroom, Integer pageNo, HttpServletRequest request, Integer pageSize,
			String schoolName, HttpServletResponse response) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Classroom classroom2 = classroomservice.findClassroomName(classroom.getClassroomName(),
				classroom.getSchoolArea());
		if (classroom2 != null) {
			output(response, JsonUtil.buildFalseJson("1", "所输入的教室编号已存在，请重新输入!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				classroom.setCreateTime(new Date());
				classroomservice.addClassroom(classroom);// 返回主键
				output(response, JsonUtil.buildFalseJson("0", "更新成功"));
			} else {
				if (!currTeacher.getCampus().equals(classroom.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("2", "您添加的校区与您所在的不一致!"));
				} else {
					classroom.setCreateTime(new Date());
					classroomservice.addClassroom(classroom);// 返回主键
					output(response, JsonUtil.buildFalseJson("0", "更新成功"));
				}
			}
		}
	}

	/**
	 * 删除教室
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteclassroom", method = RequestMethod.POST)
	public void deleteClassroom(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("classId"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(new Date());
			int len = (int) courseService.getRoomByCourseandDate(date, id);
			if (len > 0) {
				output(response, JsonUtil.buildFalseJson("2", "删除失败，当前课室已被占用"));
			} else {
				classroomservice.deleteClassroom(id);
				log.info("删除成功");
				output(response, JsonUtil.buildFalseJson("0", "删除成功"));
			}
		} catch (NumberFormatException e) {
			log.info("删除失败");
			output(response, JsonUtil.buildFalseJson("1", "删除失败"));
		}
	}

	/**
	 * 寒暑假排课列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/holiday")
	public ModelAndView Holidayarrange(HttpServletRequest request, Integer pageNo, Integer pageSize, String date) {
		session = (HttpSession) request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		ModelAndView view = generalreturn("arrangeclassmanage/holiday_arrange", teacher.getCampus());
		if (pageNo == null) {
			pageNo = 1;
		}
		List<ClassNo> list = null;
		if (teacher.getTeacherType().equals("1")) {
			list = classNoService.getHolidayAdminClassById();
		} else {
			list = classNoService.getAllClassBySchoolwhenholiday(teacher.getCampus());
		}
		List<Course> list2 = courseService.getCourseByHolidayCount();
		int totalCount = list2.size();
		if (!list2.isEmpty() && list2 != null) {
			List<Course> courses = courseService.getCourseByHoliday(list2.get(pageNo - 1).getCourseDate());
			if (!courses.isEmpty() && courses != null) {
				view.addObject("courseList", courses);
				if (date != null && date != "") {
					view.addObject("courseDate", date);
				} else {
					for (Course cou : courses) {
						view.addObject("courseDate", cou.getCourseDate());
					}
				}
			}
		}
		view.addObject("totalPage", totalCount);
		view.addObject("classList", list);
		view.addObject("pageNo", pageNo);
		if (date != null) {
			view.addObject("date", date);
		}
		return view;
	}

	/**
	 * 春秋季排课列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/springfall")
	public ModelAndView Springfallarrange(HttpServletRequest request, Integer pageNo, Integer pageSize,
			String curriculumDate) {

		if (pageNo == null) {
			pageNo = 1;
		}
		session = (HttpSession) request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		ModelAndView view = generalreturn("arrangeclassmanage/springfall_arrange", teacher.getCampus());
		List<ClassNo> list = null;
		// 判断当前登录者是否是管理员
		if (teacher.getTeacherType().equals("1")) {
			list = classNoService.getAdminClassById();
		} else {
			list = classNoService.getAllClassBySchoolwhenspringfall(teacher.getCampus());
		}
		List<Course> list2 = courseService.getCourseBySpringfallCount();
		// 获取当天日期接下来的周末
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance(Locale.CHINA);// 日历
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		System.out.println("离当前最近的周末为：" + dateFormat.format(calendar.getTime()));
		// 拿最近的周末去取得课程表
		if (!list2.isEmpty() && list2 != null) {
			List<Course> courses = courseService.getAllCourse(list2.get(pageNo - 1).getCourseDate());
			if (!courses.isEmpty() && courses != null) {
				view.addObject("courseList", courses);
				if (curriculumDate != null && curriculumDate != "") {
					view.addObject("weekend_day", curriculumDate);
				} else {
					for (Course cou : courses) {
						view.addObject("weekend_day", cou.getCourseDate());
					}
				}
			}
		}
		int totalCount = list2.size();
		view.addObject("classList", list);
		view.addObject("totalPage", totalCount);
		view.addObject("classList", list);
		view.addObject("pageNo", pageNo);
		return view;
	}

	/**
	 * 春季排课
	 * 
	 * @param returnname
	 * @param schoolId
	 * @return
	 */
	public ModelAndView generalreturn(String returnname, Integer schoolId) {
		ModelAndView view = new ModelAndView();
		view.setViewName(returnname);
		List<Subject> subjects = subjectService.getSubject();
		List<Grade> grades = gradeService.getGrade();
		List<SeasonType> seasonTypes = seasonTypeService.getSeasontype();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<Classroom> classrooms = null;
		//非管理员只可以获取到自己校区的数据
		if (currTeacher.getTeacherType().equals("1")) {
			classrooms = classroomservice.getClassroom1();
		} else {

			classrooms = classroomservice.getClassroom2(schoolId);
		}
//		List<Classroom> classrooms = classroomservice.getClassroom1();
		List<SchoolYear> schoolYears = schoolYearService.getYear();
		view.addObject("schoolList", schoolYears);
		view.addObject("subjectList", subjects);
		view.addObject("gradeList", grades);
		view.addObject("seasonList", seasonTypes);
		view.addObject("classroomList", classrooms);
		return view;
	}

	/**
	 * 添加课程信息（春秋季排课）
	 * 
	 * @param course
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/addspringfalllesson", method = RequestMethod.POST)
	public void addSpringfallLesson(Course course, HttpServletResponse response,HttpServletRequest request) throws Exception {
//		List<Course> course2 = courseService.getCourseByClassandDateandtime(course.getCourseDate(), course.getClassId(),0);
		ClassNo classNo = classNoService.getClassById(course.getClassId());
		List<Course> courses = courseService.findClassCourseList(course.getClassId(), null, null, null,0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.setTime(format.parse(course.getCourseDate()));
		int couId = 0; 
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		//非管理员无权限
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("3", "您没有添加课程的权限!"));
		} 
		else if (courses != null && !courses.isEmpty()) { //匹配课程时间是否被占用
			String name = "";
			if (courses.get(0).getCourseTimes() == 1) {
				name = "08:00-9:30";
			}
			if (courses.get(0).getCourseTimes() == 2) {
				name = "9:35-11:05";
			}
			if (courses.get(0).getCourseTimes() == 3) {
				name = "11:10-12:40";
			}
			if (courses.get(0).getCourseTimes() == 4) {
				name = "13:50-15:20";
			}
			if (courses.get(0).getCourseTimes() == 5) {
				name = "15:25-16:55";
			}
			if (courses.get(0).getCourseTimes() == 6) {
				name = "17:00-18:30";
			}
			output(response,
					JsonUtil.buildFalseJson("2", courses.get(0).getClassNo1().getClassName() + "，已在"
							+ courses.get(0).getClassroom().getClassroomName() + "教室，日期范围 "
							+ courses.get(0).getCourseDate() + "至" + courses.get(courses.size() - 1).getCourseDate()
							+ "，时间段" + name + "，授课科目" + courses.get(0).getSubject().getSubjectName()));
		} else if (classNo.getMaxNumber() <= classNo.getActualCourse()) {
			output(response, JsonUtil.buildFalseJson("1", "该班级每天的排课数已排完!"));
		} else {	
			if (currTeacher.getTeacherType().equals("1")) {	//管理员权限
				for (int i = 0; i < classNo.getCourseNumber(); i++) {
					course.setSubjectId(classNo.getSubject());
					course.setCourseType(0);// 春秋季排课
					course.setCreateTime(new Date());
					if (i == 0) {
						course.setCourseDate(course.getCourseDate());
						courseService.addCourse(course);// 返回插入的主键
					}
					if (i > 0) {
						ca.add(Calendar.DATE, 7);
						course.setCourseDate(format.format(ca.getTime()));
						courseService.addCourse(course);// 返回插入的主键
						System.out.println("当前日期为:" + format.format(ca.getTime()));
						couId = course.getCourseId();
					}
				}
				List<Course> list = new ArrayList<Course>();
				Course course3 = courseService.getCourseById(couId);
				list.add(course3);
				if (list != null && !list.isEmpty()) {
					respJson = JsonUtil.buildJson(list);
					respJson = JsonUtil.buildFalseJson("0", "添加课程成功!");
				} else {
					respJson = JsonUtil.buildFalseJson("1", "添加课程失败!");
				}
				output(response, respJson);
			}else {
				if (!currTeacher.getCampus().equals(classNo.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("5", "系统检测到您排课的班级与您所在的校区不一致!"));
				}else {
					for (int i = 0; i < classNo.getCourseNumber(); i++) {
						course.setSubjectId(classNo.getSubject());
						course.setCourseType(0);// 春秋季排课
						course.setCreateTime(new Date());
						if (i == 0) {
							course.setCourseDate(course.getCourseDate());
							courseService.addCourse(course);// 返回插入的主键
						}
						if (i > 0) {
							ca.add(Calendar.DATE, 7);
							course.setCourseDate(format.format(ca.getTime()));
							courseService.addCourse(course);// 返回插入的主键
							System.out.println("当前日期为:" + format.format(ca.getTime()));
							couId = course.getCourseId();
						}
					}
					List<Course> list = new ArrayList<Course>();
					Course course3 = courseService.getCourseById(couId);
					list.add(course3);
					if (list != null && !list.isEmpty()) {
						respJson = JsonUtil.buildJson(list);
						respJson = JsonUtil.buildFalseJson("0", "添加课程成功!");
					} else {
						respJson = JsonUtil.buildFalseJson("1", "添加课程失败!");
					}
					output(response, respJson);
				}
			}
		}
	}

	/**
	 * 添加课程信息（寒暑假排课）
	 * 
	 * @param course
	 * @param response
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/addHolidayLesson", method = RequestMethod.POST)
	public void addHolidayLesson(Course course, HttpServletResponse response,HttpServletRequest request) throws ParseException {
//		List<Course> course2 = courseService.getCourseByClassandDateandtime(course.getCourseDate(), course.getClassId(),1);
		ClassNo classNo = classNoService.getClassById(course.getClassId());
		List<Course> courses = courseService.findClassCourseList(course.getClassId(), null, null, null,1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.setTime(format.parse(course.getCourseDate()));
		int couId = 0; 
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("3", "您没有添加课程的权限!"));
		} else if (courses != null && !courses.isEmpty()) {
			String name="";
			if (courses.get(0).getCourseTimes()==1) {
				name = "08:00-9:30";
			}
			if (courses.get(0).getCourseTimes()==2) {
				name = "9:35-11:05";
			}
			if (courses.get(0).getCourseTimes()==3) {
				name = "11:10-12:40";
			}
			if (courses.get(0).getCourseTimes()==4) {
				name = "13:50-15:20";
			}
			if (courses.get(0).getCourseTimes()==5) {
				name = "15:25-16:55";
			}
			if (courses.get(0).getCourseTimes()==6) {
				name = "17:00-18:30";
			}
			output(response, JsonUtil.buildFalseJson("2",courses.get(0).getClassNo1().getClassName()+ "，已在"+courses.get(0).getClassroom().getClassroomName()+"教室，日期范围 "+ courses.get(courses.size()-1).getCourseDate()+"至"+courses.get(0).getCourseDate()+"，时间段"+name+"，授课科目"+courses.get(0).getSubject().getSubjectName()));
		} else if (classNo.getMaxNumber() <= classNo.getActualCourse()) {
			output(response, JsonUtil.buildFalseJson("1", "该班级每天的排课数已排完!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				for (int i = 0; i < classNo.getCourseNumber(); i++) {
					course.setSubjectId(classNo.getSubject());
					course.setCourseType(1);// 寒暑假排课
					course.setCreateTime(new Date());
					if (i == 0) {
						course.setCourseDate(course.getCourseDate());
						courseService.addCourse(course);// 返回插入的主键
					}
					if (i > 0) {
						ca.add(Calendar.DATE, 1);
						course.setCourseDate(format.format(ca.getTime()));
						courseService.addCourse(course);// 返回插入的主键
						System.out.println("当前日期为:" + ca.getTime());
						couId = course.getCourseId();
					}
				}
				classNo.setUpdateTime(new Date());
				if (classNo.getActualCourse() ==null) {
					classNo.setActualCourse(1);
				} else {
					classNo.setActualCourse(classNo.getActualCourse()+1);
				}
				classNoService.updateClasses(classNo);
				List<Course> list = new ArrayList<Course>();
				Course course3 = courseService.getCourseById(couId);
				list.add(course3);
				if (list != null && !list.isEmpty()) {
					respJson = JsonUtil.buildJson(list);
					respJson = JsonUtil.buildFalseJson("0", "添加课程成功!");
				}else {
					respJson = JsonUtil.buildFalseJson("1", "添加课程失败!");
				}
				output(response, respJson);
			}else {
				if (!currTeacher.getCampus().equals(classNo.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("5", "系统检测到您排课的班级与您所在的校区不一致!"));
				}else {
					for (int i = 0; i < classNo.getCourseNumber(); i++) {
						course.setSubjectId(classNo.getSubject());
						course.setCourseType(1);// 寒暑假排课
						course.setCreateTime(new Date());
						if (i == 0) {
							course.setCourseDate(course.getCourseDate());
							courseService.addCourse(course);// 返回插入的主键
						}
						if (i > 0) {
							ca.add(Calendar.DATE, 1);
							course.setCourseDate(format.format(ca.getTime()));
							courseService.addCourse(course);// 返回插入的主键
							System.out.println("当前日期为:" + ca.getTime());
							couId = course.getCourseId();
						}
					}
					classNo.setUpdateTime(new Date());
					if (classNo.getActualCourse() ==null) {
						classNo.setActualCourse(1);
					} else {
						classNo.setActualCourse(classNo.getActualCourse()+1);
					}
					classNoService.updateClasses(classNo);
					List<Course> list = new ArrayList<Course>();
					Course course3 = courseService.getCourseById(couId);
					list.add(course3);
					if (list != null && !list.isEmpty()) {
						respJson = JsonUtil.buildJson(list);
						respJson = JsonUtil.buildFalseJson("0", "添加课程成功!");
					}else {
						respJson = JsonUtil.buildFalseJson("1", "添加课程失败!");
					}
					output(response, respJson);
				}
			}
		}
	}

	/**
	 * 添加春季排课跳转的
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getLessonByClass")
	public ModelAndView getLessonByClass(HttpServletRequest request, HttpServletResponse response, Integer pageNo) {
		if (pageNo == null) {
			pageNo = 1;
		}
		session = (HttpSession) request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		ModelAndView view = generalreturn("arrangeclassmanage/springfall_arrange", teacher.getCampus());
		List<ClassNo> list = null;
		if (teacher.getTeacherType().equals("1")) {
			list = classNoService.getAdminClassById();
		} else {
			list = classNoService.getAllClassBySchoolwhenspringfall(teacher.getCampus());
		}
		String date = request.getParameter("curriculumDate");
		List<Course> courses = courseService.getAllCourse(date);
		int totalCount = 0;
		if (!courses.isEmpty() && courses != null) {
			view.addObject("courseList", courses);
			totalCount = courses.size();
		}
		view.addObject("weekend_day", date);
		view.addObject("classList", list);
		view.addObject("pageNo", pageNo);
		view.addObject("totalPage", totalCount);
		return view;
	}

	/**
	 * 添加寒暑假排课跳转的
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getLessonBysummer")
	public ModelAndView getLessonBysummer(HttpServletRequest request, HttpServletResponse response, Integer pageNo) {
		if (pageNo == null) {
			pageNo = 1;
		}
		session = (HttpSession) request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		List<ClassNo> list = null;
		ModelAndView view = generalreturn("arrangeclassmanage/holiday_arrange", teacher.getCampus());
		if (teacher.getTeacherType().equals("1")) {
			list = classNoService.getHolidayAdminClassById();
		} else {
			list = classNoService.getAllClassBySchoolwhenspringfall(teacher.getCampus());
		}
		String date1 = request.getParameter("curriculumDate");
		List<Course> courses = courseService.getCourseByHoliday(date1);
		int totalCount = 0;
		if (!courses.isEmpty() && courses != null) {
			view.addObject("courseList", courses);
			totalCount = courses.size();
		}
		view.addObject("classList", list);
		view.addObject("courseDate", date1);
		view.addObject("pageNo", pageNo);
		view.addObject("totalPage", totalCount);
		return view;
	}

	/**
	 * 筛选春季排课
	 * 
	 * @param request
	 * @param response
	 * @param subjectid
	 * @param gradeid
	 * @param seasonid
	 * @throws Exception
	 */
	@RequestMapping("/getClassBySubjectGrade")
	public void getClassBySubjectGrade(HttpServletRequest request, HttpServletResponse response, Integer subjectid,
			Integer gradeid, Integer seasonid, Integer schoolYear) throws Exception {

		session = (HttpSession) request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		// String classnos = teacher.getClassId();
		// List classlist = Arrays.asList(classnos.split(","));
		Map<String, Object> map = new HashMap<>();
		System.out.println("schoolYear=" + schoolYear);
		map.put("schoolYear", schoolYear);
		map.put("subject", subjectid);
		map.put("grade", gradeid);
		map.put("seasonType", seasonid);
		map.put("schoolArea", teacher.getCampus());
		// map.put("classList", null);
		List<ClassNo> list = null;
		if (teacher.getTeacherType().equals("1")) {
			list = classNoService.findAdminSchholIdById(map);
		} else {
			list = classNoService.getClassBySubjectGradeSeason(map);
		}
		if (list != null && !list.isEmpty()) {
			log.info("获取班级信息成功！" + list);
			output(response, JsonUtil.buildJson(list));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
	}

	/**
	 * 筛选秋季排课
	 * 
	 * @param request
	 * @param response
	 * @param subjectid
	 * @param gradeid
	 * @param seasonid
	 * @throws Exception
	 */
	@RequestMapping("/getHolidaySubject")
	public void getHolidaySubject(HttpServletRequest request, HttpServletResponse response, Integer subjectid,
			Integer gradeid, Integer seasonid, Integer schoolYear) throws Exception {

		session = (HttpSession) request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		Map<String, Object> map = new HashMap<>();
		map.put("schoolYear", schoolYear);
		map.put("subject", subjectid);
		map.put("grade", gradeid);
		map.put("seasonType", seasonid);
		map.put("schoolArea", teacher.getCampus());
		// map.put("classList", null);
		List<ClassNo> list = null;
		if (teacher.getTeacherType().equals("1")) {
			list = classNoService.getHolidayAdmin(map);
		} else {
			list = classNoService.getHolidaySubject(map);
		}
		if (list != null && !list.isEmpty()) {
			log.info("获取班级信息成功!" + list);
			output(response, JsonUtil.buildJson(list));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
	}

	/**
	 * 删除排课
	 * @param courseId
	 * @param response
	 */
	@RequestMapping("deleteCourse")
	public void deleteCourse(Integer courseId, HttpServletResponse response, HttpServletRequest request) {
		session = (HttpSession) request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Course course = courseService.getCourseById(courseId);
		ClassNo classNo = classNoService.getClassById(course.getClassId());
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("2", "您没有删除课程的权限!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				
//				Calendar ca = Calendar.getInstance();
//				ca.setTime(format.parse(course.getCourseDate()));
//				for (int i = 0; i < classNo.getCourseNumber(); i++) {
//					course.setSubjectId(classNo.getSubject());
//					course.setCourseType(0);// 春秋季排课
//					course.setCreateTime(new Date());
//					if (i == 0) {
//						course.setCourseDate(course.getCourseDate());
//						courseService.addCourse(course);// 返回插入的主键
//					}
//					if (i > 0) {
//						ca.add(Calendar.DATE, 7);
//						course.setCourseDate(format.format(ca.getTime()));
//						courseService.addCourse(course);// 返回插入的主键
//						System.out.println("当前日期为:" + format.format(ca.getTime()));
//						couId = course.getCourseId();
//					}
//				}
//				List<Course> list = new ArrayList<Course>();
//				Course course3 = courseService.getCourseById(couId);
				

				int len = courseService.deleteCourseById(courseId);
				if (len > 0) {
					output(response, JsonUtil.buildFalseJson("0", "删除成功！"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "删除失败！"));
				}
			} else {
				if (!currTeacher.getCampus().equals(classNo.getClassId())) {
					output(response, JsonUtil.buildFalseJson("3", "系统检测到您删除课程的班级与您所在的校区不一致!"));
				} else {
					int len = courseService.deleteCourseById(courseId);
					if (len > 0) {
						output(response, JsonUtil.buildFalseJson("0", "删除成功！"));
					} else {
						output(response, JsonUtil.buildFalseJson("1", "删除失败！"));
					}
				}
			}
		}
	}
}
