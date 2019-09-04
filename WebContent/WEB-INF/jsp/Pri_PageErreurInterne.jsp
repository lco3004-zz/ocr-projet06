<%@ page language="java" contentType="text/html; charset=ISO-8859-15"  pageEncoding="ISO-8859-15"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Erreur interne</title>
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
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <footer>
        Veuillez cliquer sur ce lien pour continuer <a href="<%=request.getContextPath()%>/index.jsp"> <b> Cliquez ici  </b> </a>
    </footer>

</body>
</html>
