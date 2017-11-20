package com.db.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.db.entity.ClassNo;
import com.db.entity.ClassRecord;
import com.db.entity.Grade;
import com.db.entity.Homework;
import com.db.entity.HomeworkAnswer;
import com.db.entity.HomeworkDetails;
import com.db.entity.HomeworkRecord;
import com.db.entity.LectureNotes;
import com.db.entity.Log;
import com.db.entity.MyMistakes;
import com.db.entity.QuestionCloze;
import com.db.entity.QuestionFill;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionPaer;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.QuestionTag;
import com.db.entity.QuestionsType;
import com.db.entity.RangeLesson;
import com.db.entity.SeasonType;
import com.db.entity.SeePaper;
import com.db.entity.StudentCareer;
import com.db.entity.Subject;
import com.db.entity.SubjectiveQuestion;
import com.db.entity.TaskType;
import com.db.entity.Teacher;
import com.db.entity.TeacherAuthority;
import com.db.entity.TeachingMaterial;
import com.db.entity.utilentity.QuestionAnalysis;
import com.db.service.AchievementService;
import com.db.service.ClassNoService;
import com.db.service.ClassRecordService;
import com.db.service.CourseService;
import com.db.service.GradeService;
import com.db.service.HomeworkAnswerService;
import com.db.service.HomeworkDetailsService;
import com.db.service.HomeworkRecordService;
import com.db.service.HomeworkService;
import com.db.service.LogService;
import com.db.service.MyMistakesService;
import com.db.service.PaperInfoService;
import com.db.service.QuestionClozeService;
import com.db.service.QuestionFillService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.QuestionTagService;
import com.db.service.QuestionsTypeService;
import com.db.service.RangeLessonService;
import com.db.service.SeasonTypeService;
import com.db.service.StudentCareerService;
import com.db.service.StudentService;
import com.db.service.SubjectService;
import com.db.service.SubjectiveQuestionService;
import com.db.service.TaskTypeService;
import com.db.service.TeacherAuthorityService;
import com.db.service.TeacherService;
import com.db.service.TeachingMaterialService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("/homework")
public class HomeworkController extends BaseUtil {

	@Resource
	private HomeworkService homeworkService;
	@Resource
	private QuestionReadService questionReadService;
	@Resource
	private QuestionSingleChoiceService questionSingleChoiceService;
	@Resource
	private QuestionJudgeService questionJudgeService;
	@Resource
	private QuestionMulitChoiceService questionMulitChoiceService;
	@Resource
	private HomeworkAnswerService homeworkAnswerService;
	@Resource
	private HomeworkDetailsService homeworkDetailsService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private QuestionsTypeService qtypeService;
	@Resource
	private TeachingMaterialService tmService;
	@Resource
	private GradeService gradeService;
	@Resource
	private TaskTypeService typeService;
	@Resource
	private QuestionTagService questionTagService;
	@Resource
	private ClassNoService classNoService;
	@Resource
	private SeasonTypeService seasonTypeService;
	@Resource
	private TaskTypeService taskTypeService;
	@Resource
	private AchievementService achievementService;
	@Resource
	private MyMistakesService myMistakesService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private CourseService courseService;
	@Resource
	private PaperInfoService paperInfoService;
	@Resource
	private StudentService studentService;
	@Resource
	private HomeworkRecordService homeworkRecordService;
	@Resource
	private TeacherAuthorityService authorityService;
	@Resource
	private RangeLessonService lessonService;
	@Resource
	private StudentCareerService studentCareerService;
	@Resource
	private LogService logService;
	@Resource
	private SubjectiveQuestionService questionService;
	@Resource
	private QuestionFillService fillService;
	@Resource
	private QuestionClozeService clozeService;

	private HttpSession session;
	@Resource
	private ClassRecordService recordService;

	private Logger log = Logger.getLogger(HomeworkController.class);

