package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.QuestionSource;

public interface QuestionSourceService {

	List<QuestionSource> questionSource();

	List<QuestionSource> getSourceList(@Param("sourceNamel")String sourceNamel,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize);

	int getCount(@Param("sourceNamel")String sourceNamel);

	QuestionSource getSourceName(@Param("sourceName")String sourceName);

	void saveSource(QuestionSource qSource);

	void sourceDelete(@Param("sourceId")Integer sourceId);

	QuestionSource getScourseById(@Param("sourceId")Integer sourceId);

	void updateSource(QuestionSource qs);

}
