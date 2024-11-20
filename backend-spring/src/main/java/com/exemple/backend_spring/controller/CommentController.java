package com.exemple.backend_spring.controller;

import com.exemple.backend_spring.dto.CommentDTO;
import com.exemple.backend_spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDTO> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public CommentDTO getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @GetMapping("/content/{contentId}")
    public List<CommentDTO> getCommentsByContentId(@PathVariable Long contentId) {
        return commentService.getCommentsByContentId(contentId);
    }


    @PostMapping
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO) {
        return commentService.createComment(commentDTO);
    }

    @PutMapping("/{id}")
    public CommentDTO updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        return commentService.updateComment(id, commentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}