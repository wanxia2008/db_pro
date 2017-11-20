package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.HomeworkRecord;

public interface HomeworkRecordService {
	//添加作业记录
	public int addHomeworkrecord(HomeworkRecord record);
	
	public HomeworkRecord getHomeworkRecordById(int id);
	//前端课后作业列表
	public List<HomeworkRecord> getHomeworkByStudentId(@Param("studentId")Integer studentId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize, @Param("name")String name);
	
	public int getCountByStudentId(int studentId);
	//查该学生该考卷的信息
	public HomeworkRecord getByStuIdandHomeId(@Param("studentId")int studentId,@Param("homeworkId")int homeworkId);
	//更新作业状态
	public int updateHomeworkStatus(HomeworkRecord record);
	//更新得分
	public int updateScoreByStuIdandHomId(HomeworkRecord record);

	public int gethomeworkTotalCount(Integer studentId);
	
	public int deleteByStuIdandHomeId(@Param("studentId")int studentId,@Param("homeworkId")int homeworkId);

	public int getCount(@Param("classId")Integer classId,@Param("subjectId") Integer subjectId,@Param("studentName") String studentName, @Param("paperId")Integer paperId);

	public List<HomeworkRecord> finHomeworkRecord(@Param("classId")Integer classId,@Param("subjectId") Integer subjectId,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize,@Param("studentName") String studentName,@Param("paperId") Integer paperId);

	public void deleteRecordById(@Param("id")Integer id);

	public void updateWriteNumber(HomeworkRecord record);

	public List<HomeworkRecord> findClassHomeworkAnalyList(@Param("classId")Integer classId,@Param("homeworkId") Integer homeworkId);
//	public HomeworkRecord deleteRecordById(@Param("id")Integer id);

	public void updateHomeworkRecord(HomeworkRecord tr);
}
