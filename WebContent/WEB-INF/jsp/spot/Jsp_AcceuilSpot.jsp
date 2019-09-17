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
                    <th> Click me !</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="dbSpot" items="${requestScope.dbSpots}">
                        <tr>
                            <!--  <td><input hidden type="text" name="idValSpot" value="${dbSpot.getIdspot()}"></td> -->
                            <td>${dbSpot.getNom()}</td>
                            <td>${dbSpot.getLocalisation()}</td>
                            <td>${dbSpot.getClassification()}</td>
                            <td>
                                <button class="boutonFormSimple"
                                        name="idValSpot"
                                        type="submit"
                                        formaction="AcceuilSpot/SelectionSpot"
                                        formmethod="get"
                                        value="${dbSpot.getIdspot()}"
                                        formtarget="_self"> details
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
                            <th> #</th>
                            <th> Nom</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dbSecteur" items="${requestScope.dbSpot.getSecteursByIdspot()}">
                            <tr>
                                <c:choose>
                                    <c:when test="${requestScope.idValSecteur == dbSecteur.getIdsecteur()}">
                                        <td><input type="radio" checked name="idValSecteur" required
                                                   value="${dbSecteur.getIdsecteur()}"></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><input type="radio" name="idValSecteur" required
                                                   value="${dbSecteur.getIdsecteur()}"></td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${dbSecteur.getNom()}</td>
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
                        <th> #</th>
                        <th> idSec</th>
                        <th> Nom</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dbSecteur" items="${requestScope.dbSpot.getSecteursByIdspot()}">
                            <c:forEach var="dbVoie" items="${dbSecteur.getVoiesByIdsecteur()}">
                                <tr>
                                    <c:if test="${requestScope.idValSecteur == dbSecteur.getIdsecteur()}" var="resT">
                                        <c:choose>
                                            <c:when test="${requestScope.idValVoie == dbVoie.getIdvoie()}">
                                                <td><input type="radio" checked name="idValVoie" required
                                                           value="${dbVoie.getIdvoie()}"></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><input type="radio" name="idValVoie" required
                                                           value="${dbVoie.getIdvoie()}"></td>
                                            </c:otherwise>
                                        </c:choose>

                                        <td> ${dbSecteur.getIdsecteur()}</td>
                                        <td>${dbVoie.getNom()}</td>

                                    </c:if>
                                </tr>
                            </c:forEach>
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
                        <th> idSec</th>
                        <th> idVoie</th>
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
                                            <td> ${dbSecteur.getIdsecteur()}</td>
                                            <td>${dbVoie.getIdvoie()}</td>
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