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
<title>任务规则列表</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<style type="text/css">
.tcenter{text-align: center !important}
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
</style>

</head>
<body>
	<form id="myform" class="layui-form" action="${ctx}/taskRule/taskRuleList.do">
	<div class="panels_head">
			<span>任务规则列表</span>
		</div>
		<div class="layui-form-item"
			style="margin-top: 20px; margin-left:1%;margin-right:1%">
<!-- 			<div class="layui-inline" > -->
<!-- 				<div class="layui-input-inline" style="width: 200px;"> -->
<!-- 					<input type="text" id="taskRuleName" name="taskRuleName"  -->
<!-- 						autocomplete="off" placeholder="请输入规则名称" class="layui-input" maxlength="10"> -->
<!-- 				</div> -->
<!-- 				 <button type="button" class="layui-btn layui-btn-normal" onclick="saveSourceName()"> -->
<!-- 					新增</button>  -->
<!-- 			</div> -->
			
				<div class="layui-inline" style="float:right">
				<div class="layui-input-inline" style="width: 180px;">
					<input type="text"  placeholder="请输入规则名称" id="ruleName"
						name="ruleName1" value="${ruleName1}" class="layui-input" maxlength="10">
				</div>
				<button class="layui-btn layui-btn-normal searchbtn" id="search_content" lay-submit=""
					lay-filter="demo1">搜索</button>
			</div>
		</div>
		<c:choose>
			<c:when test="${trList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
			<table class="layui-table" style="width:98%;margin:0 auto">
			<thead>
				<tr>
					<th>ID</th>
					<th>任务规则名称</th>
					<th>添加时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${trList}" var="s">
					<tr>
						<td><c:out value="${s.ruleId}"></c:out></td>
						<td>
						   <c:out value="${s.ruleName}"></c:out>
						</td>
							<td><fmt:formatDate value="${s.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>	
						<td>
                         <span class="icon-edit" data-toggle="modal" data-target="#myModal"
							id="${s.ruleId}" data-name="${s.ruleName}"
							style="font-size:14px; color:#008bca; cursor: pointer;">修改</span>
									&nbsp; 
<!-- 									<a class="button border-red" href="javascript:void(0)" -->
<%-- 									onclick="deleteone(<c:out value="${s.ruleId}"></c:out>)"> <span --%>
<!-- 										class="icon-remove-sign" -->
<!-- 										style="font-size: 14px; color: #FF5722;"> 删除</span> -->
<!-- 								</a> -->
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
						<%-- 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp; <input
								type="text" id="goPageId"
								style="width: 50px; height: 30px; line-height: 30px; text-align: center"
								value="${page}" name="goPage" />&nbsp;&nbsp;页 <a
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
	<!--模态框-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 40%;">
			<div class="modal-content">
			<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">编辑规则</h4>
</div>
				<div class="modal-body">
					<form class="layui-form" method="post">
						<div class="layui-form-item" style="margin-top: 20px;">
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 120px;">任务规划名称</label>
								<div class="input-group" style="float: left;">
									<input type="text" name="ruleName" id="ruleName2"
										class="form-control" style="width: 200px;" maxlength="10"> 
								<input type="number" hidden="" name="ruleId" id="ruleId">
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="confirm_update" style="height:30px;line-height:30px;padding:0 18px;">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>					
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript">
layui
.use(
		[ 'form', 'layedit', 'laypage', 'laydate' ],
		function() {
			var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;
		});
		
		
$('.icon-edit').on('click', function() {
	var ruleId = $(this).attr("id");
	var ruleName2 = $(this).attr("data-name");
	$('#ruleName2').val(ruleName2);
	$('#ruleId').val(ruleId);
	
});
$('#confirm_update').on('click', function() {
	var ruleName=$('#ruleName2').val();
	var ruleId=$('#ruleId').val();
	
	$.ajax({
		url:'${ctx}/taskRule/deleteTaskRole.do',
		type:'post',
		dateType:'json',
		data:{
			ruleName:ruleName,
			ruleId:ruleId
		},
		success:function(data){
			if(data.code==0){
				layer.msg('编辑成功');
				setTimeout(function(){				
					window.location.href = "taskRuleList.do";
            },1000);
			}else{
				layer.msg(data.msg,{time:3000});
				return;
			}
		},
	})
});
function deleteone(uid){
	layer.confirm('您确认删除该任务么?',{btn:['确认','取消']},function(){
		$.post('${ctx}/taskRule/taskRuleDelete.do?',{'ruleId':uid},function(data){
			if(data.code==0){
				layer.msg('删除任务成功');
				setTimeout(function(){
					window.location.href="${ctx}/taskRule/taskRuleList.do";	
				},2000);
			}else{
				layer.msg(data.msg,{time:3000});
				return;
			}
	 });
	    },function(){	
		       layer.closeAll();  
	});
}

$('#search_content').click(function(){
	var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i");   
	var taRuleName=$('#taRuleName').val();
    if(pat.test(taRuleName)==true){   
    	layer.msg("输入名称中含有非法字符!");   
        return false;   
    } else{
    	$('#myform').submit();
    }
})

function saveSourceName(){
	var taskRuleName=$("#taskRuleName").val();
	$.ajax({
		url : 'saveTaskRole.do',
		type : 'post',
		dataType : 'json',
		data : {
			taskRuleName : taskRuleName
		},
		success : function(res) {
			 if (res.code == 0) {
				layer.msg("添加成功!");
				setTimeout(function(){
					window.location.href = "taskRuleList.do";
				},1000);
			}else{
				layer.msg(res.msg,{time:3000});
	    		return;
			}
		}
	});
}
function goPageAction(page){
	window.location.href="${ctx}/taskRule/taskRuleList.do?pageNo="+page+"&ruleName=${ruleName}";
	//+"&goodsName=${goodsName}&searchBeginDate=${searchBeginDate}&searchEndDate=${searchBeginDate}"
	}
function goMyPage(page,totalPage){
	if(page>0 && page<=totalPage){
		window.location.href="${ctx}/taskRule/taskRuleList.do?pageNo="+page+"&ruleName=${ruleName}";;
	}else{
		alert("请输入有效的整数");
	}
}

</script>
</html>