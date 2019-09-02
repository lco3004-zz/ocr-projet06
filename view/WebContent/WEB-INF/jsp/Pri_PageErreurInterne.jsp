<%--
  Created by IntelliJ IDEA.
  User: cordier
  Date: 31/08/2019
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        Veuillez cliquer sur ce lien pour continuer <a href="<%=request.getContextPath()%>/index.jsp"> <h4> Cliquez ici  </h4> </a>
    </footer>

</body>
</html>
