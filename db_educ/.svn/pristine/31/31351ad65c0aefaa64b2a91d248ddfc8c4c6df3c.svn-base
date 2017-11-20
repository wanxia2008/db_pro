<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- pageEncoding="UTF-8"%> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>讲义修改</title>
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
	z-index: 10000;
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
</style>
</head>
<body>
	<form method="post" class="layui-form"
		action="${ctx}/lectureNotes/updateNotes.do">
		<input type="number" hidden="" name="handoutId" id="handoutId"
			value="${lNotes.getHandoutId()}">
		<div class="panels_head">
			<span>讲义属性</span>
			<button class="layui-btn layui-btn-normal" lay-submit=""
				lay-filter="demo1"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">确认</button>
			<button type="button" class="layui-btn layui-btn-danger"
				onclick="window.location.href='${ctx}/lectureNotes/lectureNotesList.do'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">返回</button>
		</div>
		<!-- 讲义内容开始 -->
		
		<div class="layui-form-item" style="margin-top: 10px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>讲义科目</label>
				<div class="layui-input-inline" style="width: 150px;">
						<select name="subject" id="subject" lay-verify="subject"
						autocomplete="off" placeholder="请选择科目" disabled="disabled">
						<c:forEach items="${sList}" var="g">
						<c:choose>
						   <c:when test="${lNotes.subject==g.subjectId}">
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
				<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>输入标题</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" lay-verify="handoutTitle" name="handoutTitle"
						id="handoutTitle" lay-verify="handoutTitle" autocomplete="off"
						placeholder="请输入讲义标题" class="layui-input" class="layui-input"
						value="${lNotes.getHandoutTitle()}" maxlength="10">
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>所属教材</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="materialScience" id="materialScience"
						lay-verify="materialScience" autocomplete="off"
						placeholder="请选择所属教材">
						<c:forEach items="${tmList}" var="t">
						<c:choose>
						   <c:when test="${lNotes.materialScience==t.materialId}">
						   <option selected="selected" value="<c:out value="${t.materialId}" />"><c:out
									value="${t.materialName}"></c:out></option>
						   </c:when>
						   <c:otherwise><option value="<c:out value="${t.materialId}" />"><c:out
									value="${t.materialName}"></c:out></option></c:otherwise>
						</c:choose>
						</c:forEach>
						</select>
				</div>
			</div>
				
		</div>
		  
		  <div class="layui-form-item" style="margin-top: 10px;">
		      
		     <%--  <div class="layui-inline">
		      <label class="layui-form-label" style="width: 120px;font-size: 14px;">是否共享</label>
				<div class="layui-input-inline" style="width: 150px;">
				<c:if test="${lNotes.isPublic==1}">
					<input type="radio" name="isPublic" id="isPublic" value="1" title="是"
					checked="checked">
					<input type="radio" name="isPublic" id="isPublic" value="0" title="否"
					>
				</c:if>	
				<c:if test="${lNotes.isPublic==0}">
				<input type="radio" name="isPublic" id="isPublic" value="1" title="是"
					>
				<input type="radio" name="isPublic" id="isPublic" value="0" title="否" checked="checked">
				</c:if>
				</div>

			</div> --%>
			<input type="hidden" value="${lNotes.isPublic}" id="isPublic"/>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:14px;">*&nbsp;</span>课程</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="grade" id="grade" disabled="disabled">
						<c:forEach items="${gList}" var="g">
						<c:choose>
						   <c:when test="${lNotes.grade==g.gradeId}">
						        <option selected="selected" value="${g.gradeId}">${g.gradeName}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.gradeId}">${g.gradeName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="knowledgeBegin" id="knowledgeBegin" disabled="disabled">
						<c:forEach items="${rlList}" var="g">
						<c:choose>
						<c:when test="${lNotes.knowledgeBegin==g.lessonId}">
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
		   	<!-- 上传word文档 开始-->
		   	 <c:if test="${lNotes.wordRoute!=null}">		   
			 <div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size:14px;color:#333">Word文档</label>
				<div class="layui-input-inline" style="width: 200px;">          
              <div class="layui-box layui-upload-button"><input type="file" id="upRouteWord" accept=".doc,.docx" class="layui-upload-file"><span class="layui-upload-icon"><i class="layui-icon" style="color:#008bca"></i>上传讲义</span>
			   <input type="hidden" style="width:65px;" id="insertWord" name="wordRoute" value="${lNotes.wordRoute}" />	
			   <input type="hidden" id="insertWordPdf" name="wordpdfRoute" value="${lNotes.wordpdfRoute}" /> 		   
              </div>
              <c:if test="${lNotes.wordRoute!=null}">
              <button type="button"  class="layui-btn layui-btn-normal" 
              onclick="window.location.href='${ctx}/lectureNotes/wordDownload.do?wordRoute=${lNotes.wordRoute}'">下载</button>
              </c:if>
              </div>
               <c:if test="${lNotes.wordpdfRoute!=null}">
              <button type="button"  class="layui-btn layui-btn-normal" 
              	onclick="openpdf()" style="margin-top:5px">预览</button>
               </c:if>
               <c:if test="${lNotes.wordRoute==null}">
                <span style="color:#ff4c3b;margin-left:-20px;">温馨提示:上传文档控制在10M以内</span>
                </c:if>
                
			</div>
			              <span id="routeWord" style="color:#008bca"></span>		             			          
			</c:if>
		  </div>
		  
		<div class="layui-form-item" style="margin-top: 10px;">
				
	
		<!-- 上传word文档 结束-->
		<c:if test="${lNotes.pptRoute!=null}">	
		 <div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size:14px;color:#333">上传讲义</label>
				<div class="layui-input-inline" style="width: 300px;">
              
              <div class="layui-box layui-upload-button"><input type="file" id="upRoutePpt" accept=".pdf,.ppt,.pptx"  class="layui-upload-file"><span class="layui-upload-icon"><i class="layui-icon" style="color:#008bca"></i>上传讲义</span>
			   <input type="hidden" style="width:65px;" id="insertPpt" name="pptRoute" value="${lNotes.pptRoute}" />		   
              </div>
              
            <c:if test="${lNotes.pptRoute!=null}">
              <button type="button"  class="layui-btn layui-btn-normal" 
              onclick="window.location.href='${ctx}/lectureNotes/wordDownload.do?wordRoute=${lNotes.wordpdfRoute}'">下载</button>
            </c:if>
			<c:if test="${lNotes.pptRoute!=null}">
				<button type="button" class="layui-btn layui-btn-normal"
					onclick="openpdf1()">预览</button>
			</c:if>
				<span id="routePPt" style="color:#008bca"></span>				              			          
              </div>
              
              <!-- 
             <c:if test="${lNotes.pptRoute!=null && lNotes.pptRoute!='' }">
              <input type="text" disabled="disabled" class="layui-input" value="${lNotes.wordpdfRoute}" style="width:150px;">
             </c:if>
              <c:if test="${lNotes.pptRoute!=null && lNotes.pptRoute!='' }">
              <input type="text" disabled="disabled" class="layui-input" value="${lNotes.pptRoute}" style="width:150px;">
             </c:if>
             -->
           
              <c:if test="${lNotes.pptRoute==null}">
               <span style="color:#ff4c3b;margin-left:-20px;">温馨提示:上传文档控制在20M以内</span>
               </c:if>
			</div>
	      </c:if>
		
