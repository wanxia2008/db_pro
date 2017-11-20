package com.db.action;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.ClassNo;
import com.db.entity.Grade;
import com.db.entity.Log;
import com.db.entity.PaperDetails;
import com.db.entity.PaperInfo;
import com.db.entity.QuestionCloze;
import com.db.entity.QuestionFill;
import com.db.entity.QuestionFillType;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionPaer;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.QuestionTag;
import com.db.entity.Questions;
import com.db.entity.QuestionsType;
import com.db.entity.RangeLesson;
import com.db.entity.SeePaper;
import com.db.entity.Subject;
import com.db.entity.SubjectiveQuestion;
import com.db.entity.TaskType;
import com.db.entity.Teacher;
import com.db.entity.TeacherAuthority;
import com.db.entity.TeachingMaterial;
import com.db.service.ClassNoService;
import com.db.service.GradeService;
import com.db.service.LogService;
import com.db.service.PaperDetailsService;
import com.db.service.PaperInfoService;
import com.db.service.QuestionBankService;
import com.db.service.QuestionClozeService;
import com.db.service.QuestionFillService;
import com.db.service.QuestionFillTypeService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.QuestionTagService;
import com.db.service.QuestionsTypeService;
import com.db.service.RangeLessonService;
import com.db.service.SubjectService;
import com.db.service.SubjectiveQuestionService;
import com.db.service.TaskTypeService;
import com.db.service.TeacherAuthorityService;
import com.db.service.TeachingMaterialService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("/exam")
public class ExamController extends BaseUtil {

	@Resource
	private PaperInfoService paperInfoService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private GradeService gradeService;
	@Resource
	private QuestionsTypeService qtypeService;
	@Resource
	private TeachingMaterialService tmService;
	@Resource
	private PaperDetailsService paperDetailsService;
	@Resource
	private QuestionTagService questionTagService;
	@Resource
	private QuestionBankService questionBankService;
	@Resource
	private ClassNoService classNoService;
	@Resource
	private QuestionJudgeService judgeservice;
	@Resource
	private QuestionSingleChoiceService singleservice;
	@Resource
	private QuestionMulitChoiceService mulitservice;
	@Resource
	private QuestionReadService readservice;
	@Resource
	private TaskTypeService typeService;
	private HttpSession session;
	@Resource
	private TeachingMaterialService tMaterialService;
	@Resource
	private QuestionsTypeService questionsTypeService;
	@Resource
	private TeacherAuthorityService authorityService;
	@Resource
	private RangeLessonService lessonService;
	@Resource
	private SubjectiveQuestionService questionService;
	@Resource
	private LogService logService;
	@Resource
	private QuestionFillService fillService;
	@Resource
	private QuestionFillTypeService fillTypeService;
	@Resource
	private QuestionClozeService clozeService;
	@Resource
	private QuestionSingleChoiceService singlechoiceService;
	@Resource
	private QuestionMulitChoiceService mulitchoiceService;
	@Resource
	private QuestionJudgeService judgeService;
	@Resource
	private QuestionReadService readService;	

	private Logger log = Logger.getLogger(ExamController.class);

	/**
	 * 我的试卷列表
	 * 
	 * @return
	 */
	@RequestMapping("/showmyexam")
	public ModelAndView showMyexam(HttpServletRequest request, PaperInfo pInfo, Integer pageNo, Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		String gradeId = currTeacher.getGrade();

		int rowsCount = 0;
		
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
System.out.println("pInfo :"+ pInfo.getGrade());
		ModelAndView mv = new ModelAndView();
		List<TeacherAuthority> taList = authorityService.getTeacherIdById(currTeacher.getTeacherId());
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {// 超级管理员能看所有的
			rowsCount = paperInfoService.seePaperCount(pInfo);
			List<PaperInfo> piList = paperInfoService.seepaperInfoIsPublicList(pInfo, (pageNo - 1) * pageSize,
					pageSize);
			if (!piList.isEmpty() && piList != null) {
				mv.addObject("piList", piList);
			}
		} else {// 只鞥查看自己的和别人公开的
			pInfo.setSubject(currTeacher.getSubject());
			for (TeacherAuthority ta:taList) {
				// 能读别人公开的试卷和自己录入试卷(自己所教年级和(别人年级和自己相同))
				
				if (ta.getPaperAuthority() == 1 || ta.getPaperAuthority() == 3) {
					rowsCount = paperInfoService.getPaperCount1(pInfo, currTeacher.getTeacherId());
					List<PaperInfo> piList = paperInfoService.paperInfoIsPublicList(gList,currTeacher.getTeacherId(), pInfo,
							(pageNo - 1) * pageSize, pageSize);
					if (!piList.isEmpty() && piList != null) {
//						
//						//过滤掉不是自己年级的试卷
//						for(PaperInfo paInfo :  piList  ){
//							String id =  String.valueOf(paInfo.getGrade());
//							for(String gid : idlist){
//								if(id.equals(gid)){
//								returnList.add(paInfo);
//								}
//						    }
//						}
//						piList = returnList;
//						
						mv.addObject("piList", piList);
					}
				} else {
					// 只能读自己的试卷
					rowsCount = paperInfoService.getPaperCount(pInfo, currTeacher.getTeacherId());
					List<PaperInfo> piList = paperInfoService.paperInfoList(currTeacher.getTeacherId(), pInfo,
							(pageNo - 1) * pageSize, pageSize, null, null,null);
					if (!piList.isEmpty() && piList != null) {
						mv.addObject("piList", piList);
					}
				}
			}
		}
		int isPublicCount = paperInfoService.isPublicCount(currTeacher.getTeacherId(),currTeacher.getTeacherType());
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		
		
		mv.addObject("sList", sList);
		List<ClassNo> cList = classNoService.getAllClasses(currTeacher.getCampus(), (pageNo - 1) * pageSize, pageSize);
		mv.addObject("cList", cList);
		mv.addObject("gList", gList);
		mv.setViewName("exampaper/myexam");
		List<TeachingMaterial> tmList = tMaterialService.teachingMaterial();
		mv.addObject("tmList", tmList);
		List<QuestionsType> qtList = questionsTypeService.questionsType();
		List<TaskType> qsstList = typeService.taskType();
		mv.addObject("qtList", qtList);
		mv.addObject("qsstList", qsstList);
		mv.addObject("pInfo", pInfo);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("rowsCount", rowsCount);
		mv.addObject("isPublicCount", isPublicCount);
		return mv;
	}

	/**
	 * 生成试卷页面
	 * 
	 * @return
	 */
	@RequestMapping("/createexam")
	public String createExam(Model model, HttpServletRequest request) {
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
		model.addAttribute("typeList", typeList);
		// 标签库
		List<QuestionTag> qttList = questionTagService.questionTag();
		model.addAttribute("qttList", qttList);

		model.addAttribute("sList", sList);
		model.addAttribute("gList", gList);
		// 循环拿到题型
		List<QuestionsType> qtList = qtypeService.questionsType();
		model.addAttribute("qtList", qtList);
		// 循环拿到教材
		List<QuestionFillType> fillList = fillTypeService.getQuestionFillTypeList();
		model.addAttribute("fillList", fillList);
		// 循环拿到填空题题目类型
		model.addAttribute("tMaterialsList", tMaterialsList);
		// 拿到范围
		List<RangeLesson> rlList = lessonService.findLesson();
		model.addAttribute("rlList", rlList);
		return "exampaper/createexam";
	}
	
