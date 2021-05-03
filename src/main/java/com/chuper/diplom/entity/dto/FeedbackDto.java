package com.chuper.diplom.entity.dto;

import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.Account;

public class FeedbackDto {
    private Long feedbackId;
    private String feedbackText;

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
}
