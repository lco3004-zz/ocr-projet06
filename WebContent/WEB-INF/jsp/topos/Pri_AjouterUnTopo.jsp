
<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <%@include file="/WEB-INF/fragments/entete.html" %>
</head>
<body>
<form action="CtrlAjouterUnTopo" method="post">
    <label id="nomTopo" > Nom du Topo : </label>
    <input id  name="nomTopo" required ="true" size="64" type="text"/>
    <br>
    <br>
    <label id="lieuTopo" > lieuTopo : </label>
    <input id name="lieuTopo" required ="true" size="64" type="text"/>
    <br>
    <br>
    <label id="resumeTopo"> Resum� : </label>
    <input id name="resumeTopo" required ="true" size="64"  type="text"/>
    <br>
    <br>
    <input type="submit" value="Enregistrer le  Topo"/>
</form>


<footer>
    <%@include file="/WEB-INF/fragments/footer.html" %>
</footer>

</body>
</html>