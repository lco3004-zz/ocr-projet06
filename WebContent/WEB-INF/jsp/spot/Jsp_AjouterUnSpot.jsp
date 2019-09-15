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
                        name="SelectionSecteur"
                        form="navSelectionSecteur"
                        type="submit"
                        formaction="SelectionSecteur"
                        formmethod="get"
                        value="SelectionSecteur"
                        formtarget="_self"> Selection Secteur
                </button>
                <button class="boutonLateral"
                        name="SelectionVoie"
                        form="navSelectionVoie"
                        type="submit"
                        formaction="SelectionVoie"
                        formmethod="get"
                        value="SelectionVoie"
                        formtarget="_self"> Selection Voie
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
            <header style="display: flex ; background-color: antiquewhite;flex-direction: row ; flex-wrap: nowrap ; justify-content: space-around;height: 2% ;width: 100%; border: 1px solid black">
                <p style="border: 1px dotted red ; flex-basis: 50%"> Valeur Secteur : ${requestScope.idValSecteur}  </p>
                <p style="border: 1px dotted red ; flex-basis: 50%"> Valeur Voie : ${requestScope.idValVoie}</p>
            </header>
            <c:if test="${requestScope.afficheFormeSpot == true}" scope="request" var="none">
                    <label for="formEnregistrerSpot" class="labels">Saisir infos Spot</label>
                    <form id="formEnregistrerSpot" class="formSimple">
                        <fieldset class="labels">
                            <label for="nomSpot"> Nom : </label>
                            <label for="localisationSpot"> Lieu : </label>
                        </fieldset>
                        <fieldset class="inputs">
                            <input id="nomSpot" name="nomSpot" required size="16" type="text"/>
                            <input id="localisationSpot" name="localisationSpot" required size="16" type="text"/>
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
            <c:if test="${requestScope.afficheFormeSpot == false}" scope="request" var="none">
                    <label for="formEnregistrerSecteur" class="labels">Saisir infos Secteur</label>
                    <form id="formEnregistrerSecteur" class="formSimple">
                        <fieldset class="labels">
                            <label for="nomSecteur"> Nom : </label>
                        </fieldset>
                        <fieldset class="inputs">
                            <input id="nomSecteur" name="nomSecteur" required size="16" type="text"/>
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

                <label for="formEnregistrerVoie" class="labels">Saisir infos Voies</label>
                <form id="formEnregistrerVoie" class="formSimple">
                    <fieldset class="labels">
                        <label for="nomVoie"> Nom : </label>
                    </fieldset>
                    <fieldset class="inputs">
                        <input id="nomVoie" name="nomVoie" required size="16" type="text"/>
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

                <label for="formEnregistrerLongueur" class="labels">Saisir infos Longueur</label>
                <form id="formEnregistrerLongueur" class="formSimple">
                    <fieldset class="labels">
                        <label for="nomLongueur"> Nom : </label>
                        <label for="nombreDeSpits"> Spits : </label>
                        <label for="cotationLongueur"> Cotation : </label>
                    </fieldset>
                    <fieldset class="inputs">
                        <input id="nomLongueur" name="nomLongueur" required size="16" type="text"/>
                        <input id="nombreDeSpits" name="nbreSpitsLongueur" required size="2" type="text"/>
                        <select id="cotationLongueur" required name="cotationLongueur">
                            <optgroup label="Cotation">
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
                <label style="font-size: larger ">Secteurs : Cur = ${requestScope.idValSecteur}</label>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th> #</th>
                        <th> reidSec</th>
                        <th> idSec</th>
                        <th> Nom</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:forEach var="dbSecteur" items="${requestScope.dbSpot.getSecteursByIdspot()}">
                                <td>
                                    <c:set value="${requestScope.idValSecteur}" var="A"/>

                                    <c:set value="${dbSecteur.getIdsecteur()}" var="B"/>
                                    <form id="navSelectionSecteur" autocomplete="off">
                                        <c:choose>
                                            <c:when test="${A}==${B}">
                                                <label for="UU">U</label>
                                                <input id="UU" type="radio" checked name="idValSecteur" required
                                                       value="${dbSecteur.getIdsecteur()}">
                                            </c:when>
                                            <c:otherwise>
                                                <label for="KK">U</label>
                                                <input id="KK" type="radio" name="idValSecteur" required
                                                       value="${dbSecteur.getIdsecteur()}">
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </td>
                                <td>${A}</td>
                                <td>${B} </td>
                                <td>${dbSecteur.getNom()}</td>
                            </c:forEach>

                        </tr>
                    </tbody>
                </table>
            </article>

            <article>
                <label style="font-size: larger ">Voies</label>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th> #</th>
                        <th> idSec</th>
                        <th> Nom</th>
                    </tr>
                    </thead>
                    <tbody>
                    <form id="navSelectionVoie">
                        <c:forEach var="dbSecteur" items="${requestScope.dbSpot.getSecteursByIdspot()}">
                            <c:forEach var="dbVoie" items="${dbSecteur.getVoiesByIdsecteur()}">
                                <tr>
                                    <td><input type="radio" name="idValVoie" required value="${dbVoie.getIdvoie()}">
                                    </td>
                                    <td> ${dbSecteur.getIdsecteur()}</td>
                                    <td>${dbVoie.getNom()}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </form>
                    </tbody>
                </table>
            </article>
            <article>
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
                    <form id="navSelectionLongueur">
                        <c:forEach var="dbSecteur" items="${requestScope.dbSpot.getSecteursByIdspot()}">
                            <c:forEach var="dbVoie" items="${dbSecteur.getVoiesByIdsecteur()}">
                                <c:forEach var="dbLongueur" items="${dbVoie.getLongueursByIdvoie()}">
                                    <tr>
                                        <td> ${dbSecteur.getIdsecteur()}</td>
                                        <td>${dbVoie.getIdvoie()}</td>
                                        <td>${dbLongueur.getNom()}</td>
                                        <td>${dbLongueur.getCotation().name()}</td>
                                        <td>${dbLongueur.getNombreDeSpits()}</td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </c:forEach>
                    </form>
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
