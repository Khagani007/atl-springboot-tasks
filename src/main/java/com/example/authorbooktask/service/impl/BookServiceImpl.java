package com.example.authorbooktask.service.impl;

import com.example.authorbooktask.dto.BookDTO;
import com.example.authorbooktask.entity.AuthorEntity;
import com.example.authorbooktask.entity.BookEntity;
import com.example.authorbooktask.exceptions.ResourceNotFoundException;
import com.example.authorbooktask.mapper.BookMapper;
import com.example.authorbooktask.repository.AuthorRepository;
import com.example.authorbooktask.repository.BookRepository;
import com.example.authorbooktask.service.BookService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO save(BookDTO bookDto) {
        logger.info("Saving book: {}", bookDto);
        AuthorEntity author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> {
                    logger.error("Author not found with ID: {}", bookDto.getAuthorId());
                    return new ResourceNotFoundException("Author not found");
                });
        BookEntity bookEntity = bookMapper.toEntity(bookDto, author);
        BookDTO savedBook = bookMapper.toDto(bookRepository.save(bookEntity));
        logger.info("Book saved successfully with ID: {}", savedBook.getId());
        return savedBook;
    }

    @Override
    public BookDTO getById(Long id) {
        logger.info("Fetching book with ID: {}", id);
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> {
                    logger.error("Book not found with ID: {}", id);
                    return new ResourceNotFoundException("Book not found");
                });
    }

    @Override
    public List<BookDTO> getAll() {
        logger.debug("Fetching all books");
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void delete(Long id) {
        logger.debug("Deleting book with ID: {}", id);
        if (!bookRepository.existsById(id)) {
            logger.error("Book not found with ID: {}", id);
            throw new ResourceNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
        logger.info("Book with ID: {} deleted successfully", id);
    }
}
