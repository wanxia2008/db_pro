//文档上传
var ajaxUp1 = function(id, url, func1, func2) {
	var formData = new FormData();
	formData.append('file', $(id)[0].files[0]);
	$.ajax({
		url : url,
		type : 'POST',
		cache : false,
		data : formData,
		dataType : 'json',
		processData : false,
		contentType : false,
		cache : false,
		async : true,
		timeout : 100000,
		beforeSend : function() {
			console.log('加载中');
		},
		success : function(res) {
			console.log(res);
			func1(res);
		},
		error : function(res) {
//			alert(JSON.stringify(res));
			console.log('网络错误');
			func2();
		}
	});
	return false;
}
var ck = function(id, func, tp, ise) {
	var p;
	switch (tp) {
	case 1:
		p = 'change';
		break;
	case 2:
		p = 'keyup';
		break;
	default:
		p = 'click';
		break;
	}
	$(id).unbind(p).bind(p, function(e) {
		if (ise) {
			e.preventDefault();
		}
		func($(this), e);
	});
}
var f2Back = function(res, func1, func2) {
	if (res.res == 1) {
		func1(res);
	} else {
		func2(res);
	}
}
/*
 *Word文档上传
 */
var upRouteWord1 = function(fileid, tarid, func) {

	ck(fileid, function(t) {
		layer.load(0, {
			  shade: [0.8,'#000'] //0.1透明度的白色背景
			});
		ajaxUp1(fileid, '../wordUpload/uploadpdf.do', function(data) {
			layer.closeAll();
			func(data);
			
		}, function(data) {
			layer.closeAll();
			layer.msg('上传文件失败');
//			alert('999'+JSON.stringify(data))
			//console.log(data);
		});
	}, 1);
}

upRouteWord1('#upRoutePpt', '#routePPt', function(data) {
	if(data.code == 0){
		$("#wordpdf").hide();
		$('#openpdf1').html('<span type="button" class="layui-btn layui-btn-normal" onclick="openpdf1()">预览</span>');
		$('#routePPt').html('<span style="color:green">上传成功</span>');
		$('#insertPpt').val(data.msg);
	}else{
		layer.msg("上传文件失败");
	}	
});
