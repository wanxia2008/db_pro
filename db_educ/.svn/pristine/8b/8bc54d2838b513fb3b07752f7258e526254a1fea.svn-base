package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.ClassNo;
import com.db.entity.Classroom;
import com.db.entity.Grade;
import com.db.entity.SeasonType;
import com.db.entity.Student;
import com.db.entity.Subject;
import com.db.entity.Teacher;

/**
 * 班级service
 *
 */
public interface ClassNoService {

	/**
	 * 查询所有班级信息
	 * @param pageSize 
	 * @param pageNo 
	 * @return
	 */
	public List<ClassNo> getAllClasses(@Param("schoolArea")int schoolArea, @Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize);
	/**
	 * 可根据不同的条件查询班级列表
	 * @param map
	 * @return
	 */
	public List<ClassNo> getAllClasses1(Map<String, Object> map);
	/**
	 * 根据校区查找春秋季班级信息
	 * @return
	 */
	public List<ClassNo> getAllClassBySchoolwhenspringfall(@Param("schoolArea")Integer schoolArea);
	/**
	 * 根据校区查找寒暑假班级信息
	 * @return
	 */
	public List<ClassNo> getAllClassBySchoolwhenholiday(@Param("schoolArea")Integer schoolArea);
	/**
	 * 根据课程年级校区搜索班级信息（排课管理）
	 * @param subject
	 * @param grade
	 * @return
	 */
	public List<ClassNo> getClassBySubjectGradeSeason(Map<String, Object> map);
	
	public ClassNo getClassById(int classId);
	/**
	 * 添加班级信息
	 */
	public void addClasses(ClassNo classNo);
	/**
	 * 修改班级信息
	 */
	public int updateClasses(ClassNo classNo);
	/**
	 * 删除班级信息
	 */
	public int deleteClasses(int classId);
	
	/**
	 * 根据季节来查询班级信息
	 * @param seasonType
	 * @return
	 */
	public List<ClassNo> getClassesNo2(@Param("seasonId")Integer seasonId);
	
	public List<ClassNo> getClassesNo();
	public List<Student> findHisStudents(@Param("teacherId")Integer teacherId);
	public int getCount();
	//正确获取老师所管理的班级列表
	public List<ClassNo> getClassByTeacherclass(Map<String, Object> map);
	//根据多个班级id获取信息
	public List<ClassNo> getClassByClassnos(@Param("classList")List classList,@Param("classId")Integer classId);
	public List<ClassNo> getClassBySeasonType(@Param("classList")List classList,@Param("seasonType")Integer seasonType);
	public ClassNo findTaskClassById(@Param("classId")String taskObjectClass);
	public List<ClassNo> getClassListStudent(@Param("classId")Integer classNo,@Param("subjectType") Integer subjectType,@Param("gradeId") Integer grade);
	public ClassNo getStringClassById(@Param("classId")String classId);
	
	public ClassNo judgeSameClassName(@Param("className")String className,@Param("classId")Integer classId);
	public List<ClassNo> findSubjectList(@Param("className")String className, @Param("subject")Integer subject,@Param("seasonType") Integer seasonType,@Param("grade")Integer grade,@Param("pageNo")int pageNo,@Param("pageSize") int pageSize);
	public int getCountClassId(@Param("className")String className, @Param("subject")Integer subject,@Param("seasonType") Integer seasonType,@Param("grade") Integer grade);
	public ClassNo addIsExistence(@Param("schoolArea")Integer schoolArea, @Param("grade")Integer grade, @Param("subject")Integer subject, @Param("className")String className);
	public List<ClassNo> getHolidaySubject(Map<String, Object> map);
	public List<ClassNo> findIsExistenceCampus(@Param("schoolArea")int id);
	public List<ClassNo> findClassByIdList();
	public List<ClassNo> getClassList(@Param("classList")List list);
	public List<ClassNo> findSchoolIdList(Map<String, Object> map);
	public int schoolCount(Map<String, Object> map);
	//管理员可以查看所有班级排课(春季秋季)
	public List<ClassNo> getAdminClassById();
	//管理员可以查看所有班级排课(寒暑假)
	public List<ClassNo> getHolidayAdminClassById();
	//春季筛选排课
	public List<ClassNo> findAdminSchholIdById(Map<String, Object> map);
	//寒暑假排课筛选
	public List<ClassNo> getHolidayAdmin(Map<String, Object> map);
	public int findClassCount(Map<String, Object> map);
	
	public List<ClassNo> getSchoolIdByList(@Param("seasonType")Integer seasonType, @Param("grade")Integer grade,@Param("pageNo") int pageNo, @Param("pageSize")Integer pageSize,@Param("schoolId") Integer schoolId);
	public int getCountSchoolId(@Param("seasonType")Integer seasonType,@Param("grade") Integer grade,@Param("schoolId") Integer schoolId);
}
