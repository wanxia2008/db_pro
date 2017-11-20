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
<title>我的讲义列表</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript"
	src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
ul li {
	margin-top: 5px;
	padding: 5px;
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
	<form class="layui-form" id="frm1"
		action="${ctx}/lectureNotes/lectureNotesList.do">
		<div class="panels_head">
			<span>我的讲义</span>
				<button type="button" class="layui-btn layui-btn-normal"
				lay-submit="" onclick="window.location.href='lectureNoteManage.do'"
				lay-filter="demo1"
				style="height: 30px; line-height: 30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; font-size: 14px">新增</button>
		</div>
		<div class="layui-form-item" style="margin-top: 20px">
<!-- 			<div class="layui-inline"> -->
<!-- 				<label class="layui-form-label" -->
<!-- 					style="width: 120px; margin-left: 0px;font-size: 14px;">讲义季节类型</label> -->
<!-- 				<div class="layui-input-inline" style="width: 120px;"> -->
<!-- 					<select name="handoutType" class="input w80"> -->
<!-- 						<option value="">全部</option> -->
<!-- 						<option value="">全部</option> -->
<%-- 						<c:forEach items="${stList}" var="g"> --%>
<%-- 						<c:choose> --%>
<%-- 						   <c:when test="${handoutType==g.seasonId}"> --%>
<%-- 						        <option selected="selected" value="${g.seasonId}">${g.seasonName}</option> --%>
<%-- 						   </c:when> --%>
<%-- 						   <c:otherwise> --%>
<%-- 						       <option value="${g.seasonId}">${g.seasonName}</option> --%>
<%-- 						   </c:otherwise> --%>
<%-- 						</c:choose> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="grade">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${gList}" var="g">
						<c:choose>
						   <c:when test="${grade==g.gradeId}">
						        <option selected="selected" value="${g.gradeId}">${g.gradeName}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.gradeId}">${g.gradeName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="width: 120px; margin-left: 0px;font-size: 14px;">讲义科目</label>
				<div class="layui-input-inline" style="width: 120px;">
					
					<select name="subject">
						<option value="">全部</option>
						<option value="">全部</option>
					<c:forEach items="${sList}" var="g">
						<c:choose>
						   <c:when test="${subject==g.subjectId}">
						        <option selected="selected" value="${g.subjectId}">${g.subjectName}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.subjectId}">${g.subjectName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;font-size: 14px;">是否共享</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="isPublic" id="isPublic">
						<option value="">全部</option>
						<option value="">全部</option>					
						<option value="1"
							<c:choose>
									<c:when test="${isPublic==1}">selected="selected"</c:when>
									</c:choose>>是</option>
						<option value="0"
							<c:choose>
									<c:when test="${isPublic==0}">selected="selected"</c:when>
									</c:choose>>否</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">输入标题</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" name="handoutTitle" id="notetitle"
						placeholder="请输入讲义标题" class="layui-input" value="${handoutTitle}" maxlength="15">
				</div>
				<button type="button" id="search_content" class="layui-btn layui-btn-normal"
					style="margin-right: 10px;font-size: 14px;" lay-submit="" lay-filter="demo1">搜索</button>
			</div>
		</div>

		<!--表格-->
		<div class="panels_head">
			<span>讲义列表</span>
		</div>
		<c:choose>
			<c:when test="${lnList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table" style="width: 98%; margin: 0 auto;margin-top: 20px;">
						<tr>
							<th>讲义编号</th>
							<th>讲义标题</th>
							<th>讲义年级</th>
							<th>讲义科目</th>
							<th>讲义时间</th>
							<th>是否共享</th>
							<th>创建者</th>
							<th>使用次数</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${lnList}" var="row">
							<tr>
								<td><c:out value="${row.handoutId}"></c:out></td>
								<td><c:out value="${row.handoutTitle}"></c:out></td>
								<td>${row.grades.gradeName}</td>
								<td><c:out value="${row.subName.subjectName}"></c:out></td>
								<td><fmt:formatDate value="${row.createTime}"
										pattern="yyyy-MM-dd"></fmt:formatDate></td>
								<td><c:if test="${row.isPublic==1}">
										<span style="color: #008bca;">是</span>
									</c:if> <c:if test="${row.isPublic==0}">
										<span style="color: #ff5722;">否</span>
									</c:if></td>
								<td>${row.teacher.teacherName}</td>
								<td><c:out value="${row.usedCount}"></c:out><span>次</span></td>
								<td>
								 <a href="${ctx}/lectureNotes/see.do?handoutId=<c:out value="${row.handoutId}"></c:out>&pageNo=${page}">
										<span class="icon-edit"
										style="font-size: 14px; color: #008bca; padding-right: 10px;">修改</span>
								</a>&nbsp; <a class="button border-red" href="javascript:void(0)"
									onclick="share(<c:out value="${row.handoutId}"></c:out>,<c:out value="${row.isPublic}"></c:out>)">
										<span class="icon-bullhorn"
										style="font-size: 14px; color: #008bca; padding-right: 10px;">
											共享</span>
								</a>
								 &nbsp; <a class="button border-red" href="javascript:void(0)"
									onclick="changeExamStatus(${row.handoutId},${row.isStart})">
									<c:if test="${row.isStart == 1}">
									  <span class="icon-remove-sign"
										style="font-size: 14px; color: #008bca; padding-right: 10px;">
											停用</span>
									</c:if>
									<c:if test="${row.isStart == 0}">
									  <span class="icon-bullhorn"
										style="font-size: 14px; color: #008bca; padding-right: 10px;">
											启用</span>
									</c:if>
										
								</a> 
								 &nbsp; <a class="button border-red" href="javascript:void(0)"
									onclick="deleteone(<c:out value="${row.handoutId}"></c:out>)">
										<span class="icon-remove-sign"
										style="font-size: 14px; color: #ff5722; padding-right: 10px;">
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
											<a href="javascript:;">下一页</a>&nbsp;
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
					</table>
			</c:otherwise>
		</c:choose>
		<!-- 	<div id="pages" style="text-align: center;"></div> -->
	</form>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
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
							/* layer.alert(JSON.stringify(data.field), {
								title : '最终的提交信息'
							}) */
							return true;
						});
					});
	
	function deleteone(uid){
		layer.confirm('您确认删除该条讲义么?',{btn:['确认','取消']},function(){
			$.post('${ctx}/lectureNotes/delete.do?',{'handoutId':uid},function(data){
 				if(data.code==0){
 					layer.msg('删除讲义成功');
 					setTimeout(function(){
 						 window.location.href="${ctx}/lectureNotes/lectureNotesList.do";	
 					},400);
 					
 				}else{
 					layer.msg(data.msg,{time:1000});
 					return;
 				}
		 });
		    },function(){	
		    	 layer.closeAll();
			 
		});
// 		$.ajax({
// 			url:'${ctx}/lectureNotes/delete.do',
// 			type:'post',
// 			dataType:'json',
// 			data:{
// 				handoutId:uid
// 			},
// 			success:function(data){
// 				if(data.code==0){
// 					layer.msg('删除讲义成功');
// 					 window.location.href="${ctx}/lectureNotes/lectureNotesList.do";	
// 				}else{
// 					layer.msg(data.msg);
// 					return;
// 				}
// 			}
// 		});
		 
	}
	function share(uid,isPublic){
		if(isPublic==1){
			var isTrue = 0;
			layer.confirm('您确认取消共享该条讲义么?',{btn:['确认','取消']},function(){
				 $.post('${ctx}/lectureNotes/share.do?',{'handoutId':uid,'isTrue':isTrue},function(data){
					  if(data.code==0){
							layer.msg(data.msg);
							/* {time:2500,icon:0} */
						      setTimeout(function(){
						    	  window.location.href="${ctx}/lectureNotes/lectureNotesList.do";	
						      },1000);
					  }else{
							layer.msg(data.msg,{time:1000});
							return;
					  }
				 });
			    },function(){	
			    	layer.closeAll();  
			});
		}else{
			var isTrue = 1;
			layer.confirm('您确认共享该条讲义么?',{btn:['确认','取消']},function(){
				 $.post('${ctx}/lectureNotes/share.do?',{'handoutId':uid,'isTrue':isTrue},function(data){
					  if(data.code==0){
							layer.msg(data.msg);
						      setTimeout(function(){
						    	  window.location.href="${ctx}/lectureNotes/lectureNotesList.do";	  
						      },400);
					  }else{
							layer.msg(data.msg,{time:1000});
							return;
					  }
				 });
			    },function(){	
			    	  layer.closeAll(); 
			});
		}
	}
	
	function goPageAction(page){
		window.location.href="${ctx}/lectureNotes/lectureNotesList.do?pageNo="+page+"&handoutTitle=${handoutTitle}&handoutType=${handoutType}&isPublic=${isPublic}&subject=${subject}&grade=${grade}"; 
		//
		}
	function goMyPage(page,totalPage){
		if(page>0 && page<=totalPage){
			window.location.href="${ctx}/lectureNotes/lectureNotesList.do?pageNo="+page+"&handoutTitle=${handoutTitle}&handoutType=${handoutType}&isPublic=${isPublic}&subject=${subject}&grade=${grade}";
		}else{
			alert("请输入有效的整数");
		}
	}
	
