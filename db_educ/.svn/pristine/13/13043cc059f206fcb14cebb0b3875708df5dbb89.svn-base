package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.ClassRecord;

public interface ClassRecordService {

	List<ClassRecord> getExamPaperList(Map<String, Object> map);

	int getPaperCount(Map<String, Object> map);

	List<ClassRecord> getExamPaperSchoolList(Map<String, Object> map);

	int getSchoolCount(Map<String, Object> map);

	int addClassRecord(ClassRecord cr);

	ClassRecord getPaperById(@Param("classId")Integer classid, @Param("paperId")Integer paperid,@Param("lecturenoteId") Integer lecturenoteId, @Param("homeworkId")Integer homeworkId);

	void updateStatus(ClassRecord cr);

	List<ClassRecord> findPaperExamList(Map<String, Object> map);

	int findPaperCount(Map<String, Object> map);

	ClassRecord getClassRecordById(@Param("recordId")Integer recordId);

	List<ClassRecord> findlecturenoteExamList(Map<String, Object> map);

	int findlecturenoteCount(Map<String, Object> map);

	List<ClassRecord> findhomeworkIdExamList(Map<String, Object> map);

	int findhomeworkIdCount(Map<String, Object> map);

	void deleteClassRecordById(ClassRecord record);

}
