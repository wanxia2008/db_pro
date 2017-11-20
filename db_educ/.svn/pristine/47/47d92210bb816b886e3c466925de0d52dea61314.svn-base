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
<title>校区管理</title>
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
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="${ctx}/school/schoolmanage.do" method="post">
	<div class="panels_head">
			<span>校区列表</span>
		</div>
		<div class="layui-form-item"
			style="margin-top: 20px; margin-left:1%;margin-right:1%">
			<div class="layui-inline" style="">
				<div class="layui-input-inline" style="width: 200px;">
					<input type="text" id="schoolName1" name="schoolName" 
						autocomplete="off" placeholder="请输入校区名称" class="layui-input" maxlength="10">
				</div>
				 <button type="button" class="layui-btn layui-btn-normal searchbtn" onclick="deleteone()">
					新增</button> 
			</div>
<!-- 			<div class="search_content" style="width: 20%; margin-right: 30px;"> -->
<!-- 				<div class="input-group" -->
<!-- 					style="width: 75%; float: left; margin-right: 10px;"> -->
<!-- 					<span class="input-group-addon" style="background: #FFFFFF;"><i -->
<!-- 						class="icon-search search_icon"></i></span> <input type="text" -->
<!-- 						class="form-control" placeholder="请输入角色名称"  -->
<%-- 						id="schoolName2" name="schoolName2"  value="${schoolName2}"> --%>
<!-- 				</div> -->
<!-- 				<button class="layui-btn layui-btn-normal" id="new_school" -->
<!-- 				lay-submit="" lay-filter="demo1">搜索</button> -->
<!-- 			</div> -->
			<div class="layui-inline" style="float:right">
				<div class="layui-input-inline" style="width: 180px;">
					<input type="text" placeholder="请输入校区名称"
						id="schoolName2" name="schoolName2" value="${schoolName2}" class="layui-input" maxlength="10">
				</div>
				<button class="layui-btn layui-btn-normal searchbtn" id="new_school"
					lay-submit="" lay-filter="demo1">搜索</button>
			</div>
		</div>
		
		<c:choose>
			<c:when test="${schoolList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p">~暂无数据~</p>
			</c:when>
		<c:otherwise>
		<table class="layui-table" style="width: 98%; margin: 0 auto">
		<thead>
				<tr>
					<th>校区编号</th>
					<th>校区名称</th>
					<th>状态</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
		</thead>
		<tbody>	
				<c:forEach items="${schoolList}" var="school">
					<tr>
						<td>${school.getSchoolId()}</td>
						<td>${school.getSchoolName()}</td>
						<td id="${school.getIsUsing()}" onclick="getUseing(${school.getSchoolId()},${school.getIsUsing()})">
							<div class="layui-input-block" style="margin-left: 0;">

								<c:choose>
									<c:when test="${school.getIsUsing() eq 1}">
										<%-- <input type="radio" name="${school.getSchoolId()}" value="1"
											title="使用中" checked="" class="">
										<input type="radio" name="${school.getSchoolId()}" value="0"
											title="停用"> --%>
											<!-- 下划线border-bottom:1px solid -->
										 <a style="color:#008bca;cursor:pointer;">使用中</a>	
									</c:when>
									<c:otherwise>
										<%-- <input type="radio" name="${school.getSchoolId()}" value="1"
											title="使用中">
										<input type="radio" name="${school.getSchoolId()}" value="0"
											title="停用" checked=""> --%>
											<a style="color:#ff5722;cursor:pointer;">停用</a>
									</c:otherwise>
								</c:choose>
							</div>
						</td>
						<td><c:choose>
								<c:when test="${school.getUpdateTime()!=null}">
									<fmt:formatDate value="${school.getUpdateTime()}"
								pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
								</c:when>
								<c:otherwise>
									<fmt:formatDate value="${school.getCreateTime()}"
								pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
								</c:otherwise>
							</c:choose></td>
						<td><span class="icon-edit" data-toggle="modal"
							data-target="#myModal" id="${school.getSchoolId()}"
							data-name="${school.getSchoolName()}" data-status="${school.getIsUsing()}"
							style="padding-right: 15px; font-size: 14px; color: #1e9fff;">&nbsp;编辑</span>
							<span class="icon-remove-sign" id="${school.getSchoolId()}" data-status="${school.getIsUsing()}"
							style="font-size: 14px; color: #ff5722; padding-right: 10px;">&nbsp;删除</span>
						</td>
						</tr>
				</c:forEach>
				
				<tr id="pages">
					<td colspan="5">
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
<!-- 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp; -->
<!-- 							<input type="text" -->
<%-- 								id="goPageId" style="width: 50px;height:30px;line-height:30px;text-align:center" value="${page}" name="goPage" />&nbsp;&nbsp;页 --%>
<!-- 							<a href="javascript:;"  -->
<%-- 								onclick="goMyPage($('#goPageId').val(),${totalPages});">&nbsp;&nbsp;Go</a> --%>
							</div>
					</td>
				</tr>
				</tbody>	
			</table>
		</c:otherwise>
		</c:choose>
		<c:if test="${schoolList.size() gt 9}">
			<div id="pages" style="text-align: center;"></div>
		</c:if>
	</form>
	<!--模态框-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 40%;">
			<div class="modal-content">
			<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">编辑校区</h4>
            </div>
				<div class="modal-body">
					<form  method="post">
						<div class=""
							style="width: 300px; height: 50px; margin-left: 30px;">
							<div class="input-group" style="float: left;">
								<input type="text" name="schoolName" id="school_Name"
									class="form-control" style="width: 200px;" maxlength="10"> <input
									type="number" hidden="" name="schoolId" id="school_Id">
							</div>
							<!-- <button type="button" id="comfrim_school" data-status="" class="btn btn-primary"
								style="float: right;">确定</button> -->
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="comfrim_school" style="height:30px;line-height:30px;padding:0 18px;">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui
			.use(
					[ 'form', 'layedit', 'laypage', 'laydate', 'layer' ],
					function() {
						var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

                 });
	$('.icon-edit').on('click', function() {
		var id = $(this).attr("id");
		var name= $(this).attr("data-name");
		var status = $(this).attr("data-status");
		$('#school_Name').val(name);
		$('#school_Id').val(id);
		$('#comfrim_school').attr("data-status",status);
	});
	$('#comfrim_school').on('click', function() {
		var name = $('#school_Name').val();
		var id = $('#school_Id').val();
		var status = $(this).attr("data-status");
				$.ajax({
					url : '${ctx}/school/updateschool.do',
					type : 'post',
					dataType : 'json',
					data : {
						schoolId : id,
						schoolName : name
					},
					success : function(res) {
						if (res.code == 0) {
							layer.msg("修改成功!");
							setTimeout(function(){									
								window.location.href = "schoolmanage.do";
                            },1000);
						} else  {
							layer.msg(res.msg);
							return;
						}
					}
				})
	});
	$('.icon-remove-sign').on('click', function() {
		var that = this;
		var status = $(that).attr("data-status");
		if(status==1) {
			layer.confirm('该校区在启用状态，请联系校区管理员', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理
				layer.close(index);
			});
		} else {
			layer.confirm('确认删除吗？', {
				btn : [ '确定','删除' ]
			}, function(index, layero) {//确定的处理
				$.ajax({
					url : '${ctx}/school/deleteschool.do',
					type : 'post',
					dataType : 'json',
					data : {
						schoolId : $(that).attr("id")
					},
					success : function(res) {
						if (res.code == 0) {
							layer.msg("删除成功!");
							window.location.href = "schoolmanage.do";
						} else{
							layer.msg(res.msg);
							return;
						}
					}
				})
				layer.close(index);
			},function(){
		    	  layer.closeAll();
			});
		}
	});
	
	$('#new_school').on('click',function(){
		var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i");   
		var meterialName =$('#schoolName2').val();
	    if(pat.test(meterialName)==true)   
	    {   
	    	layer.msg("输入名称中含有非法字符");   
	        return false;   
	    } else{
	    	$('#myform').submit();
	    }
		
	});
	
	function goPageAction(page){
		window.location.href="${ctx}/school/schoolmanage.do?pageNo="+page+"&schoolName1=${schoolName1}";
		//+"&goodsName=${goodsName}&searchBeginDate=${searchBeginDate}&searchEndDate=${searchBeginDate}"
		}
	function goMyPage(page,totalPage){
		if(page>0 && page<=totalPage){
			window.location.href="${ctx}/school/schoolmanage.do?pageNo="+page+"&schoolName1=${schoolName1}";
		}else{
			alert("请输入有效的整数");
		}
	}
	
	function deleteone(){
		var schoolName1=$("#schoolName1").val();
		$.ajax({
			url : 'newschool.do',
			type : 'post',
			dataType : 'json',
			data : {
				schoolName1 : schoolName1
			},
			success : function(res) {
				if (res.code == 1) {
					layer.msg("该校区已存在!")
				} else if (res.code == 0) {
					layer.msg("添加成功!");
					window.location.href = "schoolmanage.do";
				}else{
					layer.msg("请输入校区名字!");
				}
			}
		});
	}
	
	function getUseing(schoolId,isUseing){
		var html = '';
		if(isUseing == 1){
			html = '您确认停用该校区';
		}else{
			html = '您确认开启该校区';
		}
		
		layer.confirm(html, {
			offset: '100px', //水平居中，距离顶部100px
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			
			$.ajax({
				url : "${ctx}/school/changeisuseing.do",
				type : 'post',
				dataType : 'json',
				data : {
					schoolId:schoolId,
					isUsing:isUseing
				},
				success : function(res) {
					if (res.code == 0) {
						layer.msg("操作成功!");
						window.location.href = "schoolmanage.do";
					} else{
						layer.msg(res.msg);
						return;
					}
				}
			})
			
			layer.close(index);
		});
	}
</script>
</html>