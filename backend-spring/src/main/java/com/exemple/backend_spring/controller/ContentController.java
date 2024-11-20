package com.exemple.backend_spring.controller;

import com.exemple.backend_spring.dto.ContentDTO;
import com.exemple.backend_spring.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public List<ContentDTO> getAllContents() {
        return contentService.getAllContents();
    }

    @GetMapping("/{id}")
    public ContentDTO getContentById(@PathVariable Long id) {
        return contentService.getContentById(id);
    }

    @PostMapping
    public ContentDTO createContent(@RequestBody ContentDTO contentDTO) {
        return contentService.createContent(contentDTO);
    }

    @PutMapping("/{id}")
    public ContentDTO updateContent(@PathVariable Long id, @RequestBody ContentDTO contentDTO) {
        return contentService.updateContent(id, contentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
    }
}
