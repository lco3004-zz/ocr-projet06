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
                <button class="boutonLateral"
                        name="publier"
                        form="navSelectionRadio"
                        type="submit"
                        formaction="PublierTopo"
                        formmethod="post"
                        value="publier"
                        formtarget="_self"> Publier
                </button>
                <a class="boutonLateral" href="AcceuilTopo">Up</a>
            </section>
            <footer>.</footer>
        </nav>

        <main>
            <header>
                <h3> Liste des vos topos non publi�s</h3>
            </header>
            <section>
                <h3> Liste des vos topos non publi�s</h3>
                <form id="navSelectionRadio">
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
                        <c:forEach var="dbTopo" items="${requestScope.dbToposNonPublies}">
                            <tr>
                                <td><input type="radio" name="idValTopo" required value="${dbTopo.getIdtopo()}"></td>
                                <td>${dbTopo.getNom()}</td>
                                <td>${dbTopo.getLieu()}</td>
                                <td>${dbTopo.getResume()}</td>
                                <td>${dbTopo.getDateDeParution()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </section>
            <footer>
                .
            </footer>
        </main>

        <aside>
            <article>
                <label >Mes Topos</label>
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
                <label >Mes Topos en Wait Resa</label>
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
