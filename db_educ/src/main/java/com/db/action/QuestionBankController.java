package com.db.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.db.entity.ClassNo;
import com.db.entity.Grade;
import com.db.entity.QuestionCloze;
import com.db.entity.QuestionFill;
import com.db.entity.QuestionFillType;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.QuestionSource;
import com.db.entity.QuestionTag;
import com.db.entity.Questions;
import com.db.entity.QuestionsType;
import com.db.entity.RangeLesson;
import com.db.entity.Subject;
import com.db.entity.SubjectiveQuestion;
import com.db.entity.Teacher;
import com.db.entity.TeacherAuthority;
import com.db.service.ClassNoService;
import com.db.service.GradeService;
import com.db.service.LogService;
import com.db.service.QuestionBankService;
import com.db.service.QuestionClozeService;
import com.db.service.QuestionFillService;
import com.db.service.QuestionFillTypeService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.QuestionSourceService;
import com.db.service.QuestionTagService;
import com.db.service.QuestionsTypeService;
import com.db.service.RangeLessonService;
import com.db.service.SubjectService;
import com.db.service.SubjectiveQuestionService;
import com.db.service.TeacherAuthorityService;
import com.db.util.BaseUtil;
import com.db.util.CommonUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;
import com.db.util.ResultData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("/questionBank")
public class QuestionBankController extends BaseUtil {

	@Resource
	private QuestionJudgeService judgeService;
	@Resource
	private QuestionMulitChoiceService mulitchoiceService;
	@Resource
	private QuestionSingleChoiceService singlechoiceService;
	@Resource
	private QuestionReadService readService;
	@Resource
	private QuestionBankService qbService;
	@Resource
	private GradeService gradeService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private QuestionsTypeService qtypeService;
	@Resource
	private QuestionSourceService QuestionSourceService;
	@Resource
	private ClassNoService classNoService;
	@Resource
	private QuestionTagService questionTagService;

	private HttpSession session;
	@Resource
	private QuestionsTypeService questionsTypeService;
	@Resource
	private TeacherAuthorityService authorityService;
	@Resource
	private RangeLessonService rangeLessonService;
	@Resource
	private LogService logService;
	@Resource
	private SubjectiveQuestionService questionService;
	@Resource
	private QuestionFillService fillService;
	@Resource
	private QuestionClozeService clozeService;
	@Resource
	private QuestionFillTypeService questionFillTypeService;
	
	private Logger log = Logger.getLogger(QuestionBankController.class);

