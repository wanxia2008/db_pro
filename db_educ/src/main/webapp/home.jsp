<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- saved from url=(0040)http://jiaowubao.baonahao.com/Homes/home -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- meta部分 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>主体内容</title>
<!-- 样式部分 -->
<link rel="stylesheet" href="${ctx}/css/reset.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/uploadify.css">
<link rel="stylesheet" href="${ctx}/css/style.css">
<link href="${ctx}/css/jquery.mCustomScrollbar.css" rel="stylesheet">
<link rel="shortcut icon" type="image/x-icon"
	href="http://cdn.baonahao.com/common/img/ico_bnh.ico">
<link rel="stylesheet" href="${ctx}/layui/css/layui.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/lecturenote/style.css" />
<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<style type="text/css">
.index_cont_left .detail_show .table_zone {
	height: auto;
	padding: 21px 20px 10px;
}

.index_cont_right {
	padding: 80px 0 0;
}

.index_cont_left .detail_show .table_zone .tab_show th {
	text-align: center !important
}

.index_cont_left .detail_show .search_zone .inp_txt {
	height: 30px;
	line-height: 30px;
	border-radius: 4px;
}

.index_cont_left .detail_show .search_zone .create_student {
	height: 30px;
	line-height: 30px;
	font-size: 14px;
}
</style>
<!-- js部分公共部分 -->
<script src="${ctx}/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<!-- js单个页面部分 -->
<script src="${ctx}/js/artTemplate-simple-3.0.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.SuperSlide-2.1.1.min.js"
	type="text/javascript"></script>
<script src="${ctx}/js/layer.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/css/layer.css"
	id="layui_layer_skinlayercss">
<script src="${ctx}/js/page.min.js" type="text/javascript"></script>
<script src="${ctx}/js/page-main.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.mCustomScrollbar.js"
	type="text/javascript"></script>
<script src="${ctx}/js/jquery.mousewheel.min.js"></script>
<script src="${ctx}/js/commFunc.js" type="text/javascript"></script>
<script src="${ctx}/js/validate.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/jquery.uploadify.min.js"></script>
<script src="${ctx}/js/page-frame.min.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(function() {
		function tabHref(href, name) {
			name = handleTitleLength(name);
			parent.window.tabControl.addTab({
				'name' : name, //标签名
				'href' : href
			//标签链接
			});
		}
		jQuery("a[data-role='addTab']").click(function() {
			name = handleTitleLength(jQuery(this).attr("data-title"));
			parent.window.tabControl.addTab({
				'name' : name, //标签名
				'href' : jQuery(this).attr("data-url")
			//标签链接
			});
		});
		//tab页title字符串长度处理（最多6个字符）
		function handleTitleLength(str) {
			if (str.length > 6) {
				return str.slice(0, 5) + "...";
			} else {
				return str;
			}
		}

		jQuery('.con_inp').click(function() {
			parent.tabControl.closeCurTab();
		});
	});
</script>
<link href="${ctx}/css/main.css" rel="stylesheet" type="text/css">
<!-- js部分公共部分 -->
<link rel="stylesheet" href="${ctx}/css/style(1).css"
	id="layui_layer_skinjwbstylecss">
<link rel="stylesheet" href="${ctx}/css/style(2).css"
	id="layui_layer_skinjwb_askstylecss">
