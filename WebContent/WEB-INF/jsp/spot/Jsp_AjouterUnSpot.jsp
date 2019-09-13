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
                <a class="boutonLateral" href="home">Vers l'Acceuil</a>
            </section>
            <footer>.</footer>
        </nav>
        <section>
            <h3> Enregister un Spot </h3>
            <!--
            <form id="formEnregistrerData" class="formSimple">
                <fieldset class="labels">
                    <label for="nomTopo"> Nom du Topo : </label>
                    <label for="lieuTopo"> lieuTopo : </label>
                    <label for="resumeTopo"> Resumé : </label>
                </fieldset>
                <fieldset class="inputs">
                    <input id="nomTopo" name="nomTopo" required="true" size="64" type="text"/>
                    <input id="lieuTopo" name="lieuTopo" required="true" size="64" type="text"/>
                    <input id="resumeTopo" name="resumeTopo" required="true" size="64" type="text"/>
                </fieldset>
                <fieldset class="actions">
                    <button class="boutonFormSimple"
                            name="CreerSpot"
                            form="formEnregistrerData"
                            type="submit"
                            formaction="CreerSpot"
                            formmethod="post"
                            value="CreerSpot"
                            formtarget="_self"> Enregistrer
                    </button>
                </fieldset>
            </form>
                -->
        </section>
        <aside>
            <article>

                <label style="font-size: larger ">Spots</label>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th> Nom</th>
                        <th> Localisation</th>
                        <th> Classification</th>
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
                <label style="font-size: larger ">Secteurs</label>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th>A</th>
                        <th>B</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>The Shawshank Redemption</td>
                        <td>1994</td>
                    </tr>
                    </tbody>
                </table>
            </article>

            <article>
                <label style="font-size: larger ">Voies</label>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th>A</th>
                        <th>B</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>The Shawshank Redemption</td>
                        <td>1994</td>
                    </tr>
                    </tbody>
                </table>
            </article>
            <article>
                <label style="font-size: larger ">Longeurs</label>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th>A</th>
                        <th>B</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>The Shawshank Redemption</td>
                        <td>1994</td>
                    </tr>
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
