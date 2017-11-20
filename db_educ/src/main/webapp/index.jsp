<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- meta部分 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>云教育</title>
<!-- 样式部分 -->
<link rel="stylesheet" href="${ctx}/css/reset.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/uploadify.css">
<link rel="stylesheet" href="${ctx}/css/style.css">
<link href="${ctx}/css/jquery.mCustomScrollbar.css" rel="stylesheet">
<link rel="shortcut icon" type="image/x-icon"
	href="http://139.199.194.237:8086/db_educ/favicon.ico">
<!-- js部分公共部分 -->
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<!-- js单个页面部分 -->
<script src="${ctx}/js/layer.js" type="text/javascript"></script>
<script src="${ctx}/js/artTemplate-simple-3.0.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery_md5.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.SuperSlide-2.1.1.min.js" type="text/javascript"></script>
<script src="${ctx}/js/page.min.js" type="text/javascript"></script>
<script src="${ctx}/js/page-main.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery.mousewheel.min.js"></script>
<script src="${ctx}/js/commFunc.js" type="text/javascript"></script>
<script src="${ctx}/js/validate.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/jquery.uploadify.min.js"></script>
<script src="${ctx}/js/page-frame.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx}/css/style(1).css"
	id="layui_layer_skinjwbstylecss">
<link rel="stylesheet" href="${ctx}/css/style(2).css"
	id="layui_layer_skinjwb_askstylecss">
<link rel="stylesheet" href=".././layui/css/layui.css" />
<script src=".././layui/layui.js" charset="utf-8"></script>
</head>
<style type="text/css">
	.showpass {
		width:90%;
		height:90%;
		position:relative;
		margin:0 auto;
		border-radius:15px;

	}
	.passul {
		margin-left: auto;
		margin-right:auto;
		text-align:center;
		padding:10px;
	}
	.passul li {
		font-size:15px;
		color:#000000;
		margin-top:15px;
	}
	.comfirm {
		width:80px;
		height:30px;
		margin-top:10px;
		text-align:center;
		margin-left:auto;
		margin-right:auto;
		background:#35b998;
		color:#fff;
		border-radius:10px;
	}
	.confirmpass {
		height:30px;
		line-height:30px;
		text-align:center;
		
	}
	.head_a {
		padding:6px;
		color:#ffffff;
		border-radius:10px;
	}
	.head_b {
		padding:6px;
		color:#ffffff;
		border-radius:10px;
	}
</style>
<body class="index" style="background-color:#f2f9fd;">
	<!-- 顶部条 -->
	<!-- 顶部条 -->
	<div class="header_top">
		<a class="pic_a"> <img src="${ctx}/css/images/feikan.png" alt=""></a>
		<div class="right_title clreafix">
			<div class="a_groups" style="margin-right:30px;">
			 <span class="head_a" style="padding-right:20px !important">当前登录角色：${teacher2.role.roleName}</span>
             <a class="button button-little head_a" href="#" onclick="changepass();"><span class="icon-wrench"></span>修改密码</a> &nbsp;&nbsp;
             <a class="button button-little head_b" href="#" id="logout"><span class="icon-power-off"></span> 退出系统</a> 
			</div>
		</div>
	</div>
	<div class="wrapper">
		<!-- 导航条 -->
		<!-- 导航条 -->
		<div class="navbar">		
			<!-- 菜单区域开始 -->
			<div class="down_zone clearfix">
				<div class="head_photo" style="padding-top:10px !important;">
 					<div class="up_right">
						<span style="padding-bottom: 10px;padding-left:10px;">欢迎您，${currTeacher.teacherName}</span> 
