package com.db.entity.utilentity;

public class SubjectsPapers {
	private Integer paperId;
	private Integer classId;
	private String className;
	private String subjectName;
	private	String paperType;
	private String classAndGrade;

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getClassAndGrade() {
		return classAndGrade;
	}

	public void setClassAndGrade(String classAndGrade) {
		this.classAndGrade = classAndGrade;
	}

	@Override
	public String toString() {
		return "SubjectsPapers [paperId=" + paperId + ", classId=" + classId + ", className=" + className
				+ ", subjectName=" + subjectName + ", paperType=" + paperType + ", classAndGrade=" + classAndGrade
				+ "]";
	}
}
