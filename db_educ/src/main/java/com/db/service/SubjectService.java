package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Subject;

/**
 * 科目
 *
 */
public interface SubjectService {

	public List<Subject> getSubject();
	/**
	 * 根据多个id查询数据
	 * @param subjects
	 * @return
	 */
	public List<Subject> getSubjectByIds(List subjects);
	public List<Subject> getSubjectById(Integer subject);
	public int getCount(@Param("subjectName")String subjectName);
	public List<Subject> findSubjectList(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("subjectName") String subjectName);
	public Subject getSubjectNmae(@Param("subjectName")String subjectName);
	public void addSubject(Subject subject2);
	public void updateSubject(Subject subject2);
	public Subject findSubjectById(Integer subjectId);
	public void deleteSubject(@Param("subjectId")Integer subjectId);
	
	public List<Subject> getTeacherIsSubject(@Param("subjectId")Integer subject);
}
