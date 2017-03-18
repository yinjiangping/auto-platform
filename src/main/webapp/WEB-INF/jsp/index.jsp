<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>德佳通信平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="${pageContext.request.contextPath}/image/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />德佳管理平台</h1>
  </div>
  <div class="head-l"> &nbsp;&nbsp;<a class="bg-green" href="#"> </a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
        <h2 ><span class="icon-user"></span>基本功能</h2><ul style="">
        <li><a href="${pageContext.request.contextPath}/qrCode.jsp" target="right"><span class="icon-caret-right"></span>二维码生成</a></li>
    </ul>
</div>
<script type="text/javascript">

$(function(){
    $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);
	  $(this).toggleClass("on");
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
    <li></li><a href="${pageContext.request.contextPath}/login" target=_top><span style="display:block; float:right;color: #999">退出登陆</span></a>
</ul>
<a ></a>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="${pageContext.request.contextPath}/info.jsp" name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>