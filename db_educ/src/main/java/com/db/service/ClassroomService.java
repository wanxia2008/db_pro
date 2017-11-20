package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Classroom;

/**
 * 教室
 *
 */
public interface ClassroomService {
	/*
	 * 增加教室信息
	 */
	public void addClassroom(Classroom classroom);
	/**
	 * 获取总数
	 * @param roomName 
	 * @return
	 */
	public long getCount(@Param("name")String name);
	/*
	 * 获取教室信息
	 */
	public List<Classroom> getClassroom(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("name")String name);
	
	public List<Classroom> getClassroom1();
	//获取老师所在校区的教室信息
	public List<Classroom> getClassroom2(@Param("schoolId")int schoolId);
	/*
	 * 修改教室信息
	 */
	public void updateClassroom(Classroom classroom);
	/*
	 * 删除教室信息
	 */
	public void deleteClassroom(int classroomId);
	/*
	 * 根据教室编号查找教室信息
	 */
	public List<Classroom> getClassroomByroomId(int classroomId);
	
	public Classroom getClassroomByName(String name);
	
	public Classroom findClassroomName(@Param("classroomName")String classroomName,@Param("schoolArea") Integer schoolArea);
	public Classroom findClassroomById(@Param("classroomId")Integer classroomId);
	public int getSchoolIdCount(@Param("name")String roomName, @Param("schoolId")Integer campus);
	public List<Classroom> getClassroomSchoolId(@Param("pageNo")int startPos, @Param("pageSize")int pageSize,@Param("name") String roomName, @Param("schoolId")Integer campus);
}
