package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.TaskType;

public interface TaskTypeService {

	List<TaskType> taskType();

	List<TaskType> taskTypeList(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize, @Param("tasktypeName")String tasktypeName);

	int getCount(@Param("tasktypeName")String tasktypeName);

	void deletetypeTask(@Param("typeId")Integer typeId);

	void addTask(TaskType taskType);

	TaskType getTaskName(@Param("tasktypeName")String taskName);

	TaskType getTaskById(@Param("typeId")Integer typeId);

	void updateTask(TaskType type);

	List<TaskType> getTypeList();

}
