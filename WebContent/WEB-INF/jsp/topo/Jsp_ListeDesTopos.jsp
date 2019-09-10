<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <%@include file="/WEB-INF/fragments/Html_EntetePourHtml.html" %>
</head>

<body>

<table>
    <thead>
    <tr>
        <th> idTopo</th>
        <th> dateCreation</th>
        <th> est_publie</th>
        <th> etat_reservation</th>
        <th> grimpeur_idgrimpeur</th>
        <th> nom</th>
        <th> lieu</th>
        <th> resume</th>
    </tr>
    </thead>
    <tbody>
    <form id="navTopo" action="VoirDetailTopo" method="post">
        <fieldset>
            <c:forEach var="dbTopo" items="${requestScope.dbTopos}">
                <tr>
                    <td><input type="radio" name="idValTopo" value="${dbTopo.getIdtopo()}"></td>
                    <td>${dbTopo.getNom()}</td>
                    <td>${dbTopo.getLieu()}</td>
                    <td>${dbTopo.getResume()}</td>
                    <td>${dbTopo.getDateDeParution()}</td>
                </tr>
            </c:forEach>
        </fieldset>
        <fieldset>
            <input type="submit" id="submitTopo" value="voir Détail"/>
        </fieldset>
    </form>
    </tbody>
</table>
<footer>
    <%@include file="/WEB-INF/fragments/Html_FooterPourHtml.html" %>
</footer>

</body>
</html>