	/**
	 * 我的题库列表
	 * 
	 * @return
	 */
	@RequestMapping("/myquestionbank")
	public ModelAndView myQuestionBank(HttpServletRequest request, Integer pageNo, Integer pageSize,
			Integer handoutType, Integer subject, Integer gradeId, Integer questionDegree, Integer isPublic) {
		if (handoutType != null) {
			if (handoutType == 3 || handoutType == 5 || handoutType == 6) {
				if (pageNo == null) {
					pageNo = 1;
				}
				if (pageSize == null) {
					pageSize = 20;
				}
			}
		}
		if (handoutType == null) {// 默认单选
			handoutType = 1;
		}
		if (handoutType == 1 || handoutType == 2 || handoutType == 4 || handoutType == 7) {
			if (pageNo == null) {
				pageNo = 1;
			}
			if (pageSize == null) {
				pageSize = 10;
			}
		}
		int worsCount = 0;
		int totalPages = 0;
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		
		//获取年级集合
		List<Subject> sList = null;
		List<Grade> grades = null;
		if (!teacher.getTeacherType().equals("5") && !teacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			grades = gradeService.getGrade();
		} else {
			sList = subjectService.getSubjectById(teacher.getSubject());
			String[] my = teacher.getGrade().split(",");
			List gradeList = Arrays.asList(my);
			grades = gradeService.getGradeByIds(gradeList);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<TeacherAuthority> taList = authorityService.getTeacherIdById(teacher.getTeacherId());
		if (!teacher.getTeacherType().equals("5") 
				&& !teacher.getTeacherType().equals("6")
				&& !teacher.getTeacherType().equals("23")) {// 超级管理员能看到所有的
			if (handoutType == 1) {
				worsCount = singlechoiceService.getAllQuestionsCount(handoutType, subject, questionDegree, isPublic,
						null, gradeId);
				List<QuestionSingleChoice> qscList = singlechoiceService.getAllQuestions((pageNo - 1) * pageSize,
						pageSize, handoutType, subject, questionDegree, isPublic, null, gradeId);
				if (!qscList.isEmpty() && qscList != null) {
					map.put("singleList", qscList);
				} else {
					map.put("singleList", null);
				}
			} else if (handoutType == 2) {
				worsCount = mulitchoiceService.getAllQuestionsCount(handoutType, subject, questionDegree, isPublic,
						null, gradeId);
				List<QuestionMulitChoice> qmcList = mulitchoiceService.getAllQuestions((pageNo - 1) * pageSize,
						pageSize, handoutType, subject, questionDegree, isPublic, null, gradeId);
				if (!qmcList.isEmpty() && qmcList != null) {
					map.put("mulitList", qmcList);
				} else {
					map.put("mulitList", null);
				}
			} else if (handoutType == 3) {
				worsCount = judgeService.getAllQuestionsCount(handoutType, subject, questionDegree, isPublic, null,
						gradeId);
				List<QuestionJudge> qjList = judgeService.getAllQuestions((pageNo - 1) * pageSize, pageSize,
						handoutType, subject, questionDegree, isPublic, null, gradeId);
				if (!qjList.isEmpty() && qjList != null) {
					map.put("judgeList", qjList);
				} else {
					map.put("judgeList", null);
				}
			} else if (handoutType == 4) {
				worsCount = readService.getAllQuestionsCount(handoutType, subject, questionDegree, isPublic, null,
						gradeId);
				List<QuestionRead> reads = readService.getAllQuestions((pageNo - 1) * pageSize, pageSize, handoutType,
						subject, questionDegree, isPublic, null, gradeId);
				for (QuestionRead qr : reads) {
					qr.setQjList(readService.getReadChildByParentId(qr.getReadId()));
					// 获取每到小题题目
					List<QuestionRead> qrList = readService.getReadChildByParentId(qr.getReadId());
					mv.addObject("qrList", qrList);
				}
				if (!reads.isEmpty() && reads != null) {
					map.put("readList", reads);
				} else {
					map.put("readList", null);
				}
			} else if (handoutType == 5) {// 主观题
				worsCount = questionService.getAllQuestionsCount(handoutType, subject, questionDegree, isPublic, null,
						gradeId);
				List<SubjectiveQuestion> sqList = questionService.getSubjectiveQuestion((pageNo - 1) * pageSize,
						pageSize, handoutType, subject, questionDegree, isPublic, null, gradeId);
				if (sqList != null && !sqList.isEmpty()) {
					map.put("sqList", sqList);
				} else {
					map.put("sqList", null);
				}
			} else if (handoutType == 6) {// 填空题
				List<QuestionFill> qfList = fillService.QuestionFillList((pageNo - 1) * pageSize, pageSize, handoutType,
						subject, questionDegree, isPublic, null, gradeId);
				worsCount = fillService.getFillCount(handoutType, subject, questionDegree, isPublic, null, gradeId);
				if (qfList != null && !qfList.isEmpty()) {
					map.put("qfList", qfList);
				} else {
					map.put("qfList", null);
				}
			} else if (handoutType == 7) {// 完形填空
				map.put("pageNo", (pageNo - 1) * pageSize);
				map.put("pageSize", pageSize);
				map.put("subject", subject);
				map.put("isPublic", isPublic);
				map.put("gradeId", gradeId);
				List<QuestionCloze> clozes = clozeService.getQuestionClozeList(map);
				worsCount = clozeService.getQuestionClozeCount(map);
				if (clozes != null && !clozes.isEmpty()) {
					map.put("clozeList", clozes);
				} else {
					map.put("clozes", null);
				}
			}
		} else {// 只能看到自己的和别人公开的
			for (TeacherAuthority ta : taList) {
				String gradeIds = teacher.getGrade();
//				log.info("grade :" + gradeIds);
				List<String> idlist  = Arrays.asList(gradeIds.split(","));
				List returnList = new ArrayList();  
				if (ta.getQuestionsAuthority() == 1 || ta.getQuestionsAuthority() == 3) {
					// 能读所有公开的题目
					if (handoutType == 1) {
						worsCount = singlechoiceService.getconutIspublicbyIds(handoutType, teacher.getSubject(),
								questionDegree, isPublic, teacher.getTeacherId(), null, grades);
						List<QuestionSingleChoice> singleChoices = singlechoiceService.getSingleByTeacherIdIsPublicbyIds(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), questionDegree, isPublic, null, grades);// 获取单选题
						if (!singleChoices.isEmpty() && singleChoices != null) {
							
//							//去掉不是自己年级的
//							for(QuestionSingleChoice Single :singleChoices  ){
//								String id =  String.valueOf(Single.getKnowledge());
//								for(String gid : idlist){
//									if(id.equals(gid)){
//									returnList.add(Single);
//									}
//							    }
//							}
//							singleChoices = returnList;
							
							map.put("singleList", singleChoices);
						} else {
							map.put("singleList", null);
						}
					} else if (handoutType == 2) {
						worsCount = mulitchoiceService.getconutIspublicbyids(handoutType, teacher.getSubject(),
								questionDegree, isPublic, teacher.getTeacherId(), null, grades);
						List<QuestionMulitChoice> mulitChoices = mulitchoiceService.getMulitByTeacherIdIsPublicbyids(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), questionDegree, isPublic, null, grades);// 获取多选题
						if (!mulitChoices.isEmpty() && mulitChoices != null) {

							map.put("mulitList", mulitChoices);
						} else {
							map.put("mulitList", null);
						}
					} else if (handoutType == 3) {
						worsCount = judgeService.getconutIspublicbyids(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, grades);
						List<QuestionJudge> judges = judgeService.getJudgeByTeacherIdIsPublicbyids(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, null, grades);// 获取判断题
						if (!judges.isEmpty() && judges != null) {
							
							map.put("judgeList", judges);
						} else {
							map.put("judgeList", null);
						}
					} else if (handoutType == 4) {
						worsCount = readService.getconutIspublicbyids(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, grades);
						List<QuestionRead> reads = readService.getReadByTeacherIdIsPublicbyids(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, null, grades);// 获取阅读理解
						for (QuestionRead qr : reads) {
							// 获取每到小题题目
							qr.setQjList(readService.getReadChildByParentId(qr.getReadId()));
							// mv.addObject("qrList", qr.getQjList());
						}
						if (!reads.isEmpty() && reads != null) {
							
							map.put("readList", reads);
						} else {
							map.put("readList", null);
						}
					} else if (handoutType == 5) {// 主观题

						List<SubjectiveQuestion> sqList = questionService.getPublicSubjectiveQuestionbyids(
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, grades);
						worsCount = questionService.getIsPublicCountbyids(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, grades);
						if (sqList != null && !sqList.isEmpty()) {

							map.put("sqList", sqList);
						} else {
							map.put("sqList", null);
						}
					} else if (handoutType == 6) {// 填空题
						List<QuestionFill> qfList = fillService.getPublicQuestionFillListbyids((pageNo - 1) * pageSize,
								pageSize, handoutType, teacher.getSubject(), questionDegree, isPublic,
								teacher.getTeacherId(), null, grades);
						worsCount = fillService.getIsPublicCountbyids(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, grades);
						if (qfList != null && !qfList.isEmpty()) {
	
							map.put("qfList", qfList);
						} else {
							map.put("qfList", null);
						}
					} else if (handoutType == 7) {
						map.put("pageNo", (pageNo - 1) * pageSize);
						map.put("pageSize", pageSize);
						map.put("subject", teacher.getSubject());
						map.put("isPublic", isPublic);
						map.put("gradeId", gradeId);
						map.put("teacherId", teacher.getTeacherId());
						map.put("type", 1);
						List<QuestionCloze> clozes = clozeService.getIsPublicClozeList(map);
						worsCount = clozeService.getIsPublicClozeCount(map);
						if (clozes != null && !clozes.isEmpty()) {
						
							map.put("clozeList", clozes);
						} else {
							map.put("clozes", null);
						}
					}
				} else {
					// 只能读自己所有的题目
					if (handoutType == 1) {
						worsCount = singlechoiceService.getconut(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						List<QuestionSingleChoice> singleChoices = singlechoiceService.getSingleByTeacherId(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), questionDegree, isPublic, null, gradeId);// 获取单选题
						if (!singleChoices.isEmpty() && singleChoices != null) {
							map.put("singleList", singleChoices);
						} else {
							map.put("singleList", null);
						}
					} else if (handoutType == 2) {
						worsCount = mulitchoiceService.getcount(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						List<QuestionMulitChoice> mulitChoices = mulitchoiceService.getMulitByTeacherId(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), questionDegree, isPublic, null, gradeId);// 获取多选题
						if (!mulitChoices.isEmpty() && mulitChoices != null) {
							map.put("mulitList", mulitChoices);
						} else {
							map.put("mulitList", null);
						}
					} else if (handoutType == 3) {
						worsCount = judgeService.getcount(handoutType, teacher.getSubject(), questionDegree, isPublic,
								teacher.getTeacherId(), null, gradeId);
						List<QuestionJudge> judges = judgeService.getJudgeByTeacherId(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, null, gradeId);// 获取判断题
						if (!judges.isEmpty() && judges != null) {
							map.put("judgeList", judges);
						} else {
							map.put("judgeList", null);
						}
					} else if (handoutType == 4) {
						worsCount = readService.getcount(handoutType, teacher.getSubject(), questionDegree, isPublic,
								teacher.getTeacherId(), null, gradeId);
						List<QuestionRead> reads = readService.getReadByTeacherId(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, null, gradeId);// 获取阅读理解
						for (QuestionRead qr : reads) {
							// 获取每到小题题目
							List<QuestionRead> qrList = readService.getReadChildByParentId(qr.getReadId());
							mv.addObject("qrList", qrList);
						}
						if (!reads.isEmpty() && reads != null) {
							map.put("readList", reads);
						} else {
							map.put("readList", null);
						}
					} else if (handoutType == 5) {// 主观题
						worsCount = questionService.getPrivateCount(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						List<SubjectiveQuestion> sqList = questionService.getPrivateSubjectiveQuestion(
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						if (sqList != null && !sqList.isEmpty()) {
							map.put("sqList", sqList);
						} else {
							map.put("sqList", null);
						}
					} else if (handoutType == 6) {// 填空题
						List<QuestionFill> qfList = fillService.getPrivateQuestionFillList((pageNo - 1) * pageSize,
								pageSize, handoutType, teacher.getSubject(), questionDegree, isPublic,
								teacher.getTeacherId(), null, gradeId);
						worsCount = fillService.getPrivateFillCount(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						if (qfList != null && !qfList.isEmpty()) {
							map.put("qfList", qfList);
						} else {
							map.put("qfList", null);
						}
					} else if (handoutType == 7) {
						map.put("pageNo", (pageNo - 1) * pageSize);
						map.put("pageSize", pageSize);
						map.put("subject", teacher.getSubject());
						map.put("isPublic", isPublic);
						map.put("gradeId", gradeId);
						map.put("teacherId", teacher.getTeacherId());
						map.put("type", 2);
						List<QuestionCloze> clozes = clozeService.getIsPublicClozeList(map);
						worsCount = clozeService.getIsPublicClozeCount(map);
						if (clozes != null && !clozes.isEmpty()) {
							map.put("clozeList", clozes);
						} else {
							map.put("clozes", null);
						}
					}
				}
			}
		}
		if (worsCount % pageSize == 0) {
			totalPages = worsCount / pageSize;
		} else {
			totalPages = (worsCount / pageSize) + 1;
		}
		if (map.get("readList") == null && map.get("judgeList") == null && map.get("mulitList") == null
				&& map.get("singleList") == null && map.get("sqList") == null && map.get("qfList") == null
				&& map.get("clozeList") == null) {
			mv.addObject("questionList", null);
		} else {
			mv.addObject("questionList", map);
		}
	
		mv.addObject("sList", sList);
		List<QuestionsType> qtList = questionsTypeService.questionsType();
		mv.addObject("qtList", qtList);
		mv.addObject("gradeList", grades);
		mv.addObject("page", pageNo);
		mv.addObject("gradeId", gradeId);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("handoutType", handoutType);
		mv.addObject("isPublic", isPublic);
		mv.addObject("subject", subject);
		mv.addObject("questionDegree", questionDegree);
		mv.setViewName("questionbank/myquestionbank");
		return mv;
	}

	/**
	 * 获取题目
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @param handoutType 题型
	 * @param subject 科目
	 * @param gradeId 年级
	 * @param questionDegree
	 * @param isPublic
	 * @return
	 */
	@RequestMapping("/getquestionbank")
	public void getquestionbank(HttpServletRequest request, HttpServletResponse response,Integer pageNo, Integer pageSize,
			Integer handoutType, Integer subject, Integer gradeId, Integer questionDegree, Integer isPublic) {
	
		if (handoutType != null) {
			if (handoutType == 3 || handoutType == 5 || handoutType == 6) {
				if (pageNo == null) {
					pageNo = 1;
				}
				if (pageSize == null) {
					pageSize = 10;
				}
			}
		}
		if (handoutType == null) {// 默认单选
			handoutType = 1;
		}
		if (handoutType == 1 || handoutType == 2 || handoutType == 4 || handoutType == 7) {
			if (pageNo == null) {
				pageNo = 1;
			}
			if (pageSize == null) {
				pageSize = 10;
			}
		}
		int worsCount = 0;
		int totalPages = 0;
		Map<String,Object> mv = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		
		List<Subject> sList = null;
		List<Grade> grades = null;
		if (!teacher.getTeacherType().equals("5") && !teacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			grades = gradeService.getGrade();
		} else {
			sList = subjectService.getSubjectById(teacher.getSubject());
			String[] my = teacher.getGrade().split(",");
			List gradeList = Arrays.asList(my);
			grades = gradeService.getGradeByIds(gradeList);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<TeacherAuthority> taList = authorityService.getTeacherIdById(teacher.getTeacherId());
		if (!teacher.getTeacherType().equals("5") && !teacher.getTeacherType().equals("6")) {// 超级管理员能看到所有的
			if (handoutType == 1) {
				worsCount = singlechoiceService.getAllQuestionsCount(handoutType, subject, questionDegree, isPublic,
						null, gradeId);
				List<QuestionSingleChoice> qscList = singlechoiceService.getAllQuestions((pageNo - 1) * pageSize,
						pageSize, handoutType, subject, questionDegree, isPublic, null, gradeId);
				if (!qscList.isEmpty() && qscList != null) {
					map.put("singleList", qscList);
				} else {
					map.put("singleList", null);
				}
			} else if (handoutType == 2) {
				worsCount = mulitchoiceService.getAllQuestionsCount(handoutType, subject, questionDegree, isPublic,
						null, gradeId);
				List<QuestionMulitChoice> qmcList = mulitchoiceService.getAllQuestions((pageNo - 1) * pageSize,
						pageSize, handoutType, subject, questionDegree, isPublic, null, gradeId);
				if (!qmcList.isEmpty() && qmcList != null) {
					map.put("mulitList", qmcList);
				} else {
					map.put("mulitList", null);
				}
			} else if (handoutType == 3) {
				worsCount = judgeService.getAllQuestionsCount(handoutType, subject, questionDegree, isPublic, null,
						gradeId);
				List<QuestionJudge> qjList = judgeService.getAllQuestions((pageNo - 1) * pageSize, pageSize,
						handoutType, subject, questionDegree, isPublic, null, gradeId);
				if (!qjList.isEmpty() && qjList != null) {
					map.put("judgeList", qjList);
				} else {
					map.put("judgeList", null);
				}
			} else if (handoutType == 4) {
				worsCount = readService.getAllQuestionsCount(handoutType, subject, questionDegree, isPublic, null,
						gradeId);
				List<QuestionRead> reads = readService.getAllQuestions((pageNo - 1) * pageSize, pageSize, handoutType,
						subject, questionDegree, isPublic, null, gradeId);
				for (QuestionRead qr : reads) {
					qr.setQjList(readService.getReadChildByParentId(qr.getReadId()));
					// 获取每到小题题目
					List<QuestionRead> qrList = readService.getReadChildByParentId(qr.getReadId());
					mv.put("qrList", qrList);
				}
				if (!reads.isEmpty() && reads != null) {
					map.put("readList", reads);
				} else {
					map.put("readList", null);
				}
			} else if (handoutType == 5) {// 主观题
				worsCount = questionService.getAllQuestionsCount(handoutType, subject, questionDegree, isPublic, null,
						gradeId);
				List<SubjectiveQuestion> sqList = questionService.getSubjectiveQuestion((pageNo - 1) * pageSize,
						pageSize, handoutType, subject, questionDegree, isPublic, null, gradeId);
				if (sqList != null && !sqList.isEmpty()) {
					map.put("sqList", sqList);
				} else {
					map.put("sqList", null);
				}
			} else if (handoutType == 6) {// 填空题
				List<QuestionFill> qfList = fillService.QuestionFillList((pageNo - 1) * pageSize, pageSize, handoutType,
						subject, questionDegree, isPublic, null, gradeId);
				worsCount = fillService.getFillCount(handoutType, subject, questionDegree, isPublic, null, gradeId);
				if (qfList != null && !qfList.isEmpty()) {
					map.put("qfList", qfList);
				} else {
					map.put("qfList", null);
				}
			} else if (handoutType == 7) {// 完形填空
				map.put("pageNo", (pageNo - 1) * pageSize);
				map.put("pageSize", pageSize);
				map.put("subject", subject);
				map.put("isPublic", isPublic);
				map.put("gradeId", gradeId);
				List<QuestionCloze> clozes = clozeService.getQuestionClozeList(map);
				worsCount = clozeService.getQuestionClozeCount(map);
				if (clozes != null && !clozes.isEmpty()) {
					map.put("clozeList", clozes);
				} else {
					map.put("clozes", null);
				}
			}
		} else {// 只能看到自己的和别人公开的
			for (TeacherAuthority ta : taList) {
				if (ta.getQuestionsAuthority() == 1 || ta.getQuestionsAuthority() == 3) {
					// 能读所有公开的题目
					if (handoutType == 1) {
						worsCount = singlechoiceService.getconutIspublic(handoutType, teacher.getSubject(),
								questionDegree, isPublic, teacher.getTeacherId(), null, gradeId);
						List<QuestionSingleChoice> singleChoices = singlechoiceService.getSingleByTeacherIdIsPublic(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), questionDegree, isPublic, null, gradeId);// 获取单选题
						if (!singleChoices.isEmpty() && singleChoices != null) {
							map.put("singleList", singleChoices);
						} else {
							map.put("singleList", null);
						}
					} else if (handoutType == 2) {
						worsCount = mulitchoiceService.getconutIspublic(handoutType, teacher.getSubject(),
								questionDegree, isPublic, teacher.getTeacherId(), null, gradeId);
						List<QuestionMulitChoice> mulitChoices = mulitchoiceService.getMulitByTeacherIdIsPublic(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), questionDegree, isPublic, null, gradeId);// 获取多选题
						if (!mulitChoices.isEmpty() && mulitChoices != null) {
							map.put("mulitList", mulitChoices);
						} else {
							map.put("mulitList", null);
						}
					} else if (handoutType == 3) {
						worsCount = judgeService.getconutIspublic(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						List<QuestionJudge> judges = judgeService.getJudgeByTeacherIdIsPublic(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, null, gradeId);// 获取判断题
						if (!judges.isEmpty() && judges != null) {
							map.put("judgeList", judges);
						} else {
							map.put("judgeList", null);
						}
					} else if (handoutType == 4) {
						worsCount = readService.getconutIspublic(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						List<QuestionRead> reads = readService.getReadByTeacherIdIsPublic(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, null, gradeId);// 获取阅读理解
						for (QuestionRead qr : reads) {
							// 获取每到小题题目
							qr.setQjList(readService.getReadChildByParentId(qr.getReadId()));
							// mv.addObject("qrList", qr.getQjList());
						}
						if (!reads.isEmpty() && reads != null) {
							map.put("readList", reads);
						} else {
							map.put("readList", null);
						}
					} else if (handoutType == 5) {// 主观题

						List<SubjectiveQuestion> sqList = questionService.getPublicSubjectiveQuestion(
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						worsCount = questionService.getIsPublicCount(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						if (sqList != null && !sqList.isEmpty()) {
							map.put("sqList", sqList);
						} else {
							map.put("sqList", null);
						}
					} else if (handoutType == 6) {// 填空题
						List<QuestionFill> qfList = fillService.getPublicQuestionFillList((pageNo - 1) * pageSize,
								pageSize, handoutType, teacher.getSubject(), questionDegree, isPublic,
								teacher.getTeacherId(), null, gradeId);
						worsCount = fillService.getIsPublicCount(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						if (qfList != null && !qfList.isEmpty()) {
							map.put("qfList", qfList);
						} else {
							map.put("qfList", null);
						}
					} else if (handoutType == 7) {
						map.put("pageNo", (pageNo - 1) * pageSize);
						map.put("pageSize", pageSize);
						map.put("subject", teacher.getSubject());
						map.put("isPublic", isPublic);
						map.put("gradeId", gradeId);
						map.put("teacherId", teacher.getTeacherId());
						map.put("type", 1);
						List<QuestionCloze> clozes = clozeService.getIsPublicClozeList(map);
						worsCount = clozeService.getIsPublicClozeCount(map);
						if (clozes != null && !clozes.isEmpty()) {
							map.put("clozeList", clozes);
						} else {
							map.put("clozes", null);
						}
					}
				} else {
					// 只能读自己所有的题目
					if (handoutType == 1) {
						worsCount = singlechoiceService.getconut(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						List<QuestionSingleChoice> singleChoices = singlechoiceService.getSingleByTeacherId(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), questionDegree, isPublic, null, gradeId);// 获取单选题
						if (!singleChoices.isEmpty() && singleChoices != null) {
							map.put("singleList", singleChoices);
						} else {
							map.put("singleList", null);
						}
					} else if (handoutType == 2) {
						worsCount = mulitchoiceService.getcount(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						List<QuestionMulitChoice> mulitChoices = mulitchoiceService.getMulitByTeacherId(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), questionDegree, isPublic, null, gradeId);// 获取多选题
						if (!mulitChoices.isEmpty() && mulitChoices != null) {
							map.put("mulitList", mulitChoices);
						} else {
							map.put("mulitList", null);
						}
					} else if (handoutType == 3) {
						worsCount = judgeService.getcount(handoutType, teacher.getSubject(), questionDegree, isPublic,
								teacher.getTeacherId(), null, gradeId);
						List<QuestionJudge> judges = judgeService.getJudgeByTeacherId(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, null, gradeId);// 获取判断题
						if (!judges.isEmpty() && judges != null) {
							map.put("judgeList", judges);
						} else {
							map.put("judgeList", null);
						}
					} else if (handoutType == 4) {
						worsCount = readService.getcount(handoutType, teacher.getSubject(), questionDegree, isPublic,
								teacher.getTeacherId(), null, gradeId);
						List<QuestionRead> reads = readService.getReadByTeacherId(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, null, gradeId);// 获取阅读理解
						for (QuestionRead qr : reads) {
							// 获取每到小题题目
							List<QuestionRead> qrList = readService.getReadChildByParentId(qr.getReadId());
							mv.put("qrList", qrList);
						}
						if (!reads.isEmpty() && reads != null) {
							map.put("readList", reads);
						} else {
							map.put("readList", null);
						}
					} else if (handoutType == 5) {// 主观题
						worsCount = questionService.getPrivateCount(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						List<SubjectiveQuestion> sqList = questionService.getPrivateSubjectiveQuestion(
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						if (sqList != null && !sqList.isEmpty()) {
							map.put("sqList", sqList);
						} else {
							map.put("sqList", null);
						}
					} else if (handoutType == 6) {// 填空题
						List<QuestionFill> qfList = fillService.getPrivateQuestionFillList((pageNo - 1) * pageSize,
								pageSize, handoutType, teacher.getSubject(), questionDegree, isPublic,
								teacher.getTeacherId(), null, gradeId);
						worsCount = fillService.getPrivateFillCount(handoutType, teacher.getSubject(), questionDegree,
								isPublic, teacher.getTeacherId(), null, gradeId);
						if (qfList != null && !qfList.isEmpty()) {
							map.put("qfList", qfList);
						} else {
							map.put("qfList", null);
						}
					} else if (handoutType == 7) {
						map.put("pageNo", (pageNo - 1) * pageSize);
						map.put("pageSize", pageSize);
						map.put("subject", teacher.getSubject());
						map.put("isPublic", isPublic);
						map.put("gradeId", gradeId);
						map.put("teacherId", teacher.getTeacherId());
						map.put("type", 2);
						List<QuestionCloze> clozes = clozeService.getIsPublicClozeList(map);
						worsCount = clozeService.getIsPublicClozeCount(map);
						if (clozes != null && !clozes.isEmpty()) {
							map.put("clozeList", clozes);
						} else {
							map.put("clozes", null);
						}
					}
				}
			}
		}
		if (worsCount % pageSize == 0) {
			totalPages = worsCount / pageSize;
		} else {
			totalPages = (worsCount / pageSize) + 1;
		}
		map.put("handoutType", handoutType);
		if (map.get("readList") == null && map.get("judgeList") == null && map.get("mulitList") == null
				&& map.get("singleList") == null && map.get("sqList") == null && map.get("qfList") == null
				&& map.get("clozeList") == null) {
			mv.put("questionList", null);
		} else {
			mv.put("questionList", map);
		}
	
		mv.put("sList", sList);
		List<QuestionsType> qtList = questionsTypeService.questionsType();
		mv.put("qtList", qtList);
		mv.put("gradeList", grades);
		mv.put("page", pageNo);
		mv.put("gradeId", gradeId);
		mv.put("pageSize", pageSize);
		mv.put("totalPages", totalPages);
		mv.put("handoutType", handoutType);
		mv.put("isPublic", isPublic);
		mv.put("subject", subject);
		mv.put("questionDegree", questionDegree);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>() ;
		list.add(mv);
		int totalcount = questionTagService.getCount("", subject, gradeId, null);
		PageUtil pageUtil = new PageUtil(totalcount, pageNo, pageSize);
		output(response, JsonUtil.buildJsonByTotalCount(list, pageUtil.getTotalPageCount()));
	}
		
	
	/**
	 * 录入试题页面
	 * 
	 * @return
	 */
	@RequestMapping("/createquestion")
	public String CreateQuestion(Model model, HttpServletRequest request, Integer pageNo, Integer pageSize,
			HttpServletResponse response) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 1;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<Subject> sList = null;
		List<Grade> gList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			gList = gradeService.getGrade();
		} else {
			sList = subjectService.getTeacherIsSubject(currTeacher.getSubject());
			String[] gStrings = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(gStrings);
			gList = gradeService.getGradeByIds(gradeList);
		}
		//填空题类型
		List<QuestionFillType> typeList = questionFillTypeService.getQuestionFillTypeList();
		model.addAttribute("typeList", typeList);
		model.addAttribute("sList", sList);
		// 循环拿到年级
		model.addAttribute("gList", gList);
		List<ClassNo> cList = classNoService.getAllClasses(currTeacher.getCampus(), (pageNo - 1) * pageSize, pageSize);
		model.addAttribute("cList", cList);
		// 循环拿到题型
		List<QuestionsType> qtList = qtypeService.questionsType();
		model.addAttribute("qtList", qtList);
		// 循环拿到题目来源
		List<QuestionSource> qsList = QuestionSourceService.questionSource();
		model.addAttribute("qsList", qsList);
		List<RangeLesson> lessons = rangeLessonService.getLessonList();
		model.addAttribute("lessonList", lessons);
		return "questionbank/createquestion";
	}

	/**
	 * 添加判断题
	 * 
	 * @param request
	 * @param judge
	 * @return
	 */
	@RequestMapping(value = "/addJudge")
	public ModelAndView addJudge(HttpServletRequest request, QuestionJudge judge, HttpServletResponse response,
			Model model, Integer pageNo, Integer pageSize) {
		
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
//		String title = BaseUtil.guoHtml(judge.getJudgeDesc());//过滤标签
		QuestionJudge judge3 = judgeService.getQuestionJudgeTitle(judge.getJudgeDesc());
		if (judge3 == null) {
//			System.out.println("拿到的分数：" + judge.getScore2());
			if (judge.getKnowledgeBegin() != null && !judge.getKnowledgeBegin().equals("")) {
				judge.setKnowledgeBegin(judge.getKnowledgeBegin());
			}
			if (judge.getKnowledgeEnd() != null && !judge.getKnowledgeEnd().equals("")) {
				judge.setKnowledgeEnd(judge.getKnowledgeEnd());
			}
			judge.setIspublic2(0);// 默认是私有
			if (judge.getScore2() != null) {
				judge.setScore2(Float.valueOf(judge.getScore2()));
			}
			judge.setCreateTime(new Date());
			judge.setTag2("," + judge.getTag2() + ",");
			judge.setTeacherId(teacher.getTeacherId());
			judgeService.addQuestionJudge(judge);// 返回主键id
			QuestionJudge judge2 = judgeService.getJudgeById(judge.getJudgeId());
			if (judge2 == null) {
				log.info("添加失败!");
			} else {
				log.info("添加成功!");
			}
		}
		Integer handoutType = 3;
		Integer subject = null;
		Integer questionDegree = null;
		Integer isPublic = null;
		Integer gradeId = null;
		return this.myQuestionBank(request, pageNo, pageSize, handoutType, subject, gradeId, questionDegree, isPublic);
	}

	/**
	 * 修改判断题
	 * 
	 * @param request
	 * @param judge
	 * @return
	 */
	@RequestMapping(value = "/updateJudge")
	public ModelAndView updateJudge(HttpServletRequest request, QuestionJudge judge, HttpServletResponse response,
			Model model, Integer pageNo, Integer pageSize) {
		// HttpSession session = request.getSession();
		// Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Integer handoutType = 3;
		Integer subject = null;
		Integer questionDegree = null;
		Integer isPublic = null;
		Integer gradeId = null;
		judge.setUpdateTime(new Date());
		int len = judgeService.updateQuestion(judge);
		if (len > 0) {

			log.info("修改成功!");
		} else {

			log.info("修改 失败!");
		}
		return this.myQuestionBank(request, pageNo, pageSize, handoutType, subject, gradeId, questionDegree, isPublic);
	}
	
	
	//ajax修改判断题
	@RequestMapping("/modityJudge")
	public void modityJudge(HttpServletResponse response,QuestionJudge judge){
		judge.setUpdateTime(new Date());
		int len = judgeService.updateQuestion(judge);
		output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
	}

	/**
	 * 添加多选题
	 * 
	 * @param request
	 * @param choice
	 * @param model
	 * @return
	 */
	@RequestMapping("/addMulitChoice")
	public ModelAndView addMulitChoice(HttpServletRequest request, QuestionMulitChoice choice,
			HttpServletResponse response, Model model, Integer pageNo, Integer pageSize) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		QuestionMulitChoice mulitChoice = mulitchoiceService.QuestionMulitChoiceTitle(choice.getChoiceDesc1());
		if (mulitChoice == null) {
			if (choice.getKnowledgeBegin() != null && !choice.getKnowledgeBegin().equals("")) {
				choice.setKnowledgeBegin(choice.getKnowledgeBegin());
			}
			if (choice.getKnowledgeEnd() != null && !choice.getKnowledgeEnd().equals("")) {
				choice.setKnowledgeEnd(choice.getKnowledgeEnd());
			}
			if (choice.getAnswer1().endsWith(",")) {
				choice.setAnswer1(choice.getAnswer1().substring(0, choice.getAnswer1().length() - 2));
			}
			choice.setIspublic1(0);// 默认是私有
			choice.setCreateTime(new Date());
			choice.setTag1("," + choice.getTag1() + ",");
			choice.setTeacherId(teacher.getTeacherId());
			mulitchoiceService.addQuestionMulit(choice);
			QuestionMulitChoice choice2 = mulitchoiceService.getMulitChoiceById(choice.getChoiceId());
			if (choice2 == null) {
				log.info("添加失败!");
			} else {
				log.info("添加成功!");
			}
		}
		Integer handoutType = 2;
		Integer subject = null;
		Integer questionDegree = null;
		Integer isPublic = null;
		Integer gradeId = null;
		return this.myQuestionBank(request, pageNo, pageSize, handoutType, subject, gradeId, questionDegree, isPublic);
	}

	/**
	 * 修改多选题
	 * 
	 * @param request
	 * @param choice
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateMulitChoice")
	public ModelAndView updateMulitChoice(HttpServletRequest request, QuestionMulitChoice choice,
			HttpServletResponse response, Model model, Integer pageNo, Integer pageSize) {

		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		Integer handoutType = 2;
		Integer subject = null;
		Integer questionDegree = null;
		Integer isPublic = null;
		Integer gradeId = null;
		session = request.getSession();
		choice.setUpdateTime(new Date());
		int len = mulitchoiceService.updateQuestion(choice);
		if (len > 0) {

			log.info("修改成功!");
		} else {

			log.info("修改失败!");
		}
		return this.myQuestionBank(request, pageNo, pageSize, handoutType, subject, gradeId, questionDegree, isPublic);
	}

	//ajax修改多选题
	@RequestMapping("/modityMulitChoice")
	public void modityMulitChoice(HttpServletResponse response,QuestionMulitChoice choice){
		choice.setUpdateTime(new Date());
		int len = mulitchoiceService.updateQuestion(choice);
		output(response, JsonUtil.buildFalseJson("0", "修改成功"));
	}
	/**
	 * 添加单选题
	 * 
	 * @param request
	 * @param choice
	 * @param response
	 */
	@RequestMapping("/addSingleChoice")
	public ModelAndView addSingleChoice(HttpServletRequest request, QuestionSingleChoice choice,
			HttpServletResponse response, Model model, Integer pageNo, Integer pageSize) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		QuestionSingleChoice singleChoice = singlechoiceService.getQuestionSingleChoiceTitle(choice.getChoiceDesc());
		
		if (singleChoice == null) {
			if (choice.getKnowledgeBegin() != null && !choice.getKnowledgeBegin().equals("")) {
				choice.setKnowledgeBegin(choice.getKnowledgeBegin());
			}
			if (choice.getKnowledgeEnd() != null && !choice.getKnowledgeEnd().equals("")) {
				choice.setKnowledgeEnd(choice.getKnowledgeEnd());
			}
			choice.setIspublic(0);// 默认是私有
			choice.setCreateTime(new Date());
			choice.setTag("," + choice.getTag() + ",");
			choice.setTeacherId(teacher.getTeacherId());
			singlechoiceService.addQuestionSingle(choice);// 返回主键
			QuestionSingleChoice choice2 = singlechoiceService.getSingleChoiceById(choice.getChoiceId());
			if (choice2 == null) {
				log.info("添加失败!");
			} else {
				log.info("添加成功!");
			}
		}
		Integer handoutType = choice.getType();
		Integer subject = null;
		Integer questionDegree = null;
		Integer isPublic = null;
		Integer gradeId = null;
		return this.myQuestionBank(request, pageNo, pageSize, handoutType, subject, gradeId, questionDegree, isPublic);
	}

	/**
	 * 添加主观题
	 * 
	 * @param request
	 * @param choice
	 * @param response
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/addSubjective")
	public ModelAndView addSubjective(HttpServletRequest request, SubjectiveQuestion subjective,
			HttpServletResponse response, Model model, Integer pageNo, Integer pageSize) {

		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		SubjectiveQuestion question = questionService.getSubjectiveQuestionTitle(subjective.getSubjectiveTitle());
		if (question == null) {
			subjective.setCreateTime(new Date());
			subjective.setIsPublic(0);
			subjective.setIsRemove(0);
			subjective.setType(5);
			if (subjective.getTagId() != null && !subjective.getTagId().equals("")) {
				subjective.setTagId("," + subjective.getTagId() + ",");
			}
			subjective.setTeacherId(teacher.getTeacherId());
			subjective.setUsedCount(0);
			try {
				questionService.addSubjectiveQuestion(subjective);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Integer handoutType = 5;
		Integer subject = null;
		Integer questionDegree = null;
		Integer isPublic = null;
		Integer gradeId = null;
		return this.myQuestionBank(request, pageNo, pageSize, handoutType, subject, gradeId, questionDegree, isPublic);
	}

	/**
	 * 添加填空题
	 * 
	 * @param request
	 * @param fill
	 * @return
	 */
	@RequestMapping("/addQuestionFill")
	private ModelAndView addQuestionFill(HttpServletRequest request, QuestionFill fill, Integer pageNo,
			Integer pageSize) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		QuestionFill fill2 = fillService.getQuestionFillTitle(fill.getFillTitle());
		if (fill2 == null) {
			fill.setCreateTime(new Date());
			fill.setIsPublic(0);
			fill.setIsRemove(0);
			fill.setType(6);
			fill.setTeacherId(teacher.getTeacherId());
			fill.setUsedCount(0);
			if (fill.getTagId() != null && !fill.getTagId().equals("")) {
				fill.setTagId(fill.getTagId() + ",");
			}
			if (fill.getKnowledgeBegin() != null) {
				fill.setKnowledgeBegin(fill.getKnowledgeBegin());
			}
			try {
				fillService.addQuestionFill(fill);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Integer handoutType = 6;
		Integer subject = null;
		Integer questionDegree = null;
		Integer isPublic = null;
		Integer gradeId = null;
		return this.myQuestionBank(request, pageNo, pageSize, handoutType, subject, gradeId, questionDegree, isPublic);
	}

	/**
	 * 修改单选题
	 * 
	 * @param request
	 * @param choice
	 * @param response
	 */
	@RequestMapping("/updateSingleChoice")
	public ModelAndView updateSingleChoice(HttpServletRequest request, QuestionSingleChoice choice,
			HttpServletResponse response, Model model, Integer pageNo, Integer pageSize) {

		session = request.getSession();
		// Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		Integer handoutType = choice.getType();
		Integer subject = null;
		Integer questionDegree = null;
		Integer isPublic = null;
		Integer gradeId = null;
		session = request.getSession();
		choice.setUpdateTime(new Date());
		int len = singlechoiceService.updateQuestion(choice);
		if (len > 0) {
			log.info("修改成功!");

		} else {
			log.info("修改失败!");
		}
		return this.myQuestionBank(request, pageNo, pageSize, handoutType, subject, gradeId, questionDegree, isPublic);
	}
	
	//ajax修改单选题目局
	@RequestMapping("/updateSingle")
	public void updateSingle(QuestionSingleChoice choice,HttpServletResponse response){
		QuestionSingleChoice choice2 = singlechoiceService.getQuestionById(choice.getChoiceId());
		if (choice2 != null) {
			choice.setUpdateTime(new Date());
			int len = singlechoiceService.updateQuestion(choice);
			output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
		}else {
			output(response, JsonUtil.buildFalseJson("1", "修改失败!"));
		}
	}

	/**
	 * 先添加阅读主体记录（即文本内容这些）
	 * 
	 * @param request
	 * @param read
	 * @param model
	 */
	@RequestMapping("/addRead")
	@ResponseBody
	public void addRead(QuestionRead read, HttpServletRequest request, HttpServletResponse response) {
		// log.info("获取的文本内容："+read.getReadDesc());
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		if (read.getKnowledgeBegin() != null && !read.getKnowledgeBegin().equals("")) {
			read.setKnowledgeBegin(read.getKnowledgeBegin());
		}
		if (read.getKnowledgeEnd() != null && !read.getKnowledgeEnd().equals("")) {
			read.setKnowledgeEnd(read.getKnowledgeEnd());
		}
		read.setIspublic_read(0);// 默认是私有
		read.setCreateTime(new Date());
		read.setTeacherId(teacher.getTeacherId());
		read.setTag_read("," + read.getTag_read() + ",");
		readService.addParent(read);// 返回试卷id
		QuestionRead read2 = readService.getReadById(read.getReadId());
		if (read2 != null) {
			log.info("试卷添加成功!");
			List<QuestionRead> list = new ArrayList<QuestionRead>();
			list.add(read2);
			output(response, JsonUtil.buildJson(list));
		} else {
			log.info("试卷添加失败!");
			output(response, JsonUtil.builderFalseJson("2", "添加失败!"));
		}
	}

	/**
	 * 再添加阅读的每道题目内容
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addReadDetail")
	public void addReadDetail(HttpServletRequest request, HttpServletResponse response) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");

		log.info("传来的json字符串：" + request.getParameter("jsonstr"));

		String json = request.getParameter("jsonstr");
		int parentId = Integer.valueOf(request.getParameter("parentId"));

		// Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonArray array = jsonParser.parse(json).getAsJsonArray();

		JsonObject jsonObject = null;
		QuestionRead read = new QuestionRead();
		for (int i = 0; i < 10; i++) {
			jsonObject = (JsonObject) array.get(i);
			log.info("题目是" + jsonObject.get("Optiontitle"));
			log.info("".equals(jsonObject.get("Optiontitle").getAsString()));
			log.info(jsonObject.get("Optiontitle").getAsString().isEmpty());
			if (!jsonObject.get("Optiontitle").getAsString().isEmpty()) {
				// if (jsonObject.get("score_read") != null &&
				// !jsonObject.get("score_read").equals("")) {
				// read.setScore_read(jsonObject.get("score_read").getAsFloat());
				// }
				read.setAnswer_read(jsonObject.get("answer_read").getAsString());
				if (jsonObject.get("answerDesc_read") != null && !jsonObject.get("answerDesc_read").equals("")) {
					read.setAnswerDesc_read(jsonObject.get("answerDesc_read").getAsString());
				}
				read.setCreateTime(new Date());
				read.setOptionA_read(jsonObject.get("OptionA_read").getAsString());
				read.setOptionB_read(jsonObject.get("OptionB_read").getAsString());
				read.setOptionC_read(jsonObject.get("OptionC_read").getAsString());
				read.setOptionD_read(jsonObject.get("OptionD_read").getAsString());
				read.setOptiontitle(jsonObject.get("Optiontitle").getAsString());
				read.setSubject(jsonObject.get("subject").getAsInt());
				read.setType(jsonObject.get("type").getAsInt());
				read.setParentId(parentId);
				read.setTeacherId(teacher.getTeacherId());
				readService.addAllReadByparentid(read);// 返回主键记录
				float sum = 0;
				List<QuestionRead> qrList = readService.getReadChildByParentId(read.getParentId());
				for (QuestionRead qr : qrList) {
					if (qr.getScore_read() != null) {
						sum += qr.getScore_read();
					}
				}
				QuestionRead qrRead = readService.getReadById(qrList.get(0).getParentId());
				qrRead.setUpdateTime(new Date());
				qrRead.setScore_read(sum);
				readService.updateScore(qrRead);
				QuestionRead read2 = readService.getReadById(read.getReadId());
				if (read2 != null) {
					List<QuestionRead> list = new ArrayList<QuestionRead>();
					list.add(read2);
					output(response, JsonUtil.buildJson(list));
				} else {
					output(response, JsonUtil.builderFalseJson("2", "添加试卷题目失败!"));
				}
			}
		}
	}

	/**
	 * 修改阅读主体记录（即文本内容这些）
	 * 
	 * @param request
	 * @param read
	 * @param model
	 */
	@RequestMapping("/updateRead")
	@ResponseBody
	public void updateRead(QuestionRead read, HttpServletRequest request, HttpServletResponse response) {
		read.setUpdateTime(new Date());
		int len = readService.updateQuestion(read);
		QuestionRead read2 = readService.getReadById(read.getReadId());
		if (len > 0) {
			log.info("修改成功!");
			List<QuestionRead> list = new ArrayList<QuestionRead>();
			list.add(read2);
			output(response, JsonUtil.buildJson(list));
		} else {
			log.info("修改失败!");
			output(response, JsonUtil.builderFalseJson("2", "修改失败!"));
		}

	}

	/**
	 * 再添加阅读的每道题目内容
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/updateReadDetail")
	public void updateReadDetail(HttpServletRequest request, HttpServletResponse response) {
		log.info("传来的json字符串：" + request.getParameter("jsonstr"));
		String json = request.getParameter("jsonstr");
		int parentId = Integer.valueOf(request.getParameter("parentId"));
		log.info("传来的父id" + parentId);
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonArray array = jsonParser.parse(json).getAsJsonArray();
//		System.out.println(array);
		JsonObject jsonObject = null;
		QuestionRead read = new QuestionRead();
		for (int i = 0; i < 10; i++) {
			jsonObject = (JsonObject) array.get(i);
			if (jsonObject.get("Optiontitle").toString() == "") {
				continue;
			}
			read.setReadId(jsonObject.get("readId").getAsInt());
			read.setScore_read(jsonObject.get("score_read").getAsFloat());
			read.setAnswer_read(jsonObject.get("answer_read").getAsString());
			read.setAnswerDesc_read(jsonObject.get("answerDesc_read").getAsString());
			read.setUpdateTime(new Date());
			read.setOptionA_read(jsonObject.get("OptionA_read").getAsString());
			read.setOptionB_read(jsonObject.get("OptionB_read").getAsString());
			read.setOptionC_read(jsonObject.get("OptionC_read").getAsString());
			read.setOptionD_read(jsonObject.get("OptionD_read").getAsString());
			read.setOptiontitle(jsonObject.get("Optiontitle").getAsString());
			read.setSubject(jsonObject.get("subject").getAsInt());
			read.setType(jsonObject.get("type").getAsInt());
			read.setParentId(parentId);
			int len = readService.updateQuestion(read);
			if (len > 0) {
				output(response, JsonUtil.buildFalseJson("0", "修改成功！"));
			} else {
				output(response, JsonUtil.builderFalseJson("2", "修改题目失败!"));
			}
		}
	}

	/**
	 * 删除单选题库
	 * 
	 * @param questionsId
	 * @return
	 */
	@RequestMapping("/qscDelete")
	public void delete(Integer choiceId, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		QuestionSingleChoice qsc = singlechoiceService.getSingleChoiceById(choiceId);
		if (qsc.getUseCount() > 0) {
			output(response, JsonUtil.buildFalseJson("2", "该题目已被使用，不能删除!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				int len = singlechoiceService.deleteQuestionSingle(choiceId);
				if (len > 0) {
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
				}
			} else {
				if (currTeacher.getTeacherId() != qsc.getTeacherId()) {
					output(response, JsonUtil.buildFalseJson("3", "您没有删除题目的权限!"));
				} else {
					int len = singlechoiceService.deleteQuestionSingle(choiceId);
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
	 * 删除多选选题库
	 * 
	 * @param questionsId
	 * @return
	 */
	@RequestMapping("/deleteQmc")
	public void deleteQmc(Integer choiceId, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		QuestionMulitChoice qmc = mulitchoiceService.getMulitChoiceById(choiceId);
		if (qmc != null && qmc.getUseCount() > 0) {
			output(response, JsonUtil.buildFalseJson("2", "该题目已被使用，不能删除!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				int len = mulitchoiceService.deleteQuestionMulit(choiceId);
				if (len > 0) {
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
				}
			} else {
				if (qmc != null && currTeacher.getTeacherId() != qmc.getTeacherId()) {
					output(response, JsonUtil.buildFalseJson("3", "您没有删除题目的权限!"));
				} else {
					int len = mulitchoiceService.deleteQuestionMulit(choiceId);
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
	 * 删除判断题库
	 * 
	 * @param questionsId
	 * @return
	 */
	@RequestMapping("/deleteJudge")
	public void deleteJudge(Integer judgeId, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		QuestionJudge qJudge = judgeService.getJudgeById(judgeId);
		if (qJudge.getUseCount() > 0) {
			output(response, JsonUtil.buildFalseJson("2", "该题目已被使用，不能删除!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				int len = judgeService.deleteQuestionJudge(judgeId);
				if (len > 0) {
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
				}
			} else {
				if (!currTeacher.getTeacherId().equals(qJudge.getTeacherId())) {
					output(response, JsonUtil.buildFalseJson("3", "您没有删除题目的权限!"));
				} else {
					int len = judgeService.deleteQuestionJudge(judgeId);
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
	 * 删除阅读题库
	 * 
	 * @param questionsId
	 * @return
	 */
	@RequestMapping("/deleteRead")
	public void deleteRead(Integer readId, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		QuestionRead qRead = readService.getReadById(readId);
		List<QuestionRead> qrList = readService.getReadChildByParentId(readId);
		if (qRead.getUseCount() > 0) {
			output(response, JsonUtil.buildFalseJson("2", "该题目已被使用，不能删除!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.equals("6")) {
				for (QuestionRead qr : qrList) {
					int len = readService.deleteQuestionRead(readId);
					readService.deleteQuestionRead(qr.getReadId());
					if (len > 0) {
						output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
					} else {
						output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
					}
				}
			} else {
				if (!currTeacher.getTeacherId().equals(qRead.getTeacherId())) {
					output(response, JsonUtil.buildFalseJson("3", "您没有删除题目的权限!"));
				} else {
					for (QuestionRead qr : qrList) {
						int len = readService.deleteQuestionRead(readId);
						readService.deleteQuestionRead(qr.getReadId());
						if (len > 0) {
							output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
						} else {
							output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
						}
					}
				}
			}
		}
	}

	/**
	 * 查看单选题库详情
	 * 
	 * @param model
	 * @param questionsId
	 * @return
	 */
	@RequestMapping("/seeqscDetails")
	public String viewDetails(Model model, Integer choiceId) {
		QuestionSingleChoice qsc = singlechoiceService.getSingleChoiceById(choiceId);
		model.addAttribute("qsc", qsc);
		// 循环拿到所有的年级
		List<Grade> gList = gradeService.getGrade();
		model.addAttribute("gList", gList);
		return "questionbank/questionDetails";
	}

	/**
	 * 查看多选题库详情
	 * 
	 * @param model
	 * @param questionsId
	 * @return
	 */
	@RequestMapping("/seeQmcDetails")
	public String seeQmcDetails(Model model, Integer choiceId) {
		QuestionMulitChoice qmc = mulitchoiceService.getMulitChoiceById(choiceId);
		model.addAttribute("qmc", qmc);
		// 循环拿到所有的年级
		List<Grade> gList = gradeService.getGrade();
		model.addAttribute("gList", gList);
		return "questionbank/qmulitDetails";
	}

	/**
	 * 查看判断题库详情
	 * 
	 * @param model
	 * @param questionsId
	 * @return
	 */
	@RequestMapping("/seeqjDetails")
	public String seeqjDetails(Model model, Integer judgeId) {
		QuestionJudge qj = judgeService.getJudgeById(judgeId);
		model.addAttribute("qj", qj);
		// 循环拿到所有的年级
		List<Grade> gList = gradeService.getGrade();
		model.addAttribute("gList", gList);
		return "questionbank/qjudgeDetails";
	}

	/**
	 * 查看阅读理解题库详情
	 * 
	 * @param model
	 * @param questionsId
	 * @return
	 */
	@RequestMapping("/seeqrDetails")
	public String seeqrDetails(Model model, Integer readId) {
		QuestionRead qr = readService.getReadById(readId);
		List<QuestionRead> qrList = readService.getReadChildByParentId(readId);
		model.addAttribute("qr", qr);
		model.addAttribute("qrList", qrList);
		// 循环拿到所有的年级
		List<Grade> gList = gradeService.getGrade();
		model.addAttribute("gList", gList);
		return "questionbank/qrDetails";
	}

	/**
	 * 修改题库
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modity")
	public ResultData modity(Questions q) {
		ResultData rd = new ResultData();
		if (q != null) {
			qbService.updateQuestionsById(q);
			rd.setSuccess("修改成功!");
		} else {
			rd.setSuccess("修改失败!");
		}
		return rd;
	}

	/**
	 * 修改是否公开
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("updatequestionbyispublic")
	public void updateQuestionByIspublic(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		Integer type = Integer.valueOf(request.getParameter("type"));
		Integer ispublic = Integer.valueOf(request.getParameter("isPublic"));
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		int len = 0;
		if (type == 1) {
			QuestionSingleChoice qsc = singlechoiceService.getSingleChoiceById(id);
			if (!currTeacher.getTeacherId().equals(qsc.getTeacherId()) && !currTeacher.getTeacherType().equals("1")
					&& !currTeacher.getTeacherType().equals("2") && !currTeacher.getTeacherType().equals("3")
					&& !currTeacher.getTeacherType().equals("4")) {
				output(response, JsonUtil.buildFalseJson("1", "您既不是管理员也不是创建者，不能对该题目进行编辑!"));
			} else {
				QuestionSingleChoice single = new QuestionSingleChoice();
				single.setUpdateTime(new Date());
				single.setChoiceId(id);
				single.setIspublic(ispublic);
				len = singlechoiceService.updateIspublic(single);
			}
		} else if (type == 2) {
			QuestionMulitChoice qmc = mulitchoiceService.getMulitChoiceById(id);
			if (!currTeacher.getTeacherId().equals(qmc.getTeacherId()) && !currTeacher.getTeacherType().equals("1")
					&& !currTeacher.getTeacherType().equals("2") && !currTeacher.getTeacherType().equals("3")
					&& !currTeacher.getTeacherType().equals("4")) {
				output(response, JsonUtil.buildFalseJson("1", "您既不是管理员也不是创建者，不能对该题目进行编辑!"));
			} else {
				QuestionMulitChoice choice = new QuestionMulitChoice();
				choice.setUpdateTime(new Date());
				choice.setChoiceId(id);
				choice.setIspublic1(ispublic);
				len = mulitchoiceService.updateIspublic(choice);
			}
		} else if (type == 3) {
			QuestionJudge qjJudge = judgeService.getJudgeById(id);
			if (!currTeacher.getTeacherId().equals(qjJudge.getTeacherId()) && !currTeacher.getTeacherType().equals("1")
					&& !currTeacher.getTeacherType().equals("2") && !currTeacher.getTeacherType().equals("3")
					&& !currTeacher.getTeacherType().equals("4")) {
				output(response, JsonUtil.buildFalseJson("1", "您既不是管理员也不是创建者，不能对该题目进行编辑!"));
			} else {
				QuestionJudge judge = new QuestionJudge();
				judge.setUpdateTime(new Date());
				judge.setJudgeId(id);
				judge.setIspublic2(ispublic);
				len = judgeService.updateIspublic(judge);
			}
		} else if (type == 4) {
			QuestionRead qr = readService.getReadById(id);
			if (!currTeacher.getTeacherId().equals(qr.getTeacherId()) && !currTeacher.getTeacherType().equals("1")
					&& !currTeacher.getTeacherType().equals("2") && !currTeacher.getTeacherType().equals("3")
					&& !currTeacher.getTeacherType().equals("4")) {
				output(response, JsonUtil.buildFalseJson("1", "您既不是管理员也不是创建者，不能对该题目进行编辑!"));
			} else {
				QuestionRead read = new QuestionRead();
				read.setUpdateTime(new Date());
				read.setReadId(id);
				read.setIspublic_read(ispublic);
				len = readService.updateIspublic(read);
			}
		} else if (type == 5) {
			SubjectiveQuestion sub = questionService.getSubjectiveQuestionById(id);
			if (!currTeacher.getTeacherId().equals(sub.getTeacherId()) && !currTeacher.getTeacherType().equals("1")
					&& !currTeacher.getTeacherType().equals("2") && !currTeacher.getTeacherType().equals("3")
					&& !currTeacher.getTeacherType().equals("4")) {
				output(response, JsonUtil.buildFalseJson("1", "您既不是管理员也不是创建者，不能对该题目进行编辑!"));
			} else {
				SubjectiveQuestion subjectiv = new SubjectiveQuestion();
				subjectiv.setSubjectiveId(id);
				subjectiv.setUpdateTime(new Date());
				subjectiv.setIsPublic(ispublic);
				len = questionService.updateIsPublic(subjectiv);
			}
		} else if (type == 6) {
			QuestionFill questionFill = fillService.getQuestionFillById(id);
			if (!currTeacher.getTeacherId().equals(questionFill.getTeacherId())
					&& !currTeacher.getTeacherType().equals("1") && !currTeacher.getTeacherType().equals("2")
					&& !currTeacher.getTeacherType().equals("3") && !currTeacher.getTeacherType().equals("4")) {
				output(response, JsonUtil.buildFalseJson("1", "您既不是管理员也不是创建者，不能对该题目进行编辑!"));
			} else {
				QuestionFill fill = new QuestionFill();
				fill.setFillId(id);
				fill.setUpdateTime(new Date());
				fill.setIsPublic(ispublic);
				len = fillService.updateIsPublic(fill);
			}
		} else if (type == 7) {
			QuestionCloze cloze = clozeService.getQuestionClozeById(id);
			if (!currTeacher.getTeacherId().equals(cloze.getTeacherId()) && !currTeacher.getTeacherType().equals("1")
					&& !currTeacher.getTeacherType().equals("2") && !currTeacher.getTeacherType().equals("3")
					&& !currTeacher.getTeacherType().equals("4")) {
				output(response, JsonUtil.buildFalseJson("1", "您既不是管理员也不是创建者，不能对该题目进行编辑!"));
			} else {
				cloze.setUpdateTime(new Date());
				cloze.setIsPublic(ispublic);
				clozeService.updateIsPublic(cloze);
				len = 1;
			}
		}
		if (len > 0) {
			output(response, JsonUtil.buildFalseJson("0", "更新成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("2", "更新失败!"));
		}
	}

	/**
	 * 修改阅读理解
	 * 
	 * @param readId
	 * @param response
	 */
	@RequestMapping("/modityRead")
	public void modityRead(QuestionRead qr, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		QuestionRead qr1 = readService.getReadById(qr.getReadId());
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			qr1.setUpdateTime(new Date());
			readService.updateQuestion(qr1);
			output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
		} else {
			TeacherAuthority ta = authorityService.findIsTeacherGradeById(currTeacher.getTeacherId(),
					qr.getKnowledge().toString());
			if (!currTeacher.getTeacherId().equals(qr1.getTeacherId())) {
				output(response, JsonUtil.buildFalseJson("1", "您没有修改题目的权限!"));
			} else if (ta.getQuestionsAuthority() == 0 || ta.getQuestionsAuthority() == 1) {
				output(response, JsonUtil.buildFalseJson("1", "对不起，您没有修改题目的权限!"));
			} else if (!ta.getGrade().equals(qr.getKnowledge().toString())) {
				output(response, JsonUtil.buildFalseJson("2", "您修改的年级与您现有的年级不一致!"));
			} else {
				try {
					qr1.setUpdateTime(new Date());
					readService.updateQuestion(qr1);
					output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
				} catch (Exception e) {
					e.printStackTrace();
					output(response, JsonUtil.buildFalseJson("2", "修改失败!"));
				}
			}
		}
	}

	/**
	 * 修改阅读理解正文
	 * 
	 * @param response
	 * @param request
	 * @param qr
	 */
	@RequestMapping("/modityReadDesc")
	public void modityReadDesc(HttpServletResponse response, HttpServletRequest request, QuestionRead qr) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		QuestionRead qr1 = readService.getReadById(qr.getReadId());
		TeacherAuthority ta = authorityService.findIsTeacherGradeById(currTeacher.getTeacherId(),
				qr.getKnowledge().toString());
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			qr.setUpdateTime(new Date());
			readService.updateQuestion(qr);
			output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
		} else {
			if (!currTeacher.getTeacherId().equals(qr1.getTeacherId())
					&& (ta.getQuestionsAuthority() == 0 || ta.getQuestionsAuthority() == 1)) {
				output(response, JsonUtil.buildFalseJson("1", "您没有修改题目的权限!"));
			} else {
				try {
					qr.setUpdateTime(new Date());
					readService.updateQuestion(qr);
					output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
				} catch (Exception e) {
					output(response, JsonUtil.buildFalseJson("2", "修改失败!"));
				}
			}
		}
	}

	/**
	 * 前往修改题目页面
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping("/toupdatequestion")
	public ModelAndView toUpdateQuestion(Integer id, Integer type, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		if (type == 1) {
			QuestionSingleChoice question = singlechoiceService.getQuestionById(id);
			String tags = question.getTag();
			if (tags != null) {
				String[] tag = tags.split(",");
				List list = Arrays.asList(tag);
				List<QuestionTag> taglist = questionTagService.getTagByIds(list);
				question.setQuestionTags(taglist);
			}
			view.addObject("single", question);
		}
		if (type == 2) {
			QuestionMulitChoice question = mulitchoiceService.getMulitChoiceById(id);

			String tags = question.getTag1();
			if (tags != null) {
				String[] tag = tags.split(",");
				List list = Arrays.asList(tag);
				List<QuestionTag> taglist = questionTagService.getTagByIds(list);
				question.setQuestionTags(taglist);
			}
			view.addObject("mulit", question);
		}
		if (type == 3) {
			QuestionJudge question = judgeService.getJudgeById(id);

			String tags = question.getTag2();
			if (tags != null) {
				String[] tag = tags.split(",");
				List list = Arrays.asList(tag);
				List<QuestionTag> taglist = questionTagService.getTagByIds(list);
				question.setQuestionTags(taglist);
			}
			view.addObject("judge", question);
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		// 循环拿到科目
		List<Subject> sList = null;
		List<Grade> gList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			gList = gradeService.getGrade();
		} else {
			sList = subjectService.getTeacherIsSubject(currTeacher.getSubject());
			String[] gStrings = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(gStrings);
			gList = gradeService.getGradeByIds(gradeList);
		}
		view.addObject("sList", sList);
		view.addObject("gList", gList);
		// 循环拿到题型
		List<QuestionsType> qtList = qtypeService.questionsType();
		view.addObject("qtList", qtList);
		// 循环拿到题目来源
		List<RangeLesson> lessons = rangeLessonService.getLessonList();
		view.addObject("lessonList", lessons);

		view.setViewName("questionbank/updatequestion");
		List<QuestionSource> qsList = QuestionSourceService.questionSource();
		view.addObject("qsList", qsList);
		view.addObject("type", type);
		return view;
	}

	/**
	 * 修改阅读理解页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/toupdaReading")
	public ModelAndView toupdaReading(Integer id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		QuestionRead question = readService.getReadById(id);
		List<QuestionRead> qrList = readService.getReadChildByParentId(question.getReadId());
		String tags = question.getTag_read();
		if (tags != null) {
			String[] tag = tags.split(",");
			List list = Arrays.asList(tag);
			List<QuestionTag> taglist = questionTagService.getTagByIds(list);
			question.setQuestionTags(taglist);
		}
		int type = 4;

		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		// 循环拿到科目
		List<Subject> sList = null;
		List<Grade> gList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			gList = gradeService.getGrade();
		} else {
			sList = subjectService.getTeacherIsSubject(currTeacher.getSubject());
			String[] gStrings = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(gStrings);
			gList = gradeService.getGradeByIds(gradeList);
		}
		// 循环拿到题目来源
		List<QuestionSource> qsList = QuestionSourceService.questionSource();
		mv.addObject("sList", sList);
		mv.addObject("gList", gList);
		// 循环拿到题型
		List<QuestionsType> qtList = qtypeService.questionsType();
		mv.addObject("qtList", qtList);
		// 循环拿到题目来源
		List<RangeLesson> lessons = rangeLessonService.getLessonList();
		mv.addObject("lessonList", lessons);
		mv.addObject("qsList", qsList);
		mv.addObject("read", question);
		mv.addObject("qrList", qrList);
		mv.addObject("type", type);
		mv.setViewName("questionbank/toupdaReading");
		return mv;
	}

	/**
	 * 修改主观题页面
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping("/moditySubjective")
	public ModelAndView moditySubjective(Integer id, Integer type, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		SubjectiveQuestion sQuestion = questionService.getSubjectiveQuestionById(id);
		String tags = sQuestion.getTagId();
		if (tags != null) {
			String[] tag = tags.split(",");
			List list = Arrays.asList(tag);
			List<QuestionTag> taglist = questionTagService.getTagByIds(list);
			sQuestion.setQtList(taglist);
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		// 循环拿到科目
		List<Subject> sList = null;
		List<Grade> gList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			gList = gradeService.getGrade();
		} else {
			sList = subjectService.getTeacherIsSubject(currTeacher.getSubject());
			String[] gStrings = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(gStrings);
			gList = gradeService.getGradeByIds(gradeList);
		}
		// 循环拿到题目来源
		List<QuestionSource> qsList = QuestionSourceService.questionSource();
		mv.addObject("sList", sList);
		mv.addObject("gList", gList);
		// 循环拿到题型
		List<QuestionsType> qtList = qtypeService.questionsType();
		mv.addObject("qtList", qtList);
		// 循环拿到题目来源
		List<RangeLesson> lessons = rangeLessonService.getLessonList();
		mv.addObject("lessonList", lessons);
		mv.addObject("qsList", qsList);
		mv.addObject("type", type);
		mv.addObject("sQuestion", sQuestion);
		mv.setViewName("questionbank/updateSubjective");
		return mv;
	}

	/**
	 * 修改主观题
	 * 
	 * @param subjectiveId
	 * @param response
	 * @param sq
	 */
	@RequestMapping("/updateSubjective")
	public void updateSubjective(HttpServletResponse response, SubjectiveQuestion sq, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		SubjectiveQuestion fill = questionService.getSubjectiveQuestionById(sq.getSubjectiveId());
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sq.setUpdateTime(new Date());
			if (sq.getTagId() != null && !sq.getTagId().equals("")) {
				sq.setTagId(sq.getTagId());
			}
			questionService.updateSubjectiveQuestion(sq);
			output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
		} else {
			TeacherAuthority ta = authorityService.findIsTeacherGradeById(currTeacher.getTeacherId(),
					sq.getKnowledge().toString());
			if ((ta.getQuestionsAuthority() == 0 || ta.getQuestionsAuthority() == 1)
					&& !fill.getTeacherId().equals(currTeacher.getTeacherId())) {
				output(response, JsonUtil.buildFalseJson("3", "非常抱歉您没有修改的权限!"));
			} else if (!ta.getGrade().equals(sq.getKnowledge().toString())
					&& !fill.getTeacherId().equals(currTeacher.getTeacherId())) {
				output(response, JsonUtil.buildFalseJson("4", "您修改的年级与您现有的年级不一致!"));
			} else if (!currTeacher.getTeacherId().equals(fill.getTeacherId())) {
				output(response, JsonUtil.buildFalseJson("2", "你不是该题目的创建者也不是管理员，不能修改!"));
			} else {
				try {
					sq.setUpdateTime(new Date());
					if (sq.getTagId() != null && !sq.getTagId().equals("")) {
						sq.setTagId(sq.getTagId());
					}
					questionService.updateSubjectiveQuestion(sq);
					output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
				} catch (Exception e) {
					output(response, JsonUtil.buildFalseJson("1", "修改失败!"));
				}
			}
		}
	}

	//ajax修改主观题
//	@RequestMapping("/moditySub")
//	public void updateSubjective(HttpServletResponse response,SubjectiveQuestion s){
//		s.setUpdateTime(new Date());
//		questionService.updateSubjectiveQuestion(s);
//		output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
//	}
	/**
	 * 删除主观题
	 * 
	 * @param response
	 * @param subjectiveId
	 */
	@RequestMapping("/deleteoneSubjective")
	public void deleteoneSubjective(HttpServletResponse response, Integer subjectiveId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		SubjectiveQuestion sq = questionService.getSubjectiveQuestionById(subjectiveId);
		if (sq != null && sq.getUsedCount() > 0) {
			output(response, JsonUtil.buildFalseJson("1", "该题已被使用，暂不能删除!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				questionService.falseDeleteSubjective(sq);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} else {
				if (!currTeacher.getTeacherId().equals(sq.getTeacherId())) {
					output(response, JsonUtil.buildFalseJson("1", "你不是管理员也不是该题目的创建者，不能删除题目!"));
				} else {
					questionService.falseDeleteSubjective(sq);
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				}
			}
		}
	}

	// 删除完形填空
	@RequestMapping("/deleteoneCloze")
	public void deleteoneCloze(HttpServletResponse response, HttpServletRequest request, Integer clozeId) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		QuestionCloze cloze = clozeService.getQuestionClozeById(clozeId);
		if (cloze != null && cloze.getUsedCount() > 0) {
			output(response, JsonUtil.buildFalseJson("1", "该题已被使用，暂不能删除!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				clozeService.deleteQuestionCloze(cloze);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} else {
				if (!currTeacher.getTeacherId().equals(cloze.getTeacherId())) {
					output(response, JsonUtil.buildFalseJson("2", "你不是管理员也不是该题目的创建者，不能删除题目!"));
				} else {
					clozeService.deleteQuestionCloze(cloze);
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				}
			}
		}
	}

	/**
	 * 删除填空题(假删除)
	 * 
	 * @param response
	 * @param fillId
	 */
	@RequestMapping("/deleteoneFill")
	public void deleteoneFill(HttpServletResponse response, Integer fillId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		QuestionFill fill = fillService.getQuestionFillById(fillId);
		if (fill.getUsedCount() > 0) {
			output(response, JsonUtil.buildFalseJson("2", "该题已被使用，暂不能删除!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				fillService.falseDeleteFill(fill);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} else {
				if (!currTeacher.getTeacherId().equals(fill.getTeacherId())) {
					output(response, JsonUtil.buildFalseJson("1", "你不是管理员也不是该题目的创建者，不能删除题目!"));
				} else {
					fillService.falseDeleteFill(fill);
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				}
			}
		}
	}

	/**
	 * 修改填空题目页面
	 * 
	 * @param id
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping("/modityFill")
	public ModelAndView modityFill(Integer id, HttpServletRequest request, int type) {

		ModelAndView mv = new ModelAndView();
		QuestionFill fill = fillService.getQuestionFillById(id);
		String tags = fill.getTagId();
		if (tags != null) {
			String[] tag = tags.split(",");
			List list = Arrays.asList(tag);
			List<QuestionTag> taglist = questionTagService.getTagByIds(list);
			fill.setQtList(taglist);
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		// 循环拿到科目
		List<Subject> sList = null;
		List<Grade> gList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			gList = gradeService.getGrade();
		} else {
			sList = subjectService.getTeacherIsSubject(currTeacher.getSubject());
			String[] gStrings = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(gStrings);
			gList = gradeService.getGradeByIds(gradeList);
		}
		//填空题类型
		List<QuestionFillType> typeList = questionFillTypeService.getQuestionFillTypeList();
		mv.addObject("typeList", typeList);
		// 循环拿到题目来源
		List<QuestionSource> qsList = QuestionSourceService.questionSource();
		mv.addObject("sList", sList);
		mv.addObject("gList", gList);
		// 循环拿到题型
		List<QuestionsType> qtList = qtypeService.questionsType();
		mv.addObject("qtList", qtList);
		// 循环拿到题目来源
		List<RangeLesson> lessons = rangeLessonService.getLessonList();
		mv.addObject("lessonList", lessons);
		mv.addObject("qsList", qsList);
		mv.addObject("type", type);
		mv.addObject("fill", fill);
		mv.setViewName("questionFill/updateFill");
		return mv;
	}

	/**
	 * 修改填空题
	 * 
	 * @param response
	 * @param fill
	 */
	@RequestMapping("/updateFill")
	public void updateFill(HttpServletResponse response, QuestionFill fill, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		QuestionFill fill2 = fillService.getQuestionFillById(fill.getFillId());
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			fill.setUpdateTime(new Date());
			if (fill.getTagId() != null && !fill.getTagId().equals("")) {
				fill.setTagId(fill.getTagId());
			}
			fillService.updateQuestionFill(fill);
			output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
		} else {
			TeacherAuthority ta = authorityService.findIsTeacherGradeById(currTeacher.getTeacherId(),
					fill.getKnowledge().toString());
			if ((ta.getQuestionsAuthority() == 0 || ta.getQuestionsAuthority() != 1)
					&& !fill2.getTeacherId().equals(currTeacher.getTeacherId())) {
				output(response, JsonUtil.buildFalseJson("3", "您没有修改题目的权限!"));
			} else if (!ta.getGrade().equals(fill.getKnowledge().toString())
					&& !fill2.getTeacherId().equals(currTeacher.getTeacherId())) {
				output(response, JsonUtil.buildFalseJson("4", "您修改的年级与您现有的年级不一致!"));
			} else if (!currTeacher.getTeacherId().equals(fill2.getTeacherId())) {
				output(response, JsonUtil.buildFalseJson("2", "你不是该题目的创建者也不是管理员，不能修改!"));
			} else {
				try {
					fill.setUpdateTime(new Date());
					if (fill.getTagId() != null && !fill.getTagId().equals("")) {
						fill.setTagId(fill.getTagId());
					}
					fillService.updateQuestionFill(fill);
					output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
				} catch (Exception e) {
					output(response, JsonUtil.buildFalseJson("1", "修改失败!"));
				}
			}
		}
	}

	/**
	 * 检测是否可以添加题目
	 * 
	 * @param response
	 * @param request
	 * @param gradeId
	 */
	@RequestMapping("/testingIsAdd")
	public void testingIsAdd(HttpServletResponse response, Integer gradeId, HttpServletRequest request,
			Integer subject) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		TeacherAuthority ta = authorityService.findIsTeacherGradeById(currTeacher.getTeacherId(),
				String.valueOf(gradeId));
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("0", "可以添加题目!"));
		} else {
			if (!currTeacher.getSubject().equals(subject)) {
				output(response, JsonUtil.buildFalseJson("3", "您添加的科目与您所教科目不一致!"));
			} else if (ta == null) {
				output(response, JsonUtil.buildFalseJson("2", "您添加题目的年级与您现有的年级不一致!"));
			} else if (ta != null && (ta.getQuestionsAuthority() == 0 || ta.getQuestionsAuthority() == 1)) {
				output(response, JsonUtil.buildFalseJson("1", "您没有添加题目的权限!"));
			} else {
				output(response, JsonUtil.buildFalseJson("0", "可以添加题目!"));
			}
		}
	}

	// 添加完形填空
	@RequestMapping("/addCloze")
	public void addCloze(HttpServletRequest request, HttpServletResponse response, QuestionCloze cloze)
			throws Exception {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		log.info("传来的json字符串：" + request.getParameter("jsonstr"));
		String json = request.getParameter("jsonstr");

		JsonParser jsonParser = new JsonParser();
		JsonArray array = jsonParser.parse(json).getAsJsonArray();

		JsonObject jsonObject = null;
		cloze.setCreateTime(new Date());
		cloze.setTeacherId(teacher.getTeacherId());
		cloze.setUsedCount(0);
		cloze.setIsPublic(0);
		cloze.setTagId(","+cloze.getTagId()+",");
		clozeService.addQuestionCloze(cloze);// 返回clozeId
		QuestionCloze cloze2 = new QuestionCloze();
		for (int i = 0; i < 10; i++) {
			jsonObject = (JsonObject) array.get(i);
			if (!jsonObject.get("clozeOptionA").getAsString().isEmpty()) {
				if (jsonObject.get("analysis") != null && !jsonObject.get("analysis").equals("")) {
					cloze2.setAnalysis(jsonObject.get("analysis").getAsString());
				}
				cloze2.setCreateTime(new Date());
				cloze2.setClozeOptionA(jsonObject.get("clozeOptionA").getAsString());
				cloze2.setClozeOptionB(jsonObject.get("clozeOptionB").getAsString());
				cloze2.setClozeOptionC(jsonObject.get("clozeOptionC").getAsString());
				cloze2.setClozeOptionD(jsonObject.get("clozeOptionD").getAsString());
				cloze2.setClozeAnswer(jsonObject.get("clozeAnswer").getAsString());
				cloze2.setSubject(jsonObject.get("subject").getAsInt());
				cloze2.setParentId(cloze.getClozeId());
				cloze2.setKnowledge(cloze.getKnowledge());
				cloze2.setTagId(","+cloze.getTagId()+",");
				cloze2.setTeacherId(teacher.getTeacherId());
				clozeService.addQuestionCloze(cloze2);// 返回clozeId
			}
		}
		output(response, JsonUtil.buildFalseJson("0", "添加题目成功!"));
	}
	
	//保存修改完形填空
	@RequestMapping("/modityCloze")
	public void modityCloze(HttpServletResponse response,HttpServletRequest request,
			QuestionCloze cloze,String jsonstr){
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		log.info("传来的json字符串：" + jsonstr);
		JsonParser jsonParser = new JsonParser();
		JsonArray array = jsonParser.parse(jsonstr).getAsJsonArray();
		JsonObject jsonObject = null;
		QuestionCloze cloze2 = clozeService.getQuestionClozeById(cloze.getClozeId());
		if (!teacher.getTeacherType().equals("5") && !teacher.getTeacherType().equals("6")) {
			cloze.setUpdateTime(new Date());
			clozeService.updateIsPublic(cloze);
			for (int i = 0; i < array.size(); i++) {
				jsonObject = (JsonObject) array.get(i);
				QuestionCloze cloze3 = clozeService.getQuestionClozeById(jsonObject.get("clozeId").getAsInt());
				if (cloze3 != null) {
					cloze3.setUpdateTime(new Date());
					cloze3.setAnalysis(jsonObject.get("analysis").getAsString());
//					if (!jsonObject.get("clozeAnswer").getAsString().isEmpty()) {
//						cloze3.setClozeAnswer(jsonObject.get("clozeAnswer").getAsString());
//					}
					cloze3.setClozeOptionA(jsonObject.get("clozeOptionA").getAsString());
					cloze3.setClozeOptionB(jsonObject.get("clozeOptionB").getAsString());
					cloze3.setClozeOptionC(jsonObject.get("clozeOptionC").getAsString());
					cloze3.setClozeOptionD(jsonObject.get("clozeOptionD").getAsString());
					clozeService.updateIsPublic(cloze3);
				}
			}
			output(response, JsonUtil.buildFalseJson("0", "添加题目成功!"));
		} else {
			if (!teacher.getTeacherId().equals(cloze2.getTeacherId())) {
				output(response, JsonUtil.buildFalseJson("1", "您不是管理员也不是该题目的录入者，不能编辑该题目!"));
			} else {
				cloze.setUpdateTime(new Date());
				clozeService.updateIsPublic(cloze);
				for (int i = 0; i < array.size(); i++) {
					jsonObject = (JsonObject) array.get(i);
					QuestionCloze cloze3 = clozeService.getQuestionClozeById(jsonObject.get("clozeId").getAsInt());
					if (cloze3 != null) {
						cloze3.setUpdateTime(new Date());
						cloze3.setAnalysis(jsonObject.get("analysis").getAsString());
//						if (!jsonObject.get("clozeAnswer").getAsString().isEmpty()) {
//							cloze3.setClozeAnswer(jsonObject.get("clozeAnswer").getAsString());
//						}
						cloze3.setClozeOptionA(jsonObject.get("clozeOptionA").getAsString());
						cloze3.setClozeOptionB(jsonObject.get("clozeOptionB").getAsString());
						cloze3.setClozeOptionC(jsonObject.get("clozeOptionC").getAsString());
						cloze3.setClozeOptionD(jsonObject.get("clozeOptionD").getAsString());
						clozeService.updateIsPublic(cloze3);
					}
				}
				output(response, JsonUtil.buildFalseJson("0", "添加题目成功!"));
			}
		}
	}

	// 修改完形填空
	@RequestMapping("/updateCloze")
	public ModelAndView updateCloze(ModelAndView mAndView, Integer clozeId,HttpServletRequest request) {
		QuestionCloze cloze = clozeService.getQuestionClozeById(clozeId);
		List<QuestionCloze> clozes = clozeService.getparentByIdList(cloze.getClozeId());
		String tags = cloze.getTagId();
		if (tags != null) {
			String[] tag = tags.split(",");
			List list = Arrays.asList(tag);
			List<QuestionTag> taglist = questionTagService.getTagByIds(list);
			cloze.setQtList(taglist);
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		// 循环拿到科目
		List<Subject> sList = null;
		List<Grade> gList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			gList = gradeService.getGrade();
		} else {
			sList = subjectService.getTeacherIsSubject(currTeacher.getSubject());
			String[] gStrings = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(gStrings);
			gList = gradeService.getGradeByIds(gradeList);
		}
		// 循环拿到题目来源
		mAndView.addObject("cloze", cloze);
		// 循环拿到题目来源
		List<QuestionSource> qsList = QuestionSourceService.questionSource();
		mAndView.addObject("sList", sList);
		mAndView.addObject("gList", gList);
		// 循环拿到题型
		List<QuestionsType> qtList = qtypeService.questionsType();
		mAndView.addObject("qtList", qtList);
		// 循环拿到题目来源
		List<RangeLesson> lessons = rangeLessonService.getLessonList();
		mAndView.addObject("lessonList", lessons);
		mAndView.addObject("qsList", qsList);
		mAndView.addObject("clozes", clozes);
		mAndView.setViewName("questionCloze/updateCloze");
		return mAndView;
	}
}
