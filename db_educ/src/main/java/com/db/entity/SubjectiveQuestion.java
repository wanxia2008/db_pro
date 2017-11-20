package com.db.entity;

import java.util.Date;
import java.util.List;

public class SubjectiveQuestion {
	private Integer subjectiveId;
	private String subjectiveTitle;
	private String answerAnalysis;
	private Integer subjectiveType;
	private Integer knowledgeBegin;
	private Integer knowledge;
	private String tagId;
	private Integer questionDegree;
	private Integer questionSource;
	private Integer type;
	private Integer teacherId;
	private Integer usedCount;
	private Integer isPublic;
	private Integer isRemove;
	private Date createTime;
	private Date updateTime;
	private Integer subject;
	private Double questionScore;
	private List<QuestionTag> qtList;
	private Grade grade;
	
	
	private HomeworkDetails homeworkDetails;
	private PaperDetails paperDetails;
	private MyMistakes myMistakes;
	private QuestionsType questionsType;
	private PaperAnswer paperAnswer;
	private HomeworkAnswer homeworkanswer;
	private Subject subject2;
	private Teacher teacher;
	
	
	public Integer getSubjectiveId() {
		return subjectiveId;
	}
	public void setSubjectiveId(Integer subjectiveId) {
		this.subjectiveId = subjectiveId;
	}
	public String getSubjectiveTitle() {
		return subjectiveTitle;
	}
	public void setSubjectiveTitle(String subjectiveTitle) {
		this.subjectiveTitle = subjectiveTitle;
	}
	
	public String getAnswerAnalysis() {
		return answerAnalysis;
	}
	public void setAnswerAnalysis(String answerAnalysis) {
		this.answerAnalysis = answerAnalysis;
	}
	public Integer getSubjectiveType() {
		return subjectiveType;
	}
	public void setSubjectiveType(Integer subjectiveType) {
		this.subjectiveType = subjectiveType;
	}
	public Integer getKnowledgeBegin() {
		return knowledgeBegin;
	}
	public void setKnowledgeBegin(Integer knowledgeBegin) {
		this.knowledgeBegin = knowledgeBegin;
	}
	
	
	public Integer getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(Integer knowledge) {
		this.knowledge = knowledge;
	}
	
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public Integer getQuestionDegree() {
		return questionDegree;
	}
	public void setQuestionDegree(Integer questionDegree) {
		this.questionDegree = questionDegree;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	public Integer getIsRemove() {
		return isRemove;
	}
	public void setIsRemove(Integer isRemove) {
		this.isRemove = isRemove;
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
	public Integer getQuestionSource() {
		return questionSource;
	}
	public void setQuestionSource(Integer questionSource) {
		this.questionSource = questionSource;
	}
	public Integer getSubject() {
		return subject;
	}
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	public HomeworkDetails getHomeworkDetails() {
		return homeworkDetails;
	}
	public void setHomeworkDetails(HomeworkDetails homeworkDetails) {
		this.homeworkDetails = homeworkDetails;
	}
	public PaperDetails getPaperDetails() {
		return paperDetails;
	}
	public void setPaperDetails(PaperDetails paperDetails) {
		this.paperDetails = paperDetails;
	}
	public MyMistakes getMyMistakes() {
		return myMistakes;
	}
	public void setMyMistakes(MyMistakes myMistakes) {
		this.myMistakes = myMistakes;
	}
	public QuestionsType getQuestionsType() {
		return questionsType;
	}
	public void setQuestionsType(QuestionsType questionsType) {
		this.questionsType = questionsType;
	}
	public PaperAnswer getPaperAnswer() {
		return paperAnswer;
	}
	public void setPaperAnswer(PaperAnswer paperAnswer) {
		this.paperAnswer = paperAnswer;
	}
	public HomeworkAnswer getHomeworkanswer() {
		return homeworkanswer;
	}
	public void setHomeworkanswer(HomeworkAnswer homeworkanswer) {
		this.homeworkanswer = homeworkanswer;
	}
	public Subject getSubject2() {
		return subject2;
	}
	public void setSubject2(Subject subject2) {
		this.subject2 = subject2;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Double getQuestionScore() {
		return questionScore;
	}
	public void setQuestionScore(Double questionScore) {
		this.questionScore = questionScore;
	}
	public List<QuestionTag> getQtList() {
		return qtList;
	}
	public void setQtList(List<QuestionTag> qtList) {
		this.qtList = qtList;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
}
