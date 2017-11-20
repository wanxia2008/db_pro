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
<title>教室信息</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<style type="text/css">
.layui-form-checkbox {
	height: 26px;
	line-height: 24px;
}

.layui-form-checkbox span {
	font-size: 7px;
}

.layui-form-checked span, .layui-form-checked:hover span {
	background-color: #35CE8D;
}

ul li {
	margin-top: 5px;
	padding: 5px;
}
.layui-laypage a, .layui-laypage span{margin-right:5px;padding:0 18px;border-radius:4px;font-size:14px;}
.layui-laypage .layui-laypage-curr .layui-laypage-em{border-radius:4px;}
.layui-laypage>:first-child, .layui-laypage>:first-child em{border-radius:4px;}
.layui-laypage button, .layui-laypage input{border-radius:4px;}
</style>
</head>
<body>
	<form class="layui-form" action="" method="post">
	<div class="panels_head">
			<span>教室信息</span>
	 </div>		
		<div
			style="border-bottom: 1px dashed #DDDDDD; background: #FFFFFF;">
			<div class="layui-form-item"
				style="margin-left:1%;margin-right:1%;margin-top:20px;">
				<div class="layui-input-inline" style="width: 150px;">
					<select name="schoolArea" id="schoolArea" lay-verify="schoolArea1"
						autocomplete="off">
						<option value="">选择校区</option>
						<c:forEach items="${schoolList}" var="school">
							<option value="${school.getSchoolId()}">${school.getSchoolName()}</option>
						</c:forEach>
					</select>
				</div>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="number" name="classroomName" id="classroomName"
						lay-verify="classroomName1" autocomplete="off" placeholder="输入教室编号"
						min="1" class="layui-input"
						onkeyup="number_limit(this,this.value)">
				</div>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="number" name="maxNumber" id="maxNumber"
						lay-verify="maxNumber1" autocomplete="off" placeholder="输入最大使用人数"
						min="1" max="500" class="layui-input"
						onkeyup="number_limit(this,this.value)">
				</div>
				<button type="button" class="layui-btn layui-btn-normal searchbtn" onclick="addClass();">新增</button>
					 <div class="layui-inline" style="float:right">
					 
				<div class="layui-input-inline" style="width: 180px;">
					<input type="text"  placeholder="请输入教室编号" id="roomName"
						name="roomName" value="${roomName}" class="layui-input" maxlength="10">
				</div>
				<button class="layui-btn layui-btn-normal searchbtn" id="search_content">搜索</button>
			</div>
			</div>
		</div>
       
		<!--表格-->
		<div class="table-responsive"
			style="width: 100%; margin-left: auto; margin-right: auto; padding-top: 30px;">

		</div>
		<input id="" type="text" hidden="" value="">
		<div id="pages" style="text-align: center;"></div>
	</form>
	<!--模态框-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 40%;">
			<div class="modal-content">
			<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">编辑教室</h4>
