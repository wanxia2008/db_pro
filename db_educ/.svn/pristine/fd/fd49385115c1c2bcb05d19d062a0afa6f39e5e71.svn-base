package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.TaskRule;

public interface TaskRuleService {

	List<TaskRule> taskRule();

	List<TaskRule> taskRuleList(@Param("ruleName")String ruleName, @Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize);

	int getCount(@Param("ruleName")String ruleName);

	void deleteTaskRoleById(@Param("ruleId")Integer ruleId);

	void addTaskRole(TaskRule trRule);

	TaskRule getTaskRuleName(@Param("ruleName")String taskRuleName);

	void updateTaskRole(TaskRule tr);

	TaskRule getTaskById(@Param("ruleId")Integer ruleId);

}
