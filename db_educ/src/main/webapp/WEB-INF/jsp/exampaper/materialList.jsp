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
<title>教材列表</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<style type="text/css">
.tcenter{text-align: center !important}
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
</style>

</head>
<body>
	<form id="myform" class="layui-form"
		action="${ctx}/teachingMaterial/materialList.do">
		<div class="panels_head">
			<span>教材列表</span>
		</div>
		<div class="layui-form-item"
			style="margin-top: 20px; margin-left: 1%; margin-right: 1%">
			<div class="layui-input-inline" style="width: 120px;">
				<select name="subject" id="subject" lay-verify="subject">
					<option value="">请选择</option>
					<c:forEach items="${subjects}" var="lesson">
						<option value="${lesson.subjectId}">${lesson.subjectName}</option>
					</c:forEach>
				</select>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline" style="width: 200px;">
					<input type="text" id="tachMeterialName" name="tachMeterialName"
						autocomplete="off" placeholder="请输入教材名称" class="layui-input"
						maxlength="32">
				</div>
				<button type="button" class="layui-btn layui-btn-normal"
					onclick="saveSourceName()" style="font-size: 14px;">新增</button>
			</div>
			<div class="layui-inline" style="float: right">
				<div class="layui-input-inline" style="width: 180px;">
					<input type="text" placeholder="请输入教材名称" id="meterialName"
						name="meterialName" value="${meterialName}" class="layui-input"
						maxlength="32">
				</div>
				<button class="layui-btn layui-btn-normal searchbtn"
					id="search_content" lay-submit="" lay-filter="demo1">搜索</button>
			</div>
		</div>


		<c:choose>
			<c:when test="${tmList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table" style="width: 98%; margin: 0 auto">
					<thead>
						<tr>
							<th>ID</th>
							<th>科目</th>
							<th>教材名称</th>
							<th>添加时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tmList}" var="s">
							<tr>

								<td><c:out value="${s.materialId}"></c:out></td>
								<td>${s.subject2.subjectName}</td>
								<td>
									<div align="center">${s.materialName}</div>
								</td>
								<td><fmt:formatDate value="${s.createTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>
									<%-- 						 <a id="${s.materialId}" class="button border-red" --%>
									<!-- 							href="javascript:void(0)" --> <%-- 							onclick="deleteone(<c:out value="${s.materialId}"></c:out>)"> <span --%>
									<!-- 								class="icon-edit" --> <!-- 								style="font-size: 15px; color: 	#FF7256; cursor: pointer;"> -->
									<!-- 									修改</span> --> <!-- 						</a> --> <span
									class="icon-edit" data-toggle="modal" data-target="#myModal"
									id="${s.materialId}" data-name="${s.materialName}" data-subject="${s.subject}"
									style="font-size: 14px; color: #1e9fff; cursor: pointer;">修改</span>
									&nbsp; <a class="button border-red" href="javascript:void(0)"
									onclick="deleteone(<c:out value="${s.materialId}"></c:out>)">
										<span class="icon-remove-sign"
										style="font-size: 14px; color: #ff5722;"> 删除</span>
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
									<%-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp; <input
								type="text" id="goPageId"
								style="width: 50px; height: 30px; line-height: 30px; text-align: center"
								value="${page}" name="goPage" />&nbsp;&nbsp;页 <a
								href="javascript:;"
								onclick="goMyPage($('#goPageId').val(),${totalPages});">&nbsp;&nbsp;Go</a> --%>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</form>
	<!--模态框-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 40%;">
			<div class="modal-content">
			<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">编辑教材</h4>
</div>
				<div class="modal-body">
					<form class="layui-form" method="post">
						<div class="layui-form-item" style="margin-top: 20px;">
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 120px;">教材名称</label>
								<div class="input-group" style="float: left;">
									<input type="text" name="materialName" id="materialName11"
										class="form-control" style="width: 200px;" maxlength="10"> 
								<input type="number" hidden="" name=materialId id="materialId">
								</div>
							</div>
							<div class="layui-inline" id="subjectId">
								<label class="layui-form-label" style="width: 120px;">科目</label>
								<div class="layui-input-inline" style="width: 200px;">
									<select name="subject" id="subject-type" lay-verify="subject" style="display:block !important;border:1px solid #ccc;width:200px;height:34px;line-height: 1.42857143;border-radius:4px;padding: 6px 12px;">
										<%-- <option value="">请选择</option>
										<c:forEach items="${subjects}" var="lesson">
											<c:choose>
												<c:when test="${subjectId==lesson.subjectId}">
													<option selected="selected" value="${lesson.subjectId}">${lesson.subjectName}</option>
												</c:when>
												<c:otherwise>
													<option value="${lesson.subjectId}">${lesson.subjectName}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach> --%>
									</select>
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
</body>
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

			

			//监听提交
			form.on('submit(demo1)', function(data) {
				/* layer.alert(JSON.stringify(data.field), {
					title : '最终的提交信息'
				}) */
				return true;
			});
		});
		
