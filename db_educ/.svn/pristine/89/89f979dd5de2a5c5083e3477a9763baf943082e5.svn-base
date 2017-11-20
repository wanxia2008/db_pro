package com.db.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.db.action.ExamController;
import com.db.entity.ClassNo;
import com.db.entity.ClassRecord;
import com.db.entity.PaperInfo;
import com.db.entity.StudentCareer;
import com.db.entity.Task;
import com.db.entity.TestRecord;
import com.db.service.ClassNoService;
import com.db.service.ClassRecordService;
import com.db.service.PaperInfoService;
import com.db.service.StudentCareerService;
import com.db.service.TaskService;
import com.db.service.TestRecordService;

public class TaskMarkTimer {

	@Autowired
	private TaskService taskService;
	@Resource
	private ClassRecordService ClassRecordService;
	@Resource
	private ClassNoService classnoservice;
	@Resource
	private PaperInfoService paperinfoservice;
	@Resource
	private StudentCareerService studentCareerService;
	@Resource
	private TestRecordService testRecordService;

	private Logger log = Logger.getLogger(ExamController.class);

	public void calculate() throws ParseException {
		List<Task> taskList = taskService.getStarTaskList();
		log.info("进入任务列表!");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (taskList != null && !taskList.isEmpty()) {
			for (Task ta : taskList) {
				log.info("开始启动任务!");
				if (sdf.parse(sdf.format(date)).getTime() >= sdf.parse(sdf.format(ta.getCreateTime())).getTime()) {
					if (ta.getTaskObjectClass() != null) {
						String[] classnos = ta.getTaskObjectClass().split(",");
						for (int i = 0; i < classnos.length; i++) {
							ClassRecord course = ClassRecordService.getPaperById(Integer.valueOf(classnos[i]),
									Integer.valueOf(ta.getTaskCount()), null, null);
							ClassNo classNo = classnoservice.getClassById(Integer.valueOf(classnos[i]));
							if (course == null) {
								// 根据班级编号往课程表李添加数据
								course = new ClassRecord();
								course.setCreateTime(new Date());
								course.setClassId(classNo.getClassId());
								course.setStatus(1);
								course.setTaskId(ta.getTaskId());
								course.setPaperId(ta.getTaskCount());
								ClassRecordService.addClassRecord(course);
							} else {
								course.setUpdateTime(new Date());
								course.setStatus(1);// 任务
								course.setPaperId(ta.getTaskCount());
								ClassRecordService.updateStatus(course);
							}
							// 前端开启每个学员考试试卷
							List<StudentCareer> students = studentCareerService
									.getStudentByClassNo(Integer.valueOf(classnos[i]), 2);//
							if (students == null || students.isEmpty()) {
								log.info("没有知道要开启考试的学员!");
							} else {
								TestRecord record1 = new TestRecord();
								for (int j = 0; j < students.size(); j++) {
									log.info("进入开启学员任务!");
									int studentId = students.get(j).getStudentId();
									record1.setStudentId(studentId);
									record1.setSubjectId(classNo.getSubject());
									record1.setPaperId(ta.getTaskCount());
									record1.setClassId(Integer.valueOf(classnos[i]));
									record1.setPaperType(ta.getTaskType());
									record1.setGradeId(classNo.getGrade());
									record1.setChoiceId(classNo.getSchoolArea());
									record1.setStatus(1);// 启动
									record1.setCreateTime(new Date());
									testRecordService.addTestrecord(record1);// 添加到考试记录表，返回主键
									log.info("成功开启!"+j+"个学员");
								}
							}
							// 试卷使用总数加一
							PaperInfo info = paperinfoservice.getPaperInfoById(ta.getTaskCount());
							if (info != null) {
								info.setUpdateTime(new Date());
								if (String.valueOf(info.getUsedTimes()) != null) {
									info.setUsedTimes(info.getUsedTimes() + 1);
								} else {
									info.setUsedTimes(1);
								}
								paperinfoservice.updateUsertimes(info);// 更新试卷引用数
							}
							ta.setUpdateTime(new Date());
							ta.setTaskStatus(1);
							ta.setTaskId(ta.getTaskId());
							taskService.updateTask(ta);
							log.info("启动任务结束!");
						}
					} else {
						log.info("任务班级不存在!");
					}
				} else {
					log.info("当前时间段没有找到要开启的任务!");
				}
			}
		} else {
			log.info("没有要开启的任务!");
		}
	}
}
