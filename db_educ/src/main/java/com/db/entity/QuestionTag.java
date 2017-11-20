package com.db.entity;

import java.util.Date;
import java.util.List;

public class QuestionTag {
	private Integer tagId;
	private String tagName;
	private Date createTime;
	private Integer createUser;
	private Integer subject;
	private Integer grade;
	private Integer parentId;
	private Integer tagLevel;
	
	private List<QuestionTag> children;
	private Subject subject2;
	private Grade grade2;
	
	private String subjectName;
	private String gradeName;
	
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getSubject() {
		return subject;
	}
	public void setSubject(Integer subject) {
		this.subject = subject;
	}
	public Subject getSubject2() {
		return subject2;
	}
	public void setSubject2(Subject subject2) {
		this.subject2 = subject2;
	}
	public Grade getGrade2() {
		return grade2;
	}
	public void setGrade2(Grade grade2) {
		this.grade2 = grade2;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getTagLevel() {
		return tagLevel;
	}
	public void setTagLevel(Integer tagLevel) {
		this.tagLevel = tagLevel;
	}
	public List<QuestionTag> getChildren() {
		return children;
	}
	public void setChildren(List<QuestionTag> children) {
		this.children = children;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
}

