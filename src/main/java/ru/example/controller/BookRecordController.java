package ru.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.dto.BookRecordDto;
import ru.example.service.BookRecordService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/record")
public class BookRecordController {

    private final BookRecordService bookRecordService;

    @GetMapping
    public ResponseEntity<List<BookRecordDto>> getAllRecords() {
        return ResponseEntity.ok(bookRecordService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRecordDto> getReader(@PathVariable Long id) {
        return ResponseEntity.ok(bookRecordService.getRecordById(id));
    }

    @PostMapping
    public ResponseEntity<BookRecordDto> create(@RequestBody BookRecordDto bookRecordDto) {
        return ResponseEntity.ok(bookRecordService.saveBookRecord(bookRecordDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookRecordDto> update(@PathVariable Long id, @RequestBody BookRecordDto bookRecordDto) {
        return ResponseEntity.ok(bookRecordService.updateBookRecord(id, bookRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookRecordService.deleteBookRecordById(id);
        return ResponseEntity.noContent().build();
    }
}
