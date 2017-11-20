package com.db.entity;

import java.util.Date;

public class Homework {
	
	private Integer hkId;
	private String hkName;
	private Integer subject;
	private Integer grade;
	private Integer science;
	private Integer userTimes;
	private Integer isPublic;
	private Integer knowledgeBegin;
	private Integer knowledgeEnd;
	private Integer difficultyValue;
	private Integer teacherId;
	private String teacherName;
	private Integer hkTotal;//試卷總數
	private Double hkScore;//試卷總分
	private Date createTime;
	private Date updateTime;
	private Integer isStart; //是否开启作业
	
	private HomeworkAnswer homeworkAnswer;
	private Subject subject2;
	private Grade grade2;
	
	
	public HomeworkAnswer getHomeworkAnswer() {
		return homeworkAnswer;
	}
	public void setHomeworkAnswer(HomeworkAnswer homeworkAnswer) {
		this.homeworkAnswer = homeworkAnswer;
	}
	public Subject getSubject2() {
		return subject2;
	}
	public void setSubject2(Subject subject2) {
		this.subject2 = subject2;
	}
	public Integer getHkId() {
		return hkId;
	}
	public void setHkId(Integer hkId) {
		this.hkId = hkId;
	}
	public String getHkName() {
		return hkName;
	}
	public void setHkName(String hkName) {
		this.hkName = hkName;
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
	public Integer getScience() {
		return science;
	}
	public void setScience(Integer science) {
		this.science = science;
	}
	public Integer getUserTimes() {
		return userTimes;
	}
	public void setUserTimes(Integer userTimes) {
		this.userTimes = userTimes;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	
	public Integer getKnowledgeBegin() {
		return knowledgeBegin;
	}
	public void setKnowledgeBegin(Integer knowledgeBegin) {
		this.knowledgeBegin = knowledgeBegin;
	}
	public Integer getKnowledgeEnd() {
		return knowledgeEnd;
	}
	public void setKnowledgeEnd(Integer knowledgeEnd) {
		this.knowledgeEnd = knowledgeEnd;
	}
	public Integer getDifficultyValue() {
		return difficultyValue;
	}
	public void setDifficultyValue(Integer difficultyValue) {
		this.difficultyValue = difficultyValue;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getHkTotal() {
		return hkTotal;
	}
	public void setHkTotal(Integer hkTotal) {
		this.hkTotal = hkTotal;
	}
	public Double getHkScore() {
		return hkScore;
	}
	public void setHkScore(Double hkScore) {
		this.hkScore = hkScore;
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
	public Grade getGrade2() {
		return grade2;
	}
	public void setGrade2(Grade grade2) {
		this.grade2 = grade2;
	}
	public Integer getIsStart() {
		return isStart;
	}
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	
}
