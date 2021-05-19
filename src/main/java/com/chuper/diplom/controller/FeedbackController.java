package com.chuper.diplom.controller;

import com.chuper.diplom.entity.dto.AccommodationDto;
import com.chuper.diplom.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 10000)
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/addFeedback")
    public AccommodationDto addFeedback(@RequestParam(name = "id") Long id,
                                        @RequestParam(name = "text") String text,
                                        @RequestParam(name = "countStar") Integer countStar){
        return feedbackService.addFeedback(id, text, countStar);
    }
}
