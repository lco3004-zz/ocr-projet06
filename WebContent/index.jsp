<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
  <head>
    <%@include file="/WEB-INF/fragments/entete.html" %>
  </head>
  <body>
  <h3> Bienvenue sur le Site des amis de l'escalade </h3>
  <br>
  <a href="Pub_GestionsDesTopos">Nos Topos </a>
  <br>
  <a href="Pub_GestionsDesSpots">Nos Spots </a>
  <br>
  <a href="Pub_GestionDesGrimpeurs"> Se Connecter/S'inscrire </a>
  <br>
  </body>

  <footer>
    <%@include file="WEB-INF/fragments/footer.html" %>
  </footer>
</html>
