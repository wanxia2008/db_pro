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
<title>作业管理</title>
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
	<form id="myform" class="layui-form" action="">
		<div class="panels_head">
			<span>班级作业记录列表</span>
			<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/homework/homeworkmanage.do'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
		</div>	
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
						    <th>作业Id</th>
						    <th>作业标题</th>
							<th>作业年级</th>
							<th>作业科目</th>
							<th>作业难度</th>
							<th>创建时间</th>
							<th>作业分析</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${crList}" var="lecture">
							<tr>
								<td>${lecture.homework.hkId}</td>
								<td>
								<a href="${ctx}/homework/paperTypeDetails.do?hkId=${lecture.homework.hkId}" style="color:#008bca !important">${lecture.homework.hkName}</a>
								</td>
								<td>${lecture.grade.gradeName}</td>
								<td>${lecture.subject.subjectName}</td>
							<td><c:if test="${lecture.homework.difficultyValue == 1}">
										<div class="layui-input-inline" style="width: 110px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${lecture.homework.difficultyValue==2}">
										<div class="layui-input-inline" style="width: 120px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star" id="2" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${lecture.homework.difficultyValue==3}">
										<div class="layui-input-inline" style="width: 120px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star" id="2" style="cursor: pointer;"></li>
												<li class="icon-star" id="3" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${lecture.homework.difficultyValue==4}">
										<div class="layui-input-inline" style="width: 120px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star" id="2" style="cursor: pointer;"></li>
												<li class="icon-star" id="3" style="cursor: pointer;"></li>
												<li class="icon-star" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${lecture.homework.difficultyValue==5}">
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
								<td><fmt:formatDate value="${lecture.createTime}"
										pattern="yyyy-MM-dd"></fmt:formatDate></td>
							    <td>
										<a href="${ctx}/examRecord/homeworhAnalysis.do?&classId=${lecture.getClassId()}&homeworkId=${lecture.homeworkId}">
										<span class="" style="font-size: 14px; color:#008bca;">分析</span>
								         </a>
								</td>
								<td>
								<a href="${ctx}/homework/completion.do?&classId=${lecture.getClassId()}&paperId=${lecture.homeworkId}">
										<span class="icon-eye-open"
										style="font-size: 14px; color:#008bca;"> 查看</span>
								         </a>&nbsp;&nbsp;
									<span data-toggle="modal" class="icon-bullhorn"
											onclick="quxiao(${lecture.recordId},${lecture.getClassId()})" 
											style="font-size: 14px; color: #ff5722; cursor: pointer;">取消</span>
										
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
			        <h4 class="modal-title" id="myModalLabel">试卷分析</h4>
                     </div>
					<div class="modal-body">
						<table class="layui-table"
							style="width: 98%; margin-left: auto; margin-right: auto; margin-top: 10px;">							
							<thead>
								<tr>
								    <th>题型</th>
									<th>题目Id</th>
									<th>做对人数</th>
									<th>做错人数</th>
									<th>题目总数</th>
									<th>得分率</th>
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
			<td>{{value.questionName}}</td>
			<td>{{value.questionId}}</td>
			<td>{{value.trueNumber}}</td>
			<td>{{value.errorNumber}}</td>
             <td>{{value.questionTotal}}</td>
			<td>{{value.scoreRate}}%</td>
		</tr>
		{{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script>
	var pageno = 1, pagesize = 10,homeworkId,classId;
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
	});

	function quxiao(id,cid){
// 		var record_id = $(obj).attr("data-id");
		layer.confirm('确认取消作业吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'${ctx}/homework/cancelHomework.do',
				type:'post',
				dataType:'json',
				data:{recordId:id,classId:cid},
				success:function(res) {
					 if(res.code ==0) {
						window.location.href="${ctx}/examRecord/examhomeworkList.do?classId="+classid;
// 						 window.location.reload();
					}else{
						layer.msg(res.msg);
						return;
					}
				}
			})
			layer.close(index);
		});
	}
	
	function goPageAction(page){
		window.location.href="${ctx}/examRecord/examhomeworkList.do?pageNo="+page+"&classId=${classId}";
		}
</script>
</html>