package ru.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.example.dto.BookRecordDto;
import ru.example.dto.ReaderDto;
import ru.example.exception.WebApplicationException;
import ru.example.factory.BookRecordDtoFactory;
import ru.example.factory.ReaderDtoFactory;
import ru.example.repository.BookRecordRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestUtils.randomLong;
import static utils.TestUtils.randomString;

@SpringBootTest
public class BookRecordServiceTest {
    @Autowired
    BookRecordService bookRecordService;

    @Autowired
    ReaderService readerService;

    @Autowired
    BookRecordRepository bookRecordRepository;

    private BookRecordDto dto;

    @BeforeEach
    public void beforeEach() {
        bookRecordRepository.deleteAll();
    }

    @Test
    public void testSaveAndGetRecordById() {
        initializeDto();

        BookRecordDto exist = bookRecordService.getRecordById(dto.getId());

        assertNotNull(exist);
        assertEquals(dto, exist);
    }

    @Test
    public void testUpdateRecord() {
        initializeDto();
        dto.setAuthor(randomString());

        BookRecordDto updatedRecord = bookRecordService.updateBookRecord(dto.getId(), dto);

        assertEquals(dto, updatedRecord);
    }

    @Test
    public void testUpdateRecordWithoutReader() {
        initializeDto();
        Long randomReaderId = randomLong(10);
        dto.setReaderId(randomReaderId);

        Exception exception = assertThrows(WebApplicationException.class, () -> bookRecordService.updateBookRecord(dto.getId(), dto));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(randomReaderId.toString()));
    }

    @Test
    public void testDeleteRecord() {
        initializeDto();
        Long id = dto.getId();

        bookRecordService.deleteBookRecordById(id);

        Exception exception = assertThrows(WebApplicationException.class, () -> bookRecordService.getRecordById(id));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(id.toString()));
    }


    private void initializeDto() {
        ReaderDto readerDto = readerService.saveReader(ReaderDtoFactory.getReaderDto());
        dto = bookRecordService.saveBookRecord(BookRecordDtoFactory.getBookRecordDto(readerDto.getId()));
    }

}
