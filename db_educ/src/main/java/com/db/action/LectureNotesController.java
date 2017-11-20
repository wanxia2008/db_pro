package com.db.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.ClassNo;
import com.db.entity.Grade;
import com.db.entity.LectureNotes;
import com.db.entity.PaperInfo;
import com.db.entity.RangeLesson;
import com.db.entity.SeasonType;
import com.db.entity.Subject;
import com.db.entity.Teacher;
import com.db.entity.TeacherAuthority;
import com.db.entity.TeachingMaterial;
import com.db.service.ClassNoService;
import com.db.service.GradeService;
import com.db.service.LectureNotesService;
import com.db.service.RangeLessonService;
import com.db.service.SeasonTypeService;
import com.db.service.SubjectService;
import com.db.service.TaskTypeService;
import com.db.service.TeacherAuthorityService;
import com.db.service.TeacherService;
import com.db.service.TeachingMaterialService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;


@Controller
@RequestMapping("/lectureNotes")
public class LectureNotesController extends BaseUtil {

	@Resource
	private LectureNotesService lectureNotesService;
	@Resource
	private GradeService gradeService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private TaskTypeService taskTypeService;
	@Resource
	private TeachingMaterialService tMaterialService;
	@Resource
	private ClassNoService classNoService;
	@Resource
	private SeasonTypeService seasonTypeService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private TeacherAuthorityService authorityService;
	@Resource
	private RangeLessonService lessonService;

