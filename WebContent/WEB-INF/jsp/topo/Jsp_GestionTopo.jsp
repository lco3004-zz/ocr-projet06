<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="fr">
<head>
    <meta content="text/html; charset=iso-8859-15" http-equiv="content-type">
    <link href="${pageContext.request.contextPath}/css/stl_projet.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans|Roboto" rel="stylesheet">
    <meta content="width=device-width" name="viewport"/>
    <title>Le Site de L'escalade</title>
</head>
<body>
<div id="container">

    <header>
        <h1>Les amis de l'escalade</h1>
    </header>
    <main>
        <nav class="nav">
            <header>.</header>
            <section>
                <button class="boutonLateral"
                        name="DemanderResa"
                        form="navSelectionToposDispos"
                        type="submit"
                        formaction="DemanderResaTopo"
                        formmethod="post"
                        value="DemanderResa"
                        formtarget="_self"> Demander Resa
                </button>
                <button class="boutonLateral"
                        name="RestituerTopo"
                        form="navSelectionToposReservation"
                        type="submit"
                        formaction="RestituerTopo"
                        formmethod="post"
                        value="RestituerTopo"
                        formtarget="_self"> Restituer Resa
                </button>
                <a class="boutonLateral" href="GestionDemandeResaTopo">Gérer Resa</a>

                <a class="boutonLateral" href="PublierTopo">Publier</a>

                <a class="boutonLateral" href="EnregistrerTopo">Enregistrer</a>

                <a class="boutonLateral" href="home">Vers l'Acceuil</a>
            </section>
            <footer>.</footer>
        </nav>
        <section>
            <h3> Nos Topos Disponibles pour la Réservation </h3>
            <h3>Information : ${msgResultat}</h3>
            <table class="bordered">
                <thead>
                <tr>
                    <th> #</th>
                    <th> nom</th>
                    <th> lieu</th>
                    <th> resume</th>
                    <th> dateCreation</th>
                </tr>
                </thead>
                <tbody>
                <form id="navSelectionToposDispos">
                    <c:forEach var="dbTopo" items="${requestScope.dbTopos}">
                        <tr>
                            <td><input type="radio" name="idValTopo" required value="${dbTopo.getIdtopo()}"></td>
                            <td>${dbTopo.getNom()}</td>
                            <td>${dbTopo.getLieu()}</td>
                            <td>${dbTopo.getResume()}</td>
                            <td>${dbTopo.getDateDeParution()}</td>
                        </tr>
                    </c:forEach>
                </form>
                </tbody>
            </table>
            <h3> Mes Réservation de Topos </h3>
            <table class="bordered">
                <thead>
                <tr>
                    <th> #</th>
                    <th> nom</th>
                    <th> lieu</th>
                    <th> resume</th>
                    <th> dateCreation</th>
                </tr>
                </thead>
                <tbody>
                <form id="navSelectionToposReservation">
                    <c:forEach var="dbTopo" items="${requestScope.dbToposMesReservation}">
                        <tr>
                            <td><input type="radio" name="idValTopo" required value="${dbTopo.getIdtopo()}"></td>
                            <td>${dbTopo.getNom()}</td>
                            <td>${dbTopo.getLieu()}</td>
                            <td>${dbTopo.getResume()}</td>
                            <td>${dbTopo.getDateDeParution()}</td>
                        </tr>
                    </c:forEach>
                </form>
                </tbody>
            </table>
        </section>
        <aside>
            <article>
                <label style="font-size: larger ">Tous Mes Topos</label>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th> nom</th>
                        <th> lieu</th>
                        <th> resume</th>
                        <th> dateCreation</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="dbTopo" items="${requestScope.dbToposGrimpeur}">
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
            <article>
                <label style="font-size: larger ">Les Dmde de Résa</label>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th> nom</th>
                        <th> lieu</th>
                        <th> resume</th>
                        <th> dateCreation</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="dbTopo" items="${requestScope.dbDemandeDeResa}">
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
            <article>
                <label style="font-size: larger ">Mes Topos Réservés</label>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th> nom</th>
                        <th> lieu</th>
                        <th> resume</th>
                        <th> dateCreation</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="dbTopo" items="${requestScope.dbMesToposReserver}">
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
    </main>
    <footer>
        <h1>@2019 Projet 06</h1>
    </footer>
</div>
</body>
</html>