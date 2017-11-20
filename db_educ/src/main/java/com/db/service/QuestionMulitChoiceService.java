package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Grade;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionSingleChoice;

public interface QuestionMulitChoiceService {

	public void addQuestionMulit(QuestionMulitChoice mulitChoice);

	public List<QuestionMulitChoice> getQuestionMulit();

	public QuestionMulitChoice getMulitChoiceById(int choiceId);

	public int updateQuestionMulit(QuestionMulitChoice mulitChoice);

	public int deleteQuestionMulit(int choiceId);

	public void questionMulitPaper(Integer piId, int pageNo, int pageSize);

	/**
	 * 随机获取n条记录
	 * 
	 * @return
	 */
	public List<QuestionMulitChoice> getMulitByRand(Map<String, Object> map);

	public List<QuestionMulitChoice> getMulitByTeacherId(@Param("teacherId") int teacherId, @Param("pageNo") int pageNo,
			@Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	public List kesListdetails(Integer studentId, int pageNo, Integer pageSize);

	public List<QuestionMulitChoice> thePapersById(@Param("paperId")Integer paperId, @Param("type")int type,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

	public List<QuestionMulitChoice> theHomeworkById(@Param("paperId")Integer paperId,@Param("type") int type,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

	public List<QuestionMulitChoice> getQuestionMulitHom(Integer paperId, int type, int pageNo, Integer pageSize);

	public List<QuestionMulitChoice> findHomeworkDetails(Integer paperId);

	public List<QuestionMulitChoice> findPaperInfoDetails(Integer paperId);

	public int getcount(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId, @Param("gradeId") Integer gradeId);

	public List errorParsinDetails(@Param("studentId") Integer studentId, @Param("questionId") Integer questionId);

	public List<QuestionMulitChoice> thePapersAnswerById(Integer studetnId, Integer paperId, int type, int pageNo,
			Integer pageSize);

	public List<QuestionMulitChoice> theHomeworkAnswerById(Integer studetnId, Integer paperId, int type, int pageNo,
			Integer pageSize);

	// 更新题库状态
	public int updateIspublic(QuestionMulitChoice choice);

	// 更新题目引用数
	public int updateUsecount(QuestionMulitChoice choice);

	// 更改题目
	public int updateQuestion(QuestionMulitChoice choice);

	public int getconutIspublic(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);
	
	public int getconutIspublicbyids(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId,@Param("gradelist")List<Grade> gradelist);

	public List<QuestionMulitChoice> getMulitByTeacherIdIsPublic(@Param("teacherId") int teacherId,
			@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);
	
	public List<QuestionMulitChoice> getMulitByTeacherIdIsPublicbyids(@Param("teacherId") int teacherId,
			@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradelist")List<Grade> gradelist);

	public List<QuestionMulitChoice> getTageId(@Param("tagId") String tagId);

	public List<QuestionMulitChoice> getQuestionSource(@Param("sourceId") Integer sourceId);

	public List<QuestionMulitChoice> getknowledgeBegin(@Param("lessonId") Integer lessonId);

	public int getAllQuestionsCount(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	public List<QuestionMulitChoice> getAllQuestions(@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize,
			@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradeId") Integer gradeId);

	public List<QuestionMulitChoice> getMulitByRandAdmin(Map<String, Object> map);

	public List<QuestionMulitChoice> getSubjectById(@Param("subjectId") Integer subjectId);

	public List<QuestionMulitChoice> getMulitByRandAdminIsPublic(Map<String, Object> map);

	public QuestionMulitChoice QuestionMulitChoiceTitle(@Param("choiceDesc1")String choiceDesc1);
	


}
