<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>新增班级</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<style type="text/css">
.icon-plus {
	font-size: 20px;
	position: absolute;
	left: 100px;
	top: 3px;
	cursor: pointer;
	color: #35CE8D;
}
body {
	font-size: 15px;
}

.layui-form-item .layui-inline {
	
}
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;top: 20%;font-size: 10px;border-radius: 5px;background:#e33}
.tf{text-align:left !important}
.layui-form-label, .layui-form-mid, .layui-textarea{
 position:none !important
}
</style>
</head>
<body>
	<form class="layui-form" action="" method="post" >
		<div class="panels_head">
			<span>新增班级</span>
			<button class="layui-btn layui-btn-normal" lay-submit=""
				lay-filter="demo1"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">确认</button>
			<button type="button" class="layui-btn layui-btn-danger"
				onclick="window.location.href='classmanage.do'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">返回</button>
		</div>
		<table class="layui-table"
			style="width: 98%; margin-left: auto; margin-right: auto; font-size: 10px;text-align:left">
			<colgroup>
				<col width="100"/>
				<col width="300"/>
			</colgroup>
			<tbody>
				<tr>
					<td  style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>选择年份</td>
					<td class="tf">
						<div class="layui-input-inline" style="width: 300px">
							<select name="year" id="year">
								<c:forEach items="${yearList}" var="year">
									<option value="${year.id}">${year.year}</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>选择校区</td>
					<td class="tf">
						<div class="layui-input-inline" style="width: 300px">
							<select name="schoolArea" id="schoolArea">
								<c:forEach items="${schoolList}" var="school">
									<option value="${school.getSchoolId()}">${school.getSchoolName()}</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>选择类型</td>
					<td class="tf">
						<div class="layui-input-inline" style="width: 300px">
							<select name="seasonType" id="seasonType">
								<c:forEach items="${seasonList}" var="map">
									<option value="${map.getSeasonId()}">${map.getSeasonName()}</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>科目</td>
					<td class="tf">
						<div class="layui-input-inline" style="width: 300px">
							<select name="subject" id="subject">
								<c:forEach items="${subjectList}" var="subject">
									<option value="${subject.getSubjectId()}">${subject.getSubjectName()}</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>排课数 /天</td>
					<td class="tf"><input type="number" min="1" max="6" name="maxNumber" maxlength="6" id="maxNumber" lay-verify="maxNumber"
						autocomplete="off" placeholder="请输入课程数" value="1" class="layui-input"
						style="width: 300px">注：这里的数字，比如 2， 表示此班级一天可以排2次课， 一天最多可以排6次课。</td>
				</tr>
				<tr>
					<td style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>课程总数</td>
					<td class="tf"><input type="number" min="1" max="6" name="courseNumber" maxlength="6" id="courseNumber" lay-verify="maxNumber"
						autocomplete="off" placeholder="请输入课程数" value="17" class="layui-input"
						style="width: 300px">注：这里的数字是此班级总共的课程数。</td>
				</tr>
				<tr>
					<td style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>年级</td>
					<td class="tf">
						<div class="layui-input-inline" style="width: 300px">
							<select name="grade" id="grade">
								<c:forEach items="${gradesList}" var="grade">
									<option value="${grade.getGradeId()}">${grade.getGradeName()}</option>
								</c:forEach>								
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>班级类型</td>
					<td class="tf">
						<div class="layui-inline" style="margin-left: 20px;">
							<input type="radio" name="classType" id="classType" value="1" title="大班" checked="">
							<input type="radio" name="classType" id="classType" value="2" title="小班">
						</div>
					</td>
				</tr>
				<tr>
					<td style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>人数限制</td>
					<td class="tf"><input type="number" min="1" name="classNumber" maxlength="3" id="classNumber" lay-verify="title"
						autocomplete="off" placeholder="请输入人数" class="layui-input"
						style="width: 300px"></td>
				</tr>

				<tr>
					<td style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>班级命名</td>
					<td class="tf" style="position: relative;"><input type="text"
						name="className" id="className" lay-verify="className" autocomplete="off"
						placeholder="请输入班级名称" class="layui-input" style="width: 300px" maxlength="10">

						</td>
				</tr>

				<tr>
					<td>班级教师</td>
					<td class="tf">
						<div class="layui-input-inline" style="width: 500px;">
							<span id="teacherName" style="margin-left: 25px;"></span>
							 <span class="icon-plus" style="color: #008bca;"></span> 
							<div>
								<input hidden="" type="text" name="teacherId"
									lay-verify="teacherId" id="teacherId" />
							</div>
						</div>
					</td>
				</tr>

				<tr>
				   <td>班级备注</td>
				   <td class="tf">
				     <textarea
							style="width: 300px; height: 100px; border-radius: 5px;"
							placeholder="可在这里对班级进行备注" id="remarks" class="layui-textarea" name="remarks"></textarea>
				   </td>
				</tr>
			</tbody>
		</table>
		<!--模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						<h4 class="modal-title" id="myModalLabel">添加班级教师</h4>
					</div>
					<div class="modal-body">
