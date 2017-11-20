package com.db.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.Homework;
import com.db.entity.HomeworkAnswer;
import com.db.entity.HomeworkRecord;
import com.db.entity.PaperAnswer;
import com.db.entity.PaperInfo;
import com.db.entity.QuestionCloze;
import com.db.entity.QuestionFill;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionPaer;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.SeePaper;
import com.db.entity.Student;
import com.db.entity.StudentScore;
import com.db.entity.SubjectiveQuestion;
import com.db.entity.TestRecord;
import com.db.service.HomeworkAnswerService;
import com.db.service.HomeworkRecordService;
import com.db.service.HomeworkService;
import com.db.service.MyMistakesService;
import com.db.service.PaperAnswerService;
import com.db.service.PaperDetailsService;
import com.db.service.PaperInfoService;
import com.db.service.QuestionClozeService;
import com.db.service.QuestionFillService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.StudentScoreService;
import com.db.service.StudentService;
import com.db.service.SubjectiveQuestionService;
import com.db.service.TestRecordService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.MapCacheUtil;

@Controller
@RequestMapping("/app/myPaper")
public class APPMyPaperController extends BaseUtil {

	MapCacheUtil mapcahe = MapCacheUtil.getInstance();// 缓存学生考试过程

	@Resource
	private QuestionSingleChoiceService qsChoiceService;
	@Resource
	private QuestionMulitChoiceService qMulitChoiceService;
	@Resource
	private QuestionJudgeService qJudgeService;
	@Resource
	private QuestionReadService qReadService;
	@Resource
	private MyMistakesService myMistakesService;
	@Resource
	private TestRecordService testrecordservice;
	@Resource
	private StudentService studentservice;
	@Resource
	private PaperAnswerService paperAnswerService;
	@Resource
	private PaperDetailsService paperdetailservice;
	@Resource
	private PaperInfoService paperinfoservice;
	@Resource
	private HomeworkAnswerService answerService;
	@Resource
	private HomeworkService homeworkService;
	@Resource
	private HomeworkRecordService homeworkRecordService;
	@Resource
	private SubjectiveQuestionService questionService;
	@Resource
	private QuestionFillService fillservice;
	@Resource
	private StudentScoreService scoreService;
	@Resource
	private QuestionClozeService clozeService;
	
