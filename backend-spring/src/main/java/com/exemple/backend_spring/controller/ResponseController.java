package com.exemple.backend_spring.controller;

import com.exemple.backend_spring.dto.ResponseDTO;
import com.exemple.backend_spring.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {
    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping
    public List<ResponseDTO> getAllResponses() {
        return responseService.getAllResponses();
    }

    @GetMapping("/{id}")
    public ResponseDTO getResponseById(@PathVariable Long id) {
        return responseService.getResponseById(id);
    }

    @PostMapping
    public ResponseDTO createResponse(@RequestBody ResponseDTO responseDTO) {
        return responseService.createResponse(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateResponse(@PathVariable Long id, @RequestBody ResponseDTO responseDTO) {
        return responseService.updateResponse(id, responseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteResponse(@PathVariable Long id) {
        responseService.deleteResponse(id);
    }
}
