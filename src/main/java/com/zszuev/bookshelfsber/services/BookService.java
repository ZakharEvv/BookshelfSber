package com.zszuev.bookshelfsber.services;

import com.zszuev.bookshelfsber.entities.Author;
import com.zszuev.bookshelfsber.entities.Book;
import com.zszuev.bookshelfsber.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAllBooksSorted(String sortBy) {
        return bookRepository.findAll(Sort.by(sortBy));
    }

    public List<Book> getAllBooksFiltered(Boolean isAvailable) {
        return bookRepository.findByIsAvailable(isAvailable);
    }

    public List<Book> getAllBooksSortedAndFiltered(String sortBy, Boolean isAvailable) {
        return bookRepository.findByIsAvailableAndSort(isAvailable, sortBy);
    }

    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book updatedBook) {
        if (bookRepository.existsById(bookId)) {
            updatedBook.setId(bookId);
            return bookRepository.save(updatedBook);
        }
        return null;
    }

    public boolean setAvailability(Long bookId, Boolean isAvailable) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setIsAvailable(isAvailable);
            bookRepository.save(book); // Сохранение изменений
            return true; // Успешно установлено значение isAvailable
        }

        return false; // Книга с заданным идентификатором не найдена
    }

    public boolean deleteBook(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }
}
