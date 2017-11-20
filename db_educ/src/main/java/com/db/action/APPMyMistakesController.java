package com.db.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.MyMistakes;
import com.db.entity.PaperAnswer;
import com.db.entity.QuestionRead;
import com.db.entity.utilentity.CountStudentScore;
import com.db.entity.utilentity.QuestionType;
import com.db.service.MyMistakesService;
import com.db.service.PaperAnswerService;
import com.db.service.QuestionJudgeService;
import com.db.service.QuestionMulitChoiceService;
import com.db.service.QuestionReadService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/app/myMistakes")
public class APPMyMistakesController extends BaseUtil {

	@Resource
	private MyMistakesService mistakesService;
	@Resource
	private QuestionMulitChoiceService qMulitChoiceService;
	@Resource
	private QuestionJudgeService qJudgeService;
	@Resource
	private QuestionReadService qReadService;
	@Resource
	private PaperAnswerService answerService;

	private Logger log = Logger.getLogger(ExamController.class);
	/**
	 * 我的错题列表
	 * 
	 * @param response
	 */
	@RequestMapping("/myMistakesList")
	public void myMistakesList(HttpServletResponse response, Integer studentId, int pageNo, int pageSize) {
		List mList = mistakesService.myMistakesList(studentId, (pageNo - 1) * pageSize, pageSize);
		if (mList != null && !mList.isEmpty()) {
			output(response, JsonUtil.buildJson(mList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

//	/**
//	 * 错题类型详情
//	 * @param response
//	 * @param paperId
//	 * @param pageNo
//	 * @param pageSize
//	 * @param type
//	 */
//	@RequestMapping("/kesListdetails")
//	public void kesListdetails(HttpServletResponse response,Integer studentId,Integer pageNo, Integer pageSize,
//			int type) {
//		if (pageNo == null) {
//			pageNo = 1;
//		}
//		if (pageSize == null) {
//			pageSize = 5;
//		}
//		List kliList = null;
//		if (type == 1) {
//			kliList = mistakesService.kesListdetails(studentId,(pageNo - 1) * pageSize, pageSize);
//		} else if (type == 2) {
//			kliList = qMulitChoiceService.kesListdetails(studentId, (pageNo - 1) * pageSize, pageSize);
//		} else if (type == 3) {
//             kliList = qJudgeService.kesListdetails(studentId, (pageNo - 1) * pageSize, pageSize);
//		} else {
//			 kliList = qReadService.kesListdetails(studentId, (pageNo - 1) * pageSize, pageSize);
//		}
//		if (kliList != null && !kliList.isEmpty()) {
//			output(response, JsonUtil.buildJson(kliList));
//		} else {
//			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
//		}
//	}
	
//	/**
//	 * 返回错题总数
//	 * @param response
//	 * @param studentId
//	 * @param topicType
//	 */
//	@RequestMapping("/questionsTotai")
//	public void questionsTotai(HttpServletResponse response,Integer studentId,Integer topicType){
//		int count = mistakesService.findMyMistakesByIdType(studentId,topicType);
//		output(response, JsonUtil.buildFalseJson("0", String.valueOf(count)));
//	}
	/**
	 * 获取某个学生所在班级的答题情况
	 */
	@RequestMapping("/getStudentScore")
	public void getStudentScore(Integer studentId,Integer paperId,HttpServletResponse response) {
		log.info("传来的学生编号："+studentId);
		log.info("传来的试卷编号："+paperId);
		CountStudentScore paperScore = new CountStudentScore();
		paperScore.setSroceType(1);//试卷总分那一行的数据
		CountStudentScore myScore = new CountStudentScore();
		myScore.setSroceType(2);//当前用户所得分数那一行的数据
		CountStudentScore persentScore = new CountStudentScore();
		persentScore.setSroceType(3);//得分率那一行的数据
		QuestionType paperQtype = new QuestionType();
		List<QuestionType> pqList1 = new ArrayList<QuestionType>();//试卷分
		QuestionType userQtype = new QuestionType();
		List<QuestionType> pqList2 = new ArrayList<QuestionType>();//得分
		QuestionType persentQtype = new QuestionType();
		List<QuestionType> pqList3 = new ArrayList<QuestionType>();//得分率
		
		//拿到做过的试卷总分
		List<PaperAnswer> paList = answerService.findPaperAnswerBy(studentId, paperId);
//		int allscore = mistakesService.getMyAllScoreBystuId(studentId, paperId);
		int singlescore = 0;
		int singleright = 0;
		int mulitscore = 0;
		int mulitright = 0;
		int judgescore = 0;
		int judgeright = 0;
		int readscore = 0;
		int readright = 0;
		for(PaperAnswer answer:paList){
			if(answer.getQuestionType()==1){
				singlescore += answer.getValue();
				if(answer.getIsTrue()==1){
					singleright+=answer.getValue();
				}
			}else if(answer.getQuestionType()==2){
				mulitscore += answer.getValue();
				if(answer.getIsTrue()==1){
					mulitright+=answer.getValue();
				}
			}else if(answer.getQuestionType()==3){
				judgescore+= answer.getValue();
				if(answer.getIsTrue()==1){
					judgeright+=answer.getValue();
				}
			}else if(answer.getQuestionType()==4){
				readscore+=answer.getValue();
				if(answer.getIsTrue()==1){
					readright+=answer.getValue();
				}
			}
		}
//		//拿到做过的单选题的分数
//		
//		int singlescore = mistakesService.getMyScoreBystuId(null,paperId, 1);
//		//拿到做过的单选题的答对得分
//		int singleright = mistakesService.getMyRightQuestionBystuId(studentId, paperId, 1);
//		//拿到做过的多选题的分数
//		int mulitscore = mistakesService.getMyScoreBystuId(null, paperId, 2);
//		//拿到做过的多选题答对得分
//		int mulitright = mistakesService.getMyRightQuestionBystuId(studentId, paperId, 2);
//		//拿到做过的判断题的分数
//		int judgescore = mistakesService.getMyScoreBystuId(null, paperId, 3);
//		//拿到做过的判断题答对得分
//		int judgeright = mistakesService.getMyRightQuestionBystuId(studentId, paperId, 3);
//		//拿到做过的阅读理解题的分数
//		int readscore = mistakesService.getMyScoreBystuId(null, paperId, 4);
//		//拿到做过的阅读题答对得分
//		int readright = mistakesService.getMyRightQuestionBystuId(studentId, paperId, 4);

		int myAllScore = singleright+mulitright+judgeright+readright;
		int paperAllScore = singlescore+mulitscore+judgescore+readscore;
		paperQtype.setType1(String.valueOf(singlescore));
		paperQtype.setType2(String.valueOf(mulitscore));
		paperQtype.setType3(String.valueOf(judgescore));
		paperQtype.setType4(String.valueOf(readscore));
		paperQtype.setScoreCount(String.valueOf(paperAllScore));
		paperScore.setqTypeList(pqList1);
		pqList1.add(paperQtype);
		userQtype.setType1(String.valueOf(singleright));
		userQtype.setType2(String.valueOf(mulitright));
		userQtype.setType3(String.valueOf(judgeright));
		userQtype.setType4(String.valueOf(readright));
		userQtype.setScoreCount(String.valueOf(myAllScore));
		pqList2.add(userQtype);
		myScore.setqTypeList(pqList2);
		if(singlescore==0){
			persentQtype.setType1("0%");
		}else{
			persentQtype.setType1(String.valueOf(singleright*100/singlescore)+"%");
		}
		if(mulitscore==0){
			persentQtype.setType2("0%");
		}else{
			persentQtype.setType2(String.valueOf(mulitright*100/mulitscore)+"%");
		}
		if(judgescore==0){
			persentQtype.setType3("0%");
		}else{
			persentQtype.setType3(String.valueOf(judgeright*100/judgescore)+"%");
		}
		if(readscore==0){
			persentQtype.setType4("0%");
		}else{
			persentQtype.setType4(String.valueOf(readright*100/readscore)+"%");
		}
		if(singlescore+mulitscore+judgescore+readscore==0){
			persentQtype.setScoreCount("0");
		}else{
			persentQtype.setScoreCount(String.valueOf(myAllScore*100/paperAllScore)+"%");
			log.info(persentQtype.getScoreCount());
		}
		pqList3.add(persentQtype);
		persentScore.setqTypeList(pqList3);
		List<CountStudentScore> scoreCountList = new ArrayList<CountStudentScore>();
		scoreCountList.add(paperScore);
		scoreCountList.add(myScore);
		scoreCountList.add(persentScore);
//		List list = new ArrayList<>();
//		int one_score = singleright+mulitright+judgeright+readright;
//		HashMap<String, Object> map1 = new HashMap<String,Object>();
//		map1.put("score", singlescore);
//		map1.put("right", singleright);
//		map1.put("one_score", one_score);
//		map1.put("type", "单选");
//		list.add(map1);
//		HashMap<String, Object> map2 = new HashMap<String,Object>();
//		map2.put("score", mulitscore);
//		map2.put("right", mulitright);
//		map2.put("one_score", one_score);
//		map2.put("type", "多选");
//		list.add(map2);
//		HashMap<String, Object> map3 = new HashMap<String,Object>();
//		map3.put("score", judgescore);
//		map3.put("right", judgeright);
//		map3.put("one_score", one_score);
//		map3.put("type", "判断");
//		list.add(map3);
//		HashMap<String, Object> map4 = new HashMap<String,Object>();
//		map4.put("score", readscore);
//		map4.put("right", readright);
//		map4.put("one_score", one_score);
//		map4.put("type", "阅读");
//		list.add(map4);
//		output(response, JsonUtil.buildJsonByTotalCount(list, allscore));
		output(response, JsonUtil.buildJson(scoreCountList));
	}
	
	@RequestMapping("/mywrongcount")
	public void MyWrongCountByType(HttpServletResponse response,Integer studentId,Integer topicType) {
		Map<String, Object> map = new HashMap<>();
		map.put("studentId", studentId);
		List list = new ArrayList<>();
		if(topicType==1) {
			list = mistakesService.getWrongSingleByTypeandStatus(map);
		} else if(topicType==2) {
			list = mistakesService.getWrongMulitByTypeandStatus(map);
		} else if(topicType==3) {
			list = mistakesService.getWrongJudgeByTypeandStatus(map);
		} else if(topicType==4) {
			list = mistakesService.getWrongReadByTypeandStatus(map);
		}
		output(response, JsonUtil.buildFalseJson("0", String.valueOf(list.size())));
	}
	/**
	 * 单选错题本的详情
	 * @param response
	 * @param studentId
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("mysinglewrong")
	public void MySingleWrong(HttpServletResponse response,Integer studentId,Integer subjectId,Integer pageNo,Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("studentId", studentId);
		map.put("subjectId", subjectId);
		map.put("pageNo", (pageNo-1)*pageSize);
		map.put("pageSize", pageSize);
		List<MyMistakes> list = mistakesService.getWrongSingleByTypeandStatus(map);
		if(!list.isEmpty() && list!=null) {
			list.get(0).setQuestionMulitChoice(null);
			list.get(0).setQuestionJudge(null);
			list.get(0).setQuestionRead(null);
		}
		output(response, JsonUtil.buildJson(list));
	}
	/**
	 * 多选题错题本内容的详情
	 * @param response
	 * @param studentId
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("mymulitwrong")
	public void MyMulitWrong(HttpServletResponse response,Integer studentId,Integer subjectId,Integer pageNo,Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("studentId", studentId);
		map.put("subjectId", subjectId);
		map.put("pageNo", (pageNo-1)*pageSize);
		map.put("pageSize", pageSize);
		List<MyMistakes> list = mistakesService.getWrongMulitByTypeandStatus(map);
		if(!list.isEmpty() && list!=null) {
			list.get(0).setQuestionSingleChoice(null);
			list.get(0).setQuestionJudge(null);
			list.get(0).setQuestionRead(null);
		}
		output(response, JsonUtil.buildJson(list));
	}
	/**
	 * 判断题错题本内容的详情
	 * @param response
	 * @param studentId
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("myjudgewrong")
	public void MyJudgeWrong(HttpServletResponse response,Integer studentId,Integer subjectId,Integer pageNo,Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("studentId", studentId);
		map.put("subjectId", subjectId);
		map.put("pageNo", (pageNo-1)*pageSize);
		map.put("pageSize", pageSize);
		List<MyMistakes> list = mistakesService.getWrongJudgeByTypeandStatus(map);
		if(!list.isEmpty() && list!=null) {
			list.get(0).setQuestionMulitChoice(null);
			list.get(0).setQuestionSingleChoice(null);
			list.get(0).setQuestionRead(null);
		}
		output(response, JsonUtil.buildJson(list));
	}
	/**
	 * 阅读题错题本内容的详情
	 * @param response
	 * @param studentId
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("myreadwrong")
	public void MyReadWrong(HttpServletResponse response,Integer studentId,Integer pageNo,Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("studentId", studentId);
		map.put("pageNo", (pageNo-1)*pageSize);
		map.put("pageSize", pageSize);
		List<MyMistakes> list = mistakesService.getWrongReadByTypeandStatus(map);
		if(list != null && !list.isEmpty()){
			output(response, JsonUtil.buildJson(list));
		}else{
			output(response, JsonUtil.buildFalseJson("1", "没有数据"));
		}
			
//		if(!list.isEmpty() && list!=null) {
//			list.get(0).setQuestionMulitChoice(null);
//			list.get(0).setQuestionJudge(null);
//			list.get(0).setQuestionSingleChoice(null);
//		}
//		List<QuestionRead> reads = qReadService.getReadChildByParentId(list.get(0).getPaperId());
//		List list2 = new ArrayList<>();
//		list2.add(list);
//		list2.add(reads);
//		output(response, JsonUtil.buildJson(list2));
	}
	
	
}
