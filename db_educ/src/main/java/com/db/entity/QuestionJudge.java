package com.db.entity;

import java.util.Date;
import java.util.List;

public class QuestionJudge {

	private Integer judgeId;
	private String judgeDesc;
	private String answer2;
	private String answerDesc2;
	private Integer subject;
	private Integer knowledge;
	private String knowledgeBegin;
	private String knowledgeEnd;
	private Integer degree2;
	private Integer type;
	private Integer source2;
	private Float score2;
	private String tag2;
	private Integer teacherId;
	private Integer useCount;
	private Integer rightCount;
	private Integer faultCount;
	private Integer ispublic2;
	private Date createTime;
	private Date updateTime;
	private Integer isRemove;
	private PaperDetails paperDetails;
	private HomeworkDetails homeworkDetails;
	private MyMistakes myMistakes;
	private QuestionsType questionsType;
	private PaperAnswer paperAnswer;
	private HomeworkAnswer homeworkanswer;
	private Subject subject2;
	private Teacher teacher;
	private Grade grade;
	
	private List<QuestionTag> questionTags;
	
	public List<QuestionTag> getQuestionTags() {
		return questionTags;
	}
	public void setQuestionTags(List<QuestionTag> questionTags) {
		this.questionTags = questionTags;
	}
	public HomeworkAnswer getHomeworkanswer() {
		return homeworkanswer;
	}
	public void setHomeworkanswer(HomeworkAnswer homeworkanswer) {
		this.homeworkanswer = homeworkanswer;
	}
	public QuestionsType getQuestionsType() {
		return questionsType;
	}
	public void setQuestionsType(QuestionsType questionsType) {
		this.questionsType = questionsType;
	}
	public Integer getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(Integer judgeId) {
		this.judgeId = judgeId;
	}
	public String getJudgeDesc() {
		return judgeDesc;
	}
	public void setJudgeDesc(String judgeDesc) {
		this.judgeDesc = judgeDesc;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswerDesc2() {
		return answerDesc2;
	}
	public void setAnswerDesc2(String answerDesc2) {
		this.answerDesc2 = answerDesc2;
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
	public String getKnowledgeBegin() {
		return knowledgeBegin;
	}
	public void setKnowledgeBegin(String knowledgeBegin) {
		this.knowledgeBegin = knowledgeBegin;
	}
	public String getKnowledgeEnd() {
		return knowledgeEnd;
	}
	public void setKnowledgeEnd(String knowledgeEnd) {
		this.knowledgeEnd = knowledgeEnd;
	}
	public Integer getDegree2() {
		return degree2;
	}
	public void setDegree2(Integer degree2) {
		this.degree2 = degree2;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSource2() {
		return source2;
	}
	public void setSource2(Integer source2) {
		this.source2 = source2;
	}
	public Float getScore2() {
		return score2;
	}
	public void setScore2(Float score2) {
		this.score2 = score2;
	}
	public String getTag2() {
		return tag2;
	}
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getUseCount() {
		return useCount;
	}
	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	public Integer getRightCount() {
		return rightCount;
	}
	public void setRightCount(Integer rightCount) {
		this.rightCount = rightCount;
	}
	public Integer getFaultCount() {
		return faultCount;
	}
	public void setFaultCount(Integer faultCount) {
		this.faultCount = faultCount;
	}
	public Integer getIspublic2() {
		return ispublic2;
	}
	public void setIspublic2(Integer ispublic2) {
		this.ispublic2 = ispublic2;
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
	
	public PaperAnswer getPaperAnswer() {
		return paperAnswer;
	}
	public void setPaperAnswer(PaperAnswer paperAnswer) {
		this.paperAnswer = paperAnswer;
	}
	public Integer getIsRemove() {
		return isRemove;
	}
	public void setIsRemove(Integer isRemove) {
		this.isRemove = isRemove;
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
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
}
