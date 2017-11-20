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
			<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px;">确认</button>
			<button type="button" class="layui-btn layui-btn-danger" onclick="history.go(-1)"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
		</div>
		<input hidden="" id="update_type" value="${type}" />
		<c:if test="${type==1}">
			<input hidden="" name="choiceId" value="${single.choiceId}" />
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
			</div>
		</c:if>
		<c:if test="${type==2}">
			<input hidden="" name="choiceId" value="${mulit.choiceId}" />
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
		</c:if>
		<c:if test="${type==3}">
			<input hidden="" name="judgeId" value="${judge.judgeId}">
			
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
		</c:if>
		<c:if test="${type==5}">
			<input hidden="" name="readId" value="${sQuestion.subjectiveId}" />
			<div class="layui-form-item" style="margin: 20px 0">
			<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>阅读正文</label>
				<div class="layui-inline">
					<textarea id="subjectiveTitle" name="subjectiveTitle" lay-verify="readDesc"
						style="width: 710px; height: 300px; border-radius: 5px;"
						placeholder="输入课文">${sQuestion.subjectiveTitle}</textarea>
				</div>
			</div>
			<div class="layui-form-item" style="margin: 20px 0">	   
           <div class="layui-inline">
					<label class="layui-form-label" style="font-size:14px;width: 120px;">题目释义</label>
					<textarea name="subAnalysis" id="subAnalysis" placeholder="请输入题目" 
						style="width: 710px; height: 200px; border-radius: 5px;margin-left: 120px;"
						>${sQuestion.answerAnalysis}</textarea>
				</div>
	      </div>
		</c:if>
		  
		  
			<c:if test="${type==6}">
			<input hidden="" name="readId" value="${fill.fillId}" />
			<div class="layui-form-item" style="margin: 20px 0">
			<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>阅读正文</label>
				<div class="layui-inline">
					<textarea id="fillTitle" name="fillTitle" lay-verify="readDesc"
						style="width: 710px; height: 300px; border-radius: 5px;"
						placeholder="输入题目">${fill.fillTitle}</textarea>
				</div>
				<p style="color: red;margin-left:120px;">备注：填空题所需填空处请以两个或两个以上"_"分割开</p>
			</div>
	      <div class="layui-form-item" style="margin: 20px 0">	   
           <div class="layui-inline">
					<label class="layui-form-label" style="font-size:14px;width: 120px;">答案</label>
					<textarea name="fillAnswer" id="fillAnswer" lay-verify="fillAnswer" placeholder="请输入题目答案" 
						style="width: 710px; height: 200px; border-radius: 5px;margin-left: 120px;"
						>${fill.fillAnswer}</textarea>
				</div>
			<p style="color: red;margin-left:120px;">备注：有多个答案时,答案以"##"号分割开</p>				
	      </div>
			<div class="layui-form-item" style="margin: 20px 0">	   
           <div class="layui-inline">
					<label class="layui-form-label" style="font-size:14px;width: 120px;">题目释义</label>
					<textarea name="answerAnalysis" id="fillAnalysis" placeholder="请输入题目释义" 
						style="width: 710px; height: 200px; border-radius: 5px;margin-left: 120px;"
						>${fill.answerAnalysis}</textarea>
				</div>
	      </div>
		</c:if>
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
	layui.use([ 'form', 'layedit', 'laypage', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

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
							var type = $('#update_type').val();
							if(type == 1){
								var paperId = ${paperId};
								var choiceDesc = $("textarea[name='choiceDesc']").val();
								var a = $("textarea[name='OptionA']").val();
								var b = $("textarea[name='OptionB']").val();
								var c = $("textarea[name='OptionC']").val();
								var d = $("textarea[name='OptionD']").val();
								var answer = $("textarea[name='answer']").val();
								var answerDesc = $("textarea[name='answerDesc']").val();
								$.ajax({
									url:'${ctx}/questionBank/updateSingle.do',
									type:'post',
									dataType:'json',
									data:{
										choiceId:${questionId},
										choiceDesc:choiceDesc,
										OptionA:a,
										OptionB:b,
										OptionC:c,
										OptionD:d,
										answer:answer,
										answerDesc:answerDesc
									},
									success:function(data){
										if(data.code==0){
											layer.msg('编辑成功!');
											window.location.href="${ctx}/homework/modity.do?paperId="+paperId; 
										}else{
											layer.msg(data.msg);
								    		return;
										}
									},
								});
							}else if(type == 2){
								var paperId = ${paperId};
								var choiceDesc = $("textarea[name='choiceDesc1']").val();
								var a = $("textarea[name='OptionA1']").val();
								var b = $("textarea[name='OptionB1']").val();
								var c = $("textarea[name='OptionC1']").val();
								var d = $("textarea[name='OptionD1']").val();
								var answer = $("textarea[name='answer']").val();
								var answerDesc = $("textarea[name='answerDesc']").val();
								$.ajax({
									url:'${ctx}/questionBank/modityMulitChoice.do',
									type:'post',
									dataType:'json',
									data:{
										choiceId:${questionId},
										choiceDesc1:choiceDesc,
										OptionA1:a,
										OptionB1:b,
										OptionC1:c,
										OptionD1:d,
										answer1:answer,
										answerDesc1:answerDesc
									},
									success:function(data){
										if(data.code==0){
											layer.msg('编辑成功!');
											window.location.href="${ctx}/homework/modity.do?paperId="+paperId; 
										}else{
											layer.msg(data.msg);
								    		return;
										}
									},
								});
							}else if(type == 3){
								var paperId = ${paperId};
								var choiceDesc = $("textarea[name='judgeDesc']").val();
								var answer = $("textarea[name='answer2']").val();
								var answerDesc = $("textarea[name='answerDesc2']").val();
								$.ajax({
									url:'${ctx}/questionBank/modityJudge.do',
									type:'post',
									dataType:'json',
									data:{
										judgeId:${questionId},
										judgeDesc:choiceDesc,
										answer2:answer,
										answerDesc2:answerDesc
									},
									success:function(data){
										if(data.code==0){
											layer.msg('编辑成功!');
											window.location.href="${ctx}/homework/modity.do?paperId="+paperId; 
										}else{
											layer.msg(data.msg);
								    		return;
										}
									},
								});
							}else if(type == 4){
								
							}else if(type == 5){
								var paperId = ${paperId};
								var title = $("textarea[name='subjectiveTitle']").val();
								var analysis = $("textarea[name='subAnalysis']").val();
								$.ajax({
									url:'${ctx}/questionBank/updateSubjective.do',
									type:'post',
									dataType:'json',
									data:{
										subjectiveId:${questionId},
										subjectiveTitle:title,
										answerAnalysis:analysis
									},
									success:function(data){
										if(data.code==0){
											layer.msg('编辑成功!');
											window.location.href="${ctx}/homework/modity.do?paperId="+paperId; 
										}else{
											layer.msg(data.msg);
								    		return;
										}
									},
								});
							}else if(type == 6){
								var paperId = ${paperId};
								var title = $("textarea[name='fillTitle']").val();
								var fillAnswer = $("textarea[name='fillAnswer']").val();
								var analysis = $("textarea[name='answerAnalysis']").val();
								$.ajax({
									url:'${ctx}/questionBank/updateFill.do',
									type:'post',
									dataType:'json',
									data:{
										fillId:${questionId},
										fillTitle:title,
										fillAnswer:fillAnswer,
										answerAnalysis:analysis
									},
									success:function(data){
										if(data.code==0){
											layer.msg('编辑成功!');
											window.location.href="${ctx}/homework/modity.do?paperId="+paperId; 
										}else{
											layer.msg(data.msg);
								    		return;
										}
									},
								});
							}else if(type == 7){
								
							}
							return false;
						});
					});
	
	//监听分数输入框的事件
	function score_input(that, value) {
		var test1 = /^\d\.?\d{0,1}$/;//表示数字开头，数字结尾，保留一位小数
		if (!test1.test(value)) {
			alert("分数只能是整数或保留一位的小数");
			that.value = "";
			return;
		}
	}
	
