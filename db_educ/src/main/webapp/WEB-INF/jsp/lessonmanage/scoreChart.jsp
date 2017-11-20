<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>学生成绩图表</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/echarts.min.js"></script>
<style type="text/css">
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;top: 20%;font-size: 10px;background:#e33}
.subject{padding-bottom:10px;}
</style>
</head>
<body>
	<form class="layui-form" action="">
	<div class="panels_head">
			<span>试卷分析</span>
			<a class="layui-btn a-btn" href="${ctx}/lesson/studentmanage.do?studentId=${tr.studentId}&grade=${grade}&classId=${classId}&subject=${subject}">返回</a>
		</div>
		<div class="panle">
			<div style="width: 60%; margin: 0 auto;; padding: 30px 0 50px 0">
				<div class="subject">
					${tr.paperInfo.piName}：${tr.subject.subjectName}&nbsp;&nbsp;${tr.score}分
				</div>
				<div class="zhpj">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;综合评定:本试卷一共考了${knowledgeTotal} 个知识点，分别是
					<c:forEach items="${choice}" var="tag">
				${tag.tagName}、
				</c:forEach>
					。其中 掌握了${isTrueTotal}个知识点，分别是：
					<c:forEach items="${trueList}" var="tag">
				${tag.tagName}、
				</c:forEach>
					。 欠缺的知识点主要有${errorTotal}个。
					<c:if test="${errorTotal > 0}">分别是
					<c:forEach items="${errorList}" var="tag">
				   ${tag.tagName}、
				</c:forEach>
					</c:if>
						<br/>
						<p style="margin-top:10px;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此次考试成绩为 ${tr.score}分，总分：${pInfo.totalScore}分，单科排名为第${record.scoreRank}名。总共用时：
						${tr.totalTime}分钟，其中单选题得：${danxuan1}分, 多选题得：${duoxuan1}分,
					判断题得：${pduan1}分,阅读题得：${yuedu1}分,填空题得：${fill1}分。
					</p>
				</div>
			</div>
		</div>
		<div id="main" style="width: 600px;height:400px;margin:0 auto;"></div>	
	   
	   <div class="shanxing" style="padding:50px 0">
	       <div id="zhishidian"  style="width: 600px;height:400px;margin:0 auto;"></div>
	   </div>	
	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">

	layui.use([ 'form', 'laydate' ], function() {
		var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
	});
