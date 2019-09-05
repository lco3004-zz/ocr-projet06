<%--
  Created by IntelliJ IDEA.
  User: cordier
  Date: 30/08/2019
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=ISO-8859-15" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Création Topo Réussie</title>
</head>
<body>
<h3>
    Création Topo effectuée
</h3>
<br/>
<br/>

 <table>
     <thead>
     <tr>
         <th> idTopo </th>
         <th> dateCreation </th>
         <th> est_publie </th>
         <th> etat_reservation </th>
         <th> grimpeur_idgrimpeur </th>
         <th> nom </th>
         <th> lieu </th>
         <th> resume </th>
     </tr>
     </thead>
     <tbody>
         <c:forEach var="dbTopo"  items="${requestScope.dbTopos}">
             <tr>
                 <td>${dbTopo.getIdtopo()}</td>
                 <td>${dbTopo.getDateDeParution()}</td>
                 <td>${dbTopo.getEstPublie()}</td>
                 <td>${dbTopo.getEtatReservation()}</td>
                 <td>${dbTopo.getGrimpeurByGrimpeurIdgrimpeur(). getIdgrimpeur()}</td>
                 <td>${dbTopo.getNom()}</td>
                 <td>${dbTopo.getLieu()}</td>
                 <td>${dbTopo.getResume()}</td>
             </tr>
         </c:forEach>
     </tbody>
 </table>
 <br/>
 <br/>

 <footer >
     Retour Acceuil  <a href="<%=request.getContextPath()%>/index.jsp">Cliquez ici</a>
 </footer>

 </body>
</html>
