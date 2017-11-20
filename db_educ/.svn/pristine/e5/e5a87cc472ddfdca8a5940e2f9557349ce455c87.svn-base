package com.db.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.entity.MenuTree;
import com.db.entity.Role;
import com.db.entity.Teacher;
import com.db.entity.UserRole;
import com.db.service.MenuTreeService;
import com.db.service.RoleService;
import com.db.service.UserRoleService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/menuTree")
public class MenuTreeController extends BaseUtil {

	@Resource
	private MenuTreeService menuTreeService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private RoleService roleService;

	/**
	 * 当前登录的用户编号
	 * 
	 * @param response
	 */
	@RequestMapping("/menuTree")
	public void showAllParentMenu(HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
		UserRole userRole = userRoleService.getUserRole(currTeacher.getTeacherId());
		String[] my = userRole.getRoleId().split(",");
		List roleIds = Arrays.asList(my);
		List<Role> roleList = roleService.getRoleIdsById(roleIds);
		// Role role = roleService.getRoleStringById(userRole.getRoleId());
		List<MenuTree> mtList = null;
		List<MenuTree> mTreeList = null;
		List<MenuTree> mmmmList = new ArrayList<MenuTree>();
		String roles = "";
		for (Role role : roleList) {
			roles += roleService.getRoleMenuIds(role.getRoleId()).getMenuIds();
		}
		Set<String> mLinkedSet = new LinkedHashSet<String>();
		String[] idStrings = roles.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < idStrings.length; i++) {
			if (!mLinkedSet.contains(idStrings[i])) {
				mLinkedSet.add(idStrings[i]);
				sb.append(idStrings[i] + ",");
			}
		}
//		sb.toString().substring(0, sb.toString().length() - 1);
		List tmtList = Arrays.asList(idStrings);
		mtList = menuTreeService.getParentMenu(tmtList);
		mTreeList = menuList(mtList);
		for (MenuTree mTree : mTreeList) {
			MenuTree mt = new MenuTree();
			mt.setMenuId(mTree.getMenuId());
			mt.setOrderId(mTree.getOrderId());
			mt.setMenuLevel(mTree.getMenuLevel());
			mt.setMenuLogo(mTree.getMenuLogo());
			mt.setMenuName(mTree.getMenuName());
			mt.setChilds(mTree.getChilds());
			mmmmList.add(mt);
		}
		output(response, JsonUtil.buildJson(mmmmList));
	}

	/**
	 * 父菜单
	 * 
	 * @param menu
	 * @return
	 */
	public List<MenuTree> menuList(List<MenuTree> menuList) {
		List<MenuTree> treeList = new ArrayList<MenuTree>();
		for (MenuTree x : menuList) {
			MenuTree tree = new MenuTree();
			if (x.getParentId() == 0) {
				tree.setMenuId(x.getMenuId());
				tree.setMenuLevel(x.getMenuLevel());
				tree.setMenuLogo(x.getMenuLogo());
				tree.setMenuName(x.getMenuName());
				tree.setMenuUrl(x.getMenuUrl());
				tree.setOrderId(x.getOrderId());
				tree.setChilds(menuChild(x.getMenuId(), menuList));
				treeList.add(tree);
			}
		}
		return treeList;
	}

	/**
	 * 子菜单
	 * 
	 * @param id
	 * @return
	 */
	public List<MenuTree> menuChild(int id, List<MenuTree> menuList) {
		List<MenuTree> treeList = new ArrayList<MenuTree>();
		for (MenuTree a : menuList) {
			MenuTree tree = new MenuTree();
			if (a.getParentId() == id) {
				tree.setMenuId(a.getMenuId());
				tree.setMenuLevel(a.getMenuLevel());
				tree.setMenuLogo(a.getMenuLogo());
				tree.setMenuName(a.getMenuName());
				tree.setMenuUrl(a.getMenuUrl());
				tree.setOrderId(a.getOrderId());
				tree.setParentId(a.getParentId());
				tree.setChilds(menuChild(a.getMenuId(), menuList));
				treeList.add(tree);
			}
		}
		return treeList;
	}
}