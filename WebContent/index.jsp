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
  <br/>
  <br/>

  <!-- Spots-->
  <section class="contener">
    <h3> Nos Topos </h3>
    <!-- DESCRIPTION-->
    <div id="idTopos">
      <!-- PROJET 1-->
      <article class="projet">
        <p>
          Topo (dernier ajouté : ${requestScope.dbTopo} )
          <b>
            <a href="${pageContext.request.contextPath}/Pri_AjouterUnTopo" target="_self"> Enregistrer un Topo</a>
          </b>
        </p>
      </article>
    </div>
  </section>
  <br/>
  <br/>


  <!-- CONTACT -->
  <section class="contener">
    <h3> Connexion/Identification</h3>
    <div id="idCnx">
      <article class="projet">
        <p> Connexion (actuel : ${requestScope.dbGrimpeur} )
          <b>
            <a href="${pageContext.request.contextPath}/Pub_ConnexionGrimpeur" target="_self">Connexion</a>
          </b>
        </p>
        <p>
          Identification
          <b>
            <a href="${pageContext.request.contextPath}/Pub_InscriptionGrimpeur" target="_self">Inscription</a>
          </b>
        </p>
      </article>
    </div>
  </section>

  <footer>
    <%@include file="WEB-INF/fragments/footer.html" %>
  </footer>

  </body>
</html>
