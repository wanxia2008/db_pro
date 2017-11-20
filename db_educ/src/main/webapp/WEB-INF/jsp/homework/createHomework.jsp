<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>生成作业</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<style type="text/css">
.toup {
	cursor:pointer;
}
.todown {
	cursor:pointer;
}
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;top: 20%;font-size: 10px;border-radius: 5px;background:#e33}
ol, ul {
    margin-top: 4px;
    /* margin-bottom: 10px; */
}
.icon-star,.icon-star-empty{color:#333 !important}
.answer{padding:5px 0;}
.answer p{display:inline !important;color:#333 !important}
.answer img{width:200px;height:200px;display:block !important;margin:10px 0}
img.kfformula{display:inline !important;height:50px;width:50px;}
.layui-laypage a, .layui-laypage span{margin-right:5px;padding:0 18px;border-radius:4px;font-size:14px;}
.layui-laypage .layui-laypage-curr .layui-laypage-em{border-radius:4px;}
.layui-laypage>:first-child, .layui-laypage>:first-child em{border-radius:4px;}
.layui-laypage button, .layui-laypage input{border-radius:4px;}
</style>

<style type="text/css">
/*清除浮动*/
.clearfix::after,
.clearfix::before{
    clear: both;
    content: '.';
    height: 0;
    line-height: 0;
    visibility: hidden;
    display: block;
}
/*
layui默认样式修改开始

/*layui checkbox输入框修改*/
.layui-form-checked[lay-skin=primary] i {
    color: #fff;
    background-color: #f10215;
    border-color: #f10215;
}
.layui-form-checkbox[lay-skin=primary]:hover i {
    color: #fff;
    border-color: #f10215;
}
/*输入框focus状态*/
.layui-input:focus, .layui-textarea:focus {
    border-color: #1E9FFF!important;
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102,175,233,.6);
}
/*默认按钮*/
.layui-btn-primary:hover {
    border-color: #1E9FFF;
    color: #1E9FFF;
}
.layui-btn-group .layui-btn-primary:hover {
    border-color: #1E9FFF;
    color: #1E9FFF;
}
/*
layui默认样式修改线束
*/


/*自定义和修改按钮*/
.jie-btn-radius{
    border-radius: 4px;
}
.jie-blue{
    color: #1E9FFF;
    border: 1px solid #1E9FFF;
}
.jie-blue:hover{
    color: #1E9FFF;
    border: 1px solid #1E9FFF;
}
.jie-red{
    color: #ef3356;
    border: 1px solid #ef3356;
}
.jie-red:hover{
    color: #ef3356;
    border: 1px solid #ef3356;
}
.jie-black{
    color: #fff;
    border: 1px solid #192942;
    background-color: #192942;
}
.jie-black:hover{
    color: #fff;
    border: 1px solid #192942;
    background-color: #192942;
}

/*checkbox,radio美化*/
.jie-radio,
.jie-checkbox {
    color: #5a5e66;
    font-weight: 500;
    line-height: 1;
    position: relative;
    cursor: pointer;
    display: inline-block;
    white-space: nowrap;
    outline: none;
    font-size: 14px;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;
    margin-right: 8px;
}
.jie-radio::after,
.jie-checkbox::after {
    display: block;
    height: 0;
    content: "";
    clear: both;
}
.jie-radio>span,
.jie-radio>input,
.jie-checkbox>span,
.jie-checkbox>span {
    float: left;
}
.jie-radio>input,
.jie-checkbox>input {
    display: none;
}
.jie-radio>.jie-label,
.jie-checkbox>.jie-label {
    padding-left: 5px;
}
.jie-radio>.jie-inner,
.jie-checkbox>.jie-inner {
    border: 1px solid #d8dce5;
    width: 14px;
    height: 14px;
    background-color: #fff;
    position: relative;
    cursor: pointer;
    display: inline-block;
    box-sizing: border-box;
}
.jie-radio>.jie-inner {
    border-radius: 100%;
}
.jie-checkbox>.jie-inner {
    border-radius: 2px;
}
.jie-radio>.jie-inner:after {
    width: 6px;
    height: 6px;
    border-radius: 100%;
    background: #409eff;
    content: "";
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%) scale(0);
    transition: transform .15s ease;
}
.jie-radio:hover>.jie-inner,
.jie-radio>input:checked+.jie-inner {
    border-color: #409eff;
}
.jie-radio>input:checked+.jie-inner+.jie-label,
.jie-checkbox>input:checked+.jie-inner+.jie-label {
    color: #409eff;
}
.jie-radio>input:disabled+.jie-inner+.jie-label,
.jie-checkbox>input:disabled+.jie-inner+.jie-label {
    color: #b4bccc;
}
.jie-radio>input:checked+.jie-inner:after {
    transform: translate(-50%, -50%) scale(1);
}
.jie-checkbox>.jie-inner:after {
    display: block;
    box-sizing: border-box;
    width: 3px;
    height: 7px;
    border: 1px solid #fff;
    border-left: none;
    border-top: none;
    content: "";
    position: absolute;
    left: 50%;
    top: 45%;
    transform-origin: center center;
    transform: translate(-50%, -50%) rotate(36deg) scale(0);
    transition: all .2s ease;
}
.jie-checkbox:hover>.jie-inner {
    border-color: #409eff;
}
.jie-checkbox>input:checked+.jie-inner {
    border-color: #409eff;
    background: #409eff;
}
.jie-checkbox>input:disabled+.jie-inner,
.jie-radio>input:disabled+.jie-inner {
    cursor: not-allowed;
    border-color: #b4bccc;
}
.jie-checkbox>input:disabled+.jie-inner+.jie-label,
.jie-radio>input:disabled+.jie-inner+.jie-label {
    cursor: not-allowed;
    color: #b4bccc;
}
.jie-radio>input:disabled+.jie-inner:after {
    background-color: #b4bccc;
}
.jie-checkbox>input:checked:disabled+.jie-inner {
    background: #b4bccc;
}
.jie-checkbox>input:checked+.jie-inner:after {
    transform: translate(-50%, -50%) rotate(36deg) scale(1);
}


