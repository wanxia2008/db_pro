<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="ctl00_Head1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>预览试卷</title>
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="renderer" content="webkit|ie-comp|ie-stand" />
<link rel="stylesheet" href=".././css/paper/NewDefault.css" />
<link rel="stylesheet" href=".././css/paper/q.css" />
<link rel="stylesheet" href=".././css/paper/newsolid_38.css" />
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<style>
.surveyhead h1 {
	font-size: 18px !important;
	font-weight: blod;
	color: #333
}

label {
	font-size: 14px;
	font-weight: normal
}

label p {
	display: inline !important
}

.div_title_question_all {
	font-size: 16px !important;
	font-weight: normal
}

.qtypetip {
	color: #008bca !important
}

.bdnone {
	border-bottom: none
}

.div_title_question p {
	display: inline !important
}
/*  .div_title_question p img {
    display: block;
} */
.div_table_radio_question li img {
	display: inline !important
}
</style>
</head>
<body style="background-color: #f2f2f2">
	<div class="panels_head"
		style="position: fixed; z-index: 999999 !important">
<!-- 		<span>预览试卷</span> -->
		<button type="button" class="layui-btn layui-btn-danger"
			onclick="history.go(-1)"
			style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">返回</button>
	</div>
	<div id="divNotRun"
		style="height: 100px; text-align: center; display: none;"></div>
	<div id="jqContent" class="" style="text-align: left;">
		<div id="headerCss" style="overflow-x: hidden; overflow-y: hidden;">
			<div id="ctl00_header"></div>
		</div>
		<div id="mainCss">
			<div id="mainInner">
				<div id="box">
					<div class="survey" style="margin: 0px auto;">
						<div id="ctl00_ContentPlaceHolder1_JQ1_divHead" class="surveyhead"
							style="border: 0px;">
							<h1 id="ctl00_ContentPlaceHolder1_JQ1_h1Name"
								style="position: relative;">
								<span id="ctl00_ContentPlaceHolder1_JQ1_lblQuestionnaireName">${info.piName}
								</span>
							</h1>
							<!--         <h1 id="ctl00_ContentPlaceHolder1_JQ1_h1Name" style="position:relative;">  -->
							<!--         <span id="ctl00_ContentPlaceHolder1_JQ1_lblQuestionnaireName">  -->

							<!--         </span></h1>  -->
							<div id="pater_desc" class="surveydescription"
								style="cursor: pointer;">
								考试时长：${info.paperTime}分钟，满分&nbsp;${info.totalScore}分</div>
						</div>
						<div id="ctl00_ContentPlaceHolder1_JQ1_question"
							class="surveycontent">
							<div id="divMaxTime"
								style="text-align: center; display: none; width: 80px; background: white; position: fixed; top: 135px; border: 1px solid #dbdbdb; padding: 8px; z-index: 10;">
								<div id="spanTimeTip"
									style="border-bottom: 1px solid #dbdbdb; height: 30px; line-height: 30px;">
									本页时间剩余</div>
								<div
									style="color: Red; font-size: 16px; height: 30px; line-height: 30px;"
									id="spanMaxTime"></div>
							</div>
							<div id="ctl00_ContentPlaceHolder1_JQ1_surveyContent">
								<fieldset class="fieldset" id="fieldset1">
									<c:if test="${spList != null && fn:length(spList) >0}">
										<!--                <span style="font-size:16px;font-weight:bold;"> </span> <br /> -->
										<c:forEach items="${spList}" var="single" varStatus="list">
											<c:if test="${single.questiontype ==1}">
												<div class="div_question" id="div3">
													<div class="div_title_question_all">
														<div id="divTitle3" class="div_title_question">
															${list.index+1}、${single.choiceDesc}【单选】 <span
																class="req">&nbsp;*</span> <span class="qtypetip">&nbsp;</span>
														</div>
														<div style="clear: both;"></div>
													</div>
													<div class="div_table_radio_question" id="divquestion3">
														<div class="div_table_clear_top"></div>
														<ul class="ulradiocheck">
															<li style="width: 99%;"><label for="q3_1">A、${single.optionA}</label></li>
															<li style="width: 99%;"><label for="q3_2">B、${single.optionB}</label></li>
															<li style="width: 99%;"><label for="q3_3">C、${single.optionC}</label></li>
															<li style="width: 99%;"><label for="q3_4">D、${single.optionD}</label></li>
															<div style="clear: both;">正确答案：${single.answer}，提交答案：${single.writeAnswer}。
															<br /><br />
															答案解析：
															<c:if test="${single.answerDesc != null}">
															   ${single.answerDesc}。
															</c:if>
															<c:if test="${single.answerDesc == null}">
															   略。
															</c:if>
															</div>
														</ul>
														<div style="clear: both;"></div>
														<div class="div_table_clear_bottom"></div>
													</div>
													<div class="errorMessage"></div>
												</div>
											</c:if>
											<c:if test="${single.questiontype ==2}">
												<div class="div_question" id="div4">
													<div class="div_title_question_all">
														<div id="divTitle4" class="div_title_question">
															${list.index+1}、${single.choiceDesc1}【多选】 <span
																class="req">&nbsp;*</span> <span class="qtypetip">&nbsp;</span>
														</div>
														<div style="clear: both;"></div>
													</div>
													<div class="div_table_radio_question" id="divquestion4">
														<div class="div_table_clear_top"></div>
														<ul class="ulradiocheck">
															<li style="width: 99%;"><label>A、${single.optionA1}</label></li>
															<li style="width: 99%;"><label>B、${single.optionB1}</label></li>
															<li style="width: 99%;"><label>C、${single.optionC1}</label></li>
															<li style="width: 99%;"><label>D、${single.optionD1}</label></li>
															<div style="clear: both;">正确答案：${single.answer1}，提交答案：${single.writeAnswer}。
															<br />答案解析：<c:if test="${single.answerDesc1 != null}">
															   ${single.answerDesc1}。
															</c:if>
															<c:if test="${single.answerDesc1 == null}">
															   略。
															</c:if>
															</div>
														</ul>
														<div style="clear: both;"></div>
														<div class="div_table_clear_bottom"></div>
													</div>
													<div class="errorMessage"></div>
												</div>
											</c:if>
											<c:if test="${single.questiontype ==3}">
												<div class="div_question" id="div2">
													<div class="div_title_question_all">
														<div id="divTitle2" class="div_title_question">
															${list.index+1}、${single.judgeDesc} <span class="req">&nbsp;*</span>
															<span class="qtypetip">&nbsp;</span>
														</div>
														<div style="clear: both;"></div>
													</div>
													<div class="div_table_radio_question" id="divquestion2">
														<div class="div_table_clear_top"></div>
														<ul class="ulradiocheck">
															<li style="width: 99%;"><label for="q2_1">A.正确</label></li>
															<li style="width: 99%;"><label for="q2_2">B.错误</label></li>
															<div style="clear: both;">正确答案：
															 <c:if test="${single.answer2 ==1}">
															 A
															    </c:if>
															     <c:if test="${single.answer2 ==0}">
															B
														</c:if>，提交答案：
														 <c:if test="${single.writeAnswer ==1}">
															A
															    </c:if>
															     <c:if test="${single.writeAnswer ==0}">
															B
														</c:if>。
														答案解析：<c:if test="${single.answerDesc2 != null}">
															   ${single.answerDesc2}。
															</c:if>
															<c:if test="${single.answerDesc2 == null}">
															   略。
															</c:if>
															</div>
														</ul>
														<div style="clear: both;"></div>
														<div class="div_table_clear_bottom"></div>
													</div>
													<div class="errorMessage"></div>
												</div>
											</c:if>
											<c:if test="${single.questiontype ==4}">
												<div class="div_question" id="div4"
													style="border-bottom: 1px solid #EFEFEF">
													<div style="font-size: 16px; color: #333">${single.readDesc}</div>
													<div class="div_title_question_all">
														<div id="divTitle4" class="div_title_question">
															${list.index+1}、${single.getOptiontitle()}【阅读理解】 <span
																class="req">&nbsp;*</span> <span class="qtypetip">&nbsp;</span>
														</div>
														<div style="clear: both;"></div>
													</div>

													<div class="div_table_radio_question bdnone"
														id="divquestion4">
														<div class="div_table_clear_top"></div>
														<ul class="ulradiocheck">
															<li style="width: 99%;"><label>A、${single.getOptionA_read()}</label></li>
															<li style="width: 99%;"><label>B、${single.getOptionB_read()}</label></li>
															<li style="width: 99%;"><label>C、${single.getOptionC_read()}</label></li>
															<li style="width: 99%;"><label>D、${single.getOptionD_read()}</label></li>
															<div style="clear: both;">正确答案：${single.answer_read}，提交答案：${single.writeAnswer}
															<br />答案解析：<c:if test="${single.answerDesc_read != null}">
															   ${single.answerDesc_read}。
															</c:if>
															<c:if test="${single.answerDesc_read == null}">
															   略。
															</c:if>
															</div>
														</ul>
														<div style="clear: both;"></div>
														<div class="div_table_clear_bottom"></div>
													</div>
												</div>
											</c:if>
											<c:if test="${single.questiontype ==5}">
												<div class="div_question" id="div2">
													<div class="div_title_question_all">
														<div id="divTitle2" class="div_title_question">
															${list.index+1}、${single.subjectiveTitle}【主观题】 <span
																class="req">&nbsp;*</span> <span class="qtypetip">&nbsp;</span>
														</div>
														<div style="clear: both;"></div>
													</div>
													<div class="div_table_radio_question" id="divquestion2">
														<div class="div_table_clear_top"></div>
														<ul class="ulradiocheck">
															<div style="clear: both;"></div>
														</ul>
														<div style="clear: both;"></div>
														<div class="div_table_clear_bottom"></div>
													</div>
													<div class="errorMessage"></div>
												</div>
											</c:if>

											<c:if test="${single.questiontype ==6}">
												<div class="div_question" id="div2">
													<div class="div_title_question_all">
														<div id="divTitle2" class="div_title_question">
															${list.index+1}、${single.fillTitle}【填空】 <span class="req">&nbsp;*</span>
															<span class="qtypetip">&nbsp;</span>
														</div>
														<div style="clear: both;"></div>
													</div>
													<div class="div_table_radio_question" id="divquestion2">
														<div class="div_table_clear_top"></div>
														<ul class="ulradiocheck">
															<li style="width: 99%;"><label for="q2_1">${single.fillAnswer}</label></li>
															<!-- <li style="width: 99%;"><label for="q2_2">B.错误</label></li> -->
															<div style="clear: both;">
															正确答案：${single.fillAnswer}，提交答案：${single.writeAnswer}。
															答案解析：<c:if test="${single.answerAnalysis != null}">
															   ${single.answerAnalysis}。
															</c:if>
															<c:if test="${single.answerAnalysis == null}">
															   略。
															</c:if>
															</div>
														</ul>
														<div style="clear: both;"></div>
														<div class="div_table_clear_bottom"></div>
													</div>
													<div class="errorMessage"></div>
												</div>
											</c:if>
											<c:if test="${single.questiontype ==7}">
												<div class="div_question" id="div2">
													<div class="div_title_question_all">
														<div id="divTitle2" class="div_title_question">
															${list.index+1}、${single.clozeContent}【完形填空】 <span
																class="req">&nbsp;*</span> <span class="qtypetip">&nbsp;</span>
														</div>
														<div style="clear: both;"></div>
													</div>
													<div class="div_table_radio_question" id="divquestion2">
														<div class="div_table_clear_top"></div>
														<ul class="ulradiocheck">
															<div style="clear: both;"></div>
														</ul>
														<div style="clear: both;"></div>
														<div class="div_table_clear_bottom"></div>
													</div>
													<div class="errorMessage"></div>
												</div>
											</c:if>
										</c:forEach>
									</c:if>
								</fieldset>
							</div>
							<div style="padding-top: 6px; clear: both; padding-bottom: 10px;"
								id="submit_div">
								<table id="submit_table" style="margin: 20px auto;">
									<tbody>
										<tr>
											<!--<td> <input type="button" class="submitbutton" value="提交" onmouseout="this.className='submitbutton';" id="submit_button" style="padding: 0 24px; height: 32px;" /> </td>  -->
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>