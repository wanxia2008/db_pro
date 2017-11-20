package com.db.entity;

public class ErrorParsing {
	private Integer questionId;
	private String answer;
	private String answerDesc;
	private Integer currentMistakes;
	private Integer mistakesNumber;
	private Integer studentId;
	
	
	
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswerDesc() {
		return answerDesc;
	}
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
	public Integer getCurrentMistakes() {
		return currentMistakes;
	}
	public void setCurrentMistakes(Integer currentMistakes) {
		this.currentMistakes = currentMistakes;
	}
	public Integer getMistakesNumber() {
		return mistakesNumber;
	}
	public void setMistakesNumber(Integer mistakesNumber) {
		this.mistakesNumber = mistakesNumber;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
}
