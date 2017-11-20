package com.db.entity;

import java.util.List;

public class SeePaper {
	private Integer paperId;
	private String answer;
	private String choiceDesc;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String answerDesc;
	private String readDesc;
	private String optionA_read;
	private String optionB_read;
	private String optionC_read;
	private String optionD_read;
	private String answer_read;
	private String answerDesc_read; 
	private String choiceDesc1;
	private String optionA1;
	private String optionB1;
	private String optionC1;
	private String optionD1;
	private String answer1;
	private String answerDesc1;
	private String judgeDesc;
	private String answer2;
	private String answerDesc2;
	private String writeAnswer;
	private Integer isTrue;
	private Integer questionId;
	private Integer questiontype;
	private String questionName;
	private String optiontitle;
	private Double questionValue;
	private String subjectiveTitle;
	private String answerAnalysis;
	private String fillAnswer;
	private String fillTitle;
	private Integer paperType;
	private Integer id;
	private String clozeContent;
	private String typeName;
	private List<QuestionPaer> questionRead;//临时拿到数据赋值
	
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getChoiceDesc() {
		return choiceDesc;
	}
	public void setChoiceDesc(String choiceDesc) {
		this.choiceDesc = choiceDesc;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getAnswerDesc() {
		return answerDesc;
	}
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
	public String getReadDesc() {
		return readDesc;
	}
	public void setReadDesc(String readDesc) {
		this.readDesc = readDesc;
	}
	public String getOptionA_read() {
		return optionA_read;
	}
	public void setOptionA_read(String optionA_read) {
		this.optionA_read = optionA_read;
	}
	public String getOptionB_read() {
		return optionB_read;
	}
	public void setOptionB_read(String optionB_read) {
		this.optionB_read = optionB_read;
	}
	public String getOptionC_read() {
		return optionC_read;
	}
	public void setOptionC_read(String optionC_read) {
		this.optionC_read = optionC_read;
	}
	public String getOptionD_read() {
		return optionD_read;
	}
	public void setOptionD_read(String optionD_read) {
		this.optionD_read = optionD_read;
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
	public String getChoiceDesc1() {
		return choiceDesc1;
	}
	public void setChoiceDesc1(String choiceDesc1) {
		this.choiceDesc1 = choiceDesc1;
	}
	public String getOptionA1() {
		return optionA1;
	}
	public void setOptionA1(String optionA1) {
		this.optionA1 = optionA1;
	}
	public String getOptionB1() {
		return optionB1;
	}
	public void setOptionB1(String optionB1) {
		this.optionB1 = optionB1;
	}
	public String getOptionC1() {
		return optionC1;
	}
	public void setOptionC1(String optionC1) {
		this.optionC1 = optionC1;
	}
	public String getOptionD1() {
		return optionD1;
	}
	public void setOptionD1(String optionD1) {
		this.optionD1 = optionD1;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswerDesc1() {
		return answerDesc1;
	}
	public void setAnswerDesc1(String answerDesc1) {
		this.answerDesc1 = answerDesc1;
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
	public String getWriteAnswer() {
		return writeAnswer;
	}
	public void setWriteAnswer(String writeAnswer) {
		this.writeAnswer = writeAnswer;
	}
	public Integer getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(Integer isTrue) {
		this.isTrue = isTrue;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getQuestiontype() {
		return questiontype;
	}
	public void setQuestiontype(Integer questiontype) {
		this.questiontype = questiontype;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getOptiontitle() {
		return optiontitle;
	}
	public void setOptiontitle(String optiontitle) {
		this.optiontitle = optiontitle;
	}
	public List<QuestionPaer> getQuestionRead() {
		return questionRead;
	}
	public void setQuestionRead(List<QuestionPaer> questionRead) {
		this.questionRead = questionRead;
	}
	public Double getQuestionValue() {
		return questionValue;
	}
	public void setQuestionValue(Double questionValue) {
		this.questionValue = questionValue;
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
	public Integer getPaperType() {
		return paperType;
	}
	public void setPaperType(Integer paperType) {
		this.paperType = paperType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClozeContent() {
		return clozeContent;
	}
	public void setClozeContent(String clozeContent) {
		this.clozeContent = clozeContent;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
