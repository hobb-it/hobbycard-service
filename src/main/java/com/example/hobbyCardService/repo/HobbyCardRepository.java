package com.example.hobbyCardService.repo;

import com.example.hobbyCardService.model.HobbyCard;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HobbyCardRepository extends CrudRepository<HobbyCard, UUID> {
    Iterable<HobbyCard> findByUser(UUID userId);
    Iterable<HobbyCard> findByCategory(UUID categoryId);
    Iterable<HobbyCard> findByUserAndCategory(UUID userId, UUID categoryId);
}
