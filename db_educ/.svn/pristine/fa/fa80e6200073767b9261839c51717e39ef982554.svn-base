package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.PaperAnswer;

public interface PaperAnswerService {

	public int savePaperAnswer(PaperAnswer pa);

	public PaperAnswer getPaperById(Integer answerId);

	public List<PaperAnswer> findPaperAnswerBy(@Param("studentId")Integer studentId,@Param("paperId") Integer paperId);

	public int getcountTrue(@Param("studentId")Integer studentId, @Param("paperId")Integer paperId,@Param("isTrue") int isTrue,@Param("type") Integer type, @Param("classId")Integer classId,@Param("questionId") Integer questionId);

	public int getcountFlase(@Param("studentId")Integer studentId,@Param("paperId") Integer paperId,@Param("isTrue") int isTrue);

	public int updateIsTrue(PaperAnswer pAnswer);
	//获得该学生当前做该份试卷的条数（即做了多少题）
	public int getCountwhenWriting(@Param("studentId")Integer studentId,@Param("paperId")Integer paperId);
	//查找做过该道题的信息，包括选A、B、C、D的数量
	public List<PaperAnswer> getMessageByQuestion(@Param("questionId")int questionId,@Param("type")int type);

	public List<PaperAnswer> findPaerIdById(@Param("paperId")Integer paperId);
    //判断是否做个该道题目
	public PaperAnswer isdoPaperIdById(@Param("studentId")Integer studentId,@Param("paperId") Integer paperId, @Param("questionId")Integer questionId, @Param("questionType")Integer questionType);

	public int updateWriteAnswer(PaperAnswer paAnswer);

	public List<PaperAnswer> findClassPaperInfoAnalysisList(@Param("paperId")Integer paperId,@Param("classId") Integer classId, @Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize);

	public int getAnalysisCount(@Param("paperId")Integer paperId, @Param("classId")Integer classId);

	public List<PaperAnswer> findStudentInformation(@Param("classId")Integer calssId,@Param("paperId") Integer paperId, @Param("isTrue")Integer isTrue,@Param("pageNo") int pageNo,
			@Param("pageSize")Integer pageSize,@Param("questionId") Integer questionId,@Param("type") Integer questionIdType);

	public int findStudentInformationCount(@Param("classId")Integer calssId,@Param("paperId") Integer paperId, @Param("isTrue")Integer isTrue,@Param("questionId") Integer questionId,@Param("type") Integer questionIdType);

	public double getQuestionAvg(@Param("paperId")Integer paperId, @Param("classId")Integer classId, @Param("type")int type);

//	public List<PaperAnswer> getMulitchoiceByQuestion(@Param("questionId")Integer questionId,@Param("type") Integer type, @Param("aCount")String aCount, @Param("bCount")String bCount,
//			@Param("cCount")String cCount, @Param("dCount")String dCount);
}
