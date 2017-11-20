package com.db.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.Grade;
import com.db.entity.SchoolZone;
import com.db.entity.Student;
import com.db.entity.StudentCareer;
import com.db.entity.StudentScore;
import com.db.entity.Teacher;
import com.db.service.GradeService;
import com.db.service.SchoolZoneService;
import com.db.service.StudentCareerService;
import com.db.service.StudentScoreService;
import com.db.service.StudentService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/score")
public class StudentScoreController extends BaseUtil {
	@Resource
	private StudentScoreService scoreService;
	@Resource
	private StudentCareerService careeService;
	@Resource
	private GradeService gradeService;
	@Resource
	private SchoolZoneService zoneService;
	@Resource
	private StudentService studentService;
	@Resource
	private StudentCareerService careerService;

	/**
	 * 入学考试分数列表(用于入学是否需要缴费)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param gradeId
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/scoreList")
	public ModelAndView scoreList(Integer pageNo, Integer pageSize, Integer gradeId,
			Integer schoolId,Integer school,Integer grade,Integer score) {
		ModelAndView mv = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		int rowsCount = scoreService.getCount(gradeId, schoolId);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<StudentScore> studentList = scoreService.getScoreList(gradeId, schoolId, (pageNo - 1) * pageSize,
				pageSize,school,grade,score);
		for (StudentScore student : studentList) {
			List<StudentCareer> list = careerService.findCareerGradeId(student.getStudentId(), student.getGradeId());
			if (list != null && !list.isEmpty()) {
				student.setCareerList(list);
			} else {
				student.setCareerList(null);
			}
		}
		if (studentList == null || studentList.isEmpty()) {
			mv.addObject("studentList", null);
		} else {
			mv.addObject("studentList", studentList);
		}
		List<Grade> grades = gradeService.getGrade();
		List<SchoolZone> zones = zoneService.getAllSchool();
		mv.addObject("zones", zones);
		mv.addObject("grades", grades);
		mv.setViewName("studentScore/scoreList");
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("gradeId", gradeId);
		mv.addObject("schoolId", schoolId);
		mv.addObject("totalPages", totalPages);
		return mv;
	}

	/**
	 * 是否缴费入学
	 * 
	 * @param response
	 * @param studentId
	 * @param paperId
	 * @param payType
	 */
	@RequestMapping("/isPayGotoschoo")
	public void isPayGotoschoo(HttpServletResponse response, Integer studentId,
			Integer gradeId, Integer payType,Integer scoreId) {
		List<StudentCareer> careeList = careeService.findCareerGradeId(studentId, gradeId);
		StudentScore score = scoreService.getStudentScoreById(scoreId);
		Student student = studentService.getStudentById(studentId);
		if (payType == null) {
			output(response, JsonUtil.buildFalseJson("1", "请选择缴费状态!"));
		} else if (careeList == null || careeList.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("2", "请先去分配班级!"));
		} else {
			for (StudentCareer c : careeList) {
				c.setId(c.getId());
				c.setUpdateTime(new Date());
				c.setPayType(payType);
				careeService.updateCareer(c);
			}
			if (student != null) {
				student.setUpdateTime(new Date());
				student.setPayType(payType);
				studentService.updatepayType(student);
			}
			score.setPayType(payType);
			scoreService.updateStudentScore(score);
			output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
		}
	}
	/**
	 * 删除学员
	 * @param response
	 * @param scoreId
	 */
	@RequestMapping("/studentIdDelete")
	public void studentIdDelete(HttpServletResponse response,Integer scoreId,HttpServletRequest request){
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (currTeacher.getTeacherType().equals("5") || currTeacher.getTeacherType().equals("6")) {
			output(response, JsonUtil.buildFalseJson("1", "您没有删除学员的权限!"));
		}else {
			scoreService.deleteScoreId(scoreId);
			output(response, JsonUtil.buildFalseJson("0", "删除成功"));
		}
	}
}
