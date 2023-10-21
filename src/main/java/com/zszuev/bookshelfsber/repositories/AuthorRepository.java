package com.zszuev.bookshelfsber.repositories;

import com.zszuev.bookshelfsber.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Author a WHERE a.id = :authorId")
    void deleteAuthor(@Param("authorId") Long authorId);
}
