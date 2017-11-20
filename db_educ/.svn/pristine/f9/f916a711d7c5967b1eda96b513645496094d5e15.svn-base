package com.db.service;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.db.entity.StudentCareer;

public interface StudentCareerService {
	
	public void addStudentCareer(StudentCareer career);
	//班级添加学生的时候，更新班级编号，季节类型
	public int updateCareerWhenAddtoClass(StudentCareer career);
	//班级删除学生的时候，更新状态，假删除
	public int updateCareerByStatus(StudentCareer career);
	//班级删除学生，真删除
	public int deleteCareer(@Param("studentId")Integer studentId,@Param("classId")Integer classId);
	
	public StudentCareer getStuCareerById(Integer id);
	//根据学生编号查找班级信息
	public List<StudentCareer> getStuCareerByStu(Integer studentId);
	//班级搜索学生列表
	public List<StudentCareer> getAllStudent(Map<String, Object> map);
	public StudentCareer getClassByStudentId(Map<String, Object> map);
	//班级搜索学生列表的总数
	public long getAllStudentCount(Map<String, Object> map);
	//判断当年学生报这门课程和所在年级是否已有班级
	public long judgeStudentClass(@Param("year")Integer year,@Param("grade")Integer grade,@Param("subject")Integer subject,@Param("studentId")Integer studentId);
	//给班级所在学生分配考试,必须是不等于已修完的状态
	public List<StudentCareer> getStudentByClassNo(@Param("classId")Integer classId,@Param("status")Integer status);
	public List<StudentCareer> getClassListStudent(@Param("classId")Integer classNo, @Param("subject")Integer subject,@Param("grade") Integer grade);
	public void deleteStudentCareerById(@Param("id")Integer  id);
	public int getCountStudentId(@Param("studentId")int id);
	public List<StudentCareer> getTestRecordStudentId(@Param("studentId")int id);
	//判断是否添加过班级
	public StudentCareer findStudentCareer(@Param("studentId")Integer studentId,@Param("classId") Integer classId);
	public void updateCareerBy(StudentCareer career);
	public int getCountClassNo(@Param("classId")Integer classId,@Param("status")Integer status);
	public List<StudentCareer> getCareerGroupByYearAndSeason(Integer studentId);
	public List<StudentCareer> getCareerBySIdAndYearSeason(@Param("studentId")Integer studentId, @Param("year")Integer year, @Param("seasonType")Integer seasonType);
	public List<StudentCareer> isDistributionClass(@Param("classId")Integer classId);
	public StudentCareer findCareerBySIdAndYearSeason(@Param("studentId")Integer studentId, @Param("year")Integer year, @Param("seasonId")Integer seasonId,
			@Param("gradeId")Integer gradeId, @Param("subject")Integer subject);
	public List<StudentCareer> careerList(@Param("studentId")Integer studentId, @Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize);
	public List<StudentCareer> findCareerGradeId(@Param("studentId")Integer studentId,@Param("gradeId") Integer gradeId);
	public void updateCareerPayType(StudentCareer c);
	
	public void updateCareById(@Param("id")Integer id);
	public List<StudentCareer> findClassIdList(@Param("classId")Integer classId);
	public void updateCareer(StudentCareer c);
}
