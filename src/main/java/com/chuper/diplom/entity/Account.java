package com.chuper.diplom.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @OneToOne
    private UserFacade userFacade;

    private Boolean isHost;

    @OneToOne
    private Accommodation accommodation;

    @ElementCollection
    private Set<Long> favoriteAccommodationId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guestAccount")
    private List<RentalRecord> rentalRecordList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<Feedback> feedbackList;

    public Account() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public Boolean getHost() {
        return isHost;
    }

    public void setHost(Boolean host) {
        isHost = host;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public List<RentalRecord> getRentalRecordList() {
        return rentalRecordList;
    }

    public void setRentalRecordList(List<RentalRecord> rentalRecordList) {
        this.rentalRecordList = rentalRecordList;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public Set<Long> getFavoriteAccommodationId() {
        return favoriteAccommodationId;
    }

    public void setFavoriteAccommodationId(Set<Long> favoriteAccommodationId) {
        this.favoriteAccommodationId = favoriteAccommodationId;
    }
}
