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
                        name="detailler"
                        form="navSelectionRadio"
                        type="submit"
                        formaction="VoirDetailTopo"
                        formmethod="post"
                        value="detailler"
                        formtarget="_blank"> Détailler
                </button>

                <button class="boutonLateral"
                        name="AccepterResa"
                        form="navSelectionRadio"
                        type="submit"
                        formaction="AccepterResaTopo"
                        formmethod="post"
                        value="AccepterResa"
                        formtarget="_blank"> Accepter Resa
                </button>


                <button class="boutonLateral"
                        name="ListerResa"
                        form="navSelectionRadio"
                        type="submit"
                        formaction="ListeDemandeResaTopo"
                        formmethod="post"
                        value="ListerResa"
                        formtarget="_blank"> Lister Resa
                </button>

                <button class="boutonLateral"
                        name="DemanderResa"
                        form="navSelectionRadio"
                        type="submit"
                        formaction="DemanderResaTopo"
                        formmethod="post"
                        value="DemanderResa"
                        formtarget="_blank"> Demander Resa
                </button>

                <button class="boutonLateral"
                        name="publier"
                        form="navSelectionRadio"
                        type="submit"
                        formaction="PublierTopo"
                        formmethod="post"
                        value="publier"
                        formtarget="_blank"> Publier
                </button>

                <a class="nav-link " href="EnregistrerTopo">Enregistrer</a>

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
                    <th> nom</th>
                    <th> lieu</th>
                    <th> resume</th>
                    <th> dateCreation</th>
                </tr>
                </thead>
                <tbody>
                <form id="navSelectionRadio">
                    <c:forEach var="dbTopo" items="${requestScope.dbTopos}">
                        <tr>
                            <td><input type="radio" name="idValTopo" value="${dbTopo.getIdtopo()}"></td>
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

                <label style="font-size: larger ">Topos</label>
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
                    <c:forEach var="dbTopo" items="${requestScope.dbToposGrimpeur}">
                        <tr>
                            <td><input type="radio" name="idValTopo" value="${dbTopo.getIdtopo()}"></td>
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