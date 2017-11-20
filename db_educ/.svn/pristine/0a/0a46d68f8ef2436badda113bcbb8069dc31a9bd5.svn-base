package com.db.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.ClassNo;
import com.db.entity.Student;
import com.db.entity.StudentCareer;
import com.db.entity.TestRecord;
import com.db.entity.VerificationCode;
import com.db.entity.utilentity.StudentCareeHistory;
import com.db.entity.utilentity.SubjectPaper;
import com.db.entity.utilentity.SubjectsPapers;
import com.db.service.ClassNoService;
import com.db.service.StudentCareerService;
import com.db.service.StudentService;
import com.db.service.TestRecordService;
import com.db.service.VerificationCodeService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.MD5Util;

@Controller
@RequestMapping("/app/student")
public class APPStudentController extends BaseUtil {

	@Resource
	private StudentService studentservice;
	@Resource
	private ClassNoService classnoservice;
	@Resource
	private VerificationCodeService verificationCodeService;
	@Resource
	private StudentCareerService studentcareerservice;
	@Resource
	private TestRecordService testrecordService;

	/**
	 * 前端修改头像
	 * 
	 * @param request
	 */
	@RequestMapping("modityHeadImage")
	public void modityHeadImage(HttpServletResponse response, Integer studentId, String headImage) {
		Student student = studentservice.getStudentById(studentId);
		if (student != null) {
			student.setHeadImage(headImage);
			student.setUpdateTime(new Date());
			studentservice.updateStudentById(student);
			output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "用户不存在!"));
		}
	}

	/**
	 * 查看个人详情
	 * 
	 * @param response
	 * @param studentId
	 */
	@RequestMapping("/studentDateills")
	public void dateills(HttpServletResponse response, Integer studentId) {
		List<StudentCareer> careers = studentcareerservice.getStuCareerByStu(studentId);
		String classno = "";
		Student student = studentservice.studentList(studentId);
		for (StudentCareer career : careers) {
			classno += career.getClassId() + ",";
		}
		if (classno != "") {
			classno = classno.substring(0, classno.length() - 1);
			// String classno = student.getClassNo();
			List<ClassNo> classNos;
			List<ClassNo> classNos1 = null;
			List classlist;
			student.setClassNo(classno);
			if (classno != null) {
				if (classno.indexOf(",") >= 0) {
					classlist = Arrays.asList(classno.split(","));
					classNos = classnoservice.getClassByClassnos(classlist, null);
				} else {
					classlist = Arrays.asList(classno + ",");
					classNos = classnoservice.getClassByClassnos(null, Integer.valueOf(classno));
				}
				List list = new ArrayList<>();
				int index = 0, seasontype = 0;// 用来判断seasontype是否相等
				for (ClassNo no : classNos) {
					index++;
					List<ClassNo> list2 = new ArrayList<>();
					Map<String, Object> map = new HashMap<>();
					if (seasontype != no.getSeasonType()) {
						classNos1 = classnoservice.getClassBySeasonType(classlist, no.getSeasonType());
						for (ClassNo no2 : classNos1) {
							list2.add(no2);
						}
						map.put("classmesg", list2);
						map.put("seasonType", no.getSeasonType());
						map.put("year", no.getYear());
						if (classNos.size() > 1) {
							list.add(map);
							seasontype = no.getSeasonType();
						}
					}
					if (classNos.size() == 1) {
						list.add(map);
					}
				}
				student.setClassNos(list);
			}
		}
		List<Student> sList = new ArrayList<>();
		sList.add(student);
		if (sList != null && !sList.isEmpty()) {
			output(response, JsonUtil.buildJson(sList));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 修改年龄
	 * 
	 * @param response
	 * @param studentId
	 * @param age
	 */
	@RequestMapping("/modityAge")
	public void modityAge(HttpServletResponse response, Integer studentId, int age) {
		Student student = studentservice.getStudentById(studentId);
		if (student != null) {
			student.setAge(age);
			student.setUpdateTime(new Date());
			studentservice.updateAge(student);
			output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 修改用户名
	 * 
	 * @param response
	 * @param studentId
	 * @param studentName
	 */
	@RequestMapping("modityStudentName")
	public void modityStudentName(HttpServletResponse response, Integer studentId, String studentName) {
		Student student = studentservice.getStudentById(studentId);
		if (student != null) {
			student.setUpdateTime(new Date());
			student.setStudentName(studentName);
			studentservice.updateStudentName(student);
			output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param response
	 * @param studentId
	 * @param password
	 */
	@RequestMapping("/modityPassword")
	public void modityPassword(HttpServletResponse response, Integer studentId, String password) {
		Student student = studentservice.getStudentById(studentId);
		if (student != null) {
			student.setUpdateTime(new Date());
			student.setPassword(MD5Util.MD5Encode(password, "utf-8"));
			studentservice.updatePassword(student);
			output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 修改就读学校
	 * 
	 * @param response
	 * @param studentId
	 * @param attendSchool
	 */
	@RequestMapping("/modityAttendSchool")
	public void modityAttendSchool(HttpServletResponse response, Integer studentId, String attendSchool) {
		Student student = studentservice.getStudentById(studentId);
		if (student != null) {
			student.setUpdateTime(new Date());
			student.setAttendSchool(attendSchool);
			studentservice.updateAttendSchool(student);
			output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 修改意向学校
	 * 
	 * @param response
	 * @param studentId
	 * @param intentionalSchool
	 */
	@RequestMapping("/modityIntentional")
	public void modityIntentional(HttpServletResponse response, Integer studentId, String intentionalSchool) {
		Student student = studentservice.getStudentById(studentId);
		if (student != null) {
			student.setUpdateTime(new Date());
			student.setIntentionalSchool(intentionalSchool);
			studentservice.updateIntentionalSchool(student);
			output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}
	
	/**
	 * 修改年级
	 * @param response
	 * @param studentId
	 * @param grade
	 */
	@RequestMapping("/modifygrade")
	public void modifyGrade(HttpServletResponse response,Integer studentId,Integer grade){
		Student student = studentservice.getStudentById(studentId);
		if(student != null){
			 student.setUpdateTime(new Date());
			 student.setGrade(grade);
			 studentservice.updateGrade(student);
			 output(response, JsonUtil.buildFalseJson("0","编辑成功"));
		}else{
			output(response, JsonUtil.buildFalseJson("1","没有数据！"));
		}
	}
	

	/**
	 * 修改电话
	 * 
	 * @param response
	 * @param studentId
	 * @param phone
	 */
	@RequestMapping("modityPhone")
	public void modityPhone(HttpServletResponse response, Integer studentId, String phone, String code) {
		VerificationCode vCode = verificationCodeService.confirmCode(phone);
		Student studentPhone = studentservice.getStudentByPhone(phone);
		Date now = new Date();
		if (vCode != null) {
			if (!code.equals(vCode.getMessageCode())) {
				output(response, JsonUtil.buildFalseJson("2", "验证码不正确!"));
			} else if ((now.getTime() - vCode.getCreateTime().getTime()) > 300 * 100) {
				output(response, JsonUtil.buildFalseJson("3", "验证码已过期!"));
			} else if (studentPhone != null) {
				output(response, JsonUtil.buildFalseJson("4", "该号码已存在!"));
			} else {
				Student student = studentservice.getStudentById(studentId);
				if (student != null) {
					student.setUpdateTime(new Date());
					student.setPhone(phone);
					studentservice.updatePhone(student);
					output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
				} else {
					output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
				}
			}
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
		}
	}

	/**
	 * 修改账号
	 * 
	 * @param response
	 * @param studentId
	 * @param userName
	 */
	@RequestMapping("/modityUserName")
	public void modityUserName(HttpServletResponse response, Integer studentId, String userName) {
		Student stu = studentservice.getStudentByUserName(userName);
		if (stu != null) {
			output(response, JsonUtil.buildFalseJson("2", "该账号已存在!"));
		} else {
			Student student = studentservice.getStudentById(studentId);
			if (student != null) {
				student.setUpdateTime(new Date());
				student.setUserName(userName);
				studentservice.updateUserName(student);
				output(response, JsonUtil.buildFalseJson("0", "编辑成功!"));
			} else {
				output(response, JsonUtil.buildFalseJson("1", "没有数据!"));
			}
		}
	}

	@RequestMapping("/modifyJiaZhangInfo")
	public void modifyJiaZhangInfo(HttpServletResponse response, Integer studentId, String custodian1Relationship,
			String custodian1Name, String custodian1Phone, String custodian2Relationship, String custodian2Name,
			String custodian2Phone) {
		Student student = studentservice.getStudentById(studentId);
		if (student != null) {
			if (custodian1Relationship != null) {
				student.setCustodian1Relationship(custodian1Relationship);
			}
			if (custodian1Name != null) {
				student.setCustodian1Name(custodian1Name);
			}
			if (custodian1Phone != null) {
				student.setCustodian1Phone(custodian1Phone);
			}
			if (custodian2Relationship != null) {
				student.setCustodian2Relationship(custodian2Relationship);
			}
			if (custodian2Name != null) {
				student.setCustodian2Name(custodian2Name);
			}
			if (custodian2Phone != null) {
				student.setCustodian2Phone(custodian2Phone);
			}
			studentservice.updateJiangZhangInfo(student);
			output(response, JsonUtil.buildFalseJson("0", "编辑家长信息成功"));
		} else {
			output(response, JsonUtil.buildFalseJson("1", "系统找不到该用户!"));
		}
	}

	/**
	 * 修改喜欢的课程
	 * 
	 * @param response
	 * @param studentId
	 * @param subject
	 */
	@RequestMapping("moditySubject")
	public void moditySubject(HttpServletResponse response, Integer studentId, String subject) {
		Student st = studentservice.getStudentById(studentId);
		if (st != null) {
			st.setSubjectType(subject);
			studentservice.updateSubject(st);
			Student student = studentservice.getStudentById(st.getStudentId());
			if (student != null) {
				List<Student> sList = new ArrayList<Student>();
				sList.add(student);
				output(response, JsonUtil.buildJson(sList));
			} else {
				output(response, JsonUtil.buildFalseJson("1", "没有该用户!"));
			}
		} else {
			output(response, JsonUtil.buildFalseJson("1", "没有该用户!"));
		}
	}

	// 获取我考过的期中和期末试卷名称
	@RequestMapping("/studentPapers")
	public void findStudentPaperHistory(HttpServletResponse response, Integer studentId) {

		Student student = studentservice.studentList(studentId);
		// 获取当前学员所有的年--季
		List<StudentCareer> yearSeasonGroup = studentcareerservice.getCareerGroupByYearAndSeason(studentId);
		StudentCareeHistory scHistory = new StudentCareeHistory();
		List<StudentCareeHistory> schList = new ArrayList<StudentCareeHistory>();
		if (yearSeasonGroup == null || yearSeasonGroup.isEmpty()) {
			scHistory.setHeadImage(student.getHeadImage());
			scHistory.setRealName(student.getStudentName());
			scHistory.setStudentId(student.getStudentId());
		} else {
			for (StudentCareer sc : yearSeasonGroup) {
				scHistory.setHeadImage(student.getHeadImage());
				scHistory.setRealName(student.getStudentName());
				scHistory.setStudentId(student.getStudentId());
				// 根据当前年--季去查找所有的班级
				List<StudentCareer> careers = studentcareerservice.getCareerBySIdAndYearSeason(studentId, sc.getYear(),
						sc.getSeasonType());
				for (StudentCareer career : careers) {
					// 拿到当前班级去查找做过的期中和期末试卷
					List<TestRecord> testRList = testrecordService.findMyQizhongAndQimoTestRecord(studentId,
							career.getClassId());
					List<SubjectPaper> sjpList = new ArrayList<SubjectPaper>();
					List<SubjectsPapers> sjpsList = new ArrayList<SubjectsPapers>();
					SubjectPaper sbjPaper = new SubjectPaper();
					if (testRList == null||testRList.isEmpty()) {
						sjpList = null;
					} else {
						for (TestRecord record : testRList) {
							sbjPaper.setYearAndSeason(
									sc.getSchoolYear().getYear() + "-" + sc.getSeasonType2().getSeasonName());
							SubjectsPapers subjtPapres = new SubjectsPapers();
							subjtPapres.setPaperId(record.getPaperId());
							subjtPapres.setClassId(record.getClassId());
							subjtPapres.setClassName(career.getClassNo().getClassName());
							subjtPapres.setSubjectName(record.getSubject().getSubjectName());
							if (record.getPaperType() == 2) {
								subjtPapres.setPaperType("期中考试");
								sjpsList.add(subjtPapres);
								sbjPaper.setSbjsPaper(sjpsList);
							} else if (record.getPaperType() == 3) {
								subjtPapres.setPaperType("期末考试");
								sjpsList.add(subjtPapres);
								sbjPaper.setSbjsPaper(sjpsList);
							}
						}
						sjpList.add(sbjPaper);
						scHistory.setSubPaperList(sjpList);
					}
				}
			}
		}
		schList.add(scHistory);
		output(response, JsonUtil.buildJson(schList));
	}
}
