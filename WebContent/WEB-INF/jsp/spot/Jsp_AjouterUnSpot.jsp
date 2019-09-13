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
                        name="CreerSecteur"
                        form="xxx"
                        type="submit"
                        formaction="CreerSecteur"
                        formmethod="post"
                        value="CreerSecteur"
                        formtarget="_self"> Creer Secteur
                </button>
                <button class="boutonLateral"
                        name="CreerVoie"
                        form="xxx"
                        type="submit"
                        formaction="CreerVoie"
                        formmethod="post"
                        value="CreerVoie"
                        formtarget="_self"> Creer Voie
                </button>
                <button class="boutonLateral"
                        name="CreerLongeur"
                        form="xxx"
                        type="submit"
                        formaction="CreerLongeur"
                        formmethod="post"
                        value="CreerLongeur"
                        formtarget="_self"> Creer Longeur
                </button>
                <button class="boutonLateral"
                        name="Valider"
                        form="xxx"
                        type="submit"
                        formaction="Valider"
                        formmethod="post"
                        value="Valider"
                        formtarget="_self"> Valider
                </button>
                <a class="boutonLateral" href="home">Vers l'Acceuil</a>
            </section>
            <footer>.</footer>
        </nav>
        <section>
            <h3> Enregister un Spot </h3>
            <article>
                <c:set var="afficheFormeSpot" value="true" scope="page"/>
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
                        <c:set var="afficheFormeSpot" value="false" scope="page"/>
                        <tr>
                            <td>${dbSpot.getNom()}</td>
                            <td>${dbSpot.getLocalisation()}</td>
                            <td>${dbSpot.getClassification()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${afficheFormeSpot == true}" scope="page" var="none">
                    <form id="formEnregistrerSpot" class="formSimple">
                        <fieldset class="labels">
                            <label for="nomSpot"> nom : </label>
                            <label for="localisationSpot"> lieu : </label>
                        </fieldset>
                        <fieldset class="inputs">
                            <input id="nomSpot" name="nomSpot" required="true" size="16" type="text"/>
                            <input id="localisationSpot" name="localisationSpot" required="true" size="16" type="text"/>
                        </fieldset>
                        <fieldset class="actions">
                            <button class="boutonFormSimple"
                                    name="AjouterSpot"
                                    form="formEnregistrerSpot"
                                    type="submit"
                                    formaction="AjouterSpot"
                                    formmethod="post"
                                    value="AjouterSpot"
                                    formtarget="_self"> Ajouter
                            </button>
                        </fieldset>
                    </form>
                </c:if>
            </article>
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
