<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>权限管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
</style>
</head>
<body>
	<form class="layui-form" action="">
	<div class="panels_head">
			<span>教师权限列表</span>
		</div>
      <div class="layui-form-item" style="margin-top: 20px;margin-left:1%;">
			<div class="layui-inline">
				<div class="layui-input-inline" style="width: 200px;">
					<input type="text" name="teacherName" id="teacherName"
						lay-verify="schoolName" value="${teacherName}" autocomplete="off"
						placeholder="请输入教师姓名" class="layui-input">
				</div>
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1"
					 id="search_content">搜索</button>
			</div>
		</div>	
		<c:choose>
			<c:when test="${tList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table"
					style="width: 98%; margin-left: auto; margin-top: 0px;; margin-right: auto;">
					<thead>
						<tr>
<!-- 						<th>ID</th> -->
							<th>教师编号</th>
							<th>校区</th>
							<th>年级</th>
							<th>科目</th>
							<th>姓名</th>
							<th>所属角色</th>
							<th style="width:20%">数据权限</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tList}" var="t">
							<tr>
<%-- 							<td><c:out value="${t.taId}"></c:out></td> --%>
								<td><c:out value="${t.teacherId}"></c:out></td>
								<td>${t.schoolZone.schoolName}</td>
								<td><c:if test="${t.grade2.gradeName != null}">
								${t.grade2.gradeName}
								</c:if> <c:if test="${t.grade2.gradeName == null}">
								暂无年级
								</c:if></td>
								<td><c:if test="${t.subject2.subjectName != null}">
								${t.subject2.subjectName}
								</c:if> <c:if test="${t.subject2.subjectName == null}">
								暂无科目
								</c:if></td>
								<td><c:out value="${t.teacher.teacherName}"></c:out></td>
								<td style="text-align:left">
									<p>
										<c:forEach items="${rnameList}" var="role">
											<c:choose>
												<c:when
													test="${fn:contains(t.userRole.getRoleId(),role.getRoleId())}">
													<input type="radio" name="roleId-${t.taId}" 
														value="${role.getRoleId()}" lay-verify="roleId"
														title="${role.getRoleName()}" checked="checked">
													<br>
												</c:when>
												<c:otherwise>
													<input type="radio" name="roleId-${t.taId}" 
														value="${role.getRoleId()}" lay-verify="roleId"
														title="${role.getRoleName()}">
													<br>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</p>
								</td>
								<td>
									<div class="layui-form-item" pane="" style="height: 30px;">
										<label class="layui-form-label">题目</label>
										<div class="layui-input-block" style="margin-left:0">
											<c:choose>
												<c:when test="${t.questionsAuthority == 1}">
													<input type="checkbox"
														name="timu-${t.taId}-questionsAuthority[]" value="1"
														lay-skin="primary" title="读" checked="">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="timu-${t.taId}-questionsAuthority[]" value="2"
														lay-skin="primary" title="写">
												</c:when>
												<c:when test="${t.questionsAuthority == 2}">
													<input type="checkbox"
														name="timu-${t.taId}-questionsAuthority[]" value="1"
														lay-skin="primary" title="读">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="timu-${t.taId}-questionsAuthority[]" value="2"
														lay-skin="primary" title="写" checked="">
												</c:when>
												<c:when test="${t.questionsAuthority == 3}">
													<input type="checkbox"
														name="timu-${t.taId}-questionsAuthority[]" value="3"
														lay-skin="primary" title="读" checked="">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="timu-${t.taId}-questionsAuthority[]" value="3"
														lay-skin="primary" title="写" checked="">
												</c:when>
												<c:otherwise>
													<input type="checkbox"
														name="timu-${t.taId}-questionsAuthority[]" value="0"
														lay-skin="primary" title="读">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="timu-${t.taId}-questionsAuthority[]" value="0"
														lay-skin="primary" title="写">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="layui-form-item" pane="" style="height: 30px;">
										<label class="layui-form-label">试卷</label>
										<div class="layui-input-block" style="margin-left:0">
											<c:choose>
												<c:when test="${t.paperAuthority == 1}">
													<input type="checkbox"
														name="shijuan-${t.taId}-paperAuthority[]" value="1"
														lay-skin="primary" title="读" checked="">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="shijuan-${t.taId}-paperAuthority[]" value="2"
														lay-skin="primary" title="写">
												</c:when>
												<c:when test="${t.paperAuthority == 2}">
													<input type="checkbox"
														name="shijuan-${t.taId}-paperAuthority[]" value="1"
														lay-skin="primary" title="读">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="shijuan-${t.taId}-paperAuthority[]" value="2"
														lay-skin="primary" title="写" checked="">
												</c:when>
												<c:when test="${t.paperAuthority == 3}">
													<input type="checkbox"
														name="shijuan-${t.taId}-paperAuthority[]" value="3"
														lay-skin="primary" title="读" checked="">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="shijuan-${t.taId}-paperAuthority[]" value="3"
														lay-skin="primary" title="写" checked="">
												</c:when>
												<c:otherwise>
													<input type="checkbox"
														name="shijuan-${t.taId}-paperAuthority[]" value="0"
														lay-skin="primary" title="读">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="shijuan-${t.taId}-paperAuthority[]" value="0"
														lay-skin="primary" title="写">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="layui-form-item" pane="" style="height: 30px;">
										<label class="layui-form-label">讲义</label>
										<div class="layui-input-block" style="margin-left:0">
											<c:choose>
												<c:when test="${t.handoutAuthority == 1}">
													<input type="checkbox"
														name="jiangyi-${t.taId}-handoutAuthority[]" value="1"
														lay-skin="primary" title="读" checked="">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="jiangyi-${t.taId}-handoutAuthority[]" value="2"
														lay-skin="primary" title="写">
												</c:when>
												<c:when test="${t.handoutAuthority == 2}">
													<input type="checkbox"
														name="jiangyi-${t.taId}-handoutAuthority[]" value="1"
														lay-skin="primary" title="读">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="jiangyi-${t.taId}-handoutAuthority[]" value="2"
														lay-skin="primary" title="写" checked="">
												</c:when>
												<c:when test="${t.handoutAuthority == 3}">
													<input type="checkbox"
														name="jiangyi-${t.taId}-handoutAuthority[]" value="3"
														lay-skin="primary" title="读" checked="">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="jiangyi-${t.taId}-handoutAuthority[]" value="3"
														lay-skin="primary" title="写" checked="">
												</c:when>
												<c:otherwise>
													<input type="checkbox"
														name="jiangyi-${t.taId}-handoutAuthority[]" value="0"
														lay-skin="primary" title="读">
													<!-- disabled=""不能点击 -->
													<input type="checkbox"
														name="jiangyi-${t.taId}-handoutAuthority[]" value="0"
														lay-skin="primary" title="写">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</td>
								<td>
									<button type="button"
										onclick="updateAuthority(taId=<c:out value="${t.taId}"></c:out>)"
										class="layui-btn layui-btn-normal" target-form="auth-form">修改</button>
								</td>
						</c:forEach>
						<tr id="pages">
							<td colspan="14">
								<div class="pagelist">
									<a href="javascript:;" onclick="goPageAction(1);">首页</a>
									<c:choose>
										<c:when test="${page == 1}">
											<a href="javascript:;">上一页</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:;" onclick="goPageAction(${page - 1});">上一页
											</a>
											&nbsp;
										</c:otherwise>
									</c:choose>

									<c:forEach
										begin="${page>2?(page+2>totalPages?(totalPages>5?totalPages-4:1):page-2):1}"
										end="${page>3?page+2>totalPages?totalPages:page+2:totalPages>5?5:totalPages}"
										var="p">
										<c:choose>
											<c:when test="${page == p}">
												<a href="javascript:;" onclick="goPageAction(${p});"
													class="current">${p}</a>
											</c:when>
											<c:otherwise>
												<a href="javascript:;" onclick="goPageAction(${p});">${p}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:choose>
										<c:when test="${page == totalPages||totalPages==0}">
											<a href="javascript:;">下一页</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:;" onclick="goPageAction(${page + 1});">下一页
											</a>
											&nbsp;
										</c:otherwise>
									</c:choose>
									&nbsp;
									<a href="javascript:;" onclick="goPageAction(${totalPages});">末页</a>
									&nbsp;总共&nbsp; ${totalPages} &nbsp;页
