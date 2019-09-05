<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="ISO-8859-15">
    <title>Login</title>
</head>
<body>
<form action="CtrlConnexionGrimpeur" method="post">
        <label id="nomGrimpeur" > Votre Nom de Grimpeur : </label>
        <input name="nomGrimpeur" required ="true" size="64" type="text"/>
        <br>
        <br>
        <label id="mdpGrimpeur" > Votre Mot de Passe : </label>
        <input  name="mdpGrimpeur" required ="true" size="64" type="password"/>
        <br>
        <br>
        <input type="submit" value="Se connecter"/>
    </form>
    <br>
    <br>
    <br>
    <br>
<br>
<br>
    <footer>
        Veuillez cliquer sur ce lien pour continuer <a href="<%=request.getContextPath()%>/index.jsp"> <b> Cliquez
        ici </b> </a>
    </footer>

</body>
</html>