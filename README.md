# ocr-projet06
##  MODEL_V4
## ajout de synchronizd sur entitymanagerfactory (create et close) 
##
## RETRAIT de LOG4J : le cascade.persit fonctionne ! (ok)
## Ajout metamodel via maven pour Criteria (ok) - warning sur (supporte que jdk 1.6)
## Retour vers jdk11 (support de hibernate : de 8 à 11) (ok)
## Erreur sur Date corrigée (ok)
## Converters (ok) nécessitent jpa 2.1 (ko en 2.0)
## Utilise hibernate-core 5.44.final et javax.persistence 2.2
## Aucun Cache - ni JPA ni Hibernate (cache niveau 2)
## Ajout interface AutoCloseable sur Classe qui enrobe EntityManager
## Suppresion de la gestion des profiles dans Maven (inutile dans ce projet)
##
##  TODO : 
### un system de log qui fonctionne
### Injection via CDI et annotation dédiée au CRUD  (évite redondance de code)
##
## Architecture :  
### Model :  (Jpa/Hibernate) - fournit des controleurs Entitées pour gérer CRUD
### Business : (appel controleurs du Model selon la table/Entité concernée) avec cntrome validaté de la demande (webapp)
### View  : Presentation (JSP) et Controller webapp (servlets)

## Notes // à supprimer avant soutenance
### Cache L2 Hibernate - non utilisé (erreur)   
### retrait de log4J : conflit avec jpa/hibernate 
### retrait de c3p0 (provisoire)




