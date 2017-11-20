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
<title>分配班级</title>
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
 .arrow-down {  
   display: inline-block;  
  vertical-align: top;  
  border-top: 4px solid  #A9A9A9 ;  
  border-right: 6px solid transparent;  
  border-left: 6px solid transparent;  
  content: "";  
  border-width: 6px;
  margin-top: 10px;  
   }  
</style>
</head>
<body>

	<form class="layui-form" action="endExecute.do" method="post">
		<div class="panels_head">
			<span>分配班级列表</span>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;">年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="gradeId" lay-verify="gradeId" id="gradeId" autocomplete="off">
					<option value="">全部</option>
					<option value="">全部</option>
                      <c:forEach items="${grades}" var="g">
						<c:choose>
						   <c:when test="${gradeId==g.getGradeId()}">
						        <option selected="selected" value="${g.getGradeId()}">${g.getGradeName()}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.getGradeId()}">${g.getGradeName()}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">科目</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="subjectId" lay-verify="subjectId" id="subjectId" autocomplete="off">
					<option value="">全部</option>
					<option value="">全部</option>
						<c:forEach items="${subjects}" var="g">
						<c:choose>
						   <c:when test="${subjectId==g.subjectId}">
						        <option selected="selected" value="${g.subjectId}">${g.subjectName}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.subjectId}">${g.subjectName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" style="">搜索</button>
			</div>
			
		</div>
		<c:choose>
			<c:when test="${trList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table"
					style="width: 98%; margin-left: auto; margin-right: auto; font-size: 10px;">					
					<thead>
						<tr>
						    <th>校区<a href="endExecute.do?schoolId=1"><span class="arrow-down"></span></a></th>
						    <th>年级<a href="endExecute.do?grade=1"><span class="arrow-down"></span></a></th> 
							<th>科目</th>
							<th>姓名</th>
							<th>试卷</th>
							<th>分数<a href="endExecute.do?score=1"><span class="arrow-down"></span></a></th>
							<th>状态</th>
							<th>时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${trList}" var="students">
								<tr>
								   <td>${students.schoolZone.schoolName}</td>
                                    <td>${students.grade.getGradeName()}</td>
									<td>${students.subject.subjectName}</td>
									<td>${students.studentList.studentName}</td>
									<td>${students.paperInfo.piName}</td>
									<td>${students.score}</td>
								<td>
								<c:if test="${students.classId == null}">
								暂无分配班级
								</c:if> 
								<c:if test="${students.classId != null}">
								<c:choose>
										<c:when test="${students.careerList != null}">
											<c:forEach items="${students.careerList}" var="career">
												<p>${career.classNo.className}&nbsp;/&nbsp;${career.subjects.subjectName}</p>
											</c:forEach>
										</c:when>
										<c:otherwise>
										暂无分配班级
										</c:otherwise>
									</c:choose>
								</c:if></td>
								<td><fmt:formatDate value="${students.updateTime}"
										pattern="yyyy-MM-dd"></fmt:formatDate></td>
								<td>
							<a class="icon-plus-sign-alt" 
							id="${students.id}" data-grade="${students.gradeId}"
							data-subject="${students.subject.subjectId}"
							data-school="${students.choiceId}"
							data-toggle="modal" data-target="#myModal"
							 style="font-size:14px; color:#008bca;cursor:pointer;">分配班级</a>
<!-- 									&nbsp; -->
<!-- 									<a class="button border-red" -->
<!-- 									href="javascript:void(0)" -->
<%-- 									onclick="deleteone(<c:out value="${students.id}"></c:out>)"> --%>
<!-- 										<span class="icon-remove-sign" -->
<!-- 										style="font-size: 14px; color: #ff5722;"> -->
<!-- 											删除</span> -->
<!-- 								</a> -->
									</td>
								</tr>
						</c:forEach>
						<tr id="pages">
							<td colspan="10">
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
											</a>&nbsp;
										</c:otherwise>
									</c:choose>		
									<a href="javascript:;" onclick="goPageAction(${totalPages});">末页</a>
									&nbsp;总共&nbsp; ${totalPages} &nbsp;页

								</div>
							</td>
						</tr>
					</tbody>
				</table>
