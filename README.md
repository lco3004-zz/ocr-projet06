#  MODEL_V7 - au 19/09/2019

# Couverture fonctionnelle 100%
# Responsive TODO : pourquoi du responsive (UX/Ergonomie) sur un projet qui ne prend pas en compte l'IHM donc qui n'évalue pas ... l'UX/ergonomie ??

##
## Log4J- V2.12.1 ${CATALINA_HOME}/logs/projet06 
## PostgreSql 11
## JPA 2.1
## Hibernate 5.4.4.Final
## JSP EI+JSTL - pas de fragment , pas d'include pas de tag spécialisé - pas de js
## jUnit : hors scope - sera traité au projet 7 ou 8 (je ne sais plus)

# Architecture :  
## Model :  (Jpa/Hibernate) - fournit des controleurs Entitées pour gérer CRUD
## Business : (appel controleurs du Model selon la table/Entité concernée) 
## View  : Presentation (JSP) et Controller webapp (servlets)
### aucune JSP accesible via un client (toutes sous WEB-INF)
### Page statique 


# Séquence (servlet/jsp:
## Depuis un "Client" (href, submit) : 
### "get"-> servlet(doget) {traitement[DAO, session] forward{[Jsp,Servlet]}}
### "post"-> servlet(dopost) {traitement[DAO, session] forward{[Jsp,Servlet]}}

# Contrôle Accès (filter):
## L'accès aux  ressources (servlet) qui sont réservés aux Grimpeurs et aux membres est "filtré"
### "get"-> filter(?connecté) -> servlet(doget)....
### "post"-> filter(?connecté) -> servlet(dopost)....
## Deux niveaux d'accsè  Grimpeur (créer, partager, commenter), Membre ( tager, modérer, supprimer)
 
# DAO (listener) :
## Listener :
### au déploiement : "Open DAO" -> Entity Manager Factory est initialisé
### au retrait : "CLose DAO" -> Entity Manager Factory est fermé

# DAO (persistence) :
## Validation du modèle
### Option est "validate" : hibernate.hbm2ddl.auto" value="validate
## Cache JPA / Cache niveau II hibernate : non utilisé (ou non compris c'est pareil)

# DAO (requête) :
## métalmodèle statique
## API Criteria

# Maven
## filtering
### Properties : nom de l'unité de persistance 
## multi-modules : aucun intérêt  pour projet 06 (sera utilisé en projet 07)
## méta-modèle statique (mavenisé) 

# Sécurité
## TODO : javax.security pour hash mdp

# Déploiement
## tomcat : TODO





 




