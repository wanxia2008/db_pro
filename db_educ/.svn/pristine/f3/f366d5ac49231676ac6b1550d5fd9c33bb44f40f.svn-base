package com.db.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.Task;
import com.db.entity.TaskRule;
import com.db.service.TaskRuleService;
import com.db.service.TaskService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.PageUtil;

@Controller
@RequestMapping("/taskRule")
public class TaskRuleController extends BaseUtil{
	@Resource
	private TaskRuleService taskRuleService;
	@Resource
	private TaskService taskService;
	
	/**
	 * 任务类型列表
	 * @param pageNo
	 * @param pageSize
	 * @param ruleName
	 * @return
	 */
	@RequestMapping("taskRuleList")
	public ModelAndView taskRuleList(Integer pageNo,Integer pageSize,String ruleName1){
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = taskRuleService.getCount(ruleName1);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		}else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		ModelAndView mv = new ModelAndView();
		List<TaskRule> trList = taskRuleService.taskRuleList(ruleName1,(pageNo-1)*pageSize,pageSize);
		if (trList != null && !trList.isEmpty()) {
			mv.addObject("trList", trList);
		}else {
			mv.addObject("trList", null);
		}
		mv.setViewName("taskmanage/taskRuleList");
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("ruleName1", ruleName1);
		return mv;
	}
	/**
	 * 新增任务类型
	 * @param response
	 * @param taskRuleName
	 */
	@RequestMapping("/saveTaskRole")
	public void saveTaskRole(HttpServletResponse response,String taskRuleName){
		TaskRule taskRule = taskRuleService.getTaskRuleName(taskRuleName);
		if (taskRuleName == null || taskRuleName == "") {
			output(response, JsonUtil.buildFalseJson("2", "请输入任务标题!"));
		} else if (taskRule != null) {
			output(response, JsonUtil.buildFalseJson("3", "该标题已存在!"));
		}else {
			try {
				TaskRule trRule = new TaskRule();
				trRule.setCreateTime(new Date());
				trRule.setRuleName(taskRuleName);
				taskRuleService.addTaskRole(trRule);
				output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "添加失败!"));
			}
		}
	}
	
	/**
	 * 修改任务
	 * @param response
	 * @param ruleId
	 */
	@RequestMapping("/deleteTaskRole")
	public void deleteTaskRole(HttpServletResponse response,Integer ruleId,String ruleName){
		TaskRule taskRule = taskRuleService.getTaskRuleName(ruleName);
		TaskRule tr = taskRuleService.getTaskById(ruleId);
		 if (taskRule != null && !tr.getRuleName().equals(ruleName)) {
			output(response, JsonUtil.buildFalseJson("2", "该规则已存在!"));
		} else if (ruleName == null || ruleName == "") {
			output(response, JsonUtil.buildFalseJson("1", "请输入规则名称!"));
		} else {
			try {
				tr.setRuleName(ruleName);
				taskRuleService.updateTaskRole(tr);
				output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("4", "编辑失败!"));
			}
		}
	}
	/**
	 * 删除任务
	 * @param response
	 * @param ruleId
	 */
	@RequestMapping("/taskRuleDelete")
	public void taskRuleDelete(HttpServletResponse response,Integer ruleId){
		TaskRule taskRule = taskRuleService.getTaskById(ruleId);
		List<Task> task = taskService.getRuleId(taskRule.getRuleId());
		if (task != null && !task.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("2", "该任务已被使用，暂不能删除!"));
		}else {
			try {
				taskRuleService.deleteTaskRoleById(ruleId);
				output(response, JsonUtil.buildFalseJson("0", "删除任务成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "删除任务失败!"));
			}
		}
	}
	/**
	 * 分页列表
	 */
	@RequestMapping("getpublicnote")
	public void getpublicnote(Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response,String ruleName1){
		int rowsCount = taskRuleService.getCount(ruleName1);
		PageUtil pageUtil = new PageUtil(rowsCount, pageNo, pageSize);
		List<TaskRule> trList = taskRuleService.taskRuleList(ruleName1,(pageNo-1)*pageSize,pageSize);
		if (!trList.isEmpty() && trList != null) {
			output(response, JsonUtil.buildJsonByTotalCount(trList, pageUtil.getTotalPageCount()));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有更多的试卷了。"));
		}
	}
}
