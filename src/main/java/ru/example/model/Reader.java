package ru.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "reader")
public class Reader extends PersistenceEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "amount")
    private Long bookAmount;
}