# ocr-projet06
##
## retour vers jdk11 (support de hibernate : de 8 à 11)
## branche fonctionnelle (insert/update/converters) - nettoyage des imports dans pom
## que hibernate-core 5.44.final
##
## TODO : utiliser @transient sur les Collections pour bénéficier de Cascade (persist)
##

# Rappels
## Framework JPA - provider hibernate 5.4.4
## Architecture :  
### Model :  (Jpa/Hibernate) - fournit des controleurs Entitées pour gérer CRUD
### Business : (appel controleurs du Model selon la table/Entité concernée) avec cntrome validaté de la demande (webapp)
### View  : Presentation (JSP) et Controller webapp (servlets)

## Notes


## Notes // à supprimer avant soutenance
### Cache L2 Hibernate - non utilisé (erreur)  




