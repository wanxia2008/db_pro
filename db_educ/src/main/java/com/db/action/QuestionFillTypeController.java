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

import com.db.entity.QuestionFill;
import com.db.entity.QuestionFillType;
import com.db.entity.Teacher;
import com.db.service.QuestionFillService;
import com.db.service.QuestionFillTypeService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/questionfilltype")
public class QuestionFillTypeController extends BaseUtil {

   @Resource
   private  QuestionFillTypeService qfilltypeservice;
   @Resource
   private  QuestionFillService qfillservice;
   /**	
    * 新增填空题类型
    * @param response
    * @param typeName
    */
   @RequestMapping("/addfilltype")	
   public void addFillType(HttpServletResponse response,HttpServletRequest request ,String typeName){
	   if (typeName == null || typeName == "") {
			output(response, JsonUtil.buildFalseJson("2", "请输入填空题类型!"));
		} else {
			int qft = qfilltypeservice.getAllFillTypeCount(typeName);
			if(qft>0){
				output(response,JsonUtil.buildFalseJson("1","该填空题类型名称已存在"));
			}else{
				 //执行添加
				QuestionFillType questionfilltype = new QuestionFillType();
				HttpSession session = request.getSession();
				Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
				questionfilltype.setCreateTime(new Date());
				questionfilltype.setCreateUser(currTeacher.getTeacherId());
				questionfilltype.setTypeName(typeName);
				questionfilltype.setUsedCount(0);
				qfilltypeservice.addFillTypeName(questionfilltype);
				output(response, JsonUtil.buildFalseJson("0","添加填空题类型成功"));
			}	
		}
   }
   


/**
    * 获取填空题类型列表
    * @param pageNo
    * @param pageSize
    * @param typeName
    * @return
    */
   @RequestMapping("/getfilltypelist")
   public ModelAndView getFillTypeList(Integer pageNo,Integer pageSize,String typeName1){
	   ModelAndView mv = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = qfilltypeservice.getFillTypeCount(typeName1);
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<QuestionFillType> qcList = qfilltypeservice.getTypeList(typeName1,(pageNo - 1) * pageSize, pageSize);
		if (qcList != null && !qcList.isEmpty()) {
			mv.addObject("qcList", qcList);
		}else {
			mv.addObject("qcList", null);
		}
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("typeName", typeName1);
		mv.setViewName("questionbank/fillTypeList");
		return mv;
   }
   
   /**
    * 修改填空题类型
    * @param response
    * @param typeName
    */
   @RequestMapping("/savefilltype")
   public void saveFillType(HttpServletResponse response,Integer typeId,String typeName){
	   int tcount = qfilltypeservice.getAllFillTypeCount(typeName);
	   if(tcount > 0){
		   output(response, JsonUtil.buildFalseJson("1","该类型已经存在或你没有执行修改操作"));
	   }else{
			QuestionFillType questionfilltype = new QuestionFillType();
            questionfilltype.setUpdateTime(new Date());
            questionfilltype.setTypeName(typeName);
            questionfilltype.setTypeId(typeId);
            qfilltypeservice.savefill(questionfilltype);
 		    output(response, JsonUtil.buildFalseJson("0","修改成功"));
	   
	   }
	   
   }
   
   /**
    * 删除填空题类型
    * @param response
    * @param typeId
    */
   @RequestMapping("/delfilltype")
   public void delFillType(HttpServletResponse response,Integer typeId){
	   List<QuestionFill> qfill = qfillservice.getQuestionFillType(typeId);
	   if(qfill != null && !qfill.isEmpty()){
		    output(response, JsonUtil.buildFalseJson("1","该填空题类型被占用,不能执行删除操作"));
	   }else{
		   try {
			    qfilltypeservice.delType(typeId); 
			    output(response, JsonUtil.buildFalseJson("0","删除填空题类型成功"));
		} catch (Exception e) {
			    output(response, JsonUtil.buildFalseJson("2","删除填空题类型失败"));
		}
	   }
	   
   }
   
   
   
}
