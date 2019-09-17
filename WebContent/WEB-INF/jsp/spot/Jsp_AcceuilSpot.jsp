<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
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
                        name="commenter"
                        form="navSelectionSpot"
                        type="submit"
                        formaction="Commenter"
                        formmethod="post"
                        value="commenter"
                        formtarget="_self"> Commenter
                </button>

                <button class="boutonLateral"
                        name="rechercher"
                        form="navSelectionSpot"
                        type="submit"
                        formaction="Rechercher"
                        formmethod="post"
                        value="rechercher"
                        formtarget="_self"> Rechercher
                </button>

                <a class="boutonLateral" href="CreerSpot">Créer</a>

                <a class="boutonLateral" href="home">Vers l'Acceuil</a>
            </section>
            <footer>.</footer>
        </nav>
        <section>
            <h3> Nos Spots </h3>
            <form id="navSelectionSpot" class="formSimple">
            <table class="bordered">
                <thead>
                <tr>
                    <th> Nom</th>
                    <th> Localisation</th>
                    <th> Classification</th>
                    <th> - </th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="dbSpot" items="${requestScope.dbSpots}">
                        <tr>
                            <c:if test="${requestScope.idValSpot == dbSpot.getIdspot()}" var="nope">
                                <c:set var="couleurFond" scope="page" value="background-color: darkblue;color: lightgrey;font-style: oblique;"/>
                            </c:if>
                            <td style="${couleurFond}">${dbSpot.getNom()}</td>
                            <td style="${couleurFond}">${dbSpot.getLocalisation()}</td>
                            <td style="${couleurFond}">${dbSpot.getClassification()}</td>
                            <td style="${couleurFond}">
                                <button class="boutonFormSimple"
                                        name="idValSpot"
                                        type="submit"
                                        formaction="AcceuilSpot/SelectionSpot"
                                        formmethod="get"
                                        value="${dbSpot.getIdspot()}"
                                        formtarget="_self"> Select
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </form>
        </section>
        <aside>
            <article>
                <form id="navSelectionSecteur">
                    <label style="font-size: larger ">Secteurs : ${param.idValSecteur}</label>
                    <table class="bordered">
                        <thead>
                        <tr>
                            <th> Nom</th>
                            <th> # </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dbSecteur" items="${requestScope.dbSecteurs}">
                            <tr>
                                <c:if test="${requestScope.idValSecteur == dbSecteur.getIdsecteur()}" var="nope">
                                    <c:set var="couleurFond" scope="page" value="background-color: darkblue;color: lightgrey;font-style: oblique;"/>
                                </c:if>
                                <td style="${couleurFond}">${dbSecteur.getNom()}</td>
                                <td style="${couleurFond}">
                                    <button class="boutonFormSimple"
                                            name="idValSecteur"
                                            type="submit"
                                            formaction="AcceuilSpot/SelectionSecteur"
                                            formmethod="get"
                                            value="${dbSecteur.getIdsecteur()}"
                                            formtarget="_self"> Select
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </article>
            <article>
                <form id="navSelectionVoie">
                    <label style="font-size: larger ">Voies : ${param.idValVoie}</label>
                    <table class="bordered">
                        <thead>
                        <tr>
                            <th> Nom</th>
                            <th> # </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dbVoie" items="${requestScope.dbVoies}">
                            <tr>
                                <c:if test="${requestScope.idValSecteur == dbSecteur.getIdsecteur()}" var="resT">
                                    <c:if test="${requestScope.idValVoie == dbVoie.getIdvoie()}" var="nope">
                                        <c:set var="couleurFond" scope="page" value="background-color: darkblue;color: lightgrey;font-style: oblique;"/>
                                    </c:if>
                                    <td style="${couleurFond}">${dbVoie.getNom()}</td>
                                    <td style="${couleurFond}">
                                        <button class="boutonFormSimple"
                                                name="idValVoie"
                                                type="submit"
                                                formaction="AcceuilSpot/SelectionVoie"
                                                formmethod="get"
                                                value="${dbSecteur.getIdsecteur()}"
                                                formtarget="_self"> Select
                                        </button>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </article>
            <article>
                <form id="navSelectionLongueur">
                    <label style="font-size: larger ">Longeurs</label>
                    <table class="bordered">
                        <thead>
                        <tr>
                            <th> Nom</th>
                            <th> Cotation</th>
                            <th> Spits</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dbSecteur" items="${requestScope.dbSpot.getSecteursByIdspot()}">
                            <c:forEach var="dbVoie" items="${dbSecteur.getVoiesByIdsecteur()}">
                                <c:forEach var="dbLongueur" items="${dbVoie.getLongueursByIdvoie()}">
                                    <tr>
                                        <c:if test="${requestScope.idValVoie == dbVoie.getIdvoie()}" var="resT">
                                            <td>${dbLongueur.getNom()}</td>
                                            <td>${dbLongueur.getCotation()}</td>
                                            <td>${dbLongueur.getNombreDeSpits()}</td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </article>

        </aside>
    </main>
    <footer>
        <h1>@2019 Projet 06</h1>
    </footer>
</div>
</body>
</html>

