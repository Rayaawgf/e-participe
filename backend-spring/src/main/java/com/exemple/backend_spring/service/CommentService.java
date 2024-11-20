package com.exemple.backend_spring.service;

import com.exemple.backend_spring.dto.CommentDTO;
import com.exemple.backend_spring.model.Comment;
import com.exemple.backend_spring.repository.CommentRepository;
import com.exemple.backend_spring.repository.ContentRepository;
import com.exemple.backend_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, ContentRepository contentRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
    }

    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> {
                    CommentDTO dto = new CommentDTO();
                    dto.setId(comment.getId());
                    dto.setText(comment.getText());
                    dto.setUserId(comment.getUser().getId());
                    dto.setContentId(comment.getContent().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public CommentDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            CommentDTO dto = new CommentDTO();
            dto.setId(comment.getId());
            dto.setText(comment.getText());
            dto.setUserId(comment.getUser().getId());
            dto.setContentId(comment.getContent().getId());
            return dto;
        }
        return null;
    }

    public List<CommentDTO> getCommentsByContentId(Long contentId) {
        return commentRepository.findByContentId(contentId)
                .stream()
                .map(comment -> {
                    CommentDTO dto = new CommentDTO();
                    dto.setId(comment.getId());
                    dto.setText(comment.getText());
                    dto.setUserId(comment.getUser().getId());
                    dto.setContentId(comment.getContent().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setUser(userRepository.findById(commentDTO.getUserId()).orElse(null));
        comment.setContent(contentRepository.findById(commentDTO.getContentId()).orElse(null));
        Comment savedComment = commentRepository.save(comment);
        commentDTO.setId(savedComment.getId());
        return commentDTO;
    }

    public CommentDTO updateComment(Long id, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setText(commentDTO.getText());
            comment.setUser(userRepository.findById(commentDTO.getUserId()).orElse(null));
            comment.setContent(contentRepository.findById(commentDTO.getContentId()).orElse(null));
            commentRepository.save(comment);
            return commentDTO;
        }
        return null;
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
