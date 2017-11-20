package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Grade;
import com.db.entity.QuestionRead;

public interface QuestionReadService {

	/**
	 * 先插入阅读文本
	 */
	public int addParent(QuestionRead read);

	/**
	 * 再通过上条插入返回的主键值作为parent_id，插入n道题目
	 */
	public int addAllReadByparentid(QuestionRead read);

	public List<QuestionRead> getQuestionRead();

	public QuestionRead getReadById(@Param("readId") Integer readId);

	public int updateQuestionRead(QuestionRead questionRead);

	public int deleteQuestionRead(int readId);

	public List<QuestionRead> getHomeworkDeta(Integer homeworkId, int type, int pageNo, int pageSize);

	public List getQuestionReadById(Integer piId, int pageNo, int pageSize);

	/**
	 * 随机获取n条记录
	 * 
	 * @return
	 */
	public List<QuestionRead> getReadByRand(Map<String, Object> map);

	public List<QuestionRead> getReadByTeacherId(@Param("teacherId") int teacherId, @Param("pageNo") int pageNo,
			@Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	public List kesListdetails(Integer studentId, int pageNo, Integer pageSize);

	// 获取试卷阅读详情
	public List<QuestionRead> thePapersById(@Param("paperId")Integer paperId, @Param("type")int type,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

	// 获取作业阅读详情
	public List<QuestionRead> theHomeworkById(@Param("paperId")Integer paperId, @Param("type")int type,@Param("pageNo") Integer pageNo, @Param("pageSize")Integer pageSize);

	// 获取阅读详情里的题目内容
	public List<QuestionRead> getReadChildByParentId(@Param("parentId") int parentId);

	public int getcount(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	public List<QuestionRead> thePapersAnswerById(Integer studentId, Integer paperId, int type, int pageNo,
			Integer pageSize);

	public List<QuestionRead> theHomeworkAnswerById(Integer studentId, Integer paperId, int type, int pageNo,
			Integer pageSize);

	public List<QuestionRead> getReadChildByParentIdPaer(@Param("parentId") Integer readId);

	public List<QuestionRead> findPaperInfoDetails(Integer piId);

	// 更新题库状态
	public int updateIspublic(QuestionRead read);

	// 更新题目引用数
	public int updateUsecount(QuestionRead read);

	// 更改题目
	public int updateQuestion(QuestionRead read);

	public int updateReadByparentid(QuestionRead read);

	public int getconutIspublic(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	public int getconutIspublicbyids(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,
			@Param("teacherId") Integer teacherId,@Param("tagId")Integer tagId, @Param("gradelist")List<Grade> gradelist);
	
	public List<QuestionRead> getReadByTeacherIdIsPublic(@Param("teacherId") int teacherId, @Param("pageNo") int pageNo,
			@Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);
	
	public List<QuestionRead> getReadByTeacherIdIsPublicbyids(@Param("teacherId") int teacherId, @Param("pageNo") int pageNo,
			@Param("pageSize") Integer pageSize, @Param("handoutType") Integer handoutType,
			@Param("subject") Integer subject, @Param("questionDegree") Integer questionDegree,
			@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradelist")List<Grade> gradelist);


	public List<QuestionRead> findHomeworkDetails(@Param("paperId") Integer hkId);

	public List<QuestionRead> getReadTageId(@Param("tagId") String tagId);

	public List<QuestionRead> getQuestionSource(@Param("sourceId") Integer sourceId);

	public List<QuestionRead> getknowledgeBegin(@Param("lessonId") Integer lessonId);

	public int getAllQuestionsCount(@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	public List<QuestionRead> getAllQuestions(@Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize,
			@Param("handoutType") Integer handoutType, @Param("subject") Integer subject,
			@Param("questionDegree") Integer questionDegree, @Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	public List<QuestionRead> getReadByRandAdmin(Map<String, Object> map);

	public List<QuestionRead> getSubjectById(@Param("subjectId") Integer subjectId);

	public int updateScore(QuestionRead qrRead);

	public QuestionRead getPaperAnswerReadById(@Param("readId") Integer readId);

	public List<QuestionRead> theHomeworkDetailsById(@Param("paperId") Integer paperId,
			@Param("questionType") int questionType, @Param("pageNo") int pageNo, @Param("pageSize") Integer pageSize);

	public List<QuestionRead> getPaper_answerDetails(@Param("parentId") Integer readId,
			@Param("paperId") Integer paperId, @Param("studentId") Integer studentId);

	public List<QuestionRead> getHomework_answerDetails(@Param("paperId") Integer paperId,
			@Param("studentId") Integer studentId, @Param("parentId") Integer readId);

	public List<QuestionRead> getReadByRandAdminIsPublic(Map<String, Object> map);

}
