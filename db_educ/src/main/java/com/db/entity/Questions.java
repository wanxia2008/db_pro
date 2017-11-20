package com.db.entity;

import java.util.Date;

public class Questions {
	private Integer questionsId;
	private Integer subject; 	
	private Integer topicType;
	private Integer grade;
	private Integer classNo;
	private Date createTime;
	private Date updateTime;
	private Integer knowledgeBegin;
	private Integer knowledgeEnd;
	private String truthAnswer;
	private Integer questionSource;
	private String questionDesc;
	private Integer contentId;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private Integer material;
	private int usedCount;
	private int rightCount;
	private int faultCount;
	private int difficultyValue;
	private Integer enteringUser;
	private String tagId;
	private Integer number;
	private Integer score;
	private Integer isPublic;
	
	public Integer getQuestionsId() {
		return questionsId;
	}
	public void setQuestionsId(Integer questionsId) {
		this.questionsId = questionsId;
	}
	public Integer getSubject() {
		return subject;
	}
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	public Integer getTopicType() {
		return topicType;
	}
	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getClassNo() {
		return classNo;
	}
	public void setClassNo(Integer classNo) {
		this.classNo = classNo;
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
	public String getTruthAnswer() {
		return truthAnswer;
	}
	public void setTruthAnswer(String truthAnswer) {
		this.truthAnswer = truthAnswer;
	}
	public Integer getQuestionSource() {
		return questionSource;
	}
	public void setQuestionSource(Integer questionSource) {
		this.questionSource = questionSource;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public Integer getMaterial() {
		return material;
	}
	public void setMaterial(Integer material) {
		this.material = material;
	}
	public int getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(int usedCount) {
		this.usedCount = usedCount;
	}
	public int getRightCount() {
		return rightCount;
	}
	public void setRightCount(int rightCount) {
		this.rightCount = rightCount;
	}
	public int getFaultCount() {
		return faultCount;
	}
	public void setFaultCount(int faultCount) {
		this.faultCount = faultCount;
	}
	public int getDifficultyValue() {
		return difficultyValue;
	}
	public void setDifficultyValue(int difficultyValue) {
		this.difficultyValue = difficultyValue;
	}
	public Integer getEnteringUser() {
		return enteringUser;
	}
	public void setEnteringUser(Integer enteringUser) {
		this.enteringUser = enteringUser;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
}
