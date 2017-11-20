package com.db.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.Student;
import com.db.entity.Subject;
import com.db.entity.VerificationCode;
import com.db.entity.SchoolZone;
import com.db.service.StudentService;
import com.db.service.SubjectService;
import com.db.service.TeacherService;
import com.db.service.VerificationCodeService;
import com.db.service.SchoolZoneService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;
import com.db.util.MD5Util;
import com.db.util.PhoneMessageUtil;
import com.db.util.SendMessageCode;

@Controller
@RequestMapping("/app/user")
public class APPUserController extends BaseUtil{
	
	@Resource
	private TeacherService teacherService;
	@Resource
	private StudentService studentService;
	@Resource
	private VerificationCodeService verificationCodeService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private SchoolZoneService schoolzoneService;
	
	/**
	 * 用户注册
	 * 
	 * @param response
	 * @param student
	 * @param phone
	 * @param code
	 */
	@RequestMapping("/studentRegist")
	public void studentRegist(HttpServletResponse response, Student student, String code) {
		VerificationCode returnCode = verificationCodeService.getCodeByPhone(student.getPhone());
		Date now = new Date();
		if (returnCode != null) {
			if ((now.getTime() - returnCode.getCreateTime().getTime()) > 300 * 1000) {
				output(response, JsonUtil.buildFalseJson("3", "验证码已过期!"));
			} else if (!code.equals(returnCode.getMessageCode())) {
				output(response, JsonUtil.buildFalseJson("4", "验证码不正确!"));
			} else {
				Student stu = studentService.getStudentByUserName(student.getUserName());
				Student stuPhone = studentService.getStudentByPhone(student.getPhone());
				if (stu != null) {
					output(response, JsonUtil.buildFalseJson("1", "用户名已存在!"));
				} else if (stuPhone != null) {
					output(response, JsonUtil.buildFalseJson("6", "手机号码已存在!"));
				} else {
					student.setCreateTime(now);
					student.setPassword(MD5Util.MD5Encode(student.getPassword(), "utf-8"));
					student.setStudentStatus(1);
					studentService.addNewStudent(student);// 该方法会返回插入的主键
					Student stud = studentService.getStudentById(student.getStudentId());// 拿到主键ID返回新增的记录
					if (stud == null) {
						output(response, JsonUtil.buildFalseJson("2", "注册失败!"));
					} else {
						List<Student> stuL = new ArrayList<Student>();
						stuL.add(stud);
						output(response, JsonUtil.buildJson(stuL));
					}
				}
			}
		} else {
			output(response, JsonUtil.buildFalseJson("5", "手机号不正确!"));
		}
	}

	/**
	 * 注册之后跳转的页面
	 * 
	 * @param response
	 * @param student
	 */
	@RequestMapping("/fillInStudent")
	public void fillInStudent(HttpServletResponse response, Student student) {
		Student stu = studentService.getStudentById(student.getStudentId());
		if (stu != null) {
			if (student.getAge() != null) {
				stu.setAge(student.getAge());
			}
			if (student.getSex() != null) {
				stu.setSex(student.getSex());
			}
			if (student.getAttendSchool() != null) {
				stu.setAttendSchool(student.getAttendSchool());
			}
			if (student.getIntentionalSchool() != null) {
				stu.setIntentionalSchool(student.getIntentionalSchool());
			}
			if(student.getGrade()!=null) {
				stu.setGrade(student.getGrade());
			}
			if(student.getSchoolId()!=null){
				stu.setSchoolId(student.getSchoolId());
			}
			stu.setUpdateTime(new Date());
			studentService.updateFillInStudent(stu);
			Student stud = studentService.getStudentById(stu.getStudentId());// 拿到主键ID返回新增的记录
			if (stud == null) {
				output(response, JsonUtil.buildFalseJson("2", "注册失败!"));
			} else {
				List<Student> stuL = new ArrayList<Student>();
				stuL.add(stud);
				output(response, JsonUtil.buildJson(stuL));
			}
		} else {
			output(response, JsonUtil.buildFalseJson("1", "该用户不存在!"));
		}
	}
	
	/**
	 * // 忘记密码修改密码{通过手机号重置密码}
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/resetForgetPwd")
	public void resetForgetPwd(HttpServletResponse response, String phone) throws Exception {
		Student student = studentService.loginConfirm(phone);
		if (student != null) {
			VerificationCode vcList = verificationCodeService.confirmCode(phone);
			VerificationCode vc = new VerificationCode();
			String messageCode = PhoneMessageUtil.ProductCode();
			String msg = "欢迎使用云教育app,您的验证码是" + messageCode + "。【云教育】";
			String respstatus = SendMessageCode.sendCode(phone, msg);
			if (respstatus.equals("0")) {
				if (vcList == null) {
					// 该手机以前没有发送验证码
					vc.setCreateTime(new Date());
					vc.setMessageCode(messageCode);
					vc.setPhoneNumber(student.getPhone());
					vc.setStudentId(student.getStudentId());
					verificationCodeService.addNewCode(vc);
					output(response, JsonUtil.buildFalseJson("0", phone));
				} else {
					// 该手机以前发送过验证码
					vc = vcList;
					vc.setCreateTime(new Date());
					vc.setMessageCode(messageCode);
					verificationCodeService.updateCode(vc);
					output(response, JsonUtil.buildFalseJson("0", phone));
				}
			} else {
				output(response, JsonUtil.buildFalseJson("2", "系统错误!"));
			}
		} else {
			output(response, JsonUtil.buildFalseJson("1", "该手机不存在!"));
		}
	}

	/**
	 * 验证手机和验证码是否正确
	 * 
	 * @param response
	 * @param phone
	 * @param code
	 */
	@RequestMapping("/verificationCode")
	public void verificationCode(HttpServletResponse response, String phone, String code) {
		VerificationCode returnCode = verificationCodeService.getCodeByPhone(phone);
		if (returnCode != null) {
			Date now = new Date();
			if (now.getTime() - returnCode.getCreateTime().getTime() > 300 * 1000) {
				output(response, JsonUtil.buildFalseJson("2", "验证码已过期!"));
			} else if (!code.equals(returnCode.getMessageCode())) {
				output(response, JsonUtil.buildFalseJson("3", "验证码不正确!"));
			} else {
				Student student = studentService.getStudentByPhone(phone);
				Student stud = studentService.getStudentById(student.getStudentId());
				if (stud != null) {
					List<Student> sList = new ArrayList<Student>();
					sList.add(stud);
					output(response, JsonUtil.buildJson(sList));
				} else {
					output(response, JsonUtil.buildFalseJson("4", "没有数据!"));
				}
			}
		} else {
			output(response, JsonUtil.buildFalseJson("1", "手机号码不正确!"));
		}
	}

