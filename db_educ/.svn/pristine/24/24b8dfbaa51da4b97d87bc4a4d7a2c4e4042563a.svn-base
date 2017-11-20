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
<title>我的试卷列表</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
.layui-form-checked span, .layui-form-checked:hover span {
	background-color: #35CE8D;
}

.layui-form-switch {
	width: 55px;
}

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
.icon-star,.icon-star-empty{color:#333 !important}
ol, ul{margin-bottom:0;}
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="${ctx}/exam/showmyexam.do">
		<div class="panels_head">
			<span>我的试卷</span> <!-- <a class="layui-btn a-btn" href="createexam.do">新增试卷</a> -->
			<button type="button" class="layui-btn layui-btn-normal"
				lay-submit="" onclick="window.location.href='createexam.do'"
				lay-filter="demo1"
				style="height: 30px; line-height: 30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; font-size: 14px">新增</button>


		</div>
		<div class="layui-form-item" style="margin-top: 20px">
		<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="grade" id="grade">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${gList}" var="g">
						<c:choose>
						   <c:when test="${pInfo.grade==g.gradeId}">
						        <option selected="selected" value="${g.gradeId}">${g.gradeName}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.gradeId}">${g.gradeName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="margin-left: -10px;font-size: 14px;">科目</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="subject" id="subject">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${sList}" var="g">
						<c:choose>
						   <c:when test="${pInfo.subject==g.subjectId}">
						        <option selected="selected" value="${g.subjectId}">${g.subjectName}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.subjectId}">${g.subjectName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
		<div class="layui-inline">
				<label class="layui-form-label" style="margin-left:-10px;font-size:14px;width: 100px;">试卷类型</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="piType" id="piType">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${qsstList}" var="g">
						<c:choose>
						   <c:when test="${pInfo.piType==g.typeId}">
						        <option selected="selected" value="${g.typeId}">${g.taskTypeName}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.typeId}">${g.taskTypeName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			

			


		<%-- 	<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;font-size: 14px;">试卷状态</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="isPublic" id="isPublic">
						<option value="">全部</option>
						<option value="">全部</option>
						<option value="0"
							<c:choose>
									<c:when test="${pInfo.isPublic==0}">selected="selected"</c:when>
									</c:choose>>私有</option>
						<option value="1"
							<c:choose>
									<c:when test="${pInfo.isPublic==1}">selected="selected"</c:when>
									</c:choose>>公有</option>
					</select>
				</div>
			</div> --%>
			
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;font-size: 14px;">试卷名称</label>
				<div class="layui-input-inline" style="width: 120px;">
					<input type="text" name="piName" id="piName"
						value="${pInfo.piName}" placeholder="请输入试卷标题" class="layui-input" maxlength="15">
				</div>
				<button class="layui-btn layui-btn-normal searchbtn" lay-submit="" lay-filter="demo1" id="search_content">搜索</button>
			</div>

		</div>
		<%-- <div class="panels_head">
			<span>试卷列表</span> <a href="javascript:void(0)"
				style="float: right; font-size: 15px; margin-right: 100px; color: #35ce8d">试卷共:${rowsCount}份</a>
			<a href="javascript:void(0)"
				style="float: right; font-size: 15px; margin-right: 100px; color: #35ce8d">公有共:${isPublicCount}份</a>
		</div> --%>
		<span class=""
				style="height: 40px; line-height: 40px; padding: 0 18px; color: #919191; top: 20%; margin-top: 0;margin-left:1%;border-radius:0; font-size: 14px">试卷共:${rowsCount}份</span>
		<span  class=""
				style="height: 40px; line-height: 40px; padding: 0 18px; color: #919191; top: 20%; margin-top: 0;border-radius:0; font-size: 14px;margin-left:0">公有试卷：${isPublicCount}份</span>
		<c:choose>
			<c:when test="${piList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size: 14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
					<table class="layui-table" style="width:98%;margin:0 auto">
					 <thead>
						<tr>
							<th>状态</th>
							<th>试卷ID</th>
							<th>试卷标题</th>
							<th>年级</th>
							<th>科目</th>
							<th>试卷难度</th>
							<th>使用次数</th>
							<th>作者</th>
							<th>共多少题</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${piList}" var="row">
							<tr>
								<c:if test="${row.isPublic == 1}">
									<td>
										<div class="layui-input-block" style="margin-left: 0px;">
											<input type="checkbox" checked="" name="open"
												lay-skin="switch" id="${row.piId}" lay-filter="switchTest"
												lay-text="公|私">
										</div>
									</td>
								</c:if>
								<c:if test="${row.isPublic == 0}">
									<td>
										<div class="layui-input-block" style="margin-left: 0px;">
											<input type="checkbox" name="close" lay-skin="switch"
												id="${row.piId}" lay-filter="switchTest" lay-text="公|私">
										</div>
									</td>
								</c:if>
								<td><c:out value="${row.piId}"></c:out></td>
								<td><c:out value="${row.piName}"></c:out></td>
								<td><c:out value="${row.grade2.gradeName}"></c:out></td>
								<td>
								${row.subject2.subjectName}
