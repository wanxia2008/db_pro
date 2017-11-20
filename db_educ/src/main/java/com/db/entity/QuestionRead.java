package com.db.entity;

import java.util.Date;
import java.util.List;

public class QuestionRead {

	private Integer readId;
	private String readDesc;
	private String Optiontitle;
	private String OptionA_read;
	private String OptionB_read;
	private String OptionC_read;
	private String OptionD_read;
	private String answer_read;
	private String answerDesc_read;
	private Integer parentId;
	private Integer subject;
	private Integer knowledge;
	private String knowledgeBegin;
	private String knowledgeEnd;
	private Integer degree_read;
	private Integer type;
	private Integer source_read;
	private Float score_read;
	private String tag_read;
	private Integer teacherId;
	private Integer useCount;
	private Integer rightCount;
	private Integer faultCount;
	private Integer ispublic_read;
	private Date createTime;
	private Date updateTime;
	private Integer isRemove;
	List<QuestionRead> qjList;
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
	private List<QuestionRead> childDetail;
	
	public List<QuestionRead> getChildDetail() {
		return childDetail;
	}
	public void setChildDetail(List<QuestionRead> childDetail) {
		this.childDetail = childDetail;
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
	public Integer getReadId() {
		return readId;
	}
	public void setReadId(Integer readId) {
		this.readId = readId;
	}
	public String getReadDesc() {
		return readDesc;
	}
	public void setReadDesc(String readDesc) {
		this.readDesc = readDesc;
	}
	public String getOptiontitle() {
		return Optiontitle;
	}
	public void setOptiontitle(String optiontitle) {
		Optiontitle = optiontitle;
	}
	public String getOptionA_read() {
		return OptionA_read;
	}
	public void setOptionA_read(String optionA_read) {
		OptionA_read = optionA_read;
	}
	public String getOptionB_read() {
		return OptionB_read;
	}
	public void setOptionB_read(String optionB_read) {
		OptionB_read = optionB_read;
	}
	public String getOptionC_read() {
		return OptionC_read;
	}
	public void setOptionC_read(String optionC_read) {
		OptionC_read = optionC_read;
	}
	public String getOptionD_read() {
		return OptionD_read;
	}
	public void setOptionD_read(String optionD_read) {
		OptionD_read = optionD_read;
	}
	public String getAnswer_read() {
		return answer_read;
	}
	public void setAnswer_read(String answer_read) {
		this.answer_read = answer_read;
	}
	public String getAnswerDesc_read() {
		return answerDesc_read;
	}
	public void setAnswerDesc_read(String answerDesc_read) {
		this.answerDesc_read = answerDesc_read;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	public Integer getDegree_read() {
		return degree_read;
	}
	public void setDegree_read(Integer degree_read) {
		this.degree_read = degree_read;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSource_read() {
		return source_read;
	}
	public void setSource_read(Integer source_read) {
		this.source_read = source_read;
	}
	public Float getScore_read() {
		return score_read;
	}
	public void setScore_read(Float score_read) {
		this.score_read = score_read;
	}
	public String getTag_read() {
		return tag_read;
	}
	public void setTag_read(String tag_read) {
		this.tag_read = tag_read;
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
	public Integer getIspublic_read() {
		return ispublic_read;
	}
	public void setIspublic_read(Integer ispublic_read) {
		this.ispublic_read = ispublic_read;
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
	public List<QuestionRead> getQjList() {
		return qjList;
	}
	public void setQjList(List<QuestionRead> qjList) {
		this.qjList = qjList;
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
