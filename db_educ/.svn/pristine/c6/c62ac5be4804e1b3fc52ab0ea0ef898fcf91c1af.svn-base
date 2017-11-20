package com.db.action;

import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.ClassNo;
import com.db.entity.Grade;
import com.db.entity.PaperInfo;
import com.db.entity.SchoolYear;
import com.db.entity.SchoolZone;
import com.db.entity.SeasonType;
import com.db.entity.Student;
import com.db.entity.StudentCareer;
import com.db.entity.StudentExamination;
import com.db.entity.Subject;
import com.db.entity.Teacher;
import com.db.entity.TestRecord;
import com.db.service.ClassNoService;
import com.db.service.ClassroomService;
import com.db.service.GradeService;
import com.db.service.PaperInfoService;
import com.db.service.PaperRecordService;
import com.db.service.SchoolYearService;
import com.db.service.SchoolZoneService;
import com.db.service.SeasonTypeService;
import com.db.service.StudentCareerService;
import com.db.service.StudentExaminationService;
import com.db.service.StudentService;
import com.db.service.SubjectService;
import com.db.service.TestRecordService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;

/**
 * 入学考试
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping("/entranceexam")
public class EntranceExamController extends BaseUtil {

	@Resource
	private StudentService studentservice;
	@Resource
	private SubjectService subjectservice;
	@Resource
	private GradeService gradeservice;
	@Resource
	private PaperInfoService paperInfoService;
	@Resource
	private ClassroomService classroomService;
	@Resource
	private PaperRecordService recordService;
	@Resource
	private TestRecordService testRecordService;
	@Resource
	private SchoolZoneService schoolZoneService;
	@Resource
	private SchoolYearService schoolYearService;
	@Resource
	private SeasonTypeService seasonTypeService;
	@Resource
	private StudentExaminationService examinationService;
	@Resource
	private StudentCareerService careerService;

	@Resource
	private ClassNoService classNoService;

	public Logger log = Logger.getLogger(EntranceExamController.class);

	HttpSession session;

	/**
	 * 新增入学考试
	 * 
	 * @return
	 */
	@RequestMapping("/newExamination")
	public ModelAndView newExamination(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<Subject> subjects = null;
		List<Grade> grades = null;
		List<SchoolZone> szList = null;
		if (currTeacher.getTeacherType().equals("1")) {
			subjects = subjectservice.getSubject();
			grades = gradeservice.getGrade();
			szList = schoolZoneService.getAllSchool();
		}else {
			subjects = subjectservice.getTeacherIsSubject(currTeacher.getSubject());
			String[] grade =  currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(grade);
			grades = gradeservice.getGradeByIds(gradeList);
			szList = schoolZoneService.findSchoolList(currTeacher.getCampus());
		}
		
		List<SchoolYear> syList = schoolYearService.getYear();
		List<SeasonType> stList = seasonTypeService.getSeasontype();
		mv.addObject("subjects", subjects);
		mv.addObject("grades", grades);
		mv.addObject("szList", szList);
		mv.addObject("syList", syList);
		mv.addObject("stList", stList);
		mv.setViewName("entranceExam/newExam");
		return mv;
	}

	/**
	 * 保存添加的入学考试
	 * 
	 * @param response
	 * @param request
	 * @param tr
	 */
	@RequestMapping("/addExamination")
	public void addExamination(HttpServletResponse response, HttpServletRequest request, StudentExamination tr) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		StudentExamination se = examinationService.getPaperIdExaminationById(tr.getPaperId(), tr.getSubjectId(),
				tr.getChoiceId(), tr.getYear(), tr.getGradeId(), tr.getSchoolId());
		PaperInfo info = paperInfoService.getPaperInfoById(tr.getPaperId());
		if (se != null) {
			output(response, JsonUtil.buildFalseJson("1", "系统检测您已添加该入学考试!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				tr.setStatus(0);
				examinationService.addExamination(tr);
				if (info != null) {
					info.setUpdateTime(new Date());
					info.setUsedTimes(info.getUsedTimes() + 1);
					paperInfoService.updateUsedTimes(info);
				}
				output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
			} else {
				if (!tr.getSchoolId().equals(currTeacher.getCampus())) {
					output(response, JsonUtil.buildFalseJson("2", "您添加的校区与您所在的校区不一致!"));
				} else {
					tr.setStatus(0);
					examinationService.addExamination(tr);
					if (info != null) {
						info.setUpdateTime(new Date());
						info.setUsedTimes(info.getUsedTimes() + 1);
						paperInfoService.updateUsedTimes(info);
					}
					output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
				}
			}
		}
	}

	/**
	 * 入学考试列表(新增)
	 * 
	 * @param request
	 * @param response
	 * @param gradeid
	 * @param subjectid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/execute")
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response, Integer gradeId,
			Integer subjectId, Integer pageNo, Integer pageSize, Integer schoolId) throws Exception {

		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		 session = request.getSession();
		 Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		ModelAndView view = new ModelAndView();
		List<StudentExamination> records = null;
		List<SchoolZone> szList =null;
		int rowsCount = 0;
		if (teacher.getTeacherType().equals("1")) {
			records = examinationService.findEexecuteList((pageNo - 1) * pageSize, pageSize,
					subjectId, gradeId, schoolId);
			rowsCount = examinationService.getCount(subjectId, gradeId, schoolId);
			szList = schoolZoneService.getAllSchool();
		} else {
			records = examinationService.findTeacherSchoolId((pageNo - 1) * pageSize, pageSize,
					subjectId, gradeId,teacher.getCampus());
			 rowsCount = examinationService.getSchoolIdCount(subjectId, gradeId, teacher.getCampus());
			 szList = schoolZoneService.findSchoolList(teacher.getCampus());
		}
		if (records != null && !records.isEmpty()) {
			view.addObject("records", records);
		} else {
			view.addObject("records", null);
		}
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<Subject> subjects = subjectservice.getSubject();
		List<Grade> grades = gradeservice.getGrade();
		view.addObject("schoolId", schoolId);
		view.addObject("pageSize", pageSize);
		view.addObject("page", pageNo);
		view.addObject("subjectId", subjectId);
		view.addObject("gradeId", gradeId);
		view.addObject("szList", szList);
		view.addObject("subjects", subjects);
		view.addObject("grades", grades);
		view.addObject("teacher", teacher);
		view.addObject("totalPages", totalPages);
		view.setViewName("entranceExam/entranceexam");
		return view;
	}

	/**
	 * 入学考试列表
	 * 
	 * @param request
	 * @param subjectid
	 * @param gradeid
	 * @param pageNo
	 * @param pageSize
	 * @param response
	 */
	@RequestMapping("/getentranceman")
	public void getEntranceMan(HttpServletRequest request, Integer subjectid, Integer gradeid, Integer pageNo,
			Integer pageSize, HttpServletResponse response) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		Map<String, Object> map = new HashMap<>();
		map.put("schoolId", teacher.getCampus());
		map.put("subjectId", subjectid);
		map.put("gradeId", gradeid);
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		// 获取老师所在校区的学员的正在入学考试的列表
		List<TestRecord> records1 = testRecordService.getEntranceingStudent(map);
		// 获取总数
		int totalCount = (int) testRecordService.getEntranceingStudentCount(map);
		PageUtil util = new PageUtil(totalCount, pageNo, pageSize);
		if (!records1.isEmpty() && records1 != null) {
			output(response, JsonUtil.buildJsonByTotalCount(records1, util.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有正在入学考试的学员"));
		}
	}
	
	/**
	 * 完成入学考试列表(分配班级)
	 * 
	 * @return
	 */
	@RequestMapping("/endExecute")
	public ModelAndView endExecute(Integer seasonId, Integer gradeId, Integer subjectId, Integer pageNo,
			Integer pageSize, Integer schoolId, Integer grade, Integer score,HttpServletRequest request) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		int rowsCount = 0;
		List<TestRecord> trList = null;
		if (currTeacher.getTeacherType().equals("1")) {
			trList= testRecordService.getTestRecordStatus((pageNo - 1) * pageSize, pageSize, schoolId,
					subjectId, gradeId, grade, score);
			rowsCount = testRecordService.getStatusCout(schoolId, subjectId, gradeId);
		} else {
			trList= testRecordService.getTestTeacherSchoolId((pageNo - 1) * pageSize, pageSize, currTeacher.getCampus(),
					subjectId, gradeId, grade, score);
			rowsCount = testRecordService.getSchoolIdTeacherCout(currTeacher.getCampus(), subjectId, gradeId);
		}
		for (TestRecord student : trList) {
			List<StudentCareer> list = careerService.findCareerGradeId(student.getStudentId(), student.getGradeId());
			if (list != null && !list.isEmpty()) {
				student.setCareerList(list);
			} else {
				student.setCareerList(null);
			}
		}
		ModelAndView mv = new ModelAndView();
		PageUtil util = new PageUtil(rowsCount, pageNo, pageSize);// 拿到总页数
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		if (trList != null && !trList.isEmpty()) {
			mv.addObject("trList", trList);
		} else {
			mv.addObject("trList", null);
		}
		List<Subject> subjects = subjectservice.getSubject();
		List<Grade> grades = gradeservice.getGrade();
		List<SchoolZone> szList = schoolZoneService.getAllSchool();
		List<ClassNo> cnList = classNoService.getClassesNo2(seasonId);
		List<SchoolYear> syList = schoolYearService.getYear();
		List<SeasonType> stList = seasonTypeService.getSeasontype();
		mv.addObject("cnList", cnList);
		mv.addObject("syList", syList);
		mv.addObject("stList", stList);
		mv.setViewName("entranceExam/completeExecute");
		mv.addObject("totalPages", util.getTotalPageCount());
		mv.addObject("subjects", subjects);
		mv.addObject("subjectId", subjectId);
		mv.addObject("schoolId", schoolId);
		mv.addObject("gradeId", gradeId);
		mv.addObject("pageSize", pageSize);
		mv.addObject("grades", grades);
		mv.addObject("page", pageNo);
		mv.addObject("totalPages", totalPages);
		mv.addObject("szList", szList);
		return mv;
	}

	/**
	 * 分配班级页面列表
	 */
	@RequestMapping("/getClassNoList")
	public void getClassNoList(HttpServletResponse response, Integer pageNo, Integer pageSize, Integer seasonType,
			Integer grade,HttpServletRequest request) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 8;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<ClassNo> sList = null;
		int rowsCount = 0;
		if (currTeacher.getTeacherType().equals("1")) {
			sList = classNoService.findSubjectList(null, null, seasonType, grade, (pageNo - 1) * pageSize,
					pageSize);
			rowsCount = classNoService.getCountClassId(null, null, seasonType, grade);
		} else {
			sList = classNoService.getSchoolIdByList(seasonType,grade,(pageNo - 1) * pageSize,pageSize,currTeacher.getCampus());
			rowsCount = classNoService.getCountSchoolId(seasonType, grade,currTeacher.getCampus());
		}
		PageUtil util = new PageUtil(rowsCount, pageNo, pageSize);
		if (!sList.isEmpty() && sList != null) {
			output(response, JsonUtil.buildJsonByTotalCount(sList, util.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
	}

	/**
	 * 删除完成入学考试
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/endDelete")
	public void endDelete(Integer id, HttpServletResponse response) {
		TestRecord tr = testRecordService.getTestrecordById(id);
		try {
			testRecordService.deleteRecordById(tr);
			output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
		} catch (Exception e) {
			output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
		}
	}

	/**
	 * 完成入学考试Ajax分业
	 * 
	 * @param request
	 * @param subjectid
	 * @param gradeid
	 * @param pageNo
	 * @param pageSize
	 * @param response
	 */
	@RequestMapping("/getentranceexam")
	public void getEntranceExam(HttpServletRequest request, Integer subjectid, Integer gradeid, Integer pageNo,
			Integer pageSize, HttpServletResponse response) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		Map<String, Object> map = new HashMap<>();
		map.put("teacherId", teacher.getTeacherId());
		map.put("subjectId", subjectid);
		map.put("gradeId", gradeid);
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		// 获取入学考试试卷列表
		List<PaperInfo> paperInfos = paperInfoService.getEntrancePaperList(map);
		int totalCount = (int) paperInfoService.getEntrancePaperListCount(map);
		PageUtil util = new PageUtil(totalCount, pageNo, pageSize);
		if (!paperInfos.isEmpty() && paperInfos != null) {
			output(response, JsonUtil.buildJsonByTotalCount(paperInfos, util.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "入学考试还未添加"));
		}
	}

	/**
	 * 启动考试,跟新入学考试的状态
	 * 
	 * @param record
	 * @param response
	 */
	@RequestMapping("/startExam")
	public void startExam(HttpServletResponse response, Integer id, HttpServletRequest request) {
		StudentExamination exam = examinationService.getExaminationById(id);
		session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (exam.getStatus() == 1 || exam.getStatus() == 2) {
			output(response, JsonUtil.buildFalseJson("1", "该试卷已开启!"));
		} else if (exam.getStatus() == 3) {
			output(response, JsonUtil.buildFalseJson("2", "该试卷已完成考试!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				exam.setUpdateTime(new Date());
				exam.setStatus(1);// 启动考试
				examinationService.updateStatus(exam);
				output(response, JsonUtil.buildFalseJson("0", "启动成功!"));
			} else {
				if (!currTeacher.getCampus().equals(exam.getSchoolId())) {
					output(response, JsonUtil.buildFalseJson("3", "系统检测到您与该校区不符合!"));
				} else {
					exam.setUpdateTime(new Date());
					exam.setStatus(1);// 启动考试
					examinationService.updateStatus(exam);
					output(response, JsonUtil.buildFalseJson("0", "启动成功!"));
				}
			}
		}
	}

	/**
	 * 结束考试
	 * 
	 * @param response
	 * @param id
	 * @param request
	 */
	@RequestMapping("/endExam")
	public void endExam(HttpServletResponse response, Integer examId, HttpServletRequest request) {
		StudentExamination se = examinationService.getExaminationById(examId);
		session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (se != null && se.getStatus() == 3) {
			output(response, JsonUtil.buildFalseJson("1", "该试卷已完成考试!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				se.setUpdateTime(new Date());
				se.setStatus(3);// 完成考试
				examinationService.updateStatus(se);
				output(response, JsonUtil.buildFalseJson("0", "结束考试成功!"));
			} else {
				if (!currTeacher.getCampus().equals(se.getSchoolId())) {
					output(response, JsonUtil.buildFalseJson("2", "系统检测到您与该校区不匹配!"));
				} else {
					se.setUpdateTime(new Date());
					se.setStatus(3);// 完成考试
					examinationService.updateStatus(se);
					output(response, JsonUtil.buildFalseJson("0", "结束考试成功!"));
				}
			}
		}
	}

	/**
	 * 删除入学考试
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/deleteExam")
	public void deleteExam(Integer examId, HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		StudentExamination se = examinationService.getExaminationById(examId);
		if (se != null && (se.getStatus() == 1 || se.getStatus() == 2)) {
			output(response, JsonUtil.buildFalseJson("1", "系统检测到该试卷正在考试中!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				examinationService.deleteRecordById(se);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} else {
				if (!currTeacher.getCampus().equals(se.getSchoolId())) {
					output(response, JsonUtil.buildFalseJson("2", "系统检测到您与该校区不匹配!"));
				} else {
					examinationService.deleteRecordById(se);
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				}
			}
		}
	}
	
	/**
	 * 
	 * 删除学员入学考试
	 *
	 * @param id
	 * @param response
	 */
	@RequestMapping("/deleteStudentExam")
	 public void deleteStudentExam(Integer id,HttpServletResponse response){
		TestRecord tr = testRecordService.getTestrecordById(id);
		if (tr != null) {
			testRecordService.deleteRecordById(tr);
			output(response, JsonUtil.buildFalseJson("0", "删除成功"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
	}

	/**
	 * 分配班级
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/distributionClassNo")
	public void feipenClassNo(Integer id, Integer classId, HttpServletResponse response, Integer gradeId) {
		TestRecord tr = testRecordService.getTestrecordById(id);
		Student student = studentservice.getStudentById(tr.getStudentId());
		ClassNo cn = classNoService.getClassById(classId);
		StudentCareer sCareer = careerService.findStudentCareer(tr.getStudentId(), classId);
		StudentCareer sCareer2 = careerService.findCareerBySIdAndYearSeason(tr.getStudentId(),
				Integer.valueOf(cn.getYear()), cn.getSeasonType(), tr.getGradeId(), cn.getSubject());
		if (sCareer2 != null) {
			output(response, JsonUtil.buildFalseJson("7", "该学员已分配班级!"));
		} else if (sCareer != null) {
			output(response, JsonUtil.buildFalseJson("1", "该学员已在该班级，请勿重新操作!"));
		} else if (cn != null && cn.getActualNumber() == cn.getClassNumber()) {
			output(response, JsonUtil.buildFalseJson("6", "该班级目前人数已满，请分配其他班级!"));
		} else if (cn.getGrade() != gradeId) {
			output(response, JsonUtil.buildFalseJson("8", "您分配的班级和该学员所在年级不一致!"));
		} else {
			tr.setClassId(classId);
			tr.setUpdateTime(new Date());
			testRecordService.updateClassId(tr);
			// 修改学员的入学状态
			student.setUpdateTime(new Date());
			student.setClassNo(classId.toString());
			student.setStudentStatus(4);
			student.setSchoolId(cn.getSeasonType());
			studentservice.modityClassNo(student);

			// 往学员生涯表添加数据
			StudentCareer career = new StudentCareer();
			career.setCreateTime(new Date());
			career.setGrade(tr.getGradeId());
			career.setStudentId(tr.getStudentId());
			career.setClassId(classId);
			career.setYear(Integer.valueOf(cn.getYear()));
			career.setSubject(tr.getSubjectId());
			career.setSeasonType(cn.getSeasonType());
			career.setStatus(2);
		    career.setSchoolId(cn.getSchoolArea());
			careerService.addStudentCareer(career);
			// 分配班级成功后依次增加班级的实际人数
			cn.setUpdateTime(new Date());
			cn.setActualNumber(cn.getActualNumber() + 1);
			classNoService.updateClasses(cn);
			output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
		}
	}

	/**
	 * 修改入学考试页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/modityExam")
	public ModelAndView modityExam(Integer examId,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		StudentExamination tr = examinationService.getExaminationById(examId);
		PaperInfo pInfo = paperInfoService.getPaperInfoById(tr.getPaperId());
		mv.addObject("tr", tr);
		mv.addObject("pInfo", pInfo);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<Subject> subjects = null;
		List<Grade> grades = null;
		List<SchoolZone> szList = null;
		if (currTeacher.getTeacherType().equals("1")) {
			 subjects = subjectservice.getSubject();
			 grades= gradeservice.getGrade();
			 szList = schoolZoneService.getAllSchool();
		} else {
			subjects = subjectservice.getTeacherIsSubject(currTeacher.getSubject());
			szList = schoolZoneService.findSchoolList(currTeacher.getCampus());
			String[] grade = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(grade);
			grades = gradeservice.getGradeByIds(gradeList);
		}
		List<SchoolYear> syList = schoolYearService.getYear();
		List<SeasonType> stList = seasonTypeService.getSeasontype();
		mv.addObject("subjects", subjects);
		mv.addObject("grades", grades);
		mv.addObject("szList", szList);
		mv.addObject("syList", syList);
		mv.addObject("stList", stList);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = sdf.format(tr.getCreateTime());
		String endTime = sdf.format(tr.getEndTime());
		mv.addObject("startTime", startTime);
		mv.addObject("endTime", endTime);
		mv.setViewName("entranceExam/modityExam");
		return mv;

	}

	/**
	 * 保存修改的入学考试
	 * 
	 * @param response
	 * @param tr
	 */
	@RequestMapping("/updateExamination")
	public void updateExamination(HttpServletResponse response, StudentExamination tr, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		StudentExamination se = examinationService.getExaminationById(tr.getExamId());
		if (se.getStatus() != 0) {
			output(response, JsonUtil.buildFalseJson("1", "该考试已被使用，暂不能修改!"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				tr.setUpdateTime(new Date());
				examinationService.updateExamination(tr);
				output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
			} else {
				if (!currTeacher.getCampus().equals(tr.getSchoolId())) {
					output(response, JsonUtil.buildFalseJson("2", "你修改的校区与您所在的校区不一致!"));
				} else {
					tr.setUpdateTime(new Date());
					examinationService.updateExamination(tr);
					output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
				}
			}
		}
	}
}
