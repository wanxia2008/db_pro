<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<!-- pageEncoding="UTF-8"%> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>讲义管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././ueditor/ueditor.config.js"></script>
<script type="text/javascript" src=".././ueditor/ueditor.all.js"></script>
<script type="text/javascript" src=".././ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
<script type="text/javascript" src=".././ueditor/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript" src=".././ueditor/kityformula-plugin/defaultFilterFix.js"></script>
<style>
.layui-form-checkbox {
	height: 26px;
	line-height: 24px;
}

.layui-form-checkbox span {
	font-size: 7px;
}

.layui-form-checked span, .layui-form-checked:hover span {
	background-color: #35CE8D;
}

ul li {
	margin-top: 5px;
	padding: 5px;
}
.layui-form-selected dl {
	z-index:10000;
}
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;top: 20%;font-size: 10px;border-radius: 5px;background:#e33}
</style>
</head>
<body>
	<form method="post" class="layui-form" action="" >
		<div class="panels_head">
			<span>讲义属性</span>
			<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" style="margin-right: 80px;height:30px;line-height:30px;padding:0 18px;top:20%;margin-top:0;font-size:14px">确认</button>		
		<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/lectureNotes/lectureNotesList.do'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
		</div>
		<div class="layui-form-item" style="margin-top: 10px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>讲义科目</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="subject" id="subject" lay-verify="subject"
						autocomplete="off" placeholder="请选择科目">
						<c:forEach items="${sList}" var="s">
							<option value="<c:out value="${s.subjectId}"></c:out>"><c:out
									value="${s.subjectName}"></c:out></option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>输入标题</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" lay-verify="handoutTitle" name="handoutTitle" id="handoutTitle"
						lay-verify="handoutTitle" autocomplete="off" placeholder="请输入讲义标题"
						class="layui-input" class="layui-input" maxlength="100">
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">所属教材</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="materialScience" lay-verify="materialScience"
					 id="materialScience" autocomplete="off" placeholder="请选择所属教材">
						<c:forEach items="${tmList}" var="tm">
							<option value="<c:out value="${tm.materialId}"></c:out>"><c:out
									value="${tm.materialName}"></c:out></option>
						</c:forEach>
					</select>
				</div>
			</div>
				
		</div>
		  
		  <div class="layui-form-item" style="margin-top: 10px;">
		       <div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">是否共享</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="radio" name="isPublic" id="isPublic" value="1" title="是"
					checked="">
				<input type="radio" name="isPublic" id="isPublic" value="0" title="否">
				</div>
		</div>
<!-- 		      <div class="layui-inline"> -->
<!-- 				<label class="layui-form-label" style="width: 120px;font-size: 14px;">讲义类型</label> -->
<!-- 				<div class="layui-input-inline" style="width: 150px;"> -->
<!-- 					<select name="handoutType" id="handoutType" lay-verify="handoutType" -->
<!-- 						autocomplete="off" placeholder="请选择讲义类型"> -->
<%-- 						<c:forEach items="${tyList}" var="ty"> --%>
<%-- 							<option value="<c:out value="${ty.seasonId}"></c:out>"><c:out --%>
<%-- 									value="${ty.seasonName}"></c:out></option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 			</div> -->
			  
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:14px;">*&nbsp;</span>课程</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="grade" id="grade" lay-verify="grade" autocomplete="off"
						placeholder="请选择年级" >
						<option value="">请选择</option>
						<c:forEach items="${gList}" var="g">
							<option value="<c:out value="${g.gradeId}"></c:out>"><c:out
									value="${g.gradeName}"></c:out></option>
						</c:forEach>
					</select>
				</div>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="knowledgeBegin" id="knowledgeBegin"
					lay-verify="knowledgeBegin" lay-filter="select_knowstart">
						<option value="">请选择</option>
						<c:forEach items="${rlList}" var="s">
						<option value="<c:out value="${s.lessonId}"></c:out>"><c:out value="${s.lessonName}"></c:out></option>
						</c:forEach>
					</select>
				</div>
			</div>
