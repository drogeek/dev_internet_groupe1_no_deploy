package fr.univtln.groupe1.metier;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({@NamedQuery(
        name = "FIND USER",
        query = "select id from User u where u.name = :nameUser and u.pass= :passUser"
), @NamedQuery(
        name = "DEL_USER",
        query = "DELETE from User u where u.id=:idUser"
)})
public class User {
    @Id
    @GeneratedValue
    @XmlElement
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    @DefaultValue("")
    private String password;

    public User(String name, String pwd)
    {
        this.name = name;
        this.password = pwd ;
    }

    public User(String name)
    {
        this.name = name;
    }

    public String toString() {
        return "User{id='" + this.id + "'" + ", name=" + this.name + ", password=" + this.password + '}';
    }

    public int getId() {
        return this.id;
    }

    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }


    public static class UserBuilder {
        private String name;
        private String password;

        UserBuilder() {
        }

        public User.UserBuilder create(String name) {
            this.name = name;
            return this;
        }

        public User.UserBuilder create(String name, String pwd)
        {
            this.name = name;
            this.password = pwd ;
            return this;
        }

        public User build() {
            return new User(this.name);
        }

        public String toString() {
            return "User.UserBuilder(name=" + this.name + ")";
        }
    }

}
