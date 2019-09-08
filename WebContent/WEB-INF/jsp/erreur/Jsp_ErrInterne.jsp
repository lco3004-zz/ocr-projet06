<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <%@include file="/WEB-INF/fragments/Html_EntetePourHtml.html" %>
</head>
<body>
    <br/>
    <br/>
        <h3>Oups Erreur Interne :</h3>
    <br/>
    <br/>
    <p>
         ${requestScope.LocalizedMessage}
    </p>
    <p>
        ${requestScope.StackTrace}
    </p>

    <p>
        Retour Acceuil <a href="${pageContext.request.contextPath}/home"> <b> Cliquez ici </b> </a>

    </p>

</body>
</html>
