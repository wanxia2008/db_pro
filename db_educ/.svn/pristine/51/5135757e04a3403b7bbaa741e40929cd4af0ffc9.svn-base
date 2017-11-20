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
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.ClassNo;
import com.db.entity.ClassRecord;
import com.db.entity.Grade;
import com.db.entity.HomeworkAnswer;
import com.db.entity.HomeworkRecord;
import com.db.entity.Log;
import com.db.entity.PaperAnswer;
import com.db.entity.PaperInfo;
import com.db.entity.QuestionFill;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.QuestionTag;
import com.db.entity.SchoolZone;
import com.db.entity.StudentCareer;
import com.db.entity.Subject;
import com.db.entity.Teacher;
import com.db.entity.TestRecord;
import com.db.entity.utilentity.QuestionAnalysis;
import com.db.service.ClassNoService;
import com.db.service.ClassRecordService;
import com.db.service.GradeService;
import com.db.service.HomeworkAnswerService;
import com.db.service.HomeworkRecordService;
import com.db.service.LogService;
import com.db.service.PaperAnswerService;
import com.db.service.PaperInfoService;
import com.db.service.QuestionFillService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.QuestionTagService;
import com.db.service.SchoolZoneService;
import com.db.service.StudentCareerService;
import com.db.service.SubjectService;
import com.db.service.TestRecordService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;

@Controller
@RequestMapping("/examRecord")
public class ClassExamRecordController extends BaseUtil {

	@Resource
	private ClassRecordService recordService;
	@Resource
	private ClassNoService classNoService;
	@Resource
	private PaperInfoService paperInfoService;
	@Resource
	private LogService logService;
	private HttpSession session;
	@Resource
	private StudentCareerService studentCareerService;
	@Resource
	private TestRecordService testRecordService;
	@Resource
	private SchoolZoneService zoneService;
	@Resource
	HomeworkRecordService hoRecordService;
	@Resource
	private HomeworkAnswerService answerService;
	@Resource
	private GradeService gradeService;
	@Resource
	private PaperAnswerService paperAnswerService;
	@Resource
	private QuestionJudgeService judgeservice;
	@Resource
	private QuestionSingleChoiceService singleservice;
	@Resource
	private QuestionMulitChoiceService mulitservice;
	@Resource
	private QuestionFillService fillService;
	@Resource
	private QuestionTagService tagService;
	@Resource
	private QuestionReadService readservice;
	@Resource
	private SubjectService subjectService;	

	@Resource
	private HomeworkRecordService homeworkRecordService;

	private Logger log = Logger.getLogger(LessonController.class);

