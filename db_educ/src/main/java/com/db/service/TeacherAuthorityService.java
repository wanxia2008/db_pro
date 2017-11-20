package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.TeacherAuthority;

public interface TeacherAuthorityService {

	public List<TeacherAuthority> getTeacherIdById(@Param("teacherId")Integer taId);

	public void updateTeacherAuthority(TeacherAuthority ta);

	public void addTeacherAuthority(TeacherAuthority ta);
	
	public void addTeacherAuthoritybyList(@Param("TeacherAuthorityList")List<TeacherAuthority> list);

	public List<TeacherAuthority> findAuthorityById(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize, @Param("teacherName")String teacherName);

	public void deleteAuthorityById(@Param("taId")Integer taId);
	
	public void deleteAuthorityByteacherId(@Param("teacherId")Integer teacherId);

	public TeacherAuthority getAuthorityRole(@Param("taId")Integer taId);

	public List<TeacherAuthority> seefindAuthorityById(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("teacherName") String teacherName,@Param("teacherId") Integer teacherId);

	public int seeCountId(@Param("teacherName")String teacherName,@Param("teacherId") Integer teacherId);

	public int getCountId(@Param("teacherName")String teacherName);

	public TeacherAuthority getTaIdById(@Param("taId")Integer taId);

	public TeacherAuthority findIsTeacherGradeById(@Param("teacherId")Integer teacherId,@Param("gradeId") String grade);

	public void updateTeacherGradeId(TeacherAuthority ta);

	public int getCountSchoolId(@Param("teacherName")String teacherName,@Param("schoolId") Integer campus);

	public List<TeacherAuthority> findSchoolIdList(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize, @Param("teacherName")String teacherName, @Param("schoolId")Integer campus);
}
