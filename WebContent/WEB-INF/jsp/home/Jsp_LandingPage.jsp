<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta content="text/html; charset=iso-8859-15" http-equiv="content-type">
    <link href="${pageContext.request.contextPath}/css/stl_projet.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans|Roboto" rel="stylesheet">
    <meta content="width=device-width , initial-scale=1.0 , user-scalable=1 , minimum-scale=0.5 , maximum-scale=1.5" name="viewport"/>
    <title>Le Site de L'escalade</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Les amis de l'escalade</h1>
    </header>
    <div class="corpsDePage">
        <nav>
            <header> .</header>
            <section>
                <a class="boutonLateral" href="AcceuilTopo">Nos Topos</a>
                <a class="boutonLateral" href="AcceuilSpot">Nos Spots</a>
                <a class="boutonLateral" href="Connexion">Connexion</a>
            </section>
            <footer> .</footer>
        </nav>
        <main>
            <header>
                <c:set var="msgWelcome" value="Bienvenue Visiteur"/>
                <c:if test="${ not empty sessionScope.dbGrimpeur}" scope="page" var="cnxOk">
                    <c:set var="msgWelcome" value="Bonjour  ${sessionScope.dbGrimpeur.getUserName()}"/>
                </c:if>
                <h2> ${msgWelcome}</h2>
            </header>
            <section>
            </section>
            <footer>
                .
            </footer>
        </main>
        <aside>
            <article>
                <label for="nosspots">Nos Spots</label>
                <table id="nosspots" class="bordered">
                    <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Localisation</th>
                        <th>Classification</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="dbSpot" items="${requestScope.dbSpots}">
                        <tr>
                            <td>${dbSpot.getNom()}</td>
                            <td>${dbSpot.getLocalisation()}</td>
                            <td>${dbSpot.getClassification()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </article>
            <article>
                <label for="nostopos">Nos Topos</label>
                <table id="nostopos" class="bordered">
                    <thead>
                    <tr>
                        <th> nom</th>
                        <th> lieu</th>
                        <th> resume</th>
                        <th> dateCreation</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="dbTopo" items="${requestScope.dbTopos}">
                        <tr>
                            <td>${dbTopo.getNom()}</td>
                            <td>${dbTopo.getLieu()}</td>
                            <td>${dbTopo.getResume()}</td>
                            <td>${dbTopo.getDateDeParution()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </article>
        </aside>
    </div>
    <footer>
        <h1>@2019 Projet 06</h1>
    </footer>
</div>
</body>
</html>