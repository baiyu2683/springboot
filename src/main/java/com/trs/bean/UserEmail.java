package com.trs.bean;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by zhangheng on 16-7-26.
 */
@Entity
@Table(name="useremail")
public class UserEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length=255)
    private String name;
    @Column(length=255)
    private String email;

    public UserEmail() {
    }

    public UserEmail(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
