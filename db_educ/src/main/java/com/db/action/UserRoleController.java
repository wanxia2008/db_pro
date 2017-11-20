package com.db.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/userRole")
public class UserRoleController extends BaseUtil {
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private MenuTreeService mtService;
	@Resource
	private RoleService roleService;

	/**
	 * 角色权限页面
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/jurisdiction")
	public ModelAndView jurisdiction(Integer roleId) {
		ModelAndView mv = new ModelAndView();
		Role role = roleService.getRoleById(roleId);
		if (role != null) {
			mv.addObject("role", role);
		}
		mv.addObject("roleId", roleId);
		mv.setViewName("rolemanage/userQuanxian");
		return mv;
	}

	/**
	 * 获取菜单权限
	 * 
	 * @param response
	 * @param userId
	 * @param request
	 */
	@RequestMapping("/showHisRole")
	public void showHisAuthority(HttpServletResponse response, Integer userId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
//		UserRole ur = userRoleService.getUserRole(userId);
//		UserRole sessionAuth = userRoleService.getUserRole(currTeacher.getTeacherId());
		Role roleName = roleService.getRoleById(userId);
		UserRole userRole = userRoleService.getUserRole(currTeacher.getTeacherId());
	/*	Role role = roleService.getRoleById(userRole.getUserId());*/
		 Role role = roleService.getRoleStringById(userRole.getRoleId());
		String[] idStrings = role.getMenuIds().split(",");
		List tmtList = Arrays.asList(idStrings);//拿到当前登录用户的权限
		if (roleName != null) {
			String[] idStrings1 = roleName.getMenuIds().split(",");
			List tmtList1 = Arrays.asList(idStrings1);//拿到添加角色的权限
			List<MenuTree> mtList1 = mtService.getHisTreeNode(tmtList);//登录用户的权限
			List<MenuTree> mtList2 = mtService.getHisTreeNode1(tmtList1);//角色的权限
			List<MenuTree> mTreeList = menuList(mtList1, mtList2);
			output(response, JsonUtil.buildJson(mTreeList));
		} else {
			List<MenuTree> mtList = mtService.getParentMenu(tmtList);
			List<MenuTree> mTreeList = menuListName(mtList);
			output(response, JsonUtil.buildJson(mTreeList));
		}
	}

	/**
	 * 父菜单
	 * 
	 * @param menu
	 * @return
	 */
	public List<MenuTree> menuListName(List<MenuTree> menuList) {
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

	private List<MenuTree> menuList(List<MenuTree> mtList1, List<MenuTree> mtList2) {
		List<MenuTree> treeList = new ArrayList<MenuTree>();
		for (MenuTree x : mtList1) {
			MenuTree tree = new MenuTree();
			List<MenuTree> childTreeList = new ArrayList<MenuTree>();
			if (x.getMenuLevel() == 1) {
				tree.setMenuId(x.getMenuId());
				tree.setMenuLevel(x.getMenuLevel());
				tree.setMenuLogo(x.getMenuLogo());
				tree.setMenuName(x.getMenuName());
				tree.setMenuUrl(x.getMenuUrl());
				for (MenuTree mt : mtList2) {
					if (mt.getMenuId() == x.getMenuId()) {
						tree.setHasAuthority(1);
						break;
					} else {
						tree.setHasAuthority(0);
					}
				}
				tree.setChilds(childTreeList);
				for (MenuTree child1 : mtList1) {
					MenuTree tree1 = new MenuTree();
					if (child1.getMenuLevel() == 2 && child1.getParentId() == x.getMenuId()) {
						tree1.setMenuId(child1.getMenuId());
						tree1.setMenuLevel(child1.getMenuLevel());
						tree1.setMenuLogo(child1.getMenuLogo());
						tree1.setMenuName(child1.getMenuName());
						tree1.setMenuUrl(child1.getMenuUrl());
						for (MenuTree child2 : mtList2) {
							if (child2.getMenuId() == child1.getMenuId()) {
								tree1.setHasAuthority(1);
								break;
							} else {
								tree1.setHasAuthority(0);
							}
						}
						childTreeList.add(tree1);
					}
				}
				treeList.add(tree);
			}
		}
		return treeList;
	}
}