	//我的考卷列表
	@RequestMapping("/myPaperList")
	public void myPaper(HttpServletResponse response, Integer studentId, int pageNo, int pageSize,String name) {
		
		List<TestRecord> mpList = testrecordservice.getTestListByStudentId(studentId, (pageNo - 1) * pageSize,
				pageSize,name);
		for (int i = 0; i < mpList.size(); i++) {
			// 先获取paperanswer中学生+试卷编号的条数（做题总数）
			int stu_count = paperAnswerService.getCountwhenWriting(studentId, mpList.get(i).getPaperId());
			System.out.println("学生做了几道题目：" + stu_count);
			// 再获取试题中题目的总数
			int count = paperdetailservice.findPaperTotai(mpList.get(i).getPaperId());
			System.err.println("试卷的题目数：" + count);
			if (mpList.get(i).getStatus() != 3) {
				if (stu_count > 0 && stu_count <= count) {
					mpList.get(i).setStatus(2);// 继续的状态
				}
			}
			if (mapcahe.getCaseMap("statusPaperBy" + studentId + "and" + mpList.get(i).getPaperId()) != null) {
				Map<String, Object> map = new HashMap<>();
				Map<String, Object> map1 = mapcahe
						.getCaseMap("statusPaperBy" + studentId + "and" + mpList.get(i).getPaperId());
				map.put("pageNo", map1.get("pageNoPaper" + studentId + "and" + mpList.get(i).getPaperId()));
				map.put("cstatus", map1.get("statusPaper" + studentId + "and" + mpList.get(i).getPaperId()));
				mpList.get(i).setCasemap(map);
			}
			mpList.get(i).setStudentList(null);
		}
		if (mpList != null && !mpList.isEmpty()) {
			output(response, JsonUtil.buildJson(mpList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}
	
	//试卷拿一下一道题目
	@RequestMapping("/thePaperList")
	public void thePaperList(HttpServletResponse response, Integer paperId, Integer pageNo, Integer pageSize,Integer studentId){
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
			List<QuestionRead> list4 = qReadService.thePapersById(paperId, 4, null, null);
			List<SubjectiveQuestion> list5 = questionService.thePapersById(paperId, 5, null,null);
			List<QuestionFill> list6 = fillservice.thePapersById(paperId, 6, null,null);
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
	 * 
	 * 错题解析
	 */
	@RequestMapping("/errorParsing")
	public void errorParsing(HttpServletResponse response, Integer studentId, Integer questionId,
			Integer questionType) {
		List list = null;
		if (questionType == 1) {
			list = qsChoiceService.errorParsinDetails(studentId, questionId);
		} else if (questionType == 2) {
			list = qMulitChoiceService.errorParsinDetails(studentId, questionId);
		} else if (questionType == 3) {
			list = qJudgeService.errorParsinDetails(studentId, questionId);
		} else if (questionType == 4) {

		}
		if (list != null && !list.isEmpty()) {
			output(response, JsonUtil.buildJson(list));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	//点击提交按钮，生成考试分、返回错题总数、答对总数
	@RequestMapping("/generatingFraction")
	public void generatingFraction(HttpServletResponse response, Integer studentId, Integer paperId, 
			Integer type,Integer schoolId) {
		PaperInfo info = paperinfoservice.getPaperInfoById(paperId);
//		TestRecord tr = testrecordservice.getTestRecordByStuandPaper(studentId, paperId);
		TestRecord tr = testrecordservice.isWriteMessage(studentId, paperId,null);
		if (info == null || tr == null) {
			output(response, JsonUtil.builderFalseJson("1", "没有数据!"));
		} else {
			if (tr != null && tr.getStatus() != 3) {
					// 只要第一次做题更改一次updatetime就好
					if (tr.getUpdateTime() != null) {
						tr.setTotalTime((long) (new Date().getTime() - tr.getUpdateTime().getTime()) / 60000);
					}else {
						tr.setTotalTime((long) (new Date().getTime() - tr.getCreateTime().getTime()) / 60000);
					}
					tr.setUpdateTime(new Date());
					if (tr.getScore() == null) {
						tr.setScore(0.0);
					}
					tr.setStatus(3);
					int len = testrecordservice.updateTotaiTime(tr);
					if (len > 0) {
						if (type == 4) {
							TestRecord tr1 = testrecordservice.getTestRecordByStuandPaper(studentId, paperId);
							Student student = studentservice.getStudentById(studentId);
							student.setStudentId(studentId);
							student.setStudentStatus(2);// 设为未缴费
							student.setStatus(2);// 入学考试结束
							student.setSchoolId(schoolId);
							student.setUpdateTime(new Date());
							//修改校区
							tr1.setUpdateTime(new Date());
							tr1.setChoiceId(schoolId);
							testrecordservice.updateTotaiTime(tr1);
							int len1 = studentservice.updateStudentStatus(student);
							if (len1 > 0) {
								// 添加学生生涯记录
								// 做完考卷则清掉缓存
								mapcahe.removeCaseMap("statusPaperBy" + studentId + "and" + paperId);
								mapcahe.removeMap("pageNoPaper" + studentId + "and" + paperId);
								mapcahe.removeMap("statusPaper" + studentId + "and" + paperId);
								mapcahe.removeMap(studentId+"statepaper");
								List<TestRecord> list = new ArrayList<>();
								list.add(tr);
								output(response, JsonUtil.buildJson(list));
							}
						} else {
							// 做完考卷则清掉缓存
							mapcahe.removeCaseMap("statusPaperBy" + studentId + "and" + paperId);
							mapcahe.removeMap("pageNoPaper" + studentId + "and" + paperId);
							mapcahe.removeMap("statusPaper" + studentId + "and" + paperId);
							mapcahe.removeMap(studentId+"statepaper");
							List<TestRecord> list = new ArrayList<>();
							list.add(tr);
							output(response, JsonUtil.buildJson(list));
						}
					} else {
						output(response, JsonUtil.builderFalseJson("1", "提交考卷失败!"));
					}
			} else {// 老师后台已经结束考试
				// 修改考卷的状态
				tr.setUpdateTime(new Date());
				if (tr.getUpdateTime() != null) {
					tr.setTotalTime((long) (new Date().getTime() - tr.getUpdateTime().getTime()) / 60000);
				}else {
					tr.setTotalTime((long) (new Date().getTime() - tr.getCreateTime().getTime()) / 60000);
				}
				tr.setStatus(3);
				if (tr.getScore() == null) {
					tr.setScore(0.0);
				}
				int len = testrecordservice.updateTotaiTime(tr);
				if (len > 0) {
					if (type == 4) {
						TestRecord tr1 = testrecordservice.getTestRecordByStuandPaper(studentId, paperId);
						Student student = studentservice.getStudentById(studentId);
						student.setStudentId(studentId);
						student.setStudentStatus(2);// 设为未缴费
						student.setStatus(2);// 入学考试结束
						student.setSchoolId(schoolId);
						student.setUpdateTime(new Date());
						//修改校区
						tr1.setUpdateTime(new Date());
						tr1.setChoiceId(schoolId);
						testrecordservice.updateTotaiTime(tr1);
						int len1 = studentservice.updateStudentStatus(student);
						if (len1 > 0) {
							// 添加学生生涯记录
							// 做完考卷则清掉缓存
							mapcahe.removeCaseMap("statusPaperBy" + studentId + "and" + paperId);
							mapcahe.removeMap("pageNoPaper" + studentId + "and" + paperId);
							mapcahe.removeMap("statusPaper" + studentId + "and" + paperId);
							mapcahe.removeMap("state");
							List<TestRecord> list = new ArrayList<>();
							list.add(tr);
							output(response, JsonUtil.buildJson(list));
						} else {
							output(response, JsonUtil.builderFalseJson("1", "提交考卷失败!"));
						}
					} else {
						// 做完考卷则清掉缓存
						mapcahe.removeCaseMap("statusPaperBy" + studentId + "and" + paperId);
						mapcahe.removeMap("pageNoPaper" + studentId + "and" + paperId);
						mapcahe.removeMap("statusPaper" + studentId + "and" + paperId);
						mapcahe.removeMap("state");
						List<TestRecord> list = new ArrayList<>();
						list.add(tr);
						output(response, JsonUtil.buildJson(list));
					}
				} else {
					output(response, JsonUtil.builderFalseJson("1", "没有数据!"));
				}
			}
		}
	}

	/**
	 * 
	 * 查看试卷详情
	 */
	@RequestMapping("/seePaerDetails")
	public void seePaerDetails(HttpServletResponse response, Integer studentId, Integer paperId, Integer pageNo,
			Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 2000;
		}
		List<SeePaper> spList = new ArrayList<SeePaper>();
		
		// 获取单选题的列表
		List<QuestionSingleChoice> singledetail = qsChoiceService.thePapersById(paperId, 1, (pageNo - 1) * pageSize,
				pageSize);
		// 获取单选题我的答案列表
		List<QuestionSingleChoice> list1 = qsChoiceService.thePapersAnswerById(studentId, paperId, 1,
				(pageNo - 1) * pageSize, pageSize);
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
		List<QuestionMulitChoice> mulitdetail = qMulitChoiceService.thePapersById(paperId, 2, (pageNo - 1) * pageSize,
				pageSize);
		List<QuestionMulitChoice> list2 = qMulitChoiceService.thePapersAnswerById(studentId, paperId, 2,
				(pageNo - 1) * pageSize, pageSize);
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
		
		List<QuestionJudge> judgeDetail = qJudgeService.thePapersById(paperId, 3, (pageNo - 1) * pageSize, pageSize);
		List<QuestionJudge> list3 = qJudgeService.thePapersAnswerById(studentId, paperId, 3, (pageNo - 1) * pageSize,
				pageSize);
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
		List<QuestionRead> readDetail = qReadService.thePapersById(paperId, 4, (pageNo - 1) * pageSize, pageSize);
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
			List<QuestionRead> list4 = qReadService.getHomework_answerDetails(paperId, studentId,qr.getReadId());
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
		List<SubjectiveQuestion> list5 = questionService.thePaperDetailsById(studentId, paperId, 5,
				(pageNo - 1) * pageSize, pageSize);
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
		//拿到填空题
		List<QuestionFill> fillList = fillservice.thePapersById(paperId, 6,(pageNo - 1) * pageSize, pageSize);
		//判断是否提交答案
		List<QuestionFill> qfList = fillservice.thePapersAnswerById(studentId, paperId, 6, (pageNo - 1) * pageSize,
				pageSize);
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
						if (qFill.getFillId()== sq.getFillId()) {
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
		//完形填空内容
		List<QuestionCloze> cList = clozeService.thePapersById(paperId,7);
		if (cList != null && !cList.isEmpty()) {
			for (QuestionCloze cloze :cList) {
				SeePaper sp6 = new SeePaper();
				sp6.setQuestionId(cloze.getClozeId());
				sp6.setClozeContent(cloze.getClozeContent());
				List<QuestionCloze> clozes = clozeService.thePapersAnswerById(studentId,paperId,cloze.getClozeId());
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
		if (spList != null && !spList.isEmpty()) {
			output(response, JsonUtil.buildJson(spList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 
	 * 计算考试分值
	 * 
	 */
	@RequestMapping("/mypaperscore")
	public void myPaperScore(HttpServletResponse response, Integer studentId, Integer paperId, int type) {
		double score = 0.0;// 试卷总分
		double homeworScore = 0.0;// 作业总分
		double danxuan = 0.0;//单选分数
		double duoxuan = 0.0;//多选分数
		double pduan = 0.0;//判断分数
		double yuedu = 0.0;//阅读理解分数
		double fill = 0.0;//填空分数
		double clozeScore = 0.0;//完形填空
		if (type == 1) {
			TestRecord record2 = testrecordservice.getTestRecordByStuandPaper(studentId, paperId);
			List<PaperAnswer> paList = paperAnswerService.findPaperAnswerBy(studentId, paperId);
			if (paList != null && !paList.isEmpty()) {// 试卷考试
				 PaperInfo paInfo = paperinfoservice.getPaperInfoPiId(paperId);
				for (PaperAnswer pa : paList) {
					if (pa.getIsTrue() == 1) {
						score += pa.getValue();
					}
					if (pa.getQuestionType() == 1) {
						if (pa.getIsTrue() == 1) {
							danxuan += pa.getValue();
						}
					}
					if (pa.getQuestionType() == 2) {
						if (pa.getIsTrue() == 1) {
							duoxuan += pa.getValue();
						}
					}
					if (pa.getQuestionType() == 3) {
						if (pa.getIsTrue() == 1) {
							pduan += pa.getValue();
						}
					}
					if (pa.getQuestionType() == 4) {
						if (pa.getIsTrue() == 1) {
							yuedu += pa.getValue();
						}
					}
					if (pa.getQuestionType() == 6) {
						if (pa.getIsTrue() == 1) {
							fill += pa.getValue();
						}
					}
					if (pa.getQuestionType() == 7) {
						if (pa.getIsTrue() == 1) {
							clozeScore += pa.getValue();
						}
					}
				}
				record2.setScore(score);
				record2.setUpdateTime(new Date());
				if (record2.getTotalTime() == null || record2.getTotalTime() == 0) {
					record2.setTotalTime((long) 1);
				}
				if (paInfo != null) {
					record2.setWriteNumber(paInfo.getTopicTotai());
				}
				testrecordservice.updateScoreANDWriteNumber(record2);
				TestRecord tr = testrecordservice.getEntrance(studentId,paperId);
				//入学考试完成，计算考试科目的总分
				if ( tr != null && tr.getPaperType() ==4) {
					StudentScore score2 = scoreService.getScoreStudentById(studentId,paperId);
					if (score2 != null) {//修改
						score2.setCreateTime(new Date());
						score2.setScore(score);
						score2.setSchoolId(tr.getChoiceId());
						scoreService.updateScore(score2);
					} else {//新增
						score2 = new StudentScore();
						score2.setStudentId(studentId);
						score2.setCreateTime(new Date());
						score2.setGradeId(paInfo.getGrade());
						score2.setPaperId(paperId);
						if (score2.getScore() != null) {
							score2.setScore(score2.getScore()+score);
						}else {
							score2.setScore(score);
						}
						score2.setSchoolId(record2.getChoiceId());
						scoreService.addScore(score2);
					}
				}
				TestRecord record = testrecordservice.getTestRecordByStuandPaper(studentId, paperId);
				record.setMulitScore(duoxuan);
				record.setJudgeScore(pduan);
				record.setSingleScore(danxuan);
				record.setReadScore(yuedu);
				record.setFillScore(fill);
				record.setClozeScore(clozeScore);;
				record.setScore(score);
				if (record.getTotalTime() == null || record.getTotalTime() == 0) {
					record.setTotalTime((long) 1);
				}
				List<TestRecord> list = new ArrayList<>();
				list.add(record);
				if (!list.isEmpty() && list != null) {
					output(response, JsonUtil.buildJson(list));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "没有数据"));
				}
			}
		} else {
			List<HomeworkAnswer> haList = answerService.findHomeworkAnswerById(studentId, paperId);
			HomeworkRecord homeworkRecord = homeworkRecordService.getByStuIdandHomeId(studentId, paperId);
			if (haList != null && !haList.isEmpty()) {// 作业
				 Homework homework = homeworkService.getHomework(paperId);
				for (HomeworkAnswer ha : haList) {
					if (ha.getIsTrue() == 1) {
						homeworScore += ha.getValue();
					}
					if (ha.getQuestionType() == 1) {// 单选
						if (ha.getIsTrue() == 1) {
							danxuan += ha.getValue();
						}
					}
					if (ha.getQuestionType() == 2) {// 多选
						if (ha.getIsTrue() == 1) {
							duoxuan += ha.getValue();
						}
					}
					if (ha.getQuestionType() == 3) {// 判断
						if (ha.getIsTrue() == 1) {
							pduan += ha.getValue();
						}
					}
					if (ha.getQuestionType() == 4) {// 阅读理解
						if (ha.getIsTrue() == 1) {
							yuedu += ha.getValue();
						}
					}
					if (ha.getQuestionType() == 6) {//填空
						if (ha.getIsTrue() == 1) {
							fill += ha.getValue();
						}
					}
					if (ha.getQuestionType() == 7) {//填空
						if (ha.getIsTrue() == 1) {
							clozeScore += ha.getValue();
						}
					}
				}
				homeworkRecord.setScore(homeworScore);
				homeworkRecord.setUpdateTime(new Date());
				if (homework != null) {
					homeworkRecord.setWriteNumber(homework.getHkTotal());
				}
				if (homeworkRecord.getTotlTime() == null || homeworkRecord.getTotlTime() == 0) {
					homeworkRecord.setTotlTime(1);
				}
				homeworkRecordService.updateScoreByStuIdandHomId(homeworkRecord);
				HomeworkRecord hRecord = homeworkRecordService.getByStuIdandHomeId(studentId, paperId);
				hRecord.setMulitScore(duoxuan);
				hRecord.setJudgeScore(pduan);
				hRecord.setSingleScore(danxuan);
				hRecord.setReadScore(yuedu);
				hRecord.setClozeScore(clozeScore);
				hRecord.setScore(homeworScore);
				hRecord.setFillScore(fill);
				if (hRecord.getTotlTime() == null || hRecord.getTotlTime() == 0) {
					hRecord.setTotlTime(1);
				}
				List<HomeworkRecord> list = new ArrayList<>();
				list.add(hRecord);
				if (!list.isEmpty() && list != null) {
					output(response, JsonUtil.buildJson(list));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "没有数据"));
				}
			}
		}
	}

	@RequestMapping("/getpapertotal")
	public void getPaperTotal(HttpServletResponse response, Integer paperId) {
		PaperInfo info = paperinfoservice.getPaperInfoById(paperId);
		output(response, JsonUtil.buildFalseJson("0", String.valueOf(info.getTopicTotai())));
	}

	//检测是否做个该入学考试
	@RequestMapping("/isExamination")
	private void testingExamination(Integer studentId, Integer paperId, HttpServletResponse response,Integer schoolId) {
		TestRecord trList = testrecordservice.isWriteMessage(studentId, paperId,null);
		if (trList != null && trList.getStatus() == 3) {
			output(response, JsonUtil.buildFalseJson("1", "系统检测到你已做个该考试!"));
		} else {
			output(response, JsonUtil.buildFalseJson("0", "可以安排考试!"));
		}
	}
}
