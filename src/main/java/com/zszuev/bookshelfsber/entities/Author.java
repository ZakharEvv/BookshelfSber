package com.zszuev.bookshelfsber.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birthdate")
    private String birthdate;

    @Column(name = "biography")
    private String biography;

    public Author() {}
    public Author(String firstName, String lastName, String middleName, String birthdate, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthdate = birthdate;
        this.biography = biography;
    }


    public void setId(Long id) {
        this.id = id;
    }


}
