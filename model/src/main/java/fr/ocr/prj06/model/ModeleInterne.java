package fr.ocr.prj06.model;

import fr.ocr.prj06.utility.logs.LogsProjet;

import javax.persistence.EntityManager;

public abstract class ModeleInterne {
    protected LogsProjet logs;

    public ModeleInterne() {

    }

    protected abstract void setLogs();

    public abstract <E> void copyVersModele(E entity);

    public abstract <E> void copyVersEntity(E entity, EntityManager em) throws Exception;

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();


}
