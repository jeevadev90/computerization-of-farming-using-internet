package com.backend.velanmai.Service;

import com.backend.velanmai.Common.ApiResponse;
import com.backend.velanmai.DTO.CommentDto;
import com.backend.velanmai.Entity.Comments;
import com.backend.velanmai.Entity.User;
import com.backend.velanmai.Entity.UserArticle;
import com.backend.velanmai.Repository.CommentsRepository;
import com.backend.velanmai.Repository.UserAtriclesRepository;
import com.backend.velanmai.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private UserAtriclesRepository userAtriclesRepository;
    public ResponseEntity addComment(CommentDto commentDto) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userRepository.findByEmail(authentication.getName());
        Optional<UserArticle> userArticle=userAtriclesRepository.findById(commentDto.getId());

        ApiResponse apiResponse=new ApiResponse();
        Comments comments=new Comments();
        comments.setComment(commentDto.getComment());
        comments.setUser(user);
        comments.setUserArticle(userArticle.get());
        comments.setCreated_At(new Date(System.currentTimeMillis()));
        commentsRepository.save(comments);
        apiResponse.setData1(comments);
        return  ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    public ResponseEntity getComment(CommentDto commentDto) {
        ApiResponse apiResponse=new ApiResponse();
        List<Comments> comments=commentsRepository.findByUserArticleId(commentDto.getId());
        apiResponse.setData1(comments);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
