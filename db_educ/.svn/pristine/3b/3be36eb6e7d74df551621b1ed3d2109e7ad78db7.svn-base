package com.db.action;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.db.entity.MyMistakes;
import com.db.entity.PaperAnswer;
import com.db.entity.PaperDetails;
import com.db.entity.PaperInfo;
import com.db.entity.QuestionCloze;
import com.db.entity.QuestionFill;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.TestRecord;
import com.db.service.MyMistakesService;
import com.db.service.PaperAnswerService;
import com.db.service.PaperDetailsService;
import com.db.service.PaperInfoService;
import com.db.service.QuestionClozeService;
import com.db.service.QuestionFillService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.TestRecordService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("/app/paperAnswer")
public class APPPaperAnswerController extends BaseUtil {

	@Resource
	private PaperAnswerService paperAnswerService;
	@Resource
	private QuestionSingleChoiceService qschoiceService;
	@Resource
	private MyMistakesService myMistakesService;
	@Resource
	private PaperDetailsService paperDetailsService;
	@Resource
	private QuestionMulitChoiceService qmceService;
	@Resource
	private QuestionJudgeService qjService;
	@Resource
	private QuestionReadService qrService;
	@Resource
	private TestRecordService testrecordservice;
	@Resource
	private PaperInfoService infoService;
	@Resource
	private QuestionFillService fillservice;
	@Resource
	private QuestionClozeService clozeService;

