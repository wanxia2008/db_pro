<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>录入试题</title>
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

#subId {
	display: none;
}

#fillId{
	display:none;
}
#wxfill{
    display:none;
}
#filltype{
   display:none;
}
#confirm2 {
	display:none
}
#confirm3 {
	display:none
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
	font-size: 8px;
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

.layui-laypage .layui-laypage-curr .layui-laypage-em {
	background-color: #008bca !important;
}

.layui-textarea {
	padding: 0 !important
}
/* .layui-table td, .layui-table th {
    padding: 6px 12px !important;
} */
.icon-star, .icon-star-empty {
	color: #333 !important
}

.mg6 {
	margin-top: 6px
}

.layui-table td {
	padding: 0 18px;
	border-radius: 4px !important
}

.layui-table td, .layui-table th {
	border: 1px solid #e2e2e2;
	border-radius: 4px !important;
	font-size: 14px;
}

@media ( min-width : 992px) {
	.col-md-3 {
		width: auto !important;
	}
}

@media screen and (max-width: 1366px) {
	.bt10 {
		margin-top: 0px;
	}
}

.tab-bg {
	border-color: #008bca !important;
	color: #008bca
}
</style>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././ueditor/ueditor.config.js"></script>
<script type="text/javascript" src=".././ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src=".././ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script type="text/javascript"
	src=".././ueditor/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript"
	src=".././ueditor/kityformula-plugin/defaultFilterFix.js"></script>
