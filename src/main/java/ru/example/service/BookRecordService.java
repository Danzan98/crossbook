package ru.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.example.dto.BookRecordDto;
import ru.example.exception.WebApplicationException;
import ru.example.mapper.BookRecordMapper;
import ru.example.model.BookRecord;
import ru.example.repository.BookRecordRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookRecordService {

    private final BookRecordRepository bookRecordRepository;
    private final BookRecordMapper bookRecordMapper;

    public List<BookRecordDto> getAll() {
        return bookRecordMapper.toSource(bookRecordRepository.findAll());
    }

    public BookRecordDto getRecordById(Long id) {
        BookRecord record = bookRecordRepository.findById(id)
                .orElseThrow(() -> new WebApplicationException("BookRecord wasn't found, id: " + id, HttpStatus.NOT_FOUND));
        return bookRecordMapper.toSource(record);
    }

    public BookRecordDto saveBookRecord(BookRecordDto bookRecordDto) {
        BookRecord record = bookRecordMapper.toTarget(bookRecordDto);
        return bookRecordMapper.toSource(bookRecordRepository.save(record));
    }

    @Transactional
    public BookRecordDto updateBookRecord(Long id, BookRecordDto bookRecordDto) {
        BookRecord bookRecord = bookRecordRepository.findById(id)
                .orElseThrow(() -> new WebApplicationException("BookRecord wasn't found, id: " + id, HttpStatus.NOT_FOUND));
        BookRecord updatedRecord = bookRecordMapper.toTarget(bookRecordDto, bookRecord);
        return bookRecordMapper.toSource(bookRecordRepository.save(updatedRecord));
    }

    public void deleteBookRecordById(Long id) {
        bookRecordRepository.deleteById(id);
    }
}
