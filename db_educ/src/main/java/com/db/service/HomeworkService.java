package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Homework;
import com.db.entity.HomeworkAnswer;
import com.db.entity.HomeworkDetails;

public interface HomeworkService {

	public List<Homework> homeworkList(Integer studentId, int pageNo, int pageSize);

	public int getConut(Integer studentId);

	public void saveHomework(Homework homework);

	public Homework getHomework(@Param("homeworkId")Integer homeworkId);

	public List<Homework> getHomeworkId(@Param("userId")Integer userId,@Param("hk")Homework hk);

	public void deleteBomeworkById(Integer homeworkId);


	public int getCountToatai();

	public Homework findPaperId(@Param("paperId")Integer paperId, @Param("studentId")Integer studentId);

	public void updateStatus(Homework hk);
	
	
	
	/**获取该教师生成的试卷
	 * @param hk 
	 * @param subject 
	 * @param grade **/
	public List<Homework> getHomeworkByTeacherId(@Param("teacherId")int teacherId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("hk") Homework hk, @Param("gradeId")Integer grade,  @Param("subjectId")Integer subject);
	public List<Homework> getHomeworkByTeacherId1(@Param("teacherId")int teacherId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("hk") Homework hk, @Param("gradeId")Integer grade,  @Param("subjectId")Integer subject);
	//后台添加试卷
	public void addHomework(Homework homework);
	public Homework getHomeworkById(Integer hkId);
	public int updateHomeworkById(Homework homework);

	public int getCountId(@Param("hk")Homework hk, @Param("teacherId")Integer teacherId);
	//更新作业状态
	public int updateIspublic(Homework homework);
	
	//更新停用状态
	public int updateIsstart(Homework homework);

	public int getCountIspublicId(@Param("hk")Homework hk,@Param("teacherId") Integer teacherId);

	public int getCountIspublicIdbyids(@Param("hk")Homework hk,@Param("teacherId") Integer teacherId,@Param("gradeList") List<String> gradeList);

	public List<Homework> getHomeworkByTeacherIdIsPublic(@Param("teacherId")int teacherId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("hk") Homework hk);

	public List<Homework> getHomeworkByTeacherIdIsPublicbyids(@Param("teacherId")int teacherId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,
			@Param("hk") Homework hk , @Param("gradeList") List<String> gradeList);

	public void updateTotal(Homework hk);

	public List<Homework> getScience(@Param("materialId")Integer materialId);

	public List<Homework> getknowledgeBegin(@Param("lessonId")Integer lessonId);

	public int seeCountId(@Param("hk")Homework hk,@Param("gradeId") Integer grade, @Param("subjectId")Integer subject);
    
	public int seeCountId1(@Param("hk")Homework hk,@Param("gradeId") Integer grade, @Param("subjectId")Integer subject);
	
	public List<Homework> seeHomeworkByTeacherId(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize, @Param("hk")Homework hk, @Param("gradeId")Integer grade, @Param("subjectId")Integer subject);
    
	public List<Homework> seeHomeworkByTeacherId1(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize, @Param("hk")Homework hk, @Param("gradeId")Integer grade, @Param("subjectId")Integer subject);
	
	public void updateUserTimes(Homework homework);

	public List<Homework> getSubjectById(@Param("subjectId")Integer subjectId);

	public int getConutHomework(@Param("teacherId")Integer teacherId, @Param("type")String type);

	public int getIsPublicHomework(@Param("teacherId")Integer teacherId,@Param("type")String type);

	public int getHomeworkByTeacherIdCount(@Param("teacherId")Integer teacherId,@Param("pageNo") int pageNo, @Param("pageSize")Integer pageSize, @Param("gradeId")Integer grade, @Param("subjectId")Integer subject);
    
	public int getHomeworkByTeacherIdCount1(@Param("teacherId")Integer teacherId,@Param("pageNo") int pageNo, @Param("pageSize")Integer pageSize, @Param("gradeId")Integer grade, @Param("subjectId")Integer subject);

	
}
