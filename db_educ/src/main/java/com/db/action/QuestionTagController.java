package com.db.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.Grade;
import com.db.entity.QuestionFill;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.QuestionTag;
import com.db.entity.QuestionsType;
import com.db.entity.Subject;
import com.db.entity.SubjectiveQuestion;
import com.db.entity.Teacher;
import com.db.entity.TeacherAuthority;
import com.db.service.GradeService;
import com.db.service.QuestionFillService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.QuestionTagService;
import com.db.service.QuestionsTypeService;
import com.db.service.SubjectService;
import com.db.service.SubjectiveQuestionService;
import com.db.service.TeacherAuthorityService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;

@Controller
@RequestMapping("/questionTag")
public class QuestionTagController extends BaseUtil {

	@Resource
	private QuestionTagService questionTagService;
	@Resource
	private QuestionJudgeService judgeService;
	@Resource
	private QuestionMulitChoiceService mulitchoiceService;
	@Resource
	private QuestionSingleChoiceService singlechoiceService;
	@Resource
	private QuestionReadService readService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private QuestionFillService fillService;
	@Resource
	private SubjectiveQuestionService questionService;
	@Resource
	private GradeService gradeService;
	@Resource
	private TeacherAuthorityService authorityService;
	@Resource
	private QuestionsTypeService questionsTypeService;

