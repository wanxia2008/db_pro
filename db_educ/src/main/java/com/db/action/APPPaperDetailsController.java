package com.db.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.QuestionJudge;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.service.PaperDetailsService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/app/paperDetails")
public class APPPaperDetailsController extends BaseUtil {

	@Resource
	private PaperDetailsService paperDetailsService;
	@Resource
	private QuestionJudgeService questionJudgeService;
	@Resource
	private QuestionReadService questionReadService;
	@Resource
	private QuestionSingleChoiceService questionSingleChoiceService;
	@Resource
	private QuestionMulitChoiceService questionMulitChoiceService;

	/**
	 * 试卷信息
	 * 
	 * @param response
	 * @param piId
	 * @param type
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/paper")
	public void PaperDetails(HttpServletResponse response, Integer piId, int type, Integer pageNo, Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		List list = null;
		if (type == 1) {
			list = paperDetailsService.getPaerById(piId, (pageNo - 1) * pageSize, pageSize);
		} else if (type == 2) {
			questionMulitChoiceService.questionMulitPaper(piId, (pageNo - 1) * pageSize, pageSize);
		} else if (type == 3) {
			list = questionJudgeService.getQuestionJudgeById(piId, (pageNo - 1) * pageSize, pageSize);
		} else if (type == 4) {
			list = questionReadService.getQuestionReadById(piId, (pageNo - 1) * pageSize, pageSize);
		}
		if (list != null && !list.isEmpty()) {
			output(response, JsonUtil.buildJson(list));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	
	/**
	 * 添加考卷到题库
	 * @param response
	 * @param type
	 * @param qJudge
	 * @param qRead
	 * @param qChoice
	 * @param pageNo
	 * @param paeSize
	 */
	@RequestMapping("/save")
	public void save(HttpServletResponse response, int type, QuestionJudge qJudge, QuestionRead qRead,
			QuestionSingleChoice qChoice) {
		
		if (type == 1) {
			qChoice.setCreateTime(new Date());
			questionSingleChoiceService.addQuestionSingle(qChoice);
			output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
		} else if (type == 2) {
			output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
		} else if (type == 3) {
			output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
		}
	}
}
