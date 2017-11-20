package com.db.action;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.db.entity.Homework;
import com.db.entity.PaperInfo;
import com.db.entity.Subject;
import com.db.entity.Teacher;
import com.db.entity.TeachingMaterial;
import com.db.service.HomeworkService;
import com.db.service.PaperInfoService;
import com.db.service.SubjectService;
import com.db.service.TeachingMaterialService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/teachingMaterial")
public class TeachingMaterialController extends BaseUtil{

	@Resource
	private TeachingMaterialService materialService;
	@Resource
	private PaperInfoService paperInfoService;
	@Resource
	private HomeworkService homeworkService;
	@Resource
	private SubjectService subjectService;
	
	/**
	 * 教材列表
	 * @param pageNo
	 * @param pageSize
	 * @param meterialName
	 * @return
	 */
	@RequestMapping("/materialList")
	public ModelAndView materialList(Integer pageNo,Integer pageSize,String meterialName,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		Integer subject = null;
		Teacher currTeacher = (Teacher) request.getSession().getAttribute("currTeacher");
		if (currTeacher.getSubject() != null) {
			subject = currTeacher.getSubject();
		}
		int rowsCount = materialService.getCount(meterialName,currTeacher.getTeacherType(),subject);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) +1;
		}
		List<TeachingMaterial> tmList = materialService.materialList(meterialName,(pageNo-1)*pageSize,pageSize,currTeacher.getTeacherType(),subject);
		if (tmList != null && !tmList.isEmpty()) {
			mv.addObject("tmList", tmList);
		}else {
			mv.addObject("tmList", null);
		}
		
		List<Subject> subjects = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			subjects = subjectService.getSubject();
		} else {
			subjects = subjectService.getSubjectById(currTeacher.getSubject());
		}
		mv.addObject("subjects", subjects);
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("meterialName", meterialName);
		mv.addObject("totalPages", totalPages);
		mv.setViewName("exampaper/materialList");
		return mv;
	}
	
	/**
	 * 新增教材
	 * @param response
	 * @param tachMeterialName
	 */
	@RequestMapping("/saveMaterial")
	public void saveMaterial(HttpServletResponse response,String tachMeterialName,Integer subject){
		TeachingMaterial tm = materialService.getMeterialName(tachMeterialName,subject);
		if (tm != null) {
			output(response, JsonUtil.buildFalseJson("1", "该教材已存在!"));
		} else if (tachMeterialName == null || tachMeterialName=="") {
			output(response, JsonUtil.buildFalseJson("2", "请输入教材名称!"));
		} else if (subject == null) {
			output(response, JsonUtil.buildFalseJson("3", "请选择科目!"));
		} else {
			try {
				TeachingMaterial tmMaterial = new TeachingMaterial();
				tmMaterial.setCreateTime(new Date());
				tmMaterial.setMaterialName(tachMeterialName);
				tmMaterial.setSubject(subject);
				materialService.addMaterial(tmMaterial);
				output(response, JsonUtil.buildFalseJson("0", "添加成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("3", "添加失败!"));
			}
		}
	}
	
	/**
	 * 编辑教材
	 * @param response
	 * @param materialId
	 */
	@RequestMapping("/modityMaterial")
	public void daleteMaterial(HttpServletResponse response,Integer materialId,String meterialName,Integer subjectId){
		TeachingMaterial tm = materialService.getMaterialById(materialId);
		TeachingMaterial tmMaterial = materialService.getMeterialName(meterialName,subjectId);
		if (tm == null) {
			output(response, JsonUtil.buildFalseJson("2", "没有数据!"));
		} else if (meterialName == null || meterialName=="") {
			output(response, JsonUtil.buildFalseJson("3", "请输入教材名称!"));
		} else if (tmMaterial != null && !tm.getMaterialName().equals(meterialName)) {
			output(response, JsonUtil.buildFalseJson("4", "该教材名称已存在!"));
		} else {
			try {
				tm.setMaterialName(meterialName);
				tm.setSubject(subjectId);
				materialService.updateMateerial(tm);
				output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "编辑失败!"));
			}
		}
	}
	/**
	 * 删除教材
	 * @param response
	 * @param materialId
	 */
	@RequestMapping("/tagdelete")
	public void tagdelete(HttpServletResponse response,Integer materialId){
		List<PaperInfo> pInfo = paperInfoService.getScience(materialId);
		List<Homework> homework = homeworkService.getScience(materialId);
		if ((pInfo != null && !pInfo.isEmpty()) || (homework != null && !homework.isEmpty())) {
			output(response, JsonUtil.buildFalseJson("2", "该教材已被使用，删除失败!"));
		}else {
			try {
				materialService.daleteMaterial(materialId);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
			}
		}
	}
	/**
	 * 所有科目
	 * @param subject
	 * @param subjectName
	 */
	@RequestMapping("/subjectList")
	public void subjectList(HttpServletResponse response){
		List<Subject> sList = subjectService.getSubject();
		if(sList != null && !sList.isEmpty()){
			output(response, JsonUtil.buildJson(sList));
		}else{
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}
}
