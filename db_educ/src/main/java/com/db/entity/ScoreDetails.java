package com.db.entity;

public class ScoreDetails {
	private Integer detailsId;
	private Integer paperId;
	private Integer questionId;
	private String choosedAnswer;
	private int isTrue;
	
	public Integer getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getChoosedAnswer() {
		return choosedAnswer;
	}
	public void setChoosedAnswer(String choosedAnswer) {
		this.choosedAnswer = choosedAnswer;
	}
	public int getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(int isTrue) {
		this.isTrue = isTrue;
	}
}
