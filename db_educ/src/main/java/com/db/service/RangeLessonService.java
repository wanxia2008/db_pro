package com.db.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.RangeLesson;

public interface RangeLessonService {

	List<RangeLesson> rangeLessonList(@Param("lessonName")String lessonName,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize);

	int getCount(@Param("lessonName")String lessonName);

	RangeLesson getLessonNameById(@Param("lessonName")String lessonName1);

	void addLesson(RangeLesson rl);

	RangeLesson getLessonById(@Param("lessonId")Integer lessonId);

	void updateLessonName(RangeLesson rl);

	List<RangeLesson> findLesson();
	
	public List<RangeLesson> getLessonList();

	void lessonDelete(@Param("lessonId")Integer lessonId);

}
