#  MODEL_V6
##
## Log4J- V2.12.1 ${CATALINA_HOME}/logs/projet06 
## PostgreSql 11
## JPA 2.1
## Hibernate 5.4.4.Final
## JSP EI+JSTL (mavenisé)

# Architecture :  
## Model :  (Jpa/Hibernate) - fournit des controleurs Entitées pour gérer CRUD
## Business : (appel controleurs du Model selon la table/Entité concernée) avec cntrome validaté de la demande (webapp)
## View  : Presentation (JSP) et Controller webapp (servlets)
### aucune JSP accesible via un client (tous sous WEB-INF)
### aucune page html accesible depuis un client
### Page statique (sauf formulaire qui est nativement dynamique via les contrôles (expression régulière))


# Séquence (servlet/jsp:
## Depuis un "Client" (href, submit) : traitement exclusif via servlet
### "get"-> servlet(doget) {traitement[DAO, session] forward{[Jsp,Servlet]}}
### "post"-> servlet(dopost) {traitement[DAO, session] forward{[Jsp,Servlet]}}

# Contrôle Accès (filter):
## L'accès aux  ressources (servlet) qui sont réservés aux users connectés est "filtré"
### "get"-> filter(?connecté) -> servlet(doget)....
### "post"-> filter(?connecté) -> servlet(dopost)....
 
# DAO (listener) :
## Listener  déclenché :
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
## mutli-modules : abandon - aucun intérêt dans le contexte maquette type OCR (pas de suivi : one shoot)
## méta-modèle (DAO)
### plugin helper et processor mis en oeuvre

# Sécurité
## TODO : javax.security pour hash mdp
## TODO : https 
## TODO : rétiré le mdp en clair dans les traces

# Déploiement
## tomcat : TODO

# Refactoring
## annotation sur Entity du modèle DAO : décision selon temps restant
## Spring Sécurité : Abandon 
## Thymeleaf : ? 
## Page dynamique (js) - hors scope maquette type  OCR





 