</head>
<body style="padding-bottom: 100px">
	<form class="layui-form" id="myform" method="post" action="">
		<div class="panels_head head_1">
			<span>题库属性</span>
			<button class="layui-btn layui-btn-normal" id="confirm1"
				lay-submit="" lay-filter="demo1"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">确认</button>
			<button type="button" class="layui-btn layui-btn-normal"
				id="confirm2"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">确认</button>
				<button type="button" class="layui-btn layui-btn-normal"
				id="confirm3"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">确认</button>
			<button type="button" class="layui-btn layui-btn-danger"
				onclick="window.location.href='${ctx}/questionBank/myquestionbank.do'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px; background-color: #ff4c3b !important">返回</button>
		</div>
		<div class="layui-form-item" style="margin-top: 80px;">
		<div class="layui-inline">	
		<label class="layui-form-label"
					style="width: 120px; font-size: 14px;"><span
					style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>年级</label>   
		   <div class="layui-input-inline" style="width: 120px;">
					<select name="knowledge" id="knowledge" lay-verify="knowledge">
						<option value="">请选择</option>
						<c:forEach items="${gList}" var="g">
							<option  data-name="${g.gradeName}" value="${g.gradeId}">${g.gradeName}</option>
						</c:forEach>
					</select>
				</div>
		</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="width: 120px; font-size: 14px;"><span
					style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>科目</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="subject" id="subject" lay-verify="subject"
						lay-filter="subjectabc">
						<option value="">请选择</option>
						<c:forEach items="${sList}" var="s">
							<option value="${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="width: 120px; font-size: 14px;"><span
					style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>题型</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="type" lay-verify="type" id="type"
						lay-filter="select_title">
						<option value="">请选择</option>
						<c:forEach items="${qtList}" var="qt">
							<option value="${qt.topicId}">${qt.topicName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label"
					style="width: 120px; font-size: 14px;">知识范围</label>				
				<div class="layui-input-inline" style="width: 120px">
					<select name="knowledgeBegin" id="knowledgeBegin"
						lay-verify="knowledgeBegin" lay-filter="select_knowstart">
						<option value="">请选择</option>
						<c:forEach items="${lessonList}" var="lesson">
							<option value="${lesson.lessonId}">${lesson.lessonName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<!-- 填空题类型 -->
			<div id="filltype">  
			<div class="layui-inline">
				<label class="layui-form-label"
					style="width: 140px; font-size: 12px;"><span
					style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>填空题标题名称</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="typeId">
						
						<option value="" >请选择</option>
						<c:forEach items="${typeList}" var="lesson">
							<option value="${lesson.typeId}">${lesson.typeName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			</div>
			<!-- 填空题类型 -->
		</div>
		<div class="panels_head">
			<span>内容</span>
		</div>
		<div id="contents"></div>
		<!--单选题开始-->
		<div class="" id="radioselect">
			<div class="layui-form-item" style="margin: 20px 0">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>单选题目</label>
					<div class="layui-input-inline" style="width: 400px;">
						<textarea
							style="width: 710px; height: 200px; border-radius: 5px; text-index: 0 !important;"
							placeholder="请输入单选题目" class="layui-textarea" name="choiceDesc"
							id="choiceDesc1" lay-verify="choiceDesc1"></textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>A</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入A选项的内容" class="layui-textarea" name="OptionA"
							id="OptionA1" lay-verify="OptionA1" autocomplete="off"></textarea>
					</div>
				</div>
				<div class="layui-inline" style="margin-left: 10px;">
					<label class="layui-form-label" style="font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>B</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入B选项的内容" class="layui-textarea" name="OptionB"
							id="OptionB1" lay-verify="OptionB1" autocomplete="off"></textarea>
					</div>
				</div>
				<!-- <button type="button" class="layui-btn" data-toggle="modal"
						data-target="#myModal-luru" style="margin-top:120px;margin-left:50px;background-color:#008bca">快速添加答案</button> -->
			</div>
			<div class="layui-form-item" style="margin: 10px 0px">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>C</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入C选项的内容" class="layui-textarea" name="OptionC"
							id="OptionC1" lay-verify="OptionC1" autocomplete="off"></textarea>
					</div>
				</div>
				<div class="layui-inline" style="margin-left: 10px;">
					<label class="layui-form-label" style="font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>D</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入D选项的内容" class="layui-textarea" name="OptionD"
							id="OptionD1" lay-verify="OptionD1" autocomplete="off"></textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>正确答案</label>
					<div class="layui-input-block" style="width: 300px;">
						<input type="radio" name="answer" lay-verify="answer1" value="A"
							title="A" checked=""> <input type="radio" name="answer"
							lay-verify="answer1" value="B" title="B"> <input
							type="radio" name="answer" lay-verify="answer1" value="C"
							title="C"> <input type="radio" name="answer"
							lay-verify="answer1" value="D" title="D" placeholder="请选项正确答案">
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;">答案释义</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="该题。。。" class="layui-textarea" name="answerDesc"
							id="answerDesc1" lay-verify=""></textarea>
					</div>
				</div>
			</div>

			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 150px;">
						<select name="source" id="source1" lay-verify="source1">
							<c:forEach items="${qsList}" var="qs">
								<option value="${qs.sourceId}">${qs.sourceName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>难度</label>
					<div class="layui-input-inline" style="width: 150px;">
						<ul class="uls_showstars mg6">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star" id="2" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					</div>
					<input type="number" min="0" max="100" hidden="" name="degree"
						id="degree" value="2" lay-verify="degree1" />
				</div>
			</div>

			<div class="layui-form-item"
				style="margin: 10px 0; padding-bottom: 50px;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>标签</label>

					<table
						style="width: 40%; position: absolute; left: 120px; font-size: 14px;">
						<tbody id="tbody2">
						</tbody>
					</table>


					<input hidden="" name="tag" lay-verify="tag1" id="tag1" />
					<button type="button" class="layui-btn layui-btn-normal"
						data-toggle="modal" onclick="openModal();"
						style="margin-top: 10px; margin-left: 160px; background-color: #008bca; border: 0; border-radius: 4px; height: 30px; line-height: 30px;">+标签</button>
				</div>
			</div>
		</div>
		<!--单选框结束-->

		<!--多选题开始-->
		<div class="" id="multiselect">
			<div class="layui-form-item" style="margin: 20px 0">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>多选题题目</label>
					<div class="layui-input-inline" style="width: 400px;">
						<textarea
							style="width: 710px; height: 200px; border-radius: 5px; text-index: 0 !important;"
							placeholder="请输入题目" class="layui-textarea" name="choiceDesc1"
							id="choiceDesc2" lay-verify="choiceDesc2" autocomplete="off"></textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 10px 0px">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>A</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入A选项的内容" class="layui-textarea" name="OptionA1"
							id="OptionA2" lay-verify="OptionA2" autocomplete="off"></textarea>
					</div>
				</div>
				<div class="layui-inline" style="margin-left: 10px;">
					<label class="layui-form-label" style="font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>B</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入B选项的内容" class="layui-textarea" name="OptionB1"
							id="OptionB2" lay-verify="OptionB2" autocomplete="off"></textarea>

					</div>
				</div>
				<!-- <button type="button" class="layui-btn" data-toggle="modal"
						data-target="#myModal-duoxuan" style="margin-top:120px;margin-left:50px;background-color:#008bca">快速添加答案</button> -->
			</div>

			<div class="layui-form-item" style="margin: 10px 0px;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>C</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入C选项的内容" class="layui-textarea" name="OptionC1"
							id="OptionC2" lay-verify="OptionC2" autocomplete="off"></textarea>
					</div>

				</div>
				<div class="layui-inline" style="margin-left: 10px;">
					<label class="layui-form-label" style="font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>D</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入D选项的内容" class="layui-textarea" name="OptionD1"
							id="OptionD2" lay-verify="OptionD2" autocomplete="off"></textarea>

					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>正确答案</label>
					<div class="layui-input-block" style="width: 300px;">
						<input type="checkbox" name="answerss" lay-filter="answers"
							lay-skin="primary" title="A" value="A"> <input
							type="checkbox" name="answerss" lay-filter="answers"
							lay-skin="primary" title="B" value="B"> <input
							type="checkbox" name="answerss" lay-filter="answers"
							lay-skin="primary" title="C" value="C"> <input
							type="checkbox" name="answerss" lay-filter="answers"
							lay-skin="primary" title="D" value="D">
					</div>
					<input type="text" hidden="" name="answer1" id="answer_id"
						lay-verify="answer2" value="" />
				</div>
			</div>
			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;">题目释义</label>
					<div class="layui-input-inline">
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="请输入题目释义" class="layui-textarea" name="answerDesc1"
							lay-verify="" id="answerDesc2"></textarea>
					</div>
				</div>
			</div>

			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 150px;">
						<select name="source1" id="source2" lay-verify="source2">
							<c:forEach items="${qsList}" var="qs">
								<option value="${qs.sourceId}">${qs.sourceName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>难度</label>
					<div class="layui-input-inline" style="width: 150px;">
						<ul class="uls_showstars1 mg6">
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
					</div>
					<input type="number" min="0" max="100" hidden="" name="degree1"
						id="degree1" value="2" lay-verify="degree2" />
				</div>
			</div>
			<div class="layui-form-item"
				style="margin: 10px 0px; padding-bottom: 50px;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>标签</label>
					<table
						style="width: 40%; position: absolute; left: 120px; font-size: 14px;">
						<tbody id="tbody3">
						</tbody>
					</table>
					<input hidden="" name="tag1" lay-verify="tag2" id="tag2" />
					<button type="button" class="layui-btn layui-btn-normal"
						onclick="openModal()" data-toggle="modal"
						style="margin-top: 10px; font-size: 14px; margin-left: 160px; background-color: #008bca; border: 0; border-radius: 4px; height: 30px; line-height: 30px;">+标签</button>
				</div>
			</div>
		</div>
		<!--多选题结束-->

		<!--判断题开始-->
		<div class="" id="judgeselect">
			<div class="layui-form-item" style="margin: 20px 0px">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>判断题题目</label>
					<div class="layui-input-inline" style="width: 400px;">
						<textarea
							style="width: 710px; height: 200px; border-radius: 5px; text-index: 0 !important;"
							placeholder="请输入题目" class="layui-textarea" name="judgeDesc"
							id="judgeDesc" lay-verify="judgeDesc" autocomplete="off"></textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>正确答案</label>
					<div class="layui-input-block" style="width: 300px;">
						<input type="radio" name="answer2" lay-verify="answer3" value="1"
							title="正确" checked=""> <input type="radio" name="answer2"
							lay-verify="answer3" value="0" title="错误">
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;">题目释义</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="请输入题目释义" class="layui-textarea" name="answerDesc2"
							lay-verify="" id="answerDesc3"></textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 150px;">
						<select name="source2" id="source3" lay-verify="source3">
							<c:forEach items="${qsList}" var="qs">
								<option value="${qs.sourceId}">${qs.sourceName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>难度</label>
					<div class="layui-input-inline" style="width: 150px;">
						<ul class="uls_showstars2 mg6">
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

					</div>
					<input type="number" min="0" max="100" hidden="" name="degree2"
						id="degree2" value="2" lay-verify="degree3" />
				</div>
			</div>
			<div class="layui-form-item"
				style="margin: 10px 0; paddding-bottom: 50px;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>标签</label>
					<table
						style="width: 40%; position: absolute; left: 120px; font-size: 14px;">
						<tbody id="tbody4">
						</tbody>
					</table>
					<input hidden="" name="tag2" lay-verify="tag3" id="tag3" />
					<button type="button" class="layui-btn layui-btn-normal"
						data-toggle="modal" onclick="openModal();"
						style="margin-top: 10px; font-size: 14px; margin-left: 160px; background-color: #008bca; border: 0; border-radius: 4px; height: 30px; line-height: 30px;">+标签</button>
				</div>
			</div>
		</div>
		<!--判断题结束-->

		<!--阅读理解开始-->
		<div class="" id="readselect" style="padding-bottom: 100px">
			<div class="layui-form-item" style="margin: 20px 0">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>阅读理解</label>
					<div class="layui-input-inline" style="width: 400px;">
						<textarea style="width: 710px; height: 300px; border-radius: 5px;"
							placeholder="请输入题目" class="layui-textarea" name="readDesc"
							id="readDesc-Edtior" lay-verify="readDesc" autocomplete="off"></textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 20px 0">

				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 150px;">
						<select name="source_read" id="source_read"
							lay-verify="source_read">
							<c:forEach items="${qsList}" var="qs">
								<option value="${qs.sourceId}">${qs.sourceName}</option>
							</c:forEach>
						</select>
					</div>

					<div class="layui-inline">
						<label class="layui-form-label"
							style="width: 120px; font-size: 14px;"><span
							style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>难度</label>
						<div class="layui-input-inline"
							style="width: 100px; margin-top: 8px;">
							<ul class="uls_showstars3" style="margin-top: -1px">
								<li class="icon-star uls_showstars3_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</div>
						<input type="number" min="0" max="100" name="degree_read"
							hidden="" id="degree_read" lay-verify="degree_read" value="2" />
					</div>

				</div>

				<div class="layui-inline">
					<table
						style="padding: 5px 20px; font-size: 14px; margin: 0; display: inline">
						<tbody id="tbody5">
						</tbody>
					</table>
					<input hidden="" name="tag_read" lay-verify="tag4" id="tag4" />
					<button type="button" class="layui-btn layui-btn-normal"
						data-toggle="modal" onclick="openModal();"
						style="margin-bottom: 20px; background-color: #008bca; border: 0; border-radius: 4px; height: 30px; line-height: 30px;">+标签</button>
				</div>

			</div>
		</div>
		<!--阅读理解结束-->
		
		<!--完形填空开始-->
		<div class="" id="wxfill" style="padding-bottom: 100px">
			<div class="layui-form-item" style="margin: 20px 0">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>完形填空</label>
					<div class="layui-input-inline" style="width: 400px;">
						<textarea style="width: 810px; height: 300px; border-radius: 5px;"
							placeholder="请输入题目" class="layui-textarea" name="clozeContent"
							id="clozeContent" lay-verify="clozeContent" autocomplete="off"></textarea>
					</div>
				</div>
			</div>
	
			<div class="layui-form-item" style="margin: 20px 0">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 150px;">
						<select name="questionSource" id="source2017"
							lay-verify="source2017">
							<c:forEach items="${qsList}" var="qs">
								<option value="${qs.sourceId}">${qs.sourceName}</option>
							</c:forEach>
						</select>
					</div>

					<div class="layui-inline">
						<label class="layui-form-label"
							style="width: 120px; font-size: 14px;"><span
							style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>难度</label>
						<div class="layui-input-inline"
							style="width: 100px; margin-top: 8px;">
							<ul class="uls_showstars3" style="margin-top: -1px">
								<li class="icon-star uls_showstars3_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</div>
						<input type="number" min="0" max="100" name="questionDegree"
							hidden="" id="Degree2017" lay-verify="degree_read" value="2" />
					</div>

				</div>

				<div class="layui-inline">
					<table
						style="padding: 5px 20px; font-size: 14px; margin: 0; display: inline">
						<tbody id="tbody8">
						</tbody>
					</table>
					<input hidden="" name="tagId" lay-verify="tag7" id="tag7" />
					<button type="button" class="layui-btn layui-btn-normal"
						data-toggle="modal" onclick="openModal();"
						style="margin-bottom: 20px; background-color: #008bca; border: 0; border-radius: 4px; height: 30px; line-height: 30px;">+标签</button>
				</div>
				</div>
		</div>
		<!--完形填空结束-->

		<!--主观题开始-->
		<div class="" id="subId">
			<div class="layui-form-item" style="margin: 20px 0">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>主观题目</label>
					<div class="layui-input-inline" style="width: 400px;">
						<textarea
							style="width: 710px; height: 200px; border-radius: 5px; text-index: 0 !important;"
							placeholder="请输入主观题目" lay-verify="subjectiveTitle"
							class="layui-textarea" name="subjectiveTitle"
							id="subjectiveTitle"></textarea>
					</div>
				</div>
			</div>

			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;">答案释义</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="该题。。。" lay-verify="answerAnalysis"
							class="layui-textarea" name="answerAnalysis" id="answerAnalysis"></textarea>
					</div>
				</div>
			</div>

			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 150px;">
						<select name="questionSource" id="questionSource"
							lay-verify="questionSource">
							<c:forEach items="${qsList}" var="qs">
								<option value="${qs.sourceId}">${qs.sourceName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>难度</label>
					<div class="layui-input-inline" style="width: 150px;">
						<ul class="uls_showstars4 mg6">
							<li class="icon-star uls_showstars4_1" id="1"
								style="cursor: pointer;"></li>
							<li class="icon-star uls_showstars4_2" id="2"
								style="cursor: pointer;"></li>
							<li class="icon-star-empty uls_showstars4_3" id="3"
								style="cursor: pointer;"></li>
							<li class="icon-star-empty uls_showstars4_4" id="4"
								style="cursor: pointer;"></li>
							<li class="icon-star-empty uls_showstars4_5" id="5"
								style="cursor: pointer;"></li>
						</ul>
					</div>
					<input type="number" min="0" max="100" hidden=""
						name="questionDegree" id="questionDegree" value="2"
						lay-verify="questionDegree" />
				</div>
			</div>

			<div class="layui-form-item"
				style="margin: 10px 0; padding-bottom: 50px;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>标签</label>
					<table
						style="width: 40%; position: absolute; left: 120px; font-size: 14px;">
						<tbody id="tbody6">

						</tbody>
					</table>
					<input hidden="" name="tagId" lay-verify="tagId" id="tag5" />
					<button type="button" class="layui-btn layui-btn-normal"
						data-toggle="modal" onclick="openModal();"
						style="margin-top: 10px; margin-left: 160px; background-color: #008bca; border: 0; border-radius: 4px; height: 30px; line-height: 30px;">+标签</button>
				</div>
			</div>
		</div>
		<!--主观题结束-->

		<!--填空题开始-->
		<div class="" id="fillId">
			<div class="layui-form-item" style="margin: 20px 0">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>填空题目</label>
					<div class="layui-input-inline" style="width: 400px;">
						<textarea
							style="width: 710px; height: 200px; border-radius: 5px; text-index: 0 !important;"
							placeholder="请输入填空题目" lay-verify="fillTitle"
							class="layui-textarea" name="fillTitle" id="fillTitle"></textarea>
					</div>
				</div>
				<p style="color: red; margin-left: 120px;">备注：填空题所需填空处请以两个或两个以上"_"分割开</p>
			</div>

			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>答案</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="请输入题目答案" lay-verify="fillAnswer"
							class="layui-textarea" name="fillAnswer" id="fillAnswer"></textarea>
					</div>
				</div>
				<p style="color: red; margin-left: 120px;">备注：有多个答案时,答案以"##"号分割开</p>
			</div>
			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;">题目释义</label>
					<div class="layui-input-inline" style="width: 300px;">
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="该题。。。" class="layui-textarea" name="answerAnalysis"
							id="answerAnalysis1" lay-verify=""></textarea>
					</div>
				</div>
			</div>

			<div class="layui-form-item" style="margin: 20px 0;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 150px;">
						<select name="fillSourse" id="questionSource1"
							lay-verify="questionSource1">
							<c:forEach items="${qsList}" var="qs">
								<option value="${qs.sourceId}">${qs.sourceName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>难度</label>
					<div class="layui-input-inline" style="width: 150px;">
						<ul class="uls_showstars5 mg6">
							<li class="icon-star uls_showstars5_1" id="1"
								style="cursor: pointer;"></li>
							<li class="icon-star uls_showstars5_2" id="2"
								style="cursor: pointer;"></li>
							<li class="icon-star-empty uls_showstars5_3" id="3"
								style="cursor: pointer;"></li>
							<li class="icon-star-empty uls_showstars5_4" id="4"
								style="cursor: pointer;"></li>
							<li class="icon-star-empty uls_showstars5_5" id="5"
								style="cursor: pointer;"></li>
						</ul>
					</div>
					<input type="number" min="0" max="100" hidden=""
						name="questionDegree" id="questionDegree" value="2"
						lay-verify="questionDegree1" />
				</div>
			</div>

			<div class="layui-form-item"
				style="margin: 10px 0; padding-bottom: 50px;">
				<div class="layui-inline">
					<label class="layui-form-label"
						style="width: 120px; font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>标签</label>
					<table
						style="width: 40%; position: absolute; left: 120px; font-size: 14px;">
						<tbody id="tbody7">

						</tbody>
					</table>
					<input hidden="" name="tagId" lay-verify="tagId1" id="tag6" />
					<button type="button" class="layui-btn layui-btn-normal"
						data-toggle="modal" onclick="openModal();"
						style="margin-top: 10px; margin-left: 160px; background-color: #008bca; border: 0; border-radius: 4px; height: 30px; line-height: 30px;">+标签</button>
				</div>
			</div>
		</div>
		<!--填空题结束-->

		<!-- 单选快速了录入试题答案模态框开始 -->
		<div class="modal fade" id="myModal-luru" tabindex="-1" role="dialog1"
			aria-labelledby="myModalLabel1">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">录入答案选项</h4>
					</div>
					<div class="modal-body">
						<p>备注：A、B、C、D四个选项以"##"号分割开</p>
						<textarea style="width: 100%; height: 300px" id="getanswer"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="getAllAnswer()"
							style="height: 30px; line-height: 30px; padding: 0 18px;">确认</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							style="height: 30px; line-height: 30px; padding: 0 18px;">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 单选快速了录入试题答案模态框结束 -->

		<!-- 多选快速了录入试题答案模态框开始 -->
		<div class="modal fade" id="myModal-duoxuan" tabindex="-1"
			role="dialog2" aria-labelledby="myModalLabel2">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">录入答案选项</h4>
					</div>
					<div class="modal-body">
						<p>备注：A、B、C、D四个选项以"##"号分割开</p>
						<textarea style="width: 100%; height: 300px" id="getmutianswer"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="getMutiAnswer()">确认</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 多选快速了录入试题答案模态框结束 -->
		<!--模态框-->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog" style="width: 90%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel"><span id="chooseGraSub"></span>标签</h4>
					</div>
					<div class="modal-body">
						<div class="layui-form-item">
							<div class="layui-inline">
								<div class="layui-input-inline" style="width: 200px;">
									<input type="text" id="input_tag" autocomplete="off"
										placeholder="请输入要查找的标签，如there" class="layui-input">
								</div>
								<button type="button"
									class="layui-btn layui-btn-normal searchbtn" id="select_tag">查找</button>
							</div>
						</div>
						<!-- 标签内容开始 -->
						<div class="sign-content">
							<div class="row" id="tag_list" style="text-align: center">
								<!-- 标签内容 -->
							</div>
							<div class="row">
								<div class="col-md-12" id="pages" style="text-align: center"></div>
							</div>
						</div>
						<!-- 标签内容结束 -->
						<!-- 创建标签 开始-->
						<div class="layui-form-item"
							style="margin-top: 10px; padding: 20px; border: 1px solid #e2e2e2">
							
							<div class="layui-inline">
								<label class="layui-form-label"
									style="width: 120px; font-size: 14px;"><span
									style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>创建新标签</label>
								<div class="layui-input-inline" style="width: 150px;">
									<input type="text" id="create_input_tag" autocomplete="off"
										placeholder="请输入标签名称" class="layui-input">
								</div>
								<button type="button"
									class="layui-btn layui-btn-normal searchbtn bt10"
									id="create_tag" style="">创建</button>
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
		
		
		<!--  -->
<script type="text/html" id="read_template">
		{{each objects as value i}}
		<div class="layui-form-item" style="margin-top:10px;border-top:1px solid #f1f1f1;padding-top:10px;"></div>
	<div class="layui-form-item" style="margin: 20px 0">		      
           <div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;"><span style="color:red;font-size:16px !important"></span>{{i+1}}、</label>
<div class="layui-input-inline" style="width: 400px;">
					<textarea name="Optiontitle{{i}}" id="Optiontitle{{i}}" placeholder="请输入题目" 
						style="width: 710px; height: 200px; border-radius: 5px;"
						></textarea>
</div>
	</div>
	</div>
	<div class="layui-form-item" style="margin: 20px 0">
		<div class="layui-inline">
			<label class="layui-form-label" style="width:120px"><span style="color:red;font-size:16px !important"></span>A</label>
			<div class="layui-input-inline" style="width: 300px;">
				<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入A选项的内容" class="layui-textarea" id="OptionA_read{{i}}"
					autocomplete="off"></textarea>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label"><span style="color:red;font-size:16px !important"></span>B</label>
			<div class="layui-input-inline" style="width: 300px;">
				<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入B选项的内容" class="layui-textarea" id="OptionB_read{{i}}"
					autocomplete="off"></textarea>
			</div>
		</div>
	</div>
	<div class="layui-form-item" style="margin: 20px 0">
		<div class="layui-inline">
			<label class="layui-form-label" style="width:120px"><span style="color:red;font-size:16px !important"></span>C</label>
			<div class="layui-input-inline" style="width: 300px;">
				<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入C选项的内容" class="layui-textarea" id="OptionC_read{{i}}"
					autocomplete="off"></textarea>		
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label"><span style="color:red;font-size:16px !important"></span>D</label>
			<div class="layui-input-inline" style="width: 300px;">
				<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入D选项的内容" class="layui-textarea" id="OptionD_read{{i}}"
					autocomplete="off"></textarea>				
			</div>
		</div>
	</div>
	<div class="layui-form-item" style="margin:20px 0;">
		<div class="layui-inline">
			<div class="layui-inline">
			<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important"></span>正确答案</label>
			<div class="layui-input-block" style="width:300px;">
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
		</div>
		<div class="layui-form-item" style="margin: 20px 0">	   
           <div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;">题目释义</label>
			<div class="layui-input-inline" style="width: 300px;">
					<textarea name="answerDesc_read{{i}}" id="answerDesc_read{{i}}" placeholder="请输入题目释义" 
						style="width: 710px; height: 200px; border-radius: 5px;"
						></textarea> 
           </div>
		</div>
	</div> 
</div>
	{{/each}}
		</script>
		<!-- 完形填空模板 -->
		<script type="text/html" id="wxfill_template">
		{{each objects as value i}}
		<div class="layui-form-item" style="margin-top:10px;border-top:1px solid #f1f1f1;padding-top:10px;"></div>
	<div class="layui-form-item" style="margin: 20px 0">
	<label class="layui-form-label" style="width:60px;">{{i+1}}、</label>
		<div class="layui-inline">
			<label class="layui-form-label" style="width: 60px"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>A</label>
			<div class="layui-input-inline" style="width: 300px;">
				<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入A选项的内容" class="layui-textarea" id="clozeOptionA{{i}}"
					autocomplete="off"></textarea>
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>B</label>
			<div class="layui-input-inline" style="width: 300px;">
				<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入B选项的内容" class="layui-textarea" id="clozeOptionB{{i}}"
					autocomplete="off"></textarea>
			</div>
		</div>
	</div>
	<div class="layui-form-item" style="margin: 20px 0">
		<div class="layui-inline">
			<label class="layui-form-label" style="width:120px"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>C</label>
			<div class="layui-input-inline" style="width: 300px;">
				<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入C选项的内容" class="layui-textarea" id="clozeOptionC{{i}}"
					autocomplete="off"></textarea>		
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>D</label>
			<div class="layui-input-inline" style="width: 300px;">
				<textarea style="width: 300px; height: 50px; border-radius: 5px;"
							placeholder="请输入D选项的内容" class="layui-textarea" id="clozeOptionD{{i}}"
					autocomplete="off"></textarea>				
			</div>
		</div>
	</div>
	<div class="layui-form-item" style="margin:20px 0;">
		<div class="layui-inline">
			<div class="layui-inline">
			<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>正确答案</label>
			<div class="layui-input-block" style="width:300px;">
				<input type="radio" name="clozeAnswer{{i}}" lay-verify="answer" value="A"
						title="A" checked=""> 
				<input type="radio" name="clozeAnswer{{i}}"
						lay-verify="answer" value="B" title="B"> 
				<input
						type="radio" name="clozeAnswer{{i}}" lay-verify="answer" value="C" title="C">
				<input type="radio" name="clozeAnswer{{i}}" lay-verify="answer" value="D"
						title="D">
			</div>
			</div>
		</div>
		<div class="layui-form-item" style="margin: 20px 0">	   
           <div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;">题目释义</label>
			<div class="layui-input-inline" style="width: 300px;">
					<textarea name="analysis{{i}}" id="analysis{{i}}" placeholder="请输入题目释义" 
						style="width: 710px; height: 200px; border-radius: 5px;"
						></textarea> 
           </div>
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
	var state = 1;//用来判断当前是什么题型:默认是单选题
	var pageNo = 1, pageSize =100;
	var totalScore = 0;//试卷总分
	var editIndex;
	var sId=1;//默认语文
	 $(document).ready(			
			function() {
				var data = {
						objects : [ {}, {}, {}, {}, {}, {}, {}, {}, {}, {} ]
					};
					var html = template("read_template", data);
					$('#readselect').append(html);
					$('.uls_showstars3 li').on('click',
									function() {
										var id = $(this).attr('id');
										for (var i = 1; i <= id; i++) {
											$(".uls_showstars3_" + i).removeClass()
													.addClass('icon-star uls_showstars3_'+ i);
											for (var j = 5; j > id; j--) {
												$(".uls_showstars3_" + j).removeClass().addClass(
																'icon-star-empty uls_showstars3_'+ j);
											}
										}
										$('#degree_read').val(i - 1);
									})

			});
	 $(document).ready(			
				function() {
					var data = {
							objects : [ {}, {}, {}, {}, {}, {}, {}, {}, {}, {} ]
						};
						var html = template("wxfill_template", data);
						$('#wxfill').append(html);
						$('.uls_showstars3 li').on('click',
										function() {
											var id = $(this).attr('id');
											for (var i = 1; i <= id; i++) {
												$(".uls_showstars3_" + i).removeClass()
														.addClass('icon-star uls_showstars3_'+ i);
												for (var j = 5; j > id; j--) {
													$(".uls_showstars3_" + j).removeClass().addClass(
																	'icon-star-empty uls_showstars3_'+ j);
												}
											}
											$('#Degree2017').val(i - 1);
										})

				});
	function read() {
	} 
	
    
	layui.use([ 'form', 'layedit', 'laypage', 'laydate' ],
					function() {
						 form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;							
						//创建一个编辑器
						editIndex = layedit.build('LAY_demo_editor', {
							tool : [ 'strong', 'italic', 'underline', 'del',
									'|', 'left', 'center', 'right' ]
						});
	
						$('#confirm2').on('click',function() {
											var jsonstr = '[';
											layedit.sync(editIndex);//同步内容
											var subject = $('#subject').val();
											var type = $('#type').val();
											var grade = $('#knowledge').val();
											var begin = $('#knowledgeBegin').val();
											var tag_read = $('#tag4').val();
											var degrees = $('#degree_read').val();
											var sources = $('#source_read').val();
											if (grade == '') {
												layer.msg("请选择年级");
												return;												
											} else if (subject == '') {
												layer.msg("请选择科目");
												return;												
											} else if (type == '') {
												layer.msg("请选择类型");
												return;
											} else if (tag_read == '') {
												layer.msg("请选择题目标签");
												return;
											} else if (degrees == '') {
												layer.msg("请选择题型难度");
												return;
											} else if (sources == '') {
												layer.msg("请选择题目来源");
												return;
											}
											for (var i = 0; i < 10; i++) {
												var title = $('textarea[name="Optiontitle'+i+'"')
														.val();
												var oA = $('#OptionA_read' + i).val();
												var oB = $('#OptionB_read' + i).val();
												var oC = $('#OptionC_read' + i).val();
												var oD = $('#OptionD_read' + i).val();
												var answer = $('input[name="answer_read'+ i+'"]:checked').val();
												var answerdesc = $('textarea[name="answerDesc_read'+i+'"').val();
												// var parentid =  parentId;
												var score = $('#score_read' + i).val();
												if (i == 0) {
													if (title == "") {
														layer.msg("请至少输入一道题目");
														return;
													} else if (oA == "") {
														layer.msg("请输入A选项的内容");
														return;
													} else if (oB == "") {
														layer.msg("请输入B选项的内容");
														return;
													} else if (oC == "") {
														layer.msg("请输入C选项的内容");
														return;
													} else if (oD == "") {
														layer.msg("请输入D选项的内容");
														return;
													} else if (answer == "") {
														layer.msg("请选择正确答案");
														return;
													} 
												}
												totalScore += score;
												jsonstr += "{";
												jsonstr += '"Optiontitle":"'+ title + '",';
												jsonstr += '"OptionA_read":"'+ oA + '",';
												jsonstr += '"OptionB_read":"'+ oB + '",';
												jsonstr += '"OptionC_read":"'+ oC + '",';
												jsonstr += '"OptionD_read":"'+ oD + '",';
												jsonstr += '"type":"' + type+ '",';
												jsonstr += '"subject":"'+ subject + '",';
												jsonstr += '"answer_read":"'+ answer + '",';
												jsonstr += '"answerDesc_read":"'+ answerdesc + '",';
												jsonstr += '"score_read":"'+ score + '"';
												jsonstr += "},";
											}
											$('#totalScore').val(totalScore);
											jsonstr = jsonstr.substring(0,
													jsonstr.length - 1);
											jsonstr += ']';
											// index = layer.load(1);
											ajax_read()
											.then(function(res) {
														//得到parentId
														var parentId = res.objects[0].readId;
														ajax_read1(
																jsonstr,
																parentId)
																.then(function(res) {
																			layer.close(index);
																			window.location.href = "myquestionbank.do?handoutType="+4+"&subject="+subject;
																		},
																		function() {
																			layer.msg("抱歉，添加失败，请重试！");
																		});
													},
													function(error) {
														layer.close(index);
														layer.msg(error.msg);
													});
										})
										
										
										                        									
						$('#confirm3').on('click',function() {
											var jsonstr = '[';
											layedit.sync(editIndex);//同步内容
											var subject = $('#subject').val();
											var type = $('#type').val();
											var grade = $('#knowledge').val();
											var begin = $('#knowledgeBegin').val();
											var tagId = $('#tag7').val();
											var degrees = $('#Degree2017').val();
											var sources = $('#source2017').val();
											var content = $("textarea[name='clozeContent']").val();
// 											var content = $('#clozeContent').val();
											if (grade == '') {
												layer.msg("请选择年级");
												return;												
											} else if (subject == '') {
												layer.msg("请选择科目");
												return;												
											} else if (type == '') {
												layer.msg("请选择类型");
												return;
											} else if (tagId == '') {
												layer.msg("请选择题目标签");
												return;
											} else if (degrees == '') {
												layer.msg("请选择题型难度");
												return;
											} else if (sources == '') {
												layer.msg("请选择题目来源");
												return;
											}else if(content ==''){
												layer.msg("请填写题目内容");
												return;
											}
											for (var i = 0; i < 10; i++) {
												var oA = $('#clozeOptionA' + i).val();
												var oB = $('#clozeOptionB' + i).val();
												var oC = $('#clozeOptionC' + i).val();
												var oD = $('#clozeOptionD' + i).val();
												var clozeAnswer = $('input[name="clozeAnswer'+ i+'"]:checked').val();
												var analysis = $('textarea[name="analysis'+i+'"').val();
												if (i == 0) {
													if (oA == "") {
														layer.msg("请输入A选项的内容");
														return;
													} else if (oB == "") {
														layer.msg("请输入B选项的内容");
														return;
													} else if (oC == "") {
														layer.msg("请输入C选项的内容");
														return;
													} else if (oD == "") {
														layer.msg("请输入D选项的内容");
														return;
													} else if (clozeAnswer == "") {
														layer.msg("请选择正确答案");
														return;
													} 
												}
												jsonstr += "{";
												jsonstr += '"clozeOptionA":"'+ oA + '",';
												jsonstr += '"clozeOptionB":"'+ oB + '",';
												jsonstr += '"clozeOptionC":"'+ oC + '",';
												jsonstr += '"clozeOptionD":"'+ oD + '",';
												jsonstr += '"type":"' + type+ '",';
												jsonstr += '"subject":"'+ subject + '",';
												jsonstr += '"clozeAnswer":"'+ clozeAnswer + '",';
												jsonstr += '"analysis":"'+ analysis+'"';
												jsonstr += "},";
											}
											jsonstr = jsonstr.substring(0,jsonstr.length - 1);
											jsonstr += ']';
											$.ajax({
												url:'addCloze.do', 
												type:'post',
												dataType:'json',
												data:{
													'jsonstr':jsonstr,
													'knowledge':grade,
													'knowledgeBegin':begin,
													'subject':subject,
											        'clozeContent':content,
											        'tagId':tagId,
											        'questionDegree':degrees,
											        'questionSource':sources
												},
												success : function(res) {
													if (res.code == 0) {
														window.location.href = "myquestionbank.do?handoutType=7";
													} else  {
													  alert('添加失败!');
													}
												}
											})
												
												
									})
                        
						form.render();//渲染		
						//重置标签
						form.on('select(subjectabc)',function(data){
							  var subjectId = data.value;
							  //重置标签显示
							  $("#tbody2").html('');
							  $("#tbody3").html('');
							  $("#tbody4").html('');
							  $("#tbody5").html('');
							  $("#tbody6").html('');
							  $("#tbody7").html('');
							  $("#tbody8").html('');
							  //重置标签赋值
							  $('#tag1').val('');
							  $('#tag2').val('');
							  $('#tag3').val('');
							  $('#tag4').val('');
							  $('#tag5').val('');
							  $('#tag6').val('');		
							  $('#tag7').val('');		
						})
						form.on('select(select_title)', function(data) {
							//单选题目
							 var ue = UE.getEditor('choiceDesc1',{
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
												'spechars', //特殊字符
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
												'kityformula',	
												'underline' //下划线
								            
								           ]
								         ],
								         elementPathEnabled:false,
								         autoHeightEnabled:false,
								         imagePopup:false
							 });
							//单选答案
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
								         autoHeightEnabled:false
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
								         autoHeightEnabled:false
							});
							var ue3 = UE.getEditor('OptionC1',{
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
								         autoHeightEnabled:false
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
								         autoHeightEnabled:false
							});
							//单选答案描述
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
												'subscript', //下标
												'superscript', //上标
												//'forecolor', //字体颜色
												//'backcolor', //背景色
												'spechars', //特殊字符
												'kityformula',
												'underline' //下划线
								           ]
								         ],
								         elementPathEnabled:false,
								         autoHeightEnabled:false
							 });
							var vlaue = data.value;
							if (vlaue == 1) {//单选
								state = 1;
								radioselect.css('display', 'block');
								multiselect.css('display', 'none');
								judgeselect.css('display', 'none');
								readselect.css('display', 'none');
								subId.css('display', 'none');
								fillId.css('display', 'none');
								wxfill.css('display', 'none');
								filltype.css('display','none');
								$('#confirm1').css('display', 'block');
								$('#confirm2').css('display', 'none');
								$('#confirm3').css('display', 'none');
								//自定义验证规则
								form.verify({
									<!--过滤非认证的开始-->
									//过滤多选
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
									answer2 : function(value) {
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
									//过滤判断
									judgeDesc : function(value) {
									},
									source3 : function(value) {
									},
									degree3 : function(value) {	
									},
									ispublic3 : function(value) {
									},
									tag3 : function(value) {
									},
									//过滤主观题
									subjectiveTitle : function(value) {
									},
									questionSource : function(value) {
									},
									questionDegree : function(value) {
									},
									isPublic : function(value) {
									},
									tagId : function(value) {
									},
									//过滤填空
									fillTitle : function(value) {
									},
									fillAnswer : function(value) {
									},
									questionSource1 : function(value) {
									},
									questionDegree1 : function(value) {
									},
									isPublic1 : function(value) {
									},
									tagId1 : function(value) {
									},																														
									
									<!--过滤非认证的结束-->
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
								$('#myform').attr('action','addSingleChoice.do');
							}
							if (vlaue == 2) {//多选
								//多选题
								 var ue6 = UE.getEditor('choiceDesc2',{
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
													'subscript', //下标
													'superscript', //上标
													//'forecolor', //字体颜色
													//'backcolor', //背景色
													'spechars', //特殊字符
													'kityformula',
													'underline' //下划线
									           ]
									         ],
									         elementPathEnabled:false,
									         autoHeightEnabled:false
								 });
								 
								//多选答案
								 var ue7 = UE.getEditor('OptionA2',{
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
									         autoHeightEnabled:false
								  });
								 var ue8 = UE.getEditor('OptionB2',{
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
									         autoHeightEnabled:false
								 });
								 var ue9 = UE.getEditor('OptionC2',{
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
									         autoHeightEnabled:false
								 });
								 var ue10 = UE.getEditor('OptionD2',{
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
									         autoHeightEnabled:false
								 });
								 
								 var ue11 = UE.getEditor('answerDesc2',{
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
													'subscript', //下标
													'superscript', //上标
													//'forecolor', //字体颜色
													//'backcolor', //背景色
													'spechars', //特殊字符
													'kityformula',
													'underline' //下划线
									           ]
									         ],
									         elementPathEnabled:false,
									         autoHeightEnabled:false
								 });
								state = 2;
								radioselect.css('display', 'none');
								multiselect.css('display', 'block');
								judgeselect.css('display', 'none');
								readselect.css('display', 'none');
								subId.css('display', 'none');
								fillId.css('display', 'none');
								wxfill.css('display', 'none');
								filltype.css('display','none');
								$('#confirm1').css('display', 'block');
								$('#confirm2').css('display', 'none');
								$('#confirm3').css('display', 'none');
								form.verify({
									<!--过滤开始-->
									 //过滤单选
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
									//过滤判断
									judgeDesc : function(value) {
									},
									source3 : function(value) {
									},
									degree3 : function(value) {	
									},
									ispublic3 : function(value) {
									},
									tag3 : function(value) {
									},
									//过滤主观题
									subjectiveTitle : function(value) {
									},
									questionSource : function(value) {
									},
									questionDegree : function(value) {
									},
									isPublic : function(value) {
									},
									tagId : function(value) {
									},
									//过滤填空
									fillTitle : function(value) {
									},
									fillAnswer : function(value) {
									},
									questionSource1 : function(value) {
									},
									questionDegree1 : function(value) {
									},
									isPublic1 : function(value) {
									},
									tagId1 : function(value) {
									},					

									<!--过滤结束-->
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
									answer2 : function(value) {
										if (value.length <= 0) {
											return '您还未选择正确答案';
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
								$('#myform').attr('action', 'addMulitChoice.do');
							} else if (vlaue == 3) {//判断
								//判断题
								 var ue12 = UE.getEditor('judgeDesc',{
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
													'subscript', //下标
													'superscript', //上标
													//'forecolor', //字体颜色
													//'backcolor', //背景色
													'spechars', //特殊字符
													'kityformula',
													'underline' //下划线
									           ]
									         ],
									         elementPathEnabled:false,
									         autoHeightEnabled:false
								 });
								 var ue13 = UE.getEditor('answerDesc3',{
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
													'subscript', //下标
													'superscript', //上标
													//'forecolor', //字体颜色
													//'backcolor', //背景色
													'spechars', //特殊字符
													'kityformula',
													'underline' //下划线
									           ]
									         ],
									         elementPathEnabled:false,
									         autoHeightEnabled:false
								 });
								 
								state = 3;
								radioselect.css('display', 'none');
								multiselect.css('display', 'none');
								judgeselect.css('display', 'block');
								readselect.css('display', 'none');
								subId.css('display', 'none');
								fillId.css('display', 'none');
								wxfill.css('display','none');
								filltype.css('display','none');
								$('#confirm1').css('display', 'block');
								$('#confirm2').css('display', 'none');
								$('#confirm3').css('display', 'none');
								form.verify({
									<!--过滤开始-->
									//过滤单选
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
									//过滤多选
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
									answer2 : function(value) {
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
									//过滤主观题
									subjectiveTitle : function(value) {
									},
									questionSource : function(value) {
									},
									questionDegree : function(value) {
									},
									isPublic : function(value) {
									},
									tagId : function(value) {
									},
									//过滤填空
									fillTitle : function(value) {
									},
									fillAnswer : function(value) {
									},
									questionSource1 : function(value) {
									},
									questionDegree1 : function(value) {
									},
									isPublic1 : function(value) {
									},
									tagId1 : function(value) {
									},					
									<!--过滤结束-->
									judgeDesc : function(value) {
										if (value.length <= 0) {
											return '您还未输入题目';
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
								$('#myform').attr('action', 'addJudge.do');
							} else if (vlaue == 4) {//阅读
								//getReadObject();//遍历阅读
								//阅读理解题目:Optiontitle
								   var ue15 = UE.getEditor('Optiontitle0',{
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
													'subscript', //下标
													'superscript', //上标
													//'forecolor', //字体颜色
													//'backcolor', //背景色
													'spechars', //特殊字符
													'kityformula',
													'underline' //下划线
									           ]
									         ],
									         elementPathEnabled:false,
									         autoHeightEnabled:false
								 });
								   var ue16 = UE.getEditor('Optiontitle1',{
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
														'subscript', //下标
														'superscript', //上标
														//'forecolor', //字体颜色
														//'backcolor', //背景色
														'spechars', //特殊字符
														'kityformula',
														'underline' //下划线
										           ]
										         ],
										         elementPathEnabled:false,
										         autoHeightEnabled:false
									 });
								   var ue17 = UE.getEditor('Optiontitle2',{
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
														'subscript', //下标
														'superscript', //上标
														//'forecolor', //字体颜色
														//'backcolor', //背景色
														'spechars', //特殊字符
														'kityformula',
														'underline' //下划线
										           ]
										         ],
										         elementPathEnabled:false,
										         autoHeightEnabled:false
									 });
								   var ue18 = UE.getEditor('Optiontitle3',{
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
														'subscript', //下标
														'superscript', //上标
														//'forecolor', //字体颜色
														//'backcolor', //背景色
														'spechars', //特殊字符
														'kityformula',
														'underline' //下划线
										           ]
										         ],
										         elementPathEnabled:false,
										         autoHeightEnabled:false
									 });
								   var ue19 = UE.getEditor('Optiontitle4',{
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
														'subscript', //下标
														'superscript', //上标
														//'forecolor', //字体颜色
														//'backcolor', //背景色
														'spechars', //特殊字符
														'kityformula',
														'underline' //下划线
										           ]
										         ],
										         elementPathEnabled:false,
										         autoHeightEnabled:false
									 });
								   var ue20 = UE.getEditor('Optiontitle5',{
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
														'subscript', //下标
														'superscript', //上标
														//'forecolor', //字体颜色
														//'backcolor', //背景色
														'spechars', //特殊字符
														'kityformula',
														'underline' //下划线
										           ]
										         ],
										         elementPathEnabled:false,
										         autoHeightEnabled:false
									 });
								   var ue21 = UE.getEditor('Optiontitle6',{
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
										         autoHeightEnabled:false
									 });
								   var ue22 = UE.getEditor('Optiontitle7',{
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
										         autoHeightEnabled:false
									 });
								   var ue23 = UE.getEditor('Optiontitle8',{
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
										         autoHeightEnabled:false
									 });
								   var ue24 = UE.getEditor('Optiontitle9',{
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
										         autoHeightEnabled:false
									 });
								 //解释
								  var ue25 = UE.getEditor('answerDesc_read0',{
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
										         autoHeightEnabled:false
									 });
								  var ue26 = UE.getEditor('answerDesc_read1',{
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
										         autoHeightEnabled:false
									 });
								  var ue27 = UE.getEditor('answerDesc_read2',{
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
										         autoHeightEnabled:false
									 });
								  var ue28 = UE.getEditor('answerDesc_read3',{
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
										         autoHeightEnabled:false
									 });
								  var ue29 = UE.getEditor('answerDesc_read4',{
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
										         autoHeightEnabled:false
									 });
								  var ue30 = UE.getEditor('answerDesc_read5',{
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
										         autoHeightEnabled:false
									 });
								  
								  var ue31 = UE.getEditor('answerDesc_read6',{
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
										         autoHeightEnabled:false
									 });
								  var ue32 = UE.getEditor('answerDesc_read7',{
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
										         autoHeightEnabled:false
									 });
								  var ue33 = UE.getEditor('answerDesc_read8',{
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
										         autoHeightEnabled:false
									 });
								  var ue34 = UE.getEditor('answerDesc_read9',{
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
										         autoHeightEnabled:false
									 });
								state = 4;
								radioselect.css('display', 'none');
								multiselect.css('display', 'none');
								judgeselect.css('display', 'none');
								readselect.css('display', 'block');
								subId.css('display', 'none');
								fillId.css('display', 'none');
								wxfill.css('display', 'none');
								filltype.css('display','none');
								$('#myform').attr('action', '');
								$('#confirm1').css('display', 'none');
								$('#confirm2').css('display', 'block');
								$('#confirm3').css('display', 'none');
							}else if (vlaue == 5) {//主观题
								var ue40 = UE.getEditor('subjectiveTitle',{
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
									         autoHeightEnabled:false
								 });
								 
								 var ue41 = UE.getEditor('answerAnalysis',{
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
									         autoHeightEnabled:false
								 });	 
								state = 5;
								radioselect.css('display', 'none');
								multiselect.css('display', 'none');
								judgeselect.css('display', 'none');
								readselect.css('display', 'none');
								fillId.css('display', 'none');
								subId.css('display', 'block');
								wxfill.css('display', 'none');
								filltype.css('display','none');
								$('#confirm1').css('display', 'block');
								$('#confirm2').css('display', 'none');
								$('#confirm3').css('display', 'none');
								form.verify({
									<!--过滤开始-->
									//过滤单选
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
									//过滤多选
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
									answer2 : function(value) {
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
									//过滤判断
									judgeDesc : function(value) {
									},
									source3 : function(value) {
									},
									degree3 : function(value) {	
									},
									ispublic3 : function(value) {
									},
									tag3 : function(value) {
									},
									//过滤填空
									fillTitle : function(value) {
									},
									fillAnswer : function(value) {
									},
									questionSource1 : function(value) {
									},
									questionDegree1 : function(value) {
									},
									isPublic1 : function(value) {
									},
									tagId1 : function(value) {
									},						
									<!--过滤结束-->
									subjectiveTitle : function(value) {
										if (value.length == 0) {
											return '您还未输入题目呢';
										}
									},
									questionSource : function(value) {
										if (value.length <= 0) {
											return '您还未选择题目来源哦';
										}
									},
									questionDegree : function(value) {
										if (value.length <= 0) {
											return '您还未选择题目难度哦';
										}
									},
									isPublic : function(value) {
										if (value.length <= 0) {
											return '题目是否公开？';
										}
									},
									tagId : function(value) {
										if (value.length <= 0) {
											return '您还未选择标签呢';
										}
									}
								});
								$('#myform').attr('action', 'addSubjective.do');
							}else if(vlaue == 6){//填空题
								 
								 var ue42 = UE.getEditor('fillTitle',{
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
									         autoHeightEnabled:false
								 });	 
								 var ue43 = UE.getEditor('fillAnswer',{
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
									         autoHeightEnabled:false
								 });	 
								 var ue44 = UE.getEditor('answerAnalysis1',{
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
									         autoHeightEnabled:false
								 });	 
								state = 6;
								radioselect.css('display', 'none');
								multiselect.css('display', 'none');
								judgeselect.css('display', 'none');
								readselect.css('display', 'none');
								fillId.css('display', 'block');
								filltype.css('display','block');
								subId.css('display', 'none');
								wxfill.css('display', 'none');
								$('#confirm1').css('display', 'block');
								$('#confirm2').css('display', 'none');
								$('#confirm3').css('display', 'none');
								form.verify({
									<!--过滤开始-->
									//过滤单选
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
									//过滤多选
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
									answer2 : function(value) {
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
									//过滤判断
									judgeDesc : function(value) {
									},
									source3 : function(value) {
									},
									degree3 : function(value) {	
									},
									ispublic3 : function(value) {
									},
									tag3 : function(value) {
									},
									//过滤主观题
									subjectiveTitle : function(value) {
									},
									questionSource : function(value) {
									},
									questionDegree : function(value) {
									},
									isPublic : function(value) {
									},
									tagId : function(value) {
									},				
									<!--过滤结束-->
									fillTitle : function(value) {
										if (value.length == 0) {
											return '您还未输入题目呢';
										}
									},
									fillAnswer : function(value) {
										if (value.length == 0) {
											return '您还未输入答案呢';
										}
									},
									questionSource1 : function(value) {
										if (value.length <= 0) {
											return '您还未选择题目来源哦';
										}
									},
									questionDegree1 : function(value) {
										if (value.length <= 0) {
											return '您还未选择题目难度哦';
										}
									},
									isPublic1 : function(value) {
										if (value.length <= 0) {
											return '题目是否公开？';
										}
									},
									tagId1 : function(value) {
										if (value.length <= 0) {
											return '您还未选择标签呢';
										}
									}
								});
								$('#myform').attr('action', 'addQuestionFill.do');
							}else if(vlaue ==7){//完形填空
								//getWxfillObject();//遍历完形填空
								 var ue1142 = UE.getEditor('clozeContent',{
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
									         autoHeightEnabled:false
								 });
								state = 7;
								radioselect.css('display', 'none');
								multiselect.css('display', 'none');
								judgeselect.css('display', 'none');
								readselect.css('display', 'none');
								fillId.css('display', 'none');
								subId.css('display', 'none');
								wxfill.css('display', 'block');
								filltype.css('display','none');
								$('#myform').attr('action', '');
								$('#confirm1').css('display', 'none');
								$('#confirm2').css('display', 'none');
								$('#confirm3').css('display', 'block');
							}
						});

						form.verify({
							knowledge : function(value) {
								if (value.length <= 0) {
									return '请选择年级';
								}
							},
							subject : function(value) {
								if (value.length <= 0) {
									return '请选择科目';
								}
							},
							type : function(value) {
								if (value.length <= 0) {
									return '请选择类型';
								}
							},
							
						});

						form.on('select(select_tag)', function(data) {

						})

						form.on('checkbox(answers)', function(data) {
							var chk_value = [];
							$('input[name="answerss"]:checked').each(
									function() {
										chk_value.push($(this).attr('title'));
									});
							$('#answer_id').val(chk_value.join(","));
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
	var subId = $('#subId');
	var fillId=$('#fillId');
	var wxfill =$('#wxfill');
	var filltype = $("#filltype");
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
   
	$('.uls_showstars4 li').on(
			'click',
			function() {
				var id = $(this).attr('id');
				for (var i = 1; i <= id; i++) {
					$(".uls_showstars4_" + i).removeClass().addClass(
							'icon-star uls_showstars4_' + i);
					for (var j = 5; j > id; j--) {
						$(".uls_showstars4_" + j).removeClass().addClass(
								'icon-star-empty uls_showstars4_' + j);
					}
				}
				$('#questionDegree').val(i - 1);
			});
	
	$('.uls_showstars5 li').on(
			'click',
			function() {
				var id = $(this).attr('id');
				for (var i = 1; i <= id; i++) {
					$(".uls_showstars5_" + i).removeClass().addClass(
							'icon-star uls_showstars5_' + i);
					for (var j = 5; j > id; j--) {
						$(".uls_showstars5_" + j).removeClass().addClass(
								'icon-star-empty uls_showstars5_' + j);
					}
				}
				$('#questionDegree').val(i - 1);
			});
	function ajax_read() {
		return new Promise(function(resolve, reject) {
			//先将阅读内容存进去，然后返回parentId再去存每道题目
			$.ajax({
				url : 'addRead.do',
				type : 'post',
				dataType : 'json',
				data : $('#myform').serialize(),
				success : function(res) {
					if (res.code == 0) {
						resolve(res);//将返回数据传给resolve
					} else if (res.code == 1) {
						reject(res);//将返回失败数据传给reject
					} else if (res.code == 2) {
						reject(res);//将返回失败数据传给reject
					}
				}
			})
		});
	}
	function ajax_read1(jsonstr, parentId) {
		return new Promise(function(resolve, reject) {
			$.ajax({
				url : 'addReadDetail.do',
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
		var grade = $('#knowledge').val();
// 		if (value == "") {
// 			layer.msg("请输入您要查找的标签，如没有则可以在下方添加");
// 			return;
// 		}
		$.ajax({
			url : '${ctx}/questionTag/gettagbysearch.do',
			type : 'post',
			dataType : 'json',
			data : {
				content : value,
				subjectId :subjectId,
				grade:grade
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
		var value = $('#create_input_tag').val();
		var grade = $('#knowledge').val();
		var subjectName=$('#subject').val();//获取父级科目id
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
				subject:subjectName,
				grade : grade,
			},
			success : function(res) {
				if (res.code == 0) {
					var tagId = res.objects[0].tagId;
					sucess_totemplate(res);			
					$("#checked-"+tagId).addClass('tab-bg');
					var tagName=$("#checked-"+tagId).text();
					var html = "<tr id=tags"+tagId+" style='cursor:pointer;margin-bottom:20px;'><td style='height:30px;line-height:40px;'><span style='padding:5px 10px;border:1px solid #e6e6e6;border-radius:4px'><em class='icon-remove-sign' style='color:#ff0000;margin-right:5px'></em>"
					+ tagName + "</span></td></tr>"
					if(state == 1){
						//$('#tbody2').append(html);
						var val1 = $('#tag1').val();
						if (val1 == "") {
							$('#tag1').val(tagId);
						} else {
							$('#tag1').val(val1 + "," + tagId);
						}
						$('#tbody2').append(html);
						
					}else if(state == 2){
						var val1 = $('#tag2').val();
						if (val1 == "") {
							$('#tag2').val(tagId);
						} else {
							$('#tag2').val(val1 + "," + tagId);
						}
						$('#tbody3').append(html);
						
					}else if(state ==3){
						var val1 = $('#tag3').val();
						if (val1 == "") {
							$('#tag3').val(tagId);
						} else {
							$('#tag3').val(val1 + "," + tagId);
						}
						$('#tbody4').append(html);
						
					}else if(state ==4){
						var val1 = $('#tag4').val();
						if (val1 == "") {
							$('#tag4').val(tagId);
						} else {
							$('#tag4').val(val1 + "," + tagId);
						}
						$('#tbody5').append(html);
						
						
					}else if(state == 5){
						var val1 = $('#tag5').val();
						if (val1 == "") {
							$('#tag5').val(tagId);
						} else {
							$('#tag5').val(val1 + "," + tagId);
						}
						$('#tbody6').append(html);
						
					}else if(state == 6){
						var val1 = $('#tag6').val();
						if (val1 == "") {
							$('#tag6').val(tagId);
						} else {
							$('#tag6').val(val1 + "," + tagId);
						}
						$('#tbody7').append(html);
						
					}else if(state==7){ 
						var val1 = $('#tag7').val();
						if (val1 == "") {
							$('#tag7').val(tagId);
						} else {
							$('#tag7').val(val1 + "," + tagId);
						}
						$('#tbody8').append(html);
					}else{//预留
						
					}
					object_tb_click(tagId);
					$('#myModal').modal('hide');
				} else {
					layer.msg(res.msg);
				}
			}
		})
	})
	//进行删除操作
	function object_tb_click(id) {
		if (state == 1) {
			$('#tags' + id).click(function(res) {
				var val = $('#tag1').val();
				var vals = val.split(",");
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
// 										alert("删除后的tag："+$('#tag1').val());
					$('#tags' + id).remove();
				    $("#checked-"+id).removeClass('tab-bg');
					layer.close(index);
				});
			})
		} else if (state == 2) {
			$('#tags' + id).click(function(res) {
				var val = $('#tag2').val();
				var vals = val.split(",");
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
// 										alert("删除后的tag："+$('#tag2').val());
					$('#tags' + id).remove();
				    $("#checked-"+id).removeClass('tab-bg');
					layer.close(index);
				});
			})
		} else if (state == 3) {
			$('#tags' + id).click(function(res) {
				var val = $('#tag3').val();
				var vals = val.split(",");
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
// 										alert("删除后的tag："+$('#tag3').val());
					$('#tags' + id).remove();
				    $("#checked-"+id).removeClass('tab-bg');
					layer.close(index);
				});
			})
		} else if (state == 4) {
			$('#tags' + id).click(function(res) {
				var val = $('#tag4').val();
				var vals = val.split(",");
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
// 										alert("删除后的tag："+$('#tag4').val());
					$('#tags' + id).remove();
				    $("#checked-"+id).removeClass('tab-bg');
					layer.close(index);
				});
			})
		}else if(state == 5){
			$('#tags' + id).click(function(res) {
				var val = $('#tag5').val();
				var vals = val.split(",");
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
					$('#tag5').val(val);
					
					$('#tags' + id).remove();
				    $("#checked-"+id).removeClass('tab-bg');
					layer.close(index);
				});
			})
		}else if(state == 6){
			$('#tags' + id).click(function(res) {
				var val = $('#tag6').val();
				var vals = val.split(",");
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
					$('#tag6').val(val);
					
					$('#tags' + id).remove();
				    $("#checked-"+id).removeClass('tab-bg');
					layer.close(index);
				});
			})
		}else if(state ==7){
			$('#tags' + id).click(function(res) {
				var val = $('#tag7').val();
				var vals = val.split(",");
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
					$('#tag7').val(val);
					
					$('#tags' + id).remove();
				    $("#checked-"+id).removeClass('tab-bg');
					layer.close(index);
				});
			})
		}
	}

	function sucess_totemplate(res) {		
		var html = template('tag_template', res);
		$('#tag_list').html(html);
		$('.tagName').on('click',function() {	
					var id = $(this).attr("id");					 
				    $("#checked-"+id).addClass('tab-bg');
					var name = $(this).attr("data-name");
					var html = "<tr id=tags"+id+" style='cursor:pointer;margin-bottom:20px;'><td style='height:30px;line-height:40px;'><span style='padding:5px 10px;border:1px solid #e6e6e6;border-radius:4px'><em class='icon-remove-sign' style='color:#ff0000;margin-right:5px'></em>"
							+ name + "</span></td></tr>"
					if (state == 1) {
						var val1 = $('#tag1').val();
						var tag1s = val1.split(",");
						var status = 0;
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
						var tag2s = val1.split(",");
						var status = 0;
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
						var tag3s = val1.split(",");
						var status = 0;
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
						var tag4s = val1.split(",");
						var status = 0;
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
					}else if(state ==5){
						var val1 = $('#tag5').val();
						var tag3s = val1.split(",");
						var status = 0;
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
						$('#tbody6').append(html);
						if (val1 == "") {
							$('#tag5').val(id);
						} else {
							$('#tag5').val(val1 + "," + id);
						}
						object_tb_click(id);
					} else if(state ==6){
						var val1 = $('#tag6').val();
						var tag3s = val1.split(",");
						var status = 0;
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
						$('#tbody7').append(html);
						if (val1 == "") {
							$('#tag6').val(id);
						} else {
							$('#tag6').val(val1 + "," + id);
						}
						object_tb_click(id);
					}else if(state ==7){
						var val1 = $('#tag7').val();
						var tag3s = val1.split(",");
						var status = 0;
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
						$('#tbody8').append(html);
						if (val1 == "") {
							$('#tag7').val(id);
						} else {
							$('#tag7').val(val1 + "," + id);
						}
						object_tb_click(id);
					}
					$('#myModal').modal('hide');
				})
	}
