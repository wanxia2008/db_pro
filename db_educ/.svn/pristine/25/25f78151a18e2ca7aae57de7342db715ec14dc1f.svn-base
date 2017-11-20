<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改试题</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css">
<style type="text/css">
body {

}

#radioselect {
	display: block;
}

#multiselect {
	display: none
}

#judgeselect {
	display: none
}

#readselect {
	display: none
}

#confirm2 {
	display: none
}

.head_1 {
	position: fixed;
	top: 0px;
	z-index: 10000
}

.modal {
	top: 100px;
}

.layui-laypage a, .layui-laypage span {
	font-size: 15px;
}

::-webkit-input-placeholder { /* WebKit browsers */
	color: #999;
}

:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
	color: #999;
}

::-moz-placeholder { /* Mozilla Firefox 19+ */
	color: #999;
}

:-ms-input-placeholder { /* Internet Explorer 10+ */
	color: #999;
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
	background: #e33
}
.layui-textarea{padding:0 !important}
.layui-input, .layui-textarea{padding-left:0px;}
.icon-star,.icon-star-empty{color:#333 !important}
.layui-table td, .layui-table th {
    padding:0 !important;
    min-height: 30px;
    line-height: 30px;
    border: 1px solid #e2e2e2;
    font-size: 14px;
}
@media (min-width: 992px){
	.col-md-3 {
		width: auto !important;
	}
}
 @media screen and (max-width: 1366px) {
	
  .bt10{margin-top: 10px;}
 }
 .hline{height:30px;line-height:40px;}
 .tagclass{padding:5px 10px;border:1px solid #e6e6e6;border-radius:4px}
</style>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././ueditor/ueditor.config.js"></script>
<script type="text/javascript" src=".././ueditor/ueditor.all.js"></script>
<script type="text/javascript" src=".././ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script type="text/javascript" src=".././ueditor/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript" src=".././ueditor/kityformula-plugin/defaultFilterFix.js"></script>
</head>
<body style="padding-bottom:100px;">
	<form class="layui-form" id="myform" method="post" action="">
		<div class="panels_head">
			<span>修改题目</span>
			<span style="text-align:center;color: red;right: 50%;background:none;font-size: 18px;">${errorMsg}</span>
			<button class="layui-btn layui-btn-normal" id="confirm1" lay-submit=""
				lay-filter="demo1"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px;">确认</button>
			<button type="button" class="layui-btn layui-btn-normal" id="confirm2"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">确认</button>
			<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/questionBank/myquestionbank.do?handoutType=<c:out value="${type}"></c:out>'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
		</div>
		<input hidden="" id="update_type" value="${type}" />
		<c:if test="${type==1}">
			<input hidden="" name="choiceId" value="${single.choiceId}" />
			<div class="layui-form-item" style="margin-top:20px;">
			<div class="layui-inline">
			  <label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="knowledge" id="knowledge" lay-verify="knowledge">
							<option value="">请选择</option>
							<c:forEach items="${gList}" var="g">
								<c:choose>
									<c:when test="${single.knowledge==g.gradeId}">
										<option selected="selected"
											value="${g.gradeId}" data-name="${g.gradeName}">${g.gradeName}</option>
									</c:when>
									<c:otherwise>
										<option value="${g.gradeId}" data-name="${g.gradeName}">${g.gradeName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>科目</label>
				<div class="layui-input-inline" style="width: 150px;">
						<select name="subject" id="subject" lay-verify="subject" lay-filter="subjectabc">
							<option value="">请选择</option>
							<c:forEach items="${sList}" var="s">
								<c:choose>
									<c:when test="${single.subject==s.subjectId}">
										<option selected="selected" value="${s.subjectId}" id="dan-${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
									</c:when>
									<c:otherwise>
										<option value="${s.subjectId}" id="dan-${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">知识范围</label>
				<div class="layui-input-inline"
					style="width: 120px;">
						<select name="knowledgeBegin" id="knowledgeBegin"
							lay-verify="knowledgeBegin" lay-filter="select_knowstart">
							<option value="">请选择</option>
							<c:forEach items="${lessonList}" var="lesson">
								<c:choose>
									<c:when test="${single.knowledgeBegin==lesson.lessonId}">
										<option selected="selected" value="${lesson.lessonId}">${lesson.lessonName}</option>
									</c:when>
									<c:otherwise>
										<option value="${lesson.lessonId}">${lesson.lessonName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
			</div>
			<div class="layui-inline">
<!-- 				<div class="layui-form-mid">-</div> -->
<!-- 				<div class="layui-input-inline" -->
<!-- 					style="width: 120px;"> -->
<!-- 					<select name="knowledgeEnd" id="knowledgeEnd" -->
<!-- 						lay-verify="knowledgeEnd" lay-filter="select_knowend"> -->
<!-- 						<option value="">请选择</option> -->
<%-- 						<c:forEach items="${lessonList}" var="lesson"> --%>
<%-- 						<c:choose> --%>
<%-- 						<c:when test="${single.knowledgeEnd==lesson.lessonId}"> --%>
<%-- 						  <option selected="selected" value="${lesson.lessonId}">${lesson.lessonName}</option> --%>
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 						    <option value="${lesson.lessonId}">${lesson.lessonName}</option> --%>
<%-- 						</c:otherwise> --%>
<%-- 						</c:choose> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
			</div>
		</div>

			<div class="panels_head">
			<span>题目内容</span>
			</div>
			<div class="layui-form-item" style="margin-top:20px;">
					<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>单选题目</label>
					<div class="layui-input-inline" style="width:400px;">
						
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="请输入单选题目" class="layui-textarea" name="choiceDesc"
							id="choiceDesc" lay-verify="choiceDesc1">${single.choiceDesc}</textarea>
					</div>
				</div>
				
			<div class="layui-form-item" style="margin: 10px 0">
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>A</label>
					<div class="layui-input-inline" style="width: 300px;">
						
						<textarea style="width: 300px; height: 100px; border-radius: 5px;"
							placeholder="请输入A选项的答案" class="layui-textarea" name="OptionA"
							id="OptionA1" lay-verify="OptionA1">${single.getOptionA()}</textarea>
					</div>
				</div>
				<div class="layui-inline" style="margin-left:10px;">
					<label class="layui-form-label" style="font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>B</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 100px; border-radius: 5px;"
							placeholder="请输入B选项的答案" class="layui-textarea" name="OptionB"
							id="OptionB1" lay-verify="OptionB1">${single.getOptionB()}</textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0;font-size: 14px;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>C</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 100px; border-radius: 5px;"
							placeholder="请输入C选项的答案" class="layui-textarea" name="OptionC"
							id="OptionC1" lay-verify="OptionC1">${single.getOptionC()}</textarea>
					</div>
				</div>
				<div class="layui-inline" style="margin-left:10px;">
					<label class="layui-form-label" style="font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>D</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 100px; border-radius: 5px;"
							placeholder="请输入D选项的答案" class="layui-textarea" name="OptionD"
							id="OptionD1" lay-verify="OptionD1">${single.getOptionD()}</textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>正确答案</label>
					<div class="layui-input-block" style="width:300px">
						<c:if test="${single.answer=='A'}">
							<input type="radio" name="answer" lay-verify="answer1" value="A"
								title="A" checked="">
							<input type="radio" name="answer" lay-verify="answer1" value="B"
								title="B">
							<input type="radio" name="answer" lay-verify="answer1" value="C"
								title="C">
							<input type="radio" name="answer" lay-verify="answer1" value="D"
								title="D" placeholder="请选项正确答案">
						</c:if>
						<c:if test="${single.answer=='B'}">
							<input type="radio" name="answer" lay-verify="answer1" value="A"
								title="A">
							<input type="radio" name="answer" lay-verify="answer1" value="B"
								title="B" checked="">
							<input type="radio" name="answer" lay-verify="answer1" value="C"
								title="C">
							<input type="radio" name="answer" lay-verify="answer1" value="D"
								title="D" placeholder="请选项正确答案">
						</c:if>
						<c:if test="${single.answer=='C'}">
							<input type="radio" name="answer" lay-verify="answer1" value="A"
								title="A" checked="">
							<input type="radio" name="answer" lay-verify="answer1" value="B"
								title="B">
							<input type="radio" name="answer" lay-verify="answer1" value="C"
								title="C" checked="">
							<input type="radio" name="answer" lay-verify="answer1" value="D"
								title="D" placeholder="请选项正确答案">
						</c:if>
						<c:if test="${single.answer=='D'}">
							<input type="radio" name="answer" lay-verify="answer1" value="A"
								title="A" checked="">
							<input type="radio" name="answer" lay-verify="answer1" value="B"
								title="B">
							<input type="radio" name="answer" lay-verify="answer1" value="C"
								title="C">
							<input type="radio" name="answer" lay-verify="answer1" value="D"
								title="D" checked="" placeholder="请选项正确答案">
						</c:if>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width:110px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>分值</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="score" id="score1"
							lay-verify="score11" autocomplete="off" placeholder="请输入分值"
							class="layui-input" value="${single.score}"
							onkeyup="score_input(this,this.value)">
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;">答案释义</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width:710px; height: 200px; border-radius: 5px;"
							placeholder="该题。。。" class="layui-textarea" name="answerDesc"
							id="answerDesc1" lay-verify="answerDesc12">${single.answerDesc}</textarea>
					</div>
				</div>
			</div>

			<div class="layui-form-item" style="margin: 10px 0;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 120px;">
						<select name="source" id="source1" lay-verify="source1">
							<c:forEach items="${qsList}" var="qs">
							<c:choose>
							   <c:when test="${single.source==qs.sourceId}">
							   <option selected="selected" value="${qs.sourceId}">${qs.sourceName}</option>
							   </c:when>
							   <c:otherwise>
							   <option value="${qs.sourceId}">${qs.sourceName}</option>
							   </c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>难度</label>
					<div class="layui-input-inline" style="width: 150px;">
						<c:if test="${single.degree==1}">
							<ul class="uls_showstars" style="margin-top:6px">
								<li class="icon-star" id="1" style="cursor: pointer;"></li>
								<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
								<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
								<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
								<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${single.degree==2}">
							<ul class="uls_showstars" style="margin-top:6px">
								<li class="icon-star" id="1" style="cursor: pointer;"></li>
								<li class="icon-star" id="2" style="cursor: pointer;"></li>
								<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
								<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
								<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${single.degree==3}">
							<ul class="uls_showstars" style="margin-top:6px">
								<li class="icon-star" id="1" style="cursor: pointer;"></li>
								<li class="icon-star" id="2" style="cursor: pointer;"></li>
								<li class="icon-star" id="3" style="cursor: pointer;"></li>
								<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
								<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${single.degree==4}">
							<ul class="uls_showstars" style="margin-top:6px">
								<li class="icon-star" id="1" style="cursor: pointer;"></li>
								<li class="icon-star" id="2" style="cursor: pointer;"></li>
								<li class="icon-star" id="3" style="cursor: pointer;"></li>
								<li class="icon-star" id="4" style="cursor: pointer;"></li>
								<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${single.degree==5}">
							<ul class="uls_showstars" style="margin-top:6px">
								<li class="icon-star" id="1" style="cursor: pointer;"></li>
								<li class="icon-star" id="2" style="cursor: pointer;"></li>
								<li class="icon-star" id="3" style="cursor: pointer;"></li>
								<li class="icon-star" id="4" style="cursor: pointer;"></li>
								<li class="icon-star" id="5" style="cursor: pointer;"></li>
							</ul>
						</c:if>
					</div>
					<input type="number" hidden="" name="degree" id="degree"
						value="${single.degree}" lay-verify="degree1" />
				</div>
			</div>
			
			<div class="layui-form-item" style="margin: 10px 0">
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;">标签</label>
					<table style="width: 40%; position: absolute; left: 120px; font-size: 14px;">					
						<tbody id="tbody2">
							<c:forEach items="${single.getQuestionTags()}" var="tags">
								<tr id="${tags.tagId}" class="tag_tr1" style="cursor: pointer;">
									<td class="hline"><span class="tagclass"><em class='icon-remove-sign' style='color:#ff0000;margin-right:5px'></em>${tags.tagName}</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input hidden="" name="tag" lay-verify="tag1" id="tag1"
						value="${single.tag}" />
					<button type="button" class="btn btn-primary" data-toggle="modal"
						onclick="openModal()"
						style="margin-top: 10px; margin-left: 160px;font-size: 14px;border:none;border-radius:4px;background-color:#008bca !important">+标签</button>
				</div>
			</div>
			</div>
		</c:if>
		<c:if test="${type==2}">
			<input hidden="" name="choiceId" value="${mulit.choiceId}" />
			<div class="layui-form-item" style="margin-top:20px;">
			
			<div class="layui-inline">
			<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="knowledge" id="knowledge" lay-verify="knowledge">
							<option value="">请选择</option>
							<c:forEach items="${gList}" var="g">
								<c:choose>
									<c:when test="${mulit.knowledge==g.gradeId}">
										<option selected="selected"
											value="${g.gradeId}" data-name="${g.gradeName}">${g.gradeName}</option>
									</c:when>
									<c:otherwise>
										<option value="${g.gradeId}" data-name="${g.gradeName}">${g.gradeName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>科目</label>
				<div class="layui-input-inline" style="width: 150px;">
						<select name="subject" id="subject" lay-verify="subject" lay-filter="subjectabc">
							<option value="">请选择</option>
							<c:forEach items="${sList}" var="s">
								<c:choose>
									<c:when test="${mulit.subject==s.subjectId}">
										<option selected="selected" value="${s.subjectId}" id="duo-${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
									</c:when>
									<c:otherwise>
										<option value="${s.subjectId}" id="duo-${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">知识范围</label>
				<div class="layui-input-inline"
					style="width: 120px;">
						<select name="knowledgeBegin" id="knowledgeBegin"
							lay-verify="knowledgeBegin" lay-filter="select_knowstart">
							<option value="">请选择</option>
							<c:forEach items="${lessonList}" var="lesson">
								<c:choose>
									<c:when test="${mulit.knowledgeBegin==lesson.lessonId}">
										<option selected="selected" value="${lesson.lessonId}">${lesson.lessonName}</option>
									</c:when>
									<c:otherwise>
										<option value="${lesson.lessonId}">${lesson.lessonName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
			</div>
			<div class="layui-inline">
<!-- 				<div class="layui-form-mid">-</div> -->
<!-- 				<div class="layui-input-inline" -->
<!-- 					style="width: 120px;"> -->
<!-- 					<select name="knowledgeEnd" id="knowledgeEnd" -->
<!-- 						lay-verify="knowledgeEnd" lay-filter="select_knowend"> -->
<!-- 						<option value="">请选择</option> -->
<%-- 						<c:forEach items="${lessonList}" var="lesson"> --%>
<%-- 						<c:choose> --%>
<%-- 						<c:when test="${mulit.knowledgeEnd==lesson.lessonId}"> --%>
<%-- 						  <option selected="selected" value="${lesson.lessonId}">${lesson.lessonName}</option> --%>
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 						    <option value="${lesson.lessonId}">${lesson.lessonName}</option> --%>
<%-- 						</c:otherwise> --%>
<%-- 						</c:choose> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
			</div>
		</div>

			<div class="panels_head">
			<span>题目内容</span>
			</div>
			<div class="layui-form-item" style="margin-top: 20px">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>多选题题目</label>
					<div class="layui-input-inline" style="width: 400px;">
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="请输入题目" class="layui-textarea" name="choiceDesc1"
							id="choiceDesc2" lay-verify="choiceDesc2" autocomplete="off">${mulit.choiceDesc1}</textarea>
					</div>
				</div>
				
			</div>
			<div class="layui-form-item" style="margin: 10px 0px;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>A</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 100px; border-radius: 5px;"
							placeholder="请输入A答案" class="layui-textarea" name="OptionA1"
							id="OptionA2" lay-verify="OptionA2" autocomplete="off">${mulit.getOptionA1()}</textarea>
					</div>
				</div>
				<div class="layui-inline" style="margin-left:10px">
					<label class="layui-form-label" style="font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>B</label>
					<div class="layui-input-inline" style="width: 300px;">
						
						<textarea style="width: 300px; height: 100px; border-radius: 5px;"
							placeholder="请输入B答案" class="layui-textarea" name="OptionB1"
							id="OptionB2" lay-verify="OptionB2" autocomplete="off">${mulit.getOptionB1()}</textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0px;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>C</label>
					<div class="layui-input-inline" style="width: 300px;">
						
						<textarea style="width: 300px; height: 100px; border-radius: 5px;"
							placeholder="请输入C答案" class="layui-textarea" name="OptionC1"
							id="OptionC2" lay-verify="OptionC2" autocomplete="off">${mulit.getOptionC1()}</textarea>
					</div>
				</div>
				<div class="layui-inline" style="margin-left:10px">
					<label class="layui-form-label" style="font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>D</label>
					<div class="layui-input-inline" style="width: 300px;">
						
						<textarea style="width: 300px; height: 100px; border-radius: 5px;"
							placeholder="请输入D答案" class="layui-textarea" name="OptionD1"
							id="OptionD2" lay-verify="OptionD2" autocomplete="off">${mulit.getOptionD1()}</textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0px;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>正确答案</label>
					<div class="layui-input-block" style="width:300px;">
				   	<c:choose>
					<c:when test="${fn:contains(mulit.getAnswer1(),'A')}">
					<input type="checkbox" name="answer1" lay-filter="answer2" 
 							lay-skin="primary" title="A" value="A" checked> 
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="answer1" lay-filter="answer2" 
 							lay-skin="primary" title="A" value="A"> 
					</c:otherwise>
					</c:choose>
									<c:choose>
					
					<c:when test="${fn:contains(mulit.getAnswer1(),'B')}">
									<input type="checkbox" name="answer1" lay-filter="answer2" 
 							lay-skin="primary" title="B" value="B" checked> 
					</c:when>
					<c:otherwise>
					<input type="checkbox" name="answer1" lay-filter="answer2" 
 							lay-skin="primary" title="B" value="B"> 
					</c:otherwise>
					</c:choose>
									<c:choose>
					
					<c:when test="${fn:contains(mulit.getAnswer1(),'C')}">
									<input type="checkbox" name="answer1" lay-filter="answer2" 
 							lay-skin="primary" title="C" value="C" checked> 
					</c:when>
					<c:otherwise>
					<input type="checkbox" name="answer1" lay-filter="answer2" 
 							lay-skin="primary" title="C" value="C"> 
					</c:otherwise>
					</c:choose>
									<c:choose>
					
					<c:when test="${fn:contains(mulit.getAnswer1(),'D')}">
									<input type="checkbox" name="answer1" lay-filter="answer2" 
 							lay-skin="primary" title="D" value="D" checked> 
					</c:when>
					<c:otherwise>
					<input type="checkbox" name="answer1" lay-filter="answer2" 
 							lay-skin="primary" title="D" value="D"> 
					</c:otherwise>
					</c:choose>
					
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width:110px;font-size: 14px;">分值</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="score1" id="score2" lay-verify="score22"
							autocomplete="off" placeholder="请输入分值" value="${mulit.score1}"
							class="layui-input" onkeyup="score_input(this,this.value)">
					</div>
				</div>
				<input type="text" hidden="" name="answer1" id="answer_id" value="" />
			</div>
			<div class="layui-form-item" style="margin: 10px 0px;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;">题目释义</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="该题。。。" class="layui-textarea" name="answerDesc1"
							lay-verify="answerDesc222" id="answerDesc2">${mulit.answerDesc1}</textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0px;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 150px;">
						<select name="source1" id="source2" lay-verify="source2">
							<c:forEach items="${qsList}" var="qs">
								<c:choose>
							   <c:when test="${mulit.source1==qs.sourceId}">
							   <option selected="selected" value="${qs.sourceId}">${qs.sourceName}</option>
							   </c:when>
							   <c:otherwise>
							   <option value="${qs.sourceId}">${qs.sourceName}</option>
							   </c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>难度</label>
					<div class="layui-input-inline" style="width: 150px;">
						<c:if test="${mulit.degree1==1}">
							<ul class="uls_showstars1" style="margin-top:6px">
								<li class="icon-star uls_showstars1_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars1_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars1_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars1_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars1_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${mulit.degree1==2}">
							<ul class="uls_showstars1" style="margin-top:6px">
								<li class="icon-star uls_showstars1_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars1_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars1_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars1_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars1_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${mulit.degree1==3}">
							<ul class="uls_showstars1" style="margin-top:6px">
								<li class="icon-star uls_showstars1_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars1_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars1_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars1_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars1_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${mulit.degree1==4}">
							<ul class="uls_showstars1" style="margin-top:6px">
								<li class="icon-star uls_showstars1_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars1_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars1_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars1_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars1_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${mulit.degree1==5}">
							<ul class="uls_showstars1" style="margin-top:6px">
								<li class="icon-star uls_showstars1_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars1_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars1_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars1_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars1_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
					</div>
					<input type="number" hidden="" name="degree1" id="degree1"
						lay-verify="degree2" value="${mulit.degree1}" />
				</div>
			</div>
			<div class="layui-form-item"  style="margin: 10px 0;padding-bottom:50px;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>标签</label>
					<table style="width: 40%; position: absolute; left: 140px;">						
						<tbody id="tbody3">
							<c:forEach items="${mulit.getQuestionTags()}" var="tags">
								<tr id="${tags.tagId}" class="tag_tr2" style="cursor: pointer;">
									<td class="hline"><span class="tagclass"><em class='icon-remove-sign' style='color:#ff0000;margin-right:5px'></em>${tags.tagName}</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input hidden="" name="tag1" lay-verify="tag2" id="tag2"
						value="${mulit.tag1}" />
					<button type="button" class="btn btn-primary" data-toggle="modal"
						onclick="openModal()"
						style="margin-top: 10px;font-size: 14px; margin-left: 180px;border:none;border-radius:4px;background-color:#008bca !important">+标签</button>
				</div>
			</div>
		</c:if>
		<c:if test="${type==3}">
			<input hidden="" name="judgeId" value="${judge.judgeId}">
			<div class="layui-form-item" style="margin-top:20px;">
			
			<div class="layui-inline">
			<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="knowledge" id="knowledge" lay-verify="knowledge">
							<option value="">请选择</option>
							<c:forEach items="${gList}" var="g">
								<c:choose>
									<c:when test="${judge.knowledge==g.gradeId}">
										<option selected="selected"
											value="${g.gradeId}" data-name="${g.gradeName}">${g.gradeName}</option>
									</c:when>
									<c:otherwise>
										<option value="${g.gradeId}" data-name="${g.gradeName}">${g.gradeName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>科目</label>
				<div class="layui-input-inline" style="width: 150px;">
						<select name="subject" id="subject" lay-verify="subject" lay-filter="subjectabc">
							<option value="">请选择</option>
							<c:forEach items="${sList}" var="s">
								<c:choose>
									<c:when test="${judge.subject==s.subjectId}">
										<option selected="selected" value="${s.subjectId}" id="pan-${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
									</c:when>
									<c:otherwise>
										<option value="${s.subjectId}" id="pan-${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">知识范围</label>
				<div class="layui-input-inline"
					style="width: 120px;">
						<select name="knowledgeBegin" id="knowledgeBegin"
							lay-verify="knowledgeBegin" lay-filter="select_knowstart">
							<option value="">请选择</option>
							<c:forEach items="${lessonList}" var="lesson">
								<c:choose>
									<c:when test="${judge.knowledgeBegin==lesson.lessonId}">
										<option selected="selected" value="${lesson.lessonId}">${lesson.lessonName}</option>
									</c:when>
									<c:otherwise>
										<option value="${lesson.lessonId}">${lesson.lessonName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
			</div>
			<div class="layui-inline">
				
<!-- 				<div class="layui-form-mid">-</div> -->
<!-- 				<div class="layui-input-inline" -->
<!-- 					style="width: 120px;"> -->
<!-- 					<select name="knowledgeEnd" id="knowledgeEnd" -->
<!-- 						lay-verify="knowledgeEnd" lay-filter="select_knowend"> -->
<!-- 						<option value="">请选择</option> -->
<%-- 						<c:forEach items="${lessonList}" var="lesson"> --%>
<%-- 						<c:choose> --%>
<%-- 						<c:when test="${judge.knowledgeEnd==lesson.lessonId}"> --%>
<%-- 						  <option selected="selected" value="${lesson.lessonId}">${lesson.lessonName}</option> --%>
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 						    <option value="${lesson.lessonId}">${lesson.lessonName}</option> --%>
<%-- 						</c:otherwise> --%>
<%-- 						</c:choose> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
			</div>
		</div>

			<div class="panels_head">
			<span>题目内容</span>
			</div>
			<div class="layui-form-item" style="margin-top:20px">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>判断题题目</label>
					<div class="layui-input-inline" style="width: 400px;">
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="请输入题目" class="layui-textarea" name="judgeDesc"
							id="judgeDesc" lay-verify="judgeDesc" autocomplete="off">${judge.judgeDesc}</textarea>
					</div>
				</div>
				
				
			</div>
			<div class="layui-form-item" style="margin: 10px 0">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>正确答案</label>
					<div class="layui-input-block" style="width:300px;">
						<c:if test="${judge.answer2==1}">
							<input type="radio" name="answer2" lay-verify="answer3" value="1"
								title="对" checked="">
							<input type="radio" name="answer2" lay-verify="answer3" value="0"
								title="错">
						</c:if>
						<c:if test="${judge.answer2==0}">
							<input type="radio" name="answer2" lay-verify="answer3" value="1"
								title="对">
							<input type="radio" name="answer2" lay-verify="answer3" value="0"
								title="错" checked="">
						</c:if>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width:110px;font-size: 14px;">分值</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="number" min="0" max="100" name="score2" id="score3" lay-verify="score33"
							autocomplete="off" placeholder="请输入分值" value="${judge.score2}"
							class="layui-input" >
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;">题目释义</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width:710px; height: 200px; border-radius: 5px;"
							placeholder="该题。。。" class="layui-textarea" name="answerDesc2"
							lay-verify="answerDesc33" id="answerDesc3">${judge.answerDesc2}</textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 120px;">
						<select name="source2" id="source3" lay-verify="source3">
							<c:forEach items="${qsList}" var="qs">
								<c:choose>
							   <c:when test="${judge.source2==qs.sourceId}">
							   <option selected="selected" value="${qs.sourceId}">${qs.sourceName}</option>
							   </c:when>
							   <c:otherwise>
							   <option value="${qs.sourceId}">${qs.sourceName}</option>
							   </c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width:100px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>难度</label>
					<div class="layui-input-inline" style="width: 150px;">
						<c:if test="${judge.degree2==1}">
							<ul class="uls_showstars2" style="margin-top:6px">
								<li class="icon-star uls_showstars2_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars2_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars2_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars2_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars2_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${judge.degree2==2}">
							<ul class="uls_showstars2" style="margin-top:6px">
								<li class="icon-star uls_showstars2_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars2_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars2_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars2_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars2_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${judge.degree2==3}">
							<ul class="uls_showstars2" style="margin-top:6px">
								<li class="icon-star uls_showstars2_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars2_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars2_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars2_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars2_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${judge.degree2==4}">
							<ul class="uls_showstars2" style="margin-top:6px">
								<li class="icon-star uls_showstars2_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars2_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars2_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars2_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars2_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${judge.degree2==5}">
							<ul class="uls_showstars2" style="margin-top:6px">
								<li class="icon-star uls_showstars2_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars2_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars2_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars2_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars2_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
					</div>
					<input type="number" hidden="" name="degree2" id="degree2"
						lay-verify="degree3" value="${judge.degree2}" />
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0">
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>标签</label>
					<table style="width: 40%; position: absolute; left: 120px; font-size: 14px;">						
						<tbody id="tbody4">
							<c:forEach items="${judge.getQuestionTags()}" var="tags">
								<tr id="${tags.tagId}" class="tag_tr3" style="cursor: pointer;">
									<td class="hline"><span class="tagclass"><em class='icon-remove-sign' style='color:#ff0000;margin-right:5px'></em>${tags.tagName}</span></td>									
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input hidden="" name="tag2" lay-verify="tag3" id="tag3"
						value="${judge.tag2}" />
					<button type="button" class="btn btn-primary" data-toggle="modal"
						onclick="openModal()"
						style="margin-top: 10px;font-size: 14px; margin-left: 160px;border:none;border-radius:4px;background-color:#008bca !important">+标签</button>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; margin-left: 0px;font-size: 14px;">是否公有:</label>
					<div class="layui-input-inline"
						style="margin-top: 5px; margin-left:0;">
						<c:if test="${judge.ispublic2==1}">公有</c:if>
						<c:if test="${judge.ispublic2==0}">私有</c:if>
					</div>
				</div>
			</div>
		</c:if>
		
		<!--模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:90%;">
				<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel"><span id="chooseGraSub"></span>标签</h4>
			      </div>
					<div class="modal-body">
			               <div class="layui-form-item">
								<div class="layui-inline">
									<div class="layui-input-inline" style="width:200px;">
										<input type="text"  id="input_tag"
											autocomplete="off" placeholder="请输入要查找的标签，如there" class="layui-input">
									</div>
									<button type="button" class="layui-btn layui-btn-normal searchbtn" id="select_tag">查找</button>									
								</div>  
						   </div>
						   <!-- 标签内容开始 -->
						   <div class="sign-content">
						      <div class="row" id="tag_list" style="text-align:center">
								  <!-- 标签内容 -->				  
							  </div>
							     <div class="row">								  
								  <div class="col-md-12" id="pages" style="text-align:center"></div>							  
							  </div>
						   </div> 
						   <!-- 标签内容结束 -->
						   <!-- 创建标签 开始-->
						   <div class="layui-form-item" style="margin-top:10px;padding:20px;border:1px solid #e2e2e2">
								<div class="layui-inline">
								<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>创建新标签</label>
									<div class="layui-input-inline" style="width:200px;">
										<input type="text"  id="create_input_tag"
											autocomplete="off" placeholder="请输入标签名称" class="layui-input">
									</div>
									<button type="button" class="layui-btn layui-btn-normal searchbtn bt10" id="create_tag">创建</button>									
								</div>  
						   </div>
						   <!-- 创建标签结束 -->
					</div>
				</div>
			</div>
		</div>
		<script type="text/html" id="tag_template">
			{{each objects as value i}}		
             <div class="col-md-3 tagName"  id="{{value.tagId}}" data-name="{{value.tagName}}" style="cursor:pointer;padding:10px"><span style="padding:6px 18px;border:1px solid #e2e2e2;border-radius:4px;" id="checked-{{value.tagId}}">{{value.tagName}}</span></div>
			{{/each}}
		</script>
		<script type="text/html" id="read_template">
		{{each objects as value i}}
		<div class="layui-form-item" style="margin-top:10px;border-top:1px solid #35b998;padding-top:10px;">
		
	</div>
	<div class="layui-form-item" style="margin-left: 20px;">
		<div class="layui-inline">
			<input type="text" class="form-control" name="Optiontitle{{i}}" id="Optiontitle{{i}}" placeholder="请输入题目"
				style="width: 600px;">
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">A</label>
			<div class="layui-input-inline" style="width: 200px;">
				<input type="text" id="OptionA_read{{i}}"
					autocomplete="off" placeholder="请输入选项" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">B</label>
			<div class="layui-input-inline" style="width: m00px;">
				<input type="text" id="OptionB_read{{i}}"
					autocomplete="off" placeholder="请输入选项" class="layui-input">
			</div>
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">C</label>
			<div class="layui-input-inline" style="width: 200px;">
				<input type="text" id="OptionC_read{{i}}"
					autocomplete="off" placeholder="请输入选项" class="layui-input">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">D</label>
			<div class="layui-input-inline" style="width: 200px;">
				<input type="text" id="OptionD_read{{i}}"
					autocomplete="off" placeholder="请输入选项" class="layui-input">
			</div>
		</div>
	</div>
	<div class="layui-form-item" pane="" style="border-bottom:1px solid #35b998;">
		<div class="layui-inline">
			<div class="layui-inline">
			<label class="layui-form-label">正确答案</label>
			<div class="layui-input-block">
				<input type="radio" name="answer_read{{i}}" lay-verify="answer" value="A"
						title="A" checked=""> 
				<input type="radio" name="answer_read{{i}}"
						lay-verify="answer" value="B" title="B"> 
				<input
						type="radio" name="answer_read{{i}}" lay-verify="answer" value="C" title="C">
				<input type="radio" name="answer_read{{i}}" lay-verify="answer" value="D"
						title="D">
			</div>
			</div>
			<div class="layui-inline">
					<label class="layui-form-label">分值</label>
					<div class="layui-input-inline" style="width: 100px;">
						<input type="text" name="score_read{{i}}" id="score_read{{i}}"
							lay-verify="score_read{{i}}" autocomplete="off" placeholder="请输入分值"
							class="layui-input"  onkeyup="score_input(this,this.value)">
					</div>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">题目释义</label>
			<div class="layui-input-inline" style="width: 300px;">
				<textarea id="answerDesc_read{{i}}" style="width: 300px; height: 100px; border-radius: 5px;"
					placeholder="该题。。。" class="layui-textarea" name="remarks"></textarea>
			</div>
		</div>
	</div>
	{{/each}}
		</script>
	</form>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././js/bootstrap.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript">
	var index;//加载框变量
	var pageNo = 1, pageSize =100;
	var totalScore = 0;//试卷总分
	$(document)
			.ready(
					function() {
						var data = {
							objects : [ {}, {}, {}, {}, {}, {}, {}, {}, {}, {} ]
						};
						var html = template("read_template", data);
						$('#readselect').append(html);
						$('.uls_showstars3 li').on(
								'click',
								function() {
									var id = $(this).attr('id');
									for (var i = 1; i <= id; i++) {
										$(".uls_showstars3_" + i).removeClass()
												.addClass(
														'icon-star uls_showstars3_'
																+ i);
										for (var j = 5; j > id; j--) {
											$(".uls_showstars3_" + j)
													.removeClass().addClass(
															'icon-star-empty uls_showstars3_'
																	+ j);
										}
									}
									$('#degree_read').val(i - 1);
								})
						var array = new Array();
						var jsonstr = '[';
						$('#confirm2')
								.on(
										'click',
										function() {
											if (content == '') {
												layer.msg("请输入文本内容");
												return;
											} else if (tag_read == '') {
												layer.msg("请编辑您的题目标签");
												return;
											} else if (degrees == '') {
												layer.msg("请选择题型难度");
												return;
											} else if (sources == '') {
												layer.msg("请选择题目来源");
												return;
											} else if (totalScore == 0) {
												layer.msg("请输入至少一道题目");
												return;
											}
											array[6] = $('#tag4').val();
											array[8] = degrees;
											array[9] = sources;
											array[10] = totalScore;
											$('#totalScore').val(totalScore);
											for (var i = 0; i < 10; i++) {
												var title = $(
														'#Optiontitle' + i)
														.val();
												var oA = $('#OptionA_read' + i)
														.val();
												var oB = $('#OptionB_read' + i)
														.val();
												var oC = $('#OptionC_read' + i)
														.val();
												var oD = $('#OptionD_read' + i)
														.val();
												var answer = $(
														'input[name="answer_read'
																+ i
																+ '"]:checked')
														.val();
												var answerdesc = $(
														'#answerDesc_read' + i)
														.val();
												// 						//	 			var parentid =  parentId;
												var score = $('#score_read' + i)
														.val();
												totalScore += score;
												jsonstr += "{";
												jsonstr += '"Optiontitle":"'
														+ title + '",';
												jsonstr += '"OptionA_read":"'
														+ oA + '",';
												jsonstr += '"OptionB_read":"'
														+ oB + '",';
												jsonstr += '"OptionC_read":"'
														+ oC + '",';
												jsonstr += '"OptionD_read":"'
														+ oD + '",';
												jsonstr += '"type":"' + type
														+ '",';
												jsonstr += '"subject":"'
														+ subject + '",';
												jsonstr += '"answer_read":"'
														+ answer + '",';
												jsonstr += '"answerDesc_read":"'
														+ answerdesc + '",';
												jsonstr += '"score_read":"'
														+ score + '"';
												jsonstr += "},";
											}
											jsonstr = jsonstr.substring(0,
													jsonstr.length - 1);
											jsonstr += ']';
											console.log(jsonstr);
											// 			index = layer.load(1);
											ajax_read(array)
													.then(
															function(res) {
																//得到parentId
																var parentId = res.objects[0].readId;
																ajax_read1(
																		jsonstr,
																		parentId)
																		.then(
																				function(
																						res) {
																					layer
																							.close(index);
																					window.location.href = "myquestionbank.do";
																				},
																				function() {
																					layer
																							.msg("抱歉，添加失败，请重试！");
																				});
															},
															function(error) {
																layer
																		.close(index);
																layer
																		.msg("抱歉，添加失败，请重试！");
															});
										})
					});
	layui
			.use(
					[ 'form', 'layedit', 'laypage', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

						var vlaue = $('#update_type').val();
						if (vlaue == 1) {//单选
							radioselect.css('display', 'block');
							multiselect.css('display', 'none');
							judgeselect.css('display', 'none');
							readselect.css('display', 'none');
							$('#confirm1').css('display', 'block');
							$('#myform').attr('action', 'updateSingleChoice.do');
							$('#confirm2').css('display', 'none');
							//自定义验证规则
							form.verify({
								choiceDesc1 : function(value) {
									if (value.length == 0) {
										return '您还未输入题目呢';
									}
								},
								OptionA1 : function(value) {
									if (value.length <= 0) {
										return '您还未输入A选项的内容';
									}
								},
								OptionB1 : function(value) {
									if (value.length <= 0) {
										return '您还未输入B选项的内容';
									}
								},
								OptionC1 : function(value) {
									if (value.length <= 0) {
										return '您还未输入C选项的内容';
									}
								},
								OptionD1 : function(value) {
									if (value.length <= 0) {
										return '您还未输入D选项的内容';
									}
								},
								answerDesc1 : function(value) {
									if (value.length <= 0) {
										return '您还未输入答案释义';
									}
								},
								score1 : function(value) {
									if (value.length <= 0) {
										return '您还未输入分值';
									}
								},
								source1 : function(value) {
									if (value.length <= 0) {
										return '您还未选择题目来源哦';
									}
								},
								degree1 : function(value) {
									if (value.length <= 0) {
										return '您还未选择题目难度哦';
									}
								},
								ispublic1 : function(value) {
									if (value.length <= 0) {
										return '题目是否公开？';
									}
								},
								tag1 : function(value) {
									if (value.length <= 0) {
										return '您还未选择标签呢';
									}
								}
							});
						}
						if (vlaue == 2) {//多选
							radioselect.css('display', 'none');
							multiselect.css('display', 'block');
							judgeselect.css('display', 'none');
							readselect.css('display', 'none');
							$('#confirm1').css('display', 'block');
							$('#myform').attr('action', 'updateMulitChoice.do');
							$('#confirm2').css('display', 'none');
							form.verify({
								choiceDesc1 : function(value) {
								},
								OptionA1 : function(value) {
								},
								OptionB1 : function(value) {
								},
								OptionC1 : function(value) {
								},
								OptionD1 : function(value) {
								},
								answerDesc1 : function(value) {
								},
								score1 : function(value) {
								},
								source1 : function(value) {
								},
								degree1 : function(value) {
								},
								ispublic1 : function(value) {
								},
								tag1 : function(value) {
								},
								choiceDesc2 : function(value) {
									if (value.length == 0) {
										return '您还未输入题目呢';
									}
								},
								OptionA2 : function(value) {
									if (value.length <= 0) {
										return '您还未输入A选项的内容';
									}
								},
								OptionB2 : function(value) {
									if (value.length <= 0) {
										return '您还未输入B选项的内容';
									}
								},
								OptionC2 : function(value) {
									if (value.length <= 0) {
										return '您还未输入C选项的内容';
									}
								},
								OptionD2 : function(value) {
									if (value.length <= 0) {
										return '您还未输入D选项的内容';
									}
								},
								answerDesc2 : function(value) {
									if (value.length <= 0) {
										return '您还未输入答案释义';
									}
								},
								score2 : function(value) {
									if (value.length <= 0) {
										return '您还未输入分值';
									}
								},
								source2 : function(value) {
									if (value.length <= 0) {
										return '您还未选择题目来源哦';
									}
								},
								degree2 : function(value) {
									if (value.length <= 0) {
										return '您还未选择题目难度哦';
									}
								},
								ispublic2 : function(value) {
									if (value.length <= 0) {
										return '题目是否公开？';
									}
								},
								tag2 : function(value) {
									if (value.length <= 0) {
										return '您还未选择标签呢';
									}
								}
							});
						} else if (vlaue == 3) {//判断
							radioselect.css('display', 'none');
							multiselect.css('display', 'none');
							judgeselect.css('display', 'block');
							readselect.css('display', 'none');
							$('#myform').attr('action', 'updateJudge.do');
							$('#confirm1').css('display', 'block');
							$('#confirm2').css('display', 'none');
							form.verify({
								choiceDesc1 : function(value) {
								},
								OptionA1 : function(value) {
								},
								OptionB1 : function(value) {
								},
								OptionC1 : function(value) {
								},
								OptionD1 : function(value) {
								},
								answerDesc1 : function(value) {
								},
								score1 : function(value) {
								},
								source1 : function(value) {
								},
								degree1 : function(value) {
								},
								ispublic1 : function(value) {
								},
								tag1 : function(value) {
								},
								choiceDesc2 : function(value) {
								},
								OptionA2 : function(value) {
								},
								OptionB2 : function(value) {
								},
								OptionC2 : function(value) {
								},
								OptionD2 : function(value) {
								},
								answerDesc2 : function(value) {
								},
								score2 : function(value) {
								},
								source2 : function(value) {
								},
								degree2 : function(value) {
								},
								ispublic2 : function(value) {
								},
								tag2 : function(value) {
								},
								judgeDesc : function(value) {
									if (value.length <= 0) {
										return '您还未输入题目';
									}
								},
								answerDesc3 : function(value) {
									if (value.length <= 0) {
										return '您还未输入答案释义';
									}
								},
								score3 : function(value) {
									if (value.length <= 0) {
										return '您还未输入分值';
									}
								},
								source3 : function(value) {
									if (value.length <= 0) {
										return '您还未选择题目来源哦';
									}
								},
								degree3 : function(value) {
									if (value.length <= 0) {
										return '您还未选择题目难度哦';
									}
								},
								ispublic3 : function(value) {
									if (value.length <= 0) {
										return '题目是否公开？';
									}
								},
								tag3 : function(value) {
									if (value.length <= 0) {
										return '您还未选择标签呢';
									}
								}
							});
						} else if (vlaue == 4) {//阅读
							radioselect.css('display', 'none');
							multiselect.css('display', 'none');
							judgeselect.css('display', 'none');
							readselect.css('display', 'block');
							$('#myform').attr('action', '');
							$('#confirm1').css('display', 'none');
							$('#confirm2').css('display', 'block');
						}

						form.verify({
							knowledge : function(value) {
								if (value.length <= 0) {
									return '年级';
								}
							},
							subject : function(value) {
								if (value.length <= 0) {
									return '请选择科目';
								}
							},
							/* type : function(value) {
								if (value.length <= 0) {
									return '请选择类型';
								}
							}, */
							
							/* knowledgeBegin : function(value) {
								if (value.length < 0) {
									return '请选择知识水平';
								}
							},
							knowledgeEnd : function(value) {
								if (value.length < 0) {
									return '请选择知识水平';
								}
							} */
						});

						form.on('checkbox(answers)', function(data) {
							var chk_value = [];
							$('input[name="answerss"]:checked').each(
									function() {
										chk_value.push($(this).attr('title'));
									});
							$('#answer_id').val(chk_value.join(","));
						})
						
						//重置标签
						form.on('select(subjectabc)',function(data){
							 
							  //重置标签显示
							  $("#tbody2").html('');
							  $("#tbody3").html('');
							  $("#tbody4").html('');
							  //重置标签赋值
							  $('#tag1').val('');
							  $('#tag2').val('');
							  $('#tag3').val('');
							
							 
							 						  							  
						})
						
						//监听提交
						form.on('submit(demo1)', function(data) {
							var gradeId=$('#knowledge').val();
							var subject=$('#subject').val();
							$.ajax({
								url:'testingIsAdd.do',
								type:'post',
								dataType:'json',
								data:{gradeId:gradeId,subject:subject},
								success:function(res) {
									if(res.code==0) {
										$('#myform').submit();
									} else {
										layer.msg(res.msg,{time:5000});
										return false;
									}
								}
							})
							return false;
						});
						
					
					});
	var radioselect = $('#radioselect');
	var multiselect = $('#multiselect');
	var judgeselect = $('#judgeselect');
	var readselect = $('#readselect');
	$(".radio1").on("click", function(e) {
		if (e.currentTarget.dataset.click == 0) {
			$(this).attr('src', '.././css/images/checkbox_on.png');
			e.currentTarget.dataset.click = 1;
		} else {
			$(this).attr('src', '.././css/images/checkbox.png');
			e.currentTarget.dataset.click = 0;
		}

	});
	$('.uls_showstars li').on('click', function() {
		var id = $(this).attr('id');
		for (var i = 1; i <= id; i++) {
			$("#" + i).removeClass().addClass('icon-star');
			for (var j = 5; j > id; j--) {
				$("#" + j).removeClass().addClass('icon-star-empty');
			}
			$('#degree').val(i);
		}
	})
	$('.uls_showstars li').on('click', function() {
		var id = $(this).attr('id');
		for (var i = 1; i <= id; i++) {
			$("#" + i).removeClass().addClass('icon-star');
			for (var j = 5; j > id; j--) {
				$("#" + j).removeClass().addClass('icon-star-empty');
			}
		}
		$('#degree').val(i - 1);
	});
	$('.uls_showstars1 li').on(
			'click',
			function() {
				var id = $(this).attr('id');
				for (var i = 1; i <= id; i++) {
					$(".uls_showstars1_" + i).removeClass().addClass(
							'icon-star uls_showstars1_' + i);
					for (var j = 5; j > id; j--) {
						$(".uls_showstars1_" + j).removeClass().addClass(
								'icon-star-empty uls_showstars1_' + j);
					}
				}
				$('#degree1').val(i - 1);
			})
	$('.uls_showstars2 li').on(
			'click',
			function() {
				var id = $(this).attr('id');
				for (var i = 1; i <= id; i++) {
					$(".uls_showstars2_" + i).removeClass().addClass(
							'icon-star uls_showstars2_' + i);
					for (var j = 5; j > id; j--) {
						$(".uls_showstars2_" + j).removeClass().addClass(
								'icon-star-empty uls_showstars2_' + j);
					}
				}
				$('#degree2').val(i - 1);
			});

	function ajax_read(array) {
		return new Promise(function(resolve, reject) {
			//先将阅读内容存进去，然后返回parentId再去存每道题目
			$.ajax({
				url : 'updateRead.do',
				type : 'post',
				dataType : 'json',
				data : $('#myform').serialize(),
				// 				data:{
				// 					readDesc:array[0],
				// 					subject:array[1],
				// 					knowledge:array[2],
				// 					knowledgeBegin:array[3],
				// 					knowledgeEnd:array[4],
				// 					type:array[5],
				// 					tag_read:array[6],
				// 					ispublic_read:array[7]
				// 				},
				success : function(res) {
					if (res.code == 0) {
						console.log(JSON.stringify(res));
						resolve(res);//将返回数据传给resolve
					} else if (res.code == 1) {
						reject(res);//将返回失败数据传给reject
					} else if (res.resphead.resultcode == 2) {
						reject(res);//将返回失败数据传给reject
					}
				}
			})
		});
	}
	function ajax_read1(jsonstr, parentId) {
		return new Promise(function(resolve, reject) {
			$.ajax({
				url : 'updateReadDetail.do',
				type : 'post',
				dataType : 'json',
				data : {
					jsonstr : jsonstr,
					parentId : parentId
				},
				success : function(res) {
					if (res.code == 0) {
						resolve(res);//将返回数据传给resolve
					} else if (res.resphead.resultcode == 2) {
						reject(res);//将返回失败数据传给reject
					} else if (res.code == 1) {
						reject(res);
					}
				}
			});
		});
	}
	//监听分数输入框的事件
	function score_input(that, value) {
		var test1 = /^\d\.?\d{0,1}$/;//表示数字开头，数字结尾，保留一位小数
		if (!test1.test(value)) {
			alert("分数只能是整数或保留一位的小数");
			that.value = "";
			return;
		}
	}
	$('#select_tag').on('click', function() {
		var value = $('#input_tag').val();
		var subjectId = $('#subject').val();
		$.ajax({
			url : '${ctx}/questionTag/gettagbysearch.do',
			type : 'post',
			dataType : 'json',
			data : {
				content : value,
				subjectId :subjectId
			},
			success : function(res) {
				if (res.code == 0) {
					sucess_totemplate(res);
				} else {
					sucess_totemplate(res);
					layer.msg(res.msg);
				}
			}
		})
	})
	$('#create_tag').on('click', function() {
		var qtype = $("#update_type").val();
		var value = $('#create_input_tag').val();
		var subjectId = $("#subject").val();
		var grade = $('#knowledge').val();
		if (value == "") {
			layer.msg("请输入您要添加的标签名称");
			return;
		}
		$.ajax({
			url : '${ctx}/questionTag/addTag.do',
			type : 'post',
			dataType : 'json',
			data : {
				tagName : value,
				subject :subjectId,
				grade : grade
			},
			success : function(res) {
				if (res.code == 0) {
					var tagId = res.objects[0].tagId;
					sucess_totemplate(res);
					var tagName=$("#checked-"+tagId).text();
					
					if(qtype == 1){
						var html = "<tr id="+tagId+" class='tag_tr1' style='cursor: pointer;'><td class='hline'><span class='tagclass'><em class='icon-remove-sign' style='color:#ff0000;margin-right:5px'></em>"
						+ tagName + "</span></td></tr>";
						var val1 = $('#tag1').val();
						if (val1 == "") {
							$('#tag1').val(tagId);
						} else {
							$('#tag1').val(val1 + "," + tagId);
						}
						$('#tbody2').append(html);
						$('.tag_tr1').click(function() {
							var that = this;
							var id = $(that).attr("id");
							let
							val = $('#tag1').val();
							let
							vals = val.split(",");
							layer.confirm('确认删除该标签吗？', {
								btn : [ '确定' ]
							}, function(index, layero) {//确定的处理
								if (val.indexOf(",") > 0) {
									if (vals[vals.length - 1] == id) {
										val = val.replace("," + id, '');
									} else {
										val = val.replace(id + ",", '');
									}
								} else {
									val = val.replace(id, '');
								}
								$('#tag1').val(val);
								$(that).remove();
								layer.close(index);
							});
						})
					}else if(qtype == 2){
						var html = "<tr id="+tagId+" class='tag_tr2' style='cursor: pointer;'><td style='height:30px;line-height:40px;'><span style='padding:5px 10px;border:1px solid #e6e6e6;border-radius:4px'>"
						+ tagName + "</span></td></tr>";
						var val1 = $('#tag2').val();
						if (val1 == "") {
							$('#tag2').val(tagId);
						} else {
							$('#tag2').val(val1 + "," + tagId);
						}
						$('#tbody3').append(html);
						$('.tag_tr2').click(function() {
							var that = this;
							var id = $(that).attr("id");
							let
							val = $('#tag2').val();
							let
							vals = val.split(",");
							layer.confirm('确认删除该标签吗？', {
								btn : [ '确定' ]
							}, function(index, layero) {//确定的处理
								if (val.indexOf(",") > 0) {
									if (vals[vals.length - 1] == id) {
										val = val.replace("," + id, '');
									} else {
										val = val.replace(id + ",",'');
									}
								} else {
									val = val.replace(id, '');
								}
								$('#tag2').val(val);
								$(that).remove();
								layer.close(index);
							});
						})
					}else if(qtype ==3){
						var html = "<tr id="+tagId+" class='tag_tr3' style='cursor: pointer;'><td style='height:30px;line-height:40px;'><span style='padding:5px 10px;border:1px solid #e6e6e6;border-radius:4px'>"
						+ tagName + "</span></td></tr>";
						var val1 = $('#tag3').val();
						if (val1 == "") {
							$('#tag3').val(tagId);
						} else {
							$('#tag3').val(val1 + "," + tagId);
						}
						$('#tbody4').append(html);
						$('.tag_tr3').click(function() {
							var that = this;
							var id = $(that).attr("id");
							let
							val = $('#tag3').val();
							let
							vals = val.split(",");
							layer.confirm('确认删除该标签吗？', {
								btn : [ '确定' ]
							}, function(index, layero) {//确定的处理
								if (val.indexOf(",") > 0) {
									if (vals[vals.length - 1] == id) {
										val = val.replace("," + id, '');
									} else {
										val = val.replace(id + ",", '');
									}
								} else {
									val = val.replace(id, '');
								}
								$('#tag3').val(val);
								$(that).remove();
								layer.close(index);
							});
						})
					}else{  //预留
						
					}
					object_tb_click(tagId);
					$('#myModal').modal('hide');
				} else {
					layer.msg(res.msg);
				}
			}
		})
	})
	//不是添加模板的删除操作
	$('.tag_tr1').click(function() {
		var that = this;
		var id = $(that).attr("id");
		let
		val = $('#tag1').val();
		let
		vals = val.split(",");
		layer.confirm('确认删除该标签吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			if (val.indexOf(",") > 0) {
				if (vals[vals.length - 1] == id) {
					val = val.replace("," + id, '');
				} else {
					val = val.replace(id + ",", '');
				}
			} else {
				val = val.replace(id, '');
			}
			$('#tag1').val(val);
			$(that).remove();
			layer.close(index);
		});
	})
	//不是添加模板的删除操作
	$('.tag_tr2').click(function() {
		var that = this;
		var id = $(that).attr("id");
		let
		val = $('#tag2').val();
		let
		vals = val.split(",");
		layer.confirm('确认删除该标签吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			if (val.indexOf(",") > 0) {
				if (vals[vals.length - 1] == id) {
					val = val.replace("," + id, '');
				} else {
					val = val.replace(id + ",",'');
				}
			} else {
				val = val.replace(id, '');
			}
			$('#tag2').val(val);
			$(that).remove();
			layer.close(index);
		});
	})
	//不是添加模板的删除操作
	$('.tag_tr3').click(function() {
		var that = this;
		var id = $(that).attr("id");
		let
		val = $('#tag3').val();
		let
		vals = val.split(",");
		layer.confirm('确认删除该标签吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			if (val.indexOf(",") > 0) {
				if (vals[vals.length - 1] == id) {
					val = val.replace("," + id, '');
				} else {
					val = val.replace(id + ",", '');
				}
			} else {
				val = val.replace(id, '');
			}
			$('#tag3').val(val);
			$(that).remove();
			layer.close(index);
		});
	})
	//不是添加模板的删除操作
	$('.tag_tr4').click(function() {
		var that = this;
		var id = $(that).attr("id");
		let
		val = $('#tag4').val();
		let
		vals = val.split(",");
		layer.confirm('确认删除该标签吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			if (val.indexOf(",") > 0) {
				if (vals[vals.length - 1] == id) {
					val = val.replace("," + id, '');
				} else {
					val = val.replace(id + ",", '');
				}
			} else {
				val = val.replace(id, '');
			}
			$('#tag4').val(val);
			$(that).remove();
			layer.close(index);
		});
	})
	//进行删除操作
	function object_tb_click(id) {
		var state = $('#update_type').val();
		if (state == 1) {
			$('#tag' + id).click(function(res) {
				let
				val = $('#tag1').val();
				let
				vals = val.split(",");
				layer.confirm('确认删除该标签吗？', {
					btn : [ '确定' ]
				}, function(index, layero) {//确定的处理
					if (val.indexOf(",") > 0) {
						if (vals[vals.length - 1] == id) {
							val = val.replace("," + id, '');
						} else {
							val = val.replace(id + ",", '');
						}
					} else {
						val = val.replace(id, '');
					}
					$('#tag1').val(val);
					$('#tag' + id).remove();
					layer.close(index);
				});
			})
		} else if (state == 2) {
			$('#tag' + id).click(function(res) {
				let
				val = $('#tag2').val();
				let
				vals = val.split(",");
				layer.confirm('确认删除该标签吗？', {
					btn : [ '确定' ]
				}, function(index, layero) {//确定的处理
					if (val.indexOf(",") > 0) {
						if (vals[vals.length - 1] == id) {
							val = val.replace("," + id, '');
						} else {
							val = val.replace(id + ",", '');
						}
					} else {
						val = val.replace(id, '');
					}
					$('#tag2').val(val);
					$('#tag' + id).remove();
					layer.close(index);
				});
			})
		} else if (state == 3) {
			$('#tag' + id).click(function(res) {
				let
				val = $('#tag3').val();
				let
				vals = val.split(",");
				layer.confirm('确认删除该标签吗？', {
					btn : [ '确定' ]
				}, function(index, layero) {//确定的处理
					if (val.indexOf(",") > 0) {
						if (vals[vals.length - 1] == id) {
							val = val.replace("," + id, '');
						} else {
							val = val.replace(id + ",", '');
						}
					} else {
						val = val.replace(id, '');
					}
					$('#tag3').val(val);
					$('#tag' + id).remove();
					layer.close(index);
				});
			})
		} else if (state == 4) {
			$('#tag' + id).click(function(res) {
				let
				val = $('#tag4').val();
				let
				vals = val.split(",");
				layer.confirm('确认删除该标签吗？', {
					btn : [ '确定' ]
				}, function(index, layero) {//确定的处理
					if (val.indexOf(",") > 0) {
						if (vals[vals.length - 1] == id) {
							val = val.replace("," + id, '');
						} else {
							val = val.replace(id + ",", '');
						}
					} else {
						val = val.replace(id, '');
					}
					$('#tag4').val(val);
					$('#tag' + id).remove();
					layer.close(index);
				});
			})
		}
	}

	function sucess_totemplate(res) {
		var html = template('tag_template', res);
		$('#tag_list').html(html);
		$('.tagName').on(
				'click',
				function() {
					var state = $('#update_type').val();
					let
					id = $(this).attr("id");
					let
					name = $(this).attr("data-name");
					var html = "<tr id=tag"+id+" style='cursor:pointer;'><td class='hline'><span class='tagclass'><em class='icon-remove-sign' style='color:#ff0000;margin-right:5px'></em>"
							+ name + "</span></td></tr>"
					if (state == 1) {
						var val1 = $('#tag1').val();
						const
						tag1s = val1.split(",");
						let
						status = 0;
						for (var i = 0; i < tag1s.length; i++) {
							if (tag1s[i] == id) {
								layer.msg("抱歉，您已经添加过该标签了~");
								status = 1;
								break;
							}
						}
						if (status == 1) {
							return;
						}
						$('#tbody2').append(html);
						if (val1 == "") {
							$('#tag1').val(id);
						} else {
							$('#tag1').val(val1 + "," + id);
						}
						object_tb_click(id);
					} else if (state == 2) {
						var val1 = $('#tag2').val();
						const
						tag2s = val1.split(",");
						let
						status = 0;
						for (var i = 0; i < tag2s.length; i++) {
							if (tag2s[i] == id) {
								layer.msg("抱歉，您已经添加过该标签了~");
								status = 1;
								break;
							}
						}
						if (status == 1) {
							return;
						}
						$('#tbody3').append(html);
						if (val1 == "") {
							$('#tag2').val(id);
						} else {
							$('#tag2').val(val1 + "," + id);
						}
						object_tb_click(id);
					} else if (state == 3) {
						var val1 = $('#tag3').val();
						const
						tag3s = val1.split(",");
						let
						status = 0;
						for (var i = 0; i < tag3s.length; i++) {
							if (tag3s[i] == id) {
								layer.msg("抱歉，您已经添加过该标签了~");
								status = 1;
								break;
							}
						}
						if (status == 1) {
							return;
						}
						$('#tbody4').append(html);
						if (val1 == "") {
							$('#tag3').val(id);
						} else {
							$('#tag3').val(val1 + "," + id);
						}
						object_tb_click(id);
					} else if (state == 4) {
						var val1 = $('#tag4').val();
						const
						tag4s = val1.split(",");
						let
						status = 0;
						for (var i = 0; i < tag4s.length; i++) {
							if (tag4s[i] == id) {
								layer.msg("抱歉，您已经添加过该标签了~");
								status = 1;
								break;
							}
						}
						if (status == 1) {
							return;
						}
						$('#tbody5').append(html);
						if (val1 == "") {
							$('#tag4').val(id);
						} else {
							$('#tag4').val(val1 + "," + id);
						}
						object_tb_click(id);
					}
					$('#myModal').modal('hide');
				})
	}
