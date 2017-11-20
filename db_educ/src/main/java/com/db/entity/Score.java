package com.db.entity;

public class Score {
	private Integer scoreId;
	private Integer studentId;
	private Float score;
	private Integer piId;
	
	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Integer getPiId() {
		return piId;
	}
	public void setPiId(Integer piId) {
		this.piId = piId;
	}
}
