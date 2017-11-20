<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>班级成员管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
.layui-input, .layui-select, .layui-textarea {
	height: 25px;
	line-height: 25px;
}

.layui-form-item .layui-inline {
	
}

.layui-form-select dl {
	top: 25px;
}
</style>
</head>
<body>
	<form class="layui-form" action="">
		<table class="layui-table"
			style="width: 60%; margin-left: auto; margin-right: auto; font-size: 10px;">
			<colgroup>
				<col width="100" />
				<col width="100" />
				<col width="80" />
				<col width="80" />
				<col width="100" />
				<col width="100" />
				<col>
			</colgroup>
			<thead>
				<tr>
					<th>学员姓名</th>
					<th>学员id</th>
					<th>性别</th>
					<th>年龄</th>
					<th>入学成绩</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>小明</td>
					<td>1001</td>
					<td>男</td>
					<td>10</td>
					<td>100</td>
					<td>
						<p>修改</p>
						<p>删除</p>
					</td>
				</tr>
				<tr>
					<td>小明</td>
					<td>1001</td>
					<td>男</td>
					<td>10</td>
					<td>100</td>
					<td>
						<p>修改</p>
						<p>删除</p>
					</td>
				</tr>
				<tr>
					<td>小明</td>
					<td>1001</td>
					<td>男</td>
					<td>10</td>
					<td>100</td>
					<td>
						<p>修改</p>
						<p>删除</p>
					</td>
				</tr>
				<tr>
					<td>小明</td>
					<td>1001</td>
					<td>男</td>
					<td>10</td>
					<td>100</td>
					<td>
						<p>修改</p>
						<p>删除</p>
					</td>
				</tr>
				<tr>
					<td>小明</td>
					<td>1001</td>
					<td>男</td>
					<td>10</td>
					<td>100</td>
					<td>
						<p>修改</p>
						<p>删除</p>
					</td>
				</tr>
			</tbody>
		</table>
		<div id="pages" style="text-align: center;"></div>
	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui
			.use(
					[ 'form', 'layedit', 'laypage', 'laydate' ],
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
							layer.alert(JSON.stringify(data.field), {
								title : '最终的提交信息'
							})
							return false;
						});
						laypage({
							cont : 'pages',
							pages : 20,
							skip : true
						});
					});
</script>
</html>