<!-- 									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp; <input -->
<!-- 										type="text" id="goPageId" -->
<!-- 										style="width: 50px; height: 30px; line-height: 30px; text-align: center" -->
<%-- 										value="${page}" name="goPage" />&nbsp;&nbsp;页 <a --%>
<!-- 										href="javascript:;" -->
<%-- 										onclick="goMyPage($('#goPageId').val(),${totalPages});">&nbsp;&nbsp;Go</a> --%>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui.use([ 'form', 'laydate' ], function() {
		var form = layui.form(), layer = layui.layer;
		var $ = layui.jquery;
		//全选
		form.on('checkbox(allChoose)', function(data) {
			var child = $(data.elem).parents('table').find(
					'tbody input[type="checkbox"]');
			child.each(function(index, item) {
				item.checked = data.elem.checked;
			});
			form.render('checkbox');
		});
		//监听提交
	/* 	form.on('submit(demo1)', function(data) {
			layer.alert(JSON.stringify(data.field), {
				title : '最终的提交信息'
			})
			return false;
		}); */

	});
	
	function goPageAction(page){
		window.location.href="${ctx}/teacherAuthority/permissions.do?pageNo="+page+"&teacherName=${teacherName}"; 
		
		}
	function goMyPage(page,totalPage){
		if(page>0 && page<=totalPage){
			window.location.href="${ctx}/teacherAuthority/permissions.do?pageNo="+page+"&teacherName=${teacherName}";
		}else{
			alert("请输入有效的整数");
		}
	}
	
	
	function updateAuthority(taId){
		var timu = 'timu-'+taId+'-questionsAuthority[]';
		var shijuan = 'shijuan-'+taId+'-paperAuthority[]';
		var jiangyi = 'jiangyi-'+taId+'-handoutAuthority[]';
		var tId = "roleId-"+taId;
		var tmList = new Array();
		var sjList = new Array();
		var jyList = new Array();
		var roleIds = new Array();
		var tm,sj,jy;
		
		$('input[name^="'+tId+'"]:checked').each(function(i) {
			roleIds.push($(this).val());//向数组循环添加选中的值
		});
		
		var roleId1 = roleIds.join(",");//转为字符串

		 
		//题目
		$('input[name^="'+timu+'"]:checkbox').each(function(i){ 
			tmList.push($(this).prop('checked'));		
		})
		
		if(tmList[0] == true){
			 
			if(tmList[1] == true){
				
				tm = 3;
				
			}else{
				tm =1;
			}
			
		}else{
			
			if(tmList[1] == true){
				 tm=2;
			}else{
				tm = 0;
			}
			
			
		}
		//试卷
		$('input[name^="'+shijuan+'"]:checkbox').each(function(i){ 
			sjList.push($(this).prop('checked'));		
		})
		
		if(sjList[0] == true){
			 
			if(sjList[1] == true){
				
				sj = 3;
				
			}else{
				sj =1;
			}
			
		}else{
			
			if(sjList[1] == true){
				 sj=2;
			}else{
				sj = 0;
			}
			
			
		}
		
		
		//讲义
		$('input[name^="'+jiangyi+'"]:checkbox').each(function(i){ 
			jyList.push($(this).prop('checked'));		
		})
		
		if(jyList[0] == true){
			 
			if(jyList[1] == true){
				
				jy = 3;
				
			}else{
				jy =1;
			}
			
		}else{
			
			if(jyList[1] == true){
				 jy=2;
			}else{
				 jy=0;
			}
		}
		 $.ajax({
			 url:"${ctx}/teacherAuthority/updateAuthority.do", 
		     type:"POST",
		     dataType:"json",
		     data: {'taId':taId,'questionsAuthority':tm,'paperAuthority':sj,'handoutAuthority':jy,'roleId':roleId1}, 
		     success : function(data){
		    	 if (data.code == 0) {
		    		layer.msg("分配权限成功");
					setTimeout(function(){
// 						window.location.href = "permissions.do?teacherId="+taId;
                        window.location.reload();//跳转当前页
					},1000);
					} else{
						layer.msg(data.msg);
						return;
					}
		    },
		    error:function(data){
		    	alert('请勾选数据权限');
		    }
		    
		 });
		
		
    }
	
	
	function deleteone(uid){
		 layer.confirm('您确认删除该讲师么?',{btn:['取消','确认']},function(){
		       layer.closeAll();  
		    },function(){	
			 $.post('${ctx}/teacherAuthority/delete.do?',{'taId':uid}, function(data){
				  if(data){
						layer.msg('删除讲师成功',{time:2500,icon:1});
					      setTimeout(function(){
					    	  window.location.href = "permissions.do";
					      },400);
				  }else{
						layer.msg('删除讲师失败',{time:2500,icon:0});
				  }
			 }); 
		});
	}
</script>
</html>