// 	window.onload = function() {
//         //这么写是为了实现js代码与html代码的分离，当我修改js时，不能影响html代码。
//         document.getElementById("frm1").onsubmit = function() {
//             var d1 = this.startCreateTime.value;
//             var d2 = this.endCreateTime.value;
//           /*   if (!verifyDate(d1)) {
//                 alert("第一个日期格式不对!");
//                 return false;
//             }
//             if (!verifyDate(d2)) {
//                 alert("第二个日期格式不对!");
//                 return false;
//             } */
//             if (!compareDate(d1, d2)) {
//                layer.msg("第二个日期不能比第一个日期小!");
//                 return false;
//             }
//         };
//     }
    function compareDate(d1, d2) {
        var arrayD1 = d1.split("-");
        var date1 = new Date(arrayD1[0], arrayD1[1], arrayD1[2]);
        var arrayD2 = d2.split("-");
        var date2 = new Date(arrayD2[0], arrayD2[1], arrayD2[2]);
        if (date1 > date2)
            return false;
        return true;
    }
    function verifyDate(d) {
        var datePattern = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
        return datePattern.test(d);
    }
    $('#search_content').click(function(){
    	
    	var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i");   
    	var schoolName2=$('#notetitle').val();
    	
        if(pat.test(schoolName2)==true)   
        {   
            layer.msg("输入名称中含有非法字符!");   
            return false;   
        } else{
        	$('#frm1').submit();
        }
    });
    
    function changeExamStatus(handoutId,isStart){
		var msg="";
		var is_start;
		if(isStart == 1){
			msg = "确认停用该份讲义？";
			is_start = 0;
		}else{
			msg = "确认启用该份讲义";
			is_start = 1;
		}
		
		layer.confirm(msg,{btn:['确定','取消']},function(){
			$.ajax({
				url:'${ctx}/lectureNotes/changeexamstatus.do',
				type:'post',
				dataType:'json',
				data:{'handoutId':handoutId,'isStart':is_start},
				success:function(data){
					if(data.code == 0){
						   if(isStart == 1){
							   layer.msg('该讲义停用成功');
						   }else{
							   layer.msg('该讲义开启成功');
						   } 
						  setTimeout(function(){
							  window.location.reload();  
						  },2000);
					}else{
						layer.msg(data.msg);
					}
				}
			})
		},function(){});
		
		
	}
</script>
</html>