package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.EntranceStudent;
import com.db.entity.TestRecord;

public interface TestRecordService {
	//学生入学考试
	public void addTestrecord(TestRecord record);
	//班级添加考试
	public void addTestrecordByClassId(TestRecord record);
	//检查是否插入
	public TestRecord getTestrecordById(int recordId);
	
	public List<TestRecord> getTestListByStudentId(@Param("studentId")int studentId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("name") String name);
	
	public List<TestRecord> getByStudentId(int studentId);
	//获取老师所在校区的学员的正在入学考试的列表
	public List<TestRecord> getEntranceingStudent(Map<String, Object> map);
	//获取老师所在校区的学员的正在入学考试的列表总数
	public long getEntranceingStudentCount(Map<String, Object> map);
	
	public List<TestRecord> getEntranceByStudentId(@Param("studentId")int studentId,@Param("schoolId")int schoolId);
	//获取学生参加了的入学考试对应的科目
	public List<EntranceStudent> getEntranceBySchool(Integer schoolId);
	
	//获取老师所在校区的学员的参加的入学考试记录的列表
	public List<TestRecord> getEntranceStudent(int schoolId);
		
	//联表获取试卷编号和状态，状态为进行中则拿试卷编号去查试卷表，拿到试卷进行考试
	public TestRecord getPaperByTestRecord(int studentId);
	//获取该学生考试状态
	public TestRecord getStatusByStudentId(int studentId);
	//获取考过某卷的考试记录，拿到学员编号
	public List<TestRecord> getTestRecord(int examrecord);
	
	public int updateTestrecord(TestRecord record);
	//前台在考试前填写学生信息，以便核对答题卡
	public int updatenameandall(TestRecord record);
	
	public TestRecord isWriteMessage(@Param("studentId")int studentId,@Param("paperId")int paperId,@Param("schoolId") Integer schoolId);
	public int updateTotaiTime(TestRecord tr);
	//实时修改得分
	public int updateScoreByStuIdandPaperId(TestRecord record);
	
	public TestRecord getTestRecordByStuandPaper(@Param("studentId")int studentId,@Param("paperId")int paperId);
	
	public List<TestRecord> getRecordSituation(@Param("studentId")Integer studentId,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize);
	
	public TestRecord studentNameDateils(@Param("studentId")Integer studentId);
	public int getCount(@Param("subject") Integer subject,@Param("classId") Integer classId,@Param("gradeId") Integer gradeId);
	public List<TestRecord> getPaperEndList(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("subject") Integer subject,@Param("classId") Integer classId,@Param("gradeId") Integer gradeId);
	public int getCountStudentId(@Param("studentId")Integer studentId);
	public TestRecord getStauts(@Param("studentId")Integer studentId, @Param("paperId")Integer paperId);
	public TestRecord findRecordScore(@Param("id")Integer scoreId);
	public int getStatusCout(@Param("schoolId") Integer schoolId,@Param("subjectId") Integer subjectId,@Param("gradeId") Integer gradeId);
	public List<TestRecord> getTestRecordStatus(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("schoolId") Integer schoolId,@Param("subjectId") Integer subjectId,@Param("gradeId") Integer gradeId, @Param("grade")Integer grade,@Param("score") Integer score);
	public void addExamination(TestRecord tr);
	public int getCountEx(@Param("subjectId")Integer subjectId, @Param("gradeId")Integer gradeId, @Param("schoolId")Integer schoolId);
	public List<TestRecord> findEexecuteList(@Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("schoolId") Integer schoolId,@Param("subjectId") Integer subjectId,@Param("gradeId") Integer gradeId);
	public void updateStatus(TestRecord tr);
	public void deleteRecordById(TestRecord tr);
	public TestRecord distributionClassNo(@Param("id")Integer id);
	public int updateExamination(TestRecord tr);
	
	public void updateClassId(TestRecord tr);
	//查看学员的历史记录
	public List<TestRecord> getTestRecordStudentId(@Param("studentId")int id);
	//新增入学考试记录信息
	public int saveTextRecord(TestRecord record);
	//考试之前判断该学员是否艺考个
	public TestRecord findTextRecordById(@Param("studentId")Integer studentId, @Param("paperId")Integer paperId,@Param("subjectId") Integer subjectId,@Param("schoolId") Integer schoolId);
	public List<TestRecord> findMyQizhongAndQimoTestRecord(@Param("studentId")Integer studentId, @Param("classId")Integer classId);
	public void updateScoreANDWriteNumber(TestRecord record2);
	public void updateWriteNumber(TestRecord record);
	
	public TestRecord getEntrance(@Param("studentId")Integer studentId, @Param("paperId")Integer paperId);
	
	public int getSchoolIdTeacherCout(@Param("schoolId")Integer campus,@Param("subjectId") Integer subjectId, @Param("gradeId")Integer gradeId);
	public List<TestRecord> getTestTeacherSchoolId(@Param("pageNo")int paeNo,@Param("pageSize") Integer pageSize, @Param("schoolId")Integer schoolId, @Param("subjectId")Integer subjectId,
			@Param("gradeId")Integer gradeId,@Param("grade") Integer grade,@Param("score") Integer score);
	//获取成绩排名
	public List<TestRecord> findObtainScoreRankList(@Param("paperId")Integer paperId,@Param("classId") Integer classId);
	public int getClassPaperCount(@Param("classId")Integer classId, @Param("studentName")String studentName, @Param("paperId")Integer paperId);
	public List<TestRecord> findClassPaperList(@Param("classId")Integer classId, @Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("studentName") String studentName,
			@Param("paperId")Integer paperId);
	public List<TestRecord> findClassPaperInfoAnalysisList(@Param("paperId")Integer paperId, @Param("classId")Integer classId);
	public double getClassIdAvgScore(@Param("paperId")Integer paperId, @Param("classId")Integer classId);
}
