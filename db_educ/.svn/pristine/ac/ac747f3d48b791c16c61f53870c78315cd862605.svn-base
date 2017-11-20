package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Grade;
import com.db.entity.QuestionFill;

public interface QuestionFillService {

	//添加填空题
	void addQuestionFill(QuestionFill fill);

	List<QuestionFill> QuestionFillList(@Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize,@Param("handoutType") Integer handoutType, @Param("subject")Integer subject,
			@Param("questionDegree")Integer questionDegree, @Param("isPublic")Integer isPublic,@Param("tagId")Integer tagId,  @Param("gradeId")Integer gradeId);

	int updateIsPublic(QuestionFill fill);

	QuestionFill getQuestionFillById(@Param("fillId")Integer id);

	void falseDeleteFill(QuestionFill fill);

	void updateQuestionFill(QuestionFill fill);

	List<QuestionFill> getPrivateQuestionFillList(@Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize,@Param("handoutType") Integer handoutType, @Param("subject")Integer subject,
			@Param("questionDegree")Integer questionDegree, @Param("isPublic")Integer isPublic, @Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	List<QuestionFill> getPublicQuestionFillList(@Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize,@Param("handoutType") Integer handoutType, @Param("subject")Integer subject,
			@Param("questionDegree")Integer questionDegree, @Param("isPublic")Integer isPublic, @Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	List<QuestionFill> getPublicQuestionFillListbyids(@Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize,@Param("handoutType") Integer handoutType, @Param("subject")Integer subject,
			@Param("questionDegree")Integer questionDegree, @Param("isPublic")Integer isPublic, @Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId,
			@Param("gradelist")List<Grade> gradelist);

	List<QuestionFill> getFillByRandAdmin(Map<String, Object> map);

	List<QuestionFill> getFillAdminIsPublic(Map<String, Object> map);

	List<QuestionFill> findPaperInfoDetails(@Param("paperId")Integer piId);

	List<QuestionFill> findHomeworkDetails(@Param("paperId")Integer hkId);
    //前段拿列表
	List<QuestionFill> thePapersById(@Param("paperId")Integer paperId, @Param("type")int type, @Param("pageNo")Integer pageNo,@Param("pageSize") Integer pageSize);

	List<QuestionFill> thePaperDetailsById(Integer studentId, Integer paperId, int type, int pageNo, Integer pageSize);

	List<QuestionFill> thePapersAnswerById(Integer studentId, Integer paperId, int type, int pageNo, Integer pageSize);

	List<QuestionFill> theHomeworkById(@Param("paperId")Integer paperId,@Param("type") int type,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

	List<QuestionFill> theHomeworkAnswerById(Integer studentId, Integer paperId, int type, int pageNo, Integer pageSize);

	List<QuestionFill> getReadTageId(@Param("tagId")String tag);

	//拿到 自己的题目
	int getPrivateFillCount(@Param("handoutType")Integer handoutType, @Param("subject")Integer subject, @Param("questionDegree")Integer questionDegree,@Param("isPublic") Integer isPublic,
			@Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId,@Param("gradeId") Integer gradeId);

	//查看自己的和别人公开的
	int getIsPublicCount(@Param("handoutType")Integer handoutType, @Param("subject")Integer subject, @Param("questionDegree")Integer questionDegree,@Param("isPublic") Integer isPublic,
			@Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);

	//查看自己的和别人公开的，根据idlist
	int getIsPublicCountbyids(@Param("handoutType")Integer handoutType, @Param("subject")Integer subject, @Param("questionDegree")Integer questionDegree,@Param("isPublic") Integer isPublic,
				@Param("teacherId")Integer teacherId,@Param("tagId")Integer tagId, @Param("gradelist")List<Grade> gradelist );

	int getFillCount(@Param("handoutType")Integer handoutType, @Param("subject")Integer subject, @Param("questionDegree")Integer questionDegree,@Param("isPublic") Integer isPublic,@Param("tagId")Integer tagId, @Param("gradeId")Integer gradeId);
    //判断填空题类型是否被占用
	List<QuestionFill> getQuestionFillType(@Param("typeId")Integer typeId);

	QuestionFill getQuestionFillTitle(@Param("fillTitle")String fillTitle);
}