//文本框

 //阅读理解
  var ue14 = UE.getEditor('readDesc-Edtior',{
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
					'subscript', //下标
					'superscript', //上标
					//'forecolor', //字体颜色
					//'backcolor', //背景色
					'spechars', //特殊字符
					'kityformula'
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false
 });
 
 
  
//默认单选
//单选题目
							 var ue = UE.getEditor('choiceDesc1',{
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
												'spechars', //特殊字符
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
												'kityformula',	
												'underline' //下划线
								            
								           ]
								         ],
								         elementPathEnabled:false,
								         autoHeightEnabled:false,
								         imagePopup:false
							 });
							//单选答案
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
								         autoHeightEnabled:false
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
								         autoHeightEnabled:false
							});
							var ue3 = UE.getEditor('OptionC1',{
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
								         autoHeightEnabled:false
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
								         autoHeightEnabled:false
							});
							//单选答案描述
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
												'subscript', //下标
												'superscript', //上标
												//'forecolor', //字体颜色
												//'backcolor', //背景色
												'spechars', //特殊字符
												'kityformula',
												'underline' //下划线
								           ]
								         ],
								         elementPathEnabled:false,
								         autoHeightEnabled:false
							 });
	 

  <!--结束-->
	function getAllAnswer(){	
		var content = $("#getanswer").val();
		var arr = [];
		arr=content.split("##"); 
		if(arr.length!=4){
			layer.msg('输入答案格式有误或答案不足四个');
			return false;
		} 
		ue1.setContent(arr[0]);
		ue2.setContent(arr[1]);
		ue3.setContent(arr[2]);
		ue4.setContent(arr[3]);
		$('#myModal-luru').modal('hide');
		
	}
	function getMutiAnswer(){	
		var content = $("#getmutianswer").val();
		var arr = [];
		arr=content.split("##"); 
		if(arr.length!=4){
			layer.msg('输入答案格式有误或答案不足四个');
			return false;
		} 
		ue7.setContent(arr[0]);
		ue8.setContent(arr[1]);
		ue9.setContent(arr[2]);
		ue10.setContent(arr[3]);
		$('#myModal-duoxuan').modal('hide');
		
	}
	
	function openModal(){		
		 //重置文本框
		 $('#create_input_tag').val('');
		 $('#input_tag').val('');
		 var subjectId = $("#subject").val();
		 var grade = $('#knowledge').val();
		 var subjectName = $("#subject option:selected").attr("data-name");
		 var gradeName = $('#knowledge option:selected').attr("data-name");
		 var signName = gradeName+subjectName;
		 if(subjectId && grade){
			 layer.closeAll();
			 $("#chooseGraSub").text(signName);
			 getTag(grade,subjectId,pageNo);
			 $("#myModal").modal("show");
		 }else{
			 layer.msg('标签需和科目、年级联动，请先选择科目和年级',{time:5000});
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
				layer.msg("暂无标签,您可以自行创建");
			}
		})
	}
