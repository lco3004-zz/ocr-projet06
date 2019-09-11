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
            <header> .</header>
            <section>
                <a class="nav-link " href="#">Nos Topos</a>
                <a class="nav-link" href="AcceuilSpot">Nos Spots</a>
                <a class="nav-link " href="Connexion">Connexion</a>
            </section>
            <footer> .</footer>
        </nav>

        <section>
            <h3> detail</h3>
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