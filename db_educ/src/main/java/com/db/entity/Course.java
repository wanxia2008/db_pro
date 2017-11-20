package com.db.entity;

import java.util.Date;
import java.util.List;

public class Course {

	private Integer courseId;
	private Integer roomId;
	private Integer classId;
	private Integer courseType;
	private String courseDate;
	private Integer courseTimes;
	private Integer lecturenoteId;
	private Integer examId;
	private Integer homeworkId;
	private Date createTime;
	private Date updateTime;
	private Integer subjectId;
	private Integer homeworkStatus;
	private Integer paperStatus;
	
	private List<ClassNo> classlist;
	private LectureNotes lectureNotes;
	private PaperInfo paperInfo;
	private Integer paperrecordId;
	private Subject subject;
	private Homework homework;
	private ClassNo classNo1;
	private Grade grade;
	private Classroom classroom;

	
	
	public Homework getHomework() {
		return homework;
	}
	public void setHomework(Homework homework) {
		this.homework = homework;
	}
	public Integer getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}
	public Integer getPaperrecordId() {
		return paperrecordId;
	}
	public void setPaperrecordId(Integer paperrecordId) {
		this.paperrecordId = paperrecordId;
	}
	public PaperInfo getPaperInfo() {
		return paperInfo;
	}
	public void setPaperInfo(PaperInfo paperInfo) {
		this.paperInfo = paperInfo;
	}
	public LectureNotes getLectureNotes() {
		return lectureNotes;
	}
	public void setLectureNotes(LectureNotes lectureNotes) {
		this.lectureNotes = lectureNotes;
	}
	public List<ClassNo> getClasslist() {
		return classlist;
	}
	public void setClasslist(List<ClassNo> classlist) {
		this.classlist = classlist;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getCourseType() {
		return courseType;
	}
	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
	public String getCourseDate() {
		return courseDate;
	}
	public void setCourseDate(String courseDate) {
		this.courseDate = courseDate;
	}
	public Integer getCourseTimes() {
		return courseTimes;
	}
	public void setCourseTimes(Integer courseTimes) {
		this.courseTimes = courseTimes;
	}
	public Integer getLecturenoteId() {
		return lecturenoteId;
	}
	public void setLecturenoteId(Integer lecturenoteId) {
		this.lecturenoteId = lecturenoteId;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
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
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public ClassNo getClassNo1() {
		return classNo1;
	}
	public void setClassNo1(ClassNo classNo1) {
		this.classNo1 = classNo1;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public Integer getHomeworkStatus() {
		return homeworkStatus;
	}
	public void setHomeworkStatus(Integer homeworkStatus) {
		this.homeworkStatus = homeworkStatus;
	}
	public Integer getPaperStatus() {
		return paperStatus;
	}
	public void setPaperStatus(Integer paperStatus) {
		this.paperStatus = paperStatus;
	}
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
}
