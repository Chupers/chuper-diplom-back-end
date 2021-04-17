package com.chuper.diplom.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserFacade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @OneToOne
    private Account account;

    @NotNull
    private String password;

    @NotNull
    private String userName;

    @NotNull
    private String userRole;

    private String confirmCode;

    public UserFacade() {
    }

}
