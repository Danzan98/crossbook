package ru.example.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.example.dto.BookRecordDto;
import ru.example.exception.WebApplicationException;
import ru.example.mapper.mapper.AbstractBidirectionalMapper;
import ru.example.model.BookRecord;
import ru.example.model.Reader;
import ru.example.repository.ReaderRepository;

@Component
@RequiredArgsConstructor
public class BookRecordMapper extends AbstractBidirectionalMapper<BookRecordDto, BookRecord> {

    private final ReaderRepository readerRepository;
    @Override
    public BookRecordDto toSource(BookRecord target) {
        BookRecordDto bookRecordDto = BookRecordDto.builder()
                .id(target.getId())
                .name(target.getName())
                .author(target.getAuthor())
                .captureDate(target.getCaptureDate()).build();
        if (target.getReader() != null) {
            bookRecordDto.setReaderId(target.getReader().getId());
        }
        return bookRecordDto;
    }

    @Override
    public BookRecord toTarget(BookRecordDto source) {
        BookRecord bookRecord = new BookRecord();
        return toTarget(source, bookRecord);
    }

    public BookRecord toTarget(BookRecordDto source, BookRecord bookRecord) {
        bookRecord.setId(source.getId());
        bookRecord.setId(source.getId());
        bookRecord.setName(source.getName());
        bookRecord.setAuthor(source.getAuthor());
        bookRecord.setCaptureDate(source.getCaptureDate());
        Reader reader = readerRepository.findById(source.getReaderId())
                .orElseThrow(() -> new WebApplicationException("Reader wasn't found, id: " + source.getReaderId(), HttpStatus.NOT_FOUND));
        reader.setBookAmount(reader.getBookAmount() + 1);
        readerRepository.save(reader);
        bookRecord.setReader(reader);
        return bookRecord;
    }
}
