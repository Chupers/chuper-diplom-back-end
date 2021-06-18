package com.chuper.diplom.entity.dto;

import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.Feedback;
import com.chuper.diplom.entity.RentalRecord;
import com.chuper.diplom.entity.UserFacade;


import java.util.List;

public class AccountDto {
    private Long accountId;
    private Boolean isHost;
    private List<FeedbackDto> feedbackList;
    private UserFacadeDto userFacade;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public UserFacadeDto getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacadeDto userFacade) {
        this.userFacade = userFacade;
    }

    public Boolean getHost() {
        return isHost;
    }

    public void setHost(Boolean host) {
        isHost = host;
    }

    public List<FeedbackDto> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<FeedbackDto> feedbackList) {
        this.feedbackList = feedbackList;
    }
}
