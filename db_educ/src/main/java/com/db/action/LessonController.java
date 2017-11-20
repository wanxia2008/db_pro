package com.db.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.db.entity.ClassNo;
import com.db.entity.ClassRecord;
import com.db.entity.Course;
import com.db.entity.Grade;
import com.db.entity.Homework;
import com.db.entity.HomeworkRecord;
import com.db.entity.LectureNotes;
import com.db.entity.Log;
import com.db.entity.PaperAnswer;
import com.db.entity.PaperInfo;
import com.db.entity.QuestionCloze;
import com.db.entity.QuestionFill;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionPaer;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.QuestionTag;
import com.db.entity.SeePaper;
import com.db.entity.Student;
import com.db.entity.StudentCareer;
import com.db.entity.Subject;
import com.db.entity.SubjectiveQuestion;
import com.db.entity.Teacher;
import com.db.entity.TestRecord;
import com.db.service.ClassNoService;
import com.db.service.ClassRecordService;
import com.db.service.CourseService;
import com.db.service.GradeService;
import com.db.service.HomeworkRecordService;
import com.db.service.HomeworkService;
import com.db.service.LectureNotesService;
import com.db.service.LogService;
import com.db.service.PaperAnswerService;
import com.db.service.PaperDetailsService;
import com.db.service.PaperInfoService;
import com.db.service.PaperRecordService;
import com.db.service.QuestionClozeService;
import com.db.service.QuestionFillService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.QuestionTagService;
import com.db.service.StudentCareerService;
import com.db.service.StudentService;
import com.db.service.SubjectService;
import com.db.service.SubjectiveQuestionService;
import com.db.service.TeacherService;
import com.db.service.TestRecordService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;

/**
 * 上课管理的讲义管理
 * 
 * @author sun
 *
 */
@Controller
@RequestMapping("/lesson")
public class LessonController extends BaseUtil {
	@Resource
	private LectureNotesService lecturenoteservice;
	@Resource
	private CourseService courseService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private LectureNotesService lectureNotesService;
	@Resource
	private PaperInfoService paperInfoService;
	@Resource
	private PaperRecordService paperRecordService;
	@Resource
	private ClassNoService classNoService;
	@Resource
	private TestRecordService testRecordService;
	@Resource
	private StudentService studentService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private GradeService gradeService;
	@Resource
	private PaperDetailsService paDetailsService;
	@Resource
	private PaperAnswerService answerService;
	@Resource
	private StudentCareerService studentCareerService;
	@Resource
	private LogService logService;
	@Resource
	private QuestionFillService fillService;
	@Resource
	private QuestionSingleChoiceService singleChoiceService;
	@Resource
	private QuestionMulitChoiceService mulitChoiceService;
	@Resource
	private QuestionJudgeService judgeService;
	@Resource
	private QuestionReadService readService;
	@Resource
	private SubjectiveQuestionService questionService;
	@Resource
	private QuestionTagService tagService;
	@Resource
	private ClassRecordService recordService;
	@Resource
	private HomeworkRecordService homeworkRecordService;
	@Resource
	private HomeworkService homeworkService;
	@Resource
	private ClassRecordService classRecordService;
	@Resource
	private QuestionClozeService clozeService;

	private HttpSession session;
	private Logger log = Logger.getLogger(LessonController.class);