/*
试卷开始
*/
.jie-test{

}
.jie-test .jie-test-item{
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    align-items:center;/*垂直居中*/
    justify-content: center;/*水平居中*/
    padding: 20px 20px 20px 0;
    border-bottom: 1px solid #ccc;
}
.jie-test .jie-test-item .left{
    flex:  0 0 70px;
    width: 70px;
    text-align: center;
}
.jie-test .jie-test-item .left span{
    cursor: pointer;
}
.jie-test .jie-test-item .right{
    flex: 1;
}
.jie-test .jie-test-item .right .test-title{
    line-height: 20px;
}
.jie-test .jie-test-item .right .test-title.omit{
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
    overflow: hidden;
}
.jie-test .jie-test-item .right .test-title span{
    font-weight: bold;
}
.jie-test .jie-test-item .right .test-content{
    display: none;
}
.jie-test .jie-test-item .right .test-content .content-title{
    margin-top: 20px;
    line-height: 20px;
}
.jie-test .jie-test-item .right .test-content .content-title span{
    position: relative;
    bottom: 2px;
}
.jie-test .jie-test-item .right .test-content .content-select{
    margin-top: 10px;
    line-height: 20px;
}
.jie-test .jie-test-item .right .test-content .content-select span{
    float: left;
    margin-right: 20px;
    line-height: 20px;
}
.jie-test .jie-test-item .right .test-content .content-answer{
    margin-top: 20px;
    line-height: 20px;
}
.jie-test .jie-test-item .right .test-content .content-analysis{
    margin-top: 10px;
    line-height: 20px;
}
.jie-test .jie-test-item .right .test-difficult{
    margin-top: 10px;
}
/*试题分页*/
.jie-test .pages{
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    margin-top: 40px;
    align-items:center;
    justify-content: center;
}
.jie-test .pages .left{
    flex: 0 0 70px;
    width: 70px;
    text-align: center;
}
.jie-test .pages .right{
    flex: 1;
    text-align: center;
}
.jie-test .already-select{
    height: 50px;
    margin-top: 40px;
    padding-right: 20px;
    line-height: 50px;
    background-color: #f2f2f2;
    text-align: right;
}
/*
试卷结束
*/

/*
家庭作业开始
*/
.jie-homework{

}
.jie-homework .header{
    height: 60px;
    margin-top: 20px;
    line-height: 60px;
    background-color: #f2f2f2;
}
.jie-homework .header span{
    float: left;
    margin-left: 20px;
    font-weight: bold;
}
.jie-homework .header button{
    float: right;
    margin-top: 15px;
    margin-right: 20px;
}
.jie-homework .body{
   margin: 60px 20px;
}
.jie-homework .body .layui-table th {
     text-align: center;
     color: #fff;
     background-color: #2494f2;
     border: 1px solid #ccc;
 }
.jie-homework .body .layui-table tr {
    text-align: center;
}
.jie-homework .body tr.count{
    background-color: #f2f2f2;
}
/*家庭作业排名前三变色*/
.jie-homework .body tbody.ranking tr:nth-child(1){
    background-color: #e2ebd9;
}
.jie-homework .body tbody.ranking tr:nth-child(2){
    background-color: #fee3e0;
}
.jie-homework .body tbody.ranking tr:nth-child(3){
    background-color: #fcf3ce;
}
/*
家庭作业结束
*/

/*
贴标审核开始
*/
.jie-tag-review{
    margin-top: 20px;
}
.jie-tag-review .header{
    height: 60px;
    line-height: 60px;
    background-color: #F2F2F2;
}
.jie-tag-review .header h2{
    float: left;
    margin-left: 20px;
    font-weight: bold;
}
.jie-tag-review .header button{
    float: right;
    margin-right: 20px;
    margin-top: 15px;
}
.jie-tag-review .filter {
    /*margin: 40px 0;*/
    padding-left: 20px;
}
.jie-tag-review .filter .top{
    height: 30px;
    line-height: 30px;
    margin-top: 20px;
}
.jie-tag-review .filter .top span{
    float: left;
    margin-right: 40px;
}
.jie-tag-review .filter .top span strong{
    color: #ef3356;
}
.jie-tag-review .filter .top .button-first{
    height: 30px;
    line-height: 30px;
    padding: 0 10px;
    color: #ef3356;
    background-color: #fff;
    border: 1px solid #ef3356;
    border-right: none;
    border-radius: 8px 0 0 8px;
    outline: none;
}
.jie-tag-review .filter .top .button-second{
    height: 30px;
    line-height: 30px;
    padding: 0 10px;
    color: #ef3356;
    background-color: #fff;
    border: 1px solid #ef3356;
    border-radius: 0 8px 8px 0;
    outline: none;
}
.jie-tag-review .filter .top button.actived{
  background-color: #ef3356;
  color: #fff;
}
.jie-tag-review .filter .bottom{
  margin-top: 20px;
  margin-bottom: 40px;
}

.jie-tag-review .table{
  margin: 0 20px;
}
.jie-tag-review .table tr{
  text-align: center;
}
.jie-tag-review .table th{
  text-align: center;
}
.jie-tag-review .table thead tr{
  background-color: #d4edff;
}
.jie-tag-review .table tbody tr td.color-blue{
  color: #2494f2;
}