<!-- 		   	<div class="layui-inline"> -->
<!-- 				<div class="layui-form-mid">-</div> -->
<!-- 				<div class="layui-input-inline" style="width: 140px;"> -->
<!-- 					<select name="knowledgeEnd" id="knowledgeEnd"  -->
<!-- 					lay-verify="knowledgeEnd" lay-filter="select_knowend"> -->
<!-- 						<option value="">请选择</option> -->
<%-- 						<c:forEach items="${rlList}" var="s"> --%>
<%-- 						<option value="<c:out value="${s.lessonId}"></c:out>"><c:out value="${s.lessonName}"></c:out></option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
			</div>
		  
		<div class="layui-form-item" style="margin-top: 10px;">
		 
		<!-- 上传word文档 开始-->
			 <div class="layui-inline" id="wordpdf">
				<label class="layui-form-label" style="width:120px;font-size:14px;color:#333">Word文档</label>
				<div class="layui-input-inline" style="width: 120px;">            
              <div class="layui-box layui-upload-button"><input type="file" id="upRouteWord" class="layui-upload-file" accept=".doc,.docx," data-options="required:true" />
              <span class="layui-upload-icon"><i class="layui-icon" style="color:#008bca"></i>上传讲义</span>
			   <input type="hidden" style="width:65px;" id="insertWord" name="wordRoute" value="" />
               <input type="hidden" id="insertWordPdf" name="wordpdfRoute" value="" /> 
             </div>
             <span class=""  id="routeWord" style="color:#008bca"></span>		          			             				      
              </div>
              <span id="openpdf"></span>
              <span style="color:#ff4c3b;">温馨提示:上传文档控制在10M以内</span>
			</div>		
		<!-- 上传word文档 结束-->
		
		<!-- 上传pdf开始 -->
		 <div class="layui-inline" id="pptpdf">
				<label class="layui-form-label" style="width:120px;font-size:14px;color:#333">pdf或ppt文档</label>
				<div class="layui-input-inline" style="width: 120px;">            
              <div class="layui-box layui-upload-button"><input type="file" id="upRoutePpt"  class="layui-upload-file" accept=".pdf,.ppt,.pptx" data-options="required:true" />
              <span class="layui-upload-icon"><i class="layui-icon" style="color:#008bca"></i>上传讲义</span>
			   <input type="hidden" style="width:65px;" id="insertPpt" name="pptRoute" value="" />
             </div>
             <span class=""  id="routePPt"  style="color:#008bca"></span>		          			             
              </div>
              <span id="openpdf1"></span>
              <span style="color:#ff4c3b;">温馨提示:上传文档控制在20M以内</span>
			</div>
		<!-- 上传pdf结束 -->
		
		
		<!-- 
		<div class="layui-form-item" style="margin-top: 10px;">
		   		 <div class="layui-inline" style="margin-left:40px;margin-right:20px;color:#ff4c3b !important">
		   		               备注：支持Microsoft Word 2010 以上版本，字体为常用字体：微软雅黑、隶书、宋体、黑体、楷体、新宋体、仿宋字体，上传过程文档转码需要时间，限制文档大小为10M以内。
		         </div>
		</div>
 -->
		
		<div class="panels_head">
			<span>讲义内容</span>
		</div>
		<textarea id="editor" name="handoutContent"  
			style="width: 96%; height: 300px; margin:0 auto; margin-top: 20px;">
    	</textarea>

	</form>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/js/fileUpload.js?v=78798"></script>
<script src="${ctx}/js/fileUploadPPt.js"></script>
<script type="text/javascript">
	var ue = UE.getEditor('editor',{
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
    autoHeightEnabled:false
	});
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;
						
						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');
						form.on('select(select_knowend)', function(data) {
							var value = data.value;
							var start = $('#knowledgeBegin').val();
							if (parseInt(value) <= parseInt(start)) {
								layer.msg("请正确选择知识范围");
								return;
							}
						})

						form.on('select(select_knowstart)', function(data) {
							var value = data.value;
							var end = $('#knowledgeEnd').val();
							if (parseInt(value) >= parseInt(end)) {
								layer.msg("请正确选择知识范围");
								return;
							}
						})

						//自定义验证规则
						form.verify({
							handoutTitle : function(value) {
								if (value.length == "") {
									return '请输入标题';
								}
							},
							grade : function(value) {
								if (value.length == "") {
									return '请选择年级';
								}
							},
							subject : function(value) {
								if (value.length == "") {
									return '请选择科目';
								}
							},
							handoutType : function(value) {
								if (value.length == "") {
									return '请选择讲义类型';
								}
							},
							handoutContent : function(value) {
								if (value.length == "") {
									return '请输入讲义内容';
								}
							},
							
							materialScience : function(value) {
								if (value.length == "") {
									return '请选择所属教材';
								}
							},
							
						});
						//监听提交
						form.on('submit(demo1)', function(data) {
							var hand = $("textarea[name='handoutContent']").val();
							var isPublic = $("input[name='isPublic']:checked").val();
							var pdfroute = $('#insertWordPdf').val();
							if(!pdfroute){
								pdfroute=$('#insertPpt').val();
							}
						
							$.ajax({
								url:'${ctx}/lectureNotes/save.do',
								type:'post',
								dataType:'json',
								data:{
									handoutId:$('#handoutId').val(),
									handoutTitle:$('#handoutTitle').val(),
									grade:$('#grade').val(),
									subject:$('#subject').val(),
									handoutType:$('#handoutType').val(),
									materialScience:$('#materialScience').val(),
// 									classNo:$('#classNo').val(),
									handoutContent:hand,
									knowledgeBegin :$('#knowledgeBegin').val(),
							        knowledgeEnd : $('#knowledgeEnd').val(),
									wordRoute:$('#insertWord').val(),
									wordpdfRoute:pdfroute,
									pptRoute:$('#insertPpt').val(),
									isPublic:isPublic,
								},
								success:function(data){
									if(data.code==0){
										layer.msg("添加讲义成功!");
										setTimeout(function(){
											window.location.href="lectureNotesList.do";
										},1000);
									}else{
										layer.msg(data.msg,{time:3000});
									}
								}
							});
							return false;
						});
					});
	
	   function openpdf(){
		   var pdfroute = $("#insertWordPdf").val();
		   if(!pdfroute){
			   layer.msg('暂无可预览讲义');
			   return;
		   }else{
			   var encodeData = window.btoa(window.encodeURIComponent(pdfroute))//编码
			   var url="${ctx}/pdftest/web/viewer2.html?file="+encodeData;	
               window.open(url); 		   }
	   }
	   
	   function openpdf1(){
		   var pdfroute = $("#insertPpt").val();
		   if(!pdfroute){
			   layer.msg('暂无可预览讲义');
			   return;
		   }else{
			   var encodeData = window.btoa(window.encodeURIComponent(pdfroute))//编码
			   var url="${ctx}/pdftest/web/viewer2.html?file="+encodeData;	
               window.open(url); 		   }
	   }
</script>
</html>