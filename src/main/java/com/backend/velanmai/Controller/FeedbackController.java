package com.backend.velanmai.Controller;

import com.backend.velanmai.DTO.FeedbackDto;
import com.backend.velanmai.Repository.FeedbackRepository;
import com.backend.velanmai.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://letsfarming.netlify.app")
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/addfeedback")
    public ResponseEntity addFeedback(@RequestBody FeedbackDto feedbackDto)
    {

        return feedbackService.addFeedback(feedbackDto);
    }
    @GetMapping("/fetchFeedback")
    public ResponseEntity fetchFeedback()
    {
        return feedbackService.fetchFeedback();
    }
}
