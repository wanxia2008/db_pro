package com.db.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Grade;
import com.db.entity.PaperInfo;

public interface PaperInfoService {

   public int addPaperInfo(PaperInfo pInfo);

   public List<PaperInfo> paperInfoList(@Param("teacherId")Integer teacherId, @Param("pInfo")PaperInfo pInfo, @Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("subjectId")Integer subjectId,@Param("gradeId")Integer gradeId,@Param("type") Integer type);
   public List<PaperInfo> paperInfoList1(@Param("teacherId")Integer teacherId, @Param("pInfo")PaperInfo pInfo, @Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize,@Param("subjectId")Integer subjectId,@Param("gradeId")Integer gradeId,@Param("type") Integer type,@Param("isTest") Integer isTest);
   //获取入学考试列表
   public List<PaperInfo> getEntrancePaperList(Map<String, Object> map);
   //获取入学考试列表总数
   public long getEntrancePaperListCount(Map<String, Object> map);

   public void deletePaperInfo(Integer piId);

   public PaperInfo getPaperInfoById(Integer piId);

   public int updatePaper(PaperInfo pInfo);

   public int findPaperTotai(Integer paperId);

   public int getCount();
   //根据条件查询试卷
   public List<PaperInfo> getPaperByItems(Map<String, Object> map);
   //模糊查询
   public List<PaperInfo> getPaperByContent(@Param("content")String content);
   //给课程添加考卷可选的列表
   public List<PaperInfo> getPublicExam(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);
   //给课程添加考卷可选的列表的数量
   public long getPublicExamCount(@Param("teacherId")Integer teacherId, @Param("pInfo")PaperInfo pInfo,@Param("subjectId")Integer subjectId,@Param("gradeId")Integer gradeId,@Param("type") Integer type,@Param("isTest") Integer isTest);
   //更新试卷引用数
   public int updateUsertimes(PaperInfo info);

   public int isPublicCount(@Param("teacherId")Integer teacherId, @Param("type")String type);

   public int getPaperCount(@Param("pInfo")PaperInfo  pInfo,@Param("teacherId") Integer teacherId);

   public PaperInfo getPaperInfoPiId(@Param("piId")Integer paperId);

   public void updateUsedTimes(PaperInfo pInfo);
   public int updateTopictotal(PaperInfo info);

public int getPaperCount1(@Param("pInfo")PaperInfo  pInfo,@Param("teacherId") Integer teacherId);

public List<PaperInfo> paperInfoIsPublicList( @Param("gradeList")List<Grade>  gradeList,   @Param("teacherId")Integer teacherId, @Param("pInfo")PaperInfo pInfo, @Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize);

public List<PaperInfo> getScience(@Param("materialId")Integer materialId);

public List<PaperInfo> getPaperType(@Param("typeId")Integer typeId);

public List<PaperInfo> getknowledgeBegin(@Param("lessonId")Integer lessonId);

public int seePaperCount(@Param("pInfo")PaperInfo pInfo);

public List<PaperInfo> seepaperInfoIsPublicList(@Param("pInfo")PaperInfo pInfo,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize);

public PaperInfo getPiIdById(@Param("piId")Integer piId);

public int getPaperByItemsCount(Map<String, Object> map);

public List<PaperInfo> getPaperBy(Map<String, Object> map);

public int getPaperByCount(Map<String, Object> map);

public int findAdminCount(Map<String, Object> map);

public List<PaperInfo> findAdminList(Map<String, Object> map);

public List<PaperInfo> getPaperBy1(Map<String, Object> map);
public int getPaperByCount1(Map<String, Object> map);
public List<PaperInfo> getPaperByItems1(Map<String, Object> map);
public int getPaperByItemsCount1(Map<String, Object> map);

}
