package mdb.de.rating;

import java.security.Timestamp;

/**
 * Created by LethmateB on 04.11.2015.
 */
public class User {

    public String alias;
    public String email;
    public String password_hash;
    public Boolean active;
    public Timestamp last_login;

    public User() {
        super();
    }

    public User(String alias, String email, String password_hash, Boolean active, Timestamp last_login) {
        super();
        this.alias = alias;
        this.email = email;
        this.password_hash = password_hash;
        this.active = active;
        this.last_login = last_login;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }
}
