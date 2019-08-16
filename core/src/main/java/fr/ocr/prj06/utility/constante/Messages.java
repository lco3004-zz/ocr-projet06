package fr.ocr.prj06.utility.constante;

/**
 * <p>
 *
 * @author laurent cordier
 * </p>
 */
public enum Messages {
    ;

    public enum ConstantesPgm {
        UNITE_DE_PERSISTANCE("persistenceUnitName");

        private String valeurConstante;

        ConstantesPgm(String s) {
            valeurConstante = s;
        }

        public String getValeurConstante() {
            return valeurConstante;
        }

    }

    public enum DebugMessages {
        X("");
        private String messageDebug;

        DebugMessages(String s) {
            messageDebug = s;
        }

        public String getMessageDebug() {
            return messageDebug;
        }
    }

    public enum ErreurMessages {
        CONTROLLER_JPA_FIND_ENTITY("findDbEntities - Erreur: "),
        CONTROLLER_JPA_ENTITY_COUNT("getdbEntityCount - Erreur: "),
        CONTROLLER_JPA_ENTITY_CONSTRUCTEUR("Constructeur EntityCtrl - Erreur: "),
        CONTROLLER_JPA_CREATE_COMMENTAIRE("Creation Commentaire - Erreur: "),
        CONTROLLER_JPA_UPDATE_COMMENTAIRE("Mise à jour Commentaire - Erreur: "),
        CONTROLLER_JPA_READ_COMMENTAIRE("Lecture un Commentaire - Erreur: "),
        CONTROLLER_JPA_READLISTE_COMMENTAIRE("Lecture Liste Commentaire - Erreur: "),
        CONTROLLER_JPA_DELETE_COMMENTAIRE("Suppression un Commentaire - Erreur: "),
        MODELINTERNE_COPY_VERS_ENTITY_TXTINACT("CopyVersEntity Transaction inactive - Erreur: "),
        ERREUR_GENERIC("Ne doit pas etre utilisée!");

        private String messageErreur;

        ErreurMessages(String s) {
            messageErreur = s;
        }

        public String getMessageErreur() {
            return messageErreur;
        }
    }

    public enum InfosMessages {
        LANCEMENT_APPLICATION("Lancement Application "),
        FIN_NORMALE_APPLICATION("Fin normale Application"),
        LANCEMENT_GESTION_DES_PARAMETRES("Lancement LireParametres"),
        CREATION_FICHIER_PARAMETRE("Creation du fichier Parametres - "),
        FIN_NORMALE_GESTION_DES_PARAMETRES("Fin Normale LireParametres"),
        CTRL_C("interuption programme par CTRL-C"),
        SORTIE_SUR_ESCAPECHAR("Sortie par escape char"),
        REMPLACEMENT_PAR_VALEUR_DEFAUT("Remplacement par la valeur par défaut =  "),
        RANDOM_ECHOUE_COLPARDEF("Le Random a échoué, les premières couleurs de la liste sont retenues"),
        TENTAIVE_DE_TRICHE_DEFENSEUR("Incoherence score entre calcul et saisie  - Suspicion de fraude"),
        SORTIE_ESCAPE_SAISIE_SCORE("Sortie sur escape saisie Score mode Defenseur, copie du score calculé");

        private String messageInformation;

        InfosMessages(String s) {
            messageInformation = s;
        }

        public String getMessageInfos() {
            return messageInformation;
        }
    }
}
/*
 * ***************************************************************************************************************
 *  the end
 * ***************************************************************************************************************
 */