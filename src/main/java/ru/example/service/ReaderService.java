package ru.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.example.dto.ReaderDto;
import ru.example.exception.WebApplicationException;
import ru.example.mapper.ReaderMapper;
import ru.example.model.Reader;
import ru.example.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    public List<ReaderDto> getAllReaders() {
        return readerMapper.toSource(readerRepository.findAll());
    }

    public ReaderDto getReaderById(Long id) {
        Reader reader = readerRepository.findById(id)
                .orElseThrow(() -> new WebApplicationException("Reader wasn't found, id: " + id, HttpStatus.NOT_FOUND));
        return readerMapper.toSource(reader);
    }

    public ReaderDto saveReader(ReaderDto readerDto) {
        Reader reader = readerMapper.toTarget(readerDto);
        return readerMapper.toSource(readerRepository.save(reader));
    }

    public ReaderDto updateReader(Long id, ReaderDto readerDto) {
        Reader reader = readerRepository.findById(id)
                .orElseThrow(() -> new WebApplicationException("Reader wasn't found, id: " + id, HttpStatus.NOT_FOUND));
        Reader updatedReader = readerMapper.toTarget(readerDto, reader);
        return readerMapper.toSource(readerRepository.save(updatedReader));
    }

    public void deleteReaderById(Long id) {
        readerRepository.deleteById(id);
    }
}
