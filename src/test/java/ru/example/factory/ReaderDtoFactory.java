package ru.example.factory;

import ru.example.dto.ReaderDto;

import java.time.LocalDate;

import static utils.TestUtils.randomLong;
import static utils.TestUtils.randomString;

public class ReaderDtoFactory {

    public static ReaderDto getReaderDto() {
        return  ReaderDto.builder()
                .id(randomLong(10))
                .name(randomString())
                .birthday(LocalDate.now())
                .bookAmount(randomLong(5))
                .build();
    }
}
