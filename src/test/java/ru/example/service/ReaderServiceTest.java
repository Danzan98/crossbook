package ru.example.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.example.dto.ReaderDto;
import ru.example.exception.WebApplicationException;
import ru.example.factory.ReaderDtoFactory;
import ru.example.repository.ReaderRepository;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.randomLong;

@SpringBootTest
public class ReaderServiceTest {

    @Autowired
    ReaderService readerService;

    @Autowired
    ReaderRepository readerRepository;

    private ReaderDto dto;

    @BeforeEach
    public void beforeEach() {
        readerRepository.deleteAll();
    }

    @Test
    public void testSaveAndGetReaderById() {
        initializeDto();

        ReaderDto exist = readerService.getReaderById(dto.getId());

        assertNotNull(exist);
        assertEquals(dto, exist);
    }

    @Test
    public void testUpdateReader() {
        initializeDto();
        dto.setBookAmount(randomLong(5));
        ReaderDto updatedReader = readerService.updateReader(dto.getId(), dto);

        assertEquals(dto, updatedReader);
    }

    @Test
    public void testDeleteReader() {
        initializeDto();
        Long id = dto.getId();

        readerService.deleteReaderById(id);

        Exception exception = assertThrows(WebApplicationException.class, () -> readerService.getReaderById(id));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(id.toString()));
    }

    private void initializeDto() {
        dto = readerService.saveReader(ReaderDtoFactory.getReaderDto());
    }
}
