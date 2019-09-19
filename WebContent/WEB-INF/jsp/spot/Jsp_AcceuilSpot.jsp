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
<div class="container">

    <header>
        <h1>Les amis de l'escalade</h1>
    </header>
    <div class="corpsDePage">
        <nav>
            <header>.</header>
            <section>
                <a class="boutonLateral" href="AdminSpot">AdminSpot</a>
                <a class="boutonLateral" href="CreerSpot">Créer</a>
                <a class="boutonLateral" href="home">Vers l'Acceuil</a>
            </section>
            <footer>.</footer>
        </nav>
        <main>
            <header>
                .
            </header>
            <section>
                <div class="divrecherche" id="divrecherche">
                    <label for="divrecherche"><h3>Recherche</h3></label>
                    <form id="formRecherche" class="formSimple">
                        <fieldset class="labels">
                            <label for="inputNomSpot">NomSpot</label>
                            <label for="inputNbreDeSpits">Spits</label>
                            <label for="inputClassification">Classification</label>
                            <label for="inputCotation">Cotation</label>
                        </fieldset>
                        <fieldset class="inputs">
                            <input id="inputNomSpot" type="text" size="8" name="inputNomSpot">

                            <input id="inputNbreDeSpits" name="inputNbreDeSpits" size="2" type="number" min="1" max="10"/>

                            <select id="inputClassification" name="inputClassification">
                                <optgroup label="Classification">
                                    <option disabled selected value> -- Choisir Classification -- </option>
                                    <option value="STANDARD">STANDARD</option>
                                    <option value="OFFICIEL">OFFICIEL</option>
                                </optgroup>
                            </select>

                            <select id="inputCotation" name="inputCotation">
                                <optgroup label="Cotation">
                                    <option disabled selected value> -- Choisir Cotation -- </option>
                                    <option value="QUATRE_A">QUATRE_A</option>
                                    <option value="QUATRE_B">QUATRE_B</option>
                                    <option value="QUATRE_C">QUATRE_C</option>
                                    <option value="SIX_A">SIX_A</option>
                                    <option value="SIX_APLUS">SIX_APLUS</option>
                                    <option value="SIX_BPLUS">SIX_BPLUS</option>
                                    <option value="SIX_CPLUS">SIX_CPLUS</option>
                                </optgroup>
                            </select>
                        </fieldset>
                        <fieldset class="actions">
                            <button class="boutonFormSimple"
                                    name="Cherche"
                                    form="formRecherche"
                                    type="submit"
                                    formaction="AcceuilRechercheSpot"
                                    formmethod="get"
                                    value="Cherche"
                                    formtarget="_self"> Cherche
                            </button>
                        </fieldset>
                    </form>
                </div>
                <div>
                    <label for="navSelectionSpot"><h3> Nos Spots </h3> </label>
                    <form id="navSelectionSpot" class="formSimple">
                        <table id="tableSelectionSpot"  class="bordered">
                            <thead>
                            <tr>
                                <th>Nom</th>
                                <th>Localisation</th>
                                <th>Classification</th>
                                <th>#</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="dbSpot" items="${requestScope.dbSpots}">
                                <tr>
                                    <c:set var="labelBoutton" scope="page" value="-"/>
                                    <c:set var="couleurFond" scope="page" value="background-color: slategray; color: black;  font-size: 18px;"/>
                                    <c:if test="${requestScope.idValSpot == dbSpot.getIdspot()}" var="nope">
                                        <c:set var="couleurFond" scope="page" value="background-color: slategray; color: black; font-weight: bold; font-size: 20px;"/>
                                        <c:set var="labelBoutton" scope="page" value="+"/>
                                    </c:if>
                                    <td>${dbSpot.getNom()}</td>
                                    <td>${dbSpot.getLocalisation()}</td>
                                    <td>${dbSpot.getClassification()}</td>
                                    <td >
                                        <button style="${couleurFond}  border-radius: 30%; align-self: center;"
                                                name="idValSpot"
                                                type="submit"
                                                formaction="AcceuilSelectionSpot"
                                                formmethod="get"
                                                value="${dbSpot.getIdspot()}"
                                                formtarget="_self"> ${labelBoutton}
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
                <c:if test="${requestScope.voirSaisieCommentaire == true}" scope="page" var="nope">
                    <div class="divcommentaire" id="divcommentaire">
                        <label for="navSaisieCommentaire"><h3> Commenter </h3> </label>
                        <form id="navSaisieCommentaire"  class="formSimple">
                            <fieldset  class="labels">
                                <label for="inputcommentaire" class="labels">Message:</label>
                            </fieldset>
                            <fieldset class="inputs">
                                <textarea id="inputcommentaire" name="inputcommentaire" ></textarea>
                            </fieldset>
                            <fieldset class="actions">
                                <button style="border-radius: 30%;"
                                        class="boutonFormSimple"
                                        name="commentaireSpot"
                                        type="submit"
                                        formaction="AcceuilCommenterSpot"
                                        formmethod="get"
                                        formtarget="_self"> Poster
                                </button>
                            </fieldset>
                        </form>
                    </div>
                </c:if>
            </section>
            <footer>
                .
            </footer>
        </main>
        <aside>
            <article>
                <label for="tableComments"  style="font-size: larger ">Commentaires</label>
                <table id="tableComments" class="bordered">
                    <thead>
                    <tr>
                        <th>Titre</th>
                        <th>Commentaire</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dbCommentaire" items="${requestScope.dbCommentaires}">
                            <tr>
                                <td>${dbCommentaire.getNom()}</td>
                                <td>${dbCommentaire.getTexte()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </article>
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
                                <c:set var="labelBoutton" scope="page" value="-"/>
                                <c:set var="couleurFond" scope="page" value="background-color: slategray; color: black;  font-size: 15px;"/>
                                <c:if test="${requestScope.idValSecteur == dbSecteur.getIdsecteur()}" var="nope">
                                    <c:set var="couleurFond" scope="page" value="background-color: slategray; color: black; font-weight: bold; font-size: 18px;"/>
                                    <c:set var="labelBoutton" scope="page" value="+"/>
                                </c:if>
                                <td >${dbSecteur.getNom()}</td>
                                <td >
                                    <button style="${couleurFond} border-radius: 30%;"
                                            name="idValSecteur"
                                            type="submit"
                                            formaction="AcceuilSelectionSecteur"
                                            formmethod="get"
                                            value="${dbSecteur.getIdsecteur()}"
                                            formtarget="_self"> ${labelBoutton}
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
                                <c:set var="labelBoutton" scope="page" value="-"/>
                                <c:set var="couleurFond" scope="page" value="background-color: slategray; color: black;  font-size: 15px;"/>
                                <c:if test="${requestScope.idValVoie == dbVoie.getIdvoie()}" var="nope">
                                    <c:set var="couleurFond" scope="page" value="background-color: slategray; color: black; font-weight: bold; font-size: 18px;"/>
                                    <c:set var="labelBoutton" scope="page" value="+"/>
                                </c:if>
                                <td >${dbVoie.getNom()}</td>
                                <td >
                                    <button style="${couleurFond} border-radius: 30%;"
                                            name="idValVoie"
                                            type="submit"
                                            formaction="AcceuilSelectionVoie"
                                            formmethod="get"
                                            value="${dbVoie.getIdvoie()}"
                                            formtarget="_self"> ${labelBoutton}
                                    </button>
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
                        <c:forEach var="dbLongueur" items="${requestScope.dbLongueurs}">
                            <tr>
                                <td>${dbLongueur.getNom()}</td>
                                <td>${dbLongueur.getCotation()}</td>
                                <td>${dbLongueur.getNombreDeSpits()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </article>
        </aside>
    </div>
    <footer>
        <h1>@2019 Projet 06</h1>
    </footer>
</div>
</body>
</html>

