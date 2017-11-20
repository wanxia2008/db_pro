package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Grade;
import com.db.entity.QuestionSingleChoice;

public interface QuestionSingleChoiceService {

	public List<QuestionSingleChoice> getQuestionSingle();

	public QuestionSingleChoice getSingleChoiceById(@Param("choiceId") int choiceId);

	public void addQuestionSingle(QuestionSingleChoice choice);

	public int deleteQuestionSingle(int choiceId);

	public List<QuestionSingleChoice> getHomeworkId(Integer paperId, int type, int pageNo, Integer pageSize);

	/**
	 * 随机获取n条记录
	 * 
	 * @return
	 */
	public List<QuestionSingleChoice> getSingleByRand(Map<String, Object> map);

	// 超级管理员拿全部题目
	public List<QuestionSingleChoice> getSingleByRandAdmin(Map<String, Object> map);

	public List<QuestionSingleChoice> getSingleByTeacherId(@Param("teacherId") int teacherId,
			@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	public List<QuestionSingleChoice> thePapersById(@Param("paperId")Integer paperId, @Param("type")int type,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

	public List<QuestionSingleChoice> theHomeworkById(@Param("paperId")Integer paperId, @Param("type")int type,@Param("pageNo") Integer pageNo, @Param("pageSize")Integer pageSize);

	public QuestionSingleChoice getQuestionById(Integer questionId);

	public List<QuestionSingleChoice> findHomeworkDetails(Integer paperId);

	public List<QuestionSingleChoice> findPaperInfoDetails(Integer paperId);

	public int getconut(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId, @Param("gradeId") Integer gradeId);

	public List errorParsinDetails(@Param("studentId") Integer studentId, @Param("questionId") Integer questionId);

	public List<QuestionSingleChoice> thePapersAnswerById(Integer studentId, Integer paperId, int type, int pageNo,
			Integer pageSize);

	public List<QuestionSingleChoice> theHomeworkAnswerById(Integer studentId, Integer paperId, int type, int pageNo,
			Integer pageSize);

	// 更新题库状态
	public int updateIspublic(QuestionSingleChoice choice);

	// 更新题目引用数
	public int updateUsecount(QuestionSingleChoice choice);

	// 更改题目
	public int updateQuestion(QuestionSingleChoice choice);

	public int getconutIspublic(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);
	
	public int getconutIspublicbyIds(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId, @Param("gradelist")List<Grade> gradelist);


	public List<QuestionSingleChoice> getSingleByTeacherIdIsPublic(@Param("teacherId") int teacherId,
			@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	public List<QuestionSingleChoice> getSingleByTeacherIdIsPublicbyIds(@Param("teacherId") int teacherId,
			@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradelist")List<Grade> gradelist);
	
	public List<QuestionSingleChoice> getTageId(@Param("tagId") String tagId);

	public List<QuestionSingleChoice> getQuestionSource(@Param("sourceId") Integer sourceId);

	public List<QuestionSingleChoice> getknowledgeBegin(@Param("lessonId") Integer lessonId);

	// 超级管理员看到全部题目
	public int getAllQuestionsCount(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	// 超级管理员看到全部题目
	public List<QuestionSingleChoice> getAllQuestions(@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize,
			@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	public List<QuestionSingleChoice> getSubjectById(@Param("subjectId") Integer subjectId);

	public List<QuestionSingleChoice> getSingleByRandAdminIsPublic(Map<String, Object> map);

	public QuestionSingleChoice getQuestionSingleChoiceTitle(@Param("choiceDesc")String choiceDesc);
	


}
