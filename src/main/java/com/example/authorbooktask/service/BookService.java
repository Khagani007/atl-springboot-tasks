package com.example.authorbooktask.service;

import com.example.authorbooktask.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO save(BookDTO bookDto);
    BookDTO getById(Long id);
    List<BookDTO> getAll();
    void delete(Long id);
}
