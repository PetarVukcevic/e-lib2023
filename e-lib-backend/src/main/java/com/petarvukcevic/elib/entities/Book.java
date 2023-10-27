package com.petarvukcevic.elib.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title", length =255)
    private String title;

    @Column(name = "author", length =45)
    private String author;

    @Column(name = "genre", length =255)
    private String genre;

    @Column(name = "pages", length =255)
    private Integer pages;

    @Column(name = "created_at")
    @CreationTimestamp()
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp()
    private Date updatedAt;
}