	/**
	 * 上课管理(讲义、试卷、作业)
	 * 
	 * @param request
	 * @param ln
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/lecturenotesmanage")
	public ModelAndView Lecturenotesmanage(HttpServletRequest request, Integer classId, LectureNotes ln, Integer pageNo,
			Integer pageSize, String date) throws Exception {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("courseDate", date);
		param.put("pageNo", (pageNo - 1) * pageSize);
		param.put("classId", classId);
		param.put("pageSize", pageSize);
		int rowsCount = 0;
		List<ClassNo> cnList = null;
		List<Course> list = null;
		if (currTeacher.getTeacherType().equals("1")) {
			cnList = classNoService.findClassByIdList();
			list = courseService.getCourseByGradeandSubject(param);
			rowsCount = courseService.getCount(param);
		} else {
			cnList = classNoService.findIsExistenceCampus(currTeacher.getCampus());
			if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
				if (currTeacher.getClassId() != null) {

					String classIds = currTeacher.getClassId();
					String[]  arrid = classIds.split(",");
					List<Integer> listid = new ArrayList<Integer>();
					for (int i=0;i<arrid.length;i++){
						listid.add(Integer.valueOf(arrid[i]) );
					}
					param.put("classId", listid);
					param.put("schoolId", currTeacher.getCampus());
					list = courseService.getCourseByGradeandSchoolIds(param);
					
//					System.out.println("getCourseByGradeandSchoolId :"+list.size());
					rowsCount = courseService.getSchoolIdCountbyids(param);
				}
			} else {
				param.put("schoolId", currTeacher.getCampus());
				list = courseService.getCourseByGradeandSchoolId(param);
				rowsCount = courseService.getSchoolIdCount(param);
			}
		}
		// rowsCount = lectureNotesService.getCount(ln);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<LectureNotes> lnList = lectureNotesService.lectureNotesList(currTeacher.getTeacherId(), ln,
				(pageNo - 1) * pageSize, pageSize);
		log.info("获取课程表成功!" + list);
		// 筛选出已排课的日期
		List<Course> date_list = courseService.getDateByClass(null, null);
		if (list != null) {
			view.addObject("courseList", list);
		} else {
			view.addObject("courseList", null);
		}
		if (classId != null) {
			view.addObject("classId", classId);
		}
		if (date != null) {
			view.addObject("date", date);
		}
		view.addObject("dateList", date_list);
		view.addObject("lnList", lnList);
		view.addObject("page", pageNo);
		view.addObject("pageSize", pageSize);
		view.addObject("totalPages", totalPages);
		view.addObject("classList", cnList);
		view.setViewName("lessonmanage/notemanage");
		return view;
	}

	/**
	 * 获取讲义列表(点击课堂讲义)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getpublicnote")
	public void getPublicNote(Integer pageNo, Integer pageSize, Integer grade, Integer subject,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<LectureNotes> lnList = null;
		int totalcount = 0;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			totalcount = lectureNotesService.seeCountIspublicMunber(null, null, subject, null, grade);
			lnList = lectureNotesService.seelectureNotesIsPublicByIdList1((pageNo - 1) * pageSize, pageSize, null, null,
					subject, null, grade);
		} else {
			lnList = lectureNotesService.getPublicNote((pageNo - 1) * pageSize, pageSize, grade,
					currTeacher.getSubject(), currTeacher.getTeacherId());
			totalcount = (int) lecturenoteservice.getPublicNoteCount(grade, currTeacher.getSubject(),
					currTeacher.getTeacherId());// 获取列表总数
		}
		PageUtil pageUtil = new PageUtil(totalcount, pageNo, pageSize);
		System.out.println("总页数：" + pageUtil.getTotalPageCount());
		if (!lnList.isEmpty() && lnList != null) {
			output(response, JsonUtil.buildJsonByTotalCount(lnList, pageUtil.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "当前条件下没有更多的讲义了。"));
		}
	}

	/**
	 * 考试管理列表(普通考试列表)
	 * 
	 * @param request
	 * @param pInfo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/exammanage")
	public ModelAndView Exammanage(HttpServletRequest request, String date, Integer classId, Integer pageNo,
			Integer pageSize) {
		ModelAndView view = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<ClassNo> cnList = null;
		List<Course> list = null;
		int rowsCount = 0;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolId", currTeacher.getCampus());
		param.put("pageNo", (pageNo - 1) * pageSize);
		param.put("pageSize", pageSize);
		if (currTeacher.getTeacherType().equals("1")) {
			cnList = classNoService.findClassByIdList();
			list = courseService.getCourseByGradeandSubject(param);
			rowsCount = courseService.getCount(param);
		} else {
			cnList = classNoService.findIsExistenceCampus(currTeacher.getCampus());
			list = courseService.getCourseBySchoolId(param);
			rowsCount = courseService.getSchoolIdCount(param);
		}
		if (date != null) {
			param.put("courseDate", date);
		}
		log.info("获取课程表成功!" + list);
		// 筛选出已排课的日期
		List<Course> date_list = courseService.getDateByClass(null, null);
		view.setViewName("lessonmanage/exammanage");
		if (!list.isEmpty() && list != null) {
			view.addObject("courseList", list);
		} else {
			view.addObject("courseList", null);
		}

		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		if (classId != null) {
			view.addObject("classId", classId);
		}
		if (date != null) {
			view.addObject("date", date);
		}
		view.addObject("dateList", date_list);
		view.addObject("page", pageNo);
		view.addObject("pageSize", pageSize);
		view.addObject("totalPages", totalPages);
		view.addObject("classList", cnList);
		return view;
	}

	/**
	 * 获取试卷列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getpublicexam")
	public void getPublicExam(Integer pageNo, Integer pageSize, Integer subject, Integer grade,
			HttpServletRequest request, HttpServletResponse response, Integer type, Integer isTest) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		PaperInfo pInfo = new PaperInfo();
		int totalcount = 0;
		List<PaperInfo> paperInfos = null;
		if (teacher.getTeacherType().equals("5") && teacher.getTeacherType().equals("6")) {
			totalcount = (int) paperInfoService.getPublicExamCount(teacher.getTeacherId(), pInfo, subject, grade, type,
					isTest);
			paperInfos = paperInfoService.paperInfoList1(teacher.getTeacherId(), pInfo, (pageNo - 1) * pageSize,
					pageSize, subject, grade, type, isTest);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", (pageNo - 1) * pageSize);
			map.put("pageSize", pageSize);
			map.put("subject", subject);
			map.put("gradeId", grade);
			map.put("isTask", type);
			map.put("isTest", isTest);
			totalcount = paperInfoService.findAdminCount(map);
			paperInfos = paperInfoService.findAdminList(map);
		}
		PageUtil pageUtil = new PageUtil(totalcount, pageNo, pageSize);
		System.out.println("总页数：" + pageUtil.getTotalPageCount());
		if (!paperInfos.isEmpty() && paperInfos != null) {
			output(response, JsonUtil.buildJsonByTotalCount(paperInfos, pageUtil.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有更多的试卷了。"));
		}
	}

	/**
	 * 入学考试完成试卷列表
	 * 
	 * @param subject
	 * @param grade
	 * @param classId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/paperEndList")
	public ModelAndView paperEnd(Integer subject, Integer grade, Integer classId, Integer pageNo, Integer pageSize,
			Integer gradeId) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = testRecordService.getCount(subject, classId, gradeId);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<TestRecord> trList = testRecordService.getPaperEndList((pageNo - 1) * pageSize, pageSize, subject, classId,
				gradeId);
		List<Subject> subList = subjectService.getSubject();
		List<Grade> gradeList = gradeService.getGrade();
		List<ClassNo> classNosList = classNoService.getClassesNo();
		ModelAndView mv = new ModelAndView();
		mv.addObject("classNosList", classNosList);
		mv.addObject("subList", subList);
		mv.addObject("gradeList", gradeList);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("trList", trList);
		mv.setViewName("lessonmanage/paperEndList");
		return mv;
	}

	/**
	 * 学生情况
	 * 
	 * @param request
	 * @param modelView
	 * @param student
	 * @return
	 */
	@RequestMapping("/studentmanage")
	public ModelAndView Studentmanage(Integer studentId, Integer classId, Integer subject, Integer pageSize,
			Integer pageNo, Integer grade,HttpServletRequest request) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		ModelAndView modelView = new ModelAndView();
		if (classId == null) {
			classId = 1;
		}
		// if (grade == null) {
		// grade = 6;
		// }
		int roesCount = testRecordService.getCountStudentId(studentId);
		int totalPages = 0;

