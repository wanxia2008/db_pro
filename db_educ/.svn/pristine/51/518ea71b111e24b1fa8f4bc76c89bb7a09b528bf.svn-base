package com.db.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.Course;
import com.db.entity.LectureNotes;
import com.db.entity.MyLessons;
import com.db.entity.StudentCareer;
import com.db.entity.TimeAndLesson;
import com.db.service.CourseService;
import com.db.service.LectureNotesService;
import com.db.service.StudentCareerService;
import com.db.service.StudentService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/app/course")
public class APPCourseController extends BaseUtil {

	@Resource
	private CourseService courseService;
	@Resource
	private StudentService studentService;
	@Resource
	private LectureNotesService lecturenoteservice;
	@Resource
	private StudentCareerService studentcareerservice;

	/**
	 * 前端课程表
	 * 
	 * @param response
	 */
	@RequestMapping("/courseList")
	public void courseList(HttpServletResponse response, Integer studentId) {
		List<StudentCareer> careers = studentcareerservice.getStuCareerByStu(studentId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String courseDate = dateFormat.format(now);
		Map<String, Object> map = new HashMap<>();
		if (careers != null &&!careers.isEmpty()) {
			String classno = "";
			for (StudentCareer career : careers) {
				classno += career.getClassId() + ",";
			}
			classno = classno.substring(0, classno.length() - 1);
			if (classno.indexOf(",") >= 0) {
				List classlist = Arrays.asList(classno.split(","));
				map.put("classlist", classlist);
				map.put("classid", null);
			} else {
				map.put("classlist", null);
				map.put("classid", classno);
			}
			 map.put("courseDate", courseDate);
			List<Course> coursesList = courseService.getCourseByClassList(map);
			List<MyLessons> mylList = new ArrayList<MyLessons>();
			List<TimeAndLesson> talList = null;
			if (coursesList == null || coursesList.isEmpty()) {
				output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
			} else {
				for (Course cc : coursesList) {
					talList = new ArrayList<TimeAndLesson>();
					MyLessons mLessons = new MyLessons();
					TimeAndLesson time = new TimeAndLesson();
					time.setTimeZone(cc.getCourseTimes().toString());
					time.setLessonName(cc.getSubject().getSubjectName());
					time.setCourseId(cc.getCourseId());
					time.setClassId(cc.getClassId());
					time.setClassName(cc.getClassNo1().getClassName());
					if (cc.getLecturenoteId() != null) {
						time.setLecturenoteId(cc.getLecturenoteId());
						System.out.println("讲义共享？：" + cc.getLectureNotes().getIsPublic());
						time.setIspublic(cc.getLectureNotes().getIsPublic());
					} else {
						time.setLecturenoteId(null);
					}
					// talList.add(time);
					if (mylList != null && !mylList.isEmpty()) {
						if (mylList.get(mylList.size() - 1).getMyDate() != null) {
							if (mylList.get(mylList.size() - 1).getMyDate().equals(cc.getCourseDate())) {
								mylList.get(mylList.size() - 1).getTalList().add(time);
							} else {
								talList.add(time);
								mLessons.setMyDate(cc.getCourseDate());
								mLessons.setTalList(talList);
							}
						}else{
							talList.add(time);
							mLessons.setMyDate(cc.getCourseDate());
							mLessons.setTalList(talList);
						}
					} else {
						talList.add(time);
						mLessons.setMyDate(cc.getCourseDate());
						mLessons.setTalList(talList);
					}
					if(mLessons.getMyDate()!=null){
						mylList.add(mLessons);
					}
				}
				if (mylList != null && !mylList.isEmpty()) {
					output(response, JsonUtil.buildJson(mylList));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
				}
			}
		} else {// 该学生不存在班级
			output(response, JsonUtil.buildFalseJson("1", "当前暂未分配班级，无课程！"));
		}
	}

	@RequestMapping("getLectureNote")
	public void getLectureNote(Integer noteId, HttpServletResponse response) {
		LectureNotes lectureNote = lecturenoteservice.getLectureNotes(noteId);
		if (lectureNote.getIsPublic() == 1) {
			List list = new ArrayList<>();
			list.add(lectureNote);
			output(response, JsonUtil.buildJson(list));

		} else {
			output(response, JsonUtil.buildFalseJson("1", "该讲义暂无共享"));
		}
	}

}
