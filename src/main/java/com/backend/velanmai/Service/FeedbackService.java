package com.backend.velanmai.Service;

import com.backend.velanmai.Common.ApiResponse;
import com.backend.velanmai.DTO.FeedbackDto;
import com.backend.velanmai.Entity.Feedback;
import com.backend.velanmai.Entity.User;
import com.backend.velanmai.Repository.FeedbackRepository;
import com.backend.velanmai.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity addFeedback(FeedbackDto feedbackDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userRepository.findByEmail(auth.getName());
        Feedback feedback=new Feedback();
        feedback.setContent(feedbackDto.getContent());
        feedback.setUser(user);
        feedback.setEmail(feedbackDto.getEmail());
        feedback.setCreated_At(new Date(System.currentTimeMillis()));
        feedbackRepository.save(feedback);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData(feedback);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse.getData());
    }

    public ResponseEntity fetchFeedback() {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData(feedbackRepository.findAll());
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse.getData());
    }
}
