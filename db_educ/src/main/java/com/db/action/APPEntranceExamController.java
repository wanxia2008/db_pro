package com.db.action;

import java.util.ArrayList;
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

import com.db.entity.QuestionCloze;
import com.db.entity.QuestionFill;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.Student;
import com.db.entity.StudentExamination;
import com.db.entity.SubjectiveQuestion;
import com.db.entity.Teacher;
import com.db.entity.TestRecord;
import com.db.service.PaperInfoService;
import com.db.service.PaperRecordService;
import com.db.service.QuestionClozeService;
import com.db.service.QuestionFillService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.StudentExaminationService;
import com.db.service.StudentService;
import com.db.service.SubjectiveQuestionService;
import com.db.service.TestRecordService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/app/entranceexam")
public class APPEntranceExamController extends BaseUtil {

	@Resource
	private StudentService studentservice;
	@Resource
	private PaperInfoService paperInfoService;
	@Resource
	private PaperRecordService recordService;
	@Resource
	private TestRecordService testRecordService;
	@Resource
	private StudentExaminationService examinationService;
	@Resource
	private QuestionSingleChoiceService qsChoiceService;
	@Resource
	private QuestionMulitChoiceService qMulitChoiceService;
	@Resource
	private QuestionJudgeService qJudgeService;
	@Resource
	private QuestionReadService qReadService;
	@Resource
	private SubjectiveQuestionService questionService;
	@Resource
	private QuestionFillService fillservice;
	@Resource
	private TestRecordService testrecordservice;
	@Resource
	private QuestionClozeService clozeService;

	HttpSession session;
	public Logger log = Logger.getLogger(EntranceExamController.class);

	/**
	 * 入学考试列表
	 * 
	 * @param gradeId
	 * @param schoolId
	 * @param response
	 */

