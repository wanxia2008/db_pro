package com.db.entity.utilentity;

import java.util.List;

public class CountStudentScore {
	private Integer sroceType;
	private List<QuestionType> qTypeList;

	public Integer getSroceType() {
		return sroceType;
	}

	public void setSroceType(Integer sroceType) {
		this.sroceType = sroceType;
	}

	public List<QuestionType> getqTypeList() {
		return qTypeList;
	}

	public void setqTypeList(List<QuestionType> qTypeList) {
		this.qTypeList = qTypeList;
	}

	@Override
	public String toString() {
		return "CountStudentScore [sroceType=" + sroceType + ", qTypeList=" + qTypeList + "]";
	}

}
