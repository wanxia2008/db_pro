package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.MenuTree;

public interface MenuTreeService {
	public List<MenuTree> getParentMenu(@Param("ids")List idsList);

//	public List<MenuTree> getChildMenu(int parentMenuId);

	public List<MenuTree> getHisTreeNode(@Param("menuIds")List tmtList);

	public List<MenuTree> getHisTreeNode1(@Param("ids")List tmtList1);
}
