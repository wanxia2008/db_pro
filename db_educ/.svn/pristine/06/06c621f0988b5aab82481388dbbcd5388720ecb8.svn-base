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
<title>教师管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
.layui-form-item .layui-inline {
	
}

.titlecreated {
	display: none;
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
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="">
		<div class="panels_head">
			<span>教师列表</span> 
			<button type="button" class="layui-btn layui-btn-normal"
				lay-submit="" onclick="addTeacher(${currTeacher.teacherType})"
				lay-filter="demo1"
				style="height: 30px; line-height: 30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; font-size: 14px">新增</button>
		</div>
		<div class="layui-form-item"
			style="margin-top: 20px; margin-left: 1%;">
			<c:if test="${currTeacher.teacherType==1}">
				<div class="layui-inline">
					<label class="layui-form-label" style="font-size: 14px;">校区</label>
					<div class="layui-input-inline" style="width: 120px;">
						<select name="schoolId" id="schoolId">
							<option value="">全部</option>
						    <option value="">全部</option>
							<c:forEach items="${schoolList}" var="lesson">
								<c:choose>
									<c:when test="${schoolId==lesson.schoolId}">
										<option selected="selected" value="${lesson.schoolId}">${lesson.schoolName}</option>
									</c:when>
									<c:otherwise>
										<option value="${lesson.schoolId}">${lesson.schoolName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
			</c:if>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">科目</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="subject" id="subject" lay-verify="subject">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${subjects}" var="lesson">
							<c:choose>
								<c:when test="${subject==lesson.subjectId}">
									<option selected="selected" value="${lesson.subjectId}">${lesson.subjectName}</option>
								</c:when>
								<c:otherwise>
									<option value="${lesson.subjectId}">${lesson.subjectName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">名称</label>
				<div class="layui-input-inline" style="width: 200px;">
					<input type="text" name="schoolName" id="schoolName"
						lay-verify="schoolName" value="${schoolName}" autocomplete="off"
						placeholder="请输入教师姓名" class="layui-input" maxlength="6">
				</div>
				<button class="layui-btn layui-btn-normal" lay-submit=""
					lay-filter="demo1" id="search_content" style="font-size: 14px;">搜索</button>
			</div>
		</div>
		<c:choose>
			<c:when test="${tList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size: 14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table"
					style="width: 98%; margin-left: auto; margin-right: auto; font-size: 10px; margin-top: 0px;">
					<thead>
						<tr>
							<th>ID</th>
							<th>校区</th>
							<th>姓名</th>
							<th>科目</th>
							<th>年级</th>
							<th>所属角色</th>
							<th style="width:20%">数据权限</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach items="${tList}" var="t">
							<tr>
								<!-- <td><input type="checkbox" name="" lay-skin="primary"></td> -->
								<td><c:out value="${t.teacherId}"></c:out></td>
								<td><c:out value="${t.schoolZone.schoolName}"></c:out></td>
								<td><c:out value="${t.teacherName}"></c:out></td>
								<td><c:if test="${t.subject2.subjectName != NULL}">
										<c:out value="${t.subject2.subjectName}"></c:out>
									</c:if> <c:if test="${t.subject2.subjectName == NULL}">
								暂无科目
								</c:if></td>
								<td><c:forEach items="${t.gradelist}" var="g">
										<c:choose>
											<c:when test="${g.gradeName!= null and g.gradeName !=''}">
												<c:out value="${g.gradeName}"></c:out>
											</c:when>
											<c:otherwise>
								                    暂无分配年级
									</c:otherwise>
										</c:choose>
										<br>
									</c:forEach></td>
								<td style="text-align: left"><c:forEach
										items="${t.userRole.rlList}" var='ur'>
										<c:choose>
											<c:when test="${ur.roleName != null and ur.roleName !=''}">
												<input type="checkbox" name="like1[]" lay-skin="primary"
													checked="" disabled="">
												<c:out value="${ur.roleName}"></c:out>
												<br>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="roleId" lay-skin="primary"
													title="总管理员">
												<input type="checkbox" name="like1[write]"
													lay-skin="primary" title="校区管理员">
												<input type="checkbox" name="like1[write]"
													lay-skin="primary" title="校区教务管理员">
												<input type="checkbox" name="like1[write]"
													lay-skin="primary" title="任课老师角色">
												<input type="checkbox" name="like1[write]"
													lay-skin="primary" title="项目研发人员">
											</c:otherwise>
										</c:choose>
									</c:forEach></td>

								<c:forEach items="${t.taList}" var="ta">
									<td>
										<div class="layui-form-item" pane="" style="height: 30px;">
											<label class="layui-form-label">题目</label>
											<div class="layui-input-block" style="margin-left: 0">
												<c:choose>
													<c:when test="${ta.questionsAuthority == 1}">
														<input type="checkbox" name="questionsAuthority[]"
															value="1-1" lay-skin="primary" title="读" checked=""
															disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="questionsAuthority[]"
															value="1-2" lay-skin="primary" title="写" disabled="">
													</c:when>
													<c:when test="${ta.questionsAuthority == 2}">
														<input type="checkbox" name="questionsAuthority[]"
															value="2-1" lay-skin="primary" title="读" disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="questionsAuthority[]"
															value="2-2" lay-skin="primary" title="写" checked=""
															disabled="">
													</c:when>
													<c:when test="${ta.questionsAuthority == 3}">
														<input type="checkbox" name="questionsAuthority[]"
															value="3-3" lay-skin="primary" title="读" checked=""
															disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="questionsAuthority[]"
															value="3-3" lay-skin="primary" title="写" checked=""
															disabled="">
													</c:when>
													<c:otherwise>
														<input type="checkbox" name="questionsAuthority[]"
															value="1-0" lay-skin="primary" title="读" disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="questionsAuthority[]"
															value="1-0" lay-skin="primary" title="写" disabled="">
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="layui-form-item" pane="" style="height: 30px;">
											<label class="layui-form-label">试卷</label>
											<div class="layui-input-block" style="margin-left: 0">
												<c:choose>
													<c:when test="${ta.paperAuthority == 1}">
														<input type="checkbox" name="paperAuthority[]" value="2-1"
															lay-skin="primary" title="读" checked="" disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="paperAuthority[]" value="2-2"
															lay-skin="primary" title="写" disabled="">
													</c:when>
													<c:when test="${ta.paperAuthority == 2}">
														<input type="checkbox" name="paperAuthority[]" value="2-1"
															lay-skin="primary" title="读" disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="paperAuthority[]" value="2-2"
															lay-skin="primary" title="写" checked="" disabled="">
													</c:when>
													<c:when test="${ta.paperAuthority == 3}">
														<input type="checkbox" name="paperAuthority[]" value="2-2"
															lay-skin="primary" title="读" checked="" disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="paperAuthority[]" value="2-2"
															lay-skin="primary" title="写" checked="" disabled="">
													</c:when>
													<c:otherwise>
														<input type="checkbox" name="paperAuthority[]" value="2-0"
															lay-skin="primary" title="读" disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="paperAuthority[]" value="2-0"
															lay-skin="primary" title="写" disabled="">
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="layui-form-item" pane="" style="height: 30px;">
											<label class="layui-form-label">讲义</label>
											<div class="layui-input-block" style="margin-left: 0">
												<c:choose>
													<c:when test="${ta.handoutAuthority == 1}">
														<input type="checkbox" name="handoutAuthority[]"
															value="2-1" lay-skin="primary" title="读" checked=""
															disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="handoutAuthority[]"
															value="2-2" lay-skin="primary" title="写" disabled="">
													</c:when>
													<c:when test="${ta.handoutAuthority == 2}">
														<input type="checkbox" name="handoutAuthority[]"
															value="2-1" lay-skin="primary" title="读" disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="handoutAuthority[]"
															value="2-2" lay-skin="primary" title="写" checked=""
															disabled="">
													</c:when>
													<c:when test="${ta.handoutAuthority == 3}">
														<input type="checkbox" name="handoutAuthority[]"
															value="2-2" lay-skin="primary" title="读" checked=""
															disabled>
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="paperAuthority[]" value="2-2"
															lay-skin="primary" title="写" checked="" disabled="">
													</c:when>
													<c:otherwise>
														<input type="checkbox" name="handoutAuthority[]"
															value="2-0" lay-skin="primary" title="读" disabled="">
														<!-- disabled=""不能点击 -->
														<input type="checkbox" name="handoutAuthority[]"
															value="2-0" lay-skin="primary" title="写" disabled="">
													</c:otherwise>
												</c:choose>
											</div>
										</div>

									</td>
								</c:forEach>
								<td><a
									href="${ctx}/teacher/toupdateteacher.do?teacherid=<c:out value="${t.teacherId}"></c:out>&pageNo=${page}">
										<span class="icon-edit"
										style="font-size: 14px; color: #008bca;">修改</span>
								</a>&nbsp; <a class="button border-red" href="javascript:void(0)"
									onclick="deleteone(<c:out value="${t.teacherId}"></c:out>)">
										<span class="icon-remove-sign"
										style="font-size: 14px; color: #ff5722;"> 删除</span>
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
	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
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

			//监听提交
			form.on('submit(demo1)', function(data) {
				
				return true;
			});
		});
function goPageAction(page){
	window.location.href="${ctx}/teacher/teachermanage.do?pageNo="+page+"&schoolName=${schoolName}&subject=${subject}&schoolId=${schoolId}";
	//+"&goodsName=${goodsName}&searchBeginDate=${searchBeginDate}&searchEndDate=${searchBeginDate}"
	}
function goMyPage(page,totalPage){
	if(page>0 && page<=totalPage){
		window.location.href="${ctx}/teacher/teachermanage.do?pageNo="+page+"&schoolName=${schoolName}";
	}else{
		alert("请输入有效的整数");
	}
}
function deleteone(uid){
	 layer.confirm('您确认删除该教师么?',{btn:['确认','取消']},function(){
		 $.post('${ctx}/teacher/deleteteacher.do?',{'teacherId':uid},function(data){
			  if(data.code == 0){
					layer.msg('删除教师成功');
					setTimeout(function(){
						 window.location.href="${ctx}/teacher/teachermanage.do";
					},1000);
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
	var teacherName=$('#schoolName').val();
    if(pat.test(teacherName)==true)   
    {   
        layer.msg("输入名称中含有非法字符!");   
        return false;   
    } else{
    	$('#myform').submit();
    }
});
 function addTeacher(type){
	if(type == 5 || type == 6){
   	 layer.msg('您没有添加老师的权限!');
   	 return false;
    } else {
    	window.location.href='newteacher.do'
    }
 }
</script>
</html>