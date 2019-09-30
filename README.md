#  MODEL_V7 - au 19/09/2019

# Prérequis au bon fct de la maquette:
## Firefox Quantum V69
## PostgreSql V11, JDBC Driver JDBC42
## Apache Tomcat/9.0.24 - JVM 11.0.4+10-LTS  (!!!)
## Windows 10Pro v1903 - build 18362.356
## JDK 11.0.4

# Architecture
## Model :  Jpa/Hibernate 
## Business : lien entre View et Model
## View  : JEE : Presentation = JSP placé dans WEB-INF ,  Controller webapp = servlets,listener,filter
# Contrôle Accès (filter):
## L'accès aux  ressources (servlet) qui sont réservés aux Grimpeurs et aux membres est "filtré"
## Deux niveaux d'accès  Grimpeur (créer, partager, commenter), Membre ( tager, modérer, supprimer)
# Entity Manager, via un listener application:
## au déploiement appli -> Entity Manager Factory est ouvert
## au retrait appli -> Entity Manager Factory est fermé
# Persistence
## Validation du modèle : hibernate.hbm2ddl.auto" value="validate
## Mode eager/lazy par défaut 
## Api Criteria avec métamodèle statique (Mavenisé) 
# Makefile : sous Maven
## filtering: Properties - nom de l'unité de persistance 
## mono-module
# Installation Database - tables
## installation PostgreSql par défaut, 
## Prérequis : le répertoire 'c:\bd_data' doit exister - controle total pour admin, system,
## Création : rôle rl_projet06, base db_projet06 et tablespace ts_projet06 :
### psql -h localhost -p 5432 -U postgres -f {repertoire projet06}/sql/prep_projet06.sql
## modif mdp du rôle rl_projet06 
### Sous pgadmin V4,  modifier le password de rl_projet 06 : password = projet06
## Création tables
### Sous pgadmin V4/tools/query tools , exécuter {repertoire projet06}/sql/create_tbl.sql
## Jeu De test
### Sous pgadmin V4, selection de db_projet06 puis choisir "restore" : fichier {repertoire projet06}/sql/dump_data.sql 
## Utilisateur fourni par défaut
### nom = laurent , passwd = laurent ,role =MEMBRE
### Note : le mdp est hashé. 
# fin






 




