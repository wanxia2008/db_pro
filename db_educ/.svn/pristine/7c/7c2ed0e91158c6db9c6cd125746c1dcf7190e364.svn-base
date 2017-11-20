package com.db.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.Homework;
import com.db.entity.PaperInfo;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.RangeLesson;
import com.db.service.HomeworkService;
import com.db.service.PaperInfoService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.RangeLessonService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/rangeLesson")
public class RangeLessonController extends BaseUtil {

	@Resource
	private RangeLessonService lessonService;
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

	/**
	 * 范围列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param lessonName
	 * @return
	 */
	@RequestMapping("/rangeLessonList")
	public ModelAndView rangeLessonList(Integer pageNo, Integer pageSize, String lessonName2) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = lessonService.getCount(lessonName2);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		ModelAndView mv = new ModelAndView();
		List<RangeLesson> rlList = lessonService.rangeLessonList(lessonName2, (pageNo - 1) * pageSize, pageSize);
		if (rlList != null && !rlList.isEmpty()) {
			mv.addObject("rlList", rlList);
		}else {
			mv.addObject("rlList", null);
		}
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("lessonName2", lessonName2);
		mv.setViewName("exampaper/lessonList");
		return mv;
	}

	/**
	 * 添加范围
	 * 
	 * @param response
	 * @param lessonName1
	 */
	@RequestMapping("/saveLesson")
	public void saveLesson(HttpServletResponse response, String lessonName1) {
		RangeLesson rlLesson = lessonService.getLessonNameById(lessonName1);
		if (rlLesson != null) {
			output(response, JsonUtil.buildFalseJson("1", "该范围名称已存在!"));
		} else if (lessonName1 == null || lessonName1 == "") {
			output(response, JsonUtil.buildFalseJson("2", "请输入范围名称!"));
		} else {
			RangeLesson rl = new RangeLesson();
			rl.setCreateTime(new Date());
			rl.setLessonName(lessonName1);
			lessonService.addLesson(rl);
			output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
		}
	}

	/**
	 * 
	 * 修改范围
	 * 
	 * @param response
	 * @param lessonId
	 * @param lessonName
	 */
	@RequestMapping("/updateLessonName")
	public void updateLessonName(HttpServletResponse response, Integer lessonId, String lessonName) {
		RangeLesson rlLesson = lessonService.getLessonById(lessonId);
		RangeLesson rl = lessonService.getLessonNameById(lessonName);
		if (rlLesson == null) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		} else if (rl != null && !rlLesson.getLessonName().equals(lessonName)) {
			output(response, JsonUtil.buildFalseJson("1", "该范围名称已存在!"));
		} else if (lessonName == null || lessonName == "") {
			output(response, JsonUtil.buildFalseJson("2", "请输入范围名称!"));
		} else {
			rlLesson.setCreateTime(new Date());
			rlLesson.setLessonName(lessonName);
			lessonService.updateLessonName(rlLesson);
			output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
		}
	}

	/**
	 * 删除范围
	 * 
	 * @param response
	 * @param lessonId
	 */
	@RequestMapping("/lessonDelete")
	public void lessonDelete(HttpServletResponse response, Integer lessonId) {
		List<QuestionSingleChoice> qsc = singlechoiceService.getknowledgeBegin(lessonId);
		List<QuestionMulitChoice> qmc = mulitchoiceService.getknowledgeBegin(lessonId);
		List<QuestionJudge> qj = judgeService.getknowledgeBegin(lessonId);
		List<QuestionRead> qr = readService.getknowledgeBegin(lessonId);
		List<PaperInfo> paInfo = paperInfoService.getknowledgeBegin(lessonId);
		List<Homework> homework = homeworkService.getknowledgeBegin(lessonId);
		if ((qsc != null && !qsc.isEmpty()) || (qmc != null && !qmc.isEmpty()) || (qj != null && !qj.isEmpty()) || (qr != null && !qr.isEmpty())
				             || (paInfo != null && !paInfo.isEmpty()) || (homework != null && !homework.isEmpty())) {
			output(response, JsonUtil.buildFalseJson("2", "该范围已被使用，暂时不能删除!"));
		} else {
			try {
				lessonService.lessonDelete(lessonId);
				output(response, JsonUtil.buildFalseJson("0", "删除范围成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "删除范围失败!"));
			}
		}
	}
}
