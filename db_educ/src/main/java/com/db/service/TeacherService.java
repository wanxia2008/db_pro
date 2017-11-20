package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.db.entity.Teacher;

public interface TeacherService {
	
	public Teacher getTeacherByUserName(String userName);
	
	/**
	 * 获取总数
	 * @return
	 */
	public long getCount();
	/**
	 * 获取所有教师信息
	 * @return
	 */
	public List<Teacher> getAllTeacher(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
	/**
	 * 根据id查找教师信息
	 * @param teacherId
	 * @return
	 */
	public Teacher getTeacherById(int teacherId);
	
	/**
	 * 删除教师信息
	 * @param teacherId
	 */
	public void deleteTeacher(@Param("teacherId")int teacherId);
	/**
	 * 添加教师信息
	 * @param teacher
	 */
	public int addTeacher(Teacher teacher);
	/**
	 * 修改教师信息
	 * @param teacher
	 */
	public int updateTeacher(Teacher teacher);
	/**
	 * 为班级添加教师
	 * @param teacher
	 * @return
	 */
	public int updateTeacherClass(Teacher teacher);
	
	public List<Teacher> getTeacherByClassId(@Param("classId")int classId,@Param("schoolId")int schoolId);
	
	public List<Teacher> getTeacherByGradeandSubject(@Param("grade")Integer grade,@Param("subject")Integer subject,@Param("schoolId")Integer schoolId,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,  @Param("roleId")Integer roleId);
	public long getTeacherByGradeandSubjectCount(@Param("grade")Integer grade,@Param("subject")Integer subject,@Param("schoolId")Integer schoolId, @Param("roleId")Integer roleId);
	
	public List<Teacher> searchByName(@Param("content")String content);

	public void updatePassword(@Param("teacherId")Integer teacherId,@Param("password") String password);

	public List<Teacher> findTeacherById(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("teacherName") String teacherName, @Param("subject")Integer subject, @Param("schoolId")Integer schoolId);

	public int getCountId(@Param("teacherName")String teacherName, @Param("subject")Integer subject, @Param("schoolId")Integer schoolId);
	//修改教师的判断
	public Teacher getPhone(@Param("teacherId")Integer teacherId,@Param("phone")String phone);
	//新增教师的判断
	public Teacher getPhone1(@Param("phone")String phone);

	public int getCountTeacherId(@Param("teacherName")String teacherName,@Param("teacherId") Integer teacherId);

	public List<Teacher> seeTeacherById(@Param("teacherId")Integer teacherId,@Param("pageNo") int pageNo, @Param("pageSize")Integer pageSize, @Param("teacherName")String teacherName);

	public int getClassNoById(@Param("classId")int classId,@Param("schoolId")int schoolId);

	public List<Teacher> findIsExistenceCampus(@Param("schoolId")Integer schoolId);

	//登录时拿到觉得类型
	public Teacher getTeacherRileById(@Param("teacherId")Integer teacherId);

	public List<Teacher> findTeacherSchoolIdById(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("teacherName") String schoolName,@Param("schoolId") Integer schoolId, @Param("subject")Integer subject);

	public int getSchoolIdById(@Param("teacherName") String schoolName,@Param("schoolId") Integer schoolId, @Param("subject")Integer subject);

}
