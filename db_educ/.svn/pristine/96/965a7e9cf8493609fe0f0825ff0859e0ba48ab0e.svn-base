package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.db.entity.Task;

public interface TaskService {

	List<Task> taskList(@Param("teacherId")Integer teacherId,@Param("pageNo") int pageNo,@Param("pageSize") int pageSize,@Param("taskType") Integer taskType, @Param("taskTitle")String taskTitle);

	public void addTask(Task t);

	public void deleteTaskById(Integer taskId);

	Task getTaskById(Integer taskId);

	public int updateTask(Task tk);

	List<Task> getalltask(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

	public int getCount(@Param("taskType") Integer statime,@Param("taskTitle") String endtime);

	List<Task> getTaskType(@Param("taskType")Integer ruleId);

	List<Task> getRuleId(@Param("ruleId")Integer ruleId);

	int seeGetCount(@Param("taskType") Integer statime,@Param("taskTitle") String endtime, @Param("teacherId")Integer teacherId);

	List<Task> getTaskList(@Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize, @Param("taskType")Integer taskType,@Param("taskTitle") String taskTitle);

	List<Task> getStarTaskList();


}
