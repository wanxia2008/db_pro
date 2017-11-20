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
<title>标签管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">


        /*.safa-tree i{font-style:normal;  color:#fff;  background:#666;  width:1em;  display:inline-block;  text-align:center;  position:relative;  }
        .safa-tree i:before{content:'+';  }*/

        *{box-sizing:border-box;  margin:0;  padding:0;  }

        .safa-tree{line-height:1em;  font-size:16px;  }
        .safa-tree a{display:block;  padding:5px;  }
        .safa-tree a:hover{color:#666;  }
        .safa-tree ul{margin:1em 0.5em;  }
        .safa-tree i.fa{position:relative;  margin-right:0.25em;  }
        .safa-tree i.fa-square-o{display:none;  }

        .safa-tree-node{display:inline-block;  vertical-align:top;  position:relative;  cursor:pointer;  box-sizing:border-box;  }
        .safa-tree-node>ul{display:none;  position:absolute;  left:0;  right:0;  background-color:rgba(0,0,0,0.1);  }

        .safa-tree-node.on{position:static;  cursor:pointer;  }
        .safa-tree-node.on>ul{display:block;  }
        .safa-tree-node.on>a>i.fa-plus-square::before{content:"\F146";  }
        .safa-tree-node.on>a>i.fa-plus-square::after{content:"\F063";  position:absolute;  top:100%;  left:0;  right:0;  }

        .safa-tree-node.on>a:empty>i.fa-plus-square{display:none;  }
        .safa-tree-node>a:empty i.fa-square-o{display:inline-block;  }

        .safa-tree-node.on.line>ul{display:inline-block;  margin:0;  position:static;  vertical-align:top;  background:none;  }
        .safa-tree-node.on.line>ul>.safa-tree-node:not(.on){display:none;  }
        .safa-tree-node.on.line>ul>.safa-tree-node>a{color:#f00;  }
        .safa-tree-node.on.line>a{display:inline-block;  }
        .safa-tree-node.on.line>a>i.fa-plus-square::after{display:none;  }

        .safa-tree-end>a>i::after{display:none;  }
        .safa-tree-end>a>i::before{content:"\F111" !important;  }


.tcenter {
	text-align: center !important
}

.pagelist a {
	padding: 6px 18px;
	border: 1px solid #e1e2e3;
	border-radius: 4px;
}

.pagelist .current {
	background: #008bca !important;
	color: #fff
}

.pagelist a:hover {
	background: #008bca !important;
	color: #fff
}

#pages {
	height: 50px;
	line-height: 50px;
}
</style>

</head>
<body>

	<form class="layui-form" action="">
		<div class="panels_head">
			<span>标签列表</span>
		</div>
		<div class="layui-form-item" style="margin-top: 20px; margin-left: 1%">
			<div>
			<button type="button" class="layui-btn layui-btn-normal searchbtn"
					onclick="saveTag()">新增</button>
			</div>
			
			
			<div class="layui-inline">
			<%-- <div class="layui-input-inline" style="width: 120px;">
					<select id="grade" lay-verify="subject">
						<option value="">请选择年级</option>
						<c:forEach items="${grades}" var="grade">
							<option value="${grade.gradeId}">${grade.gradeName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="subject" id="subject" lay-verify="subject">
						<option value="">请选择科目</option>
						<c:forEach items="${subjects}" var="lesson">
							<option value="${lesson.subjectId}">${lesson.subjectName}</option>
						</c:forEach>
					</select>
				</div> --%>	
							
				
				
				<div class="layui-inline" style="float: right">
				<div class="layui-input-inline" style="width: 180px;">
					<input type="text" id="tagNamel" name="tagNamel"
						value="${tagNamel}" autocomplete="off" placeholder="请输入标签名称"
						class="layui-input" maxlength="32">
				</div>
				<button class="layui-btn layui-btn-normal searchbtn"
					style="margin-right: 20px;" >搜索</button>
			</div>

				
			</div>
			
                
          
		</div>


		<c:choose>
			<c:when test="${qtList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size: 14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				     <table class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>年级</th>
                    <th>科目</th>
                    <th>标签名称</th>
                    <th>添加时间</th>
                    <th>操作</th>
                  
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${qtList}" var="s">
                <tr>
                   <td><c:out value="${s.tagId}"></c:out></td>
							<td>${s.gradeName}</td>
							<td>${s.subjectName}</td>
                    <td>
                        <div class='safa-tree'>
                            <ul>
                                <!--一级标签-->
                                <li class='safa-tree-node'>
                                    <a><i class="layui-icon" style="font-size: 16px; color: #00aaee;">&#xe61f;</i>${s.tagName}</a>
                                    <ul>
                                    <c:forEach var="g" items="${s.children}">
                                        <!--二级标签 -->
                                        <li class='safa-tree-node'>
                                            <a><i class='fa fa-plus-square'></i><i class='fa fa-square-o'></i>${g.tagName}</a>
                                            <ul>
                                                <!--三级标签 -->
                                                <c:forEach var="o" items="${g.children}">
                                                <li class='safa-tree-node'>
                                                    <a><i class='fa fa-plus-square'></i><i class='fa fa-square-o'></i>${o.tagName}</a>
                                                    <ul>
                                                        <!--四级标签 -->
                                                         <c:forEach var="p" items="${o.children}">                                                       
                                                        <li class='safa-tree-node'>
                                                            <a><i class='fa fa-plus-square'></i><i class='fa fa-square-o'></i>${p.tagName}</a>
                                                            <ul>
                                                                <!--五级标签-->
                                                                <c:forEach var="q" items="${p.children}">
                                                                <li class='safa-tree-node safa-tree-end'><a><i class='fa fa-plus-square'></i><i class='fa fa-square-o'></i>${q.tagName}</a></li>
                                                               </c:forEach>
                                                            </ul>
                                                        </li>
                                                       </c:forEach>
                                                    </ul>
                                                </li>
                                                </c:forEach>
                                            </ul>
                                        </li>
                                        </c:forEach>
                                      </ul> 
                                         
                                </li>
                            </ul>
                        </div>
                    </td>
                    <td><fmt:formatDate value="${s.createTime}"
									pattern="yyyy-MM-dd" /></td>
                   	<td><span class="icon-edit" data-toggle="modal"
								data-target="#myModal" id="${s.tagId}" data-grade="${s.grade}" data-name="${s.tagName}"
								data-subject="${s.subject}" data-no="${page}"
								style="font-size: 14px; color: #008bca; cursor: pointer;">修改</span>
								&nbsp; <a class="button border-red" href="javascript:void(0)"
								onclick="deleteone(<c:out value="${s.tagId}"></c:out>)"> <span
									class="icon-remove-sign"
									style="font-size: 14px; color: #ff5722;"> 删除</span>
							</a></td>
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
								<%-- 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp; <input
								type="text" id="goPageId"
								style="width: 50px; height: 30px; line-height: 30px; text-align: center"
								value="${page}" name="goPage" />&nbsp;&nbsp;页 <a
								href="javascript:;"
								onclick="goMyPage($('#goPageId').val(),${totalPages});">&nbsp;&nbsp;Go</a> --%>
							</div>
						</td>
					</tr>
           
                </tbody>
            </table>
			</c:otherwise>
		</c:choose>
		
		
		<!--模态框-->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增标签</h4>
				</div>
				<div class="modal-body">
					
						<div class="layui-inline">
			<div class="layui-input-inline" style="width: 120px;">
					<select id="grade" lay-verify="subject">
						<option value="">请选择年级</option>
						<c:forEach items="${grades}" var="grade">
							<option value="${grade.gradeId}">${grade.gradeName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="subject" id="subject" lay-verify="subject">
						<option value="">请选择科目</option>
						<c:forEach items="${subjects}" var="lesson">
							<option value="${lesson.subjectId}">${lesson.subjectName}</option>
						</c:forEach>
					</select>
				</div>	
				<div class="layui-input-inline" style="width: 120px;" id="tagparentId">
					
				</div>			
				
					<div class="layui-input-inline" style="width: 200px;">
					<input type="text" id="tagName1" name="tagName" autocomplete="off"
						placeholder="请输入标签名称" class="layui-input" maxlength="32">
				</div>
				</div>
				<div class="modal-footer">
					<!-- <button type="button" class="btn btn-primary" id="add" onclick="add()"
						style="height: 30px; line-height: 30px; padding: 0 18px;">确定</button> -->
					<button type="button" class="btn btn-default" data-dismiss="modal"
						style="height: 30px; line-height: 30px; padding: 0 18px;">新增</button>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
	</form>
	<!--模态框-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 40%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">编辑标签</h4>
				</div>
				<div class="modal-body">
					<form class="layui-form" method="post">
						<div class="layui-form-item" style="margin-top: 20px;">
<!-- 						<div class="layui-inline" id="subjectId"> -->
<!-- 								<label class="layui-form-label" style="width: 120px;">年级</label> -->
<!-- 								<div class="layui-input-inline" style="width: 200px;"> -->
<!-- 									<select name="grade" id="grade-type" lay-verify="grade" -->
<!-- 										style="display: block !important; border: 1px solid #ccc; width: 200px; height: 34px; line-height: 1.42857143; border-radius: 4px; padding: 6px 12px;"> -->
									
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						<div class="layui-inline" id="subjectId"> -->
<!-- 								<label class="layui-form-label" style="width: 120px;">科目</label> -->
<!-- 								<div class="layui-input-inline" style="width: 200px;"> -->
<!-- 									<select name="subject" id="subject-type" lay-verify="subject" -->
<!-- 										style="display: block !important; border: 1px solid #ccc; width: 200px; height: 34px; line-height: 1.42857143; border-radius: 4px; padding: 6px 12px;"> -->
									
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 120px;">标签名称</label>
								<div class="input-group" style="float: left;">
									<input type="text" name="tagName" id="tagName"
										class="form-control" style="width: 200px;"> <input
										type="number" hidden="" name="tagId" id="tagId"> <input
										type="number" hidden="" name="pageNo" id="pageNo">
								</div>
							</div>
							
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="confirm_update"
						style="height: 30px; line-height: 30px; padding: 0 18px;">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal"
						style="height: 30px; line-height: 30px; padding: 0 18px;">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
	
	<script type="text/html" id="question_template1">
 		{{each objects as value i}}
			<tr>
				<td>{{=value.choiceId}}</td>
				<td>
					<div class="answer">{{=value.choiceDesc}}</div>
					<div class="answer">A、{{=value.optionA}}</div> 
					<div class="answer">B、{{=value.optionB}}</div>
					<div class="answer">C、{{=value.optionC}}</div>
					<div class="answer">D、{{=value.optionD}}</div>
				</td>
				<td>单选</td>
				<td>{{=value.grade.gradeName}}</td>
				<td>{{=value.subject2.subjectName}}</td>
				<td>{{=value.teacher.teacherName}}</td>
				<td>{{=value.useCount}}</td>
				<td>{{=value.degree}}</td>
				<td>{{=value.createTime}}</td>
				<td>
					<button class="layui-btn layui-btn-mini layui-btn-normal">解绑</button>
				</td>
			</tr>
 		{{/each}}
 	</script>
 	<script type="text/html" id="question_template2">
 		{{each objects as value i}}
			<tr>
				<td>{{value.choiceId}}</td>
				<td>
					<div class="answer">{{=value.choiceDesc1}}</div>
					<div class="answer">A、{{=value.optionA1}}</div> 
					<div class="answer">B、{{=value.optionB1}}</div>
					<div class="answer">C、{{=value.optionC1}}</div>
					<div class="answer">D、{{=value.optionD1}}</div>
				</td>
				<td>多选</td>
				<td>{{=value.grade.gradeName}}</td>
				<td>{{=value.subject2.subjectName}}</td>
				<td>{{=value.teacher.teacherName}}</td>
				<td>{{=value.useCount}}</td>
				<td>{{=value.degree1}}</td>
				<td>{{=value.createTime}}</td>
				<td>
					<button class="layui-btn layui-btn-mini layui-btn-normal">解绑</button>
				</td>
			</tr>
 		{{/each}}
 	</script>
 	<script type="text/html" id="question_template3">
 		{{each objects as value i}}
			<tr>
				<td>{{=value.judgeId}}</td>
				<td>
					<div class="answer">{{=value.judgeDesc}}</div>
					A、正确  &nbsp;&nbsp;B、错误
				</td>
				<td>判断</td>
				<td>{{=value.grade.gradeName}}</td>
				<td>{{=value.subject2.subjectName}}</td>
				<td>{{=value.teacher.teacherName}}</td>
				<td>{{=value.useCount}}</td>
				<td>{{=value.degree2}}</td>
				<td>{{=value.createTime}}</td>
				<td>
					<button class="layui-btn layui-btn-mini layui-btn-normal">解绑</button>
				</td>
			</tr>
 		{{/each}}
 	</script>
 	<script type="text/html" id="question_template4">
 		{{each objects as value i}}
			<tr>
				<td>{{=value.readId}}</td>
				<td style="text-align:left">
					<div class="answer">{{=value.readDesc}}</div>
				</td>
				<td>阅读理解</td>
				<td>{{=value.grade.gradeName}}</td>
				<td>{{=value.subject2.subjectName}}</td>
				<td>{{=value.teacher.teacherName}}</td>
				<td>{{=value.useCount}}</td>
				<td>{{=value.degree_read}}</td>
				<td>{{=value.createTime}}</td>
				<td>
					<button class="layui-btn layui-btn-mini layui-btn-normal">解绑</button>
				</td>
			</tr>
 		{{/each}}
 	</script>
 	<script type="text/html" id="question_template5">
 		{{each objects as value i}}
			<tr>
				<td>{{=value.subjectiveId}}</td>
				<td>
					<div class="answer">{{=value.subjectiveTitle}}</div>
					<div class="answer">{=value.fillAnswer}}</div
				</td>
				<td>填空题</td>
				<td>{{=value.grade.gradeName}}</td>
				<td>{{=value.subject2.subjectName}}</td>
				<td>{{=value.teacher.teacherName}}</td>
				<td>{{=value.useCount}}</td>
				<td>{{=value.questionDegree}}</td>
				<td>{{=value.createTime}}</td>
				<td>
					<button class="layui-btn layui-btn-mini layui-btn-normal">解绑</button>
				</td>
			</tr>
 		{{/each}}
 	</script>
 	<script type="text/html" id="question_template6">
 		{{each objects as value i}}
			<tr>
				<td>{{=value.fillId}}</td>
				<td style="text-align:left">
					<div class="answer">{{=value.subjectiveTitle}}</div>
				</td>
				<td>主观题</td>
				<td>{{=value.grade.gradeName}}</td>
				<td>{{=value.subject2.subjectName}}</td>
				<td>{{=value.teacher.teacherName}}</td>
				<td>{{=value.useCount}}</td>
				<td>{{=value.questionDegree}}</td>
				<td>{{=value.createTime}}</td>
				<td>
					<button class="layui-btn layui-btn-mini layui-btn-normal">解绑</button>
				</td>
			</tr>
 		{{/each}}
 	</script>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<script type="text/javascript" src=".././layui/layui.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript">
layui.use([ 'form', 'layedit', 'laypage', 'laydate' ],
		function() {
			form = layui.form(), layer = layui.layer, laypage = layui.laypage,laydate = layui.laydate;
			form.on();
});
getabc();	
		$('.icon-edit').on('click',function(){
			var tagId=$(this).attr("id");
			var tagName=$(this).attr("data-name");
			var subjectId = $(this).attr("data-subject");
			var gradeId = $(this).attr("data-grade");
			var pageNo = $(this).attr("data-no");
			$('#tagId').val(tagId);
			$('#tagName').val(tagName);
			$('#pageNo').val(pageNo);
			
			$.ajax({
				url:"getgradeList.do",
				method:"post",
				dataType:"json",
				data:{},
				success:function(data){
					 if(data.code == 0){
						 var grade = data.objects;
						 var html ='';
						 for(var i=0;i<grade.length;i++){
							 
						 }
					 }
				}
			})
			
			$.ajax({
				url:"getgradeList.do",
				method:"post",
				dataType:"json",
				data:{},
				success:function(data){
					 if(data.code == 0){
						 var grade = data.objects;
						 var html ='';
						 for(var i=0;i<grade.length;i++){
							 if(gradeId == grade[i].gradeId){
            					 html+='<option selected="selected" value="'+grade[i].gradeId+'">'+grade[i].gradeName+'</option>';
            				 }else{
            					 html += '<option value="'+grade[i].gradeId+'">'+grade[i].gradeName+'</option>'
            				 }

						 }
						 $("#grade-type").html(html);
					 }
				}
			})
			
            $.ajax({
            	 url:'subjectList.do',
            	 type:'post',
            	 dataType:'json',
            	 data:{},
            	 success:function(data){
            		 if(data.code == 0){
            			 var result = data.objects;
            			 var html='';
            			 for(var i=0;i<result.length;i++){
            				 if(subjectId == result[i].subjectId){
            					 html+='<option selected="selected" value="'+result[i].subjectId+'">'+result[i].subjectName+'</option>';
            				 }else{
            					 html += '<option value="'+result[i].subjectId+'">'+result[i].subjectName+'</option>'
            				 }
            			 }
            			 $("#subject-type").html(html);
            		 }
            	 }
            	 
            	
            })	 		 
		});
		
		
		$('#confirm_update').on('click',function(){
			var tagId=$('#tagId').val();
			var tagName=$('#tagName').val();
			var subjectId=$('#subject-type').val();
			var gradeId = $("#grade-type").val();
			var pageNo=$('#pageNo').val();
			
// 			if(!gradeId){
// 				layer.msg('年级不能为空');
// 				return;
// 			}
// 			if(!subjectId){
// 				layer.msg('科目不能为空');
// 				return;
// 			}
			if(!tagName){
				layer.msg('标签不能为空');
				return;
			}
			$.ajax({
				url:'deletetag.do',
				type:'post',
				dateType:'json',
				data:{
					tagName:tagName,
					tagId:tagId
				},
				success:function(data){
					if(data.code==0){
						$("#myModal").modal('hide');
						layer.msg('编辑成功');
						setTimeout(function(){						 
							window.location.href = "tagList.do?pageNo="+pageNo;
							},1000);
					}else{
						layer.msg(data.msg);
						return;
					}
				},
			})
		});
function deleteone(uid){
	layer.confirm('您确认删除该标签么?',{btn:['确认','取消']},function(){
		 $.post('${ctx}/questionTag/tagdelete.do?',{'tagId':uid},function(data){
				if(data.code==0){
					layer.msg('删除标签成功');
					setTimeout(function(){
						window.location.href="${ctx}/questionTag/tagList.do";	
					},2000);
				}else{
					layer.msg(data.msg,{time:3000});
					return;
				}
		 });
	    },function(){	
	    	 layer.closeAll();  
	});
	
}
$('#search_content').click(function(){
	var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i");   
	var sourceNamel=$('#tagNamel').val(); 
    if(pat.test(sourceNamel)==true)   
    {   
    	layer.msg("输入名称中含有非法字符");   
        return false;   
    } else{
    	$('#myform').submit();
    }
});

function saveTag(){
	/* var tagName=$("#tagName1").val();
	var subject=$("#subject").val();
	var grade = $('#grade').val();
	var parentId = $("#parenttype").val();//父id
	var tagLevel = $("#parenttype option:selected").attr("data-index");
	if(subject=="") {
		layer.msg("请选择科目");
		return;
	}
	if(grade=="") {
		layer.msg("请选择年级");
		return;
	}
	if(tagName=="") {
		layer.msg("请输入标签");
		return;
	} */
	 $('#myModal1').modal('show');
	getabc();
	
}

$(function(){$('#myModal1').on('hide.bs.modal',function(){
	var tagName=$("#tagName1").val();
	var subject=$("#subject").val();
	var grade = $('#grade').val();
	var parentId = $("#parenttype").val();//父id
	var tagLevel = $("#parenttype option:selected").attr("data-index");
	if(subject=="") {
		layer.msg("请选择科目");
		return;
	}
	if(grade=="") {
		layer.msg("请选择年级");
		return;
	}
	if(tagName=="") {
		layer.msg("请输入标签");
		return;
	} 
	$.ajax({
		url : 'saveTag.do',
		type : 'post',
		dataType : 'json',
		data : {
			tagName : tagName,
			subject : subject,
			grade : grade,
			parentId:parentId,
			tagLevel:tagLevel
		},
		success : function(res) {
			 if (res.code == 0) {
				layer.msg("添加成功!");
				setTimeout(function(){
					window.location.reload();
				},1000);
			}else {
				layer.msg(res.msg);
			}
		},
		error:function(data){
			alert(JSON.stringify(data));
		}
	});
})})

function add(){
	alert("aaa");
	var tagName=$("#tagName1").val();
	var subject=$("#subject").val();
	var grade = $('#grade').val();
	var parentId = $("#parenttype").val();//父id
	var tagLevel = $("#parenttype option:selected").attr("data-index");
	if(subject=="") {
		layer.msg("请选择科目");
		return;
	}
	if(grade=="") {
		layer.msg("请选择年级");
		return;
	}
	if(tagName=="") {
		layer.msg("请输入标签");
		return;
	} 
	$.ajax({
		url : 'saveTag.do',
		type : 'post',
		dataType : 'json',
		data : {
			tagName : tagName,
			subject : subject,
			grade : grade,
			parentId:parentId,
			tagLevel:tagLevel
		},
		success : function(res) {
			 if (res.code == 0) {
				layer.msg("添加成功!");
				setTimeout(function(){
					window.location.reload();
				},1000);
			}else {
				layer.msg(res.msg);
			}
		},
		error:function(data){
			alert(JSON.stringify(data));
		}
	});
	
}
function searchTag(){
	var tagName = $("#tagNamel").val();
	$.ajax({
		url : 'tagList.do',
		type : 'post',
		dataType : 'json',
		data : {tagName : tagName},
		success : function(res){
			alert('a');
		}
	})
}

function goPageAction(page){
	window.location.href="${ctx}/questionTag/tagList.do?pageNo="+page+"&tagNamel=${tagNamel}";
	//+"&goodsName=${goodsName}&searchBeginDate=${searchBeginDate}&searchEndDate=${searchBeginDate}"
}
function goMyPage(page,totalPage){
	if(page>0 && page<=totalPage){
		window.location.href="${ctx}/questionTag/tagList.do?pageNo="+page+"&tagNamel=${tagNamel}";
	}else{
		alert("请输入有效的整数");
	}
}
var pageNo = 1,tagId;
$('.tagNameToQuestion').click(function(){
	var id = $(this).attr("data-id");
	tagId = id;
	showquestion(tagId,pageNo,1);
});
$('#search').click(function(){
	var type = $('#type').val();
	showquestion(tagId,pageNo,type);
});

function showquestion(id,pageNo,type) {
	$.ajax({
		url : 'getQuestionByTag.do',
		type : 'post',
		dataType : 'json',
		data : {
			tagId : id,
			pageNo : pageNo,
			pageSize : 5,
			type:type
		},
		success : function(res) {
			 if (res.code == 0) {
				 console.log(JSON.stringify(res));
				 if(type==1) {
					var html = template('question_template1', res);
				 } else if(type==2) {
					var html = template('question_template2', res);
				 } else if(type==3) {
					 var html = template('question_template3', res);
				 } else if(type==4) {
					 var html = template('question_template4', res);
				 } else if(type==5) {
					 var html = template('question_template5', res);
				 }
				 if(res.count>1) {
					 laypage({
							cont : 'page',
							pages : res.count,
							skin : 'molv',
							curr : pageno || 1,//当前页
							groups : 5,
							skip : true,
							jump : function(obj, first) {
								//触发分页后的回调
								if (!first) {
									//点击跳页触发函数自身，并传递当前页：obj.curr
									showquestion(id,obj.curr,type);
								}
							}
					});
				 } else {
					 $('#page').css('display','none');
				 }
				 $('#tbody1').html(html);
				 $('#myModal1').modal('show');
			}else {
				$('#myModal1').modal('hide');
			//	$('#tbody1').empty();
			//	layer.msg(res.msg);
			}
		}
	});
}

function getabc(){
	$.ajax({
		url:'gettagbysubandgra.do',
		type:'post',
		dataType:'json',
		data:{'subjectId':null,'gradeId':null},
		success:function(data){
			console.log(data);
			if(data.code == 0){
				 var html = "";
				 var taglist = data.objects;
				 html += "<select name='parentId' id='parenttype' style='display:block;height:30px;line-height:30px;border: 1px solid #e6e6e6;background-color: #fff;border-radius: 4px;'><option value=''>请选择父ID</option>";
				 for(i in taglist){
					 html+="<option value='"+taglist[i].tagId+"' data-index='"+taglist[i].tagLevel+"'>"+taglist[i].tagName+"</option>";
					 for(j in taglist[i].children){
						 html+="<option value='"+taglist[i].children[j].tagId+"' data-index='"+taglist[i].children[j].tagLevel+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+taglist[i].children[j].tagName+"</option>";
				        for(x in taglist[i].children[j].children){
							 html+="<option value='"+taglist[i].children[j].children[x].tagId+"' data-index='"+taglist[i].children[j].children[x].tagLevel+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+taglist[i].children[j].children[x].tagName+"</option>";
				            if(taglist[i].children[j].children[x] != null){
				            	for(y in taglist[i].children[j].children[x].children){
								 		html+="<option value='"+taglist[i].children[j].children[x].children[y].tagId+"' data-index='"+taglist[i].children[j].children[x].children[y].tagLevel+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+taglist[i].children[j].children[x].children[y].tagName+"</option>";
				            	   }
				            	}
				        }
					 }
				 }
				 html+='</select>';
				 $("#tagparentId").html(html);
			}else{
				
			}
		}
	})
}

$(function (){
    var fontSize=parseInt($('.safa-tree').css('font-size'))

    $('.safa-tree')
            .on('click','.safa-tree-node.on>a',function (e){
                var li=$(this).parent()
                li.removeClass('on').removeClass('line')
                liClear(li)

                li.find('.safa-tree-node.on').removeClass('on').removeClass('line').css('height','')
            })
        // 点击到没展开的按钮上
            .on('click','.safa-tree-node:not(.on)>a',function (e){
                var li=$(this).parent()
                var ul=$(this).next()

                li.addClass('on')
                liSelect(li,ul.prop('offsetHeight'))
            })

    function liSelect(li,ulHeight){

        var addHeight=ulHeight?li.prop('offsetHeight')+ulHeight+fontSize:li.prop('offsetHeight')
        li.css('height',addHeight)

        li.parents('.safa-tree-node').each(function(index){
            $(this).addClass('line').css('height',addHeight)
        })
    }

    function liClear(li){
        li.css('height','')
        li.parents('.safa-tree-node').eq(0).removeClass('line')
    }
    
})

</script>
</html>