<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<title>报名管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
.layui-laypage a, .layui-laypage span{margin-right:5px;padding:0 18px;border-radius:4px;font-size:14px;}
.layui-laypage .layui-laypage-curr .layui-laypage-em{border-radius:4px;}
.layui-laypage>:first-child, .layui-laypage>:first-child em{border-radius:4px;}
.layui-laypage button, .layui-laypage input{border-radius:4px;}
#tbody234 tr td p{display:inline-block !important;}
</style>
</head>
<body>
	<form class="layui-form" id="frm1" action="">
		<div class="panels_head">
			<span>试卷分析列表</span>
			<p style="float: right; margin-right: 20px;">
				<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/examRecord/paperList.do?classId=${classId}'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
			</p>
		</div>
		<c:if test="${danType!=null}">
		<span style="height: 40px; line-height: 40px; padding: 0 18px; color: #919191; top: 20%; margin-top: 0;margin-left:1%;border-radius:0; font-size: 14px">单选题平均分:${typeAvg}分</span>
		</c:if>
		<c:if test="${duoType!=null}">
		<span style="height: 40px; line-height: 40px; padding: 0 18px; color: #919191; top: 20%; margin-top: 0;margin-left:1%;border-radius:0; font-size: 14px">多选题平均分:${typeAvg}分</span>
		</c:if>
		<c:if test="${pduanType!=null}">
		<span style="height: 40px; line-height: 40px; padding: 0 18px; color: #919191; top: 20%; margin-top: 0;margin-left:1%;border-radius:0; font-size: 14px">判断题平均分:${typeAvg}分</span>
		</c:if>
		<c:if test="${yueType!=null}">
		<span style="height: 40px; line-height: 40px; padding: 0 18px; color: #919191; top: 20%; margin-top: 0;margin-left:1%;border-radius:0; font-size: 14px">阅读理解题平均分:${typeAvg}分</span>
		</c:if>
		<c:if test="${tianType!=null}">
		<span style="height: 40px; line-height: 40px; padding: 0 18px; color: #919191; top: 20%; margin-top: 0;margin-left:1%;border-radius:0; font-size: 14px">填空题平均分:${typeAvg}分</span>
		</c:if>
		<c:choose>
			<c:when test="${paList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table" style="width: 98%; margin: 0 auto">
					<thead>
						<tr>
							<th>题型</th>
							<th>题目</th>
							<th>做对人数</th>
							<th>做错人数</th>
							<th>题目标签</th>
							<th>得分率</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach items="${paList}" var="s">
							<tr>
								<td>${s.questionName}-${s.questionId}</td>
								<td><a href="javascript:void(0)" class="button" onclick="goQuestion(${s.questionId},${s.questionType})" style="color:#008bca">${s.timuName}</a></td>
								<td><a href="javascript:void(0)" class="button" onclick="showStudent(1,1,${classId},${paperId},${s.questionId},${s.questionType})" style="color:#008bca">${s.trueNumber}</a></td>
								<td><a href="javascript:void(0)" class="button" onclick="showStudent(1,0,${classId},${paperId},${s.questionId},${s.questionType})" style="color:#008bca">${s.errorNumber}</a></td>
								<td>${s.tagName}</td>
								<td>${s.scoreRate}%</td>
							</tr>
						</c:forEach>
						<tr id="pages">
							<td colspan="14">
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
			</c:otherwise>
		</c:choose>
	</form>
	<!-- 模态框 1开始-->
	<div class="modal fade" id="myModal23" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%;">
			<div class="modal-content">
			<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">学生列表</h4>
            </div>
				<div class="modal-body">
						<table class="layui-table" style="width: 98%; margin-left: auto; margin-right: auto; margin-top: 10px;">
						<thead>
							<tr>
								<th>ID</th>
								<th>学生姓名</th>
								<th>提交答案</th>
								<th>提交时间</th>
							</tr>
						</thead>	
						<tbody id="tbody23">
							
						</tbody>
					</table>
					  <div id="page23" style="text-align: center;"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框1 结束 -->
	
	<!-- 模态框 2开始-->
	 <div class="modal fade" id="myModal24" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%;">
			<div class="modal-content">
			<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">题目详情</h4>
            </div>
				<table class="layui-table" style="width: 98%; margin-left: auto; margin-right: auto; margin-top: 10px;">
						<thead>
							<tr>
								<th>题目</th>
								<th>答案</th>
								<th>解析</th>
								<th>难度</th>
							</tr>
						</thead>	
						<tbody id="tbody234">
							
						</tbody>
					</table>
					 <div id="page234" style="text-align: center;"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框2结束 -->
	

