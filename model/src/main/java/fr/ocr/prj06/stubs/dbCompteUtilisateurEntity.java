package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "compte_utilisateur", schema = "ocr_projet06")
public class dbCompteUtilisateurEntity {
    private int idCompte;
    private String mdp;
    private String login;
    private String droits;

    @Id
    @Column(name = "id_compte", nullable = false)
    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    @Basic
    @Column(name = "mdp", nullable = false, length = 45)
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 64)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "droits", nullable = false, length = 1)
    public String getDroits() {
        return droits;
    }

    public void setDroits(String droits) {
        this.droits = droits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbCompteUtilisateurEntity that = (dbCompteUtilisateurEntity) o;
        return idCompte == that.idCompte &&
                Objects.equals(mdp, that.mdp) &&
                Objects.equals(login, that.login) &&
                Objects.equals(droits, that.droits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompte, mdp, login, droits);
    }
}
