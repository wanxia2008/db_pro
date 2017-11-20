package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.HomeworkDetails;

public interface HomeworkDetailsService {

	public HomeworkDetails getHomeworkDetailsById(Integer paperId, Integer questionId);

	public int getTotalByPaperId(Integer paperId);
	
	public int savehomeworkDetails(HomeworkDetails hDetails);

	public int getHomDateilsPaperId(@Param("paperId")Integer paperId);

	public List<HomeworkDetails> findHomHaIdList(@Param("paperId")Integer paperId, @Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize);

	public HomeworkDetails getHkIdById(@Param("hdId")Integer paperId);

	public void deleteTimu(Integer hdId);

	public HomeworkDetails findHomeworkDetailsById(Integer paperId, Integer questionId, Integer questionType);

	public List<HomeworkDetails> findHomeworkPaperList(@Param("paperId")Integer paperId);

}
