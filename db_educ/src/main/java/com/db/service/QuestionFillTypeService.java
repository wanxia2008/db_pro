package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.db.entity.QuestionFillType;

public interface QuestionFillTypeService {
    int getFillTypeCount(@Param("typeName")String typeName);
    int getAllFillTypeCount(@Param("typeName")String typeName);
    List<QuestionFillType> getTypeList(@Param("typeName")String typeName,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize);
    void addFillTypeName(QuestionFillType qty);
    void savefill(QuestionFillType qs);
    void delType(@Param("typeId")Integer typeId);
    
	List<QuestionFillType> getQuestionFillTypeList();
}
