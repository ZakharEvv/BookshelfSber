package com.zszuev.bookshelfsber.repositories;

import com.zszuev.bookshelfsber.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByIsAvailable(Boolean isAvailable);

    @Query("SELECT b FROM Book b WHERE b.authorId = :authorId")
    List<Book> findByAuthorId(Long authorId);

    @Query("SELECT b FROM Book b WHERE b.isAvailable = :isAvailable ORDER BY :sort")
    List<Book> findByIsAvailableAndSort(@Param("isAvailable") Boolean isAvailable, @Param("sort") String sort);
}
