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
<title>范围管理</title>
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
		action="${ctx}/rangeLesson/rangeLessonList.do">
		<div class="panels_head">
			<span>范围列表</span>
		</div>
		<div class="layui-form-item"
			style="margin-top: 20px; margin-left:1%;">
			<div class="layui-inline">
				<div class="layui-input-inline" style="width: 200px;">
					<input type="text" id="lessonName3" name="lessonName"
						autocomplete="off" placeholder="格式：lesson1" class="layui-input" maxlength="9">
				</div>
				<button type="button" class="layui-btn layui-btn-normal" onclick="saveTaskName()"
					style="font-size: 14px;">新增</button>
			</div>

              <div class="layui-inline" style="float:right">
				<div class="layui-input-inline" style="width: 180px;">
					<input type="text"  placeholder="请输入范围名称" id="lessonName2"
						name="lessonName2" value="${lessonName2}" class="layui-input" maxlength="10">
				</div>
				<button class="layui-btn layui-btn-normal searchbtn" id="search_content" lay-submit=""
					lay-filter="demo1" style="margin-right: 20px;">搜索</button>
			</div>
		</div>

		
		<c:choose>
		 <c:when test="${rlList==null}">
		 <div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:14px;">~暂无数据~</p>
		 </c:when>
		 <c:otherwise>
		  <table class="layui-table" style="width:98%;margin:0 auto">
			    <thead>
			      <tr>
			        <th>ID</th>
					<th>范围名称</th>
					<th>添加时间</th>
					<th>操作</th>
			      </tr> 
			    </thead>
			    <tbody>
			    <c:forEach items="${rlList}" var="s">
					<tr>
						<td><c:out value="${s.lessonId}"></c:out></td>
						<td>${s.lessonName}<!-- 						<div align="center"> --> <!-- 							<input type="text" name="lessonName"  -->
							<%-- 						     value="${s.lessonName}" id="${s.lessonId}" placeholder="格式：lesson1" class="layui-input tcenter" > --%>
							<!-- 						  </div> -->
						</td>
						<td><fmt:formatDate value="${s.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><span class="icon-edit" data-toggle="modal"
							data-target="#myModal" id="${s.lessonId}"
							data-name="${s.lessonName}"
							style="font-size: 14px; color: #1e9fff; cursor: pointer;">修改</span>
							&nbsp;
							<a class="button border-red" href="javascript:void(0)"
							onclick="deleteone(<c:out value="${s.lessonId}"></c:out>)"> <span
								class="icon-remove-sign"
								style="font-size: 14px; color: #ff5722;">
									删除</span></a></td>
					</tr>
				</c:forEach>
			     <tr id="pages">
					<td colspan="4">
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
			        <h4 class="modal-title" id="myModalLabel">编辑题目来源</h4>
                 </div>
				<div class="modal-body">
					<form class="layui-form" method="post">
						<div class="layui-form-item" style="margin-top: 20px;">
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 120px;">范围名称</label>
								<div class="input-group" style="float: left;">
									<input type="text" name="lessonName" id="lessonName1"
										class="form-control" placeholder="格式：lesson1"
										style="width: 200px;" maxlength="10">
										 <input type="number" hidden=""
										name=lessonId id="lessonId">
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
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript">
layui
.use(
		[ 'form', 'layedit', 'laypage', 'laydate', 'layer' ],
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
			var lessonName1=$(this).attr("data-name");
			var lessonId=$(this).attr("id");
			$('#lessonId').val(lessonId);
			$('#lessonName1').val(lessonName1);
		});
		
		
		$('#confirm_update').on('click',function(){
			var lessonName=$('#lessonName1').val();
			var lessonId=$('#lessonId').val();
			var str1= lessonName.substring(0,lessonName.length-1);
			var str2= lessonName.substring(0,lessonName.length-2);
			var str3= lessonName.substring(0,lessonName.length-3);
			var num1= lessonName.substr(lessonName.length-1,1);
			var num2= lessonName.substr(lessonName.length-2,2);
			var num3= lessonName.substr(lessonName.length-3,3);
			var r = /^\+?[1-9][0-9]*$/;
// 			alert(lessonName);
// 			alert(lessonId);
			if((str1=="lesson"&&r.test(num1)) || (str2=="lesson"&&r.test(num2)) || (str3=="lesson"&&r.test(num3))) {
				$.ajax({
					url :'${ctx}/rangeLesson/updateLessonName.do',
					type :'post',
					dateType :'json',
					data :{
						lessonName : lessonName,
						lessonId : lessonId
					},
					success :function(date){
						if(date.code == 0){
							layer.msg("修改成功!");
							setTimeout(function(){
								window.location.href = "${ctx}/rangeLesson/rangeLessonList.do";
							},1000);
						}else{
							layer.msg(date.msg);
							return;
						}
					}
				})
			} else {
				layer.msg("请对应格式“lesson+数字”输入");
			}
			
		});


$('#search_content').click(function(){	
	var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i");   
	var lessonName2 = $('#lessonName2').val();
    if(pat.test(tasktypeName)==true){   
        alert("输入名称中含有非法字符!");   
        return false;   
    } else{
    	$('#myform').submit(); 
    }
})

function saveTaskName(){
	var lessonName1=$("#lessonName3").val();
	var str1= lessonName1.substring(0,lessonName1.length-1);
	var str2= lessonName1.substring(0,lessonName1.length-2);
	var str3= lessonName1.substring(0,lessonName1.length-3);
	var num1= lessonName1.substr(lessonName1.length-1,1);
	var num2= lessonName1.substr(lessonName1.length-2,2);
	var num3= lessonName1.substr(lessonName1.length-3,3);
	var r = /^\+?[1-9][0-9]*$/;
	if((str1=="lesson"&&r.test(num1)) || (str2=="lesson"&&r.test(num2)) || (str3=="lesson"&&r.test(num3))) {
		$.ajax({
			url : '${ctx}/rangeLesson/saveLesson.do', 
			type : 'post',
			dataType : 'json',
			data : {
				lessonName1 : lessonName1
			},
			success : function(res) {
				 if (res.code == 0) {
					layer.msg("添加成功!");
					window.location.href = "${ctx}/rangeLesson/rangeLessonList.do";
				}else{
					layer.msg(res.msg);
					return;
				}
			}
		});
	} else {
		layer.msg("请对应格式“lesson+数字”输入");
	}
}

function deleteone(uid){
	layer.confirm('您确认删除该范围么?',{btn:['确认','取消']},function(){
		$.post('${ctx}/rangeLesson/lessonDelete.do?',{'lessonId':uid},function(data){
			if(data.code==0){
				layer.msg('删除范围成功');
				 window.location.href="${ctx}/rangeLesson/rangeLessonList.do";	
			}else{
				layer.msg(data.msg);
				return;
			}
	 });
	    },function(){	
	    	 layer.closeAll();  
	});
}
function goPageAction(page){
	window.location.href="${ctx}/rangeLesson/rangeLessonList.do?pageNo="+page+"&lessonName2=${lessonName2}";
	}
function goMyPage(page,totalPage){
	if(page>0 && page<=totalPage){
		window.location.href="${ctx}/rangeLesson/rangeLessonList.do?pageNo="+page+"&lessonName2=${lessonName2}";
	}else{
		alert("请输入有效的整数");
	}
}

</script>
</html>