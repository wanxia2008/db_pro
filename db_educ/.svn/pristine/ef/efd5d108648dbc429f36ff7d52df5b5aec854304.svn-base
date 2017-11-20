<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>作业列表</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th style="text-align: center;">知识范围</th>
			<th style="text-align: center;">作业科目</th>
			<th style="text-align: center;">作业题目</th>
			<th style="text-align: center;">作业难度</th>
			<th style="text-align: center;">使用次数</th>
			<th style="text-align: center;">创建时间</th>
			<th style="text-align: center;">操作</th>
		</tr>
		<c:forEach items="${homeworks}" var="homework">
			<tr>
				<td><c:out value="${homework.getGrade()}"></c:out></td>
				<td><c:out value="${homework.getSubject2().getSubjectName()}"></c:out></td>
				<td>${homework.getHkName()}</td>
				<td><c:if test="${homework.getDifficultyValue()==1}">
						<ul class="uls_showstars">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					</c:if> <c:if test="${homework.getDifficultyValue()==2}">
						<ul class="uls_showstars">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star" id="2" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					</c:if> <c:if test="${homework.getDifficultyValue()==3}">
						<ul class="uls_showstars">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star" id="2" style="cursor: pointer;"></li>
							<li class="icon-star" id="3" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					</c:if> <c:if test="${homework.getDifficultyValue()==4}">
						<ul class="uls_showstars">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star" id="2" style="cursor: pointer;"></li>
							<li class="icon-star" id="3" style="cursor: pointer;"></li>
							<li class="icon-star" id="4" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					</c:if> <c:if test="${homework.getDifficultyValue()==5}">
						<ul class="uls_showstars">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star" id="2" style="cursor: pointer;"></li>
							<li class="icon-star" id="3" style="cursor: pointer;"></li>
							<li class="icon-star" id="4" style="cursor: pointer;"></li>
							<li class="icon-star" id="5" style="cursor: pointer;"></li>
						</ul>
					</c:if></td>
				<td>${homework.getUserTimes()}</td>
				<td><fmt:formatDate value="${homework.getCreateTime()}"
						pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td>
					<button type="button" class="layui-btn add_note"
						data-id="${homework.getHkId()}"
						data-subject="${homework.getSubject()}" data-courseid=""
						data-classid=""
						style="padding: 0 10px; height: 25px; line-height: 25px; font-size: 14px;">
						添加</button>
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
						</c:otherwise>
					</c:choose>
					<a href="javascript:;" onclick="goPageAction(${totalPages});">末页</a>
					&nbsp;总共&nbsp; ${totalPages} &nbsp;页
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp; <input
						type="text" id="goPageId"
						style="width: 50px; height: 30px; line-height: 30px; text-align: center"
						value="${page}" name="goPage" />&nbsp;&nbsp;页 <a
						href="javascript:;"
						onclick="goMyPage($('#goPageId').val(),${totalPages});">&nbsp;&nbsp;Go</a>
				</div>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script>
	layui.use([ 'form', 'layedit', 'laydate', 'layer' ], function() {
		var form = layui.form();
		var layer = layui.layer;
		var layedit = layui.layedit;
		var laydate = layui.laydate;
		var layer = layui.layer;

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
			layer.msg('开关checked：' + (this.checked ? 'true' : 'false'), {
				offset : '6px'
			});
			layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
		});
		//监听提交
		form.on('submit(demo1)', function(data) {
			layer.alert(JSON.stringify(data.field), {
				title : '最终的提交信息'
			})
			return false;
		});
	});
	$('.icon-plus-sign-alt').on('click',function() {
		var courseid = $(this).attr("data-id");
		var classid = $(this).attr("data-classid");
		$('.add_note').attr('data-courseid',courseid);
		$('.add_note').attr('data-classid',classid);
	});
	$('.add_note').on('click',function(){
		var courseid = $(this).attr('data-courseid');
		var subject = $(this).attr('data-subject');//科目
		var hkid = $(this).attr('data-id');
		var classid = $(this).attr('data-classid');
		layer.confirm('确认添加该考卷吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'updateHomework.do',
				type:'post',
				dataType:'json',
				data:{courseId:courseid,homeworkId:hkid,subject:subject,classid:classid},
				success:function(res) {
					if(res.code ==1) {
						layer.msg("添加失败！请重试");
					} else if(res.code ==0) {
						window.location.href="homeworkmanage.do";
					} else if(res.code==2) {
						layer.msg(res.msg);
					}
				}
			})
			layer.close(index);
		});
	})
	
	function goPageAction(page){
		window.location.href="${ctx}/homework/homeworPaperList.do?pageNo="+page;
		//
		}
	function goMyPage(page,totalPage){
		if(page>0 && page<=totalPage){
			window.location.href="${ctx}/homework/homeworPaperList.do?pageNo="+page;
		}else{
			alert("请输入有效的整数");
		}
	}
</script>
</html>