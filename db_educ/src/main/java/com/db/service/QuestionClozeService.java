package com.db.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Grade;
import com.db.entity.QuestionCloze;

public interface QuestionClozeService {

	List<QuestionCloze> getQuestionClozeList(HashMap<String, Object> map);

	int getQuestionClozeCount(HashMap<String, Object> map);

	List<QuestionCloze> getIsPublicClozeList(HashMap<String, Object> map);
	List<QuestionCloze> getIsPublicClozeListbyids(HashMap<String, Object> map, @Param("gradelist")List<Grade> gradelist);

	int getIsPublicClozeCount(HashMap<String, Object> map);
	int getIsPublicClozeCountbyids(HashMap<String, Object> map, @Param("gradelist")List<Grade> gradelist);

	int updateIsPublic(QuestionCloze cloze);

	QuestionCloze getQuestionClozeById(@Param("clozeId")Integer id);

	int addQuestionCloze(QuestionCloze cloze);

	void deleteQuestionCloze(QuestionCloze cloze);

	List<QuestionCloze> QuestionClozeAdmin(Map<String, Object> map);

	List<QuestionCloze> findPaperInfoDetails(@Param("paperId")Integer piId);

	List<QuestionCloze> getparentByIdList(@Param("parentId")Integer clozeId);

	List<QuestionCloze> findHomeworkDetails(@Param("paperId")Integer hkId);

	List<QuestionCloze> thePapersById(@Param("paperId")Integer paperId, @Param("type")int type);

	List<QuestionCloze> thePapersAnswerById(@Param("studentId")Integer studentId,@Param("paperId") Integer paperId,@Param("parentId") int type);

	List<QuestionCloze> theHomeworkDetailsById(@Param("paperId")Integer paperId,@Param("type") int type);

	List<QuestionCloze> findHomeworkAnswerById(@Param("studentId")Integer studentId, @Param("paperId")Integer paperId,@Param("parentId") Integer clozeId);

	List<QuestionCloze> getQuestionClozeByParentId(@Param("parentId")Integer clozeId);

	List<QuestionCloze> theHomeworkById(@Param("paperId")Integer paperId, @Param("type")int type);

}
