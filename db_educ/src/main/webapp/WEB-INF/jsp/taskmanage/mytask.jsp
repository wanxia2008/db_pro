<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>我的任务列表</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<!-- <script type="text/javascript" -->
<%-- 	src="${ctx}/My97DatePicker/WdatePicker.js"></script> --%>
<style type="text/css">
.a-btn {
	height: 30px;
	line-height: 30px;
	padding: 0 18px;
	position: absolute;
	right: 25px;
	top: 20%;
	font-size: 10px;
	border-radius: 5px;
}
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="${ctx}/task/showmytask.do">
		<div class="panels_head">
			<span>我的任务</span> 
			<button type="button" class="layui-btn layui-btn-normal"
				lay-submit="" onclick="window.location.href='newmytask.do'"
				lay-filter="demo1"
				style="height: 30px; line-height: 30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; font-size: 14px">新增</button>
		</div>
		<div class="layui-form-item" style="margin-top: 20px">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;font-size: 14px;">任务类型</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="taskType" id="taskType">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${ttList}" var="qt">
						<c:choose>
						<c:when test="${taskType==qt.typeId}">
							<option selected="selected" value="<c:out value="${qt.typeId}"></c:out>"><c:out
									value="${qt.taskTypeName}"></c:out></option>
						</c:when>
						<c:otherwise>
						     	<option value="<c:out value="${qt.typeId}"></c:out>"><c:out
									value="${qt.taskTypeName}"></c:out></option>
						</c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;font-size: 14px;">任务标题</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" name="taskTitle" id="tasktitle"
						value="${taskTitle}" placeholder="请输入任务标题"
						class="layui-input" maxlength="10">
				</div>
				<button class="layui-btn layui-btn-normal searchbtn" lay-submit="" lay-filter="demo1"
					 id="search_content">搜索</button>
			</div>
		</div>
	<!-- 	<div class="panels_head" style="margin-top: 0px;">
			<span>任务列表</span>
		</div> -->
		<c:choose>
			<c:when test="${tList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size: 14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table"
					style="width: 98%;margin:0 auto">
					<thead>
						<tr>
							<th>任务类型</th>
							<th>任务标题</th>
							<th>任务要求</th>
							<th>开始时间</th>
							<th>任务规则</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tList}" var="t">
							<tr>
								<!-- <td><input type="checkbox" name="" lay-skin="primary"></td> -->
								<td><c:out value="${t.taskName.taskTypeName}"></c:out></td>
								<td><c:out value="${t.taskTitle}"></c:out></td>
								<%-- 	<td><c:out value="${t.paperInfo.piName}"></c:out></td> --%>
								<td><c:out value="${t.requirement}"></c:out></td>
								<td>${t.startTime}</td>
								<td>${t.taskRule.ruleName}</td>
								<td>
<%-- 								<c:choose> --%>
<%-- 								<c:when test="${t.taskStatus ==0}"> --%>
<%-- 								  <p>  <a class="icon-plus-sign-alt" onclick="kaiqi(${t.taskId})" --%>
<!-- 									    style="font-size: 14px; color:#008bca; cursor: pointer;">启动</a>&nbsp; -->
<!-- 								</p> -->
<%-- 								<p><a href="${ctx}/task/updatemytask.do?taskId=<c:out value="${t.taskId}"></c:out>"> --%>
<!-- 										<span class="icon-eye-open" style="color:#008bca">修改</span> -->
<!-- 								</a></p> -->
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
								<c:if test="${t.taskStatus ==1}">
								<p><a class="icon-plus-sign-alt" onclick="jieshu(${t.taskId})"
									    style="font-size: 14px; color:#008bca; cursor: pointer;">结束</a>&nbsp;
								</p>
								</c:if>
								<c:if test="${t.taskStatus ==2}">
								<p><a class="button border-red" href="javascript:void(0)"
									onclick="deleteone(<c:out value="${t.taskId}"></c:out>)"> <span
										class="icon-remove-sign" style="color: #ff5722"> 删除</span>
								   </a></p>
								</c:if>
<%-- 								</c:otherwise> --%>
<%-- 								</c:choose> --%>
								   </td>
							</tr>
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
									<a href="javascript:;" onclick="goPageAction(${totalPages});">末页</a>
									&nbsp;总共&nbsp; ${totalPages} &nbsp;页
									<%-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp;<input
										type="text" id="goPageId"
										style="width: 50px; height: 30px; line-height: 30px; text-align: center"
										value="${page}" name="goPage" />&nbsp;&nbsp;页<a
										href="javascript:;"
										onclick="goMyPage($('#goPageId').val(),${totalPages});">&nbsp;&nbsp;Go</a> --%>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</form>
</body>
<script type="text/javascript" src=".././js/jquery-1.8.3.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui.use([ 'form', 'laypage', 'laydate' ], function() {
		var form = layui.form(), laypage = layui.laypage, layer = layui.layer;
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
		form.on('submit(demo1)', function(data) {
			/* layer.alert(JSON.stringify(data.field), {
				title : '最终的提交信息'
			}) */
			return true;
		});
	});
	
	function deleteone(uid){
		layer.confirm('您确认删除该任务么?',{btn:['确认','取消']},function(){
			 $.post('${ctx}/task/deleteTsk.do?',{'taskId':uid},function(data){
				  if(data.code == 0){
						layer.msg('删除任务成功');
						setTimeout(function(){
							 window.location.href="${ctx}/task/showmytask.do";
						},2000);
				  }else{
					  layer.msg('删除任务失败',{time:1000});
			    	return;
				  }
			 });
		    },function(){	
		    	layer.closeAll();
		});
	}
	//开启任务
	function kaiqi(uid){
		layer.confirm('您确认启动该任务么?',{btn:['确认','取消']},function(){
			 $.post('${ctx}/task/startTask.do?',{'taskId':uid},function(data){
				  if(data.code == 0){
						layer.msg('启动任务成功');
						setTimeout(function(){
							 window.location.reload();
						},2000);
				  }else{
					  layer.msg('启动任务失败',{time:1000});
			    	return;
				  }
			 });
		    },function(){	
		    	layer.closeAll();
		});
	}
	//结束任务
	function jieshu(uid){
		layer.confirm('您确认结束该任务么?',{btn:['确认','取消']},function(){
			 $.post('${ctx}/task/endTask.do?',{'taskId':uid},function(data){
				  if(data.code == 0){
						layer.msg('结束任务成功');
						setTimeout(function(){
							 window.location.reload();
						},2000);
				  }else{
					  layer.msg('结束任务失败',{time:1000});
			    	return;
				  }
			 });
		    },function(){	
		    	layer.closeAll();
		});
	}
function goPageAction(page){
	window.location.href="${ctx}/task/showmytask.do?pageNo="+page+"&endCreateTime=${endCreateTime}&startCreateTime=${startCreateTime}"; ;
	
	}
function goMyPage(page,totalPage){
	if(page>0 && page<=totalPage){
		window.location.href="${ctx}/task/showmytask.do?pageNo="+page+"&endCreateTime=${endCreateTime}&startCreateTime=${startCreateTime}"; ;
	}else{
		alert("请输入有效的整数");
	}
}
$('#search_content').click(function(){
	var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i");   
	var taskname = $('#tasktitle').val();

    if(pat.test(taskname)==true)   
    {   
        alert("输入名称中含有非法字符");   
        return false;   
    } else{
    	$('#myform').submit();
    }
})
</script>
</html>