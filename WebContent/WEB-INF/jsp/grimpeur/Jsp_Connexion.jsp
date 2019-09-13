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
                <a class="boutonLateral" href="Inscription">Inscription</a>
                <a class="boutonLateral" href="home">Vers l'Acceuil</a>
            </section>
            <footer>.</footer>
        </nav>
        <section>
            <h3> Connexion </h3>
            <form id="login" class="formSimpleLogin">
                <fieldset class="labels">
                    <label for="username">Votre Nom : </label>
                    <label for="password">Mot de Passe : </label>
                </fieldset>
                <fieldset class="inputs">
                    <input id="username" name="nomGrimpeur" size="64" type="text" placeholder="Nom" autofocus required>
                    <input id="password" name="mdpGrimpeur" size="64" type="password" placeholder="Mot de Passe"
                           required>
                </fieldset>
                <fieldset class="actions">
                    <button class="boutonFormSimple"
                            name="login"
                            form="login"
                            type="submit"
                            formaction="Connexion"
                            formmethod="post"
                            value="login"
                            formtarget="_self"> Se connecter
                    </button>
                </fieldset>
                <fieldset class="informations">
                    <div id="infoerr">
                        <p>
                            ${requestScope.messageCnx}
                        </p>
                    </div>
                </fieldset>
            </form>
        </section>
        <aside>
            <article>
                <label style="ont-size: larger  ">Spots</label>
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
                <label style="ont-size: larger  ">Topos</label>
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
