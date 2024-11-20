package com.exemple.backend_spring.service;


import com.exemple.backend_spring.dto.QuestionDTO;
import com.exemple.backend_spring.model.Content;
import com.exemple.backend_spring.model.Question;
import com.exemple.backend_spring.model.Response;
import com.exemple.backend_spring.repository.ContentRepository;
import com.exemple.backend_spring.repository.QuestionRepository;
import com.exemple.backend_spring.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ContentRepository contentRepository;
    private final ResponseRepository responseRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, ContentRepository contentRepository, ResponseRepository responseRepository) {
        this.questionRepository = questionRepository;
        this.contentRepository = contentRepository;
        this.responseRepository = responseRepository;
    }

    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(question -> {
                    QuestionDTO dto = new QuestionDTO();
                    dto.setId(question.getId());
                    dto.setQuestionText(question.getQuestionText());
                    dto.setContentId(question.getContent().getId());
                    dto.setResponseType(question.getResponseType());
                    dto.setResponseIds(question.getResponses().stream().map(Response::getId).collect(Collectors.toList()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question != null) {
            QuestionDTO dto = new QuestionDTO();
            dto.setId(question.getId());
            dto.setQuestionText(question.getQuestionText());
            dto.setContentId(question.getContent().getId());
            dto.setResponseType(question.getResponseType());
            dto.setResponseIds(question.getResponses().stream().map(Response::getId).collect(Collectors.toList()));
            return dto;
        }
        return null;
    }

    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestionText(questionDTO.getQuestionText());
        question.setResponseType(questionDTO.getResponseType());


        Content content = contentRepository.findById(questionDTO.getContentId()).orElse(null);
        question.setContent(content);


        Question savedQuestion = questionRepository.save(question);


        questionDTO.setId(savedQuestion.getId());


        if (questionDTO.getResponseIds() != null) {
            List<Response> responses = responseRepository.findAllById(questionDTO.getResponseIds());
            question.setResponses(responses);
            questionRepository.save(question);
        }

        return questionDTO;
    }

    public QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question != null) {
            question.setQuestionText(questionDTO.getQuestionText());
            question.setResponseType(questionDTO.getResponseType());

            Content content = contentRepository.findById(questionDTO.getContentId()).orElse(null);
            question.setContent(content);

            // Mettre à jour les réponses associées
            if (questionDTO.getResponseIds() != null) {
                List<Response> responses = responseRepository.findAllById(questionDTO.getResponseIds());
                question.setResponses(responses);
            }

            questionRepository.save(question);
            return questionDTO;
        }
        return null;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
