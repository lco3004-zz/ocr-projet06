
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <%@include file="/WEB-INF/fragments/Html_EntetePourHtml.html" %>
</head>
<body>
<form action="PriCtrlEnregistrerTopo" method="post">
    <label id="nomTopo" > Nom du Topo : </label>
    <input id  name="nomTopo" required ="true" size="64" type="text"/>
    <br>
    <br>
    <label id="lieuTopo" > lieuTopo : </label>
    <input id name="lieuTopo" required ="true" size="64" type="text"/>
    <br>
    <br>
    <label id="resumeTopo"> Resumé : </label>
    <input id name="resumeTopo" required ="true" size="64"  type="text"/>
    <br>
    <br>
    <input type="submit" value="Enregistrer le  Topo"/>
</form>


<footer>
    <%@include file="/WEB-INF/fragments/Html_FooterPourHtml.html" %>
</footer>

</body>
</html>