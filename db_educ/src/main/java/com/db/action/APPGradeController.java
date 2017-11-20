package com.db.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.Grade;
import com.db.service.GradeService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/app/grade")
public class APPGradeController extends BaseUtil {

	@Resource
	private GradeService gradeService;
	
	/**
	 * 获取所有年级
	 * @param pageNo
	 * @param pageSize
	 */
	@RequestMapping("/gradeList")
	public void gradeList(Integer pageNo,Integer pageSize,HttpServletResponse response){
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		List<Grade> gradeList = gradeService.findGradeList((pageNo-1)*pageNo,pageSize);
		if (gradeList != null && !gradeList.isEmpty()) {
			output(response, JsonUtil.buildJson(gradeList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}
}
