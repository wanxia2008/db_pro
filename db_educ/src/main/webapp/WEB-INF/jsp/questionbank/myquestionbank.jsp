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
<title>我的题库</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
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
.answer{padding-bottom:10px;}
.answer p{display:inline !important;color:#333 !important}
.answer img{/*width:200px;height:200px;display:block !important;margin:10px 0*/}
.icon-star,.icon-star-empty{color:#333 !important}
.kfformula{width:auto !important;height:100% !important;text-align:left}





</style>
</head>
<body>
	<form class="layui-form" action="${ctx}/questionBank/myquestionbank.do">
		<div class="panels_head">
			<span>试题列表</span>
			<!-- <a class="layui-btn a-btn" href="createquestion.do">新增题目</a> -->
			<button type="button" class="layui-btn layui-btn-normal"
				lay-submit="" onclick="window.location.href='createquestion.do'"
				lay-filter="demo1"
				style="height: 30px; line-height: 30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; font-size: 14px">新增</button>

		</div>
		<div class="layui-form-item" style="margin-top: 20px">
		<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="gradeId" id="gradeId" placeholder="请选择年级">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${gradeList}" var="g">
							<c:choose>
								<c:when test="${gradeId==g.gradeId}">
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
				<label class="layui-form-label" style="font-size: 14px;">科目</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="subject" id="subject"
					             placeholder="请选择科目">
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
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">题目类型</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="handoutType" id="handoutType">
						<c:forEach items="${qtList}" var="st">
							<c:choose>
								<c:when test="${handoutType==st.topicId}">
									<option selected="selected"
										value="<c:out value="${st.topicId}"></c:out>"><c:out
											value="${st.topicName}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="<c:out value="${st.topicId}"></c:out>"><c:out
											value="${st.topicName}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 110px;font-size: 14px;">是否公有</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="isPublic" id="isPublic">
						<option value="">全部</option>
						<option value="">全部</option>
						<option value="0"
							<c:choose>
									<c:when test="${isPublic==0}">selected="selected"</c:when>
									</c:choose>>私有</option>
						<option value="1"
							<c:choose>
									<c:when test="${isPublic==1}">selected="selected"</c:when>
									</c:choose>>公有</option>
					</select>
				</div>
				<button class="layui-btn layui-btn-normal searchbtn">搜索</button>
			</div>
		</div>
		<c:choose>
			<c:when test="${questionList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size: 14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<!--表格开始-->
				<table class="layui-table" style="width: 98%; margin: 0 auto">
					<thead>
						<tr>
							<th>属性</th>
							<th style="width:35%">题目</th>
							<th>题型</th>
							<th>年级</th>
							<th>科目</th>
							<th>创建人</th>
							<th>使用数</th>
							<th>难度</th>
							<th>创建时间</th>
							<th>操作</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${questionList}" var="qb">
							<c:if test="${qb.key=='readList'}">
								<c:forEach items="${qb.value}" var="read">
									<tr>
										<c:if test="${read.getIspublic_read() == 0}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" name="close" lay-skin="switch"
														id="${read.getReadId()}" placeholder="${read.getType()}"
														lay-filter="switchTest" lay-text="公|私">
												</div>
											</td>
										</c:if>
										<c:if test="${read.getIspublic_read() == 1}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" checked="" id="${read.getReadId()}"
														name="open" lay-skin="switch"
														placeholder="${read.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<td style="text-align:left"><div class="answer">${read.readDesc}</div>
										 <c:forEach
												items="${read.getQjList()}" var="qr" varStatus="reads">
                                   <div class="answer">${reads.index+1}、${qr.getOptiontitle()}</div>
                                   <div class="answer">A.${qr.optionA_read}</div>
                                   <div class="answer">B.${qr.optionB_read}</div>
                                   <div class="answer">C.${qr.optionC_read}</div>
                                   <div class="answer">D.${qr.optionD_read}</div>
											</c:forEach>
										</td>
										<td>阅读理解</td>
										<td>${read.grade.gradeName}</td>
										<td>${read.subject2.subjectName}</td>
										<td><c:out value="${read.teacher.teacherName}"></c:out></td>										
										<td>${read.getRightCount()}<span>&nbsp;次</span></td>
										<td>${read.getDegree_read()} 
										<span class="uls_showstars">
										  <span class="icon-star"
											style="cursor: pointer;"></span>
											</span></td>
										<td><fmt:formatDate value="${read.createTime}"
												pattern="yyyy-MM-dd"></fmt:formatDate></td>	
										<td>
										   <a class="button border-red"
											href="toupdaReading.do?id=<c:out value="${read.readId}"></c:out>">
												<span class="icon-edit"
												style="font-size: 14px; color: #1e9fff;"> 修改</span>
										</a> &nbsp; <a class="button border-red" href="javascript:void(0)"
											onclick="deleteone3(<c:out value="${read.readId}"></c:out>)">
												<span class="icon-remove-sign"
												style="font-size: 14px; color: #ff5722; padding-right: 10px;">
													删除</span>
										</a>
										</td>
									</tr>
									</c:forEach>
							</c:if>
							<c:if test="${qb.key=='judgeList'}">
								<c:forEach items="${qb.value}" var="judge" varStatus="ju">
								<tr>
										<c:if test="${judge.getIspublic2() == 0}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" name="close" lay-skin="switch"
														id="${judge.getJudgeId()}"
														placeholder="${judge.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<c:if test="${judge.getIspublic2() == 1}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" checked=""
														id="${judge.getJudgeId()}" name="open" lay-skin="switch"
														placeholder="${judge.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<td style="text-align:left"><div class="answer">${ju.index+1}、${judge.judgeDesc}</div>
											<%-- 答案、 <c:if test="${judge.answer2 == 0}">错误</c:if> <c:if
												test="${judge.answer2 == 1}">正确</c:if> --%>
												 A、正确  &nbsp;&nbsp;B、错误
												</td>
										<td>判断</td>
										<td>${judge.grade.gradeName}</td>
										<td>${judge.subject2.subjectName}</td>
										<td><c:out value="${judge.teacher.teacherName}"></c:out></td>
										<td><c:out value="${judge.getRightCount()}"></c:out><span>&nbsp;次</span></td>
										<td><c:out value="${judge.getDegree2()}"></c:out>
											 <span class="uls_showstars">
										  <span class="icon-star"
											style="cursor: pointer;"></span>
											</span></td>
										<td><fmt:formatDate value="${judge.createTime}"
												pattern="yyyy-MM-dd"></fmt:formatDate></td>
										<td>
										  <a
											class="button border-red"
											href="toupdatequestion.do?id=<c:out value="${judge.judgeId}"></c:out>&type=<c:out value="${judge.type}"></c:out>">
												<span class="icon-edit"
												style="font-size: 14px; color: #1e9fff;"> 修改</span>
										</a> &nbsp; <a class="button border-red" href="javascript:void(0)"
											onclick="deleteone2(<c:out value="${judge.judgeId}"></c:out>)">
												<span class="icon-remove-sign"
												style="font-size: 14px; color: #ff5722; padding-right: 10px;">
													删除</span>
										</a>
										</td>

									</tr>
						  </c:forEach>
						</c:if>
						<c:if test="${qb.key=='mulitList'}">
								<c:forEach items="${qb.value}" var="mulit" varStatus="mut">
									<tr>
										<c:if test="${mulit.getIspublic1() == 0}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" name="close" lay-skin="switch"
														id="${mulit.getChoiceId()}"
														placeholder="${mulit.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<c:if test="${mulit.getIspublic1() == 1}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" checked="" name="open"
														lay-skin="switch" id="${mulit.getChoiceId()}"
														placeholder="${mulit.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<td style="text-align:left"><div class="answer">${mut.index+1}、${mulit.choiceDesc1}</div>
											<div class="answer">A、${mulit.optionA1}</div>
											<div class="answer">B、${mulit.optionB1}</div>
											<div class="answer">C、${mulit.optionC1}</div>
											<div class="answer">D、${mulit.optionD1}</div>
											</td>
										<td>多选</td>
										<td>${mulit.grade.gradeName}</td>
										<td>${mulit.subject2.subjectName}</td>
										<td><c:out value="${mulit.teacher.teacherName}"></c:out></td>
										<td><c:out value="${mulit.getRightCount()}"></c:out><span>&nbsp;次</span></td>
										<td><c:out value="${mulit.getDegree1()}"></c:out> <span
											class="uls_showstars"> <span class="icon-star"
												style="cursor: pointer;"></span>
										</span></td>
										<td> <fmt:formatDate
												value="${mulit.createTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
										</td>
										<td>
									 <a class="button border-red"
											href="toupdatequestion.do?id=<c:out value="${mulit.choiceId}"></c:out>&type=<c:out value="${mulit.type}"></c:out>">
												<span class="icon-edit"
												style="font-size: 14px; color: #1e9fff;"> 修改</span>
										</a> &nbsp; <a class="button border-red" href="javascript:void(0)"
											onclick="deleteone1(<c:out value="${mulit.choiceId}"></c:out>)">
												<span class="icon-remove-sign"
												style="font-size: 14px; color: #ff5722; padding-right: 10px;">
													删除</span>
										</a>

										</td>
									</tr>
								</c:forEach>
							</c:if>	
							<c:if test="${qb.key=='singleList'}">
								<c:forEach items="${qb.value}" var="single" varStatus="sin">
									<tr class="">
										<c:if test="${single.getIspublic() == 0}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" name="close" lay-skin="switch"
														id="${single.getChoiceId()}"
														placeholder="${single.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<c:if test="${single.getIspublic() == 1}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" checked="" name="open"
														lay-skin="switch" id="${single.getChoiceId()}"
														placeholder="${single.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<td style="text-align:left"><div class="answer">${sin.index+1}、${single.choiceDesc}</div>
											   <div class="answer">A、${single.optionA}</div> 
											   <div class="answer">B、${single.optionB}</div>
											   <div class="answer">C、${single.optionC}</div>
											   <div class="answer">D、${single.optionD}</div></td>
										<td>单选</td>
										<td>${single.grade.gradeName}</td>
										<td>${single.subject2.subjectName}</td>
										<td><c:out value="${single.teacher.teacherName}"></c:out></td>									
										<td><c:out value="${single.getUseCount()}"></c:out><span>&nbsp;次</span></td>
										<td><c:out value="${single.getDegree()}"></c:out>
											 <span class="uls_showstars">
										  <span class="icon-star"
											style="cursor: pointer;"></span>
											</span></td>
										<td><fmt:formatDate value="${single.createTime}"
												pattern="yyyy-MM-dd"></fmt:formatDate></td>	
										<td>
											<a class="button border-red"
											href="toupdatequestion.do?id=<c:out value="${single.choiceId}"></c:out>&type=<c:out value="${single.type}"></c:out>">
												<span class="icon-edit"
												style="font-size: 14px; color: #1e9fff;"> 修改</span>
										</a> &nbsp; <a class="button border-red" href="javascript:void(0)"
											onclick="deleteone(<c:out value="${single.choiceId}"></c:out>)">
												<span class="icon-remove-sign"
												style="font-size: 14px; color: #ff5722;"> 删除</span>
										</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							
							<c:if test="${qb.key=='sqList'}">
								<c:forEach items="${qb.value}" var="single" varStatus="sin">
									<tr class="">
										<c:if test="${single.getIsPublic() == 0}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" name="close" lay-skin="switch"
														id="${single.subjectiveId}"
														placeholder="${single.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<c:if test="${single.getIsPublic() == 1}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" checked="" name="open"
														lay-skin="switch" id="${single.subjectiveId}"
														placeholder="${single.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<td style="text-align:left"><div class="answer">${sin.index+1}、${single.subjectiveTitle}</div>
											   
										<td>主观题</td>
										<td>${single.grade.gradeName}</td>
										<td>${single.subject2.subjectName}</td>
										<td><c:out value="${single.teacher.teacherName}"></c:out></td>									
										<td><c:out value="${single.usedCount}"></c:out><span>&nbsp;次</span></td>
										<td><c:out value="${single.questionDegree}"></c:out>
											 <span class="uls_showstars">
										  <span class="icon-star"
											style="cursor: pointer;"></span>
											</span></td>
										<td><fmt:formatDate value="${single.createTime}"
												pattern="yyyy-MM-dd"></fmt:formatDate></td>	
										<td>
											<a class="button border-red"
											href="moditySubjective.do?id=<c:out value="${single.subjectiveId}"></c:out>&type=<c:out value="${single.getType()}"></c:out>">
												<span class="icon-edit"
												style="font-size: 14px; color: #1e9fff;"> 修改</span>
										</a> &nbsp; <a class="button border-red" href="javascript:void(0)"
											onclick="deleteoneSubjective(<c:out value="${single.subjectiveId}"></c:out>)">
												<span class="icon-remove-sign"
												style="font-size: 14px; color: #ff5722;"> 删除</span>
										</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						<c:if test="${qb.key=='qfList'}">
								<c:forEach items="${qb.value}" var="single" varStatus="sin">
									<tr class="">
										<c:if test="${single.getIsPublic() == 0}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" name="close" lay-skin="switch"
														id="${single.fillId}"
														placeholder="${single.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<c:if test="${single.getIsPublic() == 1}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" checked="" name="open"
														lay-skin="switch" id="${single.fillId}"
														placeholder="${single.getType()}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<td style="text-align:left">
										<div class="answer">${sin.index+1}、${single.fillTitle}</div>
											    <div class="answer">${single.fillAnswer}</div> 
										<td>填空题</td>
										<td>${single.grade.gradeName}</td>
										<td>${single.subject2.subjectName}</td>
										<td><c:out value="${single.teacher.teacherName}"></c:out></td>									
										<td><c:out value="${single.usedCount}"></c:out><span>&nbsp;次</span></td>
										<td><c:out value="${single.questionDegree}"></c:out>
											 <span class="uls_showstars">
										  <span class="icon-star"
											style="cursor: pointer;"></span>
											</span></td>
										<td><fmt:formatDate value="${single.createTime}"
												pattern="yyyy-MM-dd"></fmt:formatDate></td>	
										<td>
											<a class="button border-red"
											href="modityFill.do?id=<c:out value="${single.fillId}"></c:out>&type=<c:out value="${single.getType()}"></c:out>">
												<span class="icon-edit"
												style="font-size: 14px; color: #1e9fff;"> 修改</span>
										</a> &nbsp; <a class="button border-red" href="javascript:void(0)"
											onclick="deleteoneFill(<c:out value="${single.fillId}"></c:out>)">
												<span class="icon-remove-sign"
												style="font-size: 14px; color: #ff5722;"> 删除</span>
										</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						<c:if test="${qb.key=='clozeList'}">
								<c:forEach items="${qb.value}" var="single" varStatus="sin">
									<tr class="">
										<c:if test="${single.getIsPublic() == 0}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" name="close" lay-skin="switch"
														id="${single.clozeId}"
														placeholder="${single.type}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<c:if test="${single.getIsPublic() == 1}">
											<td>
												<div class="layui-input-block" style="margin-left: 0px;">
													<input type="checkbox" checked="" name="open"
														lay-skin="switch" id="${single.clozeId}"
														placeholder="${single.type}" lay-filter="switchTest"
														lay-text="公|私">
												</div>
											</td>
										</c:if>
										<td style="text-align:left">
										<div class="answer">${sin.index+1}、${single.clozeContent}</div>
											    
										<td>完形填空</td>
										<td>${single.grade.gradeName}</td>
										<td>${single.subject2.subjectName}</td>
										<td><c:out value="${single.teacher.teacherName}"></c:out></td>									
										<td><c:out value="${single.usedCount}"></c:out><span>&nbsp;次</span></td>
										<td><c:out value="${single.questionDegree}"></c:out>
											 <span class="uls_showstars">
										  <span class="icon-star"
											style="cursor: pointer;"></span>
											</span></td>
										<td><fmt:formatDate value="${single.createTime}"
												pattern="yyyy-MM-dd"></fmt:formatDate></td>	
										<td>
											<a class="button border-red"
											href="updateCloze.do?clozeId=<c:out value="${single.clozeId}"></c:out>&type=<c:out value="${single.type}"></c:out>">
												<span class="icon-edit"
												style="font-size: 14px; color: #1e9fff;"> 修改</span>
										</a> &nbsp; <a class="button border-red" href="javascript:void(0)"
											onclick="deleteoneCloze(<c:out value="${single.clozeId}"></c:out>)">
												<span class="icon-remove-sign"
												style="font-size: 14px; color: #ff5722;"> 删除</span>
										</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
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
				<!-- 表格结束 -->
			</c:otherwise>
		</c:choose>
	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="UTF-8"></script>
<script type="text/javascript">
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');
							
						//监听指定开关
						form.on('switch(switchTest)', function(data){
// 						   layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
// 						      offset: '6px'
// 						   });
							var id = this.id;
							var type = this.placeholder;
							var ispublic=0;
							if(this.checked) {
								ispublic=1
							} else {
								ispublic=0;
							}
							$.ajax({
								url:'updatequestionbyispublic.do',
								type:'post',
								dataType:'json',
								data:{isPublic:ispublic,id:id,type:type},
								success:function(res) {
									if(res.code==0) {
										layer.msg("更新成功!");
										setTimeout(function(){
											window.location.reload();
										},1000);
									} else {
										layer.msg(res.msg);
										this.checked = !this.checked;
										setTimeout(function(){
											window.location.reload();
										},3000);
									}
								}
							})
						});
						
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
							$('#startTime')
									.val(new Date($('#startTime').val()))
							return true;
						});
					});

	function deleteone(uid) {
		layer.confirm('您确认删除该条题库么?', {
			btn : [ '确认', '取消' ]
		}, function() {
			$.post('${ctx}/questionBank/qscDelete.do?', {
				'choiceId' : uid
			}, function(data) {
				 if (data.code == 0) {
						layer.msg("删除成功!");
						setTimeout(function(){
// 							window.location.href = "myquestionbank.do?handoutType=1";
							window.location.reload();
						},2000);
					}else{
						layer.msg(data.msg,{time:2000});
			    		return;
					}
			});
		}, function() {
			layer.closeAll();
		});
	}
	function deleteone1(uid) {
		layer.confirm('您确认删除该条试题么?', {
			btn : [ '确认', '取消' ]
		}, function() {
			$.post('${ctx}/questionBank/deleteQmc.do?', {
				'choiceId' : uid
			}, function(data) {
				 if (data.code == 0) {
						layer.msg("删除成功!");
						setTimeout(function(){
// 							window.location.href = "myquestionbank.do?handoutType=2";
							window.location.reload();
						},2000);
					}else{
						layer.msg(data.msg,{time:3000});
			    		return;
					}
			});
		}, function() {
			layer.closeAll();
		});
	}
	
	function deleteone2(uid) {
		layer.confirm('您确认删除该条题库么?', {
			btn : [ '确认', '取消' ]
		}, function() {
			$.post('${ctx}/questionBank/deleteJudge.do?', {
				'judgeId' : uid
			}, function(data) {
				 if (data.code == 0) {
						layer.msg("删除成功!");
						setTimeout(function(){
							window.location.reload();
// 							window.location.href = "myquestionbank.do?handoutType=3";
						},2000);
					}else{
						layer.msg(data.msg,{time:3000});
			    		return;
					}
			});
		}, function() {
			layer.closeAll();
		});
	}
	function deleteone3(uid) {
		layer.confirm('您确认删除该条题库么?', {
			btn : [ '确认', '取消' ]
		}, function() {
			$.post('${ctx}/questionBank/deleteRead.do?', {
				'readId' : uid
			}, function(data) {
				 if (data.code == 0) {
						layer.msg("删除成功!");
						setTimeout(function(){
// 							window.location.href = "myquestionbank.do?handoutType=4";
							window.location.reload();
						},2000);
					}else{
						layer.msg(data.msg,{time:3000});
			    		return;
					}
			});
		}, function() {
			layer.closeAll();
		});
	}
	
	function deleteoneSubjective(uid){
		layer.confirm('您确认删除该条题库么?', {
			btn : [ '确认', '取消' ]
		}, function() {
			$.post('${ctx}/questionBank/deleteoneSubjective.do?', {'subjectiveId' : uid
			}, function(data) {
				 if (data.code == 0) {
						layer.msg("删除成功!");
						setTimeout(function(){
// 							window.location.href = "myquestionbank.do?handoutType=5";
							window.location.reload();
						},2000);
					}else{
						layer.msg(data.msg,{time:3000});
			    		return;
					}
			});
		}, function() {
			layer.closeAll();
		});
	}
	
	function deleteoneFill(uid){
		layer.confirm('您确认删除该条题库么?', {
			btn : [ '确认', '取消' ]
		}, function() {
			$.post('${ctx}/questionBank/deleteoneFill.do?', {'fillId' : uid
			}, function(data) {
				 if (data.code == 0) {
						layer.msg("删除成功!");
						setTimeout(function(){
// 							window.location.href = "myquestionbank.do?handoutType=6";
							window.location.reload();
						},2000);
					}else{
						layer.msg(data.msg,{time:3000});
			    		return;
					}
			});
		}, function() {
			layer.closeAll();
		});
	}
	
	function deleteoneCloze(cid){
		layer.confirm('您确认删除该条题库么?', {
			btn : [ '确认', '取消' ]
		}, function() {
			$.post('${ctx}/questionBank/deleteoneCloze.do?', {'clozeId' : cid
			}, function(data) {
				 if (data.code == 0) {
						layer.msg("删除成功!");
						setTimeout(function(){
// 							window.location.href = "myquestionbank.do?handoutType=5";
							window.location.reload();
						},2000);
					}else{
						layer.msg(data.msg,{time:3000});
			    		return;
					}
			});
		}, function() {
			layer.closeAll();
		});
	}
	
	function goPageAction(page){
		window.location.href="${ctx}/questionBank/myquestionbank.do?pageNo="+page+"&handoutType=${handoutType}&subject=${subject}&questionDegree=${questionDegree}&isPublic=${isPublic}&gradeId=${gradeId}";
	}
	function goMyPage(page,totalPage){
		if(page > 0 && page <= totalPage){
			window.location.href="${ctx}/questionBank/myquestionbank.do?pageNo="+page+"&handoutType=${handoutType}&subject=${subject}&questionDegree=${questionDegree}&isPublic=${isPublic}";
		} else{
			alert('请输入有效的整数!');
		}
	}

</script>
</html>