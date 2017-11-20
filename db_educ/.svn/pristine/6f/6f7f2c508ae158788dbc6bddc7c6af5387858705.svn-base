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
<title>考试试卷管理</title>
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
			<span>考试试卷列表</span>
			<button type="button" class="layui-btn layui-btn-danger"
				onclick="window.location.href='${ctx}/examRecord/examPaperList.do'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">返回</button>
		</div>
		<c:choose>
			<c:when test="${crList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size: 14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table"
					style="width: 98%; margin-left: auto; margin-right: auto;">
					<thead>
						<tr>
							<th>试卷Id</th>
							<th>试卷名称</th>
							<th>试卷类型</th>
							<th>试卷科目</th>
							<th>试卷难度</th>
							<th>创建时间</th>
							<th>试卷分析</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${crList}" var="lecture">
							<tr>
								<td>${lecture.paperId}</td>
								<td><a
									href="${ctx}/exam/paperTypeDetails.do?piId=${lecture.paperId}&type=2"
									style="color: #008bca !important">${lecture.paInfo.piName}</a></td>
								<td>${lecture.taskType.taskTypeName}</td>
								<td>${lecture.subject.subjectName}</td>
								<td><c:if test="${lecture.paInfo.difficultyValue == 1}">
										<div class="layui-input-inline" style="width: 110px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${lecture.paInfo.difficultyValue==2}">
										<div class="layui-input-inline" style="width: 120px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star" id="2" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${lecture.paInfo.difficultyValue==3}">
										<div class="layui-input-inline" style="width: 120px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star" id="2" style="cursor: pointer;"></li>
												<li class="icon-star" id="3" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${lecture.paInfo.difficultyValue==4}">
										<div class="layui-input-inline" style="width: 120px;">
											<ul class="uls_showstars">
												<li class="icon-star" id="1" style="cursor: pointer;"></li>
												<li class="icon-star" id="2" style="cursor: pointer;"></li>
												<li class="icon-star" id="3" style="cursor: pointer;"></li>
												<li class="icon-star" id="4" style="cursor: pointer;"></li>
												<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
											</ul>
										</div>
									</c:if> <c:if test="${lecture.paInfo.difficultyValue==5}">
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
								<td><a
									href="${ctx}/examRecord/paperInfoAnalysis.do?&classId=${lecture.getClassId()}&paperId=${lecture.paperId}">
										<span class="" style="font-size: 14px; color: #008bca;">分析</span>
								</a>&nbsp;&nbsp;</td>
								<td><c:if
										test="${lecture.paperId !=null && lecture.getStatus()==0}">
										<p>
											<span class="icon-plus-sign-alt"
												data-classid="${lecture.getClassId()}"
												data-cid="${lecture.recordId}"
												data-sub="${lecture.paInfo.subject}"
												data-grade="${lecture.paInfo.grade}"
												style="font-size: 14px; color: #008bca; cursor: pointer;">替换</span>
										</p>
										<p>
											<span data-toggle="modal" id="openexam"
												class="openexam icon-bullhorn"
												data-classid="${lecture.getClassId()}"
												data-id="${lecture.recordId}"
												data-paperid="${lecture.paperId}"
												data-type="${lecture.paInfo.piType}"
												style="font-size: 14px; color: #008bca; cursor: pointer;">启动</span>
										</p>
										<p>
											<span data-toggle="modal"
												onclick="revokePaper(${lecture.recordId},${lecture.getClassId()})"
												class="icon-bullhorn"
												style="font-size: 14px; color: #008bca; cursor: pointer;">撤回</span>
										</p>
									</c:if> <c:if test="${lecture.getStatus()==1}">
										<a
											href="${ctx}/examRecord/paperCompletion.do?&classId=${lecture.getClassId()}&paperId=${lecture.paperId}">
											<span class="icon-eye-open"
											style="font-size: 14px; color: #008bca;"> 查看</span>
										</a>&nbsp;&nbsp;
														<span data-toggle="modal" class="closeexam icon-bullhorn"
											id="closeexam" data-id="${lecture.recordId}"
											data-paperid="${lecture.paperId}"
											data-classid="${lecture.getClassId()}"
											style="font-size: 14px; color: #008bca; cursor: pointer;">结束</span>

									</c:if> <c:if test="${lecture.getStatus()==2}">
										<a
											href="${ctx}/examRecord/paperCompletion.do?&classId=${lecture.getClassId()}&paperId=${lecture.paperId}">
											<span class="icon-eye-open"
											style="font-size: 14px; color: #008bca;"> 查看</span>
										</a>&nbsp;&nbsp;
														<span style="font-size: 14px; color: #008bca;">已结束</span>

									</c:if></td>
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
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">设置考试</h4>
					</div>
					<div class="modal-body">
						<table class="layui-table"
							style="width: 98%; margin-left: auto; margin-right: auto; margin-top: 10px;">
							<thead>
								<tr>
									<th style="text-align: center;">知识范围</th>
									<th style="text-align: center;">试卷科目</th>
									<th style="text-align: center;">试卷类型</th>
									<th style="text-align: center;">试卷题目</th>
									<th style="text-align: center;">试卷难度</th>
									<th style="text-align: center;">使用次数</th>
									<th style="text-align: center;">创建时间</th>
									<th style="text-align: center;">操作</th>
								</tr>
							</thead>
							<tbody id="tbody1">
							</tbody>
						</table>
						<div id="page" style="text-align: center;"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							style="height: 30px; line-height: 30px; padding: 0 18px;">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--模态框结束-->

		<!--模态框-->
		<div class="modal fade" id="myModalPaer" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
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
									<th>题目标签</th>
									<th>得分率</th>
								</tr>
							</thead>
							<tbody id="tbody11">

							</tbody>
						</table>
						<div id="pageId" style="text-align: center;"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							style="height: 30px; line-height: 30px; padding: 0 18px;">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--模态框结束-->
	</form>
	<script type="text/html" id="exam_template">
		{{each objects as value i}}
		<tr>
			<td>{{value.grade2.gradeName}}</td>
			<td>{{value.subject2.subjectName}}</td>
			<td>{{value.paperType.taskTypeName}}</td>
			<td>{{value.piName}}</td>
			<td>
				{{if value.difficultyValue==1}}
					<ul class="uls_showstars">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
					</ul>
				{{/if}} 
				{{if value.difficultyValue==2}}
					<ul class="uls_showstars">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
					</ul>
				{{/if}} 
				{{if value.difficultyValue==3}}
					<ul class="uls_showstars">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star" id="3" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
					</ul>
				{{/if}} 
				{{if value.difficultyValue==4}}
					<ul class="uls_showstars">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star" id="3" style="cursor: pointer;"></li>
						<li class="icon-star" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
					</ul>
				{{/if}} 
				{{if value.difficultyValue==5}}
					<ul class="uls_showstars">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star" id="3" style="cursor: pointer;"></li>
						<li class="icon-star" id="4" style="cursor: pointer;"></li>
						<li class="icon-star" id="5" style="cursor: pointer;"></li>
					</ul>
				{{/if}} 
			</td>
			<td>{{value.usedTimes}}</td>
			<td>{{value.createTime.substring(0,10)}}</td>
			<td>
				<button type="button" class="layui-btn layui-btn-normal add_note"
					data-id="{{value.piId}}" data-type="{{value.piType}}"
					data-subject="{{value.subject}}" data-courseid=""
					data-classid=""
					style="height:30px;line-height:30px;padding:0 18px;">
					添加</button>
			</td>
		</tr>
		{{/each}}
	</script>
	<script type="text/html" id="note_template">
		{{each objects as value i}}
		<tr>
			<td>{{value.questionName}}</td>
			<td>{{value.questionId}}</td>
			<td>{{value.trueNumber}}</td>
			<td>{{value.errorNumber}}</td>
            <td>{{value.tagName}}&nbsp;</td>
			<td>{{value.scoreRate}}%</td>
		</tr>
		{{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script>
	var pageno = 1, pagesize = 5,paperId,classId,subjectId,grade;
	var status=0;//判断列表状态
	layui.use([ 'form', 'layedit','laypage', 'laydate', 'layer' ], function() {
		var form = layui.form();
		var layer = layui.layer;
		var laypage = layui.laypage;
		var layedit = layui.layedit;
		var laydate = layui.laydate;
		var layer = layui.layer;

		//创建一个编辑器
		var editIndex = layedit.build('LAY_demo_editor');

		//自定义验证规则
		form.verify({
		
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

			return;
		});
	});
	
	$('.icon-plus-sign-alt').on('click',function() {
		var classid = $(this).attr("data-classid");
		var recordId = $(this).attr("data-cid");
		grade = $(this).attr("data-grade");
		subjectId = $(this).attr("data-sub");
		$('.add_note').attr('data-classid',classid);
		$('.add_note').attr('data-cid',recordId);
		$('#myModal').modal('show');
		getExam(pageno,grade,subjectId);
	});
	
	
	function getExam(pageno,grade,subjectId) {
		$.getJSON("${ctx}/lesson/getpublicexam.do", {
			pageNo : pageno,
			pageSize : pagesize,
			type:10,
			grade : grade,
			subject : subjectId
		}, function(res) {
			if (res.code == 0) {
				status=1;
				var html = template('exam_template', res);
				$('#tbody1').html(html);
				click_addexam();
				if (res.count > 1) {
					laypage({
						cont : 'page',
						pages : res.count,
						skin : 'molv',
						curr : pageno || 1,//当前页
						groups : 5,
//						skip : true,
						jump : function(obj, first) {
							//触发分页后的回调
							if (!first) {
								//点击跳页触发函数自身，并传递当前页：obj.curr
								getExam(obj.curr);
							}
						}
					});
				}
			} else {
				layer.msg(res.msg);
			}
		})
	}
	
	
	function paperInfoAnalysis(pageno,paperId,classId) {
		$.getJSON("${ctx}/examRecord/paperInfoAnalysis.do", {
			pageNo : pageno,
			pageSize : pagesize,
			paperId:paperId,
			classId:classId
		}, function(res) {
			if (res.code == 0) {
				status=1;
				var html = template('note_template', res);
				console.log(JSON.stringify(res));
				$('#tbody11').html(html);
				click_addnote();
				if (res.count > 1) {
					laypage({
						cont : 'pageId',
						pages : res.count,
						skin : 'molv',
						curr : pageno || 1,//当前页
						groups : 5,
						skip : true,
						jump : function(obj, first) {
							//触发分页后的回调
							if (!first) {
								//点击跳页触发函数自身，并传递当前页：obj.curr
								paperInfoAnalysis(obj.curr);
							}
						}
					});
				}
			} else{
				layer.msg(res.msg);
			}
		})
	}
	
	
	click_addexam();
	function click_addexam() {
		$('.add_note').on('click',function(){
			var type = $(this).attr('data-type');//试卷类型
			var subject = $(this).attr('data-subject');//科目
			var paperId = $(this).attr('data-id');
			var recordId = $(this).attr('data-cid');
			layer.confirm('确认替换该考卷吗？', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理,暂未添加到记录表
				$.ajax({
					url:'replacePaperId.do',
					type:'post',
					dataType:'json',
					data:{paperId:paperId,subject:subject,type:type,recordId:recordId},
					success:function(res) {
						 if(res.code ==0) {
// 							window.location.href="examPaperList.do?";
							 window.location.reload();
						} else {
							layer.msg(res.msg,{time:3000});
						}
					}
				})
				layer.close(index);
			});
		})
	}
	
	$('.openexam').on('click',function(){
		var classid = $(this).attr('data-classid');
		var examid = $(this).attr('data-paperid');
		var type = $(this).attr("data-type"); 
		var recordId =$(this).attr('data-id');
		layer.confirm('确认启动考试吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'startExam.do',
				type:'post',
				dataType:'json',
				data:{classid:classid,type:type,recordId:recordId},
				success:function(res) {
					if(res.code ==1) {
						layer.msg(res.msg);
					} else if(res.code ==0) {
						window.location.href="paperList.do?paperId="+examid+"&classId="+classid;
						window.location.reload();
					}
				}
			})
			layer.close(index);
		});
	});
	
	
	
	//撤销考试
	function revokePaper(recordId,classId){
		layer.confirm('确认撤回考试吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'deleteExam.do',
				type:'post',
				dataType:'json',
				data:{recordId:recordId},
				success:function(res) {
					if(res.code ==0) {
						layer.msg('撤销考试成功');
						window.location.href="${ctx}/examRecord/paperList.do?classId="+classId;
// 						window.location.reload();
					}else{
						layer.msg(res.msg);
					}
				}
			})
			layer.close(index);
		});
	}
	
	$('.closeexam').on('click',function(){
		var classid = $(this).attr('data-classid');
		var examid = $(this).attr('data-paperid');
		var recordid = $(this).attr("data-id");
		layer.confirm('确认结束考试吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'endExam.do',
				type:'post',
				dataType:'json',
				data:{recordId:recordid,classid:classid},
				success:function(res) {
					 if(res.code ==0) {
						window.location.href="paperList.do?classId="+classid+"&paperId="+examid;
// 						window.location.reload();
					} else{
					     layer.msg(res.msg,{time:2000});
						return;
					}
				}
			})
			layer.close(index);
		}); 
	});
	function goPageAction(page){
		window.location.href="${ctx}/examRecord/paperList.do?pageNo="+page+"&classId=${classId}";
		
		}
</script>
</html>