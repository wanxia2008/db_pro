<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>学生管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/echarts.min.js"></script>
<style type="text/css">
.activechoose{background-color:#008bca !important;}
.activechoose a{color:#fff !important}
.pagelist a{padding:5px 18px;border:1px solid #e1e2e3;}
.pagelist .current{background:#008bca;color:#fff}
.pagelist a:hover{background:#008bca;color:#fff}
#pages{height:50px;line-height:50px;}
</style>
</head>
<body>
	<form class="layui-form" action="${ctx}/lesson/studentmanage.do">
		<div class="paenls_content2" style="height:auto !important">
			<div style="width: 20%;padding-bottom:20px;background: #eeeeee;">
				
				<div class="layui-form-item"
					style="margin-left: 65px; height: 30px; margin-top: 30px;font-size: 14px;">
					<div class="layui-inline" style="width: 120px;">
						<select name="grade" id="grade">
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
				<div class="layui-form-item"
					style="margin-left: 65px; height: 30px; margin-top: 30px;font-size: 14px;">
					<div class="layui-inline" style="width: 120px;">
						<select name="classId" id="classId">
<!-- 							<option value="">全部</option> -->
<!-- 							<option value="">全部</option> -->
							<c:forEach items="${classList}" var="c">
								<c:choose>
									<c:when test="${classId==c.classId}">
										<option selected="selected" value="${c.classId}">${c.className}</option>
									</c:when>
									<c:otherwise>
										<option value="${c.classId}">${c.className}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-form-item"
					style="margin-left: 65px; height: 30px; margin-top: 30px;font-size: 14px;">
					<div class="layui-inline" style="width: 120px;">
						<select name="subject" id="subject">
							<option value="">全部</option>
							<option value="">全部</option>
							<c:forEach items="${subjects}" var="g">
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
				<button  class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1"
						style="font-size:14px;margin-left:65px;width: 120px;margin-top:10px;">搜索</button>
				<table class="layui-table" lay-skin="row"
					style="width: 50%; margin-left:65px;margin-top:30px;">
					<colgroup>
						<col width="100">
					</colgroup>
					<thead>
						<tr>
							<td style="font-size:14px;margin-left:65px;">整体情况</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${classNosList}" var="class1">
						<tr>
							<td id="${class1.student.studentId}">
							<a href="${ctx}/lesson/studentmanage.do?studentId=${class1.student.studentId}&grade=${grade}&classId=${classId}&subject=${subject}">${class1.student.studentName}</a>
						      </td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<c:choose>
			   <c:when test="${stuId==null}">
				<p class="no_data_p"  style="margin-top: 30px;">
				<span style="font-size: 14px;">~暂无数据~</span></p>
				<span class="" 
 					   style="font-size: 14px; color: #1e9fff;"></span>
			   </c:when>
			   <c:otherwise>
			<div style="width: 80%; background: #ffffff;text-align:center;">
			<div style="margin-top: 15px;">
			<a href="${ctx}/lesson/studentNameDateils.do?studentId=<c:out value="${stuId.studentId}"></c:out>&grade=${grade}&classId=${classId}&subject=${subject}"> 
					<span class="icon-eye-open" 
 					   style="font-size: 14px; color: #1e9fff;">学籍资料</span></a>
 				</div>	   
			<table class="layui-table" >
			<thead>
				<tr>
					<th>试卷编号</th>
					<th>试卷标题</th>
					<th>试卷科目</th>
					<th>考试时间</th>
					<th>考试分数</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${trList}" var="row">
					<tr>
						<td><c:out value="${row.paperId}"></c:out></td>
						<td>
						<c:choose>
						<c:when test="${row.paperInfo.piName!=null}"><c:out value="${row.paperInfo.piName}"></c:out></c:when>
						<c:otherwise>
						暂无标题
						</c:otherwise>
						</c:choose>
						</td>
						<td><c:out value="${row.subject.subjectName}"></c:out></td>
						<td><fmt:formatDate value="${row.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
						<td>
						<c:choose>
						<c:when test="${row.score!=null}">
						<c:out value="${row.score}"></c:out>分
						</c:when>
							<c:otherwise>
							暂无分数
							</c:otherwise>
						</c:choose>
						
						</td>
						<td>
						<a href="${ctx}/lesson/checkPaper.do?studentId=${row.studentId}&paperId=${row.paperId}">
						   <span class="icon-eye-open" style="font-size: 15px;color:#1e9fff;">查看</span>
						</a>&nbsp;
						<a href="${ctx}/lesson/studentScore.do?scoreId=<c:out value="${row.id}&grade=${grade}&classId=${classId}&subject=${subject}&studentId=${row.studentId}&paperId=${row.paperId}"></c:out>">
						   <span class="icon-eye-open" style="font-size: 15px;color:#1e9fff;">分析</span>
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
			</div>
			   </c:otherwise>
			</c:choose>
		</div>
	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
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
		
		
function goPageAction(page){
	window.location.href="${ctx}/lesson/studentmanage.do?pageNo="+page+"&grade=${grade}&classId=${classId}&subject=${subject}&studentId=${stuId.studentId}";
	//
	}
function goMyPage(page,totalPage){
	if(page>0 && page<=totalPage){
		window.location.href="${ctx}/lesson/studentmanage.do?pageNo="+page+"&grade=${grade}&classId=${classId}&subject=${subject}&studentId=${stuId.studentId}";
	}else{
		alert("请输入有效的整数");
	}
}

//选中高亮
function getUrlParam(name)
{
var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
var r = window.location.search.substr(1).match(reg);  //匹配目标参数
if (r!=null) return unescape(r[2]); return null; //返回参数值
} 
var studentId = getUrlParam('studentId');
if(studentId!=null){
	$("#"+studentId).addClass("activechoose");
}
</script>
</html>