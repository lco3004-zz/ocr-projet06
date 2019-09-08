
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <%@include file="/WEB-INF/fragments/Html_EnteteFormulaire.html" %>
</head>
<body>
<form  id="login" action="EnregistrerTopo" method="post">
    <h1>Enregistrement</h1>
    <fieldset id="inputs">
        <label for="nomTopo" > Nom du Topo : </label>
        <input id="nomTopo"  name="nomTopo" required ="true" size="64" type="text"/>

        <label for="lieuTopo" > lieuTopo : </label>
        <input id="lieuTopo" name="lieuTopo" required ="true" size="64" type="text"/>

        <label for="resumeTopo"> Resumé : </label>
        <input id="resumeTopo" name="resumeTopo" required ="true" size="64"  type="text"/>
    </fieldset>
    <fieldset id="actions">
        <input type="submit" id="submit" value="Enregistrer">
        <a href="home">Acceuil </a>
    </fieldset>
</form>
</body>
</html>