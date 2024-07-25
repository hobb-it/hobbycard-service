package com.example.hobbyCardService.repo;

import com.example.hobbyCardService.model.HobbyCard;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HobbyCardRepository extends CrudRepository<HobbyCard, UUID> {
    Iterable<HobbyCard> findByUsername(String username);
    Iterable<HobbyCard> findByCategoryName(String categoryName);
    Iterable<HobbyCard> findByUsernameAndCategoryName(String username, String categoryName);
}
