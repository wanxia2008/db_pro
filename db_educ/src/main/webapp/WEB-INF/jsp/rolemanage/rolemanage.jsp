<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>角色管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
.layui-input, .layui-select, .layui-textarea {

}

.layui-form-item .layui-inline {
	
}

.layui-form-select dl {
	top: 25px;
}
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
</style>

</head>
<body>
	<form class="layui-form" action="${ctx}/role/rolemanage.do">
	
		<div class="panels_head">
			<span>角色列表</span>
			<button type="button" class="layui-btn layui-btn-normal"
				lay-submit="" onclick="window.location.href='newrole.do'"
				lay-filter="demo1"
				style="height: 30px; line-height: 30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; font-size: 14px">新增</button>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;margin-left:1%;">

			<div class="layui-inline">
				<div class="layui-input-inline" style="width: 200px;">
					<input type="text" name="roleName" id="roleName"
						 value="${roleName}" autocomplete="off"
						placeholder="请输入角色名称" class="layui-input" maxlength="10">
				</div>
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1"
					 id="search_content">搜索</button>
			</div>
		
		</div>	
		<c:choose>
			<c:when test="${rList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size: 14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
<!-- 				<div class="table-responsive" -->
<!-- 					style="width: 98%; margin-left: auto; margin-right: auto; margin-top: 0px;"> -->
				<table class="layui-table" style="width: 98%; margin: 0 auto;">
				  <thead>
						<tr>
							<th>角色助记码</th>
							<th>角色名称</th>
							<th>可否修改</th>
							<th>创建时间</th>
							<th>角色描述</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${rList}" var="s">
							<tr>
								<td><c:out value="${s.roleId}"></c:out></td>
								<td><c:out value="${s.roleName}"></c:out></td>
								<td><c:if test="${s.isModify == 1}">是</c:if> <c:if
										test="${s.isModify == 0}">否</c:if></td>
								<td><fmt:formatDate value="${s.createTime}"
										pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
								<td><c:out value="${s.roleDescribe}"></c:out></td>
								<td><a
									href="${ctx}/userRole/jurisdiction.do?roleId=<c:out value="${s.roleId}"></c:out>"><span
										data-toggle="modal" id="openexam" class="icon-edit"
										style="font-size: 14px; color: #008bca; cursor: pointer;">修改</span></a>
									&nbsp;&nbsp; <a class="button border-red"
									href="javascript:void(0)"
									onclick="deleteone(<c:out value="${s.roleId}"></c:out>)"> <span
										class="icon-remove-sign"
										style="font-size: 14px; color: #ff5722; padding-right: 10px;">
											删除</span>
								</a></td>
							</tr>
						</c:forEach>
						<tr id="pages">
							<td colspan="9">
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
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript">
layui
.use(
		[ 'form', 'layedit', 'laypage', 'laydate' ],
		function() {
			var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

			//创建一个编辑器
			var editIndex = layedit.build('LAY_demo_editor');

			//自定义验证规则
			form.verify({
				title : function(value) {
					if (value.length < 5) {
						return '标题至少得5个字符啊';
					}
				},
				pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
				content : function(value) {
					layedit.sync(editIndex);
				}
			});

			//监听指定开关
			form.on('switch(switchTest)', function(data) {
				layer.msg('开关checked：'
						+ (this.checked ? 'true' : 'false'), {
					offset : '6px'
				});
				layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF',
						data.othis)
			});

			//监听提交
			form.on('submit(demo1)', function(data) {
				/* layer.alert(JSON.stringify(data.field), {
					title : '最终的提交信息'
				}) */
				return true;
			});
		});
function deleteone(uid){
	 layer.confirm('您确认删除该角色么?',{btn:['确认','取消']},function(){
	       $.post('${ctx}/role/roleDelete.do?',{'roleId':uid},function(data){
				 if(data.code == 0){
					 layer.msg('删除角色成功');
			    		window.location.href = "rolemanage.do";
			    	}else{
			    		layer.msg(data.msg);
			    		return;
			    	}
			 });
	    },function(){	
	    	  layer.closeAll();  
	});
}
function goPageAction(page){
	window.location.href="${ctx}/role/rolemanage.do?pageNo="+page+"&roleName=${roleName}";
	//+"&goodsName=${goodsName}&searchBeginDate=${searchBeginDate}&searchEndDate=${searchBeginDate}"
	}
function goMyPage(page,totalPage){
	if(page>0 && page<=totalPage){
		window.location.href="${ctx}/role/rolemanage.do?pageNo="+page+"&roleName=${roleName}";
	}else{
		alert("请输入有效的整数");
	}
}
$('#search_content').click(function(){
	
	var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i");   
	var schoolName2=$('#roleName').val();
	
    if(pat.test(schoolName2)==true)   
    {   
        layer.msg("输入名称中含有非法字符!");   
        return false;   
    } else{
    	$('#frm1').submit();
    }
});
</script>
</html>