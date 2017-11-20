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
			<span>作业管理列表</span>
		</div>	
		<div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;">班级</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="classId" id="classId" lay-filter="select_class">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${classList}" var="qt">
							<option value="${qt.classId}"
								<c:if test="${classId eq qt.classId}">selected="selected"</c:if>>${qt.className}</option>
						</c:forEach>
					</select>
				</div>
				<button class="layui-btn layui-btn-normal searchbtn" lay-submit="" lay-filter="demo1">搜索</button>
			</div>
		</div>
		<c:choose>
			<c:when test="${courseList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table"
					style="width: 98%; margin-left: auto; margin-right: auto;">
					<thead>
						<tr>
						    <th>年</th>
						    <th>校区</th>
							<th>年级</th>
							<th>班级</th>
							<th>科目</th>
							<th>作业情况</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${courseList}" var="lecture">
							<tr>
								<td>${lecture.schoolYear.year}</td>
								<td>${lecture.schoolZone.schoolName}</td>
								<td>${lecture.grade.gradeName}</td>
								<td>${lecture.classNo.className}</td>
								<td>${lecture.subject.subjectName}</td>
								<td>
								<c:choose>
										<c:when test="${lecture.recordList != null}">
											<span class="icon-eye-open" data-id="${lecture.classId}"
												style="font-size: 14px; color: #008bca; cursor: pointer;">
												查看</span> &nbsp;
								</c:when>
										<c:otherwise>
								   暂无作业
								 </c:otherwise>
									</c:choose>
								</td>
								<td>
<%-- 								<span class="icon-eye-open" data-id="${lecture.getClassId()}" --%>
<!-- 								style="font-size: 14px; color:#008bca;cursor:pointer;">查看</span> -->
<!-- 								&nbsp; -->
								<a class="icon-plus-sign-alt" 
									data-classid="${lecture.getClassId()}" 
									data-subject="${lecture.subject.subjectId}" data-grade="${lecture.grade.gradeId}"
									style="font-size: 14px; color:#008bca; cursor: pointer;">设置</a>

										
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
			        <h4 class="modal-title" id="myModalLabel">设置作业</h4>
            </div>
				<div class="modal-body">
						<table class="layui-table" style="width: 98%; margin-left: auto; margin-right: auto; margin-top: 10px;">
						<thead>
							<tr>
								<th>年级</th>
								<th>作业科目</th>
								<th>作业题目</th>
								<th>作业难度</th>
								<th>使用次数</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>	
						<tbody id="tbody23">
							
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
	<script type="text/html" id="note_template12">
		{{each objects as value i}}
		    <tr>
            <td>{{value.grade2.gradeName}}</td>
			<td>{{value.subject2.subjectName}}</td>
             <td>{{value.hkName}}</td>
			<td
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
            <td>{{value.userTimes}}</td>
			<td>{{value.createTime}}</td>
			<td>
			<button type="button"
			  class="layui-btn add_note"
				 data-id="{{value.hkId}}"
				 data-subject="{{value.subject}}" data-courseid=""
				 data-classid="">添加</button>
			</td>
		</tr>
      {{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script>
var pageno = 1, pagesize = 10,grade,subjectId;
var status=0;//判断列表状态
layui.use([ 'form', 'layedit','laypage', 'laydate', 'layer' ], function() {
	var form = layui.form();
	var layer = layui.layer;
	laypage = layui.laypage;
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

			return true;
		});
	});
	
	$('.icon-plus-sign-alt').on('click',function() {
		classid = $(this).attr("data-classid");
		$('.add_note').attr('data-classid',classid);
		grade = $(this).attr("data-grade");
		subjectId = $(this).attr("data-subject");
		$.ajax({
			url:'${ctx}/examRecord/isEnd.do',
		    type:'post',
		    dataType:'json',
		    data:{
		    	classId:classid
		    },
		    success:function(data){
		    	if(data.code==0){
		    		$('#myModal').modal('show');
		    		getNote(pageno,grade,subjectId);
		    	}else{
		    		layer.msg(data.msg,{time:1000});
		    	}
		    }
		})
	});
	
	
	function getNote(pageno,grade,subjectId) { 
		$.getJSON("${ctx}/homework/paperInfoList.do", {
			pageNo : pageno,
			pageSize:pagesize,
			grade:grade,
			subject:subjectId
		}, function(res) {
			if (res.code == 0) {
				var html = template('note_template12', res);
				$('#tbody23').html(html);
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
								getNote(obj.curr,grade,subjectId);
							}
						}
					});
				}
			} else{
				layer.msg(res.msg);
				var html = template('note_template12', res);
				$('#tbody23').html(html);
			}
		})
	}
	$('.icon-eye-open').on('click',function(){
		var classId=$(this).attr('data-id');
		$.ajax({
			url:'${ctx}/examRecord/testinghomework.do',
		    type:'post',
		    dataType:'json',
		    data:{
		    	classId:classId
		    },
		    success:function(data){
		    	if(data.code==0){
		    		window.location.href="${ctx}/examRecord/examhomeworkList.do?classId="+classId;
		    	}else{
		    		layer.msg(data.msg,{time:1000});
		    	}
		    }
		})
	});
	click_addnote();
	function click_addnote() {
	$('.add_note').on('click',function(){
		var hkid = $(this).attr('data-id');
		//var classid = $(this).attr('data-classid');
		layer.confirm('确认分发作业吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'addHomeworkCourse.do',
				type:'post',
				dataType:'json',
				data:{homeworkId:hkid,classid:classid},
				success:function(res) {
					 if(res.code ==0) {
						 layer.msg("分发作业成功");
						window.location.href="${ctx}/examRecord/examhomeworkList.do?classId="+classid;/* +"&homeworkId="+hkid */
					} else {
						layer.msg(res.msg,{time:1000});
						return;
					}
				}
			})
			layer.close(index);
		});
	})
}
	function quxiao(obj){
		var classid = $(obj).attr('data-classid');
		var hkid = $(obj).attr('data-hkid');
		var courseid = $(obj).attr("data-id");
		var date = $(obj).attr("data-date"); 
		layer.confirm('确认取消作业吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'cancelHomework.do',
				type:'post',
				dataType:'json',
				data:{classId:classid,homeworkId:hkid,courseId:courseid},
				success:function(res) {
					 if(res.code ==0) {
						window.location.href="homeworkmanage.do?date="+date+"&classId="+classid;
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
		window.location.href="${ctx}/homework/homeworkmanage.do?pageNo="+page+"&classId=${classId}";
		
		}
</script>
</html>