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
                <button class="boutonLateral"
                        name="SelectionSecteur"
                        form="navSelectionSecteur"
                        type="submit"
                        formaction="AjouterSelectionSecteur"
                        formmethod="get"
                        value="SelectionSecteur"
                        formtarget="_self"> Selection Secteur
                </button>
                <button class="boutonLateral"
                        name="SelectionVoie"
                        form="navSelectionVoie"
                        type="submit"
                        formaction="AjouterSelectionVoie"
                        formmethod="get"
                        value="SelectionVoie"
                        formtarget="_self"> Selection Voie
                </button>
                <c:choose>
                    <c:when test="${requestScope.activerValider == true}">
                        <c:set var="choixValiderEnable" scope="page" value="enabled"> </c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="choixValiderEnable" scope="page" value="disabled"> </c:set>
                    </c:otherwise>
                </c:choose>

                <form id="formHidenValider" style="display: none">
                    <input hidden name="vide" value="0" type="text"/>
                </form>

                <button class="boutonLateral"
                        name="Valider"
                        type="submit"
                        form="formHidenValider"
                        formaction="Valider"
                        formmethod="get"
                        value="Valider"
                        formtarget="_self"
                ${choixValiderEnable}> Valider
                </button>

                <a class="boutonLateral" href="AcceuilSpot">Vers l'Acceuil</a>
            </section>
            <footer>.</footer>
        </nav>
        <main>
            <header>
                .
            </header>
            <section>
                <c:choose>
                    <c:when test="${requestScope.afficheFormeSpot == true}">
                        <c:set var="choixEnable" scope="page" value="enabled"> </c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="choixEnable" scope="page" value="disabled"> </c:set>
                    </c:otherwise>
                </c:choose>

                <label for="formEnregistrerSpot">Saisir infos Spot</label>
                <form id="formEnregistrerSpot" class="formSimple">
                    <fieldset class="inputs">
                        <input placeholder="nomSpot" name="nomSpot" required size="16" type="text"/>
                        <input placeholder="localisationSpot" name="localisationSpot" required size="16" type="text"/>
                    </fieldset>
                    <fieldset class="actions">
                        <button class="boutonFormSimple"
                                name="AjouterSpot"
                                form="formEnregistrerSpot"
                                type="submit"
                                formaction="AjouterSpot"
                                formmethod="post"
                                value="AjouterSpot"
                                formtarget="_self"
                        ${choixEnable} > Ajouter
                        </button>
                    </fieldset>
                </form>
                <c:if test="${requestScope.afficheFormeSpot == false}" scope="request" var="none">
                    <label for="formEnregistrerSecteur">Saisir infos Secteur</label>
                    <form id="formEnregistrerSecteur" class="formSimple">
                        <fieldset class="inputs">
                            <input placeholder="nomSecteur" name="nomSecteur" required size="16" type="text"/>
                        </fieldset>
                        <fieldset class="actions">
                            <button class="boutonFormSimple"
                                    name="AjouterSecteur"
                                    form="formEnregistrerSecteur"
                                    type="submit"
                                    formaction="AjouterSecteur"
                                    formmethod="post"
                                    value="AjouterSecteur"
                                    formtarget="_self"> Ajouter
                            </button>
                        </fieldset>
                    </form>

                    <label for="formEnregistrerVoie">Saisir infos Voies</label>
                    <form id="formEnregistrerVoie" class="formSimple">
                        <fieldset class="inputs">
                            <input placeholder="nomVoie" name="nomVoie" required size="16" type="text"/>
                        </fieldset>
                        <fieldset class="actions">
                            <button class="boutonFormSimple"
                                    name="AjouterVoie"
                                    form="formEnregistrerVoie"
                                    type="submit"
                                    formaction="AjouterVoie"
                                    formmethod="post"
                                    value="AjouterVoie"
                                    formtarget="_self"> Ajouter
                            </button>
                        </fieldset>
                    </form>

                    <label for="formEnregistrerLongueur">Saisir infos Longueur</label>
                    <form id="formEnregistrerLongueur" class="formSimple">
                        <fieldset class="inputs">
                            <input placeholder="nomLongueur" name="nomLongueur" required size="16" type="text"/>
                            <input placeholder="nombreDeSpits" name="nbreSpitsLongueur" required size="2" type="number" min="1" max="10" value="1"/>
                            <select required name="cotationLongueur">
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
                                    name="AjouterLongueur"
                                    form="formEnregistrerLongueur"
                                    type="submit"
                                    formaction="AjouterLongueur"
                                    formmethod="post"
                                    value="AjouterLongueur"
                                    formtarget="_self"> Ajouter
                            </button>
                        </fieldset>
                    </form>
                </c:if>
            </section>
            <footer>
                .
            </footer>
        </main>
        <aside>
            <article>
                <label>Spots</label>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th> Nom</th>
                        <th> Localisation</th>
                        <th> Classification</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty requestScope.dbSpot}" scope="request" var="non">
                        <tr>
                            <td>${requestScope.dbSpot.getNom()}</td>
                            <td>${requestScope.dbSpot.getLocalisation()}</td>
                            <td>${requestScope.dbSpot.getClassification()}</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </article>
            <article>
                <form id="navSelectionSecteur">
                    <label>Secteurs : ${param.idValSecteur}</label>
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
                    <label>Voies : ${param.idValVoie}</label>
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
                    <label>Longeurs</label>
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
    </div>
    <footer>
        <h1>@2019 Projet 06</h1>
    </footer>
</div>
</body>
</html>