.jie-tag-review .pages{
  margin-top: 40px;
  margin-bottom: 40px;
  text-align: center;
}
/*审核不通过*/
.jie-tag-review .title-content{
    margin-top: 40px;
    margin-bottom: 40px;
}
.jie-tag-review .title-content .header{
    height: 60px;
    line-height: 60px;
    font-weight: bold;
    background-color: #f2f2f2;
    padding-left: 20px;
}
.jie-tag-review .title-content .body{
    margin-top: 40px;
    margin-left: 20px;
}
.jie-tag-review .title-content .body .top{
    width: 800px;
    padding-left: 100px;
    border: 1px solid #ccc;
    border-bottom: 1px dashed #ccc;
    box-sizing: border-box;
}
.jie-tag-review .title-content .body .top .title{
    height: 40px;
    margin-top: 20px;
    line-height: 40px;
}
.jie-tag-review .title-content .body .top .select{
    width: 80%;
    text-align: center;
    border: 1px solid #ccc;
}
.jie-tag-review .title-content .body .top .select li{
    float: left;
    width: 50%;
    line-height: 40px;
    background-color: #f2f2f2;
}
.jie-tag-review .title-content .body .top .answer{
    line-height: 40px;
}
.jie-tag-review .title-content .body .top .answer .title{
    display: inline-block;
    height: 30px;
    line-height: 30px;
    padding: 0 8px;
    color: #fff;
    background-color: #2494f2;
    border: 1px solid #2494f2;
    border-radius: 6px 0 6px 0;
}
.jie-tag-review .title-content .body .top .answer .text{
    display: inline-block;
    margin-left: 20px;
}
.jie-tag-review .title-content .body .top .source {
    margin-top: 10px;
    line-height: 40px;
}
.jie-tag-review .title-content .body .top .source span{
    float: left;
    margin-right: 20px;
    line-height: 40px;
}
.jie-tag-review .title-content .body .bottom{
    width: 800px;
    padding-left: 100px;
    border: 1px solid #ccc;
    box-sizing: border-box;
}
.jie-tag-review .title-content .body .bottom .bottom-header{
    height: 40px;
    line-height: 40px;
}
.jie-tag-review .title-content .body .bottom .bottom-header span{
    float: left;
    margin-right: 30px;
}
.jie-tag-review .title-content .body .bottom .bottom-top{
    display: flex;
    width: 70%;
}
.jie-tag-review .title-content .body .bottom .bottom-top .left{
    flex: 0 0 60px;
    width: 60px;
    margin-top: 20px;
}
.jie-tag-review .title-content .body .bottom .bottom-top .right{
    flex: 1;
    margin-top: 15px;
}
.jie-tag-review .title-content .body .bottom .bottom-top .right li{
    width: 100%;
    float: left;
    margin-bottom: 10px;
    line-height: 30px;
}
.jie-tag-review .title-content .body .bottom .bottom-top .right li span{
    float: left;
    margin-right: 20px;
}
.jie-tag-review .title-content .body .bottom .bottom-top .right li .tag-select{
    position: relative;
    padding: 0 16px 0 8px;
    border: 1px solid #1E9FFF;
    color: #1E9FFF;
}
.jie-tag-review .title-content .body .bottom .bottom-top .right li .tag-select .triangle{
    position: absolute;
    bottom: 0;
    right: -20px;
    width: 0;
    border-top: 6px solid transparent;
    border-left: 18px solid transparent;
    border-bottom: 18px solid #1E9FFF;
}
.jie-tag-review .title-content .body .bottom .bottom-top .right li .number-color{
    color: #ef3356;
}
.jie-tag-review .title-content .body .bottom .bottom-top .right li .layui-form {
    float: right;
    margin-right: 20px;
}
.jie-tag-review .title-content .body .bottom .bottom-top .right li .layui-form input[type="checkbox"]{
    width: 20px;
    height: 20px;
}
.jie-tag-review .title-content .body .bottom .bottom-bot{
    margin-top: 20px;
    margin-bottom: 40px;
    line-height: 40px;
}
/*
贴标审核结束
*/
</style>



