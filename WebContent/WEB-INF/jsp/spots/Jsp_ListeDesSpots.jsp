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
        <th> IdDuSpot   </th>
        <th> IdOwner    </th>
        <th> NomduSpot  </th>
        <th> TagDuSpot  </th>
        <th> LieuDuSpot </th>
    </tr>

    </thead>
    <tbody>
    <c:forEach var="dbSpot"  items="${requestScope.dbSpots}">
        <tr>
            <td>${dbSpot.getIdspot()}</td>
            <td>${dbSpot.getGrimpeurByGrimpeurIdgrimpeur().getIdgrimpeur()}</td>
            <td>${dbSpot.getNom()}</td>
            <td>${dbSpot.getClassification()}</td>
            <td>${dbSpot.getLocalisation()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<footer>
    <%@include file="/WEB-INF/fragments/Html_FooterPourHtml.html" %>
</footer>


</body>
</html>
