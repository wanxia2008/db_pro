//package com.db.action;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.db.entity.Curriculum;
//import com.db.entity.MyLessons;
//import com.db.entity.TimeAndLesson;
//import com.db.service.CurriculumService;
//import com.db.util.BaseUtil;
//import com.db.util.JsonUtil;
//
//@Controller
//@RequestMapping("/curriculum")
//public class CurriculumController extends BaseUtil {
//
//	@Resource
//	private CurriculumService curriculumService;
//
//	/**
//	 * 前端课程表
//	 * 
//	 * @param response
//	 */
//	@RequestMapping("/curriculumList")
//	public void curriculumList(HttpServletResponse response, Integer studentId,Integer pageNo,Integer pageSize) {
//		if (pageNo == null) {
//			pageNo = 1;
//		}
//		if (pageSize == null) {
//			pageSize = 5;
//		}
//		List<Curriculum> cList = curriculumService.curriculumList(studentId,(pageNo-1)*pageNo,pageSize);
//		List<MyLessons> mylList = new ArrayList<MyLessons>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////		Map<String, List<TimeAndLesson>> map = new HashMap<String, List<TimeAndLesson>>();
//		if (cList == null || cList.isEmpty()) {
//			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
//		} else {
//			for (Curriculum cu : cList) {
//				List<TimeAndLesson> talList = new ArrayList<TimeAndLesson>();
//				MyLessons mLessons = new MyLessons();
//				TimeAndLesson timeAndLesson1 = new TimeAndLesson();
//				timeAndLesson1.setTimeZone(cu.getCurriculumTime1());
//				timeAndLesson1.setLessonName(cu.getCurriculumName1());
//				talList.add(timeAndLesson1);
//				TimeAndLesson timeAndLesson2 = new TimeAndLesson();
//				timeAndLesson2.setTimeZone(cu.getCurriculumTime2());
//				timeAndLesson2.setLessonName(cu.getCurriculumName2());
//				talList.add(timeAndLesson2);
//				TimeAndLesson timeAndLesson3 = new TimeAndLesson();
//				timeAndLesson3.setTimeZone(cu.getCurriculumTime3());
//				timeAndLesson3.setLessonName(cu.getCurriculumName3());
//				talList.add(timeAndLesson3);
//				mLessons.setMyDate(sdf.format(cu.getCurriculumDate()));
//				mLessons.setTalList(talList);
//				mylList.add(mLessons);
//			}
//			output(response, JsonUtil.buildJson(mylList));
//		}
//	}
//	/**
//	 * 点击科目查看讲义
//	 * @param response
//	 * @param studentId
//	 * @param classId
//	 * @param curriculum
//	 * @throws ParseException 
//	 */
//	@RequestMapping("/seeLectureNotes")
//	public void seeLectureNotes(HttpServletResponse response,Integer studentId,Integer classId,String curriculum) throws ParseException{
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////		String starttime = sdf.format(curriculum);
//		List<Curriculum> cList = curriculumService.findCurriculumClassNo(studentId,classId,curriculum);
//		if (cList != null && !cList.isEmpty()) {
//			output(response, JsonUtil.buildJson(cList));
//		}else {
//			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
//		}
//	}
//}
