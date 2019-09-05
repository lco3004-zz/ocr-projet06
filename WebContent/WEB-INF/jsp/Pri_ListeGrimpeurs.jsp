<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>Liste des Grimepeurs</title>
</head>
<body>

<h3>
    Liste des Grimpeurs
</h3>

<br/>
<br/>

<table>
    <tr>
        <th> idGrimpeur</th>
        <th> nomGrimpeur</th>
        <th> mdpGrimpeur</th>
        <th> roleGrimpeur</th>
        <th> estConnecté ?</th>
    </tr>
    <c:forEach var="dbGrimpeur" items="${requestScope.dbGrimpeurs}">
        <c:set var="boolCon" scope="request" value="non"></c:set>
        <tr>
            <td>${dbGrimpeur.getIdgrimpeur()}</td>
            <td>${dbGrimpeur.getUserName()}</td>
            <td>${dbGrimpeur.getUserPass()}</td>
            <td>${dbGrimpeur.getRoleName()}</td>
            <c:if test="${dbGrimpeur.getUserName() == sessionScope.dbGrimpeur.getUserName()}">
                <c:set var="boolCon" scope="request" value="OUI"></c:set>
            </c:if>
            <td> ${boolCon}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<br/>

<footer>
    Retour Acceuil <a href="<%=request.getContextPath()%>/index.jsp">Cliquez ici</a>
</footer>

</body>
</html>