	/**
	 * 添加标签
	 * 
	 * @param qTag
	 */
	@RequestMapping("/saveTag")
	public void saveTag(String tagName, HttpServletResponse response, HttpServletRequest request, Integer subject,
			Integer grade,Integer parentId,Integer tagLevel) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (tagName == null || tagName == "") {
			output(response, JsonUtil.buildFalseJson("2", "请输入标签!"));
		} else if (subject == null) {
			output(response, JsonUtil.buildFalseJson("3", "请选择科目!"));
		} else {
			QuestionTag qt = questionTagService.getIsName(tagName, subject, grade);
			if (qt != null) {
				output(response, JsonUtil.buildFalseJson("1", "标签已存在!"));
			} else {
				try {
					QuestionTag qTag = new QuestionTag();
					qTag.setTagName(tagName);
					qTag.setCreateTime(new Date());
					qTag.setCreateUser(currTeacher.getTeacherId());
					qTag.setSubject(subject);
					qTag.setGrade(grade);
					if(parentId == null){
					  qTag.setParentId(0);
					  qTag.setTagLevel(1);
					}else{
					  qTag.setParentId(parentId);
					  qTag.setTagLevel(tagLevel);
					}
					questionTagService.saveQuestionTag(qTag);
					output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
				} catch (Exception e) {
					output(response, JsonUtil.buildFalseJson("1", "添加错误!"));
				}
			}
		}
	}

	/**
	 * 标签列表
	 * 
	 * @param qTag
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("tagList")
	public ModelAndView tagList(String tagNamel, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		/*ModelAndView mv = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		Integer subject = null;
		Teacher currTeacher = (Teacher) request.getSession().getAttribute("currTeacher");
		if (currTeacher.getSubject() != null) {
			subject = currTeacher.getSubject();
		}
		int rowsCount = questionTagService.getCount(tagNamel, subject, null, currTeacher.getTeacherType());
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<QuestionTag> qtList = questionTagService.tagList(tagNamel, (pageNo - 1) * pageSize, pageSize, subject,
				currTeacher.getTeacherType());
		if (qtList != null && !qtList.isEmpty()) {
			mv.addObject("qtList", qtList);
		} else {
			mv.addObject("qtList", null);
		}
		List<Subject> subjects = null;
		List<Grade> grades = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			subjects = subjectService.getSubject();
			grades = gradeService.getGrade();
		} else {
			String[] grade = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(grade);
			grades = gradeService.getGradeByIds(gradeList);
			subjects = subjectService.getSubjectById(currTeacher.getSubject());
		}
		mv.setViewName("questionbank/tagList");
		List<QuestionsType> typelist = questionsTypeService.questionsType();
		mv.addObject("typeList", typelist);
		mv.addObject("subjects", subjects);
		mv.addObject("grades", grades);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("tagNamel", tagNamel);
		return mv;*/
		
		ModelAndView mv = new ModelAndView();
		if (pageNo == null) {
			pageNo = 0;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		Integer subject = null;
		Teacher currTeacher = (Teacher) request.getSession().getAttribute("currTeacher");
		if (currTeacher.getSubject() != null) {
			subject = currTeacher.getSubject();
		}
	//	int rowsCount = questionTagService.getCount(tagNamel, subject, null, currTeacher.getTeacherType());
	//	List<QuestionTag> qtList = getParentList(questionTagService.questionTag());
	//@Param("tagName")String tagName,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize,@Param("subject") Integer subject, @Param("type")String type
		List<QuestionTag> qtList = getParentList(questionTagService.tagList(tagNamel,pageNo,pageSize,subject));
		if (qtList != null && !qtList.isEmpty()) {
			mv.addObject("qtList", qtList);
		} else {
			mv.addObject("qtList", null);
		}
		
		int rowsCount = qtList.size();
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		
		List<Subject> subjects = null;
		List<Grade> grades = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			subjects = subjectService.getSubject();
			grades = gradeService.getGrade();
		} else {
			String[] grade = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(grade);
			grades = gradeService.getGradeByIds(gradeList);
			subjects = subjectService.getSubjectById(currTeacher.getSubject());
		}
		mv.setViewName("questionbank/tagList");
		List<QuestionsType> typelist = questionsTypeService.questionsType();
		mv.addObject("typeList", typelist);
		mv.addObject("subjects", subjects);
		mv.addObject("grades", grades);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("tagNamel", tagNamel);
		return mv;
		
	}

	/**
	 * 搜索标签
	 * 
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @param response
	 */
	@RequestMapping("gettagbysearch")
	public String getTagBySearch(HttpServletRequest request, Integer pageNo, Integer pageSize,
			HttpServletResponse response, String tagName) {
	//	String content = request.getParameter("content");
		if (pageNo == null) {
			pageNo = 0;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		List<QuestionTag> list = questionTagService.getTagNamePage(tagName,(pageNo - 1) * pageSize, pageSize
				);
		if (list != null && !list.isEmpty()) {
			output(response, JsonUtil.buildJson(list));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "该条件下没有数据!"));
		}
		return "questionbank/tagList";
	}

	@RequestMapping("getalltag")
	public void getAllTag(HttpServletRequest request, HttpServletResponse response, Integer subjectId, Integer grade) {
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 6;
		}
		int totalcount = questionTagService.getCount("", subjectId, grade, null);
		PageUtil pageUtil = new PageUtil(totalcount, pageNo, pageSize);
		List<QuestionTag> list = questionTagService.getTagByPage(pageUtil.getStartPos(), pageSize, subjectId, grade);
		output(response, JsonUtil.buildJsonByTotalCount(list, pageUtil.getTotalPageCount()));
	}

	/**
	 * 题库里的添加标签
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addTag")
	public void addTag(String tagName, HttpServletResponse response, HttpServletRequest request, Integer subject,
			Integer grade) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		QuestionTag qt = questionTagService.getIsName(tagName, subject, grade);
		if (tagName == null || tagName == "") {
			output(response, JsonUtil.buildFalseJson("2", "请输入标签!"));
		} else if (qt != null) {
			output(response, JsonUtil.buildFalseJson("1", "标签已存在或标签描述类似!"));
		} else {
			try {
				QuestionTag qTag = new QuestionTag();
				qTag.setTagName(tagName);
				qTag.setCreateTime(new Date());
				qTag.setSubject(subject);
				qTag.setGrade(grade);
				qTag.setCreateUser(currTeacher.getTeacherId());
				questionTagService.saveQuestionTag(qTag);// 返回主键
				QuestionTag questionTag = questionTagService.getTagById(qTag.getTagId());
				if (questionTag != null) {
					List<QuestionTag> list = new ArrayList<>();
					list.add(questionTag);
					output(response, JsonUtil.buildJson(list));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "添加错误!"));
				}
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "添加错误!"));
			}
		}
	}

	/**
	 * 编辑标签
	 * 
	 * @param request
	 * @param response
	 * @param tagName,Integer
	 *            subject,Integer grade
	 */
	@RequestMapping("/deletetag")
	public void deleteTag(HttpServletRequest request, HttpServletResponse response, String tagName) {
		int tagid = Integer.valueOf(request.getParameter("tagId"));
		QuestionTag qt = questionTagService.getTagById(tagid);
		QuestionTag tag = questionTagService.getIsName(tagName, qt.getSubject(), qt.getGrade());
		if (tag != null && !qt.getTagName().equals(tagName)) {
			output(response, JsonUtil.buildFalseJson("2", "该标签已存在!"));
		} else if (tagName == null || tagName == "") {
			output(response, JsonUtil.buildFalseJson("4", "请输入标签!"));
		} else {
			try {
				qt.setTagName(tagName);
				questionTagService.updateTag(qt);
				output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "编辑失败!"));
			}
		}
	}

	/**
	 * 删除标签
	 * 
	 * @param response
	 * @param tagId
	 */
	@RequestMapping("/tagdelete")
	public void tagdelete(HttpServletResponse response, Integer tagId) {
		String tag = "," + tagId + ",";
		List<QuestionSingleChoice> qService = singlechoiceService.getTageId(tag);
		List<QuestionMulitChoice> qmc = mulitchoiceService.getTageId(tag);
		List<QuestionJudge> qJudge = judgeService.getTageId(tag);
		List<QuestionRead> qRead = readService.getReadTageId(tag);
		List<QuestionFill> fill = fillService.getReadTageId(tag);
		List<SubjectiveQuestion> questions = questionService.getReadTageId(tag);
		if ((qService != null && !qService.isEmpty()) || (qmc != null && !qmc.isEmpty())
				|| (qJudge != null && !qJudge.isEmpty()) || (questions != null && !questions.isEmpty())
				|| (qRead != null && !qRead.isEmpty()) || (!fill.isEmpty() && fill != null)) {
			output(response, JsonUtil.buildFalseJson("1", "该标签已被使用，暂时不能删除!"));
		} else {
			try {
				questionTagService.deleteTag(tagId);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
			}
		}
	}

	/**
	 * 所有科目
	 * 
	 * @param subject
	 * @param subjectName
	 */
	@RequestMapping("/subjectList")
	public void subjectList(HttpServletResponse response) {
		List<Subject> sList = subjectService.getSubject();
		if (sList != null && !sList.isEmpty()) {
			output(response, JsonUtil.buildJson(sList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 所有年级
	 * 
	 * @return list<grades>
	 */
	@RequestMapping("/getgradeList")
	public void getgradeList(HttpServletResponse response) {
		List<Grade> grades = gradeService.getGrade();
		if (grades != null && !grades.isEmpty()) {
			output(response, JsonUtil.buildJson(grades));
		} else {
			output(response, JsonUtil.builderFalseJson("1", "没有数据"));
		}
	}

	@RequestMapping("/getQuestionByTag")
	public void getQuestionByTag(Integer tagId, Integer type, Integer isPublic, Integer pageNo, Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		if (type == null) {
			type = 1;
		}
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<TeacherAuthority> taList = authorityService.getTeacherIdById(teacher.getTeacherId());
		int totalCount = 0;
		List list = new ArrayList<>();
		if (teacher.getTeacherType().equals("1")) {// 超级管理员能看到所有的
			if (type == 1) {
				totalCount = singlechoiceService.getAllQuestionsCount(type, null, null, isPublic, tagId, null);
				List<QuestionSingleChoice> qscList = singlechoiceService.getAllQuestions((pageNo - 1) * pageSize,
						pageSize, type, null, null, isPublic, tagId, null);
				if (!qscList.isEmpty() && qscList != null) {
					list = qscList;
				} else {
				}
			} else if (type == 2) {
				totalCount = mulitchoiceService.getAllQuestionsCount(type, null, null, isPublic, tagId, null);
				List<QuestionMulitChoice> qmcList = mulitchoiceService.getAllQuestions((pageNo - 1) * pageSize,
						pageSize, type, null, null, isPublic, tagId, null);
				if (!qmcList.isEmpty() && qmcList != null) {
					list = qmcList;
				} else {
				}
			} else if (type == 3) {
				totalCount = judgeService.getAllQuestionsCount(type, null, null, isPublic, tagId, null);
				List<QuestionJudge> qjList = judgeService.getAllQuestions((pageNo - 1) * pageSize, pageSize, type, null,
						null, isPublic, tagId, null);
				if (!qjList.isEmpty() && qjList != null) {
					list = qjList;
				} else {
				}
			} else if (type == 4) {
				totalCount = readService.getAllQuestionsCount(type, null, null, isPublic, tagId, null);
				List<QuestionRead> reads = readService.getAllQuestions((pageNo - 1) * pageSize, pageSize, type, null,
						null, isPublic, tagId, null);
				for (QuestionRead qr : reads) {
					qr.setQjList(readService.getReadChildByParentId(qr.getReadId()));
					// 获取每到小题题目
					List<QuestionRead> qrList = readService.getReadChildByParentId(qr.getReadId());
				}
				if (!reads.isEmpty() && reads != null) {
					list = reads;
				} else {
				}
			} else if (type == 5) {// 主观题
				totalCount = questionService.getAllQuestionsCount(type, null, null, isPublic, tagId, null);
				List<SubjectiveQuestion> sqList = questionService.getSubjectiveQuestion((pageNo - 1) * pageSize,
						pageSize, type, null, null, isPublic, tagId, null);
				if (sqList != null && !sqList.isEmpty()) {
					list = sqList;
				} else {
				}
			} else {// 填空题
				List<QuestionFill> qfList = fillService.QuestionFillList((pageNo - 1) * pageSize, pageSize, type, null,
						null, isPublic, tagId, null);
				totalCount = fillService.getFillCount(type, null, null, isPublic, tagId, null);
				if (qfList != null && !qfList.isEmpty()) {
					list = qfList;
				} else {
				}
			}
		} else {// 只能看到自己的和别人公开的
			for (TeacherAuthority ta : taList) {
				if (ta.getQuestionsAuthority() == 1 || ta.getQuestionsAuthority() == 3) {
					// 能读所有公开的题目
					if (type == 1) {
						totalCount = singlechoiceService.getconutIspublic(type, null, null, isPublic,
								teacher.getTeacherId(), tagId, null);
						List<QuestionSingleChoice> singleChoices = singlechoiceService.getSingleByTeacherIdIsPublic(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, type, null, null, isPublic,
								tagId, null);// 获取单选题
						if (!singleChoices.isEmpty() && singleChoices != null) {
							list = singleChoices;
						} else {
						}
					} else if (type == 2) {
						totalCount = mulitchoiceService.getconutIspublic(type, null, null, isPublic,
								teacher.getTeacherId(), tagId, null);
						List<QuestionMulitChoice> mulitChoices = mulitchoiceService.getMulitByTeacherIdIsPublic(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, type, null, null, isPublic,
								tagId, null);// 获取多选题
						if (!mulitChoices.isEmpty() && mulitChoices != null) {
							list = mulitChoices;
						} else {
						}
					} else if (type == 3) {
						totalCount = judgeService.getconutIspublic(type, null, null, isPublic, teacher.getTeacherId(),
								tagId, null);
						List<QuestionJudge> judges = judgeService.getJudgeByTeacherIdIsPublic(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, type, null, null, isPublic, tagId, null);// 获取判断题
						if (!judges.isEmpty() && judges != null) {
							list = judges;
						} else {
						}
					} else if (type == 4) {
						totalCount = readService.getconutIspublic(type, null, null, isPublic, teacher.getTeacherId(),
								tagId, null);
						List<QuestionRead> reads = readService.getReadByTeacherIdIsPublic(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, type, null, null, isPublic, tagId, null);// 获取阅读理解
						for (QuestionRead qr : reads) {
							// 获取每到小题题目
							qr.setQjList(readService.getReadChildByParentId(qr.getReadId()));
							// mv.addObject("qrList", qr.getQjList());
						}
						if (!reads.isEmpty() && reads != null) {
							list = reads;
						} else {
						}
					} else if (type == 5) {// 主观题

						List<SubjectiveQuestion> sqList = questionService.getPublicSubjectiveQuestion(
								(pageNo - 1) * pageSize, pageSize, type, null, null, isPublic, teacher.getTeacherId(),
								tagId, null);
						totalCount = questionService.getIsPublicCount(type, null, null, isPublic,
								teacher.getTeacherId(), tagId, null);
						if (sqList != null && !sqList.isEmpty()) {
							list = sqList;
						} else {
						}
					} else {// 填空题
						List<QuestionFill> qfList = fillService.getPublicQuestionFillList((pageNo - 1) * pageSize,
								pageSize, type, null, null, isPublic, teacher.getTeacherId(), tagId, null);
						totalCount = fillService.getIsPublicCount(type, null, null, isPublic, teacher.getTeacherId(),
								tagId, null);
						if (qfList != null && !qfList.isEmpty()) {
							list = qfList;
						} else {
						}
					}
				} else {
					// 只能读自己所有的题目
					if (type == 1) {
						totalCount = singlechoiceService.getconut(type, null, null, isPublic, teacher.getTeacherId(),
								tagId, null);
						List<QuestionSingleChoice> singleChoices = singlechoiceService.getSingleByTeacherId(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, type, null, null, isPublic,
								tagId, null);// 获取单选题
						if (!singleChoices.isEmpty() && singleChoices != null) {
							list = singleChoices;
						} else {
						}
					} else if (type == 2) {
						totalCount = mulitchoiceService.getcount(type, null, null, isPublic, teacher.getTeacherId(),
								tagId, null);
						List<QuestionMulitChoice> mulitChoices = mulitchoiceService.getMulitByTeacherId(
								teacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, type, null, null, isPublic,
								tagId, null);// 获取多选题
						if (!mulitChoices.isEmpty() && mulitChoices != null) {
							list = mulitChoices;
						} else {
						}
					} else if (type == 3) {
						totalCount = judgeService.getcount(type, null, null, isPublic, teacher.getTeacherId(), tagId,
								null);
						List<QuestionJudge> judges = judgeService.getJudgeByTeacherId(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, type, null, null, isPublic, tagId, null);// 获取判断题
						if (!judges.isEmpty() && judges != null) {
							list = judges;
						} else {
						}
					} else if (type == 4) {
						totalCount = readService.getcount(type, null, null, isPublic, teacher.getTeacherId(), tagId,
								null);
						List<QuestionRead> reads = readService.getReadByTeacherId(teacher.getTeacherId(),
								(pageNo - 1) * pageSize, pageSize, type, null, null, isPublic, tagId, null);// 获取阅读理解
						for (QuestionRead qr : reads) {
							// 获取每到小题题目
							List<QuestionRead> qrList = readService.getReadChildByParentId(qr.getReadId());
						}
						if (!reads.isEmpty() && reads != null) {
							list = reads;
						} else {
						}
					} else if (type == 5) {// 主观题
						totalCount = questionService.getPrivateCount(type, null, null, isPublic, teacher.getTeacherId(),
								tagId, null);
						List<SubjectiveQuestion> sqList = questionService.getPrivateSubjectiveQuestion(
								(pageNo - 1) * pageSize, pageSize, type, null, null, isPublic, teacher.getTeacherId(),
								tagId, null);
						if (sqList != null && !sqList.isEmpty()) {
							list = sqList;
						} else {
						}
					} else {// 填空题
						List<QuestionFill> qfList = fillService.getPrivateQuestionFillList((pageNo - 1) * pageSize,
								pageSize, type, null, null, isPublic, teacher.getTeacherId(), tagId, null);
						totalCount = fillService.getPrivateFillCount(type, null, null, isPublic, teacher.getTeacherId(),
								tagId, null);
						if (qfList != null && !qfList.isEmpty()) {
							list = qfList;
						} else {
						}
					}
				}
			}
		}
		PageUtil util = new PageUtil(totalCount, pageNo, pageSize);
		if (list != null && !list.isEmpty()) {
			output(response, JsonUtil.buildJsonByTotalCount(list, util.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "暂无绑定的题目"));
		}
	}

	//// java 递归无限极分类
	// @RequestMapping("/getTagList")
	// public void getTagList(int pid, int level,HttpServletResponse response){
	// List<QuestionTag> list = questionTagService.questionTag();
	// List<QuestionTag> list2 = getCategory(list, pid, level);
	// output(response, JsonUtil.buildJson(list2));
	// }
	//
	/*public List<QuestionTag> getCategory(List<QuestionTag> list,Integer pid, Integer level) {
        if(level == null){
        	level = 1;
        }
        if(pid == null){
        	pid = 0;
        }
		List<QuestionTag> result = new ArrayList<QuestionTag>();
		for (QuestionTag tag : list) {
			QuestionTag cate = new QuestionTag();
			if (tag.getParentId() == pid) {
				cate.setParentId(tag.getParentId());
				cate.setTagId(tag.getTagId());
				cate.setTagName(tag.getTagName());
				//拿到子
				List<QuestionTag> tagList = new ArrayList<QuestionTag>();
				List<QuestionTag> pidList = questionTagService.findQuestionTagPidList(tag.getTagId());
				if(!pidList.isEmpty()){
					for (QuestionTag t:pidList) {
						QuestionTag tag1 = new QuestionTag();
						tag1.setParentId(t.getParentId());
						tag1.setTagId(t.getTagId());
						tag1.setTagName(t.getTagName());
						tagList.add(tag1);
					}
				}
				cate.setParentList(tagList);
				result.add(cate);
				getCategory(list, tag.getTagId(), level + 1);
			}
		}
		return result;
	}
*/
	/**
	 * 根据年级和科目获取标签 :分成调用该接口
	 * 
	 * @param response
	 * @param subjectId
	 * @param gradeId
	 */
	@RequestMapping("/gettagbysubandgra")
	public void getTagBySubAndGra(HttpServletResponse response, Integer subjectId, Integer gradeId) {
		List<QuestionTag> lists = questionTagService.getTagByOther(subjectId, gradeId);
		List<QuestionTag> tagList = getParentList(lists);
		output(response,JsonUtil.buildJson(tagList));
	}
    
	/**
	 * 获取父标签
	 * @param lists
	 * @return
	 */ 
	public List<QuestionTag> getParentList(List<QuestionTag> lists){
		List<QuestionTag> treeList = new ArrayList<QuestionTag>();
		for(QuestionTag tag : lists){
			QuestionTag tag1 = new QuestionTag();
			if(tag.getParentId() == 0){
				tag1.setParentId(tag.getParentId());
				tag1.setTagId(tag.getTagId());
				tag1.setCreateTime(tag.getCreateTime());
				if (tag.getSubject2() != null) {
					tag1.setSubjectName(tag.getSubject2().getSubjectName());
				}
				if (tag.getGrade2() !=null) {
					tag1.setGradeName(tag.getGrade2().getGradeName());
				}
				tag1.setTagName(tag.getTagName());
				tag1.setTagLevel(tag.getTagLevel());
				tag1.setChildren(getChildrenList(lists,tag.getTagId()));
				treeList.add(tag1);
			}
		}
		return treeList;
	}
	
	/**
	 * 获取子标签
	 * @param lists
	 * @param pid
	 * @return
	 */
	public List<QuestionTag> getChildrenList(List<QuestionTag> lists,int pid){
		List<QuestionTag> treeList = new ArrayList<QuestionTag>();
		for(QuestionTag tag : lists){
			QuestionTag tag1 = new QuestionTag();
			if(tag.getParentId() == pid){
				tag1.setParentId(tag.getParentId());
				tag1.setTagId(tag.getTagId());
				tag1.setTagName(tag.getTagName());
				tag1.setTagLevel(tag.getTagLevel());
				tag1.setChildren(getChildrenList(lists,tag.getTagId()));
				treeList.add(tag1);
			}
		}
		return treeList;
	}
	/*public List<QuestionTag> get_parent_list(List<QuestionTag> lists,int id){
		List<QuestionTag> result = new ArrayList<QuestionTag>();
        for(QuestionTag tag : lists){
        	 if(tag.getParentId() == id){
        		 QuestionTag tag1 = new QuestionTag();
					tag1.setParentId(tag.getParentId());
					tag1.setTagId(tag.getTagId());
					tag1.setTagName(tag.getTagName());
					result.add(tag1);
					get_parent_list(lists,tag.getTagId());
        	 }
        } 
		return result;
	}*/
	
	public static void main(String[] args) {
		System.out.println(f(6));
	}

	public static int f(int n) {
		if (1 == n || 2 == n)
			return 1;
		else
			return f(n - 1) + f(n - 2);
	}
}
