package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.ErrorParsing;
import com.db.entity.Grade;
import com.db.entity.QuestionJudge;

public interface QuestionJudgeService {

	public List<QuestionJudge> getQuestionJudge();

	public QuestionJudge getJudgeById(int judgeId);

	public int addQuestionJudge(QuestionJudge judge);

	public int updateQuestionJudge(QuestionJudge judge);

	public int deleteQuestionJudge(int judgeId);

	public List getQuestionJudgeById(Integer piId, int pageNo, int pageSize);

	public List<QuestionJudge> getHomework(Integer homeworkId, int type, int pageNo, int pageSize);

	/**
	 * 随机获取n条记录
	 * 
	 * @return
	 */
	public List<QuestionJudge> getJudgeByRand(Map<String, Object> map);

	public List<QuestionJudge> getJudgeByTeacherId(@Param("teacherId") int teacherId, @Param("pageNo") int pageNo,
			@Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	public List kesListdetails(Integer studentId, int i, Integer pageSize);

	public List<QuestionJudge> thePapersById(@Param("paperId")Integer paperId, @Param("type")int type,@Param("pageNo") Integer pageNo, @Param("pageSize")Integer pageSize);

	public List<QuestionJudge> theHomeworkById(@Param("paperId")Integer paperId, @Param("type")int type,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

	public List<QuestionJudge> findHomeworkDetails(Integer paperId);

	public List<QuestionJudge> findPaperInfoDetails(Integer paperId);

	public int getcount(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	public List<ErrorParsing> errorParsinDetails(@Param("studentId") Integer studentId,
			@Param("questionId") Integer questionId);

	public List<QuestionJudge> thePapersAnswerById(Integer studentId, Integer paperId, int type, int pageNo,
			Integer pageSize);

	public List<QuestionJudge> theHomeworkAnswerById(Integer studentId, Integer paperId, int type, int pageNo,
			Integer pageSize);

	// 更新题库状态
	public int updateIspublic(QuestionJudge judge);

	// 更新题目引用数
	public int updateUsecount(QuestionJudge judge);

	// 更改题目
	public int updateQuestion(QuestionJudge judge);

	public int getconutIspublic(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);
	
	public int getconutIspublicbyids(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId, @Param("gradelist")List<Grade> gradelist);
	

	public List<QuestionJudge> getJudgeByTeacherIdIsPublic(@Param("teacherId") int teacherId,
			@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	
	public List<QuestionJudge> getJudgeByTeacherIdIsPublicbyids(@Param("teacherId") int teacherId,
			@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradelist")List<Grade> gradelist);
	public List<QuestionJudge> getTageId(@Param("tagId") String tagId);

	public List<QuestionJudge> getQuestionSource(@Param("sourceId") Integer sourceId);

	public List<QuestionJudge> getknowledgeBegin(@Param("lessonId") Integer lessonId);

	public int getAllQuestionsCount(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	public List<QuestionJudge> getAllQuestions(@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize,
			@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	public List<QuestionJudge> getJudgeByRandAdmin(Map<String, Object> map);

	public List<QuestionJudge> getSubjectById(@Param("subjectId") Integer subjectId);

	public List<QuestionJudge> getJudgeByRandAdminIsPublic(Map<String, Object> map);

	public QuestionJudge getQuestionJudgeTitle(@Param("judgeDesc")String judgeDesc);
	

}
