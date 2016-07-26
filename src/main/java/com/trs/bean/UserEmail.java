package com.trs.bean;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by zhangheng on 16-7-26.
 */
@Entity
@Table(name="useremail")
public class UserEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column()
    private String name;

    private String email;

    public UserEmail() {
    }
}