	/**
	 * 重置密码
	 * 
	 * @param response
	 * @param student
	 */
	@RequestMapping("/resetPassword")
	public void resetPassword(HttpServletResponse response, Student student) {
		Student stu = studentService.getStudentById(student.getStudentId());
		if (stu != null) {
			stu.setUpdateTime(new Date());
			stu.setPassword(MD5Util.MD5Encode(student.getPassword(), "utf-8"));
			studentService.updatePwassword(stu);
			Student stud = studentService.getStudentById(stu.getStudentId());// 拿到主键ID返回新增的记录
			if (stud == null) {
				output(response, JsonUtil.buildFalseJson("2", "重置密码失败!"));
			} else {
				List<Student> stuL = new ArrayList<Student>();
				stuL.add(stud);
				output(response, JsonUtil.buildJson(stuL));
			}
		} else {
			output(response, JsonUtil.buildFalseJson("1", "用户不存在!"));
		}
	}

	/**
	 * 发送手机短信,用于用户注册
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/sendPhoneMessage")
	public void sendPhoneMessage(HttpServletResponse response, String phone) throws Exception {
		Student stu = studentService.getStudentByPhone(phone);
		if (stu == null) {
			VerificationCode vccc = verificationCodeService.confirmCode(phone);
			String messageCode = PhoneMessageUtil.ProductCode();
			String msg = "【云教育】欢迎使用云教育app,您的验证码是" + messageCode + "。";
			String respstatus = SendMessageCode.sendCode(phone, msg);
			if (respstatus.equals("0")) {
				if (vccc == null) {
					VerificationCode vc = new VerificationCode();
					// 该手机以前没有发送验证码
					vc.setCreateTime(new Date());
					vc.setMessageCode(messageCode);
					vc.setPhoneNumber(phone);
					verificationCodeService.addNewCode(vc);
					output(response, JsonUtil.buildFalseJson("0", "发送成功!"));
				} else {
					// 该手机以前发送过验证码
					vccc.setCreateTime(new Date());
					vccc.setMessageCode(messageCode);
					verificationCodeService.updateCode(vccc);
					output(response, JsonUtil.buildFalseJson("0", "发送成功!"));
				}
			} else {
				output(response, JsonUtil.buildFalseJson("1", "短信系统错误!"));
			}
		} else {
			output(response, JsonUtil.buildFalseJson("2", "该手机号码已被注册!"));
		}
	}
    

	/**
	 * 发送手机短信,用于用户注册
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getPasswordPhoneMessage")
	public void getPasswordPhoneMessage(HttpServletResponse response, String phone) throws Exception {
		Student stu = studentService.getStudentByPhone(phone);
		if (stu != null) {
			VerificationCode vccc = verificationCodeService.confirmCode(phone);
			String messageCode = PhoneMessageUtil.ProductCode();
			String msg = "【云教育】欢迎使用云教育app,您的验证码是" + messageCode + "。";
			String respstatus = SendMessageCode.sendCode(phone, msg);
			if (respstatus.equals("0")) {
				if (vccc == null) {
					VerificationCode vc = new VerificationCode();
					// 该手机以前没有发送验证码
					vc.setCreateTime(new Date());
					vc.setMessageCode(messageCode);
					vc.setPhoneNumber(phone);
					verificationCodeService.addNewCode(vc);
					output(response, JsonUtil.buildFalseJson("0", "发送成功!"));
				} else {
					// 该手机以前发送过验证码
					vccc.setCreateTime(new Date());
					vccc.setMessageCode(messageCode);
					verificationCodeService.updateCode(vccc);
					output(response, JsonUtil.buildFalseJson("0", "发送成功!"));
				}
			} else {
				output(response, JsonUtil.buildFalseJson("1", "短信系统错误!"));
			}
		} else {
			output(response, JsonUtil.buildFalseJson("2", "该手机号码暂未注册云教育平台!"));
		}
	}
	@RequestMapping("/getsubject")
	public void getSubject(HttpServletResponse response) {
		List<Subject> list = subjectService.getSubject();
		output(response, JsonUtil.buildJson(list));
	}
	@RequestMapping("/getschoolzone")
	public void getSchoolZone(HttpServletResponse response){
		List<SchoolZone> list = schoolzoneService.getAllSchool();
		output(response, JsonUtil.buildJson(list));
	}
}
