package fr.ocr.model.entities;

import fr.ocr.model.constantes.UserProfile;
import fr.ocr.model.converters.JpaConvEnumUserToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "grimpeur",  schema = "public", catalog = "db_projet06")
public class DbGrimpeur implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idgrimpeur;
    private String userPass;
    private String userName;
    private UserProfile roleName;


    private Collection<DbSpot> spotsByIdgrimpeur;
    private Collection<DbTopo> toposByIdgrimpeur;

    public DbGrimpeur() {
        spotsByIdgrimpeur=new ArrayList<>();
        toposByIdgrimpeur=new ArrayList<>();
    }

    @Id
    @Column(name = "idgrimpeur")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdgrimpeur() {
        return idgrimpeur;
    }
    public void setIdgrimpeur(int idgrimpeur) {
        this.idgrimpeur = idgrimpeur;
    }

    @Basic
    @Column(name = "user_pass", nullable = false)
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Basic
    @Column(name = "user_name", nullable = false, unique = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "role_name", nullable = false)
    @Convert(converter = JpaConvEnumUserToString.class)
    public UserProfile getRoleName() {
        return roleName;
    }
    public void setRoleName(UserProfile roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbGrimpeur that = (DbGrimpeur) o;
        return idgrimpeur == that.idgrimpeur &&
                Objects.equals(userPass, that.userPass) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idgrimpeur, userPass, userName, roleName);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grimpeurByGrimpeurIdgrimpeur")
    public Collection<DbSpot> getSpotsByIduser() {
        return spotsByIdgrimpeur;
    }

    public void setSpotsByIduser(Collection<DbSpot> spotsByIdgrimpeur) {

        this.spotsByIdgrimpeur = spotsByIdgrimpeur;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "grimpeurByGrimpeurIdgrimpeur")
    public Collection<DbTopo> getToposByIduser() {

        return toposByIdgrimpeur;
    }

    public void setToposByIduser(Collection<DbTopo> toposByIdgrimpeur) {

        this.toposByIdgrimpeur = toposByIdgrimpeur;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append(" idUser: ").append(getIdgrimpeur())
                .append(" Nom: ").append(getUserName())
                .append(" Mdp: ").append(getUserPass())
                .append(" Role: ").append(getRoleName().toString())
                .toString();
    }

}
