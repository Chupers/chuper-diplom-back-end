package com.chuper.diplom.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Review {
    private final Integer countReview;
    private final Double rating;

    public Review(Integer countReview, Double rating) {
        this.countReview = countReview;
        this.rating = rating;
    }

    public Review() {
        this.countReview = 0;
        this.rating = (double) 0;
    }

    public Integer getCountReview() {
        return countReview;
    }

    public Double getRating() {
        return rating;
    }
}