<script type="text/html" id="note_template23">
		{{each objects as value i}}
		    <tr>
            <td>{{value.studentId}}</td>
			<td>{{value.student.studentName}}</td>
            <td>
               {{if value.questionType == 3}}
                   {{if value.writeAnswer == 1}}
                         A、正确
                   {{else}}
                         B、错误 
                   {{/if}}     
                {{else}}
                 {{=value.writeAnswer}}
                {{/if}}
            </td>
            <td>{{value.createTime}}</td>
		</tr>
      {{/each}}
	</script>	
	
	<script type="text/html" id="topicanalysis">
		{{each objects as value i}}
		    <tr>
            <td>
     
      {{if value.questionType == 4}}
      {{=value.readDesc}}
      {{/if}}
      {{=value.timuName}}
    {{if value.questionType == 1 || value.questionType == 2 || value.questionType == 4}}
<br/>
             A、{{=value.optionA}}
<br/>
             B、{{=value.optionB}}
<br/>
             C、{{=value.optionC}}
<br/>
             D、{{=value.optionD}}
     {{/if}}
            </td>
			<td>
            {{if value.questionType == 3}}
                   {{if value.answer == 1}}
                         A、正确
                   {{else}}
                         B、错误 
                   {{/if}}     
                {{else}}
                 {{=value.answer}}
                {{/if}}
           </td>
            <td>{{=value.analysis}}</td>
            <td>{{=value.degree}}&nbsp;星</td>
		</tr>
      {{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
var pageno = 1, pagesize = 5,seasonType,grade,subject;
layui.use([ 'form', 'layedit', 'laypage', 'laydate' ],
		function() {
			 form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

			//创建一个编辑器
			var editIndex = layedit.build('LAY_demo_editor');

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

			//监听指定开关
			form.on('switch(switchTest)', function(data) {
				layer.msg('开关checked：'
						+ (this.checked ? 'true' : 'false'), {
					offset : '6px'
				});
				layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF',
						data.othis)
			});

			//监听提交
			form.on('submit(demo1)', function(data) {
				
				return true;
			});	
		});
		
		
    function goPageAction(page){
    	
	  window.location.href="paperInfoAnalysis.do?pageNo="+page+"&paperId=${paperId}&classId=${classId}";
	
	}
	
	function goQuestion(qId,qtype){
		$.getJSON("seeQuestion.do", {
			questionId:qId,
			questionType:qtype
		}, function(res) {
			if (res.code == 0) {
				var html = template('topicanalysis', res);
				$('#tbody234').html(html);
				if (res.count > 2) {
					laypage({
						cont : 'page234',
						pages : res.count,
						skin : 'molv',
						curr : pageno || 1,//当前页
						groups : 5,
						//skip : true,
						jump : function(obj, first) {
							//触发分页后的回调
							if (!first) {
								//点击跳页触发函数自身，并传递当前页：obj.curr
								showStudent(obj.curr,isTrue,classid,paperid,questionId);
							}
						}
					});
				}
				$("#myModal24").modal("show");
			} else{
				layer.msg(res.msg);
				return;
			}
		})
	}
	function showStudent(pageno,isTrue,classid,paperid,questionId,qtype){	
		$.getJSON("studentInformation.do", {
			classId:classid,
			paperId:paperid,
			isTrue:isTrue,
			questionId:questionId,
			type:1,
	        pageNo:pageno,
	        questionType:qtype
		}, function(res) {
// 			alert(JSON.stringify(res));
			if (res.code == 0) {
				var html = template('note_template23', res);
				$('#tbody23').html(html);
				if (res.count > 2) {
					laypage({
						cont : 'page23',
						pages : res.count,
						skin : 'molv',
						curr : pageno || 1,//当前页
						groups : 5,
						//skip : true,
						jump : function(obj, first) {
							//触发分页后的回调
							if (!first) {
								//点击跳页触发函数自身，并传递当前页：obj.curr
								showStudent(obj.curr,isTrue,classid,paperid,questionId);
							}
						}
					});
				}
				 $("#myModal23").modal("show");
			} else{
				layer.msg(res.msg);
				return;
			}
		})
	}
</script>
</html>