		if (roesCount % pageSize == 0) {
			totalPages = roesCount / pageSize;
		} else {
			totalPages = (roesCount / pageSize) + 1;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<StudentCareer> classNosList = studentCareerService.getClassListStudent(classId, subject, grade);
		if (studentId != null) {
			Student stuId = studentService.getStudentById(studentId);
			List<TestRecord> trList = testRecordService.getRecordSituation(studentId, (pageNo - 1) * pageSize,
					pageSize);
			modelView.addObject("trList", trList);
			modelView.addObject("stuId", stuId);
		} else {
			modelView.addObject("stuId", null);
		}
		List<ClassNo> classList = null;
		List<Grade> gList = null;
		List<Subject> subjects = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			classList = classNoService.getClassesNo();
			gList = gradeService.getGrade();
			subjects = subjectService.getSubject();
		} else {
			String [] my = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(my);
			gList = gradeService.getGradeByIds(gradeList);
			subjects = subjectService.getSubjectById(currTeacher.getSubject());
			String [] classNo = currTeacher.getClassId().split(",");
			List cList = Arrays.asList(classNo);
			classList = classNoService.getClassList(cList);
			
		}
		modelView.setViewName("lessonmanage/studentmanage");
		modelView.addObject("classNosList", classNosList);
		modelView.addObject("classList", classList);
		modelView.addObject("subjects", subjects);
		modelView.addObject("gList", gList);
		modelView.addObject("subject", subject);
		modelView.addObject("grade", grade);
		modelView.addObject("classId", classId);
		modelView.addObject("page", pageNo);
		modelView.addObject("pageSize", pageSize);
		modelView.addObject("totalPages", totalPages);
		return modelView;
	}

	/**
	 * 查看学生成绩图表 試卷分析
	 * 
	 * @param scoreId
	 * @param classId
	 * @param grade
	 * @param subject
	 * @param studentId
	 * @param paperId
	 * @return
	 */
	@RequestMapping("/studentScore")
	public ModelAndView studentScore(Integer scoreId, Integer classId, Integer grade, Integer subject,
			Integer studentId, Integer paperId) {
		ModelAndView mv = new ModelAndView();
		String tagName = "";
		Double danxuan = 0.0, duoxuan = 0.0, pduan = 0.0, yuedu = 0.0, fill = 0.0;
		Double danxuan1 = 0.0, duoxuan1 = 0.0, pduan1 = 0.0, yuedu1 = 0.0, fill1 = 0.0;
		int knowledgeTotal = 0, isTrueTotal = 0, errorTotal = 0;
		// 拿到所有知识点(标签)
		List<QuestionTag> qtList = new ArrayList<QuestionTag>();
		// 拿到正确知识点
		List<QuestionTag> trueList = new ArrayList<QuestionTag>();
		// 拿到错误知识点
		List<QuestionTag> errorList = new ArrayList<QuestionTag>();
		String tagId11 = "";
		String tag = "";// 做对
		String errorTage = "";
		TestRecord tr = testRecordService.findRecordScore(scoreId);
		PaperInfo pInfo = paperInfoService.getPaperInfoById(paperId);
		List<PaperAnswer> paList = answerService.findPaperAnswerBy(studentId, paperId);
		List<TestRecord> recordList = testRecordService.findObtainScoreRankList(paperId, null);// 获取单科试卷成绩排名
		TestRecord record = null;
		for (TestRecord tr1 : recordList) {
			if (tr1.getStudentId().equals(studentId) && tr1.getPaperId().equals(paperId)) {
				record = testRecordService.isWriteMessage(studentId, paperId, null);
				record.setScoreRank(tr1.getScoreRank());
			}
		}
		mv.addObject("record", record);
		for (PaperAnswer pa : paList) {
			if (pa.getQuestionType() == 1) {// 单选
				QuestionSingleChoice choice1 = singleChoiceService.getSingleChoiceById(pa.getQuestionId());
				tagId11 = choice1.getTag();
				knowledgeTotal++;
				if (pa.getIsTrue() == 0) {
					danxuan += pa.getValue();
					errorTage += choice1.getTag();
				} else {
					danxuan1 += pa.getValue();
					isTrueTotal++;
					tag += choice1.getTag();
				}
			}
			if (pa.getQuestionType() == 2) {// 多选
				QuestionMulitChoice mChoice = mulitChoiceService.getMulitChoiceById(pa.getQuestionId());
				tagId11 += mChoice.getTag1();
				knowledgeTotal++;
				if (pa.getIsTrue() == 0) {
					duoxuan += pa.getValue();
					errorTage += mChoice.getTag1();
				} else {
					duoxuan1 += pa.getValue();
					isTrueTotal++;
					tag += mChoice.getTag1();
				}
			}
			if (pa.getQuestionType() == 3) {// 判断
				QuestionJudge judge = judgeService.getJudgeById(pa.getQuestionId());
				tagId11 += judge.getTag2();
				knowledgeTotal++;
				if (pa.getIsTrue() == 0) {
					pduan += pa.getValue();
					errorTage += judge.getTag2();
				} else {
					pduan1 += pa.getValue();
					isTrueTotal++;
					tag += judge.getTag2();
				}
			}
			if (pa.getQuestionType() == 4) {// 阅读理解
				QuestionRead read = readService.getReadById(pa.getQuestionId());
				QuestionRead read1 = readService.getReadById(read.getParentId());
				tagId11 += read1.getTag_read();
				knowledgeTotal++;
				if (pa.getIsTrue() == 0) {
					yuedu += pa.getValue();
					errorTage += read1.getTag_read();
				} else {
					yuedu1 += pa.getValue();
					isTrueTotal++;
					tag += read1.getTag_read();
				}
			}
			if (pa.getQuestionType() == 6) {// 填空题
				QuestionFill fill2 = fillService.getQuestionFillById(pa.getQuestionId());
				tagId11 += fill2.getTagId();
				knowledgeTotal++;
				if (pa.getIsTrue() == 0) {
					fill += pa.getValue();
					errorTage += fill2.getTagId();
				} else {
					fill1 += pa.getValue();
					isTrueTotal++;
					tag += fill2.getTagId();
				}
			}
		}
		// 总标签
		String[] tag1 = tagId11.split(",");
		// 过滤数组中重复的元素
		TreeSet<String> tagCount = new TreeSet<String>();
		for (int i = 0; i < tag1.length; i++) {
			tagCount.add(tag1[i]);
		}
		String[] Counts2 = new String[tagCount.size()];
		for (int i = 0; i < Counts2.length; i++) {
			Counts2[i] = tagCount.pollFirst();// 从TreeSet中取出元素重新赋给数组
		}
		List<String> idsList = Arrays.asList(Counts2);
		List<QuestionTag> tagList = tagService.getTagByIds(idsList);
		for (QuestionTag qt : tagList) {
			QuestionTag qTag = new QuestionTag();
			qTag.setTagName(qt.getTagName());
			tagName += "\'" + qt.getTagName() + "\',";
			qtList.add(qTag);
		}
		// 做对的标签
		String[] tag11 = tag.split(",");
		// 过滤数组中重复的元素
		TreeSet<String> trTag = new TreeSet<String>();
		for (int i = 0; i < tag11.length; i++) {
			trTag.add(tag11[i]);
		}
		String[] s2 = new String[trTag.size()];
		for (int i = 0; i < s2.length; i++) {
			s2[i] = trTag.pollFirst();// 从TreeSet中取出元素重新赋给数组
		}
		List<String> itList = Arrays.asList(s2);
		List<QuestionTag> agList = tagService.getTagByIds(itList);
		for (QuestionTag qt : agList) {
			QuestionTag qTag = new QuestionTag();
			qTag.setTagName(qt.getTagName());
			trueList.add(qTag);
		}
		// 做错的标签
		String[] flaseTage = errorTage.split(",");
		// 过滤数组中重复的元素
		TreeSet<String> tagErr = new TreeSet<String>();
		for (int i = 0; i < flaseTage.length; i++) {
			tagErr.add(flaseTage[i]);
		}
		String[] s3 = new String[tagErr.size()];
		for (int i = 0; i < s3.length; i++) {
			s3[i] = tagErr.pollFirst();// 从TreeSet中取出元素重新赋给数组
		}
		List<String> falseList = Arrays.asList(s3);
		List<QuestionTag> aggList = tagService.getTagByIds(falseList);
		for (QuestionTag qt : aggList) {
			QuestionTag qTag = new QuestionTag();
			qTag.setTagName(qt.getTagName());
			errorList.add(qTag);
		}
		// 我的得分
		String scores = String.valueOf(danxuan1) + "," + String.valueOf(duoxuan1) + "," + String.valueOf(pduan1) + ","
				+ String.valueOf(yuedu1) + "," + String.valueOf(fill1);
		// 模块总分
		String scores1 = String.valueOf(danxuan + danxuan1) + "," + String.valueOf(duoxuan + duoxuan1) + ","
				+ String.valueOf(pduan + pduan1) + "," + String.valueOf(yuedu + yuedu1) + ","
				+ String.valueOf(fill + fill1);
		mv.addObject("tagName", tagName);
		mv.addObject("scores", scores);
		mv.addObject("scores1", scores1);
		mv.addObject("danxuan1", danxuan1);
		mv.addObject("duoxuan1", duoxuan1);
		mv.addObject("pduan1", pduan1);
		mv.addObject("yuedu1", yuedu1);
		mv.addObject("fill1", fill1);
		mv.addObject("tr", tr);
		mv.addObject("grade", grade);
		mv.addObject("subject", subject);
		mv.addObject("classId", classId);
		mv.addObject("choice", qtList);
		mv.addObject("pInfo", pInfo);
		mv.addObject("trueList", trueList);
		mv.addObject("errorList", errorList);
		mv.addObject("knowledgeTotal", knowledgeTotal);
		mv.addObject("isTrueTotal", isTrueTotal);
		mv.addObject("errorTotal", errorTotal = knowledgeTotal - isTrueTotal);
		mv.setViewName("lessonmanage/scoreChart");
		return mv;
	}

	/**
	 * 查看学生学籍
	 * 
	 * @param studentId
	 * @return
	 */
	@RequestMapping("/studentNameDateils")
	public ModelAndView studentNameDateils(Integer studentId, Integer classId, Integer grade, Integer subject) {
		ModelAndView mv = new ModelAndView();
		// TestRecord tr = testRecordService.studentNameDateils(studentId);
		// Student student = studentService.findstudentDateils(studentId);
		Student stu = studentService.getStudentById(studentId);
		List<Student> student = studentService.studentNameDateils(studentId);
		mv.setViewName("lessonmanage/studentDateils");
		mv.addObject("student", student);
		mv.addObject("subject", subject);
		mv.addObject("grade", grade);
		mv.addObject("classId", classId);
		mv.addObject("stu", stu);
		return mv;
	}

	/**
	 * 添加讲义
	 * 
	 * @param course
	 * @param response
	 */
	@RequestMapping("/updateLectureNote")
	public void updateLectureNote(Course course, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Course course2 = courseService.findNontClassId(course.getClassId(), course.getLecturenoteId(), null, null);
		LectureNotes lectureNotes = lectureNotesService.getLectureNotes(course.getLecturenoteId());
		if (course2 != null) {
			output(response, JsonUtil.buildFalseJson("1", "系统检测到此讲义改班级已添加"));
		} else {
			course.setUpdateTime(new Date());
			int row = courseService.updateCoursebynote(course);

			if (row > 0) {
				log.info("添加讲义成功！");
				// 添加讲义之后，讲义使用总数加一
				if (course.getLecturenoteId() != null) {
					lectureNotes.setUsedCount(lectureNotes.getUsedCount() + 1);
					lectureNotesService.updateUsedCount(lectureNotes);
				}
				output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("讲义管理");
				log1.setOperationType("添加");
				log1.setOperationResult("成功");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent(
						"管理员【" + currTeacher.getTeacherName() + "】添加讲义【" + lectureNotes.getHandoutTitle() + "】成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
			} else {
				log.info("添加讲义失败!");
				output(response, JsonUtil.buildFalseJson("1", "修改失败!"));
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("讲义管理");
				log1.setOperationType("添加");
				log1.setOperationResult("失败");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent(
						"管理员【" + currTeacher.getTeacherName() + "】添加讲义【" + lectureNotes.getHandoutTitle() + "】失败!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
			}
		}
	}

	/**
	 * 添加考试作业(课后作业)
	 * 
	 * @param response
	 * @param homeworkId
	 * @param courseId
	 * @param type
	 */
	@RequestMapping("/addHomeworkCourse")
	public void addHomeworkCourse(HttpServletResponse response, Course course, Integer type,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Course course2 = courseService.findNontClassId(course.getClassId(), null, course.getHomeworkId(), null);
		List<HomeworkRecord> hList = homeworkRecordService.findClassHomeworkAnalyList(course.getClassId(),
				course.getHomeworkId());
		if (course2 != null) {
			output(response, JsonUtil.buildFalseJson("2", "系统检测到您已添加过该作业，请勿重新添加!"));
		} else if (hList.size() > 0) {
			output(response, JsonUtil.buildFalseJson("2", "系统检测到您已添加过该作业，请勿重新添加!"));
		} else {
			ClassNo classNo = classNoService.getClassById(course.getClassId());
			if (currTeacher.getTeacherType().equals("1")) {
				course.setUpdateTime(new Date());
				int row = courseService.updateCoursebyexam(course);
				output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
			} else {
				if (!currTeacher.getCampus().equals(classNo.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("1", "系统检测到您所在的校区与此班级不一致!"));
				} else {
					course.setUpdateTime(new Date());
					int row = courseService.updateCoursebyexam(course);
					output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
				}
			}
		}
	}
	
	// 启动作业
	@RequestMapping("/startUpHomework")
	public void startUpHomework(HttpServletResponse response, Course course) {
		Course course2 = courseService.getCourseById(course.getCourseId());
		if (course2 == null) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		} else {
			ClassRecord cr = recordService.getPaperById(course2.getClassId(), null, null, course2.getHomeworkId());
			Homework homework = homeworkService.getHomeworkById(course2.getHomeworkId());
			ClassNo classNo = classNoService.getClassById(course2.getClassId());
			List<StudentCareer> students = studentCareerService.getStudentByClassNo(course2.getClassId(), 2);
			if (students.size() > 0) {
				for (int i = 0; i < students.size(); i++) {
					int studentId1 = students.get(i).getStudentId();
					HomeworkRecord record = homeworkRecordService.getByStuIdandHomeId(studentId1, course2.getHomeworkId());
					if (record == null) {
						record = new HomeworkRecord();
						record.setStudentId(studentId1);
						record.setHomeworkId(course2.getHomeworkId());
						record.setGradeId(classNo.getGrade());
						record.setSubject(classNo.getSubject());
						record.setClassId(course2.getClassId());
						record.setStatus(1);// 启动该作业
						record.setCreateTime(new Date());
						homeworkRecordService.addHomeworkrecord(record);// 添加到作业记录表，返回主键
					}
				}
			}
			if (cr == null) {
				cr = new ClassRecord();
				cr.setCreateTime(new Date());
				cr.setClassId(classNo.getClassId());
			    cr.setHomeworkId(course2.getHomeworkId());
				cr.setStatus(1);
				recordService.addClassRecord(cr);
			}
			if (homework != null) {
				// 添加作业之后把作业使用数加一
				homework.setUpdateTime(new Date());
				homework.setUserTimes(homework.getUserTimes() + 1);
				homeworkService.updateUserTimes(homework);
			}
			course.setUpdateTime(new Date());
			courseService.updateCoursebyexam(course);
			output(response, JsonUtil.buildFalseJson("0", "启动成功!"));
		}
	}
	
	
	//结束作业
	@RequestMapping("/endHomework")
	public void endHomework(HttpServletResponse response,Course course){
		Course course2 = courseService.getCourseById(course.getCourseId());
		if (course2 == null) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		} else {
			List<HomeworkRecord> hList = homeworkRecordService.findClassHomeworkAnalyList(course2.getClassId(),course2.getHomeworkId());
			ClassRecord record = classRecordService.getPaperById(course2.getClassId(), null, null, course2.getHomeworkId());
			record.setUpdateTime(new Date());
			record.setStatus(2);//考试结束
			classRecordService.updateStatus(record);
			if (hList != null && !hList.isEmpty()) {
				for (HomeworkRecord test : hList) {
					HomeworkRecord tr = homeworkRecordService.getHomeworkRecordById(test.getId());
					if (tr != null) {
						tr.setUpdateTime(new Date());
						tr.setStatus(3);//考试结束
						homeworkRecordService.updateHomeworkRecord(tr);
					}
				}
			}
			course.setUpdateTime(new Date());
			courseService.updateCoursebyexam(course);
			output(response, JsonUtil.buildFalseJson("0", "结束成功!"));
		}
	}

	/**
	 * 添加考试，暂不添加到考试记录表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateCourse")
	public void updateCourse(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		int courseId = Integer.valueOf(request.getParameter("courseId"));
		int paperId = Integer.valueOf(request.getParameter("paperId"));
		int classId = Integer.valueOf(request.getParameter("classId"));
		int subject = Integer.valueOf(request.getParameter("subject"));
		ClassNo classNo = classNoService.getClassById(classId);
		PaperInfo paperInfo = paperInfoService.getPaperInfoById(paperId);
		Course course = courseService.getCourseById(courseId);
		List<TestRecord> trList = testRecordService.findClassPaperInfoAnalysisList(paperId, classId);
		if (currTeacher.getTeacherType().equals("5") && currTeacher.getClassId() == null) {
			output(response, JsonUtil.buildFalseJson("2", "系统检测到您还没有分配所教班级!"));
		} else if (paperInfo.getSubject() != subject) {
			output(response, JsonUtil.buildFalseJson("1", "试卷对应的科目不符合，请仔细查看后再添加!"));
		} else if (classNo != null && paperInfo.getGrade() != classNo.getGrade()) {
			output(response, JsonUtil.buildFalseJson("3", "试卷对应的年级不符合，请仔细查看后再添加!"));
		} else if (trList.size() > 0) {
			output(response, JsonUtil.buildFalseJson("4", "系统检测到改班级已添加过该试卷!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				course.setUpdateTime(new Date());
				course.setExamId(paperId);
				courseService.updateCoursebyexam(course);
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("试卷管理");
				log1.setOperationType("添加");
				log1.setOperationResult("成功");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent(
						"管理员【" + currTeacher.getTeacherName() + "】添加试卷【" + paperInfo.getPiName() + "】成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
				output(response, JsonUtil.buildFalseJson("0", "添加考卷成功!"));
			} else {
				if (!currTeacher.getCampus().equals(classNo.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("4", "系统检测到您的校区与该班级不一致!"));
				} else {
					course.setUpdateTime(new Date());
					course.setExamId(paperId);
					courseService.updateCoursebyexam(course);
					Log log1 = new Log();
					log1.setStartTime(new Date());
					log1.setModular("试卷管理");
					log1.setOperationType("添加");
					log1.setOperationResult("成功");
					log1.setOperationId(currTeacher.getTeacherId().toString());
					log1.setOperationName(currTeacher.getTeacherName());
					log1.setOperationContent(
							"【" + currTeacher.getTeacherName() + "】添加试卷【" + paperInfo.getPiName() + "】成功!");
					log1.setEndTime(new Date());
					logService.saveLog(log1);
					output(response, JsonUtil.buildFalseJson("0", "添加考卷成功!"));
				}
			}
		}
	}

	// 启动试卷
	@RequestMapping("/startUpPaper")
	public void startUp(HttpServletResponse response, Course course) {
		Course course2 = courseService.getCourseById(course.getCourseId());
		if (course2 != null) {
			ClassRecord record = classRecordService.getPaperById(course2.getClassId(), course2.getExamId(), null, null);
			List<StudentCareer> students = studentCareerService.getStudentByClassNo(course2.getClassId(), 2);// 查找已分班并且班级编号为classid的学生
			PaperInfo paperInfo = paperInfoService.getPaperInfoById(course2.getExamId());
			ClassNo classNo = classNoService.getClassById(course2.getClassId());
			if (record == null) {
				record = new ClassRecord();
				record.setCreateTime(new Date());
				record.setClassId(course2.getClassId());
				record.setPaperId(course2.getExamId());
				record.setStatus(1);
				classRecordService.addClassRecord(record);
			}
			for (StudentCareer career : students) {
				TestRecord record2 = testRecordService.getTestRecordByStuandPaper(career.getStudentId(),
						course2.getExamId());
				if (record2 == null) {
					record2 = new TestRecord();
					record2.setCreateTime(new Date());
					record2.setClassId(course2.getClassId());
					record2.setGradeId(classNo.getGrade());
					record2.setPaperType(1);
					record2.setChoiceId(classNo.getSchoolArea());
					record2.setStatus(1);
					record2.setSubjectId(course2.getSubjectId());
					record2.setStudentId(career.getStudentId());
					record2.setPaperId(course2.getExamId());
					testRecordService.addTestrecord(record2);
				}
			}
			// 使用试卷之后，试卷使用数依次加一
			if (paperInfo != null) {
				paperInfo.setUpdateTime(new Date());
				paperInfo.setUsedTimes(paperInfo.getUsedTimes() + 1);
				paperInfoService.updateUsedTimes(paperInfo);
			}
			course.setUpdateTime(new Date());
			courseService.updateCoursebyexam(course);
			output(response, JsonUtil.buildFalseJson("0", "启动成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}
	
	//结束考试
	@RequestMapping("/endPaper")
	public void endPaper(HttpServletResponse response,Course course){
		Course course2 = courseService.getCourseById(course.getCourseId());
		if (course2 == null) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		} else {
			ClassRecord record = classRecordService.getPaperById(course2.getClassId(), course2.getExamId(), null, null);
			record.setUpdateTime(new Date());
			record.setStatus(2);//考试结束
			classRecordService.updateStatus(record);
			List<TestRecord> testList = testRecordService.findClassPaperInfoAnalysisList(course2.getExamId(), course2.getClassId());
			if (testList != null && !testList.isEmpty()) {
				for (TestRecord test : testList) {
					TestRecord tr = testRecordService.getTestrecordById(test.getId());
					if (tr != null) {
						tr.setUpdateTime(new Date());
						tr.setStatus(3);//考试结束
						testRecordService.updateStatus(tr);
					}
				}
			}
			course.setUpdateTime(new Date());
			courseService.updateCoursebyexam(course);
			output(response, JsonUtil.buildFalseJson("0", "结束成功!"));
		}
	}
	/**
	 * 撤回考试，只需要把课程表里关联的试卷清掉就好
	 * 
	 * @param courseid
	 * @param classid
	 * @param paperid
	 * @param response
	 */
	@RequestMapping("deleteExam")
	public void deleteExam(Integer courseId, HttpServletResponse response, HttpServletRequest request,
			Integer handoutId) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Course course = courseService.getCourseById(courseId);
		PaperInfo info = paperInfoService.getPiIdById(course.getExamId());
		ClassNo condition = classNoService.getClassById(course.getClassId());
		List<TestRecord> trList = testRecordService.findClassPaperInfoAnalysisList(course.getExamId(),
				course.getClassId());
		ClassRecord record = classRecordService.getPaperById(course.getClassId(), course.getExamId(), null, handoutId);
		if (currTeacher.getTeacherType().equals("1")) {
			course.setExamId(null);
			course.setUpdateTime(new Date());
			int len = courseService.updateCoursebyexam1(course);
			if (len > 0) {
				for (TestRecord tr : trList) {
					TestRecord tr1 = testRecordService.getTestrecordById(tr.getId());
					testRecordService.deleteRecordById(tr1);
				}
				if (record != null) {
					classRecordService.deleteClassRecordById(record);
				}
				// 删除试卷之后，试卷使用数依次减一
				info.setUpdateTime(new Date());
				if (info.getUsedTimes() != 0 && info.getUsedTimes() != null) {
					info.setUsedTimes(info.getUsedTimes() - 1);
				} else {
					info.setUsedTimes(0);
				}
				paperInfoService.updateUsedTimes(info);
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("考试管理");
				log1.setOperationType("撤销");
				log1.setOperationResult("成功");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】撤销试卷成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} else {
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("考试义管理");
				log1.setOperationType("撤销");
				log1.setOperationResult("失败");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】撤销试卷失败!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
				output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
			}
		} else {
			if (!currTeacher.getCampus().equals(condition.getSchoolArea())) {
				output(response, JsonUtil.buildFalseJson("2", "系统检测到您所在的校区与改班级不一致!!"));
			} else {
				course.setExamId(null);
				course.setUpdateTime(new Date());
				int len = courseService.updateCoursebyexam1(course);
				if (len > 0) {
					for (TestRecord tr : trList) {
						TestRecord tr1 = testRecordService.getTestrecordById(tr.getId());
						testRecordService.deleteRecordById(tr1);
					}
					if (record != null) {
						classRecordService.deleteClassRecordById(record);
					}
					// 删除试卷之后，试卷使用数依次减一
					info.setUpdateTime(new Date());
					if (info.getUsedTimes() != 0 && info.getUsedTimes() != null) {
						info.setUsedTimes(info.getUsedTimes() - 1);
					} else {
						info.setUsedTimes(0);
					}
					paperInfoService.updateUsedTimes(info);
					Log log1 = new Log();
					log1.setStartTime(new Date());
					log1.setModular("考试管理");
					log1.setOperationType("撤销");
					log1.setOperationResult("成功");
					log1.setOperationId(currTeacher.getTeacherId().toString());
					log1.setOperationName(currTeacher.getTeacherName());
					log1.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】撤销试卷成功!");
					log1.setEndTime(new Date());
					logService.saveLog(log1);
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				} else {
					Log log1 = new Log();
					log1.setStartTime(new Date());
					log1.setModular("考试义管理");
					log1.setOperationType("撤销");
					log1.setOperationResult("失败");
					log1.setOperationId(currTeacher.getTeacherId().toString());
					log1.setOperationName(currTeacher.getTeacherName());
					log1.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】撤销试卷失败!");
					log1.setEndTime(new Date());
					logService.saveLog(log1);
					output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
				}
			}
		}
	}

	/**
	 * 撤回讲义，只需要把课程表里关联的试卷清掉就好
	 * 
	 * @param courseid
	 * @param classid
	 * @param paperid
	 * @param response
	 */
	@RequestMapping("deletenote")
	public void deleteNote(Integer courseid, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Course course = new Course();
		course.setCourseId(courseid);
		course.setLecturenoteId(null);
		course.setUpdateTime(new Date());
		int len = courseService.updateCoursebynote1(course);
		if (len > 0) {
			Log log1 = new Log();
			log1.setStartTime(new Date());
			log1.setModular("讲义管理");
			log1.setOperationType("撤销");
			log1.setOperationResult("成功");
			log1.setOperationId(currTeacher.getTeacherId().toString());
			log1.setOperationName(currTeacher.getTeacherName());
			log1.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】撤销讲义成功!");
			log1.setEndTime(new Date());
			logService.saveLog(log1);
			output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
		} else {
			Log log1 = new Log();
			log1.setStartTime(new Date());
			log1.setModular("讲义管理");
			log1.setOperationType("撤销");
			log1.setOperationResult("失败");
			log1.setOperationId(currTeacher.getTeacherId().toString());
			log1.setOperationName(currTeacher.getTeacherName());
			log1.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】撤销试卷失败!");
			log1.setEndTime(new Date());
			logService.saveLog(log1);
			output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
		}
	}

	/**
	 * 取消作业
	 * 
	 * @param response
	 * @param type
	 * @param courseid
	 */
	@RequestMapping("/deletehomework")
	public void deletehomework(HttpServletResponse response, Integer type, Integer courseId) {
		Course course = courseService.getCourseById(courseId);
		if (type == 1) {
			List<HomeworkRecord> recordList = homeworkRecordService.findClassHomeworkAnalyList(course.getClassId(),
					course.getExamId());
			if (!recordList.isEmpty()) {
				for (HomeworkRecord hr : recordList) {
					homeworkRecordService.deleteRecordById(hr.getId());
				}
			}
			Homework homework = homeworkService.getHomeworkById(course.getExamId());
			if (homework != null) {
				// 添加作业之后把作业使用数加一
				homework.setUpdateTime(new Date());
				if (homework.getUserTimes() != null && homework.getUserTimes() != 0) {
					homework.setUserTimes(homework.getUserTimes() - 1);
				} else {
					homework.setUserTimes(0);
				}
				homeworkService.updateUserTimes(homework);
			}
			course.setUpdateTime(new Date());
			course.setExamId(null);
			courseService.deleteExam(course);
			output(response, JsonUtil.buildFalseJson("0", "取消成功!"));
		} else {
			List<HomeworkRecord> recordList = homeworkRecordService.findClassHomeworkAnalyList(course.getClassId(),
					course.getHomeworkId());
			if (!recordList.isEmpty()) {
				for (HomeworkRecord hr : recordList) {
					homeworkRecordService.deleteRecordById(hr.getId());
				}
			}
			Homework homework = homeworkService.getHomeworkById(course.getHomeworkId());
			if (homework != null) {
				// 添加作业之后把作业使用数加一
				homework.setUpdateTime(new Date());
				if (homework.getUserTimes() != null && homework.getUserTimes() != 0) {
					homework.setUserTimes(homework.getUserTimes() - 1);
				} else {
					homework.setUserTimes(0);
				}
				homeworkService.updateUserTimes(homework);
			}
			course.setUpdateTime(new Date());
			course.setHomeworkId(null);
			courseService.deleteHome(course);
			output(response, JsonUtil.buildFalseJson("0", "取消成功!"));
		}
	}
	
	//查看试卷情况
	@RequestMapping("/checkPaper")
	public ModelAndView checkPaper(ModelAndView mView, Integer paperId, Integer studentId) {
		List<SeePaper> spList = new ArrayList<SeePaper>();
		PaperInfo info = paperInfoService.getPaperInfoById(paperId);
		// 获取单选题的列表
		List<QuestionSingleChoice> singledetail = singleChoiceService.thePapersById(paperId, 1, 0, 100);
		// 获取单选题我的答案列表
		List<QuestionSingleChoice> list1 = singleChoiceService.thePapersAnswerById(studentId, paperId, 1, 0, 100);
		for (QuestionSingleChoice qsc : singledetail) {
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
			if (!list1.isEmpty() && list1 != null) {
				for (QuestionSingleChoice qsc1 : list1) {
					if (qsc1.getChoiceId() == qsc.getChoiceId()) {
						sp.setIsTrue(qsc1.getPaperAnswer().getIsTrue());
						sp.setWriteAnswer(qsc1.getPaperAnswer().getWriteAnswer());
					}
				}
			} else {
				sp.setWriteAnswer("未提交");
			}
			spList.add(sp);
		}
	    //多选
		List<QuestionMulitChoice> mulitdetail = mulitChoiceService.thePapersById(paperId, 2, 0, 100);
		List<QuestionMulitChoice> list2 = mulitChoiceService.thePapersAnswerById(studentId, paperId, 2, 0, 100);
		for (QuestionMulitChoice qmc : mulitdetail) {
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
			if (!list2.isEmpty() && list2 != null) {
				for (QuestionMulitChoice qmc1 : list2) {
					if (qmc1.getChoiceId() == qmc.getChoiceId()) {
						sp1.setIsTrue(qmc1.getPaperAnswer().getIsTrue());
						sp1.setWriteAnswer(qmc1.getPaperAnswer().getWriteAnswer());
					}
				}
			} else {
				sp1.setWriteAnswer("未提交");
			}
			spList.add(sp1);
		}
		//判断
		List<QuestionJudge> judgeDetail = judgeService.thePapersById(paperId, 3, 0, 100);
		List<QuestionJudge> list3 = judgeService.thePapersAnswerById(studentId, paperId, 3, 0, 100);
		for (QuestionJudge qj : judgeDetail) {
			SeePaper sp2 = new SeePaper();
			sp2.setQuestionId(qj.getJudgeId());
			sp2.setQuestiontype(qj.getQuestionsType().getTopicId());
			sp2.setJudgeDesc(qj.getJudgeDesc());
			sp2.setAnswer2(qj.getAnswer2());
			sp2.setQuestionName(qj.getQuestionsType().getTopicName());
			sp2.setAnswerDesc2(qj.getAnswerDesc2());
			if (!list3.isEmpty() && list3 != null) {
				for (QuestionJudge qj1 : list3) {
					if (qj1.getJudgeId() == qj.getJudgeId()) {
						sp2.setIsTrue(qj1.getPaperAnswer().getIsTrue());
						sp2.setWriteAnswer(qj1.getPaperAnswer().getWriteAnswer());
					}
				}
			} else {
				sp2.setWriteAnswer("未提交");
			}
			spList.add(sp2);
		}
		// 先拿到阅读理解大标题
		List<QuestionRead> readDetail = readService.thePapersById(paperId, 4, 0, 100);
		for (QuestionRead qr : readDetail) {
			// 在拿到子题目
			SeePaper sp3 = new SeePaper();
			sp3.setQuestionId(qr.getReadId());
			sp3.setAnswer_read(qr.getAnswer_read());
			sp3.setOptionA_read(qr.getOptionA_read());
			sp3.setOptionB_read(qr.getOptionB_read());
			sp3.setOptionC_read(qr.getOptionC_read());
			sp3.setOptionD_read(qr.getOptionD_read());
			sp3.setReadDesc(qr.getReadDesc());
			List<QuestionRead> list4 = readService.getHomework_answerDetails(paperId, studentId, qr.getReadId());
			if (!list4.isEmpty() && list4 != null) {
				List<QuestionPaer> qrList = new ArrayList<QuestionPaer>();
				for (QuestionRead qt : list4) {
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
					qrQuestionRead.setAnswerDesc_read(qt.getAnswerDesc_read());
					qrList.add(qrQuestionRead);
				}
				sp3.setQuestionRead(qrList);
				sp3.setQuestiontype(qr.getQuestionsType().getTopicId());
				sp3.setQuestionName(qr.getQuestionsType().getTopicName());
			} else {
				sp3.setWriteAnswer("未提交");
			}
			spList.add(sp3);
		}
		// 拿到主观题
		List<SubjectiveQuestion> list5 = questionService.thePaperDetailsById(studentId, paperId, 5, 0, 100);
		if (list5 != null && !list5.isEmpty()) {
			for (SubjectiveQuestion sq : list5) {
				SeePaper sp4 = new SeePaper();
				sp4.setQuestionId(sq.getSubjectiveId());
				sp4.setQuestiontype(sq.getQuestionsType().getTopicId());
				sp4.setSubjectiveTitle(sq.getSubjectiveTitle());
				sp4.setAnswerAnalysis(sq.getAnswerAnalysis());
				sp4.setQuestionName(sq.getQuestionsType().getTopicName());
				spList.add(sp4);
			}
		}
		// 拿到填空题
		List<QuestionFill> fillList = fillService.thePapersById(paperId, 6, 0, 100);
		// 判断是否提交答案
		List<QuestionFill> qfList = fillService.thePapersAnswerById(studentId, paperId, 6, 0, 100);
		if (fillList != null && !fillList.isEmpty()) {
			for (QuestionFill sq : fillList) {
				SeePaper sp5 = new SeePaper();
				sp5.setQuestionId(sq.getFillId());
				sp5.setQuestiontype(sq.getQuestionsType().getTopicId());
				sp5.setFillTitle(sq.getFillTitle());
				sp5.setAnswerAnalysis(sq.getAnswerAnalysis());
				sp5.setFillAnswer(sq.getFillAnswer());
				sp5.setTypeName(sq.getFillType().getTypeName());
				sp5.setQuestionName(sq.getQuestionsType().getTopicName());
				if (!qfList.isEmpty() && qfList != null) {
					for (QuestionFill qFill : qfList) {
						if (qFill.getFillId() == sq.getFillId()) {
							sp5.setIsTrue(qFill.getPaperAnswer().getIsTrue());
							sp5.setWriteAnswer(qFill.getPaperAnswer().getWriteAnswer());
						}
					}
				} else {
					sp5.setWriteAnswer("未提交");
				}
				spList.add(sp5);
			}
		}
		// 完形填空内容
		List<QuestionCloze> cList = clozeService.thePapersById(paperId, 7);
		if (cList != null && !cList.isEmpty()) {
			for (QuestionCloze cloze : cList) {
				SeePaper sp6 = new SeePaper();
				sp6.setQuestionId(cloze.getClozeId());
				sp6.setClozeContent(cloze.getClozeContent());
				List<QuestionCloze> clozes = clozeService.thePapersAnswerById(studentId, paperId, cloze.getClozeId());
				if (!clozes.isEmpty() && clozes != null) {
					List<QuestionPaer> qrList = new ArrayList<QuestionPaer>();
					for (QuestionCloze qt : clozes) {
						QuestionPaer qrQuestionRead = new QuestionPaer();
						qrQuestionRead.setReadId(qt.getClozeId());
						qrQuestionRead.setIsTrue(qt.getPaperAnswer().getIsTrue());
						qrQuestionRead.setWriteAnswer(qt.getPaperAnswer().getWriteAnswer());
						qrQuestionRead.setOptionA_read(qt.getClozeOptionA());
						qrQuestionRead.setOptionB_read(qt.getClozeOptionB());
						qrQuestionRead.setOptionC_read(qt.getClozeOptionC());
						qrQuestionRead.setOptionD_read(qt.getClozeOptionD());
						qrQuestionRead.setAnswer_read(qt.getClozeAnswer());
						qrQuestionRead.setAnswerDesc_read(qt.getAnalysis());
						qrList.add(qrQuestionRead);
					}
					sp6.setQuestionRead(qrList);
					sp6.setQuestiontype(cloze.getQuestionsType().getTopicId());
					sp6.setQuestionName(cloze.getQuestionsType().getTopicName());
				} else {
					sp6.setWriteAnswer("未提交");
				}
				spList.add(sp6);
			}
		}
		mView.addObject("info", info);
		mView.addObject("spList", spList);
		mView.setViewName("lessonmanage/checkPaper");
		return mView;
	}
}
