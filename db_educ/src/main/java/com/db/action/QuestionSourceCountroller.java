package com.db.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.QuestionSource;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.QuestionSourceService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/source")
public class QuestionSourceCountroller extends BaseUtil {

	@Resource
	private QuestionSourceService sourceService;
	@Resource
	private QuestionJudgeService judgeService;
	@Resource
	private QuestionMulitChoiceService mulitchoiceService;
	@Resource
	private QuestionSingleChoiceService singlechoiceService;
	@Resource
	private QuestionReadService readService;

	/**
	 * 题目来源列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param qs
	 * @return
	 */
	@RequestMapping("/questionSourceList")
	public ModelAndView questionSourceList(Integer pageNo, Integer pageSize, String sourceNamel) {
		ModelAndView mv = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = sourceService.getCount(sourceNamel);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<QuestionSource> qcList = sourceService.getSourceList(sourceNamel, (pageNo - 1) * pageSize, pageSize);
		if (qcList != null && !qcList.isEmpty()) {
			mv.addObject("qcList", qcList);
		}else {
			mv.addObject("qcList", null);
		}
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("sourceNamel", sourceNamel);
		mv.setViewName("questionbank/sourceList");
		return mv;
	}

	/**
	 * 添加题目
	 * 
	 * @param response
	 * @param sourceName
	 */
	@RequestMapping(value = "saveSourceName")
	public void saveSourceName(HttpServletResponse response, String sourceName) {
		if (sourceName == null || sourceName == "") {
			output(response, JsonUtil.buildFalseJson("2", "请输入题目来源!"));
		} else {
			QuestionSource qc = sourceService.getSourceName(sourceName);

			if (qc != null) {
				output(response, JsonUtil.buildFalseJson("1", "已存在!"));
			} else {
				QuestionSource qSource = new QuestionSource();
				qSource.setSourceName(sourceName);
				qSource.setCreateTime(new Date());
				sourceService.saveSource(qSource);
				output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
			}
		}
	}

	/**
	 * 修改题目
	 * 
	 * @param response
	 * @param sourceId
	 */
	@RequestMapping("sourceDelete")
	public void sourceDelete(HttpServletResponse response, Integer sourceId, String sourceName) {
		QuestionSource qSource = sourceService.getSourceName(sourceName);
		QuestionSource qs = sourceService.getScourseById(sourceId);
		if (qSource != null && !qs.getSourceName().equals(sourceName)) {
			output(response, JsonUtil.buildFalseJson("2", "该题目来源已存在!"));
		} else if (sourceName == null || sourceName == "") {
			output(response, JsonUtil.buildFalseJson("4", "请输入题目来源名称!"));
		} else {
			try {
				qs.setSourceName(sourceName);
				sourceService.updateSource(qs);
				output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "编辑失败!"));
			}
		}
	}
	
	/**
	 * 删除题目来源
	 * @param response
	 * @param sourceId
	 */
	@RequestMapping("/tagdelete")
	public void tagdelete(HttpServletResponse response,Integer sourceId){
		List<QuestionSingleChoice> qService = singlechoiceService.getQuestionSource(sourceId);
		List<QuestionMulitChoice> qmc = mulitchoiceService.getQuestionSource(sourceId);
		List<QuestionJudge> qJudge = judgeService.getQuestionSource(sourceId);
		List<QuestionRead> qRead = readService.getQuestionSource(sourceId);
		if ((qService != null && !qService.isEmpty()) || (qmc != null && !qmc.isEmpty()) || (qJudge != null && !qJudge.isEmpty()) || (qRead != null && !qRead.isEmpty())) {
			output(response, JsonUtil.buildFalseJson("2", "该题目来源已被使用，删除失败!"));
		}else {
			try {
				sourceService.sourceDelete(sourceId);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
			}
		}
	}
}
