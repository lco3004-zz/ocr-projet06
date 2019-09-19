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
                        name="tagger"
                        form="navSelectionSpot"
                        type="submit"
                        formaction="TaggerSpot"
                        formmethod="post"
                        value="tagger"
                        formtarget="_self"> Tagger
                </button>
                <a class="boutonLateral" href="AcceuilSpot">Vers l'Acceuil Spot</a>
            </section>
            <footer>.</footer>
        </nav>
        <main>
            <header>
                <h3> Nos Spots </h3>
            </header>
            <section>
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
                                    <c:set var="labelBouttonSpot" scope="page" value="-"/>
                                    <c:set var="couleurFondSpot" scope="page" value="background-color: slategray; color: black;  font-size: 18px;"/>
                                    <c:if test="${requestScope.idValSpot == dbSpot.getIdspot()}" var="nope">
                                        <c:set var="couleurFondSpot" scope="page" value="background-color: slategray; color: black; font-weight: bold; font-size: 20px;"/>
                                        <c:set var="labelBouttonSpot" scope="page" value="+"/>
                                    </c:if>
                                    <td>${dbSpot.getNom()}</td>
                                    <td>${dbSpot.getLocalisation()}</td>
                                    <td>${dbSpot.getClassification()}</td>
                                    <td >
                                        <button style="${couleurFondSpot}  border-radius: 30%; align-self: center;"
                                                name="idValSpot"
                                                type="submit"
                                                formaction="AdminSelectionSpot"
                                                formmethod="get"
                                                value="${dbSpot.getIdspot()}"
                                                formtarget="_self"> ${labelBouttonSpot}
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div>
                    <label for="navSelectionComments" >Commentaires</label>
                    <form id="navSelectionComments" class="formSimple">
                        <table id="tableComments" class="bordered">
                            <thead>
                            <tr>
                                <c:set var="labelBouttonModer" scope="page" value="Mod."/>
                                <c:set var="labelBouttonSuppr" scope="page" value="Sup."/>
                                <c:set var="couleurFondCmt" scope="page" value="background-color: slategray; color: black;  font-size: 18px;"/>
                                <c:if test="${requestScope.idValSpot == dbSpot.getIdspot()}" var="nope">
                                    <c:set var="couleurFondCmt" scope="page" value="background-color: slategray; color: black; font-weight: bold; font-size: 20px;"/>
                                </c:if>

                                <th>Titre</th>
                                <th>Commentaire</th>
                                <th>Suppr.</th>
                                <th>Modér.</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="dbCommentaire" items="${requestScope.dbCommentaires}">
                                <tr>
                                    <td>${dbCommentaire.getNom()}</td>
                                    <td>${dbCommentaire.getTexte()}</td>
                                    <td>
                                        <button style="${couleurFondCmt}  border-radius: 30%; align-self: center;"
                                                name="idValCmt"
                                                type="submit"
                                                formaction="AdminSupprimerCmt"
                                                formmethod="post"
                                                value="${dbCommentaire.getIdcommentaire()}"
                                                formtarget="_self"> ${labelBouttonSuppr}
                                        </button>
                                    </td>
                                    <td>
                                        <button style="${couleurFondCmt}  border-radius: 30%; align-self: center;"
                                                name="idValCommentaire"
                                                type="submit"
                                                formaction="AdminSelectModereCommentaire"
                                                formmethod="get"
                                                value="${dbCommentaire.getIdcommentaire()}"
                                                formtarget="_self"> ${labelBouttonModer}
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
                                <textarea id="inputcommentaire" name="inputcommentaire" >[Modération] : ${requestScope.dbCommentaire.texte}</textarea>
                            </fieldset>
                            <fieldset class="actions">
                                <button style="border-radius: 30%;"
                                        class="boutonFormSimple"
                                        name="commentaireSpot"
                                        type="submit"
                                        formaction="AdminModereCommentaire"
                                        formmethod="post"
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
                <label for="tableAsideComments"  style="font-size: larger ">Commentaires</label>
                <table id="tableAsideComments" class="bordered">
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
        </aside>
    </div>
    <footer>
        <h1>@2019 Projet 06</h1>
    </footer>
</div>
</body>
</html>