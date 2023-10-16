package com.zszuev.bookshelfsber.repositories;

import com.zszuev.bookshelfsber.entities.Author;
import com.zszuev.bookshelfsber.entities.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Component
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByIsAvailable(Boolean isAvailable);

    @Query("SELECT b FROM Book b WHERE b.authorId = :authorId")
    List<Book> findByAuthorId(Long authorId);

    @Query("SELECT b FROM Book b WHERE b.isAvailable = :isAvailable ORDER BY :sort")
    List<Book> findByIsAvailableAndSort(@Param("isAvailable") Boolean isAvailable, @Param("sort") String sort);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.title = :title, b.isAvailable = :isAvailable, b.authorId = :authorId WHERE b.id = :bookId")
    void updateBook(
            @Param("bookId") Long bookId,
            @Param("title") String title,
            @Param("isAvailable") Boolean isAvailable,
            @Param("authorId") Long authorId
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Book b WHERE b.id = :bookId")
    void deleteBook(@Param("bookId") Long bookId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Book b WHERE b.authorId = :authorId")
    void deleteAuthorBooks(@Param("authorId") Long authorId);
}
