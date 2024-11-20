package com.exemple.backend_spring.service;

import com.exemple.backend_spring.dto.ResponseDTO;
import com.exemple.backend_spring.model.Response;
import com.exemple.backend_spring.repository.QuestionRepository;
import com.exemple.backend_spring.repository.ResponseRepository;
import com.exemple.backend_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository, QuestionRepository questionRepository, UserRepository userRepository) {
        this.responseRepository = responseRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public List<ResponseDTO> getAllResponses() {
        return responseRepository.findAll()
                .stream()
                .map(response -> {
                    ResponseDTO dto = new ResponseDTO();
                    dto.setId(response.getId());
                    dto.setAnswerText(response.getAnswerText());
                    dto.setQuestionId(response.getQuestion().getId());
                    dto.setUserId(response.getUser().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ResponseDTO getResponseById(Long id) {
        Response response = responseRepository.findById(id).orElse(null);
        if (response != null) {
            ResponseDTO dto = new ResponseDTO();
            dto.setId(response.getId());
            dto.setAnswerText(response.getAnswerText());
            dto.setQuestionId(response.getQuestion().getId());
            dto.setUserId(response.getUser().getId());
            return dto;
        }
        return null;
    }

    public ResponseDTO createResponse(ResponseDTO responseDTO) {
        Response response = new Response();
        response.setAnswerText(responseDTO.getAnswerText());
        response.setQuestion(questionRepository.findById(responseDTO.getQuestionId()).orElse(null));
        response.setUser(userRepository.findById(responseDTO.getUserId()).orElse(null));
        Response savedResponse = responseRepository.save(response);
        responseDTO.setId(savedResponse.getId());
        return responseDTO;
    }

    public ResponseDTO updateResponse(Long id, ResponseDTO responseDTO) {
        Response response = responseRepository.findById(id).orElse(null);
        if (response != null) {
            response.setAnswerText(responseDTO.getAnswerText());
            response.setQuestion(questionRepository.findById(responseDTO.getQuestionId()).orElse(null));
            response.setUser(userRepository.findById(responseDTO.getUserId()).orElse(null));
            responseRepository.save(response);
            return responseDTO;
        }
        return null;
    }

    public void deleteResponse(Long id) {
        responseRepository.deleteById(id);
    }
}