	/**
	 * 考试管理列表
	 * 
	 * @param pageNo
	 * @param classId
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/examPaperList")
	public ModelAndView examPaperList(Integer pageNo, Integer classId, Integer pageSize,
			HttpServletRequest request,Integer schoolId)throws Exception {
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
		List<SchoolZone> szList = null;
		if (currTeacher.getTeacherType().equals("1")) {
			map.put("schoolId", schoolId);
			crList = recordService.getExamPaperList(map);
			rowsCount = recordService.getPaperCount(map);
			classList = classNoService.getClassesNo();
			szList = zoneService.getAllSchool();
		} else {
			szList = zoneService.findSchoolList(currTeacher.getCampus());
			classList = classNoService.findIsExistenceCampus(currTeacher.getCampus());
			if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
				if (currTeacher.getClassId() != null) {
					map.put("classId", currTeacher.getClassId());
					map.put("schoolId", currTeacher.getCampus());
					crList = recordService.getExamPaperSchoolList(map);
					rowsCount = recordService.getSchoolCount(map);
				}
			}else {
				map.put("schoolId", currTeacher.getCampus());
				crList = recordService.getExamPaperSchoolList(map);
				rowsCount = recordService.getSchoolCount(map);
			}
		}
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		if (crList != null && !crList.isEmpty()) {
			mv.addObject("crList", crList);
		} else {
			mv.addObject("crList", null);
		}
		mv.addObject("classId", classId);
		mv.addObject("schoolId", schoolId);
		mv.addObject("szList", szList);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("classList", classList);
		mv.setViewName("classExamRecord/exammanage");
		return mv;
	}

	/**
	 * 考试试卷列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param classId
	 * @param pageId
	 * @return
	 */
	@RequestMapping("/paperList")
	public ModelAndView paperList(Integer pageNo, Integer pageSize, Integer classId, Integer paperId) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("classId", classId);
		map.put("paperId", paperId);
		int rowsCount = 0;
		int totalPages = 0;
		List<ClassRecord> crList = recordService.findPaperExamList(map);
		rowsCount = recordService.findPaperCount(map);
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		if (crList != null && !crList.isEmpty()) {
			mv.addObject("crList", crList);
		} else {
			mv.addObject("crList", null);
		}
		mv.addObject("page", pageNo);
		mv.addObject("classId", classId);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("crList", crList);
		mv.setViewName("classExamRecord/exampaperList");
		return mv;
	}

	
	/**
	 * 后台查看前端学员试卷完成情况
	 * 
	 * @param subjectId
	 * @param classId
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/paperCompletion")
	public ModelAndView completion(Integer classId,Integer pageNo, Integer pageSize,String studentName,Integer paperId) {
		ModelAndView mv = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = testRecordService.getClassPaperCount(classId,studentName,paperId);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		double avgScore = 0.0;
		List<TestRecord> recordList = null;
		List<TestRecord> trList = testRecordService.findClassPaperList(classId,(pageNo - 1) * pageSize, pageSize, studentName,paperId);
		if (trList != null && !trList.isEmpty()) {
			mv.addObject("trList", trList);
			avgScore = testRecordService.getClassIdAvgScore(paperId, classId);
			recordList = testRecordService.findObtainScoreRankList(paperId, classId);
		} else {
			mv.addObject("trList", null);
		}
		if (recordList != null) {
			mv.addObject("recordList", recordList);
		}
		ClassNo classNo = classNoService.getClassById(classId);
		Grade grade = gradeService.getGradeById(classNo.getGrade());
		List<ClassNo> classNos = classNoService.getClassesNo();
		PaperInfo info = paperInfoService.getPiIdById(paperId);
		BigDecimal b = new BigDecimal(avgScore);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		mv.addObject("page", pageNo);
		mv.addObject("classNos", classNos);
		mv.addObject("pageSize", pageSize);
		mv.addObject("classId", classId);
		mv.addObject("paperId", paperId);
		mv.addObject("classAvg", f1);
		mv.addObject("grade", grade);
		mv.addObject("info", info);
		mv.addObject("totalPages", totalPages);
		mv.addObject("studentName", studentName);
		mv.setViewName("classExamRecord/papercompletion");
		return mv;
	}
	
	
	/**
	 * 讲义管理列表
	 * @param classId
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("notLecturenoteList")
	public ModelAndView notLecturenoteList(Integer classId,Integer lecturenoteId,Integer pageNo,Integer pageSize,HttpServletRequest request){
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("classId", classId);
		map.put("lecturenoteId", lecturenoteId);
		int rowsCount = 0;
		int totalPages = 0;
		List<ClassRecord> crList = recordService.findlecturenoteExamList(map);
		rowsCount = recordService.findlecturenoteCount(map);
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		if (crList != null && !crList.isEmpty()) {
			mv.addObject("crList", crList);
		} else {
			mv.addObject("crList", null);
		}
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("crList", crList);
		mv.setViewName("classExamRecord/examnoteList");
		return mv;
	}
	
	
	
	/**
	 * 作业管理列表
	 * @param classId
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/examhomeworkList")
	public ModelAndView homeList(Integer classId,Integer homeworkId,Integer pageNo,Integer pageSize,HttpServletRequest request){
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("classId", classId);
		map.put("homeworkId", homeworkId);
		int rowsCount = 0;
		int totalPages = 0;
		List<ClassRecord> crList = recordService.findhomeworkIdExamList(map);
		rowsCount = recordService.findhomeworkIdCount(map);
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		if (crList != null && !crList.isEmpty()) {
			mv.addObject("crList", crList);
		} else {
			mv.addObject("crList", null);
		}
		mv.addObject("page", pageNo);
		mv.addObject("classId", classId);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("crList", crList);
		mv.setViewName("classExamRecord/examhomeworkList");
		return mv;
	}
	
	
	/**
	 * 试卷分析
	 * @param response
	 * @param paperId
	 * @param classId
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/paperInfoAnalysis")
	public ModelAndView paperInfoAnalysis(HttpServletResponse response, Integer paperId, Integer classId, Integer pageNo,
      Integer pageSize) {
		ModelAndView mv = new ModelAndView();
		int questionTotal = 0, errorNumber = 0, questionNumber = 0;// 做对人数和做错人数
		int rowsCount = 0;
		int totalPages = 0;
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		double typeAvg=0.0;
		List<QuestionTag> questionTags = null;
			List<QuestionAnalysis> qaList = new ArrayList<QuestionAnalysis>();
			List<PaperAnswer> paList = paperAnswerService.findClassPaperInfoAnalysisList(paperId, classId,(pageNo-1)*pageSize,pageSize);
			rowsCount = paperAnswerService.getAnalysisCount(paperId,classId);
			if (paList != null && !paList.isEmpty()) {
				for (PaperAnswer hr : paList) {
					QuestionAnalysis qa = new QuestionAnalysis();
					if (hr.getQuestionType() == 1) {
						mv.addObject("danType", 1);
						typeAvg = paperAnswerService.getQuestionAvg(paperId,classId,1);
						questionNumber = paperAnswerService.getcountTrue(null, paperId, 1,1,classId,hr.getQuestionId());
						errorNumber = paperAnswerService.getcountTrue(null, paperId, 0, 1,classId,hr.getQuestionId());
						qa.setTrueNumber(questionNumber);
						qa.setQuestionName("选择题");
						BigDecimal b = new BigDecimal(typeAvg);
						double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						mv.addObject("typeAvg", f1);
						qa.setQuestionId(hr.getQuestionId());
						QuestionSingleChoice choice = singleservice.getSingleChoiceById(hr.getQuestionId());
						String[] myTag = choice.getTag().split(",");
						List tagList = Arrays.asList(myTag);
						questionTags = tagService.getTagByIds(tagList);
						if (questionTags != null && !questionTags.isEmpty()) {
							for (QuestionTag tag : questionTags) {
								qa.setTagName(tag.getTagName());
							}
						}
						qa.setQuestionType(1);
						qa.setTimuName(choice.getChoiceDesc());
						qa.setErrorNumber(errorNumber);
						questionTotal = questionNumber + errorNumber;
						if (errorNumber == 0) {
							qa.setScoreRate(100.0);
						} else {
							qa.setScoreRate(Double.valueOf(questionNumber * 100 / questionTotal));
						}
						qa.setQuestionTotal(questionTotal);
						qaList.add(qa);
					}
					if (hr.getQuestionType() == 2) {
						questionNumber = paperAnswerService.getcountTrue(null, paperId, 1, 2,classId,hr.getQuestionId());
						errorNumber = paperAnswerService.getcountTrue(null, paperId, 0, 2,classId,hr.getQuestionId());
						mv.addObject("duoType", 2);
						typeAvg = paperAnswerService.getQuestionAvg(paperId,classId,2);
						BigDecimal b = new BigDecimal(typeAvg);
						double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						mv.addObject("typeAvg", f1);
						qa.setTrueNumber(questionNumber);
						qa.setQuestionName("多选题");
						qa.setQuestionId(hr.getQuestionId());
						qa.setErrorNumber(errorNumber);
						qa.setQuestionType(2);
						QuestionMulitChoice mChoice = mulitservice.getMulitChoiceById(hr.getQuestionId());
						String[] myTag = mChoice.getTag1().split(",");
						List tagList = Arrays.asList(myTag);
						questionTags = tagService.getTagByIds(tagList);
						if (questionTags != null && !questionTags.isEmpty()) {
							for (QuestionTag tag : questionTags) {
								qa.setTagName(tag.getTagName());
							}
						}
						questionTotal = questionNumber + errorNumber;
						if (errorNumber == 0) {
							qa.setScoreRate(100.0);
						} else {
							qa.setScoreRate(Double.valueOf(questionNumber * 100 / questionTotal));
						}
						qa.setTimuName(mChoice.getChoiceDesc1());
						qa.setQuestionTotal(questionTotal);
						qaList.add(qa);
					}
					if (hr.getQuestionType() == 3) {
						questionNumber = paperAnswerService.getcountTrue(null, paperId, 1, 3,classId,hr.getQuestionId());
						errorNumber = paperAnswerService.getcountTrue(null, paperId, 0, 3,classId,hr.getQuestionId());
						mv.addObject("pduanType", 3);
						typeAvg = paperAnswerService.getQuestionAvg(paperId,classId,3);
						BigDecimal b = new BigDecimal(typeAvg);
						double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						mv.addObject("typeAvg", f1);
						qa.setTrueNumber(questionNumber);
						qa.setQuestionName("判断题");
						qa.setQuestionType(3);
						qa.setQuestionId(hr.getQuestionId());
						QuestionJudge judge = judgeservice.getJudgeById(hr.getQuestionId());
						String[] myTag = judge.getTag2().split(",");
						List tagList = Arrays.asList(myTag);
						questionTags = tagService.getTagByIds(tagList);
						if (questionTags != null && !questionTags.isEmpty()) {
							for (QuestionTag tag : questionTags) {
								qa.setTagName(tag.getTagName());
							}
						}
						qa.setTimuName(judge.getJudgeDesc());
						qa.setErrorNumber(errorNumber);
						questionTotal = questionNumber + errorNumber;
						if (errorNumber == 0) {
							qa.setScoreRate(100.0);
						} else {
							qa.setScoreRate(Double.valueOf(questionNumber * 100 / questionTotal));
						}
						qa.setQuestionTotal(questionTotal);
						qaList.add(qa);
					}
					if (hr.getQuestionType() == 4) {
						questionNumber = paperAnswerService.getcountTrue(null, paperId, 1, 4,classId,hr.getQuestionId());
						errorNumber = paperAnswerService.getcountTrue(null, paperId, 0,4,classId,hr.getQuestionId());
						mv.addObject("yueType", 4);
						typeAvg = paperAnswerService.getQuestionAvg(paperId,classId,4);
						BigDecimal b = new BigDecimal(typeAvg);
						double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						mv.addObject("typeAvg", f1);
						qa.setTrueNumber(questionNumber);
						qa.setQuestionName("阅读理解");
						qa.setQuestionId(hr.getQuestionId());
						qa.setQuestionType(4);
						qa.setErrorNumber(errorNumber);
						QuestionRead read = readservice.getReadById(hr.getQuestionId());
						String[] myTag = read.getTag_read().split(",");
						List tagList = Arrays.asList(myTag);
						if (questionTags != null && !questionTags.isEmpty()) {
							for (QuestionTag tag : questionTags) {
								qa.setTagName(tag.getTagName());
							}
						}
						questionTags = tagService.getTagByIds(tagList);
						questionTotal = questionNumber + errorNumber;
						if (errorNumber == 0) {
							qa.setScoreRate(100.0);
						} else {
							qa.setScoreRate(Double.valueOf(questionNumber * 100 / questionTotal));
						}
						qa.setTimuName(read.getOptiontitle());
						qa.setQuestionTotal(questionTotal);
						qaList.add(qa);
					}
					if (hr.getQuestionType() == 6) {
						questionNumber = paperAnswerService.getcountTrue(null, paperId, 1, 6,classId,hr.getQuestionId());
						errorNumber = paperAnswerService.getcountTrue(null, paperId, 0, 6,classId,hr.getQuestionId());
						mv.addObject("tianType", 6);
						typeAvg = paperAnswerService.getQuestionAvg(paperId,classId,6);
						BigDecimal b = new BigDecimal(typeAvg);
						double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						mv.addObject("typeAvg", f1);
						qa.setTrueNumber(questionNumber);
						qa.setQuestionName("填空题");
						qa.setQuestionId(hr.getQuestionId());
						qa.setQuestionType(6);
						QuestionFill fill = fillService.getQuestionFillById(hr.getQuestionId());
						String[] myTag = fill.getTagId().split(",");
						List tagList = Arrays.asList(myTag);
						questionTags = tagService.getTagByIds(tagList);
						if (questionTags != null && !questionTags.isEmpty()) {
							for (QuestionTag tag : questionTags) {
								qa.setTagName(tag.getTagName());
							}
						}
						qa.setScoreRate(10.0);
						qa.setErrorNumber(errorNumber);
						questionTotal = questionNumber + errorNumber;
						if (errorNumber == 0) {
							qa.setScoreRate(100.0);
						} else {
							qa.setScoreRate(Double.valueOf(questionNumber * 100 / questionTotal));
						}
						qa.setTimuName(fill.getFillTitle());
						qa.setQuestionTotal(questionTotal);
						qaList.add(qa);
					}
				}
				if (rowsCount % pageSize == 0) {
					totalPages = rowsCount / pageSize;
				} else {
					totalPages = (rowsCount / pageSize) + 1;
				}
				if (qaList != null && !qaList.isEmpty()) {
					mv.addObject("paList", qaList);
				} else {
					mv.addObject("paList", null);
				}
			}
			mv.addObject("page", pageNo);
			mv.addObject("classId", classId);
			mv.addObject("paperId", paperId);
			mv.addObject("pageSize", pageSize);
			mv.addObject("totalPages", totalPages);
		    mv.setViewName("classExamRecord/paperanalysis");
		    return mv;
	}
	
	/**
	 * 作业分析
	 * @param response
	 * @param homeworkId
	 * @param classId
	 */
	@RequestMapping("/homeworhAnalysis")
	public ModelAndView homeworhAnalysis(HttpServletResponse response,Integer homeworkId,Integer classId,
			Integer pageNo, Integer pageSize,Integer subjectId,String studentName,Integer paperId) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = 0;
		int totalPages = 0;
		ModelAndView mv = new ModelAndView();
		int questionTotal = 0, errorNumber = 0, questionNumber = 0;// 做对人数和做错人数
		List<QuestionAnalysis> qaList = new ArrayList<QuestionAnalysis>();
		List<HomeworkAnswer> haList = answerService.findhomeworhAnalysis(homeworkId, (pageNo - 1) * pageSize, pageSize,
				classId);
		rowsCount = answerService.findAnalysisCount(homeworkId,classId);
		double typeAvg = 0.0;
		if (haList != null && !haList.isEmpty()) {
			for (HomeworkAnswer hr : haList) {
				QuestionAnalysis qa = new QuestionAnalysis();
				List<QuestionTag> questionTags = null;
				if (hr.getQuestionType() == 1) {
					questionNumber = answerService.getcountTrue(null, homeworkId, 1, 1,hr.getQuestionId(),classId);
					errorNumber = answerService.getcountTrue(null, homeworkId, 0, 1,hr.getQuestionId(),classId);
					mv.addObject("danType", 1);
					typeAvg = answerService.getQuestionAvg(homeworkId,classId,1);
					BigDecimal b = new BigDecimal(typeAvg);
					double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					mv.addObject("typeAvg", f1);
					qa.setTrueNumber(questionNumber);
					qa.setQuestionName("选择题");
					QuestionSingleChoice choice = singleservice.getSingleChoiceById(hr.getQuestionId());
					qa.setQuestionId(hr.getQuestionId());
					qa.setErrorNumber(errorNumber);
					qa.setQuestionType(1);
					String[] myTag = choice.getTag().split(",");
					List tagList = Arrays.asList(myTag);
					questionTags = tagService.getTagByIds(tagList);
					if (questionTags != null && !questionTags.isEmpty()) {
						for (QuestionTag tag : questionTags) {
							qa.setTagName(tag.getTagName());
						}
					}
					questionTotal = questionNumber + errorNumber;
					if (errorNumber == 0) {
						qa.setScoreRate(100.0);
					} else {
						qa.setScoreRate(Double.valueOf(questionNumber * 100 / questionTotal));
					}
					qa.setTimuName(choice.getChoiceDesc());
					qa.setQuestionTotal(questionTotal);
					qaList.add(qa);
				}
				if (hr.getQuestionType() == 2) {
					questionNumber = answerService.getcountTrue(null, homeworkId, 1, 2,hr.getQuestionId(),classId);
					errorNumber = answerService.getcountTrue(null, homeworkId, 0, 2,hr.getQuestionId(),classId);
					mv.addObject("duoType", 2);
					typeAvg = answerService.getQuestionAvg(homeworkId,classId,2);
					BigDecimal b = new BigDecimal(typeAvg);
					double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					mv.addObject("typeAvg", f1);
					qa.setTrueNumber(questionNumber);
					qa.setQuestionName("多选题");
					qa.setQuestionId(hr.getQuestionId());
					qa.setErrorNumber(errorNumber);
					questionTotal = questionNumber + errorNumber;
					QuestionMulitChoice mChoice = mulitservice.getMulitChoiceById(hr.getQuestionId()); 
					if (errorNumber == 0) {
						qa.setScoreRate(100.0);
					} else {
						qa.setScoreRate(Double.valueOf(questionNumber * 100 / questionTotal));
					}
					String[] myTag = mChoice.getTag1().split(",");
					List tagList = Arrays.asList(myTag);
					questionTags = tagService.getTagByIds(tagList);
					if (questionTags != null && !questionTags.isEmpty()) {
						for (QuestionTag tag : questionTags) {
							qa.setTagName(tag.getTagName());
						}
					}
					qa.setTimuName(mChoice.getChoiceDesc1());
					qa.setQuestionType(2);
					qa.setQuestionTotal(questionTotal);
					qaList.add(qa);
				}
				if (hr.getQuestionType() == 3) {
					questionNumber = answerService.getcountTrue(null, homeworkId, 1, 3,hr.getQuestionId(),classId);
					errorNumber = answerService.getcountTrue(null, homeworkId, 0, 3,hr.getQuestionId(),classId);
					mv.addObject("pduanType", 3);
					typeAvg = answerService.getQuestionAvg(homeworkId,classId,3);
					BigDecimal b = new BigDecimal(typeAvg);
					double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					mv.addObject("typeAvg", f1);
					qa.setTrueNumber(questionNumber);
					qa.setQuestionName("判断题");
					qa.setQuestionId(hr.getQuestionId());
					QuestionJudge judge = judgeservice.getJudgeById(hr.getQuestionId());
					qa.setErrorNumber(errorNumber);
					questionTotal = questionNumber + errorNumber;
					if (errorNumber == 0) {
						qa.setScoreRate(100.0);
					} else {
						qa.setScoreRate(Double.valueOf(questionNumber * 100 / questionTotal));
					}
					String[] myTag = judge.getTag2().split(",");
					List tagList = Arrays.asList(myTag);
					questionTags = tagService.getTagByIds(tagList);
					if (questionTags != null && !questionTags.isEmpty()) {
						for (QuestionTag tag : questionTags) {
							qa.setTagName(tag.getTagName());
						}
					}
					qa.setTimuName(judge.getJudgeDesc());
					qa.setQuestionType(3);
					qa.setQuestionTotal(questionTotal);
					qaList.add(qa);
				}
				if (hr.getQuestionType() == 4) {
					questionNumber = answerService.getcountTrue(null, homeworkId, 1, 4,hr.getQuestionId(),classId);
					errorNumber = answerService.getcountTrue(null, homeworkId, 0,4,hr.getQuestionId(),classId);
					mv.addObject("yueType", 4);
					typeAvg = answerService.getQuestionAvg(homeworkId,classId,4);
					BigDecimal b = new BigDecimal(typeAvg);
					double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					mv.addObject("typeAvg", f1);
					qa.setTrueNumber(questionNumber);
					qa.setQuestionName("阅读理解");
					qa.setQuestionId(hr.getQuestionId());
					qa.setErrorNumber(errorNumber);
					QuestionRead read = readservice.getReadById(hr.getQuestionId());
					questionTotal = questionNumber + errorNumber;
					if (errorNumber == 0) {
						qa.setScoreRate(100.0);
					} else {
						qa.setScoreRate(Double.valueOf(questionNumber * 100 / questionTotal));
					}
					String[] myTag = read.getTag_read().split(",");
					List tagList = Arrays.asList(myTag);
					questionTags = tagService.getTagByIds(tagList);
					if (questionTags != null && !questionTags.isEmpty()) {
						for (QuestionTag tag : questionTags) {
							qa.setTagName(tag.getTagName());
						}
					}
					qa.setTimuName(read.getOptiontitle());
					qa.setQuestionType(4);
					qa.setQuestionTotal(questionTotal);
					qaList.add(qa);
				}
				if (hr.getQuestionType() == 6) {
					questionNumber = answerService.getcountTrue(null, homeworkId, 1, 6,hr.getQuestionId(),classId);
					errorNumber = answerService.getcountTrue(null, homeworkId, 0, 6,hr.getQuestionId(),classId);
					mv.addObject("tianType", 6);
					typeAvg = answerService.getQuestionAvg(homeworkId,classId,6);
					BigDecimal b = new BigDecimal(typeAvg);
					double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					mv.addObject("typeAvg", f1);
					qa.setTrueNumber(questionNumber);
					qa.setQuestionName("填空题");
					qa.setQuestionId(hr.getQuestionId());
					qa.setScoreRate(10.0);
					qa.setErrorNumber(errorNumber);
					QuestionFill fill = fillService.getQuestionFillById(hr.getQuestionId()); 
					qa.setQuestionType(6);
					questionTotal = questionNumber + errorNumber;
					if (errorNumber == 0) {
						qa.setScoreRate(100.0);
					} else {
						qa.setScoreRate(Double.valueOf(questionNumber * 100 / questionTotal));
					}
					String[] myTag = fill.getTagId().split(",");
					List tagList = Arrays.asList(myTag);
					questionTags = tagService.getTagByIds(tagList);
					if (questionTags != null && !questionTags.isEmpty()) {
						for (QuestionTag tag : questionTags) {
							qa.setTagName(tag.getTagName());
						}
					}
					qa.setTimuName(fill.getFillTitle());
					qa.setQuestionTotal(questionTotal);
					qaList.add(qa);
				}
			}
			if (rowsCount % pageSize == 0) {
				totalPages = rowsCount / pageSize;
			} else {
				totalPages = (rowsCount / pageSize) + 1;
			}
			if (qaList != null && !qaList.isEmpty()) {
				mv.addObject("qaList", qaList);
			} else {
				mv.addObject("qaList", null);
			}
		}
		
		//=========================
		int rowsCount1 = homeworkRecordService.getCount(classId, subjectId, studentName,paperId);
		int totalPages1 = 0;
		if (rowsCount1 % pageSize == 0) {
			totalPages1 = rowsCount1 / pageSize;
		} else {
			totalPages1 = (rowsCount1 / pageSize) + 1;
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
//		mv.addObject("page", pageNo);
		mv.addObject("subjects", subjects);
		mv.addObject("classNos", classNos);
//		mv.addObject("pageSize", pageSize);
//		mv.addObject("classId", classId);
		mv.addObject("paperId", paperId);
		mv.addObject("subjectId", subjectId);
		mv.addObject("grade", grade);
		mv.addObject("totalPages1", totalPages1);
		mv.addObject("studentName", studentName);		
		//=========================
		
		mv.addObject("page", pageNo);
		mv.addObject("classId", classId);
		mv.addObject("pageSize", pageSize);
		mv.addObject("paperId", homeworkId);
		mv.addObject("totalPages", totalPages);
		mv.setViewName("classExamRecord/homeworhanalysiscompletion");
		return mv;
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
		int paperId = Integer.valueOf(request.getParameter("paperId"));
		int classId = Integer.valueOf(request.getParameter("classid"));
		int subjectId = Integer.valueOf(request.getParameter("subject"));
//		int status = 0;// 识别是否都参与了考试
		ClassNo classNo = classNoService.getClassById(classId);
		PaperInfo paperInfo = paperInfoService.getPaperInfoById(paperId);
		ClassRecord cr = recordService.getPaperById(classId, paperId, null,null);
		if (paperInfo.getSubject() != subjectId) {
			output(response, JsonUtil.buildFalseJson("1", "试卷对应的科目不符合，请仔细查看后再添加!"));
		} else if (classNo != null && paperInfo.getGrade() != classNo.getGrade()) {
			output(response, JsonUtil.buildFalseJson("3", "试卷对应的年级不符合，请仔细查看后再添加!"));
		} else {
			if (cr != null) {
				output(response, JsonUtil.buildFalseJson("2", "对不起，该班级已经考过该试卷，请选择其他考卷"));
			} else {
				cr = new ClassRecord();
				cr.setClassId(classId);
				cr.setCreateTime(new Date());
				cr.setPaperId(paperId);
				cr.setStatus(0);
				recordService.addClassRecord(cr);
				log.info("添加考卷到课程成功!");
				// 使用试卷之后，试卷使用数依次加一
				paperInfo.setUpdateTime(new Date());
				paperInfo.setUsedTimes(paperInfo.getUsedTimes() + 1);
				paperInfoService.updateUsedTimes(paperInfo);
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
				output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
			}
		}
	}

	/**
	 * 替换考试
	 * @param response
	 * @param recordId
	 * @param paperId
	 */
	@RequestMapping("/replacePaperId")
	public void replacePaperId(HttpServletResponse response, Integer recordId, Integer paperId, Integer subject) {
		ClassRecord record = recordService.getClassRecordById(recordId);
		PaperInfo pInfo = paperInfoService.getPaperInfoById(paperId);
		if (record == null) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		} else {
			ClassNo classNo = classNoService.getClassById(record.getClassId());
			ClassRecord cr = recordService.getPaperById(record.getClassId(), paperId, null, null);
			if (record.getStatus() != 0) {
				output(response, JsonUtil.buildFalseJson("2", "该试卷已被启动，暂时不能删除考卷!"));
			} else if (cr != null) {
				output(response, JsonUtil.buildFalseJson("2", "对不起，该班级已经考过该试卷，请选择其他考卷"));
			} else if (pInfo.getSubject() != subject) {
				output(response, JsonUtil.buildFalseJson("1", "试卷对应的科目不符合，请仔细查看后再添加!"));
			} else if (classNo != null && pInfo.getGrade() != classNo.getGrade()) {
				output(response, JsonUtil.buildFalseJson("3", "试卷对应的年级不符合，请仔细查看后再添加!"));
			} else {
				try {
					record.setUpdateTime(new Date());
					record.setPaperId(paperId);
					record.setStatus(0);
					recordService.updateStatus(record);
					pInfo.setUpdateTime(new Date());
					if (pInfo.getUsedTimes() != null) {
						pInfo.setUsedTimes(pInfo.getUsedTimes()+1);
					}else {
						pInfo.setUsedTimes(1);
					}
					paperInfoService.updateUsedTimes(pInfo);
					PaperInfo info = paperInfoService.getPaperInfoById(record.getPaperId());
					pInfo.setUpdateTime(new Date());
					if (pInfo.getUsedTimes() != null && pInfo.getUsedTimes()!=0) {
						pInfo.setUsedTimes(pInfo.getUsedTimes()-1);
					}else {
						pInfo.setUsedTimes(0);
					}
					paperInfoService.updateUsedTimes(info);
					output(response, JsonUtil.buildFalseJson("0", "替换成功"));
				} catch (Exception e) {
					output(response, JsonUtil.buildFalseJson("1", "替换失败"));
				}
			}
		}
	}
	/**
	 * 启动考试，存入记录表(先添加试卷记录表，再更新课程表的试卷记录表id，
	 *  用于判断是否启动或结束，再添加考试记录表)
	 * 
	 * @param record
	 * @param response
	 */
	@RequestMapping("/startExam")
	public void startExam(HttpServletRequest request, Integer classid, Integer type, HttpServletResponse response,
			Integer recordId) {
		session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		List<StudentCareer> students = studentCareerService.getStudentByClassNo(classid, 2);// 查找已分班并且班级编号为classid的学生
		ClassNo classNo = classNoService.getClassById(classid);
		ClassRecord cr = recordService.getClassRecordById(recordId);
		if (students == null || students.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("1", "启动失败，该班级暂时无分配学生"));
		} else {
			TestRecord record1 = new TestRecord();
			for (int i = 0; i < students.size(); i++) {
				int studentId = students.get(i).getStudentId();
				record1.setStudentId(studentId);
				record1.setSubjectId(classNo.getSubject());
				record1.setPaperId(cr.getPaperId());
				record1.setClassId(classid);
				record1.setPaperType(type);
				record1.setGradeId(classNo.getGrade());
				record1.setChoiceId(classNo.getSchoolArea());
				record1.setStatus(1);// 启动
				record1.setCreateTime(new Date());
				testRecordService.addTestrecord(record1);// 添加到考试记录表，返回主键
			}
			cr.setUpdateTime(new Date());
			cr.setStatus(1);
			recordService.updateStatus(cr);
			TestRecord record2 = testRecordService.getTestrecordById(record1.getId());
			if (record2 != null) {
				log.info("添加考试记录成功！");
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("考试管理");
				log1.setOperationType("启动");
				log1.setOperationResult("成功");
				log1.setOperationId(teacher.getTeacherId().toString());
				log1.setOperationName(teacher.getTeacherName());
				log1.setOperationContent("管理员【" + teacher.getTeacherName() + "】启动试卷成功!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
				output(response, JsonUtil.buildFalseJson("0", "启动成功"));
			} else {
				log.error("添加记录失败");
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("考试管理");
				log1.setOperationType("启动");
				log1.setOperationResult("失败");
				log1.setOperationId(teacher.getTeacherId().toString());
				log1.setOperationName(teacher.getTeacherName());
				log1.setOperationContent("管理员【" + teacher.getTeacherName() + "】启动试卷失败!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
				output(response, JsonUtil.buildFalseJson("1", "启动失败"));
			}
		}
	}

	/**
	 * 结束考试，修改试卷记录和考试记录表
	 * 
	 * @param record
	 * @param response
	 */
	@RequestMapping("/endExam")
	public void endExam(Integer recordId, Integer classid, HttpServletResponse response, HttpServletRequest request) {
		List<StudentCareer> students = studentCareerService.getStudentByClassNo(classid, 2);// 查找已分班并且班级编号为classid的学生
		session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		int len = 0;
		ClassRecord cr = recordService.getClassRecordById(recordId);
		ClassNo condition = classNoService.getClassById(classid);
		if (currTeacher.getTeacherType().equals("1")) {
			for (StudentCareer sc : students) {
				TestRecord record = testRecordService.getTestRecordByStuandPaper(sc.getStudentId(), cr.getPaperId());
				if (record != null) {
					record.setStatus(3);// 已结束状态
					record.setUpdateTime(new Date());
					record.setExamenStatus(1);//考试完成计算排名用的
					len = testRecordService.updateTestrecord(record);
				}
			}
			cr.setUpdateTime(new Date());
			cr.setStatus(2);
			recordService.updateStatus(cr);
			if (len > 0) {
				log.info("修改成功!");
				output(response, JsonUtil.buildFalseJson("0", "结束成功"));
				Log log1 = new Log();
				log1.setStartTime(new Date());
				log1.setModular("考试管理");
				log1.setOperationType("结束");
				log1.setOperationResult("成功");
				log1.setOperationId(currTeacher.getTeacherId().toString());
				log1.setOperationName(currTeacher.getTeacherName());
				log1.setOperationContent("管理员【" + currTeacher.getTeacherName() + "】结束考试失败!");
				log1.setEndTime(new Date());
				logService.saveLog(log1);
			}
		} else {
			if (!currTeacher.getCampus().equals(condition.getSchoolArea())) {
				output(response, JsonUtil.buildFalseJson("2", "系统检测到您的校区与该班级不一致!"));
			} else {
				for (StudentCareer sc : students) {
					TestRecord record = testRecordService.getTestRecordByStuandPaper(sc.getStudentId(),
							cr.getPaperId());
					if (record != null) {
						record.setStatus(3);// 已结束状态
						record.setUpdateTime(new Date());
						record.setExamenStatus(1);//考试完成计算排名用的
						len = testRecordService.updateTestrecord(record);
					}
				}
				cr.setUpdateTime(new Date());
				cr.setStatus(2);
				recordService.updateStatus(cr);
				output(response, JsonUtil.buildFalseJson("0", "结束成功"));
			}
		}
	}

	/**
	 * 检测是否有考试记录
	 * @param classId
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/testingPaperId")
	public void testingPaperId(Integer classId, HttpServletResponse response, Integer pageNo, Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classId", classId);
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		List<ClassRecord> crList = recordService.findPaperExamList(map);
		if (crList != null && !crList.isEmpty()) {
			output(response, JsonUtil.buildJson(crList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "暂无考试，如有需要请先设置试卷!"));
		}
	}
	
	/**
	 * 检测是否有讲义
	 * @param classId
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/testingNote")
	public void testingNote(Integer classId, HttpServletResponse response, Integer pageNo, Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classId", classId);
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		List<ClassRecord> crList = recordService.findhomeworkIdExamList(map);
		if (crList != null && !crList.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("0", "进行下一步!"));
		}else {
			output(response, JsonUtil.buildFalseJson("1", "暂无安排讲义!"));
		}
	}
	
	/**
	 * 检测是否有作业
	 * @param classId
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/testinghomework")
	public void testinghomework(Integer classId,HttpServletResponse response,Integer pageNo,Integer pageSize){
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classId", classId);
		map.put("pageNo", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		List<ClassRecord> crList = recordService.findhomeworkIdExamList(map);
		if (crList != null && !crList.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("0", "有作业!"));
		}else {
			output(response, JsonUtil.buildFalseJson("1", "系统检测到您还没有添加过作业!"));
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
	public void deleteExam(Integer recordId, HttpServletResponse response, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		ClassRecord record = recordService.getClassRecordById(recordId);
		if (record == null) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		} else if (record.getStatus() != 0) {
			output(response, JsonUtil.buildFalseJson("2", "改试卷已被使用，暂不能删除!"));
		} else {
			PaperInfo pInfo = paperInfoService.getPaperInfoById(record.getPaperId());
			ClassNo condition = classNoService.getClassById(record.getClassId());
			if (currTeacher.getTeacherType().equals("1")) {
				try {
					recordService.deleteClassRecordById(record);
					pInfo.setUpdateTime(new Date());
					if (pInfo.getUsedTimes() != 0) {
						pInfo.setUsedTimes(pInfo.getUsedTimes() - 1);
					} else {
						pInfo.setUsedTimes(0);
					}
					paperInfoService.updateUsedTimes(pInfo);
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
				} catch (Exception e) {
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
			}else {
				if (!currTeacher.getCampus().equals(condition.getSchoolArea())) {
					output(response, JsonUtil.buildFalseJson("5", "系统检测到您所在的校区与改班级不一致!"));
				}else {
					try {
						recordService.deleteClassRecordById(record);
						pInfo.setUpdateTime(new Date());
						if (pInfo.getUsedTimes() != 0) {
							pInfo.setUsedTimes(pInfo.getUsedTimes() - 1);
						} else {
							pInfo.setUsedTimes(0);
						}
						paperInfoService.updateUsedTimes(pInfo);
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
					} catch (Exception e) {
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
	}
	/**
	 * 删除考试学员
	 * @param response
	 * @param id
	 */
	@RequestMapping("/deleteStudentId")
	public void deleteStudentId(HttpServletResponse response,Integer id){
		TestRecord tr = testRecordService.getTestrecordById(id);
		if (tr!= null) {
			testRecordService.deleteRecordById(tr);
			output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
		}else {
			output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
		}
	}
	
	/**
	 * 拿到学员的信息
	 * @param response
	 * @param ytpe
	 * @param calssId
	 * @param paperId
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/studentInformation")
	public void studentInformation(HttpServletResponse response,Integer type,Integer classId,
			Integer paperId,Integer pageNo,Integer pageSize,Integer isTrue,Integer questionId,Integer questionType){
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		int totalcount = 0;
		if (type == 1) {// 试卷
			List<PaperAnswer> paList = paperAnswerService.findStudentInformation(classId,paperId,isTrue,(pageNo-1)*pageSize,pageSize,questionId,questionType);
			totalcount = paperAnswerService.findStudentInformationCount(classId,paperId,isTrue,questionId,questionType);
			PageUtil pageUtil = new PageUtil(totalcount, pageNo, pageSize);
			if (paList != null && !paList.isEmpty()) {
				output(response, JsonUtil.buildJsonByTotalCount(paList, pageUtil.getTotalPageCount()));
			}else {
				output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
			}
		} else {// 作业
			List<HomeworkAnswer> haList = answerService.findStudentInformation(classId,paperId,isTrue,(pageNo-1)*pageSize,pageSize,questionId,questionType);
			totalcount = answerService.findStudentInformationCount(classId,paperId,isTrue,questionId,questionType);
			PageUtil pageUtil = new PageUtil(totalcount, pageNo, pageSize);
			if (haList != null && !haList.isEmpty()) {
				output(response, JsonUtil.buildJsonByTotalCount(haList, pageUtil.getTotalPageCount()));
			}else {
				output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
			}
		}
	}
	
	/**
	 * 拿到题目
	 * @param response
	 * @param questionType
	 */
	@RequestMapping("/seeQuestion")
	public void seeQuestion(HttpServletResponse response,Integer questionType,Integer questionId){
		List<QuestionAnalysis> qaList = new ArrayList<QuestionAnalysis>();
		QuestionAnalysis qa = new QuestionAnalysis();
		if (questionType == 1) {
			QuestionSingleChoice choice = singleservice.getSingleChoiceById(questionId);
			qa.setTimuName(choice.getChoiceDesc());
			qa.setAnswer(choice.getAnswer());
			qa.setAnalysis(choice.getAnswerDesc());
			qa.setDegree(choice.getDegree());
			qa.setOptionA(choice.getOptionA());
			qa.setOptionB(choice.getOptionB());
			qa.setOptionC(choice.getOptionC());
			qa.setOptionD(choice.getOptionD());
			qa.setAnalysis(choice.getAnswerDesc());
			qa.setQuestionType(1);
			qaList.add(qa);
		} else if (questionType == 2) {
			QuestionMulitChoice mChoice = mulitservice.getMulitChoiceById(questionId); 
			qa.setTimuName(mChoice.getChoiceDesc1());
			qa.setAnswer(mChoice.getAnswer1());
			qa.setAnalysis(mChoice.getAnswerDesc1());
			qa.setDegree(mChoice.getDegree1());
			qa.setQuestionType(2);
			qa.setOptionA(mChoice.getOptionA1());
			qa.setOptionB(mChoice.getOptionB1());
			qa.setOptionC(mChoice.getOptionC1());
			qa.setOptionD(mChoice.getOptionD1());
			qaList.add(qa);

		} else if (questionType == 3) {
			QuestionJudge judge = judgeservice.getJudgeById(questionId);
			qa.setTimuName(judge.getJudgeDesc());
			qa.setAnswer(judge.getAnswer2());
			qa.setQuestionType(3);
			qa.setAnalysis(judge.getAnswerDesc2());
			qa.setDegree(judge.getDegree2());
			qaList.add(qa);

		} else if (questionType == 4) {
			QuestionRead read = readservice.getReadById(questionId);
			QuestionRead read2 = readservice.getReadById(read.getParentId());
			qa.setTimuName(read.getOptiontitle());
			qa.setAnswer(read.getAnswer_read());
			qa.setAnalysis(read.getAnswerDesc_read());
			qa.setQuestionType(4);
			qa.setReadDesc(read2.getReadDesc());
			qa.setOptionA(read.getOptionA_read());
			qa.setOptionB(read.getOptionB_read());
			qa.setOptionC(read.getOptionC_read());
			qa.setOptionD(read.getOptionD_read());
			qa.setDegree(read.getDegree_read());
			qaList.add(qa);

		} else if (questionType == 6) {
			QuestionFill fill = fillService.getQuestionFillById(questionId); 
			qa.setTimuName(fill.getFillTitle());
			qa.setAnswer(fill.getFillAnswer());
			qa.setQuestionType(6);
			qa.setAnalysis(fill.getAnswerAnalysis());
			qa.setDegree(fill.getQuestionDegree());
			qaList.add(qa);
		}
		if (qaList != null && !qaList.isEmpty()) {
			output(response, JsonUtil.buildJson(qaList));
		}else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
	}
	/**
	 * 检测班级是否结束课程
	 * @param response
	 * @param classId
	 */
	@RequestMapping("/isEnd")
	public void isEnd(HttpServletResponse response,Integer classId){
		ClassNo classNo = classNoService.getClassById(classId);
		if (classNo.getIsEndCourse()== 1) {
			output(response, JsonUtil.buildFalseJson("1", "系统检测到改班级已结束课程!"));
		}else {
			output(response, JsonUtil.buildFalseJson("0", "允许此操作!"));
		}
	}
}