//组装对象
function getReadObject(){
	var data = {
			objects : [ {}, {}, {}, {}, {}, {}, {}, {}, {}, {} ]
		};
	var html = template("read_template", data);
	$('#readselect').append(html);
		$('.uls_showstars3 li').on('click',
						function() {
							var id = $(this).attr('id');
							for (var i = 1; i <= id; i++) {
								$(".uls_showstars3_" + i).removeClass()
										.addClass('icon-star uls_showstars3_'+ i);
								for (var j = 5; j > id; j--) {
									$(".uls_showstars3_" + j).removeClass().addClass(
													'icon-star-empty uls_showstars3_'+ j);
								}
							}
							$('#degree_read').val(i - 1);
						})
}

function getWxfillObject(){
	var data = {
			objects : [ {}, {}, {}, {}, {}, {}, {}, {}, {}, {} ]
		};
	var html = template("wxfill_template", data);
	$('#wxfill').append(html);
		$('.uls_showstars3 li').on('click',
						function() {
							var id = $(this).attr('id');
							for (var i = 1; i <= id; i++) {
								$(".uls_showstars3_" + i).removeClass()
										.addClass('icon-star uls_showstars3_'+ i);
								for (var j = 5; j > id; j--) {
									$(".uls_showstars3_" + j).removeClass().addClass(
													'icon-star-empty uls_showstars3_'+ j);
								}
							}
							$('#degree_read').val(i - 1);
						})
}
</script>
</html>