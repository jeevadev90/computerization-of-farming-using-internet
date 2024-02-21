package com.backend.velanmai.Controller;

import com.backend.velanmai.DTO.CommentDto;
import com.backend.velanmai.Repository.CommentsRepository;
import com.backend.velanmai.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private CommentsService commentsService;

    @PostMapping("/addcomment")
    public ResponseEntity addComment(@RequestBody CommentDto commentDto)
    {
        return  commentsService.addComment(commentDto);
    }
    @GetMapping("/getcomment")
    public ResponseEntity getcomment(@RequestBody CommentDto commentDto)
    {
        return commentsService.getComment(commentDto);
    }
}