	/**
	 * 我的讲义列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/lectureNotesList")
	public ModelAndView LectureNotesList(HttpServletRequest request, Integer pageNo, Integer pageSize,
			Integer handoutType, String handoutTitle, Integer subject, Integer isPublic, Integer grade)
					throws ParseException {
		ModelAndView view = new ModelAndView();
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		int rowsCount = 0;
		int totalPages = 0;
		System.out.println("grade : "+ grade);
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		String gradeIds = currTeacher.getGrade(); //获取教师的所有年级
		List<String> gradesList  = Arrays.asList(gradeIds.split(","));
		List<TeacherAuthority> taList = authorityService.getTeacherIdById(currTeacher.getTeacherId());
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {// 超级管理员能查看所有的讲义列表
			rowsCount = lectureNotesService.seeCountIspublicMunber1(handoutType, handoutTitle, subject, isPublic, grade);
			List<LectureNotes> lnList = lectureNotesService.seelectureNotesIsPublicByIdList1((pageNo - 1) * pageSize,
					pageSize, handoutType, handoutTitle, subject, isPublic, grade);
			if (!lnList.isEmpty() && lnList != null) {
				view.addObject("lnList", lnList);
			} else {
				view.addObject("lnList", null);
			}
		} else {// 只能查看自己的和别人公开的讲义
			// 读取所有公开的讲义
			for (TeacherAuthority ta : taList) {
				if (ta.getHandoutAuthority() == 1 || ta.getHandoutAuthority() == 3) {
					rowsCount = lectureNotesService.getCountIspublicMunber(gradesList,handoutType, handoutTitle, currTeacher.getSubject(), isPublic,
							currTeacher.getTeacherId());
					List<LectureNotes> lnList = lectureNotesService.lectureNotesIsPublicByIdList(gradesList,
							currTeacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize, handoutType, handoutTitle,
							 currTeacher.getSubject(), isPublic, grade);
					if (!lnList.isEmpty() && lnList != null) {

						view.addObject("lnList", lnList);
					} else {
						view.addObject("lnList", null);
					}
				} else {// 只能读取自己的讲义
					rowsCount = lectureNotesService.getCountMunber(gradesList,handoutType, handoutTitle, subject, isPublic,
							currTeacher.getTeacherId(), grade);
					List<LectureNotes> lnList = lectureNotesService.lectureNotesByIdList(gradesList,currTeacher.getTeacherId(),
							(pageNo - 1) * pageSize, pageSize, handoutType, handoutTitle, subject, isPublic, grade);
					if (!lnList.isEmpty() && lnList != null) {
						view.addObject("lnList", lnList);
					} else {
						view.addObject("lnList", null);
					}
				}
			}
		}
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<Subject> sList = null;
		List<Grade> gList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList= subjectService.getSubject();
			gList = gradeService.getGrade();
			System.out.println("gList 1: "+ gList.size());
		} else {
			sList = subjectService.getSubjectById(currTeacher.getSubject());
			String [] my = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(my);
			System.out.println(gradeList);
			gList = gradeService.getGradeByIds(gradeList);
			System.out.println("gList 2: "+ gList.size());
		}
		view.setViewName("lectureNoteManage/mylectureNote");
		view.addObject("sList", sList);
		view.addObject("gList", gList);
		List<ClassNo> cList = classNoService.getAllClasses(currTeacher.getCampus(), (pageNo - 1) * pageSize, pageSize);
		view.addObject("cList", cList);
		List<SeasonType> stList = seasonTypeService.getSeasontype();
		view.addObject("stList", stList);
		view.addObject("page", pageNo);
		view.addObject("pageSize", pageSize);
		view.addObject("totalPages", totalPages);
		// view.addObject("startCreateTime", startCreateTime);
		// view.addObject("endCreateTime", endCreateTime);
		view.addObject("handoutTitle", handoutTitle);
		view.addObject("handoutType", handoutType);
		view.addObject("subject", subject);
		view.addObject("grade", grade);
		view.addObject("isPublic", isPublic);
		List<TeachingMaterial> tmList = tMaterialService.teachingMaterial();
		view.addObject("tmList", tmList);
		return view;
	}

	/**
	 * 分页查询讲义列表
	 * 
	 * @param id
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "flip", method = RequestMethod.GET)
	public ModelAndView flip(@RequestParam("page") int page, HttpSession session, LectureNotes ln) {
		session.setAttribute("currentPage", page);
		int lastPage = lectureNotesService.getCount(ln) - 1;
		if (lastPage < 0) {
			lastPage = 0;
		}
		session.setAttribute("lastPage", lastPage);
		// 如果是最后一页
		if (page == -1) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("lectureNotes", lectureNotesService.flip(lastPage));
			mv.setViewName("lectureNoteManage/mylectureNote");
			return mv;
		}
		// 如果在页码范围内,按页码打印对应信息
		if (page >= 0 && page <= lastPage) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("lectureNotes", lectureNotesService.flip(page));
			mv.setViewName("lectureNoteManage/mylectureNote");
			return mv;
		} else {// 如果不在页码范围内,打印第一页
			ModelAndView mv = new ModelAndView();
			mv.addObject("lectureNotes", lectureNotesService.flip(0));
			mv.setViewName("lectureNoteManage/mylectureNote");
			return mv;
		}
	}

	/**
	 * 创建讲义页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/lectureNoteManage")
	public String LectureNoteManage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		// 循环拿到科目
		List<Subject> sList = null;
		List<Grade> gList = null;
		List<TeachingMaterial> tmList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			gList = gradeService.getGrade();
			tmList = tMaterialService.teachingMaterial();
		} else {
			sList = subjectService.getTeacherIsSubject(currTeacher.getSubject());
			String[] gStrings = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(gStrings);
			gList = gradeService.getGradeByIds(gradeList);
			tmList = tMaterialService.getTeacherIsMate(currTeacher.getSubject());
		}
		model.addAttribute("gList", gList);
		model.addAttribute("sList", sList);
		// 循环拿到讲义类型
		// List<TaskType> tyList = taskTypeService.taskType();
		List<SeasonType> tyList = seasonTypeService.getSeasontype();
		model.addAttribute("tyList", tyList);
		// 循环拿到所属教材

		model.addAttribute("tmList", tmList);
		List<ClassNo> cList = classNoService.getClassesNo();
		List<RangeLesson> rlList = lessonService.getLessonList();
		model.addAttribute("rlList", rlList);
		model.addAttribute("cList", cList);
		return "lectureNoteManage/lecturemanage";
	}

	@RequestMapping("/addexam")
	public String AddExam() {
		return "lectureNoteManage/addexam";
	}

	/**
	 * 讲义保存
	 * 
	 * @param ln
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/save")
	public void save(LectureNotes ln, HttpServletRequest request, HttpServletResponse response) throws ParseException {

		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		TeacherAuthority ta = authorityService.findIsTeacherGradeById(currTeacher.getTeacherId(),
				ln.getGrade().toString());

		String path = request.getServletContext().getRealPath("/") + "WordDir";// 文件所在盘路径
		String pdfRoutename = ln.getPptRoute();
		if (pdfRoutename != null && pdfRoutename !="") {
			String pptName =  pdfRoutename.substring(pdfRoutename.lastIndexOf("/"), pdfRoutename.lastIndexOf("."));
			String pptpath =path + pptName + ".ppt" ;
			String pptxpath =path + pptName + ".pptx" ;
			File pptfile = new File(pptpath);
			File pptxfile = new File(pptxpath);
			if(pptfile.exists()){
				String wordpdfRoute = pdfRoutename.substring(0, pdfRoutename.lastIndexOf(".") )+".ppt" ;
				ln.setWordpdfRoute(wordpdfRoute);
			} 
			if(pptxfile.exists()){
				String wordpdfRoute = pdfRoutename.substring(0, pdfRoutename.lastIndexOf(".") )+".pptx" ;
				ln.setWordpdfRoute(wordpdfRoute);
			} 
		} 

		if ((ln.getWordRoute() == null || ln.getWordRoute() == "") && (ln.getPptRoute() == null || ln.getPptRoute()=="")) {
			output(response, JsonUtil.buildFalseJson("2", "请至少上传一种格式的文档"));
		} else {
			if (currTeacher.getTeacherType().equals("1")) {
				Date now = new Date();
				ln.setCreateTime(now);
				ln.setUsedCount(0);
				ln.setCreateUser(currTeacher.getTeacherId());
				lectureNotesService.addlectureNotes(ln);
				output(response, JsonUtil.buildFalseJson("0", "录入讲义成功!"));
			} else {
				if (!currTeacher.getSubject().equals(ln.getSubject())) {
					output(response, JsonUtil.buildFalseJson("3", "系统检测到您添加的科目与您不符合!"));
				} else if (ta == null) {
					output(response, JsonUtil.buildFalseJson("4", "系统检测到您添加的年级与您不符合!"));
				} else if (ta != null && ta.getHandoutAuthority() == 0 || ta.getHandoutAuthority() == 1) {
					output(response, JsonUtil.buildFalseJson("1", "系统检测到您没有录入讲义的权限，如有需要请及时联系管理员!"));
				} else {
					Date now = new Date();
					ln.setCreateTime(now);
					ln.setUsedCount(0);
					ln.setCreateUser(currTeacher.getTeacherId());
					lectureNotesService.addlectureNotes(ln);
					output(response, JsonUtil.buildFalseJson("0", "录入讲义成功!"));
				}
			}
		}
	}

	/**
	 * 跳转修改讲义详情页面
	 * 
	 * @return
	 */
	@RequestMapping("/see")
	public String see(Model model, Integer handoutId, Integer pageNo, HttpServletRequest request) {
		// method =RequestMethod.GET
		LectureNotes lNotes = lectureNotesService.getLectureNotes(handoutId);
		if (lNotes != null) {
			Teacher teacher = teacherService.getTeacherById(lNotes.getCreateUser());
			model.addAttribute("teacher", teacher);
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		// 循环拿到科目
		List<Subject> sList = null;
		List<Grade> gList = null;
		List<TeachingMaterial> tmList = null;
		if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
			sList = subjectService.getSubject();
			gList = gradeService.getGrade();
			tmList = tMaterialService.teachingMaterial();
		} else {
			sList = subjectService.getTeacherIsSubject(currTeacher.getSubject());
			String[] gStrings = currTeacher.getGrade().split(",");
			List gradeList = Arrays.asList(gStrings);
			gList = gradeService.getGradeByIds(gradeList);
			tmList = tMaterialService.getTeacherIsMate(currTeacher.getSubject());
		}
		RangeLesson rLesson = lessonService.getLessonById(lNotes.getKnowledgeBegin());
		RangeLesson rLesson1 = lessonService.getLessonById(lNotes.getKnowledgeEnd());
		model.addAttribute("lNotes", lNotes);
		model.addAttribute("rLesson", rLesson);
		model.addAttribute("rLesson1", rLesson1);
		// 循环拿到所属教材
		model.addAttribute("tmList", tmList);
		TeachingMaterial tm = tMaterialService.getMaterialById(lNotes.getMaterialScience());
		model.addAttribute("tm", tm);
		// 循环拿到讲义类型
		List<SeasonType> tyList = seasonTypeService.getSeasontype();
		model.addAttribute("tyList", tyList);
		// 循环拿到年级
		model.addAttribute("gList", gList);
		List<ClassNo> cList = classNoService.getClassesNo();
		model.addAttribute("cList", cList);
		List<RangeLesson> rlList = lessonService.getLessonList();
		model.addAttribute("rlList", rlList);
		model.addAttribute("sList", sList);
		model.addAttribute("page", pageNo);
		return "lectureNoteManage/seeDetails";
	}

