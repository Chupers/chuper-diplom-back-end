package com.chuper.diplom.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @OneToOne
    private UserFacade userFacade;

    private Boolean isHost = false;

    @OneToOne
    private Accommodation accommodation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guestAccount")
    private List<RentalRecord> rentalRecordList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<Feedback> feedbackList;

    public Account() {
    }
}
