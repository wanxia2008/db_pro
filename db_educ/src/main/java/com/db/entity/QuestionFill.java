package com.db.entity;

import java.util.Date;
import java.util.List;

public class QuestionFill {
	
	private Integer fillId;
	private String fillTitle;
	private String fillAnswer;
	private String answerAnalysis;
	private Integer subject;
	private Integer knowledge;
	private Integer knowledgeBegin;
	private Integer questionDegree;
	private Integer type;
	private Integer fillSourse;
	private String tagId;
	private Integer teacherId;
	private Integer usedCount;
	private Integer isPublic;
	private Integer isRemove;
	private Date createTime;
	private Date updateTime;
	private Double questionScore;
	private Integer typeId;
//	private String typeName;
	
	
	
	private PaperDetails paperDetails;
	private HomeworkDetails homeworkDetails;
	private MyMistakes myMistakes;
	private QuestionsType questionsType;
	private PaperAnswer paperAnswer;
	private HomeworkAnswer homeworkanswer;
	private Subject subject2;
	private Teacher teacher;
	private List<QuestionTag> qtList;
	private Grade grade;
	private QuestionFillType fillType;
	
	
	public Integer getFillId() {
		return fillId;
	}
	public void setFillId(Integer fillId) {
		this.fillId = fillId;
	}
	public String getFillTitle() {
		return fillTitle;
	}
	public void setFillTitle(String fillTitle) {
		this.fillTitle = fillTitle;
	}
	public String getFillAnswer() {
		return fillAnswer;
	}
	public void setFillAnswer(String fillAnswer) {
		this.fillAnswer = fillAnswer;
	}
	public String getAnswerAnalysis() {
		return answerAnalysis;
	}
	public void setAnswerAnalysis(String answerAnalysis) {
		this.answerAnalysis = answerAnalysis;
	}
	public Integer getSubject() {
		return subject;
	}
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	public Integer getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(Integer knowledge) {
		this.knowledge = knowledge;
	}
	public Integer getKnowledgeBegin() {
		return knowledgeBegin;
	}
	public void setKnowledgeBegin(Integer knowledgeBegin) {
		this.knowledgeBegin = knowledgeBegin;
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
	
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
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
	public PaperDetails getPaperDetails() {
		return paperDetails;
	}
	public void setPaperDetails(PaperDetails paperDetails) {
		this.paperDetails = paperDetails;
	}
	public HomeworkDetails getHomeworkDetails() {
		return homeworkDetails;
	}
	public void setHomeworkDetails(HomeworkDetails homeworkDetails) {
		this.homeworkDetails = homeworkDetails;
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
	public List<QuestionTag> getQtList() {
		return qtList;
	}
	public void setQtList(List<QuestionTag> qtList) {
		this.qtList = qtList;
	}
	public Double getQuestionScore() {
		return questionScore;
	}
	public void setQuestionScore(Double questionScore) {
		this.questionScore = questionScore;
	}
	public Integer getFillSourse() {
		return fillSourse;
	}
	public void setFillSourse(Integer fillSourse) {
		this.fillSourse = fillSourse;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public QuestionFillType getFillType() {
		return fillType;
	}
	public void setFillType(QuestionFillType fillType) {
		this.fillType = fillType;
	}
}
