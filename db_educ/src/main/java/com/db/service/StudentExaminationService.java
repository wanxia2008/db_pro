package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.StudentExamination;

public interface StudentExaminationService {

	void addExamination(StudentExamination tr);

	void updateExamination(StudentExamination tr);

	StudentExamination getExaminationById(@Param("examId")Integer examId);

	void deleteRecordById(StudentExamination se);

	void updateStatus(StudentExamination se);

	List<StudentExamination> findEexecuteList(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("subjectId") Integer subjectId,@Param("gradeId") Integer gradeId,@Param("schoolId") Integer schoolId);

	int getCount(@Param("subjectId")Integer subjectId, @Param("gradeId")Integer gradeId, @Param("schoolId")Integer schoolId);

	List<StudentExamination> getexaminationList(@Param("schoolId")Integer schoolId, @Param("gradeId")Integer gradeId, @Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize);

	StudentExamination getPaperIdById(@Param("paperId")Integer paperId,@Param("subjectId") Integer subjectId,@Param("schoolId") Integer schoolId);

	StudentExamination getPaperIdANDsubjectId(@Param("paperId")Integer paperId, @Param("subjectId")Integer studentId);

	StudentExamination getPaperIdExaminationById(@Param("paperId")Integer paperId, @Param("subjectId")Integer subjectId,@Param("choiceId") Integer choiceId,@Param("year") Integer year,
			@Param("gradeId")Integer gradeId,@Param("schoolId") Integer schoolId);

	List<StudentExamination> findIsExistenceCampus(@Param("schoolId")Integer schoolId);

	List<StudentExamination> findTeacherSchoolId(@Param("pageNo")int pageNo,@Param("pageSize")Integer pageSize,@Param("subjectId") Integer subjectId, @Param("gradeId")Integer gradeId,
			@Param("schoolId")Integer campus);

	int getSchoolIdCount(@Param("subjectId")Integer subjectId, @Param("gradeId")Integer gradeId, @Param("schoolId")Integer schoolId);

}
