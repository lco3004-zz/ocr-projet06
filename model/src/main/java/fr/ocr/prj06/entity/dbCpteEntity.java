package fr.ocr.prj06.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cpte", schema = "ocr_projet06")
public class dbCpteEntity implements Serializable {
    private int userIduser;
    private String mdp;
    private String login;
    private String droits;
    private dbUserEntity userByUserIduser;

    @Id
    @Column(name = "user_iduser", nullable = false)
    public int getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(int userIduser) {
        this.userIduser = userIduser;
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
    @Column(name = "droits", nullable = false, length = 2)
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
        dbCpteEntity that = (dbCpteEntity) o;
        return userIduser == that.userIduser &&
                Objects.equals(mdp, that.mdp) &&
                Objects.equals(login, that.login) &&
                Objects.equals(droits, that.droits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userIduser, mdp, login, droits);
    }


    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", nullable = false)
    public dbUserEntity getUserByUserIduser() {
        return userByUserIduser;
    }

    public void setUserByUserIduser(dbUserEntity userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }
}
