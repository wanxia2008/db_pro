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
<title>学员成绩情况</title>
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
	<form id="myform" class="layui-form" action="scoreList.do">
		<div class="panels_head">
			<span>分配班级列表</span>
		</div>
				<div class="layui-form-item" style="margin-top: 20px;">
			
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">校区</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="schoolId" lay-verify="schoolId" id="schoolId" autocomplete="off">
					<option value="">全部</option>
					<option value="">全部</option>
						<c:forEach items="${zones}" var="g">
						<c:choose>
						   <c:when test="${schoolId==g.schoolId}">
						        <option selected="selected" value="${g.schoolId}">${g.schoolName}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.schoolId}">${g.schoolName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
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
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" style="">搜索</button>
			</div>
		</div>
		<c:choose>
		 <c:when test="${studentList==null}">
		 <div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:14px;">~暂无数据~</p>
		 </c:when>
		 <c:otherwise>
			<table class="layui-table" style="width:98%;margin:0 auto">
			<thead>
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>校区<a href="scoreList.do?school=1"><span class="arrow-down"></span></a></th>
				    <th>年级<a href="scoreList.do?grade=1"><span class="arrow-down"></span></a></th> 
					<th>总分<a href="scoreList.do?score=1"><span class="arrow-down"></span></a></th>
					<th>状态</th>
					<th>缴费状态</th>
					<th>添加时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${studentList}" var="s">
					<tr>
								<td><c:out value="${s.scoreId}"></c:out></td>
								<td><c:out value="${s.student.studentName}"></c:out>
								<td><c:out value="${s.schoolZone.schoolName}"></c:out></td>
								<td><c:out value="${s.grade.gradeName}"></c:out></td>
								<td><c:out value="${s.score}"></c:out></td>
								<td>
								<c:choose>
											<c:when test="${s.careerList != null}">
												<c:forEach items="${s.careerList}" var="career">
													<p>${career.classNo.className}&nbsp;/&nbsp;${career.subjects.subjectName}</p>
												</c:forEach>
											</c:when>
											<c:otherwise>
								     	未分班
										</c:otherwise>
										</c:choose>
								</td>
								<td>
								<c:choose>
										<c:when test="${s.payType==null}">
								     暂无
								</c:when>
										<c:otherwise>
											<c:if test="${s.payType==1}">
								免费入学
								</c:if>
											<c:if test="${s.payType==2}">
								缴费入学
								</c:if>
										</c:otherwise>
									</c:choose>
								</td>
								<td><fmt:formatDate value="${s.createTime}"
										pattern="yyyy-MM-dd" /></td>
								<td><span class="icon-edit" data-toggle="modal"
									data-target="#myModal" id="${s.studentId}"
									data-grade="${s.grade.gradeId}" data-scoreId="${s.scoreId}"
									style="font-size: 14px; color: #008bca; cursor: pointer;">分配班级</span>
<!-- 									&nbsp;  -->
<!-- 									<a class="button border-red" href="javascript:void(0)" -->
<%-- 									      onclick="deleteone(<c:out value="${s.scoreId}"></c:out>)"> --%>
<!-- 										<span class="icon-remove-sign" -->
<!-- 										style="font-size: 14px; color: #ff5722;"> 删除</span> -->
<!-- 								</a> -->
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
			        <h4 class="modal-title" id="myModalLabel">缴费类型</h4>
               </div>
				<div class="modal-body">
					<form class="layui-form" method="post">
						<div class="layui-form-item" style="margin-top: 20px;">
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 120px;">缴费类型</label>
								<div class="input-group" style="float:left;width:140px;">
									<select name="payType" id="payType">
									<option value="">请选择</option>
									<option value="1">免费入学</option>
									<option value="2">缴费入学</option>
									</select>
								</div>
							</div>
						</div>
						<input type="number" hidden="" name="studentId" id="studentId">
						<input type="number" hidden="" name="gradeId" id="gradeId2">
						<input type="number" hidden="" name="scoreId" id="scoreId">
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
	var studentId=$(this).attr("id");
	var gradeId2=$(this).attr("data-grade");
	var scoreId=$(this).attr("data-scoreId");
	$('#studentId').val(studentId);
	$('#gradeId2').val(gradeId2);
	$('#scoreId').val(scoreId);
});

$('#confirm_update').on('click',function(){
	var payType=$('#payType').val();
	var studentId=$('#studentId').val();
	var gradeId2 = $('#gradeId2').val();
	var id = $('#scoreId').val();
	$.ajax({
		url:'${ctx}/score/isPayGotoschoo.do',
		type:'post',
		dateType:'json',
	      data:{
	    	  payType:payType,
	    	  studentId:studentId,
	    	  gradeId:gradeId2,
	    	  scoreId:id
	      },
	      success:function(data){
		    	if(data.code == 0){
		    		layer.msg("修改成功!");
		    		setTimeout(function(){
			    		window.location.href = "${ctx}/score/scoreList.do";
		    		},500);
		    	}else{
		    		layer.msg(data.msg,{time:3000});
		    		return;
		    	}
		    },
	})
});


function deleteone(uid){
	layer.confirm('您确认删除学员么?',{btn:['确认','取消']},function(){
		 $.post('${ctx}/score/studentIdDelete.do?',{'scoreId':uid},function(data){
				if(data.code==0){
					layer.msg('删除学员成功');
					setTimeout(function(){
						 window.location.href="${ctx}/score/scoreList.do";	
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
	var subjectName =$('#subjectName').val();
    if(pat.test(subjectName)==true)   
    {   
    	layer.msg("输入名称中含有非法字符");   
        return false;   
    } else{
    	$('#myform').submit();
    }
})


function goPageAction(page){
	window.location.href="${ctx}/score/scoreList.do?pageNo="+page+"&schoolId=${schoolId}&gradeId=${gradeId}";
	}

</script>
</html>