package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.PaperDetails;

public interface PaperDetailsService {

   public int savePaperDetails(PaperDetails pd);
   
   public void getQuestionSingle(Integer piId);

   public List getPaerById(Integer piId, int pageNo, int pageSize);

   public PaperDetails getPaperDetails(Integer paperId, Integer questionId);
   //前台获取试卷详情
   public List<PaperDetails> getPaperDetailsByPaperId(@Param("type")int type,@Param("paperId")int paperId);

   public int findPaperTotai(Integer paperId);

   public PaperDetails findPaperDetailsById(Integer paperId,Integer questionId,Integer type);

   public List<PaperDetails> getPaperDetatilsId(@Param("paperId")Integer piId);

   public List<PaperDetails> findPaperList(@Param("paperId")Integer piId,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize);

  public int getCount(@Param("paperId")Integer piId);

public void updateIsRemove(PaperDetails pd);

public PaperDetails findPaperId(@Param("id")Integer paperId);

public void paperDelete(@Param("id")Integer paperId);

public List<PaperDetails> getPaperDetailsListById(@Param("paperId")Integer id);

}
