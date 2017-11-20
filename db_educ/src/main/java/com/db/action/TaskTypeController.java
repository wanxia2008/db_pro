package com.db.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.PaperInfo;
import com.db.entity.Task;
import com.db.entity.TaskType;
import com.db.service.PaperInfoService;
import com.db.service.TaskService;
import com.db.service.TaskTypeService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/paperType")
public class TaskTypeController extends BaseUtil {

	@Resource
	private TaskTypeService taskTypeService;
	@Resource
	private PaperInfoService paperInfoService;
	@Resource
	private TaskService taskService;

	/**
	 * 试卷类型列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param tasktypeName
	 * @return
	 */
	@RequestMapping("/taskTypeList")
	public ModelAndView taskType(Integer pageNo, Integer pageSize, String tasktypeName) {
		ModelAndView mv = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = taskTypeService.getCount(tasktypeName);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<TaskType> taskList = taskTypeService.taskTypeList((pageNo - 1) * pageSize, pageSize, tasktypeName);
		if (taskList != null && !taskList.isEmpty()) {
			mv.addObject("taskList", taskList);
		} else {
			mv.addObject("taskList", null);
		}
		mv.addObject("totalPages", totalPages);
		mv.addObject("pageSize", pageSize);
		mv.addObject("page", pageNo);
		mv.addObject("tasktypeName", tasktypeName);
		mv.setViewName("exampaper/tasktypeList");
		return mv;
	}

	/**
	 * 添加类型名称
	 * 
	 * @param taskName
	 * @param response
	 */
	@RequestMapping("/savetaskType")
	public void saveTask(String taskName, HttpServletResponse response) {
		TaskType task = taskTypeService.getTaskName(taskName);
		if (taskName == null || taskName == "") {
			output(response, JsonUtil.buildFalseJson("1", "请输入类型名称!"));
		} else if (task != null) {
			output(response, JsonUtil.buildFalseJson("2", "该名称已存在!"));
		} else {
			TaskType taskType = new TaskType();
			taskType.setTaskTypeName(taskName);
			taskType.setCreateTime(new Date());
			taskTypeService.addTask(taskType);
			output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
		}
	}

	/**
	 * 修改
	 * 
	 * @param typeId
	 * @param response
	 */
	@RequestMapping("/deleteTasktype")
	public void deleteTask(Integer typeId, HttpServletResponse response, String taskTypeName) {
		TaskType type = taskTypeService.getTaskById(typeId);
		TaskType task = taskTypeService.getTaskName(taskTypeName);
		if (taskTypeName == null || taskTypeName == "") {
			output(response, JsonUtil.buildFalseJson("1", "请输入类型名称!"));
		} else if (task != null && !type.getTaskTypeName().equals(taskTypeName)) {
			output(response, JsonUtil.buildFalseJson("3", "该名称已存在!"));
		} else {
			type.setTaskTypeName(taskTypeName);
			taskTypeService.updateTask(type);
			output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
		}
	}

	/**
	 * 删除试卷类型
	 * 
	 * @param response
	 * @param typeId
	 */
	@RequestMapping("/typeDelete")
	public void typeDelete(HttpServletResponse response, Integer typeId) {
		TaskType taskType = taskTypeService.getTaskById(typeId);
		List<PaperInfo> pInfoList = paperInfoService.getPaperType(taskType.getTypeId());
		List<Task> taskList = taskService.getTaskType(typeId);
		if ((pInfoList != null && !pInfoList.isEmpty()) || (!taskList.isEmpty() && taskList != null)) {
			output(response, JsonUtil.buildFalseJson("2", "该试卷类型已被使用，暂时不能删除!"));
		} else {
			try {
				taskTypeService.deletetypeTask(typeId);
				output(response, JsonUtil.buildFalseJson("0", "删除试卷类型成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "删除试卷类型失败!"));
			}
		}
	}
}
