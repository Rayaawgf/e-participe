package com.exemple.backend_spring.service;

import com.exemple.backend_spring.dto.PropositionDTO;
import com.exemple.backend_spring.model.Proposition;
import com.exemple.backend_spring.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropositionService {

    @Autowired
    private PropositionRepository propositionRepository;

    @Value("${upload.dir}") // Chemin du répertoire pour stocker les fichiers téléchargés
    private String uploadDir;

    public PropositionDTO createProposition(PropositionDTO propositionDTO, MultipartFile file) throws IOException {
        String filePath = storeFile(file);
        Proposition proposition = new Proposition();
        proposition.setTitle(propositionDTO.getTitle());
        proposition.setDocument(filePath);
        proposition.setUserId(propositionDTO.getUserId());
        proposition.setPublicationDate(LocalDateTime.now());
        proposition.setStatus(false);
        Proposition savedProposition = propositionRepository.save(proposition);
        return mapToDTO(savedProposition);
    }

    public List<PropositionDTO> getAllPropositions() {
        return propositionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PropositionDTO getPropositionById(Long id) {
        Proposition proposition = propositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposition not found"));
        return mapToDTO(proposition);
    }

    public PropositionDTO updateProposition(Long id, PropositionDTO propositionDTO) {
        Proposition proposition = propositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposition not found"));
        proposition.setTitle(propositionDTO.getTitle());
        proposition.setDocument(propositionDTO.getDocument()); // Vous pouvez gérer le fichier ici si nécessaire
        Proposition updatedProposition = propositionRepository.save(proposition);
        return mapToDTO(updatedProposition);
    }

    public void deleteProposition(Long id) {
        propositionRepository.deleteById(id);
    }

    private String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Failed to store empty file.");
        }

        Path destinationPath = Paths.get(uploadDir).resolve(file.getOriginalFilename());
        // Copier le fichier dans le répertoire de destination
        Files.copy(file.getInputStream(), destinationPath);
        return destinationPath.toString(); // Retourner le chemin du fichier
    }

    private PropositionDTO mapToDTO(Proposition proposition) {
        PropositionDTO dto = new PropositionDTO();
        dto.setId(proposition.getId());
        dto.setTitle(proposition.getTitle());
        dto.setDocument(proposition.getDocument());
        dto.setStatus(proposition.isStatus());
        dto.setUserId(proposition.getUserId());
        return dto;
    }
}