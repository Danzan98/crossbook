package ru.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.dto.ReaderDto;
import ru.example.service.ReaderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/reader")
public class ReaderController {
    private final ReaderService readerService;

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getAllReaders() {
        return ResponseEntity.ok(readerService.getAllReaders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable Long id) {
        return ResponseEntity.ok(readerService.getReaderById(id));
    }

    @PostMapping
    public ResponseEntity<ReaderDto> create(@RequestBody ReaderDto readerDto) {
        return ResponseEntity.ok(readerService.saveReader(readerDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReaderDto> update(@PathVariable Long id, @RequestBody ReaderDto readerDto) {
        return ResponseEntity.ok(readerService.updateReader(id, readerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        readerService.deleteReaderById(id);
        return ResponseEntity.noContent().build();
    }
}
