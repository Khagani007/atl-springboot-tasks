package com.example.authorbooktask.repository;

import com.example.authorbooktask.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
