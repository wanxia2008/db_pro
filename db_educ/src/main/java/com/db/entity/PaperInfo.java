package com.db.entity;

import java.util.Date;

public class PaperInfo {
	private Integer piId;
	private String piName;
	private Integer subject;
	private Integer piType;
	private Integer grade;
	private Integer science;
	private int usedTimes;
	private Integer isPublic;
	private Integer knowledgeBegin;
	private Integer knowledgeEnd;
	private Integer difficultyValue;
	private Integer buildUser;
	private Date createTime;
	private Date updateTime;
	private String buildUserName;
	private Integer topicTotai;
	private Double totalScore;
	private String paperExplain;//考试说明
	private Integer paperTime;//考试时间
	private Integer isStart; //是否开始试卷
	
	private Subject subject2;
	private QuestionsType questionsType;
	private PaperDetails paperDetails;
	private Grade grade2;
	private TaskType paperType;
	
	public TaskType getPaperType() {
		return paperType;
	}
	public void setPaperType(TaskType paperType) {
		this.paperType = paperType;
	}
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getPiId() {
		return piId;
	}
	public void setPiId(Integer piId) {
		this.piId = piId;
	}
	public String getPiName() {
		return piName;
	}
	public void setPiName(String piName) {
		this.piName = piName;
	}
	public Integer getSubject() {
		return subject;
	}
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	public Integer getPiType() {
		return piType;
	}
	public void setPiType(Integer piType) {
		this.piType = piType;
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
	public Integer getUsedTimes() {
		return usedTimes;
	}
	public void setUsedTimes(Integer usedTimes) {
		this.usedTimes = usedTimes;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	
	public Integer getDifficultyValue() {
		return difficultyValue;
	}
	public void setDifficultyValue(Integer difficultyValue) {
		this.difficultyValue = difficultyValue;
	}
	public Integer getBuildUser() {
		return buildUser;
	}
	public void setBuildUser(Integer buildUser) {
		this.buildUser = buildUser;
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
	public String getBuildUserName() {
		return buildUserName;
	}
	public void setBuildUserName(String buildUserName) {
		this.buildUserName = buildUserName;
	}
	public Subject getSubject2() {
		return subject2;
	}
	public void setSubject2(Subject subject2) {
		this.subject2 = subject2;
	}
	public QuestionsType getQuestionsType() {
		return questionsType;
	}
	public void setQuestionsType(QuestionsType questionsType) {
		this.questionsType = questionsType;
	}
	public PaperDetails getPaperDetails() {
		return paperDetails;
	}
	public void setPaperDetails(PaperDetails paperDetails) {
		this.paperDetails = paperDetails;
	}
	public Integer getTopicTotai() {
		return topicTotai;
	}
	public void setTopicTotai(Integer topicTotai) {
		this.topicTotai = topicTotai;
	}
	public Grade getGrade2() {
		return grade2;
	}
	public void setGrade2(Grade grade2) {
		this.grade2 = grade2;
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
	public String getPaperExplain() {
		return paperExplain;
	}
	public void setPaperExplain(String paperExplain) {
		this.paperExplain = paperExplain;
	}
	public Integer getPaperTime() {
		return paperTime;
	}
	public void setPaperTime(Integer paperTime) {
		this.paperTime = paperTime;
	}
	public void setUsedTimes(int usedTimes) {
		this.usedTimes = usedTimes;
	}
	public Integer getIsStart() {
		return isStart;
	}
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	
}
