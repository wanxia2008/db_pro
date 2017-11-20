package com.db.action;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.db.entity.LectureNotes;
import com.db.service.CourseService;
import com.db.service.LectureNotesService;
import com.db.service.StudentService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseUtil {

	@Resource
	private CourseService courseService;
	@Resource
	private StudentService studentService;
	@Resource
	private LectureNotesService lecturenoteservice;

	/**
	 * 前端课程表
	 * 
	 * @param response
	 */
	@RequestMapping("/courseList")
	public void courseList(HttpServletResponse response, Integer studentId) {
//		Student student = studentService.getStudentById(studentId);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date now = new Date();
//		String courseDate = dateFormat.format(now);
//		List<Course> coursesList = courseService.getCourseByClassList(student.getClassNo(), courseDate);
//
//		List<MyLessons> mylList = new ArrayList<MyLessons>();
//		if (coursesList == null || coursesList.isEmpty()) {
//			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
//		} else {
//			for (Course c : coursesList) {
//				List<TimeAndLesson> talList = new ArrayList<TimeAndLesson>();
//				MyLessons mLessons = new MyLessons();
//				List<Course> cList = courseService.findcCourseDateLit(c.getCourseDate());
//				for (Course cc : cList) {
//					TimeAndLesson time = new TimeAndLesson();
//					time.setTimeZone(cc.getCourseTimes().toString());
//					time.setLessonName(cc.getSubject().getSubjectName());
//					time.setCourseId(cc.getCourseId());
//					time.setLecturenoteId(cc.getLecturenoteId());
//					talList.add(time);
//				}
//				mLessons.setMyDate(c.getCourseDate());
//				mLessons.setTalList(talList);
//				mylList.add(mLessons);
//			}
//			// 去掉list里重复的元素
//			for (int i = 0; i < mylList.size() - 1; i++) {
//				for (int j = mylList.size() - 1; j > i; j--) {
//					if (mylList.size() > j + 1) {
//						mylList.remove(j);
//					}
//				}
//			}
//			output(response, JsonUtil.buildJson(mylList));
//		}
		// 获取当天日期接下来的周末
		// Calendar calendar = Calendar.getInstance(Locale.CHINA);// 日历
		// calendar.setFirstDayOfWeek(Calendar.MONDAY);
		// calendar.setTimeInMillis(System.currentTimeMillis());
		// calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		// System.out.println("离当前最近的周末为：" +
		// dateFormat.format(calendar.getTime()));
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("courseDate", dateFormat.format(calendar.getTime()));
		// map.put("classId", student.getClassNo());
		// // 拿最近的周末去取得课程表
		// List<Course> courses = courseService.getCourseByClass(map);
		// Map<String, Object> map1 = new HashMap<>();
		// map1.put("myDate", courses.get(0).getCourseDate());
		// for(int i=0;i<courses.size();i++) {
		// courses.get(i).setClasslist(null);
		// courses.get(i).setPaperInfo(null);
		// map1.put("talList"+i, courses.get(i));
		// }
		// List list = new ArrayList<>();
		// list.add(map1);
		// output(response, JsonUtil.buildJsonByTotalCount(list,
		// courses.size()));
	}
	@RequestMapping("getLectureNote")
	public void getLectureNote(Integer noteId,HttpServletResponse response) {
		LectureNotes lectureNote = lecturenoteservice.getLectureNotes(noteId);
		if(lectureNote.getIsPublic()==1) {
			List list = new ArrayList<>();
			list.add(lectureNote);
			output(response, JsonUtil.buildJson(list));
			
		} else {
			output(response, JsonUtil.buildFalseJson("1", "该讲义暂无共享"));
		}
	}
}
