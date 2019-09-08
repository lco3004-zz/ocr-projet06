
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>

        <%@include file="/WEB-INF/fragments/Html_EnteteFormulaire.html" %>

</head>
<body>
<form id="login" action="Inscription" method="post">
    <h1>Inscription</h1>
    <fieldset id="inputs">
        <input id="username" name="nomGrimpeur" type="text" placeholder="Votre Nom " autofocus required>
        <input id="password" name="mdpGrimpeur" type="password" placeholder=" Mot de Passe " required>
    </fieldset>
    <fieldset id="actions">
        <input type="submit" id="submit" value="S'enregistrer"/>
        <a href="home">Retour acceuil</a>
    </fieldset>
</form>
</body>
</html>