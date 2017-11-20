package com.db.entity;

import java.util.Date;

public class QuestionsType {
	private Integer topicId;
	private String topicName;
	private Date createTime;
	private Date updateTime;
    private Integer typicOrder;
    
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getTypicOrder() {
		return typicOrder;
	}
	public void setTypicOrder(Integer typicOrder) {
		this.typicOrder = typicOrder;
	}
}
