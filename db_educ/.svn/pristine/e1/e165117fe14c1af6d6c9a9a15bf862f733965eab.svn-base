<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
.icon-star,.icon-star-empty{color:#333 !important}
.mg6{margin-top:-2px}
.layui-table td, .layui-table th {
    padding: 5px 15px;
    /* min-height: 20px; */
    /* line-height: 20px; */
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
<body>
	<form class="layui-form" id="myform" method="post" action="">
		<div class="panels_head">
			<span>修改题目</span>
			<span style="text-align:center;color: red;right: 50%;background:none;font-size:14px;">${errorMsg}</span>
			
		<button type="button" class="layui-btn layui-btn-normal" onclick="modityFill(<c:out value="${fill.fillId}"></c:out>)"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">确认</button>
		<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/questionBank/myquestionbank.do?handoutType=<c:out value="${type}"></c:out>'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
		</div>
		<input hidden="" id="update_type" value="${type}" />
		<c:if test="${type==6}">
			<input hidden="" name="readId" value="${fill.fillId}" />
			<div class="layui-form-item" style="margin-top:20px;">
			
			<div class="layui-inline">
			<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="knowledge" id="knowledge" lay-verify="knowledge">
							<option value="">请选择</option>
							<c:forEach items="${gList}" var="g">
								<c:choose>
									<c:when test="${fill.knowledge==g.gradeId}">
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
									<c:when test="${fill.subject==s.subjectId}">
										<option selected="selected" value="${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
									</c:when>
									<c:otherwise>
										<option value="${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
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
									<c:when test="${fill.knowledgeBegin==lesson.lessonId}">
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
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">填空题类型</label>
				<div class="layui-input-inline"
					style="width: 120px;">
						<select name="typeId" id="typeId">
							<option value="">请选择</option>
							<c:forEach items="${typeList}" var="lesson">
								<c:choose>
									<c:when test="${fill.typeId==lesson.typeId}">
										<option selected="selected" value="${lesson.typeId}">${lesson.typeName}</option>
									</c:when>
									<c:otherwise>
										<option value="${lesson.typeId}">${lesson.typeName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
			</div>
		</div>

			<div class="panels_head">
			<span>题目内容</span>
			</div>
			<div class="layui-form-item" style="margin: 20px 0">
			<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>阅读正文</label>
				<div class="layui-inline">
					<textarea id="fillTitle" name="fillTitle" lay-verify="readDesc"
						style="width: 710px; height: 300px; border-radius: 5px;"
						placeholder="输入题目">${fill.fillTitle}</textarea>
				</div>
				<p style="color: red;margin-left:120px;">备注：填空题所需填空处请以两个或两个以上"_"分割开</p>
			</div>
			<%-- 	<div class="layui-form-item" style="margin: 20px 0">	   
           <div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size:14px;">答案</label>
						<textarea style="width: 710px; height: 200px; border-radius: 5px;"
							placeholder="请输入题目答案" class="layui-textarea" name="fillAnswer"
							id="fillAnswer" lay-verify="fillAnswer">${fill.fillAnswer}</textarea>
				</div>
	      </div> --%>
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
					<textarea name="answerAnalysis" id="answerAnalysis" placeholder="请输入题目释义" 
						style="width: 710px; height: 200px; border-radius: 5px;margin-left: 120px;"
						>${fill.answerAnalysis}</textarea>
				</div>
	      </div>
			 	<div class="layui-form-item" style="margin: 20px 0">
			
			 <div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>题目来源</label>
					<div class="layui-input-inline" style="width: 150px;">
						<select name="questionSource" id="source_read"
							lay-verify="source_read">
							<option value="">请选择</option>
							<c:forEach items="${qsList}" var="qs">
								<c:choose>
									<c:when test="${fill.fillSourse==qs.sourceId}">
										<option selected="selected" value="${qs.sourceId}">${qs.sourceName}</option>
									</c:when>
									<c:otherwise>
										<option value="${qs.sourceId}">${qs.sourceName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
			
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>难度</label>
					<div class="layui-input-inline"
						style="width: 100px; margin-top: 8px;">
						<c:if test="${fill.questionDegree==1}">
							<ul class="uls_showstars3 mg6">
								<li class="icon-star uls_showstars3_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${fill.questionDegree==2}">
							<ul class="uls_showstars3 mg6">
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
						</c:if>
						<c:if test="${fill.questionDegree==3}">
							<ul class="uls_showstars3 mg6">
								<li class="icon-star uls_showstars3_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${fill.questionDegree==4}">
							<ul class="uls_showstars3 mg6">
								<li class="icon-star uls_showstars3_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star-empty uls_showstars3_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
						<c:if test="${fill.questionDegree==5}">
							<ul class="uls_showstars3 mg6">
								<li class="icon-star uls_showstars3_1" id="1"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_2" id="2"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_3" id="3"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_4" id="4"
									style="cursor: pointer;"></li>
								<li class="icon-star uls_showstars3_5" id="5"
									style="cursor: pointer;"></li>
							</ul>
						</c:if>
					</div>
					<input type="number" min="0" max="100" name="questionDegree" hidden="" id="degree_read"
						lay-verify="degree_read" value="${fill.questionDegree}" />
				</div>
				
				</div>
				
				<div class="layui-inline">
				
					<table style="padding:5px 20px;font-size: 14px;margin:0;display:inline">
						<tbody id="tbody5">
							<c:forEach items="${fill.qtList}" var="tags">
								<tr id="${tags.tagId}" class="tag_tr4" style="cursor: pointer;">
									<td class="hline"><span class="tagclass"><em class='icon-remove-sign' style='color:#ff0000;margin-right:5px'></em>${tags.tagName}</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input hidden="" value="${fill.tagId}" name="tagId" lay-verify="tag4" id="tag4" />
				<button type="button" class="btn btn-primary bt10" data-toggle="modal"
						onclick="openModal()"
						style="margin-bottom:15px;background-color:#008bca;border:0;border-radius:5px;">+标签</button>
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
								<label class="layui-form-label" style="width:120px;font-size: 14px;">创建新标签</label>
									<div class="layui-input-inline" style="width:200px;">
										<input type="text"  id="create_input_tag"
											autocomplete="off" placeholder="请输入标签名称" class="layui-input">
									</div>
									<button type="button" class="layui-btn layui-btn-normal searchbtn" id="create_tag">创建</button>									
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
		<div class="layui-form-item" style="margin-top:10px;padding-top:10px;"></div>

	</form>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././js/bootstrap.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript">
	var index;//加载框变量
	var pageNo = 1, pageSize = 100;
	var totalScore = 0;//试卷总分
	$(document).ready(function() {
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
														'icon-star uls_showstars3_'+ i);
										for (var j = 5; j > id; j--) {
											$(".uls_showstars3_" + j).removeClass().addClass(
															'icon-star-empty uls_showstars3_'+ j);
										}
									}
									$('#degree_read').val(i - 1);
								})
						var array = new Array();
						var jsonstr = '[';
						$('#confirm2').on('click',function() {
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
												var title = $('#Optiontitle' + i).val();
												var oA = $('#OptionA_read' + i).val();
												var oB = $('#OptionB_read' + i).val();
												var oC = $('#OptionC_read' + i).val();
												var oD = $('#OptionD_read' + i).val();
												var answer = $('input[name="answer_read'+ i
																+ '"]:checked').val();
												var answerdesc = $('#answerDesc_read' + i).val();
												// 						//	 			var parentid =  parentId;
												var score = $('#score_read' + i).val();
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
											jsonstr = jsonstr.substring(0,
													jsonstr.length - 1);
											jsonstr += ']';
											ajax_read(array)
													.then(
															function(res) {
																//得到parentId
																var parentId = res.objects[0].readId;
																ajax_read1(jsonstr,parentId).then(
																				function(res) {
																					layer.close(index);
																					window.location.href = "myquestionbank.do";
																				},
																				function() {
																					layer.msg("抱歉，添加失败，请重试！");
																				});
															},
															function(error) {
																layer.close(index);
																layer.msg("抱歉，添加失败，请重试！");
															});
										})
					});
	layui.use([ 'form', 'layedit', 'laypage', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

						var vlaue = $('#update_type').val();
						 

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
						/* 	type : function(value) {
								if (value.length <= 0) {
									return '请选择类型';
								}
							},
						
							knowledgeBegin : function(value) {
								if (value.length <= 0) {
									return '请选择知识水平';
								}
							},
							knowledgeEnd : function(value) {
								if (value.length <= 0) {
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
							  $("#tbody5").html('');
							  //重置标签赋值
							  $('#tag4').val('');
							
							 
							 						  							  
						})
						//监听提交
						form.on('submit(demo1)', function(data) {							
							return true;
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
		var subjectId = $("#subject").val();
		$.ajax({
			url : '${ctx}/questionTag/gettagbysearch.do',
			type : 'post',
			dataType : 'json',
			data : {
				content : value,
				subjectId:subjectId
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
				subject:subjectId,
				grade:grade
			},
			success : function(res) {
				if (res.code == 0) {
					var tagId = res.objects[0].tagId;
					sucess_totemplate(res);
					var tagName=$("#checked-"+tagId).text();
					var html = "<tr id="+tagId+" class='tag_tr4' style='cursor: pointer;'><td class='hline'><span class='tagclass'><em class='icon-remove-sign' style='color:#ff0000;margin-right:5px'></em>"
					+ tagName + "</span></td></tr>";
					var val1 = $('#tag4').val();
					if (val1 == "") {
						$('#tag4').val(tagId);
					} else {
						$('#tag4').val(val1 + "," + tagId);
					}
					$('#tbody5').append(html);
					object_tb_click(tagId);
					$('#myModal').modal('hide');
					
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
					});
					
				} else {
					layer.msg(res.msg);
				}
			}
		})
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
		} else if (state == 5) {
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
					
					$('#myModal').modal('hide');
				})
	}
	function modityTopic(uid){
		var roleIds = new Array();
		var answer_read ="answer_read-"+uid;
		$('input[name^="'+answer_read+'"]:checked').each(function(i) {
			roleIds.push($(this).val());//向数组循环添加选中的值
		});
		var answer =roleIds.join(",");//转为字符串
		//取值
		$.ajax({
			url:'modityRead.do',
			type:'post',
			dataType:'json',
			data:{
				readId:uid,
				optiontitle:$("#optiontitle_"+uid).val(),
				optionA_read:$('#optionA_read_'+uid).val(),
				optionB_read:$('#optionB_read_'+uid).val(),
				optionC_read:$('#optionC_read_'+uid).val(),
				optionD_read:$('#optionD_read_'+uid).val(),
				answer_read:answer,
				score_read:$('#score_read_'+uid).val(),
				answerDesc_read:$('#answerDesc_read_'+uid).val(),
			},
			success:function(data){
				if(data.code==0){
					layer.msg('编辑成功!');
					window.location.href="${ctx}/questionBank/myquestionbank.do?handoutType=4"; 
				}else{
					alert(data.msg);
					return;
				}
			},
		});
	}
	function modityFill(uid){
		var title = $("textarea[name='fillTitle']").val();
		var fillAnswer = $("textarea[name='fillAnswer']").val();
		var analysis = $("textarea[name='answerAnalysis']").val();
		$.ajax({
			url:'updateFill.do',
			type:'post',
			dataType:'json',
			data:{
				fillId:uid,
				fillTitle:title,
				fillAnswer:fillAnswer,
				answerAnalysis:analysis,
				tagId:$('#tag4').val(),
				questionDegree:$('#degree_read').val(),
				questionSource:$('#source_read').val(),
				subject:$('#subject').val(),
				knowledge:$('#knowledge').val(),
				knowledgeBegin:$('#knowledgeBegin').val(),
				typeId:$('#typeId').val()
			},
			success:function(data){
				if(data.code==0){
					layer.msg('编辑成功!');
					window.location.href="${ctx}/questionBank/myquestionbank.do?handoutType=6"; 
				}else{
					layer.msg(data.msg);
		    		return;
				}
			},
		});
	}
	 var ue = UE.getEditor('fillTitle',{
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
		         elementPathEnabled:false
	 });	
	 
	 var ue1 = UE.getEditor('fillAnswer',{
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
	 var ue2 = UE.getEditor('answerAnalysis',{
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
		         elementPathEnabled:false 
	 });	
	 
	 function openModal(){
		    //重置输入框
			$('#input_tag').val('');
		    $('#create_input_tag').val('');
			var subjectId = $("#subject").val();
			var grade = $("#knowledge").val();
			var subjectName = $("#subject option:selected").attr("data-name");
			var gradeName = $("#knowledge option:selected").attr("data-name");
			var signName = gradeName+subjectName;
			if(subjectId){
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
									getTag(sid,obj.curr);
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