$('.icon-edit').on('click',function(){
	var materialName11=$(this).attr("data-name");
	var materialId=$(this).attr("id");
	var subjectId = $(this).attr("data-subject");
	$('#materialId').val(materialId);
	$('#materialName11').val(materialName11);
	
    $.ajax({
   	 url:'subjectList.do',
   	 type:'post',
   	 dataType:'json',
   	 data:{},
   	 success:function(data){
   		 if(data.code == 0){
   			 var result = data.objects;
   			 var html='';
   			 for(var i=0;i<result.length;i++){
   				 if(subjectId == result[i].subjectId){
   					 html+='<option selected="selected" value="'+result[i].subjectId+'">'+result[i].subjectName+'</option>';
   				 }else{
   					 html += '<option value="'+result[i].subjectId+'">'+result[i].subjectName+'</option>'
   				 }
   			 }
   			 $("#subject-type").html(html);
   		 }
   	 }
   })
});

$('#confirm_update').on('click',function(){
	var materialName11=$('#materialName11').val();
	var materialId=$('#materialId').val();
	var subjectId=$('#subject-type').val();
	$.ajax({
		url:'${ctx}/teachingMaterial/modityMaterial.do',
		type:'post',
		dateType:'json',
	      data:{
	    	  meterialName:materialName11,
	    	  materialId:materialId,
	    	  subjectId:subjectId
	      },
	      success:function(data){
		    	if(data.code == 0){
		    		layer.msg("修改成功!");
		    		setTimeout(function(){
			    		window.location.href = "${ctx}/teachingMaterial/materialList.do";
		    		},1000);
		    	}else{
		    		layer.msg(data.msg);
		    		return;
		    	}
		    },
	})
});


function deleteone(uid){
	layer.confirm('您确认删除该教材么?',{btn:['确认','取消']},function(){
		$.post('${ctx}/teachingMaterial/tagdelete.do?',{'materialId':uid},function(data){
			if(data.code==0){
				layer.msg('删除教材成功');
				 window.location.href="${ctx}/teachingMaterial/materialList.do";	
			}else{
				layer.msg(data.msg);
				return;
			}
	 });
	    },function(){	
	    	 layer.closeAll();  
	});
}

$('#search_content').click(function(){
	
	var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i");   
	var meterialName =$('#meterialName').val();
    if(pat.test(meterialName)==true)   
    {   
    	layer.msg("输入名称中含有非法字符");   
        return false;   
    } else{
    	$('#myform').submit();
    }
})

function saveSourceName(){
	var tachMeterialName=$("#tachMeterialName").val();
	var subject=$('#subject').val();
	$.ajax({
		url : 'saveMaterial.do',
		type : 'post',
		dataType : 'json',
		data : {
			tachMeterialName : tachMeterialName,
			subject:subject
		},
		success : function(res) {
			 if (res.code == 0) {
				layer.msg("添加成功!");
				window.location.href = "materialList.do";
			}else{
				layer.msg(res.msg);
	    		return;
			}
		}
	});
}
function goPageAction(page){
	window.location.href="${ctx}/teachingMaterial/materialList.do?pageNo="+page+"&ruleName=${ruleName}";
	//+"&goodsName=${goodsName}&searchBeginDate=${searchBeginDate}&searchEndDate=${searchBeginDate}"
	}
function goMyPage(page,totalPage){
	if(page>0 && page<=totalPage){
		window.location.href="${ctx}/teachingMaterial/materialList.do?pageNo="+page+"&ruleName=${ruleName}";;
	}else{
		alert("请输入有效的整数");
	}
}

</script>
</html>