<%@ page contentType="text/html;charset=ISO-8859-15" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>Connexion réussie</title>
</head>
<body>

<h3>
    Connexion réussie
</h3>

<br/>
<br/>

<table>
    <thead>
        <tr>
            <th> idGrimpeur </th>
            <th> nomGrimpeur </th>
            <th> mdpGrimpeur </th>
            <th> roleGrimpeur </th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="dbGrimpeur"  items="${requestScope.dbGrimpeurs}">
            <tr>
                <td>${dbGrimpeur.getIdGrimpeur()}</td>
                <td>${dbGrimpeur.getUserName()}</td>
                <td>${dbGrimpeur.getUserPass()}</td>
                <td>${dbGrimpeur.getRoleName()}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<br/>
<br/>

<footer >
    Retour Acceuil  <a href="<%=request.getContextPath()%>/index.jsp">Cliquez ici</a>
</footer>



</body>
</html>
