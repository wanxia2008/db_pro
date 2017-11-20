package com.db.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.ClassNo;
import com.db.entity.SchoolZone;
import com.db.entity.Student;
import com.db.entity.StudentExamination;
import com.db.entity.Teacher;
import com.db.service.ClassNoService;
import com.db.service.SchoolZoneService;
import com.db.service.StudentExaminationService;
import com.db.service.StudentService;
import com.db.service.TeacherService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/school")
public class SchoolController extends BaseUtil {

	@Resource
	private SchoolZoneService schoolservice;
	@Resource
	private ClassNoService classNoService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private StudentService studentService;
	@Resource
	private StudentExaminationService examinationService;

	private String respJson = null;

	private Logger log = Logger.getLogger("SchoolController");

	/**
	 * 校区管理列表
	 * 
	 * @return
	 */
	@RequestMapping("/schoolmanage")
	public ModelAndView Schoolmanage(Integer pageNo, Integer pageSize, String schoolName2) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = schoolservice.getCount(schoolName2);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		ModelAndView view = new ModelAndView();
		view.setViewName("classmanage/schoolmanage");
		List<SchoolZone> list = schoolservice.getSchoolZone1(schoolName2, (pageNo - 1) * pageSize, pageSize);
		if (list != null && !list.isEmpty()) {
			view.addObject("schoolList", list);
		} else {
			view.addObject("schoolList", null);
		}
		view.addObject("page", pageNo);
		view.addObject("pageSize", pageSize);
		view.addObject("totalPages", totalPages);
		view.addObject("schoolName2", schoolName2);
		return view;
	}

	@RequestMapping(value = "/newschool")
	public void NewSchool(Model model, String schoolName1, HttpServletResponse response) {
		SchoolZone sZone = schoolservice.getschoolName(schoolName1);
		if (schoolName1.equals("")) {
			output(response, JsonUtil.buildFalseJson("2", "请输入校区名称!"));
		} else {
			if (sZone != null) {
				model.addAttribute("errorUserName", "该校区也存在，请勿重复添加!");
				output(response, JsonUtil.buildFalseJson("1", "该校区也存在，请勿重复添加!"));
			} else {
				SchoolZone schoolZone = new SchoolZone();
				schoolZone.setIsUsing(0);// 默认未使用
				schoolZone.setCreateTime(new Date());
				schoolZone.setSchoolName(schoolName1);
				schoolservice.addSchoolZone(schoolZone);
				output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
			}
		}
	}
	
	/**
	 * 修改校区
	 * @param response
	 * @param schoolZone
	 */

	@RequestMapping(value = "/updateschool", method = RequestMethod.POST)
	public void UpdateSchool(HttpServletResponse response, SchoolZone schoolZone) {
		SchoolZone sz = schoolservice.getschoolName(schoolZone.getSchoolName());
		SchoolZone sZone = schoolservice.getSchoolZoneById(schoolZone.getSchoolId());
		 if (sz != null && !sZone.getSchoolName().equals(schoolZone.getSchoolName())) {
			output(response, JsonUtil.buildFalseJson("2", "该校区已存在!"));
		} else if (schoolZone.getSchoolName() == null || schoolZone.getSchoolName() == "") {
			output(response, JsonUtil.buildFalseJson("3", "请输入校区名称"));
		} else {
			try {
				schoolZone.setUpdateTime(new Date());
				schoolservice.updateSchoolZone(schoolZone);
				output(response, JsonUtil.buildFalseJson("0", "修改成功"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "修改失败"));
			}
		}
	}

	/**
	 * 删除校区
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteschool", method = RequestMethod.POST)
	public void DeleteSchool(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String ids = request.getParameter("schoolId");
			int id = Integer.parseInt(ids);
			SchoolZone sZone = schoolservice.getSchoolZoneById(id);
			List<ClassNo> cnList = classNoService.findIsExistenceCampus(id);
			List<Teacher> teachers = teacherService.findIsExistenceCampus(id);
			List<Student> students = studentService.findIsExistenceCampus(id);
			List<StudentExamination> examinations = examinationService.findIsExistenceCampus(id);
			if (sZone!= null && sZone.getIsUsing() ==1 ) {
				respJson = JsonUtil.buildFalseJson("2", "该校区正在使用中，暂时不能删除!");
			} else if ((cnList != null && !cnList.isEmpty()) || (teachers != null && !teachers.isEmpty())
					|| (students != null && !students.isEmpty()) || (examinations != null && !examinations.isEmpty())) {
				respJson = JsonUtil.buildFalseJson("3", "该校区已被使用!");
			} else {
				schoolservice.deleteSchoolZone(id);
				respJson = JsonUtil.buildFalseJson("0", "删除成功!");
			}
		} catch (NumberFormatException e) {
			
			respJson = JsonUtil.buildFalseJson("1", "删除失败!");
		}
		output(response, respJson);
	}
	
	/**
	 * 修改校区是否停用
	 * @param response
	 * @param schoolId
	 * @param isUsing
	 */
	@RequestMapping("/changeisuseing")
	public void changeisuseing(HttpServletResponse response,Integer schoolId,Integer isUsing){
		SchoolZone sZone = schoolservice.getSchoolZoneById(schoolId);
		List<ClassNo> cnList = classNoService.findIsExistenceCampus(schoolId);
		List<Teacher> teachers = teacherService.findIsExistenceCampus(schoolId);
		List<Student> students = studentService.findIsExistenceCampus(schoolId);
		List<StudentExamination> examinations = examinationService.findIsExistenceCampus(schoolId);
		if ((cnList != null && !cnList.isEmpty()) || (teachers != null && !teachers.isEmpty())
				|| (students != null && !students.isEmpty()) || (examinations != null && !examinations.isEmpty())) {
			output(response, JsonUtil.buildFalseJson("1", "该校区正在使用中!"));
		}else {
			if (sZone.getIsUsing() ==0) {//启用校区
				sZone.setUpdateTime(new Date());
				sZone.setIsUsing(1);
				schoolservice.updateIsUsing(sZone);
				output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
			} else {//停用校区
				sZone.setUpdateTime(new Date());
				sZone.setIsUsing(0);
				schoolservice.updateIsUsing(sZone);
				output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
			}
		}
	}
}
