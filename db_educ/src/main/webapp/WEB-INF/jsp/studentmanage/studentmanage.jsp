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
<title>学生管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
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
</style>
</head>
<body>
	<form class="layui-form" id="frm1" action="">
		<div class="panels_head">
			<span>学员列表</span>
			<p style="float: right; margin-right: 20px;">
<!-- 				<button type="button" class="layui-btn layui-btn-normal" -->
<!-- 					lay-submit="" onclick="window.location.href='newstudent.do'" -->
<!-- 					lay-filter="demo1" -->
<!-- 					style="height: 30px; line-height: 30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; font-size: 14px">新增</button> -->
			</p>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
            <div class="layui-inline">
			<c:if test="${currTeacher.teacherType==1}">
			<label class="layui-form-label" style="width: 100px;font-size:14px;">校区</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="schoolId" id="schoolId" class="input w80">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${szList}" var="sz">
							<c:choose>
								<c:when test="${schoolId==sz.schoolId}">
									<option selected="selected" value="${sz.schoolId}">${sz.schoolName}</option>
								</c:when>
								<c:otherwise>
									<option value="${sz.schoolId}">${sz.schoolName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
			</div>
			</c:if>
		</div>
		<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;font-size:14px;">学生状态</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="studentStatus" id="studentStatus" class="input w80">
						<option value="">全部</option>
						<option value="">全部</option>
						<option value="1"
							<c:choose>
									<c:when test="${studentStatus==1}">selected="selected"</c:when>
									</c:choose>>预报名</option>
						<option value="2"
							<c:choose>
									<c:when test="${studentStatus==2}">selected="selected"</c:when>
									</c:choose>>未交学费</option>
						<option value="3"
							<c:choose>
									<c:when test="${studentStatus==3}">selected="selected"</c:when>
									</c:choose>>未分班</option>
						<option value="4"
							<c:choose>
									<c:when test="${studentStatus==4}">selected="selected"</c:when>
									</c:choose>>已分班</option>
						<option value="5"
							<c:choose>
									<c:when test="${studentStatus==5}">selected="selected"</c:when>
									</c:choose>>完成课程</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;font-size: 14px;">学员姓名</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" name="studentName" id="studentName"
						lay-verify="studentName" value="${studentName}" autocomplete="off"
						placeholder="请输入学生姓名" class="layui-input" maxlength="6">
				</div>
			<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" id="search_content">搜索</button>			
			</div>
			
			
	</div>
		<c:choose>
			<c:when test="${sList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table" style="width: 98%; margin: 0 auto">
					<thead>
						<tr>
							<th>ID</th>
							<th>校区</th>
							<th>年级</th>
							<th>姓名</th>
							<th>性别</th>
							<th>状态</th>
							<th>缴费状态</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach items="${sList}" var="s">
							<tr>
								<td><c:out value="${s.studentId}"></c:out></td>
								<td>
								<c:choose>
										<c:when test="${s.schoolZone.schoolName !=null}">
								${s.schoolZone.schoolName}
								</c:when>
										<c:otherwise>
								      暂无安排校区
								</c:otherwise>
									</c:choose>
								</td>
								<td><c:out value="${s.grade2.gradeName}"></c:out></td>
								<td><c:out value="${s.studentName}"></c:out></td>
								<td><c:if test="${s.sex==1}">男</c:if> <c:if
										test="${s.sex==2}">女</c:if></td>
								<td><c:if test="${s.studentStatus==1}">预报名</c:if> <c:if
										test="${s.studentStatus==2}">未交学费</c:if> <c:if
										test="${s.studentStatus==3}">未分班</c:if> <c:if
										test="${s.studentStatus==4}">
											<c:choose>
											<c:when test="${s.careerList != null}">
												<c:forEach items="${s.careerList}" var="career">
													<p>${career.classNo.className}&nbsp;/&nbsp;${career.subjects.subjectName}</p>
												</c:forEach>
											</c:when>
											<c:otherwise>
								     	暂无班级
										</c:otherwise>
										</c:choose>
								</c:if> 
								<c:if test="${s.studentStatus==5}"><span style="color: #ff5722;">完成课程</span></c:if></td>
								<td>
								  <c:choose>
								  <c:when test="${s.payType == null}">
								       暂无
								  </c:when>
								  <c:otherwise>
								  <c:if test="${s.payType == 1}">
								  免费入学
								  </c:if>
								  <c:if test="${s.payType == 2}">
								   缴费入学
								  </c:if>
								  </c:otherwise>
								  </c:choose>
								</td>
								<td><fmt:formatDate value="${s.createTime}"
										pattern="yyyy-MM-dd"></fmt:formatDate></td>
								<td>

								<a href="toupdatestudent.do?studentid=<c:out value="${s.studentId}"></c:out>&pageNo=${page}&type=1">
										<span class="icon-edit"
										style="font-size: 14px; color: #008bca;">查看</span>
								</a> &nbsp;
					<!--			 <a class="button border-red" href="javascript:void(0)"
									onclick="deleteone(<c:out value="${s.studentId}"></c:out>)">
										<span class="icon-remove-sign"
										style="font-size: 14px; color: #ff5722; padding-right: 10px;">
											删除</span>
								</a>  --></td>
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
<!-- 									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp; <input -->
<!-- 										type="text" id="goPageId" -->
<!-- 										style="width: 50px; height: 30px; line-height: 30px; text-align: center" -->
<%-- 										value="${page}" name="goPage" />&nbsp;&nbsp;页 <a --%>
<!-- 										href="javascript:;" -->
<%-- 										onclick="goMyPage($('#goPageId').val(),${totalPages});">&nbsp;&nbsp;Go</a> --%>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
			<!--模态框结束-->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			     aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:65%;">
				<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">分配班级</h4>
                     </div>
					<div class="modal-body">
					
					<div class="layui-form-item" style="margin-top: 10px;">
							<div class="layui-inline">
								<label class="layui-form-label" style="font-size: 14px;">季节</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="seasonType" id="seasonType" lay-filter="select_grade">
										<option value="">全部</option>
										<option value="">全部</option>
										<c:forEach items="${stList}" var="y">
											<option value="<c:out value="${y.seasonId}" />"><c:out
													value="${y.seasonName}"></c:out></option>
										</c:forEach>
									</select>
								</div>
								</div>
								<div class="layui-inline">
								<label class="layui-form-label" style="font-size: 14px;">科目</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="subject" id="subject" lay-filter="select_grade">
										<option value="">全部</option>
										<option value="">全部</option>
										<c:forEach items="${subjects}" var="y">
											<option value="<c:out value="${y.subjectId}" />"><c:out
													value="${y.subjectName}"></c:out></option>
										</c:forEach>
									</select>
								</div>
								<div class="layui-inline">
								<label class="layui-form-label" style="font-size: 14px;">年级</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="grade" id="grade" lay-filter="select_grade">
										<option value="">全部</option>
										<option value="">全部</option>
										<c:forEach items="${grades}" var="y">
											<option value="<c:out value="${y.gradeId}" />"><c:out
													value="${y.gradeName}"></c:out></option>
										</c:forEach>
									</select>
								</div>
								</div>
								<button type="button" class="layui-btn layui-btn-normal" id="search111"
									    style="margin-top: -10px;">搜索</button>
							</div>
					</div>
						<table class="layui-table" style="width: 98%; margin: 0 auto">
						<thead>
							<tr>
						     	<th>年份</th>
						     	<th>季节</th>
						     	<th>校区</th>
						     	<th>科目</th>
						     	<th>年级</th>
								<th>班级</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody  id="tbody2">
						
						</tbody>
					</table>
					<input type="text" hidden="" name="studentId" id="studentId2"/>
					<input type="text" hidden="" name="year" id="year2"/>
					<div id="page" style="text-align: center;"></div>
				</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	
     <script type="text/html" id="note_template1">
		{{each objects as value i}}
		<tr>
            <td>{{value.schoolYear.year}}</td>
            <td>{{value.seasonType2.seasonName}}</td>
              <td>{{value.schoolZone.schoolName}}</td>
			<td>{{value.subject2.subjectName}}</td>
              <td>{{value.grade2.gradeName}}</td>
			<td>{{value.className}}</td>
			<td>
				<button type="button" class="layui-btn add_note"
					data-id="{{value.classId}}" data-grade="{{value.grade}}"
                    data-subject="{{value.subject}}"  data-type="{{value.seasonType}}"
                     data-year="{{value.year}}" data-school="{{value.schoolArea}}"
					style="height:30px;line-height:30px;padding:0 18px;">
					添加</button>
			</td>
		</tr>
		{{/each}}
	</script>

</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
var pageno = 1, pagesize = 5,seasonType,grade,subject;
layui.use([ 'form', 'layedit', 'laypage', 'laydate' ],
		function() {
			var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

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
				/* layer.alert(JSON.stringify(data.field), {
					title : '最终的提交信息'
				}) */
				return true;
			});
			
			getNote(pageno,grade,subject,seasonType);
			function getNote(pageno,grade,subject,seasonType) {
				$.getJSON("getClassNoList.do", {
					pageNo : pageno,
					pageSize : pagesize,
					grade:grade,
					subject:subject,
					seasonType:seasonType
				}, function(res) {
					if (res.code == 0) {
						var html = template('note_template1', res);
						$('#tbody2').html(html);
						click_addnote();
						if (res.count > 1) {
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
										getNote(obj.curr);
									}
								}
							});
						}
					} else{
//	 					layer.msg(res.msg);
					}
				})
			}
			
			
		});
		
		
