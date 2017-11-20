package com.db.entity;

import java.util.Date;

public class HomeworkDetails {
	private Integer hdId;
	private Integer questionId;
	private Integer questionType;
	private Integer paperId;
	private Double value;
	private Date createTime;
	private Date updateTime;
	private int subjectId;
	private QuestionSingleChoice questionSingleChoice;
	private QuestionMulitChoice questionMulitChoice;
	private QuestionJudge questionJudge;
	private QuestionRead questionRead;
	
	public Integer getHdId() {
		return hdId;
	}
	public void setHdId(Integer hdId) {
		this.hdId = hdId;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
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
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public QuestionSingleChoice getQuestionSingleChoice() {
		return questionSingleChoice;
	}
	public void setQuestionSingleChoice(QuestionSingleChoice questionSingleChoice) {
		this.questionSingleChoice = questionSingleChoice;
	}
	public QuestionMulitChoice getQuestionMulitChoice() {
		return questionMulitChoice;
	}
	public void setQuestionMulitChoice(QuestionMulitChoice questionMulitChoice) {
		this.questionMulitChoice = questionMulitChoice;
	}
	public QuestionJudge getQuestionJudge() {
		return questionJudge;
	}
	public void setQuestionJudge(QuestionJudge questionJudge) {
		this.questionJudge = questionJudge;
	}
	public QuestionRead getQuestionRead() {
		return questionRead;
	}
	public void setQuestionRead(QuestionRead questionRead) {
		this.questionRead = questionRead;
	}
}
