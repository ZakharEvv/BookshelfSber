package com.zszuev.bookshelfsber.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "author_id")
    private Long authorId;

    public Book() {}
    public Book(String title, boolean isAvailable, Long authorId) {
        this.title = title;
        this.isAvailable = isAvailable;
        this.authorId = authorId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsAvailable(boolean isAvailable) {this.isAvailable = isAvailable;}

}
