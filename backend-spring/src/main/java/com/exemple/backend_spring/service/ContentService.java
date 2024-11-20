package com.exemple.backend_spring.service;

import com.exemple.backend_spring.dto.ContentDTO;
import com.exemple.backend_spring.enums.ContentType;
import com.exemple.backend_spring.model.Content;
import com.exemple.backend_spring.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {
    private final ContentRepository contentRepository;

    @Autowired
    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public List<ContentDTO> getAllContents() {
        return contentRepository.findAll()
                .stream()
                .map(content -> {
                    ContentDTO dto = new ContentDTO();
                    dto.setId(content.getId());
                    dto.setTitle(content.getTitle());
                    dto.setBody(content.getBody());
                    dto.setCreatedDate(content.getCreatedDate());
                    dto.setCategory(content.getCategory());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ContentDTO getContentById(Long id) {
        Content content = contentRepository.findById(id).orElse(null);
        if (content != null) {
            ContentDTO dto = new ContentDTO();
            dto.setId(content.getId());
            dto.setTitle(content.getTitle());
            dto.setBody(content.getBody());
            dto.setCreatedDate(content.getCreatedDate());
            dto.setCategory(content.getCategory());
            return dto;
        }
        return null;
    }

    public ContentDTO createContent(ContentDTO contentDTO) {
        Content content = new Content();
        content.setTitle(contentDTO.getTitle());
        content.setBody(contentDTO.getBody());
        content.setCreatedDate(contentDTO.getCreatedDate());
        content.setCategory(ContentType.valueOf(contentDTO.getCategory().name())); // Convertir la chaîne en énumération
        Content savedContent = contentRepository.save(content);
        contentDTO.setId(savedContent.getId());
        return contentDTO;
    }

    public ContentDTO updateContent(Long id, ContentDTO contentDTO) {
        Content content = contentRepository.findById(id).orElse(null);
        if (content != null) {
            content.setTitle(contentDTO.getTitle());
            content.setBody(contentDTO.getBody());
            content.setCategory(ContentType.valueOf(contentDTO.getCategory().name()));
            contentRepository.save(content);
            return contentDTO;
        }
        return null;
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public List<ContentDTO> getContentsByCategory(ContentType category) {
        return contentRepository.findByCategory(category)
                .stream()
                .map(content -> {
                    ContentDTO dto = new ContentDTO();
                    dto.setId(content.getId());
                    dto.setTitle(content.getTitle());
                    dto.setBody(content.getBody());
                    dto.setCreatedDate(content.getCreatedDate());
                    dto.setCategory(content.getCategory());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}