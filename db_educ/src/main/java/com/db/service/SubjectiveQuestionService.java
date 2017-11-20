package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Grade;
import com.db.entity.SubjectiveQuestion;

public interface SubjectiveQuestionService {

	void addSubjectiveQuestion(SubjectiveQuestion subjective);

	List<SubjectiveQuestion> getSubjectiveQuestion(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("handoutType")  Integer handoutType,@Param("subject")  Integer subject,
			@Param("questionDegree")Integer questionDegree, @Param("isPublic")Integer isPublic,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	int getAllQuestionsCount(@Param("handoutType")Integer handoutType,@Param("pageNo") Integer subject, @Param("questionDegree")Integer questionDegree,@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	List<SubjectiveQuestion> getMulitByRandAdmin(Map<String, Object> map);

	void updatesetUsedCount(SubjectiveQuestion sq);

	List<SubjectiveQuestion> findPaperInfoDetails(@Param("paperId")Integer piId);

	SubjectiveQuestion getSubjectiveQuestionById(@Param("subjectiveId")Integer id);

	void updateSubjectiveQuestion(SubjectiveQuestion sq);

	void falseDeleteSubjective(SubjectiveQuestion sq);

	List<SubjectiveQuestion> thePapersById(@Param("paperId")Integer paperId,@Param("type") int type, @Param("pageNo")Integer pageNo,@Param("pageSize") Integer pageSize);

	List<SubjectiveQuestion> getReadByRandAdminIsPublic(Map<String, Object> map);

	int getIsPublicCount(@Param("handoutType")Integer handoutType,@Param("subject") Integer subject, @Param("questionDegree")Integer questionDegree,@Param("isPublic") Integer isPublic, @Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	
	int getIsPublicCountbyids(@Param("handoutType")Integer handoutType,@Param("subject") Integer subject, @Param("questionDegree")Integer questionDegree,
			@Param("isPublic") Integer isPublic, @Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId,@Param("gradelist")List<Grade> gradelist);

	List<SubjectiveQuestion> getIsPublicSubjectiveQuestion(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("handoutType")  Integer handoutType,@Param("subject")  Integer subject,
			@Param("questionDegree")Integer questionDegree, @Param("isPublic")Integer isPublic,@Param("teacherId")Integer teacherId);

	List<SubjectiveQuestion> findHomeworkDetails(@Param("paperId")Integer hkId);

	SubjectiveQuestion getSubjectiveHomeById(Integer questionId);

	List<SubjectiveQuestion> theHomeworkAnswerById(@Param("studentId")Integer studentId,@Param("paperId") Integer paperId, @Param("type")int type,@Param("pageNo") int pageNo, @Param("pageSize")Integer pageSize);

	List<SubjectiveQuestion> thePaperDetailsById(@Param("studentId")Integer studentId,@Param("paperId") Integer paperId, @Param("type")int type,@Param("pageNo") int pageNo, @Param("pageSize")Integer pageSize);

	int updateIsPublic(SubjectiveQuestion subjectiv);

	List<SubjectiveQuestion> theHomeworkById(@Param("paperId")Integer paperId, @Param("type")int type,@Param("pageNo") Integer pageNo, @Param("pageSize")Integer pageSize);

	List<SubjectiveQuestion> getPublicSubjectiveQuestion(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("handoutType")  Integer handoutType,@Param("subject")  Integer subject,
			@Param("questionDegree")Integer questionDegree, @Param("isPublic")Integer isPublic,@Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	List<SubjectiveQuestion> getPublicSubjectiveQuestionbyids(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("handoutType")  Integer handoutType,@Param("subject")  Integer subject,
			@Param("questionDegree")Integer questionDegree, @Param("isPublic")Integer isPublic,@Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId, 
			@Param("gradelist")List<Grade> gradelist);

	List<SubjectiveQuestion> getPrivateSubjectiveQuestion(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("handoutType")  Integer handoutType,@Param("subject")  Integer subject,
			@Param("questionDegree")Integer questionDegree, @Param("isPublic")Integer isPublic,@Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	List<SubjectiveQuestion> getPrivateSubjectiveQuestionbyids(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("handoutType")  Integer handoutType,@Param("subject")  Integer subject,
			@Param("questionDegree")Integer questionDegree, @Param("isPublic")Integer isPublic,@Param("teacherId")Integer teacherId,
			@Param("tagId")Integer tagId,@Param("gradelist")List<Grade> gradelist);

	List<SubjectiveQuestion> getReadTageId(@Param("tagId")String tag);

	int getPrivateCount(@Param("handoutType")Integer handoutType,@Param("subject") Integer subject, @Param("questionDegree")Integer questionDegree,@Param("isPublic") Integer isPublic, @Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	SubjectiveQuestion getSubjectiveQuestionTitle(@Param("subjectiveTitle")String subjectiveTitle);

}