	/**
	 * 作业管理
	 * 
	 * @param request
	 * @param pInfo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/homeworkmanage")
	public ModelAndView HomeworkManage(HttpServletRequest request, Integer pageNo, Integer pageSize, String date,
			Integer classId, Homework hk) throws Exception{
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("classId", classId);
		List<ClassRecord> crList = null;
		List<ClassNo> classList = null;
		int rowsCount = 0;
		int totalPages = 0;
		if (currTeacher.getTeacherType().equals("1")) {
			crList = recordService.getExamPaperList(map);
			rowsCount = recordService.getPaperCount(map);
			classList = classNoService.getClassesNo();
		} else {
			map.put("schoolId", currTeacher.getCampus());
			classList = classNoService.findIsExistenceCampus(currTeacher.getCampus());
			if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
				if (currTeacher.getClassId() != null) {
					map.put("classId", currTeacher.getClassId());
					crList = recordService.getExamPaperSchoolList(map);
					rowsCount = recordService.getSchoolCount(map);
				}
			}else {
				crList = recordService.getExamPaperSchoolList(map);
				rowsCount = recordService.getSchoolCount(map);
			}
		}
		for(ClassRecord record :crList){
			map.put("classId", record.getClassId());
			List<ClassRecord> crecordList = recordService.findhomeworkIdExamList(map);
			if (crecordList != null && !crecordList.isEmpty()) {
				record.setRecordList(crecordList);
			}else {
				record.setRecordList(null);
			}
		}
		
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		if (crList != null && !crList.isEmpty()) {
			mv.addObject("courseList", crList);
		} else {
			mv.addObject("courseList", null);
		}
		mv.addObject("classId", classId);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("crList", crList);
		mv.addObject("classList", classList);
		mv.setViewName("homework/homeworkmanage");
		return mv;
	}

	/**
	 * 后台查看前端学员作业完成情况
	 * 
	 * @param subjectId
	 * @param classId
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/completion")
	public ModelAndView completion(Integer subjectId, Integer classId,
			Integer pageNo, Integer pageSize,String studentName,Integer paperId) {
		ModelAndView mv = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = homeworkRecordService.getCount(classId, subjectId, studentName,paperId);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<HomeworkRecord> hrList = homeworkRecordService.finHomeworkRecord(classId, subjectId,
				(pageNo - 1) * pageSize, pageSize, studentName,paperId);
		if (hrList != null && !hrList.isEmpty()) {
			mv.addObject("hrList", hrList);
		} else {
			mv.addObject("hrList", null);
		}
		ClassNo classNo = classNoService.getClassById(classId);
		Grade grade = gradeService.getGradeById(classNo.getGrade());
		List<Subject> subjects = subjectService.getSubject();
		List<ClassNo> classNos = classNoService.getClassesNo();
		mv.addObject("page", pageNo);
		mv.addObject("subjects", subjects);
		mv.addObject("classNos", classNos);
		mv.addObject("pageSize", pageSize);
		mv.addObject("classId", classId);
		mv.addObject("paperId", paperId);
		mv.addObject("subjectId", subjectId);
		mv.addObject("grade", grade);
		mv.addObject("totalPages", totalPages);
		mv.addObject("studentName", studentName);
		mv.setViewName("classExamRecord/completion");
		return mv;
	}
	
	/**
	 * 删除作业历史记录
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/deleteHomeRecord")
	public void deleteHomeRecord(Integer id, HttpServletResponse response) {
		// HomeworkRecord hr = homeworkRecordService.getHomeworkRecordById(id);
		try {
			homeworkRecordService.deleteRecordById(id);
			output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
		} catch (Exception e) {
			output(response, JsonUtil.buildFalseJson("1", "删除失败!"));

		}
	}

	/**
	 * 作业管理添加作业的分业
	 * 
	 * @param response
	 */
	@RequestMapping("/paperInfoList")
	public void paperInfoList(HttpServletResponse response, Integer pageNo, Integer pageSize,
			HttpServletRequest request, Homework hk,Integer grade,Integer subject) {

		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		session = request.getSession();
		List<Homework> homeworks = null;
		int rowsCount = 0;
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		if (teacher.getTeacherType().equals("5") && teacher.getTeacherType().equals("6")) {
			homeworks = homeworkService.getHomeworkByTeacherId1(teacher.getTeacherId(),
					(pageNo - 1) * pageSize, pageSize, hk,grade,teacher.getSubject());
			rowsCount = homeworkService.getHomeworkByTeacherIdCount1(teacher.getTeacherId(),
					(pageNo - 1) * pageSize, pageSize,grade,teacher.getSubject());
		} else {
			rowsCount = homeworkService.seeCountId1(hk,grade,subject);
		    homeworks = homeworkService.seeHomeworkByTeacherId1((pageNo - 1) * pageSize, pageSize, hk,grade,subject);
		}
		PageUtil util = new PageUtil(rowsCount, pageNo, pageSize);
		if (!homeworks.isEmpty() && homeworks != null) {
			output(response, JsonUtil.buildJsonByTotalCount(homeworks, util.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
	}

	/**
	 * 显示课后作业试卷列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param hk
	 * @param request
	 * @return
	 */
	@RequestMapping("/homeworPaperList")
	public ModelAndView homeworList(Integer pageNo, Integer pageSize, Homework hk, HttpServletRequest request) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		int rowsCount = homeworkService.getCountId(hk, teacher.getTeacherId());
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<Homework> homeworks = homeworkService.getHomeworkByTeacherId(teacher.getTeacherId(),
				(pageNo - 1) * pageSize, pageSize, hk,null,null);
		ModelAndView view = new ModelAndView();
		view.addObject("homeworks", homeworks);
		view.addObject("page", pageNo);
		view.addObject("pageSize", pageSize);
		view.addObject("totalPages", totalPages);
		view.setViewName("homework/saveHomewkList");
		return view;

	}

	/**
	 * 添加作业(给前端学员做的)
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addHomeworkCourse")
	public void addHomeworkCourse(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		// int subject = Integer.valueOf(request.getParameter("subject"));
		// int courseId = Integer.valueOf(request.getParameter("courseId"));
		int homeworkId = Integer.valueOf(request.getParameter("homeworkId"));
		int classId = Integer.valueOf(request.getParameter("classid"));
		int status = 0;// 用于判断该班级的学生是否有做过该作业
		ClassNo classNo = classNoService.getClassById(classId);
		Homework homework = homeworkService.getHomeworkById(homeworkId);
		HomeworkRecord record = new HomeworkRecord();
		ClassRecord cr = recordService.getPaperById(classId, null, null, homeworkId);
		List<StudentCareer> students = studentCareerService.getStudentByClassNo(classId, 2);// 查找已分班并且班级编号为classid的学生
		if (homework.getSubject() != classNo.getSubject()) {
			output(response, JsonUtil.buildFalseJson("4", "试卷对应的科目不符合，请仔细查看后再添加!"));
		} else if (homework.getGrade() != classNo.getGrade()) {
			output(response, JsonUtil.buildFalseJson("3", "试卷对应的年级不符合，请仔细查看后再添加!"));
		} else if (students == null || students.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("2", "系统检测到该班级暂无分配到学生,请先添加学员!"));
		} else if (cr != null){
			output(response, JsonUtil.buildFalseJson("5", "系统检测到您已添加过该作业，请选择其他作业"));
		} else {
			for (int i = 0; i < students.size(); i++) {
				// int studentId = students.get(i).getStudentId();
				// HomeworkRecord record2 =
				// homeworkRecordService.getByStuIdandHomeId(studentId,
				// homeworkId);
				if (students.size() == i + 1 && status == 0) {
					for (int j = 0; j < students.size(); j++) {
						int studentId1 = students.get(j).getStudentId();
						record.setStudentId(studentId1);
						record.setHomeworkId(homeworkId);
						record.setSubject(classNo.getSubject());
						record.setClassId(classId);
						record.setStatus(1);// 添加即启动该作业
						record.setCreateTime(new Date());
						homeworkRecordService.addHomeworkrecord(record);// 添加到作业记录表，返回主键
					}
				}
			}
			HomeworkRecord record2 = homeworkRecordService.getHomeworkRecordById(record.getId());
			if (record2 != null) {
				cr = new ClassRecord();
				cr.setCreateTime(new Date());
				cr.setClassId(classId);
				cr.setHomeworkId(homeworkId);
				cr.setStatus(0);
				recordService.addClassRecord(cr);
				log.info("添加考卷成功！");
				// 添加作业之后把作业使用数加一
				homework.setUpdateTime(new Date());
				homework.setUserTimes(homework.getUserTimes() + 1);
				homeworkService.updateUserTimes(homework);
				output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("作业管理");
				log1.setOperationType("添加");
				log1.setOperationResult("成功");
				log1.setOperationId(teacher.getTeacherId().toString());
				log1.setOperationName(teacher.getTeacherName());
				log1.setOperationContent("管理员【" + teacher.getTeacherName() + "】添加作业成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
			} else {
				log.info("添加考卷失败!");
				output(response, JsonUtil.buildFalseJson("1", "系统错误!"));
			}
		}
	}

	/**
	 * 取消作业
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/cancelHomework")
	public void cancelHomework(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		int recordId = Integer.valueOf(request.getParameter("recordId"));
		ClassRecord record = recordService.getClassRecordById(recordId);
		int classId = Integer.valueOf(request.getParameter("classId"));
		System.out.println("传来的班级编号！" + classId);
		List<StudentCareer> students = studentCareerService.getStudentByClassNo(classId, 2);// 查找已分班并且班级编号为classid的学生
		HomeworkRecord hr = null;
		if (record.getHomeworkId() != null) {
			hr = homeworkRecordService.getHomeworkRecordById(record.getHomeworkId());
		}
		if (students == null) {
			output(response, JsonUtil.buildFalseJson("2", "该班级暂无分配到学生"));
		} else if (hr != null && hr.getStatus() != 1) {
			output(response, JsonUtil.buildFalseJson("3", "作业已被分配，暂时不能撤销!"));
		} else if (record.getHomeworkId() == null) {
			output(response, JsonUtil.buildFalseJson("4", "暂时没有作业，撤销失败!"));
		} else {
			for (int i = 0; i < students.size(); i++) {
				int studentid = students.get(i).getStudentId();
				homeworkRecordService.deleteByStuIdandHomeId(studentid, record.getHomeworkId());// 删除该班级学生的考试记录表
			}
			try {
				record.setUpdateTime(new Date());
				record.setStatus(0);
				record.setHomeworkId(0);
				recordService.updateStatus(record);
				Homework homework = homeworkService.getHomeworkById(record.getHomeworkId());
				log.info("取消作业成功！");
				// 取消作业之后把作业使用数减一
				if (homework != null) {
					homework.setUpdateTime(new Date());
					if (homework.getUserTimes() != 0) {
						homework.setUserTimes(homework.getUserTimes() - 1);
					} else {
						homework.setUserTimes(0);
					}
					homeworkService.updateUserTimes(homework);
				}
				output(response, JsonUtil.buildFalseJson("0", "取消作业成功！"));
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("作业管理");
				log1.setOperationType("取消");
				log1.setOperationResult("成功");
				log1.setOperationId(teacher.getTeacherId().toString());
				log1.setOperationName(teacher.getTeacherName());
				log1.setOperationContent("管理员【" + teacher.getTeacherName() + "】取消作业成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("取消作业失败！");
				output(response, JsonUtil.buildFalseJson("1", "取消作业失败！"));
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("作业管理");
				log1.setOperationType("取消");
				log1.setOperationResult("失败");
				log1.setOperationId(teacher.getTeacherId().toString());
				log1.setOperationName(teacher.getTeacherName());
				log1.setOperationContent("管理员【" + teacher.getTeacherName() + "】取消作业失败!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
			}
		}
	}

	/**
	 * 后台我的课后作业列表
	 * 
	 * @return
	 */
	@RequestMapping("/showHomeworkList")
	public ModelAndView showHomeworkList(HttpServletRequest request, Integer pageNo, Integer pageSize, Homework hk) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		ModelAndView mav = new ModelAndView();
		int rowsCount = 0;
		List<TeacherAuthority> taList = authorityService.getTeacherIdById(currTeacher.getTeacherId());
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {// 超级管理员能查看所有的作业
			rowsCount = homeworkService.seeCountId(hk,null,null);
			List<Homework> hList = homeworkService.seeHomeworkByTeacherId((pageNo - 1) * pageSize, pageSize, hk,null,null);
			if (hList != null && !hList.isEmpty()) {
				mav.addObject("hList", hList);
			} else {
				mav.addObject("hList", null);
			}
		} else {// 只能查看自己的和别人公开的
				// 没有读取别人公开作业的权限
			hk.setSubject(currTeacher.getSubject());
			for (TeacherAuthority ta : taList) {
				if (ta.getPaperAuthority() == 0 || ta.getPaperAuthority() == 2) {
					rowsCount = homeworkService.getCountId(hk, currTeacher.getTeacherId());
					List<Homework> hList = homeworkService.getHomeworkByTeacherId(currTeacher.getTeacherId(),
							(pageNo - 1) * pageSize, pageSize, hk,null,null);
					if (hList != null && !hList.isEmpty()) {
						mav.addObject("hList", hList);
					} else {
						mav.addObject("hList", null);
					}
				} else {
					// 有读取所有公开作业的权限
					
					String gradeIds = currTeacher.getGrade(); //获取教师的所有年级
					String[]  arr = gradeIds.split(",");
					List<String> gradeList = Arrays.asList(arr);
					
					rowsCount = homeworkService.getCountIspublicIdbyids(hk, currTeacher.getTeacherId(),gradeList);
					List<Homework> hList = homeworkService.getHomeworkByTeacherIdIsPublicbyids(currTeacher.getTeacherId(),
							(pageNo - 1) * pageSize, pageSize, hk,gradeList);
					if (!hList.isEmpty() && hList != null) {

						mav.addObject("hList", hList);
					} else {
						mav.addObject("hList", null);
					}
				}
			}
		}
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<Subject> sList = null;
		List<Grade> gList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList= subjectService.getSubject();
			gList = gradeService.getGrade();
		} else {
			sList = subjectService.getSubjectById(currTeacher.getSubject());
			String [] my = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(my);
			gList = gradeService.getGradeByIds(gradeList);
		}
		int homeworkCount = homeworkService.getConutHomework(currTeacher.getTeacherId(),currTeacher.getTeacherType());
		int isPublicCount = homeworkService.getIsPublicHomework(currTeacher.getTeacherId(),currTeacher.getTeacherType());
		mav.addObject("sList", sList);
		List<QuestionsType> qtList = qtypeService.questionsType();
		mav.addObject("qtList", qtList);
		List<TeachingMaterial> tmList = tmService.teachingMaterial();
		mav.addObject("tmList", tmList);
		mav.addObject("gList", gList);
		mav.addObject("page", pageNo);
		mav.addObject("pageSize", pageSize);
		mav.addObject("totalPages", totalPages);
		mav.addObject("homeworkCount", homeworkCount);
		mav.addObject("isPublicCount", isPublicCount);
		mav.addObject("hk", hk);
		mav.setViewName("homework/homeworkList");
		return mav;
	}

	/**
	 * 生成作业页面
	 * 
	 * @return
	 */
	@RequestMapping("/createHomework")
	public ModelAndView createHomework(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		// 循环拿到科目
		List<Subject> sList = null;
		List<Grade> gList = null;
		List<TeachingMaterial> tMaterialsList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			gList = gradeService.getGrade();
			tMaterialsList = tmService.teachingMaterial();
		} else {
			sList = subjectService.getTeacherIsSubject(currTeacher.getSubject());
			String[] gStrings = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(gStrings);
			gList = gradeService.getGradeByIds(gradeList);
			tMaterialsList = tmService.getTeacherIsMate(currTeacher.getSubject());
		}
		// 循环拿到年级
		ModelAndView mv = new ModelAndView();
		// 试卷类型
		List<TaskType> typeList = typeService.taskType();
		mv.addObject("typeList", typeList);
		// 标签库
		List<QuestionTag> qttList = questionTagService.questionTag();
		mv.addObject("qttList", qttList);
		mv.addObject("sList", sList);
		mv.addObject("gList", gList);
		// 循环拿到题型
		List<QuestionsType> qtList = qtypeService.questionsType();
		mv.addObject("qtList", qtList);
		// 循环拿到教材

