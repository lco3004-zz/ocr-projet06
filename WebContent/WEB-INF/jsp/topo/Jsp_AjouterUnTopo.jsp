
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
        <nav >
            <header>.</header>
            <section>
                <a class="boutonLateral" href="home">Vers l'Acceuil</a>
            </section>
            <footer>.</footer>
        </nav>
        <main>
            <header>
                <h3> Enregister un Topo </h3>
            </header>
            <section>
                <form id="formEnregistrerData" class="formSimple">
                    <fieldset class="labels">
                        <label for="nomTopo">Nom :</label>
                        <label for="lieuTopo">Lieu :</label>
                        <label for="resumeTopo">Résumé :</label>
                    </fieldset>
                    <fieldset class="inputs">
                        <input id="nomTopo" name="nomTopo" required size="40%" type="text"/>
                        <input id="lieuTopo" name="lieuTopo" required size="40%" type="text"/>
                        <input id="resumeTopo" name="resumeTopo" required size="40%" type="text"/>
                    </fieldset>
                    <fieldset class="actions">
                        <button class="boutonFormSimple"
                                name="Enregistrer"
                                form="formEnregistrerData"
                                type="submit"
                                formaction="EnregistrerTopo"
                                formmethod="post"
                                value="Enregistrer"
                                formtarget="_self"> Enregistrer
                        </button>
                    </fieldset>
                </form>
            </section>
            <footer>
                .
            </footer>
        </main>
        <aside>
            <article>
                <label style="font-size: larger ">Mes Topos</label>
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
                <label style="font-size: larger ">Mes Topos en Wait Resa</label>
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
        </aside>
    </div>

    <footer>
        <h1>@2019 Projet 06</h1>
    </footer>
</div>
</body>
</html>
