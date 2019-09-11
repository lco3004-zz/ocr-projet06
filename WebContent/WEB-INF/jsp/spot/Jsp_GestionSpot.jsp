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

                <button class="boutonSpot"
                        name="detaillerSpot"
                        form="navSpot"
                        type="submit"
                        formaction="#"
                        formmethod="post"
                        value="detaillerSpot"
                        formtarget="_self"> Détailler
                </button>

                <button class="boutonSpot"
                        name="commenterSpot"
                        form="navSpot"
                        type="submit"
                        formaction="#"
                        formmethod="post"
                        value="commenterSpot"
                        formtarget="_self"> Commenter
                </button>

                <button class="boutonSpot"
                        name="taggerSpot"
                        form="navSpot"
                        type="submit"
                        formaction="#"
                        formmethod="post"
                        value="taggerSpot"
                        formtarget="_self"> Tagger
                </button>

                <button class="boutonSpot"
                        name="ajouterSpot"
                        form="navSpot"
                        type="submit"
                        formaction="#"
                        formmethod="post"
                        value="ajouterSpot"
                        formtarget="_self"> Créer
                </button>

                <a class="nav-link " href="home">Vers l'Acceuil</a>
            </section>
            <footer>.</footer>
        </nav>
        <section>
            <h3> Nos Spots </h3>
            <table class="bordered">
                <thead>
                <tr>
                    <th> #</th>
                    <th> Nom</th>
                    <th> Localisation</th>
                    <th> Classification</th>
                </tr>
                </thead>
                <tbody>
                <form id="navSpot">
                    <c:forEach var="dbSpot" items="${requestScope.dbSpots}">
                        <tr>
                            <td><input type="radio" name="idValSpot" value="${dbSpot.getIdSpot()}"></td>
                            <td>${dbSpot.getNom()}</td>
                            <td>${dbSpot.getLocalisation()}</td>
                            <td>${dbSpot.getClassification()}</td>
                        </tr>
                    </c:forEach>
                </form>
                </tbody>
            </table>
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