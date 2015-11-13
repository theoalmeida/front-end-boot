package com.almeida.entity.user;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by theo on 12/11/15.
 */
@Table(name = "authorities")
@Entity
public class Authorities implements Serializable {


    private static final long serialVersionUID = -1876962669450068280L;

    @Id
    @Column(name = "username")
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Role authority;


    public Authorities() {
    }

    public Authorities(Role authority, String userName) {
        this.authority = authority;
        this.userName = userName;
    }


    public void setAuthority(Role authority) {
        this.authority = authority;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getAuthority() {
        return authority;
    }
}
