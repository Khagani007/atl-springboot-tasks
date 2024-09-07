package com.example.authorbooktask.mapper;

import com.example.authorbooktask.dto.AuthorDTO;
import com.example.authorbooktask.entity.AuthorEntity;
import com.example.authorbooktask.entity.BookEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {
    public AuthorDTO toDto(AuthorEntity entity) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        return dto;
    }

    public AuthorEntity toEntity(AuthorDTO dto) {
        AuthorEntity entity = new AuthorEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        return entity;
    }
}