var questiontype = ${type};
if(questiontype==1){
<!--单选开始-->
	 var ue = UE.getEditor('choiceDesc',{
		 toolbars:[
		           [
					'simpleupload', //单图上传
					'insertimage', //多图上传
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula'
	            
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
						'kityformula'
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
						'kityformula'
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
						'kityformula'
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
						'kityformula'
	 	           ]
	 	         ],
	 	         elementPathEnabled:false,
	 	         autoHeightEnabled:false,
		         imageScaleEnabled:false
	 });
	 var ue5 = UE.getEditor('answerDesc1',{
	 	 toolbars:[
	 	           [
	 					
	 					'simpleupload', //单图上传
	 					'insertimage', //多图上传	
	 					'subscript', //下标
						'superscript', //上标
						'spechars', //特殊字符
						'kityformula'
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
					
					'simpleupload', //单图上传
					'insertimage', //多图上传		
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula'
	            
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
					'kityformula'
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
					'kityformula'
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
					'kityformula'
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
					'kityformula'
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
});
var ue5 = UE.getEditor('answerDesc2',{
	 toolbars:[
	           [
					
					'simpleupload', //单图上传
					'insertimage', //多图上传	
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula'
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
					'simpleupload', //单图上传
					'insertimage', //多图上传		
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula'
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
 });
 
 var ue2 = UE.getEditor('answerDesc3',{
	 toolbars:[
	           [
					'simpleupload', //单图上传
					'insertimage', //多图上传	
					'subscript', //下标
					'superscript', //上标
					'spechars', //特殊字符
					'kityformula'
	           ]
	         ],
	         elementPathEnabled:false,
	         autoHeightEnabled:false,
	         imageScaleEnabled:false
 });
 
 //判断结束
	
} else if(questiontype ==4){
	
	
} else if(questiontype ==5){
	
	 var ue1 = UE.getEditor('subjectiveTitle',{
		 toolbars:[
		           [
						'simpleupload', //单图上传
						'insertimage', //多图上传	
						'subscript', //下标
						'superscript', //上标
						'spechars', //特殊字符
						'kityformula'
		           ]
		         ],
		         elementPathEnabled:false,
		         autoHeightEnabled:false,
		         imageScaleEnabled:false
	 });
	 var ue2 = UE.getEditor('subAnalysis',{
		 toolbars:[
		           [
						'simpleupload', //单图上传
						'insertimage', //多图上传	
						'subscript', //下标
						'superscript', //上标
						'spechars', //特殊字符
						'kityformula'
		           ]
		         ],
		         elementPathEnabled:false,
		         autoHeightEnabled:false,
		         imageScaleEnabled:false
	 });
	
} else if(questiontype ==6){
	 var ue1 = UE.getEditor('fillTitle',{
		 toolbars:[
		           [
						'simpleupload', //单图上传
						'insertimage', //多图上传	
						'subscript', //下标
						'superscript', //上标
						'spechars', //特殊字符
						'kityformula'
		           ]
		         ],
		         elementPathEnabled:false,
		         autoHeightEnabled:false,
		         imageScaleEnabled:false
	 });
	 var ue2 = UE.getEditor('fillAnswer',{
		 toolbars:[
		           [
						'simpleupload', //单图上传
						'insertimage', //多图上传	
						'subscript', //下标
						'superscript', //上标
						'spechars', //特殊字符
						'kityformula'
		           ]
		         ],
		         elementPathEnabled:false,
		         autoHeightEnabled:false,
		         imageScaleEnabled:false
	 });
	 var ue3 = UE.getEditor('fillAnalysis',{
		 toolbars:[
		           [
						'simpleupload', //单图上传
						'insertimage', //多图上传	
						'subscript', //下标
						'superscript', //上标
						'spechars', //特殊字符
						'kityformula'
		           ]
		         ],
		         elementPathEnabled:false,
		         autoHeightEnabled:false,
		         imageScaleEnabled:false
	 });
} else if(questiontype ==7){
	
}
</script>
</html>