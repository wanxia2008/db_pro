package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Grade;

/**
 * 年级
 *
 */
public interface GradeService {

	public List<Grade> getGrade();
	/**
	 * 根据一串id查找数据
	 * @param id
	 * @return
	 */
	public List<Grade> getGradeByIds(List listGrades);
	
	public Grade getGradeById(int gradeId);
	
	public List<Grade> findGradeList(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize);
}
