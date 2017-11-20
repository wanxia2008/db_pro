package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.MyMistakes;

public interface MyMistakesService {

	List myMistakesList(Integer studentId, int pageNo, int pageSize);

	List kesListdetails(Integer studentId, Integer pageNo, Integer pageSize);

	public void saveMyMistakes(MyMistakes my);

	public int findMyMistakesByIdType(Integer studentId, Integer topicType);

	public void removeMistakes(Integer mistakesId);

	/**
	 * 根据学生编号和班级编号返回他所在这个班考过试卷的总分
	 * @return
	 */
	public int getMyAllScoreBystuId(@Param("studentId")Integer studentId,@Param("paperId")Integer classId);
	
	/**
	 * 根据学生编号和班级编号返回他所在这个班考过试卷的某种题型的总分
	 * @return
	 */
	public int getMyScoreBystuId(@Param("studentId")Integer studentId,@Param("paperId")Integer classId,@Param("topicType")Integer topicType);

	/**
	 * 根据学生编号和班级编号返回他所在这个班考过试卷的某种题型答对的总分
	 * @return
	 */
	public int getMyRightQuestionBystuId(@Param("studentId")Integer studentId,@Param("paperId")Integer classId,@Param("topicType")Integer topicType);

	List<MyMistakes> findMyMistakesById(Integer studentId, Integer paperId);

	MyMistakes getMyMistakesBy(@Param("paperId")Integer paperId,@Param("questionId")Integer questionId,@Param("studentId")Integer studentId);

	public void updateMyMistakes(MyMistakes mm);
	
	/**
	 * jerry
	 */
	//添加作业的错题，每做一道对应一条记录，要加班级编号
	public void addHomeworkMistake(MyMistakes m);
	//添加考卷的错题，每做一道对应一条记录，要加班级编号
	public void addPaperMistake(MyMistakes m);
	//添加后查询是否有记录
	public MyMistakes getMistakeById(int id);
	//获取考完后（不同类型）的得分情况，map有studentId,paperId or homeworkId
	public List<MyMistakes> getRightByStatus(Map<String, Object> map);
	//获取不同类型的错题本，map有studentId,pageNo,pageSize
	public List<MyMistakes> getWrongSingleByTypeandStatus(Map<String, Object> map);
	public List<MyMistakes> getWrongMulitByTypeandStatus(Map<String, Object> map);
	public List<MyMistakes> getWrongJudgeByTypeandStatus(Map<String, Object> map);
	public List<MyMistakes> getWrongReadByTypeandStatus(Map<String, Object> map);

	public int countThisSubjectMistake(@Param("studentId")Integer studentId, @Param("subjectId")Integer subjectId,@Param("topicType")Integer topicType);

	MyMistakes findMyMistakesHomeworkById(@Param("studentId")Integer studentId,@Param("homeworkId") Integer homeworkId, @Param("questionId")int questionId, @Param("type")int type,@Param("paperId") Integer paperId);

	void updateMyMistakesHomeworkById(MyMistakes mistakes);
}
