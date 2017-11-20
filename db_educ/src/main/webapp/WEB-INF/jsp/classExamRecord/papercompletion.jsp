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
<title>学员作业完成情况</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
.layui-form-checked span, .layui-form-checked:hover span {
	background-color: #35CE8D;
}

.layui-form-switch {
	width: 55px;
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
.icon-star,.icon-star-empty{color:#333 !important}
ol, ul{margin-bottom:0;}
</style>
</head>
<body>
	<form class="layui-form" action="">
		<div class="panels_head">
			<span>学员试卷完成情况</span> 
	    <input hidden="" type="text" name="classId" id="classId" value="${classId}">
	      <input hidden="" type="text" name="paperId" id="paperId" value="${paperId}">
        <button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/examRecord/paperList.do?classId=${classId}'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
       </div>
		<div class="layui-form-item" style="margin-top: 20px">
			<div class="layui-inline">
				<label class="layui-form-label"
					style="width: 120px; font-size: 14px;">学员姓名</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" name="studentName" id="studentName"
						placeholder="请输入学员姓名" class="layui-input" value="${studentName}">
				</div>
					<button class="layui-btn layui-btn-normal"  lay-submit=""
					lay-filter="demo1" id="search_content">搜索</button>
			</div>
		</div>
		<span style="height: 40px; line-height: 40px; padding: 0 18px; color: #919191; top: 20%; margin-top: 0;margin-left:1%;border-radius:0; font-size: 14px">试卷总分:${info.totalScore}分</span>
		<span style="height: 40px; line-height: 40px; padding: 0 18px; color: #919191; top: 20%; margin-top: 0;border-radius:0; font-size: 14px;margin-left:0">班级平均分：${classAvg}分</span>
		<c:choose>
			<c:when test="${trList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size: 14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				
					<table class="layui-table" style="width: 98%; margin: 0 auto">
					<thead>
						<tr>
						    <th>试卷年级</th>
							<th>试卷班级</th>
							<th>试卷科目</th>
							<th>试卷名称</th>
							<th>学员姓名</th>
							<th>分数</th>
							<th>班级排名</th>
							<th>考试时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${trList}" var="row">
							<tr>
							  <td>${grade.gradeName}</td>
								<td>${row.classNo.className}</td>
								<td>${row.subject.subjectName}</td>
								<td>${row.paperInfo.piName}</td>
								<td>${row.studentList.studentName}</td>
								<td>
								<c:choose>
										<c:when test="${row.score != null}">
								         ${row.score}
								</c:when>
										<c:otherwise>
								      暂无分数
								</c:otherwise>
									</c:choose>
								</td>
								<td>
								<c:forEach items="${recordList}" var="tr">
								<c:if test="${row.studentId==tr.studentId}">
								  第&nbsp;${tr.scoreRank}&nbsp;名
								</c:if>
								</c:forEach>
								</td>
								<td>
								<fmt:formatDate value="${row.createTime}" 
										pattern="yyyy-MM-dd"></fmt:formatDate>
<!-- 								<a class="button border-red" href="javascript:void(0)" -->
<%-- 									onclick="deleteone(<c:out value="${row.id}"></c:out>)"> <span --%>
<!-- 										class="icon-remove-sign" -->
<!-- 										style="font-size: 14px; color: #ff5722; padding-right: 10px;"> -->
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
											</a>
											&nbsp;
										</c:otherwise>
									</c:choose>
									<a href="javascript:;" onclick="goPageAction(${totalPages});">末页</a>
									&nbsp;总共&nbsp; ${totalPages} &nbsp;页
								</div>
							</td>
						</tr>
						</tbody>	
					</table>
			</c:otherwise>
		</c:choose>
	</form>
</body>
<script type="text/javascript" src=".././js/jquery-1.8.3.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
layui
.use(
		[ 'form', 'layedit', 'laypage', 'laydate' ],
		function() {
			var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

			//创建一个编辑器
			var editIndex = layedit.build('LAY_demo_editor');

		//监听提交
		form.on('submit(demo1)', function(data) {
			
			return true;
		});
		
	});
	

function deleteone(uid){
	layer.confirm('您确认删除该学员么?',{btn:['确认','取消']},function(){
		 $.post('${ctx}/examRecord/deleteStudentId.do?',{'id':uid},function(data){ 
				if(data.code==0){
					layer.msg('删除学员成功');
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
	  
	function goPageAction(page){
		window.location.href="${ctx}/examRecord/paperCompletion.do?pageNo="+page+"&studentName=${studentName}&classId=${classId}&paperId=${paperId}";
		
		}
	
</script>
</html>