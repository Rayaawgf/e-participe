package com.exemple.backend_spring.repository;

import com.exemple.backend_spring.model.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropositionRepository extends JpaRepository<Proposition, Long> {
    // Méthodes personnalisées si nécessaire
}