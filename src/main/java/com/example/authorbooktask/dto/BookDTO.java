package com.example.authorbooktask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Positive(message = "Count must be positive")
    private int count;

    private Long authorId;
}
