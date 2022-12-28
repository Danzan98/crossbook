package ru.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "book_record")
public class BookRecord extends PersistenceEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "capture_date")
    private LocalDate captureDate;
    @OneToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "id")
    private Reader reader;
}