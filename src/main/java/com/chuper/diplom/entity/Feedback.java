package com.chuper.diplom.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedbackId;

    private String feedbackText;

    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private Integer countStar;

    public Feedback(String feedbackText, Accommodation accommodation, Account account, Integer countStar) {
        this.feedbackText = feedbackText;
        this.accommodation = accommodation;
        this.account = account;
        this.countStar = countStar;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getCountStar() {
        return countStar;
    }

    public void setCountStar(Integer countStar) {
        this.countStar = countStar;
    }

    public Feedback() {
    }



}