function goPageAction(page){
	window.location.href="${ctx}/student/studentmanage.do?pageNo="+page+"&studentName=${studentName}&startCreateTime=${startCreateTime}&endCreateTime=${endCreateTime}&studentStatus=${studentStatus}&schoolId=${schoolId}";
	
	}
function goMyPage(page,totalPage){
	if(page>0 && page<=totalPage){
		window.location.href="${ctx}/student/studentmanage.do?pageNo="+page+"&studentName=${studentName}&startCreateTime=${startCreateTime}&endCreateTime=${endCreateTime}&studentStatus=${studentStatus}&schoolId=${schoolId}";
	}else{
		alert("请输入有效的整数");
	}
}

function deleteone(uid){
	 layer.confirm('您确认删除该学员么?',{btn:['确认','取消']},function(){
		 $.post('${ctx}/student/deletestudent.do?',{'studentid':uid},function(data){
			  if(data.code == 0){
					layer.msg('删除学员成功');
					setTimeout(function(){
						 window.location.href="${ctx}/student/studentmanage.do";
					},1000);
					
			  }else{
				  layer.msg(data.msg,{time:3000});
		    	return;
			  }
		 });
	    },function(){	
	    	layer.closeAll();
	});
}

window.onload = function() {
    //这么写是为了实现js代码与html代码的分离，
   // 当我修改js时，不能影响html代码。
    document.getElementById("frm1").onsubmit = function() {
        var d1 = this.startCreateTime.value;
        var d2 = this.endCreateTime.value;
     
        if (!compareDate(d1, d2)) {
           layer.msg("第二个日期不能比第一个日期小!");
            return false;
        }
    };
}
function compareDate(d1, d2) {
    var arrayD1 = d1.split("-");
    var date1 = new Date(arrayD1[0], arrayD1[1], arrayD1[2]);
    var arrayD2 = d2.split("-");
    var date2 = new Date(arrayD2[0], arrayD2[1], arrayD2[2]);
    if (date1 > date2)
        return false;
    return true;
}
function verifyDate(d) {
    var datePattern = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
    return datePattern.test(d);
}