<%-- 								<fmt:formatDate value="${row.createTime}" --%>
<%-- 										pattern="yyyy-MM-dd"></fmt:formatDate> --%>
										</td>
								<td><c:if test="${row.difficultyValue == 1}">
										<div class="layui-input-inline" style="width: 110px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${row.difficultyValue==2}">
										<div class="layui-input-inline" style="width: 120px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star" id="2" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${row.difficultyValue==3}">
										<div class="layui-input-inline" style="width: 120px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star" id="2" style="cursor: pointer;"></li>
												<li class="icon-star" id="3" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${row.difficultyValue==4}">
										<div class="layui-input-inline" style="width: 120px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star" id="2" style="cursor: pointer;"></li>
												<li class="icon-star" id="3" style="cursor: pointer;"></li>
												<li class="icon-star" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${row.difficultyValue==5}">
										<div class="layui-input-inline" style="width: 120px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star" id="2" style="cursor: pointer;"></li>
												<li class="icon-star" id="3" style="cursor: pointer;"></li>
												<li class="icon-star" id="4" style="cursor: pointer;"></li>
												<li class="icon-star" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if></td>
						        <td>${row.usedTimes}&nbsp;次</td>
								<td><c:out value="${row.buildUserName}"></c:out></td>
								<td><c:out value="${row.topicTotai}"></c:out>&nbsp;道</td>
								<td><a
									href="${ctx}/exam/modity.do?piId=<c:out value="${row.piId}"></c:out>&pageNo=${page}">
										<span class="icon-edit"
										style="font-size: 14px; color: #008bca; cursor: pointer;">修改</span>
								</a> &nbsp; <a
									href="${ctx}/exam/paperTypeDetails.do?piId=<c:out value="${row.piId}"></c:out>&type=1&pageNo=${page}">
										<span class="icon-eye-open"
										style="font-size: 14px; color: #008bca;"> 查看</span>
								</a>&nbsp; <a
									href="javascript:void(0)" onclick="changeExamStatus(${row.piId},${row.isStart})">
									<c:if test="${row.isStart == 1}">
									  <span class="icon-eye-open"
										style="font-size: 14px; color: #008bca;">启用</span>
									</c:if>
									
									<c:if test="${row.isStart == 0}">
									  <span class="icon-eye-open"
										style="font-size: 14px; color:#ff5722;">停用</span>
									</c:if>
										
								</a> 
								 &nbsp; <a class="button border-red" href="javascript:void(0)"
									onclick="deleteone(${row.piId},${row.usedTimes})"> <span
										class="icon-remove-sign"
										style="font-size: 14px; color: #ff5722; padding-right: 10px;">
											删除</span>
								</a></td>
							</tr>
						</c:forEach>
						<tr id="pages">
							<td colspan="10">
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
											</a>&nbsp;
										</c:otherwise>
									</c:choose>		
									<a href="javascript:;" onclick="goPageAction(${totalPages});">末页</a>
									&nbsp;总共&nbsp; ${totalPages} &nbsp;页
									<%-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp; <input
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
</body>
<script type="text/javascript" src=".././js/jquery-1.8.3.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
layui.use([ 'form', 'laypage', 'laydate' ], function() {
	var form = layui.form(), laypage = layui.laypage, layer = layui.layer;
	var $ = layui.jquery;
	
	//监听指定开关
	form.on('switch(switchTest)', function(data){
		var id = this.id;
		var ispublic=this.checked?1:0;
		$.ajax({
			url:'${ctx}/exam/share.do?',
			type:'post',
			dataType:'json',
			data:{piId:id,ispublic:ispublic},
			success:function(res) {
				if(res.code==0) {
					layer.msg(res.msg);
					setTimeout(function(){
						window.location.reload();
					},400);
				} else {
					layer.msg(res.msg);
					setTimeout(function(){
						window.location.reload();
					},3000);
					this.checked = !this.checked;
				}
			}
		})
	});
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
	
	
	
	function deleteone(uid,useTime){
		if(useTime>=1) {
			layer.confirm('考卷已被调用，无法执行删除操作！', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理
				layer.close(index);
			});
		} else {
			layer.confirm('确认删除该考卷吗？', {
				btn : [ '确定','取消' ]
			}, function(index, layero) {//确定的处理
				$.ajax({
					url:'${ctx}/exam/delete.do',
					type:'post',
					dataType:'json',
					data:{
						piId:uid
					},
					success:function(data){
						if(data.code==0){
							layer.msg('删除成功!');
							setTimeout(function(){
								window.location.reload();
							},1000);
						}else{
							layer.msg(data.msg);
							return;
						}
					},
				});
			},function(){
			       layer.closeAll();  
			});
		}
	}
	
	   $('#search_content').click(function(){
	    	
	    	var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i");   
	    	var schoolName2=$('#piName').val();
	    	
	        if(pat.test(schoolName2)==true)   
	        {   
	            layer.msg("输入名称中含有非法字符!");   
	            return false;   
	        } else{
	        	$('#myform').submit();
	        }
	    });
	   
	function goPageAction(page){
		window.location.href="${ctx}/exam/showmyexam.do?pageNo="+page+"&grade=${pInfo.grade}&subject=${pInfo.subject}&piType=${pInfo.piType}&piName=${pInfo.piName}&piType=${pInfo.piType}";
		//+"&goodsName=${goodsName}&searchBeginDate=${searchBeginDate}&searchEndDate=${searchBeginDate}"
		}
	function goMyPage(page,totalPage){
		if(page>0 && page<=totalPage){
			window.location.href="${ctx}/exam/showmyexam.do?pageNo="+page+"&grade=${grade}&subject=${subject}&piType=${piType}&piName=${piName}";
		}else{
			alert("请输入有效的整数");
		}
	}
	function addExam(){
		window.location.href = "${ctx}/exam/createexam.do";
	}
	
	function changeExamStatus(piId,isStart){
		var msg="";
		var is_start;
		if(isStart == 1){
			msg = "确认停用该份试卷？";
			is_start = 0;
		}else{
			msg = "确认启用该份试卷";
			is_start = 1;
		}
		
		layer.confirm(msg,{btn:['确定','取消']},function(){
			$.ajax({
				url:'${ctx}/exam/changeexamstatus.do',
				type:'post',
				dataType:'json',
				data:{'piId':piId,'isStart':is_start},
				success:function(data){
					if(data.code == 0){
						   if(isStart == 1){
							   layer.msg('该试卷停用成功');
						   }else{
							   layer.msg('该试卷开启成功');
						   } 
						  setTimeout(function(){
							  window.location.reload();  
						  },2000);
					}else{
						layer.msg(data.msg);
					}
				}
			})
		},function(){});
		
		
	}
	
</script>
</html>