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
<title>试卷详情页面</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
</style>
</head>
<body>
	<div class="exam_content_content" style="display: block">
	<button type="button" class="layui-btn" style="float: right;">
			<a href="${ctx}/questionBank/myquestionbank.do">返回</a>
		</button>
		<h4>
			阅读理解题目详情
		</h4>
		
		<div class="titlecreated" style="display: block; font-size: 15px;">
			 
			 <c:out value="${qr.readId}"></c:out> ${qr.readDesc}
					<p style="margin-left: 10px;">
					<c:forEach items="${qrList}" var="q">
					 <c:out value="${q.optiontitle}"></c:out><br>
						A、<c:out value="${q.optionA_read}"></c:out>&nbsp;&nbsp; 
						B、<c:out value="${q.optionB_read}"></c:out><br> 
						C、<c:out value="${q.optionC_read}"></c:out>&nbsp;&nbsp;
						D、<c:out value="${q.optionD_read}"></c:out><br>
						</c:forEach>
					</p>
					<p>答案：<c:out value="${qr.answer_read}"></c:out></p>
					<p>答案的描述：<c:out value="${qr.answerDesc_read}"></c:out></p>
					<p>难度系数：<c:out value="${qr.degree_read}"></c:out>星</p>
					<p>题目分值：<c:out value="${qr.score_read}"></c:out></p>
					<p>使用总次数：<c:out value="${qr.useCount}"></c:out></p>
					<p>题目是否公有：
					<c:if test="${qr.ispublic_read ==1}">是</c:if>
					<c:if test="${qr.ispublic_read ==0}">否</c:if>
					</p>
				<p>题目创建时间：<fmt:formatDate value="${qr.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></p>
		</div>
	</div>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	
</script>

</html>