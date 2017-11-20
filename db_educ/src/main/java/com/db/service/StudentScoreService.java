package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.StudentScore;

public interface StudentScoreService {

	StudentScore getScoreStudentById(@Param("studentId")Integer studentId, @Param("paperId")Integer paperId);

	void updateScore(StudentScore score2);

	void addScore(StudentScore score2);

	int getCount(@Param("gradeId")Integer gradeId, @Param("schoolId")Integer schoolId);

	List<StudentScore> getScoreList(@Param("gradeId")Integer gradeId, @Param("schoolId")Integer schoolId,@Param("pageNo") int pageNo, @Param("pageSize")Integer pageSize,  @Param("school")Integer school,  @Param("grade")Integer grade,  @Param("score")Integer score);

	void deleteScoreId(@Param("scoreId")Integer scoreId);

	StudentScore getStudentScoreById(@Param("scoreId")Integer scoreId);

	void updateStudentScore(StudentScore score);

}
