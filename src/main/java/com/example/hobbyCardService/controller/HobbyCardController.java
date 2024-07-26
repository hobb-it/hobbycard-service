package com.example.hobbyCardService.controller;

import com.example.hobbyCardService.model.HobbyCard;
import com.example.hobbyCardService.repo.HobbyCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/hobbycard")
@RequiredArgsConstructor
@Slf4j
public class HobbyCardController {

    private final HobbyCardRepository hobbyCardRepository;

    @GetMapping("/all")
    @CrossOrigin
    public Iterable<HobbyCard> getHobbyCards(
            @RequestParam(value="username", required=false) String username,
            @RequestParam(value="categoryName", required=false) String categoryName
    ) {
        List<HobbyCard> hobbyCards = new ArrayList<>();
        if (username != null && categoryName != null) {
            hobbyCardRepository.findByUsernameAndCategoryName(username, categoryName).forEach(hobbyCards::add);
        } else if (categoryName != null) {
            hobbyCardRepository.findByCategoryName(categoryName).forEach(hobbyCards::add);
        } else if (username != null) {
            hobbyCardRepository.findByUsername(username).forEach(hobbyCards::add);
        } else {
            hobbyCardRepository.findAll().forEach(hobbyCards::add);
        }
        return hobbyCards;
    }

    @GetMapping("/{id}")
    public HobbyCard getHobbyCard(@PathVariable String id) {
        return hobbyCardRepository.findById(UUID.fromString(id)).orElse(null);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public HobbyCard createHobbyCard(@RequestBody HobbyCard hobbyCard) {
        return hobbyCardRepository.save(hobbyCard);
    }

    @PutMapping("/update")
    public void updateHobbyCard(@RequestBody HobbyCard hobbyCard) {
        UUID hobbyCardId = hobbyCard.getId();
        Optional<HobbyCard> hobbyCardData = hobbyCardRepository.findById(hobbyCardId);
        if (hobbyCardData.isPresent()) {
            HobbyCard _hobbyCard = hobbyCardData.get();
            _hobbyCard.setDescription(hobbyCard.getDescription());
            hobbyCardRepository.save(_hobbyCard);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHobbyCard(@PathVariable String id) {
        UUID hobbyCardId = UUID.fromString(id);
        hobbyCardRepository.deleteById(hobbyCardId);
    }

}
