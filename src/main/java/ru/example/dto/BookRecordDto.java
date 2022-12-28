package ru.example.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookRecordDto {
    private Long id;
    private String name;
    private String author;
    private LocalDate captureDate;
    private Long readerId;
}
