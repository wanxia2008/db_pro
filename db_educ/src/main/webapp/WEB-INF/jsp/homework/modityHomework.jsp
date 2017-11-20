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
<title>修改作业</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
.toup {
	cursor:pointer;
}
.todown {
	cursor:pointer;
}
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;top: 20%;font-size: 10px;border-radius: 5px;background:#e33}
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
.icon-star,.icon-star-empty{color:#333 !important}
ol, ul{margin-bottom:0;}
.answer{padding-bottom:10px;}
.answer p{display:inline !important;color:#333 !important}
.answer img{/*width:200px;height:200px;display:block !important;margin:10px 0*/}
.kfformula{width:auto !important;height:100% !important;text-align:left}
</style>
</head>
<body>
	<form class="layui-form" action="">
		<div class="panels_head">
			<span>修改作业</span>
    <button class="layui-btn layui-btn-normal"  lay-submit=""
						lay-filter="demo1"
						style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">确认</button>
<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='showHomeworkList.do?pageNo=${page}'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
			<input hidden="" name="hkId" id="hkId" value="${homework.hkId}"/>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>作业名称</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" lay-verify="hkName" name="hkName" id="hkName"
						autocomplete="off" placeholder="请输入作业标题" value="${homework.hkName}" class="layui-input" maxlength="15">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width: 120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>科目</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="subject" id="subject" lay-verify="subject">
                                <option value="">全部</option>
						<c:forEach items="${sList}" var="g">
						  <c:choose>
						     <c:when test="${homework.subject==g.subjectId}">
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
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>知识范围</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="grade" id="grade" lay-verify="grade">
                           <option value="">全部</option>
						<c:forEach items="${gList}" var="g">
						  <c:choose>
						     <c:when test="${homework.grade==g.gradeId}">
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
				<div class="layui-input-inline"
					style="width: 120px;">
					<select name="knowledgeBegin" id="knowledgeBegin"
						lay-verify="knowledgeBegin" lay-filter="select_knowstart">
						<c:forEach items="${rlList}" var="g">
						<c:choose>
						   <c:when test="${homework.knowledgeBegin==g.lessonId}">
						    <option selected="selected" value="${g.lessonId}">${g.lessonName}</option>
						   </c:when>
						   <c:otherwise>
						   <option value="${g.lessonId}">${g.lessonName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline"
					style="width: 120px;">
					<select name="knowledgeEnd" id="knowledgeEnd"
						lay-verify="knowledgeEnd" lay-filter="select_knowend">
						<c:forEach items="${rlList}" var="g">
							<c:choose>
						   <c:when test="${homework.knowledgeEnd==g.lessonId}">
						    <option selected="selected" value="${g.lessonId}">${g.lessonName}</option>
						   </c:when>
						   <c:otherwise>
						   <option value="${g.lessonId}">${g.lessonName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>所属教材</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="science" id="science"
						lay-verify="science" autocomplete="off" placeholder="请选择所属教材">
						<c:forEach items="${tMaterialsList}" var="t">
						<c:choose>
						  <c:when test="${homework.science==t.materialId}">
						  <option selected="selected" value="<c:out value="${t.materialId}" />"><c:out
									value="${t.materialName}"></c:out></option>
						  </c:when>
						  <c:otherwise>
						  <option value="<c:out value="${t.materialId}" />"><c:out
									value="${t.materialName}"></c:out></option>
						  </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width: 120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>难度</label>
				<div class="layui-input-inline"
					style="width: 120px; margin-top:8px;">
					<c:if test="${homework.difficultyValue==1}">
						<ul class="uls_showstars" style="margin-top:-1px;">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					</c:if>
					<c:if test="${homework.difficultyValue==2}">
						<ul class="uls_showstars" style="margin-top:-1px;">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star" id="2" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					</c:if>
					<c:if test="${homework.difficultyValue==3}">
						<ul class="uls_showstars" style="margin-top:-1px;">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star" id="2" style="cursor: pointer;"></li>
							<li class="icon-star" id="3" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					</c:if>
					<c:if test="${homework.difficultyValue==4}">
						<ul class="uls_showstars" style="margin-top:-1px;">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star" id="2" style="cursor: pointer;"></li>
							<li class="icon-star" id="3" style="cursor: pointer;"></li>
							<li class="icon-star" id="4" style="cursor: pointer;"></li>
							<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					</c:if>
					<c:if test="${homework.difficultyValue==5}">
						<ul class="uls_showstars" style="margin-top:-1px;">
							<li class="icon-star" id="1" style="cursor: pointer;"></li>
							<li class="icon-star" id="2" style="cursor: pointer;"></li>
							<li class="icon-star" id="3" style="cursor: pointer;"></li>
							<li class="icon-star" id="4" style="cursor: pointer;"></li>
							<li class="icon-star" id="5" style="cursor: pointer;"></li>
						</ul>
					</c:if>
				</div>
				<input type="number" hidden="" name="difficultyValue"
					id="difficultyValue" lay-verify="difficultyValue" value="${homework.difficultyValue}" />
			</div>
		</div>
		<!-- <div class="panels_head">
			<span>作业题目</span>
		</div> -->
		
			<table class="layui-table" style="width: 98%; margin: 0 auto">
			<thead>
				<tr>
					<th style="width: 10%">序号</th>
					<th style="width: 10%">题型</th>
					<th style="width: 54%">题目</th>
					<th style="width: 10%">分值</th>
					<!-- <th>难度</th> -->
					<th style="width: 14%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${spList}" var="row" varStatus="suoyin">
					<tr>
						<td><c:out value="${suoyin.index+1}"></c:out></td>
						<td>
						<c:if test="${row.questiontype == 1}">单选</c:if> 
						<c:if test="${row.questiontype == 2}">多选</c:if>
						<c:if test="${row.questiontype == 3}">判断选</c:if> 
						<c:if test="${row.questiontype == 4}">阅读理解</c:if>
						<c:if test="${row.questiontype == 5}">主观题</c:if>
						<c:if test="${row.questiontype == 6}">填空题</c:if>
						<c:if test="${row.questiontype == 7}">完形填空</c:if>
						</td>
						<td style="text-align:left"><c:if test="${row.questiontype == 1}">
							  <div class="answer">${row.choiceDesc}</div>						
						      <div class="answer">A.${row.optionA}</div>
						      <div class="answer">B.${row.optionB}</div>
							  <div class="answer">C.${row.optionC}</div>
							  <div class="answer">D.${row.optionD}</div>
							</c:if> 
							<c:if test="${row.questiontype == 2}">
							 <div class="answer">${row.choiceDesc1}</div>
						      <div class="answer">A.${row.optionA1}</div> 
						      <div class="answer">B.${row.optionB1}</div>
							  <div class="answer">C.${row.optionC1}</div>
							  <div class="answer">D.${row.optionD1}</div>
							</c:if> 
							<c:if test="${row.questiontype == 3}">
								<div class="answer">${row.judgeDesc}</div>
						        A.正确  &nbsp;&nbsp; 0.错误
						   </c:if>
						<c:if test="${row.questiontype == 4}">
								<div class="answer">${row.questionId}、${row.readDesc}</div>							
						<c:forEach items="${row.questionRead}" var="qr" varStatus="status">								
							<div class="answer">${status.index + 1}、${qr.optiontitle}</div>
						      <div class="answer">A.${qr.optionA_read}</div>
						      <div class="answer">B.${qr.optionB_read}</div>
						      <div class="answer">C.${qr.optionC_read}</div>
						      <div class="answer">D.${qr.optionD_read}</div>
							</c:forEach>
							</c:if> 
							<c:if test="${row.questiontype == 5}">
								<div class="answer">${row.subjectiveTitle}</div>
						      
						   </c:if>
						   <c:if test="${row.questiontype == 6}">
								<div class="answer">${row.fillTitle}</div>
						        <div class="answer">${row.fillAnswer}</div>
						</c:if> 
						 <c:if test="${row.questiontype == 7}">
								<div class="answer">${row.clozeContent}</div>
						</c:if> 
						</td>
						<td><c:out value="${row.questionValue}"></c:out></td>
						<td>
						<c:if test="${row.questiontype == 7}">
						<a class="button border-red" href="${ctx}/questionBank/updateCloze.do?clozeId=<c:out value="${row.questionId}"></c:out>&type=7">
												<span class="icon-edit"
												style="font-size: 14px; color: #1e9fff;"> 修改</span>
										</a> &nbsp; 
						</c:if>
						<c:if test="${row.questiontype == 4}">
						 <a class="button border-red"
											href="${ctx}/questionBank/toupdaReading.do?id=<c:out value="${row.questionId}"></c:out>">
												<span class="icon-edit"
												style="font-size: 14px; color: #1e9fff;"> 修改</span>
										</a> &nbsp; 
						</c:if>
						<c:if test="${row.questiontype != 4 && row.questiontype != 7}">
						<a href="${ctx}/homework/modityQuestion.do?questionId=${row.questionId}&type=${row.questiontype}&paperId=${homework.hkId}">
						<span class="icon-edit"
							style="font-size: 14px;color:#008bca;cursor:pointer;">修改</span></a>&nbsp;
						</c:if>
						<a class="button border-red" href="javascript:void(0)"
							onclick="deleteone(<c:out value="${row.paperId}"></c:out>)">
								<span class="icon-remove-sign"
								style="font-size: 15px; color: #ff5722; padding-right: 10px;">
									删除</span>
						</a>
						</td>
					</tr>
				</c:forEach>
				<tr id="pages">
					<td colspan="5">
						<div class="pagelist">
							<a href="javascript:;" onclick="goPageAction(1);">首页</a>
							<c:choose>
								<c:when test="${page == 1}">
									<a href="javascript:;">上一页</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:;" onclick="goPageAction(${page - 1});">上一页
									</a>
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
								</c:otherwise>
							</c:choose>
							<a href="javascript:;" onclick="goPageAction(${totalPages});">末页</a>
							&nbsp;总共&nbsp; ${totalPages} &nbsp;页
						</div>
					</td>
				</tr>
				</tbody>	
			</table>		
		<!-- 	<div id="pages" style="text-align: center;"></div> -->
	</form>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript">
	layui
			.use(
					[ 'form', 'layedit', 'laypage', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

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
							science : function(value) {
								if (value.length == "") {
									return '请选择所属教材';
								}
							},
							knowledgeBegin : function(value) {
								if (value.length == "") {
									return '请选择知识起始水平';
								} else {
									var end = $('#knowledgeEnd').val();
									if (parseInt(value) > parseInt(end)) {
										return '请正确选择知识范围';
									}
								}
							},
							knowledgeEnd : function(value) {
								if (value.length == "") {
									return '请选择知识起始水平';
								} else {
									var start = $('#knowledgeBegin').val();
									/* if (parseInt(value) <= parseInt(start)) {
										return '请正确选择知识范围';
									} */
								}
							},
							difficultyValue : function(value) {
								if (value.length == "") {
									return '请点击五角星选择难度等级';
								}
							}
						});

						//监听提交
						form.on('submit(demo1)', function(data) {
							$.ajax({
								url:'updateHomework.do',
								type:'post',
								dataType:'json',
								data:{
									science:$('#science').val(),
									grade:$('#grade').val(),
									knowledgeBegin:$('#knowledgeBegin').val(),
									knowledgeEnd:$('#knowledgeEnd').val(),
									difficultyValue:$('#difficultyValue').val(),
									subject:$('#subject').val(),
									hkName:$('#hkName').val(),
									hkId:$('#hkId').val()
								},
								success:function(res) {
									if(res.code==0) {
										layer.msg("修改作业成功!");
										window.location.href="${ctx}/homework/showHomeworkList.do?pageNo="+${page};
									} else {
										layer.msg(res.msg);
										return;
									}
								}
							})
							return false;
						});
					});
	
	function deleteone(uid){
		 layer.confirm('您确认删除该道题目么?',{btn:['确认','取消']},function(){
			 $.post('${ctx}/homework/deleteTimu.do?',{'paperId':uid},function(data){
				  if(data.code==0){
						layer.msg('删除题目成功');
						window.location.href="${ctx}/exam/modity.do";
						window.location.reload();
				  }else{
						layer.msg(data.msg);
						return;
				  }
			 });
		    },function(){	
			       layer.closeAll();  
		});
	}
	
	
	function goPageAction(page){
		window.location.href="${ctx}/homework/modity.do?pageNo="+page+"&paperId=${homework.hkId}";
		
		}
// 	function goMyPage(page,totalPage){
// 		if(page>0 && page<=totalPage){
// 			window.location.href="${ctx}/homework/modity.do?pageNo="+page+"&hkId=${homework.hkId}";
// 		}else{
// 			alert("请输入有效的整数");
// 		}
// 	}
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
</script>
</html>