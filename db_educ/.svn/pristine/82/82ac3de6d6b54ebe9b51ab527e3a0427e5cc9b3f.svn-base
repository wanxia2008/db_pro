package com.db.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.ClassNo;
import com.db.entity.ClassRecord;
import com.db.entity.Classroom;
import com.db.entity.Grade;
import com.db.entity.PaperInfo;
import com.db.entity.SchoolYear;
import com.db.entity.SchoolZone;
import com.db.entity.SeasonType;
import com.db.entity.Subject;
import com.db.entity.Task;
import com.db.entity.TaskRule;
import com.db.entity.TaskType;
import com.db.entity.Teacher;
import com.db.entity.TeachingMaterial;
import com.db.service.ClassNoService;
import com.db.service.ClassRecordService;
import com.db.service.ClassroomService;
import com.db.service.CourseService;
import com.db.service.GradeService;
import com.db.service.PaperInfoService;
import com.db.service.SchoolYearService;
import com.db.service.SchoolZoneService;
import com.db.service.SeasonTypeService;
import com.db.service.StudentCareerService;
import com.db.service.StudentService;
import com.db.service.SubjectService;
import com.db.service.TaskRuleService;
import com.db.service.TaskService;
import com.db.service.TaskTypeService;
import com.db.service.TeachingMaterialService;
import com.db.service.TestRecordService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseUtil {

	@Resource
	private TaskService taskService;
	@Resource
	private TaskTypeService taskTypeService;
	@Resource
	private TaskRuleService taskRuleService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private GradeService gradeservice;
	@Resource
	private TeachingMaterialService teachingMaterialservice;
	@Resource
	private SeasonTypeService seasontypeservice;
	@Resource
	private SchoolYearService schoolyearservice;
	@Resource
	private TaskRuleService taskruleservice;
	@Resource
	private CourseService courseService;
	@Resource
	private ClassNoService classnoservice;
	@Resource
	private PaperInfoService paperinfoservice;
	@Resource
	private StudentService studentService;
	@Resource
	private TestRecordService testRecordService;
	@Resource
	private StudentCareerService studentCareerService;
	@Resource
	private ClassroomService classroomService;
	@Resource
	private SchoolZoneService schoolZoneService;
	@Resource
	private ClassRecordService ClassRecordService;

	/**
	 * 我的任务列表
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/showmytask")
	public ModelAndView showMytask(HttpServletRequest request,Integer taskType,String taskTitle,Integer pageNo, Integer pageSize)
			throws ParseException {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		ModelAndView mv = new ModelAndView();
		int rowsCount = 0; // 获得总条数
		int totalPages = 0;
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		if (currTeacher.getTeacherType().equals("1")) {// 超级管理员能看所有的任务
			List<Task> tList = taskService.getTaskList((pageNo - 1) * pageSize, pageSize, taskType, taskTitle);
			rowsCount = taskService.getCount(taskType, taskTitle);
			if (!tList.isEmpty() && tList != null) {
				mv.addObject("tList", tList);
			} else {
				mv.addObject("tList", null);
			}
		} else {// 只能看自己的任务
			List<Task> tList = taskService.taskList(currTeacher.getTeacherId(), (pageNo - 1) * pageSize, pageSize,
					taskType, taskTitle);
			rowsCount = taskService.seeGetCount(taskType, taskTitle, currTeacher.getTeacherId());
			if (!tList.isEmpty() && tList != null) {
				mv.addObject("tList", tList);
			} else {
				mv.addObject("tList", null);
			}
		}
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		List<Subject> sList = subjectService.getSubject();
		List<TaskType> tyList = taskTypeService.getTypeList();
		mv.addObject("ttList", tyList);
		mv.addObject("sList", sList);
		mv.addObject("page", pageNo);
		mv.addObject("taskType", taskType);
		mv.addObject("taskTitle", taskTitle);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.setViewName("taskmanage/mytask");
		return mv;
	}

	/**
	 * 新增任务页面
	 * 
	 * @return
	 */
	@RequestMapping("/newmytask")
	public String newMytask(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<SchoolZone> sZones = null;
		List<Subject> sList = null;
		List<Grade> grades = null;
		if (currTeacher.getTeacherType().equals("1")) {
			sZones = schoolZoneService.getAllSchool();
			sList = subjectService.getSubject();
			grades = gradeservice.getGrade();
		}else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				sList = subjectService.getSubject();
				grades = gradeservice.getGrade();
			}else {
				sList = subjectService.getSubjectById(currTeacher.getSubject());
				String [] grade = currTeacher.getGrade().split(",");
				List gradeList = Arrays.asList(grade);
				grades = gradeservice.getGradeByIds(gradeList);
			}
			sZones = schoolZoneService.findSchoolList(currTeacher.getCampus());
		}
		List<TaskType> tyList = taskTypeService.getTypeList();
		model.addAttribute("tyList", tyList);
		List<TaskRule> trList = taskRuleService.taskRule();
		model.addAttribute("trList", trList);
		model.addAttribute("sList", sList);
		model.addAttribute("gradeList", grades);
		List<TeachingMaterial> materials = teachingMaterialservice.teachingMaterial();
		model.addAttribute("scienceList", materials);
		List<Classroom>  cmList = classroomService.getClassroom1();
		List<SchoolYear> schoolYears = schoolyearservice.getYear();
		model.addAttribute("yearList", schoolYears);
		List<SeasonType> seasonTypes = seasontypeservice.getSeasontype();
		model.addAttribute("seasonList", seasonTypes);
		List<TaskRule> rules = taskruleservice.taskRule();
		model.addAttribute("roleList", rules);
		model.addAttribute("cmList", cmList);
		model.addAttribute("sZones", sZones);
		return "taskmanage/newtask";
	}

	/**
	 * 保存新增任务
	 * 
	 * @param task
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/saveTask", method = RequestMethod.POST)
	public ModelAndView saveTask(Task t, HttpServletRequest request, Model model, Integer pageNo, Integer pageSize)
			throws ParseException {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		// 保存任务
		t.setCreateTime(new Date());
		t.setTaskStatus(0);
		t.setCreateUser(currTeacher.getTeacherId());
		taskService.addTask(t);// 返回主键
		return this.showMytask(request, null, null, pageNo, pageSize);
	}
	
	//启动任务
	@RequestMapping("/startTask")
	public void startTask(HttpServletResponse response,Integer taskId){
		Task task = taskService.getTaskById(taskId);
		if (task != null) {
			// 试卷使用总数加一
			PaperInfo info = paperinfoservice.getPaperInfoById(task.getTaskCount());
			if (info != null) {
				info.setUpdateTime(new Date());
				if (String.valueOf(info.getUsedTimes()) != null) {
					info.setUsedTimes(info.getUsedTimes() + 1);
				} else {
					info.setUsedTimes(1);
				}
				paperinfoservice.updateUsertimes(info);// 更新试卷引用数
			}
			if (task.getTaskObjectClass() != null) {
				String[] classnos = task.getTaskObjectClass().split(",");
				for (int i = 0; i < classnos.length; i++) {
					ClassRecord course = ClassRecordService.getPaperById(Integer.valueOf(classnos[i]),Integer.valueOf( task.getTaskCount()), null, null);
					if (course == null) {
						// 根据班级编号往课程表李添加数据
						course = new ClassRecord();
						ClassNo classNo = classnoservice.getClassById(Integer.valueOf(classnos[i]));
						course.setCreateTime(new Date());
						course.setClassId(classNo.getClassId());
						course.setStatus(1);
						course.setTaskId(task.getTaskId());
						course.setPaperId(task.getTaskCount());
						ClassRecordService.addClassRecord(course);
					} else {
						course.setUpdateTime(new Date());
						course.setStatus(1);// 任务
						course.setPaperId(task.getTaskCount());
						ClassRecordService.updateStatus(course);
						
					}
				}
			}
			task.setUpdateTime(new Date());
			task.setTaskStatus(1);
			taskService.updateTask(task);
			output(response, JsonUtil.buildFalseJson("0", "启动成功!"));
		}else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	//结束任务
	@RequestMapping("/endTask")
	public void endTask(HttpServletResponse response,Integer taskId){
		Task task = taskService.getTaskById(taskId);
		if (task == null) {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}else {
			task.setUpdateTime(new Date());
			task.setTaskStatus(2);
			taskService.updateTask(task);
			output(response, JsonUtil.buildFalseJson("0", "结束成功!"));
		}
	}
	
	/**
	 * 查看或编辑任务详情页面
	 */
	@RequestMapping("/updatemytask")
	public String updateMytask(Integer taskId, Model model,HttpServletRequest request) {
		Task task = taskService.getTaskById(taskId);
		String clasnos = null;
		if (task.getTaskObjectClass() != null) {
			clasnos = task.getTaskObjectClass();
		}
		String tags = task.getTaskObjectClass();
		if (tags != null) {
			String[] tag = tags.split(",");
			List list = Arrays.asList(tag);
			List<ClassNo> taglist = classnoservice.getClassList(list);
			task.setClassNos(taglist);
		}
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		List<SchoolZone> sZones = null;
		List<Subject> sList = null;
		List<Grade> grades = null;
		if (currTeacher.getTeacherType().equals("1")) {
			sZones = schoolZoneService.getAllSchool();
			sList = subjectService.getSubject();
			grades = gradeservice.getGrade();
		}else {
			if (!currTeacher.getTeacherType().equals("5") && !currTeacher.getTeacherType().equals("6")) {
				sList = subjectService.getSubject();
				grades = gradeservice.getGrade();
			}else {
				sList = subjectService.getSubjectById(currTeacher.getSubject());
				String [] grade = currTeacher.getGrade().split(",");
				List gradeList = Arrays.asList(grade);
				grades = gradeservice.getGradeByIds(gradeList);
			}
			sZones = schoolZoneService.findSchoolList(currTeacher.getCampus());
		}
		model.addAttribute("task", task);
		List<TaskType> tkList = taskTypeService.taskType();
		List<TaskRule> trList = taskRuleService.taskRule();
		model.addAttribute("trList", trList);
		model.addAttribute("tkList", tkList);
		model.addAttribute("sZones", sZones);
		model.addAttribute("sList", sList);
		model.addAttribute("gradeList", grades);
		List<TeachingMaterial> materials = teachingMaterialservice.teachingMaterial();
		model.addAttribute("scienceList", materials);
		List<SchoolYear> schoolYears = schoolyearservice.getYear();
		model.addAttribute("yearList", schoolYears);
		List<SeasonType> seasonTypes = seasontypeservice.getSeasontype();
		model.addAttribute("seasonList", seasonTypes);
		List<TaskRule> rules = taskruleservice.taskRule();
		model.addAttribute("roleList", rules);
		List<Classroom> cmList = classroomService.getClassroom1();
		model.addAttribute("cmList", cmList);
		return "taskmanage/updatetask";
	}

	/**
	 * 保存修改的任务
	 * 
	 * @param request
	 * @param taskId
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/updateTask", method = RequestMethod.POST)
	public ModelAndView updateTask(HttpServletRequest request, Task task, Integer pageNo, Integer pageSize)
			throws ParseException {
		Task tk = taskService.getTaskById(task.getTaskId());
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		// 修改任务内容
		task.setUpdateTime(new Date());
		taskService.updateTask(task);
		if (tk.getTaskObjectClass() != null) {
			String[] classnos = tk.getTaskObjectClass().split(",");
			for (int j = 0; j < classnos.length; j++) {
				ClassRecord course = ClassRecordService.getPaperById(Integer.valueOf(classnos[j]),
						Integer.valueOf(task.getTaskCount()), null, null);
				if (course != null) {
					course.setUpdateTime(new Date());
					course.setPaperId(task.getTaskCount());
					course.setStatus(1);
					ClassRecordService.updateStatus(course);
				}
			}
		}
		return this.showMytask(request, null, null, pageNo, pageSize);
	}

	/**
	 * 删除任务
	 * 
	 * @param taskId
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/deleteTsk", method = RequestMethod.POST)
	public void deleteTsk(Integer taskId, HttpServletResponse response, HttpServletRequest request)
			throws ParseException {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		Task task = taskService.getTaskById(taskId);
		if (currTeacher.getTeacherId().equals(1) && task.getCreateUser() != currTeacher.getTeacherId()) {
			output(response, JsonUtil.buildFalseJson("3", "对不起，您没有删除任务的权限!"));
		} else {
			try {
				ClassRecord record = ClassRecordService.getPaperById(Integer.valueOf(task.getTaskObjectClass()), task.getTaskCount(), null, null);
				ClassRecordService.deleteClassRecordById(record);
				taskService.deleteTaskById(taskId);
				output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
			} catch (Exception e) {
				output(response, JsonUtil.buildFalseJson("2", "删除失败!"));
			}
		}
	}

	/**
	 * 添加任务前先判断该日期的课程是否已经安排
	 * 
	 * @param date
	 * @param classid
	 * @param response
	 */
	@RequestMapping("courseisexist")
	public void courseisexist(String date, String classid, String start, Integer examId, Integer type,
			HttpServletResponse response, Integer ruleId) {
		List<Task> task = taskService.getRuleId(ruleId);
		if (task != null && !task.isEmpty()) {
			output(response, JsonUtil.buildFalseJson("0", "可以进行下一步"));
			// for (Task task2 :task) {
			// if (task2.getRule()==1) {
			// output(response, JsonUtil.buildFalseJson("1", "该任务规则已被使用!"));
			// }else {
			// output(response, JsonUtil.buildFalseJson("0", "可以进行下一步"));
			// }
			// }
		} else {
			output(response, JsonUtil.buildFalseJson("0", "可以进行下一步"));
		}
	}

	/**
	 * 取出不同的部分
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static <T> List<T> compare(T[] str1, T[] str2) {
		List<T> list1 = Arrays.asList(str1);
		List<T> list2 = new ArrayList<T>();
		for (T t : str2) {
			if (!list1.contains(t)) {
				list2.add(t);
			}
		}
		return list2;
	}
}
