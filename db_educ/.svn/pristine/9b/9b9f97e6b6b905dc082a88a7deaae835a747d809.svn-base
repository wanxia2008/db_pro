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
<title>生成试卷</title>
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
#aaaa{
  display:none;
}
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;top: 20%;font-size: 10px;border-radius: 5px;background:#e33}
ol, ul {
    margin-top: 4px;
    /* margin-bottom: 10px; */
}
.icon-star,.icon-star-empty{color:#333 !important}
.answer{padding:5px 0;}
.answer p{display:inline !important;color:#333 !important}
.answer img{width:200px;height:200px;display:block !important;margin:10px 0 }
img.kfformula{display:inline !important;height:50px;width:50px;}
.layui-laypage a, .layui-laypage span{margin-right:5px;padding:0 18px;border-radius:4px;font-size:14px;}
.layui-laypage .layui-laypage-curr .layui-laypage-em{border-radius:4px;}
.layui-laypage>:first-child, .layui-laypage>:first-child em{border-radius:4px;}
.layui-laypage button, .layui-laypage input{border-radius:4px;}
</style>
</head>
<body>
	<form class="layui-form">
		<div class="panels_head">
			<span>试卷属性</span>
			<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" style="margin-right: 80px;height:30px;line-height:30px;padding:0 18px;top:20%;margin-top:0;font-size:14px">确认</button>			 
<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/exam/showmyexam.do'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
		<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>年级</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="grade" id="grade" lay-verify="grade">
						<option value="">请选择</option>
						<c:forEach items="${gList}" var="g">
							<option value="${g.gradeId}" data-name="${g.gradeName}">${g.gradeName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>科目</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="subject" id="subject" lay-verify="subject" lay-filter="subject">
						<option value="">请选择</option>
						<c:forEach items="${sList}" var="s">
							<option value="${s.subjectId}" data-name="${s.subjectName}">${s.subjectName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>试卷类型</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="piType" lay-verify="piType" id="piType">
						<option value="">请选择</option>
						<c:forEach items="${typeList}" var="type">
							<option value="${type.getTypeId()}">${type.getTaskTypeName()}</option>
						</c:forEach>
					</select>
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
					id="difficultyValue" lay-verify="difficultyValue" value="1" />
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>试卷名</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" lay-verify="piName" name="piName" id="piName"
						autocomplete="off" placeholder="请输入试卷名称" class="layui-input" maxlength="30">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">所属教材</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="science" id="science" lay-verify="science" 
						autocomplete="off" placeholder="请选择所属教材">
						<option value=""></option>
<%-- 						<c:forEach items="${tMaterialsList}" var="t"> --%>
<%-- 							<option value="<c:out value="${t.materialId}" />"><c:out --%>
<%-- 									value="${t.materialName}"></c:out></option> --%>
<%-- 						</c:forEach> --%>
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
						<c:forEach items="${rlList}" var="g">
							<option value="${g.lessonId}">${g.lessonName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline"
					style="width: 120px;">
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
        <div class="layui-form-item" style="margin-top: 20px;">
        <div class="layui-inline">
				<label class="layui-form-label" style="width: 120px; font-size: 14px;">考试时长</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="number" name="paperTime" id="paperTime" value="10" lay-verify="paperTime"
						autocomplete="off" placeholder="请输入时间" min="1" maxlength="3" class="layui-input"
						onkeyup="score_input(this,this.value)">
				</div>
				<span style="padding-top: 5px;margin-left:-12px;float: left;">（分钟）</span>
		</div>
         <div class="layui-inline" > 
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">考试说明</label>
				<div class="layui-input-inline" style="width: 150px;">
					<textarea style="width: 280px; min-height:50px; border-radius: 5px;resize:none"
							placeholder="请输入考试说明" class="layui-textarea" name="paperExplain"
							id="paperExplain" lay-verify="paperExplain" autocomplete="off"></textarea>
				</div>
			</div>
		</div>
		
			
		<!-- 试卷内容 -->
		<div class="panels_head">
			<span>试卷内容</span>
			<button type="button" class="layui-btn layui-btn-normal"
				data-toggle="modal" id="add_stu" onclick="addExam()" style="height:30px;line-height:30px; padding:0 18px;top:20%;margin-top:0;font-size:14px;">添加</button>
		</div>

		  <p class="no_data_p"  id="nodatapaper" style="margin-top:20px;font-size:14px;color:#008bca;letter-spacing:2px" >~暂无数据请先去添加试卷题目~</p>
<!-- 		  <a data-toggle="modal" id="add_stu" data-target="#myModal" style="font-size:20px;height:30px;line-height:30px;color:#57B6F2;font-weight:blod; cursor:pointer;">新增题目</a></p> -->
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
		<!--模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:80%;">
				<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel"><span id="chooseGrdSub"></span>标签</h4>
                  </div>
					<div class="modal-body">
					<div  class="layui-form">
						<div class="layui-form-item"
							style="margin-top: 10px; margin-bottom: 5px">
							<div class="layui-inline">
								<label class="layui-form-label" style="width:100px;">题目题型</label>
								<div class="layui-input-inline" style="width:120px;">
									<select name="topicType" id="topicType" lay-filter="fillType">
										<option value="">请选择</option>
										<c:forEach items="${qtList}" var="qq">
											<option value="${qq.topicId}">${qq.topicName}</option>
										</c:forEach>
									</select>
									<%--  <form:select  itemLabel="topicId" itemValue="topicName" items="${qqtList}" path="qqtList.topicType"></form:select>  --%>
								</div>
							</div>
				<div id="aaaa">  
			<div class="layui-inline">
				<label class="layui-form-label"
					style="width: 140px; font-size: 12px;"><span
					style="color: red; font-size: 16px !important">&nbsp;&nbsp;</span>填空题标题名称</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="typeId">
						
						<option value="" >请选择</option>
						<c:forEach items="${fillList}" var="lesson">
							<option value="${lesson.typeId}">${lesson.typeName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			</div>
							<div class="layui-inline">
								<label class="layui-form-label"
									style="width: 100px; font-size: 14px;">数量</label>
								<div class="layui-input-inline" style="width:120px;">
									<input type="number" name="number" min="1" max="100" maxlength="2"  step="1" id="number" lay-verify="paperTime"
										autocomplete="off" placeholder="请输入数量" value="10" class="layui-input"
										onkeyup="score_input(this,this.value)">
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label"
									style="width: 100px; font-size:14px;">分值</label>
								<div class="layui-input-inline" style="width:120px;">
									<input type="number" min="1" max="100" maxlength="2" value="5" name="score" id="score" lay-verify="paperTime"
										autocomplete="off" placeholder="请输入分值" class="layui-input"
										onkeyup="score_input(this,this.value)">
								</div>
							</div>
						</div>
						  <div class="layui-form-item">
						    <label class="layui-form-label" style="width: 100px; font-size:14px;">选择标签</label>
						    <div class="layui-input-block" id="tag_list1">
						      
						    </div>
						     <div class="row">								  
								  <div class="col-md-12" id="pages" style="text-align:center"></div>							  
							  </div>
						  </div>
						
						  <input hidden="" name="tagId" id="tagId" />
						  <!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->
						</div>
						
					</div>
					<div class="modal-footer">
						<button type="button" id="create_question" class="btn btn-primary" style="height:30px;line-height:30px;padding:0 18px;">生成</button>
						<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
					</div>
				</div>
			</div>
			</div>
			<!--模态框结束-->
			<!--模态框2-->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width: 40%;">
					<div class="modal-content">
						<div class="modal-body">
							<div class=""
								style="width: 300px; height: 50px; margin-left: 30px;">
								<div class="input-group" style="float: left;">
									<input type="text" class="form-control" id="input_tag"
										placeholder="请输入要查找的标签，如there" style="width: 200px;">
								</div>
								<button type="button" class="btn btn-success" id="select_tag"
									style="float: right;">查找</button>
							</div>
							<div class="handouts_property" style="margin-top: 0px;">
								<div id="tag_list"></div>
								<div id="pages22" style="text-align: center; margin-top: 30px;"></div>
								
							</div>
							<div class="handouts_property"
								style="width: 100%; margin-left: 0px; border: 1px solid #EEEEEE; padding-top: 10px; margin-top: 10px;">
								<label>创建新标签</label>
								<div class=""
									style="width: 300px; height: 50px; margin-left: 30px;">
									<div class="input-group" style="float: left;">
										<input type="text" class="form-control"
											placeholder="key:value" style="width: 150px;"
											id="create_input_tag">
									</div>
									<button type="button" class="btn btn-success"
										style="float: right;" id="create_tag">创建</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</form>
	<script type="text/html" id="tag_template">
              
			{{each objects as value i}}		
              <input type="checkbox" lay-skin="primary" lay-filter="tag_filter" name="tag" title="{{value.tagName}}"
										value="{{value.tagId}}">          
			{{/each}}

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
               <div class="answer">{{value.choiceDesc1}}</div>
               <div class="answer">A.{{value.OptionA1}}</div>
               <div class="answer">B.{{value.OptionB1}}</div>
               <div class="answer">C:{{value.OptionC1}}</div> 
               <div class="answer">D:{{value.OptionD1}}</div>
			</td>
			<td>{{value.score1}}</td>
			<td>{{value.degree1}}</td>
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
                   <div class="answer">{{value.judgeDesc}}</div>
                    </br>A.正确  &nbsp;&nbsp; B.错误
			</td>
			<td>{{value.score2}}</td>
			<td>{{value.degree2}}</td>
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
			<td style="text-align:left"> <div class="answer">{{value.readDesc}}</div></td>
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
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<script src=".././layui/layui.js" charset="UTF-8"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script>
template.config("escape", false); //转移html
	var questionList = '', questionjson, questionlength = "", totalScore = 0;
	var score;//题目的分数
	var shiti='';//所增加的题目
	var pageNo = 1, pageSize = 100;
	layui.use([ 'form', 'layedit', 'laypage', 'laydate' ],
					function() {
						form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;
						//创建一个编辑器
						//var editIndex = layedit.build('LAY_demo_editor');
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
							/* science : function(value) {
								if (value.length == "") {
									return '请选择所属教材';
								}
							}, */
							grade : function(value) {
								if (value.length == "") {
									return '请选择年级';
								}
							},
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
							},
							piType : function(value) {
								if (value.length == "") {
									return '请选择试卷类型';
								}
							},
							subject : function(value) {
								if (value.length == "") {
									return '请选择科目';
								}
							},
							piName : function(value) {
								if (value.length == "") {
									return '请输入试卷名';
								}
							},
// 							paperExplain : function(value) {
// 								if (value.length == "") {
// 									return '请输入考试说明';
// 								}
// 							},
							paperTime : function(value) {
								var ageName = /^\+?[1-9][0-9]*$/;
								 if (value > 0 && !ageName.test(value)) {
									return "请输入正整数";
								} else if(value.length>3){
									return "时长不能大于3位";
								}
							} 
						});

						//监听提交
						form.on('submit(demo1)',function(data) {						
											if (questionList == null || questionList == '') {
												layer.msg("您还未添加试题，赶快去添加把~");
											} else {
												var loadIndex = layer.load(0, {
													  shade: [0.8,'#000'] //0.1透明度的白色背景
													});
												$.ajax({
															url : 'savePaperInfo.do',
															type : 'post',
															dataType : 'json',
															data : {
																piName : $('#piName').val(),
																subject : $('#subject').val(),
																piType : $('#piType').val(),
																grade : $('#grade').val(),
																science : $('#science').val(),
																isPublic : 0,//默认私有
																knowledgeBegin :$('#knowledgeBegin').val(),
																knowledgeEnd : $('#knowledgeEnd').val(),
																difficultyValue : $('#difficultyValue').val(),
																questionList : questionList,
																score : score,
																totalScore : totalScore,
																questionlength : questionlength,
																paperExplain:$('#paperExplain').val(),
																paperTime:$('#paperTime').val()
															},
															success : function(res) {
																layer.close(loadIndex);
																if (res.code == 0) {
																	layer.msg("添加试卷成功！");
																	setTimeout(function(){
																		window.location.href = "showmyexam.do";
																	},1000);
																}else{
																	layer.msg(res.msg);
																}
															}
														})
											}
											return false;
										});
// 						getjiaocai(1);
						form.on('select(subject)',function(data){
							var subjectId=data.value;
							getjiaocai(subjectId);
						})
						form.on('select(fillType)',function(data){
							var type=data.value;
							if(type == 6){
								$("#aaaa").css('display','block');
							} else{
								$("#aaaa").css('display','none');
							}
						})
					});
	
	$('#create_question').on('click', function() {
		var type = $('#topicType').val();			
		var number = $('#number').val();
		if(number<=0){
			layer.msg('请输入合法的试题数量');
			return false;
		}
		if(number.length>2){
			layer.msg('试题数量不能大于3位数');
			return false;
		}
		score = $('#score').val();
		if(score<=0){
			layer.msg('试题分值需大于0');
			return false;
		}
		if(score.length>2){
			layer.msg('试题分值需大于3位数');
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
						$('#papercontent').show();
						$('#myModal').modal('hide');
						$("#nodatapaper").hide();
						toupordown(questionjson);//执行题目上下移操作
						deletes(questionjson);//删除题目操作
					} else {
						var status=0;
						var object = questionjson.objects;
						var data = res.objects;
						console.log("缓存的Objects"+JSON.stringify(object));
						console.log("拿到的Objects"+JSON.stringify(res.objects));
						for(var j=0;j<object.length;j++) {
							for (var i = 0; i < data.length; i++) {
								//先判断查询结果是否已在列表中
								if(type==1) {
									//单选
									if(object[j].choiceId==data[i].choiceId && object[j].type==1) {
										console.log("单选多选----在列表中");
										res.objects.splice(i,1);//如果已存在则把数组内的元素删掉
									}
								} else if(type==2) {
									//多选
									if(object[j].choiceId==data[i].choiceId && object[j].type==2) {
										console.log("单选多选----在列表中");
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
										console.log("主观---在列表中");
										res.objects.splice(i,1);//如果已存在则把数组内的元素删掉
									}
								} else if(type == 6) {
									//填空题
									if(object[j].fillId==data[i].fillId) {
										console.log("填空---在列表中");
										res.objects.splice(i,1);//如果已存在则把数组内的元素删掉
									}
								} else if(type == 7) {
									//填空题
									if(object[j].clozeId==data[i].clozeId) {
										console.log("完形填空填空---在列表中");
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
// 					layer.msg("该条件下暂无"+(type==1?"单选题":type==2?"多选题":type==3?"判断题":type==4?"阅读理解":type=5?"主观题":"填空")+"的题目");
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
			layer.confirm('确认移除该题吗？', {
				btn : [ '确定','取消']
			}, function() {//确定的处理		
				var objects = questionjson.objects;
				objects.splice(id,1);
				questionList = JSON.stringify(questionjson);
				var html = template("single_template", questionjson);
				$('#tbody').html(html);
				toupordown(questionjson);//执行题目上下移操作
				deletes(questionjson);
			    layer.closeAll();  
			},function(){
			       layer.closeAll();  
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
				deletes(questionjson);
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
				deletes(questionjson);
			}
		});
	}
	
	function addExam(){
		 var subjectId = $('#subject').val();
		 var grade = $('#grade').val();
		 var gradeName = $("#grade option:selected").attr("data-name");
	     var subjectName = $("#subject option:selected").attr("data-name");
	     var signName = gradeName+subjectName;
		 if(subjectId && grade){ //允许打开添加试题模态框
			 layer.closeAll(); //隐藏弹出层
			 $("#chooseGrdSub").text(signName);
			 $("#myModal").modal('show');
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
				if (res.count > 0) {
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
				layer.msg("该条件下暂无标签");
			}
		})
	}
	
	function sucess_totemplate(res) {
		var html = template('tag_template', res);
        $('#tag_list1').html(html);
        form.render('checkbox');
	}
	
	function getjiaocai(subjectId){
		$.ajax({
			type:"post",
			url:"getSubjectMaterial.do",
			data:{
				subjectId:subjectId
			},
			dataType:"json",
			success:function(data){
				if(data.code == 0){				
					    $("#science").empty();
	                    $("#science").append("<option value=''>请选择</option>");
	                    $.each(data.objects,function(index,item){
	                        $("#science").append( "<option value='"+item.materialId+"'>"+item.materialName+"</option>");
	                    });
	                    form.render('select', 'science');
 
				}else{
				    $("#science").empty();
				}
				
            }
		});
	}
</script>
</html>