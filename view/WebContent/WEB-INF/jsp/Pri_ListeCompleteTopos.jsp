<%--
  Created by IntelliJ IDEA.
  User: cordier
  Date: 31/08/2019
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>ok liste topo</title>
</head>

<body>
<table>
    <tr>
        <th> idTopo </th>
        <th> dateCreation </th>
        <th> est_publie </th>
        <th> etat_reservation </th>
        <th> grimpeur_idgrimpeur </th>
        <th> nom </th>
        <th> lieu </th>
        <th> resume </th>
    </tr>
    <c:forEach var="dbTopo"  items="${requestScope.dbTopos}">

        <tr>
            <td>${dbTopo.getIdtopo()}</td>
            <td>${dbTopo.getDateDeParution()}</td>
            <td>${dbTopo.getEstPublie()}</td>
            <td>${dbTopo.getEtatReservation()}</td>
            <td>${dbTopo.getGrimpeurByGrimpeurIdgrimpeur(). getIdgrimpeur()}</td>
            <td>${dbTopo.getNom()}</td>
            <td>${dbTopo.getLieu()}</td>
            <td>${dbTopo.getResume()}</td>
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