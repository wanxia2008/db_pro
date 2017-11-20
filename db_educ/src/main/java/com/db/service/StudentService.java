package com.db.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Student;
import com.db.entity.Subject;

public interface StudentService {
	
	public List<Student> getAllStudent(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("schoolId")int schoolId);
	
	public Student getStudentByUserName(String UserName);
	
	public List<Student> getStudentByClassnoId(@Param("classNo")String classNo);
	
	public List<Student> getStudentByParam(Map<String,Object> map);
	
	public long getStudentByParamCount(Map<String,Object> map);
	
	public int updateStudentBySchoolId(@Param("studentid")int studentid,@Param("schoolId")Integer schoolId,@Param("date")Date date);
	
	public long getCount();

	public int addNewStudent(Student student);

	public Student getStudentById(@Param("studentId")Integer studentId);
	
	public void updateStudent(Student student);
	
	public void deleteStudent(int studentId);
    //前端修改头像
	public void updateStudentById(Student student);

	public Student studentList(@Param("studentId")Integer studentId);
    	
	public Student loginConfirm(String phone);

	public int updateFillInStudent(Student stu);

	public Student getStudentByPhone(String phone);

	public int updatePwassword(Student stu);
	//获取状态为预报名（status=1）的学生列表
	public List<Student> getStudentByStatus(Map<String, Object> map);
	public long getStudentByStatusCount(Map<String, Object> map);
	//修改学生的状态
	public int updateStudentStatus(Student student);

	public void updateAge(Student student);

	public void updateStudentName(Student student);

	public void updatePassword(Student student);

	public void updateAttendSchool(Student student);

	public void updateIntentionalSchool(Student student);

	public void updatePhone(Student student);

	public void updateUserName(Student student);
	
	public void updateGrade(Student student);
	
	public List<Student> getStudentByClassNoandstatus(@Param("classNo")Integer classNo);

	public int updateSubject(Student st);
	//修改家长信息
	public void updateJiangZhangInfo(Student student);

	public List<Student> getStudentList(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize, @Param("studentName")String studentName,@Param("studentStatus") Integer studentStatus, @Param("schoolId") Integer schoolId);

	public int getCountMuber(@Param("studentName")String studentName,@Param("studentStatus")Integer studentStatus,@Param("schoolId") Integer schoolId);

	public Student findStudentGradeById(@Param("studentId")Integer studentId);
	//获取最近报名的学生
	public List<Student> getStudentByTime(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

	public List<Student> findStudentList(@Param("subjectType")Integer subjectType,@Param("classId") Integer classId,@Param("studentId") Integer studentId);

	public List<Student> studentNameDateils(@Param("studentId")Integer studentId);

	public List<Student> getClassIdandGradeId(@Param("classId")Integer classId,@Param("gradeId") Integer grageId);

	public Student findstudentDateils(@Param("studentId")Integer studentId);

	public void falseDeleteStudent(@Param("studentId")int id);
	//根据姓名模糊搜索信息
	public List<Student> getStudnetByName(@Param("name")String name);
	
	public List<Student> getStudentByClassandSchool(@Param("classId")int classId,@Param("schoolId")int schoolId);

	public void modityClassNo(Student student);

	public int getCountClassNo(@Param("schoolId")Integer schoolId,@Param("subject") Integer subject,@Param("gradeId") Integer gradeId);

	public List<Student> distributionCalssNoGradeById(@Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize, @Param("gradeId")Integer gradeId,@Param("schoolId") Integer schoolId,
			@Param("subject")Integer subject);
    //查找是否有可以加入该条件下的学员
	public List<Student> getIsAllStudent(Map<String, Object> map);

	public int getAllStudentCount(Map<String, Object> map);

	public void updateClassNo(Student student);

	public List<Student> findIsExistenceCampus(@Param("schoolId")Integer schoolId);

	public List<Student> getEnlistListList(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize, @Param("studentName")String studentName,@Param("studentStatus") Integer studentStatus,
			@Param("schoolId")Integer schoolId);

	public List<Student> getScoolIdList(@Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize,@Param("studentName") String studentName, @Param("studentStatus")Integer studentStatus,
			@Param("schoolId")Integer schoolId, @Param("classId")String classId);

	public int getScoolIdCount(@Param("studentName")String studentName, @Param("studentStatus")Integer studentStatus, @Param("schoolId")Integer schoolId, @Param("classId")String classId);

	public void updatepayType(Student student);

	public void updatePayType(Student student);
}