</div>
				<div class="modal-body">
					<form class="layui-form" method="post">

						<div class="layui-form-item" style="margin-top: 20px;">
							<div class="layui-inline">
								<label class="layui-form-label" style="font-size: 14px;">教室</label>
								<div class="input-group" style="float: left;">
									<input type="text" name="classroomName" id="room_Name"
										class="form-control" style="width: 200px;" maxlength="10"> <input
										type="number" hidden="" name="classroomId" id="room_Id">
								</div>
							</div>
						</div>
						<div class="layui-form-item" style="margin-top: 20px;">
							<div class="layui-inline">
								<label class="layui-form-label" style="font-size: 14px;">人数</label>
								<div class="input-group" style="float: left;">
									<input type="text" name="maxNumber" id="maxnumber"
										class="form-control" style="width: 200px;">
								</div>
							</div>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="confirm_update" style="height:30px;line-height:30px;padding:0 18px;">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
	<script id="room_template" type="text/html">
		<table  class="layui-table" style="width: 98%; margin: 0 auto">
            <thead>
				<tr class="active">
					<th>所属校区</th>
					<th>教室编号</th>
					<th>教室人数</th>
                    <th>创建时间</th>
					<th>操作</th>
				</tr> 
             </thead>
             <tbody>    
                   {{each objects as value i}}
				<tr class="roomlist">
					<td>{{value.schoolList[0].schoolName}}</td>
					<td>{{value.classroomName}}</td>
					<td>{{value.maxNumber}}</td>
                     <td>{{value.createTime}}</td>
					<td>
						<span class="icon-edit" data-toggle="modal"
							data-target="#myModal" id="{{value.classroomId}}" data-name="{{value.classroomName}}" data-school="{{value.schoolList[0].schoolId}}"
							data-num="{{value.maxNumber}}" style="font-size: 14px; color: #1e9fff;cursor:pointer;">编辑</span>
                          &nbsp;
						<span class="icon-remove-sign" id="{{value.classroomId}}"
							style="font-size: 14px; color: #ff5722;cursor:pointer;">删除</span>
					</td>
				</tr>
                    {{/each}}
          </tbody>
		</table>
	</script>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript">
	var pageNo = 1, pageSize = 8,name;
	layui.use([ 'form', 'laypage', 'layer' ], function() {
		var form = layui.form(), laypage = layui.laypage, layer = layui.layer;
		;
		//自定义验证规则
		form.verify({
			 schoolArea : function(value) {
				if (value.length <= 0) {
					return '请选择校区！';
				}
			},
			classroomName : function(value) {
				var ageName = /^\+?[1-9][0-9]*$/;
				if (value.length == 0) {
					return "请输入教室名称";
				} else if (!ageName.test(value)) {
					return "请输入正确的教室名称";
				}
			},
			maxNumber : function(value) {
				var ageName = /^\+?[1-9][0-9]*$/;
				if (value.length == 0) {
					return "请输入最大使用人数";
				} else if (!ageName.test(value)) {
					return "请输入正整数";
				} else if (value.length > 2) {
					return "教室人数不能超过两位数";
				}
			} 
		});
		//监听提交
	     
		$('#search_content').click(function() {
			  name = $('#roomName').val();
			 getClassroom(pageNo, name);
// 			 $.ajax({
// 					url : 'getclassroom.do', 
// 					type : 'post',
// 					dataType : 'json',
// 					data : {
// 						roomName : roomName
// 					},
// 					success : function(res) {
// 						if (res.code == 0) {
// 							getClassroom(pageNo, roomName);
// 						} else {
// 							layer.msg(res.msg);
// 							return false;
// 						}
// 					}
// 				})

		});
		getClassroom(pageNo, name);
		function getClassroom(pageno, name) {
			$.getJSON("getclassroom.do", {
				pageNo : pageno,
				pageSize : pageSize,
				roomName : name
			}, function(res) {
				if (res.code == 0) {
					var html = template('room_template', res);
					$('.table-responsive').html(html);
					if (res.count > 1) {
						laypage({
							cont : 'pages',
							pages : res.count,
							skin : 'molv',
							curr : pageno || 1,//当前页
							groups : 5,
// 							skip : true,//控制搜索页数(跳转)
							jump : function(obj, first) {
								//触发分页后的回调
								if (!first) {
									//点击跳页触发函数自身，并传递当前页：obj.curr
									getClassroom(obj.curr);
								}
							}
						});
					}
					$('.icon-edit').on('click', function() {
						var id = $(this).attr("id");
						var name = $(this).attr("data-name");
						var number = $(this).attr("data-num");
						// var school = $(this).attr("data-school");
						$('#room_Name').val(name);
						$('#room_Id').val(id);
						$('#maxnumber').val(number);
						// $('#school_Name').val(school);
					});
					$('.icon-remove-sign').on('click', function() {
						var id = $(this).attr("id");
						layer.confirm('确认删除吗？', {
							btn : [ '确定','取消' ]
						}, function(index, layero) {//确定的处理
							$.ajax({
								url : 'deleteclassroom.do',
								type : 'post',
								dataType : 'json',
								data : {
									classId : id
								},
								success : function(res) {
									 if (res.code == 0) {
										layer.msg("删除成功!");
										window.location.href = "classroom.do";
									}else{
										layer.msg(res.msg);
										return;
									}
								}
							});
							layer.close(index);
						},function(){
					    	  layer.closeAll();
						});

					});
				} else if (res.code == 1) {
					layer.msg("没有数据!");
				}
			})
		}
	});

	$('#confirm_update').on('click', function() {
		var name = $('#room_Name').val();
		var id = $('#room_Id').val();
		var number = $('#maxnumber').val();
		// 		var school = $('#school_Name').val();
		$.ajax({
			url : 'updateclassroom.do',
			type : 'post',
			dataType : 'json',
			data : {
				classroomName : name,
				maxNumber : number,
				classroomId : id
			},
			success : function(res) {
				if (res.code == 1) {
					layer.msg(res.msg);
				} else if (res.code == 0) {
					layer.msg("修改成功!");
					window.location.href = "classroom.do";
				}
			}
		})
	});
// 	function goPageAction(page) {
// 		window.location.href = "${ctx}/arrangeclass/classroom.do?pageNo="+ page;
		
// 	}
// 	function goMyPage(page, totalPage) {
// 		if (page > 0 && page <= totalPage) {
// 			window.location.href = "${ctx}/arrangeclass/classroom.do?pageNo="+ page;
// 		} else {
// 			alert("请输入有效的整数");
// 		}
// 	}
	function number_limit(that, value) {
		var test1 = /^[1-9]\d*$/;
		if (!test1.test(value)) {
			alert("不能以0开头");
			that.value = "";
			return;
		}
	}
	
	 function addClass() {
			var name = $('#classroomName').val();
			var number = $('#maxNumber').val();
			var school = $('#schoolArea').val();
			if(!school){
				layer.msg('请选择校区');
				return false;
			}
			if(!name){
				layer.msg('请输入班级编号');
				return false;
			}
			if(name.length>6){
				layer.msg('班级编号不能大于6位数');
				return false;
			}
			
			if(number<=0){
				layer.msg('请输入班级人数');
				return false;
			}
			if(number.length>2){
				layer.msg('教室人数不能大于3位数');
				return false;
			}
			
			$.ajax({
				url : 'addclassroom.do',
				type : 'post',
				dataType : 'json',
				data : {
					classroomName : name,
					maxNumber : number,
					schoolArea : school
				},
				success : function(res) {
					if (res.code == 0) {
						layer.msg('添加成功');
						setTimeout(function(){
							window.location.reload();
						},1000);
					} else {
						layer.msg(res.msg);
						return false;
					}
				}
			})
			return false;
		}
</script>
</html>