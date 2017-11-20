package com.db.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.Homework;
import com.db.entity.HomeworkAnswer;
import com.db.entity.HomeworkDetails;
import com.db.entity.HomeworkRecord;
import com.db.entity.MyHomework;
import com.db.entity.MyMistakes;
import com.db.entity.QuestionCloze;
import com.db.entity.QuestionFill;
import com.db.entity.QuestionJudge;
import com.db.entity.QuestionMulitChoice;
import com.db.entity.QuestionPaer;
import com.db.entity.QuestionRead;
import com.db.entity.QuestionSingleChoice;
import com.db.entity.SeePaper;
import com.db.entity.SubjectiveQuestion;
import com.db.entity.TimeAndHomework;
import com.db.service.HomeworkAnswerService;
import com.db.service.HomeworkDetailsService;
import com.db.service.HomeworkRecordService;
import com.db.service.HomeworkService;
import com.db.service.MyMistakesService;
import com.db.service.PaperInfoService;
import com.db.service.QuestionClozeService;
import com.db.service.QuestionFillService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.service.QuestionSingleChoiceService;
import com.db.service.SubjectiveQuestionService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.MapCacheUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("/app/homework")
public class APPHomeworkController extends BaseUtil {

	MapCacheUtil mapcahe = MapCacheUtil.getInstance();

	// 缓存某个学生读题的状态
	public volatile static Map<String, Map<String, Object>> casemap1 = new HashMap<String, Map<String, Object>>();
	public volatile static Map<String, Object> map1 = new HashMap<>();
	
	@Resource
	private HomeworkService homeworkService;
	@Resource
	private QuestionReadService questionReadService;
	@Resource
	private QuestionSingleChoiceService questionSingleChoiceService;
	@Resource
	private QuestionJudgeService questionJudgeService;
	@Resource
	private QuestionMulitChoiceService questionMulitChoiceService;
	@Resource
	private HomeworkAnswerService homeworkAnswerService;
	@Resource
	private HomeworkDetailsService homeworkDetailsService;
	@Resource
	private MyMistakesService myMistakesService;
	@Resource
	private PaperInfoService paperInfoService;
	@Resource
	private HomeworkRecordService homeworkRecordService;
	@Resource
	private SubjectiveQuestionService questionService;
	@Resource
	private QuestionFillService fillservice;
	@Resource
	private QuestionClozeService clozeService;

	/**
	 * 前端课后作业列表
	 * 
	 * @param response
	 */
	@RequestMapping("/homeworkList")
	public void homeworkList(HttpServletResponse response, Integer studentId, Integer pageNo, Integer pageSize,String name) {

		List<HomeworkRecord> hList = homeworkRecordService.getHomeworkByStudentId(studentId, (pageNo - 1) * pageSize,
				pageSize,name);
		List<MyHomework> myhomList = new ArrayList<MyHomework>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (hList == null || hList.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		} else {
			for (HomeworkRecord hr : hList) {
				// 先获取paperanswer中学生+试卷编号的条数（做题总数）
				int stu_count = homeworkAnswerService.getCountwhenWriting(studentId, hr.getHomeworkId());
				System.out.println("学生做了几道题目：" + stu_count);
				// 再获取试题中题目的总数
				int count = homeworkDetailsService.getTotalByPaperId(hr.getHomeworkId());
				System.err.println("试卷的题目数：" + count);
				if (hr.getStatus() != 3) {
					if (stu_count > 0 && stu_count <= count) {
						hr.setStatus(2);// 继续的状态
					}
				}
				List<TimeAndHomework> tahList = new ArrayList<TimeAndHomework>();
				MyHomework myMyh = new MyHomework();
				TimeAndHomework tah1 = new TimeAndHomework();
				tah1.setHomeworkId(hr.getHomeworkId());
				tah1.setSubjectName(hr.getSubject2().getSubjectName());
				tah1.setStudentId(hr.getStudentId());
				tah1.setScore(hr.getScore());
				tah1.setHomeworkName(hr.getHomework().getHkName());
				tah1.setHomeworStatus(hr.getStatus());
				if (hr.getSubject() != null) {
					tah1.setSubject(String.valueOf(hr.getSubject()));
				}
				if (hr.getTotalTime() != null) {
					tah1.setTotalTime(hr.getTotalTime());
				}
				if (mapcahe.getCaseMap("statusHomeBy" + studentId + "and" + hr.getHomeworkId()) != null) {
					Map<String, Object> map = new HashMap<>();
					Map<String, Object> map1 = mapcahe
							.getCaseMap("statusHomeBy" + studentId + "and" + hr.getHomeworkId());
					map.put("pageNo", map1.get("pageNoHome" + studentId + "and" + hr.getHomeworkId()));
					map.put("cstatus", map1.get("statusHome" + studentId + "and" + hr.getHomeworkId()));
					hr.setCasemap(map);
				}
				tah1.setCasemap(hr.getCasemap());
				tah1.setWriteNumber(hr.getWriteNumber());
				tah1.setHkTotal(hr.getHomework().getHkTotal());
				tah1.setStatus(hr.getStatus());
				tahList.add(tah1);
				myMyh.setMyDate(sdf.format(hr.getCreateTime()));
				myMyh.setTaHomeworks(tahList);
				myhomList.add(myMyh);
				hr.setStudentList(null);
			}
			output(response, JsonUtil.buildJson(myhomList));
		}
	}

	
	/**
	 * 前端课后详情(下一道题)
	 */
	@RequestMapping("/homeworkDetailsList")
	public void homeworkDetailsList(HttpServletResponse response, Integer paperId, Integer pageNo, Integer pageSize,
			Integer status, Integer studentId) throws Exception {
		// 获取学生的考试记录状态
		HomeworkRecord record = homeworkRecordService.getByStuIdandHomeId(studentId, paperId);
		if (record != null && record.getStatus() == 3) {
			output(response, JsonUtil.buildFalseJson("2", "对不起，当前作业已结束!"));
		} else {
			if (pageNo == null) {
				pageNo = 1;
			}
			if (pageSize == null) {
				pageSize = 1;
			}
			List pList = new ArrayList<>();
			List<QuestionSingleChoice> list1 = questionSingleChoiceService.theHomeworkById(paperId, 1, null, null);
			List<QuestionMulitChoice> list2 = questionMulitChoiceService.theHomeworkById(paperId, 2, null, null);
			List<QuestionJudge> list3 = questionJudgeService.theHomeworkById(paperId, 3, null, null);
			List<QuestionRead> list4 = questionReadService.theHomeworkById(paperId, 4, null, null);
			List<SubjectiveQuestion> list5 = questionService.theHomeworkById(paperId, 5, null, null);
			List<QuestionFill> list6 = fillservice.theHomeworkById(paperId, 6, null, null);
			List<QuestionCloze> list7 = clozeService.theHomeworkById(paperId, 7);
			int list1Size = list1.size();
			int list2Size = list2.size();
			int list3Size = list3.size();
			int list4Size = list4.size();
			int list5Size = list5.size();
			int list6Size = list6.size();
			int list7Size = list7.size();
			Map<String, Object> map = new HashMap<String, Object>();
			if (list1Size > 0 && pageNo <= list1Size) {// 第1个list中取数据
				list1 = list1.subList(pageNo - 1, pageNo);
				map.put("question", list1);
			} else if (list2Size > 0 && pageNo > list1Size && pageNo <= list1Size + list2Size) {// 第2个list中取数据
				pageNo = pageNo - list1Size;
				list2 = list2.subList(pageNo - 1, pageNo);
				map.put("question", list2);
			} else if (list3Size > 0 && pageNo > list2Size && pageNo <= list1Size + list2Size + list3Size) {// 第3个list中取数据
				pageNo = pageNo - list1Size - list2Size;
				list3 = list3.subList(pageNo - 1, pageNo);
				map.put("question", list3);
			} else if (list4Size > 0 && pageNo > list3Size && pageNo <= list1Size + list2Size + list3Size + list4Size) {// 第4个list中取数据
				pageNo = pageNo - list1Size - list2Size - list3Size;
				list4 = list4.subList(pageNo - 1, pageNo);
				map.put("question", list4);
				// 获得阅读的每道题目
				List<QuestionRead> childlist = questionReadService.getReadChildByParentId(list4.get(0).getReadId());
				map.put("read_detail", childlist);
			} else if (list5Size > 0 && pageNo > list4Size
					&& pageNo <= list1Size + list2Size + list3Size + list4Size + list5Size) {// 第5个list中取数据
				pageNo = pageNo - list1Size - list2Size - list3Size - list4Size;
				list5 = list5.subList(pageNo - 1, pageNo);
				map.put("question", list5);
			} else if (list6Size > 0 && pageNo > list5Size
					&& pageNo <= list1Size + list2Size + list3Size + list4Size + list5Size + list6Size) {// 第6个list中取数据
				pageNo = pageNo - list1Size - list2Size - list3Size - list4Size - list5Size;
				list6 = list6.subList(pageNo - 1, pageNo);
				map.put("question", list6);
			} else if (list7Size > 0 && pageNo > list6Size
					&& pageNo <= list1Size + list2Size + list3Size + list4Size + list5Size + list6Size + list7Size) {// 第7个list中取数据
				pageNo = pageNo - list1Size - list2Size - list3Size - list4Size - list5Size - list6Size;
				list7 = list7.subList(pageNo - 1, pageNo);
				map.put("question", list7);
				List<QuestionCloze> clozeList = clozeService.getQuestionClozeByParentId(list7.get(0).getClozeId());
				map.put("clozeList", clozeList);
			}
			pList.add(map);
			if (pList != null && !pList.isEmpty()) {
				if (map != null && !map.isEmpty()) {
					output(response, JsonUtil.buildJson(pList));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
				}
			} else {
				output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
			}
		}
	}