<link rel="stylesheet" href="${ctx}/css/layer.ext.css"
	id="layui_layer_skinlayerextcss">
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
</head>
<body class="frame_index">
	<!-- 内容右 -->
	<div class="index_cont_right">
		<div class="cont_middle">
			<div class="notice_title">
				<span class="notice_left" style="color: #000; line-height: 30px;">公告</span>
				<span class="layui-btn layui-btn-normal notice_right"
					id="add_notice" style="float: right; padding-top: 0">添加</span>
			</div>
			<div class="notice_c"></div>
		</div>

	</div>
	<!-- 内容左 -->
	<div class="index_cont_left">
		<p class="fast_enter">快速入口</p>
		<div class="detail_show">
			<div class="search_zone clearfix">
				<div class="ser_result_list" style="margin: 0">
					<input type="text" id="studentName" name="studentName"
						onkeyup="searchStudent(this)" onfocus="searchStudent(this)"
						placeholder="姓名" class="inp_txt"
						style="padding-left: 22px; border-radius: 4px; width: 250px; float: left; margin-right: 10px;">
					<button type="button" class="layui-btn layui-btn-normal"
						id="btn_search">搜索</button>
					<c:if test="${permiss_createStu==1}">
						<button type="button"
							class="layui-btn layui-btn-normal layui-btn-disabled">添加</button>
					</c:if>
					<c:if test="${permiss_createStu==0}">
						<button type="button"
							class="create_student layui-btn layui-btn-normal"
							style="border: none">添加</button>
					</c:if>
					<!-- 下拉列表 -->
					<ul class="ser_list_result" style="display: none;">

					</ul>
				</div>

				<script type="text/template" id="list_result">
					<li onclick="searchStudentList(this);">
						<a href="{url}">
							<span class="name">{student_name}</span>
							<span class="phone">{parent_phone}</span>
						</a>
					</li>
				</script>
				<script type="text/javascript">
					function searchStudentHide(obj) {
						jQuery('.ser_list_result').hide();
					}
					//学员查询结果
					function searchStudent(obj) {
						var obj = jQuery(obj);
						var value = obj.val();
						if (value.length == 0) {
							jQuery('.ser_list_result').hide();
							return;
						}
						jQuery.post('/Ajaxs/getStudent', {
							'value' : value
						}, function(data) {
							var html = '';
							jQuery.each(data, function(i, o) {
								var tpl = jQuery("#list_result").html();
								var tpl = tpl.replace("{url}", "javascript:;");
								var tpl = tpl.replace("{student_name}",
										o.student_name);
								var tpl = tpl.replace("{parent_phone}",
										o.parent_phone);
								html += tpl;
							});
							jQuery('.ser_list_result').show().html(html);
						}, 'json');
					}
				</script>
			</div>
			<div class="table_zone">
				<div class="switch_title">
					<div class="switch_zone">
						<span> 最近报名 </span>
					</div>
					<div class="look_det">
						<span class="refresh" id="refresh_data_account_sign_up">刷新</span>
					</div>
				</div>
				<div class="con_zone">
					<div class="con" id="flag" style="display: none;">
						<div id="tab_show_no_data" style="display: none;">
							<!-- 内容为空 -->
							<div class="content_blank index_data_chart">
								<p class="con">暂无相关数据</p>
							</div>
						</div>


						<table class="layui-table tab_show">


						</table>
					</div>
					<script type="text/template" id="signUpList" style="display: none;">
						<tr>
							<td>{s_name}</td>
                            <td>{sphone}</td>
							<td>{remark}</td>
							<td>{modifier}</td>
							<td>{modified}</td>
						</tr>
					</script>
				</div>
			</div>
			<script type="text/javascript">
				jQuery(".table_zone").slide({
					titCell : ".switch_zone span",
					mainCell : ".con_zone",
					autoPlay : "false",
					effect : "fade",
					trigger : "click"
				});
			</script>

			<script type="text/template" id="no_attendance">
					<li>
						<a href="javascript:;" class="addTabNoAttendance" data-title="班级考勤" data-url="/Lessons/index/gid:{url}" data-role="addTab">{goods_name}</a>
					</li>
				</script>
		</div>
	</div>
	</div>

	<!-- 添加公告模板 -->
	<script type="text/html" id="tpl_notice">
		<div class="layer_con_folder notice_tpl">
			<h1 class="tit"><span style="color:red;font-size:16px !important">*</span>公告标题：<span id="message_title_tip" class="tip_error">内容不能为空！</span></h1>
			<input type="text" class="inp msg_title" id="message_title"/>
			<h1 class="tit sec"><span style="color:red;font-size:16px !important">*</span>公告内容：<span id="message_content_tip" class="tip_error">内容不能为空！</span></h1>
			<textarea id="message_content" class="inp area msg_content"></textarea>
		</div>
	</script>
	<!-- 修改公告模板 -->
	<script type="text/html" id="tpl_notice_eidt">
		<div class="layer_con_folder notice_tpl">
			<h1 class="tit"><span style="color:red;font-size:16px !important">*</span>公告标题：<span id="message_title_tip" class="tip_error">内容不能为空！</span></h1>
			<input type="text" class="inp msg_title" value="{title}"/>
			<h1 class="tit sec"><span style="color:red;font-size:16px !important">*</span>公告内容：<span id="message_content_tip" class="tip_error">内容不能为空！</span></h1>
			<textarea class="inp area msg_content">{content}</textarea>
			<input type="hidden" class="msg_id" value="{msg_id}" />
		</div>
	</script>
	<!-- 公告详情模板 -->
	<script type="text/template" id="msg_details">
		<div class="notice_detail gg_{key}">
			<div class="tit">
				<i class="shut_btn icon-reply"></i>
				<div class="ctrl_btns">
					<i class="btn_edit icon-edit" data-role="notice_edit"></i>
					<i class="btn_dele icon-trash" data-role="notice_dele" style="color:#008bca"></i>
				</div>
				<h1 class="h_msg_title" style="font-size:15px !important;">{title}</h1>
			</div>
			<div class="con h_msg_content">{content}</div>
			<input type="hidden" class="msg_id" value="{msg_id}" />
            <div style="float:right;font-size:12px !important;">发布者：{publisher}</div> 
		</div>
	</script>

	<!-- 交互脚本 -->
	<script>
		$(function() {
			$('#btn_search')
					.click(
							function() {
								//var keyword = $.trim($("#search_text").val().replace(/(\\|%|\')+/g, ''));
								/* var studentName = checkUqiString($("#studentName").val()); */
								var studentName = $("#studentName").val();
								if (!studentName && studentName == "") {
									layer.alert("请输入搜索关键字");
									return;
								}
								$
										.ajax({
											url : '${ctx}/home/getstudent.do',
											type : 'get',
											data : {
												name : studentName
											},
											dataType : 'json',
											success : function(res) {
												if (res.code == 0) {
													parent.window.tabControl
															.addTab({
																'name' : '查看学员', //标签名
																//'href': '/Students/index'  //标签链接
																'href' : '${ctx}/student/studentmanage.do?studentName=${studentName}'
																		+ studentName //标签链接
															});
												} else {
													layer.alert(res.msg);
													return;
												}
											}
										})
							});

			//回车搜索
			$('#search_text').bind('keypress', function(event) {
				if (event.keyCode == "13") {
					//var keyword = $.trim($("#search_text").val().replace(/(\\|%|\')+/g, ''));
					var keyword = checkUqiString($("#search_text").val());
					parent.window.tabControl.addTab({
						'name' : '学员列表', //标签名
						'href' : '/Students/index/keyword:' + keyword //标签链接
					});
				}
			});
			jQuery('.down_zone .item-list').find('a').click(function() {
				var name = jQuery(this).text();
				var href = jQuery(this).attr('data-href');
				window.tabControl.addTab({
					'name' : name, //标签名
					'href' : href
				//标签链接
				});
			});
			getMerchantMessage();
			// ==添加公告交互
			$(".notice_right").click(
					function() {
						// 测试基于layer的二开
						parent.layerPc.confirm({
							title : "发布新公告",
							content : $("#tpl_notice").html(),
							area : [ "525px", "440px" ],
							btn : [ "发布", "取消" ],
							isShut : true,
							call : function() {
								// 这里传入回调
								var title = parent.$(".msg_title").val();
								var content = parent.$(".msg_content").val();
								var flag = true;
								if (title.length == 0 && content.length == 0) {
									parent.$("#message_title_tip").html(
											"标题不能为空").show();
									parent.$("#message_content_tip").html(
											"内容不能为空").show();
									flag = false;
								}
								if (title.length == 0) {
									parent.$("#message_title_tip").html(
											"标题不能为空").show();
									flag = false;
								} else {
									parent.$("#message_title_tip").hide();
								}
								if (content.length == 0) {
									parent.$("#message_content_tip").html(
											"内容不能为空").show();
									flag = false;
								} else {
									parent.$("#message_content_tip").hide();
								}
								if (title.length > 50) {
									parent.$("#message_title_tip").html(
											"最多输入50字符哦").show();
									flag = false;
								}
								if (content.length > 500) {
									parent.$("#message_content_tip").html(
											"最多输入500字符哦").show();
									flag = false;
								}
								if (!flag) {
									return;
								} else {
									$.ajax({
										url : '${ctx}/home/addnotice.do',
										type : 'post',
										dataType : 'json',
										data : {
											'noticeTitle' : title,
											'noticeContent' : content
										},
										success : function(res) {
											if (res.code == 0) {
												parent.layer.closeAll();
												parent.location.reload();
											} else {
												parent.window.msg = res.msg;
											}
										}
									});

								}

							}
						});
					});
			// 公告区域交互
			$(".cont_d").live(
					'click',
					function() {
						$(".notice_detail." + $(this).attr('data-name')).stop(
								true, true).fadeIn(300);
						$(".notice_detail .shut_btn").live(
								'click',
								function() {
									$(this).parents(".notice_detail").stop(
											true, true).fadeOut(300);
								});
					});
			// 			$(".click_return").live('click',function()}{
			// 				$
			// 			})
			$(".btn_dele")
					.live(
							"click",
							function() {
								var msg_id = $(this).parents(".notice_detail")
										.find(".msg_id").val();
								parent.layerPc
										.ask({
											call : function() {
												$
														.ajax({
															url : '${ctx}/home/deletenotice.do',
															type : 'post',
															dataType : 'json',
															data : {
																'id' : msg_id
															},
															success : function(
																	res) {
																if (res.code == 0) {
																	top.location.href = "${ctx}/user/index.do";
																} else {
																	parent.window.msg = data.msg;
																}
															}
														})
											}
										});
							});
			// ==编辑公共交互
			$(".btn_edit")
					.live(
							"click",
							function() {
								var id = $(this).parents(".notice_detail")
										.find(".msg_id").val();
								var title = $(this).parents(".notice_detail")
										.find(".h_msg_title").html();
								var content = $(this).parents(".notice_detail")
										.find(".h_msg_content").html();
								var cont_html = $("#tpl_notice_eidt").html();
								cont_html = cont_html.replace("{msg_id}", id);
								cont_html = cont_html.replace("{title}", title);
								cont_html = cont_html.replace("{content}",
										content);
								parent.layerPc
										.confirm({
											title : "编辑公告",
											content : cont_html,
											area : [ "525px", "440px" ],
											btn : [ "发布", "取消" ],
											isShut : true,
											call : function() {
												// 这里传入回调
												var id = parent.$(".msg_id")
														.val();
												var title = parent.$(
														".msg_title").val();
												var content = parent.$(
														".msg_content").val();
												var flag = true;
												if (title.length == 0
														&& content.length == 0) {
													parent
															.$(
																	"#message_title_tip")
															.html("标题不能为空")
															.show();
													parent
															.$(
																	"#message_content_tip")
															.html("内容不能为空")
															.show();
													flag = false;
												}
												if (title.length == 0) {
													parent
															.$(
																	"#message_title_tip")
															.html("标题不能为空")
															.show();
													flag = false;
												} else {
													parent
															.$(
																	"#message_title_tip")
															.hide();
												}
												if (content.length == 0) {
													parent
															.$(
																	"#message_content_tip")
															.html("内容不能为空")
															.show();
													flag = false;
												} else {
													parent
															.$(
																	"#message_content_tip")
															.hide();
												}
												if (title.length > 50) {
													parent
															.$(
																	"#message_title_tip")
															.html("最多输入50字符哦")
															.show();
													flag = false;
												}
												if (content.length > 500) {
													parent
															.$(
																	"#message_content_tip")
															.html("最多输入500字符哦")
															.show();
													flag = false;
												}
												if (!flag) {
													return;
												} else {
													parent.layer.closeAll();
												}
												$
														.ajax({
															url : '${ctx}/home/updatenotice.do',
															type : 'post',
															dataType : 'json',
															data : {
																'id' : id,
																'title' : title,
																'content' : content
															},
															success : function(
																	res) {
																if (res.code == 0) {
																	top.location.href = "${ctx}/user/index.do";
																} else {
																	parent.window.msg = data.msg;
																}
															}
														})

											}
										});
							});

		
			$('.create_student1').click(function() {
				layer.alert("您没有权限操作");
			});
			$('.create_student')
					.click(
							function() {
								$
										.ajax({
											url : '${ctx}/home/addStudent.do',
											dataType : 'json',
											data : {

											},
											success : function(data) {
												if (data.code == 0) {
													window.location.href = "${ctx}/student/newstudent.do"; //标签链接
												} else {
													layer.msg(res.msg);
													return;
												}
											}
										})

							});

			$(".addTabNoAttendance").live('click', function() {
				parent.window.tabControl.addTab({
					'name' : jQuery(this).attr("data-title"), //标签名
					'href' : jQuery(this).attr("data-url")
				//标签链接
				});
			})

			$("body").not(".ser_list_result ul li").click(function() {
				jQuery('.ser_list_result').hide();
			});

			//获取报名最新记录
			function getSignUpList() {
				jQuery
						.ajax({
							url : "${ctx}/home/getstudentbytime.do",
							type : 'post',
							async : false,//使用同步的方式,true为异步方式
							data : {},//这里使用json对象
							dataType : 'json',
							success : function(res) {
								if (!res || res.count == 0) {
									jQuery("#tab_show_no_data").show();
									jQuery(".tab_show").hide();
								} else {
									var data = res.objects;
									jQuery("#tab_show_no_data").hide();
									jQuery(".tab_show").show();
									var html = '<tr><th>学员姓名</th><th>联系方式</th><th>学员状态</th><th>就读学校</th><th>加入时间</th></tr>';
									jQuery
											.each(
													data,
													function(i, o) {
														var tpl = jQuery(
																"#signUpList")
																.html();
														if (o.studentName) {
															tpl = tpl
																	.replace(
																			"{s_name}",
																			o.studentName);
														} else {
															tpl = tpl.replace(
																	"{s_name}",
																	o.userName);
														}
														tpl = tpl
																.replace(
																		"{sphone}",
																		o.phone ? o.phone
																				: '未填写');
														tpl = tpl
																.replace(
																		"{remark}",
																		o.studentStatus == 1 ? '预报名'
																				: o.studentStatus == 2 ? '未交学费'
																						: o.studentStatus == 3 ? '未分班'
																								: o.studentStatus == 4 ? '已分班'
																										: '完成课程');
														tpl = tpl
																.replace(
																		"{modifier}",
																		o.attendSchool ? o.attendSchool
																				: '暂无');
														var modified = o.createTime
																.substring(0,
																		10);
														tpl = tpl.replace(
																"{modified}",
																modified);
														html += tpl;
													});
									jQuery(".tab_show").html(html);
								}
								loadingDestroy("flag");
							},
							fail : function() {
							}
						});
				// //设置操作人
				commFunc.setOperator();
			}
			getSignUpList();
			//支出、收入数据容器
			var expenses = income = '';

			function getMerchantMessage() {
				jQuery
						.post(
								'${ctx}/home/getnotice.do',
								{
									pageNo : 1,
									pageSize : 5
								},
								function(res) {
									var dataLen = res.count;
									if (dataLen > 0) {
										var tit_html = '';
										var cont_html = '';
										jQuery
												.each(
														res.objects,
														function(i, o) {
															var tit_tpl = '<div class="cont_d" data-name="gg_{key}">{title}<span style="float:right">{time}</span></div>';
															tit_tpl = tit_tpl
																	.replace(
																			"{key}",
																			i);
															var title = o.noticeTitle;
															tit_tpl = tit_tpl
																	.replace(
																			"{title}",
																			title);
															var time = o.createTime;
															tit_tpl = tit_tpl
																	.replace(
																			"{time}",
																			time);
															tit_html += tit_tpl;
															var cont_tpl = $(
																	"#msg_details")
																	.html();
															cont_tpl = cont_tpl
																	.replace(
																			"{key}",
																			i);
															cont_tpl = cont_tpl
																	.replace(
																			"{msg_id}",
																			o.noticeId);
															cont_tpl = cont_tpl
																	.replace(
																			"{title}",
																			title);
															cont_tpl = cont_tpl
																	.replace(
																			"{content}",
																			o.noticeContent);
															cont_tpl = cont_tpl
																	.replace(
																			"{publisher}",
																			o.teacher.teacherName);
															cont_html += cont_tpl;
														});
										$(".notice_c").html(tit_html);
										$(".notice_detail").remove();
										$(".cont_middle").append(cont_html);

									} else {
										$(".notice_c").html('');
										$(".notice_detail").stop(true, true)
												.fadeOut(300);
									}
								}, 'json');
			}

			$('#sp_data').find('a').live("click", function() {
				var span = jQuery(this).find('span');
				parent.window.tabControl.addTab({
					'name' : span.text(), //标签名
					'href' : span.attr('data-href')
				//标签链接
				});
			});

		}); //readyEnd

		/**
		 * 快速入口点击搜索学员
		 *
		 * @param obj
		 */
		function searchStudentList(obj) {
			var name = $.trim($(obj).find('.name').html());
			var phone = $.trim($(obj).find('.phone').html());
			parent.window.tabControl.addTab({
				'name' : '学员列表', //标签名
				'href' : '/Students/index/name:' + name + '/phone:' + phone //标签链接
			});

			jQuery('.ser_list_result').hide();
		}
	</script>

</body>
</html>