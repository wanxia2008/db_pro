package com.db.entity;

import java.util.Date;

public class QuestionName {
	private Integer topicType;
	private Integer usedCount;
	private Integer rightCount;
	private Integer isPublic;
	private Integer difficultyValue;
	private Date createTime;
	
	public Integer getTopicType() {
		return topicType;
	}
	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}
	public Integer getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	public Integer getRightCount() {
		return rightCount;
	}
	public void setRightCount(Integer rightCount) {
		this.rightCount = rightCount;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	
	public Integer getDifficultyValue() {
		return difficultyValue;
	}
	public void setDifficultyValue(Integer difficultyValue) {
		this.difficultyValue = difficultyValue;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
