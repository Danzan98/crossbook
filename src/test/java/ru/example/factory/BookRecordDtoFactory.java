package ru.example.factory;

import ru.example.dto.BookRecordDto;

import java.time.LocalDate;

import static utils.TestUtils.randomLong;
import static utils.TestUtils.randomString;

public class BookRecordDtoFactory {
    public static BookRecordDto getBookRecordDto(Long readerId) {
        return BookRecordDto.builder()
                .id(randomLong(10))
                .name(randomString())
                .author(randomString())
                .captureDate(LocalDate.now())
                .readerId(readerId)
                .build();
    }
}
