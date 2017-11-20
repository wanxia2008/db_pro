package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.db.entity.QuestionsType;

public interface QuestionsTypeService {

	List<QuestionsType> questionsType();

	QuestionsType getQuestionsTypeById(@Param("topicId")Integer piType);

}
