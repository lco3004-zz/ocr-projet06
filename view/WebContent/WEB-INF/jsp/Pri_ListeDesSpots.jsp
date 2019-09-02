<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cordier
  Date: 02/09/2019
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des SPots du Site les amis de l'escalade</title>
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
        <th> Nb.Comments</th>
        <th> Nb.Secteurs</th>
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
            <td>
                <c:if test=" ${dbSpot.getCommentairesByIdspot() != null}">
                    ${dbSpot.getCommentairesByIdspot().size()}
                </c:if>
            </td>
            <td>
                <c:choose>
                    <c:when test="${dbSpot.getSecteursByIdspot().size() != null}">
                        ${dbSpot.getSecteursByIdspot().size()}
                    </c:when>
                    <c:otherwise>
                        0
                    </c:otherwise>
                </c:choose>
            </td>
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
