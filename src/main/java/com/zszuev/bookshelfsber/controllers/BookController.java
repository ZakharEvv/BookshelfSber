package com.zszuev.bookshelfsber.controllers;

import com.zszuev.bookshelfsber.entities.Author;
import com.zszuev.bookshelfsber.entities.Book;
import com.zszuev.bookshelfsber.services.AuthorService;
import com.zszuev.bookshelfsber.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Api(tags = "Книги")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final ResponseEntity<String> bookNotFoundResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with the given ID.");
    private final ResponseEntity<String> authorNotFoundResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found with the given ID.");

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    @ApiOperation("Получить все книги с возможностью сортировки и фильтрации")
    public List<Book> getAllBooks(
            @ApiParam(value = "Сортировка книг по определенному полю.", allowableValues = "id, title, isAvailable, authorId")
            @RequestParam(required = false) String sortBy,
            @ApiParam(value = "Фильтрация по наличию на полке.", allowableValues = "true, false")
            @RequestParam(required = false) Boolean isAvailable
    ) {
        if (sortBy != null && isAvailable != null) {
            return bookService.getAllBooksSortedAndFiltered(sortBy, isAvailable);
        } else if (sortBy != null) {
            return bookService.getAllBooksSorted(sortBy);
        } else if (isAvailable != null) {
            return bookService.getAllBooksFiltered(isAvailable);
        } else {
            return bookService.getAllBooks();
        }
    }

    @GetMapping("/{bookId}")
    @ApiOperation("Получить книгу по ID")
    public ResponseEntity<?> getBook(
            @ApiParam(value = "Получение книги по ID")
            @PathVariable Long bookId
    ) {
        Optional<Book> bookOptional = bookService.getBookById(bookId);

        if (bookOptional.isPresent()) {
            return ResponseEntity.ok(bookOptional.get());
        } else {
            return bookNotFoundResponse;
        }
    }

    @GetMapping("/author/{authorId}")
    @ApiOperation("Получить книги по ID автора")
    public ResponseEntity<?> getBooksByAuthor(
            @ApiParam(value = "Получение книг по ID автора")
            @PathVariable Long authorId) {
        Author author = authorService.getAuthorById(authorId).orElse(null);
        if (author != null) {
            return ResponseEntity.ok(bookService.getBooksByAuthorId(authorId));
        }
        return authorNotFoundResponse;
    }

    @PostMapping
    @ApiOperation("Добавить книгу")
    public ResponseEntity<?> createBook(
            @ApiParam(value = "Добавление книги")
            @RequestBody Book book
    ) {
        try {
            Book savedBook = bookService.createBook(book);
            return ResponseEntity.ok(savedBook);
        } catch(Exception e) {
            return authorNotFoundResponse;
        }
    }

    @PutMapping("/{bookId}")
    @ApiOperation("Изменить книгу")
    public ResponseEntity<?> updateBook(
            @ApiParam(value = "Изменить автора по ID")
            @PathVariable Long bookId,
            @RequestBody Book updatedBook) {
        try {
            Book afterUpdate = bookService.updateBook(bookId, updatedBook);
            if (afterUpdate!=null){
                return ResponseEntity.ok(afterUpdate);
            } else {
                return bookNotFoundResponse;
            }
        } catch(Exception e) {
            return authorNotFoundResponse;
        }
    }

    @PutMapping("/{bookId}/setAvailability")
    @ApiOperation("Изменить наличие книги на полке")
    public ResponseEntity<String> setAvailability(
            @ApiParam(value = "Изменение наличия книги на полке")
            @PathVariable Long bookId,
            @RequestParam Boolean isAvailable
    ) {
        if (bookService.setAvailability(bookId, isAvailable)) {
            return ResponseEntity.ok("Availability updated successfully.");
        } else {
            return bookNotFoundResponse;
        }
    }

    @DeleteMapping("/{bookId}")
    @ApiOperation("Удалить книгу")
    public ResponseEntity<String> deleteBook(
            @ApiParam("Удаление книги по ID")
            @PathVariable Long bookId
    ) {
        if (bookService.deleteBook(bookId)) {
            return ResponseEntity.ok("Book deleted successfully.");
        } else {
            return bookNotFoundResponse;
        }
    }
}
