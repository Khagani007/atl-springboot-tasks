package com.example.authorbooktask.mapper;

import com.example.authorbooktask.dto.BookDTO;
import com.example.authorbooktask.entity.AuthorEntity;
import com.example.authorbooktask.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO toDto(BookEntity entity) {
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCount(entity.getCount());
        dto.setAuthorId(entity.getAuthor().getId());
        return dto;
    }

    public BookEntity toEntity(BookDTO dto, AuthorEntity author) {
        BookEntity entity = new BookEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCount(dto.getCount());
        entity.setAuthor(author);
        return entity;
    }
}
