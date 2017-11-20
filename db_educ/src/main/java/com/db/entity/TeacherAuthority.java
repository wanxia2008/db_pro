package com.db.entity;

import java.util.Date;
import java.util.List;

public class TeacherAuthority {
	private Integer taId;
	private Integer teacherId;
	private String grade;
	private Integer handoutAuthority;
	private Integer questionsAuthority;
	private Integer paperAuthority;
	private Date createTime;
	private Subject subject2;
	private List<Grade> gradelist;
	private Teacher teacher;
	private String teacherName;
	private UserRole userRole;
	private Role role;
	private Grade grade2;
	private SchoolZone schoolZone;
	
	
	public Integer getTaId() {
		return taId;
	}
	public void setTaId(Integer taId) {
		this.taId = taId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Integer getHandoutAuthority() {
		return handoutAuthority;
	}
	public void setHandoutAuthority(Integer handoutAuthority) {
		this.handoutAuthority = handoutAuthority;
	}
	public Integer getQuestionsAuthority() {
		return questionsAuthority;
	}
	public void setQuestionsAuthority(Integer questionsAuthority) {
		this.questionsAuthority = questionsAuthority;
	}
	public Integer getPaperAuthority() {
		return paperAuthority;
	}
	public void setPaperAuthority(Integer paperAuthority) {
		this.paperAuthority = paperAuthority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Subject getSubject2() {
		return subject2;
	}
	public void setSubject2(Subject subject2) {
		this.subject2 = subject2;
	}
	public List<Grade> getGradelist() {
		return gradelist;
	}
	public void setGradelist(List<Grade> gradelist) {
		this.gradelist = gradelist;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Grade getGrade2() {
		return grade2;
	}
	public void setGrade2(Grade grade2) {
		this.grade2 = grade2;
	}
	public SchoolZone getSchoolZone() {
		return schoolZone;
	}
	public void setSchoolZone(SchoolZone schoolZone) {
		this.schoolZone = schoolZone;
	}
}
