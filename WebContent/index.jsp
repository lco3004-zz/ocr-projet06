<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
  <head>
    <%@include file="/WEB-INF/fragments/entete.html" %>
  </head>
  <body>

  <nav>
    <ul>
      <li><a href="#idSpots" target="_self" title="Nos Spots"> Nos Spots </a></li>
      <li><a href="#idTopos" target="_self" title="Les Topos "> Nos Topos </a></li>
      <li><a href="#idCnx" target="_self" title="Connexion"> Se connecter</a></li>
    </ul>
  </nav>

  <!-- Spots-->
  <section class="contener">
    <h3> Nos Spots </h3>
    <!-- DESCRIPTION-->
    <div id="idSpots">
      <!-- PROJET 1-->
      <article class="projet">
        <a href="monpremierprojet.html" target="_self"> ListeDesSpots</a>
      </article>

    </div>
  </section>

  <!-- CONTACT -->
  <section class="contener">
    <h3> Connexion/Identification</h3>
    <article id="idCnx">
      <p> Connexion
        <b>
          <a href="<%=application.getContextPath()%>/WEB-INF/jsp/grimpeurs/Pub_ConnexionGrimpeur.jsp" target="_self">Connexion</a>
        </b>
      </p>
      <p>
        Identification
        <b>
          <a href="<%=application.getContextPath()%>/WEB-INF/jsp/grimpeurs/Pub_InscriptionGrimpeur.jsp" target="_self">Inscription</a>
        </b>
      </p>
    </article>
  </section>

  <footer>
    <%@include file="WEB-INF/fragments/footer.html" %>
  </footer>

  </body>
</html>