</head>
<body>
	<form class="layui-form">
		<div class="panels_head">
			<span>作业属性</span> 
			<span style="text-align: center; color: red; right: 50%; background: none; font-size: 18px;">${errorMsg}</span>
			
				<button class="layui-btn layui-btn-normal" lay-submit=""
					lay-filter="demo1"
					style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">确认</button>
			
			<button type="button" class="layui-btn layui-btn-danger"
				onclick="window.location.href='showHomeworkList.do'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">返回</button>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
		<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="grade" id="grade" lay-verify="grade">
						<option value="">请选择</option>
						<c:forEach items="${gList}" var="g">
							<option value="${g.gradeId}" data-name="${g.gradeName}">${g.gradeName}</option>
						</c:forEach>
					</select>
				</div>
			</div>	
		<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width: 120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>科目</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="subject" id="subject" lay-verify="subject">
						<c:forEach items="${sList}" var="s">
							<option value="${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>作业名称</label>
				<div class="layui-input-inline" style="width: 120px;">
					<input type="text" lay-verify="hkName" name="hkName" id="hkName"
						autocomplete="off" placeholder="请输入作业标题" class="layui-input" maxlength="30">
				</div>
			</div>
			
			
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">所属教材</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="science" id="science"
						lay-verify="science" autocomplete="off" placeholder="请选择所属教材">
						<option value="">请选择</option>
						<c:forEach items="${tMaterialsList}" var="t">
							<option value="<c:out value="${t.materialId}" />"><c:out
									value="${t.materialName}"></c:out></option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label"
					style="width: 120px; font-size: 14px;">知识范围</label>
				<div class="layui-inline">
					<div class="layui-input-inline" style="width: 120px;">
						<select name="knowledgeBegin" id="knowledgeBegin"
							lay-verify="knowledgeBegin" lay-filter="select_knowstart">
							<option value="">请选择</option>
							<c:forEach items="${rlList}" var="g">
								<option value="${g.lessonId}">${g.lessonName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="layui-form-mid">-</div>
					<div class="layui-input-inline" style="width: 120px;">
						<select name="knowledgeEnd" id="knowledgeEnd"
							lay-verify="knowledgeEnd" lay-filter="select_knowend">
							<option value="">请选择</option>
							<c:forEach items="${rlList}" var="g">
								<option value="${g.lessonId}">${g.lessonName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">难度</label>
				<div class="layui-input-inline"
					style="width: 150px; margin-top: 5px;">
					<div class="layui-input-inline" style="width: 150px;">
						<ul class="uls_showstars" style="margin-top:-1px;">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					</div>
				</div>
				<input type="number" hidden="" name="difficultyValue"
					id="difficultyValue" lay-verify="difficultyValue" value="3" />
			</div>
		</div>

		<!-- 试卷内容 -->
		<div class="panels_head">
			<span>作业内容</span>
			<!--  -->
			<button type="button" class="layui-btn layui-btn-normal"
				data-toggle="modal" id="add_stu_manu" onclick="manu_addHomework()" 
				style="height:30px;line-height:30px;padding:0 18px;font-size:14px;right:120px ">手动选择</button>
					
			<button type="button" class="layui-btn layui-btn-normal"
				data-toggle="modal" id="add_stu" onclick="addHomework()" 
				style="height:30px;line-height:30px;padding:0 18px;font-size:14px;right:10px ">自动选择</button>
		
		</div>
           <p class="no_data_p"  id="nodatapaper" style="margin-top:20px;font-size: 14px;color:#008bca;letter-spacing:2px" >~暂无数据,请新增作业题目~</p>
		<table class="layui-table"
			style="width: 98%; margin-left: auto; margin-right: auto;display:none" id="papercontent">
			
			<thead>
				<tr>
					<th>序号</th>
					<th>题型</th>
					<th>内容</th>
					<th>分值</th>
					<th>难度</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tbody">
				
			</tbody>
		</table>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:90%;">
				<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel"><span id="choosegrasub"></span>标签</h4>
                 </div>
					<div class="modal-body">
						<div class="layui-form-item" style="margin-top: 10px;margin-bottom:5px">
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 100px;">题目题型</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="topicType" id="topicType">
										<option value="">请选择</option>
										<c:forEach items="${qtList}" var="qq">
											<option value="${qq.topicId}">${qq.topicName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
		               <div class="layui-inline">
								<label class="layui-form-label"
									style="width: 100px; font-size: 14px;">数量</label>
								<div class="layui-input-inline" style="width:120px;">
									<input type="number" name="number" min="1" max="100" value="10" id="number" lay-verify=""
										autocomplete="off" placeholder="请输入数量" class="layui-input"
										onkeyup="score_input(this,this.value)">
								</div>
							</div>
							
							<div class="layui-inline">
								<label class="layui-form-label"
									style="width: 100px; font-size: 14px;">智能排序</label>
								<div class="layui-input-inline" style="width:120px;">
									<select name="topicType" id="topicType" lay-filter="fillType">
										<option value="">请选择</option>
										<option value="">难度最高</option>
										<option value="">难度最低</option>
										<option value="">时间最近</option>
									</select>
								</div>
							</div>	
													
							<div class="layui-inline">
								<label class="layui-form-label"
									style="width: 100px; font-size: 14px;">分值</label>
								<div class="layui-input-inline" style="width:120px;">
									<input type="number" name="score" min="0" max="100" id="score" lay-verify=""
										autocomplete="off" placeholder="请输入分值" value="5" class="layui-input"
										onkeyup="score_input(this,this.value)">
								</div>
							</div>
							<div class="layui-inline" id="search_tit">
								<label class="layui-form-label"
									style="width: 100px; font-size: 14px;"></label>
								<div class="layui-input-inline" style="width:120px;">
							<button type="button" class="layui-btn layui-btn-normal"
							data-toggle="modal" id="sear_que" onclick="search_que()" 
							style="height:30px;line-height:30px;padding:0 18px;font-size:14px;right:10px ">搜索</button>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label" style="width:120px;"id="tag_tag1">选择标签关系</label>
							<div class="layui-input-block" id="tag_list1">
								<%-- <c:forEach items="${qttList}" var="qt">
									<input lay-skin="primary" type="checkbox" lay-filter="tag_filter" name="tag" title="${qt.tagName}" value="${qt.tagId}">
								</c:forEach>
								<input hidden="" name="tagId" id="tagId" /> --%>
							</div>
							<div id="pages" style="text-align: center; margin-top: 30px;"></div>
							<input hidden="" name="tagId" id="tagId" />
						</div>
						
								
	<!-- 样式开始 -->
	<section id="checkbox" class="jie-test">
    <!--试题-->
    <div class="jie-test-item">
        <div class="left">
            <label class="jie-checkbox">
                <input type="checkbox">
                <span class="jie-inner"></span>
            </label>
        </div>
        <div class="right">
            <div class="test-title omit">
                <span>(阅读理解)</span>  do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do
            </div>
            <div class="test-content">
                <div class="content-title">
                    <span>( )</span> 1. how do you do ____ do you do
                </div>
                <div class="content-select clearfix">
                    <span class="select-item">A. how do you do</span>
                    <span class="select-item">B. how do you do</span>
                    <span class="select-item">C. how do you do</span>
                    <span class="select-item">D. how do you do</span>
                </div>
                <div class="content-answer">
                    答案: A
                </div>
                <div class="content-analysis">
                    解析: 语句通路，结构浅析
                </div>
            </div>
            <div class="test-content">
                <div class="content-title">
                    <span>( )</span> 1. how do you do ____ do you do
                </div>
                <div class="content-select clearfix">
                    <span class="select-item">A. how do you do</span>
                    <span class="select-item">B. how do you do</span>
                    <span class="select-item">C. how do you do</span>
                    <span class="select-item">D. how do you do</span>
                </div>
                <div class="content-answer">
                    答案: A
                </div>
                <div class="content-analysis">
                    解析: 语句通路，结构浅析
                </div>
            </div>
            <div class="test-content">
                <div class="content-title">
                    <span>( )</span> 1. how do you do ____ do you do
                </div>
                <div class="content-select clearfix">
                    <span class="select-item">A. how do you do</span>
                    <span class="select-item">B. how do you do</span>
                    <span class="select-item">C. how do you do</span>
                    <span class="select-item">D. how do you do</span>
                </div>
                <div class="content-answer">
                    答案: A
                </div>
                <div class="content-analysis">
                    解析: 语句通路，结构浅析
                </div>
            </div>

            <div class="test-difficult">
                <button class="layui-btn layui-btn-primary layui-btn-mini jie-btn-radius jie-blue">平均难度4.0</button>
                <button class="layui-btn layui-btn-primary layui-btn-mini jie-btn-radius jie-blue">2013江西卷</button>
            </div>
        </div>
    </div>
    <div class="jie-test-item">
        <div class="left">
            <label class="jie-checkbox">
                <input type="checkbox">
                <span class="jie-inner"></span>
            </label>
        </div>
        <div class="right">
            <div class="test-title omit">
                <span>(阅读理解)</span>  do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do
            </div>
            <div class="test-content">
                <div class="content-title">
                    <span>( )</span> 1. how do you do ____ do you do
                </div>
                <div class="content-select clearfix">
                    <span class="select-item">A. how do you do</span>
                    <span class="select-item">B. how do you do</span>
                    <span class="select-item">C. how do you do</span>
                    <span class="select-item">D. how do you do</span>
                </div>
                <div class="content-answer">
                    答案: A
                </div>
                <div class="content-analysis">
                    解析: 语句通路，结构浅析
                </div>
            </div>
            <div class="test-content">
                <div class="content-title">
                    <span>( )</span> 1. how do you do ____ do you do
                </div>
                <div class="content-select clearfix">
                    <span class="select-item">A. how do you do</span>
                    <span class="select-item">B. how do you do</span>
                    <span class="select-item">C. how do you do</span>
                    <span class="select-item">D. how do you do</span>
                </div>
                <div class="content-answer">
                    答案: A
                </div>
                <div class="content-analysis">
                    解析: 语句通路，结构浅析
                </div>
            </div>
            <div class="test-content">
                <div class="content-title">
                    <span>( )</span> 1. how do you do ____ do you do
                </div>
                <div class="content-select clearfix">
                    <span class="select-item">A. how do you do</span>
                    <span class="select-item">B. how do you do</span>
                    <span class="select-item">C. how do you do</span>
                    <span class="select-item">D. how do you do</span>
                </div>
                <div class="content-answer">
                    答案: A
                </div>
                <div class="content-analysis">
                    解析: 语句通路，结构浅析
                </div>
            </div>

            <div class="test-difficult">
                <button class="layui-btn layui-btn-primary layui-btn-mini jie-btn-radius jie-blue">平均难度4.0</button>
                <button class="layui-btn layui-btn-primary layui-btn-mini jie-btn-radius jie-blue">2013江西卷</button>
            </div>
        </div>
    </div>
    <div class="jie-test-item">
        <div class="left">
            <label class="jie-checkbox">
                <input type="checkbox">
                <span class="jie-inner"></span>
            </label>
        </div>
        <div class="right">
            <div class="test-title omit">
                <span>(阅读理解)</span>  do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do how do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you dohow do you do
            </div>
            <div class="test-content">
                <div class="content-title">
                    <span>( )</span> 1. how do you do ____ do you do
                </div>
                <div class="content-select clearfix">
                    <span class="select-item">A. how do you do</span>
                    <span class="select-item">B. how do you do</span>
                    <span class="select-item">C. how do you do</span>
                    <span class="select-item">D. how do you do</span>
                </div>
                <div class="content-answer">
                    答案: A
                </div>
                <div class="content-analysis">
                    解析: 语句通路，结构浅析
                </div>
            </div>
            <div class="test-content">
                <div class="content-title">
                    <span>( )</span> 1. how do you do ____ do you do
                </div>
                <div class="content-select clearfix">
                    <span class="select-item">A. how do you do</span>
                    <span class="select-item">B. how do you do</span>
                    <span class="select-item">C. how do you do</span>
                    <span class="select-item">D. how do you do</span>
                </div>
                <div class="content-answer">
                    答案: A
                </div>
                <div class="content-analysis">
                    解析: 语句通路，结构浅析
                </div>
            </div>
            <div class="test-content">
                <div class="content-title">
                    <span>( )</span> 1. how do you do ____ do you do
                </div>
                <div class="content-select clearfix">
                    <span class="select-item">A. how do you do</span>
                    <span class="select-item">B. how do you do</span>
                    <span class="select-item">C. how do you do</span>
                    <span class="select-item">D. how do you do</span>
                </div>
                <div class="content-answer">
                    答案: A
                </div>
                <div class="content-analysis">
                    解析: 语句通路，结构浅析
                </div>
            </div>

            <div class="test-difficult">
                <button class="layui-btn layui-btn-primary layui-btn-mini jie-btn-radius jie-blue">平均难度4.0</button>
                <button class="layui-btn layui-btn-primary layui-btn-mini jie-btn-radius jie-blue">2013江西卷</button>
            </div>
        </div>
    </div>

    <!--分页-->
    <div class="pages">

        <div class="left">
            <label class="jie-checkbox">
                <input id="allCheck" type="checkbox">
                <span class="jie-inner"></span>
            </label>
        </div>
        <div class="right">
            <button class="layui-btn layui-btn-primary layui-btn-small jie-blue">上一页</button>
            <button class="layui-btn layui-btn-primary layui-btn-small">下一页</button>
        </div>
    </div>
    <div class="already-select">
        <button class="layui-btn layui-btn-normal layui-btn-small jie-btn-radius">已经选择 (<span id="checkNumber">0</span>) 道</button>
        <button class="layui-btn layui-btn-primary layui-btn-small jie-blue jie-btn-radius">返回</button>
    </div>
</section>
						
			

<!-- 样式结束 -->
			<div class="modal-footer">
				<button type="button" id="create_question" class="btn btn-primary" style="height:30px;line-height:30px;padding:0 18px;">生成</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>						
			</div>
					
				</div>
			</div>
			</div>
			<!--模态框结束-->
		
	</form>
	<script type="text/html" id="tag_template">

			{{each objects as value i}}		
              <input type="checkbox" lay-skin="primary" lay-filter="tag_filter" name="tag" title="{{value.tagName}}"
										value="{{value.tagId}}">
			{{/each}}

	</script>
	<script type="text/html" id="quetion_template">      
 		

		 <input type="checkbox" lay-skin="primary" lay-filter="tag_filter" name="tag" title="{{gradeList[0].gradeId}}"
										value="{{gradeList[0].gradeName}}">


	</script>
	<script type="text/html" id="single_template">
		{{each objects as value i}}
		{{if value.type==1}}
			<tr>
			<td>{{i+1}}</td>
			<td>
               <div class="answer">{{value.questionsType.topicName}}</div>
            </td>
			<td style="text-align:left">
               <div class="answer">{{value.choiceDesc}}</div> 
               <div class="answer">A.{{value.OptionA}}</div>
               <div class="answer">B.{{value.OptionB}}</div> 
               <div class="answer">C:{{value.OptionC}}</div> 
               <div class="answer">D:{{value.OptionD}}</div>
			</td>
			<td>{{value.score}}</td>
			<td>{{value.degree}}</td>
			<td>
				<p class="toup" id="{{i}}">
					<i class="icon-arrow-up" style="color:#008bca"></i>
				</p>
				<p class="todown" id="{{i}}">
					<i class="icon-arrow-down" style="color:#008bca"></i>
				</p> <a class="delete" href="#" id="{{i}}" style="color:#ff5722">删除</a>
			</td>
		</tr>
		{{/if}}
		{{if value.type==2}}
			<tr>
			<td>{{i+1}}</td>
			<td>
                <div class="answer">{{value.questionsType.topicName}}</div>
            </td>
			<td style="text-align:left"> 
                <div class="answer">{{value.choiceDesc}}</div>
                <div class="answer">A.{{value.OptionA}}</div>
                <div class="answer">B.{{value.OptionB}}</div> 
                <div class="answer">C:{{value.OptionC}}</div> 
                <div class="answer">D:{{value.OptionD}}</div>
			</td>
			<td>{{value.score1}}</td>
			<td>{{value.degree}}</td>
			<td>
				<p class="toup" id="{{i}}">
					<i class="icon-arrow-up" style="color:#008bca"></i>
				</p>
				<p class="todown" id="{{i}}">
					<i class="icon-arrow-down" style="color:#008bca"></i>
				</p> <a class="delete" href="#" id="{{i}}" style="color:#ff5722">删除</a>
			</td>
		</tr>
		{{/if}}
		{{if value.type==3}}
			<tr>
			<td>{{i+1}}</td>
			<td>
                <div class="answer">{{value.questionsType.topicName}}</div>
            </td>
			<td style="text-align:left">
               <div class="answer">{{value.choiceDesc}}</div>
               <div class="answer">A.{{value.OptionA}}</div> 
               <div class="answer">B.{{value.OptionB}}</div> 
               <div class="answer">C:{{value.OptionC}}</div>
               <div class="answer">D:{{value.OptionD}}</div>
			</td>
			<td>{{value.score2}}</td>
			<td>{{value.degree}}</td>
			<td>
				<p class="toup" id="{{i}}">
					<i class="icon-arrow-up" style="color:#008bca"></i>
				</p>
				<p class="todown" id="{{i}}">
					<i class="icon-arrow-down" style="color:#008bca"></i>
				</p> <a class="delete" href="#" id="{{i}}" style="color:#ff5722">删除</a>
			</td>
		</tr>
		{{/if}}
		{{if value.type==4}}
			<tr>
			<td>{{i+1}}</td>
			<td>
                <div class="answer">{{value.questionsType.topicName}}</div>
            </td>
			<td style="text-align:left"><div class="answer">{{value.readDesc}}</div></td>
			<td>{{value.score_read}}</td>
			<td>{{value.degree}}</td>
			<td>
				<p class="toup" id="{{i}}">
					<i class="icon-arrow-up" style="color:#008bca"></i>
				</p>
				<p class="todown" id="{{i}}">
					<i class="icon-arrow-down" style="color:#008bca"></i>
				</p> <a class="delete" href="#" id="{{i}}" style="color:#ff5722">删除</a>
			</td>
		</tr>
		{{/if}}
    {{if value.type==5}}
			<tr>
			<td>{{i+1}}</td>
			<td>
                <div class="answer">{{value.questionsType.topicName}}</div>
            </td>
			<td style="text-align:left"> <div class="answer">{{value.subjectiveTitle}}</div></td>
			<td>{{value.questionScore}}</td>
			<td>{{value.questionDegree}}</td>
			<td>
				<p class="toup" id="{{i}}">
					<i class="icon-arrow-up" style="color:#008bca"></i>
				</p>
				<p class="todown" id="{{i}}">
					<i class="icon-arrow-down" style="color:#008bca"></i>
				</p> <a class="delete" href="#" id="{{i}}" style="color:#ff5722">删除</a>
			</td>
		</tr>
		{{/if}}
       {{if value.type==6}}
			<tr>
			<td>{{i+1}}</td>
			<td>
                <div class="answer">{{value.questionsType.topicName}}</div>
            </td>
			<td style="text-align:left"> <div class="answer">{{value.fillTitle}}</div></td>
			<td>{{value.questionScore}}</td>
			<td>{{value.questionDegree}}</td>
			<td>
				<p class="toup" id="{{i}}">
					<i class="icon-arrow-up" style="color:#008bca"></i>
				</p>
				<p class="todown" id="{{i}}">
					<i class="icon-arrow-down" style="color:#008bca"></i>
				</p> <a class="delete" href="#" id="{{i}}" style="color:#ff5722">删除</a>
			</td>
		</tr>
		{{/if}}
{{if value.type==7}}
			<tr>
			<td>{{i+1}}</td>
			<td>
                <div class="answer">{{value.questionsType.topicName}}</div>
            </td>
			<td style="text-align:left"> <div class="answer">{{value.clozeContent}}</div></td>
			<td>{{value.questionScore}}</td>
			<td>{{value.questionDegree}}</td>
			<td>
				<p class="toup" id="{{i}}">
					<i class="icon-arrow-up" style="color:#008bca"></i>
				</p>
				<p class="todown" id="{{i}}">
					<i class="icon-arrow-down" style="color:#008bca"></i>
				</p> <a class="delete" href="#" id="{{i}}" style="color:#ff5722">删除</a>
			</td>
		</tr>
		{{/if}}
		{{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script src=".././layui/layui.js" charset="UTF-8"></script>
<script>
template.config("escape", false);//转移html
	var questionList = '', questionjson, questionlength = "", totalScore = 0;
	var score;//题目的分数
	var shiti='';//所增加的题目
	var pageNo = 1, pageSize = 100;
	layui
			.use(
					[ 'form', 'layedit','laypage','laydate' ],
					function() {
						 form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');

						form.on('select(select_knowend)', function(data) {
							var value = data.value;
							var start = $('#knowledgeBegin').val();
							if (parseInt(value) < parseInt(start)) {
								layer.msg("请正确选择知识范围");
								return;
							}
						})

						form.on('select(select_knowstart)', function(data) {
							var value = data.value;
							var end = $('#knowledgeEnd').val();
							if (parseInt(value) > parseInt(end)) {
								layer.msg("请正确选择知识范围");
								return;
							}
						})
						
						form.on('checkbox(tag_filter)', function(data) {
							var chk_value = [];
							$('input[name="tag"]:checked').each(
									function() {
										chk_value.push($(this).attr('value'));
									});
							$('#tagId').val(chk_value.join(","));
						})

						//自定义验证规则
						form.verify({
							hkName : function(value) {
								if (value.length == "") {
									return '请输入作业名称';
								}
							},
							subject : function(value) {
								if (value.length == "") {
									return '请选择作业科目';
								}
							},
							grade : function(value) {
								if (value.length == "") {
									return "请选择知识范围";
								}
							},
// 							 science : function(value) {
// 								if (value.length == "") {
// 									return '请选择所属教材';
// 								}
// 							}, 
							knowledgeBegin : function(value) {
									var end = $('#knowledgeEnd').val();
									if (parseInt(value) > parseInt(end)) {
										return '请正确选择知识范围';
								}
							},
							knowledgeEnd : function(value) {
									var start = $('#knowledgeBegin').val();
									 if (parseInt(value) <= parseInt(start)) {
										return '请正确选择知识范围';
								}
							},
							difficultyValue : function(value) {
								if (value.length == "") {
									return '请点击五角星选择难度等级';
								}
							}
						});

						//监听提交
						form.on('submit(demo1)',function(data) {
                                      
											if (questionList == null|| questionList == '') {
												layer.msg("您还未添加试题，赶快去添加把~");
											} else {
												var loadIndex = layer.load(0, {
													  shade: [0.8,'#000'] //0.1透明度的白色背景
													});
												$.ajax({
													url : 'addHomework.do',
													type : 'post',
													dataType : 'json',
													data : {
														hkName : $('#hkName').val(),
														subject : $('#subject').val(),
														grade : $('#grade').val(),
														science : $('#science').val(),
														isPublic : 0,
														knowledgeBegin :$('#knowledgeBegin').val(),
														knowledgeEnd : $('#knowledgeEnd').val(),
														difficultyValue : $('#difficultyValue').val(),
														questionList : questionList,
														totalScore : totalScore,
														questionlength : questionlength
													},
													success : function(res) {
														layer.close(loadIndex);
														if (res.code == 0) {
															layer.msg("添加作业成功!");
															setTimeout(function(){
																window.location.href = "${ctx}/homework/showHomeworkList.do";
															},500);
														}else{
															layer.msg(res.msg,{time:1000});
														}
													}
												})
											}
											return false;
										});
					});
	
	$('#create_question').on('click', function() {
		var type = $('#topicType').val();
		var number = $('#number').val();
		if(number<=0){
			layer.msg('请输入合法的试题数量');
			return false;
		}
		score = $('#score').val();
		if(score<=0){
			layer.msg('分值需大于0');
			return false;
		}
		var subject = $('#subject').val();
		var grade= $('#grade').val();
		var science=$('#difficultyValue').val();
		var tag = $('#tagId').val();
		if (subject == '') {
			layer.msg("您还未选择科目");
			return;
		}
		if (grade == '') {
			layer.msg("您还未选择知识范围");
			return;
		}
		
		if (type == '') {
			layer.msg("您还未选择题型");
			return;
		}
		if (number == '') {
			layer.msg("您还未选择题目数量");
			return;
		}
		if (score == '') {
			layer.msg("您还未选择分值");
			return;
		}
		if(tag == '' || tag == null) {
			layer.msg("您还未选择标签");
			return;
		}
		// 		var tagId=$('#tagId').val(); ,'number':number,'score':score,'tagId':tagId 
		$.ajax({
			url : "fuzzyQuery.do",
			type : 'post',
			dataType : 'json',
			data : {
				type : type,
				number : number,
				score : score,
				subject : subject,
				grade:grade,
				science:science,
				tagId:tag
			},
			success : function(res) {
				 if (res.code == 0) {
					totalScore = totalScore + parseFloat(score * res.count);
					questionlength += res.count;
					if (typeof (questionjson) == "undefined") {
						questionjson = res;
						questionList = JSON.stringify(questionjson);
						var html = template("single_template", res);
						$('#tbody').html(html);
						$('#myModal').modal('hide');
						$("#nodatapaper").hide();
						$('#papercontent').show();
						toupordown(questionjson);//执行题目上下移操作
						deletes(questionjson);//删除题目操作
					} else {
						var status=0;
						var object = questionjson.objects;
						var data = res.objects;
						for(var j=0;j<object.length;j++) {
							for (var i = 0; i < data.length; i++) {
								//先判断查询结果是否已在列表中
								if(type==1) {
									//单选
									if(object[j].choiceId==data[i].choiceId && object[j].type==1) {
										console.log("单选----在列表中");
										res.objects.splice(i,1);//如果已存在则把数组内的元素删掉
									}
								} else if(type==2) {
									//多选
									if(object[j].choiceId==data[i].choiceId && object[j].type==2) {
										console.log("多选----在列表中");
										res.objects.splice(i,1);//如果已存在则把数组内的元素删掉
									}
								} else if(type==3) {
									//判断
									if(object[j].judgeId==data[i].judgeId) {
										console.log("判断---在列表中");
										res.objects.splice(i,1);//如果已存在则把数组内的元素删掉
									}
								} else if(type==4) {
									//阅读
									if(object[j].readId==data[i].readId) {
										console.log("阅读---在列表中");
										res.objects.splice(i,1);//如果已存在则把数组内的元素删掉
									}
								} else if(type==5) {
										//主观题
										if(object[j].subjectiveId==data[i].subjectiveId) {
											console.log("主观题---在列表中");
											res.objects.splice(i,1);//如果已存在则把数组内的元素删掉
										}
									} else if(type==6) {
									//填空题
									if(object[j].fillId==data[i].fillId) {
										console.log("填空题---在列表中");
										res.objects.splice(i,1);//如果已存在则把数组内的元素删掉
									}
								} else if(type == 7){
									if(object[j].clozeId==data[i].clozeId) {
										console.log("完形填空---在列表中");
										res.objects.splice(i,1);//如果已存在则把数组内的元素删掉
									}
								}
							}
						}
						var len =res.objects.length;
						if(len>0) {
							if(len<res.count) {
								layer.msg("有部分题已添加~");
							}
							for(var i=0;i<len;i++) {
								object.push(res.objects[i]);
							}
							console.log("json对象：" + JSON.stringify(questionjson));
							questionList = JSON.stringify(questionjson);
							var html = template("single_template", questionjson);
							$('#tbody').html(html);
							$('#myModal').modal('hide');
							toupordown(questionjson);//执行题目上下移操作
							deletes(questionjson);//删除题目操作
						} else {
							layer.msg("您已经添加过该题目了，请重换条件查找");
						}
					}
				} else {
					layer.msg(res.msg);
				}
			}
		});
	});
	$('.uls_showstars li').on('click', function() {
		var id = $(this).attr('id');
		for (var i = 1; i <= id; i++) {
			$("#" + i).removeClass().addClass('icon-star');
			for (var j = 5; j > id; j--) {
				$("#" + j).removeClass().addClass('icon-star-empty');
			}
		}
		$('#difficultyValue').val(i - 1);
	});
	//删除题目操作
	function deletes(questionjson){
		$('.delete').on('click',function(){
			var id = $(this).attr("id");
			layer.confirm('确认删除吗？', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理
				var objects = questionjson.objects;
				objects.splice(id,1);
				questionList = JSON.stringify(questionjson);
				var html = template("single_template", questionjson);
				$('#tbody').html(html);
				deletes(questionjson);
				toupordown(questionjson);//执行题目上下移操作
				console.log("删除后的数组："+questionList);
				layer.close(index);
			});
		})
	}
	//执行题目上下移操作
	function toupordown(questionjson) {
		var len = questionjson.objects.length;//获取数组长度
		$('.toup').on('click',function(){//上移
			var id = $(this).attr("id");
			if(id==0) {
				layer.msg("抱歉，此题是第一道，无法执行操作");
			} else {
				var next = questionjson.objects[id-1];
				var now = questionjson.objects[id];
				questionjson.objects[id]=next;
				questionjson.objects[id-1]=now;
				questionList = JSON.stringify(questionjson);
				console.log("执行上移后的数组"+questionList);
				var html = template("single_template", questionjson);
				$('#tbody').html(html);
				toupordown(questionjson);
			}
		});
		$('.todown').on('click',function(){
			var id = $(this).attr("id");
			id = parseInt(id);
			var ids =id+1;
			if(ids==len) {
				layer.msg("抱歉，此题是最后一道，无法执行操作");
			} else {
				var next1 = questionjson.objects[id+1];
				var now1 = questionjson.objects[id];
				questionjson.objects[id]=next1;
				questionjson.objects[id+1]=now1;
				questionList = JSON.stringify(questionjson);
				console.log("执行下移后的数组"+questionList);
				var html = template("single_template", questionjson);
				$('#tbody').html(html);
				toupordown(questionjson);
			}
		});
	}
	
	
	function manu_addHomework(){
		var subjectId = $('#subject').val();
		var grade = $('#grade').val();
		var gradeName = $("#grade option:selected").attr("data-name");
		var subjectName = $("#subject option:selected").attr("data-name");
		var signName = gradeName + subjectName;
		var topicType = $('#topicType').val();
		 if(subjectId && grade){ //允许打开添加试题模态框
			 layer.closeAll(); //隐藏弹出层
			 $("#choosegrasub").text(signName);
			 $("#myModal").modal('show');
			 //getTag(grade,subjectId,pageNo);
		 }else{
			 layer.msg('生成试题时，题目标签需和科目、年级联动，请先选择科目和年级',{time:5000});
			 $("#myModal").modal("hide");			
		 }
	}
	
	function addHomework(){
		var subjectId = $('#subject').val();
		var grade = $('#grade').val();
		var gradeName = $("#grade option:selected").attr("data-name");
		var subjectName = $("#subject option:selected").attr("data-name");
		var signName = gradeName + subjectName;
		 if(subjectId && grade){ //允许打开添加试题模态框
			 layer.closeAll(); //隐藏弹出层
			 $("#choosegrasub").text(signName);
			 $("#myModal").modal('show');
			 $("#sear_que").hide();
			 $("#tag_tag1").text("选择标签关系");
			 getTag(grade,subjectId,pageNo);
		 }else{
			 layer.msg('生成试题时，题目标签需和科目、年级联动，请先选择科目和年级',{time:5000});
			 $("#myModal").modal("hide");			
		 }
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
				$('#tag_list1').empty();
				layer.msg("暂无标签,您可以自行创建");
			}
		})
	}
	
	
	function search_que(){
		var subjectId = $('#subject').val();
		var grade = $('#grade').val();
		var gradeName = $("#grade option:selected").attr("data-name");
		var subjectName = $("#subject option:selected").attr("data-name");
		var signName = gradeName + subjectName;
		var topicType = $('#topicType').val();
		var handoutType = parseInt( topicType );
		alert("题型："+handoutType);
		//alert("科目subjectId："+subjectId);
		//alert("年级grade："+grade);

		 if(subjectId && grade){ //允许打开添加试题模态框
			 layer.closeAll(); //隐藏弹出层
			 $("#choosegrasub").text(signName);
			 $("#myModal").modal('show');
			 $("#tag_tag1").text("选择题目");
			 sear_question(grade,subjectId,handoutType);
		 }else{
			 layer.msg('生成试题时，题目标签需和科目、年级联动，请先选择科目和年级',{time:5000});
			 $("#myModal").modal("hide");			
		 }
	}
	
	function sear_question(grade,sid,handoutType) {
		$.getJSON("${ctx}/questionBank/getquestionbank.do", {
			grade:grade,
			subjectId:sid,
			//pageNo : pageno,
			//pageSize : pageSize
			handoutType : handoutType 
		}, function(res) {
			if (res.code == 0) {
				sucess_quetemplate(res);
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
								sear_question(grade,sid,handoutType);
							}
						}
					});
				}
			} else if (res.code == 1) {
				$('#tag_list1').empty();
				layer.msg("暂无标签,您可以自行创建");
			}
		})
	}
	
	function sucess_totemplate(res) {
		var html = template('tag_template', res);
		$('#tag_list1').html(html);
        form.render('checkbox');
	}
	function sucess_quetemplate(res) {
		var result = res.objects[0];
		var html = template('quetion_template', result);
	
		//console.log("要输出的内容");
		//document.write(JSON.stringify(result)); 
		alert( JSON.stringify(result));
		$('#tag_list1').html(html);
		slideToggles()
        form.render('checkbox');
	}

	
   function slideToggles(){
    $('.jie-pop .jie-pop-body .items .item  .right .title').click(function(){
        $(this).siblings(".content").slideToggle();
    })
	}
   
  $(function(){
        $('.jie-pop .jie-pop-body .items .item  .right .title').click(function(){
            $(this).siblings(".content").slideToggle();
        })
    }) 
</script>
	
</script>
</html>