	@RequestMapping("/createexam1")
	@ResponseBody
	public String QurstionList(Integer gradeId,Integer subject,Integer handoutType,Integer pageNo, Integer pageSize,HttpServletRequest request){
/*		List<String> questionSingleList = new ArrayList<String>();
		if(topicId==1){  //单选题			
			List<QuestionSingleChoice> questionSingleChoiceList = singlechoiceService.getQuestionBysubjectAndgrade(subjectId, gradeId);
			for(QuestionSingleChoice tem : questionSingleChoiceList){
				String questionSingle = tem.getChoiceDesc()+"A."+tem.getOptionA()+"B."+tem.getOptionB()+"C."+tem.getOptionC()+"D."+tem.getOptionD();
				questionSingleList.add(questionSingle);
			}
		}
		
		if(topicId==2){ //d多选
			List<QuestionMulitChoice> qmcList = mulitchoiceService.questionMulitByGradeIdAndSubjectId(subjectId, gradeId);
			for(QuestionMulitChoice tem : qmcList){
				String qmc = tem.getChoiceDesc1()+"A."+tem.getOptionA1()+"B."+tem.getOptionB1()+"C."+tem.getOptionC1()+"D."+tem.getOptionD1();
				questionSingleList.add(qmc);
			}
		}
		
		if(topicId==3){//判断题
			 List<QuestionJudge> qjList = judgeService.getJudgeBySubjectAndKnowledge(subjectId, gradeId);
		}*/
		
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
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<TeacherAuthority> taList = authorityService.getTeacherIdById(teacher.getTeacherId());
		if (!teacher.getTeacherType().equals("5") && !teacher.getTeacherType().equals("6")) {// 超级管理员能看到所有的
			if (handoutType == 1) {
				worsCount = singlechoiceService.getAllQuestionsCount(handoutType, subject, null, null,
						null, gradeId);
				List<QuestionSingleChoice> qscList = singlechoiceService.getAllQuestions((pageNo - 1) * pageSize,
						pageSize, handoutType, subject, null, null, null, gradeId);
				if (!qscList.isEmpty() && qscList != null) {
					map.put("singleList", qscList);
				} else {
					map.put("singleList", null);
				}
			} else if (handoutType == 2) {
				worsCount = mulitchoiceService.getAllQuestionsCount(handoutType, subject, null, null,
						null, gradeId);
				List<QuestionMulitChoice> qmcList = mulitchoiceService.getAllQuestions((pageNo - 1) * pageSize,
						pageSize, handoutType, subject, null, null, null, gradeId);
				if (!qmcList.isEmpty() && qmcList != null) {
					map.put("mulitList", qmcList);
				} else {
					map.put("mulitList", null);
				}
			} else if (handoutType == 3) {
				worsCount = judgeService.getAllQuestionsCount(handoutType, subject, null, null, null,
						gradeId);
				List<QuestionJudge> qjList = judgeService.getAllQuestions((pageNo - 1) * pageSize, pageSize,
						handoutType, subject, null, null, null, gradeId);
				if (!qjList.isEmpty() && qjList != null) {
					map.put("judgeList", qjList);
				} else {
					map.put("judgeList", null);
				}
			} else if (handoutType == 4) {
				worsCount = readService.getAllQuestionsCount(handoutType, subject, null, null, null,
						gradeId);
				List<QuestionRead> reads = readService.getAllQuestions((pageNo - 1) * pageSize, pageSize, handoutType,
						subject, null, null, null, gradeId);
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
				worsCount = questionService.getAllQuestionsCount(handoutType, subject, null, null, null,
						gradeId);
				List<SubjectiveQuestion> sqList = questionService.getSubjectiveQuestion((pageNo - 1) * pageSize,
						pageSize, handoutType, subject, null, null, null, gradeId);
				if (sqList != null && !sqList.isEmpty()) {
					map.put("sqList", sqList);
				} else {
					map.put("sqList", null);
				}
			} else if (handoutType == 6) {// 填空题
				List<QuestionFill> qfList = fillService.QuestionFillList((pageNo - 1) * pageSize, pageSize, handoutType,
						subject, null, null, null, gradeId);
				worsCount = fillService.getFillCount(handoutType, subject, null, null, null, gradeId);
				if (qfList != null && !qfList.isEmpty()) {
					map.put("qfList", qfList);
				} else {
					map.put("qfList", null);
				}
			} else if (handoutType == 7) {// 完形填空
				map.put("pageNo", (pageNo - 1) * pageSize);
				map.put("pageSize", pageSize);
				map.put("subject", subject);
				map.put("isPublic", null);
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
								null, null, teacher.getTeacherId(), null, gradeId);
						List<QuestionSingleChoice> singleChoices = singlechoiceService.getSingleByTeacherIdIsPublic(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), null, null, null, gradeId);// 获取单选题
						if (!singleChoices.isEmpty() && singleChoices != null) {
							map.put("singleList", singleChoices);
						} else {
							map.put("singleList", null);
						}
					} else if (handoutType == 2) {
						worsCount = mulitchoiceService.getconutIspublic(handoutType, teacher.getSubject(),
								null, null, teacher.getTeacherId(), null, gradeId);
						List<QuestionMulitChoice> mulitChoices = mulitchoiceService.getMulitByTeacherIdIsPublic(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), null, null, null, gradeId);// 获取多选题
						if (!mulitChoices.isEmpty() && mulitChoices != null) {
							map.put("mulitList", mulitChoices);
						} else {
							map.put("mulitList", null);
						}
					} else if (handoutType == 3) {
						worsCount = judgeService.getconutIspublic(handoutType, teacher.getSubject(), null,
								null, teacher.getTeacherId(), null, gradeId);
						List<QuestionJudge> judges = judgeService.getJudgeByTeacherIdIsPublic(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), null,
								null, null, gradeId);// 获取判断题
						if (!judges.isEmpty() && judges != null) {
							map.put("judgeList", judges);
						} else {
							map.put("judgeList", null);
						}
					} else if (handoutType == 4) {
						worsCount = readService.getconutIspublic(handoutType, teacher.getSubject(), null,
								null, teacher.getTeacherId(), null, gradeId);
						List<QuestionRead> reads = readService.getReadByTeacherIdIsPublic(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), null,
								null, null, gradeId);// 获取阅读理解
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
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), null,
								null, teacher.getTeacherId(), null, gradeId);
						worsCount = questionService.getIsPublicCount(handoutType, teacher.getSubject(), null,
								null, teacher.getTeacherId(), null, gradeId);
						if (sqList != null && !sqList.isEmpty()) {
							map.put("sqList", sqList);
						} else {
							map.put("sqList", null);
						}
					} else if (handoutType == 6) {// 填空题
						List<QuestionFill> qfList = fillService.getPublicQuestionFillList((pageNo - 1) * pageSize,
								pageSize, handoutType, teacher.getSubject(), null, null,
								teacher.getTeacherId(), null, gradeId);
						worsCount = fillService.getIsPublicCount(handoutType, teacher.getSubject(), null,
								null, teacher.getTeacherId(), null, gradeId);
						if (qfList != null && !qfList.isEmpty()) {
							map.put("qfList", qfList);
						} else {
							map.put("qfList", null);
						}
					} else if (handoutType == 7) {
						map.put("pageNo", (pageNo - 1) * pageSize);
						map.put("pageSize", pageSize);
						map.put("subject", teacher.getSubject());
						map.put("isPublic", null);
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
						worsCount = singlechoiceService.getconut(handoutType, teacher.getSubject(), null,
								null, teacher.getTeacherId(), null, gradeId);
						List<QuestionSingleChoice> singleChoices = singlechoiceService.getSingleByTeacherId(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), null, null, null, gradeId);// 获取单选题
						if (!singleChoices.isEmpty() && singleChoices != null) {
							map.put("singleList", singleChoices);
						} else {
							map.put("singleList", null);
						}
					} else if (handoutType == 2) {
						worsCount = mulitchoiceService.getcount(handoutType, teacher.getSubject(), null,
								null, teacher.getTeacherId(), null, gradeId);
						List<QuestionMulitChoice> mulitChoices = mulitchoiceService.getMulitByTeacherId(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType,
								teacher.getSubject(), null, null, null, gradeId);// 获取多选题
						if (!mulitChoices.isEmpty() && mulitChoices != null) {
							map.put("mulitList", mulitChoices);
						} else {
							map.put("mulitList", null);
						}
					} else if (handoutType == 3) {
						worsCount = judgeService.getcount(handoutType, teacher.getSubject(), null, null,
								teacher.getTeacherId(), null, gradeId);
						List<QuestionJudge> judges = judgeService.getJudgeByTeacherId(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), null,
								null, null, gradeId);// 获取判断题
						if (!judges.isEmpty() && judges != null) {
							map.put("judgeList", judges);
						} else {
							map.put("judgeList", null);
						}
					} else if (handoutType == 4) {
						worsCount = readService.getcount(handoutType, teacher.getSubject(), null, null,
								teacher.getTeacherId(), null, gradeId);
						List<QuestionRead> reads = readService.getReadByTeacherId(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), null,
								null, null, gradeId);// 获取阅读理解
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
						worsCount = questionService.getPrivateCount(handoutType, teacher.getSubject(), null,
								null, teacher.getTeacherId(), null, gradeId);
						List<SubjectiveQuestion> sqList = questionService.getPrivateSubjectiveQuestion(
								(pageNo - 1) * pageSize, pageSize, handoutType, teacher.getSubject(), null,
								null, teacher.getTeacherId(), null, gradeId);
						if (sqList != null && !sqList.isEmpty()) {
							map.put("sqList", sqList);
						} else {
							map.put("sqList", null);
						}
					} else if (handoutType == 6) {// 填空题
						List<QuestionFill> qfList = fillService.getPrivateQuestionFillList((pageNo - 1) * pageSize,
								pageSize, handoutType, teacher.getSubject(), null, null,
								teacher.getTeacherId(), null, gradeId);
						worsCount = fillService.getPrivateFillCount(handoutType, teacher.getSubject(), null,
								null, teacher.getTeacherId(), null, gradeId);
						if (qfList != null && !qfList.isEmpty()) {
							map.put("qfList", qfList);
						} else {
							map.put("qfList", null);
						}
					} else if (handoutType == 7) {
						map.put("pageNo", (pageNo - 1) * pageSize);
						map.put("pageSize", pageSize);
						map.put("subject", teacher.getSubject());
						map.put("isPublic", null);
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
		mv.addObject("sList", sList);
		List<QuestionsType> qtList = questionsTypeService.questionsType();
		mv.addObject("qtList", qtList);
		mv.addObject("gradeList", grades);
		mv.addObject("page", pageNo);
		mv.addObject("gradeId", gradeId);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("handoutType", handoutType);
		mv.addObject("isPublic", null);
		mv.addObject("subject", subject);
		mv.addObject("questionDegree", null);		
		
		
		return mv.toString();
	}
	
	/**
	 * 查看试卷详情页面
	 * 
	 * @return
	 */
	@RequestMapping("/watchexam")
	public ModelAndView watchExam(Model model, Integer piId, HttpServletResponse response) {
		// PaperInfo pInfo = paperInfoService.getPaperInfoById(piId);
		List pList = new ArrayList<>();
		List<PaperDetails> detail_single = paperDetailsService.getPaperDetailsByPaperId(1, piId);
		List<PaperDetails> detail_mulit = paperDetailsService.getPaperDetailsByPaperId(2, piId);
		List<PaperDetails> detail_judge = paperDetailsService.getPaperDetailsByPaperId(3, piId);
		List<PaperDetails> detail_read = paperDetailsService.getPaperDetailsByPaperId(4, piId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("single", detail_single);
		map.put("mulit", detail_mulit);
		map.put("judge", detail_judge);
		map.put("read", detail_read);
		pList.add(map);
		ModelAndView view = new ModelAndView();
		view.setViewName("exampaper/watchexam");
		view.addObject("detail_List", map);
		return view;
	}

	/**
	 * 新增题目页面
	 * 
	 * @return
	 */
	@RequestMapping("/newexam")
	public ModelAndView newExam(Questions q) {
		ModelAndView mav = new ModelAndView();

		List<Questions> qList = questionBankService.queryQuestionList(q);
		mav.addObject("qList", qList);
		List<QuestionTag> qtList = questionTagService.questionTag();
		mav.addObject("qtList", qtList);
		List<QuestionsType> qqtList = qtypeService.questionsType();
		mav.addObject("qqtList", qqtList);
		mav.setViewName("exampaper/newexam");
		return mav;
	}

	/**
	 * 增加标签
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addQuestionTag", method = RequestMethod.POST)
	public ModelAndView addQuestionTag(String tagName, HttpServletRequest request, Questions q) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		questionTagService.saveQuestionTag(tagName, currTeacher.getTeacherId());
		return this.newExam(q);

	}

	/**
	 * 后台添加试卷
	 * 
	 * @param pInfo
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/savePaperInfo", method = RequestMethod.POST)
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		// 先拿到当前登录的用户编号，然后去教师权限表看看有没有读写试卷的权限
		String name = request.getParameter("piName");
		String paper_explain = request.getParameter("paperExplain");
		int subject = Integer.parseInt(request.getParameter("subject"));
		int type = Integer.parseInt(request.getParameter("piType"));
		int grade = Integer.parseInt(request.getParameter("grade"));
		Integer science = null;
		if (request.getParameter("science") != null && !request.getParameter("science").equals("")) {
			science = Integer.parseInt(request.getParameter("science"));
		}
		Integer paperTime = Integer.parseInt(request.getParameter("paperTime"));
		Double totalScore = Double.parseDouble(request.getParameter("totalScore"));
		Integer end = null;
		Integer begin = null;
		if (request.getParameter("knowledgeBegin") != null && request.getParameter("knowledgeBegin")!= "") {
			 begin = Integer.parseInt(request.getParameter("knowledgeBegin"));
		}
		if (request.getParameter("knowledgeEnd") != null && request.getParameter("knowledgeEnd")!= "") {
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
		TeacherAuthority ta= authorityService.findIsTeacherGradeById(teacher.getTeacherId(), String.valueOf(grade));
		if (!teacher.getTeacherType().equals("5") && !teacher.getTeacherType().equals("6")) {
			System.out.println("json数组的长度：" + array.size());
			PaperInfo info = new PaperInfo();
			info.setPiName(name);
			if (paper_explain != null && paper_explain != "") {
				info.setPaperExplain(paper_explain);
			}
			if (paperTime != null) {
				info.setPaperTime(paperTime);
			}
			info.setSubject(subject);
			info.setPiType(type);
			info.setGrade(grade);
			if (science != null) {
				info.setScience(science);
			}
			if (begin != null) {
				info.setKnowledgeBegin(begin);
			}
			if (end != null) {
				info.setKnowledgeEnd(end);
			}
			info.setDifficultyValue(degree);
			info.setUsedTimes(0);// 试卷使用数
			info.setTopicTotai(array.size());// 试卷题目
			info.setTotalScore(totalScore);// 试卷总分
			info.setBuildUser(teacher.getTeacherId());
			info.setIsPublic(0);// 是否公开【默认不公开】
			info.setBuildUserName(teacher.getTeacherName());
			info.setCreateTime(new Date());
			PaperDetails details = new PaperDetails();
			paperInfoService.addPaperInfo(info);// 返回主键
			PaperInfo info2 = paperInfoService.getPaperInfoById(info.getPiId());
			if (info2 == null) {
				output(response, JsonUtil.buildFalseJson("1", "添加失败"));
			} else {
				for (int i = 0; i < array.size(); i++) {
					JsonObject jo2 = (JsonObject) array.get(i);
					if (Integer.valueOf(jo2.get("type").toString()) == 1) {
						QuestionSingleChoice choice = new QuestionSingleChoice();
						if (choice.getUseCount() != null) {
							choice.setUseCount(choice.getUseCount() + 1);
						} else {
							choice.setUseCount(1);
						}
						choice.setChoiceId(Integer.valueOf(jo2.get("choiceId").toString()));
						choice.setUpdateTime(new Date());
						singleservice.updateUsecount(choice);
						details.setQuestionId(Integer.valueOf(jo2.get("choiceId").toString()));// 题目编号
						details.setValue(Double.valueOf(jo2.get("score").toString()));// 单选题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 2) {
						QuestionMulitChoice choice = new QuestionMulitChoice();
						if (choice.getUseCount() != null) {
							choice.setUseCount(choice.getUseCount() + 1);
						} else {
							choice.setUseCount(1);
						}
						choice.setChoiceId(Integer.valueOf(jo2.get("choiceId").toString()));
						choice.setUpdateTime(new Date());
						mulitservice.updateUsecount(choice);
						details.setQuestionId(Integer.valueOf(jo2.get("choiceId").toString()));// 题目编号
						details.setValue(Double.valueOf(jo2.get("score1").toString()));// 多选题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 3) {
						QuestionJudge judge = new QuestionJudge();
						if (judge.getUseCount() != null) {
							judge.setUseCount(judge.getUseCount() + 1);
						} else {
							judge.setUseCount(1);
						}
						judge.setJudgeId(Integer.valueOf(jo2.get("judgeId").toString()));
						judge.setUpdateTime(new Date());
						judgeservice.updateUsecount(judge);
						details.setQuestionId(Integer.valueOf(jo2.get("judgeId").toString()));// 题目编号
						details.setValue(Double.valueOf(jo2.get("score2").toString()));// 判断题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 4) {
						QuestionRead read = new QuestionRead();
						if (read.getUseCount() != null) {
							read.setUseCount(read.getUseCount() + 1);
						} else {
							read.setUseCount(1);
						}
						read.setReadId(Integer.valueOf(jo2.get("readId").toString()));
						read.setUpdateTime(new Date());
						readservice.updateUsecount(read);
						details.setQuestionId(Integer.valueOf(jo2.get("readId").toString()));// 题目编号
						List<QuestionRead> qrList = readservice
								.getReadChildByParentId(Integer.valueOf(jo2.get("readId").toString()));
						details.setValue(Double.valueOf(jo2.get("score_read").toString()) * qrList.size());// 阅读题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 5) {
						SubjectiveQuestion sq = new SubjectiveQuestion();
						if (sq.getUsedCount() != null) {
							sq.setUsedCount(sq.getUsedCount() + 1);
						} else {
							sq.setUsedCount(1);
						}
						sq.setSubjectiveId(Integer.valueOf(jo2.get("subjectiveId").toString()));
						sq.setUpdateTime(new Date());
						questionService.updatesetUsedCount(sq);
						details.setQuestionId(Integer.valueOf(jo2.get("subjectiveId").toString()));// 题目编号
						details.setValue(Double.valueOf(jo2.get("questionScore").toString()));// 多选题分数
					} else if (Integer.valueOf(jo2.get("type").toString()) == 6) {
						QuestionFill fill = new QuestionFill();
						if (fill.getUsedCount() != null) {
							fill.setUsedCount(fill.getUsedCount() + 1);
						} else {
							fill.setUsedCount(1);
						}
						fill.setFillId(Integer.valueOf(jo2.get("fillId").toString()));
						fill.setUpdateTime(new Date());
						fillService.updateIsPublic(fill);
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
					details.setPaperId(info2.getPiId());// 试卷编号
					details.setCreateTime(new Date());
					paperDetailsService.savePaperDetails(details);// 添加试卷，返回主键
				}
				double paperScore = 0.0;
				List<PaperDetails> pdList = paperDetailsService.getPaperDetailsListById(details.getPaperId());
				for (PaperDetails pd : pdList) {
					paperScore += pd.getValue();
				}
				info2.setUpdateTime(new Date());
				info2.setTotalScore(paperScore);
				paperInfoService.updateTopictotal(info2);
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("试卷管理");
				log1.setOperationType("新增");
				log1.setOperationResult("成功");
				log1.setOperationId(teacher.getTeacherId().toString());
				log1.setOperationName(teacher.getTeacherName());
				log1.setOperationContent("管理员【" + teacher.getTeacherName() + "】添加试卷【" + name + "成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
				output(response, JsonUtil.buildFalseJson("0", "添加成功！"));
			}
		} else {
			if (ta != null) {
				if ((ta.getPaperAuthority()==0 || ta.getPaperAuthority()==1)){
					output(response, JsonUtil.buildFalseJson("2", "对不起，您没有录入试卷的权限!"));
				} else if (!ta.getGrade().equals(String.valueOf(grade))) {
					output(response, JsonUtil.buildFalseJson("3", "您没有录入试卷的年级与您现有的年级权限不一致!"));
				}else {

					System.out.println("json数组的长度：" + array.size());
					PaperInfo info = new PaperInfo();
					info.setPiName(name);
					if (paper_explain != null && paper_explain != "") {
						info.setPaperExplain(paper_explain);
					}
					if (paperTime != null) {
						info.setPaperTime(paperTime);
					}
					info.setSubject(subject);
					info.setPiType(type);
					info.setGrade(grade);
					if (science != null) {
						info.setScience(science);
					}
					if (begin != null) {
						info.setKnowledgeBegin(begin);
					}
					if (end != null) {
						info.setKnowledgeEnd(end);
					}
					info.setDifficultyValue(degree);
					info.setUsedTimes(0);// 试卷使用数
					info.setTopicTotai(array.size());// 试卷题目
					info.setTotalScore(totalScore);// 试卷总分
					info.setBuildUser(teacher.getTeacherId());
					info.setIsPublic(0);// 是否公开【默认不公开】
					info.setBuildUserName(teacher.getTeacherName());
					info.setCreateTime(new Date());
					PaperDetails details = new PaperDetails();
					paperInfoService.addPaperInfo(info);// 返回主键
					PaperInfo info2 = paperInfoService.getPaperInfoById(info.getPiId());
					if (info2 == null) {
						output(response, JsonUtil.buildFalseJson("1", "添加失败"));
					} else {
						for (int i = 0; i < array.size(); i++) {
							JsonObject jo2 = (JsonObject) array.get(i);
							if (Integer.valueOf(jo2.get("type").toString()) == 1) {
								QuestionSingleChoice choice = new QuestionSingleChoice();
								if (choice.getUseCount() != null) {
									choice.setUseCount(choice.getUseCount() + 1);
								} else {
									choice.setUseCount(1);
								}
								choice.setChoiceId(Integer.valueOf(jo2.get("choiceId").toString()));
								choice.setUpdateTime(new Date());
								singleservice.updateUsecount(choice);
								details.setQuestionId(Integer.valueOf(jo2.get("choiceId").toString()));// 题目编号
								details.setValue(Double.valueOf(jo2.get("score").toString()));// 单选题分数
							} else if (Integer.valueOf(jo2.get("type").toString()) == 2) {
								QuestionMulitChoice choice = new QuestionMulitChoice();
								if (choice.getUseCount() != null) {
									choice.setUseCount(choice.getUseCount() + 1);
								} else {
									choice.setUseCount(1);
								}
								choice.setChoiceId(Integer.valueOf(jo2.get("choiceId").toString()));
								choice.setUpdateTime(new Date());
								mulitservice.updateUsecount(choice);
								details.setQuestionId(Integer.valueOf(jo2.get("choiceId").toString()));// 题目编号
								details.setValue(Double.valueOf(jo2.get("score1").toString()));// 多选题分数
							} else if (Integer.valueOf(jo2.get("type").toString()) == 3) {
								QuestionJudge judge = new QuestionJudge();
								if (judge.getUseCount() != null) {
									judge.setUseCount(judge.getUseCount() + 1);
								} else {
									judge.setUseCount(1);
								}
								judge.setJudgeId(Integer.valueOf(jo2.get("judgeId").toString()));
								judge.setUpdateTime(new Date());
								judgeservice.updateUsecount(judge);
								details.setQuestionId(Integer.valueOf(jo2.get("judgeId").toString()));// 题目编号
								details.setValue(Double.valueOf(jo2.get("score2").toString()));// 判断题分数
							} else if (Integer.valueOf(jo2.get("type").toString()) == 4) {
								QuestionRead read = new QuestionRead();
								if (read.getUseCount() != null) {
									read.setUseCount(read.getUseCount() + 1);
								} else {
									read.setUseCount(1);
								}
								read.setReadId(Integer.valueOf(jo2.get("readId").toString()));
								read.setUpdateTime(new Date());
								readservice.updateUsecount(read);
								details.setQuestionId(Integer.valueOf(jo2.get("readId").toString()));// 题目编号
								List<QuestionRead> qrList = readservice
										.getReadChildByParentId(Integer.valueOf(jo2.get("readId").toString()));
								details.setValue(Double.valueOf(jo2.get("score_read").toString()) * qrList.size());// 阅读题分数
							} else if (Integer.valueOf(jo2.get("type").toString()) == 5) {
								SubjectiveQuestion sq = new SubjectiveQuestion();
								if (sq.getUsedCount() != null) {
									sq.setUsedCount(sq.getUsedCount() + 1);
								} else {
									sq.setUsedCount(1);
								}
								sq.setSubjectiveId(Integer.valueOf(jo2.get("subjectiveId").toString()));
								sq.setUpdateTime(new Date());
								questionService.updatesetUsedCount(sq);
								details.setQuestionId(Integer.valueOf(jo2.get("subjectiveId").toString()));// 题目编号
								details.setValue(Double.valueOf(jo2.get("questionScore").toString()));// 多选题分数
							} else if (Integer.valueOf(jo2.get("type").toString()) == 6) {
								QuestionFill fill = new QuestionFill();
								if (fill.getUsedCount() != null) {
									fill.setUsedCount(fill.getUsedCount() + 1);
								} else {
									fill.setUsedCount(1);
								}
								fill.setFillId(Integer.valueOf(jo2.get("fillId").toString()));
								fill.setUpdateTime(new Date());
								fillService.updateIsPublic(fill);
								details.setQuestionId(Integer.valueOf(jo2.get("fillId").toString()));// 题目编号
								details.setValue(Double.valueOf(jo2.get("questionScore").toString()));// 填空题分数
							}
							details.setQuestionType(Integer.valueOf(jo2.get("type").toString()));// 题型
							details.setPaperId(info2.getPiId());// 试卷编号
							details.setCreateTime(new Date());
							paperDetailsService.savePaperDetails(details);// 添加试卷，返回主键
						}
						double paperScore = 0.0;
						List<PaperDetails> pdList = paperDetailsService.getPaperDetailsListById(details.getPaperId());
						for (PaperDetails pd : pdList) {
							paperScore += pd.getValue();
						}
						info2.setUpdateTime(new Date());
						info2.setTotalScore(paperScore);
						paperInfoService.updateTopictotal(info2);
						Log log1 = new Log();
						log1.setStartTime(new Date());
						log1.setModular("试卷管理");
						log1.setOperationType("新增");
						log1.setOperationResult("成功");
						log1.setOperationId(teacher.getTeacherId().toString());
						log1.setOperationName(teacher.getTeacherName());
						log1.setOperationContent("管理员【" + teacher.getTeacherName() + "】添加试卷【" + name + "成功!");
						log1.setEndTime(new Date());
						logService.saveLog(log1);
						output(response, JsonUtil.buildFalseJson("0", "添加成功！"));
					}
				}
			}else {
				output(response, JsonUtil.buildFalseJson("2", "对不起，您没有录入试卷的权限!"));
			}
		}
	}

	/**
	 * 按条件模糊查询，然后显示出来
	 * 
	 * @return
	 */
	@RequestMapping(value = "/fuzzyQuery", method = RequestMethod.POST)
	public void fuzzyQuery(Integer type, Integer number, Integer subject, String tagId, HttpServletRequest request,
			HttpServletResponse response, Integer grade, Integer science, Integer score) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		TeacherAuthority ta = authorityService.findIsTeacherGradeById(currTeacher.getTeacherId(),
				String.valueOf(grade));
		/**
		 * 1)先判断当前登录的是否是超级管理员。是则执行if,不是则执行else。 2)在判断有没有写的权限
		 */
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			// 1.当前登录的是超级管理员。
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
					List<QuestionSingleChoice> list = singleservice.getSingleByRandAdmin(map);
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
					List<QuestionMulitChoice> list = mulitservice.getMulitByRandAdmin(map);
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
					List<QuestionJudge> list = judgeservice.getJudgeByRandAdmin(map);
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
					List<QuestionRead> list = readservice.getReadByRandAdmin(map);
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
				}else if (type ==7) {
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
						log.info("获取完形填空数据成功!" + clozes + ",总共有" + clozes.size() + "条记录");
					}
				}
			}
		} else {
			// 1.不是超级管理员。2.在判断有没有写的权限
			if (ta == null) {
				output(response, JsonUtil.buildFalseJson("4", "你没有读写试题的权限!"));
			} else if (ta != null && ta.getPaperAuthority() == 0 || ta.getPaperAuthority() == 1) {
				output(response, JsonUtil.buildFalseJson("1", "你没有写试题的权限!"));
			} else if (ta != null && ta.getPaperAuthority() == 0 || ta.getPaperAuthority() == 2) {
				output(response, JsonUtil.buildFalseJson("3", "你没有读试题的权限!"));
			} else {
				// 有写的权限
				String tags1 = null, tags2 = null, tags3 = null, tags4 = null, tags5 = null, tags6 = null, tags7 = null,
						tags8 = null, tags9 = null, tags10 = null;
				String[] tags = tagId.split(",");
				if (tags.length > 9) {
					output(response, JsonUtil.buildFalseJson("2", "标签最多能选10个!"));
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
						List<QuestionSingleChoice> list = singleservice.getSingleByRandAdminIsPublic(map);
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
						List<QuestionMulitChoice> list = mulitservice.getMulitByRandAdminIsPublic(map);
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
						List<QuestionJudge> list = judgeservice.getJudgeByRandAdminIsPublic(map);
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
						List<QuestionRead> list = readservice.getReadByRandAdminIsPublic(map);
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
					}else if (type ==7) {
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
	 * 删除试卷
	 * 
	 * @return
	 */
	@RequestMapping("/delete")
	public void delete(Integer piId, HttpServletResponse response, HttpServletRequest request) {
		PaperInfo pInfo = paperInfoService.getPaperInfoById(piId);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (!currTeacher.getTeacherType().equals("1") && !currTeacher.getTeacherType().equals("2")
				&& !currTeacher.getTeacherType().equals("3") && !currTeacher.getTeacherType().equals("4")
				&& currTeacher.getTeacherId() != pInfo.getBuildUser()) {
			output(response, JsonUtil.buildFalseJson("2", "对不起，您没有删除作业的权限!"));
		} else if (pInfo.getUsedTimes() > 0) {
			output(response, JsonUtil.buildFalseJson("1", "该试卷已被使用，不能删除!"));
		} else {
			try {
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("试卷管理");
				log1.setOperationType("删除");
				log1.setOperationResult("成功");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】删除试卷【" + pInfo.getPiName() + "】成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
				paperInfoService.deletePaperInfo(piId);
				output(response, JsonUtil.buildFalseJson("0", "删除试卷成功!"));
				log.info("管理员【" + currTeacher.getTeacherName() + "】删除试卷【" + pInfo.getPiName() + "】成功!");
			} catch (Exception e) {
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("试卷管理");
				log1.setOperationType("删除");
				log1.setOperationResult("失败");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】删除试卷【" + pInfo.getPiName() + "】失败!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
				output(response, JsonUtil.buildFalseJson("3", "删除试卷失败!"));
				log.info("管理员【" + currTeacher.getTeacherName() + "】删除试卷【" + pInfo.getPiName() + "】失败!");
			}
		}
	}

	/**
	 * 共享试卷
	 * 
	 * @param piId
	 * @param pInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/share")
	public void share(Integer piId, Integer ispublic, HttpServletRequest request, HttpServletResponse response) {
		PaperInfo paperInfo = paperInfoService.getPaperInfoById(piId);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (paperInfo != null && paperInfo.getUsedTimes() > 0 && paperInfo.getIsPublic() != 0) {
			output(response, JsonUtil.buildFalseJson("2", "该试卷已被使用，暂时不能设置私有!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				paperInfo.setIsPublic(ispublic);
				paperInfo.setUpdateTime(new Date());
				int len = paperInfoService.updatePaper(paperInfo);
				if (len > 0) {
					log.info("共享试卷成功");
					output(response, JsonUtil.buildFalseJson("0", "更新试卷状态成功!"));
				} else {
					log.info("共享试卷失败");
					output(response, JsonUtil.buildFalseJson("1", "更新试卷状态失败，请重试!"));
				}
			} else {
				if (paperInfo != null && !currTeacher.getTeacherId().equals(paperInfo.getBuildUser())) {
					output(response, JsonUtil.buildFalseJson("3", "你不是试卷的创建者也不是管理员，不能对试卷进行修改!"));
				} else {
					paperInfo.setIsPublic(ispublic);
					paperInfo.setUpdateTime(new Date());
					int len = paperInfoService.updatePaper(paperInfo);
					if (len > 0) {
						log.info("共享试卷成功");
						output(response, JsonUtil.buildFalseJson("0", "更新试卷状态成功!"));
					} else {
						log.info("共享试卷失败");
						output(response, JsonUtil.buildFalseJson("1", "更新试卷状态失败，请重试!"));
					}
				}
			}
		}
	}
    
	/**
	 * 开启/停用试卷
	 * @param piId
	 * @param isStart
	 */
	@RequestMapping("/changeexamstatus")
	public void changeExamStatus(HttpServletResponse response,HttpServletRequest request,Integer piId,Integer isStart){		
		PaperInfo paperInfo = paperInfoService.getPaperInfoById(piId);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				paperInfo.setIsStart(isStart);
				paperInfo.setUpdateTime(new Date());
				int len = paperInfoService.updatePaper(paperInfo);
				if (len > 0) {
					log.info("更新试卷状态成功");
					output(response, JsonUtil.buildFalseJson("0", "更新试卷状态成功!"));
				} else {
					log.info("更新试卷状态失败");
					output(response, JsonUtil.buildFalseJson("1", "更新试卷状态失败，请重试!"));
				}
			} else {
				if (paperInfo != null && !currTeacher.getTeacherId().equals(paperInfo.getBuildUser())) {
					output(response, JsonUtil.buildFalseJson("3", "你不是试卷的创建者也不是管理员，不能对试卷进行修改!"));
				} else {
					paperInfo.setIsStart(isStart);
					paperInfo.setUpdateTime(new Date());
					int len = paperInfoService.updatePaper(paperInfo);
					if (len > 0) {
						log.info("共享试卷成功");
						output(response, JsonUtil.buildFalseJson("0", "更新试卷状态成功!"));
					} else {
						log.info("共享试卷失败");
						output(response, JsonUtil.buildFalseJson("1", "更新试卷状态失败，请重试!"));
					}
				}
			}				
	}
	/**
	 * 跳转修改页面
	 * 
	 * @param request
	 * @param piId
	 * @return
	 */
	@RequestMapping("/modity")
	public ModelAndView modity(Integer piId, Integer pageNo, Integer pageSize,HttpServletRequest request) {

		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 6;
		}
		int rowsCount = paperDetailsService.getCount(piId);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<PaperDetails> pdList = paperDetailsService.findPaperList(piId, (pageNo - 1) * pageSize, pageSize);
		List<SeePaper> spList = new ArrayList<SeePaper>();
		for (PaperDetails pd : pdList) {
			if (pd.getQuestionType() == 1) {// 单选
				QuestionSingleChoice qsc = singleservice.getSingleChoiceById(pd.getQuestionId());
				SeePaper sp = new SeePaper();
				sp.setPaperId(pd.getPaperId());
				sp.setId(pd.getId());
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
				QuestionMulitChoice qmc = mulitservice.getMulitChoiceById(pd.getQuestionId());
				SeePaper sp1 = new SeePaper();
				sp1.setPaperId(pd.getPaperId());
				sp1.setId(pd.getId());
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
				QuestionJudge qj = judgeservice.getJudgeById(pd.getQuestionId());
				SeePaper sp2 = new SeePaper();
				sp2.setPaperId(pd.getPaperId());
				sp2.setId(pd.getId());
				sp2.setQuestionId(pd.getQuestionId());
				sp2.setQuestiontype(pd.getQuestionType());
				sp2.setQuestionValue(pd.getValue());
				sp2.setJudgeDesc(qj.getJudgeDesc());
				spList.add(sp2);
			}
			if (pd.getQuestionType() == 4) {// 阅读理解
				SeePaper sp3 = new SeePaper();
				QuestionRead qRead = readservice.getReadById(pd.getQuestionId());
				List<QuestionPaer> qqrrList = new ArrayList<QuestionPaer>();
				List<QuestionRead> qrList = readservice.getReadChildByParentId(qRead.getReadId());
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
				sp3.setPaperId(pd.getPaperId());
				sp3.setId(pd.getId());
				sp3.setQuestionId(pd.getQuestionId());
				sp3.setQuestiontype(pd.getQuestionType());
				sp3.setQuestionValue(pd.getValue());
				sp3.setReadDesc(qRead.getReadDesc());
				spList.add(sp3);
			}
			if (pd.getQuestionType() == 5) {// 主观题
				SeePaper sp4 = new SeePaper();
				SubjectiveQuestion subjective = questionService.getSubjectiveQuestionById(pd.getQuestionId());
				sp4.setPaperId(pd.getPaperId());
				sp4.setId(pd.getId());
				sp4.setQuestionId(pd.getQuestionId());
				sp4.setQuestiontype(pd.getQuestionType());
				sp4.setQuestionValue(pd.getValue());
				sp4.setSubjectiveTitle(subjective.getSubjectiveTitle());
				spList.add(sp4);
			}
			if (pd.getQuestionType() == 6) {// 填空题
				SeePaper sp5 = new SeePaper();
				QuestionFill fill = fillService.getQuestionFillById(pd.getQuestionId());
				sp5.setPaperId(pd.getPaperId());
				sp5.setId(pd.getId());
				sp5.setQuestionId(pd.getQuestionId());
				sp5.setQuestiontype(pd.getQuestionType());
				sp5.setQuestionValue(pd.getValue());
				sp5.setFillTitle(fill.getFillTitle());
				sp5.setFillAnswer(fill.getFillAnswer());
				spList.add(sp5);
			}if (pd.getQuestionType() == 7) {
				SeePaper sp6 = new SeePaper();
				QuestionCloze cloze = clozeService.getQuestionClozeById(pd.getQuestionId());
				sp6.setPaperId(pd.getPaperId());
				sp6.setId(pd.getId());
				sp6.setQuestionId(pd.getQuestionId());
				sp6.setQuestiontype(pd.getQuestionType());
				sp6.setQuestionValue(pd.getValue());
				sp6.setClozeContent(cloze.getClozeContent());
				spList.add(sp6);
			}
		}
		ModelAndView mv = new ModelAndView();
		
		
		
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
		// 拿到范围
		List<RangeLesson> rlList = lessonService.findLesson();
		mv.addObject("rlList", rlList);
		mv.addObject("spList", spList);
		PaperInfo info = paperInfoService.getPiIdById(piId);
		mv.addObject("paper", info);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.setViewName("exampaper/modityPaperInfo");
		return mv;
	}

	/**
	 * 删除试卷中的题目
	 * 
	 * @param paperId
	 * @param response
	 */
	@RequestMapping("/deleteTimu")
	public void deleteTimu(Integer paperId, Integer id,HttpServletResponse response, HttpServletRequest request) {
		PaperInfo pInfo = paperInfoService.getPaperInfoPiId(paperId);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (pInfo != null && pInfo.getUsedTimes() > 0) {
			output(response, JsonUtil.buildFalseJson("2", "该试卷已被使用，暂不能删除题目!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				paperDetailsService.paperDelete(id);
				pInfo.setTopicTotai(pInfo.getTopicTotai() - 1);// 改变试卷题目数量
				pInfo.setUpdateTime(new Date());
				paperInfoService.updateTopictotal(pInfo);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				log.info("删除题目成功");
			} else {
				if (pInfo != null && !currTeacher.getTeacherId().equals(pInfo.getBuildUser())) {
					output(response, JsonUtil.buildFalseJson("1", "你不是试卷的创建者也不是管理员，不能对试卷进行修改!"));
				} else {
					paperDetailsService.paperDelete(id);
					pInfo.setTopicTotai(pInfo.getTopicTotai() - 1);// 改变试卷题目数量
					pInfo.setUpdateTime(new Date());
					paperInfoService.updateTopictotal(pInfo);
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
					log.info("删除题目成功");
				}
			}
		}
	}

	/**
	 * 保存修改试卷
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updatePaperInfo")
	public void updatePaperInfo(PaperInfo pl, HttpServletResponse response, HttpServletRequest request) {
		PaperInfo pInfo = paperInfoService.getPaperInfoById(pl.getPiId());
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (pInfo != null && pInfo.getUsedTimes() != null && pInfo.getUsedTimes() > 0) {
			output(response, JsonUtil.buildFalseJson("1", "该试卷已被使用，无法修改！"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				pl.setUpdateTime(new Date());
				int len = paperInfoService.updatePaper(pl);
				if (len > 0) {
					output(response, JsonUtil.buildFalseJson("0", "修改成功！"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "修改失败，请重试！"));
				}
			} else {
				if (pInfo != null && !currTeacher.getTeacherId().equals(pInfo.getBuildUser())) {
					output(response, JsonUtil.buildFalseJson("3", "你不是试卷的创建者也不是管理员，不能对试卷进行修改!"));
				} else {
					pl.setUpdateTime(new Date());
					int len = paperInfoService.updatePaper(pl);
					if (len > 0) {
						output(response, JsonUtil.buildFalseJson("0", "修改成功！"));
					} else {
						output(response, JsonUtil.buildFalseJson("1", "修改失败，请重试！"));
					}
				}
			}
		}
	}

	/**
	 * 查看试卷详情
	 * 
	 * @param piId
	 * @return
	 */
	@RequestMapping("/paperTypeDetails")
	private ModelAndView paperDetails(Integer piId, HttpServletResponse response, Integer pageNo,Integer type) {
		ModelAndView view = new ModelAndView();
		Double singlescore = 0.0, mulitscore = 0.0, judgescore = 0.0, readscore = 0.0, subjective = 0.0, fill = 0.0,cloze=0.0;
		int singleChoice, Judge, Read, mulitChoice, subCount, fillCount,clozeCount;
		Double danxuan = 0.0, duoxuan = 0.0, pduan = 0.0, yuedu = 0.0, question = 0.0, fillScore = 0.0,clozeScore=0.0;
		PaperInfo pInfo = paperInfoService.getPaperInfoById(piId);
	    //拿到题目顺序
		String singleOrder,multOrder,judgeOrder,fillOrder,subOrder,readOrder,clozeOrder;
		List<QuestionSingleChoice> singles = singleservice.findPaperInfoDetails(piId);
		List<QuestionMulitChoice> mulits = mulitservice.findPaperInfoDetails(piId);
		List<QuestionJudge> judges = judgeservice.findPaperInfoDetails(piId);
		List<QuestionRead> reads = readservice.findPaperInfoDetails(piId);
		List<SubjectiveQuestion> subjectives = questionService.findPaperInfoDetails(piId);
		List<QuestionFill> qfList = fillService.findPaperInfoDetails(piId);
		List<QuestionCloze> clozes = clozeService.findPaperInfoDetails(piId);
		List<QuestionRead> qrList = null;
		int i =1;
		if (!singles.isEmpty()) {
			if(i==1){
				singleOrder ="一";
				view.addObject("singleOrder",singleOrder);
				i++;
			}
		}
		if (!mulits.isEmpty()) {
			if(i==1){
				multOrder ="一";
				i++;
				view.addObject("multOrder",multOrder);
			}else{
				multOrder ="二";	
				i++;
				view.addObject("multOrder",multOrder);
			}
			
		}
		if (!judges.isEmpty()) {
			if(i==1){
				judgeOrder ="一";
				i++;
				view.addObject("judgeOrder",judgeOrder);
			}else if(i==2){
				judgeOrder ="二";
				i++;
				view.addObject("judgeOrder",judgeOrder);
			}else{
				judgeOrder ="三";
				i++;
				view.addObject("judgeOrder",judgeOrder);
			}
		}
		if (!reads.isEmpty()) {
			if(i==1){
				readOrder ="一";
				i++;
				view.addObject("readOrder",readOrder);
			}else if(i==2){
				readOrder ="二";
				i++;
				view.addObject("readOrder",readOrder);
			}else if(i==3){
				readOrder ="三";
				i++;
				view.addObject("readOrder",readOrder);
			}else{
				readOrder ="四";
				i++;
				view.addObject("readOrder",readOrder);
			}
		}
		if (!subjectives.isEmpty()) {
			if(i==1){
				subOrder ="一";
				i++;
				view.addObject("subOrder",subOrder);
			}else if(i==2){
				subOrder ="二";
				i++;
				view.addObject("subOrder",subOrder);
			}else if(i==3){
				subOrder ="三";
				i++;
				view.addObject("subOrder",subOrder);
			}else if(i==4){
				subOrder ="四";
				i++;
				view.addObject("subOrder",subOrder);
			}else{
				subOrder ="五";
				i++;
				view.addObject("subOrder",subOrder);
			}
		}
		if (!qfList.isEmpty()) {
			if(i==1){
				fillOrder ="一";
				i++;
				view.addObject("fillOrder",fillOrder);
			}else if(i==2){
				fillOrder ="二";
				i++;
				view.addObject("fillOrder",fillOrder);
			}else if(i==3){
				fillOrder ="三";
				i++;
				view.addObject("fillOrder",fillOrder);
			}else if(i==4){
				fillOrder ="四";
				i++;
				view.addObject("fillOrder",fillOrder);
			}else if(i==5){
				fillOrder ="五";
				i++;
				view.addObject("fillOrder",fillOrder);
			}else{
				fillOrder ="六";
				i++;
				view.addObject("fillOrder",fillOrder);
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
			qrList = readservice.getReadChildByParentId(qr.getPaperDetails().getQuestionId());
		}
		for (QuestionRead read : reads) {
			List<QuestionRead> readChilds = readservice.getReadChildByParentId(read.getReadId());
			read.setChildDetail(readChilds);
			if (read.getPaperDetails().getValue() != null) {
				readscore += read.getPaperDetails().getValue();
			}
		}
		for (SubjectiveQuestion sub : subjectives) {
			if (sub.getPaperDetails().getValue() != null) {
				subjective += sub.getPaperDetails().getValue();
			}
		}
		for (QuestionFill sub : qfList) {
			if (sub.getPaperDetails().getValue() != null) {
				fill += sub.getPaperDetails().getValue();
			}
		}
		
		for (QuestionCloze c:clozes) {
			if (c.getPaperDetails().getValue() != null) {
				cloze += c.getPaperDetails().getValue();
			}
		}
		
		for (QuestionSingleChoice single : singles) {
			if (single.getPaperDetails().getValue() != null) {
				singlescore += single.getPaperDetails().getValue();
			}
		}
		for (QuestionMulitChoice mulit : mulits) {
			if (mulit.getPaperDetails().getValue() != null) {
				mulitscore += mulit.getPaperDetails().getValue();
			}
		}
		for (QuestionJudge judge : judges) {
			if (judge.getPaperDetails().getValue() != null) {
				judgescore += judge.getPaperDetails().getValue();
			}
		}
		singleChoice = singles.size();
		Judge = judges.size();
		subCount = subjectives.size();
		fillCount = qfList.size();
		clozeCount = clozes.size();
		if (qrList == null || qrList.isEmpty()) {
			Read = 0;
		} else {
			Read = qrList.size();
		}
		mulitChoice = mulits.size();
		if (!singles.isEmpty() && singles.get(0).getPaperDetails().getValue() != null) {
			danxuan = singles.get(0).getPaperDetails().getValue();
		}
		if (!mulits.isEmpty() && mulits.get(0).getPaperDetails().getValue() != null) {
			duoxuan = mulits.get(0).getPaperDetails().getValue();
		}
		if (!judges.isEmpty() && judges.get(0).getPaperDetails().getValue() != null) {
			pduan = judges.get(0).getPaperDetails().getValue();
		}
		if (!reads.isEmpty() && reads.get(0).getPaperDetails().getValue() != null) {
			yuedu = reads.get(0).getPaperDetails().getValue() / Read;
		}
		if (subjectives != null && !subjectives.isEmpty()) {
			question = subjectives.get(0).getPaperDetails().getValue();
		}
		if (qfList != null && !qfList.isEmpty()) {
			fillScore = qfList.get(0).getPaperDetails().getValue();
		}
		if (!clozes.isEmpty()) {
			clozeScore = clozes.get(0).getPaperDetails().getValue();
		}
		
		List<Subject> sList = subjectService.getSubject();
		List<Grade> gList = gradeService.getGrade();
		view.addObject("sList", sList);
		view.addObject("gList", gList);
		view.addObject("qfList", qfList);
		view.setViewName("exampaper/watchexam");
		view.addObject("paper", pInfo);
		view.addObject("singleList", singles);
		view.addObject("mulitList", mulits);
		view.addObject("judegeList", judges);
		view.addObject("readList", reads);
		view.addObject("singleChoice", singleChoice);
		view.addObject("subjectives", subjectives);
		view.addObject("Judge", Judge);
		view.addObject("subCount", subCount);
		view.addObject("Read", Read);
		view.addObject("danxuan", danxuan);
		view.addObject("duoxuan", duoxuan);
		view.addObject("pduan", pduan);
		view.addObject("yuedu", yuedu);
		view.addObject("cloze", cloze);
		view.addObject("clozes", clozes);
		view.addObject("clozeCount", clozeCount);
		view.addObject("question", question);
		view.addObject("fillCount", fillCount);
		view.addObject("fillScore", fillScore);
		view.addObject("mulitChoice", mulitChoice);
		view.addObject("singlescore", singlescore.intValue());
		view.addObject("mulitscore", mulitscore.intValue());
		view.addObject("judgescore", judgescore.intValue());
		view.addObject("readscore", readscore.intValue());
		view.addObject("subjective", subjective.intValue());
		view.addObject("clozeScore", clozeScore.intValue());
		view.addObject("fill", fill.intValue());
		view.addObject("page", pageNo);
		view.addObject("type", type);
		return view;
	}

	/**
	 * 根据试卷ID返回所有的总数
	 * 
	 * @param response
	 * @param paperId
	 * @param piType
	 */
	@RequestMapping("/paperTotai")
	public void paperTotai(HttpServletResponse response, Integer paperId) {
		int conut = paperDetailsService.findPaperTotai(paperId);
		output(response, JsonUtil.buildFalseJson("0", String.valueOf(conut)));
	}

	/**
	 * 根据条件查询试卷
	 * 
	 * @param type
	 * @param grade
	 * @param knowstart
	 * @param knowend
	 * @param subject
	 * @param science
	 * @param response
	 */
	@RequestMapping("/getpaperbyitem")
	public void getPaperByItem(Integer type, Integer grade, String knowstart, String knowend, Integer subject,
			Integer science, HttpServletResponse response, HttpServletRequest request, Integer pageNo, Integer pageSize,
			String piName,Integer isTask) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 6;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("grade", grade);
		map.put("start", knowstart);
		map.put("end", knowend);
		map.put("subject", subject);
		map.put("science", science);
		map.put("teacherId", currTeacher.getTeacherId());
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("piName", piName);
		map.put("isTask", isTask);
		List<PaperInfo> list = null;
		int totalcount = 0;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			list = paperInfoService.getPaperBy(map);
			totalcount = paperInfoService.getPaperByCount(map);
		} else {
			list = paperInfoService.getPaperByItems(map);
			totalcount = paperInfoService.getPaperByItemsCount(map);
		}
		PageUtil pageUtil = new PageUtil(totalcount, pageNo, pageSize);
		if (!list.isEmpty() && list != null) {
			output(response, JsonUtil.buildJsonByTotalCount(list, pageUtil.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有找到符合条件的试卷，请换个条件"));
		}
	}
    
   
	/**
	 * 根据条件查询试卷
	 * 
	 * @param type
	 * @param grade
	 * @param knowstart
	 * @param knowend
	 * @param subject
	 * @param science
	 * @param response
	 */
	@RequestMapping("/getpaperbyitem1")
	public void getPaperByItem1(Integer type, Integer grade, String knowstart, String knowend, Integer subject,
			Integer science, HttpServletResponse response, HttpServletRequest request, Integer pageNo, Integer pageSize,
			String piName,Integer isTask) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 6;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("grade", grade);
		map.put("start", knowstart);
		map.put("end", knowend);
		map.put("science", science);
		map.put("teacherId", currTeacher.getTeacherId());
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("piName", piName);
		map.put("isTask", isTask);
		List<PaperInfo> list = null;
		int totalcount = 0;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			map.put("subject", subject);
			list = paperInfoService.getPaperBy1(map);
			totalcount = paperInfoService.getPaperByCount1(map);
		} else {
			map.put("subject", currTeacher.getSubject());
			list = paperInfoService.getPaperByItems1(map);
			totalcount = paperInfoService.getPaperByItemsCount1(map);
		}
		PageUtil pageUtil = new PageUtil(totalcount, pageNo, pageSize);
		if (!list.isEmpty() && list != null) {
			output(response, JsonUtil.buildJsonByTotalCount(list, pageUtil.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有找到符合条件的试卷，请换个条件"));
		}
	}
    
	
	
	/**
	 * 模糊查询
	 * 
	 * @param content
	 * @param response
	 */
	@RequestMapping("/getpaperbycontent")
	public void getPaperByContent(String content, HttpServletResponse response) {
		List<PaperInfo> list = paperInfoService.getPaperByContent(content);
		if (!list.isEmpty() && list != null) {
			output(response, JsonUtil.buildJson(list));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有找到符合的试卷~"));
		}
	}
	
	/**
	 * 根据科目联动教材
	 * @param response
	 * @param subjectId
	 */
	@RequestMapping("/getSubjectMaterial")
	public void getSubjectMaterial(HttpServletResponse response,Integer subjectId){
		List<TeachingMaterial> tMaterials = tmService.getTeacherIsMate(subjectId);
		if (tMaterials != null && !tMaterials.isEmpty()) {
			output(response, JsonUtil.buildJson(tMaterials));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
	}
}
