package com.chuper.diplom.entity.dto;

import com.chuper.diplom.entity.*;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

public class AccommodationDto {

    private Long accommodationId;
    private AccommodationInfoDto accommodationInfo;
    private List<AccommodationCharacteristicDto> accommodationCharacteristicList;
    private Boolean favorite;
    private List<FeedbackDto> feedbackList;
    private Integer countReview;
    private Double rating;

    public Integer getCountReview() {
        return countReview;
    }

    public void setCountReview(Integer countReview) {
        this.countReview = countReview;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public List<FeedbackDto> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<FeedbackDto> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public AccommodationInfoDto getAccommodationInfo() {
        return accommodationInfo;
    }

    public void setAccommodationInfo(AccommodationInfoDto accommodationInfo) {
        this.accommodationInfo = accommodationInfo;
    }

    public List<AccommodationCharacteristicDto> getAccommodationCharacteristicList() {
        return accommodationCharacteristicList;
    }

    public void setAccommodationCharacteristicList(List<AccommodationCharacteristicDto> accommodationCharacteristicList) {
        this.accommodationCharacteristicList = accommodationCharacteristicList;
    }

}
