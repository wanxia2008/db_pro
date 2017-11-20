package com.db.entity;


import java.util.Date;
import java.util.List;

public class ClassNo {
	
	private Integer classId;
	private String year;
	private Integer seasonType;
	private Integer subject;
	private Integer grade;
	private String className;
	private Integer classType;
	private Integer classNumber;
	private String remarks;
	private Date createTime;
	private Date updateTime;
	private Integer roomId;
	private Integer schoolArea;
	private Integer actualNumber;
	private Integer maxNumber;
	private Integer teacherId;
	private Integer isEndCourse;
	private Integer courseNumber;
	private Integer actualCourse;
	
	private List<Subject> subjectlist;
	private List<Grade> gradelist;
	private List<SeasonType> seasonTypelist;
	private List<SchoolZone> schoolZonelist;
	private List<Classroom> classroomlist;
	private Teacher teacher;
	private Grade grade2;
	private SeasonType seasonType2;
	private Subject subject2;
	private SchoolYear schoolYear;
	private SchoolZone schoolZone;
	private List<Student> studentlist;
	private Course course;
	
	private List<Teacher> teacherlist;
	
	
	
	public List<Teacher> getTeacherlist() {
		return teacherlist;
	}
	public void setTeacherlist(List<Teacher> teacherlist) {
		this.teacherlist = teacherlist;
	}
	public List<Classroom> getClassroomlist() {
		return classroomlist;
	}
	public void setClassroomlist(List<Classroom> classroomlist) {
		this.classroomlist = classroomlist;
	}
	public List<SchoolZone> getSchoolZonelist() {
		return schoolZonelist;
	}
	public void setSchoolZonelist(List<SchoolZone> schoolZonelist) {
		this.schoolZonelist = schoolZonelist;
	}
	public List<SeasonType> getSeasonTypelist() {
		return seasonTypelist;
	}
	public void setSeasonTypelist(List<SeasonType> seasonTypelist) {
		this.seasonTypelist = seasonTypelist;
	}
	public List<Subject> getSubjectlist() {
		return subjectlist;
	}
	public void setSubjectlist(List<Subject> subjectlist) {
		this.subjectlist = subjectlist;
	}
	public List<Grade> getGradelist() {
		return gradelist;
	}
	public void setGradelist(List<Grade> gradelist) {
		this.gradelist = gradelist;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getSeasonType() {
		return seasonType;
	}
	public void setSeasonType(Integer seasonType) {
		this.seasonType = seasonType;
	}
	public Integer getSubject() {
		return subject;
	}
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getClassType() {
		return classType;
	}
	public void setClassType(Integer classType) {
		this.classType = classType;
	}
	
	public Integer getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(Integer classNumber) {
		this.classNumber = classNumber;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getSchoolArea() {
		return schoolArea;
	}
	public void setSchoolArea(Integer schoolArea) {
		this.schoolArea = schoolArea;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Student> getStudentlist() {
		return studentlist;
	}
	public void setStudentlist(List<Student> studentlist) {
		this.studentlist = studentlist;
	}
	public Grade getGrade2() {
		return grade2;
	}
	public void setGrade2(Grade grade2) {
		this.grade2 = grade2;
	}
	public SeasonType getSeasonType2() {
		return seasonType2;
	}
	public void setSeasonType2(SeasonType seasonType2) {
		this.seasonType2 = seasonType2;
	}
	public Subject getSubject2() {
		return subject2;
	}
	public void setSubject2(Subject subject2) {
		this.subject2 = subject2;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Integer getActualNumber() {
		return actualNumber;
	}
	public void setActualNumber(Integer actualNumber) {
		this.actualNumber = actualNumber;
	}
	public SchoolYear getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}
	public SchoolZone getSchoolZone() {
		return schoolZone;
	}
	public void setSchoolZone(SchoolZone schoolZone) {
		this.schoolZone = schoolZone;
	}
	public Integer getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getIsEndCourse() {
		return isEndCourse;
	}
	public void setIsEndCourse(Integer isEndCourse) {
		this.isEndCourse = isEndCourse;
	}
	public Integer getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(Integer courseNumber) {
		this.courseNumber = courseNumber;
	}
	public Integer getActualCourse() {
		return actualCourse;
	}
	public void setActualCourse(Integer actualCourse) {
		this.actualCourse = actualCourse;
	}
}