	/**
	 * 修改讲义
	 * 
	 * @param handoutId
	 * @throws ParseException
	 */
	@RequestMapping("/updateNotes")
	public void modity(LectureNotes ln, HttpServletResponse response, HttpServletRequest request)
			throws ParseException {
		LectureNotes lNotes = lectureNotesService.getLectuseTitle(ln.getHandoutTitle());
		LectureNotes lnId = lectureNotesService.getLectureNotes(ln.getHandoutId());
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		TeacherAuthority ta = authorityService.findIsTeacherGradeById(currTeacher.getTeacherId(),
				ln.getGrade().toString());
		if (lNotes != null && lNotes.getCreateUser() != currTeacher.getTeacherId()
				&& !ln.getHandoutTitle().equals(lnId.getHandoutTitle())) {
			output(response, JsonUtil.buildFalseJson("1", "改标题已存在!"));
		} else if (lnId == null) {
			output(response, JsonUtil.buildFalseJson("3", "没有数据!"));
		} else if (!currTeacher.getTeacherId().equals(lnId.getCreateUser())
				&& !currTeacher.getTeacherType().equals("1")) {
			output(response, JsonUtil.buildFalseJson("4", "你不是讲义的创建者也不是管理员，不能对讲义进行修改!"));
		} //else if (lnId.getUsedCount() > 0) {
			//output(response, JsonUtil.buildFalseJson("5", "该讲义已被使用，暂时不能修改!"));
		//} 
	    else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				if (ln.getHandoutContent() != null && !ln.getHandoutContent().equals("")) {
					ln.setHandoutContent(ln.getHandoutContent());
				}
				lectureNotesService.updateNotes(ln);
				output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
			} else {
				if ((ta.getHandoutAuthority() == 0 || ta.getHandoutAuthority() == 1)
						&& !currTeacher.getTeacherId().equals(lnId.getCreateUser())) {
					output(response, JsonUtil.buildFalseJson("1", "对不起，您没有录入讲义的权限，如有需要请及时联系管理员!"));
				} else if (!ta.getGrade().equals(ln.getGrade().toString())
						&& !currTeacher.getTeacherId().equals(lnId.getCreateUser())) {
					output(response, JsonUtil.buildFalseJson("6", "您修改的年级与您现有年级权限不一致!"));
				} else {
					if (ln.getHandoutContent() != null && !ln.getHandoutContent().equals("")) {
						ln.setHandoutContent(ln.getHandoutContent());
					}
					lectureNotesService.updateNotes(ln);
					output(response, JsonUtil.buildFalseJson("0", "修改成功!"));
				}
			}
		}
	}

	/**
	 * 删除讲义
	 * 
	 * @param handoutId
	 * @return
	 */
	@RequestMapping("/delete")
	public void delete(Integer handoutId, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		LectureNotes ln = lectureNotesService.getLectureNotes(handoutId);

		if (ln == null) {
			output(response, JsonUtil.buildFalseJson("2", "没有数据!"));
		} else if (ln.getUsedCount() > 0) {
			output(response, JsonUtil.buildFalseJson("4", "此讲义已被使用，不能删除!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				lectureNotesService.deleteLectureNote(handoutId);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} else {
				if (currTeacher.getTeacherId() != ln.getCreateUser()) {
					output(response, JsonUtil.buildFalseJson("3", "您不是此讲义的创建者，也不是超级管理员，不能删除!"));
				} else {
					lectureNotesService.deleteLectureNote(handoutId);
					output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
				}
			}
		}
	}

	/**
	 * 共享讲义
	 * 
	 * @param handoutId
	 * @param request
	 * @param ln
	 * @param view
	 * @return
	 */
	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public void share(Integer handoutId, Integer isTrue, HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		LectureNotes lNotes = lectureNotesService.getLectureNotes(handoutId);
		if (lNotes != null && lNotes.getUsedCount() > 0 && lNotes.getIsPublic() != 0) {
			output(response, JsonUtil.buildFalseJson("2", "该讲义已被使用，暂时不能私有!"));
		} else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				if (isTrue == 1) {
					lNotes.setIsPublic(isTrue);
					lectureNotesService.updateIsPublic(lNotes);
					output(response, JsonUtil.buildFalseJson("0", "共享成功!"));
				} else {
					lNotes.setIsPublic(isTrue);
					lectureNotesService.updateIsPublic(lNotes);
					output(response, JsonUtil.buildFalseJson("0", "取消共享成功!"));
				}
			} else {
				if (lNotes.getCreateUser() != currTeacher.getTeacherId()) {
					output(response, JsonUtil.buildFalseJson("1", "您没有修改讲义的权限!"));
				} else {
					if (isTrue == 1) {
						lNotes.setIsPublic(isTrue);
						lectureNotesService.updateIsPublic(lNotes);
						output(response, JsonUtil.buildFalseJson("0", "共享成功!"));
					} else {
						lNotes.setIsPublic(isTrue);
						lectureNotesService.updateIsPublic(lNotes);
						output(response, JsonUtil.buildFalseJson("0", "取消共享成功!"));
					}
				}
			}
		}
	}
	
	/**
	 * 停用/启用讲义
	 * @param request
	 * @param response
	 * @param handoutId
	 * @param isStart
	 */
	@RequestMapping("/changeexamstatus")
	public void changeExamStatus(HttpServletRequest request,HttpServletResponse response,Integer handoutId,Integer isStart){
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		LectureNotes lNotes = lectureNotesService.getLectureNotes(handoutId);
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
					lNotes.setIsStart(isStart);
					lectureNotesService.updateIsStart(lNotes);
					output(response, JsonUtil.buildFalseJson("0", "更新讲义状态成功!"));
				
			} else {
				if (lNotes.getCreateUser() != currTeacher.getTeacherId()) {
					output(response, JsonUtil.buildFalseJson("1", "您没有修改讲义的权限!"));
				} else {
					
						lNotes.setIsStart(isStart);
						lectureNotesService.updateIsStart(lNotes);
						output(response, JsonUtil.buildFalseJson("0", "更新讲义成功!"));
					
				}
			}
		
	}

	@RequestMapping("/seeHandoutContent")
	public ModelAndView seeHandoutContent(Integer handoutId) {

		ModelAndView mv = new ModelAndView();
		LectureNotes ln = lectureNotesService.getNotesSubject(handoutId);
		Teacher teacher = teacherService.getTeacherById(ln.getCreateUser());
		mv.addObject("ln", ln);
		mv.addObject("teacher", teacher);
		mv.setViewName("lectureNoteManage/leHandoutContent");
		return mv;
	}

	/**
	 * word下载
	 * 
	 * @param wordRoute
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/wordDownload")
	public ResponseEntity<byte[]> wordDownload(String wordRoute, HttpServletRequest request) throws IOException {
		/**
		 * 截取指定字符串方法一
		 */
		// String [] my = wordRoute.split("/");
		// System.out.println(my[my.length-1]);
		/**
		 * 截取指定字符串方法二
		 */
		String myFile = wordRoute.substring(wordRoute.indexOf("WordDir/") + 8, wordRoute.length());
		System.out.println(myFile);
		String path = request.getServletContext().getRealPath("/WordDir/") + myFile;// 文件所在盘路径
		File file = new File(path);
		String fileName = new String(myFile.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		HttpHeaders headers = new HttpHeaders();
		// fileName.substring(19,fileName.length())
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

	/**
	 * PPT下载
	 * 
	 * @param pptRoute
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/pptDownload")
	public ResponseEntity<byte[]> pptDownload(String pptRoute, HttpServletRequest request) throws IOException {
		/**
		 * 截取指定字符串方法一
		 */
		// String [] my = wordRoute.split("/");
		// System.out.println(my[my.length-1]);
		/**
		 * 截取指定字符串方法二
		 */
		String myFile = pptRoute.substring(pptRoute.indexOf("PPtDir/") + 8, pptRoute.length());
		System.out.println(myFile);
		String path = request.getServletContext().getRealPath("/PPtDir/") + myFile;// 文件所在盘路径
		File file = new File(path);
		String fileName = new String(myFile.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		HttpHeaders headers = new HttpHeaders();
		// fileName.substring(19,fileName.length())
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
}