// 		alert("${tagName}");
// 		alert("${tagName1}");
//折柱混合
(function(){
	 //获取考试分数详情
	 $.ajax({
		 url:'',
		 type:'post',
		 dataType:'json',
		 data:{},
		 success:function(data){
		 },
		 error:function(data){
		 }
	 })
		
	var columnar4 = echarts.init(document.getElementById("main"));
	option = {
		
		title : {
			text: "考试分析",
			x: "left"
		},
		
	    tooltip: {
	        trigger: 'axis'
	    },
	    toolbox: {
	        feature: {
	            saveAsImage: {show: true}
	        }
	    },
	    legend: {
	        data:['模块总分','我的得分']
	    },
	    xAxis: [
	        {
	            type: 'category',
	            data: ['单选题','多选题','判断题','阅读理解','填空题']
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: '分值',
	            min: 0,
	            max: 100,
	            interval: 10,
	            axisLabel: {
	                formatter: '{value}'
	            }
	        }
	        /* {
	            type: 'value',
	            name: '温度',
	            min: 0,
	            max: 25,
	            interval: 5,
	            axisLabel: {
	                formatter: '{value} °C'
	            }
	        } */
	    ],
	    series: [
	        {
	            name:'模块总分',
	            type:'bar',
	            data:[${scores1}]
	        },
	        {
	            name:'我的得分',
	            type:'bar',
	            data:[${scores}]
	        }
	       /*  {
	            name:'平均温度',
	            type:'line',
	            yAxisIndex: 1,
	            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
	        } */
	    ]
	};
	columnar4.setOption(option);
// 	//扇形图：
// 	var weatherIcons = {
//     'Sunny': './data/asset/img/weather/sunny_128.png',
//     'Cloudy': './data/asset/img/weather/cloudy_128.png',
//     'Showers': './data/asset/img/weather/showers_128.png'
// };
// var columnar5 = echarts.init(document.getElementById("zhishidian"));
//    option1 = {
		
// 		title : {
// 			text: "知识点掌握情况",
// 			x: "left"
// 		},
		
// 	    tooltip: {
// 	        trigger: 'axis'
// 	    },
// 	    toolbox: {
// 	        feature: {
// 	            saveAsImage: {show: true}
// 	        }
// 	    },
// 	    legend: {
// 	        data:['模块总数','答对总数']
// 	    },
// 	    xAxis: [
// 	        {
// 	            type: 'category',
// 	            data: [${tagName}]
// 	        }
// 	    ],
// 	    yAxis: [
// 	        {
// 	            type: 'value',
// 	            name: '数量',
// 	            min: 0,
// 	            max: 100,
// 	            interval: 10,
// 	            axisLabel: {
// 	                formatter: '{value}'
// 	            }
// 	        }
	       
// 	    ],
// 	    series: [
// 	        {
// 	            name:'模块总数',
// 	            type:'bar',
// 	            data:[${knowledgeTotal}]
// 	        },
// 	        {
// 	            name:'答对总数',
// 	            type:'bar',
// 	            data:[${isTrueTotal}]
// 	        }
// 	    ]
// 	};
// option1 = {
//     title: {
//         text: '知识点掌握情况',
//         subtext: '',
//         left: 'center'
//     },
//     tooltip : {
//         trigger: 'item',
//         formatter: "{a} <br/>{b} : {c} ({d}%)"
//     },
//     legend: {
//         // orient: 'vertical',
//         // top: 'middle',
//         bottom: 10,
//         left: 'center',
//         data: ['古诗词填空', '造句','阅读理解','拼音','作文']
//     },
//     series : [
//         {
//             type: 'pie',
//             radius : '65%',
//             center: ['50%', '50%'],
//             selectedMode: 'single',
//             data:[
//                 {
//                     value:3,
//                     name: '古诗词填空',
//                     label: {
//                         normal: {
//                             /* formatter: [
//                                 '{title|{b}}{abg|}',
//                                 '  {weatherHead|天气}{valueHead|天数}{rateHead|占比}',
//                                 '{hr|}',
//                                 '  {Sunny|}{value|202}{rate|55.3%}',
//                                 '  {Cloudy|}{value|142}{rate|38.9%}',
//                                 '  {Showers|}{value|21}{rate|5.8%}'
//                             ].join('\n'), */
//                             backgroundColor: '#eee',
//                             borderColor: '#777',
//                             borderWidth: 1,
//                             borderRadius: 4,
//                             rich: {
//                                 title: {
//                                     color: '#eee',
//                                     align: 'center'
//                                 },
//                                 abg: {
//                                     backgroundColor: '#333',
//                                     width: '100%',
//                                     align: 'right',
//                                     height: 25,
//                                     borderRadius: [4, 4, 0, 0]
//                                 },
//                                 Sunny: {
//                                     height: 30,
//                                     align: 'left',
//                                     backgroundColor: {
//                                         image: weatherIcons.Sunny
//                                     }
//                                 },
//                                 Cloudy: {
//                                     height: 30,
//                                     align: 'left',
//                                     backgroundColor: {
//                                         image: weatherIcons.Cloudy
//                                     }
//                                 },
//                                 Showers: {
//                                     height: 30,
//                                     align: 'left',
//                                     backgroundColor: {
//                                         image: weatherIcons.Showers
//                                     }
//                                 },
//                                 weatherHead: {
//                                     color: '#333',
//                                     height: 24,
//                                     align: 'left'
//                                 },
//                                 hr: {
//                                     borderColor: '#777',
//                                     width: '100%',
//                                     borderWidth: 0.5,
//                                     height: 0
//                                 },
//                                 value: {
//                                     width: 20,
//                                     padding: [0, 20, 0, 30],
//                                     align: 'left'
//                                 },
//                                 valueHead: {
//                                     color: '#333',
//                                     width: 20,
//                                     padding: [0, 20, 0, 30],
//                                     align: 'center'
//                                 },
//                                 rate: {
//                                     width: 40,
//                                     align: 'right',
//                                     padding: [0, 10, 0, 0]
//                                 },
//                                 rateHead: {
//                                     color: '#333',
//                                     width: 40,
//                                     align: 'center',
//                                     padding: [0, 10, 0, 0]
//                                 }
//                             }
//                         }
//                     }
//                 },
//                 {value:2, name: '造句'},
//                 {value:3, name: '阅读理解'},
//                 {value:4, name: '拼音'},
//                 {value:5, name: '作文'}
//             ],
//             itemStyle: {
//                 emphasis: {
//                     shadowBlur: 10,
//                     shadowOffsetX: 0,
//                     shadowColor: 'rgba(0, 0, 0, 0.5)'
//                 }
//             }
//         }
//     ]
// };
//columnar5.setOption(option1);

	
	})();
	
	
	
</script>
</html>