<!-- 	                     <p style="text-align:center;color:#fff">管理员</p> -->
					</div>
				</div>

				<!--所有分类 Start(父类菜单)-->
				<div class="wrap mCustomScrollbar _mCS_1" id="wrap"
					style="height: 706px;">
					<div class="mCustomScrollBox mCS-light" id="mCSB_1"
						style="position: relative; height: 100%; overflow: hidden; max-width: 100%;">
						<div class="mCSB_container mCS_no_scrollbar"
							style="position: relative; top: 0;">

							<div class="all-sort-list">
								<div class="item bo">
									<h3 class="nav1 zhao_manage"
										style="background-image: url('../image/shouye_10.png')";>
										<a href="javascript:;"
											onclick="tabControl.addTab({name:&#39;首页&#39;, href:&#39;/Homes/home&#39;})"
											data-href="/Homes/home">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页
										</a>
									</h3>
								</div>
								<!-- 父菜单列表 -->
								<div id="parentMenuList"></div>
							</div>
						</div>
						<div class="mCSB_scrollTools"
							style="position: absolute; display: none;">
							<div class="mCSB_draggerContainer">
								<div class="mCSB_dragger" style="position: absolute; top: 0px;"
									oncontextmenu="return false;">
									<div class="mCSB_dragger_bar" style="position: relative;"></div>
								</div>
								<div class="mCSB_draggerRail"></div>
							</div>
						</div>

					</div>
				</div>

				<!-- 导航条隐藏部分(子菜单) -->
				<div id="childMenuList">
					
				</div>

			</div>
			<!-- 菜单区域结束 -->
		</div>
	</div>
	<!-- 主要内容 -->
	<div class="right_zone">
		<!-- 副导航 -->
		<!-- 副导航 -->
		<div class="nav_sub">
			<!-- 向前 -->
			<span class="go_prev">&lt;&lt;&lt;</span>
			<!-- 向后 -->
			<div class="future_go">
				<span class="go_next">&gt;&gt;&gt;</span>
				<div class="close_oper">
					<div class="up_close">
						<span class="bg_close" id="shut_ctrl">关闭标签页</span>
						<ul class="close_list" id="close_list" style="display: none;">
							<li id="set_position">定位当前选项卡</li>
							<li id="close_all_btn">关闭全部选项卡</li>
							<li id="close_other_btn">关闭其他选项卡</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- 历史记录卡 -->
			<div class="his_tab_list_box">
				<ul id="his_tab_list" class="his_tab_list">
					<li id="tab_Homes_home"
						onclick="tabControl.addTab({name:&#39;首页&#39;, href:&#39;/Homes/home&#39;})"
						data-href="/Homes/home" class="on"><a href="javascript:;">首页</a></li>
				</ul>
			</div>
		</div>
		<!-- 主要内容 -->
		<div class="main_cont" id="main_cont" style="height: 797px;">
			<iframe id="frame_Homes_home" src="${ctx}/home/tohome.do" frameborder="0"
				width="100%" height="100%" style="display: inline;"></iframe>
		</div>
	</div>

	<!-- js交互部分 -->
	<script type="text/javascript">
	layui
	.use(
			[ 'form', 'layedit', 'laypage', 'laydate', 'layer' ],
			function() {
				var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

			
			});
	$(function(){
		  jQuery.ajax({
		    	 url:'${ctx}/menuTree/menuTree.do',
		    	 type: 'post', 
		    	 dataType: 'json',
				 success:function(data){
					var menus = getMenuItem(data); 
					$('#parentMenuList').html(menus.html);
					$('#childMenuList').html(menus.html2);
					// 左侧导航对象封装
		            var nav_ctrl = new NavCtrl({
		                callHide: function(iWidth){
		                    window.tabControl.containerWid = iWidth;
		                },
		                callShow: function(iWidth){
		                    window.tabControl.containerWid = iWidth;
		                }
		            });
					// tab控件及记录历史
					window.tabControl.config({
						tabContainer: 'his_tab_list', //tab 容器ID
						frameContainer: 'main_cont', //iframe 容器ID
						expires: 7, 				 //过期时间
						tabCookieName: 'his_tab_list', //tab标签cookie名
						selectedClass: 'on', 		 //选中样式
						defaultHref: '/Homes/home', //默认href
						hrefCookieName: 'his_tab_list_href', //当前href的cookie名
						tabBlockElement: 'li', //tab块顶层元素名
						prevBtn: 'go_prev', //向前按钮
						nextBtn: 'go_next', //向后按钮
						setPosBtn: 'set_position', //定位当前选项卡
						closeAll: 'close_all_btn', //定位当前选项卡
						closeOther: 'close_other_btn' //定位当前选项卡
					});
		            window.tabControl.initTab();
					jQuery('.all-sort-list').find('a').click(function(){
						var name = jQuery(this).text();
						var href = jQuery(this).attr('data-url');

						/* window.tabControl.addTab({
							'name':handleTitleLength(name), //标签名
							'href':href  //标签链接
						}); */
					});

		            // ==给隐藏显示元素a绑定事件
		            jQuery('.down_zone .item-list').find('a').click(function(){
		                var name = jQuery(this).text();
		                var href = jQuery(this).attr('data-href');
		                window.tabControl.addTab({
		                    'name':name, //标签名
		                    'href':href  //标签链接
		                });
		            });
		 		 }
			})
		}); 
	
	/*  显示父菜单*/
	function getMenuItem(data){
		var html = '';
		var html2 = '';
		for(i in data.objects){
			html += '<div class="item"> <h3 class="nav1 zhao_manage" style="background-image:url(&#39;'+data.objects[i].menuLogo+'&#39;);"> <a href="javascript:;" data-role="addTab">'+data.objects[i].menuName+'</a></h3></div>';
			var childs = data.objects[i].childs;
 			if(childs!= null){
 				html2+='<div class="item-list clearfix" style="top: 188px; display: none;"><div class="subitem" style="width: 104px;"><dl>'
				for(var j=0;j<childs.length;j++){
					html2+='<dd><a href="javascript:;" data-href='+childs[j].menuUrl+'>'+childs[j].menuName+'</a></dd>'
				}
 				html2+='</dl></div></div>';
			}
 			
		}
		return {html:html?html:'',html2:html2?html2:''};
	}
        var add_tab = "";//上架课程直接打开新建班级页面

		$(function (){
			// 设置主要内容的高度 by 高
			var iWinHei = getViewPortSize().height;
			var iTopHei = $(".header_top").outerHeight();
			var iSubNavHei = $(".nav_sub").outerHeight();
			window["iHeiResult"] = iWinHei - iTopHei - iSubNavHei;
			
			$("#main_cont").height(iHeiResult);
			$(window).resize(function(){
				var iWinHei = getViewPortSize().height;
				$("#main_cont").height(iWinHei - iTopHei - iSubNavHei);
			});

			// 下拉菜单交互
			$(".header_top").slide({
				type:"menu", 
				titCell:".drop_down_list", 
				targetCell:".drop_down_list_sub",
				effect:"slideDown",
				delayTime:300,
				returnDefault:false,
				defaultPlay:false,
				startFun: function( i, c, slider, titCell, mainCell, targetCell, prevCell, nextCell){
					var oTit = titCell.eq(i);
					if(oTit.attr("data-role") == "setting"){
						$(".close_oper").removeClass('on');
						$("#close_list").hide();
					}
				}
			});
			$(".drop_down_list").hover(function() {
				
			}, function() {
				$(this).removeClass('on');
			});
			$(".drop_down_list_sub_box").hover(function() {
				$(this).parents(".drop_down_list").addClass('on');
			}, function() {
				$(this).parents(".drop_down_list").removeClass('on');
			});

			// =副导航关闭操作交互
			$("#shut_ctrl").click(function(ev) {
				var oPar = $(this).parent().parent();
				var oSib = $(this).siblings();
				var oEv = ev || event;
				oEv.stopPropagation();
				if(oPar.hasClass('on')){
					oPar.removeClass('on');
					oSib.hide();
				}else{
					oPar.addClass('on');
					oSib.show();
				}
			});

			// ==点击空白区域隐藏关闭操作按钮组
			$(document).click(function() {
				$("#shut_ctrl").parent().parent().removeClass('on');
				$("#close_list").hide();
			});
			

            /** 上架课程 */
           /*  if (add_tab == 'add_goods') {
                window.tabControl.addTab({
                    'name':"添加班级", //标签名
                    'href': "/GoodsClassOtms/add"  //标签链接
                });
            } */

			function handleTitleLength(str){
				if(str.length > 6){
					return  str.slice(0,5) + "...";
				}else{
					return str;
				}
			}


			
		});
        
        
		function changepass() {
	 		layer.open({
	 			 type: 1,
	 			 skin: '#008bca',
	 			 area: ['520px', '320px'], //宽高
	 			 title:'修改密码',
	 			 btn:['确定','取消'],
	 			 anim: 1,
	 			 content: '<div id="showdiv" class="showpass"><ul class="passul"><li class="layui-inline"><label class="layui-form-label" style="width:120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>原始密码：</label><input class="layui-input" style="width:200px;"  type="password" id="old" placeholder="请输入原始密码"/></li><li class="layui-inline"><label class="layui-form-label" style="width:120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>新的密码：</label><input class="layui-input" style="width:200px;" type="password" id="new1" placeholder="请输入新的密码" value=""/></li><li class="layui-inline"><label class="layui-form-label" style="width:120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>确认密码：</label><input style="width:200px;" class="layui-input" type="password" id="new2" placeholder="再次确认新密码" value=""/></li></ul></div>',
	 			 yes:function() {
	 				var oldpass = $('#old').val();
	 		 		var newpass = $('#new1').val();
	 		 		var newpass1 = $('#new2').val();
	 		 		var match = /^[\w]{6,16}$/;
	 		 		if(!oldpass) {
	 		 			layer.msg('原始密码不能为空!');
	 		 			return;
	 		 		}
	 		 		if(!newpass) {
	 		 			layer.msg('所设的新密码不能为空!');
	 		 			return;
	 		 		}
	 		 		if(newpass == oldpass) {
	 		 			layer.msg('新密码不能与原始密码相同!');
	 		 			return;
	 		 		}
	 		 		if(!newpass1) {
	 		 			layer.msg('需再次输入新密码!');
	 		 			return;
	 		 		}
	 		 		if(newpass!=newpass1) {
	 		 			layer.msg('两次输入不一样，请重新输入!');
	 		 			return;
	 		 		}
	 		 		if(!match.test(newpass)) {
	 		 			layer.msg('密码须由字母和数字或下划线组,可设置位数6-16!');
	 		 			return;
	 		 		} 
	 		 		$.ajax({
	 		 			url:'${ctx}/teacher/preservationPass.do',
	 		 			type:'POST',
	 		 			dataType:'JSON',
	 		 			data:{'password':oldpass,'confirmPassword':newpass,'modifyPassword':newpass1},
	 		 			
	 		 			success:function(data){
	 				    	if(data.code == 0){
	 				    		layer.msg('修改密码成功!');
	 				    		setTimeout(function(){
		 				    		top.location.href = "${ctx}/user/teacherLogin.do";

	 				    		},1000);
	 				    	}else{
	 				    		layer.msg(data.msg);
	 				    		return;
	 				    	}
	 				    },
	 		 			error:function() {
	 		 				layer.msg("修改密码失败!");
	 		 				return;
	 		 			}
	 		 		}); 	
	 			 },
	 			 btn2:(function(index) {
	 				 layer.close(index);
	 			 })
	 		});
	 	}
		
		$('#logout').on('click',function() {
			layer.confirm('确认退出吗？', {
				btn : [ '确定','取消' ]
			}, function(){
				document.getElementById("close_all_btn").click();  //点击促发关闭导航栏,防止再次登陆的时候出现权限不匹配问题
				window.location.href="${ctx}/user/doLogout.do";
				layer.close(index);
			},function(index, layero) {//确定的处理
				 layer.closeAll();  
			});
		})
	</script>
</body>
</html>
