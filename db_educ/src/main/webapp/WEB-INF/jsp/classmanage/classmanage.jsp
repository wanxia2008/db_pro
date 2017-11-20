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
<title>班级管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
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
	<form id="myform" class="layui-form" action="classmanage.do">
		<div class="panels_head">
			<span>班级列表</span>
					<button type="button" class="layui-btn layui-btn-normal"
				onclick="window.location.href='${ctx}/classes/newclasses.do'"
				
				style="height: 30px; line-height: 30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; font-size: 14px">新增</button>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
		<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 14px;">年份</label>
			<div class="layui-input-inline" style="width: 150px;">
					<select name="yearId" id="yearId" lay-filter="select_year">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${yearList}" var="year1">
							<c:choose>
								<c:when test="${yearId==year1.id}">
									<option selected="selected" value="${year1.id}">${year1.year}</option>
								</c:when>
								<c:otherwise>
									<option value="${year1.id}">${year1.year}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 14px;">季节</label>
			<div class="layui-input-inline" style="width: 150px;">
					<select name="seasontype" id="seasontype" lay-filter="select_seasontype">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${seasonList}" var="map">
							<c:choose>
								<c:when test="${seasontype==map.getSeasonId()}">
									<option selected="selected" value="${map.getSeasonId()}">${map.getSeasonName()}</option>
								</c:when>
								<c:otherwise>
									<option value="${map.getSeasonId()}">${map.getSeasonName()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
		</div>
			<div class="layui-inline">
			<label class="layui-form-label" style="font-size: 14px;">科目</label>
			<div class="layui-input-inline" style="width: 150px;">
					<select name="subject" id="subject" lay-filter="select_subject">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${subjectList}" var="sub">
							<c:choose>
								<c:when test="${subject==sub.getSubjectId()}">
									<option selected="selected" value="${sub.getSubjectId()}">${sub.getSubjectName()}</option>
								</c:when>
								<c:otherwise>
									<option value="${sub.getSubjectId()}">${sub.getSubjectName()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1"
					style="margin-left:10px;font-size:14px;">搜索</button>
			</div>
			
			
	</div>
		<c:choose>
			<c:when test="${classList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table"
					style="width: 98%; margin-left: auto; margin-right: auto;">
					
					<thead>
						<tr>
						    <th>ID</th>
							<th>年份</th>
							<th>校区</th>
							<th>类型</th>
							<th>科目</th>
							<th>年级</th>
							<th>班级名称</th>
							<th>任课老师</th>
							<th>总课程数</th>
							<th>是否结课</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${classList}" var="classes">
							<tr>
							    <td>${classes.classId}</td>
								<td>${classes.schoolYear.year}</td>
								<td>${classes.getSchoolZonelist().get(0).getSchoolName()}</td>
								<td>${classes.getSeasonTypelist().get(0).getSeasonName()}</td>
								<td>${classes.getSubjectlist().get(0).getSubjectName()}</td>
								<td>${classes.getGradelist().get(0).getGradeName()}</td>
								<td>${classes.getClassName()}</td>
								<td><c:choose>
										<c:when test="${classes.getTeacherId() !=0}">
											${classes.teacher.teacherName}
										</c:when>
										<c:otherwise>
											<p>暂无教师</p>
										</c:otherwise>
									</c:choose></td>
								<td>${classes.courseNumber}节</td>
								<c:if test="${classes.isEndCourse==0}">
									<td><button type="button"
											class="layui-btn layui-btn-normal endcurriculum"
											id="${classes.getClassId()}">结课</button></td>
								</c:if>
								<c:if test="${classes.isEndCourse==1}">
									<td><span style="color: #ff5722;">已结课</span></td>
								</c:if>
								<td>
                                <span class="icon-eye-open"  onclick="goCourse(${classes.getClassId()})"
                                style="font-size:14px; color:#008bca;cursor: pointer;">课程</span>&nbsp;
								<a href="toupdateclasses.do?classId=${classes.getClassId()}&page=${page}"><span
										class="icon-eye-open" style="font-size:14px; color:#008bca;">修改</span></a>
										&nbsp;
								<a class="button border-red"
									href="javascript:void(0)"
									onclick="deleteone(<c:out value="${classes.getClassId()}"></c:out>)">
										<span class="icon-remove-sign"
										style="font-size: 14px; color: #ff5722;">
											删除</span>
								   </a>
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

// 						form.on('select(select_subject)', function(data) {
// 							$('#myform').submit();
// 						});
						
// 						form.on('select(select_year)', function(data) {
// 							$('#myform').submit();
// 						});
						
// 						form.on('select(select_seasontype)', function(data) {
// 							$('#myform').submit();
// 						});
						
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
// 							layer.alert(JSON.stringify(data.field), {
// 								title : '最终的提交信息'
// 							})
							return true;
						});
					});
	
	
	function goPageAction(page){
		window.location.href="${ctx}/classes/classmanage.do?pageNo="+page+"&yearId=${yearId}&subject=${subject}&seasontype=${seasontype}";
		
		}
	function goMyPage(page,totalPage){
		if(page>0 && page<=totalPage){
			window.location.href="${ctx}/classes/classmanage.do?pageNo="+page;
		}else{
			alert("请输入有效的整数");
		}
	}
	function deleteone(uid){
		 layer.confirm('您确认删除班级么?',{btn:['确认','取消']},function(){
			 $.post('${ctx}/classes/deleteclass.do?',{'classId':uid},function(data){
				  if(data.code == 0){
						layer.msg('删除班级成功');
// 						 window.location.href="${ctx}/classes/classmanage.do";
						 window.location.reload();
				  }else{
					  layer.msg(data.msg);
			    	return;
				  }
			 });
		    },function(){	
		    	  layer.closeAll();  
		});
	}
	
	$('.endcurriculum').on('click',function(){
		var classId = $(this).attr("id");
			layer.confirm('确认结束课程吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url : 'endCurriculum.do',
				type : 'post',
				dataType : 'json',
				data : {
					classId : classId
				},
				success : function(res) {
					 if (res.code == 0) {
						layer.msg("结束课程成功");
                        window.location.reload();
					}else{
						layer.msg(res.msg)
					}
				}
			});
			layer.close(index);
		});
	});
	$('#search').click(function(){
		$('#myform').submit();
	})
	
		/* 检测是否有排课 */
	function goCourse(classId){		
		$.ajax({
			url:'testingCurriculum.do',
			type:'post',
			dataType:'json',
			data:{classId:classId},
			success:function(res) {
				 if(res.code ==0) { 
					window.location.href="findclassCourseList.do?classId="+classId;
				} else {
					layer.msg(res.msg,{time:3000});
				}
			}
		})
	}
</script>
</html>