package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Role;

public interface RoleService {

	List<Role> getRoleList(@Param("roleName")String roleName,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize);

	int getRoleCount(@Param("roleName")String roleName);

	public void roleDelete(@Param("roleId")Integer roleId);

	 public int addRole(Role role);

	Role getRoleById(@Param("roleId")Integer roleId);

	Object getUserRole(Integer teacherId);

	Role getRoleMenuIds(@Param("roleId")Integer roleId);

	public int updateRole(Role role);

	List<Role> getRoleIdsById(@Param("ids")List roleList);

	List<Role> getRnameList();

	List<Role> getRole();
	//修改的判断
	Role findIsName(@Param("roleId")Integer roleId,@Param("roleName")String roleName);
	//新增的判断
	Role findIsName1(@Param("roleName")String roleName);

	Role getRoleStringById(@Param("roleId")String roleId);

}