	@RequestMapping("/examinationList")
	public void examinationList(Integer gradeId, Integer schoolId, HttpServletResponse response, Integer pageNo,
			Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		List<StudentExamination> seList = examinationService.getexaminationList(schoolId, gradeId,
				(pageNo - 1) * pageSize, pageSize);
		if (seList != null && !seList.isEmpty()) {
			output(response, JsonUtil.buildJson(seList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 入学考试详情(拿题目)
	 * 
	 * @param response
	 * @param paperId
	 * @param pageNo
	 * @param pageSize
	 * @param status
	 * @param studentId
	 */
	@RequestMapping("/examDetails")
	public void examDetails(HttpServletResponse response, Integer paperId, Integer pageNo, Integer pageSize,
			Integer status, Integer studentId,Integer schoolId) {
		// 获取学生的考试记录状态
		TestRecord record = testrecordservice.getStauts(studentId, paperId);
		if (record != null && record.getStatus() == 3) {
			output(response, JsonUtil.buildFalseJson("2", "不好意思，当前考试已结束，请交卷！"));
		} else {
			if (pageNo == null) {
				pageNo = 1;
			}
			if (pageSize == null) {
				pageSize = 1;
			}
			List pList = new ArrayList<>();
			List<QuestionSingleChoice> list1 = qsChoiceService.thePapersById(paperId, 1, null,null);
			List<QuestionMulitChoice> list2 = qMulitChoiceService.thePapersById(paperId, 2, null,null);
			List<QuestionJudge> list3 = qJudgeService.thePapersById(paperId, 3, null, null);
			List<QuestionRead> list4 = qReadService.thePapersById(paperId, 4,null, null);
			List<SubjectiveQuestion> list5 = questionService.thePapersById(paperId, 5, null,null);
			List<QuestionFill> list6 = fillservice.thePapersById(paperId, 6, null, null);
			List<QuestionCloze> list7 = clozeService.thePapersById(paperId,7);
			int list1Size = list1.size();
			int list2Size = list2.size();
			int list3Size = list3.size();
			int list4Size = list4.size();
			int list5Size = list5.size();
			int list6Size = list6.size();
			int list7Size = list7.size();
			Map<String, Object> map = new HashMap<String, Object>();
			if (list1Size > 0 && pageNo <= list1Size) {// 第1个list中取数据
				list1 = list1.subList(pageNo - 1, pageNo);
				map.put("question", list1);
			} else if (list2Size > 0 && pageNo > list1Size && pageNo <= list1Size + list2Size) {// 第2个list中取数据
				pageNo = pageNo - list1Size;
				list2 = list2.subList(pageNo - 1, pageNo);
				map.put("question", list2);
			} else if (list3Size > 0 && pageNo > list2Size && pageNo <= list1Size + list2Size + list3Size) {// 第3个list中取数据
				pageNo = pageNo - list1Size - list2Size;
				list3 = list3.subList(pageNo - 1, pageNo);
				map.put("question", list3);
			} else if (list4Size > 0 && pageNo > list3Size && pageNo <= list1Size + list2Size + list3Size + list4Size) {// 第4个list中取数据
				pageNo = pageNo - list1Size - list2Size - list3Size;
				list4 = list4.subList(pageNo - 1, pageNo);
				map.put("question", list4);
				// 获得阅读的每道题目
				List<QuestionRead> childlist = qReadService.getReadChildByParentId(list4.get(0).getReadId());
				map.put("read_detail", childlist);
			} else if (list5Size > 0 && pageNo > list4Size
					&& pageNo <= list1Size + list2Size + list3Size + list4Size + list5Size) {// 第5个list中取数据
				pageNo = pageNo - list1Size - list2Size - list3Size - list4Size;
				list5 = list5.subList(pageNo - 1, pageNo);
				map.put("question", list5);
			} else if (list6Size > 0 && pageNo > list5Size && pageNo <= list1Size + list2Size + list3Size
					+ list4Size + list5Size+list6Size) {// 第6个list中取数据
				pageNo = pageNo - list1Size - list2Size - list3Size - list4Size - list5Size;
				list6 = list6.subList(pageNo - 1, pageNo);
				map.put("question", list6);
			} else if (list7Size > 0 && pageNo > list6Size && pageNo <= list1Size + list2Size + list3Size
					+ list4Size + list5Size+list6Size+list7Size) {// 第7个list中取数据
				pageNo = pageNo - list1Size - list2Size - list3Size - list4Size - list5Size-list6Size;
				list7 = list7.subList(pageNo - 1, pageNo);
				map.put("question", list7);
				List<QuestionCloze> clozeList = clozeService.getQuestionClozeByParentId(list7.get(0).getClozeId());
				map.put("clozeList", clozeList);
			}
			pList.add(map);
			if (pList != null && !pList.isEmpty()) {
				if (map != null && !map.isEmpty()) {
					output(response, JsonUtil.buildJson(pList));
				}else {
					output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
				}
			} else {
				output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
			}
		}
	}

	/**
	 * 结束考试，更新考试记录表的状态，更新成功后再更新学生的状态
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/endExam")
	public void endExam(HttpServletRequest request, HttpServletResponse response) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		int paperId = Integer.valueOf(request.getParameter("paperId"));
		TestRecord testRecord = new TestRecord();
		testRecord.setPaperId(paperId);
		testRecord.setUpdateTime(new Date());
		testRecord.setStatus(2);// 已结束
		int len1 = 0, len2 = 0;
		// 获取老师所在校区的学员参加的入学考试记录的列表，用于后面判断所报科目是否都已参加完入学考试
		List<TestRecord> records = testRecordService.getEntranceStudent(teacher.getCampus());
		Student student = new Student();
		for (int i = 0; i < records.size(); i++) {
			testRecord.setStudentId(records.get(i).getStudentId());
			// 更新考试记录表，该学生入学考试结束
			len1 = testRecordService.updateTestrecord(testRecord);
			if (len1 != 0) {
				student.setStudentId(records.get(i).getStudentId());
				// 拿到学生的信息，得到学生报名的课程，用于后判断报名课程是否都已参加入学考试
				Student students = studentservice.getStudentById(records.get(i).getStudentId());
				String subjects = students.getSubjectType();
				if (subjects == "" || subjects == null) {
					continue;
				}
				String[] subjectss = subjects.split(",");
				int num = 0;
				for (int j = 0; j < subjectss.length; j++) {
					if (Integer.valueOf(subjectss[j]) == records.get(i).getSubjectId()) {
						// 该学生某科目参加了入学考试
						num++;
						if (num == subjectss.length - 1) {
							student.setStudentStatus(2);// 当报名的所有课程都已参加完入学考试，则状态改为2未缴学费状态
						}
					}
				}
				student.setStatus(2);// 入学考试结束
				student.setUpdateTime(new Date());
				len2 = studentservice.updateStudentStatus(student);
			}
		}
		if (len2 != 0) {
			log.info("结束成功！");
			output(response, JsonUtil.buildFalseJson("0", "结束成功"));
		} else {
			log.error("结束失败");
			output(response, JsonUtil.buildFalseJson("2", "结束失败"));
		}
	}

	// 前台获取学生当前是否参加了入学考试的状态
	@RequestMapping("/getStatus")
	public void getStatus(HttpServletResponse response, int studentId) {
		// TestRecord record =
		// testRecordService.getStatusByStudentId(studentId);
		Student student = studentservice.getStudentById(studentId);
		System.out.println("学生入学考试状态：" + student.getStatus());
		if (student.getStatus() != null) {
			if (student.getStatus() == 1) {
				output(response, JsonUtil.buildFalseJson("1", "入学考试中"));
			} else if (student.getStatus() == 2) {
				output(response, JsonUtil.buildFalseJson("2", "已结束"));
			} else {
				output(response, JsonUtil.buildFalseJson("0", "没有入学考试"));
			}
		} else {
			output(response, JsonUtil.buildFalseJson("0", "没有入学考试"));
		}
	}

	// 前台获取试卷信息
	@RequestMapping("/getPaperInfo")
	public void getPaperInfo(HttpServletResponse response, int studentId) {
		TestRecord record = testRecordService.getPaperByTestRecord(studentId);
		if (record == null) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		} else {
			List list = new ArrayList<>();
			list.add(record);
			output(response, JsonUtil.buildJson(list));
		}
	}

	/**
	 * 是否在考试前填写了信息
	 * 
	 * @param response
	 * @param studentId
	 * @param paperId
	 */
	@RequestMapping("/isWriteMessage")
	public void isWriteMessage(HttpServletResponse response, Integer studentId, Integer paperId,Integer schoolId) {
		TestRecord record = testRecordService.isWriteMessage(studentId, paperId,schoolId);
		if (record != null) {
			output(response, JsonUtil.buildFalseJson("0", "有填写"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "还未填写"));
		}
	}
}
