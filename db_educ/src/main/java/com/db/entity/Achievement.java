package com.db.entity;

import java.util.Date;

public class Achievement {
	private Integer achievementId;
	private Integer studentId;
	private Integer paperId;
	private Integer score;
	private Date createTime;
	private MyMistakes myMistakes;
	private Student student;
	private PaperInfo paperInfo;
	private Subject subject;
	
	public Integer getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(Integer achievementId) {
		this.achievementId = achievementId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public MyMistakes getMyMistakes() {
		return myMistakes;
	}
	public void setMyMistakes(MyMistakes myMistakes) {
		this.myMistakes = myMistakes;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public PaperInfo getPaperInfo() {
		return paperInfo;
	}
	public void setPaperInfo(PaperInfo paperInfo) {
		this.paperInfo = paperInfo;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
