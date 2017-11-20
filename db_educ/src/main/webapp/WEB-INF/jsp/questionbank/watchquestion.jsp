<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>创建试题</title>
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<link rel="stylesheet" href=".././css/jquery.page.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css">
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././js/bootstrap.min.js"></script>
</head>
<body>
	<!--单选题开始-->
	<div class="handouts_property" id="radioselect"
		style="margin-left: auto; margin-right: auto;">
		<div style="width: 100%; height: 35px;">
			<div class="handouts_property_select" style="width: 300px;">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="请输入题目"
						style="width: 300px;">
				</div>
			</div>
		</div>
		<div style="width: 100%; height: 60px;">
			<div
				style="width: 100%; height: 30px; display: flex; justify-content: space-between;">
				<span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">A</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
				</span> <span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">B</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
				</span>
			</div>
			<div
				style="width: 100%; height: 30px; display: flex; justify-content: space-between;">
				<span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">C</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
				</span> <span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">D</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
			</div>
		</div>
		<div style="width: 100%; height: 60px; margin-top: 20px;">
			<textarea style="width: 300px; height: 60px; border-radius: 5px;"
				placeholder="释义：正确答案为..."></textarea>
		</div>
		<div style="width: 100%; height: 60px; margin-top: 20px;">
			<label>正确答案</label>
			<div class="handouts_property_select1">
				<select class="">
					<option>A</option>
					<option>B</option>
					<option>C</option>
					<option>D</option>
				</select> <i class="icon-angle-down to_down"></i>
			</div>
			<div class="handouts_property_select1">
				<select class="" name="题目来源">
					<option>清华真题</option>
					<option>北大真题</option>
					<option>上交真题</option>
					<option>浙大真题</option>
				</select> <i class="icon-angle-down to_down"></i>
			</div>
			<div class="handouts_property_select">
				<ul class="uls_showstars">
					<li class="icon-star-empty" id="1" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
				</ul>
			</div>
		</div>
		<div style="width: 100%; height: 60px;">
			<label>题目的唯一id（组卷时可以唯一索引道题目，自动生成）</label>
			<div class="handouts_property_select"
				style="width: 100px; margin-left: 30px;">
				<div class="input-group">
					<input type="text" class="form-control" placeholder=""
						style="width: 100px;">
				</div>
			</div>
		</div>
		<div style="width: 100%; height: 150px;">
			<textarea style="width: 300px; height: 100px; border-radius: 5px;"
				placeholder="标签"></textarea>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">+标签</button>
		</div>
		<div>
			<button class="btn btn-primary" type="submit">完成</button>
		</div>
	</div>
	<!--单选框结束-->
	<!--多选题开始-->
	<div class="handouts_property" id="multiselect">
		<div style="width: 100%; height: 35px;">
			<div class="handouts_property_select" style="width: 300px;">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="请输入题目"
						style="width: 300px;">
				</div>
			</div>
		</div>
		<div style="width: 100%; height: 60px;">
			<div
				style="width: 100%; height: 30px; display: flex; justify-content: space-between;">
				<span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">A</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
				</span> <span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">B</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
				</span>
			</div>
			<div
				style="width: 100%; height: 30px; display: flex; justify-content: space-between;">
				<span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">C</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
				</span> <span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">D</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
			</div>
		</div>
		<div style="width: 100%; height: 60px; margin-top: 20px;">
			<textarea style="width: 300px; height: 60px; border-radius: 5px;"
				placeholder="释义：正确答案为..."></textarea>
		</div>
		<div style="width: 100%; height: 60px; margin-top: 20px;">
			<label>正确答案</label>
			<div class="handouts_property_select" style="width: auto;">
				<img src="checkbox1.png" class="radio1" data-click="0" /><span
					style="margin-left: -50px;">A</span> <img src="checkbox1.png"
					class="radio1" data-click="0" style="margin-left: 5px;" /><span
					style="margin-left: 30px;">B</span>
			</div>
			<div class="handouts_property_select" style="width: auto;">
				<img src="checkbox1.png" class="radio1" data-click="0" /><span
					style="margin-left: -50px;">C</span> <img src="checkbox1.png"
					class="radio1" data-click="0" style="margin-left: 5px;" /><span
					style="margin-left: 30px;">D</span>
			</div>
			<div class="handouts_property_select1">
				<select class="" name="题目来源">
					<option>清华真题</option>
					<option>北大真题</option>
					<option>上交真题</option>
					<option>浙大真题</option>
				</select> <i class="icon-angle-down to_down"></i>
			</div>
			<div class="handouts_property_select">
				<ul class="uls_showstars">
					<li class="icon-star-empty" id="1" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
				</ul>
			</div>
		</div>
		<div style="width: 100%; height: 60px;">
			<label>题目的唯一id（组卷时可以唯一索引道题目，自动生成）</label>
			<div class="handouts_property_select"
				style="width: 100px; margin-left: 30px;">
				<div class="input-group">
					<input type="text" class="form-control" placeholder=""
						style="width: 100px;">
				</div>
			</div>
		</div>
		<div style="width: 100%; height: 150px;">
			<textarea style="width: 300px; height: 100px; border-radius: 5px;"
				placeholder="标签"></textarea>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">+标签</button>
		</div>
		<div>
			<button class="btn btn-primary" type="submit">提交</button>
		</div>
	</div>
	<!--多选题结束-->
	<!--判断题开始-->
	<div class="handouts_property" id="judgeselect">
		<div style="width: 100%; height: 35px;">
			<div class="handouts_property_select" style="width: 300px;">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="请输入题目"
						style="width: 300px;">
				</div>
			</div>
		</div>
		<div style="width: 100%; height: 20px;">
			<div class="handouts_property_select" style="width: auto;">
				<img src="checkbox1.png" class="radio1" data-click="0" /><span
					style="margin-left: -65px;">A</span> <img src="checkbox1.png"
					class="radio1" data-click="0" style="margin-left: 23px;" /><span
					style="margin-left: 44px;">B</span>
			</div>
		</div>
		<div style="width: 100%; height: 60px; margin-top: 20px;">
			<textarea style="width: 300px; height: 60px; border-radius: 5px;"
				placeholder="释义：正确答案为..."></textarea>
		</div>
		<div style="width: 100%; height: 60px; margin-top: 20px;">
			<label>正确答案</label>
			<div class="handouts_property_select" style="width: 80px;">
				<select class="" style="width: 80px;">
					<option>对</option>
					<option>错</option>
				</select> <i class="icon-angle-down to_down"></i>
			</div>
			<div class="handouts_property_select1">
				<select class="" name="题目来源">
					<option>清华真题</option>
					<option>北大真题</option>
					<option>上交真题</option>
					<option>浙大真题</option>
				</select> <i class="icon-angle-down to_down"></i>
			</div>
			<div class="handouts_property_select">
				<ul class="uls_showstars">
					<li class="icon-star-empty" id="1" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
				</ul>
			</div>
		</div>
		<div style="width: 100%; height: 150px;">
			<textarea style="width: 300px; height: 100px; border-radius: 5px;"
				placeholder="标签"></textarea>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">+标签</button>
		</div>
		<div>
			<button class="btn btn-primary" type="submit">提交</button>
		</div>
	</div>
	<!--判断题结束-->
	<!--阅读理解开始-->
	<div class="handouts_property" id="readselect">
		<div style="width: 100%; height: 120px; margin-top: 20px;">
			<textarea style="width: 300px; height: 100px; border-radius: 5px;"
				placeholder="输入课文"></textarea>
			<textarea
				style="width: 300px; height: 100px; border-radius: 5px; margin-left: 20px;"
				placeholder="标签"></textarea>
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">+标签</button>
		</div>
		<div style="width: 100%; height: 35px;">
			<div class="handouts_property_select" style="width: 300px;">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="请输入题目"
						style="width: 300px;">
				</div>
			</div>
		</div>
		<div style="width: 100%; height: 60px;">
			<div
				style="width: 100%; height: 30px; display: flex; justify-content: space-between;">
				<span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">A</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
				</span> <span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">B</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
				</span>
			</div>
			<div
				style="width: 100%; height: 30px; display: flex; justify-content: space-between;">
				<span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">C</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
				</span> <span class="left" style="position: relative;"> <span
					style="position: absolute; left: 10px; top: 5px;">D</span>
					<div class="handouts_property_select"
						style="width: 150px; margin-left: 30px;">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="请输入答案"
								style="width: 150px;">
						</div>
					</div>
			</div>
		</div>
		<div style="width: 100%; height: 60px; margin-top: 20px;">
			<textarea style="width: 300px; height: 60px; border-radius: 5px;"
				placeholder="释义：正确答案为..."></textarea>
		</div>
		<div style="width: 100%; height: 60px; margin-top: 20px;">
			<label>正确答案</label>
			<div class="handouts_property_select1">
				<select class="">
					<option>A</option>
					<option>B</option>
					<option>C</option>
					<option>D</option>
				</select> <i class="icon-angle-down to_down"></i>
			</div>
			<div class="handouts_property_select1">
				<select class="" name="题目来源">
					<option>清华真题</option>
					<option>北大真题</option>
					<option>上交真题</option>
					<option>浙大真题</option>
				</select> <i class="icon-angle-down to_down"></i>
			</div>
			<div class="handouts_property_select">
				<ul class="uls_showstars">
					<li class="icon-star-empty" id="1" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
					<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
				</ul>
			</div>
		</div>
		<div>
			<button class="btn btn-primary" type="submit">提交</button>
		</div>
	</div>
	<!--阅读理解结束-->
	<!--模态框-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 40%;">
			<div class="modal-content">
				<div class="modal-body">
					<div class=""
						style="width: 300px; height: 50px; margin-left: 30px;">
						<div class="input-group" style="float: left;">
							<input type="text" class="form-control" placeholder="hahaha"
								style="width: 200px;">
						</div>
						<button type="button" class="btn btn-success"
							style="float: right;">确定</button>
					</div>
					<div class="handouts_property" style="margin-top: 0px;">
						<div style="width: 100%; height: 30px;">
							<div class="input-group" style="float: left;">
								<input type="text" class="form-control"
									placeholder="短语：three for"
									style="width: 100px; border-radius: 5px;">
							</div>
						</div>
						<div style="width: 100%; height: 30px;">
							<div class="input-group" style="float: left;">
								<input type="text" class="form-control" placeholder="教材：新概念"
									style="width: 100px; border-radius: 5px;">
							</div>
						</div>
						<div style="width: 100%; height: 30px;">
							<div class="input-group" style="float: left;">
								<input type="text" class="form-control"
									placeholder="短语：three for"
									style="width: 100px; border-radius: 5px;">
							</div>
						</div>
					</div>
					<div class="handouts_property"
						style="width: 100%; margin-left: 0px; border: 1px solid #EEEEEE; padding-top: 10px;">
						<label>创建新标签</label>
						<div class=""
							style="width: 300px; height: 50px; margin-left: 30px;">
							<div class="input-group" style="float: left;">
								<input type="text" class="form-control" placeholder="hahaha"
									style="width: 100px;">
							</div>
							<button type="button" class="btn btn-success"
								style="float: right;">确定</button>
						</div>
					</div>
					<!--<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
							</button>
					<button type="button" class="btn btn-primary">
							提交更改
							</button>
				</div>-->
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
</body>
<script type="text/javascript">
	$(function() {
		$('#radioselect').css('display', 'block');
	});
	$(".radio1").on("click", function(e) {
		if (e.currentTarget.dataset.click == 0) {
			$(this).attr('src', '.././css/images/checkbox_on.png');
			e.currentTarget.dataset.click = 1;
		} else {
			$(this).attr('src', '.././css/images/checkbox.png');
			e.currentTarget.dataset.click = 0;
		}

	});
	$('.uls_showstars li').on('click', function() {
		var id = $(this).attr('id');
		for (var i = 1; i <= id; i++) {
			$("#" + i).removeClass().addClass('icon-star');
			for (var j = 5; j > id; j--) {
				$("#" + j).removeClass().addClass('icon-star-empty');
			}
		}
	})
	//保存成功的提示信息
	function showResult() {
		$('#submitForm').submit();
		return;
	}
</script>
</html>