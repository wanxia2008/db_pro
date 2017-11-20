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
<title>讲义管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<style type="text/css">
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
.icon-star,.icon-star-empty{color:#333 !important}
ol, ul{margin-bottom:0;}
.layui-laypage a, .layui-laypage span{margin-right:5px;padding:0 18px;border-radius:4px;font-size:14px;}
.layui-laypage .layui-laypage-curr .layui-laypage-em{border-radius:4px;}
.layui-laypage>:first-child, .layui-laypage>:first-child em{border-radius:4px;}
.layui-laypage button, .layui-laypage input{border-radius:4px;}
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="exammanage.do">
		<div class="panels_head">
			<span>讲义管理列表</span>
			<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/lesson/lecturenotesmanage.do'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
		</div>	
<!-- 		<div class="layui-form-item" style="margin-top: 20px;"> -->
<!-- 			<div class="layui-inline"> -->
<!-- 				<label class="layui-form-label" style="font-size:14px;">班级</label> -->
<!-- 				<div class="layui-input-inline" style="width: 150px;"> -->
<!-- 					<select name="classId" id="classId" lay-filter="select_class"> -->
<!-- 						<option value="">全部</option> -->
<!-- 						<option value="">全部</option> -->
<%-- 						<c:forEach items="${classList}" var="qt"> --%>
<%-- 							<option value="${qt.classId}" --%>
<%-- 								<c:if test="${classId eq qt.classId}">selected="selected"</c:if>>${qt.className}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<button class="layui-btn layui-btn-normal searchbtn" lay-submit="" lay-filter="demo1">搜索</button> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<c:choose>
			<c:when test="${crList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table"
					style="width: 98%; margin-left: auto; margin-right: auto;">
					<thead>
						<tr>
						    <th>讲义Id</th>
						    <th>讲义标题</th>
							<th>讲义年级</th>
							<th>讲义科目</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${crList}" var="lecture">
							<tr>
								<td>${lecture.lectureNotes.handoutId}</td>
								<td>${lecture.lectureNotes.handoutTitle}</td>
								<td>${lecture.grade.gradeName}</td>
								<td>${lecture.subject.subjectName}</td>
								<td><fmt:formatDate value="${lecture.createTime}"
										pattern="yyyy-MM-dd"></fmt:formatDate></td>
								<td>
									<a class="icon-minus-sign" data-toggle="modal"
									data-classid="${lecture.getClassId()}"
									data-id="${lecture.recordId}"
									style="font-size: 14px; color: #ff5722; cursor: pointer;">取消</a>
										
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
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
			<!--模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">设置讲义</h4>
                     </div>
					<div class="modal-body">
						<table class="layui-table"
							style="width: 98%; margin-left: auto; margin-right: auto; margin-top: 10px;">							
							<thead>
								<tr>
									<th>讲义编号</th>
									<th>讲义标题</th>
									<th>讲义科目</th>
									<th>知识范围</th>
									<th>使用次数</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="tbody1">
							
							</tbody>
						</table>
						<div id="page" style="text-align: center;"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--模态框结束-->
	</form>
	<script type="text/html" id="note_template">
		{{each objects as value i}}
		<tr>
			<td>{{value.handoutId}}</td>
			<td>{{value.handoutTitle}}</td>
			<td>{{value.subName.subjectName}}</td>
			<td>{{value.grades.gradeName}}</td>
			<td>{{value.usedCount}}</td>
			<td>{{value.createTime.substring(0,10)}}</td>
			<td>
				<button type="button" class="layui-btn add_note"
					data-id="{{value.handoutId}}" data-courseid=""
					data-classid="" data-date=""
					style="height:30px;line-height:30px;padding:0 18px;">
					添加</button>
			</td>
		</tr>
		{{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script>
	var pageno = 1, pagesize = 6;
	var status=0;
	layui.use([ 'form', 'layedit','laypage', 'laydate', 'layer' ], function() {
		var form = layui.form();
		var layer = layui.layer;
		var laypage = layui.laypage;
		var layedit = layui.layedit;
		var laydate = layui.laydate;
		var layer = layui.layer;

		//监听提交
		form.on('submit(demo1)', function(data) {

			return true;
		});
		
		getNote(pageno);
		function getNote(pageno) {
			$.getJSON("getpublicnote.do", {
				pageNo : pageno,
				pageSize : pagesize,
				subject :　$('#subject').val(),
				grade :　$('#grade').val()
			}, function(res) {
				if (res.code == 0) {
					status=1;
					var html = template('note_template', res);
					console.log(JSON.stringify(res));
					$('#tbody1').html(html);
					click_addnote();
					if (res.count > 1) {
						laypage({
							cont : 'page',
							pages : res.count,
							skin : 'molv',
							curr : pageno || 1,//当前页
							groups : 5,
							skip : true,
							jump : function(obj, first) {
								//触发分页后的回调
								if (!first) {
									//点击跳页触发函数自身，并传递当前页：obj.curr
									getNote(obj.curr);
								}
							}
						});
					}
				} else{
// 					layer.msg(res.msg);
				}
			})
		}
	});
	$('.icon-plus-sign-alt').on('click',function() {
		var classid = $(this).attr('data-classid');
		$('.add_note').attr('data-classid',classid);
		if(status==1) {
			$('#myModal').modal('show');
		} else {
			layer.msg("暂时没有对应的讲义，请添加讲义");
		}
	});
	$('.icon-minus-sign').on('click',function(){
		var record_id= $(this).attr('data-id');
		layer.confirm('确认取消该讲义吗?', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'${ctx}/lesson/deletenote.do',
				type:'post',
				dataType:'json',
				data:{recordId:record_id},
				success:function(res) {
					if(res.code==0) {
						layer.msg('取消该讲义成功!');
// 						window.location.href="lecturenotesmanage.do?date="+date+"&classId="+classid;
						window.location.reload();
					} else {
						layer.msg(res.msg);
						return;
					}
				}
			})
		});
	})
	
	
	click_addnote();
	function click_addnote() {
		$('.add_note').on('click',function(){
			var noteid = $(this).attr('data-id');
			var classid = $(this).attr('data-classid');
			layer.confirm('确认添加讲义?', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理
				$.ajax({
					url:'updateLectureNote.do',
					type:'post',
					dataType:'json',
					data:{classId:classid,lecturenoteId:noteid},
					success:function(res) {
						if(res.code ==0) {
							layer.msg('添加讲义成功!');
							window.location.href="lecturenotesmanage.do?date="+date+"&classId="+classid;
						}else{
							layer.msg(res.msg);
							return;
						}
					}
				})
				layer.close(index);
			});
		})
	}
	
	function goPageAction(page){
		window.location.href="${ctx}/lesson/lecturenotesmanage.do?pageNo="+page+"&date=${date}&classId=${classId}";
		
		}
</script>
</html>