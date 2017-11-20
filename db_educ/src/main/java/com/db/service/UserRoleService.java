package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.UserRole;

public interface UserRoleService {

	public UserRole getUserRole(@Param("userId")Integer teacherId);

	public void updateUserRole(UserRole ur);

	public void saveUserRole(UserRole ur);

	public void deleteUserRoleById(@Param("userId")int id);

	public List<UserRole> getRoleById(@Param("roleId")Integer roleId);

}
