package com.db.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.StudentCareer;
import com.db.entity.Subject;
import com.db.entity.utilentity.SubjectMistakeCount;
import com.db.service.MyMistakesService;
import com.db.service.StudentCareerService;
import com.db.service.SubjectService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

/**
 * 学员历史记录
 * 
 * @author sun
 *
 */
@Controller
@RequestMapping("/app/career")
public class APPStudentCareerController extends BaseUtil {
	@Resource
	private StudentCareerService careerService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private MyMistakesService mmisService;

	/**
	 * 学员历史记录
	 * 
	 * @param response
	 * @param studentId
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/careerList")
	public void careerList(HttpServletResponse response, Integer studentId, Integer pageNo, Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		List<StudentCareer> scList = careerService.careerList(studentId, (pageNo - 1) * pageSize, pageSize);
		if (scList != null && !scList.isEmpty()) {
			output(response, JsonUtil.buildJson(scList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	// 返回所有科目
	@RequestMapping("/allSubject")
	private void showAllSubject(HttpServletResponse response, int studentId) {
		List<Subject> subjectList = subjectService.findSubjectList(0, 100, null);
		List<SubjectMistakeCount> smcList = new ArrayList<SubjectMistakeCount>();
		for (Subject sj : subjectList) {
			SubjectMistakeCount smc = new SubjectMistakeCount();
			int currCount = mmisService.countThisSubjectMistake(studentId, sj.getSubjectId(), null);
			if (currCount > 0) {
				smc.setProjectId(sj.getSubjectId());
				smc.setProjectName(sj.getSubjectName());
				smc.setMisCount(currCount);
				smcList.add(smc);
			}
		}
		if (smcList != null && !smcList.isEmpty()) {
			output(response, JsonUtil.buildJson(smcList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 
	 * @param response
	 * @param studentId
	 * @param subjectId
	 * 
	 */
	// 返回某个学生某一科目的错题总数，分类统计{单选，多选，判断}
	@RequestMapping("/countMistake")
	private void countStudentMistakesBySubject(HttpServletResponse response, int studentId, int subjectId) {
		int danxuan = mmisService.countThisSubjectMistake(studentId, subjectId, 1);
		int duoxuan = mmisService.countThisSubjectMistake(studentId, subjectId, 2);
		int panduan = mmisService.countThisSubjectMistake(studentId, subjectId, 3);
		int tiankong = mmisService.countThisSubjectMistake(studentId, subjectId,6);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("danxuan", danxuan);
		returnMap.put("duoxuan", duoxuan);
		returnMap.put("panduan", panduan);
		returnMap.put("tiankong", tiankong);
		output(response, JsonUtil.MapToJson(returnMap));
	}
}