		mv.addObject("tMaterialsList", tMaterialsList);
		// 循环拿到班级
		List<ClassNo> cList = classNoService.getClassesNo();
		mv.addObject("cList", cList);

		// 循环拿到季节类型
		List<SeasonType> stList = seasonTypeService.getSeasontype();
		mv.addObject("stList", stList);
		// 循环拿到考试类型
		List<TaskType> tyList = taskTypeService.taskType();
		mv.addObject("tyList", tyList);
		// 拿到范围
		List<RangeLesson> rlList = lessonService.findLesson();
		mv.addObject("rlList", rlList);
		mv.setViewName("homework/createHomework");
		return mv;
	}

	/**
	 * 后台添加作业
	 * 
	 * @param pInfo
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addHomework", method = RequestMethod.POST)
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		String name = request.getParameter("hkName");
		int subject = Integer.parseInt(request.getParameter("subject"));
		int grade = Integer.parseInt(request.getParameter("grade"));
		Integer science = null;
		if (request.getParameter("science") != null && !request.getParameter("science").equals("")) {
			science = Integer.parseInt(request.getParameter("science"));
		}
		int ispublic = Integer.parseInt(request.getParameter("isPublic"));
		Double totalScore = Double.parseDouble(request.getParameter("totalScore"));
		Integer begin = null;
		Integer end = null;
		if (request.getParameter("knowledgeBegin") != null && request.getParameter("knowledgeBegin") != "") {
			begin = Integer.parseInt(request.getParameter("knowledgeBegin"));
		}
		if (request.getParameter("knowledgeEnd") != null && request.getParameter("knowledgeEnd") != "") {
			end = Integer.parseInt(request.getParameter("knowledgeEnd"));
		}
		int degree = Integer.parseInt(request.getParameter("difficultyValue"));
		String json = request.getParameter("questionList");
		log.info("传来的题目json字符串" + json);
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(json);// 将json字符串转对象
		JsonObject jsonObject = null;
		if (element.isJsonObject()) {
			jsonObject = element.getAsJsonObject();
		}
		;
		JsonArray array = (JsonArray) jsonObject.get("objects");
		TeacherAuthority ta = authorityService.findIsTeacherGradeById(teacher.getTeacherId(), String.valueOf(grade));
		if (!teacher.getTeacherType().equals("5") && !teacher.getTeacherType().equals("6")) {
			System.out.println("json数组的长度：" + array.size());
			Homework hk = new Homework();
			hk.setHkName(name);
			hk.setSubject(subject);
			hk.setGrade(grade);
			if (science != null) {
				hk.setScience(science);
			}
			hk.setIsPublic(ispublic);
			if (begin != null) {
				hk.setKnowledgeBegin(begin);
			}
			if (end != null) {
				hk.setKnowledgeEnd(end);
			}
			hk.setDifficultyValue(degree);
			hk.setHkScore(totalScore);// 试题总分
			hk.setHkTotal(array.size());// 试题总数
			hk.setUserTimes(0);
			hk.setTeacherId(teacher.getTeacherId());
			hk.setTeacherName(teacher.getTeacherName());
			hk.setCreateTime(new Date());
			homeworkService.addHomework(hk);// 返回主键
			Homework hk2 = homeworkService.getHomeworkById(hk.getHkId());
			if (hk2 == null) {
				output(response, JsonUtil.buildFalseJson("1", "添加失败"));
			} else {
				HomeworkDetails details = new HomeworkDetails();
				for (int i = 0; i < array.size(); i++) {
					JsonObject jo2 = (JsonObject) array.get(i);
					if (Integer.valueOf(jo2.get("type").toString()) == 1) {
						System.out.println(Integer.valueOf(jo2.get("choiceId").toString()));
						details.setQuestionId(Integer.valueOf(jo2.get("choiceId").toString()));// 题目编号
						details.setValue(Double.valueOf(jo2.get("score").toString()));// 单选题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 2) {
						details.setQuestionId(Integer.valueOf(jo2.get("choiceId").toString()));// 题目编号
						details.setValue(Double.valueOf(jo2.get("score1").toString()));// 多选题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 3) {
						details.setQuestionId(Integer.valueOf(jo2.get("judgeId").toString()));// 题目编号
						details.setValue(Double.valueOf(jo2.get("score2").toString()));// 判断题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 4) {
						details.setQuestionId(Integer.valueOf(jo2.get("readId").toString()));// 题目编号
						List<QuestionRead> qrList = questionReadService
								.getReadChildByParentId(Integer.valueOf(jo2.get("readId").toString()));
						details.setValue(Double.valueOf(jo2.get("score_read").toString()) * qrList.size());// 阅读题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 5) {// 主观题
						details.setQuestionId(Integer.valueOf(jo2.get("subjectiveId").toString()));// 题目编号
						details.setValue(Double.valueOf(jo2.get("questionScore").toString()));// 主观题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 6) {
						details.setQuestionId(Integer.valueOf(jo2.get("fillId").toString()));// 题目编号
						details.setValue(Double.valueOf(jo2.get("questionScore").toString()));// 填空题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 7) {//完形填空
						QuestionCloze cloze = clozeService.getQuestionClozeById(Integer.valueOf(jo2.get("clozeId").toString()));
						cloze.setUpdateTime(new Date());
						cloze.setUsedCount(cloze.getUsedCount()+1);
						clozeService.updateIsPublic(cloze);
						details.setQuestionId(Integer.valueOf(jo2.get("clozeId").toString()));// 题目编号
						details.setValue(Double.valueOf(jo2.get("questionScore").toString()));// 填空题分数
					}
					details.setQuestionType(Integer.valueOf(jo2.get("type").toString()));// 题型
					details.setPaperId(hk2.getHkId());// 作业编号
					details.setCreateTime(new Date());
					homeworkDetailsService.savehomeworkDetails(details);
				}
				double homeScore = 0.0;
				List<HomeworkDetails> hdList = homeworkDetailsService.findHomeworkPaperList(details.getPaperId());
				for (HomeworkDetails hd : hdList) {
					homeScore += hd.getValue();
				}
				// 修改作业的分值
				hk2.setUpdateTime(new Date());
				hk2.setHkScore(homeScore);
				homeworkService.updateTotal(hk2);

				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("作业管理");
				log1.setOperationType("新增");
				log1.setOperationResult("成功");
				log1.setOperationId(teacher.getTeacherId().toString());
				log1.setOperationName(teacher.getTeacherName());
				log1.setOperationContent("管理员【" + teacher.getTeacherName() + "】添加作业【" + name + "成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
				output(response, JsonUtil.buildFalseJson("0", "添加成功！"));
			}
		} else {
			if (ta == null) {
				output(response, JsonUtil.buildFalseJson("1", "您没有录入作业的权限!"));
			} else {
				if ((ta.getPaperAuthority() == 0 || ta.getPaperAuthority() == 1)) {
					output(response, JsonUtil.buildFalseJson("1", "您没有录入作业的权限!"));
				} else if (!ta.getGrade().equals(String.valueOf(grade))) {
					output(response, JsonUtil.buildFalseJson("2", "您添加的年级与您现有的年级不一致!"));
				} else {
					System.out.println("json数组的长度：" + array.size());
					Homework hk = new Homework();
					hk.setHkName(name);
					hk.setSubject(subject);
					hk.setGrade(grade);
					if (science != null) {
						hk.setScience(science);
					}
					hk.setIsPublic(ispublic);
					if (begin != null) {
						hk.setKnowledgeBegin(begin);
					}
					if (end != null) {
						hk.setKnowledgeEnd(end);
					}
					hk.setDifficultyValue(degree);
					hk.setHkScore(totalScore);// 试题总分
					hk.setHkTotal(array.size());// 试题总数
					hk.setUserTimes(0);
					hk.setTeacherId(teacher.getTeacherId());
					hk.setTeacherName(teacher.getTeacherName());
					hk.setCreateTime(new Date());
					homeworkService.addHomework(hk);// 返回主键
					Homework hk2 = homeworkService.getHomeworkById(hk.getHkId());
					if (hk2 == null) {
						output(response, JsonUtil.buildFalseJson("1", "添加失败"));
					} else {
						HomeworkDetails details = new HomeworkDetails();
						for (int i = 0; i < array.size(); i++) {
							JsonObject jo2 = (JsonObject) array.get(i);
							if (Integer.valueOf(jo2.get("type").toString()) == 1) {
								System.out.println(Integer.valueOf(jo2.get("choiceId").toString()));
								details.setQuestionId(Integer.valueOf(jo2.get("choiceId").toString()));// 题目编号
								details.setValue(Double.valueOf(jo2.get("score").toString()));// 单选题分数
							} else if (Integer.valueOf(jo2.get("type").toString()) == 2) {
								details.setQuestionId(Integer.valueOf(jo2.get("choiceId").toString()));// 题目编号
								details.setValue(Double.valueOf(jo2.get("score1").toString()));// 多选题分数
							} else if (Integer.valueOf(jo2.get("type").toString()) == 3) {
								details.setQuestionId(Integer.valueOf(jo2.get("judgeId").toString()));// 题目编号
								details.setValue(Double.valueOf(jo2.get("score2").toString()));// 判断题分数
							} else if (Integer.valueOf(jo2.get("type").toString()) == 4) {
								details.setQuestionId(Integer.valueOf(jo2.get("readId").toString()));// 题目编号
								List<QuestionRead> qrList = questionReadService
										.getReadChildByParentId(Integer.valueOf(jo2.get("readId").toString()));
								details.setValue(Double.valueOf(jo2.get("score_read").toString()) * qrList.size());// 阅读题分数
							} else if (Integer.valueOf(jo2.get("type").toString()) == 5) {// 主观题
								details.setQuestionId(Integer.valueOf(jo2.get("subjectiveId").toString()));// 题目编号
								details.setValue(Double.valueOf(jo2.get("questionScore").toString()));// 主观题分数
							} else if (Integer.valueOf(jo2.get("type").toString()) == 6) {
								details.setQuestionId(Integer.valueOf(jo2.get("fillId").toString()));// 题目编号
								details.setValue(Double.valueOf(jo2.get("questionScore").toString()));// 填空题分数
							}
							details.setQuestionType(Integer.valueOf(jo2.get("type").toString()));// 题型
							details.setPaperId(hk2.getHkId());// 作业编号
							details.setCreateTime(new Date());
							homeworkDetailsService.savehomeworkDetails(details);
						}
						double homeScore = 0.0;
						List<HomeworkDetails> hdList = homeworkDetailsService
								.findHomeworkPaperList(details.getPaperId());
						for (HomeworkDetails hd : hdList) {
							homeScore += hd.getValue();
						}
						// 修改作业的分值
						hk2.setUpdateTime(new Date());
						hk2.setHkScore(homeScore);
						homeworkService.updateTotal(hk2);
						Log log1 = new Log();
						log1.setStartTime(new Date());
						log1.setModular("作业管理");
						log1.setOperationType("新增");
						log1.setOperationResult("成功");
						log1.setOperationId(teacher.getTeacherId().toString());
						log1.setOperationName(teacher.getTeacherName());
						log1.setOperationContent("教师【" + teacher.getTeacherName() + "】添加作业【" + name + "成功!");
						log1.setEndTime(new Date());
						logService.saveLog(log1);
						output(response, JsonUtil.buildFalseJson("0", "添加成功！"));
					}
				}
			}
		}
	}

	/**
	 * 按条件模糊查询，然后显示出来
	 * 
	 * @return
	 */
	@RequestMapping(value = "/fuzzyQuery", method = RequestMethod.POST)
	public void fuzzyQuery(Integer type, Integer number, Integer score, Integer subject, String tagId,
			HttpServletRequest request, HttpServletResponse response, Integer grade, Integer science) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		TeacherAuthority ta = authorityService.findIsTeacherGradeById(currTeacher.getTeacherId(),
				String.valueOf(grade));
		/**
		 * 1)先判断当前登录的是否是超级管理员。是则执行if,不是则执行else。 2)在判断有没有读写的权限
		 */
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			String tags1 = null, tags2 = null, tags3 = null, tags4 = null, tags5 = null, tags6 = null, tags7 = null,
					tags8 = null, tags9 = null, tags10 = null;
			String[] tags = tagId.split(",");
			if (tags.length > 9) {
				output(response, JsonUtil.buildFalseJson("3", "标签最多能选10个!"));
			} else {
				if (tags.length > 0) {
					tags1 = "," + tags[0] + ",";
				}
				if (tags.length > 1) {
					tags2 = "," + tags[1] + ",";
				}
				if (tags.length > 2) {
					tags3 = "," + tags[2] + ",";
				}
				if (tags.length > 3) {
					tags4 = "," + tags[3] + ",";
				}
				if (tags.length > 4) {
					tags5 = "," + tags[4] + ",";
				}
				if (tags.length > 5) {
					tags6 = "," + tags[5] + ",";
				}
				if (tags.length > 6) {
					tags7 = "," + tags[6] + ",";
				}
				if (tags.length > 7) {
					tags8 = "," + tags[7] + ",";
				}
				if (tags.length > 8) {
					tags9 = "," + tags[8] + ",";
				}
				if (tags.length > 9) {
					tags10 = "," + tags[9] + ",";
				}
				List listtag = Arrays.asList(tags);
				Map<String, Object> map = new HashMap<>();
				map.put("number", number);
				map.put("subject", subject);
				map.put("listTag", listtag);
				map.put("tags1", tags1);
				map.put("tags2", tags2);
				map.put("tags3", tags3);
				map.put("tags4", tags4);
				map.put("tags5", tags5);
				map.put("tags6", tags6);
				map.put("tags7", tags7);
				map.put("tags8", tags8);
				map.put("tags9", tags9);
				map.put("tags10", tags10);
				map.put("type", type);
				map.put("grade", grade);
				map.put("science", science);
				if (type == 1) {// 单选
					List<QuestionSingleChoice> list = questionSingleChoiceService.getSingleByRandAdmin(map);
					if (list == null || list.isEmpty()) {
						output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
						log.info("获取单选数据失败!");
					} else {
						List<QuestionSingleChoice> returnList = new ArrayList<QuestionSingleChoice>();
						for (QuestionSingleChoice qsc : list) {
							qsc.setScore(Float.valueOf(score));
							returnList.add(qsc);
						}
						output(response, JsonUtil.buildJson(returnList));

						log.info("获取单选数据成功!" + list + ",总共有" + list.size() + "条记录");
					}
				} else if (type == 2) {// 多选
					List<QuestionMulitChoice> list = questionMulitChoiceService.getMulitByRandAdmin(map);
					if (list == null || list.isEmpty()) {
						output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
						log.info("获取多选数据失败!");
					} else {
						List<QuestionMulitChoice> returnList = new ArrayList<QuestionMulitChoice>();
						for (QuestionMulitChoice qmc : list) {
							qmc.setScore1(Float.valueOf(score));
							returnList.add(qmc);
						}
						output(response, JsonUtil.buildJson(returnList));
						log.info("获取多选数据成功!" + list + ",总共有" + list.size() + "条记录");
					}
				} else if (type == 3) {// 判断
					List<QuestionJudge> list = questionJudgeService.getJudgeByRandAdmin(map);
					if (list == null || list.isEmpty()) {
						output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
						log.info("获取判断数据失败!");
					} else {
						List<QuestionJudge> returnList = new ArrayList<QuestionJudge>();
						for (QuestionJudge qtj : list) {
							qtj.setScore2(Float.valueOf(score));
							returnList.add(qtj);
						}
						output(response, JsonUtil.buildJson(returnList));
						log.info("获取判断数据成功!" + list + ",总共有" + list.size() + "条记录");
					}
				} else if (type == 4) {// 阅读理解
					List<QuestionRead> list = questionReadService.getReadByRandAdmin(map);
					if (list == null || list.isEmpty()) {
						output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
						log.info("获取阅读数据失败!");
					} else {
						List<QuestionRead> returnList = new ArrayList<QuestionRead>();
						for (QuestionRead qtr : list) {
							qtr.setScore_read(Float.valueOf(score));
							returnList.add(qtr);
						}
						output(response, JsonUtil.buildJson(returnList));
						output(response, JsonUtil.buildJson(list));
						log.info("获取阅读数据成功!" + list + ",总共有" + list.size() + "条记录");
					}
				} else if (type == 5) {// 主观题
					List<SubjectiveQuestion> saquestionList = questionService.getMulitByRandAdmin(map);
					if (saquestionList == null || saquestionList.isEmpty()) {
						output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
					} else {
						List<SubjectiveQuestion> returnList = new ArrayList<SubjectiveQuestion>();
						for (SubjectiveQuestion sq : saquestionList) {
							sq.setQuestionScore(Double.valueOf(score));
							returnList.add(sq);
						}
						output(response, JsonUtil.buildJson(returnList));
						output(response, JsonUtil.buildJson(saquestionList));
					}
				} else if (type == 6) {
					List<QuestionFill> qfList = fillService.getFillByRandAdmin(map);
					if (qfList == null || qfList.isEmpty()) {
						output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
					} else {
						List<QuestionFill> returnList = new ArrayList<QuestionFill>();
						for (QuestionFill sq : qfList) {
							sq.setQuestionScore(Double.valueOf(score));
							returnList.add(sq);
						}
						output(response, JsonUtil.buildJson(returnList));
						output(response, JsonUtil.buildJson(qfList));
					}
				} else if (type == 7) {
					List<QuestionCloze> clozes = clozeService.QuestionClozeAdmin(map);
					if (clozes == null || clozes.isEmpty()) {
						output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
					}else {
						List<QuestionCloze> returnList = new ArrayList<QuestionCloze>();
						for (QuestionCloze cloze:clozes) {
							cloze.setQuestionScore(score);
							returnList.add(cloze);
						}
						output(response, JsonUtil.buildJson(returnList));
						output(response, JsonUtil.buildJson(clozes));
					}
				}
			}
		} else {
			if (ta == null) {
				output(response, JsonUtil.buildFalseJson("4", "你没有读写作业的权限!"));
			} else if (ta.getPaperAuthority() == 0 || ta.getPaperAuthority() == 1) {
				output(response, JsonUtil.buildFalseJson("1", "你没有写作业的权限!"));
			} else if (ta.getPaperAuthority() == 0 || ta.getPaperAuthority() == 2) {
				output(response, JsonUtil.buildFalseJson("2", "你没有读作业的权限!"));
			} else {
				String tags1 = null, tags2 = null, tags3 = null, tags4 = null, tags5 = null, tags6 = null, tags7 = null,
						tags8 = null, tags9 = null, tags10 = null;
				String[] tags = tagId.split(",");
				if (tags.length > 9) {
					output(response, JsonUtil.buildFalseJson("3", "标签最多能选10个!"));
				} else {
					if (tags.length > 0) {
						tags1 = "," + tags[0] + ",";
					}
					if (tags.length > 1) {
						tags2 = "," + tags[1] + ",";
					}
					if (tags.length > 2) {
						tags3 = "," + tags[2] + ",";
					}
					if (tags.length > 3) {
						tags4 = "," + tags[3] + ",";
					}
					if (tags.length > 4) {
						tags5 = "," + tags[4] + ",";
					}
					if (tags.length > 5) {
						tags6 = "," + tags[5] + ",";
					}
					if (tags.length > 6) {
						tags7 = "," + tags[6] + ",";
					}
					if (tags.length > 7) {
						tags8 = "," + tags[7] + ",";
					}
					if (tags.length > 8) {
						tags9 = "," + tags[8] + ",";
					}
					if (tags.length > 9) {
						tags10 = "," + tags[9] + ",";
					}
					List listtag = Arrays.asList(tags);
					Map<String, Object> map = new HashMap<>();
					map.put("number", number);
					map.put("subject", subject);
					map.put("listTag", listtag);
					map.put("tags1", tags1);
					map.put("tags2", tags2);
					map.put("tags3", tags3);
					map.put("tags4", tags4);
					map.put("tags5", tags5);
					map.put("tags6", tags6);
					map.put("tags7", tags7);
					map.put("tags8", tags8);
					map.put("tags9", tags9);
					map.put("tags10", tags10);
					map.put("type", type);
					map.put("grade", grade);
					map.put("science", science);
					map.put("teacherId", currTeacher.getTeacherId());
					if (type == 1) {// 单选
						List<QuestionSingleChoice> list = questionSingleChoiceService.getSingleByRand(map);
						if (list == null || list.isEmpty()) {
							output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
							log.info("获取单选数据失败!");
						} else {
							List<QuestionSingleChoice> returnList = new ArrayList<QuestionSingleChoice>();
							for (QuestionSingleChoice qsc : list) {
								qsc.setScore(Float.valueOf(score));
								returnList.add(qsc);
							}
							output(response, JsonUtil.buildJson(returnList));

							log.info("获取单选数据成功!" + list + ",总共有" + list.size() + "条记录");
						}
					} else if (type == 2) {// 多选
						List<QuestionMulitChoice> list = questionMulitChoiceService.getMulitByRand(map);
						if (list == null || list.isEmpty()) {
							output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
							log.info("获取多选数据失败!");
						} else {
							List<QuestionMulitChoice> returnList = new ArrayList<QuestionMulitChoice>();
							for (QuestionMulitChoice qmc : list) {
								qmc.setScore1(Float.valueOf(score));
								returnList.add(qmc);
							}
							output(response, JsonUtil.buildJson(returnList));
							log.info("获取多选数据成功!" + list + ",总共有" + list.size() + "条记录");
						}
					} else if (type == 3) {// 判断
						List<QuestionJudge> list = questionJudgeService.getJudgeByRand(map);
						if (list == null || list.isEmpty()) {
							output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
							log.info("获取判断数据失败!");
						} else {
							List<QuestionJudge> returnList = new ArrayList<QuestionJudge>();
							for (QuestionJudge qtj : list) {
								qtj.setScore2(Float.valueOf(score));
								returnList.add(qtj);
							}
							output(response, JsonUtil.buildJson(returnList));
							log.info("获取判断数据成功!" + list + ",总共有" + list.size() + "条记录");
						}
					} else if (type == 4) {// 阅读理解
						List<QuestionRead> list = questionReadService.getReadByRand(map);
						if (list == null || list.isEmpty()) {
							output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
							log.info("获取阅读数据失败!");
						} else {
							List<QuestionRead> returnList = new ArrayList<QuestionRead>();
							for (QuestionRead qtr : list) {
								qtr.setScore_read(Float.valueOf(score));
								returnList.add(qtr);
							}
							output(response, JsonUtil.buildJson(returnList));
							output(response, JsonUtil.buildJson(list));
							log.info("获取阅读数据成功!" + list + ",总共有" + list.size() + "条记录");
						}
					} else if (type == 5) {// 主观题
						List<SubjectiveQuestion> saquestionList = questionService.getReadByRandAdminIsPublic(map);
						if (saquestionList == null || saquestionList.isEmpty()) {
							output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
						} else {
							List<SubjectiveQuestion> returnList = new ArrayList<SubjectiveQuestion>();
							for (SubjectiveQuestion sq : saquestionList) {
								sq.setQuestionScore(Double.valueOf(score));
								returnList.add(sq);
							}
							output(response, JsonUtil.buildJson(returnList));
							output(response, JsonUtil.buildJson(saquestionList));
						}
					} else if (type == 6) {
						List<QuestionFill> qfList = fillService.getFillAdminIsPublic(map);
						if (qfList == null || qfList.isEmpty()) {
							output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
						} else {
							List<QuestionFill> returnList = new ArrayList<QuestionFill>();
							for (QuestionFill sq : qfList) {
								sq.setQuestionScore(Double.valueOf(score));
								returnList.add(sq);
							}
							output(response, JsonUtil.buildJson(returnList));
							output(response, JsonUtil.buildJson(qfList));
						}
					} else if (type == 7) {
						List<QuestionCloze> clozes = clozeService.QuestionClozeAdmin(map);
						if (clozes == null || clozes.isEmpty()) {
							output(response, JsonUtil.buildFalseJson("2", "暂无数据，请换个条件试试!"));
						}else {
							List<QuestionCloze> returnList = new ArrayList<QuestionCloze>();
							for (QuestionCloze cloze:clozes) {
								cloze.setQuestionScore(score);
								returnList.add(cloze);
							}
							output(response, JsonUtil.buildJson(returnList));
							output(response, JsonUtil.buildJson(clozes));
						}
					}
				}
			}
		}
	}

	/**
	 * 修改作业页面
	 * 
	 * @return
	 */
	@RequestMapping("/modityHome")
	public ModelAndView modity(Integer homeworkId) {
		ModelAndView mv = new ModelAndView();
		Homework hk = homeworkService.getHomework(homeworkId);
		mv.addObject("hk", hk);
		mv.setViewName("homework/modityHomework");
		return mv;
	}

	/**
	 * 跳转修改页面
	 * 
	 * @param request
	 * @param piId
	 * @return
	 */
	@RequestMapping("/modity")
	public ModelAndView modity(Integer paperId, Integer pageNo, Integer pageSize, HttpServletRequest request) {

		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 6;
		}
		int rowsCount = homeworkDetailsService.getHomDateilsPaperId(paperId);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<HomeworkDetails> hdList = homeworkDetailsService.findHomHaIdList(paperId, (pageNo - 1) * pageSize,
				pageSize);
		List<SeePaper> spList = new ArrayList<SeePaper>();
		for (HomeworkDetails pd : hdList) {
			if (pd.getQuestionType() == 1) {// 单选
				QuestionSingleChoice qsc = questionSingleChoiceService.getSingleChoiceById(pd.getQuestionId());
				SeePaper sp = new SeePaper();
				sp.setPaperId(pd.getHdId());
				sp.setQuestionId(qsc.getChoiceId());
				sp.setChoiceDesc(qsc.getChoiceDesc());
				sp.setOptionA(qsc.getOptionA());
				sp.setOptionB(qsc.getOptionB());
				sp.setOptionC(qsc.getOptionC());
				sp.setOptionD(qsc.getOptionD());
				sp.setQuestiontype(pd.getQuestionType());
				sp.setQuestionValue(pd.getValue());
				spList.add(sp);
			}
			if (pd.getQuestionType() == 2) {// 多选
				QuestionMulitChoice qmc = questionMulitChoiceService.getMulitChoiceById(pd.getQuestionId());
				SeePaper sp1 = new SeePaper();
				sp1.setPaperId(pd.getHdId());
				sp1.setQuestionId(pd.getQuestionId());
				sp1.setQuestiontype(pd.getQuestionType());
				sp1.setQuestionValue(pd.getValue());
				sp1.setChoiceDesc1(qmc.getChoiceDesc1());
				sp1.setOptionA1(qmc.getOptionA1());
				sp1.setOptionB1(qmc.getOptionB1());
				sp1.setOptionC1(qmc.getOptionB1());
				sp1.setOptionD1(qmc.getOptionD1());
				spList.add(sp1);
			}
			if (pd.getQuestionType() == 3) {// 判断
				QuestionJudge qj = questionJudgeService.getJudgeById(pd.getQuestionId());
				SeePaper sp2 = new SeePaper();
				sp2.setPaperId(pd.getHdId());
				sp2.setQuestionId(pd.getQuestionId());
				sp2.setQuestiontype(pd.getQuestionType());
				sp2.setQuestionValue(pd.getValue());
				sp2.setJudgeDesc(qj.getJudgeDesc());
				spList.add(sp2);
			}
			if (pd.getQuestionType() == 4) {// 阅读理解
				SeePaper sp3 = new SeePaper();
				QuestionRead qRead = questionReadService.getReadById(pd.getQuestionId());
				List<QuestionPaer> qqrrList = new ArrayList<QuestionPaer>();
				List<QuestionRead> qrList = questionReadService.getReadChildByParentId(qRead.getReadId());
				for (QuestionRead qr : qrList) {
					QuestionPaer qrQuestionRead = new QuestionPaer();
					qrQuestionRead.setOptiontitle(qr.getOptiontitle());
					qrQuestionRead.setOptionA_read(qr.getOptionA_read());
					qrQuestionRead.setOptionB_read(qr.getOptionB_read());
					qrQuestionRead.setOptionC_read(qr.getOptionC_read());
					qrQuestionRead.setOptionD_read(qr.getOptionD_read());
					qqrrList.add(qrQuestionRead);
				}
				sp3.setQuestionRead(qqrrList);
				sp3.setPaperId(pd.getHdId());
				sp3.setQuestionId(pd.getQuestionId());
				sp3.setQuestiontype(pd.getQuestionType());
				sp3.setQuestionValue(pd.getValue());
				sp3.setReadDesc(qRead.getReadDesc());
				spList.add(sp3);
			}
			if (pd.getQuestionType() == 5) {// 主观题
				SeePaper sp4 = new SeePaper();
				SubjectiveQuestion subjective = questionService.getSubjectiveQuestionById(pd.getQuestionId());
				sp4.setPaperId(pd.getHdId());
				sp4.setQuestionId(pd.getQuestionId());
				sp4.setQuestiontype(pd.getQuestionType());
				sp4.setQuestionValue(pd.getValue());
				sp4.setSubjectiveTitle(subjective.getSubjectiveTitle());
				spList.add(sp4);
			}
			if (pd.getQuestionType() == 6) {// 填空题
				SeePaper sp5 = new SeePaper();
				QuestionFill fill = fillService.getQuestionFillById(pd.getQuestionId());
				sp5.setPaperId(pd.getHdId());
				sp5.setQuestionId(pd.getQuestionId());
				sp5.setQuestiontype(pd.getQuestionType());
				sp5.setQuestionValue(pd.getValue());
				sp5.setFillTitle(fill.getFillTitle());
				sp5.setFillAnswer(fill.getFillAnswer());
				spList.add(sp5);
			}
			if (pd.getQuestionType() == 7) {
				SeePaper sp6 = new SeePaper();
				QuestionCloze cloze = clozeService.getQuestionClozeById(pd.getQuestionId());
				sp6.setPaperId(pd.getHdId());
				sp6.setQuestionId(pd.getQuestionId());
				sp6.setQuestiontype(pd.getQuestionType());
				sp6.setQuestionValue(pd.getValue());
				sp6.setClozeContent(cloze.getClozeContent());
				spList.add(sp6);
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("spList", spList);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		// 循环拿到科目
		List<Subject> sList = null;
		List<Grade> gList = null;
		List<TeachingMaterial> tMaterialsList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			gList = gradeService.getGrade();
			tMaterialsList = tmService.teachingMaterial();
		} else {
			sList = subjectService.getTeacherIsSubject(currTeacher.getSubject());
			String[] gStrings = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(gStrings);
			gList = gradeService.getGradeByIds(gradeList);
			tMaterialsList = tmService.getTeacherIsMate(currTeacher.getSubject());
		}
		// 试卷类型
		List<TaskType> typeList = typeService.taskType();
		mv.addObject("typeList", typeList);
		// 标签库
		List<QuestionTag> qttList = questionTagService.questionTag();
		mv.addObject("qttList", qttList);
		mv.addObject("sList", sList);
		mv.addObject("gList", gList);
		// 循环拿到题型
		List<QuestionsType> qtList = qtypeService.questionsType();
		mv.addObject("qtList", qtList);
		// 循环拿到教材

		mv.addObject("tMaterialsList", tMaterialsList);

		// 循环拿到班级
		List<ClassNo> cList = classNoService.getClassesNo();
		mv.addObject("cList", cList);
		// 循环拿到季节类型
		List<SeasonType> stList = seasonTypeService.getSeasontype();
		mv.addObject("stList", stList);
		// 循环拿到考试类型
		List<TaskType> tyList = taskTypeService.taskType();
		mv.addObject("tyList", tyList);
		// 拿到范围
		List<RangeLesson> rlList = lessonService.findLesson();
		mv.addObject("rlList", rlList);
		Homework homework = homeworkService.getHomework(paperId);
		if (homework != null) {
			TeachingMaterial tm = tmService.getMaterialById(homework.getScience());
			RangeLesson rLesson = lessonService.getLessonById(homework.getKnowledgeBegin());
			RangeLesson rLesson1 = lessonService.getLessonById(homework.getKnowledgeEnd());
			mv.addObject("tm", tm);
			mv.addObject("rLesson", rLesson);
			mv.addObject("rLesson1", rLesson1);
		}
		mv.addObject("homework", homework);
		mv.setViewName("homework/modityHomework");
		return mv;
	}

	/**
	 * 查看试卷详情
	 * 
	 * @param piId
	 * @return
	 */
	@RequestMapping("/paperTypeDetails")
	private ModelAndView paperDetails(Integer hkId, HttpServletResponse response, Integer pageNo) {
		ModelAndView view = new ModelAndView();
		Double singlescore = 0.0, mulitscore = 0.0, judgescore = 0.0, readscore = 0.0, subjective = 0.0, fill = 0.0,cloze=0.0;
		int singleChoice, Judge, Read, mulitChoice, subCount, fillCount,clozeCount;
		Double danxuan = 0.0, duoxuan = 0.0, pduan = 0.0, yuedu = 0.0, question = 0.0, fillScore = 0.0,clozeScore=0.0;
		Homework homework = homeworkService.getHomeworkById(hkId);
		List<QuestionSingleChoice> singles = questionSingleChoiceService.findHomeworkDetails(hkId);
		List<QuestionMulitChoice> mulits = questionMulitChoiceService.findHomeworkDetails(hkId);
		List<QuestionJudge> judges = questionJudgeService.findHomeworkDetails(hkId);
		List<QuestionRead> reads = questionReadService.findHomeworkDetails(hkId);
		List<SubjectiveQuestion> subjectives = questionService.findHomeworkDetails(hkId);
		List<QuestionFill> qfList = fillService.findHomeworkDetails(hkId);
		List<QuestionCloze> clozes = clozeService.findHomeworkDetails(hkId);
		List<QuestionRead> qrList = null;
		// 拿到题目顺序
		String singleOrder, multOrder, judgeOrder, fillOrder, subOrder, readOrder,clozeOrder;
		int i = 1;
		if (!singles.isEmpty()) {
			if (i == 1) {
				singleOrder = "一";
				view.addObject("singleOrder", singleOrder);
				i++;
			}
		}
		if (!mulits.isEmpty()) {
			if (i == 1) {
				multOrder = "一";
				i++;
				view.addObject("multOrder", multOrder);
			} else {
				multOrder = "二";
				i++;
				view.addObject("multOrder", multOrder);
			}

		}
		if (!judges.isEmpty()) {
			if (i == 1) {
				judgeOrder = "一";
				i++;
				view.addObject("judgeOrder", judgeOrder);
			} else if (i == 2) {
				judgeOrder = "二";
				i++;
				view.addObject("judgeOrder", judgeOrder);
			} else {
				judgeOrder = "三";
				i++;
				view.addObject("judgeOrder", judgeOrder);
			}
		}
		if (!reads.isEmpty()) {
			if (i == 1) {
				readOrder = "一";
				i++;
				view.addObject("readOrder", readOrder);
			} else if (i == 2) {
				readOrder = "二";
				i++;
				view.addObject("readOrder", readOrder);
			} else if (i == 3) {
				readOrder = "三";
				i++;
				view.addObject("readOrder", readOrder);
			} else {
				readOrder = "四";
				i++;
				view.addObject("readOrder", readOrder);
			}
		}
		if (!subjectives.isEmpty()) {
			if (i == 1) {
				subOrder = "一";
				i++;
				view.addObject("subOrder", subOrder);
			} else if (i == 2) {
				subOrder = "二";
				i++;
				view.addObject("subOrder", subOrder);
			} else if (i == 3) {
				subOrder = "三";
				i++;
				view.addObject("subOrder", subOrder);
			} else if (i == 4) {
				subOrder = "四";
				i++;
				view.addObject("subOrder", subOrder);
			} else {
				subOrder = "五";
				i++;
				view.addObject("subOrder", subOrder);
			}
		}
		if (!qfList.isEmpty()) {
			if (i == 1) {
				fillOrder = "一";
				i++;
				view.addObject("fillOrder", fillOrder);
			} else if (i == 2) {
				fillOrder = "二";
				i++;
				view.addObject("fillOrder", fillOrder);
			} else if (i == 3) {
				fillOrder = "三";
				i++;
				view.addObject("fillOrder", fillOrder);
			} else if (i == 4) {
				fillOrder = "四";
				i++;
				view.addObject("fillOrder", fillOrder);
			} else if (i == 5) {
				fillOrder = "五";
				i++;
				view.addObject("fillOrder", fillOrder);
			} else {
				fillOrder = "六";
				i++;
				view.addObject("fillOrder", fillOrder);
			}
		}
		if (!clozes.isEmpty()) {
			if(i==1){
				clozeOrder ="一";
				i++;
				view.addObject("clozeOrder",clozeOrder);
			}else if(i==2){
				clozeOrder ="二";
				i++;
				view.addObject("clozeOrder",clozeOrder);
			}else if(i==3){
				clozeOrder ="三";
				i++;
				view.addObject("clozeOrder",clozeOrder);
			}else if(i==4){
				clozeOrder ="四";
				i++;
				view.addObject("clozeOrder",clozeOrder);
			}else if(i==5){
				clozeOrder ="五";
				i++;
				view.addObject("clozeOrder",clozeOrder);
			}else if(i == 6){
				clozeOrder ="六";
				i++;
				view.addObject("clozeOrder",clozeOrder);
			}else {
				clozeOrder ="七";
				i++;
				view.addObject("clozeOrder",clozeOrder);
			}
		}
		for (QuestionRead qr : reads) {
			qrList = questionReadService.getReadChildByParentId(qr.getHomeworkDetails().getQuestionId());
		}
		if (reads != null && !reads.isEmpty()) {
			for (QuestionRead read : reads) {
				if (read.getHomeworkDetails().getQuestionType() == 4) {
					List<QuestionRead> readChilds = questionReadService.getReadChildByParentId(read.getReadId());
					read.setChildDetail(readChilds);
					if (read.getHomeworkDetails().getValue() != null) {
						readscore += read.getHomeworkDetails().getValue();
					}
				}
			}
		}
		for (QuestionFill sub : qfList) {
			if (sub.getPaperDetails().getValue() != null) {
				fill += sub.getPaperDetails().getValue();
			}
		}
		if (singles != null && !singles.isEmpty()) {
			for (QuestionSingleChoice single : singles) {
				if (single.getHomeworkDetails().getValue() != null) {
					singlescore += single.getHomeworkDetails().getValue();
				}
			}
		}
		for (QuestionCloze c:clozes) {
			if (c.getHomeworkDetails().getValue() != null) {
				cloze += c.getHomeworkDetails().getValue();
			}
		}
		if (mulits != null && !mulits.isEmpty()) {
			for (QuestionMulitChoice mulit : mulits) {
				if (mulit.getHomeworkDetails().getValue() != null) {
					mulitscore += mulit.getHomeworkDetails().getValue();
				}
			}
		}
		for (SubjectiveQuestion sub : subjectives) {
			if (sub.getPaperDetails().getValue() != null) {
				subjective += sub.getPaperDetails().getValue();
			}
		}
		if (judges != null && !judges.isEmpty()) {
			for (QuestionJudge judge : judges) {
				if (judge.getHomeworkDetails().getValue() != null) {
					judgescore += judge.getHomeworkDetails().getValue();
				}
			}
		}
		singleChoice = singles.size();
		Judge = judges.size();
		subCount = subjectives.size();
		fillCount = qfList.size();
		clozeCount = clozes.size();
		if (qrList != null) {
			Read = qrList.size();
		} else {
			Read = 0;
		}
		mulitChoice = mulits.size();
		if (!singles.isEmpty() && singles.get(0).getHomeworkDetails().getValue() != null) {
			danxuan = singles.get(0).getHomeworkDetails().getValue();
		}
		if (!mulits.isEmpty() && mulits.get(0).getHomeworkDetails().getValue() != null) {
			duoxuan = mulits.get(0).getHomeworkDetails().getValue();
		}
		if (!judges.isEmpty() && judges.get(0).getHomeworkDetails().getValue() != null) {
			pduan = judges.get(0).getHomeworkDetails().getValue();
		}
		if (!reads.isEmpty() && reads.get(0).getHomeworkDetails().getValue() != null) {
			yuedu = reads.get(0).getHomeworkDetails().getValue() / Read;
		}
		if (subjectives != null && !subjectives.isEmpty()) {
			question = subjectives.get(0).getHomeworkDetails().getValue();
		}
		if (qfList != null && !qfList.isEmpty()) {
			fillScore = qfList.get(0).getHomeworkDetails().getValue();
		}
		if (!clozes.isEmpty()) {
			clozeScore = clozes.get(0).getHomeworkDetails().getValue();
		}
		view.setViewName("homework/watchexam");
		view.addObject("paper", homework);
		view.addObject("singleList", singles);
		view.addObject("mulitList", mulits);
		view.addObject("judegeList", judges);
		view.addObject("readList", reads);
		view.addObject("singleChoice", singleChoice);
		view.addObject("Judge", Judge);
		view.addObject("Read", Read);
		view.addObject("danxuan", danxuan);
		view.addObject("duoxuan", duoxuan);
		view.addObject("pduan", pduan);
		view.addObject("cloze", cloze);
		view.addObject("yuedu", yuedu);
		view.addObject("clozeCount", clozeCount);
		view.addObject("clozes", clozes);
		view.addObject("qfList", qfList);
		view.addObject("question", question);
		view.addObject("subjectives", subjectives);
		view.addObject("subCount", subCount);
		view.addObject("mulitChoice", mulitChoice);
		view.addObject("fillCount", fillCount);
		view.addObject("fillScore", fillScore);
		view.addObject("singlescore", singlescore.intValue());
		view.addObject("mulitscore", mulitscore.intValue());
		view.addObject("judgescore", judgescore.intValue());
		view.addObject("readscore", readscore.intValue());
		view.addObject("subjective", subjective.intValue());
		view.addObject("clozeScore", clozeScore.intValue());
		view.addObject("fill", fill.intValue());
		view.addObject("page", pageNo);
		return view;
	}

	/**
	 * 保存修改信息
	 *
	 * @param request
	 * @param homework
	 * @return
	 */
	@RequestMapping(value = "/updateHomework", method = RequestMethod.POST)
	public void updateHomework(Homework homework, HttpServletResponse response, HttpServletRequest request) {
		Homework hk = homeworkService.getHomeworkById(homework.getHkId());
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (hk != null && hk.getUserTimes() != null && hk.getUserTimes() > 0) {
			output(response, JsonUtil.buildFalseJson("1", "该作业已被使用，无法修改!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				homework.setUpdateTime(new Date());
				int len = homeworkService.updateHomeworkById(homework);
				if (len > 0) {
					output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "修改失败!"));
				}
			} else {
				if (hk != null && !currTeacher.getTeacherId().equals(hk.getTeacherId())) {
					output(response, JsonUtil.buildFalseJson("3", "你不是试卷的创建者也不是管理员，不能对试卷进行修改!"));
				} else {
					homework.setUpdateTime(new Date());
					int len = homeworkService.updateHomeworkById(homework);
					if (len > 0) {
						output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
					} else {
						output(response, JsonUtil.buildFalseJson("1", "修改失败!"));
					}
				}
			}
		}
	}

	/**
	 * 查看详情
	 * 
	 * @param homeworkId
	 * @return
	 */
	@RequestMapping("viewDetails")
	public ModelAndView viewDetails(Integer homeworkId) {
		ModelAndView mv = new ModelAndView();
		Homework hk = homeworkService.getHomework(homeworkId);
		mv.addObject("hk", hk);
		mv.setViewName("homework/homeworkDetails");
		return mv;
	}

	/**
	 * 删除作业
	 *
	 * @param request
	 * @param homeworkId
	 * @return
	 */
	@RequestMapping(value = "/deleteHome", method = RequestMethod.POST)
	public void deleteHome(Integer homeworkId, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Homework homework = homeworkService.getHomeworkById(homeworkId);
		if (homework.getUserTimes() != null && homework.getUserTimes() > 0) {
			output(response, JsonUtil.buildFalseJson("3", "该作业已被使用，不能删除!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				homeworkService.deleteBomeworkById(homeworkId);
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("作业管理");
				log1.setOperationType("删除");
				log1.setOperationResult("成功");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent(
						"管理员【" + currTeacher.getTeacherName() + "】删除作业【" + homework.getHkName() + "成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} else {
				if (currTeacher.getTeacherId() != homework.getTeacherId()) {
					output(response, JsonUtil.buildFalseJson("2", "系统检测到您没有删除作业的权限!"));
				} else {
					homeworkService.deleteBomeworkById(homeworkId);
					Log log1 = new Log();
					log1.setStartTime(new Date());
					log1.setModular("作业管理");
					log1.setOperationType("删除");
					log1.setOperationResult("成功");
					log1.setOperationId(currTeacher.getTeacherId().toString());
					log1.setOperationName(currTeacher.getTeacherName());
					log1.setOperationContent(
							"管理员【" + currTeacher.getTeacherName() + "】删除作业【" + homework.getHkName() + "成功!");
					log1.setEndTime(new Date());
					logService.saveLog(log1);
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				}
			}
		}
	}

	/**
	 * 查看作业详情（已做完）
	 * 
	 * @param response
	 * @param studentId
	 * @param paperId
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/seeHomeworkDetails")
	public void seeHomeworkDetails(HttpServletResponse response, Integer studentId, Integer paperId, Integer pageNo,
			Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		List<SeePaper> spList = new ArrayList<SeePaper>();
		List<QuestionSingleChoice> list1 = questionSingleChoiceService.theHomeworkAnswerById(studentId, paperId, 1,
				(pageNo - 1) * pageSize, pageSize);
		for (QuestionSingleChoice qsc : list1) {
			SeePaper sp = new SeePaper();
			sp.setQuestionId(qsc.getChoiceId());
			sp.setAnswer(qsc.getAnswer());
			sp.setAnswerDesc(qsc.getAnswerDesc());
			sp.setOptionA(qsc.getOptionA());
			sp.setOptionB(qsc.getOptionB());
			sp.setOptionC(qsc.getOptionC());
			sp.setOptionD(qsc.getOptionD());
			sp.setQuestiontype(qsc.getQuestionsType().getTopicId());
			sp.setQuestionName(qsc.getQuestionsType().getTopicName());
			sp.setChoiceDesc(qsc.getChoiceDesc());
			sp.setIsTrue(qsc.getPaperAnswer().getIsTrue());
			sp.setWriteAnswer(qsc.getPaperAnswer().getWriteAnswer());
			spList.add(sp);
		}
		List<QuestionMulitChoice> list2 = questionMulitChoiceService.theHomeworkAnswerById(studentId, paperId, 2,
				(pageNo - 1) * pageSize, pageSize);
		for (QuestionMulitChoice qmc : list2) {
			SeePaper sp1 = new SeePaper();
			sp1.setQuestionId(qmc.getChoiceId());
			sp1.setOptionA1(qmc.getOptionA1());
			sp1.setOptionB1(qmc.getOptionB1());
			sp1.setOptionC1(qmc.getOptionC1());
			sp1.setOptionD1(qmc.getOptionD1());
			sp1.setQuestiontype(qmc.getQuestionsType().getTopicId());
			sp1.setQuestionName(qmc.getQuestionsType().getTopicName());
			sp1.setChoiceDesc1(qmc.getChoiceDesc1());
			sp1.setAnswer1(qmc.getAnswer1());
			sp1.setAnswerDesc1(qmc.getAnswerDesc1());
			sp1.setIsTrue(qmc.getPaperAnswer().getIsTrue());
			sp1.setWriteAnswer(qmc.getPaperAnswer().getWriteAnswer());
			spList.add(sp1);
		}
		List<QuestionJudge> list3 = questionJudgeService.theHomeworkAnswerById(studentId, paperId, 3,
				(pageNo - 1) * pageSize, pageSize);
		for (QuestionJudge qj : list3) {
			SeePaper sp2 = new SeePaper();
			sp2.setQuestionId(qj.getJudgeId());
			sp2.setQuestiontype(qj.getQuestionsType().getTopicId());
			sp2.setJudgeDesc(qj.getJudgeDesc());
			sp2.setAnswer2(qj.getAnswer2());
			sp2.setQuestionName(qj.getQuestionsType().getTopicName());
			sp2.setAnswerDesc2(qj.getAnswerDesc2());
			sp2.setIsTrue(qj.getPaperAnswer().getIsTrue());
			sp2.setWriteAnswer(qj.getPaperAnswer().getWriteAnswer());
			spList.add(sp2);
		}
		List<QuestionRead> list4 = questionReadService.theHomeworkAnswerById(studentId, paperId, 4,
				(pageNo - 1) * pageSize, pageSize);
		for (QuestionRead qr : list4) {
			SeePaper sp3 = new SeePaper();
			sp3.setQuestionId(qr.getReadId());
			sp3.setAnswer_read(qr.getAnswer_read());
			sp3.setOptionA_read(qr.getOptionA_read());
			sp3.setOptionB_read(qr.getOptionB_read());
			sp3.setOptionC_read(qr.getOptionC_read());
			sp3.setOptionD_read(qr.getOptionD_read());
			sp3.setReadDesc(qr.getReadDesc());
			List<QuestionRead> list5 = questionReadService.getReadChildByParentIdPaer(list4.get(0).getReadId());
			List<QuestionPaer> qrList = new ArrayList<QuestionPaer>();
			for (QuestionRead qt : list5) {
				QuestionPaer qrQuestionRead = new QuestionPaer();
				qrQuestionRead.setReadId(qt.getReadId());
				qrQuestionRead.setIsTrue(qt.getPaperAnswer().getIsTrue());
				qrQuestionRead.setWriteAnswer(qt.getPaperAnswer().getWriteAnswer());
				qrQuestionRead.setOptiontitle(qt.getOptiontitle());
				qrQuestionRead.setOptionA_read(qt.getOptionA_read());
				qrQuestionRead.setOptionB_read(qt.getOptionB_read());
				qrQuestionRead.setOptionC_read(qt.getOptionC_read());
				qrQuestionRead.setOptionD_read(qt.getOptionD_read());
				qrQuestionRead.setAnswer_read(qt.getAnswer_read());
				qrQuestionRead.setAnswer_read(qt.getAnswerDesc_read());
				qrList.add(qrQuestionRead);

			}
			sp3.setQuestionRead(qrList);
			sp3.setQuestiontype(qr.getQuestionsType().getTopicId());
			sp3.setQuestionName(qr.getQuestionsType().getTopicName());
			spList.add(sp3);
		}
		if (spList != null && !spList.isEmpty()) {
			output(response, JsonUtil.buildJson(spList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 共享作业
	 * 
	 * @param piId
	 * @param pInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/share")
	public void share(Integer hkId, Integer ispublic, HttpServletRequest request, HttpServletResponse response) {
		Homework homework = homeworkService.getHomework(hkId);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (homework != null && homework.getUserTimes() > 0 && homework.getIsPublic() != 0) {
			output(response, JsonUtil.buildFalseJson("2", "该作业已被使用了，暂时不能私有!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				homework.setIsPublic(ispublic);
				homework.setUpdateTime(new Date());
				int len = homeworkService.updateIspublic(homework);
				if (len > 0) {
					output(response, JsonUtil.buildFalseJson("0", "更新作业状态成功!"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "更新作业状态失败，请重试!"));
				}
			} else {
				if (homework != null && !currTeacher.getTeacherId().equals(homework.getTeacherId())) {
					output(response, JsonUtil.buildFalseJson("3", "你不是试卷的创建者也不是管理员，不能对试卷进行修改!"));
				} else {
					homework.setIsPublic(ispublic);
					homework.setUpdateTime(new Date());
					int len = homeworkService.updateIspublic(homework);
					if (len > 0) {
						output(response, JsonUtil.buildFalseJson("0", "更新作业状态成功!"));
					} else {
						output(response, JsonUtil.buildFalseJson("1", "更新作业状态失败，请重试!"));
					}
				}
			}
		}
	}
	
	/**
	 * 停用/启用作业
	 * @param hkId
	 * @param isStart
	 */
	 @RequestMapping("/changeexamstatus")
	 public void changeExamStatus(HttpServletRequest request,HttpServletResponse response,Integer hkId,Integer isStart){
		 
		 Homework homework = homeworkService.getHomework(hkId);
			HttpSession session = request.getSession();
			Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
				if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
					homework.setIsStart(isStart);
					homework.setUpdateTime(new Date());
					int len = homeworkService.updateIsstart(homework);
					if (len > 0) {
						output(response, JsonUtil.buildFalseJson("0", "更新作业状态成功!"));
					} else {
						output(response, JsonUtil.buildFalseJson("1", "更新作业状态失败，请重试!"));
					}
				} else {
					if (homework != null && !currTeacher.getTeacherId().equals(homework.getTeacherId())) {
						output(response, JsonUtil.buildFalseJson("3", "你不是试卷的创建者也不是管理员，不能对试卷进行修改!"));
					} else {
						homework.setIsStart(isStart);
						homework.setUpdateTime(new Date());
						int len = homeworkService.updateIsstart(homework);
						if (len > 0) {
							output(response, JsonUtil.buildFalseJson("0", "更新作业状态成功!"));
						} else {
							output(response, JsonUtil.buildFalseJson("1", "更新作业状态失败，请重试!"));
						}
					}
				}
			
		 
	 }

	/**
	 * 修改作业中，删除题目
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/deleteTimu")
	public void deleteTimu(HttpServletResponse response, HttpServletRequest request, Integer paperId) {
		HomeworkDetails hDetails = homeworkDetailsService.getHkIdById(paperId);
		Homework hk = homeworkService.getHomeworkById(hDetails.getPaperId());
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (hk != null && hk.getUserTimes() != null && hk.getUserTimes() > 0) {
			output(response, JsonUtil.buildFalseJson("1", "该题目已被使用，暂不能删除!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				homeworkDetailsService.deleteTimu(paperId);
				hk.setHkTotal(hk.getHkTotal() - 1);
				hk.setUpdateTime(new Date());
				homeworkService.updateTotal(hk);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} else {
				if (currTeacher.getTeacherId() != hk.getTeacherId()) {
					output(response, JsonUtil.buildFalseJson("2", "您没有删除作业的权限!"));
				} else {
					homeworkDetailsService.deleteTimu(paperId);
					hk.setHkTotal(hk.getHkTotal() - 1);
					hk.setUpdateTime(new Date());
					homeworkService.updateTotal(hk);
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				}
			}
		}
	}

	@RequestMapping("myhomeworkscore")
	public void myHomeworkScore(HttpServletResponse response, Integer studentId, Integer paperId) {
		HomeworkRecord record2 = homeworkRecordService.getByStuIdandHomeId(studentId, paperId);
		List<HomeworkRecord> lRecords = new ArrayList<>();
		lRecords.add(record2);
		System.out.println(JsonUtil.buildJson(lRecords));
		System.out.println("学生名字1：" + record2.getStudentList().getStudentName());
		Map<String, Object> map = new HashMap<>();
		map.put("paperId", paperId);
		map.put("studentId", studentId);
		List<MyMistakes> mistakes = myMistakesService.getRightByStatus(map);
		for (MyMistakes mistakes2 : mistakes) {
			if (mistakes2.getTopicType() == 1) {
				if (record2.getSingleScore() != null) {
					record2.setSingleScore(record2.getSingleScore() + mistakes2.getValue());
				} else {
					record2.setSingleScore(mistakes2.getValue().doubleValue());
				}
			} else if (mistakes2.getTopicType() == 2) {
				if (record2.getMulitScore() != null) {
					record2.setMulitScore(record2.getMulitScore() + mistakes2.getValue());
				} else {
					record2.setMulitScore(mistakes2.getValue().doubleValue());
				}
			} else if (mistakes2.getTopicType() == 3) {
				if (record2.getJudgeScore() != null) {
					record2.setJudgeScore(record2.getJudgeScore() + mistakes2.getValue());
				} else {
					record2.setJudgeScore(mistakes2.getValue().doubleValue());
				}
			} else if (mistakes2.getTopicType() == 4) {
				if (record2.getReadScore() != null) {
					record2.setReadScore(record2.getReadScore() + mistakes2.getValue());
				} else {
					record2.setReadScore(mistakes2.getValue().doubleValue());
				}
			}
		}
		List<HomeworkRecord> list = new ArrayList<>();
		list.add(record2);
		if (!list.isEmpty() && list != null) {
			output(response, JsonUtil.buildJson(list));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
	}
	
	//修改作业题目页面
	@RequestMapping("/modityQuestion")
	public ModelAndView modityQuestion(int type,Integer questionId,ModelAndView mav,Integer paperId){
		if (type == 1) {
			QuestionSingleChoice single = questionSingleChoiceService.getQuestionById(questionId);
			mav.addObject("single", single);
		} else if (type == 2) {
		QuestionMulitChoice mulit = questionMulitChoiceService.getMulitChoiceById(questionId);
		mav.addObject("mulit", mulit);
		} else if (type == 3) {
			QuestionJudge judge = questionJudgeService.getJudgeById(questionId);
			mav.addObject("judge", judge);
		} else if (type == 4) {

		} else if (type == 5) {
			SubjectiveQuestion sQuestion = questionService.getSubjectiveQuestionById(questionId);
			mav.addObject("sQuestion", sQuestion);
		} else if (type == 6) {
			QuestionFill fill = fillService.getQuestionFillById(questionId);
			mav.addObject("fill", fill);
		} else if (type == 7) {
			QuestionCloze cloze = clozeService.getQuestionClozeById(questionId);
			mav.addObject("cloze", cloze);
			mav.setViewName("questionCloze/updateCloze");
		}
		if (type !=7 && type !=4) {
			mav.setViewName("homework/modityQuestion");
		}
		mav.addObject("type", type);
		mav.addObject("paperId", paperId);
		mav.addObject("questionId", questionId);
		return mav;
	}
}
