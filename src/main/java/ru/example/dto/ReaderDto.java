package ru.example.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReaderDto {
    private Long id;
    private String name;
    private LocalDate birthday;
    private Long bookAmount;
}
