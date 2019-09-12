
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

                <a class="nav-link " href="home">Vers l'Acceuil</a>
            </section>
            <footer>.</footer>
        </nav>
        <section>
            <h3> Enregister un Topo </h3>
            <form id="EnregistrerTopo" class="bordered" action="EnregistrerTopo" method="post">
                <fieldset id="inputs">
                    <label for="nomTopo"> Nom du Topo : </label>
                    <input id="nomTopo" name="nomTopo" required="true" size="64" type="text"/>

                    <label for="lieuTopo"> lieuTopo : </label>
                    <input id="lieuTopo" name="lieuTopo" required="true" size="64" type="text"/>

                    <label for="resumeTopo"> Resumé : </label>
                    <input id="resumeTopo" name="resumeTopo" required="true" size="64" type="text"/>
                </fieldset>
                <fieldset id="actions">
                    <input type="submit" id="submit" value="Enregistrer">
                    <a href="home">Acceuil </a>
                </fieldset>
            </form>
        </section>
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
    </main>
    <footer>
        <h1>@2019 Projet 06</h1>
    </footer>
</div>
</body>
</html>


<!--
<form  id="login" action="EnregistrerTopo" method="post">
    <h1>Enregistrement</h1>
    <fieldset id="inputs">
        <label for="nomTopo" > Nom du Topo : </label>
        <input id="nomTopo"  name="nomTopo" required ="true" size="64" type="text"/>

        <label for="lieuTopo" > lieuTopo : </label>
        <input id="lieuTopo" name="lieuTopo" required ="true" size="64" type="text"/>

        <label for="resumeTopo"> Resumé : </label>
        <input id="resumeTopo" name="resumeTopo" required ="true" size="64"  type="text"/>
    </fieldset>
    <fieldset id="actions">
        <input type="submit" id="submit" value="Enregistrer">
        <a href="home">Acceuil </a>
    </fieldset>
</form>
-->