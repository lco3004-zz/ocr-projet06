#  MODEL_V7 - au 19/09/2019

# Prérequis :
## Firefox Quantum V69
## PostgreSql V11, JDBC Driver JDBC42
## Apache Tomcat Version 9
## Windows 10Pro 64bits - v1903 - build 18362.356
## Windows Defender (4.18.1908.7, 1.1.16400.2, 1.303.119.0, 1.303.119.0)
## être adminsitrateur de la machine où est installé Tomcat/PostgreSql

# Généralités : 
## Architecture
### Model :  Jpa/Hibernate / Api Criteria avec métamodèle statique (Mavenisé)
### Business : lien entre View et Model
### View  : JEE : Presentation = JSP placé dans WEB-INF ,  Controller webapp = servlets,listener,filter
## Contrôle Accès (filter):
### L'accès aux  ressources (servlet) qui sont réservés aux Grimpeurs et aux membres est "filtré"
### Deux niveaux d'accès  Grimpeur (créer, partager, commenter), Membre ( tager, modérer, supprimer)
## Entity Manager, via un listener :
### au déploiement -> Entity Manager Factory est ouvert
### au retrait  -> Entity Manager Factory est fermé
## Persistence
### Validation du modèle : hibernate.hbm2ddl.auto" value="validate
## Makefile : sous Maven
### filtering: Properties - nom de l'unité de persistance 
### mono-module

# Installation PostgreSql + jDBC + database/tables
## installation par défaut : service démarrage auto - compte : "Service réseau", 
## Path modifié pour  {rép. instal Postgres}/bin,
### Prérequis : le répertoire 'c:\bd_data' doit exister (mkdir 'c:\bd_data') - controle total pour admin, system,
## lancer la commande suivante , les chemins dépendent de l'installation PostgreSql et Projet06:
### {Rép. PostgreSql}/bin/psql -h localhost -p 5432 -U postgres -f {Rép. Projet06}/sql/prep_projet06.sql
### le mot de passe de postgres est celui saisi lors de l'installation 
### le role rl_projet06, le tablespace ts_projet06 et la base db_projet06 sont créés.
## lancer pgadmin V4 , 
### modifier le password de rl_projet 06 : password = projet06
### depuis tools/query tools , ouvrez {Rép. Projet06}/sql/create_tbl.sql et exécuter le 
### vérifier que 7 tables sont crées
## la table grimpeur contient un tupple qui est un grimpeur membre  
### nom = laurent , passwd = laurent ,role =MEMBRE
### le mdp est hashé 
# fin






 