$('#search_content').click(function(){
	
	var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i");   
	var schoolName2=$('#studentName').val();
	
    if(pat.test(schoolName2)==true)   
    {   
        layer.msg("输入名称中含有非法字符!");   
        return false;   
    } else{
    	$('#frm1').submit();
    }
});
$('.icon-edit').on('click',function(){
	var studentId= $(this).attr("id");
	var classNo=$(this).attr("data-name");
	var year=$(this).attr("data-year");
	$('#studentId').val(studentId);
	$('#classNo').val(classNo);
	$('#year2').val(year);
});
$('#confirm_update').on('click',function(){
	var studentId=$('#studentId').val();
	var classNos = new Array();
	$('input[name="classNo"]:checked').each(function() {
		classNos.push($(this).val());//向数组循环添加选中的值
	});
	var calssNo = classNos.join(",");//转为字符串
	
	$.ajax({
		url:'modityClassNo.do',
		type:'post',
		dateType:'json',
		data:{
			studentId:studentId,
			calssNo:calssNo
		},
	     success:function(data){
		if(data.code==0){
			  layer.msg("分配班级成功!");  
			  window.location.reload();
		}else{
			layer.msg(data.msg);   
			return;
		}
	},
	})
});
$('.icon-edit').on('click',function(){
	var studentId=$(this).attr('id');
	$('#studentId2').val(studentId);
	var year=$(this).attr('data-year');
	
});
click_addnote();
function click_addnote() {
$('.add_note').on('click',function(){
	var classId=$(this).attr('data-id');
	var studentId=$('#studentId2').val();
	var year=$(this).attr('data-year');
	var subjectId=$(this).attr('data-subject');
	var gradeId=$(this).attr('data-grade');
	var seasonId=$(this).attr('data-type');
	var schoolId=$(this).attr('data-school');
	layer.confirm('确认添加该班级吗？', {
		btn : [ '确定' ]
	}, function(index, layero) {//确定的处理,暂未添加到记录表
		$.ajax({
			url:'${ctx}/student/distClassId.do',
			type:'post',
			dateType:'json',
			data:{
				studentId:studentId,
				classId:classId,
				subjectId:subjectId,
				gradeId:gradeId,
				seasonId:seasonId,
				year:year,
				schoolId:schoolId
			},
			success:function(data){
				if(data.code==0){
					layer.msg('添加班级成功!');
					window.location.href = "${ctx}/student/studentmanage.do";
				}else{
					layer.msg(data.msg);
					return;
				}
			},
		})
		layer.close(index);
	});
})
}

$('#search111').on('click', function() {
	 grade= $('#grade').val();
	 subject= $('#subject').val();
	 seasonType= $('#seasonType').val();
		$.ajax({
			url : 'getClassNoList.do', 
			type : 'post',
			dataType : 'json',
			data : {
				grade : grade,
				subject : subject,
				seasonType : seasonType
			},
			success : function(res) {
				if (res.code == 0) {
					var html = template('note_template1', res);
					$('#tbody2').html(html);
					$('#myModal').modal('show');
					click_addnote();
				} else {
					layer.msg(res.msg);
				}
			}
		})
})
</script>
</html>