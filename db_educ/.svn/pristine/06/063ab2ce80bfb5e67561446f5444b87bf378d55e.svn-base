package com.db.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.AnswerCard;
import com.db.service.AnswerCardService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/app/answerCard")
public class APPAnswerCardController extends BaseUtil{
	
	@Resource
	private AnswerCardService answerCardService;

	/**
	 * 上传答题卡
	 * @param respons
	 * @param ac
	 */
	@RequestMapping("/uploadImage")
	public void uploadImage(HttpServletResponse response,AnswerCard ac){
		if (ac != null) {
			ac.setCreateTime(new Date());
			answerCardService.saveAnswerCard(ac);
			output(response, JsonUtil.buildFalseJson("0", "上传成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "上传失败!"));
		}
	}
}
