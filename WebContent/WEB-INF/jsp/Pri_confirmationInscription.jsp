<%@ page contentType="text/html;charset=ISO-8859-15" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>Inscription confirmée</title>
</head>
<body>
<br/>
<br/>

<h3>
   Inscription confirmée
</h3>

<br/>
<br/>

<table>
    <tr>
        <th> idGrimpeur </th>
        <th> nomGrimpeur </th>
        <th> mdpGrimpeur </th>
        <th> roleGrimpeur </th>
    </tr>
    <c:forEach var="dbGrimpeur"  items="${requestScope.dbGrimpeurs}">

        <tr>
            <td>${dbGrimpeur.getIdgrimpeur()}</td>
            <td>${dbGrimpeur.getUserName()}</td>
            <td>${dbGrimpeur.getUserPass()}</td>
            <td>${dbGrimpeur.getRoleName()}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<br/>

<footer >
    Retour Acceuil  <a href="<%=request.getContextPath()%>/index.jsp">Cliquez ici</a>
</footer>


</body>
</html>
