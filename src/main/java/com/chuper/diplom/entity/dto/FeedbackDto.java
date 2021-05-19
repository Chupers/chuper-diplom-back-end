package com.chuper.diplom.entity.dto;

import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.Account;

public class FeedbackDto {
    private Long feedbackId;
    private String feedbackText;
    private Integer countStar;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public Integer getCountStar() {
        return countStar;
    }

    public void setCountStar(Integer countStar) {
        this.countStar = countStar;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
}
