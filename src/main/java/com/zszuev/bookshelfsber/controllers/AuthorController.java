package com.zszuev.bookshelfsber.controllers;

import com.zszuev.bookshelfsber.entities.Author;
import com.zszuev.bookshelfsber.services.AuthorService;
import com.zszuev.bookshelfsber.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
@Api(tags = "Авторы")
public class AuthorController {

    private final AuthorService authorService;
    private final ResponseEntity<String> notFoundResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found with the given ID.");

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ApiOperation("Получить всех авторов")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{authorId}")
    @ApiOperation("Получить автора по ID")
    public ResponseEntity<?> getAuthor(
            @ApiParam("Получение автора по ID")
            @PathVariable Long authorId
    ) {
        Optional<Author> authorOptional = authorService.getAuthorById(authorId);

        if (authorOptional.isPresent()) {
            return ResponseEntity.ok(authorOptional.get());
        } else {
            return notFoundResponse;
        }
    }

    @PostMapping
    @ApiOperation("Добавить автора")
    public Author createAuthor(
            @ApiParam(value = "Добавление автора")
            @RequestBody Author author
    ) {
        return authorService.createAuthor(author);
    }

    @PutMapping("/{authorId}")
    @ApiOperation("Изменить автора")
    public ResponseEntity<?> updateAuthor(
            @ApiParam(value = "Изменить автора по ID")
            @PathVariable Long authorId,
            @RequestBody Author updatedAuthor
    ) {
        Author afterUpdate = authorService.updateAuthor(authorId, updatedAuthor);
        if (afterUpdate!=null){
            return ResponseEntity.ok(afterUpdate);
        } else {
            return notFoundResponse;
        }
    }

    @DeleteMapping("/{authorId}")
    @ApiOperation("Удалить автора")
    public ResponseEntity<String> deleteAuthor(
            @ApiParam("При удалении автора по ID все его книги также удаляются")
            @PathVariable Long authorId
    ) {
        if (authorService.deleteAuthor(authorId)){
            return ResponseEntity.ok("Author deleted successfully");
        } else {
            return notFoundResponse;
        }
    }
}