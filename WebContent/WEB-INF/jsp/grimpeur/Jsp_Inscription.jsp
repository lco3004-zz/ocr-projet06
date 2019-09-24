
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
<div class="container">
    <header>
        <h1>Les amis de l'escalade</h1>
    </header>
    <div class="corpsDePage">
        <nav>
            <header>
                .
            </header>
            <section>
                <a class="boutonLateral" href="home">Vers l'Acceuil</a>
            </section>
            <footer>
                .
            </footer>
        </nav>
        <main>
            <header>
                <h3> Inscription </h3>
            </header>
            <section>
                <form id="login" class="formSimpleLogin">
                    <fieldset class="inputs">
                        <input name="nomGrimpeur" type="text" placeholder="Nom" autofocus required>
                        <input name="mdpGrimpeur" type="password" placeholder="Mot de Passe"
                               required>
                    </fieldset>
                    <fieldset class="actions">
                        <button class="boutonFormSimple"
                                name="Inscription"
                                form="login"
                                type="submit"
                                formaction="Inscription"
                                formmethod="post"
                                value="Inscription"
                                formtarget="_self"> S'enregistrer
                        </button>
                    </fieldset>
                    <fieldset class="informations">
                        <div id="infoerr">
                            <p>
                                ${requestScope.messageCnx}
                            </p>
                        </div>
                    </fieldset>
                </form>
            </section>
            <footer>
                .
            </footer>
        </main>
        <aside>
            <aside>
                <article>
                    <table id="tableSelectionSpot"  class="bordered">
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
                    <label style="font-size: larger ">Nos Topos</label>
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
        </aside>
    </div>
    <footer>
        <h1>@2019 Projet 06</h1>
    </footer>
</div>
</body>
</html>
