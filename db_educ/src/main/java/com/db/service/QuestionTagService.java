package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.QuestionTag;

public interface QuestionTagService {

	List<QuestionTag> questionTag();

	

	void saveQuestionTag(QuestionTag qTag);



	List<QuestionTag> tagList(@Param("tagName")String tagName,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize,@Param("subject") Integer subject);



	QuestionTag getIsName(@Param("tagName")String tagName, @Param("subject")Integer subject, @Param("grade")Integer grade);



	public int getCount(@Param("tagName")String tagName, @Param("subjectId")Integer subjectId,  @Param("grade")Integer grade, @Param("type")String type);



	void saveQuestionTag(String tagName, Integer teacherId);
	
	public List<QuestionTag> getTagByPage(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize, @Param("subjectId")Integer subjectId, @Param("grade")Integer grade);
	 
	public List<QuestionTag> getTagBySearch(@Param("content")String content,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize, @Param("subjectId")Integer subjectId,@Param("grade")Integer grade);
	
	public QuestionTag getTagById(Integer tagId);
	
	public int deleteTag(Integer tagId);

	QuestionTag getTagName(@Param("tagName")String tagName);

	public List<QuestionTag> getTagNamePage(@Param("tagName") String tagName,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);
	
	void updateTag(QuestionTag qt);
	/**
	 * 根据多个id查出列表
	 * @param list
	 * @return
	 */
	public List<QuestionTag> getTagByIds(@Param("list")List list);

    public List<QuestionTag> getTagByOther(@Param("subjectId")Integer subjectId,@Param("gradeId")Integer gradeId);



	List<QuestionTag> findQuestionTagPidList(@Param("parentId")Integer tagId);

	
}