<!-- 				<div id="pages" style="text-align: center; margin-top: 30px;"></div> -->
			</c:otherwise>
		</c:choose>
		<!--模态框开始-->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			     aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:65%;">
				<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">分配班级</h4>
                     </div>
					<div class="modal-body">
	
						<table class="layui-table" style="width: 98%; margin: 0 auto">
						<thead>
							<tr>
						     	<th>年份</th>
						     	<th>季节</th>
						     	<th>校区</th>
						     	<th>科目</th>
						     	<th>年级</th>
								<th>班级</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody  id="tbody2">
						
						</tbody>
					</table>
					<input type="text" hidden="" name="studentId" id="studentId2"/>
					<input type="text" hidden="" name="gradeId" id="gradeId"/>
					
					<div id="page" style="text-align: center;"></div>
				</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 模态框结束--> 
	</form>
	
	<script type="text/html" id="note_template1">
		{{each objects as value i}}
		<tr>
            <td>{{value.schoolYear.year}}</td>
            <td>{{value.seasonType2.seasonName}}</td>
              <td>{{value.schoolZone.schoolName}}</td>
			<td>{{value.subject2.subjectName}}</td>
              <td>{{value.grade2.gradeName}}</td>
			<td>{{value.className}}</td>
			<td>
				<button type="button" class="layui-btn add_note"
                    data-toggle="modal" data-target="#myModal1"
					data-id="{{value.classId}}" data-grade="{{value.grade}}"
                    data-subject="{{value.subject}}"  data-type="{{value.seasonType}}"
                     data-year="{{value.year}}" data-school="{{value.schoolArea}}"
					style="height:30px;line-height:30px;padding:0 18px;">
					添加</button>
			</td>
		</tr>
		{{/each}}
	</script>
           
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript">
var pageno = 1, pagesize = 5,seasonType,grade;
	layui.use([ 'form', 'layedit', 'layer', 'laypage', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, laypage = layui.laypage, laydate = layui.laydate;

						//自定义验证规则
						form.verify({
// 							
						})
						//全选
						form.on('checkbox(allChoose)', function(data) {
							var child = $(data.elem).parents('table').find(
									'tbody input[name="checkbox"]');
							child.each(function(index, item) {
								item.checked = data.elem.checked;
							});
							form.render('checkbox');
						})
						//监听提交
						form.on('submit(demo1)', function(data) {
							
							return true;
						})
// 						form.on('select(select_knowend)',function(data){
// 							var seasonId=data.value;
// 							$.ajax({
// 								type:"post",
// 								url:"getClassesNo2.do",
// 								data:{
// 									seasonId:seasonId
// 								},
// 								dataType:"json",
// 								success:function(data){
// 				                    $("#classId").empty();
// 				                    $("#classId").append("<option value=''>请选择</option>");
// 				                    $.each(data.classNos,function(index,item){
// 				                        $("#classId").append( "<option value='"+item.classId+"'>"+item.className+"</option>");
// 				                        form.render('select');
// 				                    });
// 				                }
// 							});
// 						})
			});
	function goPageAction(page){
		window.location.href="${ctx}/entranceexam/endExecute.do?pageNo="+page+"&gradeId=${gradeId}&subjectId=${subjectId}&schoolId=${schoolId}";
		
		}
	
//getNote(pageno,grade,subject,seasonType);
function getNote(pageno,grade,seasonType) {
	$.getJSON("getClassNoList.do", {
		pageNo : pageno,
		pageSize : pagesize,
		grade:grade,
		seasonType:seasonType
	}, function(res) {
		if (res.code == 0) {
			console.log(JSON.stringify(res));
			var html = template('note_template1', res);
			$('#tbody2').html(html);
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
							getNote(obj.curr);
						}
					}
				});
			}
		} else{
				layer.msg(res.msg);
				var html = template('note_template1', res);
				$('#tbody2').html(html);
				return false;
		}
	})
}

	$('.icon-plus-sign-alt').on('click',function(){
		var studentId=$(this).attr('id');
		 seasonType=$(this).attr('data-school');
		 $('#studentId2').val(studentId);
		 grade = $(this).attr("data-grade");
		 $('#gradeId').val(grade);
		 getNote(pageno,grade,seasonType);
	});
	
	
	click_addnote();
	function click_addnote() {
	$('.add_note').on('click',function(){
		var classId=$(this).attr('data-id');
		var studentId=$('#studentId2').val();
		var gradeId=$(this).attr("data-grade");
		layer.confirm('确认添加该班级吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理,暂未添加到记录表
			$.ajax({
				url:'distributionClassNo.do',
				type:'post',
				dateType:'json',
				data:{
					id:studentId,
					gradeId:gradeId,
					classId:classId
				},
				success:function(data){
					if(data.code==0){
						layer.msg('添加班级成功!');
						window.location.href = "${ctx}/entranceexam/endExecute.do";
					}else{
						layer.msg(data.msg);
						return;
					}
				},
			})
			layer.close(index);
		});
	})
}
// 	$('#confirm_update').on('click',function(){
// 		var studentId=$('#studentId3').val();
// 		var classId=$('#classId1').val();
// 		var gradeId=$('#gradeId1').val();
// 		var gradeId=$('#gradeId1').val();
// 		var payType = $('#payType').val();
// 		$.ajax({
// 			url:'distributionClassNo.do',
// 			type:'post',
// 			dateType:'json',
// 			data:{
// 				id:studentId,
// 				classId:classId,
// 				gradeId:gradeId,
// 				payType:payType
// 			},
// 			success:function(data){
// 				//alert(JSON.stringify(data));
// 				if(data.code==0){
// 					layer.msg('分配班级成功!');
// 					setTimeout(function(){
// 						window.location.href = "${ctx}/entranceexam/endExecute.do";
// 					},1000)
// 				}else{
// 					layer.msg(data.msg,{time:3000});
// 					return;
// 				}
// 			},
// 		})
// 	});
	
	function deleteone(uid){
		 layer.confirm('您确认删除入学考试么?',{btn:['确认','取消']},function(){
			 $.post('${ctx}/entranceexam/deleteStudentExam.do?',{'id':uid},function(data){
				  if(data.code == 0){
						layer.msg('删除入学考试成功');
						setTimeout(function(){
							window.location.href="${ctx}/entranceexam/endExecute.do";
						},1000);
				  }else{
					  layer.msg(data.msg,{time:3000})
			    	return;
				  }
			 });
		    },function(){	
		    	  layer.closeAll();  
		});
	}
		
	
</script>
</html>