	/**
	 * 把答案保存到数据库中(普通考试)
	 * 
	 * @param response
	 * @param pa
	 */
	@RequestMapping("/saveAnswer")
	public void saveAnswer(HttpServletResponse response, PaperAnswer pa) {
		// 考试判断是否做个该道题(同一套套试卷)，做个则修改答案，没做个则添加
		PaperAnswer paAnswer = paperAnswerService.isdoPaperIdById(pa.getStudentId(), pa.getPaperId(),
				pa.getQuestionId(), pa.getQuestionType());
		PaperDetails pd = paperDetailsService.findPaperDetailsById(pa.getPaperId(), pa.getQuestionId(),
				pa.getQuestionType());
		MyMistakes my = myMistakesService.findMyMistakesHomeworkById(pa.getStudentId(), null, pa.getQuestionId(),
				pa.getQuestionType(), null);
		TestRecord tr = testrecordservice.getStauts(pa.getStudentId(), pa.getPaperId());
		if (paAnswer != null) {// 做个这道题则修改
			paAnswer.setWriteAnswer(pa.getWriteAnswer());
			paAnswer.setValue(pd.getValue());
			if(tr != null){
				if(tr.getClassId() != null){
					paAnswer.setClassId(tr.getClassId());
				}
			}
			tr.setUpdateTime(new Date());
			testrecordservice.updateTestrecord(tr);
			paperAnswerService.updateWriteAnswer(paAnswer);
			int len1 = 0;
			PaperAnswer pAnswer = paperAnswerService.getPaperById(paAnswer.getAnswerId());
			if (pa.getQuestionType() == 1) {
				QuestionSingleChoice qsc = qschoiceService.getQuestionById(pAnswer.getQuestionId());
				if (qsc.getAnswer().equals(pAnswer.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
					if (my != null) {
						my.setUpdateTime(new Date());
						my.setStatus(1);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				} else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
					if(my != null){
						my.setUpdateTime(new Date());
						my.setStatus(0);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				}
			} else if (pa.getQuestionType() == 2) {
				QuestionMulitChoice qmc = qmceService.getMulitChoiceById(pAnswer.getQuestionId());
				if (qmc.getAnswer1().equals(pAnswer.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
					if (my != null) {
						my.setUpdateTime(new Date());
						my.setStatus(1);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				} else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
					if(my != null){
						my.setUpdateTime(new Date());
						my.setStatus(0);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				}
			} else if (pa.getQuestionType() == 3) {
				QuestionJudge qj = qjService.getJudgeById(pAnswer.getQuestionId());
				if (qj.getAnswer2().equals(pAnswer.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
					if (my != null) {
						my.setUpdateTime(new Date());
						my.setStatus(1);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				} else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
					if(my != null){
						my.setUpdateTime(new Date());
						my.setStatus(0);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				}
			} else if (pa.getQuestionType() == 6) {// 填空题
				QuestionFill fill = fillservice.getQuestionFillById(pa.getQuestionId());
				String fillName = guoHtml(fill.getFillAnswer());
				String[] paperAnswer = pa.getWriteAnswer().split("##");
				String[] fillAnswer = fillName.split("##");
				if (paperAnswer.length > 1 && fillAnswer.length > 1) {// 有多个答案
					for (int i = 0; i < paperAnswer.length; i++) {
						for (int j = 0; j < fillAnswer.length; j++) {
							if (paperAnswer[i].equals(fillAnswer[j])) {
								pAnswer.setIsTrue(1);
								paperAnswerService.updateIsTrue(pAnswer);
								my.setUpdateTime(new Date());
								if (my != null) {
									my.setUpdateTime(new Date());
									my.setStatus(1);
									myMistakesService.updateMyMistakesHomeworkById(my);
								}
							} else {
								pAnswer.setIsTrue(0);
								len1 = paperAnswerService.updateIsTrue(pAnswer);
								if(my != null){
									my.setUpdateTime(new Date());
									my.setStatus(0);
									myMistakesService.updateMyMistakesHomeworkById(my);
								}
							}
						}
					}
				} else {// 只有一个答案
					if (pa.getWriteAnswer().equals(fillName)) {
						pAnswer.setIsTrue(1);
						paperAnswerService.updateIsTrue(pAnswer);
						if (my != null) {
							my.setUpdateTime(new Date());
							my.setStatus(1);
							myMistakesService.updateMyMistakesHomeworkById(my);
						}
					} else {
						pAnswer.setIsTrue(0);
						len1 = paperAnswerService.updateIsTrue(pAnswer);
						if(my != null){
							my.setUpdateTime(new Date());
							my.setStatus(0);
							myMistakesService.updateMyMistakesHomeworkById(my);
						}
					}
				}
			}else if (pa.getQuestionType() ==7) {//完形填空
				QuestionCloze cloze = clozeService.getQuestionClozeById(pa.getQuestionId());
				if (cloze.getClozeAnswer().equals(pAnswer.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
					if (my != null) {
						my.setUpdateTime(new Date());
						my.setStatus(1);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				}else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
					if(my != null){
						my.setUpdateTime(new Date());
						my.setStatus(0);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				}
			}
			output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
		} else {// 没做个则新增
			pa.setCreateTime(new Date());
			pa.setValue(pd.getValue());
			if(tr != null){
				if(tr.getClassId() != null){
					pa.setClassId(tr.getClassId());
				}
			}
			paperAnswerService.savePaperAnswer(pa);// 返回主键Id
			PaperAnswer pAnswer = paperAnswerService.getPaperById(pa.getAnswerId());
			TestRecord record = testrecordservice.getTestRecordByStuandPaper(pa.getStudentId(), pa.getPaperId());
			PaperInfo pinfo = infoService.getPaperInfoById(pa.getPaperId());
			if (pAnswer != null) {
				int len = 0, len1 = 0;
				if (pa.getQuestionType() == 1) {
					QuestionSingleChoice qsc = qschoiceService.getQuestionById(pAnswer.getQuestionId());
					if (qsc.getAnswer().equals(pAnswer.getWriteAnswer())) {
						pAnswer.setIsTrue(1);
						paperAnswerService.updateIsTrue(pAnswer);
						// 做对修改作业记录表的得分
						if (record.getScore() != null) {
							record.setScore(record.getScore() + pAnswer.getValue());
						} else {
							record.setScore(pAnswer.getValue());
						}
					} else {
						if (my == null) {
							my = new MyMistakes();
							my.setTopicType(1);
							my.setCreateTime(new Date());
							my.setPaperId(pAnswer.getPaperId());
							my.setStudentId(pAnswer.getStudentId());
							my.setQuestionId(pAnswer.getQuestionId());
							if (record.getClassId() != null) {
								my.setClassId(record.getClassId());
							}
							my.setValue(pAnswer.getValue());
							my.setSubjectId(pinfo.getSubject());
							my.setStatus(0);
							myMistakesService.addPaperMistake(my);
						}
						pAnswer.setIsTrue(0);
						len1 = paperAnswerService.updateIsTrue(pAnswer);
					}
					if (record.getWriteNumber() != null) {
						record.setWriteNumber(record.getWriteNumber() + 1);
					} else {
						record.setWriteNumber(1);
					}
					if (record.getUpdateTime() == null) {// 只要修改一次就好，到时完成试卷用它来算出用时
						record.setUpdateTime(new Date());
					}
					record.setGradeId(pinfo.getGrade());
					len = testrecordservice.updateScoreByStuIdandPaperId(record);
				} else if (pa.getQuestionType() == 2) {
					QuestionMulitChoice qmc = qmceService.getMulitChoiceById(pAnswer.getQuestionId());
					if (qmc.getAnswer1().equals(pAnswer.getWriteAnswer())) {
						pAnswer.setIsTrue(1);
						paperAnswerService.updateIsTrue(pAnswer);
						// 做对修改作业记录表的得分
						if (record.getScore() != null) {
							record.setScore(record.getScore() + pAnswer.getValue());
						} else {
							record.setScore(pAnswer.getValue());
						}
					} else {
						if (my == null) {
							my = new MyMistakes();
							my.setTopicType(2);
							my.setCreateTime(new Date());
							my.setPaperId(pAnswer.getPaperId());
							my.setStudentId(pAnswer.getStudentId());
							my.setQuestionId(pAnswer.getQuestionId());
							if (record.getClassId() != null) {
								my.setClassId(record.getClassId());
							}
							my.setValue(pAnswer.getValue());
							my.setSubjectId(pinfo.getSubject());
							my.setStatus(0);
							myMistakesService.addPaperMistake(my);
						}
						pAnswer.setIsTrue(0);
						len1 = paperAnswerService.updateIsTrue(pAnswer);
					}
					if (record.getWriteNumber() != null) {
						record.setWriteNumber(record.getWriteNumber() + 1);
					} else {
						record.setWriteNumber(1);
					}
					if (record.getUpdateTime() == null) {// 只要修改一次就好，到时完成试卷用它来算出用时
						record.setUpdateTime(new Date());
					}
					record.setGradeId(pinfo.getGrade());
					len = testrecordservice.updateScoreByStuIdandPaperId(record);
				} else if (pa.getQuestionType() == 3) {
					QuestionJudge qj = qjService.getJudgeById(pAnswer.getQuestionId());
					if (qj.getAnswer2().equals(pAnswer.getWriteAnswer())) {
						pAnswer.setIsTrue(1);
						paperAnswerService.updateIsTrue(pAnswer);
						// 做对修改作业记录表的得分
						if (record.getScore() != null) {
							record.setScore(record.getScore() + pAnswer.getValue());
						} else {
							record.setScore(pAnswer.getValue());
						}
					} else {
						if (my == null) {
							my = new MyMistakes();
							my.setTopicType(3);
							my.setCreateTime(new Date());
							my.setPaperId(pAnswer.getPaperId());
							my.setStudentId(pAnswer.getStudentId());
							my.setQuestionId(pAnswer.getQuestionId());
							if (record.getClassId() != null) {
								my.setClassId(record.getClassId());
							}
							my.setValue(pAnswer.getValue());
							my.setSubjectId(pinfo.getSubject());
							my.setStatus(0);
							myMistakesService.addPaperMistake(my);
						}
						pAnswer.setIsTrue(0);
						len1 = paperAnswerService.updateIsTrue(pAnswer);
					}
					if (record.getWriteNumber() != null) {
						record.setWriteNumber(record.getWriteNumber() + 1);
					} else {
						record.setWriteNumber(1);
					}
					if (record.getUpdateTime() == null) {// 只要修改一次就好，到时完成试卷用它来算出用时
						record.setUpdateTime(new Date());
					}
					record.setGradeId(pinfo.getGrade());
					len = testrecordservice.updateScoreByStuIdandPaperId(record);
				} else if (pa.getQuestionType() == 6) {// 填空题
					QuestionFill fill = fillservice.getQuestionFillById(pa.getQuestionId());
					String fillName = guoHtml(fill.getFillAnswer());
					String[] paperAnswer = pa.getWriteAnswer().split("##");
					String[] fillAnswer = fillName.split("##");
					if (paperAnswer.length > 1 && fillAnswer.length > 1) {// 有多个答案
						for (int i = 0; i < paperAnswer.length; i++) {
							for (int j = 0; j < fillAnswer.length; j++) {
								if (paperAnswer[i].equals(fillAnswer[j])) {
									pAnswer.setIsTrue(1);
									paperAnswerService.updateIsTrue(pAnswer);
								} else {
									if (my == null) {
										my = new MyMistakes();
										my.setTopicType(6);
										my.setCreateTime(new Date());
										my.setPaperId(pAnswer.getPaperId());
										my.setStudentId(pAnswer.getStudentId());
										my.setQuestionId(pAnswer.getQuestionId());
										if (record.getClassId() != null) {
											my.setClassId(record.getClassId());
										}
										my.setValue(pAnswer.getValue());
										my.setSubjectId(pinfo.getSubject());
										my.setStatus(0);
										myMistakesService.addPaperMistake(my);
									}
									pAnswer.setIsTrue(0);
									len1 = paperAnswerService.updateIsTrue(pAnswer);
								}
							}
						}
					} else {// 只有一个答案
						if (pa.getWriteAnswer().equals(fillName)) {
							pAnswer.setIsTrue(1);
							paperAnswerService.updateIsTrue(pAnswer);
						} else {
							if (my == null) {
								my = new MyMistakes();
								my.setTopicType(6);
								my.setCreateTime(new Date());
								my.setPaperId(pAnswer.getPaperId());
								my.setStudentId(pAnswer.getStudentId());
								my.setQuestionId(pAnswer.getQuestionId());
								if (record.getClassId() != null) {
									my.setClassId(record.getClassId());
								}
								my.setValue(pAnswer.getValue());
								my.setSubjectId(pinfo.getSubject());
								my.setStatus(0);
								myMistakesService.addPaperMistake(my);
							}
							pAnswer.setIsTrue(0);
							len1 = paperAnswerService.updateIsTrue(pAnswer);
						}
					}
					if (record.getWriteNumber() != null) {
						record.setWriteNumber(record.getWriteNumber() + 1);
					} else {
						record.setWriteNumber(1);
					}
					if (record.getUpdateTime() == null) {// 只要修改一次就好，到时完成试卷用它来算出用时
						record.setUpdateTime(new Date());
					}
					record.setGradeId(pinfo.getGrade());
					len = testrecordservice.updateScoreByStuIdandPaperId(record);
				} 
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
			}
		}
	}

	/**
	 * 入学考试
	 * 
	 * @param response
	 * @param pa
	 */
	@RequestMapping("/examinationRecord")
	public void examinationRecord(HttpServletResponse response, PaperAnswer pa) {
		// 入学考试钱判断是否做个该道题(同一套套试卷)，做个则修改答案，没做个则添加
		PaperAnswer paAnswer = paperAnswerService.isdoPaperIdById(pa.getStudentId(), pa.getPaperId(),
				pa.getQuestionId(), pa.getQuestionType());
		PaperDetails pd = paperDetailsService.findPaperDetailsById(pa.getPaperId(), pa.getQuestionId(),
				pa.getQuestionType());
		PaperInfo info = infoService.getPaperInfoById(pa.getPaperId());
		TestRecord record1 = testrecordservice.isWriteMessage(pa.getStudentId(), pa.getPaperId(),null);
		if (record1 == null) {
			TestRecord tr = new TestRecord();
			tr.setStudentId(pa.getStudentId());
			tr.setStatus(2);
			if (info != null) {
				tr.setGradeId(info.getGrade());
				tr.setSubjectId(info.getSubject());
			}
			tr.setCreateTime(new Date());
			tr.setPaperId(pa.getPaperId());
			tr.setPaperType(4);
			testrecordservice.saveTextRecord(tr);
		}
		TestRecord record = testrecordservice.isWriteMessage(pa.getStudentId(), pa.getPaperId(),null);
		if (paAnswer != null) {// 做过则修改
			paAnswer.setWriteAnswer(pa.getWriteAnswer());
			paAnswer.setValue(pd.getValue());
			paperAnswerService.updateWriteAnswer(paAnswer);
			int len1 = 0;
			PaperAnswer pAnswer = paperAnswerService.getPaperById(paAnswer.getAnswerId());
			if (pa.getQuestionType() == 1) {
				QuestionSingleChoice qsc = qschoiceService.getQuestionById(pAnswer.getQuestionId());
				if (qsc.getAnswer().equals(pa.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
				} else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
				}
			} else if (pa.getQuestionType() == 2) {
				QuestionMulitChoice qmc = qmceService.getMulitChoiceById(pAnswer.getQuestionId());
				if (qmc.getAnswer1().equals(pAnswer.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
				} else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
				}
			} else if (pa.getQuestionType() == 3) {
				QuestionJudge qj = qjService.getJudgeById(pAnswer.getQuestionId());
				if (qj.getAnswer2().equals(pAnswer.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
				} else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
				}
			} else if (pa.getQuestionType() == 6) {// 填空题
				QuestionFill fill = fillservice.getQuestionFillById(pa.getQuestionId());
				String fillName = guoHtml(fill.getFillAnswer());
				String[] paperAnswer = pa.getWriteAnswer().split("##");
				String[] fillAnswer = fillName.split("##");
				if (paperAnswer.length > 1 && fillAnswer.length > 1) {// 有多个答案
					for (int i = 0; i < paperAnswer.length; i++) {
						for (int j = 0; j < fillAnswer.length; j++) {
							if (paperAnswer[i].equals(fillAnswer[j])) {
								pAnswer.setIsTrue(1);
								paperAnswerService.updateIsTrue(pAnswer);
							} else {
								pAnswer.setIsTrue(0);
								len1 = paperAnswerService.updateIsTrue(pAnswer);
							}
						}
					}
				} else {// 只有一个答案
					if (pa.getWriteAnswer().equals(fillName)) {
						pAnswer.setIsTrue(1);
						paperAnswerService.updateIsTrue(pAnswer);
					} else {
						pAnswer.setIsTrue(0);
						len1 = paperAnswerService.updateIsTrue(pAnswer);
					}
				}
			} else if (pa.getQuestionType() == 7) {// 完形填空
				QuestionCloze cloze = clozeService.getQuestionClozeById(pa.getQuestionId());
				if (cloze.getClozeAnswer().equals(pAnswer.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
				} else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
				}
			}
			output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
		} else {// 没做过则新增
			pa.setCreateTime(new Date());
			pa.setValue(pd.getValue());
			paperAnswerService.savePaperAnswer(pa);// 返回主键Id//根据主键查找数据
			PaperAnswer pAnswer = paperAnswerService.getPaperById(pa.getAnswerId());
			int len = 0, len1 = 0;
			if (pa.getQuestionType() == 1) {
				QuestionSingleChoice qsc = qschoiceService.getQuestionById(pAnswer.getQuestionId());
				if (qsc.getAnswer().equals(pAnswer.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
					// 做对修改作业记录表的得分
					if (record.getScore() != 0) {
						record.setScore(record.getScore() + pa.getValue());
					} else {
						record.setScore(pa.getValue().doubleValue());
					}
				} else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
				}
				if (record.getWriteNumber() != null) {
					record.setWriteNumber(record.getWriteNumber() + 1);
				} else {
					record.setWriteNumber(1);
				}
				if (record.getUpdateTime() == null) {// 只要修改一次就好，到时完成试卷用它来算出用时
					record.setUpdateTime(new Date());
				}
				len = testrecordservice.updateScoreByStuIdandPaperId(record);
			} else if (pa.getQuestionType() == 2) {
				QuestionMulitChoice qmc = qmceService.getMulitChoiceById(pAnswer.getQuestionId());

				if (qmc.getAnswer1().equals(pAnswer.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
					// 做对修改作业记录表的得分
					if (record.getScore() != 0) {
						record.setScore(record.getScore() + pa.getValue());
					} else {
						record.setScore(pa.getValue().doubleValue());
					}
				} else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
				}
				if (record.getWriteNumber() != null) {
					record.setWriteNumber(record.getWriteNumber() + 1);
				} else {
					record.setWriteNumber(1);
				}
				if (record.getUpdateTime() == null) {// 只要修改一次就好，到时完成试卷用它来算出用时
					record.setUpdateTime(new Date());
				}
				len = testrecordservice.updateScoreByStuIdandPaperId(record);
			} else if (pa.getQuestionType() == 3) {
				QuestionJudge qj = qjService.getJudgeById(pAnswer.getQuestionId());
				if (qj.getAnswer2().equals(pAnswer.getWriteAnswer())) {
					pAnswer.setIsTrue(1);
					paperAnswerService.updateIsTrue(pAnswer);
					// 做对修改作业记录表的得分
					if (record.getScore() != 0) {
						record.setScore(record.getScore() + pa.getValue());
					} else {
						record.setScore(pa.getValue().doubleValue());
					}
				} else {
					pAnswer.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pAnswer);
				}
				if (record.getWriteNumber() != null) {
					record.setWriteNumber(record.getWriteNumber() + 1);
				} else {
					record.setWriteNumber(1);
				}
				if (record.getUpdateTime() == null) {// 只要修改一次就好，到时完成试卷用它来算出用时
					record.setUpdateTime(new Date());
				}
				len = testrecordservice.updateScoreByStuIdandPaperId(record);
			} else if (pa.getQuestionType() == 6) {// 填空题
				QuestionFill fill = fillservice.getQuestionFillById(pa.getQuestionId());
				String fillName = guoHtml(fill.getFillAnswer());
				String[] paperAnswer = pa.getWriteAnswer().split("##");
				String[] fillAnswer = fillName.split("##");
				if (paperAnswer.length > 1 && fillAnswer.length > 1) {// 有多个答案
					for (int i = 0; i < paperAnswer.length; i++) {
						for (int j = 0; j < fillAnswer.length; j++) {
							if (paperAnswer[i].equals(fillAnswer[j])) {
								pAnswer.setIsTrue(1);
								paperAnswerService.updateIsTrue(pAnswer);
								// 做对修改作业记录表的得分
								if (record.getScore() != 0) {
									record.setScore(record.getScore() + pa.getValue());
								} else {
									record.setScore(pa.getValue().doubleValue());
								}
							} else {
								pAnswer.setIsTrue(0);
								len1 = paperAnswerService.updateIsTrue(pAnswer);
							}
						}
					}
				} else {// 只有一个答案
					if (pa.getWriteAnswer().equals(fillName)) {
						pAnswer.setIsTrue(1);
						paperAnswerService.updateIsTrue(pAnswer);
						// 做对修改作业记录表的得分
						if (record.getScore() != 0) {
							record.setScore(record.getScore() + pa.getValue());
						} else {
							record.setScore(pa.getValue().doubleValue());
						}
					} else {
						pAnswer.setIsTrue(0);
						len1 = paperAnswerService.updateIsTrue(pAnswer);
					}
				}
				if (record.getWriteNumber() != null) {
					record.setWriteNumber(record.getWriteNumber() + 1);
				} else {
					record.setWriteNumber(1);
				}
				if (record.getUpdateTime() == null) {// 只要修改一次就好，到时完成试卷用它来算出用时
					record.setUpdateTime(new Date());
				}
				record.setGradeId(info.getGrade());
				len = testrecordservice.updateScoreByStuIdandPaperId(record);
			} 
			output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
		}
	}

	/**
	 * 删除我的错题
	 * 
	 * @param response
	 * @param mistakesId
	 * @param questionId
	 */
	@RequestMapping("/removeMistakes")
	public void removeMistakes(HttpServletResponse response, Integer mistakesId) {
		try {
			myMistakesService.removeMistakes(mistakesId);
			output(response, JsonUtil.buildFalseJson("0", "移除成功!"));
		} catch (Exception e) {
			output(response, JsonUtil.buildFalseJson("1", "移除失败!"));
		}
	}

	
	//保存完形填空
	@RequestMapping("/addQuestionCloze") 
	public void addQuestionCloze(HttpServletResponse response,Integer studentId,Integer paperId,String locations,Integer questionId){
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(locations);
		JsonArray jsonArray = null;
		if (element.isJsonArray()) {
			jsonArray = element.getAsJsonArray();
		}
		PaperInfo info = infoService.getPiIdById(paperId);
		TestRecord record1 = testrecordservice.isWriteMessage(studentId, paperId,null);
		if (record1 == null) {
			TestRecord tr = new TestRecord();
			tr.setStudentId(studentId);
			tr.setStatus(2);
			if (info != null) {
				tr.setGradeId(info.getGrade());
				tr.setSubjectId(info.getSubject());
			}
			tr.setCreateTime(new Date());
			tr.setPaperId(paperId);
			tr.setPaperType(4);
			testrecordservice.saveTextRecord(tr);
		}
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject job = (JsonObject) jsonArray.get(i);
			PaperDetails pd = paperDetailsService.findPaperDetailsById(paperId, questionId, 7);
			List<QuestionCloze> clozes = clozeService.getQuestionClozeByParentId(pd.getQuestionId());
			// 查看是否做个这道题
			PaperAnswer tl = paperAnswerService.isdoPaperIdById(studentId, paperId, job.get("tid").getAsInt(), 4);
			TestRecord tr = testrecordservice.getStauts(studentId, paperId);
			if (tl == null) {
				// 没做个则新增
				tl = new PaperAnswer();
				tl.setStudentId(studentId);
				tl.setCreateTime(new Date());
				tl.setQuestionType(7);
				if(tr != null){
					if(tr.getClassId() != null){
						tl.setClassId(tr.getClassId());
					}
				}
				tl.setPaperId(paperId);
				tl.setValue(pd.getValue() / clozes.size());
				tl.setQuestionId(job.get("tid").getAsInt());
				tl.setWriteAnswer(job.get("answer").getAsString());
				paperAnswerService.savePaperAnswer(tl);// 返回主键Id
				// 根据主键查找数据
				PaperAnswer pa = paperAnswerService.getPaperById(tl.getAnswerId());
				// 拿到班级编号
				TestRecord record = testrecordservice.getTestRecordByStuandPaper(studentId, paperId);
				PaperInfo pInfo = infoService.getPiIdById(paperId);
				if (record != null) {
					// 根据题目id去阅读理解表里查找答案是否正确
					QuestionCloze cloze = clozeService.getQuestionClozeById(job.get("tid").getAsInt());
					MyMistakes my = myMistakesService.findMyMistakesHomeworkById(studentId, null,
							job.get("tid").getAsInt(), 7, null);
					// 正确
					if (job.get("answer").getAsString().equals(cloze.getClozeAnswer())
							|| cloze.getClozeAnswer().equals(job.get("answer").getAsString())) {
						pa.setIsTrue(1);
						paperAnswerService.updateIsTrue(pa);
						
					} else {// 错误则往错题库里添加数据
						if (my == null) {
							my = new MyMistakes();
							my.setCreateTime(new Date());
							my.setHomeworkId(pa.getPaperId());
							my.setStudentId(pa.getStudentId());
							my.setQuestionId(pa.getQuestionId());
							if (record.getClassId() != null) {
								my.setClassId(record.getClassId());
							}
							my.setValue(pa.getValue());
							my.setTopicType(7);
							my.setStatus(0);
							my.setSubjectId(pInfo.getSubject());
							myMistakesService.addHomeworkMistake(my);// 返回主键
						}
						pa.setIsTrue(0);
						 paperAnswerService.updateIsTrue(pa);
					}
				}
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
			}else {
				// 做个则修改
				tl.setValue(pd.getValue());
				tl.setQuestionId(job.get("tid").getAsInt());
				tl.setWriteAnswer(job.get("answer").getAsString());
				if(tr != null){
					if(tr.getClassId() != null){
						tl.setClassId(tr.getClassId());
					}
				}
				paperAnswerService.updateWriteAnswer(tl);// 返回主键Id
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
				// 根据主键查找数据
				PaperAnswer pa = paperAnswerService.getPaperById(tl.getAnswerId());
				// 根据题目id去阅读理解表里查找答案是否正确
				QuestionCloze cloze = clozeService.getQuestionClozeById(job.get("tid").getAsInt());
				MyMistakes mistakes = myMistakesService.findMyMistakesHomeworkById(studentId, null,
						job.get("tid").getAsInt(), 7, paperId);
				// 正确
				if (job.get("answer").getAsString().equals(cloze.getClozeAnswer())
						|| cloze.getClozeAnswer().equals(job.get("answer").getAsString())) {
					pa.setIsTrue(1);
					paperAnswerService.updateIsTrue(pa);
					if (mistakes != null) {
						mistakes.setUpdateTime(new Date());
						mistakes.setStatus(1);
						myMistakesService.updateMyMistakesHomeworkById(mistakes);
					}
				} else {// 错误则往错题库里添加数据
					pa.setIsTrue(0);
					 paperAnswerService.updateIsTrue(pa);
					 if(mistakes != null){
						 mistakes.setUpdateTime(new Date());
							mistakes.setStatus(0);
							myMistakesService.updateMyMistakesHomeworkById(mistakes);
					 }
				}
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
			}
		}
		if (record1.getWriteNumber() != null) {
			record1.setWriteNumber(record1.getWriteNumber() + 1);
		} else {
			record1.setWriteNumber(1);
		}
		if (record1.getUpdateTime() == null) {// 只要修改一次就好，到时完成试卷用它来算出用时
			record1.setUpdateTime(new Date());
		}
		testrecordservice.updateScoreByStuIdandPaperId(record1);
	}
	/**
	 * 保存阅读理解
	 * 
	 * @param response
	 * @param pa
	 */
	@RequestMapping("/saveQuestionRead")
	public void saveQuestionRead(HttpServletResponse response, Integer studentId, Integer paperId, String locations,
			Integer questionId) {
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(locations);
		JsonArray jsonArray = null;
		int len = 0, len1 = 0;
		if (element.isJsonArray()) {
			jsonArray = element.getAsJsonArray();
		}
		PaperInfo info = infoService.getPiIdById(paperId);
		TestRecord record1 = testrecordservice.isWriteMessage(studentId, paperId,null);
		if (record1 == null) {
			TestRecord tr = new TestRecord();
			tr.setStudentId(studentId);
			tr.setStatus(2);
			if (info != null) {
				tr.setGradeId(info.getGrade());
				tr.setSubjectId(info.getSubject());
			}
			tr.setCreateTime(new Date());
			tr.setPaperId(paperId);
			tr.setPaperType(4);
			testrecordservice.saveTextRecord(tr);
		}
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject job = (JsonObject) jsonArray.get(i);
			PaperDetails pd = paperDetailsService.findPaperDetailsById(paperId, questionId, 4);
			// 根据阅读理解大题目的Id查找有多少子题目
			List<QuestionRead> qrList = qrService.getReadChildByParentId(pd.getQuestionId());
			// 查看是否做个这道题
			PaperAnswer tl = paperAnswerService.isdoPaperIdById(studentId, paperId, job.get("tid").getAsInt(), 4);
			TestRecord tr = testrecordservice.getStauts(studentId, paperId);
			if (tl == null) {// 没做个则新增
				tl = new PaperAnswer();
				tl.setStudentId(studentId);
				tl.setCreateTime(new Date());
				tl.setQuestionType(4);
				if(tr != null){
					if(tr.getClassId() != null){
						tl.setClassId(tr.getClassId());
					}
				}
				tl.setPaperId(paperId);
				tl.setValue(pd.getValue() / qrList.size());
				tl.setQuestionId(job.get("tid").getAsInt());
				tl.setWriteAnswer(job.get("answer").getAsString());
				paperAnswerService.savePaperAnswer(tl);// 返回主键Id
				// 根据主键查找数据
				PaperAnswer pa = paperAnswerService.getPaperById(tl.getAnswerId());
				// 拿到班级编号
				TestRecord record = testrecordservice.getTestRecordByStuandPaper(studentId, paperId);
				PaperInfo pInfo = infoService.getPiIdById(paperId);
				if (record != null) {
					// 根据题目id去阅读理解表里查找答案是否正确
					QuestionRead qr = qrService.getPaperAnswerReadById(job.get("tid").getAsInt());
					MyMistakes my = myMistakesService.findMyMistakesHomeworkById(studentId, null,
							job.get("tid").getAsInt(), 4, null);
					// 正确
					if (job.get("answer").getAsString().equals(qr.getAnswer_read())
							|| qr.getAnswer_read().equals(job.get("answer").getAsString())) {
						pa.setIsTrue(1);
						paperAnswerService.updateIsTrue(pa);
					} else {// 错误则往错题库里添加数据
						if (my == null) {
							my = new MyMistakes();
							my.setCreateTime(new Date());
							my.setHomeworkId(pa.getPaperId());
							my.setStudentId(pa.getStudentId());
							my.setQuestionId(pa.getQuestionId());
							my.setClassId(record.getClassId());
							my.setValue(pa.getValue());
							my.setTopicType(4);
							my.setStatus(0);
							my.setSubjectId(pInfo.getSubject());
							myMistakesService.addHomeworkMistake(my);// 返回主键
						}
						pa.setIsTrue(0);
						len1 = paperAnswerService.updateIsTrue(pa);
					}
				}
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
			} else {// 做个则修改
//				tl.setValue(pd.getValue());
				tl.setQuestionId(job.get("tid").getAsInt());
				tl.setWriteAnswer(job.get("answer").getAsString());
				if(tr != null){
					if(tr.getClassId() != null){
						tl.setClassId(tr.getClassId());
					}
				}
				paperAnswerService.updateWriteAnswer(tl);// 返回主键Id
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
				// 根据主键查找数据
				PaperAnswer pa = paperAnswerService.getPaperById(tl.getAnswerId());
				// 根据题目id去阅读理解表里查找答案是否正确
				QuestionRead qr = qrService.getPaperAnswerReadById(job.get("tid").getAsInt());
				MyMistakes mistakes = myMistakesService.findMyMistakesHomeworkById(studentId, null,
						job.get("tid").getAsInt(), 4, paperId);
				// 正确
				if (job.get("answer").getAsString().equals(qr.getAnswer_read())
						|| qr.getAnswer_read().equals(job.get("answer").getAsString())) {
					pa.setIsTrue(1);
					paperAnswerService.updateIsTrue(pa);
					if (mistakes != null) {
						mistakes.setUpdateTime(new Date());
						mistakes.setStatus(1);
						myMistakesService.updateMyMistakesHomeworkById(mistakes);
					}
				} else {// 错误则往错题库里添加数据
					pa.setIsTrue(0);
					len1 = paperAnswerService.updateIsTrue(pa);
					if(mistakes != null){
						mistakes.setUpdateTime(new Date());
						mistakes.setStatus(0);
						myMistakesService.updateMyMistakesHomeworkById(mistakes);
					}
				}
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
			}
		}
		if (record1.getWriteNumber() != null) {
			record1.setWriteNumber(record1.getWriteNumber() + 1);
		} else {
			record1.setWriteNumber(1);
		}
		if (record1.getUpdateTime() == null) {// 只要修改一次就好，到时完成试卷用它来算出用时
			record1.setUpdateTime(new Date());
		}
		len = testrecordservice.updateScoreByStuIdandPaperId(record1);
	}

	/**
	 * 作业主观题加一
	 * 
	 * @param response
	 * @param studentId
	 * @param paperId
	 */
	@RequestMapping("/addSubjective")
	public void addSubjective(HttpServletResponse response, Integer studentId, Integer paperId) {
		// 拿到班级编号
		TestRecord record = testrecordservice.getTestRecordByStuandPaper(studentId, paperId);
		if (record.getWriteNumber() != null) {
			record.setWriteNumber(record.getWriteNumber() + 1);
		} else {
			record.setWriteNumber(1);
		}
		record.setUpdateTime(new Date());
		try {
			testrecordservice.updateWriteNumber(record);
			output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
		} catch (Exception e) {
			output(response, JsonUtil.buildFalseJson("0", "保存失败!"));
		}
	}

	/**
	 * 查找做过该道题的信息，包括选A、B、C、D的数量
	 * 
	 * @param response
	 * @param questionId
	 * @param type
	 */
	@RequestMapping("questionMessage")
	public void questionMessage(HttpServletResponse response, Integer questionId, Integer type) {
		List<PaperAnswer> paList = paperAnswerService.getMessageByQuestion(questionId, type);
		NumberFormat format = NumberFormat.getPercentInstance();// 获取实例
		double a = 0.0, b = 0.0, c = 0.0, d = 0.0, judge1 = 0.0, judge2 = 0.0, duoxuan1 = 0.0, duoxuan2 = 0.0;
		int status = 0;
		for (PaperAnswer answer : paList) {
			if (type == 1 || type == 4) {
				// 是否包含answer.getWriteAnswer().indexOf("A") != -1
				if (answer.getWriteAnswer().equals("A")) {
					a++;
					if (answer.getIsTrue() == 1) {
						status = 1;
					}
				} else if (answer.getWriteAnswer().equals("B")) {
					b++;
					if (answer.getIsTrue() == 1) {
						status = 2;
					}
				} else if (answer.getWriteAnswer().equals("C")) {
					c++;
					if (answer.getIsTrue() == 1) {
						status = 3;
					}
				} else if (answer.getWriteAnswer().equals("D")) {
					d++;
					if (answer.getIsTrue() == 1) {
						status = 4;
					}
				}
			} else if (type == 2) {// 多选
				if (answer.getIsTrue() == 1) {
					duoxuan1++;
					status = 7;
				} else {
					duoxuan2++;
					status = 8;
				}
			} else if (type == 3) {// 判断
				if (answer.getWriteAnswer().equals("1")) {
					judge1++;
					if (answer.getIsTrue() == 1) {
						status = 5;
					}
				} else if (answer.getWriteAnswer().equals("0")) {
					judge2++;
					if (answer.getIsTrue() == 1) {
						status = 6;
					}
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", paList.size());
		if (type == 1 || type == 4) {
			map.put("a_count", a);
			map.put("b_count", b);
			map.put("c_count", c);
			map.put("d_count", d);
		} else if (type == 2) {
			map.put("judge1_count", duoxuan1);
			map.put("judge2_count", duoxuan2);
		} else {
			map.put("judge1_count", judge1);
			map.put("judge2_count", judge2);
		}
		if (status == 1) {
			map.put("score_rate", format.format(a / paList.size()));
		} else if (status == 2) {
			map.put("score_rate", format.format(b / paList.size()));
		} else if (status == 3) {
			map.put("score_rate", format.format(c / paList.size()));
		} else if (status == 4) {
			map.put("score_rate", format.format(d / paList.size()));
		} else if (status == 5) {
			map.put("score_rate", format.format(judge1 / paList.size()));
		} else if (status == 6) {
			map.put("score_rate", format.format(judge2 / paList.size()));
		} else if (status == 7) {
			map.put("score_rate", format.format(duoxuan1 / paList.size()));
		} else if (status == 8) {
			map.put("score_rate", format.format(duoxuan2 / paList.size()));
		} else {
			map.put("score_rate", 0);
		}
		List list2 = new ArrayList<>();
		list2.add(map);
		output(response, JsonUtil.buildJson(list2));
	}

	// 过滤标签
	public static String guoHtml(String string) {
		if (!string.equals("") && string != null) {
			/*String str = string.replaceAll("<[.[^<]]*>", "");
			str = str.replaceAll("\\s*|\t|\r|\n", "");*/
			String content = string.replaceAll("</?[^<]+>", "");
			// 去除字符串中的空格 回车 换行符 制表符 等
			content = content.replaceAll("\\s*|\t|\r|\n", "");
			// 去除空格
			content = content.replaceAll("&nbsp;", "");
			// 去掉其他一些字符
//			content = content.replaceAll("\\", "");
			return content;
		} else {
			return string;
		}
	}
}
