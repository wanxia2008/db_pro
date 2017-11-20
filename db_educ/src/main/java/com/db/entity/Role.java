package com.db.entity;

import java.util.Date;

public class Role {
	private Integer roleId;
	private String roleName;
	private String roleDescribe;
	private Integer isModify;
	private Date createTime;
	private String menuIds;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescribe() {
		return roleDescribe;
	}
	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}
	
	public Integer getIsModify() {
		return isModify;
	}
	public void setIsModify(Integer isModify) {
		this.isModify = isModify;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
}
