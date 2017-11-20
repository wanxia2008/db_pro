package com.db.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.TestRecord;
import com.db.entity.utilentity.StudentRanking;
import com.db.service.PaperDetailsService;
import com.db.service.TestRecordService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/app/exam")
public class APPExamController extends BaseUtil{
	
	@Resource
	private PaperDetailsService paperDetailsService;
	@Resource
	private TestRecordService recordService;
	/**
	 * 根据试卷ID返回所有的总数
	 * 
	 * @param response
	 * @param paperId
	 * @param piType
	 */
	@RequestMapping("/paperTotai")
	public void paperTotai(HttpServletResponse response, Integer paperId) {
		int conut = paperDetailsService.findPaperTotai(paperId);
		output(response, JsonUtil.buildFalseJson("0", String.valueOf(conut)));
	}

	/**
	 * 考试完成返回在班级的排名(单科考卷)
	 * 
	 * @param response
	 * @param paperId
	 * @param classId
	 */
	@RequestMapping("/studentScoreRanking")
	public void studentRanking(HttpServletResponse response, Integer paperId, Integer classId,Integer studentId) {
		List<TestRecord> trList = recordService.findObtainScoreRankList(paperId, classId);
		List<StudentRanking> recordList = new ArrayList<StudentRanking>();
		double avgScore = recordService.getClassIdAvgScore(paperId,classId);
		if (trList != null && !trList.isEmpty()) {
			for (TestRecord tr:trList) {
				if (tr.getStudentId().equals(studentId)) {
					StudentRanking ranking = new StudentRanking();
					ranking.setScoreRank(tr.getScoreRank());
					BigDecimal b = new BigDecimal(avgScore);
					double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					ranking.setAverage(f1);
					recordList.add(ranking);
				}
			}
			if (recordList != null && !recordList.isEmpty()) {
				output(response, JsonUtil.buildJson(recordList));
			}else {
				output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
			}
		}else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}
}
