package com.zszuev.bookshelfsber.services;

import com.zszuev.bookshelfsber.entities.Author;
import com.zszuev.bookshelfsber.repositories.AuthorRepository;
import com.zszuev.bookshelfsber.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long authorId) {
        return authorRepository.findById(authorId);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long authorId, Author updatedAuthor) {
        if (authorRepository.existsById(authorId)) {
            updatedAuthor.setId(authorId);
            return authorRepository.save(updatedAuthor);
        }
        return null;
    }

    public boolean deleteAuthor(Long authorId) {
        if (authorRepository.existsById(authorId)) {
            bookRepository.deleteAuthorBooks(authorId);
            authorRepository.deleteById(authorId);
            return true;
        }
        return false;
    }
}
