package ru.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