<!-- 						<div class="layui-form-item" style="margin-top: 20px;"> -->
<!-- 							<div class="layui-inline"> -->
<!-- 								<label class="layui-form-label" style="width: 100px;">科目</label> -->
<!-- 								<div class="layui-input-inline" style="width: 120px;"> -->
<!-- 									<select name="subject" id="subject" lay-filter="select_subject"> -->
<!-- 									    <option value="">全部</option> -->
<!-- 										<option value="">全部</option> -->
<%-- 										<c:forEach items="${sList}" var="s"> --%>
<%-- 											<option value="${s.subjectId}">${s.subjectName}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<table class="layui-table" style="width: 98%; margin: 0 auto">
							<thead>
								<tr class="active">
									<th>教师ID</th>
									<th>教师姓名</th>
									<th>教师性别</th>
									<th>教师年龄</th>
									<th>注册时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="tbody">
							   <!--试卷内容  -->
							</tbody>
							</table>
						<div id="pages" style="text-align:center;"></div>
					</div>
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
				</div>
				</div>
			</div>
		</div>
		<!--模态框结束-->
		<!--  <button class="layui-btn" lay-submit="" lay-filter="demo1" 
		style="float: right;margin-right: 100px;height:30px;line-height:30px;padding:0 18px">确认增加</button>  -->
	</form>
	<script type="text/html" id="exam_template">
		   {{each objects as value i}}
               <tr>
               <td>{{value.teacherId}}</td>
				<td>{{value.teacherName}}</td>
				{{if value.sex==1}}<td>男</td>{{/if}}
				{{if value.sex==2}}<td>女</td>{{/if}}
				<td>{{value.age}}</td>
                <td>{{(value.createTime).substring(0,10)}}</td>
                <td><button type="button" class="layui-btn add_note"
					data-id="{{value.teacherId}}" data-name="{{value.teacherName}}"
					style="height:30px;line-height:30px;padding:0 18px;">
					添加</button></td>
                </tr>
       	{{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript">
var pageno = 1, pagesize = 10;
	layui.use([ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');

						//自定义验证规则
						form.verify({
							title : function(value) {
								var ageName = /^\+?[1-9][0-9]*$/;
								if (value.length == "") {
									return "人数不能为空~";
								}else if(value.length>3){
									return "人数不能超过3位";
								}
								 else if(!ageName.test(value)){
									return '只能输入正整数';
								} 
							},
							maxNumber : function(value) {
								var ageName = /^\+?[1-9][0-9]*$/;
								if (value.length == "") {
									return "科目数不能为空~";
								}else if(value.length>2){
									return "科目数不能超过2位";
								}
								 else if(!ageName.test(value)){
									return '只能输入正整数';
								} 
							},
							
							
							className : function(value) {
								var ageName = /^\+?[1-9][0-9]*$/;
								if (value.length == "") {
									return "班级名称不能为空~";
								}
							},
							
							pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
							content : function(value) {
								layedit.sync(editIndex);
							}
						});

						//监听指定开关
						form.on('switch(switchTest)', function(data) {
							
						});
						//监听提交
						form.on('submit(demo1)', function(data) {
							var maxnumber = $('#maxNumber').val();
							if(maxnumber>6){
								layer.msg('课程限制最大为6');
								return false;
							}
                               $.ajax({
                            	   url:'${ctx}/classes/addclasses.do',
                            	   type:'post',
                            	   dataType:'json',
                            	   data:{
                            		   year:$('#year').val(),
                            		   schoolArea:$('#schoolArea').val(),
                            		   seasonType:$('#seasonType').val(),
                            		   subject:$('#subject').val(),
                            		   grade:$('#grade').val(),
                            		   classType:$('#classType').val(),
                            		   classNumber:$('#classNumber').val(),
                            		   className:$('#className').val(),
                            		   remarks:$('#remarks').val(),
                            		   teacherId:$('#teacherId').val(),
                            		   maxNumber:maxnumber,
                            		   courseNumber:$('#courseNumber').val()
                            	   },
                            	   success:function(data){
                            		   if(data.code==0){
                            			   layer.msg('添加班级成功!');
                            			   setTimeout(function(){
                                			   window.location.href="${ctx}/classes/classmanage.do";
                            			   },500);
                            		   }else{
                            			   layer.msg(data.msg);
                            			   return;
                            		   }
                            	   },
                               });
							return false;
						});
					});
	
	$('.icon-plus').on('click', function() {
		var grade = $('#grade').val();
		var subject = $('#subject').val();
		var schoolId = $('#schoolArea').val();
		if(!grade){
			layer.msg("请选择年级");
			return false;
		} else if(!subject){
			layer.msg("请选择科目");
			return false;
		} else if(!schoolId){
			layer.msg("请选择校区");
			return false;
		} else{
			addClassTracherId(pageno,grade,subject,schoolId);
		}
	});
	
	function addClassTracherId(pageno,grade,subject,schoolId){
		$.ajax({
			url : '${ctx}/classes/getallteacher.do',
			type : 'post',
			dataType : 'json',
			data : {
				pageNo:pageno,
				pageSize:10,
				gradeId:grade,
				subjectId:subject,
				schoolId:schoolId
				},
			    success : function(res) {
				if (res.code == 0) {
					var html = template("exam_template", res);
					$('#tbody').html(html);
					$('#myModal').modal('show');
					click_content();
					if (res.count > 1) {
						laypage({
							cont : 'pages',
							pages : res.count,
							skin : 'molv',
							curr : pageno || 1,//当前页
							groups : 5,
							skip : true,
							jump : function(obj, first) {
								//触发分页后的回调
								if (!first) {
									//点击跳页触发函数自身，并传递当前页：obj.curr
									ajax_exam(obj.curr,type,grade,subject);
								}
							}
						});
					}
				} else {
					var html = template("exam_template", res);
					$('#tbody').html(html);
					layer.msg(res.msg,{time:5000});
					return;
				}
			}
		})
	} 
	//点击模态框的试卷事件
	click_content();
	function click_content() {
		$('.add_note').on('click',function() {
			var id = $(this).attr("data-id");
			var name = $(this).attr("data-name");
			layer.confirm('确认添加该教师吗？', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理
				$('#myModal').modal('hide');
				$('#teacherName').text(name);
				$('#teacherId').val(id);
				layer.close(index);
			});
		})
	}
</script>
</html>