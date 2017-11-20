package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.HomeworkAnswer;

public interface HomeworkAnswerService {

	public int saveHomewor(HomeworkAnswer ha);

	public HomeworkAnswer getHomeworkAnswerById(Integer answerId);

	public int updateIsTrue(HomeworkAnswer hAnswer);

	public List<HomeworkAnswer> findHomeworkAnswerById(Integer studentId, Integer paperId);

	public int getcountTrue(@Param("studentId")Integer studentId,@Param("paperId") Integer paperId,@Param("isTrue") int isTrue,@Param("type") Integer type,@Param("questionId") Integer questionId,@Param("classId") Integer classId);

	public int getcountFlase(@Param("studentId")Integer studentId,@Param("paperId") Integer paperId,@Param("isTrue") int isTrue);
	
	//获得该学生当前做该份作业的条数（即做了多少题）
	public int getCountwhenWriting(@Param("studentId")Integer studentId,@Param("paperId")Integer paperId);

	public HomeworkAnswer isdoPaperIdById(@Param("studentId")Integer studentId, @Param("paperId")Integer paperId, @Param("questionId")Integer questionId,@Param("questionType") Integer questionType);

	public int updateWriteAnswer(HomeworkAnswer paAnswer);

	public List<HomeworkAnswer> findhomeworhAnalysis(@Param("paperId")Integer homeworkId, @Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize,@Param("classId") Integer classId);

	public int findAnalysisCount(@Param("paperId")Integer homeworkId,@Param("classId") Integer classId);

	public List<HomeworkAnswer> findStudentInformation(@Param("classId")Integer calssId,@Param("paperId") Integer paperId, @Param("isTrue")Integer isTrue,@Param("pageNo") int pageNo,
			@Param("pageSize")Integer pageSize,@Param("questionId") Integer questionId,@Param("type") Integer questionType);

	public int findStudentInformationCount(@Param("classId")Integer calssId,@Param("paperId") Integer paperId, @Param("isTrue")Integer isTrue,@Param("questionId") Integer questionId,@Param("type") Integer questionType);

	public double getQuestionAvg(@Param("paperId")Integer homeworkId, @Param("classId")Integer classId,@Param("type") int type);
}
