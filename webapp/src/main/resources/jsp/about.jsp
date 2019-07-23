<%--
  Created by IntelliJ IDEA.
  User: laurent
  Date: 22/07/2019
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About - ${project.description}</title>
</head>
<body>
<%@include file="_include/header.jsp"%>
    <ul>
        <li> Application : ${project.description} </li>
        <li> Version :  ${project.version}</li>
        <li> Date du build : ${build.timestamp}</li>

    </ul>
<%@include file="_include/footer.jsp"%>
</body>
</html>