<!-- 		<div class="layui-form-item" style="margin-top: 10px;"> -->
<!-- 		  <div class="layui-inline"> -->
<!-- 				<label class="layui-form-label" style="width:120px;font-size: 14px;">创建人</label> -->
<!-- 				<div class="layui-input-inline" style="width: 150px;"> -->
<!-- 					<input type="text" lay-verify="handoutTitle" name="handoutTitle" id="handoutTitle" -->
<!-- 						lay-verify="handoutTitle" autocomplete="off"  -->
<%-- 						class="layui-input" class="layui-input" value="${teacher.teacherName}" disabled="disabled"> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="layui-inline"> -->
<!-- 				<label class="layui-form-label" style="width:120px;font-size: 14px;">使用次数</label> -->
<!-- 				<div class="layui-input-inline" style="width: 150px;"> -->
<!-- 					<input type="text"  name="usedCount" disabled="disabled" -->
<!-- 						id="usedCount"  autocomplete="off" -->
<%-- 						 class="layui-input" value="${lNotes.usedCount}"> --%>
<!-- 						</div> -->
				
<!-- 				</div> -->
<!-- 		</div> -->
			</div>
		<!-- 删除版本要求  by zhangzhaojian
		<div class="layui-form-item" style="margin-top: 10px;">
		   		 <div class="layui-inline" style="margin-left:40px;margin-right:20px;color:#ff4c3b !important">
		   		               备注：支持Microsoft Word 2010 以上版本，字体为常用字体：微软雅黑、隶书、宋体、黑体、楷体、新宋体、仿宋字体，上传过程文档转码需要时间，限制文档大小为10M以内。
		         </div>
		</div>
		 -->
        <!-- 讲义内容结束 -->
		<div class="panels_head">
			<span>讲义内容</span>
		</div>
		<textarea id="editor" name="handoutContent"
			style="width: 96%; height: 300px; margin:0 auto; margin-top: 20px;">${lNotes.getHandoutContent()}</textarea>

	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/js/fileUpload.js?v=2017"></script>
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
	layui.use([ 'form', 'layedit', 'laydate' ],function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');

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
							materialScience : function(value) {
								if (value.length == "") {
									return '请选择所属教材';
								}
							},
						});
						//监听提交
						form.on('submit(demo1)',function(data) {
											var hand = $("textarea[name='handoutContent']").val();
											var pdfroute = $('#insertWordPdf').val();
											if(!pdfroute){
												pdfroute=$('#insertPpt').val();
											}
											$.ajax({
														url : '${ctx}/lectureNotes/updateNotes.do',
														type : 'post',
														dataType : 'json',
														data : {
															handoutId : $('#handoutId').val(),
															handoutTitle : $('#handoutTitle').val(),
															grade : $('#grade').val(),
															subject : $('#subject').val(),
															handoutType : $('#handoutType').val(),
															materialScience : $('#materialScience').val(),
															classNo : $('#classNo').val(),
															handoutContent : hand,
															wordRoute:$('#insertWord').val(),
															wordpdfRoute:pdfroute,
															pptRoute:$('#insertPpt').val(),
															isPublic:$("#isPublic").val()
														},
														success : function(data) {
															if (data.code == 0) {
																layer.msg("修改成功!");
																setTimeout(function(){
																	window.location.href = "lectureNotesList.do?pageNo="+${page};
																},500);
																
															} else {
																layer.msg(data.msg,{time:1000});
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
               window.open(url);  
		   }
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