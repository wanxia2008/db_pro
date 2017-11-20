<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>新增角色</title>
<link rel="stylesheet" href="${ctx}/css/pintuer/pintuer.css"/>
<link rel="stylesheet" href="${ctx}/css/pintuer/admin.css"/>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<style type="text/css">
html,body{background-color:#fff !important}
.quanxian li{float:left;padding-right:30px;}
.parent{font-size:16px !important;font-weight:400;padding-bottom:10px;}
.table{padding-left:10% !important}
.form-group .table{width:80%;margin:0 auto}
.pd10{padding:20px 0 !important}
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;margin-top:10px;font-size: 10px;border-radius: 5px;background:#ff4c3b}
.panel-head{
width: 100%;
height: 50px;
line-height: 50px;
background: #f2f2f2;
color: #7e7e7e;
position: relative;
}
</style>

</head>
<body>
	<div class="panel admin-panel">
 <div class="panel-head" id="add" style="height:50px;line-height:50px;padding:0 15px;font-size: 18px;font-weight: bold;"><strong><span class="icon-pencil-square-o"></span>新增角色</strong>
			<a class="layui-btn a-btn" href="${ctx}/role/rolemanage.do" style="color:#fff;font-size:14px">返回</a>
  </div>   <div class="body-content">
    <div class="form-x">  
      <div class="form-group">
        <div class="label">
          <label style="font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>角色名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="roleName" id="roleName" data-validate="required:请输入角色名称" maxlength="10" />
          <div class="tips"></div>
        </div>
      </div>
      
   
      <div class="form-group">
        <div class="label">
          <label style="font-size: 14px;">角色描述：</label>
        </div>
        <div class="field">
          <textarea class="input" name="note" style=" height:90px;width:25%;" id="roleDescribe"></textarea>
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
          <div class="label">
            <label style="font-size: 14px;">是否可修改：</label>
          </div>
          <div class="field">
            <select name="idmodify" id="ismodify" class="input w50">
              <option value="1">是</option>
              <option value="0" selected="selected">否</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
    <div class="panel-head" id="add"  style="font-size: 18px;font-weight: bold;"><strong><span class="icon-pencil-square-o" ></span>分配权限</strong></div> 
       <div class="form-group">      
          <table class="table" id="parentMenuList" style="font-size: 14px;">	
          <!--  
				 <tr>
					<td class="pd10 rule_check"> 
			            <div class="parent"> <input type="checkbox" class="rules_row" name="rules[]" />试题管理</div>
			              <ul class="quanxian">
								<li> <input type="checkbox" class="child_row" name="rules[]"/>试题管理</li>
								<li> <input type="checkbox" class="child_row" name="rules[]" />试题管理</li>
								<li> <input type="checkbox" class="child_row" name="rules[]"/>试题管理</li>
							</ul>      
					</td>
				</tr>	
			-->						
         </table>       
        </div>
     
     
    
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button type="button" class="button bg-main icon-check-square-o" onclick="updateAuthority()" style="background-color:#008bca !important;padding:0 18px;height:30px;line-height:30px;"> 提交</button>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/pintuer.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">	
layui.use(
		[ 'form', 'layedit', 'laypage', 'laydate' ],
		function() {
			var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;
		});			
	/* var userId = $("#yjy_userId").val(); */
	//获取数据
$(document).ready(function(){ 
	$.ajax({
		url : '${ctx}/userRole/showHisRole.do',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			/* alert(JSON.stringify(data)); */
			var htm = '';
			htm += getMenuItem(data);
			$('#parentMenuList').html(htm);
			//全选反选
			$('.rules_row').on(
					'change',
					function() {
						$(this).closest('.rule_check').find('.child_row').find(
								'input').prop('checked', this.checked);
					}); 			
			//子选中父选中：存在缺陷即当所有子节点都不被选中时，父节点仍然被选中
			$(".child_row").on('change',function(){
			   //$(this).parent().parent().parent().addClass('aaa');
			   $(this).parent().parent().prev().find('input').prop('checked',true);
			})
			
		}
	//重新遍历
	})
});
	//拼接数据
	function getMenuItem(data) {
		if(data){ 
			var html = "";
			for (var i = 0; i < data.objects.length; i++) {
				html += '<tr><td class="pd10 rule_check">';
				if (data.objects[i].hasAuthority == 1) {		
				    html += '<div class="parent"><input type="checkbox" class="rules_row" name="rules[]" checked="checked" value="'+data.objects[i].menuId+'" />'+data.objects[i].menuName+'</div><ul class="quanxian child_row">';
				} else {
					    html += '<div class="parent"><input type="checkbox" class="rules_row" name="rules[]"  value="'+data.objects[i].menuId+'" />'+data.objects[i].menuName+'</div><ul class="quanxian child_row">';
				}
				var childs = data.objects[i].childs;
				if (childs != null) {
					for (var j = 0; j < childs.length; j++) {
						if (childs[j].hasAuthority == 1) {
							html += '<li> <input type="checkbox" checked="checked" class="child_row" name="rules[]" value="'+childs[j].menuId+'"/>'+childs[j].menuName+'</li>';
						} else {
							html += '<li> <input type="checkbox"  class="child_row" name="rules[]" value="'+childs[j].menuId+'"/>'+childs[j].menuName+'</li>';
	
						}
					}
				}
				html += '</ul></td></tr>';
			}		
		}
		//勾选
		//getcheck(rules);
		return html;
	}

	

	function updateAuthority(){
		 var Checkbox = false;
		 var roleDescribe= $("#roleDescribe").val();
		 var roleName= $("#roleName").val(); 
         var ismodify = $("#ismodify").val();
         if(!roleName){
        	 layer.msg('角色名称不能为空');
        	 return false;
         }
         
		$("input[name='rules[]']").each(function() {
			if (this.checked == true) {
				Checkbox = true;
			}
		});
		if(Checkbox == false){
			layer.msg("请勾选权限菜单!");
			return;
		}
		if (Checkbox) {
			var checkedList = new Array();
			$("input[name='rules[]']:checked").each(function() {
				checkedList.push($(this).val());
			});
			$.ajax({
				url:"${ctx}/role/saveRoleUser.do",
				type:"post",
				dataType : "json",
				data:{'roleName' : roleName,'roleDescribe' : roleDescribe,'menuIds' : checkedList.toString(),'isModify':ismodify},
			    success:function(data){
			    	if(data.code == 0){
			    		layer.msg('分配权限成功');
			    		setTimeout(function(){
				    		window.location.href = "rolemanage.do";
			    		},1000);
			    	}else{
			    		alert(data.msg);
			    		return;
			    	}
			    },
			    error:function(data){
			    	alert('error'+JSON.stringify(data));
			    }
				
			});
			
		 }else{
			
			layer.msg('请勾选权限');
			return;
		}	 
			
}	
/* function test(){	
		$.ajax({
			 url:'${ctx}/role/test.do',
			 type:'post',
			 data:{'roleName':'1','roleDescribe':'2','menuIds':'3'},
			 dataType:'json',
			 timeOut:30000,
			 success:function(data){
				 alert(JSON.stringify(data));
			 },
			 error:function(data){
				 alert(JSON.stringify(data));
			 }
		});
	} */
</script>
</html>