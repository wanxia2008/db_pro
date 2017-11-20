package com.db.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.Grade;
import com.db.entity.Homework;
import com.db.entity.LectureNotes;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.Subject;
import com.db.service.GradeService;
import com.db.service.HomeworkService;
import com.db.service.LectureNotesService;
import com.db.service.PaperInfoService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.SubjectService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;

@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseUtil {

	@Resource
	private SubjectService subjectService;
	@Resource
	private QuestionJudgeService judgeService;
	@Resource
	private QuestionMulitChoiceService mulitchoiceService;
	@Resource
	private QuestionSingleChoiceService singlechoiceService;
	@Resource
	private QuestionReadService readService;
	@Resource
	private PaperInfoService paperInfoService;
	@Resource
	private HomeworkService homeworkService;
	@Resource
	private LectureNotesService lectureNotesService;
	@Resource
	private GradeService gradeService;

	/**
	 * 科目列表
	 * 
	 * @return
	 */
	@RequestMapping("/subjectList")
	private ModelAndView subjectList(Integer pageNo, Integer pageSize, String subjectIdName) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		ModelAndView mv = new ModelAndView();
		int totalPages = 0;
		int rowsCount = subjectService.getCount(subjectIdName);
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<Subject> sList = subjectService.findSubjectList((pageNo - 1) * pageSize, pageSize, subjectIdName);
		if (sList != null && !sList.isEmpty()) {
			mv.addObject("sList", sList);
		} else {
			mv.addObject("sList", null);
		}
		List<Grade> gList = gradeService.getGrade();
		mv.addObject("gList", gList);
		mv.addObject("pageSize", pageSize);
		mv.addObject("page", pageNo);
		mv.addObject("totalPages", totalPages);
		mv.addObject("subjectIdName", subjectIdName);
		mv.setViewName("subjectmanage/subjectList");
		return mv;
	}
	/**
	 * 新增科目
	 * @param response
	 * @param subjectName
	 */
	@RequestMapping("/saveSubject")
	public void saveSubject(HttpServletResponse response,String subjectName,Integer gradeId){
		Subject subject = subjectService.getSubjectNmae(subjectName);
		if (subject != null) {
			output(response, JsonUtil.buildFalseJson("1", "科目已存在!"));
		} else if (subjectName == null || subjectName == "") {
			output(response, JsonUtil.buildFalseJson("2", "请输入科目!"));
		} 
//		else if (gradeId == null) {
//			output(response, JsonUtil.buildFalseJson("3", "请请选择年级!"));
//		} 
		else {
			Subject subject2 = new Subject();
			subject2.setCreateTime(new Date());
//			subject2.setGradeId(gradeId);
			subject2.setSubjectName(subjectName);
			subjectService.addSubject(subject2);
			output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
		}
	}
	
	/**
	 * 修改科目
	 * @param response
	 * @param subjectId
	 * @param subjectNmae
	 */
	@RequestMapping("/updateSubject")
	public void updateSubject(HttpServletResponse response,Integer subjectId,String subjectName,Integer gradeId){
		Subject subject = subjectService.getSubjectNmae(subjectName);
		Subject subject2 = subjectService.findSubjectById(subjectId);
		if (!subject2.getSubjectName().equals(subjectName) && subject != null ) {
			output(response, JsonUtil.buildFalseJson("1", "科目已存在!"));
		}  else if (subjectName == null || subjectName == "") {
			output(response, JsonUtil.buildFalseJson("2", "请输入科目!"));
		} 
//		else if (gradeId == null) {
//			output(response, JsonUtil.buildFalseJson("3", "请选择年级!"));
//		} 
		else {
			subject2.setCreateTime(new Date());
//			subject2.setGradeId(gradeId);
			subject2.setSubjectName(subjectName);
			subjectService.updateSubject(subject2);
			output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
		}
	}
	/**
	 * 删除科目
	 * @param response
	 * @param subjectId
	 */
	@RequestMapping("subjectdelete")
	public void subjectdelete(HttpServletResponse response,Integer subjectId){
//		Subject subject2 = subjectService.findSubjectById(subjectId);
		List<QuestionSingleChoice> qscList = singlechoiceService.getSubjectById(subjectId);
		List<QuestionRead> qrList =  readService.getSubjectById(subjectId);
		List<QuestionJudge> qjList = judgeService.getSubjectById(subjectId);
		List<QuestionMulitChoice> qmList = mulitchoiceService.getSubjectById(subjectId);
		List<LectureNotes> lnList = lectureNotesService.getSubjectById(subjectId);
		List<Homework> hList = homeworkService.getSubjectById(subjectId);
		if ((qscList != null && !qscList.isEmpty()) || (qjList != null && !qjList.isEmpty()) ||(qrList != null && !qrList.isEmpty())
				|| (qmList != null && !qmList.isEmpty()) || (lnList != null && !lnList.isEmpty()) || (hList != null && !hList.isEmpty()) ) {
			  output(response, JsonUtil.buildFalseJson("1", "该科目已被使用!"));
		}else {
			subjectService.deleteSubject(subjectId);
			output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
		}
	}

	/**
	 *  ajax分业
	 * @param pageNo
	 * @param pageSize
	 * @param roomName
	 * @param response
	 */
	@RequestMapping("/getSubjectList")
	public void getSubjectList(Integer pageNo, Integer pageSize, String subjectIdName,
			HttpServletResponse response){
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 6;
		}
		int rowsCount = subjectService.getCount(subjectIdName);
		PageUtil util = new PageUtil(rowsCount, pageNo, pageSize);
		List<Subject> sList = subjectService.findSubjectList(util.getStartPos(), util.getPageSize(), subjectIdName);
		if (!sList.isEmpty() && sList != null) {
			output(response, JsonUtil.buildJsonByTotalCount(sList, util.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
	}
}