var questiontype = ${type};
if(questiontype==1){
<!--单选开始-->
	 var ue = UE.getEditor('choiceDesc',{
		 toolbars:[
		           [
	            
					//'anchor', //锚点
					//'undo', //撤销
					//'redo', //重做
					//'bold', //加粗
					//'indent', //首行缩进
					//'justifyleft', //居左对齐
					//'justifyright', //居右对齐
					//'justifycenter', //居中对齐
					//'justifyjustify', //两端对齐
					//'snapscreen', //截图
					//'italic', //斜体
					//'underline', //下划线
					//'strikethrough', //删除线
					//'fontborder', //字符边框				
					//'formatmatch', //格式刷
					//'source', //源代码
					//'blockquote', //引用
					//'pasteplain', //纯文本粘贴模式
					//'selectall', //全选
					//'print', //打印
					//'preview', //预览
					//'horizontal', //分隔线
					//'removeformat', //清除格式
					//'time', //时间
					//'date', //日期
					//'unlink', //取消链接
					//'insertrow', //前插入行
					//'insertcol', //前插入列
					//'mergeright', //右合并单元格
					//'mergedown', //下合并单元格
					//'deleterow', //删除行
					//'deletecol', //删除列
					//'splittorows', //拆分成行
					//'splittocols', //拆分成列
					//'splittocells', //完全拆分单元格
					//'deletecaption', //删除表格标题
					//'inserttitle', //插入标题
					//'mergecells', //合并多个单元格
					//'deletetable', //删除表格
					//'cleardoc', //清空文档
					//'insertparagraphbeforetable', //"表格前插入行"
					//'insertcode', //代码语言
					//'fontfamily', //字体
					//'fontsize', //字号
					//'paragraph', //段落格式
					'simpleupload', //单图上传
					'insertimage', //多图上传
					'subscript', //下标
					'superscript', //上标
					//'edittable', //表格属性
					//'edittd', //单元格属性
					//'link', //超链接
					//'emotion', //表情
					//'searchreplace', //查询替换
					//'map', //Baidu地图
					//'gmap', //Google地图
					//'insertvideo', //视频
					//'help', //帮助				
					//'forecolor', //字体颜色
					//'backcolor', //背景色
					//'insertorderedlist', //有序列表
					//'insertunorderedlist', //无序列表
					//'fullscreen', //全屏
					//'directionalityltr', //从左向右输入
					//'directionalityrtl', //从右向左输入
					//'rowspacingtop', //段前距
					//'rowspacingbottom', //段后距
					//'pagebreak', //分页
					//'insertframe', //插入Iframe
					//'imagenone', //默认
					//'imageleft', //左浮动
					//'imageright', //右浮动
					//'attachment', //附件
					//'imagecenter', //居中
					//'wordimage', //图片转存
					//'lineheight', //行间距
					//'edittip ', //编辑提示
					//'customstyle', //自定义标题
					//'autotypeset', //自动排版
					//'webapp', //百度应用
					//'touppercase', //字母大写
					//'tolowercase', //字母小写
					//'background', //背景
					//'template', //模板
					//'scrawl', //涂鸦
					//'music', //音乐
					//'inserttable', //插入表格
					//'drafts', // 从草稿箱加载
					//'charts', // 图表
					'spechars', //特殊字符
					'kityformula',
					'underline' //下划线
	            
	           ]
		         ],
		         elementPathEnabled:false,
		         autoHeightEnabled:false,
		         imageScaleEnabled:false
	 });
	
	 var ue1 = UE.getEditor('OptionA1',{
	 	 toolbars:[
	 	           [	            
	 					'simpleupload', //单图上传
	 					'insertimage', //多图上传	
	 					'subscript', //下标
						'superscript', //上标
						'spechars', //特殊字符
						'kityformula',
						'underline' //下划线
	 	           ]
	 	         ],
	 	         elementPathEnabled:false,
	 	         autoHeightEnabled:false,
		         imageScaleEnabled:false
	  });
	 var ue2 = UE.getEditor('OptionB1',{
	 	 toolbars:[
	 	           [	            
	 					'simpleupload', //单图上传
	 					'insertimage', //多图上传	
	 					'subscript', //下标
						'superscript', //上标
						'spechars', //特殊字符
						'kityformula',
						'underline' //下划线
	 	           ]
	 	         ],
	 	         elementPathEnabled:false,
	 	         autoHeightEnabled:false,
		         imageScaleEnabled:false
	 });
	 var ue3 = UE.getEditor('OptionC1',{
	 	 toolbars:[
	 	           [	            
	 					'simpleupload', //单图上传
	 					'insertimage', //多图上传
	 					'subscript', //下标
						'superscript', //上标
						'kityformula',
						'underline' //下划线
	 	           ]
	 	         ],
	 	         elementPathEnabled:false,
	 	         autoHeightEnabled:false,
		         imageScaleEnabled:false
	 });
	 var ue4 = UE.getEditor('OptionD1',{
	 	 toolbars:[
	 	           [	            
	 					'simpleupload', //单图上传
	 					'insertimage', //多图上传
	 					'subscript', //下标
						'superscript', //上标
						'spechars', //特殊字符
						'kityformula',
						'underline' //下划线
	 	           ]
	 	         ],
	 	         elementPathEnabled:false,
	 	         autoHeightEnabled:false,
		         imageScaleEnabled:false
	 });
	 var ue5 = UE.getEditor('answerDesc1',{
	 	 toolbars:[
	 	           [
	 					//'bold', //加粗
	 					//'indent', //首行缩进
	 					//'justifyleft', //居左对齐
	 					//'justifyright', //居右对齐
	 					//'justifycenter', //居中对齐
	 					//'justifyjustify', //两端对齐
	 					//'fontfamily', //字体
	 					//'fontsize', //字号
	 					'simpleupload', //单图上传
	 					'insertimage', //多图上传		
	 					//'forecolor', //字体颜色
	 					//'backcolor', //背景色
	 					'subscript', //下标
						'superscript', //上标
						'spechars', //特殊字符
						'kityformula',
						'underline' //下划线
	 	           ]
	 	         ],
	 	         elementPathEnabled:false,
	 	         autoHeightEnabled:false,
		         imageScaleEnabled:false
	  });
<!--单选结束-->	 
}else if(questiontype == 2){
<!--多选开始-->
var ue = UE.getEditor('choiceDesc2',{
	 toolbars:[
	           [		            
					//'bold', //加粗
					//'indent', //首行缩进
					//'justifyleft', //居左对齐
					//'justifyright', //居右对齐
					//'justifycenter', //居中对齐
					//'justifyjustify', //两端对齐				
					//'formatmatch', //格式刷					
					//'fontfamily', //字体
					//'fontsize', //字号	
					'simpleupload', //单图上传
					'insertimage', //多图上传					
					//'forecolor', //字体颜色
					//'backcolor', //背景色	
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula',
					'underline' //下划线
	            
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
});

var ue1 = UE.getEditor('OptionA2',{
	 toolbars:[
	           [	            
					'simpleupload', //单图上传
					'insertimage', //多图上传	
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula',
					'underline' //下划线
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
 });
var ue2 = UE.getEditor('OptionB2',{
	 toolbars:[
	           [	            
					'simpleupload', //单图上传
					'insertimage', //多图上传
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula',
					'underline' //下划线
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
});
var ue3 = UE.getEditor('OptionC2',{
	 toolbars:[
	           [	            
					'simpleupload', //单图上传
					'insertimage', //多图上传	
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula',
					'underline' //下划线
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
});
var ue4 = UE.getEditor('OptionD2',{
	 toolbars:[
	           [	            
					'simpleupload', //单图上传
					'insertimage', //多图上传	
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula',
					'underline' //下划线
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
});
var ue5 = UE.getEditor('answerDesc2',{
	 toolbars:[
	           [
					//'bold', //加粗
					//'indent', //首行缩进
					//'justifyleft', //居左对齐
					//'justifyright', //居右对齐
					//'justifycenter', //居中对齐
					//'justifyjustify', //两端对齐
					//'fontfamily', //字体
					//'fontsize', //字号
					'simpleupload', //单图上传
					'insertimage', //多图上传		
					//'forecolor', //字体颜色
					//'backcolor', //背景色
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula',
					'underline' //下划线
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
 });

<!--多选结束-->
}else if(questiontype == 3){
 //判断开始
 var ue1 = UE.getEditor('judgeDesc',{
	 toolbars:[
	           [
					//'bold', //加粗
					//'indent', //首行缩进
					//'justifyleft', //居左对齐
					//'justifyright', //居右对齐
					//'justifycenter', //居中对齐
					//'justifyjustify', //两端对齐
					//'fontfamily', //字体
					//'fontsize', //字号
					'simpleupload', //单图上传
					'insertimage', //多图上传		
					//'forecolor', //字体颜色
					//'backcolor', //背景色
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula',
					'underline' //下划线
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
 });
 
 var ue2 = UE.getEditor('answerDesc3',{
	 toolbars:[
	           [
					//'bold', //加粗
					//'indent', //首行缩进
					//'justifyleft', //居左对齐
					//'justifyright', //居右对齐
					//'justifycenter', //居中对齐
					//'justifyjustify', //两端对齐
					//'fontfamily', //字体
					//'fontsize', //字号
					'simpleupload', //单图上传
					'insertimage', //多图上传		
					//'forecolor', //字体颜色
					//'backcolor', //背景色
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula',
					'underline' //下划线
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
 });
 
 //判断结束
	
}else{
	
	
}

function openModal(){
	//重置输入框
	$('#input_tag').val('');
    $('#create_input_tag').val('');
	var subjectId = $("#subject").val();
	var grade = $('#knowledge').val();
	var subjectName = $("#subject option:selected").attr("data-name");
	var gradeName = $("#knowledge option:selected").attr("data-name");
	var signName = gradeName+subjectName; 
	if(subjectId && grade){
		 layer.closeAll();
		 $("#chooseGraSub").text(signName);
		 getTag(grade,subjectId,pageNo);
		 $("#myModal").modal("show");
	 }else{
		 layer.msg('标签需和科目联动，请先选择科目',{time:5000});
		 $("#myModal").modal("hide");			 
	 }
	//解决bug-462： 暂采用禁止enter键
	$(this).keydown( function(e) {
	    var key = window.event?e.keyCode:e.which;
	    if(key.toString() == "13"){
	        return false;
	    }
	});
}

function getTag(grade,sid,pageno) {
	$.getJSON("${ctx}/questionTag/getalltag.do", {
		grade:grade,
		subjectId:sid,
		pageNo : pageno,
		pageSize : pageSize
	}, function(res) {
		if (res.code == 0) {
			sucess_totemplate(res);
			if (res.count > 2) {
				laypage({
					cont : 'pages',
					pages : res.count,
					skin : 'molv',
					curr : pageno || 1,//当前页
					groups : 5,
					jump : function(obj, first) {
						//触发分页后的回调
						if (!first) {
							//点击跳页触发函数自身，并传递当前页：obj.curr
							getTag(grade,sid,obj.curr);
						}
					}
				});
			}
		} else if (res.code == 1) {
			$('#tag_list').empty();
			layer.msg('暂无标签');
		}
	})
}
</script>
</html>