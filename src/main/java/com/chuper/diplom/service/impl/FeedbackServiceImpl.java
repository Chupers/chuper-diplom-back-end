package com.chuper.diplom.service.impl;


import com.chuper.diplom.entity.Accommodation;
import com.chuper.diplom.entity.Account;
import com.chuper.diplom.entity.Feedback;
import com.chuper.diplom.entity.dto.AccommodationDto;
import com.chuper.diplom.repository.FeedbackRepository;
import com.chuper.diplom.service.AccommodationService;
import com.chuper.diplom.service.AccountService;
import com.chuper.diplom.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final AccountService accountService;
    private final AccommodationService accommodationService;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, AccountService accountService, AccommodationService accommodationService) {
        this.feedbackRepository = feedbackRepository;
        this.accountService = accountService;
        this.accommodationService = accommodationService;
    }

    @Override
    public AccommodationDto addFeedback(Long accommodationId, String text, Integer count) {
        Account account = accountService.getActiveAccount();
        Accommodation accommodation = accommodationService.getById(accommodationId);
        Feedback feedback = new Feedback(text,accommodation,account,count);
        feedback = feedbackRepository.save(feedback);
        accommodation.getFeedbackList().add(feedback);

        return accommodationService.saveAccommodation(accommodation);
    }
}
