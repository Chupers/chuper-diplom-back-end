package com.chuper.diplom.service;

import com.chuper.diplom.entity.dto.AccommodationDto;

public interface FeedbackService {

    AccommodationDto addFeedback(Long accommodationId, String text, Integer count);
}
