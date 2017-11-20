package com.db.entity;

import java.util.Date;
import java.util.List;

public class QuestionCloze {
	private Integer clozeId;
	private String clozeContent;
	private String clozeOptionA;
	private String clozeOptionB;
	private String clozeOptionC;
	private String clozeOptionD;
	private String clozeAnswer;
	private String analysis;
	private String tagId;
	private Integer subject;
	private Integer knowledge;
	private Integer questionDegree;
	private Integer type;
	private Integer questionSource;
	private Integer teacherId;
	private Integer usedCount;
	private Integer isPublic;
	private Integer parentId;
	private Integer clozeSonId;
	private Integer knowledgeBegin;
	private Integer questionScore;
	private Date createTime;
	private Date updateTime;
	
	private List<QuestionTag> qtList;
	
	private Subject subject2;
	private Grade grade;
	private Teacher teacher;
	private QuestionsType questionsType;
	private PaperDetails paperDetails;
	private HomeworkDetails homeworkDetails;
	private PaperAnswer paperAnswer;
	private HomeworkAnswer homeworkanswer;
	
	
	public Integer getClozeId() {
		return clozeId;
	}
	public void setClozeId(Integer clozeId) {
		this.clozeId = clozeId;
	}
	
	public String getClozeContent() {
		return clozeContent;
	}
	public void setClozeContent(String clozeContent) {
		this.clozeContent = clozeContent;
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
	public Integer getQuestionSource() {
		return questionSource;
	}
	public void setQuestionSource(Integer questionSource) {
		this.questionSource = questionSource;
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
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public Integer getKnowledgeBegin() {
		return knowledgeBegin;
	}
	public void setKnowledgeBegin(Integer knowledgeBegin) {
		this.knowledgeBegin = knowledgeBegin;
	}
	public Integer getQuestionScore() {
		return questionScore;
	}
	public void setQuestionScore(Integer questionScore) {
		this.questionScore = questionScore;
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
	public String getClozeOptionA() {
		return clozeOptionA;
	}
	public void setClozeOptionA(String clozeOptionA) {
		this.clozeOptionA = clozeOptionA;
	}
	public String getClozeOptionB() {
		return clozeOptionB;
	}
	public void setClozeOptionB(String clozeOptionB) {
		this.clozeOptionB = clozeOptionB;
	}
	public String getClozeOptionC() {
		return clozeOptionC;
	}
	public void setClozeOptionC(String clozeOptionC) {
		this.clozeOptionC = clozeOptionC;
	}
	public String getClozeOptionD() {
		return clozeOptionD;
	}
	public void setClozeOptionD(String clozeOptionD) {
		this.clozeOptionD = clozeOptionD;
	}
	public String getClozeAnswer() {
		return clozeAnswer;
	}
	public void setClozeAnswer(String clozeAnswer) {
		this.clozeAnswer = clozeAnswer;
	}
	
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getClozeSonId() {
		return clozeSonId;
	}
	public void setClozeSonId(Integer clozeSonId) {
		this.clozeSonId = clozeSonId;
	}
	public Subject getSubject2() {
		return subject2;
	}
	public void setSubject2(Subject subject2) {
		this.subject2 = subject2;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
	public HomeworkDetails getHomeworkDetails() {
		return homeworkDetails;
	}
	public void setHomeworkDetails(HomeworkDetails homeworkDetails) {
		this.homeworkDetails = homeworkDetails;
	}
	public List<QuestionTag> getQtList() {
		return qtList;
	}
	public void setQtList(List<QuestionTag> qtList) {
		this.qtList = qtList;
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
}