	/**
	 * 获取课后作业详情的题目总数
	 * 
	 * @param response
	 * @param paperId
	 */
	@RequestMapping("/homeworkQuestionCount")
	public void homeworkQuestionCount(HttpServletResponse response, int paperId) {
		int count = homeworkDetailsService.getTotalByPaperId(paperId);
		output(response, JsonUtil.buildFalseJson("0", String.valueOf(count)));
	}

	/**
	 * 课后作业总数
	 * 
	 * @param response
	 */
	@RequestMapping("/homeworkConut")
	public void homeworkConut(HttpServletResponse response, Integer studentId) {
		int conut = homeworkRecordService.getCountByStudentId(studentId);
		output(response, JsonUtil.buildFalseJson("0", String.valueOf(conut)));
	}

	/**
	 * 保存提交的课后作业题答案
	 * @param response
	 * @param homework
	 */
	@RequestMapping("/saveHomeworAnswer")
	public void saveHomeworAnswer(HttpServletResponse response, HomeworkAnswer ha) {
		HomeworkAnswer paAnswer = homeworkAnswerService.isdoPaperIdById(ha.getStudentId(), ha.getPaperId(),
				ha.getQuestionId(), ha.getQuestionType());
		HomeworkDetails hd = homeworkDetailsService.findHomeworkDetailsById(ha.getPaperId(), ha.getQuestionId(),
				ha.getQuestionType());
		Homework home = homeworkService.getHomeworkById(ha.getPaperId());
		// 拿到班级编号
		HomeworkRecord record = homeworkRecordService.getByStuIdandHomeId(ha.getStudentId(), ha.getPaperId());
		MyMistakes my = myMistakesService.findMyMistakesHomeworkById(ha.getStudentId(), null, ha.getQuestionId(),
				ha.getQuestionType(), null);

		int len = 0, len1 = 0;
		if (paAnswer != null) {// 做个这道题则修改
			paAnswer.setCreateTime(new Date());
			paAnswer.setValue(hd.getValue());
			if (record != null && record.getClassId() != null) {
				paAnswer.setClassId(record.getClassId());
			}
			paAnswer.setWriteAnswer(ha.getWriteAnswer());
			record.setUpdateTime(new Date());
			homeworkRecordService.updateHomeworkStatus(record);
			homeworkAnswerService.updateWriteAnswer(paAnswer);
			HomeworkAnswer hAnswer = homeworkAnswerService.getHomeworkAnswerById(paAnswer.getAnswerId());
			if (ha.getQuestionType() == 1) {
				QuestionSingleChoice qsc = questionSingleChoiceService.getSingleChoiceById(ha.getQuestionId());
				if (qsc.getAnswer().equals(hAnswer.getWriteAnswer())) {
					hAnswer.setIsTrue(1);
					homeworkAnswerService.updateIsTrue(hAnswer);
					if (my != null) {
						my.setUpdateTime(new Date());
						my.setStatus(1);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				} else {
					hAnswer.setIsTrue(0);
					len1 = homeworkAnswerService.updateIsTrue(hAnswer);
					if(my != null){
						my.setUpdateTime(new Date());
						my.setStatus(0);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				}
			} else if (ha.getQuestionType() == 2) {
				QuestionMulitChoice qmc = questionMulitChoiceService.getMulitChoiceById(hAnswer.getQuestionId());
				if (qmc.getAnswer1().equals(hAnswer.getWriteAnswer())) {
					hAnswer.setIsTrue(1);
					homeworkAnswerService.updateIsTrue(hAnswer);
					if (my != null) {
						my.setUpdateTime(new Date());
						my.setStatus(1);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				} else {
					hAnswer.setIsTrue(0);
					len1 = homeworkAnswerService.updateIsTrue(hAnswer);
					if(my != null){
						my.setUpdateTime(new Date());
						my.setStatus(0);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				}
			} else if (ha.getQuestionType() == 3) {
				QuestionJudge qj = questionJudgeService.getJudgeById(hAnswer.getQuestionId());
				if (qj.getAnswer2().equals(hAnswer.getWriteAnswer())) {
					hAnswer.setIsTrue(1);
					homeworkAnswerService.updateIsTrue(hAnswer);
					if (my != null) {
						my.setUpdateTime(new Date());
						my.setStatus(1);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				} else {
					hAnswer.setIsTrue(0);
					len1 = homeworkAnswerService.updateIsTrue(hAnswer);
					if(my != null){
						my.setUpdateTime(new Date());
						my.setStatus(0);
						myMistakesService.updateMyMistakesHomeworkById(my);
					}
				}
			} else if (ha.getQuestionType() == 6) {// 填空
				QuestionFill fill = fillservice.getQuestionFillById(ha.getQuestionId());
				String fillName = guoHtml(fill.getFillAnswer());
				String[] fillAnswer = fillName.split("##");
				String[] homeAnswer = ha.getWriteAnswer().split("##");
				if (fillAnswer.length > 1 && homeAnswer.length > 1) {// 有多个答案
					for (int i = 0; i < homeAnswer.length; i++) {
						for (int j = 0; j < fillAnswer.length; j++) {
							if (homeAnswer[i].equals(fillAnswer[j])) {
								hAnswer.setIsTrue(1);
								homeworkAnswerService.updateIsTrue(hAnswer);
								if (my != null) {
									my.setUpdateTime(new Date());
									my.setStatus(1);
									myMistakesService.updateMyMistakesHomeworkById(my);
								}
							} else {
								hAnswer.setIsTrue(0);
								homeworkAnswerService.updateIsTrue(hAnswer);
								if(my != null){
									my.setUpdateTime(new Date());
									my.setStatus(0);
									myMistakesService.updateMyMistakesHomeworkById(my);
								}
							}
						}
					}
				} else {// 只有一个答案
					if (ha.getWriteAnswer().equals(fillName)) {
						hAnswer.setIsTrue(1);
						homeworkAnswerService.updateIsTrue(hAnswer);
						if (my != null) {
							my.setUpdateTime(new Date());
							my.setStatus(1);
							myMistakesService.updateMyMistakesHomeworkById(my);
						}
					} else {
						hAnswer.setIsTrue(0);
						homeworkAnswerService.updateIsTrue(hAnswer);
						if(my != null){
							my.setUpdateTime(new Date());
							my.setStatus(0);
							myMistakesService.updateMyMistakesHomeworkById(my);
						}
					}
				}
			}
			output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
		} else {// 没做个则增加
			ha.setCreateTime(new Date());
			ha.setValue(hd.getValue());
			if (record != null && record.getClassId() != null) {
				ha.setClassId(record.getClassId());
			}
			homeworkAnswerService.saveHomewor(ha);
			HomeworkAnswer hAnswer = homeworkAnswerService.getHomeworkAnswerById(ha.getAnswerId());
			if (hAnswer == null) {
				output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
			} else {
				if (ha.getQuestionType() == 1) {
					QuestionSingleChoice qsc = questionSingleChoiceService.getSingleChoiceById(ha.getQuestionId());

					if (qsc.getAnswer().equals(hAnswer.getWriteAnswer())) {
						hAnswer.setIsTrue(1);
						homeworkAnswerService.updateIsTrue(hAnswer);
						// 做对修改作业记录表的得分
						if (record.getScore() != null) {
							record.setScore(record.getScore() + hAnswer.getValue());
						} else {
							record.setScore(hAnswer.getValue().doubleValue());
						}
						System.out.println("record.getscore:" + record.getScore());
					} else {
						if (my == null) {
							my = new MyMistakes();
							my.setTopicType(1);
							my.setStatus(0);
							my.setCreateTime(new Date());
							my.setHomeworkId(hAnswer.getPaperId());
							my.setStudentId(hAnswer.getStudentId());
							my.setQuestionId(hAnswer.getQuestionId());
							if (record.getClassId() != null) {
								my.setClassId(record.getClassId());
							}
							my.setValue(hd.getValue().doubleValue());
							my.setSubjectId(home.getSubject());
							myMistakesService.addPaperMistake(my);
						}
						hAnswer.setIsTrue(0);
						len1 = homeworkAnswerService.updateIsTrue(hAnswer);
					}
					if (record.getWriteNumber() != null) {
						record.setWriteNumber(record.getWriteNumber() + 1);
					} else {
						record.setWriteNumber(1);
					}
					if (record.getUpdateTime() != null) {
						if (record.getTotalTime() != null) {
							record.setTotlTime(record.getTotalTime()
									+ (int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
						} else {
							record.setTotlTime((int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
						}
					} else {
						record.setTotlTime((int) (new Date().getTime() - record.getCreateTime().getTime()) / 60000);
					}
					record.setUpdateTime(new Date());
					len = homeworkRecordService.updateScoreByStuIdandHomId(record);
				} else if (ha.getQuestionType() == 2) {
					QuestionMulitChoice qmc = questionMulitChoiceService.getMulitChoiceById(hAnswer.getQuestionId());
					if (qmc.getAnswer1().equals(hAnswer.getWriteAnswer())) {
						hAnswer.setIsTrue(1);
						homeworkAnswerService.updateIsTrue(hAnswer);
						// 做对修改作业记录表的得分
						if (record.getScore() != null) {
							record.setScore(record.getScore() + hAnswer.getValue());
						} else {
							record.setScore(hAnswer.getValue().doubleValue());
						}
					} else {
						if (my == null) {
							my = new MyMistakes();
							my.setTopicType(2);
							my.setStatus(0);
							my.setCreateTime(new Date());
							my.setHomeworkId(hAnswer.getPaperId());
							my.setStudentId(hAnswer.getStudentId());
							my.setQuestionId(hAnswer.getQuestionId());
							if (record.getClassId() != null) {
								my.setClassId(record.getClassId());
							}
							my.setValue(hd.getValue().doubleValue());
							my.setSubjectId(home.getSubject());
							myMistakesService.addPaperMistake(my);
						}
						hAnswer.setIsTrue(0);
						len1 = homeworkAnswerService.updateIsTrue(hAnswer);
					}
					if (record.getWriteNumber() != null) {
						record.setWriteNumber(record.getWriteNumber() + 1);
					} else {
						record.setWriteNumber(1);
					}
					if (record.getUpdateTime() != null) {
						if (record.getTotalTime() != null) {
							record.setTotlTime(record.getTotalTime()
									+ (int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
						} else {
							record.setTotlTime((int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
						}
					} else {
						record.setTotlTime((int) (new Date().getTime() - record.getCreateTime().getTime()) / 60000);
					}
					record.setUpdateTime(new Date());
					len = homeworkRecordService.updateScoreByStuIdandHomId(record);
				} else if (ha.getQuestionType() == 3) {
					QuestionJudge qj = questionJudgeService.getJudgeById(hAnswer.getQuestionId());

					if (qj.getAnswer2().equals(hAnswer.getWriteAnswer())) {
						hAnswer.setIsTrue(1);
						homeworkAnswerService.updateIsTrue(hAnswer);
						// 做对修改作业记录表的得分
						if (record.getScore() != null) {
							record.setScore(record.getScore() + hAnswer.getValue());
						} else {
							record.setScore(hAnswer.getValue().doubleValue());
						}
					} else {
						if (my == null) {
							my = new MyMistakes();
							my.setTopicType(3);
							my.setStatus(0);
							my.setCreateTime(new Date());
							my.setHomeworkId(hAnswer.getPaperId());
							my.setStudentId(hAnswer.getStudentId());
							my.setQuestionId(hAnswer.getQuestionId());
							if (record.getClassId() != null) {
								my.setClassId(record.getClassId());
							}
							my.setValue(hd.getValue().doubleValue());
							my.setSubjectId(home.getSubject());
							myMistakesService.addPaperMistake(my);
						}
						hAnswer.setIsTrue(0);
						len1 = homeworkAnswerService.updateIsTrue(hAnswer);
					}
					if (record.getWriteNumber() != null) {
						record.setWriteNumber(record.getWriteNumber() + 1);
					} else {
						record.setWriteNumber(1);
					}
					if (record.getUpdateTime() != null) {
						if (record.getTotalTime() != null) {
							record.setTotlTime(record.getTotalTime()
									+ (int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
						} else {
							record.setTotlTime((int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
						}
					} else {
						record.setTotlTime((int) (new Date().getTime() - record.getCreateTime().getTime()) / 60000);
					}
					record.setUpdateTime(new Date());
					len = homeworkRecordService.updateScoreByStuIdandHomId(record);
				} else if (ha.getQuestionType() == 6) {// 填空
					QuestionFill fill = fillservice.getQuestionFillById(ha.getQuestionId());
					String fillName = guoHtml(fill.getFillAnswer());
					String[] fillAnswer = fillName.split("##");
					String[] homeAnswer = ha.getWriteAnswer().split("##");
					if (fillAnswer.length > 1 && homeAnswer.length > 1) {// 有多个答案
						for (int i = 0; i < homeAnswer.length; i++) {
							for (int j = 0; j < fillAnswer.length; j++) {
								if (homeAnswer[i].equals(fillAnswer[j])) {
									hAnswer.setIsTrue(1);
									homeworkAnswerService.updateIsTrue(hAnswer);
								} else {
									if (my == null) {
										my = new MyMistakes();
										my.setTopicType(6);
										my.setStatus(0);
										my.setCreateTime(new Date());
										my.setHomeworkId(hAnswer.getPaperId());
										my.setStudentId(hAnswer.getStudentId());
										my.setQuestionId(hAnswer.getQuestionId());
										if (record.getClassId() != null) {
											my.setClassId(record.getClassId());
										}
										my.setValue(hd.getValue().doubleValue());
										my.setSubjectId(home.getSubject());
										myMistakesService.addPaperMistake(my);
									}
									hAnswer.setIsTrue(0);
									homeworkAnswerService.updateIsTrue(hAnswer);
								}
							}
						}
					} else {// 只有一个答案
						if (ha.getWriteAnswer().equals(fillName)) {
							hAnswer.setIsTrue(1);
							homeworkAnswerService.updateIsTrue(hAnswer);
						} else {
							if (my == null) {
								my = new MyMistakes();
								my.setTopicType(6);
								my.setStatus(0);
								my.setCreateTime(new Date());
								my.setHomeworkId(hAnswer.getPaperId());
								my.setStudentId(hAnswer.getStudentId());
								my.setQuestionId(hAnswer.getQuestionId());
								if (record.getClassId() != null) {
									my.setClassId(record.getClassId());
								}
								my.setValue(hd.getValue().doubleValue());
								my.setSubjectId(home.getSubject());
								myMistakesService.addPaperMistake(my);
							}
							hAnswer.setIsTrue(0);
							homeworkAnswerService.updateIsTrue(hAnswer);
						}
					}
					if (record.getWriteNumber() != null) {
						record.setWriteNumber(record.getWriteNumber() + 1);
					} else {
						record.setWriteNumber(1);
					}
					if (record.getUpdateTime() != null) {
						if (record.getTotalTime() != null) {
							record.setTotlTime(record.getTotalTime()
									+ (int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
						} else {
							record.setTotlTime((int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
						}
					} else {
						record.setTotlTime((int) (new Date().getTime() - record.getCreateTime().getTime()) / 60000);
					}
					record.setUpdateTime(new Date());
					len = homeworkRecordService.updateScoreByStuIdandHomId(record);
				}
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
			}
		}
	}

	//作业主观题加一
	@RequestMapping("/addSubjective")
	public void addSubjective(HttpServletResponse response, Integer studentId, Integer paperId) {
		// 拿到班级编号
		HomeworkRecord record = homeworkRecordService.getByStuIdandHomeId(studentId, paperId);
		if (record.getWriteNumber() != null) {
			record.setWriteNumber(record.getWriteNumber() + 1);
		} else {
			record.setWriteNumber(1);
		}
		record.setUpdateTime(new Date());
		try {
			homeworkRecordService.updateWriteNumber(record);
			output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
		} catch (Exception e) {
			output(response, JsonUtil.buildFalseJson("1", "保存失败!"));
		}
	}

	// 保存完形填空
	@RequestMapping("/addQuestionCloze")
	public void addQuestionCloze(HttpServletResponse response, Integer studentId, Integer paperId, String locations,
			Integer questionId) {
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(locations);
		JsonArray jsonArray = null;
		if (element.isJsonArray()) {
			jsonArray = element.getAsJsonArray();
		}
		HomeworkRecord record = homeworkRecordService.getByStuIdandHomeId(studentId, paperId);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject job = (JsonObject) jsonArray.get(i);
			HomeworkDetails hdetails = homeworkDetailsService.findHomeworkDetailsById(paperId, questionId, 7);
			HomeworkAnswer ha = homeworkAnswerService.isdoPaperIdById(studentId, paperId, job.get("tid").getAsInt(), 7);
			Homework home = homeworkService.getHomeworkById(paperId);
			List<QuestionCloze> clozes = clozeService.getQuestionClozeByParentId(hdetails.getQuestionId());
			if (ha == null) {
				ha = new HomeworkAnswer();
				ha.setCreateTime(new Date());
				ha.setStudentId(studentId);
				ha.setQuestionType(7);
				if (record != null && record.getClassId() != null) {
					ha.setClassId(record.getClassId());
				}
				ha.setPaperId(paperId);
				ha.setValue(hdetails.getValue() / clozes.size());
				ha.setQuestionId(job.get("tid").getAsInt());
				ha.setWriteAnswer(job.get("answer").getAsString());
				homeworkAnswerService.saveHomewor(ha);
				HomeworkAnswer hAnswer = homeworkAnswerService.getHomeworkAnswerById(ha.getAnswerId());
				if (hAnswer == null) {
					output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
				} else {
					QuestionCloze cloze = clozeService.getQuestionClozeById(hAnswer.getQuestionId());
					MyMistakes my = myMistakesService.findMyMistakesHomeworkById(studentId, null,
							job.get("tid").getAsInt(), 7, null);
					if (cloze.getClozeAnswer().equals(hAnswer.getWriteAnswer())) {
						hAnswer.setIsTrue(1);
						homeworkAnswerService.updateIsTrue(hAnswer);
						// 做对修改作业记录表的得分
						if (record.getScore() != null) {
							record.setScore(record.getScore() + ha.getValue());
						} else {
							record.setScore(ha.getValue().doubleValue());
						}
					} else {
						if (my == null) {
							my = new MyMistakes();
							my.setCreateTime(new Date());
							my.setHomeworkId(paperId);
							my.setTopicType(7);
							my.setStudentId(hAnswer.getStudentId());
							my.setQuestionId(hAnswer.getQuestionId());
							if (record.getClassId() != null) {
								my.setClassId(record.getClassId());
							}
							my.setValue(hAnswer.getValue().doubleValue());
							my.setTopicType(7);
							my.setStatus(0);
							my.setSubjectId(home.getSubject());
							myMistakesService.addHomeworkMistake(my);// 返回主键
						}
						hAnswer.setIsTrue(0);
						homeworkAnswerService.updateIsTrue(hAnswer);
					}
				}
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
			} else {
				ha.setValue(hdetails.getValue());
				ha.setQuestionId(job.get("tid").getAsInt());
				ha.setWriteAnswer(job.get("answer").getAsString());
				ha.setCreateTime(new Date());
				if (record != null && record.getClassId() != null) {
					ha.setClassId(record.getClassId());
				}
				homeworkAnswerService.updateWriteAnswer(ha);
				HomeworkAnswer hAnswer = homeworkAnswerService.getHomeworkAnswerById(ha.getAnswerId());
				if (hAnswer == null) {
					output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
				} else {
					MyMistakes mistakes = myMistakesService.findMyMistakesHomeworkById(studentId, paperId,
							job.get("tid").getAsInt(), 7, null);
					QuestionCloze cloze = clozeService.getQuestionClozeById(hAnswer.getQuestionId());
					if (cloze.getClozeAnswer().equals(hAnswer.getWriteAnswer())) {
						hAnswer.setIsTrue(1);
						if (mistakes != null) {
							mistakes.setUpdateTime(new Date());
							mistakes.setStatus(1);
							myMistakesService.updateMyMistakesHomeworkById(mistakes);
						}
						homeworkAnswerService.updateIsTrue(hAnswer);
					} else {
						hAnswer.setIsTrue(0);
						try {
							homeworkAnswerService.updateIsTrue(hAnswer);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(mistakes != null){
							mistakes.setUpdateTime(new Date());
							mistakes.setStatus(0);
							myMistakesService.updateMyMistakesHomeworkById(mistakes);
						}
					}
				}
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
			}
		}
		if (record.getWriteNumber() != null) {
			record.setWriteNumber(record.getWriteNumber() + 1);
		} else {
			record.setWriteNumber(1);
		}
		if (record.getUpdateTime() != null) {
			if (record.getTotalTime() != null) {
				record.setTotlTime(record.getTotalTime()
						+ (int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
			} else {
				record.setTotlTime((int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
			}
		} else {
			record.setTotlTime((int) (new Date().getTime() - record.getCreateTime().getTime()) / 60000);
		}
		record.setUpdateTime(new Date());
		homeworkRecordService.updateScoreByStuIdandHomId(record);
	}

	//保存阅读理解答案
	@RequestMapping("/saveQuestionRead")
	public void saveQuestionRead(HttpServletResponse response, Integer studentId, Integer paperId, String locations,
			Integer questionId) {
		JsonParser jsParser = new JsonParser();
		JsonElement jsElement = jsParser.parse(locations);
		JsonArray jsonArray = null;
		int len = 0, len1 = 0;
		if (jsElement.isJsonArray()) {
			jsonArray = jsElement.getAsJsonArray();
		}
		HomeworkRecord record = homeworkRecordService.getByStuIdandHomeId(studentId, paperId);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject job = (JsonObject) jsonArray.get(i);
			HomeworkDetails hdetails = homeworkDetailsService.findHomeworkDetailsById(paperId, questionId, 4);
			List<QuestionRead> qrList = questionReadService.getReadChildByParentId(hdetails.getQuestionId());
			HomeworkAnswer ha = homeworkAnswerService.isdoPaperIdById(studentId, paperId, job.get("tid").getAsInt(), 4);
			// PaperInfo pInfo = paperInfoService.getPiIdById(paperId);
			Homework home = homeworkService.getHomeworkById(paperId);
			if (ha == null) {
				ha = new HomeworkAnswer();
				ha.setCreateTime(new Date());
				ha.setStudentId(studentId);
				ha.setQuestionType(4);
				if (record != null && record.getClassId() != null) {
					ha.setClassId(record.getClassId());
				}
				ha.setPaperId(paperId);
				ha.setValue(hdetails.getValue() / qrList.size());
				ha.setQuestionId(job.get("tid").getAsInt());
				ha.setWriteAnswer(job.get("answer").getAsString());
				homeworkAnswerService.saveHomewor(ha);
				HomeworkAnswer hAnswer = homeworkAnswerService.getHomeworkAnswerById(ha.getAnswerId());
				// 拿到班级编号
				// HomeworkRecord record =
				// homeworkRecordService.getByStuIdandHomeId(ha.getStudentId(),
				// ha.getPaperId());
				if (hAnswer == null) {
					output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
				} else {
					QuestionRead qr = questionReadService.getReadById(hAnswer.getQuestionId());
					MyMistakes my = myMistakesService.findMyMistakesHomeworkById(studentId, null,
							job.get("tid").getAsInt(), 4, null);
					if (qr.getAnswer_read().equals(hAnswer.getWriteAnswer())) {
						hAnswer.setIsTrue(1);
						homeworkAnswerService.updateIsTrue(hAnswer);
						// 做对修改作业记录表的得分
						if (record.getScore() != null) {
							record.setScore(record.getScore() + ha.getValue());
						} else {
							record.setScore(ha.getValue().doubleValue());
						}
					} else {
						if (my == null) {
							my = new MyMistakes();
							my.setCreateTime(new Date());
							my.setHomeworkId(paperId);
							my.setTopicType(4);
							my.setStudentId(hAnswer.getStudentId());
							my.setQuestionId(hAnswer.getQuestionId());
							if (record.getClassId() != null) {
								my.setClassId(record.getClassId());
							}
							my.setValue(hAnswer.getValue().doubleValue());
							my.setTopicType(4);
							my.setStatus(0);
							my.setSubjectId(home.getSubject());
							myMistakesService.addHomeworkMistake(my);// 返回主键
						}
						hAnswer.setIsTrue(0);
						len1 = homeworkAnswerService.updateIsTrue(hAnswer);
					}
				}
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
			} else {
				ha.setValue(hdetails.getValue());
				ha.setQuestionId(job.get("tid").getAsInt());
				ha.setWriteAnswer(job.get("answer").getAsString());
				ha.setCreateTime(new Date());
				if (record != null && record.getClassId() != null) {
					ha.setClassId(record.getClassId());
				}
				homeworkAnswerService.updateWriteAnswer(ha);
				HomeworkAnswer hAnswer = homeworkAnswerService.getHomeworkAnswerById(ha.getAnswerId());
				if (hAnswer == null) {
					output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
				} else {
					MyMistakes mistakes = myMistakesService.findMyMistakesHomeworkById(studentId, paperId,
							job.get("tid").getAsInt(), 4, null);
					QuestionRead qr = questionReadService.getReadById(hAnswer.getQuestionId());
					if (qr.getAnswer_read().equals(hAnswer.getWriteAnswer())) {
						hAnswer.setIsTrue(1);
						if (mistakes != null) {
							mistakes.setUpdateTime(new Date());
							mistakes.setStatus(1);
							myMistakesService.updateMyMistakesHomeworkById(mistakes);
						}
						homeworkAnswerService.updateIsTrue(hAnswer);
					} else {
						hAnswer.setIsTrue(0);
						len1 = homeworkAnswerService.updateIsTrue(hAnswer);
						if(mistakes != null){
							mistakes.setUpdateTime(new Date());
							mistakes.setStatus(0);
							myMistakesService.updateMyMistakesHomeworkById(mistakes);
						}
					}
				}
				output(response, JsonUtil.buildFalseJson("0", "保存成功!"));
			}
		}
		if (record.getWriteNumber() != null) {
			record.setWriteNumber(record.getWriteNumber() + 1);
		} else {
			record.setWriteNumber(1);
		}
		if (record.getUpdateTime() != null) {
			if (record.getTotalTime() != null) {
				record.setTotlTime(record.getTotalTime()
						+ (int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
			} else {
				record.setTotlTime((int) (new Date().getTime() - record.getUpdateTime().getTime()) / 60000);
			}
		} else {
			record.setTotlTime((int) (new Date().getTime() - record.getCreateTime().getTime()) / 60000);
		}
		record.setUpdateTime(new Date());
		len = homeworkRecordService.updateScoreByStuIdandHomId(record);
	}

	// 点击提交按钮，生成考试分、返回错题总数、答对总数
	@RequestMapping("/saveHomework")
	public void saveHomework(HttpServletResponse response, Integer studentId, Integer paperId) {
		List<HomeworkAnswer> haList = homeworkAnswerService.findHomeworkAnswerById(studentId, paperId);
		Homework homework = homeworkService.getHomework(paperId);
		HomeworkRecord record = homeworkRecordService.getByStuIdandHomeId(studentId, paperId);
		if ((haList == null || haList.isEmpty()) || (record == null)) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		} else {
			// 修改考卷的状态
			record.setUpdateTime(new Date());
			record.setStatus(3);
			record.setWriteNumber(homework.getHkTotal());
			int len = homeworkRecordService.updateHomeworkStatus(record);
			if (len < 0) {
				output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
			} else {
				HomeworkRecord record2 = homeworkRecordService.getByStuIdandHomeId(studentId, paperId);
				// 做完作业清掉缓存
				mapcahe.removeCaseMap("statusHomeBy" + studentId + "and" + paperId);
				mapcahe.removeMap("pageNoHome" + studentId + "and" + paperId);
				mapcahe.removeMap("statusHome" + studentId + "and" + paperId);
				mapcahe.removeMap(studentId + "statehome");
				List<HomeworkRecord> list = new ArrayList<>();
				list.add(record2);
				output(response, JsonUtil.buildJson(list));
				System.out.println("结果：" + JsonUtil.buildJson(list));
			}
		}
	}

	//查看作业详情（已做完）
	@RequestMapping("/seeHomeworkDetails")
	public void seeHomeworkDetails(HttpServletResponse response, Integer studentId, Integer paperId, Integer pageNo,
			Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 100;
		}
		List<SeePaper> spList = new ArrayList<SeePaper>();
		List<QuestionSingleChoice> list1 = questionSingleChoiceService.theHomeworkAnswerById(studentId, paperId, 1,
				(pageNo - 1) * pageSize, pageSize);
		for (QuestionSingleChoice qsc : list1) {
			SeePaper sp = new SeePaper();
			sp.setQuestionId(qsc.getChoiceId());
			sp.setAnswer(qsc.getAnswer());
			sp.setAnswerDesc(qsc.getAnswerDesc());
			sp.setOptionA(qsc.getOptionA());
			sp.setOptionB(qsc.getOptionB());
			sp.setOptionC(qsc.getOptionC());
			sp.setOptionD(qsc.getOptionD());
			sp.setQuestiontype(qsc.getQuestionsType().getTopicId());
			sp.setQuestionName(qsc.getQuestionsType().getTopicName());
			sp.setChoiceDesc(qsc.getChoiceDesc());
			sp.setIsTrue(qsc.getHomeworkanswer().getIsTrue());
			sp.setWriteAnswer(qsc.getHomeworkanswer().getWriteAnswer());
			spList.add(sp);
		}
		List<QuestionMulitChoice> list2 = questionMulitChoiceService.theHomeworkAnswerById(studentId, paperId, 2,
				(pageNo - 1) * pageSize, pageSize);
		for (QuestionMulitChoice qmc : list2) {
			SeePaper sp1 = new SeePaper();
			sp1.setQuestionId(qmc.getChoiceId());
			sp1.setOptionA1(qmc.getOptionA1());
			sp1.setOptionB1(qmc.getOptionB1());
			sp1.setOptionC1(qmc.getOptionC1());
			sp1.setOptionD1(qmc.getOptionD1());
			sp1.setQuestiontype(qmc.getQuestionsType().getTopicId());
			sp1.setQuestionName(qmc.getQuestionsType().getTopicName());
			sp1.setChoiceDesc1(qmc.getChoiceDesc1());
			sp1.setAnswer1(qmc.getAnswer1());
			sp1.setAnswerDesc1(qmc.getAnswerDesc1());
			sp1.setIsTrue(qmc.getHomeworkanswer().getIsTrue());
			sp1.setWriteAnswer(qmc.getHomeworkanswer().getWriteAnswer());
			spList.add(sp1);
		}
		List<QuestionJudge> list3 = questionJudgeService.theHomeworkAnswerById(studentId, paperId, 3,
				(pageNo - 1) * pageSize, pageSize);
		for (QuestionJudge qj : list3) {
			SeePaper sp2 = new SeePaper();
			sp2.setQuestionId(qj.getJudgeId());
			sp2.setQuestiontype(qj.getQuestionsType().getTopicId());
			sp2.setJudgeDesc(qj.getJudgeDesc());
			sp2.setAnswer2(qj.getAnswer2());
			sp2.setQuestionName(qj.getQuestionsType().getTopicName());
			sp2.setAnswerDesc2(qj.getAnswerDesc2());
			sp2.setIsTrue(qj.getHomeworkanswer().getIsTrue());
			sp2.setWriteAnswer(qj.getHomeworkanswer().getWriteAnswer());
			spList.add(sp2);
		}
		// 先拿到阅读理解大标题
		List<QuestionRead> list4 = questionReadService.theHomeworkDetailsById(paperId, 4, (pageNo - 1) * pageSize,
				pageSize);
		for (QuestionRead qr : list4) {
			SeePaper sp3 = new SeePaper();
			sp3.setQuestionId(qr.getReadId());
			sp3.setAnswer_read(qr.getAnswer_read());
			sp3.setOptionA_read(qr.getOptionA_read());
			sp3.setOptionB_read(qr.getOptionB_read());
			sp3.setOptionC_read(qr.getOptionC_read());
			sp3.setOptionD_read(qr.getOptionD_read());
			// 根据试卷编号查找所有的阅读理解大标题
			sp3.setReadDesc(qr.getReadDesc());
			// List<QuestionRead> list5 = questionReadService.getReadChildByParentIdPaer(list4.get(0).getReadId());
			List<QuestionRead> qrList1 = questionReadService.getPaper_answerDetails(qr.getReadId(), paperId, studentId);
			// 根据大标题的查找所有的子标题
			List<QuestionPaer> qrList = new ArrayList<QuestionPaer>();
			// 循环拿到所有的子标题
			for (QuestionRead qt : qrList1) {
				QuestionPaer qrQuestionRead = new QuestionPaer();
				qrQuestionRead.setReadId(qt.getReadId());
				qrQuestionRead.setIsTrue(qt.getHomeworkanswer().getIsTrue());
				qrQuestionRead.setWriteAnswer(qt.getHomeworkanswer().getWriteAnswer());
				qrQuestionRead.setOptiontitle(qt.getOptiontitle());
				qrQuestionRead.setOptionA_read(qt.getOptionA_read());
				qrQuestionRead.setOptionB_read(qt.getOptionB_read());
				qrQuestionRead.setOptionC_read(qt.getOptionC_read());
				qrQuestionRead.setOptionD_read(qt.getOptionD_read());
				qrQuestionRead.setAnswer_read(qt.getAnswer_read());
				qrQuestionRead.setAnswerDesc_read(qt.getAnswerDesc_read());
				qrList.add(qrQuestionRead);
			}
			sp3.setQuestionRead(qrList);
			sp3.setQuestiontype(qr.getQuestionsType().getTopicId());
			sp3.setQuestionName(qr.getQuestionsType().getTopicName());
			spList.add(sp3);
		}
		// 拿到主观题
		List<SubjectiveQuestion> list5 = questionService.theHomeworkAnswerById(studentId, paperId, 5,
				(pageNo - 1) * pageSize, pageSize);
		for (SubjectiveQuestion sq : list5) {
			SeePaper sp4 = new SeePaper();
			sp4.setQuestionId(sq.getSubjectiveId());
			sp4.setQuestiontype(sq.getQuestionsType().getTopicId());
			sp4.setSubjectiveTitle(sq.getSubjectiveTitle());
			sp4.setAnswerAnalysis(sq.getAnswerAnalysis());
			sp4.setQuestionName(sq.getQuestionsType().getTopicName());
			spList.add(sp4);
		}
		// 拿到填空题
		List<QuestionFill> fillList = fillservice.theHomeworkAnswerById(studentId, paperId, 6, (pageNo - 1) * pageSize,
				pageSize);
		for (QuestionFill fill : fillList) {
			SeePaper sp5 = new SeePaper();
			sp5.setQuestionId(fill.getFillId());
			sp5.setQuestiontype(fill.getQuestionsType().getTopicId());
			sp5.setFillTitle(fill.getFillTitle());
			sp5.setFillAnswer(fill.getFillAnswer());
			sp5.setIsTrue(fill.getHomeworkanswer().getIsTrue());
			sp5.setTypeName(fill.getFillType().getTypeName());
			sp5.setWriteAnswer(fill.getHomeworkanswer().getWriteAnswer());
			sp5.setAnswerAnalysis(fill.getAnswerAnalysis());
			sp5.setQuestionName(fill.getQuestionsType().getTopicName());
			spList.add(sp5);
		}
		// 完形填空内容
		List<QuestionCloze> clozeList = clozeService.theHomeworkDetailsById(paperId, 7);
		if (clozeList != null && !clozeList.isEmpty()) {
			for (QuestionCloze cloze : clozeList) {
				SeePaper sp6 = new SeePaper();
				sp6.setQuestionId(cloze.getClozeId());
				sp6.setClozeContent(cloze.getClozeContent());
				// 拿到所有选项及答案
				List<QuestionCloze> clozes = clozeService.findHomeworkAnswerById(studentId, paperId,
						cloze.getClozeId());
				if (!clozes.isEmpty() && clozes != null) {
					List<QuestionPaer> qrList = new ArrayList<QuestionPaer>();
					for (QuestionCloze qt : clozes) {
						QuestionPaer qrQuestionRead = new QuestionPaer();
						qrQuestionRead.setReadId(qt.getClozeId());
						qrQuestionRead.setIsTrue(qt.getPaperAnswer().getIsTrue());
						qrQuestionRead.setWriteAnswer(qt.getPaperAnswer().getWriteAnswer());
						qrQuestionRead.setOptionA_read(qt.getClozeOptionA());
						qrQuestionRead.setOptionB_read(qt.getClozeOptionB());
						qrQuestionRead.setOptionC_read(qt.getClozeOptionC());
						qrQuestionRead.setOptionD_read(qt.getClozeOptionD());
						qrQuestionRead.setAnswer_read(qt.getClozeAnswer());
						qrQuestionRead.setAnswerDesc_read(qt.getAnalysis());
						qrList.add(qrQuestionRead);
					}
					sp6.setQuestionRead(qrList);
					sp6.setQuestiontype(cloze.getQuestionsType().getTopicId());
					sp6.setQuestionName(cloze.getQuestionsType().getTopicName());
				} else {
					sp6.setWriteAnswer("未提交");
				}
				spList.add(sp6);
			}

		}
		if (spList != null && !spList.isEmpty()) {
			output(response, JsonUtil.buildJson(spList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	 //	课后作业总数
	@RequestMapping("homeworkTotal")
	public void homeworkTotal(HttpServletResponse response, Integer studentId) {
		int totalCount = homeworkRecordService.gethomeworkTotalCount(studentId);
		output(response, JsonUtil.buildFalseJson("0", String.valueOf(totalCount)));
	}

	// 过滤标签
	public static String guoHtml(String string) {
		if (!string.equals("") && string != null) {
			/*
			 * String str = string.replaceAll("<[.[^<]]*>", ""); str =
			 * str.replaceAll("\\s*|\t|\r|\n", "");
			 */
			String content = string.replaceAll("</?[^<]+>", "");
			// 去除字符串中的空格 回车 换行符 制表符 等
			content = content.replaceAll("\\s*|\t|\r|\n", "");
			// 去除空格
			content = content.replaceAll("&nbsp;", "");
			// 去掉其他一些字符
			// content = content.replaceAll("\\", "");
			return content;
		} else {
			return string;
		}
	}
}
