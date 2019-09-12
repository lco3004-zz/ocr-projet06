
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="ISO-8859-15">

    <link href="${pageContext.request.contextPath}/css/stl_projet.css" rel="stylesheet" type="text/css">
    <title> Hi</title>
</head>
<body>

<form id="login" action="Connexion" method="post">
    <h1>Connexion</h1>
    <fieldset id="inputs">
        <input id="username" name="nomGrimpeur" type="text" placeholder="Votre Nom " autofocus required>
        <input id="password" name="mdpGrimpeur" type="password" placeholder=" Mot de Passe " required>
    </fieldset>
    <fieldset id="actions">
        <input type="submit" id="submit" value="Se connecter">
        <a href="home">mdp oublié ?</a><a href="Inscription">Inscription</a>
    </fieldset>
    <fieldset id="information">
            <div id="infoerr">
                <p >
                        ${requestScope.messageCnx}
                </p>
            </div>
    </fieldset>
</form>

</body>
</html>