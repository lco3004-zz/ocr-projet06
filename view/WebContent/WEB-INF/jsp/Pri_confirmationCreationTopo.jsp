<%--
  Created by IntelliJ IDEA.
  User: cordier
  Date: 30/08/2019
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ok creation</title>
</head>
<body>
 <p> Topo Cree</p>
 <p>
     ${ requestScope.dbTopo.getIdtopo()}
         <br>
     ${ requestScope.dbTopo.getNom()}
         <br>
     ${ requestScope.dbTopo.getLieu()}
         <br>
     ${ requestScope.dbTopo.getResume()}
         <br>
     ${ requestScope.dbTopo.getDateDeParution() }
         <br>
     ${ requestScope.dbTopo.getEstPublie()}
         <br>
     ${ requestScope.dbTopo.getEtatReservation()}
         <br>
     ${ requestScope.dbTopo.getGrimpeurByGrimpeurIdgrimpeur().getIdgrimpeur()}

         <br>
         <a href="../index.jsp" >Retour Acceuil</a>
 </p>
</body>
</html>
