
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <%@include file="/WEB-INF/fragments/Html_EntetePourHtml.html" %>
</head>
<body>
<form id="start"  action="Connexion" method="post">
    <h1>Inscription &agrave; l'Escalade</h1>
    <p>
        <label for="name"id="nomGrimpeur" > Votre Nom de Grimpeur : </label>
        <input id="name" name="nomGrimpeur" required ="true" size="64" type="text"/>
    </p>
    <br>
    <p>
        <label for="password">Votre Mot de Passe :</label>
        <input id="password" name="mdpGrimpeur"  required ="true"type="password" />
    </p>

        <br>

    <input type="submit" value="Se connecter" /> ou <a href="home">annuler</a>


    </form>

<footer>
    <%@include file="/WEB-INF/fragments/Html_FooterPourHtml.html" %>
</footer>
</body>
</html>