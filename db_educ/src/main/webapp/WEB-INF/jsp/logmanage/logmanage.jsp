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
<title>日志管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript"
	src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.a-btn {
	height: 30px;
	line-height: 30px;
	padding: 0 18px;
	position: absolute;
	left: 25px;
	top: 20%;
	font-size: 10px;
	border-radius: 5px;
	background: #e33
}
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
</style>
</head>
<body>
	<form id="frm1" class="layui-form" action="">
		<div class="panels_head">
			<span>日志列表</span>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;font-size: 14px;">操作用户</label>
				<div class="layui-input-inline" style="width: 120px;">
					<input type="text" name="operationName" lay-verify="operationName"
						value="${log.operationName}" autocomplete="off"
						placeholder="请输入用户名称" class="layui-input" maxlength="6">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;font-size: 14px;">操作时间</label>
				<div class="layui-input-inline" style="width: 120px;">
					<input type="text" placeholder="选择时间" name="startCreateTime"
						value="${startCreateTime}" class="layui-input"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
				</div>
				<label class="layui-form-label" style="margin-left: -45px;font-size: 14px;">到</label>
				<div class="layui-input-inline" style="width: 120px;">
					<input type="text" placeholder="选择时间" name="endCreateTime"
						value="${endCreateTime}" class="layui-input"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
				</div>
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">搜索</button>
			</div>
		</div>
		<c:choose>
			<c:when test="${logList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size: 14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<button type="button" class="layui-btn layui-btn-normal"
					onclick="window.location.href='exportLog.do?startTime=${startCreateTime}&endTime=${endCreateTime}'"
					style="height:30px; line-height:30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; margin-left: 1%; border-radius:4px; font-size: 14px">导出日志</button>
				<button type="button" class="layui-btn layui-btn-danger"
					onclick="DelSelect()"
					style="height:30px; line-height:30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; border-radius:4px; font-size: 14px; margin-left: 0">批量删除</button>
				<table class="layui-table" style="width: 98%; margin: 0 auto;">
					<thead>
						<tr>
							<th><input type="checkbox" name="" lay-skin="primary"
								lay-filter="allChoose"></th>
							<th>Id</th>
							<th>操作模块</th>
							<th>操作类型</th>
							<th>操作用户</th>
							<th>操作结果</th>
							<th>操作时间</th>
							<th>操作描述</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${logList}" var="log">
							<tr>
								<td><input type="checkbox" name="checkbox" lay-skin="primary"
									value="<c:out value="${log.logId}"></c:out>"></td>
								<td><c:out value="${log.logId}"></c:out></td>
								<td>${log.modular}<%-- <c:out value="${log.modularType.modularName}"></c:out> --%></td>
								<td><c:out value="${log.operationType}"></c:out></td>
								<td><c:out value="${log.operationName}"></c:out></td>
								<td><c:out value="${log.operationResult}"></c:out></td>
								<td><fmt:formatDate value="${log.startTime}"
										pattern="yyyy-MM-dd"></fmt:formatDate></td>
								<td><c:out value="${log.operationContent}"></c:out></td>
								<td><a class="button border-red" href="javascript:void(0)"
									onclick="deleteone(<c:out value="${log.logId}"></c:out>)">
										<span class="icon-remove-sign"
										style="font-size: 14px; color: #ff5722; padding-right: 10px;">
											删除</span>
								</a></td>
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
		<!-- <div id="pages" style="text-align: center;"></div> -->
	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
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

			//全选
			form.on('checkbox(allChoose)', function(data) {
				var child = $(data.elem).parents('table').find(
						'tbody input[name="checkbox"]');
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
	
	function goPageAction(page){
		 window.location.href="${ctx}/logger/logmanage.do?pageNo="+page+"&endCreateTime=${endCreateTime}&startCreateTime=${startCreateTime}&operationName=${log.operationName}"; 
		//
		}
	
		function goMyPage(page,totalPage){
			
			if(page>0 && page<=totalPage){
				 window.location.href="${ctx}/logger/logmanage.do?pageNo="+page+"&endCreateTime=${endCreateTime}&startCreateTime=${startCreateTime}"; 
			}else{
				alert("请输入有效的整数");  
			}
		}
		
		
		window.onload = function() {
		    //这么写是为了实现js代码与html代码的分离，
		   // 当我修改js时，不能影响html代码。
		    document.getElementById("frm1").onsubmit = function() {
		        var d1 = this.startCreateTime.value;
		        var d2 = this.endCreateTime.value;
		     
		        if (!compareDate(d1, d2)) {
		           layer.msg("第二个日期不能比第一个日期小!");
		            return false;
		        }
		    };
		}
		function compareDate(d1, d2) {
		    var arrayD1 = d1.split("-");
		    var date1 = new Date(arrayD1[0], arrayD1[1], arrayD1[2]);
		    var arrayD2 = d2.split("-");
		    var date2 = new Date(arrayD2[0], arrayD2[1], arrayD2[2]);
		    if (date1 > date2)
		        return false;
		    return true;
		}
		function verifyDate(d) {
		    var datePattern = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
		    return datePattern.test(d);
		}
		
		
		
		
		function deleteone(uid){
			 layer.confirm('您确认删除该条日志么?',{btn:['确认','取消']},
					 function(){
				 $.post('${ctx}/logger/delete.do?',{'logId':uid},function(data){
					  if(data){
							layer.msg('删除日志成功');
						      setTimeout(function(){
						    		  window.location.reload();	  
						      },2000);
					  }else{
							layer.msg('删除日志失败',{time:2500});
					  }
				 });
			    }, function(){	
			    	 layer.closeAll();  
			});
		}
		
		//全选
// 		$("#checkall").click(function(){ 
// 		  $("input[name='id[]']").each(function(){
// 			  if (this.checked) {
// 				  this.checked = false;
// 			  }
// 			  else {
// 				  this.checked = true;
// 			  }
// 		  });
// 		})
		
		
		//批量删除日志
function DelSelect(){
	var Checkbox=false;
	 $("input[name='checkbox']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		layer.confirm('您确认删除选中的日志?',{btn:['确认','取消']},function(){
			   var checkedList = new Array(); 
				$("input[name='checkbox']:checked").each(function() { 
				checkedList.push($(this).val()); 
				}); 
				//执行ajax
				$.ajax({ 
					type: "POST", 
					url: "${ctx}/logger/batchDelete.do", 
					data: {'logIds':checkedList.toString()}, 
					success: function(result) { 		 
					 if(result.code==0){
						 layer.msg('批量删除日志成功!');
						 window.location.href="${ctx}/logger/logmanage.do";
					 }else{
						 layer.msg(result.msg);
						 return;
					 }
					},
					
					}); 
		    }
		
		
		
		,function(){
			 layer.closeAll(); 
		       $('#checkall').attr("checked",false);
			   $("[name ='checkbox']:checkbox").attr("checked", false); 
			   
		});
	}else{
		layer.msg('请选择您要删除的日志!',{time:3000,icon:0});
		return false;
	}
}
		
</script>
</html>