package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.SchoolZone;

/**
 * 校区管理
 *
 */
public interface SchoolZoneService {

	/**
	 * 插入
	 * @param schoolZone
	 */
	public void addSchoolZone(SchoolZone schoolZone);
	/**
	 * 获取所有校区信息
	 * @param pageSize 
	 * @param i 
	 * @param schoolName 
	 * @return
	 */
	public List<SchoolZone> getSchoolZone(@Param("schoolName")String schoolName);
	
	public List<SchoolZone> getSchoolZone1(@Param("schoolName")String schoolName,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 修改
	 * @param schoolZone
	 */
	public void updateSchoolZone(SchoolZone schoolZone);
	/**
	 * 删除
	 * @param schoolZone
	 */
	public void deleteSchoolZone(int schoolId);
	
	public SchoolZone getSchoolZoneName(SchoolZone schoolZone);
	
	public int getCount(@Param("schoolName")String schoolName);
	
	public SchoolZone getschoolName(@Param("schoolName")String schoolName);
	
	public SchoolZone getSchoolZoneById(@Param("schoolId")Integer schoolId);
	//获取所有校区
	public List<SchoolZone> getAllSchool();
	
	public void updateIsUsing(SchoolZone sZone);
	public List<SchoolZone> findSchoolList(@Param("schoolId")Integer campus);
}
