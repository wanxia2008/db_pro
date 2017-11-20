package com.db.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.LectureNotes;
import com.db.service.LectureNotesService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/app/lecturenotes")
public class APPLectureNotesController extends BaseUtil{
	@Resource
	private LectureNotesService lnotesService;
	
	@RequestMapping("/showContent")
	private void showLectureNotesContent(HttpServletResponse response,int noteId){
		LectureNotes lnotes = lnotesService.getLectureNotes(noteId);
		List<LectureNotes> nol = new ArrayList<LectureNotes>();
		nol.add(lnotes);
		output(response, JsonUtil.buildJson(nol));
	}
}
