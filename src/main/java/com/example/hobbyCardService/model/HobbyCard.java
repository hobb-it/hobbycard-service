package com.example.hobbyCardService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "HobbyCard")
@Table(name = "HobbyCard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HobbyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="description")
    private String description;

    @Column(name="categoryName")
    private String categoryName;

    @Column(name="username")
    private String username;
}
