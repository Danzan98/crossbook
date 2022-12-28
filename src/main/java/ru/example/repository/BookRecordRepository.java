package ru.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.model.BookRecord;

public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {
}
