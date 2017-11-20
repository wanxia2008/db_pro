package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Course;

public interface CourseService {

	public int addCourse(Course course);
	
	public int updateCoursebynote(Course course);
	public int updateCoursebyexam(Course course);
	public int updateCoursebyexam1(Course course);//设置examid为空
	public int updateCoursebynote1(Course course);//设置讲义编号为空
	public int updateCoursebyhomework(Course course);
	
	public List<Course> getAllCourse(String courseDate);//取得春秋季排课
	public List<Course> getCourseByHoliday(@Param("courseDate")String courseDate);//取得寒暑假排课
	public List<Course> getCourseByHolidayCount();
	
	public List<Course> getCourseByClass(Map<String, Object> param);
	
	public Course getCourseById(int courseId);
	
	public List<Course> getCourseByGradeandSubject(Map<String, Object> param);
	
	public List<Course> getCourse_HomeworkByClass(Map<String, Object> param);

	public int getCount(Map<String, Object> param);

	public List<Course> getCourseByClassList(Map<String, Object> map);

	public List<Course> findcCourseDateLit(Map<String, Object> map);
	/**
	 * 根据添加的任务来给某个课程添加考试
	 * @param course
	 * @return
	 */
	public int updateCourseTest(Course course);
	
	public List<Course> getStatusByClassAndDate(@Param("courseDate")String courseDate,@Param("classId")Integer classId,@Param("time")Integer time);

	public List<Course> getCourseByClassandDateandtime(@Param("courseDate")String courseDate,@Param("classId")Integer classId,@Param("type") int type);
	
	public List<Course> getDateByClass(@Param("classlist")List classlist,@Param("classId") Integer classId);
	
	public int deleteCourseById(Integer courseId);
	
	public long getRoomByCourseandDate(@Param("date")String date,@Param("roomId")Integer roomId);

	public List<Course> getCourseBySpringfallCount();

	public Course getIsAddTask(@Param("date")String date, @Param("classId")Integer classId,  @Param("isTask")int isTask);

	//查看班级排课情况总数
	public int getClassCourseCount(@Param("classId")Integer classId, @Param("date")String date);
	
	//查看班级排课情况总数，多个id
	public int getClassCourseCountbyIds(@Param("classIdList")List<Integer> classidlist, @Param("date")String date);
	
	//查看班级排课情况
	public List<Course> findClassCourseList(@Param("classId")Integer classId, @Param("pageNo")Integer pageNo,@Param("pageSize") Integer pageSize,@Param("date")String date,@Param("type")Integer type);

	//查看班级排课情况，多个id
	public List<Course> findClassCourseListbyIds(@Param("classIdList")List<Integer> classidlist, @Param("pageNo")Integer pageNo,@Param("pageSize") Integer pageSize,@Param("date")String date,@Param("type")Integer type);

	public List<Course> getCourseBySchoolId(Map<String, Object> param);

	public int getSchoolIdCount(Map<String, Object> param);
	public int getSchoolIdCountbyids(Map<String, Object> param );

	public List<Course> getCourseByGradeandSchoolId(Map<String, Object> param);
	
	public List<Course> getCourseByGradeandSchoolIds(Map<String, Object> param);

	public Course findNontClassId(@Param("classId")Integer classId,@Param("lecturenoteId") Integer lecturenoteId, @Param("homeworkId")Integer homeworkId, @Param("paperId")Integer paperId);

	public List<Course> findCourseDateList(@Param("classId")Integer classId,@Param("courseDate") String courseDate);

	public void deleteExam(Course course);

	public void deleteHome(Course course);
}
