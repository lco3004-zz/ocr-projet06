
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <%@include file="/WEB-INF/fragments/entete.html" %>
</head>
<body>
<form action="Pri_CtrlConnexionGrimpeur" method="post">
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

<footer>
    <%@include file="/WEB-INF/fragments/footer.html" %>
</footer>
</body>
</html>