<%--
  Created by IntelliJ IDEA.
  User: jumping
  Date: 2017/3/14 0014
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>德佳通信平台</title>
</head>
<body>
<SCRIPT language="JavaScript">
    alert("用户已在其他地方登陆，请重新登录。");
    setTimeout(function () {
        window.top.location.href="${pageContext.request.contextPath}/login";
    },2000);
</script>
</body>

</html>
