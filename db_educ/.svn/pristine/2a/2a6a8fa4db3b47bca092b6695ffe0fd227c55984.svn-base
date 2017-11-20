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
<title>讲义内容</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
</style>
</head>
<body>
<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/lectureNotes/lectureNotesList.do'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
	<div class="exam_content_content" style="display: block">
		<span style="margin-left: 300px;"> <c:out
				value="${ln.subName.subjectName}"></c:out>&nbsp;讲义&nbsp;lessson1<br>
		</span> <span style="margin-left: 390px;"> <c:out
				value="${teacher.teacherName}"></c:out></span> <br>
		<br>
		<div class="titlecreated" style="display: block; font-size: 15px;">
			<c:choose>
				<c:when test="${ln.handoutContent==null}">
			<span style="margin-left: 300px;">暂无讲义内容</span>
				</c:when>
				<c:otherwise>
				<span style="margin-left: 300px;"><c:out value="${ln.handoutContent}"  escapeXml="<true>" />
				</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">	
</script>
</html>