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
    @Query("UPDATE Author a SET a.firstName = :firstName, a.lastName = :lastName, a.middleName = :middleName, a.birthdate = :birthdate, a.biography = :biography WHERE a.id = :authorId")
    void updateAuthor(
            @Param("authorId") Long authorId,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("middleName") String middleName,
            @Param("birthdate") String birthdate,
            @Param("biography") String biography
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Author a WHERE a.id = :authorId")
    void deleteAuthor(@Param("authorId") Long authorId);
}
